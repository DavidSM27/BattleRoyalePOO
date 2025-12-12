package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaArmas {
	
	public static final String RUTA_FICHERO="\\files\\Armas.csv";
	
	private List<Arma> armas;
	
	public ListaArmas() {
		this.armas=new ArrayList<Arma>();
		
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
		File archivo=new File(System.getProperty("user.dir")+ListaArmas.RUTA_FICHERO);
		String linea;
		
		BufferedReader bf=new BufferedReader(new FileReader(archivo));
		bf.readLine();
		
		while((linea=bf.readLine())!=null) {
			Scanner sc=new Scanner(linea);
			sc.useDelimiter(";");
			
			this.armas.add(new Arma(sc.next(), Double.valueOf(sc.next()), Math.random()+1));
		}
		
		bf.close();
	}

	public Arma getRandomArma() {
		// En este caso hay 5 nombres, pero si no fueran 40 tambien funcionaria
		// El numero seria del 0 al 5
		// porque si es 0,5 lo redondea a 1 y no tendria la misma probabilidad
		// y luego hago modulo 5 para que del 5.5 a 5 se redondea a 5 y el modulo de 5 es 0
		// igualando las probabilidades
		return this.armas.get((int) (Math.round(Math.random()*this.armas.size()) %this.armas.size()) );
	}
}
