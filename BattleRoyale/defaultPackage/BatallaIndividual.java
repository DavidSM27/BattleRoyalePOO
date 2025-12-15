package defaultPackage;

import java.util.Scanner;

public class BatallaIndividual extends Batalla<Personaje> {

    private static Scanner sc = new Scanner(System.in);
    private static Integer turno;
    private static String LOG;
    private static final Integer ENERGIA=50;

    public BatallaIndividual() {
        super();
    }

    public String iniciarBatalla(Personaje jugador1, Personaje jugador2) {
        turno = 1;
        LOG = "";
        iniciar();

        System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());
        System.out.println();

        while (jugador1.isVivo() && jugador2.isVivo() && this.enCurso) {
            LOG += "\t-Turno " + turno + "\n";
            ejecutarTurno(jugador1, jugador2);
            turno++;
            try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				// Por si hay alguna interrupcion
			}
        }

        if (jugador1.isVivo()) {
            terminar(jugador1, jugador2);
            otorgarRecompensas(jugador1, jugador2);
        } else if (jugador2.isVivo()){
            terminar(jugador2, jugador1);
            otorgarRecompensas(jugador1, jugador2);
        }

        return LOG;
    }

    private void ejecutarTurno(Personaje jugador1, Personaje jugador2) {
        System.out.println("\n========== TURNO " + turno  + " ==========");

        if (jugador1.isVivo()) {
            System.out.println("\n=== Turno de " + jugador1.getNombre() + " ===");
            mostrarEstadoBatalla(jugador1, jugador2);
            ejecutarAccion(jugador1, jugador2);
        }

        if (jugador2.isVivo() && jugador1.isVivo() && this.enCurso) {
            System.out.println("\n=== Turno de " + jugador2.getNombre() + " ===");
            mostrarEstadoBatalla(jugador1, jugador2);
            ejecutarAccion(jugador2, jugador1);
        }
    }

    private void mostrarEstadoBatalla(Personaje jugador1, Personaje jugador2) {
        System.out.println(jugador1.getNombre() + ": " +
                jugador1.getVida() + " HP  " +
                jugador1.getEnergia() + " Energía");
        System.out.println(jugador2.getNombre() + ": " +
                jugador2.getVida() + " HP  " +
                jugador2.getEnergia() + " Energía");
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
            System.out.println("3. Recuperar 50 de energia");
            System.out.println("4. Huir de la batalla");

            while (!sc.hasNextInt()) {
                System.out.println("Opción no válida.");
                sc.next();
            }

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción no válida. Elige entre 1 y 5.");
            }
            
            if (opcion == 2 && atacante.getEnergia() < Personaje.COSTE_HABILIDAD) {
                System.out.println(atacante.getNombre() + " no tiene suficiente energía para usar habilidades!");
            }

        } while (opcion < 1 || opcion > 4 || (opcion == 2 && atacante.getEnergia() < Personaje.COSTE_HABILIDAD));

        switch (opcion) {
            case 1:
                ataqueBasico(atacante, objetivo);
                LOG += "\t\t-" + atacante.getNombre() + " ha atacado a " + objetivo.getNombre() +
                        " con " + atacante.getArma().getNombre() + " y ha hecho " +
                        atacante.getArma().getAtaque() + " de daño\n";
                break;
            case 2:
                usarHabilidad(atacante, objetivo);
                break;
            case 3:
                LOG += "\t\t-" + atacante.getNombre() + " pasa turno para recupera "+ENERGIA+" de energía\n";
                recuperarEnergia(atacante);
                break;
            case 4:
                if (intentarHuir(atacante)) {
                    LOG += "\t\t-" + atacante.getNombre() + " ha huido de " + objetivo.getNombre() + "\n";
                    terminarPorHuida(atacante);
                } else {
                    LOG += "\t\t-" + atacante.getNombre() + " intentó huir pero no lo consiguió\n";
                    System.out.println(atacante.getNombre() + " no pudo escapar!");
                }
                break;
        }
    }

    private void ejecutarAccionNPC(Personaje atacante, Personaje objetivo) {
    	int decision = 0;
    	
    	if(atacante.getEnergia() == Personaje.ENERGIA_MAX) {
    		decision=(int) (Math.random() * 79);
    	}else {
    		decision=(int) (Math.random() * 100);
    	}
    	
        

        if (decision < 40) {
        	if(atacante.getEnergia() >= Personaje.COSTE_HABILIDAD) {
        		usarHabilidad(atacante, objetivo);
        	}else {
        		ataqueBasico(atacante, objetivo);
                LOG += "\t\t-" + atacante.getNombre() + " ha usado su ataque normal a " + objetivo.getNombre() +
                        " y le ha hecho " + atacante.getArma().getAtaque() + " de daño\n";
        	}
        } else if (decision < 80) {
            ataqueBasico(atacante, objetivo);
            LOG += "\t\t-" + atacante.getNombre() + " ha usado su ataque normal a " + objetivo.getNombre() +
                    " y le ha hecho " + atacante.getArma().getAtaque() + " de daño\n";
        } else {
        	recuperarEnergia(atacante);
            LOG += "\t\t-" + atacante.getNombre() + " recupera "+ENERGIA+" de energia\n";
        }
    }

    private void ataqueBasico(Personaje atacante, Personaje objetivo) {
        int danoBase = (int) (atacante.getArma().getAtaque() + (atacante.getFuerza() * 2));
        int danoFinal = Math.max(1, danoBase - (objetivo.getDefensa() / 2));

        System.out.println(atacante.getNombre() + " ataca con " + atacante.getArma().getNombre() + "!");
        objetivo.recibirDanio(danoFinal);
    }

    private void usarHabilidad(Personaje atacante, Personaje objetivo) {

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
                System.out.println("1. Lluvia Infernal (" + Fuego.getDañoHabilidad1() + " de daño)");
                System.out.println("2. Mar de Lava (" + Fuego.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Curación del Infierno (" + Fuego.getCuracionHabilidad() + " de curacion)");
                break;
            case AGUA:
                System.out.println("1. Tsunami (" + Agua.getDañoHabilidad1() + " de daño)");
                System.out.println("2. Vorágine (" + Agua.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Curación de Poseidón (" + Agua.getCuracionHabilidad() + " de curacion)");
                break;
            case TIERRA:
                System.out.println("1. Crear Grieta (" + Tierra.getDañoHabilidad1() + " de daño)");
                System.out.println("2. Lanzar Roca (" + Tierra.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Sanación Rocal (" + Tierra.getCuracionHabilidad() + " de curacion)");
                break;
            case VIENTO:
                System.out.println("1. Lanzar Torbellino (" + Viento.getDañoHabilidad1() + " de daño)");
                System.out.println("2. Soplido de Dios (" + Viento.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Curación de Eolo (" + Viento.getCuracionHabilidad() + " de curacion)");
                break;
            case MAGIA:
                System.out.println("1. Bola de Fuego (" + Magia.getDañoHabilidad1() + " de curacion)");
                System.out.println("2. Congelar Enemigo (" + Magia.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Curación Divina (" + Magia.getCuracionHabilidad() + " de curacion)");
                break;
            case VIDA:
                System.out.println("1. Apretón de Corazón (" + Vida.getDañoHabilidad1() + " de curacion)");
                System.out.println("2. Explotar Sangre (" + Vida.getDañoHabilidad2() + " de daño)");
                System.out.println("3. Curación Médica (" + Vida.getCuracionHabilidad() + " de curacion)");
                break;
        }

        do {
            System.out.print("Introduce una opcion: ");
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
        int habilidad;
        if(atacante.getVida()==atacante.getVidaMax()) {
        	habilidad = (int) (Math.random() * 2)%2 + 1;
        }else {
        	habilidad = (int) (Math.random() * 3)%3 + 1;
        }
        
        ejecutarHabilidadEspecifica(atacante, objetivo, habilidad);
    }

    private void ejecutarHabilidadEspecifica(Personaje atacante, Personaje objetivo, int habilidad) {
    	int vidaAnteriorOjetivo=objetivo.getVida();
    	int vidaAnteriorAtacante=atacante.getVida();
    	
        switch (atacante.getElemento()) {
            case FUEGO:
                Fuego fuego = new Fuego(atacante);
                if (habilidad == 1) {
                    fuego.lluviaInfernal(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Lluvia Infernal y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                    fuego.marDeLava(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Mar de Lava y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                    fuego.curacionDelInfierno();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Curación del Infierno y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                
                break;
            case AGUA:
                Agua agua = new Agua(atacante);
                if (habilidad == 1) {
                	agua.tsunami(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Tsunami y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                	agua.voragine(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Voragine y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                	agua.curacionDePoseidon();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Curación de Poseidon y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                break;
            case TIERRA:
                Tierra tierra = new Tierra(atacante);
                if (habilidad == 1) {
                	tierra.terremoto(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Terremoto y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                	tierra.lanzarRoca(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Lanzar Roca y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                	tierra.sanacionRocal();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Sanación Rocal y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                break;
            case VIENTO:
                Viento viento = new Viento(atacante);
                if (habilidad == 1) {
                	viento.LanzarTorbellino(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Lanzar Torbellino y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                	viento.SoplidoDeDios(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Soplido de Dios y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                	viento.CuracionDeEolo();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Curación de Eolo y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                break;
            case MAGIA:
                Magia magia = new Magia(atacante);
                if (habilidad == 1) {
                	magia.lanzarBolaDeFuego(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Lanzar Bola de Fuego y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                	magia.congelarEnemigo(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Congelar Enemigo y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                	magia.curacionDivina();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Curación Divina y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                break;
            case VIDA:
                Vida vida = new Vida(atacante);
                if (habilidad == 1) {
                	vida.apretonDeCorazon(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Apreton de Corazón y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else if (habilidad == 2) {
                	vida.explotarSangre(objetivo);
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Explotar Sangre y ha infligido " +
                            (vidaAnteriorOjetivo - objetivo.getVida()) + " de daño\n";
                }else {
                	vida.curacionMedica();
                    LOG+="\t\t-" + atacante.getNombre() + " ha usado una habilidad Curación Medica y se ha curado " +
                            (atacante.getVida() - vidaAnteriorAtacante) + " de vida\n";
                }
                break;
        }
    }

    private void recuperarEnergia(Personaje personaje) {
        System.out.println(personaje.getNombre() + " pasa su turno y recupera energía.");
        personaje.recuperarEnergia(ENERGIA);
    }

    private boolean intentarHuir(Personaje personaje) {
        double probabilidad = 0.3 + ((personaje.getVelocidad()-1) * 0.05) + ((personaje.getSuerte()-1) * 0.05);
        return Math.random() < probabilidad;
    }

    private void otorgarRecompensas(Personaje ganador, Personaje perdedor) {
        int oroGanado = perdedor.getOro();
        int xpGanado = (int) Math
                .round((Math.random() * 50. + 100.) * (((double) (ganador.getSuerte() - 1)) * 5. / 100. + 1));

        ganador.anadirOro(oroGanado);
        ganador.ganarXP(xpGanado);

        System.out.println("Felicidades por resultar victorioso en tu batalla: ");
        System.out.println("\n RECOMPENSAS:");
        System.out.println("  +" + oroGanado + " oro ");
        System.out.println("  +" + xpGanado + " XP ");
    }

    public String getLOG() {
        return LOG;
    }
    
    protected void terminar(Personaje ganador, Personaje perdedor) {
		this.enCurso = false;
		
		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println("Ganador: " + ganador.getNombre());
		System.out.println("Perdedor: " + perdedor.getNombre());
		System.out.println("==========================================\n");
	}
    
    protected void terminarPorHuida(Personaje cagon) {
		this.enCurso = false;

		System.out.println("\n ========== BATALLA TERMINADA ==========");
		System.out.println(cagon.getNombre()+" huyó de la batalla!");
		System.out.println("==========================================\n");
	}
    
}