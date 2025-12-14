package defaultPackage;

public class Fuego extends Personaje {

    private int nivel;

    public Fuego(String nombre, int nivel, boolean esNPC) {
        super(nombre, nivel, esNPC);
        this.setElemento(Elemento.FUEGO);

        if (nivel < 1)
            this.nivel = 1;
        else if (nivel > 5)
            this.nivel = 5;
        else
            this.nivel = nivel;
    }
    
    public Fuego(Personaje personaje) {
    	super(personaje);
    }

    public void lluviaInfernal(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            

            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(20))*this.calcularPotenciador(objetivo));
            objetivo.recibirDanio(dano);
        }
    }

    public void marDeLava(Personaje objetivo) {
        if (intentarGastarEnergia()) {
            System.out.println(this.nombre + " congela las piernas de " + objetivo.getNombre() + "!");
            
            this.energia-=Personaje.COSTE_HABILIDAD;
            
            // Aplicamos el switch según el nivel
            int dano = (int)Math.round(((double)calcularDanoNivel(10))*this.calcularPotenciador(objetivo));

            objetivo.recibirDanio(dano);
        }
    }

    public void curacionDelInfierno() {
        if (intentarGastarEnergia()) {
            
            this.energia-=Personaje.COSTE_HABILIDAD;
        	
            System.out.println(this.nombre + " usa magia sagrada.");
            this.curarVida(30);
        }
    }

}
