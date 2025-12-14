package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private String nombre;
	private List<Personaje> miembros = new ArrayList<>();
	private boolean estanVivos;
	private boolean sonNPCs;
	
	public Equipo(String nombre, boolean sonNPCs) {
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
        estanVivos = true;
        return false;
    }

	public String getNombre() {
		return nombre;
	}
	
	public String getNombres() {
		String nombres="";
		for(int i=0; i<miembros.size(); i++) {
			if(i < miembros.size()-2) {
				nombres += miembros.get(i).getNombre()+", ";
			}else if(i == miembros.size()-2) {
				nombres += miembros.get(i).getNombre()+" y ";
			}else {
				nombres += miembros.get(i).getNombre();
			}
		}
		return nombres;
	}
	
	public List<Personaje> getMiembros() {
		return miembros;
	}

	public boolean areVivos() {
		return estanVivos;
	}
	
	public boolean areNPCs() {
		return sonNPCs;
	}
	
	public int getMediaSuerte() {
		Integer media=0;
		for(int i=0; i<miembros.size(); i++) {
			media+=miembros.get(i).getSuerte()-1;
		}
		
		return media/miembros.size();
	}
	
}
