package defaultPackage;

public class Magia extends Personaje {

    public Magia(String nombre) {
        super(nombre, 100);
    }

    public void lanzarBolaDeFuego(Personaje objetivo, Integer potenciador) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40 * potenciador);
        }
    }

    public void congelarEnemigo(Personaje objetivo, Integer potenciador) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20 * potenciador);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(50);
        }
    }

}