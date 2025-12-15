package defaultPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EventoEquipos extends ListaArmas {
	private static Scanner sc=new Scanner(System.in);
	
	private static Integer RONDA=0;
	private static Integer ORO_TOTAL_COFRE=100;
	private static Integer COFRES_TOTALES=0;
	private static Integer COFRES_RESTANTES=0;
	private static final Double PROBABILIDAD_MAX_COFRES=0.9;
	private static Integer EQUIPOS_TOTALES=0;
	private static final Double PROBABILIDAD_BATALLA=0.9;
	private static String LOG="";
	private static Integer I=0;
	private static List<String> OPCIONES=new ArrayList<String>();
	
	
	private List<Equipo> equipos;
	private Tienda tienda;
	private ImprimirLOG imprimirLOG;
	private BatallaEquipo batallaEquipo;
	
	public EventoEquipos(List<Equipo> equipos){
		super();
		this.equipos=equipos;
		Collections.shuffle(this.equipos);
		
		this.tienda=new Tienda();
		this.imprimirLOG=new ImprimirLOG();
		this.batallaEquipo=new BatallaEquipo();
		
		EventoEquipos.COFRES_TOTALES=this.equipos.size()*this.equipos.get(0).size()*4;
		EventoEquipos.COFRES_RESTANTES=COFRES_TOTALES;
		EventoEquipos.EQUIPOS_TOTALES=this.equipos.size();
		
		EventoEquipos.OPCIONES.add("buscar un Cofre");
		EventoEquipos.OPCIONES.add("buscar una Tienda");
		EventoEquipos.OPCIONES.add("buscar una Batalla");
		EventoEquipos.OPCIONES.add("Campear");
		
		
		while(this.equipos.size()!=1) {
			this.rondas();
		}
		
		System.out.println("\nEl equipo ganador es: "+this.equipos.getFirst().getNombre());
		
		try{
			this.imprimirLOG.imprimir("\nEl equipo ganador es: "+this.equipos.getFirst().getNombre()+"\n\n");
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
			
			if(equipos.size()==1) {
				continue;
			}
			
			if(!equipos.get(I).areVivos()) {
				equipos.remove((int)I--);
				continue;
			}
			
			if(!equipos.get(I).areNPCs()) {
				
				
				System.out.println("¿"+equipos.get(I).getNombre()+" que quereis hacer?");
	            System.out.println("\t[1] Buscar un Cofre");
	            System.out.println("\t[2] Buscar una Tienda");
		        System.out.println("\t[3] Buscar una Batalla");
		        System.out.println("\t[4] Campear");
	            
	            do {
	            	System.out.print("\nEligir una opcion: ");
	            	try{
	            		opcion=sc.nextInt();
	            	}catch(InputMismatchException e) {
	            		System.out.println("\tError, escribir un número válido.");
	            		opcion=0;
	            	}
	            	sc.nextLine();
				} while (opcion<1 || opcion>4);
	            
			}else {
				// El NPC nunca campea, ni busca una tienda
				if(RONDA>5) {
					opcion=(int)Math.round(Math.random()*2)%2;
				}else {
					opcion=0;
				}
				opcion++;
				if (opcion==2) {
					opcion=3;
				}
				System.out.println("El equipo de NPCs "+equipos.get(I).getNombre()+" intentan "+EventoEquipos.OPCIONES.get(opcion-1));
			}
			EventoEquipos.LOG+="\t-El equipo"+equipos.get(I).getNombre()+" han intentan "+EventoEquipos.OPCIONES.get(opcion-1)+"\n";
			this.opciones(opcion);
			Utilidades.sleep(1000);
			EventoEquipos.LOG+="\n\n";
			System.out.println("\n");
		}
		try{
			this.imprimirLOG.imprimir(LOG);
		}catch (ErrorEscrituraException e) {
			System.out.println(e.getMessage());
		}
		if(equipos.size()!=1) {
			System.out.print("La Ronda "+RONDA+" ha terminado. Pulsa ENTER para continuar.");
			sc.nextLine();
		}
		System.out.println("\n");
		
	}
	
	private void opciones(Integer opcion) {
		Double prob_cofre=((double)COFRES_RESTANTES)*PROBABILIDAD_MAX_COFRES/((double)COFRES_TOTALES);
		Double prob_batalla=((double)this.equipos.size())*PROBABILIDAD_BATALLA/((double)EQUIPOS_TOTALES);
		Double prob_tienda=0.6, prob_camp=0.9, random;
		
		if(RONDA<5) {
			prob_cofre=0.95;
			prob_batalla=0.4;
		}else if(RONDA==5){
			prob_batalla=0.4;
		}
		
		if(prob_batalla<0.5) {
			prob_batalla=0.5;
		}
		if(this.equipos.size()<7) {
			prob_tienda=0.2;
		}
		
		Double suerte=( ((double)equipos.get(I).getMediaSuerte())*5. /100.)+1.;
		
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
					if(!equipos.get(I).areNPCs()) {
						this.tienda();
					}else {
						System.out.println("Mala suerte no habeis encontrado nada.");
					}
				}else if(1-random<(1.-prob_cofre)*0.75) {
					// Battala
					this.batalla();
				}else {
					System.out.println("Mala suerte no habeis encontrado nada.");
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
					System.out.println("Mala suerte no habeis encontrado nada.");
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
					if(!equipos.get(I).areNPCs()) {
						this.tienda();
					}else {
						System.out.println("Mala suerte no habeis encontrado nada.");
					}
				}else {
					System.out.println("Mala suerte no habeis encontrado nada.");
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
		Integer opcion=0;
		
		if(!equipos.get(I).areNPCs()) {
			System.out.println("¿Quien quiere abrir el cofre?");
			for (int i = 0; i < equipos.get(I).size(); i++) {
				System.out.println("["+ (i+1) +"] "+equipos.get(I).get(i).getNombre());
			}
			do {
	        	System.out.print("Elegir a alguien: ");
	        	try{
	        		opcion=sc.nextInt();
	        	}catch(InputMismatchException e) {
	        		System.out.println("\tError, escribe un número válido.");
	        		opcion=0;
	        	}
	        	sc.nextLine();
			} while (opcion<1 || opcion>this.equipos.get(I).size());
			opcion--;
		}else {
			opcion=(int) Math.round( (Math.random()*equipos.get(I).size())%equipos.get(I).size());
		}
		
		EventoEquipos.LOG+="\t\t-"+this.equipos.get(I).get(opcion).getNombre()+" a abierto un cofre\n";
		
		Double suerte=( ((double)equipos.get(I).get(opcion).getSuerte()-1)*5. /100.)+1.;
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
		
		EventoEquipos.LOG+=this.equipos.get(I).get(opcion).equiparArma(arma);
		// A menor mejora más oro y a mayor menor oro
		this.equipos.get(I).get(opcion).setOro((int) (ORO_TOTAL_COFRE*(3-mejora)) );
	}
	
	private void tienda() {
		EventoEquipos.LOG+="\t\t-El equipo "+this.equipos.get(I).getNombre()+" a encontrado una tienda";
		
		for (int i = 0; i < equipos.get(I).size(); i++) {
			System.out.println(this.equipos.get(I).get(i).getNombre()+" vas a usar la tienda:");
			Utilidades.sleep(1000);
			EventoEquipos.LOG+="\t\t-"+this.equipos.get(I).get(i).getNombre()+" usa la tienda:";
			EventoEquipos.LOG+=tienda.menuTienda(equipos.get(I).get(i));
		}
	}
	
	private void batalla() {
		Integer random=(int)Math.round(Math.random()*(this.equipos.size()-1))%(this.equipos.size()-1);
		if(random.equals(I)) {
			random=this.equipos.size()-1;
		}
		
		EventoEquipos.LOG+=batallaEquipo.iniciarBatalla(this.equipos.get(I), this.equipos.get(random));
		
		try{
			if(!this.equipos.get(I).areVivos()) {
				this.equipos.remove((int)I);
			}else if (!this.equipos.get(random).areVivos()) {
				if(random<I) {
					I--;
				}
				this.equipos.remove((int)random);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// 1. Lista principal de equipos del torneo
	    List<Equipo> listaDeEquiposParticipantes = new ArrayList<>();

	    // -----------------------------------------------------------
	    // EQUIPO 1: TUS HÉROES (Humanos)
	    // -----------------------------------------------------------
	    // false indica que NO son NPCs
	    Equipo equipoHeroes = new Equipo("Los Vengadores", false);
	    
	    // IMPORTANTE: Instanciamos las clases hijas (Fuego, Agua...) NO Personaje a secas
	    // Ajusta los parámetros del constructor (Nombre, Nivel/Suerte, esNPC) según tus clases
	    equipoHeroes.add(new Fuego("Dr. Strange", 5, false));
	    equipoHeroes.add(new Tierra("Hulk", 5, false));
	    
	    listaDeEquiposParticipantes.add(equipoHeroes);


	    // -----------------------------------------------------------
	    // EQUIPO 2: RIVALES (NPCs)
	    // -----------------------------------------------------------
	    // true indica que SÍ son NPCs
	    Equipo equipoOrcos = new Equipo("La Horda", true);
	    
	    equipoOrcos.add(new Vida("Chamán Orco", 3, true));
	    equipoOrcos.add(new Viento("Jinete de Lobo", 3, true));
	    
	    listaDeEquiposParticipantes.add(equipoOrcos);


	    // -----------------------------------------------------------
	    // EQUIPO 3: RIVALES (NPCs)
	    // -----------------------------------------------------------
	    Equipo equipoMagos = new Equipo("Los Nigromantes", true);
	    
	    equipoMagos.add(new Magia("Hechicero Oscuro", 4, true));
	    equipoMagos.add(new Agua("Espíritu del Agua", 4, true));
	    
	    listaDeEquiposParticipantes.add(equipoMagos);


	    // -----------------------------------------------------------
	    // 3. INICIAR EL EVENTO
	    // -----------------------------------------------------------
	    System.out.println("========================================");
	    System.out.println("     INICIANDO TORNEO DE EQUIPOS");
	    System.out.println("========================================");
	    System.out.println("Equipos inscritos: " + listaDeEquiposParticipantes.size() + "\n");

	    try {
	        // Pasamos la lista de EQUIPOS, no de personajes
	        new EventoEquipos(listaDeEquiposParticipantes);
	        
	    } catch (Exception e) {
	        System.err.println("¡Error crítico en el evento!");
	        e.printStackTrace();
	    }
	}
}