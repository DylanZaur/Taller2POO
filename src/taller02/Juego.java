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
					if (jugador == null) break;
					System.out.println("Bienvenid@ de vuelta, " + jugador.getNombreCuenta() + "!");
					menuSecundario(menu);
					break;

				case 2:
					System.out.println("Ingrese su apodo de jugador:");
					System.out.print("> ");
					String apodo = menu.nextLine();
					jugador = new Jugador(apodo, "ninguno");
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					System.out.println("Bienvenid@, " + apodo + "!");
					menuSecundario(menu);
					break;

				case 3:
					System.out.println("Saliste");
					break;
			}

		} while (opcionMenuPrincipal < 1 || opcionMenuPrincipal > 3);
		menu.close();
	}

	static void menuSecundario(Scanner menu) {
		int opcionMenuSecundario;
		do {
			System.out.println(jugador.getNombreCuenta() + ", que deseas hacer?");
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir.");
			System.out.print("Ingrese Opcion: ");
			opcionMenuSecundario = Integer.parseInt(menu.nextLine());

			switch (opcionMenuSecundario) {
				case 1:
					revisarEquipo();
					break;
				case 2:
					salirACapturar(menu);
					break;
				case 3:
					accesoPC(menu);
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					jugador.curarTodos();
					break;
				case 7:
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					break;
				case 8:
					GestorDeArchivos.guardarRegistros("Registros.txt", jugador);
					System.out.println("Saliste");
					break;
			}

		} while (opcionMenuSecundario != 8);
	}

	static void revisarEquipo() {
		ArrayList<Pokemon> equipo = jugador.getEquipo();

		if (equipo.isEmpty()) {
			System.out.println("No tienes Pokemon en tu equipo.");
			return;
		}

		System.out.println("Tu equipo");
		for (int i = 0; i < equipo.size(); i++) {
			System.out.println((i + 1) + ") " + equipo.get(i).getNombre()
					+ " | Tipo: " + equipo.get(i).getTipo()
					+ " | Stats: " + equipo.get(i).getSumaStats()
					+ " | Estado: " + equipo.get(i).getEstado());
		}
	}

	static void salirACapturar(Scanner menu) {
		int opcionZona;
		do {
			System.out.println("Donde deseas ir a explorar?");
			System.out.println("Zonas disponibles:");
			for (int i = 0; i < habitats.size(); i++) {
				System.out.println((i + 1) + ") " + habitats.get(i).getNombre());
			}
			System.out.println((habitats.size() + 1) + ") Volver al menu.");
			System.out.print("Ingrese Zona: ");
			opcionZona = Integer.parseInt(menu.nextLine());

			if (opcionZona == habitats.size() + 1) {
				return;
			}

			if (opcionZona < 1 || opcionZona > habitats.size()) {
				System.out.println("Zona invalida.");
				continue;
			}

			Habitat zonaElegida = habitats.get(opcionZona - 1);

			if (zonaElegida.getPokemones().isEmpty()) {
				System.out.println("No hay Pokemon en esta zona.");
				continue;
			}

			Pokemon aparecio = zonaElegida.generarPokemonAleatorio();

			System.out.println("Oh!! Ha aparecido un increible " + aparecio.getNombre() + "!!");
			System.out.println("Que deseas hacer?");
			System.out.println("1) Capturar");
			System.out.println("2) Huir");
			System.out.print("Ingrese Opcion: ");
			int opcionCaptura = Integer.parseInt(menu.nextLine());

			if (opcionCaptura == 1) {
				if (!jugador.agregarPokemon(aparecio)) {
					System.out.println("Ya tienes este Pokemon!");
				} else {
					System.out.println(aparecio.getNombre() + " capturado con exito!!");

					if (jugador.getPokemones().size() <= 6) {
						System.out.println(aparecio.getNombre() + " ha sido agregado a tu equipo!");
					} else {
						System.out.println(aparecio.getNombre() + " ha sido enviado al PC.");
					}
				}
			} else {
				System.out.println("Huiste exitosamente.");
			}

		} while (true);
	}

	static void accesoPC(Scanner menu) {
		int opcionPC;
		do {
			ArrayList<Pokemon> todos = jugador.getPokemones();

			if (todos.isEmpty()) {
				System.out.println("No tienes Pokemon capturados.");
				return;
			}

			System.out.println("Todos tus Pokemon");
			for (int i = 0; i < todos.size(); i++) {
				String lugar;
				if (i < 6) {
				    lugar = "Equipo";
				} else {
				    lugar = "PC";
				}
				System.out.println((i + 1) + ") " + todos.get(i).getNombre()
						+ " | Tipo: " + todos.get(i).getTipo()
						+ " | Estado: " + todos.get(i).getEstado()
						+ " [" + lugar + "]");
			}

			System.out.println("\n1) Cambiar Pokemon.");
			System.out.println("2) Salir.");
			System.out.print("Ingrese Opcion: ");
			opcionPC = Integer.parseInt(menu.nextLine());

			if (opcionPC == 1) {
				System.out.print("Ingrese el numero del primer Pokemon: ");
				int num1 = Integer.parseInt(menu.nextLine());
				System.out.print("Ingrese el numero del segundo Pokemon: ");
				int num2 = Integer.parseInt(menu.nextLine());

				if (!jugador.intercambiarPokemon(num1, num2)) {
					System.out.println("Numeros invalidos, intenta de nuevo.");
				} else {
					System.out.println(jugador.getPokemones().get(num2 - 1).getNombre()
							+ " y " + jugador.getPokemones().get(num1 - 1).getNombre()
							+ " han intercambiado posiciones!");
				}
			}

		} while (opcionPC != 2);
	}
}