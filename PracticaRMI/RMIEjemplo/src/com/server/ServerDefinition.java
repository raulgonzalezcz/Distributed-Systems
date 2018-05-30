package com.server;
//Bibliotecas para usar RMI
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; /*Exportar un objeto remoto con JRMP y obtener el stub */
//Esta clase implementa de la interfaz TestRemote
import com.interf.test.TestRemote;

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-02-20
 */

/*
Código que implementa cada uno de los servicios remotos de nuestro Server. Recordemos que SIEMPRE se pasa por valor
*/

/*Se desarrolla una clase derivada de UnicastRemoteObject para exportar un objeto remoto con JRMP y obtener el stub 
que se comunica con el objeto remoto. Implementamos la interfaz remota TestRemote
*/
public class ServerDefinition extends UnicastRemoteObject implements TestRemote {
    /** Identificador único de la serialización (Default). */
	private static final long serialVersionUID = 1L;

    /*Constructor de la subclase ServerDefnition para invocar los métodos definidos en TestRemote*/
	protected ServerDefinition() throws RemoteException {
		super();
	}

    /*Sobreescribimos los métodos definidos en la interfaz que estamos implementando*/
	@Override
        /* Analiza si la cadena recibida desde el cliente es igual a "test" (se ignora que la cadena
        haya sido escrita en mayúsculas) regresando "true" o "false".
        **/
	public Boolean test(String test) throws RemoteException {
		if(test.equalsIgnoreCase("test")) return true;
		return false;
	}
        
    @Override
        /* Se realiza la operación de multiplicación de dos enteros recibidos desde el cliente, 
        regresando el resultado.
        */
    public int multiplicar(int a, int b) throws RemoteException{
        return a*b;
    }
       
    @Override
        /* Se analiza si la cadena enviada por el cliente es un palíndromo, regresando el valor
        de "true" o "false".
        */
    public Boolean esPalindromo(String a) throws RemoteException{
        String b = "";
        /*Obtenemos la palabra al revés */
        int i = a.length()-1;
        while(i>-1){
            b = b + a.charAt(i);
            i--;
        }
        /* System.out.println(b); Imprimo en consola el resultado del proceso previo para comprobación*/
        if(a.equalsIgnoreCase(b)) return true;
        else return false;
    }

}
