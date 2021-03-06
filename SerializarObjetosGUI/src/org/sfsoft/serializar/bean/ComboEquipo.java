package org.sfsoft.serializar.bean;

import java.util.ArrayList;

import javax.swing.JComboBox;

import org.sfsoft.serializar.base.Equipo;

/**
 * Combo personalizado para trabajar con Equipos
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class ComboEquipo extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Equipo> equipos;

	public ComboEquipo() {
		super();
	}
	
	/**
	 * Inicializa el combo y lista los equipos
	 * @param equipos La lista de los equipos que debe tratar
	 */
	public void inicializar(ArrayList<Equipo> equipos) {
		
		equipos = equipos;
		listar();
	}
	
	/**
	 * Lista/refresca los equipos que haya actualmente registrados
	 */
	public void listar() {
		
		// Antes de listarlo, tendremos que borrar su contenido
		removeAllItems();
		
		if (this.equipos.size() == 0) {
			addItem("<No hay Equipos>");
			return;
		}
		
		addItem("<Selecciona Equipo>");
		for (Equipo equipo : equipos) {
			addItem(equipo.getNombre());
		}
	}
	
	/**
	 * Devuelve el equipo que está seleccionado en cada momento
	 * @return El equipo seleccionado
	 */
	public Equipo getEquipo() {
		
		int posicion = 0;
		Equipo equipo = null;
		
		// Si no hay ningún elemento pulsado se devuelve null
		posicion = getSelectedIndex();
		if (posicion == -1) {
			return null;
		}
		
		// Está seleccionado '<Selecciona Equipo>'
		if (posicion == 0) {
			return null;
		}
		
		// El combo siempre contiene un elemento más de los que hay realmente
		equipo = equipos.get(posicion - 1);
		return equipo;
	}
}
