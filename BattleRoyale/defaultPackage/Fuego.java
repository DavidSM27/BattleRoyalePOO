package defaultPackage;

public class Fuego extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=38;
	private static final int DAÑO_HABILIDAD2=16;
	private static final int CURACION_HABILIDAD = 23;

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
        if (intentarGastarEnergia(COSTE_HABILIDAD1)) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");            

            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void marDeLava(Personaje objetivo) {
        if (intentarGastarEnergia(COSTE_HABILIDAD2)) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            
            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));

            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDelInfierno() {
    	if (intentarGastarEnergia(COSTE_HABILIDAD3)) {
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }

}
