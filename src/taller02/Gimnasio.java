// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;

//Clase para los lideres de gimnasio y obtener sus nombres y pokemon.

public class Gimnasio {

    private int numero;
    private String lider;
    private String estado;
    private ArrayList<Pokemon> pokemones;

    public Gimnasio(int numero, String lider, String estado, ArrayList<Pokemon> pokemones) {
        this.numero    = numero;
        this.lider     = lider;
        this.estado    = estado;
        this.pokemones = pokemones;
    }

    public int    getNumero() { 
    	return numero; }
    public String getLider() { 
    	return lider; }
    public String getEstado() { 
    	return estado; }
    public ArrayList<Pokemon> getPokemones() { 
    	return pokemones; }

    public void setEstado(String estado) { 
    	this.estado = estado; }

    public Pokemon getPrimerPokemonVivo() {
        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getEstado().equalsIgnoreCase("Vivo")) {
                return pokemones.get(i);
            }
        }
        return null;
    }
}