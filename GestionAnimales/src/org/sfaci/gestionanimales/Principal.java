package org.sfaci.gestionanimales;

import org.sfaci.gestionanimales.gui.Ventana;
import org.sfaci.gestionanimales.gui.VentanaController;
import org.sfaci.gestionanimales.gui.VentanaModel;

/**
 * Created by Santi on 30/10/15.
 */
public class Principal {

    public static void main(String args[]) {

        Ventana view = new Ventana();
        VentanaModel model = new VentanaModel();
        VentanaController controller = new VentanaController(model, view);
    }
}
