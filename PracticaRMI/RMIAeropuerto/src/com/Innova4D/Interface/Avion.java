package com.Innova4D.Interface;

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-01
 */
//Librerías para serializar el objeto y manejo de excepciones
import java.io.Serializable;
import java.rmi.RemoteException; /*Aplicado en cada método de esta clase*/

/**
 * Los aviones van a formar parte de nuestra interfaz,
 * por eso es importante declarar los objetos dentro del mismo paquete.
 * 
 * Aquí vamos a experimentar con Marshalling, que es básicamente serialización de objetos.
 * 
 * Hacer Marshalling significa grabar el estado de un objeto y su codebase.
 * así cuando un objeto es "UnMarshalled" el objeto es exactamente el mismo.
 */
public class Avion implements Serializable {
	
	private static final long serialVersionUID = 42L; /*Serial Unique ID*/
	private int x; /*Fila*/
	private int y; /*Columna*/
	private String id;
	
	public Avion(String id, int x, int y) throws RemoteException {
		super(); /*Llamamos el Constructor de la super clase*/
		this.id = id;
		this.setX(x);
		this.setY(y);
	}

	public String getId() throws RemoteException  { //Obtenemos el ID del objeto
		return id;
	}

	public int getX() { //Obtenemos fila
		return x;
	}

	public void setX(int x) { //Estabecemos fila
		this.x = x;
	}

	public int getY() { //Obtenemos columna
		return y;
	}

	public void setY(int y) { //Establecemos nueva columna
		this.y = y;
	}
}
