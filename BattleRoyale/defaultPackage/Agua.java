package defaultPackage;

public class Agua extends Personaje {
	
	 private static final int DAÑO_HABILIDAD1=32;
	 private static final int DAÑO_HABILIDAD2=20;
	 private static final int CURACION_HABILIDAD = 26;
	 
    public Agua(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.AGUA);
    }
    
    public Agua(Personaje personaje) {
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

    public void tsunami(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD1)) {
            System.out.println(this.nombre + " lanza un Tsunami a " + objetivo.getNombre() + "!");
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            
            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void voragine(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD2)) {
            System.out.println(this.nombre + " usa una vorgine para hundir a  " + objetivo.getNombre() + "!");
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));

            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void curacionDePoseidon() {
        if (intentarGastarEnergia(COSTE_HABILIDAD3)) {
        	
            System.out.println(this.nombre + " se cura por los poderes de poseidon.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }
}