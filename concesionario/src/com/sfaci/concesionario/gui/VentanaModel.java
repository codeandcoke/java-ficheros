package com.sfaci.concesionario.gui;

import com.sfaci.concesionario.base.Coche;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Modelo para la ventana de la aplicación
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class VentanaModel {

    private HashMap<String, Coche> coches;

    public VentanaModel() {
        coches = new HashMap<>();
    }

    /**
     * Registra un nuevo coche
     */
    public void registrarCoche() {

    }

    /**
     * Elimina un coche
     */
    public void eliminarCoche() {

    }

    /**
     * Obtiene un coche a partir de su matrícula
     * @param matricula
     * @return
     */
    public Coche obtenerCoche(String matricula) {

        return null;
    }

    /**
     * Obtiene una lista con todos los coches
     * @return
     */
    public ArrayList<Coche> obtenerCoches() {

        return null;
    }
}
