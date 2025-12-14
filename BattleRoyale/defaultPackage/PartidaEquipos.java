package defaultPackage;

import java.util.List;

public class PartidaEquipos extends Partida{
	
	private List<Equipo> equipos;
	private int tamEquipo;
	
	
	PartidaEquipos(String modoJuego) {
		super(modoJuego);
		if (modoJuego.equals("DUOS")) {
			this.tamEquipo = 2;
		}else {
			this.tamEquipo = 2;
		}
	}
	
	private void iniciarJuego() {
		 establecerReglas();      
		 crearPersonajes();      
		 crearEquipos();              
		 mostrarResumenEquipos();  
		 new Evento(personajesVivos);
	}
	

}
