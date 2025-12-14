package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private int id;
	private String nombre;
	private List<Personaje> miembros = new ArrayList<>();
	private boolean estanVivos;
	private boolean sonNPCs;
	
	public Equipo(int id, String nombre, boolean sonNPCs) {
		this.id = id;
		this.nombre = nombre;
		this.estanVivos = false;
		this.miembros = new ArrayList<Personaje>();
		this.sonNPCs=sonNPCs;
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
