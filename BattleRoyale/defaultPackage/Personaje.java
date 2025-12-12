package defaultPackage;

import java.util.Scanner;

public class Personaje {

    protected static final int ENERGIA_MAX = 100;
    protected static final int COSTE_HABILIDAD = 50;
    protected static final int ORO_DEFAULT = 0;
    protected static int contador = 1;
    protected static final int VIDAMAX_DEF = 100;
    protected static final String NOMBRE_DEF = "Jugador ";

    protected String nombre;
    protected int vida;
    protected int vidaMax;
    protected int energia;
    protected boolean estaVivo;
    protected int oro;
    protected Arma arma;

    public Personaje() {
        this((NOMBRE_DEF + contador++), VIDAMAX_DEF);
    }

    public Personaje(String nombre) {
        this(nombre, 100);
    }

    public Personaje(String nombre, int vidaMax) {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.energia = ENERGIA_MAX;
        this.estaVivo = true;
        this.oro = ORO_DEFAULT;
        this.arma = new Arma(); // Pico por defecto
        
    }
    
    public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + nombre +
				"\n\t Vida: " + vida +
				"\n\t VidaMax: " + vidaMax +
				"\n\t Energia: " + energia +
				"\n\t EstaVivo: " + estaVivo +
				"\n\t Oro: " + oro +
				"\n\t " + arma;
	}

	protected boolean intentarGastarEnergia(String nombreEnergia) {
        if (this.energia >= COSTE_HABILIDAD) {
            this.energia -= COSTE_HABILIDAD;
            return true;
        } else {
            System.out
                    .println(">> " + this.nombre + " intenta atacar pero está AGOTADO (Energía: " + this.energia + ")");
            return false;
        }
    }

    public void recuperarEnergia(int cantidad) {
        this.energia += cantidad;
        if (this.energia > ENERGIA_MAX) {
            this.energia = ENERGIA_MAX;
        }
        System.out.println(
                this.nombre + " recupera " + cantidad + " de energía. Total: " + this.energia + "/" + ENERGIA_MAX);
    }

    public void recibirDanio(int cantidad) {
        if (!estaVivo) {
            System.out.println(this.nombre + " ya está muerto, déjalo en paz.");
            return;
        }

        this.vida -= cantidad;
        System.out.println(
                this.nombre + " recibe " + cantidad + " de daño! (Vida: " + this.vida + "/" + this.vidaMax + ")");

        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
            System.out.println(this.nombre + " ha sido DERROTADO.");
        }
    }

    public void curarVida(int cantidad) {
        if (estaVivo) {
            this.vida += cantidad;
            if (this.vida > vidaMax)
                this.vida = vidaMax;
            System.out.println(this.nombre + " se cura " + cantidad + " HP. (Vida: " + this.vida + ")");
        }
    }
    
    //****NUEVO*****
    
    //equipa al jugador el nuevo arma que se encuentra en el cofre
    public void equiparArma(Arma nuevaArma) {
    	Scanner sc = new Scanner(System.in);
    	String respuesta;
    	
    	do {
    		System.out.println("¿Quiere cambiar de arma a " + nuevaArma.getNombre() + "? (S/N)");
    		respuesta = sc.nextLine().toUpperCase();
    		
    		if(respuesta.equals("S")) {
    			this.arma = nuevaArma;
    	    	System.out.println(this.nombre + " ahora lleva " + nuevaArma.getNombre());
    		}else if(respuesta.equals("N")){
    			System.out.println(this.nombre + "mantiene su arma actual" + this.arma.getNombre());
    		}else {
    			System.out.println("Respuesta no valida. Escribe S o N");
    		}
    	}while(!respuesta.equals("S") && !respuesta.equals("N"));
    }

    public String getNombre() {
        return nombre;
    }

    public int getOro() {
		return oro;
	}

	public void setOro(int oro) {
		this.oro = oro;
	}

	public boolean isVivo() {
        return estaVivo;
    }
}