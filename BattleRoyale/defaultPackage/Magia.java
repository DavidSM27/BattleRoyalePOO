package defaultPackage;

public class Magia extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=29;
	private static final int DAÑO_HABILIDAD2=17;
	private static final int CURACION_HABILIDAD = 35;
	
    public Magia(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.MAGIA);
    }
    
    public Magia(Personaje personaje) {
    	super(personaje);
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

    public void lanzarBolaDeFuego(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD1)) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void congelarEnemigo(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD2)) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            

            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia(COSTE_HABILIDAD3)) {
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }

}