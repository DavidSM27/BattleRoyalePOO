package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class PartidaEquipos extends Partida{
	
	private List<Equipo> equipos;
	private int tamEquipo;
	private int nEquiposHumanos;
	private int nEquiposNPCs;
	private ListaNombresEquipos listaNombresEquipos;
	
	PartidaEquipos(String modoJuego) {
		super(modoJuego);
		if (modoJuego.equals("DUOS")) {
			this.tamEquipo = 2;
		}else {
			this.tamEquipo = 4;
		}
		
		this.equipos = new ArrayList<Equipo>();
		
		this.listaNombresEquipos = new ListaNombresEquipos();
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
        System.out.println("   Equipos Maestros del Portal: " + nEquiposHumanos);
        System.out.println("   Equipos Skylanders: " + nEquiposNPCs);
        System.out.println("   Tamaño equipo: " + tamEquipo + " jugadores");
	}
	
	private int numeroEquiposJugadores() {
		int equiposJugadores = 0;
        
        System.out.println("¿Cuantos equipos de Maestros de Portal van a haber?");
        do {
    			System.out.print("Equipos de Maestros de Portal: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Equipos de Maestros de Portal: ");
    				sc.next();
    			}
    			
    			equiposJugadores = sc.nextInt();
        		sc.nextLine();
        		if(equiposJugadores < 1) {
        			System.out.println("Debe haber al menos 1 equipo de Maestros de Portal");
        		}
        } while(equiposJugadores < 1);
    		System.out.println();
        
        return equiposJugadores;
	}
	
	private int numeroEquiposNPCs() {
		int equiposNPCs = 0;
        
        System.out.println("¿Cuantos equipos de Skylanders van a haber?");
        do {
    			System.out.print("Equipos de Skylanders: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Equipos de Skylanders: ");
    				sc.next();
    			}
    			
    			equiposNPCs = sc.nextInt();
        		sc.nextLine();
        		if(equiposNPCs < 1) {
        			System.out.println("Debe haber al menos 1 equipo de Skylanders");
        		}
        } while(equiposNPCs < 1);
    		System.out.println();
        
        return equiposNPCs;
	}
	
	@Override
	protected int numeroJugadores() {
	    nJugadores = nEquiposHumanos * tamEquipo;
	    System.out.println("\nSe crearán " + nJugadores + " Maestros de Portal");
	    
	    return nJugadores;
	}
	
	@Override
	protected int numeroNPCs() {
	    nNPCs = nEquiposNPCs * tamEquipo;
	    System.out.println("Se crearán " + nNPCs + " Skylanders");
	    
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
		 
		 System.out.println("EQUIPOS DE Skylanders\n");
		 for (int i = 0; i < nEquiposNPCs; i++) {
			if(jugadoresNPCs.size()<tamEquipo) {
				nEquiposNPCs=equipos.size()-nEquiposHumanos;
				System.out.println("El numero de equipos de Skylanders creados van a se "+nEquiposNPCs+", porque no hay mas nombres de equipos de Skylanders.");
			}else if(this.listaNombresEquipos.getNombres().size()!=0) {
				try {
					Equipo equipo = new Equipo(this.listaNombresEquipos.getRandomNombres(), true);
					
					
					for (int j = 0; j < tamEquipo; j++) {
						Personaje p = new Personaje(this.getRandomPersonajeNPC());
						equipo.add(p);
					}
					
					equipos.add(equipo);
					System.out.println(equipo.getNombre() + ": " + equipo.getNombres());
				}catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					nEquiposNPCs=equipos.size()-nEquiposHumanos;
					System.out.println("El numero de equipos de Skylanders creados van a se "+nEquiposNPCs+", porque no hay mas nombres de equipos de Skylanders.");
				}
			}
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
	    System.out.println("Equipos Maestros de Portal: " + nEquiposHumanos);
	    System.out.println("Equipos Skylanders: " + nEquiposNPCs);
	    System.out.println("Jugadores totales: " + (nJugadores + nNPCs));
	    System.out.println("\n");
	}
	
	 
}











