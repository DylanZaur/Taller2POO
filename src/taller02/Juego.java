// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
	static ArrayList<Pokemon> pokedex  = new ArrayList<>();
	static ArrayList<Habitat>  habitats = new ArrayList<>();
	static Jugador jugador = null;

	public static void main(String[] args) {
		Scanner menu = new Scanner(System.in);
		int opcionMenuPrincipal;

		pokedex  = GestorDeArchivos.cargarPokedex("Pokedex.txt");
		habitats = GestorDeArchivos.cargarHabitats("Habitats.txt", pokedex);

		do {
			System.out.println("Bienvenid@ al juego");
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva Partida.");
			System.out.println("3) Salir.");
			System.out.print("> ");
			opcionMenuPrincipal = Integer.parseInt(menu.nextLine());

			switch (opcionMenuPrincipal) {
				case 1:
					jugador = GestorDeArchivos.cargarRegistros("Registros.txt", pokedex);
				    if (jugador == null) 
				    	break; 
				    System.out.println("Bienvenid@ de vuelta, " + jugador.getNombreCuenta() + "!");
				    menuSecundario(menu);
				    break;

				case 2:
					System.out.println("Ingrese Apodo:");
					System.out.print("> ");
					String apodo = menu.nextLine();
					jugador = new Jugador(apodo, "ninguno");
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					System.out.println("Bienvenid@, " + apodo + "!");
					menuSecundario(menu);
					break;

				case 3:
					System.out.println("Saliste.");
					break;
			}

		} while (opcionMenuPrincipal < 1 || opcionMenuPrincipal > 3);
		menu.close();
	}

	static void menuSecundario(Scanner menu) {
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
			opcionMenuSecundario = Integer.parseInt(menu.nextLine());

			switch (opcionMenuSecundario) {
				case 1:
					break;
				case 2:
					salirACapturar(menu);
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
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					break;
				case 8:
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					System.out.println("Gracias por jugar.");
					break;
			}

		} while (opcionMenuSecundario < 1 || opcionMenuSecundario > 7);
	}

	static void salirACapturar(Scanner menu) {
		System.out.println("Zonas disponibles");
		for (int i = 0; i < habitats.size(); i++) {
			System.out.println((i + 1) + ") " + habitats.get(i).getNombre());
		}
		System.out.print("> ");
		int opcionZona = Integer.parseInt(menu.nextLine());

		if (opcionZona < 1 || opcionZona > habitats.size()) {
			System.out.println("Zona invalida.");
			return;
		}

		Habitat zonaElegida = habitats.get(opcionZona - 1);

		if (zonaElegida.getPokemones().isEmpty()) {
			System.out.println("No hay Pokemon en esta zona.");
			return;
		}

		Pokemon aparecio = zonaElegida.generarPokemonAleatorio();
		System.out.println("Oh!! Ha aparecido un increible " + aparecio.getNombre() + "!!");

		if (jugador.agregarPokemon(aparecio) == false) {
			System.out.println("Ya tienes este Pokemon.");
			return;
		}

		System.out.println("1) Capturar.");
		System.out.println("2) Huir.");
		System.out.print("> ");
		int opcion = Integer.parseInt(menu.nextLine());

		if (opcion == 1) {
			jugador.agregarPokemon(aparecio);
			System.out.println("¡" + aparecio.getNombre() + " fue capturado!");
		} else {
			jugador.getPokemones().remove(aparecio);
			System.out.println("Huiste exitosamente.");
		}
	}
}