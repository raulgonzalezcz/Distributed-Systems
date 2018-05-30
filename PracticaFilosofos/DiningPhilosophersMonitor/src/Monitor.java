/**
 * El monitor asegura que el filósofo levantará únicamente
 * ambos cubiertos cuando sus vecinos nó estén comiendo.
* @author   Raúl González Cruz <raul.gonzalezcz@udlap.mx> ID: 151211
*           Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version  0.1
 * @since    2017-03-15
 */
class Monitor {
	// El monitor conoce los diferentes estados para cada filósofo.
	private enum State {PENSANDO, HAMBRIENTO, COMIENDO};

	// Un vector que contiene el estado de cada filósofo.
	private State[] estadoFilosofo;

	/** Constructor que crea un monitor para el número adecuado de filósofos
	 * Como estado inicial, todos los filósofos están pensando.
	 * @param numFilosofos El número de filósofos.
	 */
	public Monitor (int numFilosofos) {
		estadoFilosofo = new State[numFilosofos];
		for (int i = 0; i < estadoFilosofo.length; i++) {
			estadoFilosofo[i] = State.PENSANDO;
		}
	}

	/**
	 * Un filósofo toma ambos cubiertos.
	 * El filósofo se pone a pensar si ambos vecinos comen.
	 * @param idFilosofo El filósofo que desea comer.
	 * @throws InterruptedException si falla el hilo. Es decir:
* Es lanzado cuando un hilo está esperando, durmiendo u ocupado, y el hilo se interrumpe, antes o durante la actividad. 
* También de vez en cuando un método puede desear probar si el hilo actual se ha interrumpido, y si es así, 
lanzar inmediatamente esta excepción
	 */
	public synchronized void levantaCubiertos(int idFilosofo) throws InterruptedException {
		// Si levanta los cubiertos, come.
		estadoFilosofo[idFilosofo] = State.HAMBRIENTO;
		System.out.println("Filósofo: " + idFilosofo + " está hambriento.\n");
		/* Mientras los vecinos comen, el filósofo debería estar esperando. */
		while (losVecinosComen(idFilosofo)) {
                    System.out.println("Filósofo: " + idFilosofo + " está esperando a sus vecinos.\n");
                    wait();
		}
		/* Cuando los vecinos dejan de comer, ahora, y solo ahora, éste filósofo está comiendo. */
		estadoFilosofo[idFilosofo] = State.COMIENDO;
		System.out.println("Filósofo: " + idFilosofo + " está comiendo.\n");
	}

	/**
	 * Regresar true si ningún vecino come.
	 * @param idFilosofo El filósofo objetivo para verificar vecinos.
	 * @return true si ningún vecino come.
	 */
	private boolean losVecinosComen(int idFilosofo) {
		// Verificar filósofo de un lado.
		if (estadoFilosofo[(idFilosofo + 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Verificar filósofo del otro.
		if (estadoFilosofo[(idFilosofo + estadoFilosofo.length - 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Ninguno está comiendo
		return false;
	}

	/** El filósofo baja los cubiertos. Éste método ejecuta una tarea sincronizada.
	 * (Bajar los cubiertos al mismo tiempo y notificar a todos).
	 *
	 * Notificar a todos que ya pueden ocupar los recursos (Cubiertos). 
	 * @param idFilosofo El filósofo que a terminado de comer.
	 */
	public synchronized void bajarCubiertos(int idFilosofo) {
		estadoFilosofo[idFilosofo] = State.PENSANDO;
		/* Notificar a todos. */
                System.out.println("Filósofo: " + idFilosofo + " baja los cubiertos.\n");
                notifyAll();
	}
}
