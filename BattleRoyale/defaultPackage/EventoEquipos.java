package defaultPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EventoEquipos extends ListaArmas {
	private static Scanner sc=new Scanner(System.in);
	
	private static Integer PARTIDA=1;
	private static Integer RONDA=0;
	private static Integer ORO_TOTAL_COFRE=100;
	private static Integer COFRES_TOTALES=0;
	private static Integer COFRES_RESTANTES=0;
	private static final Double PROBABILIDAD_MAX_COFRES=0.9;
	private static Integer JUGADORES_TOTALES=0;
	private static final Double PROBABILIDAD_BATALLA=0.9;
	private static String LOG="";
	private static Integer I=0;
	private static List<String> OPCIONES=new ArrayList<String>();
	
	
	private List<List<Personaje>> equipos;
	private Tienda tienda;
	private ImprimirLOG imprimirLOG;
	
	public EventoEquipos(List<List<Personaje>> equipos){
		super();
		this.equipos=equipos;
		Collections.shuffle(this.equipos);
		
		this.tienda=new Tienda();
		this.imprimirLOG=new ImprimirLOG();
		
		EventoEquipos.COFRES_TOTALES=this.equipos.size()*4;
		EventoEquipos.COFRES_RESTANTES=COFRES_TOTALES;
		EventoEquipos.JUGADORES_TOTALES=this.equipos.size();
		
		EventoEquipos.OPCIONES.add("buscar un Cofre");
		EventoEquipos.OPCIONES.add("buscar una Tienda");
		EventoEquipos.OPCIONES.add("buscar una Batalla");
		EventoEquipos.OPCIONES.add("Campear");
		
		try{
			this.imprimirLOG.imprimir("Partida "+PARTIDA++ +"\n");
		}catch (ErrorEscrituraException e) {
			System.out.println(e.getMessage());
		}
		
		while(this.equipos.size()!=1) {
			this.rondas();
		}
		
		System.out.println("\nEl ganador es: "+this.equipos.getFirst().getNombres());
		
		try{
			this.imprimirLOG.imprimir("\nEl ganador es: "+this.equipos.getFirst().getNombres()+"\n\n");
		}catch (ErrorEscrituraException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void rondas() {
		Integer opcion;
		RONDA++;
		EventoEquipos.LOG="Ronda "+RONDA+"\n";
		System.out.println("Ronda "+RONDA+"\n");
		
		for (I=0; I < equipos.size(); I++) {
			opcion=0;
			
			if(!equipos.get(I).areVivos()) {
				equipos.remove((int)I--);
				continue;
			}
			
			if(!equipos.get(I).areNPCs()) {
				
				
				System.out.println("¿"+equipos.get(I).getNombres()+" que quereis hacer?");
	            System.out.println("\t[1] Buscar un Cofre");
	            System.out.println("\t[2] Buscar una Tienda");
	            System.out.println("\t[3] Buscar una Batalla");
	            System.out.println("\t[4] Campear\n");
	            
	            do {
	            	System.out.print("Elige una opcion: ");
	            	try{
	            		opcion=sc.nextInt();
	            	}catch(InputMismatchException e) {
	            		System.out.println("\tError, escribe un número válido.");
	            		opcion=0;
	            	}
	            	sc.nextLine();
				} while (opcion<1 || opcion>4);
	            
			}else {
				// El NPC nunca campea, ni busca una tienda
				if(RONDA>2) {
					opcion=(int)Math.round(Math.random()*2)%2;
				}else if(RONDA<=2) {
					opcion=0;
				}
				opcion++;
				if (opcion==2) {
					opcion=3;
				}
				System.out.println("El NPC "+equipos.get(I).getNombres()+" intenta "+EventoEquipos.OPCIONES.get(opcion-1));
			}
			EventoEquipos.LOG+="\t-"+equipos.get(I).getNombres()+" intenta "+EventoEquipos.OPCIONES.get(opcion-1)+"\n";
			this.opciones(opcion);
			EventoEquipos.LOG+="\n\n";
			System.out.println("\n");
		}
		try{
			this.imprimirLOG.imprimir(LOG);
		}catch (ErrorEscrituraException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("La Ronda "+RONDA+" ha terminado. Pulsa ENTER para continuar.");
		sc.nextLine();
		
		for (int i = 0; i < this.equipos.size(); i++) {
			if(!equipos.get(i).areVivos()) {
				equipos.remove((int)i);
			}
		}
	}
	
	private void opciones(Integer opcion) {
		Double prob_cofre=((double)COFRES_RESTANTES)*PROBABILIDAD_MAX_COFRES/((double)COFRES_TOTALES);
		Double prob_batalla=((double)this.jugadores.size())*PROBABILIDAD_BATALLA/((double)JUGADORES_TOTALES);
		Double prob_tienda=0.6, prob_camp=0.9, random;
		
		if(RONDA==1) {
			prob_cofre=0.95;
			prob_batalla=0.4;
		}else if(RONDA==2){
			prob_batalla=0.4;
		}
		
		if(prob_batalla<0.5) {
			prob_batalla=0.5;
		}
		if(this.jugadores.size()<7) {
			prob_tienda=0.2;
		}
		
		Double suerte=( ((double)jugadores.get(I).getSuerte()-1)*5. /100.);
		
		prob_cofre+=prob_cofre*suerte;
		prob_batalla+=prob_batalla*suerte;
		prob_tienda+=prob_tienda*suerte;
		prob_camp+=prob_camp*suerte;
		
		random=Math.random();
		switch (opcion) {
			case 1:
				if(random<prob_cofre) {
					// Cofre
					this.cofre();
				}else if(1-random<(1.-prob_cofre)*0.5) {
					// Tienda
					if(!jugadores.get(I).isNPC()) {
						this.tienda();
					}else {
						System.out.println("Mala suerte no encontrado nada.");
					}
				}else if(1-random<(1.-prob_cofre)*0.75) {
					// Battala
					if(RONDA>2) {
						this.batalla();
					}else {
						System.out.println("Mala suerte no encontrado nada.");
					}
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
					if(RONDA>2) {
						this.batalla();
					}else {
						System.out.println("Mala suerte no encontrado nada.");
					}
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
					if(!jugadores.get(I).isNPC()) {
						this.tienda();
					}else {
						System.out.println("Mala suerte no encontrado nada.");
					}
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
		EventoEquipos.LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a abierto un cofre\n";
		
		Double suerte=( ((double)jugadores.get(I).getSuerte()-1)*5. /100.);
		Double random=Math.random();
		Integer aux=0;
		if(random<0.05*suerte) {
			aux=0;
		}else if(random<0.2*suerte) {
			aux=1;
		}else if(random<0.4*suerte) {
			aux=2;
		}else if(random<0.65*suerte) {
			aux=3;
		}else {
			aux=4;
		}
		
		Double mejora=((double)Math.round(Math.random()*10))/10+1;
		
		Arma arma=new Arma(super.armas.get(aux).getNombre(),
						   super.armas.get(aux).getAtaqueSinMejora(),
						   mejora);
		
		EventoEquipos.LOG+=this.jugadores.get(I).equiparArma(arma);
		// A menor mejora más oro y a mayor menor oro
		this.jugadores.get(I).setOro((int) (ORO_TOTAL_COFRE*(3-mejora)) );
	}
	
	private void tienda() {
		EventoEquipos.LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a encontrado una tienda";
		
		tienda.menuTienda(this.jugadores.get(I));
	}
	
	private void batalla() {
		Integer random=(int)Math.round(Math.random()*(this.jugadores.size()-1))%(this.jugadores.size()-1);
		if(random.equals(I)) {
			random=this.jugadores.size()-1;
		}
		
		//Batalla jugador=new Batalla(this.jugadores.get(I));
		//Evento.LOG+=jugador.batalla(this.jugadores.get(random));
		
		this.jugadores.get(random).recibirDanio(1000);
		
		if(this.jugadores.get(I).estaVivo) {
			EventoEquipos.LOG+="\t\t-"+this.jugadores.get(I).getNombre()+" a matado a "+this.jugadores.get(random).getNombre();
			
		}else{
			EventoEquipos.LOG+="\t\t-"+this.jugadores.get(random).getNombre()+" a matado a "+this.jugadores.get(I).getNombre();
		}
		
	}
	
	public static void main(String[] args) {
		// 1. Crear la lista
        List<Personaje> listaJugadores = new ArrayList<>();

        // 2. Generar 50 personas
        for (int i = 1; i <= 2; i++) {
            listaJugadores.add(new Personaje(("Jugador "+i), 1, true));
        }

        System.out.println("Iniciando evento con " + listaJugadores.size() + " jugadores.");

        // 3. Insanciar el Evento
        new EventoEquipos(listaJugadores);
	}
}