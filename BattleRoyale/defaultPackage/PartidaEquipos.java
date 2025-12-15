package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class PartidaEquipos extends Partida{
	
	private List<Equipo> equipos;
	private int tamEquipo;
	private int nEquiposHumanos;
	private int nEquiposNPCs;
	private ListaNombres listaNombres;
	
	PartidaEquipos(String modoJuego) {
		super(modoJuego);
		if (modoJuego.equals("DUOS")) {
			this.tamEquipo = 2;
		}else {
			this.tamEquipo = 4;
		}
		
		this.equipos = new ArrayList<Equipo>();
		
		this.listaNombres = new ListaNombres("Nombres_Equipos.csv");
	}
	
	@Override
	protected void iniciarJuego() {
		pedirNumeroEquipos();
		establecerReglas();      
		crearPersonajes();      
		crearEquipos();              
		mostrarResumenEquipos();  
		
		new EventoEquipos(equipos);
	}
	
	
	private void pedirNumeroEquipos(){
		nEquiposHumanos = numeroEquiposJugadores();
		nEquiposNPCs = numeroEquiposNPCs();
		
		System.out.println("\n✅ Configuración:");
        System.out.println("   Equipos humanos: " + nEquiposHumanos);
        System.out.println("   Equipos NPCs: " + nEquiposNPCs);
        System.out.println("   Tamaño equipo: " + tamEquipo + " jugadores");
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
	
	@Override
	protected int numeroJugadores() {
	    nJugadores = nEquiposHumanos * tamEquipo;
	    System.out.println("\nSe crearán " + nJugadores + " jugadores humanos");
	    
	    return nJugadores;
	}
	
	@Override
	protected int numeroNPCs() {
	    nNPCs = nEquiposNPCs * tamEquipo;
	    System.out.println("Se crearán " + nNPCs + " NPCs");
	    
	    return nNPCs;
	}
	
	 private void crearEquipos() {
		 System.out.println("Creacion de equipos:");
		 
		 System.out.println("EQUIPOS DE JUGADORES REALES\n");
		 for (int i = 0; i < nEquiposHumanos; i++) {
			String nombreEquipo;
			
			System.out.println("Nombre del equipo: ");
			nombreEquipo = sc.nextLine();
			
			Equipo equipo = new Equipo(nombreEquipo, false);
			
			
			int jugador = i*tamEquipo;
			
			for (int j = 0; j < tamEquipo; j++) {
				Personaje p = jugadoresHumanos.get(jugador + j);
				equipo.add(p);
			}
			
			equipos.add(equipo);			
			System.out.println(equipo.getNombre() + ": " + equipo.getNombres());
		 }
		 
		 System.out.println("EQUIPOS DE NPCs\n");
		 for (int i = 0; i < nEquiposNPCs; i++) {
			
			Equipo equipo = new Equipo(this.listaNombres.getRandomNombres(), true);
			
			
			int npc = i*tamEquipo;
			
			for (int j = 0; j < tamEquipo; j++) {
				Personaje p = jugadoresNPCs.get(npc + j);
				equipo.add(p);
			}
			
			equipos.add(equipo);	
			System.out.println(equipo.getNombre() + ": " + equipo.getNombres());
		 }
		 
		 System.out.println("\n✅ " + equipos.size() + " equipos creados");
	 }
	 
	 private void mostrarResumenEquipos() {
	    System.out.println("\n=== RESUMEN FINAL ===");
	    
	    for (Equipo equipo : equipos) {
	        System.out.println("\n" + equipo.getNombre());
	        System.out.print("Miembros: ");
	        
	        for (int i = 0; i < equipo.size(); i++) {
	            System.out.print(equipo.get(i).getNombre());
	            if (i < equipo.size() - 1) {
	                System.out.print(", ");
	            }
	        }
	    }
	    
	    System.out.println("\n\n=== TOTALES ===");
	    System.out.println("Equipos: " + equipos.size());
	    System.out.println("Equipos humanos: " + nEquiposHumanos);
	    System.out.println("Equipos NPCs: " + nEquiposNPCs);
	    System.out.println("Personajes totales: " + (nJugadores + nNPCs));
	    System.out.println("\n");
	}
	
	 
}











