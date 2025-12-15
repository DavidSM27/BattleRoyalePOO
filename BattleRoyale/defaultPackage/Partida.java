package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Partida {
	
	Scanner sc = new Scanner(System.in);
	
	protected String modoJuego;
	protected Integer nJugadores;
	protected Integer nNPCs;
	
	protected List<Personaje> personajes;
	protected List<Personaje> personajesVivos;
	protected List<Personaje> jugadoresHumanos;
	protected List<Personaje> jugadoresNPCs;
	
	private ListaNombresNPC nombreNPC;
	
	public Partida(String modoJuego){
		this.modoJuego = modoJuego;
		
		this.personajes = new ArrayList<Personaje>();
		this.personajesVivos = new ArrayList<Personaje>();
		this.jugadoresHumanos = new ArrayList<Personaje>();
		this.jugadoresNPCs = new ArrayList<Personaje>();
		
		this.nombreNPC = new ListaNombresNPC();
	}	
	
	protected void iniciarJuego(){
		establecerReglas();
		crearPersonajes();
		mostrarResumenPersonajes();
		new Evento(personajesVivos);
	}
	
	protected void establecerReglas() {
		
	    nJugadores = numeroJugadores();
	    nNPCs = numeroNPCs();
	    
	    System.out.println("\n=== CONFIGURACIÓN COMPLETA ===");
        System.out.println("Modo de juego: " + modoJuego);
        System.out.println("Maestros del Portal: " + nJugadores);
        System.out.println("Skyalanders: " + nNPCs);
        System.out.println("Jugadores totales: " + (nJugadores + nNPCs));    	
	}
	
	protected int numeroJugadores() {
        int jugadores = 0;
        
        System.out.println("¿Cuantos Maestros de Portal van a jugar?");
        do {
    			System.out.print("Maestros del portal: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("\nPor favor, ingresa un número válido.\n");
    				System.out.print("Maestros de Portal: ");
    				sc.next();
    			}
    			
    			jugadores = sc.nextInt();
        		sc.nextLine();
        		if(jugadores < 1) {
        			System.out.println("\nDebe haber al menos 1 Maestros del Portal\n");
        		}
        } while(jugadores < 1);
    		System.out.println();
        
        return jugadores;
    } 
    
	protected int numeroNPCs() {
        int npcs = 0;
        
        System.out.println("¿Cuantos Skylanders van a jugar?");
        do {
    			System.out.print("Skylanders: ");
    			while(!sc.hasNextInt()) {
    				System.out.println("Por favor, ingresa un número válido.");
    				System.out.print("Skylanders: ");
    				sc.next();
    			}
    			
    			npcs = sc.nextInt();
        		sc.nextLine();
        		
        		if(npcs < 1) {
        			System.out.println("Debe haber al menos 1 Skylanders");
        		}
        } while(npcs < 1);
    		System.out.println();
        
        return npcs;
    }	
	
	protected void crearPersonajes() {
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
        	try {
        		Personaje npc = crearNPC(i);
        		jugadoresNPCs.add(npc);
        		personajes.add(npc);
        		personajesVivos.add(npc);
        	} catch (Exception e) {
        		this.nNPCs = this.jugadoresNPCs.size();
        		System.out.println("Numero de Skylanders alcanzado, los Skylanders creados an sido " + nNPCs);
        	}
        }
        
        System.out.println("✅ " + personajes.size() + " jugadores creados.");
    }
    
	private Personaje crearJugador(int numero) {
        int elemento;
        boolean esNpc = false;
    		
    	System.out.println("\n--- Maestro del Portal " + numero + " ---");
        
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
        return crearPersonajePorElemento(nombre, elemento, esNpc);
    }
    
    private Personaje crearNPC(int numero) {
    	boolean esNpc = true;
    	
        // Elemento aleatorio 1-6
        int elemento = (int)(Math.random() * 6) + 1;
        
        Personaje npc = crearPersonajePorElemento(this.nombreNPC.getRandomNombres(), elemento, esNpc);
        	
        return npc;
    }
    
    protected Personaje getRandomPersonajeNPC() {
		Integer random=(int) (Math.round(Math.random()*this.jugadoresNPCs.size()) %this.jugadoresNPCs.size());
		Personaje nombre=this.jugadoresNPCs.get(random);
		this.jugadoresNPCs.remove((int)random);
		return nombre;
	}
    
    private Personaje crearPersonajePorElemento(String nombre, int elemento, boolean esNPC) {
        switch(elemento) {
            case 1: // FUEGO
                return new Fuego(nombre, 1, esNPC);  // hay que
            case 2: // AGUA
                return new Agua(nombre, 1, esNPC);
            case 3: // TIERRA
                return new Tierra(nombre, 1, esNPC);
            case 4: // VIENTO
                return new Viento(nombre, 1, esNPC);
            case 5: // MAGIA
                return new Magia(nombre, 1, esNPC);
            case 6: // VIDA
                return new Vida(nombre, 1, esNPC);
            default:
                return new Fuego(nombre, 1, esNPC);
        }
    }
    
    private void mostrarResumenPersonajes() {
        System.out.println("\n=== LISTA DE JUGADORES ===");
        for(int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            String tipo = jugadoresHumanos.contains(p) ? "[Maestro del Portal]" : "[Skylander]";
            System.out.println((i+1) + ". " + p.getNombre() + " " + tipo + " " + p.getElemento());
            
        }
        System.out.println("\n");
    }
    
}	