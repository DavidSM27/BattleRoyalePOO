package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class Evento extends ListaArmas {
	private static final Integer RONDA=1;
	
	private List<Personaje> jugadores;
	
	public Evento(List<Personaje> jugadores) {
		super();
		this.jugadores=jugadores;
		this.jugadores.get(0).setOro(100);
	}
	
	
	
	
	public static void main(String[] args) {
		List<Personaje> jugadores=new ArrayList<Personaje>();
		jugadores.add(new Personaje());
		jugadores.add(new Personaje());
		jugadores.add(new Personaje());
		jugadores.add(new Personaje());
		
		System.out.println(jugadores.get(0));
		
		Evento evento=new Evento(jugadores);
		
		System.out.println(evento.jugadores.get(0));
		System.out.println(jugadores.get(0).toString());
		
	}
}
