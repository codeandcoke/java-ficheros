package com.sfaci.concesionario.gui;

import javax.swing.*;

/**
 * Created by profesor on 29/09/2016.
 */
public class Ventana {
    JPanel panel1;
    JLabel lbEstado;
    JTextField tfMatricula;
    JTextField tfModelo;
    JTextField tfCompra;
    JTextField tfPotencia;
    JTextField tfBusqueda;
    JList lCoches;
    JButton btEliminar;
    JButton btNuevo;
    JButton btModificar;
    JButton btGuardar;
    JButton btCancelar;
    JRadioButton rbGasolina;
    JRadioButton rbDiesel;
    JCheckBox cbHibrido;
    ButtonGroup bgCombustible;

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        bgCombustible = new ButtonGroup();
        bgCombustible.add(rbDiesel);
        bgCombustible.add(rbGasolina);
    }
}
