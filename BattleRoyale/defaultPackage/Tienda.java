package defaultPackage;

import java.util.Scanner;

public class Tienda {
	
	 private Scanner sc;

    public Tienda(Scanner sc) {
        this.sc = sc;
    }
	
	public void menuTienda(Personaje p) {
	
		int opcion;

        do {
            System.out.println("Vendedor: \"¡Bienvenido, viajero! Tengo armas para mejorar");
            System.out.println("y pociones para mantenerte con vida. ¿Qué deseas hoy?\"\n");
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
                    menuMejorarArma(p);
                    break;
                case 2:
                    //menuPociones(p); // si la haces luego
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
		
		System.out.println("Vendedor: \"Ahh… ¿Quieres fortalecer tu acero? Puedo mejorar cualquiera\r\n"
				+ "de tus armas, por el precio adecuado, claro...\"\n");
		System.out.println("===== MEJORAR ARMAS =====");
		System.out.println("Tu oro: " + p.getOro() + "\n");
		System.out.println("Elige un arma para mejorar: \n");
		
		System.out.println("[1] " + p.getArma().getNombre() + "- Coste: 20 oro");
		System.out.println("[2] Volver");
		
		int opcion;
		System.out.println("> ");
		
		//compruba si es un numero, sino lo es te lo pide de nuevo
		while(!sc.hasNextInt()) {
			System.out.println("Por favor, igresa un numero valido");
			sc.next();
			System.out.println("> ");
		}
		
		opcion = sc.nextInt();
		
		switch(opcion) {
			case 1:
				if(!p.getArma().getNombre().equals(Arma.ARMA_DEFAULT)) {
					
					System.out.println("Vendedor: \"¿Quieres mejorar tu "+ p.getArma().getNombre() + "? Puedo hacerla brillar de nuevo…\r\n"
							+ "pero te costará 20 de oro.\"\n");
				
					if(p.getOro() >= 20){//20 por ejemplo, hay que ver cuanto cuesta cada mejora
						
						
						p.setOro(p.getOro() - 20);
						p.getArma().modificacion(1.2); // ya veremos como metemos las mejoras
						
						System.out.println("Has mejorado tu " + p.getArma().getNombre() + " 🗡️");
						System.out.println("Nuevo ataque: " + p.getArma().getAtaque() + "\n");
						
					}else {
						System.out.println("No tienes suficiente oro 🪙");
					}
					
				}else {
					System.out.println("No puedes mejorar el pico ⛏️\n");
				}
				
				
				break;
			case 2:
				System.out.println("Volviendo al menu de la tienda🛒");
				break;
			default:
				System.out.println("Opcion no valida\n");
				break;
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // Crear un personaje para probar
        Personaje jugador = new Personaje("Jugador de prueba");
        jugador.setOro(50); // darle algo de oro para probar mejoras

        // Crear la tienda
        Tienda tienda = new Tienda(sc);

        // Mostrar menú de tienda
        tienda.menuTienda(jugador);

        System.out.println("\n=== ESTADO FINAL DEL JUGADOR ===");
        System.out.println("Arma equipada: " + jugador.getArma().getNombre());
        System.out.println("Ataque: " + jugador.getArma().getAtaque());
        System.out.println("Oro restante: " + jugador.getOro());

        sc.close();
    }
	
	
}
