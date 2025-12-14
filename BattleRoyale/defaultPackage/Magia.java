package defaultPackage;

public class Magia extends Personaje {

    public Magia(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.MAGIA);
    }

    private int calcularDanoPorNivel(int danoBase) {
        int danoFinal = 0;

        switch (nivel) {
            case 1:

                danoFinal = danoBase;
                break;
            case 2:

                danoFinal = danoBase + 10;
                break;
            case 3:

                danoFinal = (int) (danoBase * 1.5);
                break;
            case 4:

                danoFinal = danoBase * 2;
                break;
            case 5:

                danoFinal = danoBase * 3;
                break;
            default:
                danoFinal = danoBase;
                break;
        }
        return danoFinal;
    }

    public void lanzarBolaDeFuego(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            int danoBase = 40;

            int danoReal = calcularDanoPorNivel(danoBase);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void congelarEnemigo(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");

            int danoBase = 20;
            int danoReal = calcularDanoPorNivel(danoBase);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void curacionDivina() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(50);
        }
    }

}