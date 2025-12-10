package defaultPackage;

public class Tierra extends Personaje {

    public Tierra(String nombre) {
        super(nombre, 100);
    }

    public void crearGrieta(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " tira por una grieta a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40);
        }
    }

    public void lanzarRoca(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " aplasta con una roca a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20);
        }
    }

    public void sanacionRocal() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " se cura por el poder de las rocas.");
            this.curarVida(50);
        }
    }

}