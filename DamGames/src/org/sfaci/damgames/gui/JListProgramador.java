package org.sfaci.damgames.gui;

import org.sfaci.damgames.base.Programador;

import javax.swing.*;
import java.util.List;

/**
 * Created by Santi on 30/10/15.
 */
public class JListProgramador extends JList<Programador> {

    private DefaultListModel<Programador> modelo;
    private List<Programador> datos;

    public JListProgramador(List<Programador> datos) {

        super();
        this.datos = datos;
        modelo = new DefaultListModel<>();
        setModel(modelo);
        listar();
    }

    public void listar() {

        for (Programador programador : datos)
            modelo.addElement(programador);
    }

    public void listar(String filtro) {

    }

    public Programador getProgramadorSeleccionado() {

        if (getSelectedIndex() == -1)
            return null;

        return datos.get(getSelectedIndex());
    }

    public int getCantidadProgramadores() {
        return datos.size();
    }
}
