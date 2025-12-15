package defaultPackage;

public class Utilidades {

    public static void imprimirConDelay(String texto, int delayMs) {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nHilo interrumpido");
            }
        }
        System.out.println(); // salto de línea al final
    }
}

