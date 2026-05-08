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

    public ArrayList<Pokemon> getEquipo() {
        ArrayList<Pokemon> equipo = new ArrayList<>();
        int limite = Math.min(6, pokemones.size());
        for (int i = 0; i < limite; i++) {
            equipo.add(pokemones.get(i));
        }
        return equipo;
    }

    public void curarTodos() {
        for (int i = 0; i < pokemones.size(); i++) {
            pokemones.get(i).setEstado("Vivo");
        }
        System.out.println("Tu equipo se ha recuperado!");
    }

    public boolean intercambiarPokemon(int num1, int num2) {
        int i = num1 - 1;
        int j = num2 - 1;

        if (i < 0 || j < 0 || i >= pokemones.size() || j >= pokemones.size()) {
            return false;
        }

        Pokemon temp = pokemones.get(i);
        pokemones.set(i, pokemones.get(j));
        pokemones.set(j, temp);
        return true;
    }

    public void addMedalla(String nombreLider) {
        if (medallas.equals("ninguna")) {
            medallas = nombreLider;
        } else {
            medallas = medallas + "," + nombreLider;
        }
    }

    public int getCantMedallas() {
        if (medallas.equals("ninguna")) {
            return 0;
        }
        return medallas.split(",").length;
    }
}