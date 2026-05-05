// Dylan Nicolás Ordóñez Miranda
// RUT: 21.387.801-8
// Ingeniería Civil Industrial
package taller02;

import java.util.ArrayList;

public class Jugador {

    private String nombreCuenta;
    private String medallas;
    private ArrayList<Pokemon> pokemones;

    public Jugador(String nombreCuenta, String medallas) {
        this.nombreCuenta = nombreCuenta;
        this.medallas     = medallas;
        this.pokemones    = new ArrayList<>();
    }

    public String getNombreCuenta() {
    	return nombreCuenta; }
    public String getMedallas() { 
    	return medallas; }
    public ArrayList<Pokemon> getPokemones() { 
    	return pokemones; }

    public boolean agregarPokemon(Pokemon p) {
    	for (int i = 0; i < pokemones.size(); i++) {
    	    if (pokemones.get(i).getNombre().equalsIgnoreCase(p.getNombre())) {
    	        return false;
            }
        }
        pokemones.add(p);
        return true;
    }
}