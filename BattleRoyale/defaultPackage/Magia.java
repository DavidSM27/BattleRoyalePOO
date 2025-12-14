package defaultPackage;

public class Magia extends Personaje {

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
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(Personaje.DAÑO_HABILIDAD1))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void congelarEnemigo(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(Personaje.DAÑO_HABILIDAD2))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia()) {
            
            this.energia-=Personaje.COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(30);
        }
    }

}