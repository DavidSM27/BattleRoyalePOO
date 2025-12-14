package defaultPackage;

public class Fuego extends Personaje {

    private int nivel;

    public Fuego(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.FUEGO);

        if (nivel < 1)
            this.nivel = 1;
        else if (nivel > 5)
            this.nivel = 5;
        else
            this.nivel = nivel;
    }

    private int calcularDanoPorNivel(int danoBase) {
        int danoFinal = 0;

        switch (this.nivel) {
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

    public void lluviaInfernal(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");

            // Calculamos el daño base original
            int danoBase = 40;
            // Aplicamos el switch según el nivel
            int danoReal = calcularDanoPorNivel(danoBase);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void marDeLava(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");

            // Calculamos el daño base original
            int danoBase = 20;
            // Aplicamos el switch según el nivel
            int danoReal = calcularDanoPorNivel(danoBase);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void curacionDelInfierno() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(50);
        }
    }

}
