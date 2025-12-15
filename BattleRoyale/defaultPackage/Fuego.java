package defaultPackage;

public class Fuego extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=24;
	private static final int DAÑO_HABILIDAD2=6;
	private static final int CURACION_HABILIDAD = 20;

    public Fuego(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.FUEGO);
    }
    
    public Fuego(Personaje personaje) {
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

	public void lluviaInfernal(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            

            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void marDeLava(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            
            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));

            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDelInfierno() {
        if (intentarGastarEnergia()) {
            
            this.energia-=COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }

}
