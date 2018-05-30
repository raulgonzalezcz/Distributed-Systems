package com.client;
//Bibliotecas para usar RMI
import java.rmi.NotBoundException; /*Intento de buscar un nombre que no esté enlazado.*/
import java.rmi.RemoteException; /*Excepción para error de comunicación*/
import java.rmi.registry.LocateRegistry; /*Se utiliza para obtener una referencia a un registro de objeto remoto de arranque en un host*/
import java.rmi.registry.Registry; /*Clase para crear el registro RMI*/
//Esta clase implementa de la interfaz TestRemote
import com.interf.test.TestRemote;

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-02-20
 */
public class TestClient {

	public static final String RMI_ID = "TestRMI"; /* Define el nombre lógico de nuestra prueba  */
	
	public static final int RMI_PORT = 8081; /* Define el puerto en donde se encuentra el servidor */

/*
Función principal de Client. Accederá al Registro de Servicios para invocar  de manera remota	
los métodos (servicios) que ofrece Server.
*/

/*
Un NotBoundException se lanza si se hace un intento de buscar o desvincular en el registro un nombre que no tiene vinculación asociada.
Se activa la excepción RemoteException para cada método definido, usado por RMI para notificar errores relacionados con la comunicación.
*/
	public static void main(String[] args) throws RemoteException, NotBoundException { 
        /*
        Datos para que Client obtenga la referencia remota al registro de servicios de Server.
        LocateRegistry.getRegistry devuelve una referencia a un registro en el host de Server 
        dado la dirección IP (localhost) y el puerto de registro.
        */
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
        
        /*
        Se busca el objeto remoto por un ID en el registro del host de Server con el método
       	lookup de registry. Se obtiene una referencia remota del servicio.

       	Remote es el objeto con el que se invocarán los servicios de Server de manera remota
       	y de manera convencional en Java (remote.*).
        */
		TestRemote remote = (TestRemote) registry.lookup(RMI_ID);	
		System.out.println(remote.test("123"));     /*Método test de Server invocado remotamente*/
		System.out.println(remote.test("test"));    
        System.out.println(remote.multiplicar(2,6)); /*Método multiplicar de Server invocado remotamente*/
        System.out.println(remote.esPalindromo("ana"));	/*Método esPalindromo de Server invocado remotamente*/
        System.out.println(remote.esPalindromo("casada"));
	}

}
