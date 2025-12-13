package defaultPackage;

public class Agua extends Personaje {

    private int nivel;

<<<<<<< HEAD
    public Agua(String nombre, int nivel, boolean esNPC) {
        super(nombre, esNPC);
=======
    public Agua(String nombre, int nivel) {
        super(nombre, false);
>>>>>>> 577781a85e2c6c2e163a4e7d38d919409124b560
        if (nivel < 1) {
            this.nivel = 1;
        } else if (nivel > 5) {
            this.nivel = 5;
        } else {
            this.nivel = nivel;
        }
    }

    private int calcularDanoNivel(int danoBase) {
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

    public void tsunami(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza un Tsunami a " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(40);
            objetivo.recibirDanio(dano);
        }
    }

    public void voragine(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " usa una vorgine para hundir a  " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(20);
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDePoseidon() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " se cura por los poderes de poseidon.");
            this.curarVida(50);
        }
    }
}