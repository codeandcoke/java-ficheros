package org.sfaci.damgames.gui;

import org.sfaci.damgames.base.Programador;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santi on 29/10/15.
 */
public class JComboProgramador<Programador> extends JComboBox {

    private List<Programador> datos;

    public JComboProgramador(List<Programador> datos) {
        super();
        this.datos = datos;
    }

    public void listar() {

    }

    public void listar(String busqueda) {

    }

    public String getNombreProgramadorSeleccionado() {

        return null;
    }
}
