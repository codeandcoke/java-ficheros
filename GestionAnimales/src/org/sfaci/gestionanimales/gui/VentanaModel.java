package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;

import java.util.ArrayList;

/**
 * Created by Santi on 30/10/15.
 */
public class VentanaModel {

    private ArrayList<Animal> listaAnimales;
    private int posicion;

    public VentanaModel() {
        listaAnimales = new ArrayList<>();
        posicion = 0;
    }

    public void guardar(Animal animal) {

        listaAnimales.add(animal);
    }

    public void modificar(Animal animalModificado) {

        Animal animal = listaAnimales.get(posicion);
        animal.setNombre(animalModificado.getNombre());
        animal.setCaracteristicas(animalModificado.getCaracteristicas());
        animal.setRaza(animalModificado.getRaza());
        animal.setPeso(animalModificado.getPeso());
    }

    public void eliminar() {
        listaAnimales.remove(posicion);
    }

    public Animal getActual() {

        return listaAnimales.get(posicion);
    }

    public Animal getPrimero() {

        posicion = 0;
        return listaAnimales.get(posicion);
    }

    public Animal getAnterior() {

        if (posicion == 0)
            return null;

        posicion--;
        return listaAnimales.get(posicion);
    }

    public Animal getSiguiente() {

        if (posicion == listaAnimales.size() - 1)
            return null;

        posicion++;
        return listaAnimales.get(posicion);
    }

    public Animal getUltimo() {

        posicion = listaAnimales.size() - 1;
        return listaAnimales.get(posicion);
    }
}
