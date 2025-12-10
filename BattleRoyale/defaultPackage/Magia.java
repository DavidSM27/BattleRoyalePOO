package defaultPackage;

public class Magia extends Personaje {

    public Magia(String nombre) {
        super(nombre, 100);
    }

    public void lanzarBolaDeFuego(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40);
        }
    }

    public void congelarEnemigo(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(50);
        }
    }

}