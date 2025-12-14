package defaultPackage;

public class Vida extends Personaje {

    private int nivel;

    public Vida(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.VIDA);
        if (nivel < 1) {
            this.nivel = 1;
        } else if (nivel > 5) {
            this.nivel = 5;
        } else {
            this.nivel = nivel;
        }
    }
    
    public Vida(Personaje personaje) {
    	super(personaje);
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

    public void apretonDeCorazon(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " le para el corazon a " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(40);
            objetivo.recibirDanio(dano);
        }
    }

    public void explotarSangre(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " saca toda la sangre del cuerpo de " + objetivo.getNombre() + "!");
            int dano = calcularDanoNivel(20);
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionMedica() {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " es operado del corazon en un quirofano");
            this.curarVida(50);
        }
    }
}