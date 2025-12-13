package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Partida {
	
	Scanner sc = new Scanner(System.in);
	
	protected String modoJuego;
	protected Integer nJugadores;
	protected Integer nNPCs;
	
	protected List<Personaje> personajes = new ArrayList<>();
	protected List<Personaje> personajesVivos = new ArrayList<>();
	protected List<Personaje> jugadoresHumanos = new ArrayList<>();
	protected List<Personaje> jugadoresNPCs = new ArrayList<>();
	
	Partida(String modoJuego){
		this.modoJuego = modoJuego;
		iniciarJuego();
		
	}	
	  
	public void iniciarJuego(){
		establecerReglas();
		crearPersonajes();
		mostrarResumenPersonajes();
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
	
    public void crearPersonajes() {
        System.out.println("\n=== CREACIÓN DE PERSONAJES ===");
        
        // 1. CREAR JUGADORES HUMANOS
        for(int i = 1; i <= nJugadores; i++) {
            Personaje jugador = crearJugador(i);
            jugadoresHumanos.add(jugador);
            personajes.add(jugador);
            personajesVivos.add(jugador);
        }
        
        // 2. CREAR NPCs
        for(int i = 1; i <= nNPCs; i++) {
            Personaje npc = crearNPC(i);
            jugadoresNPCs.add(npc);
            personajes.add(npc);
            personajesVivos.add(npc);
        }
        
        System.out.println("✅ " + personajes.size() + " personajes creados.");
    }
    
    private Personaje crearJugador(int numero) {
        int elemento;
    		
    		System.out.println("\n--- Jugador " + numero + " ---");
        
        // 1. PEDIR NOMBRE
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        // 2. ELEGIR ELEMENTO
        System.out.println("\nElige elemento:");
        System.out.println("1. FUEGO   2. AGUA   3. TIERRA");
        System.out.println("4. VIENTO  5. MAGIA  6. VIDA");
        
        do {
    		System.out.print("Elemento: ");
    		while(!sc.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                System.out.print("Elemento: ");
                sc.next();
        }
    		elemento = sc.nextInt();
    		sc.nextLine();
    		
    		if(elemento < 1 || elemento > 6) {
    				System.out.println("Por favor, ingresa un número válido");
    				System.out.println("1. FUEGO   2. AGUA   3. TIERRA");
    		        System.out.println("4. VIENTO  5. MAGIA  6. VIDA");
    			}   		
        } while(elemento < 1 || elemento > 6);
        System.out.println();
        
        // 3. CREAR OBJETO SEGÚN ELEMENTO
        return crearPersonajePorElemento(nombre, elemento);
    }
    
    private Personaje crearNPC(int numero) {
        String[] nombresNPC = {"Thor", "Luna", "Kai", "Zara", "Rex", "Nova"};
        String nombre = nombresNPC[(numero-1) % nombresNPC.length] + "_" + numero;
        
        // Elemento aleatorio 1-6
        int elemento = (int)(Math.random() * 6) + 1;
        
        Personaje npc = crearPersonajePorElemento(nombre, elemento);
        
        return npc;
    }
    
    private Personaje crearPersonajePorElemento(String nombre, int elemento) {
        switch(elemento) {
            //case 1: // FUEGO
                //return new Fuego(nombre, 1);  // nivel 1
            case 2: // AGUA
                return new Agua(nombre, 1);
            case 3: // TIERRA
                return new Tierra(nombre, 1);
            case 4: // VIENTO
                return new Viento(nombre, 1);
            case 5: // MAGIA
                return new Magia(nombre, 1);
            case 6: // VIDA
                return new Vida(nombre, 1);
            default:
                return new Vida(nombre, 1);
        }
    }
    
    private void mostrarResumenPersonajes() {
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        for(int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            String tipo = jugadoresHumanos.contains(p) ? "[Humano]" : "[NPC]";
            System.out.println((i+1) + ". " + p.getNombre() + " " + tipo);
        }
    }
    
}	