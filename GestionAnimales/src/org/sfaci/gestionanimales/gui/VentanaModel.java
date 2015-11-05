package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;

import java.util.ArrayList;

/**
 * Modelo para la ventana
 * @author Santiago Faci
 * @curso 2015-2016
 */
public class VentanaModel {

    private ArrayList<Animal> listaAnimales;
    private int posicion;

    public VentanaModel() {
        listaAnimales = new ArrayList<>();
        posicion = 0;
    }

    /**
     * Guarda un animal en la lista
     * @param animal
     */
    public void guardar(Animal animal) {

        listaAnimales.add(animal);
        posicion++;
    }

    /**
     * Modifica los datos del animal actual
     * @param animalModificado
     */
    public void modificar(Animal animalModificado) {

        Animal animal = listaAnimales.get(posicion);
        animal.setNombre(animalModificado.getNombre());
        animal.setCaracteristicas(animalModificado.getCaracteristicas());
        animal.setRaza(animalModificado.getRaza());
        animal.setPeso(animalModificado.getPeso());
    }

    /**
     * Elimina el animal actual
     */
    public void eliminar() {
        listaAnimales.remove(posicion);
    }

    public Animal getActual() {

        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en primera posición en la lista
     * @return
     */
    public Animal getPrimero() {

        posicion = 0;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición anterior a la actual
     * @return
     */
    public Animal getAnterior() {

        if (posicion == 0)
            return null;

        posicion--;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición siguiente a la actual
     * @return
     */
    public Animal getSiguiente() {

        if (posicion == listaAnimales.size() - 1)
            return null;

        posicion++;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la última posición de la lista
     * @return
     */
    public Animal getUltimo() {

        posicion = listaAnimales.size() - 1;
        return listaAnimales.get(posicion);
    }
}
