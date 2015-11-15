package org.sfaci.damgames.util;

import javax.swing.*;

/**
 * Created by Santi on 30/10/15.
 */
public class Util {

    public int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }
}
