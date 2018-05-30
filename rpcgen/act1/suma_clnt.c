/* Stub generado para nuestro cliente; serializa (marshalling) parámetros:

clnt  (Input): Un puntero a la estructura de control de cliente que resulta de llamar a una función de creación de cliente que utiliza 
una llamada de procedimiento remoto (RPC) como la API clnt_create ().
procnum  (Input): El procedimiento en la máquina host.
inproc  (Input): El nombre del procedimiento XDR que codifica los parámetros de procedimiento.
in  (Input): La dirección de los argumentos del procedimiento.
outproc  (Input): El nombre del procedimiento XDR que decodifica los resultados del procedimiento.
out  (Output): La dirección donde se colocan los resultados.
tout  (Input): El tiempo permitido para que el servidor responda											*/

//Liberías a utilizar
#include <memory.h> /* for memset */
#include "suma.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

//Función stub del cliente que implementa la función de nuestro programa (suma). Llama al procedimiento remoto y devuelve el resultado.
int *
suma_1(intpair *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, SUMA,
		(xdrproc_t) xdr_intpair, (caddr_t) argp,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}
