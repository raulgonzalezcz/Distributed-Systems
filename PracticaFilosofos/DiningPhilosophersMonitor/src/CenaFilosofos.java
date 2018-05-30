/**
 * Esta versión no causa Dealock.
 * Un filósofo podría morir de hambre.
 * 
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-15
 */
public class CenaFilosofos {
	// Número de filósofos
	private static final int num_filosofos = 4;
	
	public static void main (String[] args) {
		Filosofo[] filosofos = new Filosofo[num_filosofos];
		// El monitor controla la asignación de recursos compartidos.
		Monitor monitor = new Monitor(num_filosofos);
		for (int i = 0; i < num_filosofos; i++) {
			filosofos[i] = new Filosofo(i, monitor);
			new Thread(filosofos[i]).start(); /*Iniciamos el Thread*/
		}
	}
}
