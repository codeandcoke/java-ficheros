package org.sfaci.damgames.gui;

import org.sfaci.damgames.base.Compania;
import org.sfaci.damgames.base.Programador;

import javax.swing.*;
import java.util.List;

/**
 * Created by Santi on 29/10/15.
 */
public class JComboCompania extends JComboBox {

    private List<Compania> datos;

    public JComboCompania(List<Compania> datos) {
        super();
        this.datos = datos;
        listar();
    }

    public void listar() {

        removeAllItems();

        if (datos.size() == 0) {
            addItem("<Sin datos>");
            return;
        }

        addItem("<Selecciona Compania>");
        for (Compania compania : datos)
            addItem(compania);
    }

    public void listar(String filtro) {

        removeAllItems();

        if (datos.size() == 0) {
            addItem("<Sin datos>");
            return;
        }

        addItem("<Selecciona Compania>");
        for (Compania Compania : datos) {
            if (Compania.getNombre().contains(filtro))
                addItem(Compania);
        }
    }

    public String getNombreCompaniaSeleccionada() {

        Compania compania = getCompaniaSeleccionada();
        if (compania == null)
            return null;

        return compania.getNombre();
    }

    public Compania getCompaniaSeleccionada() {

        int itemSeleccionado = getSelectedIndex();
        if (itemSeleccionado == 0)
            return null;

        return datos.get(itemSeleccionado - 1);
    }
}
