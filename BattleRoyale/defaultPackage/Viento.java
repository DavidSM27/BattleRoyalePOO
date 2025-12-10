package defaultPackage;

public class Viento extends Personaje {

    public Viento(String nombre) {
        super(nombre, 100);
    }

    public void LanzarTorbellino(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " mete en un torbellino a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(40);
        }
    }

    public void SoplidoDeDios(Personaje objetivo) {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " manda volando a " + objetivo.getNombre() + "!");
            objetivo.recibirDanio(20);
        }
    }

    public void CuracionDeEolo() {
        if (intentarGastarEnergia("Maná")) {
            System.out.println(this.nombre + " usa curacion de Eolo.");
            this.curarVida(50);
        }
    }

}