package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;
import org.sfaci.gestionanimales.gui.Ventana;
import org.sfaci.gestionanimales.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Santi on 30/10/15.
 */
public class VentanaController implements ActionListener {

    private VentanaModel model;
    private Ventana view;

    public VentanaController(VentanaModel model, Ventana view) {
        this.model = model;
        this.view = view;
        anadirListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();

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

                Animal animal = new Animal();
                animal.setNombre(view.tfNombre.getText());
                animal.setRaza(view.tfRaza.getText());
                animal.setCaracteristicas(view.tfCaracteristicas.getText());
                animal.setPeso(Float.parseFloat(view.tfPeso.getText()));

                model.guardar(animal);

                view.btGuardar.setEnabled(false);
                break;
            case "Modificar":
                break;
            case "Cancelar":
                break;
            case "Eliminar":
                break;
            default:
                break;
        }
    }

    private void anadirListeners(ActionListener listener) {

        view.btNuevo.addActionListener(listener);
        view.btGuardar.addActionListener(listener);
    }
}
