package defaultPackage;

import java.util.Scanner;

public class Tienda {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final Integer COSTO_MEJORA=50;
	private static final Integer COSTO_POCIONES=50;
	private static final Integer CURACION=30;
	private static final Integer MANA=30;
	private static String LOG;
	
	public String menuTienda(Personaje p) {
		LOG="";
	
		int opcion;
		
		System.out.println("\nVendedor: \"¡Bienvenido, viajero! Tengo armas para mejorar");
        System.out.println("y pociones para mantenerte con vida. ¿Qué deseas hoy?\"\n");
        
        System.out.println("Tu oro: " + p.getOro() + " 🪙");
        System.out.println("Tu vida actual: " + p.getVida() + " ps");
        System.out.println("Tu Maná actual: " + p.getEnergia() + "/100\n");
		
        do {
        	System.out.println("========== MENÚ DE LA TIENDA ==========");
            System.out.println("[1] Mejorar armas ⚔️");
            System.out.println("[2] Comprar pociones de curacion 🫙");
            System.out.println("[3] Comprar pociones de maná 🌀");
            System.out.println("[0] Salir ❌");
            System.out.println("=======================================");
            System.out.print("> ");

            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                sc.nextLine();
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
                case 3:
                	menuRecuperarMana(p);
                	break;
                case 0:
                    System.out.println("Vendedor: \"Vuelve pronto, viajero.\"");
                    break;
                default:
                    System.out.println("Opcion no valida.\n");
            }

        } while (opcion != 0);
        
        return LOG;
	}
	
	public void menuMejorarArma(Personaje p) {
		
		String opcion;
		
		do {
			
			System.out.println("\nVendedor: \"Ahh… ¿Quieres fortalecer tu acero? Puedo mejorar cualquiera\r\n"
					+ "de tus armas, por el precio adecuado, claro...\"\n");
			System.out.println("===== MEJORAR ARMAS =====");
			System.out.println("Tu oro: " + p.getOro() + "\n");
			
			System.out.println("Tu arma: " + p.getArma().toString());
			System.out.println("Coste de la mejora: " + COSTO_MEJORA + " oro\n");
			
			System.out.print("Vendedor: \"¿Quieres mejorar tu "+ p.getArma().getNombre() + "? (Si/No) ");
			
			opcion = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			switch(opcion) {
			case "S":
				if(!p.getArma().getNombre().equals(Arma.ARMA_DEFAULT)) {
					if(p.getOro() >= COSTO_MEJORA){
					
						p.setOro(p.getOro() - COSTO_MEJORA);
						p.getArma().modificacion(p.getArma().getMejora()+0.1);
						
						System.out.println("Has mejorado tu " + p.getArma().getNombre() + " 🗡️");
						System.out.println("Nuevo ataque: " + p.getArma().getAtaque() + "\n");
						LOG+="\t\t-"+p.getNombre()+" ha mejorado su "+p.getArma().getNombre()+
								": Mejora="+p.getArma().getMejora()+"\n";
						
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
			System.out.println("\nVendedor: \"Ah, las mejores pociones curativas de la región. \r\n"
				+ "Siempre conviene tener algunas encima...\"\n");
		
			System.out.println("===== COMPRAR POCIONES =====");
			System.out.println("Tu oro: " + p.getOro() + " 🪙");
			System.out.println("Tu vida actual: " + p.getVida() + " ps\n");
			System.out.println("Precio por pocion de curacion (+" + CURACION + " PS): " + COSTO_POCIONES + " de oro\n");
			
			
			
			System.out.print("¿Quiere comprar una pocion? (Si/No) ");
			
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			switch(respuesta) {
				case "S":
					if(p.getOro() >= COSTO_POCIONES){
						p.setOro(p.getOro() - COSTO_POCIONES);
						
						System.out.println("Vendedor: \"Ya puedes disfrutar de las pociones viajero\"\n");
						
						Integer vidaAnterior=p.getVida();
						p.curarVida(p.getVida() + CURACION);//por ejemplo luego definimos cuanto curan
						
						LOG+="\t\t-"+p.getNombre()+" ha recuperado "+CURACION+" de salud.\n";
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
	
	public void menuRecuperarMana(Personaje p) {
		
		String respuesta;
		
		do {
			System.out.println("Vendedor: \"Ah… maná puro. Esencia destilada de antiguos cristales.\"\n");
			System.out.println("===== COMPRAR MANÁ =====");
			System.out.println("Maná actual: " + p.getEnergia() + "/100");
			System.out.println("Tu oro: " + p.getOro() + " 🪙");
			System.out.println("Precio por pocion de maná (+" + MANA + " maná): " + COSTO_POCIONES + " de oro\n");
			System.out.println("¿Quieres comprar una pocion de maná? (Si/No)");
			
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			switch(respuesta){
				case "S":
					if(p.getOro() >= COSTO_POCIONES) {
						System.out.println("Vendedor: \"¡¡¡Gracias por comprar una pocion de maná viajero!!!!\"\n");
						
						p.setOro(p.getOro() - COSTO_POCIONES);
						p.recuperarEnergia(p.getEnergia() + MANA);
						
						LOG+="\t\t-"+p.getNombre()+" ha recuperado "+MANA+" de mana.\n";
						
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					break;
				case "N":
					System.out.println("Vendedor: \"Vuelve pronto, viajero.\"\n");
					break;
				default:
					System.out.println("Opcion no valida\n");
			}
			
		}while(!respuesta.equals("S") && !respuesta.equals("N"));
	}
	
	public static void main(String[] args) {
	
        // Crear un personaje para probar
        Personaje jugador = new Personaje("Jugador de prueba");
        jugador.equiparArma(new Arma("Fusil", 30., 1.9));
        jugador.setOro(20000); //darle algo de oro para probar mejoras
        //jugador.getArma().setMejora(1.9);
        jugador.setEnergia(80);
        

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
