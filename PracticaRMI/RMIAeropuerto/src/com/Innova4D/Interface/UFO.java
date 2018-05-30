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

public class UFO implements Serializable {
	
	private static final long serialVersionUID = 22L; /*Serial Unique ID*/
	private int x; /*Fila*/
	private int y; /*Columna*/
	private String id;
	
	public UFO(String id, int x, int y) throws RemoteException {
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

	public void setX(int x) { //Estabecemos nueva posición en fila
		this.x = x;
	}

	public int getY() { //Obtenemos columna
		return y;
	}

	public void setY(int y) { //Establecemos nueva posición en columna
		this.y = y;
	}
}
