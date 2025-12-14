package defaultPackage;

import java.util.Scanner;

public class BatallaIndividual extends Batalla {

    private static Scanner sc = new Scanner(System.in);
    private Personaje jugador1;
    private Personaje jugador2;
    private int turno;

    public BatallaIndividual() {
        super();
    }

    public void iniciarBatalla(Personaje jugador1, Personaje jugador2) {
        iniciar();

        System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());
        System.out.println();

        while (jugador1.isVivo() && jugador2.isVivo()) {
            ejecutarTurno();
            turno++;
        }

        if (jugador1.isVivo()) {
            terminar(jugador1, jugador2);
            otorgarRecompensas(jugador1, jugador2);
        } else {
            terminar(jugador2, jugador1);
            otorgarRecompensas(jugador2, jugador1);
        }
    }

    private void ejecutarTurno() {
        System.out.println("\n========== TURNO " + turno + " ==========");

        if (jugador1.isVivo()) {
            System.out.println("\n=== Turno de " + jugador1.getNombre() + " ===");
            mostrarEstadoBatalla();
            ejecutarAccion(jugador1, jugador2);
        }

        if (jugador2.isVivo() && jugador1.isVivo()) {
            System.out.println("\n=== Turno de " + jugador2.getNombre() + " ===");
            mostrarEstadoBatalla();
            ejecutarAccion(jugador2, jugador1);
        }

        if (jugador1.isVivo()) {
            jugador1.recuperarEnergia(20);
        }
        if (jugador2.isVivo()) {
            jugador2.recuperarEnergia(20);
        }
    }

    private void mostrarEstadoBatalla() {
        System.out.println(jugador1.getNombre() + ": " +
                jugador1.getVida() + "+vida!  " +
                jugador1.getEnergia() + "+energia!");
        System.out.println(
                jugador2.getNombre() + ": " + jugador2.getVida() + "+vida!  " + jugador2.getEnergia() + "+energia!");
    }

    private void ejecutarAccion(Personaje atacante, Personaje objetivo) {
        if (atacante.isNPC()) {

            ejecutarAccionNPC(atacante, objetivo);
        } else {

            ejecutarAccionJugador(atacante, objetivo);
        }
    }

    private void ejecutarAccionJugador(Personaje atacante, Personaje objetivo) {
        int opcion = 0;

        do {
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Ataque básico (con arma)");
            System.out.println("2. Usar habilidad elemental");
            System.out.println("3. Defender (reducir daño próximo turno)");
            System.out.println("4. Pasar turno (recuperar energía)");

            if (!atacante.isNPC()) {
                System.out.println("5. Huir de la batalla");
            }

            while (!sc.hasNextInt()) {
                System.out.println("Opción no válida.");
                sc.next();
            }

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 5) {
                System.out.println("Opción no válida. Elige entre 1 y 5.");
            }

        } while (opcion < 1 || opcion > 5);

        switch (opcion) {
            case 1:
                ataqueBasico(atacante, objetivo);
                break;
            case 2:
                usarHabilidad(atacante, objetivo);
                break;
            case 3:
                defender(atacante);
                break;
            case 4:
                pasarTurno(atacante);
                break;
            case 5:
                if (!atacante.isNPC()) {
                    if (intentarHuir(atacante)) {
                        terminar(objetivo, atacante);
                        System.out.println(atacante.getNombre() + " huyó de la batalla!");
                    }
                }
                break;
        }
    }

    private void ejecutarAccionNPC(Personaje atacante, Personaje objetivo) {
        int decision = (int) (Math.random() * 100);

        if (atacante.getEnergia() >= Personaje.COSTE_HABILIDAD && decision < 40) {

            usarHabilidad(atacante, objetivo);
        } else if (decision < 80) {

            ataqueBasico(atacante, objetivo);
        } else {
            defender(atacante);
        }
    }

    private void ataqueBasico(Personaje atacante, Personaje objetivo) {
        int danoBase = (int) (atacante.getArma().getAtaque() + (atacante.getFuerza() * 2));

        int danoFinal = Math.max(1, danoBase - (objetivo.getDefensa() / 2));

        System.out.println(atacante.getNombre() + " ataca con " + atacante.getArma().getNombre() + "!");
        objetivo.recibirDanio(danoFinal);

    }

    private void usarHabilidad(Personaje atacante, Personaje objetivo) {

        if (atacante.getEnergia() < Personaje.COSTE_HABILIDAD) {
            System.out.println(atacante.getNombre() + " no tiene suficiente energía para usar habilidades!");
            ataqueBasico(atacante, objetivo);
            return;
        }

        if (!atacante.isNPC()) {
            mostrarMenuHabilidades(atacante, objetivo);
        } else {

            usarHabilidadAleatoria(atacante, objetivo);
        }
    }

    private void mostrarMenuHabilidades(Personaje atacante, Personaje objetivo) {
        Elemento elemento = atacante.getElemento();
        int opcion = 0;

        System.out.println("\nElige una habilidad:");

        switch (elemento) {
            case FUEGO:
                System.out.println("1. Lluvia Infernal (Daño alto)");
                System.out.println("2. Mar de Lava (Daño medio)");
                System.out.println("3. Curación del Infierno");
                break;
            case AGUA:
                System.out.println("1. Tsunami (Daño alto)");
                System.out.println("2. Vorágine (Daño medio)");
                System.out.println("3. Curación de Poseidón");
                break;
            case TIERRA:
                System.out.println("1. Crear Grieta (Daño alto)");
                System.out.println("2. Lanzar Roca (Daño medio)");
                System.out.println("3. Sanación Rocal");
                break;
            case VIENTO:
                System.out.println("1. Lanzar Torbellino (Daño alto)");
                System.out.println("2. Soplido de Dios (Daño medio)");
                System.out.println("3. Curación de Eolo");
                break;
            case MAGIA:
                System.out.println("1. Bola de Fuego (Daño alto)");
                System.out.println("2. Congelar Enemigo (Daño medio)");
                System.out.println("3. Curación Divina");
                break;
            case VIDA:
                System.out.println("1. Apretón de Corazón (Daño alto)");
                System.out.println("2. Explotar Sangre (Daño medio)");
                System.out.println("3. Curación Médica");
                break;
        }

        do {
            while (!sc.hasNextInt()) {
                System.out.println("<Opción no válida>");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 3) {
                System.out.println("Elige entre 1 y 3.");
            }
        } while (opcion < 1 || opcion > 3);

        ejecutarHabilidadEspecifica(atacante, objetivo, opcion);
    }

    private void usarHabilidadAleatoria(Personaje atacante, Personaje objetivo) {
        int habilidad = (int) (Math.random() * 3) + 1;
        ejecutarHabilidadEspecifica(atacante, objetivo, habilidad);
    }

    private void ejecutarHabilidadEspecifica(Personaje atacante, Personaje objetivo, int habilidad) {

        switch (atacante.getElemento()) {
            case FUEGO:
                Fuego fuego = (Fuego) atacante;
                if (habilidad == 1)
                    fuego.lluviaInfernal(objetivo);
                else if (habilidad == 2)
                    fuego.marDeLava(objetivo);
                else
                    fuego.curacionDelInfierno();
                break;
            case AGUA:
                Agua agua = (Agua) atacante;
                if (habilidad == 1)
                    agua.tsunami(objetivo);
                else if (habilidad == 2)
                    agua.voragine(objetivo);
                else
                    agua.curacionDePoseidon();
                break;
            case TIERRA:
                Tierra tierra = (Tierra) atacante;
                if (habilidad == 1)
                    tierra.crearGrieta(objetivo);
                else if (habilidad == 2)
                    tierra.lanzarRoca(objetivo);
                else
                    tierra.sanacionRocal();
                break;
            case VIENTO:
                Viento viento = (Viento) atacante;
                if (habilidad == 1)
                    viento.LanzarTorbellino(objetivo);
                else if (habilidad == 2)
                    viento.SoplidoDeDios(objetivo);
                else
                    viento.CuracionDeEolo();
                break;
            case MAGIA:
                Magia magia = (Magia) atacante;
                if (habilidad == 1)
                    magia.lanzarBolaDeFuego(objetivo);
                else if (habilidad == 2)
                    magia.congelarEnemigo(objetivo);
                else
                    magia.curacionDivina();
                break;
            case VIDA:
                Vida vida = (Vida) atacante;
                if (habilidad == 1)
                    vida.apretonDeCorazon(objetivo);
                else if (habilidad == 2)
                    vida.explotarSangre(objetivo);
                else
                    vida.curacionMedica();
                break;
        }
    }

    private void defender(Personaje personaje) {
        System.out.println(personaje.getNombre() + " adopta una postura defensiva.");
        personaje.recuperarEnergia(30);
    }

    private void pasarTurno(Personaje personaje) {
        System.out.println(personaje.getNombre() + " pasa su turno y recupera energía.");
        personaje.recuperarEnergia(40);
    }

    private boolean intentarHuir(Personaje personaje) {
        double probabilidad = 0.3 + (personaje.getVelocidad() * 0.05);
        return Math.random() < probabilidad;
    }

    private void otorgarRecompensas(Personaje ganador, Personaje perdedor) {
        int oroGanado = 50 + (int) (Math.random() * 50);
        int xpGanado = 30 + (int) (Math.random() * 20);

        ganador.anadirOro(oroGanado);
        ganador.ganarXP(xpGanado);

        System.out.println("Felicidades por resultar victorioso en tu batalla: ");
        System.out.println("\n RECOMPENSAS:");
        System.out.println("  +" + oroGanado + " oro ");
        System.out.println("  +" + xpGanado + " XP ");
    }
}