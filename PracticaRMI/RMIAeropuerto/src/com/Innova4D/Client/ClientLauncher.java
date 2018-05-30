package com.Innova4D.Client;
/**Librerías RMI para el manejo de excepciones*/
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/*Liberías para la ejecución concurrente de cada objeto*/
import java.util.concurrent.Executors; 
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//Clases que usaremos para crear nuestros clientes
import com.Innova4D.Interface.Auto;
import com.Innova4D.Interface.Avion;
import com.Innova4D.Interface.UFO;
import com.Innova4D.Interface.Boeing;
import com.Innova4D.Interface.Constant;
import com.Innova4D.Interface.RemoteInterface;
/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-01
 */
public class ClientLauncher {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		if(args.length==2){
		    Constant.RMI_IP = args[0];//Cambiar la dirección IP que sera empleada para conectarse al servidor RMI
            //Enviar un vehículo específico especificado en los argumentos
            switch (Integer.parseInt(args[1])){
                case 1:
                    System.out.println("Iniciar el cliente para mandar un Avión...");
                    ClientLauncher.avionClient(); /*Iniciamos cliente...*/
		    ClientLauncher.guiClient();
                    break;
                case 2:
                    System.out.println("Iniciar el cliente para mandar un Auto...");
                    ClientLauncher.autoClient();
	            ClientLauncher.guiClient();
                    break;    
                case 3:
                    System.out.println("Iniciar el cliente para mandar un UFO...");
                    ClientLauncher.ufoClient();
		    ClientLauncher.guiClient();
                    break;
                case 4:
                    System.out.println("Iniciar el cliente para mandar un Boeing...");
                    ClientLauncher.boeingClient();
		    ClientLauncher.guiClient();
                    break;
                    
            }
		}else {
		/*
		 * Iniciar el cliente de la interfaz gráfica.
		 */
            ClientLauncher.guiClient();
		/*
		 * Iniciar el cliente para mandar un avión...
		 */
            ClientLauncher.avionClient();
		/*
		 * Iniciar el cliente para mandar un auto...
		 */
            ClientLauncher.autoClient();
            /*
		 * Iniciar el cliente para mandar un UFO...
		 */
            ClientLauncher.ufoClient();
            /*
		 * Iniciar el cliente para mandar un Boeing...
		 */
            ClientLauncher.boeingClient();
        }
	}
	/**
	 * Se crea un cliente que administra el avión.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private static void avionClient () throws RemoteException, NotBoundException {
		/*
		 * Buscar el servidor en localhost, en puerto -> Constant.RMI_PORT.
		 */
		Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT);
		final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID); /*Casting de lo que devuelve lookup*/

		/*
		 * Crear una nave, con un ID y una Ubicación (0,0 default).
		 * El objeto es de tipo final para asegurar que la información no va a cambiar.
		 */
		final Avion a1 = new Avion("Lufthansa",0,0); //"L" Aparece en el mapa.
		remote.checkInAvion(a1);                
		/*
		 * Temporizador que ejecuta movePlane cada segundo.
		 * También se obtiene el espacio aéreo y se imprime.
		 */
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
                      /*Instrucciones dentro del hilo de ejecución*/
			try {
				remote.moverAvion(remote.getAvion(a1.getId(), 0),0); /*Función para mover nuestro objeto*/
			} catch (Exception e) {
				//e.printStackTrace();
			}
		  }
		}, 0, 1, TimeUnit.SECONDS); /*Unidad de tiempo de nuestro temporizador (1 segundo)*/
	}
	/**
	 * Se crea un cliente que administra el auto.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private static void autoClient () throws RemoteException, NotBoundException {
		/*
		 * Buscar el servidor en localhost, en puerto -> Constant.RMI_PORT.
		 */
		Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT);
		final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);

		/*
		 * Crear un auto, con un ID y una Ubicación (1,0 default).
		 * El objeto es de tipo final para asegurar que la información no va a cambiar.
		 */
		final Auto a1 = new Auto("Servicio",1,0); // "S" Aparece en el mapa.
		remote.checkInAuto(a1);
		/*
		 * Temporizador que ejecuta moverAuto cada 2 segundos.
		 */
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			try {
				remote.moverAuto(remote.getAuto(a1.getId(), 1),1);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		  }
		}, 0, 2, TimeUnit.SECONDS);
	}
        /**
	 * Se crea un cliente que administra el UFO.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private static void ufoClient () throws RemoteException, NotBoundException {
		/*
		 * Buscar el servidor en localhost, en puerto -> Constant.RMI_PORT.
		 */
		Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT);
		final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID); /*Casting de lo que devuelve lookup*/

		/*
		 * Crear una nave, con un ID y una Ubicación (0,0 default).
		 * El objeto es de tipo final para asegurar que la información no va a cambiar.
		 */
		final UFO a1 = new UFO("UFO",2,0); //"U" Aparece en el mapa.
		remote.checkInUFO(a1);
		/*
		 * Temporizador que ejecuta movePlane cada 3 segundos.
		 * También se obtiene el espacio aéreo y se imprime.
		 */
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
                      /*Instrucciones dentro del hilo de ejecución*/
			try {
				remote.moverUFO(remote.getUFO(a1.getId(), 2),2); /*Función para mover nuestro objeto*/
			} catch (Exception e) {
				//e.printStackTrace();
			}
		  }
		}, 0, 3, TimeUnit.SECONDS); /*Unidad de tiempo de nuestro temporizador (1 segundo)*/
	}
        /**
	 * Se crea un cliente que administra el auto.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private static void boeingClient () throws RemoteException, NotBoundException {
		/*
		 * Buscar el servidor en localhost, en puerto -> Constant.RMI_PORT.
		 */
		Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT);
		final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);

		/*
		 * Crear un auto, con un ID y una Ubicación (1,0 default).
		 * El objeto es de tipo final para asegurar que la información no va a cambiar.
		 */
		final Boeing a1 = new Boeing("Boeing",3,0); // "B" Aparece en el mapa.
		remote.checkInBoeing(a1);
		/*
		 * Temporizador que ejecuta moverAuto cada 4 segundos.
		 */
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			try {
				remote.moverBoeing(remote.getBoeing(a1.getId(), 3),3);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		  }
		}, 0, 4, TimeUnit.SECONDS);
	}
	/**
	 * Cliente que imprime la GUI (Graphical user interface) de las pistas.
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	private static void guiClient () throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT); /*Obtenemos el registro del servicio*/
		final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID); /*Buscamos el servicio*/
		/*
		 * Obtiene la pista y se imprime. Se crea un hilo de ejecución propio para que el recurso no sea compartido por otros
		 */
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			try { //Imprimos la pista en cada iteración
				System.out.println(ClientLauncher.printPista(remote.getMapaPistas()));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		  }
		}, 0, 1, TimeUnit.SECONDS);
	}
	/**
	 * Imprime en consola el espacio aéreo desde la
	 * torre de control (Servidor) en el Cliente.
	 * ** Método que implementa guiClient() **
	 * @param a Matriz que contiene el espacio aéreo a imprimir.
	 * @throws RemoteException
	 */
	private static String printPista(Object[][] a) throws RemoteException {
		Object o = null;
		String output = "=== Pista ===\n";
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 8; j++) {
		    	if(a[i][j] != null) { //Mostramos nuestra pista por cada cliente en su fila correspondiente
		    		o = a[i][j];
		    		if(o.getClass().getSimpleName().equals("Auto"))
		    			output = output + "["+((Auto)o).getId().charAt(0)+"]";
		    		if(o.getClass().getSimpleName().equals("Avion"))
		    			output = output + "["+((Avion)o).getId().charAt(0)+"]"; 
                                if(o.getClass().getSimpleName().equals("UFO"))
		    			output = output + "["+((UFO)o).getId().charAt(0)+"]";
		    		if(o.getClass().getSimpleName().equals("Boeing"))
		    			output = output + "["+((Boeing)o).getId().charAt(0)+"]"; 
		    	} else { 
		    		output = output + "[ ]";
		    	}

		    } //Agregamos los índices de nuestros clientes en la pista
		    if (i == 0 ) output = output + "<- Avión";
		    if (i == 1 ) output = output + "<- Auto ";
                    if (i == 2 ) output = output + "<- UFO ";
		    if (i == 3 ) output = output + "<- Boeing ";
		    output = output + "\n";	
		}
		return output = output + "\n";
	}
}