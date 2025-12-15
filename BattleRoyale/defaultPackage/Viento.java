package defaultPackage;

public class Viento extends Personaje {
	
	private static final int DAÑO_HABILIDAD1=16;
	private static final int DAÑO_HABILIDAD2=14;
	private static final int CURACION_HABILIDAD = 40;

    public Viento(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.VIENTO);
    }
    
    public Viento(Personaje personaje) {
    	super(personaje);
    }

    public void LanzarTorbellino(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " mete en un torbellino a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void SoplidoDeDios(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " manda volando a " + objetivo.getNombre() + "!");
            
            this.energia-=COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void CuracionDeEolo() {
        if (intentarGastarEnergia()) {
            
            this.energia-=COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa curacion de Eolo.");
            this.curarVida(CURACION_HABILIDAD);
        }
    }
}