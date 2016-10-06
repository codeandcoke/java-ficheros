package com.sfaci.concesionario;

import com.sfaci.concesionario.gui.Ventana;
import com.sfaci.concesionario.gui.VentanaController;
import com.sfaci.concesionario.gui.VentanaModel;

/**
 * Clase principal de la aplicación
 * Sólo ejecuta la aplicación
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class Aplicacion {

    public static void main(String args[]) {

        Ventana view = new Ventana();
        VentanaModel model = new VentanaModel();
        VentanaController controller =
                new VentanaController(view, model);
    }
}
