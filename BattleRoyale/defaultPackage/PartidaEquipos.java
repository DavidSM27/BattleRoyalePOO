package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class PartidaEquipos extends Partida{
	
	private List<Equipo> equipos = new ArrayList<>();
	private int tamEquipo;
	private int nEquiposHumanos;
	private int nEquiposNPCs;
	
	PartidaEquipos(String modoJuego) {
		super(modoJuego);
		if (modoJuego.equals("DUOS")) {
			this.tamEquipo = 2;
		}else {
			this.tamEquipo = 4;
		}
		
		iniciarJuego();
	}
	
	private void iniciarJuego() {
		pedirNumeroEquipos();
		establecerReglas();      
		crearPersonajes();      
		crearEquipos();              
		mostrarResumenEquipos();  
		
		new Evento(personajesVivos);
	}
	
	
	private void pedirNumeroEquipos(){
		nEquiposHumanos = numeroEquiposJugadores();
		nEquiposNPCs = numeroEquiposNPCs();
	}
	
	private int numeroEquiposJugadores() {
		int equiposJugadores = 0;
        
        System.out.println("¿Cuantos equipos de jugadores van a haber?");
        do {
    			System.out.print("Equipos de jugadores: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Equipos de jugadores: ");
    				sc.next();
    			}
    			
    			equiposJugadores = sc.nextInt();
        		sc.nextLine();
        		if(equiposJugadores < 1) {
        			System.out.println("Debe haber al menos 1 equipo de jugadores");
        		}
        } while(equiposJugadores < 1);
    		System.out.println();
        
        return equiposJugadores;
	}
	
	private int numeroEquiposNPCs() {
		int equiposNPCs = 0;
        
        System.out.println("¿Cuantos equipos de NPCs van a haber?");
        do {
    			System.out.print("Equipos de NPCs: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Equipos de NPCs: ");
    				sc.next();
    			}
    			
    			equiposNPCs = sc.nextInt();
        		sc.nextLine();
        		if(equiposNPCs < 1) {
        			System.out.println("Debe haber al menos 1 equipo de NPCs");
        		}
        } while(equiposNPCs < 1);
    		System.out.println();
        
        return equiposNPCs;
	}
	
	
	 private void crearEquipos() {
	        System.out.println("\n=== CREANDO EQUIPOS ===");
	        System.out.println("Todos los equipos son de " + tamEquipo + " jugadores");
	        
	        // 1. EQUIPOS HUMANOS
	        System.out.println("\n--- EQUIPOS HUMANOS ---");
	        crearEquiposDeLista(jugadoresHumanos, false);
	        
	        // 2. EQUIPOS NPCS
	        System.out.println("\n--- EQUIPOS NPCS ---");
	        crearEquiposDeLista(jugadoresNPCs, true);
	        
	        System.out.println("\n✅ Total: " + equipos.size() + " equipos creados");
	    }	
	
}
