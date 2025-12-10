package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public abstract class Batalla {

		protected Object ganador;
		protected Object perdedor;
		protected boolean enCurso;
		
		public void iniciar() {
			enCurso = true;
	        ganador = null;
	        perdedor = null;
	        System.out.println("La batalla ha comenzado");
		}
		
		public String obtenerInfo() {
			String estado = null;
			String fraseGanador=null, frasePerdedor=null;
			if(enCurso==true) {
				estado="EN CURSO";
			}else {
				estado="FINALIZADA";
			}
			
			if (ganador != null) {
			    fraseGanador=ganador.toString();
			    frasePerdedor=perdedor.toString();
			} else {
				fraseGanador="Aún no hay";
				frasePerdedor="Aún no hay";
			}

	        return "Estado de la batalla: " + estado + "\nGanador: " + fraseGanador + "\nPerdedor: " + frasePerdedor;
		}
		
		public java.util.List<String> obtenerAcciones(){
			List<String> acciones = new ArrayList<>();
	        acciones.add("Atacar");
	        acciones.add("Defender");
	        acciones.add("Pasar turno");
	        return acciones;
		}
		
		public Object obtenerResultado() {
			if (ganador != null) {
	            System.out.println("La batalla ha terminado. Ganador: " + ganador);
	        } else {
	            System.out.println("La batalla aún no tiene ganador");
	        }
	        return ganador;
		}
		
		public boolean estaEnCurso() {
	        return enCurso;
	    }
		
}
