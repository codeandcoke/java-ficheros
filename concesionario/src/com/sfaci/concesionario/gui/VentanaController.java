package com.sfaci.concesionario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controlador de la ventana Ventana
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class VentanaController implements ActionListener,
        KeyListener {

    private enum Accion {
        NUEVO, MODIFICAR;
    }

    private Ventana view;
    private VentanaModel model;

    private Accion accion;

    public VentanaController(Ventana view, VentanaModel model) {

        this.view = view;
        this.model = model;

        addActionListeners();
        addKeyListener();
    }

    private void addActionListeners() {

        view.btNuevo.addActionListener(this);
        view.btCancelar.addActionListener(this);
        view.btEliminar.addActionListener(this);
        view.btGuardar.addActionListener(this);
        view.btModificar.addActionListener(this);
    }

    private void addKeyListener() {

        view.tfBusqueda.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String comando = actionEvent.getActionCommand();

        switch (comando) {
            case "Nuevo":
                accion = Accion.NUEVO;
                modoEdicion(true);
                limpiarGUI();
                view.lbEstado.setText("Nuevo");

                break;
            case "Modificar":
                accion = Accion.MODIFICAR;
                modoEdicion(true);
                view.lbEstado.setText("Modificar");

                break;
            case "Guardar":
                modoEdicion(false);
                switch (accion) {
                    case NUEVO:

                        break;
                    case MODIFICAR:

                        break;
                    default:
                }
                view.lbEstado.setText("Guardado");
                break;
            case "Cancelar":
                modoEdicion(false);
                view.lbEstado.setText("Cancelado");
                break;
            case "Eliminar":

                break;
            default:
        }
    }

    /*
    Limpia las cajas de texto de la GUI
     */
    private void limpiarGUI() {

        view.tfMatricula.setText("");
        view.tfModelo.setText("");
        view.tfPotencia.setText("");
        view.tfCompra.setText("");
        view.cbHibrido.setSelected(false);
    }

    /**
     * Activa/desactiva el modo edición de la GUI
     * @param edicion Si vale true se activará el modo edición. En caso contrario se activa el modo 'sólo ver'
     */
    private void modoEdicion(boolean edicion) {

        view.btNuevo.setEnabled(!edicion);
        view.btGuardar.setEnabled(edicion);
        view.btEliminar.setEnabled(!edicion);
        view.btModificar.setEnabled(!edicion);
        view.btCancelar.setEnabled(edicion);

        view.tfMatricula.setEditable(edicion);
        view.tfModelo.setEditable(edicion);
        view.tfPotencia.setEditable(edicion);
        view.tfCompra.setEditable(edicion);

        view.tfBusqueda.setEnabled(!edicion);
        view.lCoches.setEnabled(!edicion);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
