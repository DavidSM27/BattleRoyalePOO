package defaultPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GuardarLOG {
	
	private static final Scanner sc=new Scanner(System.in);
	private static final String RUTA_FICHERO="\\files\\LOG.txt";
	private static final String DIRECTORIO_ACTUAL=System.getProperty("user.dir");
	
	private File fichero;
	
	public GuardarLOG() {
		try {
			this.fichero=new File(DIRECTORIO_ACTUAL+RUTA_FICHERO);
			boolean existe=this.fichero.exists();
			if(existe) {
				System.out.print("¿Quieres sobreescribir el fichero \"" + this.fichero.getName() + "? (Si/No) ");
				
				if(sc.next().toUpperCase().substring(0, 1).equals("S")) {
					new FileWriter(this.fichero, !existe).close();
				}
				sc.nextLine();
			}else {
				new FileWriter(this.fichero, existe).close();
			}
			
		}catch (IOException e) {
			
		}
	}
	
	public static String getRutaFichero() {
		return RUTA_FICHERO;
	}

	public void imprimir(String log) throws ErrorEscrituraException {
		try {
			FileWriter fileWriter=new FileWriter(this.fichero, true);
			PrintWriter impresora=new PrintWriter(fileWriter);
			
			impresora.append(log);
			
			impresora.close();
		}catch (IOException e) {
			throw new ErrorEscrituraException("Fallo al intentar guardar la partina en disco");
		}
	}
}
