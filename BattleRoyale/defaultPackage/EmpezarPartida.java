package defaultPackage;

import java.util.Scanner;

public class EmpezarPartida {
	
	Scanner sc = new Scanner(System.in);
	
	private String modoJuego;
	private Integer modo;
	
	public static void main(String[] args) {
		new EmpezarPartida();
	}
	
	public EmpezarPartida() {
		establecerModo();
	}

	private void establecerModo() {
		preguntarReglas();
	    seleccionarModo();  	
	    iniciarPartida();
	}
	
	
	private void preguntarReglas() {
		String respuesta;
		
		do {
			System.out.print("¿Quieres escuchar una explicación de las reglas del juego? (Si/No) ");
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
		} while(!respuesta.equals("S") && !respuesta.equals("N"));
		
		if(respuesta.equals("S")) {
			mostrarReglas();
		}
	}
	
	private void mostrarReglas() {
	    System.out.println("\n=== REGLAS DEL BATTLE ROYALE ELEMENTAL ===\n");
	    
	    System.out.println("🎮 OBJETIVO:");
	    System.out.println("   • Ser el último jugador o equipo en pie");
	    System.out.println("   • Los equipos pierden cuando todos sus integrantes caen\n");
	    
	    System.out.println("👥 MODOS DE JUEGO:");
	    System.out.println("   1. SOLITARIO - Cada jugador por su cuenta");
	    System.out.println("   2. DUOS - Equipos de 2 jugadores");
	    System.out.println("   3. ESCUADRONES - Equipos de 4 jugadores\n");
	    
	    System.out.println("⚔️ SISTEMA DE PERSONAJES:");
	    System.out.println("   • 6 clases elementales: FUEGO, AGUA, TIERRA, AIRE, MAGIA, VIDA");
	    System.out.println("   • Cada elemento tiene ventajas/desventajas contra otros");
	    System.out.println("   • Estadísticas iniciales: Fuerza, Defensa, Velocidad, Suerte");
	    System.out.println("   • Todos empiezan con el arma base 'Pico'");
	    System.out.println("   • Jugadores reales: Eligen nombre, elemento y distribuyen puntos de nivel");
	    System.out.println("   • NPCs: Se generan aleatoriamente\n");
	    
	    System.out.println("🎲 SISTEMA DE TURNOS Y EVENTOS:");
	    System.out.println("   • Cada turno se puede elegir un evento:");
	    System.out.println("     🎁 COFRE - Encuentra armas mejoradas con rareza (afectada por Suerte)");
	    System.out.println("                Jugadores ganan oro");
	    System.out.println("     🛒 TIENDA - Compra pociones de vida o mejora tu arma");
	    System.out.println("     ⚔️ ENEMIGO - Batalla contra otro jugador o equipo\n");
	    
	    System.out.println("⚡ SISTEMA DE COMBATE:");
	    System.out.println("   • Ataque básico: Depende del arma equipada");
	    System.out.println("   • Habilidades elementales: 3 por clase, consumen poder elemental");
	    System.out.println("   • Turnos individuales con menú de opciones");
	    System.out.println("   • En equipos: Elegir enemigo objetivo antes de atacar");
	    
	    System.out.println("📈 PROGRESIÓN:");
	    System.out.println("   • Victoria en batalla = Oro + Experiencia");
	    System.out.println("   • Subir de nivel mejora estadísticas");
	    System.out.println("   • Probabilidades de eventos cambian por ronda");
	    System.out.println("   • Al principio: Más cofres, menos batallas");
	    System.out.println("   • Final: Más batallas, menos cofres\n");
	    
	    System.out.println("🏃‍♂️ OPCIONES ESPECIALES:");
	    System.out.println("   • Buscar cofre, buscar enemigo o buscar tienda (con riesgo de fallo)");
	    
	    System.out.println("🏆 FINAL DE LA PARTIDA:");
	    System.out.println("   • Victoria anunciada para el ganador/equipo");
	    System.out.println("   • Opción de guardar partida completa en archivo");
	    System.out.println("   • Si no quedan jugadores reales, gana NPC/equipo aleatorio\n");
	    
	    System.out.println("==============================================\n");
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
	    			System.out.println("Por favor, ingresa un número válido");
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
	
	private void iniciarPartida() {
		if(modo == 1) {
			new Partida(modoJuego);
		} else {
    			new PartidaEquipos(modoJuego);
		}
	}	
}
