package defaultPackage;

public class Viento extends Personaje {

    private int nivel;

    public Viento(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.VIENTO);

        if (nivel < 1) {
            this.nivel = 1;
        } else if (nivel > 5) {
            this.nivel = 5;
        } else {
            this.nivel = nivel;
        }
    }
    
    public Viento(Personaje personaje) {
    	super(personaje);
    }

    public void LanzarTorbellino(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " mete en un torbellino a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void SoplidoDeDios(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " manda volando a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void CuracionDeEolo() {
        if (intentarGastarEnergia()) {
            
            this.energia-=Personaje.COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa curacion de Eolo.");
            this.curarVida(30);
        }
    }
}