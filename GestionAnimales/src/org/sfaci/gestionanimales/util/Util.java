package org.sfaci.gestionanimales.util;

import javax.swing.*;

/**
 * Created by Santi on 30/10/15.
 */
public class Util {

    public static void mensajeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
