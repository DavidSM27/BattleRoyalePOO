package defaultPackage;

public class Vida extends Personaje {

	private static final int DAÑO_HABILIDAD1=20;
	private static final int DAÑO_HABILIDAD2=13;
	private static final int CURACION_HABILIDAD = 50;

    public Vida(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.VIDA);
    }
    
    public Vida(Personaje personaje) {
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

    public void apretonDeCorazon(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD1)) {
            System.out.println(this.nombre + " le para el corazon a " + objetivo.getNombre() + "!");            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));

            dano*=(1+(this.fuerza-objetivo.defensa-2)*5/200);

            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void explotarSangre(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD2)) {
            System.out.println(this.nombre + " saca toda la sangre del cuerpo de " + objetivo.getNombre() + "!");            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));

            dano*=(1+(this.fuerza-objetivo.defensa-2)*5/200);

            int comparacion=this.compareTo(objetivo);
            objetivo.recibirDanio(dano, comparacion);
        }
    }

    public void curacionMedica() {
        if (intentarGastarEnergia(COSTE_HABILIDAD3)) {
        	
            System.out.println(this.nombre + " es operado del corazon en un quirofano");
            this.curarVida(CURACION_HABILIDAD);
        }
    }
}