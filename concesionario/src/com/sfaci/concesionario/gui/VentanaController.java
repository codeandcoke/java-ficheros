package com.sfaci.concesionario.gui;

import com.sfaci.concesionario.base.Coche;
import com.sfaci.concesionario.util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Date;

/**
 * Controlador de la ventana Ventana
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class VentanaController implements ActionListener,
        KeyListener, ListSelectionListener {

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
        addSelectionListeners();
        addKeyListener();
    }

    private void addActionListeners() {

        view.btNuevo.addActionListener(this);
        view.btCancelar.addActionListener(this);
        view.btEliminar.addActionListener(this);
        view.btGuardar.addActionListener(this);
        view.btModificar.addActionListener(this);
    }

    private void addSelectionListeners() {
        view.lCoches.addListSelectionListener(this);
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
                switch (accion) {
                    case NUEVO:

                        if (view.tfMatricula.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Matricula obligatoria",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (view.bgCombustible.isSelected(null)) {
                            JOptionPane.showMessageDialog(null, "Selecciona combustible",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Date fecha;
                        try {
                            fecha = Util.parseFecha(view.tfCompra.getText());
                        } catch (ParseException pe) {
                            JOptionPane.showMessageDialog(null, "Fecha no válida",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Coche coche = new Coche();
                        coche.setMatricula(view.tfMatricula.getText());
                        coche.setModelo(view.tfModelo.getText());
                        coche.setPotencia(Float.parseFloat(view.tfPotencia.getText()));
                        coche.setFechaCompra(fecha);
                        coche.setHibrido(view.cbHibrido.isSelected());
                        if (view.rbDiesel.isSelected())
                            coche.setCombustible(Coche.Combustible.DIESEL);
                        else if (view.rbGasolina.isSelected())
                            coche.setCombustible(Coche.Combustible.GASOLINA);

                        model.registrarCoche(coche);
                        view.dlmCoches.addElement(coche);

                        break;
                    case MODIFICAR:

                        break;
                    default:
                }
                view.lbEstado.setText("Guardado");
                modoEdicion(false);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {

        Coche coche = (Coche) view.lCoches.getSelectedValue();
        if (coche == null)
            return;

        view.tfMatricula.setText(coche.getMatricula());
        view.tfModelo.setText(coche.getModelo());
        view.tfCompra.setText(Util.formatFecha(coche.getFechaCompra()));
        view.tfPotencia.setText(String.valueOf(coche.getPotencia()));
        view.cbHibrido.setSelected(coche.isHibrido());
        if (coche.getCombustible() == Coche.Combustible.DIESEL)
            view.rbDiesel.setSelected(true);
        else
            view.rbGasolina.setSelected(true);
    }
}
