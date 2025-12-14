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

    public void apretonDeCorazon(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " le para el corazon a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void explotarSangre(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " saca toda la sangre del cuerpo de " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void curacionMedica() {
        if (intentarGastarEnergia()) {
            
            this.energia-=Personaje.COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " es operado del corazon en un quirofano");
            this.curarVida(30);
        }
    }
}