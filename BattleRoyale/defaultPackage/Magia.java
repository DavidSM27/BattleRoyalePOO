package defaultPackage;

public class Magia extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=16;
	private static final int DAÑO_HABILIDAD2=14;
	private static final int CURACION_HABILIDAD = 40;
	
    public Magia(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.MAGIA);
    }
    
    public Magia(Personaje personaje) {
    	super(personaje);
    }

    public void lanzarBolaDeFuego(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void congelarEnemigo(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia()) {
            
            this.energia-=COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }

}