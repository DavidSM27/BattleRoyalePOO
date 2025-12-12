package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaNombresNPC {
	
	public static final String RUTA_FICHERO="\\files\\Nombres_Jugadores.csv";
	
	private List<String> nombres;
	
	public ListaNombresNPC() {
		this.nombres=new ArrayList<String>();
		
		try {
			this.leerFichero();
		} catch (FileNotFoundException e) {
			System.out.println("\tError, fichero no encontrado, cambia la variable global \"RUTA_FICHERO\".\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("\tError, con los permisos del fichero o algo relacionado con el fichero.\n");
			e.printStackTrace();
		}
	}
	
	private void leerFichero() throws FileNotFoundException, IOException {
		File archivo=new File(System.getProperty("user.dir")+ListaNombresNPC.RUTA_FICHERO);
		String linea;
		
		BufferedReader bf=new BufferedReader(new FileReader(archivo));
		bf.readLine();
		
		while((linea=bf.readLine())!=null) {
			Scanner sc=new Scanner(linea);
			sc.useDelimiter(";");
			this.nombres.add(sc.next());
			sc.close();
		}
		
		bf.close();
	}

	public String getRandomNombres() {
		// En este caso hay 40 nombres, pero si no fueran 40 tambien funcionaria
		// El numero seria del 0 al 40
		// porque si es 0,5 lo redondea a 1 y no tendria la misma probabilidad
		// y luego hago modulo 40 para que del 39.5 a 40 se redondea a 40 y el modulo de 40 es 0
		// igualando las probabilidades
		return this.nombres.get((int) (Math.round(Math.random()*this.nombres.size()) %this.nombres.size()) );
	}
	
	@Override
	public String toString() {
		return "ListaNombresNPC [nombres=" + nombres + "]";
	}

	public static void main(String[] args) {
		ListaNombresNPC armas=new ListaNombresNPC();
		
		System.out.println(armas);
	}
}
