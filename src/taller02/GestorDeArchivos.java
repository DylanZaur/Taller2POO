// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDeArchivos {
	
    public static ArrayList<Pokemon> cargarPokedex(String rutaArchivo) {
        ArrayList<Pokemon> pokedex = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(rutaArchivo));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) continue;

                String[] p = linea.split(";");
                if (p.length < 10) continue;

                pokedex.add(new Pokemon(
                    p[0],
                    p[1],
                    Double.parseDouble(p[2]),
                    Integer.parseInt(p[3]),
                    Integer.parseInt(p[4]),
                    Integer.parseInt(p[5]),
                    Integer.parseInt(p[6]),
                    Integer.parseInt(p[7]),
                    Integer.parseInt(p[8]),
                    p[9]
                ));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer " + rutaArchivo);
        }

        return pokedex;
    }

    public static ArrayList<Habitat> cargarHabitats(String rutaArchivo, ArrayList<Pokemon> pokedex) {
        ArrayList<Habitat> habitats = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(rutaArchivo));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) continue;

                Habitat h = new Habitat(linea);

                for (int i = 0; i < pokedex.size(); i++) {
                    if (pokedex.get(i).getHabitat().equalsIgnoreCase(linea)) {
                        h.agregarPokemon(pokedex.get(i));
                    }
                }

                habitats.add(h);
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer " + rutaArchivo);
        }

        return habitats;
    }

    public static ArrayList<Gimnasio> cargarGimnasios(String rutaArchivo, ArrayList<Pokemon> pokedex) {
        ArrayList<Gimnasio> gimnasios = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(rutaArchivo));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) continue;

                String[] p = linea.split(";");
                if (p.length < 4) continue;

                int numero       = Integer.parseInt(p[0]);
                String lider     = p[1];
                String estado    = p[2];
                int cantPokemons = Integer.parseInt(p[3]);

                ArrayList<Pokemon> pokemonesGimnasio = new ArrayList<>();

                for (int i = 4; i < 4 + cantPokemons && i < p.length; i++) {
                    boolean encontrado = false;
                    for (int j = 0; j < pokedex.size(); j++) {
                        if (pokedex.get(j).getNombre().equalsIgnoreCase(p[i])) {
                            pokemonesGimnasio.add(new Pokemon(pokedex.get(j)));
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        pokemonesGimnasio.add(new Pokemon(p[i]));
                    }
                }

                gimnasios.add(new Gimnasio(numero, lider, estado, pokemonesGimnasio));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer " + rutaArchivo);
        }

        return gimnasios;
    }

    public static ArrayList<MiembroAltoMando> cargarAltoMando(String rutaArchivo, ArrayList<Pokemon> pokedex) {
        ArrayList<MiembroAltoMando> altoMando = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(rutaArchivo));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) continue;

                String[] p = linea.split(";");
                if (p.length < 3) continue;

                int numero    = Integer.parseInt(p[0]);
                String nombre = p[1];

                ArrayList<Pokemon> pokemonesMiembro = new ArrayList<>();

                for (int i = 2; i < p.length; i++) {
                    boolean encontrado = false;
                    for (int j = 0; j < pokedex.size(); j++) {
                        if (pokedex.get(j).getNombre().equalsIgnoreCase(p[i])) {
                            pokemonesMiembro.add(new Pokemon(pokedex.get(j)));
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        pokemonesMiembro.add(new Pokemon(p[i]));
                    }
                }

                altoMando.add(new MiembroAltoMando(numero, nombre, pokemonesMiembro));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer " + rutaArchivo);
        }

        return altoMando;
    }

    public static Jugador cargarRegistros(String rutaArchivo, ArrayList<Pokemon> pokedex) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return null;
        }

        if (archivo.length() == 0) {
            System.out.println("No hay partidas guardadas, porfavor cree una.");
            return null;
        }

        Jugador jugador = null;

        try {
            Scanner sc = new Scanner(archivo);
            String[] datos = sc.nextLine().split(";");
            String nombre   = datos[0];
            String medallas;
            if (datos.length > 1) {
                medallas = datos[1];
            } else {
                medallas = "Ninguna";
            }
            jugador = new Jugador(nombre, medallas);

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(";");
                if (partes.length < 2) continue;

                for (int i = 0; i < pokedex.size(); i++) {
                    if (pokedex.get(i).getNombre().equalsIgnoreCase(partes[0])) {
                        Pokemon copia = new Pokemon(pokedex.get(i));
                        copia.setEstado(partes[1]);
                        jugador.agregarPokemon(copia);
                        break;
                    }
                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer " + rutaArchivo);
        }

        return jugador;
    }

    public static void guardarRegistros(String rutaArchivo, Jugador jugador) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false));

            bw.write(jugador.getNombreCuenta() + ";" + jugador.getMedallas());
            bw.newLine();

            for (int i = 0; i < jugador.getPokemones().size(); i++) {
                bw.write(jugador.getPokemones().get(i).getNombre() + ";" + jugador.getPokemones().get(i).getEstado());
                bw.newLine();
            }

            bw.close();
            System.out.println("Partida guardada correctamente.");

        } catch (IOException e) {
            System.out.println("No se pudo guardar " + rutaArchivo);
        }
    }
}