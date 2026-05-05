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
                    p[0],                        // nombre
                    p[1],                        // habitat
                    Double.parseDouble(p[2]),    // porcentajeAparicion
                    Integer.parseInt(p[3]),      // vida
                    Integer.parseInt(p[4]),      // ataque
                    Integer.parseInt(p[5]),      // defensa
                    Integer.parseInt(p[6]),      // ataqueEspecial
                    Integer.parseInt(p[7]),      // defensaEspecial
                    Integer.parseInt(p[8]),      // velocidad
                    p[9]                         // tipo
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