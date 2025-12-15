package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImprimirLOG {
	
	private String RUTA_FICHERO=GuardarLOG.getRutaFichero();
	private static final String DIRECTORIO_ACTUAL=System.getProperty("user.dir");
	
	private String texto;
	
	public ImprimirLOG() {
		try {
			this.leerFichero(RUTA_FICHERO);
			
		} catch (FileNotFoundException e) {
			System.out.println("\tError, fichero \""+RUTA_FICHERO+"\"no encontrado.");
			System.out.println("\t"+e.getMessage());
		} catch (IOException e) {
			System.out.println("\tError, con los permisos del fichero o algo relacionado con el fichero.");
			System.out.println("\t"+e.getMessage()+"\n");
		}
	}
	
	private void leerFichero(String ruta) throws IOException {
		File archivo=new File(DIRECTORIO_ACTUAL+ruta);
		String linea;
		
		BufferedReader bf=new BufferedReader(new FileReader(archivo));
		
		while((texto+=bf.readLine())!=null);
		
		bf.close();
	}
	
	public String getTexto() {
		return texto;
	}
}