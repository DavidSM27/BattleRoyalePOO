package defaultPackage;

public class Arma implements Modificaciones{
	//Constantes
	public static final String ARMA_DEFAULT = "Pico";
	public static final Double ATAQUE_DEFAULT = 20.0; //daño default del pico
	public static final Double MEJORA_DEFAULT = 1.0;
	
	//Atributos
	private String nombre;
	private Double ataque;
	private Double ataqueSinMejora; 
	private Double mejora; //[1, 2] 
	
	//constructores
	
	//Constructor sin parametros(Coje el pico como DEFAULT)
	public Arma() {
		this(ARMA_DEFAULT, ATAQUE_DEFAULT, MEJORA_DEFAULT);
	}
	
	//Constructor con 2 parametros(cuando no hay mejora)
	public Arma(String nombre, Double ataque) {
		this(nombre, ataque, MEJORA_DEFAULT);
	}
	//Constructor con 3 parametros (no se si hara falta)
	public Arma(String nombre, Double ataque, Double mejora) {
		if(mejora>2 || mejora<1) {
			mejora=MEJORA_DEFAULT;
		}
		this.nombre = nombre;
		this.mejora = mejora;
		this.ataque = ((double)(Math.round(ataque * this.mejora * 100)) / 100);
		this.ataqueSinMejora = ataque;
	}

	//Getters y setters
	public Double getAtaque() {
		return this.ataque;
	}

	private void setAtaque(Double ataque) {
		this.ataque = ataque;
	}

	public Double getMejora() {
		return this.mejora;
	}

	public void setMejora(Double mejora) {
		this.mejora = mejora;
	}
	
	public Double getAtaqueSinMejora() {
		return this.ataqueSinMejora;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Arma:\n\t  Nombre: " + nombre + "\n\t  Ataque: " + ataque + 
				"\n\t  AtaqueSinMejora: " + ataqueSinMejora + "\n\t  Mejora: " + mejora;
	}
	
	public void modificacion(Double mejora) {
		this.setMejora(mejora);
		this.setAtaque(((double)(Math.round((this.ataqueSinMejora * this.mejora) * 100)) / 100));
	}
	
	//main de pruebas
	public static void main(String[] args) {
		
		Arma arco = new Arma("Arco", 20.0, 1.2);
		Arma RPG = new Arma("RPG", 90.3345, 1.33);
		Arma pico = new Arma();
		
		arco.modificacion(1.4);
		
		System.out.println(arco.toString());
		System.out.println(RPG.toString());
		System.out.println(pico.toString());
		
	}
}
