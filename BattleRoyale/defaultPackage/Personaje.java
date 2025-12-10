package defaultPackage;

public abstract class Personaje {
    // Atributos (privados según el guion '-' del diagrama)
    private String nombre;
    private int vida;
    private int vidaMax;
    private Estadisticas estadisticas;
    private Arma arma;
    private boolean enCombate;
    private boolean estaVivo;

    // Constructor
    public Personaje(String nombre, int vidaMax, Estadisticas estadisticas, Arma arma) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax; // Al inicio la vida está al máximo
        this.estadisticas = estadisticas;
        this.arma = arma;
        this.enCombate = false;
        this.estaVivo = true;
    }

    // --- Métodos Abstractos (abs) ---
    // Deben ser implementados por las clases hijas
    public abstract String getElemento();

    public abstract void habilidadElemental();

    public abstract void recibirAtaque(int cantidad); // Asumo un int para el daño

    // --- Métodos Concretos ---
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

    // Getters y Setters necesarios según diagrama
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

    // Getter para nombre (útil para logs)
    public String getNombre() {
        return nombre;
    }
}
