package defaultPackage;

public class Utilidades {

    public static void imprimirConDelay(String texto, int delayMs) {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            sleep(delayMs);
        }
        System.out.println(); // salto de línea al final
    }
    
    public static void sleep(int delayMs) {
	    	try {
	    		Thread.sleep(delayMs);
	    	} catch (InterruptedException e) {
	        	Thread.currentThread().interrupt();
	            System.out.println("\nHilo interrumpido");
	        }
    }
}

