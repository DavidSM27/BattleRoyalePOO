package defaultPackage;

import java.util.Scanner;


public class Partida {

	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Partida partida = new Partida();
		
		partida.StartGame();
	}
	
	public void StartGame(){
		SetRules();
	}
	
	public void SetRules() {
		System.out.println("¡BIENVENIDO AL BATTLE ROYALE!");
		System.out.print("Cuantas personas van a jugar: ");
	}
	
}	