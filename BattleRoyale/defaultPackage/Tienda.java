package defaultPackage;

import java.util.Scanner;

public class Tienda {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final Integer COSTO_MEJORA=50;
	private static final Integer COSTO_CURACION_PEQUENA = 50;
	private static final Integer COSTO_CURACION_GRANDE = 70;
	private static final Integer CURACION_PEQUENA=30;
	private static final Integer CURACION_GRANDE = 60;
	private static final Integer MANA=30;
	private static final Integer COSTO_MANA = 40;
	private static String LOG;
	
	public String menuTienda(Personaje p) {
		LOG="";
	
		int opcion;
		
		Utilidades.imprimirConDelay("\nVendedor: \"¡Bienvenido, viajero! Tengo armas para mejorar\n"
						+ " y pociones para mantenerte con vida. ¿Qué deseas hoy?\"\n", 20);
        
        System.out.println("Tu oro: " + p.getOro() + " 🪙");
        System.out.println("Tu vida actual: " + p.getVida() + " HP ❤️");
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
                		Utilidades.imprimirConDelay("Vendedor: \"Vuelve pronto, viajero.\"\n", 20);
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
			
			Utilidades.imprimirConDelay("\nVendedor: \"Ahh… ¿Quieres fortalecer tu acero? Puedo mejorar cualquiera\r\n"
					+ "de tus armas, por el precio adecuado, claro...\"\n", 20);
			
			System.out.println("===== MEJORAR ARMAS =====");
			System.out.println("Tu oro: " + p.getOro() + " 🪙\n");
			
			System.out.println("Tu arma: " + p.getArma().toString());
			System.out.println("Coste de la mejora --> " + COSTO_MEJORA + " oro\n");
			
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
						System.out.println("Nuevo ataque: " + p.getArma().getAtaque() + " 💥\n");
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
				System.out.println("Volviendo al menu de la tienda...\n");
				break;
			default:
				System.out.println("Opcion no valida\n");
				break;
		}
			
			
		}while(!opcion.equals("S") && !opcion.equals("N"));

	}
	
	public void menuPociones(Personaje p) {
		
		int respuesta;
		
		do {
			
			Utilidades.imprimirConDelay("\nVendedor: \"Ah, las mejores pociones curativas de la región. \r\n"
				+ "Siempre conviene tener algunas encima...\"\n", 20);
		
			System.out.println("===== COMPRAR POCIONES =====");
			System.out.println("Tu oro: " + p.getOro() + " 🪙");
			System.out.println("Tu vida actual: " + p.getVida() + " HP ❤️\n");
			
			System.out.println("[1] Pocion de curacion grande ( +" + CURACION_GRANDE + " HP) ⚗️ --> " + COSTO_CURACION_GRANDE + " oro");
			System.out.println("[2] Pocion de curacion pequeña ( +" + CURACION_PEQUENA + " HP) 🧪 --> " + COSTO_CURACION_PEQUENA + " oro");
			System.out.println("[0] Volver");
			System.out.println("============================\n");
			System.out.println("¿Cual desea comprar?\n");
			System.out.print("> ");
			
			 while (!sc.hasNextInt()) {
	                System.out.println("Por favor, ingresa un número válido.");
	                sc.nextLine();
	                System.out.print("> ");
	            }
	            respuesta = sc.nextInt();
	            sc.nextLine(); // limpiar salto de línea
			
			switch(respuesta) {
				case 1:
					if(p.getOro() >= COSTO_CURACION_GRANDE){
						p.setOro(p.getOro() - COSTO_CURACION_GRANDE);
						
						System.out.println("Vendedor: \"Ya puedes disfrutar de las pociones viajero\"\n");
						
						Integer vidaAnterior=p.getVida();
						p.curarVida(CURACION_GRANDE);
						
						LOG+="\t\t-"+p.getNombre()+" ha recuperado "+ (p.getVida()-vidaAnterior) +" de salud.\n";
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					
					break;
				case 2:
					if(p.getOro() >= COSTO_CURACION_PEQUENA){
						p.setOro(p.getOro() - COSTO_CURACION_PEQUENA);
						
						System.out.println("Vendedor: \"Ya puedes disfrutar de las pociones viajero\"\n");
						
						Integer vidaAnterior=p.getVida();
						p.curarVida(CURACION_PEQUENA);
						
						LOG+="\t\t-"+p.getNombre()+" ha recuperado "+ (p.getVida()-vidaAnterior) +" de salud.\n";
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					
					break;
				case 0:
					System.out.println("Volviendo al menu de la tienda...\n");
					break;
				default:
					System.out.println("Opcion no valida\n");
					break;
			}
			
		}while(respuesta != 0);
	}
	
	public void menuRecuperarMana(Personaje p) {
		
		String respuesta;
		
		do {
			
			Utilidades.imprimirConDelay("Vendedor: \"Ah… maná puro. Esencia destilada de antiguos cristales.\"\n", 20);
			
			System.out.println("===== COMPRAR MANÁ =====");
			System.out.println("Maná actual: " + p.getEnergia() + "/100");
			System.out.println("Tu oro: " + p.getOro() + " 🪙");
			System.out.println("Precio por pocion de maná (+" + MANA + " maná): " + COSTO_MANA + " de oro\n");
			System.out.println("========================\n");
			System.out.println("¿Quieres comprar una pocion de maná? (Si/No)");
			System.out.println("> ");
			
			respuesta = sc.next().toUpperCase().substring(0, 1);
			sc.nextLine();
			
			switch(respuesta){
				case "S":
					if(p.getOro() >= COSTO_MANA) {
						System.out.println("Vendedor: \"¡¡¡Gracias por comprar una pocion de maná viajero!!!\"\n");
						
						p.setOro(p.getOro() - COSTO_MANA);
						
						Integer manaAnterior=p.getEnergia();
						p.recuperarEnergia(MANA);
						
						LOG+="\t\t-"+p.getNombre()+" ha recuperado "+ (p.getEnergia()-manaAnterior) +" de mana.\n";
						
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					break;
				case "N":
					System.out.println("Volviendo al menu de la tienda...\n");
					break;
				default:
					System.out.println("Opcion no valida\n");
			}
			
		}while(!respuesta.equals("S") && !respuesta.equals("N"));
	}

}
