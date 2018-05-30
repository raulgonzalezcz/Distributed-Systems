/*
Se lleva a cabo la seralización de los datos, regersando el éxito(TRUE) o fracaso(FALSE) de la operación.
Utiliza XDR (External Data Representation) para convertir los dos enteros a un formato estándar.
*/


#include "suma.h"

bool_t
xdr_intpair (XDR *xdrs, intpair *objp)
{
	register int32_t *buf;

	 if (!xdr_int (xdrs, &objp->a)) /*serialización del primer entero*/
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->b)) /*serialización del segundo entero*/
		 return FALSE;
	return TRUE;
}
