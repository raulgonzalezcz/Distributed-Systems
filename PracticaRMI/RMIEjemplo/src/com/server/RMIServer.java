package com.server;
//Bibliotecas para usar RMI
import java.rmi.AccessException; /*Operación no permitida. El registro restringe bind, rebind y unbind al mismo host. 
La operación de búsqueda puede originarse desde cualquier host.*/
import java.rmi.AlreadyBoundException; /*Intento de enlazar un nombre que ya está enlazado*/
import java.rmi.RemoteException; /*Error de comunicación*/
import java.rmi.registry.LocateRegistry; /*Se utiliza para obtener una referencia a un registro de objeto remoto de arranque en un host*/
import java.rmi.registry.Registry; /*Clase para crear el registro RMI*/

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-02-20
 */

/*
Programa que actúe como servidor. Nótese que se podría optar por usar la clase ServerDefinition
para implementar el servicio y para activarlo pero se ha preferido mantenerlos en clases separadas por claridad.*/
public class RMIServer {
	
	public static final String RMI_ID = "TestRMI"; /* Define el nombre lógico de nuestra prueba */
	
	public static final int RMI_PORT = 8081; /** Define el puerto en donde se encuentra el servidor */
	
	/**
	 * @param args
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */

/*
Código para iniciar el servicio remoto y hacerlo públicamente accesible usando el rmiregistry (el servicio básico de binding en Java RMI). 
*/
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
				
		ServerDefinition impl = new ServerDefinition(); /*Definición de nuestro nuevo servicio*/
		/*Creamos el registro de objeto remoto que acepta llamadas en un puerto específico.*/
		Registry registry = LocateRegistry.createRegistry(RMI_PORT); 
		registry.bind(RMI_ID, impl); /* Server registra sus objetos remotos con el registro para que puedan ser consultados.*/
		
		System.out.println("Server is running..."); /*Notificamos al usuario que Server está listo para usarse...*/
	}
}
