/* Crea el cliente con la función clnt_create cuyos parámetros son: 
host: dirección del servidor (nombre del host remoto donde se encuentra el servidor), BITACORA_PROG: número del programa remoto
BITACORA_VERS: versión del programa remoto, udp: protocolo de red (protocolo de transporte)		*/
#include "bitacora.h"
#include <stdio.h>
#include <stdlib.h>
//Recibe los argumentos proporcionados en el Main
void bitacora_prog_1(char *host, char* opt, char *name)
{
    CLIENT *clnt;		/*Cliente que se creará*/
	char * *result_1;	/*Resultado en caso de ser add*/
	char * add_1_arg;	/*opción add*/
	char * *result_2;	/*Resultado en caso de ser search*/
	char * search_1_arg;	/*opción search*/
#ifndef	DEBUG
	clnt = clnt_create (host, BITACORA_PROG, BITACORA_VERS, "udp");
	if (clnt == NULL) {				//En caso de que no se haya creado el cliente
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */
	
	if(strcmp(opt, "add")==0){//opción es igual a "add"
		add_1_arg = name;	//Añadimos el nombre de la persona
		result_1 = add_1(&add_1_arg, clnt);
		if (result_1 == (char **) NULL) {
			clnt_perror (clnt, "call failed");
		} else {
			printf("Server response: %s", *result_1); //Imprimos con éxito el resultado
		}
	}
	if(strcmp(opt, "search")==0){//opción es igual a "search"
		search_1_arg = name;
		result_2 = search_1(&search_1_arg, clnt);	//realizamos la búsqueda de la persona
		if (result_2 == (char **) NULL) {
			clnt_perror (clnt, "call failed");
		} else {
			printf("Server response: %s \n", *result_2); //Imprimos con éxito el resultado
		}
	}	
#ifndef	DEBUG
	clnt_destroy (clnt);		//Matamos el cliente
#endif	 /* DEBUG */
}

/* Función main del lado del cliente. Toma como argumentos la dirección del servidor, una opción (add o search) y el nombre de la persona */
int
main(int argc, char *argv[]) {
        char *host;
        char *name;
        char* opt;
        int id;
//Condiciones para que el cliente proporcione adecuadamente los datos
        if (argc != 4) {
            printf ("usage: %s host opt name\n", argv[0]);
            exit(1);
        }
        host = argv[1]; // Host.
        opt  = argv[2]; //Option.
        name = argv[3]; 

        if (strcmp(opt, "add")!=0 && strcmp(opt, "search")!=0) {
            fprintf(stderr, "invalid value: %s\n", argv[2]);
            exit(1);
        }
        bitacora_prog_1(host, opt, name);		//Los datos ingresados son correctos para iniciar el proceso del programa
}
