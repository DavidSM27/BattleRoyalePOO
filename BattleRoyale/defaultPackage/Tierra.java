package defaultPackage;

public class Tierra extends Personaje {

	private static final int DAÑO_HABILIDAD1=18;
	private static final int DAÑO_HABILIDAD2=12;
	private static final int CURACION_HABILIDAD = 35;

    public Tierra(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.TIERRA);
        if (nivel < 1)
            this.nivel = 1;
        else if (nivel > 5)
            this.nivel = 5;
        else
            this.nivel = nivel;
    }
    
    public static int getDañoHabilidad1() {
		return DAÑO_HABILIDAD1;
	}

	public static int getDañoHabilidad2() {
		return DAÑO_HABILIDAD2;
	}

	public static int getCuracionHabilidad() {
		return CURACION_HABILIDAD;
	}

    public Tierra(Personaje personaje) {
        super(personaje);
    }

    public void terremoto(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " tira por una grieta a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void lanzarRoca(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " aplasta con una roca a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void sanacionRocal() {
        if (intentarGastarEnergia()) {
            
            this.energia-=COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " se cura por el poder de las rocas.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }

}