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

    private int calcularImpactoTerrestre(int danoBase) {
        int danoFinal = 0;

        switch (this.nivel) {
            case 1:
                danoFinal = danoBase;
                break;
            case 2:
                danoFinal = danoBase + 5;
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

    public void crearGrieta(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " tira por una grieta a " + objetivo.getNombre() + "!");

            int danoReal = calcularImpactoTerrestre(40);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void lanzarRoca(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " aplasta con una roca a " + objetivo.getNombre() + "!");

            int danoReal = calcularImpactoTerrestre(20);

            objetivo.recibirDanio(danoReal);
        }
    }

    public void sanacionRocal() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " se cura por el poder de las rocas.");
            this.curarVida(50);
        }
    }

}