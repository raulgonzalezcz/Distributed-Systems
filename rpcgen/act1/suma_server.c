/*
Código en donde implementaremos el procedimiento remoto.
 */

#include "suma.h"

int *
suma_1_svc(intpair *argp, struct svc_req *rqstp)
{
/*Result es estática debido a que las variables locales viven en el stack, esto es un problema por que el espacio en memoria 
puede ser solicitado para ser utilizado por el servidor y causar errores.  */
	static int  result;

	/*
	 * insert server code here
	 */
	//Computamos el resultado y lo enviamos de vuelta al cliente
	printf("Server response to client...\n");
	printf("parameters: %d, %d\n", argp->a, argp->b);
	result = argp->a + argp->b;
	printf("returning: %d\n", result);
	return &result;
}
