package defaultPackage;

public class Vida extends Personaje {

    public Vida(String nombre) {
        super(nombre, 100);
    }

    public void apretonDeCorazon(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " le para el corazon a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40);
        }
    }

    public void explotarSangre(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " saca toda la sangre del cuerpo de " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20);
        }
    }

    public void curacionMedica() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " es operado del corazon en un quirofano");
            this.curarVida(50);
        }
    }

}