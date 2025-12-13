package defaultPackage;

import java.util.Scanner;


public class Partida {
	
	Scanner sc = new Scanner(System.in);
	
	protected String modoJuego;
	protected Integer nJugadores;
	protected Integer nNPCs;
	
	Partida(String modoJuego){
		this.modoJuego = modoJuego;
		iniciarJuego();
		
	}	
	  
	public void iniciarJuego(){
		establecerReglas();
	
	}
	
	public void establecerReglas() {
		
	    nJugadores = numeroJugadores();
	    nNPCs = numeroNPCs();
	    
	    	System.out.println("\n=== CONFIGURACIÓN COMPLETA ===");
        System.out.println("Modo: " + modoJuego);
        System.out.println("Jugadores reales: " + nJugadores);
        System.out.println("NPCs: " + nNPCs);
        System.out.println("Total de jugadores: " + (nJugadores + nNPCs));    	
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
        		if(jugadores < 1) {
        			System.out.println("Debe haber al menos 1 jugador");
        		}
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
        		
        		if(npcs < 1) {
        			System.out.println("Debe haber al menos 1 bot");
        		}
        } while(npcs < 1);
    		System.out.println();
        
        return npcs;
    }	
	
}	