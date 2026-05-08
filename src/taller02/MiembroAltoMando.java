// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;

public class MiembroAltoMando {

    private int numero;
    private String nombre;
    private ArrayList<Pokemon> pokemones;

    public MiembroAltoMando(int numero, String nombre, ArrayList<Pokemon> pokemones) {
        this.numero    = numero;
        this.nombre    = nombre;
        this.pokemones = pokemones;
    }

    public int    getNumero() { 
    	return numero; }
    public String getNombre() { 
    	return nombre; }
    public ArrayList<Pokemon> getPokemones() { 
    	return pokemones; }

    public Pokemon getPrimerPokemonVivo() {
        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getEstado().equalsIgnoreCase("Vivo")) {
                return pokemones.get(i);
            }
        }
        return null;
    }
}