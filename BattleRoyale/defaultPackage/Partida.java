package defaultPackage;

import java.util.Scanner;


public class Partida {
	
	Scanner sc = new Scanner(System.in);
	
	protected String modoJuego;
	private Integer nJugadores;
	private Integer nNPCs;
	
	Partida(String modoJuego){
		this.modoJuego = modoJuego;
		System.out.println("Estamos en solitario");
		
	}	
	
	private Boolean equipos;
	private String respuesta;
	//private String modoJuego;
	private Integer modo;
	
	
	public static void main(String[] args) {
		System.out.println("¡BIENVENIDO AL BATTLE ROYALE!");
		EmpezarPartida config = new EmpezarPartida();	
		
	}
	
	public void iniciarJuego(){
		establecerReglas();
		System.out.println(modoJuego);
	
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