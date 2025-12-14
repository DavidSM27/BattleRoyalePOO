package defaultPackage;

import java.util.ArrayList;
import java.util.List;

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

	public String obtenerInfo() {
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

		return "Estado de la batalla: " + estado + "\nGanador: " + fraseGanador + "\nPerdedor: " + frasePerdedor;
	}

	public List<String> obtenerAcciones() {
		List<String> acciones = new ArrayList<>();
		acciones.add("Atacar");
		acciones.add("Defender");
		acciones.add("Pasar turno");
		return acciones;
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
}
