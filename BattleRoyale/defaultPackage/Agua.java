package defaultPackage;

public class Agua extends Personaje {

    private int nivel;

    public Agua(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.AGUA);
        if (nivel < 1) {
            this.nivel = 1;
        } else if (nivel > 5) {
            this.nivel = 5;
        } else {
            this.nivel = nivel;
        }
    }
    
    public Agua(Personaje personaje) {
    	super(personaje);
    }

    public void tsunami(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza un Tsunami a " + objetivo.getNombre() + "!");
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void voragine(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " usa una vorgine para hundir a  " + objetivo.getNombre() + "!");
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDePoseidon() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " se cura por los poderes de poseidon.");
            this.curarVida(30);
        }
    }
}