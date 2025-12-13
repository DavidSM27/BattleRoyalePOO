package defaultPackage;

import java.util.Scanner;

public class Personaje {
    private static final Scanner sc = new Scanner(System.in);

    // Constantes
    protected static final int ENERGIA_MAX = 100;
    protected static final int COSTE_HABILIDAD = 50;
    protected static final int ORO_INICIAL = 0;
    protected static final int VIDA_MAX_DEFECTO = 100;
    protected static final int PUNTOS_DE_NIVEL = 20;
    protected static final int NIVEL_ESTADISTICAS_INCIALES = 1;

    private static int contadorPersonajes = 1;

    // Atributos
    protected String nombre;
    protected int vida;
    protected int energia;
    protected boolean estaVivo;
    protected int oro;
    protected Arma arma;
    protected boolean esNPC;
    protected int fuerza;
    protected int velocidad;
    protected int defensa;
    protected int suerte;

    // Constructor por defecto
    public Personaje() {
        this("Jugador " + contadorPersonajes++, false);
    }

    // Constructor con nombre propio
    public Personaje(String nombre) {
        this(nombre, false);
    }

    // Constructor completo
    public Personaje(String nombre, boolean esNPC) {
        this.nombre = nombre;
        this.vida = VIDA_MAX_DEFECTO;
        this.energia = ENERGIA_MAX;
        this.estaVivo = true;
        this.oro = ORO_INICIAL;
        this.arma = new Arma(); // Arma por defecto
        this.esNPC = esNPC;
        this.fuerza = NIVEL_ESTADISTICAS_INCIALES;
        this.velocidad = NIVEL_ESTADISTICAS_INCIALES;
        this.defensa = NIVEL_ESTADISTICAS_INCIALES;
        this.suerte = NIVEL_ESTADISTICAS_INCIALES;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = VIDA_MAX_DEFECTO;
    }

    public int getVidaMax() {
        return VIDA_MAX_DEFECTO;
    }

    public int getEnergia() {
        return energia;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = Math.max(0, oro);
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        if (arma != null) {
            this.arma = arma;
        }
    }

    public boolean isVivo() {
        return estaVivo;
    }

    public boolean isNPC() {
        return esNPC;
    }

    // Intenta gastar energia si tiene
    protected boolean intentarGastarEnergia() {
        if (this.energia >= COSTE_HABILIDAD) {
            this.energia -= COSTE_HABILIDAD;
            return true;
        }

        System.out.println(">> " + this.nombre +
                " intenta atacar pero está AGOTADO (Energía: " + this.energia + ")");
        return false;
    }

    // Recupera energia
    public void recuperarEnergia(int cantidad) {
        if (cantidad <= 0)
            return;

        this.energia = Math.min(this.energia + cantidad, ENERGIA_MAX);

        System.out.println(this.nombre + " recupera " + cantidad +
                " de energía. Total: " + this.energia + "/" + ENERGIA_MAX);
    }

    // El personaje recibe dano
    public void recibirDanio(int cantidad) {
        if (!estaVivo) {
            System.out.println(this.nombre + " ya está muerto, déjalo en paz.");
            return;
        }

        if (cantidad <= 0)
            return;

        this.vida -= cantidad;

        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
            System.out.println(this.nombre + " recibe " + cantidad +
                    " de daño! (Vida: 0/" + this.vidaMax + ")");
            System.out.println(this.nombre + " ha sido DERROTADO.");
        } else {
            System.out.println(this.nombre + " recibe " + cantidad +
                    " de daño! (Vida: " + this.vida + "/" + this.vidaMax + ")");
        }
    }

    /**
     * Cura la vida del personaje
     */
    public void curarVida(int cantidad) {
        if (!estaVivo) {
            System.out.println(this.nombre + " está muerto y no puede curarse.");
            return;
        }

        if (cantidad <= 0)
            return;

        int vidaAnterior = this.vida;
        this.vida = Math.min(this.vida + cantidad, vidaMax);
        int vidaCurada = this.vida - vidaAnterior;

        System.out.println(this.nombre + " se cura " + vidaCurada +
                " HP. (Vida: " + this.vida + "/" + this.vidaMax + ")");
    }

    // Permite equipar armas desde cofres
    public void equiparArma(Arma nuevaArma) {
        if (nuevaArma == null) {
            System.out.println("Error: El arma no es válida.");
            return;
        }

        if (esNPC) {
            this.arma = nuevaArma;
            System.out.println(this.nombre + " ahora lleva " + nuevaArma.getNombre());
            return;
        }

        // Preguntar a los jugadores si quieren cambiar el arma
        String respuesta;
        do {
            System.out.println("\n¿Quieres cambiar tu arma actual (" +
                    this.arma.getNombre() + ") por " + nuevaArma.getNombre() + "? (S/N)");
            respuesta = sc.nextLine().trim().toUpperCase();

            switch (respuesta) {
                case "S":
                    this.arma = nuevaArma;
                    System.out.println(this.nombre + " ahora lleva " + nuevaArma.getNombre());
                    break;
                case "N":
                    System.out.println(this.nombre + " mantiene su arma actual: " +
                            this.arma.getNombre());
                    break;
                default:
                    System.out.println("Respuesta no válida. Escribe S o N.");
            }
        } while (!respuesta.equals("S") && !respuesta.equals("N"));
    }

    // Agrega oro a el inventario del jugador
    public void añadirOro(int cantidad) {
        if (cantidad > 0) {
            this.oro += cantidad;
            System.out.println(this.nombre + " obtiene " + cantidad +
                    " de oro. Total: " + this.oro);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "=== %s ===%n" +
                        "  Vida: %d/%d%n" +
                        "  Energía: %d/%d%n" +
                        "  Estado: %s%n" +
                        "  Oro: %d%n" +
                        "  Arma: %s%n" +
                        "  Tipo: %s",
                nombre, vida, vidaMax, energia, ENERGIA_MAX,
                estaVivo ? "Vivo" : "Muerto", oro, arma.getNombre(),
                esNPC ? "NPC" : "Jugador");
    }

    // Funcion para cerrar el scanner
    public static void cerrarScanner() {
        if (sc != null) {
            sc.close();
        }
    }
}