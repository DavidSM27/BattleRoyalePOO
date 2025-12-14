package defaultPackage;

public abstract class Batalla {
	
	protected boolean enCurso;

	public Batalla() {
		this.enCurso = false;
	}

	public void iniciar() {
		enCurso = true;
		System.out.println("\n ========== LA BATALLA HA COMENZADO ========== \n");
	}

	public void obtenerInfo() {
		String estado;
		if (enCurso == true) {
			estado = "EN CURSO";
		} else {
			estado = "FINALIZADA";
		}
		
		System.out.println("Estado de la batalla: " + estado);
	}

	public boolean estaEnCurso() {
		return enCurso;
	}

	protected void terminar(Personaje ganador, Personaje perdedor) {
		this.enCurso = false;
		
		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println("Ganador: " + ganador.getNombre());
		System.out.println("Perdedor: " + perdedor.getNombre());
		System.out.println("==========================================\n");
	}
	
	protected void terminarPorHuida(Personaje cagon) {
		this.enCurso = false;

		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println(cagon.getNombre()+" huyó de la batalla!");
		System.out.println("==========================================\n");
	}
	
	protected void terminar(Equipo ganador, Equipo perdedor) {
		this.enCurso = false;

		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println("Ganador: " + ganador.getNombre());
		System.out.println("Perdedor: " + perdedor.getNombre());
		System.out.println("==========================================\n");
	}
	
	protected void terminarPorHuida(Equipo cagon) {
		this.enCurso = false;

		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println(cagon.getNombre()+" huyó de la batalla!");
		System.out.println("==========================================\n");
	}
}
