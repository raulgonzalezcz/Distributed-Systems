import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * El problema de los filósofos (Esta versión causa un Deadlock).
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-15
 */
public class CenaFilosofos {
	// El número de filósofos...
	private static final int NUM_FILOSOFOS = 5;
	
	/**
	 * Una prueba de los filósofos.
	 * @param args Not used
	 */
	public static void main (String[] args) {
		/** Cada tenedor es un recurso compartido.
                 * Los recursos compartidos en Java se definen como tipo Lock.
                 * Con Reentrant permitimos que una porción de código sea utilizado por todas las isntancias
		 */
                ReentrantLock[] tenedores = new ReentrantLock[NUM_FILOSOFOS];
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			tenedores[i] = new ReentrantLock();
		}
		/** Crear un arreglo de filósofos [5] 
		 * En cada posición del arreglo, crear un filósofo (Inicializar el Thread para cada filósofo). 
		 * En el siguiente ejemplo se implementa un código para inicializar un Thread con un filósofo, 
		 * En la segunda parte se deben inicializar 5 ó más filósofos (checar NUM_FILOSOFOS)
		 */
                for (int i = 0; i < NUM_FILOSOFOS; i++) {
		Filosofo filosofo = new Filosofo(i, tenedores[i], tenedores[(i+1)%NUM_FILOSOFOS]);
		new Thread(filosofo).start();
                }
	}
}
