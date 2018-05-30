package com.Innova4D.Server;

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-01
 */
//Bibliotecas RMI
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; /*Obtener el stub*/
//Clases que necesitamos para el manejo de objetos cliente
import com.Innova4D.Interface.Auto;
import com.Innova4D.Interface.Avion;
import com.Innova4D.Interface.UFO;
import com.Innova4D.Interface.Boeing;
import com.Innova4D.Interface.RemoteInterface;
/*Código que implementa los servicios remotos de nuestro Server*/
/*
Con Unicast exportamos un objeto remoto con JRMP y obtenemos el stub que se comunica con el objeto remoto.
Se implemneta la interfaz RemoteInterface
*/
public class ControlTower extends UnicastRemoteObject implements RemoteInterface {
	
	/*
	 * El constructor de la superclase, protegido para asegurar la integridad de ControlTower.
	 * Protected: Accesible únicamente por ésta clase y sus subclases.
	 */
	protected ControlTower() throws RemoteException {
		super(); 
		// TODO Auto-generated constructor stub
	}

	/**
	 * { 0, 0, 0, 0, 0, 0, 0, 0 },
	 * { 0, 0, 0, 0, 0, 0, 0, 0 },
	 * { 0, 0, 0, 0, 0, 0, 0, 0 },
	 * { 0, 0, 0, 0, 0, 0, 0, 0 }
	 */	
	private Object[][] mapaPista = new Object[4][8];

	private static final long serialVersionUID = 1L; /*Serial Unique ID*/
	/*Sobreescribimos los métodos definidos en la interfaz que estamos implementando*/
	/* Regresa el mapa de las pistas. */
	@Override
	public Object[][] getMapaPistas() throws RemoteException {
		return this.mapaPista;
	}
	
	/** Mueve un avión en la matriz, un bloque a la vez. Izq. Der. */
	@Override
	public Boolean moverAvion(Avion a, int c) throws RemoteException {
		Boolean flag = false;
		int newY = a.getY() + 1;
		if (newY < 8) {
		try {
				this.mapaPista[c][a.getY()] = null;
				this.mapaPista[c][newY] = a;
				a.setX(0);
				a.setY(newY);
				flag = true; /*La operación fue realizada con éxito*/
			} catch (Exception e) {
				flag = false; /*No fue posible mover el avión: Llego al final del camino*/
			}
		}
		return flag;
	}
	
  /**
 	* Busca por un avión en el mapa aéreo usando su ID.
 	* @param id El identificador del objeto.
 	* @param c  El carril donde se encuentra el objeto.
 	*/
	@Override
	public Avion getAvion(String id, int c) throws RemoteException {
		Avion a = null;
		for (int i = 0; i < 8; i++) {
		    if(this.mapaPista[c][i] != null){
		    	a = (Avion) this.mapaPista[c][i]; /*Poisición del objeto*/
		    	if(!a.getId().equals(id))
		    		a = null; /*El objeto no tiene posición, no está*/
		    }
		}
		return a;
	}

	/* Verificamos que el objeto este en el mapa*/
	@Override
	public Boolean checkInAvion(Avion a) throws RemoteException {
		if(this.mapaPista[a.getX()][a.getY()] == null)
			this.mapaPista[a.getX()][a.getY()] = a;
		return true;
	}

	/* Aquí comienzan los métodos del auto. Siguen la misma lógica del código documentado previamente*/
	
	@Override
	public Boolean checkInAuto(Auto a) throws RemoteException {
		if(this.mapaPista[a.getX()][a.getY()] == null)
			this.mapaPista[a.getX()][a.getY()] = a;
		return true;
	}
	@Override
	public Auto getAuto(String id, int c) throws RemoteException {
		Auto a = null;
		for (int i = 0; i < 8; i++) {
		    if(this.mapaPista[c][i] != null){
		    	a = (Auto) this.mapaPista[c][i];
		    	if(!a.getId().equals(id))
		    		a = null;
		    }
		}
		return a;
	}

	@Override
	public Boolean moverAuto(Auto a, int c) throws RemoteException {
		Boolean flag = false;
		int newY = a.getY() + 1;
		if (newY < 8) {
		try {
				this.mapaPista[c][a.getY()] = null;
				this.mapaPista[c][newY] = a;
				a.setX(0);
				a.setY(newY);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
        
    /* Aquí comienzan los métodos del UFO. */
	/* Verificamos que el objeto este en el mapa*/
	@Override
	public Boolean checkInUFO(UFO a) throws RemoteException {
		if(this.mapaPista[a.getX()][a.getY()] == null)
			this.mapaPista[a.getX()][a.getY()] = a;
		return true;
	}
	/**
 	* Busca por un UFO en el mapa aéreo usando su ID.
 	* @param id El identificador del objeto.
 	* @param c  El carril donde se encuentra el objeto.
 	*/
        @Override
	public UFO getUFO(String id, int c) throws RemoteException {
		UFO a = null;
		for (int i = 0; i < 8; i++) {
		    if(this.mapaPista[c][i] != null){
		    	a = (UFO) this.mapaPista[c][i];
		    	if(!a.getId().equals(id))
		    		a = null;
		    }
		}
		return a;
	}

        /** Mueve un UFO en la matriz, un bloque a la vez. Izq. Der. */
	@Override
	public Boolean moverUFO(UFO a, int c) throws RemoteException {
		Boolean flag = false;
		int newY = a.getY() + 1;
		if (newY < 8) {
		try {
				this.mapaPista[c][a.getY()] = null;
				this.mapaPista[c][newY] = a;
				a.setX(0);
				a.setY(newY);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
        
    /* Aquí comienzan los métodos del Boeing. */
	
	@Override
	public Boolean checkInBoeing(Boeing a) throws RemoteException {
		if(this.mapaPista[a.getX()][a.getY()] == null)
			this.mapaPista[a.getX()][a.getY()] = a;
		return true;
	}
	@Override
	public Boeing getBoeing(String id, int c) throws RemoteException {
		Boeing a = null;
		for (int i = 0; i < 8; i++) {
		    if(this.mapaPista[c][i] != null){
		    	a = (Boeing) this.mapaPista[c][i];
		    	if(!a.getId().equals(id))
		    		a = null;
		    }
		}
		return a;
	}

	@Override
	public Boolean moverBoeing(Boeing a, int c) throws RemoteException {
		Boolean flag = false;
		int newY = a.getY() + 1;
		if (newY < 8) {
		try {
				this.mapaPista[c][a.getY()] = null;
				this.mapaPista[c][newY] = a;
				a.setX(0);
				a.setY(newY);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
}