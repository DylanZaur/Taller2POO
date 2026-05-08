// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
	static ArrayList<Pokemon>          pokedex   = new ArrayList<>();
	static ArrayList<Habitat>          habitats  = new ArrayList<>();
	static ArrayList<Gimnasio>         gimnasios = new ArrayList<>();
	static ArrayList<MiembroAltoMando> altoMando = new ArrayList<>();
	static Jugador jugador = null;

	public static void main(String[] args) {
		Scanner menu = new Scanner(System.in);
		int opcionMenuPrincipal;

		pokedex   = GestorDeArchivos.cargarPokedex("Pokedex.txt");
		habitats  = GestorDeArchivos.cargarHabitats("Habitats.txt", pokedex);
		gimnasios = GestorDeArchivos.cargarGimnasios("Gimnasios.txt", pokedex);
		altoMando = GestorDeArchivos.cargarAltoMando("Alto Mando.txt", pokedex);

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
					retarGimnasio(menu);
					break;
				case 5:
					desafiarAltoMando(menu);
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

			System.out.println("1) Cambiar Pokemon.");
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

	static void retarGimnasio(Scanner menu) {
		System.out.println("Gimnasios");
		for (int i = 0; i < gimnasios.size(); i++) {
			System.out.println(gimnasios.get(i).getNumero() + ") "
					+ gimnasios.get(i).getLider()
					+ " | Estado: " + gimnasios.get(i).getEstado());
		}

		System.out.print("Ingrese el numero del gimnasio a retar (0 para volver): ");
		int opcion = Integer.parseInt(menu.nextLine());

		if (opcion == 0) 
			return;

		if (opcion < 1 || opcion > gimnasios.size()) {
			System.out.println("Gimnasio invalido.");
			return;
		}

		Gimnasio gimnasio = gimnasios.get(opcion - 1);

		if (gimnasio.getEstado().equalsIgnoreCase("Derrotado")) {
			System.out.println("Ya derrotaste a este gimnasio!");
			return;
		}

		if (opcion > 1 && !gimnasios.get(opcion - 2).getEstado().equalsIgnoreCase("Derrotado")) {
			System.out.println("Debes derrotar primero el gimnasio " + (opcion - 1) + "!");
			return;
		}

		Pokemon pokemonJugador = obtenerPrimerVivo(jugador.getEquipo());
		if (pokemonJugador == null) {
			System.out.println("No tienes Pokemon vivos en tu equipo. Cura primero!");
			return;
		}

		System.out.println("Desafiando a " + gimnasio.getLider() + "!!");

		Pokemon pokemonRival = gimnasio.getPrimerPokemonVivo();
		System.out.println(gimnasio.getLider() + " saca a " + pokemonRival.getNombre() + "!");
		System.out.println(jugador.getNombreCuenta() + " saca a " + pokemonJugador.getNombre() + "!");

		boolean gano = combatir(menu, pokemonJugador, gimnasio.getLider(), gimnasio.getPokemones());

		if (gano) {
			gimnasio.setEstado("Derrotado");
			jugador.addMedalla(gimnasio.getLider());
			System.out.println("Has derrotado a " + gimnasio.getLider() + "! Obtuviste una medalla!");
			System.out.println("Medallas: " + jugador.getMedallas());
		}
	}

	static void desafiarAltoMando(Scanner menu) {
		if (jugador.getCantMedallas() < 8) {
			System.out.println("Necesitas las 8 medallas para desafiar al Alto Mando!");
			System.out.println("Medallas actuales: " + jugador.getCantMedallas());
			return;
		}

		Pokemon primerVivo = obtenerPrimerVivo(jugador.getEquipo());
		if (primerVivo == null) {
			System.out.println("No tienes Pokemon vivos en tu equipo. Cura primero!");
			return;
		}

		System.out.println("Desafiando al Alto Mando!!");
		System.out.println("No podras volver al menu hasta ser derrotado o rendirte.");

		for (int i = 0; i < altoMando.size(); i++) {
			ArrayList<Pokemon> pokemones = altoMando.get(i).getPokemones();
			for (int j = 0; j < pokemones.size(); j++) {
				pokemones.get(j).setEstado("Vivo");
			}
		}

		Pokemon pokemonJugador = primerVivo;

		for (int i = 0; i < altoMando.size(); i++) {
			MiembroAltoMando miembro = altoMando.get(i);

			System.out.println("Desafiando a " + miembro.getNombre() + "!!");

			Pokemon pokemonRival = miembro.getPrimerPokemonVivo();
			System.out.println(miembro.getNombre() + " saca a " + pokemonRival.getNombre() + "!");
			System.out.println(jugador.getNombreCuenta() + " saca a " + pokemonJugador.getNombre() + "!");

			boolean gano = combatir(menu, pokemonJugador, miembro.getNombre(), miembro.getPokemones());

			if (!gano) {
				System.out.println("Volviendo al menu...");
				return;
			}

			System.out.println("Has derrotado a " + miembro.getNombre() + "!");

			pokemonJugador = obtenerPrimerVivo(jugador.getEquipo());
			if (pokemonJugador == null) {
				System.out.println("Te has quedado sin Pokemon vivos!");
				return;
			}
		}

		System.out.println("FELICIDADES " + jugador.getNombreCuenta().toUpperCase() + "!!");
		System.out.println("Has derrotado al Alto Mando y te has coronado CAMPEON!");
	}

	static boolean combatir(Scanner menu, Pokemon pokemonJugador,
			String nombreRival, ArrayList<Pokemon> pokemonesRival) {

		Pokemon pokemonRival = obtenerPrimerVivo(pokemonesRival);

		while (pokemonRival != null) {
			System.out.println("Que deseas hacer?");
			System.out.println("1) Atacar");
			System.out.println("2) Cambiar de pokemon");
			System.out.println("3) Rendirse");
			System.out.print("Ingrese Opcion: ");
			int opcion = Integer.parseInt(menu.nextLine());

			if (opcion == 1) {
				int statsJugador = pokemonJugador.getSumaStats();
				int statsRival   = pokemonRival.getSumaStats();

				System.out.println(pokemonJugador.getNombre() + " -> " + statsJugador + " puntos");
				System.out.println(pokemonRival.getNombre() + " -> " + statsRival + " puntos");

				double multiplicador = TablaTipos.getMultiplicador(
						pokemonJugador.getTipo(), pokemonRival.getTipo());

				int statsJugadorFinal = statsJugador;

				if (multiplicador == 2.0) {
					System.out.println(pokemonJugador.getNombre() + " es efectivo contra " + pokemonRival.getNombre() + "!");
					statsJugadorFinal = statsJugador * 2;
					System.out.println("Nuevo puntaje:");
					System.out.println(pokemonJugador.getNombre() + " -> " + statsJugadorFinal + " puntos");
					System.out.println(pokemonRival.getNombre() + " -> " + statsRival + " puntos");
				} else if (multiplicador == 0.5) {
					System.out.println(pokemonJugador.getNombre() + " no es efectivo contra " + pokemonRival.getNombre() + "!");
					statsJugadorFinal = statsJugador / 2;
					System.out.println("Nuevo puntaje:");
					System.out.println(pokemonJugador.getNombre() + " -> " + statsJugadorFinal + " puntos");
					System.out.println(pokemonRival.getNombre() + " -> " + statsRival + " puntos");
				} else if (multiplicador == 0.0) {
					System.out.println(pokemonJugador.getNombre() + " no tiene efecto contra " + pokemonRival.getNombre() + "!");
					statsJugadorFinal = 0;
					System.out.println("Nuevo puntaje:");
					System.out.println(pokemonJugador.getNombre() + " -> " + statsJugadorFinal + " puntos");
					System.out.println(pokemonRival.getNombre() + " -> " + statsRival + " puntos");
				}

				if (statsJugadorFinal >= statsRival) {
					System.out.println("Ha ganado " + pokemonJugador.getNombre() + "! " + pokemonRival.getNombre() + " ha sido derrotado...");
					pokemonRival.setEstado("Debilitado");
					pokemonRival = obtenerPrimerVivo(pokemonesRival);
					if (pokemonRival != null) {
						System.out.println(nombreRival + " saca a " + pokemonRival.getNombre() + "!");
					}
				} else {
					System.out.println("Ha ganado " + pokemonRival.getNombre() + "! " + pokemonJugador.getNombre() + " ha sido derrotado...");
					pokemonJugador.setEstado("Debilitado");
					pokemonJugador = obtenerPrimerVivo(jugador.getEquipo());
					if (pokemonJugador == null) {
						System.out.println("Te has quedado sin pokemons en tu equipo!");
						return false;
					}
					System.out.println(jugador.getNombreCuenta() + " saca a " + pokemonJugador.getNombre() + "!");
				}

			} else if (opcion == 2) {
				ArrayList<Pokemon> equipo = jugador.getEquipo();
				System.out.println("Elige un Pokemon:");
				for (int i = 0; i < equipo.size(); i++) {
					System.out.println((i + 1) + ") " + equipo.get(i).getNombre()
							+ " | Estado: " + equipo.get(i).getEstado());
				}
				System.out.print("Ingrese Opcion: ");
				int numPoke = Integer.parseInt(menu.nextLine());

				if (numPoke < 1 || numPoke > equipo.size()) {
					System.out.println("Opcion invalida.");
				} else if (equipo.get(numPoke - 1).getEstado().equalsIgnoreCase("Debilitado")) {
					System.out.println("Ese Pokemon esta Debilitado, elige otro.");
				} else {
					pokemonJugador = equipo.get(numPoke - 1);
					System.out.println(jugador.getNombreCuenta() + " saca a " + pokemonJugador.getNombre() + "!");
				}

			} else if (opcion == 3) {
				System.out.println("Te has rendido.");
				return false;
			}
		}

		return true;
	}

	static Pokemon obtenerPrimerVivo(ArrayList<Pokemon> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEstado().equalsIgnoreCase("Vivo")) {
				return lista.get(i);
			}
		}
		return null;
	}
}