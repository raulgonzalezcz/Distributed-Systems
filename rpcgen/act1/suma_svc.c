/* Skeleton generado para el servidor */

#include "suma.h"
#include <stdio.h>
#include <stdlib.h>
#include <rpc/pmap_clnt.h>
#include <string.h>
#include <memory.h>
#include <sys/socket.h>
#include <netinet/in.h>

//Directivas (parámetros generales) para definir un identificador que el compilador utilizará
#ifndef SIG_PF
#define SIG_PF void(*)(int)
#endif

//Función skeleton del servidor que implementa la función de nuestro programa (sumar)
static void
suma_prog_1(struct svc_req *rqstp, register SVCXPRT *transp)
{
	union {
		intpair suma_1_arg;
	} argument;
	char *result;
	xdrproc_t _xdr_argument, _xdr_result;
	char *(*local)(char *, struct svc_req *);

// todos los procedimientos remotos soportados por este programa y esta versión. 
	switch (rqstp->rq_proc) {
//Procedimiento nulo
	case NULLPROC:
		(void) svc_sendreply (transp, (xdrproc_t) xdr_void, (char *)NULL);
		return;

// Procedimiento de suma
	case SUMA:
		_xdr_argument = (xdrproc_t) xdr_intpair;		//Serialización de datos
		_xdr_result = (xdrproc_t) xdr_int;
//se establece un puntero a función (local) a la función de servidor, suma_1_svc		
		local = (char *(*)(char *, struct svc_req *)) suma_1_svc;
		break;

//Procedimiento distinto a los anteriores
	default:
		svcerr_noproc (transp);
		return;
	}
	memset ((char *)&argument, 0, sizeof (argument));
//Unmarshalling de los datos
	if (!svc_getargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		svcerr_decode (transp);
		return;
	}
	result = (*local)((char *)&argument, rqstp);
	if (result != NULL && !svc_sendreply(transp, (xdrproc_t) _xdr_result, result)) {
		svcerr_systemerr (transp);
	}
	if (!svc_freeargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		fprintf (stderr, "%s", "unable to free arguments");
		exit (1);
	}
	return;
}

//Implementa el código principal y es aquí donde se registra el servicio
int
main (int argc, char **argv)
{
	register SVCXPRT *transp;		//Registro del servicio

	pmap_unset (SUMA_PROG, SUMA_VERS);	//Esta rutina borra la entrada para SUMA_PROG de las tablas del mapeador de puertos.

	transp = svcudp_create(RPC_ANYSOCK);	/*Rutina para obtener un identificador de protocolo de datagramas de usuario (UDP) 
	donde la biblioteca RPC crea un socket en el que va a recibir y responder llamadas de procedimiento remoto*/

	if (transp == NULL) {	//No se obtuvo in identificador
		fprintf (stderr, "%s", "cannot create udp service.");
		exit(1);
	}
	if (!svc_register(transp, SUMA_PROG, SUMA_VERS, suma_prog_1, IPPROTO_UDP)) {
/*Un servicio debe registrar su número de puerto con el servicio de asignación de puertos local. 
Esto se hace especificando un número de protocolo distinto de cero en la rutina svc_register, el cual en caso de fallar se notifica al usuario*/
		fprintf (stderr, "%s", "unable to register (SUMA_PROG, SUMA_VERS, udp).");
		exit(1);
	}

	transp = svctcp_create(RPC_ANYSOCK, 0, 0);
//Rutina para obtener un identificador de protocolo de datagramas de usuario (UDP). En los siguientes condicionales se realiza lo mismo descrito anteriormente
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create tcp service.");
		exit(1);
	}
	if (!svc_register(transp, SUMA_PROG, SUMA_VERS, suma_prog_1, IPPROTO_TCP)) {
		fprintf (stderr, "%s", "unable to register (SUMA_PROG, SUMA_VERS, tcp).");
		exit(1);
	}

	svc_run ();
	fprintf (stderr, "%s", "svc_run returned");
	exit (1);
	/* NOTREACHED */
}
