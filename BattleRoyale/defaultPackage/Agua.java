package defaultPackage;

public class Agua extends Personaje {

    public Agua(String nombre) {
        super(nombre, 100);
    }

    public void tsunami(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40);
        }
    }

    // Habilidad 2: Daño moderado + Efecto visual
    public void voragine(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " usa una vorgine para hundir a  " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20);
        }
    }

    public void curacionDePoseidon() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " se cura por los poderes de poseidon.");
            this.curarVida(50);
        }
    }

}