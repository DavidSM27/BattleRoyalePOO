package defaultPackage;

import java.util.Scanner;


public class Partida {
	
	Scanner sc = new Scanner(System.in);
	
	protected String modoJuego;
	private Integer modo;
	private Integer nJugadores;
	private Integer nNPCs;
	
	public void iniciarJuego(){
		establecerReglas();
		System.out.println(modoJuego);
	
	}
	
	public void establecerReglas() {
		
		
	    seleccionarModo();
	    nJugadores = numeroJugadores();
	    nNPCs = numeroNPCs();
	    
	    	System.out.println("\n=== CONFIGURACIÓN COMPLETA ===");
        System.out.println("Modo: " + modoJuego);
        System.out.println("Jugadores reales: " + nJugadores);
        System.out.println("NPCs: " + nNPCs);
        System.out.println("Total de jugadores: " + (nJugadores + nNPCs));    	
	}
	
	private void mostrarReglas(){
		System.out.println("REGLAS");
	}
	
	private void seleccionarModo() {
		System.out.println("¿A que modo de juego quieres jugar?");
	    System.out.println("(1) SOLITARIO");
	    System.out.println("(2) DUOS");
	    System.out.println("(3) ESCUADRONES");
	    do {
	    		System.out.print("Modo: ");
	    		while(!sc.hasNextInt()) {
	                System.out.println("Por favor, ingresa un número válido.");
	                System.out.print("Modo: ");
	                sc.next();
	        }
	    		modo = sc.nextInt();
	    		sc.nextLine();
	    		
	    		if(modo < 1 || modo > 3) {
	    			System.out.println("Opcion invalida, intentelo de nuevo");
	    			System.out.println("\n(1) SOLITARIO");
	    		    System.out.println("(2) DUOS");
	    		    System.out.println("(3) ESCUADRONES");
	    		}   		
	    } while(modo < 1 || modo > 3);
	    System.out.println();
	    
	    if(modo.equals(1)) {
	    		modoJuego = "SOLITARIO";
	    } else if(modo.equals(2)) {
	    		modoJuego = "DUOS";
	    } else {
	    		modoJuego = "ESCUADRONES";
	    }
	}
	
	public int numeroJugadores() {
        int jugadores = 0;
        
        System.out.println("¿Cuantos personas van a jugar?");
        do {
    			System.out.print("Jugadores: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Jugadores: ");
    				sc.next();
    			}
    			
    			jugadores = sc.nextInt();
        		sc.nextLine();
        } while(jugadores < 1);
    		System.out.println();
        
        return jugadores;
    } 
    
    public int numeroNPCs() {
        int npcs = 0;
        
        System.out.println("¿Cuantos bots van a jugar?");
        do {
    			System.out.print("Bots: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Bots: ");
    				sc.next();
    			}
    			
    			npcs = sc.nextInt();
        		sc.nextLine();
        } while(npcs < 1);
    		System.out.println();
        
        return npcs;
    }	
	
}	