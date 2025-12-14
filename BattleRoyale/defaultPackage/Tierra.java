package defaultPackage;

public class Tierra extends Personaje {

    private int nivel;

    public Tierra(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.TIERRA);
        if (nivel < 1)
            this.nivel = 1;
        else if (nivel > 5)
            this.nivel = 5;
        else
            this.nivel = nivel;
    }

    public Tierra(Personaje personaje) {
        super(personaje);
    }

    public void terremoto(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " tira por una grieta a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void lanzarRoca(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " aplasta con una roca a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            

            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void sanacionRocal() {
        if (intentarGastarEnergia()) {
            
            this.energia-=Personaje.COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " se cura por el poder de las rocas.");
            this.curarVida(30);
        }
    }

}