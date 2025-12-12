package defaultPackage;

import java.util.List;
import java.util.Scanner;

public class Evento extends ListaArmas {
	private static Integer RONDA=1;
	private static Integer COFRES_TOTALES=0;
	private static Integer COFRES_ABIERTOS=0;
	private static Integer COFRES_ABIERTOS=0;
	
	private List<Personaje> jugadores;
	
	public Evento(List<Personaje> jugadores){
		super();
		this.jugadores=jugadores;
		Evento.COFRES_TOTALES=this.jugadores.size()*2;
	}
	
	public void rondas() {
		RONDA++;
		for (int i=0; i < jugadores.size(); i++) {
			Integer opcion=0;
			if(!jugadores.get(i).isVivo()) {
				Scanner sc=new Scanner(System.in);
				
				System.out.println("¿Que quieres hacer?");
	            System.out.println("[0] Buscar un Cofre");
	            System.out.println("[1] Buscar una Tienda");
	            System.out.println("[2] Buscar una Batalla");
	            System.out.println("[3] Campear");
	            
	            do {
	            	System.out.print("Elige la opcion:");
					opcion=sc.nextInt();
				} while (opcion<0 && opcion>3);
	            sc.close();
			}else {
				// El NPC nunca campea
				if(RONDA>2) {
					opcion=(int)Math.round(Math.random()*3)%3;
				}else if(RONDA.equals(0)) {
					opcion=0;
				}else {
					opcion=(int)Math.round((Math.random()*2)%2);
				}
				
			}
			this.opciones(jugadores.get(i), opcion);
		}
		for (int i=0; i < jugadores.size(); i++) {
			if(!jugadores.get(i).isVivo()) {
				jugadores.remove(i--);
			}
		}
	}
	
	private void opciones(Personaje persona, Integer opcion) {
		
		switch (opcion) {
			case 1:
				
				
				
				
				
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
				
			case 4:
				
				break;
		}
		
		
	}
	
	
	
	
}
