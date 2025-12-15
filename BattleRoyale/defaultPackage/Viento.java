package defaultPackage;

public class Viento extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=30;
	private static final int DAÑO_HABILIDAD2=19;
	private static final int CURACION_HABILIDAD = 28;

    public Viento(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.VIENTO);
    }
    
    public Viento(Personaje personaje) {
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

    public void LanzarTorbellino(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD1)) {
            System.out.println(this.nombre + " mete en un torbellino a " + objetivo.getNombre() + "!");            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));

            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void SoplidoDeDios(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD2)) {
            System.out.println(this.nombre + " manda volando a " + objetivo.getNombre() + "!");            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));

            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void CuracionDeEolo() {
        if (intentarGastarEnergia(COSTE_HABILIDAD3)) {

            System.out.println(this.nombre + " usa curacion de Eolo.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }
}