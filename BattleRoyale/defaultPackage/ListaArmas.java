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
	
	private static final Scanner sc=new Scanner(System.in);
	private static final String RUTA_FICHERO="\\files\\Armas.csv";
	
	protected List<Arma> armas;
	
	public ListaArmas() {
		this.armas=new ArrayList<Arma>();
		
		boolean errorRuta=false;
		String ruta=RUTA_FICHERO;
		do {
			try {
				this.leerFichero(ruta);
				errorRuta=false;
				
			} catch (FileNotFoundException e) {
				System.out.println("\tError, fichero \""+ruta+"\"no encontrado.");
				System.out.println("\t"+e.getMessage());
				
				// La condición es que errorRuta==false para que se haga una vez solo
				if(!errorRuta) {
					errorRuta=true;
					System.out.print("Completa la ruta del fichero:\n"+System.getProperty("user.dir"));
					ruta=sc.next();
					sc.nextLine();
					System.out.println("Se va usar la ruta auxiliar: "+ruta+"\n\n");
				}else {
					errorRuta=false;
				}
				
			} catch (IOException e) {
				System.out.println("\tError, con los permisos del fichero o algo relacionado con el fichero.");
				System.out.println("\t"+e.getMessage()+"\n");
			} catch (Exception e) {
				System.out.println("\tHay un Error.");
				System.out.println("\t"+e.getMessage()+"\n");
			}
		}while(errorRuta);
	}
	
	private void leerFichero(String ruta) throws FileNotFoundException, IOException, Exception {
		File archivo=new File(System.getProperty("user.dir")+ruta);
		String linea, nombre;
		Double daño;
		
		BufferedReader bf=new BufferedReader(new FileReader(archivo));
		bf.readLine();
		
		while((linea=bf.readLine())!=null) {
			Scanner sc=new Scanner(linea);
			sc.useDelimiter(";");
			
			nombre=sc.next();
			daño=Double.valueOf(sc.next());
			sc.close();
			
			this.armas.add(new Arma(nombre, daño));
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
	
	@Override
	public String toString() {
		for (int i = 0; i < this.armas.size(); i++) {
			System.out.println(this.armas.get(i));
		}
		return "";
	}

	public static void main(String[] args) {
		ListaArmas armas=new ListaArmas();
		
		System.out.println(armas);
	}
}
