package com.interf.test;
//Bibliotecas para usar RMI
import java.rmi.Remote;	/*TestRemote deriva de la interfaz Remote*/
import java.rmi.RemoteException; /*Excepción para error de comunicación/

/**
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-02-20
 */
public interface TestRemote extends Remote{

	/* Contrato de operaciones definido para Client y Server. En esta interfaz se definen los métodos (servicio)
que deberá implementar Server en ServerDefinition. En caso de agregar métodos que reciban o envíen objetos
se debe tener en cuenta el serializado de dicho objeto (Marshalling).

TestRemote deriva de la interfaz Remote y activa la excepción RemoteException para cada método definido, esto es
usado por RMI para notificar errores relacionados con la comunicación.

	 * @param test Un String a comparar, si es idéntico a la palabra "test".
	 * @return true en caso de que sea idéntico a "test".
	 */
	public Boolean test(String test) throws RemoteException;
        
    /*
	 * @param a,b dos enteros a multiplicar.
	 * @return el resultado de la multiplicación efectuada.
	 */
        public int multiplicar(int a, int b) throws RemoteException;
        
    /*
	 * @param c Un String a comparar, si es una palabra palíndroma.
	 * @return true en caso de que sea idéntico a su reverso, false en caso contrario.
	 */
        public Boolean esPalindromo(String c) throws RemoteException;

}