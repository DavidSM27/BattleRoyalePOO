package defaultPackage;

import java.util.Scanner;

public class BatallaEquipo extends Batalla<Equipo>{
	private static Scanner sc = new Scanner(System.in);
    private static Integer turno;
    private static String LOG;
    private static final Integer ENERGIA=50;
    private static Integer EQUIPO1_SIZE;
    private static Integer EQUIPO_OBJETIVO_SIZE;
    private static Equipo EQUIPO1_AUX;
    private static Equipo EQUIPO2_AUX;
    private static Integer BOTE_EQUIPO1;
    private static Integer BOTE_EQUIPO2;
    private static Integer XP1;
    private static Integer XP2;
    

    public BatallaEquipo() {
        super();
    }

    public String iniciarBatalla(Equipo equipo1, Equipo equipo2) {
        turno = 1;
        LOG = "";
        iniciar();
        
        BOTE_EQUIPO1=0;
        BOTE_EQUIPO2=0;
        XP1=0;
        XP2=0;
        
        for(int i=0; i<equipo1.size(); i++) {
        	BOTE_EQUIPO1+=equipo1.get(i).getOro();
        	XP1+=(int) Math.round((Math.random() * 50. + 100.) * (((double) (equipo1.get(i).getSuerte() - 1)) * 5. / 100. + 1));
        }
        for(int i=0; i<equipo2.size(); i++) {
        	BOTE_EQUIPO2+=equipo2.get(i).getOro();
        	XP2+=(int) Math.round((Math.random() * 50. + 100.) * (((double) (equipo2.get(i).getSuerte() - 1)) * 5. / 100. + 1));
        }
        
        System.out.println(equipo1.getNombre() + " VS " + equipo2.getNombre());

        while (equipo1.areVivos() && equipo2.areVivos() && this.enCurso) {
            LOG += "\n\t-Turno " + turno + "\n";
            System.out.println("\n\n========== TURNO " + turno + " ==========");
            
            // Para que mostrarEstadoBatalla imprima los equipos siempre de la mismo orden
            EQUIPO1_AUX=equipo1;
            EQUIPO2_AUX=equipo2;
            
            ejecutarTurno(equipo1, equipo2);
            
            EQUIPO1_AUX=equipo1;
            EQUIPO2_AUX=equipo2;
            
            
            ejecutarTurno(equipo2, equipo1);
            
            equipo1.shuffle();
            equipo2.shuffle();
            
            turno++;
        }

        if (equipo1.areVivos()) {
        	terminar(equipo1, equipo2);
        	otorgarRecompensas(equipo1, equipo2);
        } else if (equipo2.areVivos()){
        	terminar(equipo2, equipo1);
        	otorgarRecompensas(equipo2, equipo1);
        }

        return LOG;
    }

    private void ejecutarTurno(Equipo equipo1, Equipo equipo2) {
    	
        if (equipo1.areVivos() && equipo2.areVivos() && this.enCurso) {
        	EQUIPO1_SIZE=equipo1.size();
        	
        	System.out.print("\n=== TURNO DEL EQUIPO " + equipo1.getNombre().toUpperCase() + " ===");
        	LOG+="\n\t\t=== TURNO DEL EQUIPO " + equipo1.getNombre().toUpperCase() + " ===";
        	
        	for(int i=0; i < EQUIPO1_SIZE; i++) {
            	
        		if (equipo2.areVivos() && this.enCurso) {
		            System.out.println("\n=== Turno de " + equipo1.get(i).getNombre() + " ===");
		            LOG+="\n\t\t=== Turno de " + equipo1.get(i).getNombre() + "===\n";
		            mostrarEstadoBatalla(EQUIPO1_AUX);
		            mostrarEstadoBatalla(EQUIPO2_AUX);
		            LOG+="\n";
		            System.out.println();
		            elegirOponente(equipo1.get(i), equipo2, equipo1);
		            LOG+="\n";
		            
		            equipo2.quitarMuertos();
		            
		            Utilidades.sleep(3000);
        		}
        	}
        }
    }

    private void mostrarEstadoBatalla(Equipo equipo1) {
    	System.out.println(equipo1.getNombre()+":");
    	for (int i = 0; i < equipo1.size(); i++) {
	        System.out.println("\t"+equipo1.get(i).getNombre() + ": " +
	        		equipo1.get(i).getVida() + " HP  " +
	        		equipo1.get(i).getEnergia() + " Energía");
	        LOG+="\t\t\t-" + equipo1.get(i).getNombre() + ": " +
	        		equipo1.get(i).getVida() + " HP  " +
	        		equipo1.get(i).getEnergia() + " Energía\n";
    	}
    }
    
    private void elegirOponente(Personaje atacante, Equipo equipo, Equipo equipoAtacante) {
    	EQUIPO_OBJETIVO_SIZE=equipo.size();
    	int opcion=0;
    	if (equipo.size()!=1) {
	        if(!atacante.isNPC()) {
	        	System.out.println("¿A quien quieres atacar?");
	    		for (int i = 0; i < EQUIPO_OBJETIVO_SIZE; i++) {
	    			System.out.println("\t["+ (i+1) +"] "+equipo.get(i).getNombre());
	    		}
	    		do{
	    			while (!sc.hasNextInt()) {
		                System.out.println("Opción no válida.");
		                sc.next();
		            }
		
		            opcion = sc.nextInt();
		            sc.nextLine();
	    		}while(opcion<1 || opcion>EQUIPO_OBJETIVO_SIZE);
	    		opcion--;
	    		
	        }else {
	        	opcion=(int)Math.round(((Math.random()*EQUIPO_OBJETIVO_SIZE)%EQUIPO_OBJETIVO_SIZE));
	        }
	        
    	}else {
    		opcion=0;
    	}
        
    	ejecutarAccion(atacante, equipo.get(opcion), equipoAtacante);
    }

    private void ejecutarAccion(Personaje atacante, Personaje objetivo, Equipo equipoAtacante) {
    	
        if (atacante.isNPC()) {
            ejecutarAccionNPC(atacante, objetivo);
        } else {
            ejecutarAccionJugador(atacante, objetivo, equipoAtacante);
        }
    }

    private void ejecutarAccionJugador(Personaje atacante, Personaje objetivo, Equipo equipoAtacante) {
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
            if (opcion == 2 && atacante.getEnergia() < Personaje.COSTE_HABILIDAD2) {
                System.out.println(atacante.getNombre() + " no tiene suficiente energía para usar habilidades!");
                opcion=0;
            }

        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                ataqueBasico(atacante, objetivo);
                LOG += "\t\t-" + atacante.getNombre() + " ha atacado a " + objetivo.getNombre() +
                        " con " + atacante.getArma().getNombre() + " y ha hecho " +
                        atacante.getArma().getAtaque() + " de daño\n";
                break;
            case 2:
                usarHabilidad(atacante, objetivo, equipoAtacante);
                break;
            case 3:
            	int energiaAnterior=atacante.getEnergia();
                recuperarEnergia(atacante);
                LOG += "\t\t-" + atacante.getNombre() + " pasa turno para recupera "+ (atacante.getEnergia()-energiaAnterior) +" de energía\n";
                break;
            case 4:
                if (intentarHuir(equipoAtacante)) {
                    LOG += "\t\t-El equipo" + equipoAtacante.getNombre() + " ha huido\n";
                    terminarPorHuida(equipoAtacante);
                } else {
                    LOG += "\t\t-" + equipoAtacante.getNombre() + " intentó huir pero no lo consiguió\n";
                    System.out.println(equipoAtacante.getNombre() + " no pudo escapar!");
                }
                break;
        }
    }

    private void ejecutarAccionNPC(Personaje atacante, Personaje objetivo) {
    	int decision = 0;
    	
    	decision=(int) (Math.random() * 100);
    	
    	if(atacante.getEnergia() < Personaje.COSTE_HABILIDAD2) {
    		int energiaAnterior=atacante.getEnergia();
            recuperarEnergia(atacante);
            LOG += "\t\t-" + atacante.getNombre() + " pasa turno para recupera "+ (atacante.getEnergia()-energiaAnterior) +" de energía\n";
    	}else {
    		if (decision < 50) {
    			usarHabilidad(atacante, objetivo, null);
    		}else {
				ataqueBasico(atacante, objetivo);
                LOG += "\t\t-" + atacante.getNombre() + " ha usado su ataque normal a " + objetivo.getNombre() +
                        " y le ha hecho " + atacante.getArma().getAtaque() + " de daño\n";
			}
        }
    }

    private void ataqueBasico(Personaje atacante, Personaje objetivo) {
        int danoBase =(int) Math.round(atacante.getArma().getAtaque());

        System.out.println(atacante.getNombre() + " ataca con " + atacante.getArma().getNombre() + "!");
        objetivo.recibirDanio(danoBase);
    }

    private void usarHabilidad(Personaje atacante, Personaje objetivo, Equipo equipoAtacante) {

        if (!atacante.isNPC()) {
            mostrarMenuHabilidades(atacante, objetivo, equipoAtacante);
        } else {
            usarHabilidadAleatoria(atacante, objetivo);
        }
    }

    private void mostrarMenuHabilidades(Personaje atacante, Personaje objetivo, Equipo equipoAtacante) {
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
        System.out.println("4. Volver");

        do {
            System.out.print("Introduce una opcion: ");
            while (!sc.hasNextInt()) {
                System.out.println("<Opción no válida>");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 4) {
                System.out.println("Elige entre 1 y 4.");
            }else if(opcion==1 && atacante.getEnergia() < Personaje.COSTE_HABILIDAD1) {
            	System.out.println("No tienes suficiente energia");
            	opcion=0;
            }else if(opcion==3 && atacante.getEnergia() < Personaje.COSTE_HABILIDAD3) {
            	System.out.println("No tienes suficiente energia");
            	opcion=0;
            }
        } while (opcion < 1 || opcion > 4);
        
        
        if(opcion!=4) {
        	ejecutarHabilidadEspecifica(atacante, objetivo, opcion);
        }else {
        	ejecutarAccionJugador(atacante, objetivo, equipoAtacante);
        }
    }

    private void usarHabilidadAleatoria(Personaje atacante, Personaje objetivo) {
    	int habilidad=0;
        if(atacante.getVida()==atacante.getVidaMax()) {
        	// habilidad1 energia 20
        	if(atacante.getEnergia() >= Personaje.COSTE_HABILIDAD1) {
        		habilidad = (int) (Math.random() * 2)%2 + 1;
        	}else {
        		habilidad = 2;
        	}
        }else {
        	if(atacante.getEnergia() >= Personaje.COSTE_HABILIDAD3) {
        		habilidad=3;
        	}if(atacante.getEnergia() >= Personaje.COSTE_HABILIDAD1) {
        		habilidad = (int) (Math.random() * 2)%2 + 1;
        	}else {
        		habilidad = 2;
        	}
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
                
                atacante.setEnergia(fuego.getEnergia());
                
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
                
                atacante.setEnergia(agua.getEnergia());
                
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
                
                atacante.setEnergia(tierra.getEnergia());
                
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
                
                atacante.setEnergia(viento.getEnergia());
                
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
                
                atacante.setEnergia(magia.getEnergia());
                
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
                
                atacante.setEnergia(vida.getEnergia());
                
                break;
        }
    }
    
    private void recuperarEnergia(Personaje personaje) {
        System.out.println(personaje.getNombre() + " pasa su turno y recupera energía.");
        personaje.recuperarEnergia(ENERGIA);
    }

    private boolean intentarHuir(Equipo equipo) {
        double probabilidad = 0.3 + (equipo.getMediaVelocidad() * 0.05) + (equipo.getMediaSuerte() * 0.05);
        return Math.random() < probabilidad;
    }

    private void otorgarRecompensas(Equipo ganador, Equipo perdedor) {
    	int boteOro=0;
    	int boteXP=0;
    	
    	if(ganador.getNombre().equals(EQUIPO1_AUX.getNombre())) {
    		boteOro=BOTE_EQUIPO1;
    		boteXP=XP1;
    	}else {
    		boteOro=BOTE_EQUIPO2;
    		boteXP=XP2;
    	}
    	
    	int oro=(int)(boteOro/ganador.size());
    	int xp=(int)(boteXP/ganador.size());
    	
    	System.out.println("Los que han sobrevivido:");
    	for (int i = 0; i < ganador.size(); i++) {
    		System.out.println("\t-"+ganador.get(i).getNombre());
    		ganador.get(i).anadirOro(oro);
        	ganador.get(i).ganarXP(xp);
    	}
    	System.out.println();
    	
        System.out.println("\nFelicidades por resultar victorioso en tu batalla: ");
        System.out.println("\n RECOMPENSAS:");
        System.out.println("  +" + oro + " oro a cada uno");
        System.out.println("  +" + xp + " XP a cada uno");
    }
    
    protected void terminar(Equipo ganador, Equipo perdedor) {
        this.enCurso = false;
        
        System.out.println("\n ========== BATALLA TERMINADA ==========");
        System.out.println("Ganador: " + ganador.getNombre());
        System.out.println("Perdedor: " + perdedor.getNombre());
        System.out.println("==========================================\n");
        
        LOG+="\n ========== BATALLA TERMINADA ==========\n";
		LOG+="Ganador: " + ganador.getNombre()+"\n";
		LOG+="Perdedor: " + perdedor.getNombre()+"\n\n";
    }
    
    protected void terminarPorHuida(Equipo cagon) {
        this.enCurso = false;

        System.out.println("\n ========== BATALLA TERMINADA ==========");
        System.out.println(cagon.getNombre()+" huyó de la batalla!");
        System.out.println("==========================================\n");
        
        LOG+="\n ========== BATALLA TERMINADA ==========\n";
		LOG+=cagon.getNombre()+" huyó de la batalla!\n\n";
    }
}
