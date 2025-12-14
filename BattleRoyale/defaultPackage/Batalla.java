package defaultPackage;

public abstract class Batalla {

	protected Object ganador;
	protected Object perdedor;
	protected boolean enCurso;

	public Batalla() {
		this.ganador = null;
		this.perdedor = null;
		this.enCurso = false;
	}

	public void iniciar() {
		enCurso = true;
		ganador = null;
		perdedor = null;
		System.out.println("\n ========== LA BATALLA HA COMENZADO ========== \n");
	}

	public void obtenerInfo() {
		String estado;
		if (enCurso == true) {
			estado = "EN CURSO";
		} else {
			estado = "FINALIZADA";
		}

		String fraseGanador, frasePerdedor;
		if (ganador != null) {
			fraseGanador = ganador.toString();
			frasePerdedor = perdedor.toString();
		} else {
			fraseGanador = "Aún no hay";
			frasePerdedor = "Aún no hay";
		}

		System.out.println("Estado de la batalla: " + estado);
		System.out.println("Ganador: " + fraseGanador);
		System.out.println("Perdededor" + frasePerdedor);

	}

	public Object obtenerResultado() {
		if (ganador != null) {
			System.out.println("La batalla ha terminado. Ganador: " + ((Personaje) ganador).getNombre());
		} else {
			System.out.println("La batalla aún no tiene ganador");
		}
		return ganador;
	}

	public boolean estaEnCurso() {
		return enCurso;
	}

	protected void terminar(Personaje ganador, Personaje perdedor) {
		this.enCurso = false;
		this.ganador = ganador;
		this.perdedor = perdedor;

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
}
