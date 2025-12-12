package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Evento extends ListaArmas {
	private static Integer RONDA=0;
	private static Integer ORO_TOTAL_COFRE=100;
	private static Integer COFRES_TOTALES=0;
	private static Integer COFRES_RESTANTES=0;
	private static final Double PROBABILIDAD_MAX_COFRES=0.9;
	private static Integer JUGADORES_TOTALES=0;
	private static Integer JUGADORES_VIVOS=0;
	private static final Double PROBABILIDAD_BATALLA=0.9;
	private static String LOG="";
	private static Integer I=0;
	private static List<String> OPCIONES=new ArrayList<String>();
	
	
	private List<Personaje> jugadores;
	
	public Evento(List<Personaje> jugadores){
		super();
		this.jugadores=jugadores;
		Evento.COFRES_TOTALES=this.jugadores.size()*4;
		Evento.JUGADORES_TOTALES=this.jugadores.size();
		Evento.JUGADORES_VIVOS=this.jugadores.size();
		
		OPCIONES.add("buscar un Cofre");
		OPCIONES.add("buscar una Tienda");
		OPCIONES.add("buscar una Batalla");
		OPCIONES.add("Campear");
	}
	
	public void rondas() {
		if(JUGADORES_VIVOS>1) {
			RONDA++;
			LOG="Ronda "+RONDA+"\n";
			System.out.println("Ronda "+RONDA+"\n");
			
			for (I=0; I < jugadores.size(); I++) {
				Integer opcion=0;
				
				if(!jugadores.get(I).isVivo()) {
					jugadores.remove(I--);
					JUGADORES_VIVOS--;
				}
				
				if(/*jugadores.get(I).isNPC()*/Math.random()<0.5) {
					Scanner sc=new Scanner(System.in);
					
					System.out.println("¿"+jugadores.get(I).getNombre()+" que quieres hacer?");
		            System.out.println("\t[0] Buscar un Cofre");
		            System.out.println("\t[1] Buscar una Tienda");
		            System.out.println("\t[2] Buscar una Batalla");
		            System.out.println("\t[3] Campear\n");
		            
		            do {
		            	System.out.print("Elige una opcion:");
						opcion=sc.nextInt();
					} while (opcion<0 && opcion>3);
		            sc.close();
		            
				}else {
					// El NPC nunca campea
					if(RONDA>2) {
						opcion=(int)Math.round(Math.random()*3)%3;
					}else if(RONDA.equals(1)) {
						opcion=0;
					}else {
						opcion=(int)Math.round((Math.random()*2)%2);
					}
				}
				LOG+="\t-"+jugadores.get(I).getNombre()+" intenta "+OPCIONES.get(opcion)+"\n";
				this.opciones(opcion);
				LOG+="\n\n";
				System.out.println("\n\n");
			}
			System.out.println(LOG);
		}else {
			System.out.println("Ha ganado "+jugadores.getFirst().getNombre());
		}
	}
	
	private void opciones(Integer opcion) {
		Double prob_cofre=((double)COFRES_RESTANTES)*PROBABILIDAD_MAX_COFRES/((double)COFRES_TOTALES);
		Double prob_batalla=((double)JUGADORES_VIVOS)*PROBABILIDAD_BATALLA/((double)JUGADORES_TOTALES);
		Double prob_tienda=0.6, prob_camp=0.9, random;
		
		if(RONDA==1) {
			prob_cofre=0.95;
			prob_batalla=0.4;
		}else if(RONDA==2){
			prob_batalla=0.4;
		}
		
		//prob_cofre+=prob_cofre*( ((Double)jugadores.get(I).getSuerte()) /100.);
		//prob_batalla+=prob_batalla*( ((Double)jugadores.get(I).getSuerte()) /100.);
		//prob_tienda+=prob_tienda*( ((Double)jugadores.get(I).getSuerte()) /100.);
		//prob_camp+=prob_camp*( ((Double)jugadores.get(I).getSuerte()) /100.);
		
		random=Math.random();
		switch (opcion) {
			case 1:
				if(random<prob_cofre) {
					// Cofre
					this.cofre();
				}else if(1-random<(1.-prob_cofre)*0.5) {
					// Tienda
					this.tienda();
				}else if(1-random<(1.-prob_cofre)*0.75) {
					// Battala
					this.batalla();
				}else {
					System.out.println("Mala suerte no encontrado nada.");
				}
				break;
				
			case 2:
				if(random<prob_tienda) {
					// Tienda
					this.tienda();
				}else if(1-random<(1.-prob_tienda)*0.5*prob_cofre) {
					// Cofre
					this.cofre();
				}else if(1-random<(1.-prob_tienda)*0.75) {
					// Battala
					this.batalla();
				}else {
					System.out.println("Mala suerte no encontrado nada.");
				}
				break;
				
			case 3:
				if(random<prob_batalla) {
					// Battala
					this.batalla();
				}else if(1-random<(1.-prob_batalla)*0.25*prob_cofre) {
					// Cofre
					this.cofre();
				}else if(1-random<(1.-prob_tienda)*0.5) {
					// Tienda
					this.tienda();
				}else {
					System.out.println("Mala suerte no encontrado nada.");
				}
				break;
				
			case 4:
				if(random<prob_camp) {
					System.out.println("Nadie te ha encontrado.");
				}else {
					// Battala
					this.batalla();
				}
				break;
		}
	}
	
	private void cofre() {
		LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a abierto un cofre\n";
		
		Integer random=(int)Math.round(Math.random()*super.armas.size())%super.armas.size();
		Double mejora=((double)Math.round(Math.random()*10))/10+1;
		
		Arma arma=new Arma(super.armas.get(random).getNombre(),
						   super.armas.get(random).getAtaqueSinMejora(),
						   mejora);
		
		this.jugadores.get(I).equiparArma(arma);
		// A menor mejora más oro y a mayor menor oro
		this.jugadores.get(I).setOro((int) (ORO_TOTAL_COFRE*(3-mejora)) );
	}
	
	private void tienda() {
		LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a encontrado una tienda\n";
		
	}
	
	private void batalla() {
		Integer random=(int)Math.round(Math.random()*(this.jugadores.size()-1))%(this.jugadores.size()-1);
		if(random.equals(I)) {
			random=this.jugadores.size()-1;
		}
		//LOG+="\t-"+this.jugadores.get(I).batalla(this.jugadores.get(random));
		LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a matado a "+this.jugadores.get(random).getNombre();
		this.jugadores.get(random).recibirDanio(1000);
	}
	
	public static void main(String[] args) {
		// 1. Crear la lista
        List<Personaje> listaJugadores = new ArrayList<>();

        // 2. Generar 50 personas
        for (int i = 1; i <= 50; i++) {
            listaJugadores.add(new Personaje());
        }

        System.out.println("Iniciando evento con " + listaJugadores.size() + " jugadores.");

        // 3. Instanciar el Evento
        Evento evento = new Evento(listaJugadores);

        // 4. Bucle del juego (Mientras quede más de 1 vivo)
        while (Evento.JUGADORES_VIVOS > 1) {
            evento.rondas();
        }

        // 5. Anunciar ganador
        for(Personaje p : listaJugadores) {
            if(p.isVivo()) {
                System.out.println("\n¡¡EL GANADOR ES " + p.getNombre() + "!! 🎉");
                break;
            }
        }
	}
}