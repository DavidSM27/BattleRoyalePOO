package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private String nombre;
	private List<Personaje> miembros = new ArrayList<>();
	private boolean sonNPCs;
	
	public Equipo(String nombre, boolean sonNPCs) {
		this.nombre = nombre;
		this.miembros = new ArrayList<Personaje>();
		this.sonNPCs=sonNPCs;
	}
	
	public void add(Personaje p) {
		miembros.add(p);
	}
	
	public int size() {
		return miembros.size();
	}

	public Personaje get(int i) {
		return miembros.get(i);
	}

	public Personaje remove(int i) {
		return miembros.remove(i);
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
	
	public boolean areVivos() {
		int vivos=0;
		for(int i=0; i<miembros.size(); i++) {
			if(miembros.get(i).isVivo()) {
				vivos++;
			}
		}
		
		return vivos==miembros.size() && miembros.size()>0;
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
	
	public int getMediaVelocidad() {
		Integer media=0;
		for(int i=0; i<miembros.size(); i++) {
			media+=miembros.get(i).getVelocidad()-1;
		}
		
		return media/miembros.size();
	}
	
}
