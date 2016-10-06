package com.sfaci.concesionario;

import com.sfaci.concesionario.gui.Ventana;
import com.sfaci.concesionario.gui.VentanaController;
import com.sfaci.concesionario.gui.VentanaModel;

/**
 * Created by profesor on 29/09/2016.
 */
public class Aplicacion {

    public static void main(String args[]) {

        Ventana view = new Ventana();
        VentanaModel model = new VentanaModel();
        VentanaController controller =
                new VentanaController(view, model);
    }
}
