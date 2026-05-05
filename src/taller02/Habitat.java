// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;
import java.util.Random;

public class Habitat {

    private String nombre;
    private ArrayList<Pokemon> pokemones;

    public Habitat(String nombre) {
        this.nombre    = nombre;
        this.pokemones = new ArrayList<>();
    }


    public String getNombre() { 
    	return nombre; }
    public ArrayList<Pokemon> getPokemones() { 
    	return pokemones; }

    public void agregarPokemon(Pokemon p) {
        pokemones.add(p);
    }

    public Pokemon generarPokemonAleatorio() {
        Random pokerandom= new Random();
        double valor = pokerandom.nextDouble();
        double acumulado = 0.0;

        for (int i = 0; i < pokemones.size(); i++) {
            Pokemon p = pokemones.get(i);
            acumulado += p.getPorcentajeAparicion();
            if (valor <= acumulado) {
                return new Pokemon(p);
            }
        }
        return new Pokemon(pokemones.get(pokemones.size() - 1));
    }
}