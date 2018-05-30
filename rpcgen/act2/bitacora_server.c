/* Código en donde implementaremos el procedimiento remoto. */

//Librerías a utilizar
#include "bitacora.h"
#include <time.h>		//Librería para registrar la fecha
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

//Primer procedimiento remoto: ADD
char **
add_1_svc(char **argp, struct svc_req *rqstp)
{
/*result es estática debido a que las variables locales viven en el stack, 
esto es un problema por que el espacio en memoria puede ser solicitado para ser utilizado por el servidor y causar errores.  */
	static char * result;
	time_t timeres = time(NULL);			//Variable de tiempo
	
	FILE *f = fopen("file.txt", "a+"); 		
	//checamos si file.txt existe
	if (f == NULL) {
    printf("Error opening file!\n");			//Si no se puede leer, notificamos al usuario
    exit(1);
	}
	char * time = asctime(localtime(&timeres));	//Registramos la fecha en ese momento dado
	fprintf(f, "%s %s", *argp, time);
	printf("Server Added:%s %s", *argp, time);	//Imprimimos con éxito la operación realizada
	fclose(f);
	asprintf(&result, "%s %s", *argp, time);
	return &result;
}

//Segundo procedimiento remoto: SEARCH
char **
search_1_svc(char **argp, struct svc_req *rqstp)
{
	static char * result;
	printf("Server is searching for: %s \n", *argp);	
	
	FILE* fh = fopen("file.txt", "r");
	//checamos si file.txt existe
	if (fh == NULL){
    printf("file does not exists");
    return 0;
	}
 	char buff[1000];		//Tamaño del buffer donde almacenaremos el resultado de la búsqueda
  	char *s;
	//Iniciamos proceso de lectura de archivo leyendo linea por linea
	const size_t line_size = 300;	//Tamaño de la linea a leer
	char* line = malloc(line_size);
	int count = 0;			//Variable en caso de haber más de una ocurrencia
	while (fgets(line, line_size, fh) != NULL){
  	//printf("%s",line);
  	//Imprimimos cada ocurrencia encontrada
  	s = strstr(line, *argp);
 		if (s != NULL) {
     count++;
    }
    else {
			printf("String not found\n"); //No encontramos a la persona
 		}
	}
	//Imprimimos el número de registros hallados
  	printf("Registry found: %d times \n", count);
	asprintf(&result, "Server Found, %s %d times", *argp, count);
	return &result;
}
