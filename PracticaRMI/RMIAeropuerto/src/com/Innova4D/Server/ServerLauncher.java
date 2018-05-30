package com.Innova4D.Server;

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-01
 */
//Librerías para manejo de excepciones
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; /*Obtener la referencia a un registro de objeto remoto*/
import java.rmi.registry.Registry; /*Libreria para crear el registro RMI*/

import com.Innova4D.Interface.Constant;

public class ServerLauncher {
	/**
	 * 
	 * @param args
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		ControlTower control = new ControlTower(); /*Definición de nuestro nuevo servicio*/
		/*Creamos el registro del objeto remoto que acepta llamadas a un puerto específico*/
		Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		registry.bind(Constant.RMI_ID, control); /*Server liga el objeto remoto con el registro*/
		System.out.println("Control Tower (Server) has started"); /*Notificamos el inicio del servicio*/
	}
}
