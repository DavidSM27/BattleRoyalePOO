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

		
		Utilidades.imprimirConDelay("¡SALUDOS MAESTRO DEL PORTAL!\n", 20);
		Utilidades.sleep(500);
		
		do {
			System.out.print("¿Quieres saltar la introduccion? (SI/NO) ");
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			if(!respuesta.equals("S") && !respuesta.equals("N")) {
				System.out.println("\nPor favor introduce SI o NO\n");
			}
			
		} while(!respuesta.equals("S") && !respuesta.equals("N"));
		
		if(respuesta.equals("N")) {
			mostrarIntroduccion();
		}
		
		
		do {
			System.out.print("¿Quieres escuchar una explicación de las reglas del juego? (SI/NO) ");
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			if(!respuesta.equals("S") && !respuesta.equals("N")) {
				System.out.println("\nPor favor introduce SI o NO\n");
			}
			
		} while(!respuesta.equals("S") && !respuesta.equals("N"));
		
		if(respuesta.equals("S")) {
			mostrarReglas();
		}
	}
	
	private void mostrarReglas() {
	    System.out.println("\n=== REGLAS DEL BATTLE ROYALE SKYLANDER ===\n");
	    
	    System.out.println("🎮 OBJETIVO:");
	    System.out.println("   • Ser el último jugador o equipo en pie");
	    System.out.println("   • Los equipos pierden cuando todos sus integrantes caen\n");
	    Utilidades.sleep(3000);
	    System.out.println("👥 MODOS DE JUEGO:");
	    System.out.println("   1. SOLITARIO - Cada jugador por su cuenta");
	    System.out.println("   2. DUOS - Equipos de 2 jugadores");
	    System.out.println("   3. ESCUADRONES - Equipos de 4 jugadores\n");
	    Utilidades.sleep(3000);
	    System.out.println("⚔️ SISTEMA DE PERSONAJES:");
	    System.out.println("   • 6 clases elementales: FUEGO, AGUA, TIERRA, AIRE, MAGIA, VIDA");
	    System.out.println("   • Cada elemento tiene ventajas/desventajas contra otros");
	    System.out.println("   • Estadísticas iniciales: Fuerza, Defensa, Velocidad, Suerte");
	    System.out.println("   • Todos empiezan con el arma base 'Pico'");
	    System.out.println("   • Jugadores reales: Eligen nombre, elemento y distribuyen puntos de nivel");
	    System.out.println("   • NPCs: Se generan aleatoriamente\n");
	    Utilidades.sleep(6000);
	    System.out.println("🎲 SISTEMA DE TURNOS Y EVENTOS:");
	    System.out.println("   • Cada turno se puede elegir un evento:");
	    System.out.println("     🎁 COFRE - Encuentra armas mejoradas con rareza (afectada por Suerte)");
	    System.out.println("                Jugadores ganan oro");
	    System.out.println("     🛒 TIENDA - Compra pociones de vida o mejora tu arma");
	    System.out.println("     ⚔️ ENEMIGO - Batalla contra otro jugador o equipo\n");
	    Utilidades.sleep(6000);
	    System.out.println("⚡ SISTEMA DE COMBATE:");
	    System.out.println("   • Ataque básico: Depende del arma equipada");
	    System.out.println("   • Habilidades elementales: 3 por clase, consumen poder elemental");
	    System.out.println("   • Turnos individuales con menú de opciones");
	    System.out.println("   • En equipos: Elegir enemigo objetivo antes de atacar\n");
	    Utilidades.sleep(5000);
	    System.out.println("📈 PROGRESIÓN:");
	    System.out.println("   • Victoria en batalla = Oro + Experiencia");
	    System.out.println("   • Subir de nivel mejora estadísticas");
	    System.out.println("   • Probabilidades de eventos cambian por ronda");
	    System.out.println("   • Al principio: Más cofres, menos batallas");
	    System.out.println("   • Final: Más batallas, menos cofres\n");
	    Utilidades.sleep(5000);
	    System.out.println("🏃‍♂️ OPCIONES ESPECIALES:");
	    System.out.println("   • Buscar cofre, buscar enemigo o buscar tienda (con riesgo de fallo)");
	    Utilidades.sleep(2000);
	    System.out.println("🏆 FINAL DE LA PARTIDA:");
	    System.out.println("   • Victoria anunciada para el ganador/equipo");
	    System.out.println("   • Opción de guardar partida completa en archivo");
	    System.out.println("   • Si no quedan jugadores reales, gana NPC/equipo aleatorio\n");   
	    System.out.println("==============================================");
	    Utilidades.sleep(4000);
	}
	
	private void seleccionarModo() {
		System.out.println("\n¿A que modo de juego quieres jugar?");
	    System.out.println("(1) SOLITARIO");
	    System.out.println("(2) DUOS");
	    System.out.println("(3) ESCUADRONES");
	    do {
	    		System.out.print("Modo: ");
	    		while(!sc.hasNextInt()) {
	                System.out.println("\nPor favor, ingresa un número válido.\n");
	                System.out.print("Modo: ");
	                sc.next();
	        }
	    		modo = sc.nextInt();
	    		sc.nextLine();
	    		
	    		if(modo < 1 || modo > 3) {
	    			System.out.println("\nPor favor, ingresa un número válido");
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
			Partida partida = new Partida(modoJuego);
			partida.iniciarJuego();
		} else {
    		PartidaEquipos partida = new PartidaEquipos(modoJuego);
    		partida.iniciarJuego();
		}
	}
	
	private void mostrarIntroduccion(){
		Utilidades.imprimirConDelay("\nSoy Eon, tu guía en este mundo", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("Pero te preguntaras donde estas", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("\n¡Esto es Skylands!", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("Un antiguo mundo de maravillas y misterios", 20);	
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("Durantes generaciones los maestros del portal y sus Skylanders han mantenido", 20);
		Utilidades.imprimirConDelay("la paz y el equilibrio para que las criaturas prosperen.", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("¡Aqui la magia fluye a traves de todo!", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("Cada piedra, cada arbol, cada bestia", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("\nHoy en día, los Skylanders pasan las horas entrenando entre ellos en una", 20);
		Utilidades.imprimirConDelay("lucha constante de todos contra todos, ya sea individualmente o por equipos.", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("Los Skyalandres me han comentado que quieren luchar contra ti, maestro del portal", 20);
		Utilidades.sleep(500);
		Utilidades.imprimirConDelay("\nQue me dices, ¿aceptas el desafio?\n", 20);
	}
}
