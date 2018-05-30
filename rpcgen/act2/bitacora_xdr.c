/*
Se lleva a cabo la seralización de los datos, regersando el éxito(TRUE) o fracaso(FALSE) de la operación.
Utiliza XDR (External Data Representation) para convertir los dos enteros a un formato estándar.
*/

#include "bitacora.h"


bool_t
xdr_String (XDR *xdrs, String *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_vector (xdrs, (char *)objp->opt, 100,	/*serialización del la opcion (ADD o SEARCH)*/
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	 if (!xdr_vector (xdrs, (char *)objp->name, 100, /*serialización del nombre*/
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	return TRUE;
}
