package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private int id;
	private String nombre;
	private List<Personaje> miembros = new ArrayList<>();
	private boolean eliminado;
	
	public Equipo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.eliminado = false;
	}
	
	public void meterMiembro(Personaje p) {
		miembros.add(p);
	}
	
	public boolean estaVivo() {
        for (Personaje p : miembros) {
            if (p.isVivo()) {
                return true;
            }
        }
        eliminado = true;
        return false;
    }

	public String getNombre() {
		return nombre;
	}
	
	public List<Personaje> getMiembros() {
		return miembros;
	}

	public boolean isEliminado() {
		return eliminado;
	}
	
}
