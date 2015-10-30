package org.sfaci.damgames.gui;

import org.sfaci.damgames.base.Programador;

import javax.swing.*;
import java.util.List;

/**
 * Created by Santi on 30/10/15.
 */
public class JListProgramador extends JList {

    private DefaultListModel<Programador> modelo;
    private List<Programador> datos;

    public JListProgramador() {

    }

    public void listar() {

    }

    public void listar(String filtro) {

    }

    public Programador getProgramadorSeleccionado() {

        return null;
    }

    public int getCantidadProgramadores() {
        return datos.size();
    }
}
