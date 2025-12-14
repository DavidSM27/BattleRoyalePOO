package defaultPackage;

public abstract class Batalla<T> {
	
	protected boolean enCurso;

	public Batalla() {
		this.enCurso = false;
	}

	public void iniciar() {
		enCurso = true;
		System.out.println("\n ========== LA BATALLA HA COMENZADO ========== \n");
	}

	protected abstract void terminar(T ganador, T perdedor);
	
	protected abstract void terminarPorHuida(T cagon);
}
