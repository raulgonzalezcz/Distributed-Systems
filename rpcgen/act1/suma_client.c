/* Crea el cliente con la función clnt_create cuyos parámetros son: 
host: dirección del servidor (nombre del host remoto donde se encuentra el servidor), SUMA_PROG: número del programa remoto
SUMA_VERS: versión del programa remoto, udp: protocolo de red (protocolo de transporte)										*/

#include "suma.h"
#include <stdio.h>

//Recibe los argumentos proporcionados en el Main
void
suma_prog_1(char *host, int a, int b)
{
	CLIENT *clnt;
	int  *result_1;
	intpair  suma_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, SUMA_PROG, SUMA_VERS, "udp");	//Parámetros para crear el cliente
	if (clnt == NULL) {				//En caso de que no se haya creado el cliente
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */
	
	suma_1_arg.a = a;				//Datos a sumar
	suma_1_arg.b = b;
	result_1 = suma_1(&suma_1_arg, clnt); 	//Hace la llamada al procedimiento suma_1 del stub suma_clnt.c
	if (result_1 == (int *) NULL) { 		//En caso de que no se haga conexión con el server, se notifica error
		clnt_perror (clnt, "call failed");
	}  else {
	    printf("result = %d\n", *result_1);		//Imprimimos con éxito el resultado
	}	
#ifndef	DEBUG
	clnt_destroy (clnt);				//Matamos el cliente
#endif	 /* DEBUG */
}

/* Función main del lado del cliente. Toma como argumentos la dirección del servidor y los datos a sumar */
int
main(int argc, char *argv[]) {
        char *host;
        int a, b;
	//Condiciones para que el cliente proporcione adecuadamente los datos
        if (argc != 4) { 
            printf ("usage: %s server_host num1 num2\n", argv[0]);
            exit(1);
        }
        host = argv[1];
        if ((a = atoi(argv[2])) == 0 && *argv[2] != '0') {
            fprintf(stderr, "invalid value: %s\n", argv[2]);
            exit(1);
        }
        if ((b = atoi(argv[3])) == 0 && *argv[3] != '0') {
            fprintf(stderr, "invalid value: %s\n", argv[3]);
            exit(1);
        }
        suma_prog_1(host, a, b);	//Los datos ingresados son correctos para iniciar el proceso de suma
}
