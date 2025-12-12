package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NombresNPC {
	
	public final String RUTA_FICHERO="\\files\\Nombres_Jugadores.csv";
	
	private List<String> nombres;
	
	public NombresNPC() {
		this.nombres=new ArrayList<String>();
		
		try {
			this.leerFichero();
		} catch (FileNotFoundException e) {
			System.out.println("\tError, Fichero no encontrado.\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("\tError, con los permisos del fichero o algo relacionado con el fichero.\n");
			e.printStackTrace();
		}
	}
	
	private void leerFichero() throws FileNotFoundException, IOException {
		File archivo=new File(System.getProperty("user.dir")+this.RUTA_FICHERO);
		String linea;
		
		BufferedReader bf=new BufferedReader(new FileReader(archivo));
		bf.readLine();
		
		while((linea=bf.readLine())!=null) {
			this.nombres.add(linea);
		}
		
		bf.close();
	}
}
