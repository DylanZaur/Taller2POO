// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.Scanner;

public class Juego {

	public static void main(String[] args) {
		Scanner menu= new Scanner(System.in);
		int opcionMenuPrincipal;
		do {
			System.out.println("Bienvenid@ al juego");
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva Partida.");
			System.out.println("3) Salir.");
			System.out.print("> ");
			opcionMenuPrincipal = Integer.parseInt(menu.nextLine());
			
			switch (opcionMenuPrincipal){
				case 1:
					int opcionMenuSecundario;
					do {
						System.out.println("1) Revisar equipo.");
						System.out.println("2) Salir a capturar.");
						System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
						System.out.println("4) Retar un gimnasio.");
						System.out.println("5) Desafío al Alto Mando.");
						System.out.println("6) Curar Pokémon.");
						System.out.println("7) Guardar.");
						System.out.println("8) Guardar y Salir.");
						System.out.print("> ");
						opcionMenuSecundario=Integer.parseInt(menu.nextLine());
						
						switch (opcionMenuSecundario) {
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							break;
						case 6:
							break;
						case 7:
							System.out.println("Partida guardada.");
							break;
							
						case 8:
							System.out.println("Partida guardada.Gracias por jugar.");
							break;
							
						}
					break;
					} while (opcionMenuSecundario < 1 || opcionMenuSecundario > 8);
				case 2:
					System.out.println("Ingrese Apodo:");
					break;
				case 3:
					System.out.println("Saliste");
					break;
			}
				
			
		}while (opcionMenuPrincipal < 1 || opcionMenuPrincipal > 3);
		menu.close();
	} 
}
