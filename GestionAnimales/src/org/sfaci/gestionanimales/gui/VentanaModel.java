package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;

import java.util.ArrayList;

/**
 * Created by Santi on 30/10/15.
 */
public class VentanaModel {

    private ArrayList<Animal> listaAnimales;

    public VentanaModel() {
        listaAnimales = new ArrayList<>();
    }

    public void guardar(Animal animal) {

        listaAnimales.add(animal);
    }
}
