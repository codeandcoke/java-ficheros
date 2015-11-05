package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;
import org.sfaci.gestionanimales.gui.Ventana;
import org.sfaci.gestionanimales.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador para la ventana
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class VentanaController implements ActionListener {

    private VentanaModel model;
    private Ventana view;

    private int posicion;

    public VentanaController(VentanaModel model, Ventana view) {
        this.model = model;
        this.view = view;
        anadirListeners(this);

        posicion = 0;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();
        Animal animal = null;

        switch (actionCommand) {
            case "Nuevo":
                view.tfNombre.setText("");
                view.tfNombre.setEditable(true);
                view.tfCaracteristicas.setText("");
                view.tfCaracteristicas.setEditable(true);
                view.tfPeso.setText("");
                view.tfPeso.setEditable(true);
                view.tfRaza.setText("");
                view.tfRaza.setEditable(true);

                view.btGuardar.setEnabled(true);
                break;
            case "Guardar":

                if (view.tfNombre.getText().equals("")) {
                    Util.mensajeError("El nombre es un campo obligatorio", "Nuevo Animal");
                    return;
                }

                animal = new Animal();
                animal.setNombre(view.tfNombre.getText());
                animal.setRaza(view.tfRaza.getText());
                animal.setCaracteristicas(view.tfCaracteristicas.getText());
                animal.setPeso(Float.parseFloat(view.tfPeso.getText()));

                model.guardar(animal);

                view.btGuardar.setEnabled(false);
                break;
            case "Modificar":
                animal = new Animal();
                animal.setNombre(view.tfNombre.getText());
                animal.setRaza(view.tfRaza.getText());
                animal.setCaracteristicas(view.tfCaracteristicas.getText());
                animal.setPeso(Float.parseFloat(view.tfPeso.getText()));

                model.modificar(animal);
                break;
            case "Cancelar":
                view.tfNombre.setEditable(false);
                view.tfCaracteristicas.setEditable(false);
                view.tfPeso.setEditable(false);
                view.tfRaza.setEditable(false);

                animal = model.getActual();
                cargar(animal);

                view.btGuardar.setEnabled(false);
                break;
            case "Eliminar":
                if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    return;

                model.eliminar();
                animal = model.getActual();
                cargar(animal);
                break;
            case "Primero":
                animal = model.getPrimero();
                cargar(animal);
                break;
            case "Anterior":
                animal = model.getAnterior();
                cargar(animal);
                break;
            case "Siguiente":
                animal = model.getSiguiente();
                cargar(animal);
                break;
            case "Ultimo":
                animal = model.getUltimo();
                cargar(animal);
                break;
            default:
                break;
        }
    }

    /**
     * Carga los datos de un animal en la vista
     * @param animal
     */
    private void cargar(Animal animal) {
        if (animal == null)
            return;

        view.tfNombre.setText(animal.getNombre());
        view.tfCaracteristicas.setText(animal.getCaracteristicas());
        view.tfRaza.setText(animal.getRaza());
        view.tfPeso.setText(String.valueOf(animal.getPeso()));
    }

    private void anadirListeners(ActionListener listener) {

        view.btNuevo.addActionListener(listener);
        view.btGuardar.addActionListener(listener);
        view.btModificar.addActionListener(listener);
        view.btEliminar.addActionListener(listener);
        view.btPrimero.addActionListener(listener);
        view.btAnterior.addActionListener(listener);
        view.btSiguiente.addActionListener(listener);
        view.btUltimo.addActionListener(listener);
    }
}
