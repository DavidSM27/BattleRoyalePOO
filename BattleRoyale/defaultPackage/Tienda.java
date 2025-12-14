package defaultPackage;

import java.util.Scanner;

public class Tienda {
	
	private static Scanner sc=new Scanner(System.in);
	private static final Integer COSTO_MEJORA=50;
	private static final Integer COSTO_POCIONES=50;
	private static final Integer CURACION=30;
	

    public Tienda() {
    	sc=new Scanner(System.in);
    }
	
	public void menuTienda(Personaje p) {
	
		int opcion;
		
		System.out.println("Vendedor: \"¡Bienvenido, viajero! Tengo armas para mejorar");
        System.out.println("y pociones para mantenerte con vida. ¿Qué deseas hoy?\"\n");
		
        do {
        	System.out.println("========== MENÚ DE LA TIENDA ==========");
            System.out.println("[1] Mejorar armas ⚔️");
            System.out.println("[2] Comprar pociones 🫙");
            System.out.println("[0] Salir ❌");
            System.out.println("=======================================");
            System.out.print("> ");

            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                sc.next();
                System.out.print("> ");
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea

            switch (opcion) {
                case 1:
                	if(p.getArma().getMejora() < 2.0) {
                    menuMejorarArma(p);
                	}else {
    					System.out.println("Tu arma esta al maximo\n");
    				}
                    break;
                case 2:
                    menuPociones(p); 
                    break;
                case 0:
                    System.out.println("Vendedor: \"Vuelve pronto, viajero.\"");
                    break;
                default:
                    System.out.println("Opcion no valida.\n");
            }

        } while (opcion != 0);

	}
	
	public void menuMejorarArma(Personaje p) {
		
		String opcion;
		
		do {
			
			System.out.println("Vendedor: \"Ahh… ¿Quieres fortalecer tu acero? Puedo mejorar cualquiera\r\n"
					+ "de tus armas, por el precio adecuado, claro...\"\n");
			System.out.println("===== MEJORAR ARMAS =====");
			System.out.println("Tu oro: " + p.getOro() + "\n");
			
			System.out.println("Tu arma: " + p.getArma().toString());
			System.out.println("Coste de la mejora: " + COSTO_MEJORA + " oro\n");
			
			System.out.println("Vendedor: \"¿Quieres mejorar tu "+ p.getArma().getNombre() + "?");
			
			System.out.print("> ");
			
			opcion = sc.nextLine().toUpperCase();
			
			switch(opcion) {
			case "S":
				if(!p.getArma().getNombre().equals(Arma.ARMA_DEFAULT)) {
					if(p.getOro() >= COSTO_MEJORA){
					
						p.setOro(p.getOro() - COSTO_MEJORA);
						p.getArma().modificacion(p.getArma().getMejora()+0.1);
						
						System.out.println("Has mejorado tu " + p.getArma().getNombre() + " 🗡️");
						System.out.println("Nuevo ataque: " + p.getArma().getAtaque() + "\n");
						
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
				}
				else {
					System.out.println("No puedes mejorar el pico ⛏️\n");
				}
				
				break;
			case "N":
				System.out.println("Volviendo al menu de la tienda🛒\n");
				break;
			default:
				System.out.println("Opcion no valida\n");
				break;
		}
			
			
		}while(!opcion.equals("S") && !opcion.equals("N"));

	}
	
	public void menuPociones(Personaje p) {
		
		String respuesta;
		
		do {
			System.out.println("Vendedor: \"Ah, las mejores pociones curativas de la región. \r\n"
				+ "Siempre conviene tener algunas encima...\"\n");
		
			System.out.println("===== COMPRAR POCIONES =====");
			System.out.println("Tu oro: " + p.getOro() + " 🪙");
			System.out.println("Precio por pocion de curacion (+" + CURACION + " PS): " + COSTO_POCIONES + " de oro\n");
			
			System.out.println("Tu vida actual: " + p.getVida() + " ps\n");
			
			System.out.println("¿Quiere comprar una pocion? (S/N)");
			
			System.out.print("> ");
			
			respuesta = sc.nextLine().toUpperCase();
			
			switch(respuesta) {
				case "S":
					if(p.getOro() >= COSTO_POCIONES){
						p.setOro(p.getOro() - COSTO_POCIONES);
						
						System.out.println("Vendedor: \"Ya puedes disfrutar de las pociones viajero\"\n");
						
						p.setVida(p.getVida() + CURACION);//por ejemplo luego definimos cuanto curan
						
						System.out.println("Te has curado " + CURACION + " de vida, tu vida actual: " + p.getVida() +" ps\n");
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					
					break;
				case "N":
					System.out.println("Vendedor: \"Vuelve pronto, viajero.\"\n");
					break;
				default:
					System.out.println("Opcion no valida\n");
					break;
			}
			
			
		}while(!respuesta.equals("S") && !respuesta.equals("N"));
	}
	
	public static void main(String[] args) {
	
        // Crear un personaje para probar
        Personaje jugador = new Personaje("Jugador de prueba");
        jugador.equiparArma(new Arma("Fusil", 30., 1.9));
        jugador.setOro(20000); //darle algo de oro para probar mejoras
        jugador.getArma().setMejora(1.9);
        

        // Crear la tienda
        Tienda tienda = new Tienda();

        // Mostrar menú de tienda
        tienda.menuTienda(jugador);

        System.out.println("\n=== ESTADO FINAL DEL JUGADOR ===");
        System.out.println("Arma equipada: " + jugador.getArma().getNombre());
        System.out.println("Ataque: " + jugador.getArma().getAtaque());
        System.out.println("Oro restante: " + jugador.getOro());
    }
	
	
}
