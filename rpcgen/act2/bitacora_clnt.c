/* Stub generado para nuestro cliente; serializa (marshalling) de los parámetros*/

//Liberías a utilizar
#include <memory.h> /* for memset */
#include "bitacora.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

//Funciones stub del cliente: Función ADD
char **
add_1(char **argp, CLIENT *clnt)
{
	static char *clnt_res;
	memset((char *)&clnt_res, 0, sizeof(clnt_res)); /*Copia un carácter sin signo a los primeros n caracteres de la cadena apuntada 
	por el argumento clnt_res */
	if (clnt_call (clnt, ADD,
		(xdrproc_t) xdr_wrapstring, (caddr_t) argp,
		(xdrproc_t) xdr_wrapstring, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

//Funciones stub del cliente: Función SEARCH
char **
search_1(char **argp, CLIENT *clnt)
{
	static char *clnt_res;
	memset((char *)&clnt_res, 0, sizeof(clnt_res));/*Copia un carácter sin signo a los primeros n caracteres de la cadena apuntada 
	por el argumento clnt_res */
	if (clnt_call (clnt, SEARCH,
		(xdrproc_t) xdr_wrapstring, (caddr_t) argp,
		(xdrproc_t) xdr_wrapstring, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}
