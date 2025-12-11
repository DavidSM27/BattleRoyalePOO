package defaultPackage;

import java.util.Scanner;

public class EmpezarPartida {
	
	Scanner sc = new Scanner(System.in);
	
	private Boolean equipos;
	private String respuesta;
	private String modoJuego;
	private Integer modo;
	
	
	public static void main(String[] args) {
		System.out.println("¡BIENVENIDO AL BATTLE ROYALE!");
		EmpezarPartida config = new EmpezarPartida();	
		
	}
	
	
	public EmpezarPartida() {
		Partida partida = establecerModo();
		partida.iniciarJuego();
	}



	public Partida establecerModo() {
		do {
	        System.out.println("¿Quieres escuchar una explicación de las reglas del juego? (S/N)");
	        respuesta = sc.nextLine().toUpperCase();
	    } while(!respuesta.equals("S") && !respuesta.equals("N"));
		
	    if(respuesta.equals("S")) {
	        mostrarReglas();
	    }
		
	    seleccionarModo();  	
	    
	    if(modo.equals(1)) {
    			return new Partida();
	    } else if(modo.equals(2)) {
	    		return new PartidaPorEquipos();
	    } else {
	    		return new PartidaPorEquipos();
	    }
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
	    		equipos = false;
	    } else if(modo.equals(2)) {
	    		modoJuego = "DUOS";
	    		equipos = true;
	    } else {
	    		modoJuego = "ESCUADRONES";
	    		equipos = true;
	    }
	}
	
}
