package org.sfaci.damgames.gui;

import javax.swing.*;
import java.util.List;

/**
 * Created by Santi on 29/10/15.
 */
public class JComboGenerico<E> extends JComboBox<E> {

    private List<E> datos;

    public JComboGenerico(List<E> datos) {
        super();

    }

    public void listar() {

        for (E dato : datos) {
            addItem(dato);
        }
    }

    public void listar(String filtro) {

    }

    public E getValorSeleccionado() {

        return datos.get(getSelectedIndex());
    }
}
