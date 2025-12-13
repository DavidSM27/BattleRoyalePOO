package defaultPackage;

import java.security.ProtectionDomain;
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
    protected static final int SUBIDA_NIVEL = 100;
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
    protected int puntosDeNivel;
    protected Elemento elemento;
    protected int xp;
    protected int nivel;

    // Constructor por defecto
    public Personaje() {
        this("Jugador " + contadorPersonajes++, 1, false);
        this.elemento = Elemento.FUEGO;
    }

    // Constructor con nombre propio
    public Personaje(String nombre) {
        this(nombre, 1, false);
        this.elemento = Elemento.FUEGO;
    }

    // Constructor completo
    public Personaje(String nombre, int nivel, boolean esNPC) {
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
        this.puntosDeNivel = PUNTOS_DE_NIVEL;
        this.elemento = Elemento.FUEGO; // Por defecto el personaje es tipo fuego
        this.nivel = nivel;
        this.xp = 0;
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

    public int getFuerza() {
        return fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getSuerte() {
        return suerte;
    }

    public Elemento getElemento() {
        return this.elemento;
    }

    protected void setElemento(Elemento elemento) {
        this.elemento = elemento;
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
                    " de daño! (Vida: 0/" + VIDA_MAX_DEFECTO + ")");
            System.out.println(this.nombre + " ha sido DERROTADO.");
        } else {
            System.out.println(this.nombre + " recibe " + cantidad +
                    " de daño! (Vida: " + this.vida + "/" + VIDA_MAX_DEFECTO + ")");
        }
    }

    //
    public void curarVida(int cantidad) {
        if (!estaVivo) {
            System.out.println(this.nombre + " está muerto y no puede curarse.");
            return;
        }

        if (cantidad <= 0)
            return;

        int vidaAnterior = this.vida;
        this.vida = Math.min(this.vida + cantidad, VIDA_MAX_DEFECTO);
        int vidaCurada = this.vida - vidaAnterior;

        System.out.println(this.nombre + " se cura " + vidaCurada +
                " HP. (Vida: " + this.vida + "/" + VIDA_MAX_DEFECTO + ")");
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
                    this.arma.getNombre() + ") por " + nuevaArma.getNombre() + "? (Escribe S o N)");
            respuesta = sc.nextLine().toUpperCase();

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
    public void anadirOro(int cantidad) {
        if (cantidad > 0) {
            this.oro += cantidad;
            System.out.println(this.nombre + " obtiene " + cantidad +
                    " de oro. Total: " + this.oro);
        }
    }

    @Override
    public String toString() {

        String estado;
        if (estaVivo) {
            estado = "Vivo";
        } else {
            estado = "Muerto";
        }

        String textoTipo;
        if (esNPC) {
            textoTipo = "NPC";
        } else {
            textoTipo = "Jugador";
        }

        return String.format(
                "=== %s ===%n" +
                        "  Vida: %d/%d%n" +
                        "  Energía: %d/%d%n" +
                        "  Estado: %s%n" +
                        "  Oro: %d%n" +
                        "  Arma: %s%n" +
                        "  Tipo: %s",
                nombre,
                vida,
                VIDA_MAX_DEFECTO,
                energia,
                ENERGIA_MAX,
                estado,
                oro,
                arma.getNombre(),
                textoTipo);
    }

    // Funcion para cerrar el scanner
    public static void cerrarScanner() {
        if (sc != null) {
            sc.close();
        }
    }

    public void establecerEstadisticas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== ESTABLECER ESTADÍSTICAS ===");
        System.out.println("Tienes " + this.puntosDeNivel + " puntos para distribuir");
        System.out.println("Estadísticas actuales:");
        System.out.println("  1. Fuerza: " + this.fuerza);
        System.out.println("  2. Velocidad: " + this.velocidad);
        System.out.println("  3. Defensa: " + this.defensa);
        System.out.println("  4. Suerte: " + this.suerte);
        System.out.println();

        while (this.puntosDeNivel > 0) {
            System.out.println("Puntos restantes: " + this.puntosDeNivel);
            System.out.println("\n¿Qué estadística deseas mejorar?");
            System.out.println("1. Fuerza (actual: " + this.fuerza + ")");
            System.out.println("2. Velocidad (actual: " + this.velocidad + ")");
            System.out.println("3. Defensa (actual: " + this.defensa + ")");
            System.out.println("4. Suerte (actual: " + this.suerte + ")");
            System.out.println("5. Finalizar distribución");

            int opcion = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("Elige una opción: ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    if (opcion >= 0 && opcion <= 4) {
                        entradaValida = true;
                    } else {
                        System.out.println("Opción no válida. Elige entre 0 y 4.");
                    }
                } else {
                    System.out.println("Por favor, introduce un número.");
                    sc.next();
                }
            }

            if (opcion == 0) {
                if (this.puntosDeNivel > 0) {
                    System.out.println();
                    System.out.println("Aún te quedan " + this.puntosDeNivel + " puntos sin asignar.");
                    System.out.print("¿Estás seguro de que quieres finalizar? (S/N): ");
                    String confirmacion = sc.next().toUpperCase();
                    if (confirmacion.equals("S")) {
                        break;
                    }
                } else {
                    break;
                }
                continue;
            }

            // Pedir cantidad de puntos
            System.out.print("¿Cuántos puntos quieres asignar? (máximo " + this.puntosDeNivel + "): ");
            int puntosAsignados = 0;
            boolean cantidadValida = false;

            while (!cantidadValida) {
                if (sc.hasNextInt()) {
                    puntosAsignados = sc.nextInt();
                    if (puntosAsignados > 0 && puntosAsignados <= this.puntosDeNivel) {
                        cantidadValida = true;
                    } else if (puntosAsignados <= 0) {
                        System.out.println("Debes asignar al menos 1 punto.");
                        System.out.print("Intenta de nuevo: ");
                    } else {
                        System.out.println(
                                "No tienes tantos puntos. Solo tienes " + this.puntosDeNivel + " disponibles.");
                        System.out.print("Intenta de nuevo: ");
                    }
                } else {
                    System.out.println("Por favor, introduce un número válido.");
                    System.out.print("Intenta de nuevo: ");
                    sc.next();
                }
            }

            // Asignar puntos a la estadística elegida
            switch (opcion) {
                case 1:
                    this.fuerza += puntosAsignados;
                    this.puntosDeNivel -= puntosAsignados;
                    System.out.println("Fuerza aumentada a " + this.fuerza);
                    break;
                case 2:
                    this.velocidad += puntosAsignados;
                    this.puntosDeNivel -= puntosAsignados;
                    System.out.println("Velocidad aumentada a " + this.velocidad);
                    break;
                case 3:
                    this.defensa += puntosAsignados;
                    this.puntosDeNivel -= puntosAsignados;
                    System.out.println("Defensa aumentada a " + this.defensa);
                    break;
                case 4:
                    this.suerte += puntosAsignados;
                    this.puntosDeNivel -= puntosAsignados;
                    System.out.println("Suerte aumentada a " + this.suerte);
                    break;
            }
        }

        mostrarEstadisticas();
        sc.close();
    }

    public void distribuirPuntosAleatorio() {
        if (this.puntosDeNivel <= 0) {
            return;
        }

        System.out.println(this.nombre + " (NPC) distribuye sus " + this.puntosDeNivel + " puntos...");

        int puntosRestantes = this.puntosDeNivel;

        // Distribuir puntos uno a uno de forma aleatoria
        while (puntosRestantes > 0) {
            int estatistica = (int) (Math.random() * 4);

            switch (estatistica) {
                case 0:
                    this.fuerza++;
                    break;
                case 1:
                    this.velocidad++;
                    break;
                case 2:
                    this.defensa++;
                    break;
                case 3:
                    this.suerte++;
                    break;
            }

            puntosRestantes--;
        }

        // Resetear puntos de nivel
        this.puntosDeNivel = 0;

        mostrarEstadisticas();
    }

    public void mostrarEstadisticas() {

        System.out.println("\n=== ESTADÍSTICAS FINALES ===");
        System.out.println("  Fuerza: " + this.fuerza);
        System.out.println("  Velocidad: " + this.velocidad);
        System.out.println("  Defensa: " + this.defensa);
        System.out.println("  Suerte: " + this.suerte);
        System.out.println("  Puntos sobrantes: " + this.puntosDeNivel);
        System.out.println("============================\n");
    }

    public void ganarXP(int cantidad) {
        if (cantidad > 0) {
            xp += cantidad;
            System.out.println(nombre + " gana " + cantidad + " XP. Total: " + xp + "/" + SUBIDA_NIVEL);

            // Verificar si puede subir de nivel
            if (xp >= SUBIDA_NIVEL) {
                System.out.println("¡Puedes subir de nivel!");
            }
        }
    }
}