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
	
	private static final Scanner sc=new Scanner(System.in);
	private static final String RUTA_FICHERO="\\files\\Nombres_Jugadores.csv";
	
	private List<String> nombres;
	
	public ListaNombresNPC() {
		this.nombres=new ArrayList<String>();
		
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
		
		Integer random=(int) (Math.round(Math.random()*this.nombres.size()) %this.nombres.size());
		String nombre=this.nombres.get(random);
		this.nombres.remove((int)random);
		return nombre;
	}
	
	@Override
	public String toString() {
		return "ListaNombresNPC [nombres=" + nombres + "]";
	}

	public static void main(String[] args) {
		ListaNombresNPC nombres=new ListaNombresNPC();
		
		System.out.println(nombres);
	}
}
