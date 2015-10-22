package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Aplicación para la gestión de animales
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Ventana {
    private JPanel panel1;
    private JTextField tfNombre;
    private JTextField tfRaza;
    private JTextField tfCaracteristicas;
    private JTextField tfPeso;
    private JButton btGuardar;
    private JButton btNuevo;
    private JButton btModificar;
    private JButton btEliminar;
    private JButton btAnterior;
    private JButton btSiguiente;
    private JButton btPrimero;
    private JButton btUltimo;

    private List<Animal> listaAnimales;
    private int posicionActual;
    private boolean guardarNuevo;

    public Ventana() {

        listaAnimales = new ArrayList<Animal>();
        posicionActual = 0;

        btNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevo();
            }
        });
        btGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                guardar();
            }
        });

        btModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar();
            }
        });
        btAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                anterior();
            }
        });
    }

    private void nuevo() {

        modoEdicion();

        tfNombre.setText("");
        tfCaracteristicas.setText("");
        tfPeso.setText("");
        tfRaza.setText("");

        guardarNuevo = true;
    }

    private void modificar() {

        modoEdicion();
        guardarNuevo = false;
    }

    private void guardar() {
        if (tfNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "El nombre es un campo obligatorio",
                    "Guardar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tfRaza.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "La raza es un campo obligatorio",
                    "Guardar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tfPeso.getText().equals(""))
            tfPeso.setText("0");

        if (guardarNuevo) {
            // Se da de alta uno nuevo
            Animal animal = new Animal();
            animal.setNombre(tfNombre.getText());
            animal.setRaza(tfRaza.getText());
            animal.setCaracteristicas(tfCaracteristicas.getText());
            animal.setPeso(Float.parseFloat(tfPeso.getText()));

            listaAnimales.add(animal);
            posicionActual = listaAnimales.size() - 1;
        }
        else {
            // Se modifica
            if (JOptionPane.showConfirmDialog(null, "¿Está seguro?",
                "Eliminar", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {

                cargarPosicionActual();
                modoVisualizar();
                return;
            }


            Animal animal = listaAnimales.get(posicionActual);
            animal.setNombre(tfNombre.getText());
            animal.setRaza(tfRaza.getText());
            animal.setCaracteristicas(tfCaracteristicas.getText());
            animal.setPeso(Float.parseFloat(tfPeso.getText()));
        }

        modoVisualizar();
    }

    private void modoVisualizar() {

        btGuardar.setEnabled(false);
        btNuevo.setEnabled(true);
        btModificar.setEnabled(true);
        btEliminar.setEnabled(true);
        desactivarCajas();
        comprobarBotonesNavegacion();
    }

    private void modoEdicion() {

        btModificar.setEnabled(false);
        btNuevo.setEnabled(false);
        btModificar.setEnabled(false);
        btGuardar.setEnabled(true);
        btPrimero.setEnabled(false);
        btAnterior.setEnabled(false);
        btSiguiente.setEnabled(false);
        btUltimo.setEnabled(false);

        activarCajas();
    }

    private void cargarPosicionActual() {
        Animal animal = listaAnimales.get(posicionActual);
        tfNombre.setText(animal.getNombre());
        tfRaza.setText(animal.getRaza());
        tfCaracteristicas.setText(animal.getCaracteristicas());
        tfPeso.setText(String.valueOf(animal.getPeso()));
    }

    private void anterior() {

        posicionActual--;
        cargarPosicionActual();

        comprobarBotonesNavegacion();
    }

    private void comprobarBotonesNavegacion() {

        if ((listaAnimales.size() == 0) || (listaAnimales.size() == 1)) {
            btPrimero.setEnabled(false);
            btAnterior.setEnabled(false);
            btSiguiente.setEnabled(false);
            btUltimo.setEnabled(false);
        }
        else {
            if (posicionActual == 0) {
                btPrimero.setEnabled(false);
                btAnterior.setEnabled(false);
                btSiguiente.setEnabled(true);
                btUltimo.setEnabled(true);
            }
            else if (posicionActual == listaAnimales.size() - 1) {
                btPrimero.setEnabled(true);
                btAnterior.setEnabled(true);
                btSiguiente.setEnabled(false);
                btUltimo.setEnabled(false);
            }
            else {
                btPrimero.setEnabled(true);
                btAnterior.setEnabled(true);
                btSiguiente.setEnabled(true);
                btUltimo.setEnabled(true);
            }
        }
    }

    private void activarCajas() {
        tfNombre.setEditable(true);
        tfRaza.setEditable(true);
        tfCaracteristicas.setEditable(true);
        tfPeso.setEditable(true);
    }
    private void desactivarCajas() {
        tfNombre.setEditable(false);
        tfRaza.setEditable(false);
        tfCaracteristicas.setEditable(false);
        tfPeso.setEditable(false);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
