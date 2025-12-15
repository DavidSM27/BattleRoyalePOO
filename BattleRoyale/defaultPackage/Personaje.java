package defaultPackage;

import java.util.Scanner;

public class Personaje implements Comparable<Personaje> {
    private static final Scanner sc = new Scanner(System.in);

    // Constantes
    protected static final int ENERGIA_MAX = 100; // Maxima energia del personaje cuando se instancia
    protected static final int COSTE_HABILIDAD1 = 55;
    protected static final int COSTE_HABILIDAD2 = 30;
    protected static final int COSTE_HABILIDAD3 = 35;
    protected static final int ORO_INICIAL = 0; // El oro con el que empieza el jugador
    protected static final int VIDA_MAX_DEFECTO = 100; // El max de vida del personaje
    protected static final int PUNTOS_DE_NIVEL = 20; // Los puntos que tienes para gastarte en las estadisticas de tu
                                                     // personaje // personaje
    protected static final int NIVEL_ESTADISTICAS_INCIALES = 1; // Todas las estadisticas comienzan en 1
    protected static final int SUBIDA_NIVEL = 100; // La xp necesaria para subir un nivel
    private static int contadorPersonajes = 1; // Contador de personajes

    // Atributos
    protected String nombre;
    protected int vida;
    protected int energia;
    protected boolean estaVivo;
    protected int oro;
    protected Arma arma;
    protected boolean esNPC; // saber si es npc
    protected Elemento elemento;
    protected int xp;
    protected int nivel;
    // Atributos de las estadisticas
    protected int fuerza;
    protected int velocidad;
    protected int defensa;
    protected int suerte;
    protected int puntosDeNivel;

    // Constructor por defecto sin parametros
    public Personaje() {
        this("Jugador N" + contadorPersonajes++, 1, false);
        this.elemento = Elemento.FUEGO;
    }

    // Constructor con un parametro , en este caso nombre
    public Personaje(String nombre) {
        this(nombre, 1, false);
        this.elemento = Elemento.FUEGO;
    }

    // Constructor completo por defecto
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
        if (esNPC) { // Si es npc tiene estadisticas aleatorias
            distribuirPuntosAleatorio();
        } else { // Si no es npc le vamos a preguntar en que se quiere gastar sus puntos de
                 // estadisticas
            establecerEstadisticas();
        }
    }

    public Personaje(Personaje jugador) {
        this.nombre = jugador.getNombre();
        this.vida = jugador.getVida();
        this.energia = jugador.getEnergia();
        this.estaVivo = jugador.isVivo();
        this.oro = jugador.getOro();
        this.arma = jugador.getArma();
        this.esNPC = jugador.isNPC();
        this.fuerza = jugador.getFuerza();
        this.velocidad = jugador.getVelocidad();
        this.defensa = jugador.getDefensa();
        this.suerte = jugador.getSuerte();
        this.puntosDeNivel = jugador.getPuntosDeNivel();
        this.elemento = jugador.getElemento();
        this.nivel = jugador.getNivel();
        this.xp = 0;

    }

    // Hacemos el compareTo con otro personaje para poder compararlos y devolver una
    // respuesta para los posteriores ataques

    public int compareTo(Personaje otro) {
        int respuesta = 0;

        switch (this.getElemento()) {
            case FUEGO:
                if (otro.getElemento().equals(Elemento.VIDA) || otro.getElemento().equals(Elemento.VIENTO)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.AGUA) || otro.getElemento().equals(Elemento.TIERRA)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
            case AGUA:
                if (otro.getElemento().equals(Elemento.TIERRA) || otro.getElemento().equals(Elemento.FUEGO)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.VIDA) || otro.getElemento().equals(Elemento.VIENTO)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
            case TIERRA:
                if (otro.getElemento().equals(Elemento.VIDA) || otro.getElemento().equals(Elemento.FUEGO)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.AGUA) || otro.getElemento().equals(Elemento.VIENTO)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
            case VIENTO:
                if (otro.getElemento().equals(Elemento.AGUA) || otro.getElemento().equals(Elemento.TIERRA)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.MAGIA) || otro.getElemento().equals(Elemento.FUEGO)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
            case MAGIA:
                if (otro.getElemento().equals(Elemento.VIENTO)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.VIDA)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
            case VIDA:
                if (otro.getElemento().equals(Elemento.MAGIA) || otro.getElemento().equals(Elemento.AGUA)) {
                    respuesta = 1;
                } else if (otro.getElemento().equals(Elemento.FUEGO) || otro.getElemento().equals(Elemento.TIERRA)) {
                    respuesta = -1;
                } else
                    respuesta = 0;
                break;
        }

        return respuesta;
    }
    
    public static void imprimirDebilidades() {
    	
    	System.out.println("Fuego:");
    	System.out.println("\t-Fuerte contra la Vida y el Viento.");
    	System.out.println("\t-Debil contra el Agua y la Tierra.");
    	Utilidades.sleep(1000);
    	System.out.println("Agua:");
    	System.out.println("\t-Fuerte contra la Tierra y el Fuego.");
    	System.out.println("\t-Debil contra la Vida y el Viento.");
    	Utilidades.sleep(1000);
    	System.out.println("Tierra:");
    	System.out.println("\t-Fuerte contra la Vida y el Fuego.");
    	System.out.println("\t-Debil contra el Agua y el Viento.");
    	Utilidades.sleep(1000);
    	System.out.println("Viento:");
    	System.out.println("\t-Fuerte contra el Agua y la Tierra.");
    	System.out.println("\t-Debil contra la Magia y el Fuego.");
    	Utilidades.sleep(1000);
    	System.out.println("Magia:");
    	System.out.println("\t-Fuerte contra el Viento.");
    	System.out.println("\t-Debil contra la Vida.\n");
    	Utilidades.sleep(1000);
    	System.out.println("Vida:");
    	System.out.println("\t-Fuerte contra la Magia y el Agua.");
    	System.out.println("\t-Debil contra el Fuago y la Tierra.");
    	Utilidades.sleep(1000);
    }
    
    // Verificamos los elementos de los 2 personajes que se enfrentan para saber si
    // alguno le hace potenciador a otro
    public double calcularPotenciador(Personaje otro) {
        int comparacion = compareTo(otro);
        double potenciador = 1.;
        if (comparacion == 1) {
            potenciador = 1.2;
        } else if (comparacion == -1) {
            potenciador = 0.8;
        }

        return potenciador;
    }

    // Calculamos el dano que se hace dependiendo el nivel y el multiplicador que se
    // le aplica
    public int calcularDanoNivel(int danoBase) {
        int danoFinal = 0;
        danoFinal = danoBase + this.getNivel() - 1;
        return danoFinal;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeNivel() {
        return puntosDeNivel;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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
        this.oro = oro;
    }

    public Arma getArma() {
        return arma;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
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
    
    // Intenta gastar energia si tiene, sino nos devuelve un mensaje de error
    protected boolean intentarGastarEnergia(int coste) {
        if (this.energia >= coste) {
            this.setEnergia(this.getEnergia()-coste);
            return true;
        }

        System.out.println(">> " + this.nombre +
                " intenta atacar pero está AGOTADO (Energía: " + this.energia + ")");
        return false;
    }

    // Recupera energia cuando se le llame a esta funcion, se recuperara la vida
    // indicada y se mostrara por pantalla
    public void recuperarEnergia(int cantidad) {
        if (cantidad <= 0)
            return;

        int energiaAnterior = this.energia;
        this.energia = Math.min(this.energia + cantidad, ENERGIA_MAX);
        int energiaRecuperada = this.energia - energiaAnterior;

        System.out.println(this.nombre + " recupera " + energiaRecuperada +
                " de energía. Total: " + this.energia + "/" + ENERGIA_MAX);
    }

    // El personaje recibe dano y lo muestra por pantalla
    public void recibirDanio(int cantidad, int comparacion) {
        if (!estaVivo) {
            System.out.println(this.nombre + " ya está muerto, déjalo en paz.");
            return;
        }
        
        this.vida -= cantidad;

        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
            if(comparacion==0) {
            	System.out.println(this.nombre + " recibe " + cantidad +
            			" de daño! (Vida: 0/" + VIDA_MAX_DEFECTO + ")");
            }else if (comparacion==1) {
            	System.out.println(this.nombre + " recibe un Ataque Crítico " + cantidad +
            			" de daño! (Vida: 0/" + VIDA_MAX_DEFECTO + ")");
            }else {
            	System.out.println(this.nombre + " recibe un Ataque Poco Éficaz " + cantidad +
            			" de daño! (Vida: 0/" + VIDA_MAX_DEFECTO + ")");
            }
            System.out.println(this.nombre + " ha sido DERROTADO.");
        } else {
        	if(comparacion==0) {
            	System.out.println(this.nombre + " recibe " + cantidad +
            			" de daño! (Vida: "+ this.vida +"/" + VIDA_MAX_DEFECTO + ")");
            }else if (comparacion==1) {
            	System.out.println(this.nombre + " recibe un Ataque Crítico " + cantidad +
            			" de daño! (Vida: "+ this.vida +"/" + VIDA_MAX_DEFECTO + ")");
            }else {
            	System.out.println(this.nombre + " recibe un Ataque Poco Éficaz " + cantidad +
            			" de daño! (Vida: "+ this.vida +"/" + VIDA_MAX_DEFECTO + ")");
            }
        }
    }
    
    public void recibirDanioArma(int cantidad) {
        if (!estaVivo) {
            System.out.println(this.nombre + " ya está muerto, déjalo en paz.");
            return;
        }
        
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

    // Con este metodo permitimos que un personaje recupere vida y que se muestre
    // por pantalla
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

    // Permite cambiar nuestras armas actuales por otras nuevas que aparezcan en
    // cofres
    public String equiparArma(Arma nuevaArma) {
        String log = "";

        if (nuevaArma == null) {
            System.out.println("Error: El arma no es válida.");
            log = "\t\t-Ha habido un error.";
        }

        else if (esNPC) {
            if (this.arma.compareTo(nuevaArma) == -1) {
                this.arma = nuevaArma;
                System.out.println(this.nombre + " ahora lleva " + nuevaArma.getNombre());
                log = "\t\t-" + this.nombre + " ahora lleva " + nuevaArma.getNombre();
            } else {
                log = "\t\t-" + this.nombre + " mantiene su arma actual: " + this.arma.getNombre();
            }
        }

        else {

            // Preguntar a los jugadores si quieren cambiar el arma
            String respuesta;
            do {
                System.out.println("\nTe ha tocado esta " + nuevaArma.toString());
                System.out.println("\nTu actual " + this.arma.toString());

                System.out.print("\n¿Quieres cambiar tu arma? (Si/No): ");

                respuesta = sc.next().toUpperCase().substring(0, 1);
                sc.nextLine();

                switch (respuesta) {
                    case "S":
                        this.arma = nuevaArma;
                        System.out.println(this.nombre + " ahora lleva " + nuevaArma.getNombre());
                        log = "\t\t-" + this.nombre + " ahora lleva " + nuevaArma.getNombre();
                        break;
                    case "N":
                        System.out.println(this.nombre + " mantiene su arma actual: " + this.arma.getNombre());
                        log = "\t\t-" + this.nombre + " mantiene su arma actual: " + this.arma.getNombre();
                        break;
                    default:
                        System.out.println("Respuesta no válida. Escribe Si o No.");
                }
            } while (!respuesta.equals("S") && !respuesta.equals("N"));
        }

        return log + "\n";
    }

    // Agregamos el oro indicado a el inventario de nuestro jugador
    public void anadirOro(int cantidad) {
        if (cantidad > 0) {
            this.oro += cantidad;
            System.out.println(this.nombre + " obtiene " + cantidad +
                    " de oro. Total: " + this.oro);
        }
    }

    // Hacemos el metodo toString personalizado
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

    // Todo el metodo para establecer las estadisticas de un humano
    public void establecerEstadisticas() {
        // Mostramos las opciones que tiene actualmente y los puntos
        // que tiene disponibles de asignacion de puntos para las estadisticas

        System.out.println("\n=== ESTABLECER ESTADÍSTICAS ===");
        System.out.println("Tienes " + this.puntosDeNivel + " puntos para distribuir");
        System.out.println("Estadísticas actuales:");
        System.out.println("  (1) Fuerza: " + this.fuerza);
        System.out.println("  (2) Velocidad: " + this.velocidad);
        System.out.println("  (3) Defensa: " + this.defensa);
        System.out.println("  (4) Suerte: " + this.suerte);
        System.out.println();

        while (this.puntosDeNivel > 0) {
            // Mostramos las opciones que tiene para mejorar y los puntos
            // que tiene disponibles en cada iteracion de asignacion de puntos
            System.out.println("Puntos restantes: " + this.puntosDeNivel);
            System.out.println("\n¿Qué estadística deseas mejorar?");
            System.out.println("(1) Fuerza (actual: " + this.fuerza + ")");
            System.out.println("(2) Velocidad (actual: " + this.velocidad + ")");
            System.out.println("(3) Defensa (actual: " + this.defensa + ")");
            System.out.println("(4) Suerte (actual: " + this.suerte + ")");
            System.out.println("(0) Finalizar distribución");

            int opcion = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("Elige una opción: "); // Pedimos la opcion
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion >= 0 && opcion <= 4) {// Vemos si es valida
                        entradaValida = true;
                    } else {
                        System.out.println("Opción no válida. Elige entre 0 y 4."); // Si no es valida imprimimos
                    }
                } else {
                    System.out.println("Por favor, introduce un número."); // Si no es un numero
                    sc.nextLine();
                }
            }

            // Confirmacion de que se quiere marchar del menu sin haber gastado todos, los
            // puntos
            // Si por el contrario ya los ha gastado todos directamente sale
            if (opcion == 0) {
                if (this.puntosDeNivel > 0) {
                    System.out.println();
                    System.out.println("Aún te quedan " + this.puntosDeNivel + " puntos sin asignar.");
                    System.out.print("¿Estás seguro de que quieres finalizar? (Si/No): ");
                    String confirmacion = sc.next().toUpperCase().substring(0, 1);
                    sc.nextLine();
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

        mostrarEstadisticas(); // Llamamos al metodo que nos muestra las estadisticas asociadas a nuestro
                               // personaje
    }

    // Este metodo esta dedicado a la distribucion aleatoria de los puntos de las
    // estadisticas para los bots
    public void distribuirPuntosAleatorio() {
        // Si los puntos son menos de 0 directamente nos saca
        if (this.puntosDeNivel <= 0) {
            return;
        }

        // Imprimimos por pantalla lo que la maquina esta haciendo para la distribucion
        // de puntos de forma automatica
        System.out.println(this.nombre + " (Skylander) distribuye sus " + this.puntosDeNivel + " puntos...");

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

        mostrarEstadisticas(); // LLamamos a el metodo que nos muestra las estadisticas de nuestros bots
    }

    // Metodo para imprimir por pantalla las estadisticas actuales de los personajes
    public void mostrarEstadisticas() {

        System.out.println("\n=== Estadisticas de " + this.nombre +" ===");
        System.out.println("  Fuerza: " + this.fuerza);
        System.out.println("  Velocidad: " + this.velocidad);
        System.out.println("  Defensa: " + this.defensa);
        System.out.println("  Suerte: " + this.suerte);
        System.out.println("  Puntos sobrantes: " + this.puntosDeNivel);
        System.out.println("============================\n");
    }

    // Metodo para poder ganar experiencia y para imprimir por pantalla esta subida
    // de xp
    public void ganarXP(int cantidad) {
        if (cantidad > 0) {
            xp += cantidad;
            System.out.println(nombre + " gana " + cantidad + " XP. Total: " + xp + "/" + SUBIDA_NIVEL);

            // Verificar si puede subir de nivel
            if (xp >= SUBIDA_NIVEL) {
                System.out.println("¡Has subido de nivel!");
                nivel++;
            }
        }

    }
}