package defaultPackage;

public class Viento extends Personaje {

    private int nivel;

    public Viento(String nombre, int nivel, boolean esNPC) {
        super(nombre, esNPC);
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

    public void LanzarTorbellino(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " mete en un torbellino a " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(40);
            objetivo.recibirDanio(dano);
        }
    }

    public void SoplidoDeDios(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " manda volando a " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(20);
            objetivo.recibirDanio(dano);
        }
    }

    public void CuracionDeEolo() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " usa curacion de Eolo.");
            this.curarVida(50);
        }
    }
}