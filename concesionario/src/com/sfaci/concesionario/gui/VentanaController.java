package com.sfaci.concesionario.gui;

import com.sfaci.concesionario.base.Coche;
import com.sfaci.concesionario.util.Util;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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

                Coche nuevoCoche = new Coche();
                nuevoCoche.setMatricula(view.tfMatricula.getText());
                nuevoCoche.setModelo(view.tfModelo.getText());
                nuevoCoche.setPotencia(Float.parseFloat(view.tfPotencia.getText()));
                nuevoCoche.setFechaCompra(fecha);
                nuevoCoche.setHibrido(view.cbHibrido.isSelected());
                if (view.rbDiesel.isSelected())
                    nuevoCoche.setCombustible(Coche.Combustible.DIESEL);
                else if (view.rbGasolina.isSelected())
                    nuevoCoche.setCombustible(Coche.Combustible.GASOLINA);

                switch (accion) {
                    case NUEVO:
                        model.registrarCoche(nuevoCoche);
                        break;
                    case MODIFICAR:
                        String matricula = view.lCoches.getSelectedValue().toString();
                        model.modificarCoche(nuevoCoche, matricula);
                        break;
                    default:
                }
                try {
                    model.guardarAFichero();
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error al escribir en disco", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                refrescarLista();
                view.lbEstado.setText("Guardado");
                modoEdicion(false);
                break;
            case "Cancelar":
                modoEdicion(false);
                view.lbEstado.setText("Cancelado");
                break;
            case "Eliminar":
                // 1. Preguntar al usuario
                if (JOptionPane.showConfirmDialog(null,
                        "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.NO_OPTION)
                    return;

                // 2. Eliminar
                Coche coche = (Coche) view.lCoches.getSelectedValue();
                model.eliminarCoche(coche.getMatricula());

                // 3. Refrescar
                refrescarLista();

                // 4. Guardar cambios en disco
                try {
                    model.guardarAFichero();
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error al escribir en disco", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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

    /*
    Refresca la lista de coches de la ventana
     */
    private void refrescarLista() {

        view.dlmCoches.clear();
        for (Coche coche : model.obtenerCoches()) {
            view.dlmCoches.addElement(coche);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        ArrayList<Coche> coches;
        String cadenaBusqueda = view.tfBusqueda.getText();

        if (cadenaBusqueda.length() < 3) {
            coches = new ArrayList<>(model.obtenerCoches());
        }
        else {
            coches = model.obtenerCoches(cadenaBusqueda);
        }

        view.dlmCoches.clear();
        for (Coche coche : coches)
            view.dlmCoches.addElement(coche);
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
