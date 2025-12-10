package defaultPackage;

public abstract class Personaje {

    private static final int VIDA_DEFAULT = 100;
    
    private String nombre;
    private int vida;
    private int vidaMax;
    private Estadisticas estadisticas;
    private Arma arma;
    private boolean enCombate;
    private boolean estaVivo;

    public Personaje(){
        this.nombre = "jugador";
        this.vida = VIDA_DEFAULT;
        this.vidaMax = VIDA_DEFAULT;
        this.estadisticas = null;
        this.arma = arma.ARMA_DEFAULT;
        this.enCombate = false;
        this.estaVivo = true;
    }

    
    public Personaje(String nombre, int vidaMax, Estadisticas estadisticas, Arma arma) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.estadisticas = estadisticas;
        this.arma = arma;
        this.enCombate = false;
        this.estaVivo = true;
    }

    
    public abstract String getElemento();

    public abstract void habilidadElemental();

    public abstract void recibirAtaque(int cantidad); 

    
    public void atacar() {
        if (estaVivo && arma != null) {
            System.out.println(this.nombre + " ataca con " + arma.getNombre() + "!");
        } else {
            System.out.println(this.nombre + " no puede atacar.");
        }
    }

    public void cambiarVida(int cantidad) {
        this.vida += cantidad;
        if (this.vida > vidaMax)
            this.vida = vidaMax;
        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
            System.out.println(this.nombre + " ha caído en combate.");
        }
    }

    
    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public String getNombre() {
        return nombre;
    }
}
