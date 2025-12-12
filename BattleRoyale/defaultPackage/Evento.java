package defaultPackage;

import java.util.List;

public class Evento extends ListaArmas {
	private static final Integer RONDA=1;
	
	private List<Personaje> jugadores;
	
	public Evento(List<Personaje> jugadores){
		super();
		this.jugadores=jugadores;
	}
	
	public void rondas() {
		for (int i=0, j=-1; i < jugadores.size(); i++) {
			if(jugadores.get(i).isVivo()) {
				
			}else {
				jugadores.remove(i--);
				
			}
			
			
			
		}
	}
}
