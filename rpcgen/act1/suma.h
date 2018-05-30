/* Archivo de cabecera que incluiremos en nuestro cliente y código de servidor. También define SUMA_PROG y una función para checar 
la consistencia del parámetro enviado (xdr_intpair), la interfaz del cliente (suma_1) y la interfaz para la función de servidor 
que vamos a tener que escribir (suma_1_svc). 

#ifdef compila si su "identifier" ya ha sido definido para el preprocesador en la directiva #define. 
#ifndef compila si identifier en este momento no está definido por la directiva del preprocesador #define.
#define declaración de constante 
*/

//Directivas (parámetros generales) para definir un identificador que el compilador utilizará
#ifndef _SUMA_H_RPCGEN
#define _SUMA_H_RPCGEN

//Importación de librerías de RPC
#include <rpc/rpc.h>

//Establecer compatibilidad con C/C++
#ifdef __cplusplus
extern "C" {
#endif

/*Define la estructura que definimos (intpair) y typedefs a un tipo del mismo nombre en suma.x 
que usaran el cliente-servidor para serialización*/
struct intpair {
	int a;
	int b;
};
typedef struct intpair intpair;

//Directivas para definir símbolos (id único del programa en hexadecimal y la versión del programa)
#define SUMA_PROG 0x23451111
#define SUMA_VERS 1

//En caso de ser codigo C/C++
#if defined(__STDC__) || defined(__cplusplus)
#define SUMA 1
extern  int * suma_1(intpair *, CLIENT *);			//Interfaz stub del cliente
extern  int * suma_1_svc(intpair *, struct svc_req *);		//Procedimiento recomoto del servidor
extern int suma_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t); //Resultado dada la versión de nuestro programa

#else /* Para cualquier otra versión de C: K&R C */
#define SUMA 1
extern  int * suma_1();			//Interfaz stub del cliente
extern  int * suma_1_svc();		//Procedimiento recomoto del servidor
extern int suma_prog_1_freeresult ();	//Resultado de la versión de nuestra suma
#endif /* K&R C */

/* the xdr functions */
//Checamos la consistencia de la estructura enviada con xdr_intpair
#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_intpair (XDR *, intpair*);

#else /* Para cualquier otra versión de C: K&R C */
extern bool_t xdr_intpair ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_SUMA_H_RPCGEN */
