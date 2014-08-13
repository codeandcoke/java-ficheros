package org.sfsoft.serializar.bean;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.sfsoft.serializar.base.Equipo;

/**
 * Tabla para visualizar información sobre Equipos
 * @author Santiago Faci
 * @version curso 2014-2015
 */
@SuppressWarnings("serial")
public class TablaEquipo extends JTable {
	
	// Gestiona los datos que la tabla muestra en la pantalla
	private DefaultTableModel modeloTabla;
	private ArrayList<Equipo> equipos;
	
	public TablaEquipo() {
		
		super();
		this.modeloTabla = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		this.setModel(this.modeloTabla);
		this.equipos = new ArrayList<Equipo>();
	}

	/**
	 * Inicializa la tabla y sus columnas 
	 * @param equipos La lista de equipos que tiene que representar
	 */
	public void inicializar(ArrayList<Equipo> equipos) {
		
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Patrocinador");
        modeloTabla.addColumn("Camiseta");
		
		this.equipos = equipos;
	}
	
	/**
	 * Lista/Refresca la lista de equipos en la tabla
	 */
	public void listar() {
		
		this.modeloTabla.setNumRows(0);
		for (Equipo equipo : this.equipos) {
			
			// Crea un vector para añadir una fila con algunos de los datos del equipo
			Object[] fila = new String[]{equipo.getNombre(), equipo.getPatrocinador(), equipo.getColor1()};			
			this.modeloTabla.addRow(fila);
		}
	}
	
	/**
	 * Lista/Refresca la lista de equipos en la tabla aplicando un filtro
	 * @param filtro
	 */
	public void listar(String filtro) {
		
		this.modeloTabla.setNumRows(0);
		for (Equipo equipo : this.equipos) {
			
			// Crea un vector para añadir una fila con algunos de los datos del equipo
			if (equipo.getNombre().contains(filtro) || equipo.getPatrocinador().contains(filtro) ||
					equipo.getColor1().contains(filtro)) {
				Object[] fila = new String[]{equipo.getNombre(), equipo.getPatrocinador(), equipo.getColor1()};			
				this.modeloTabla.addRow(fila);
			}
		}
	}
	
	/**
	 * Obtiene el equipo seleccionado actualmente
	 * @return El equipo seleccionado
	 */
	public Equipo getEquipoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = this.getSelectedRow();
		if (filaSeleccionada == -1) {
			return null;
		}
		
		return this.equipos.get(filaSeleccionada);
	}
	
	/**
	 * Obtiene el nombre del equipo seleccionado
	 * @return El nombre del equipo seleccionado
	 */
	public String getNombreEquipoSeleccionado() {
		
		int filaSeleccionada;
		
		filaSeleccionada = this.getSelectedRow();
		if (filaSeleccionada == -1) {
			return null;
		}
		
		return this.modeloTabla.getValueAt(filaSeleccionada, 0).toString();
	}
	
	/**
	 * Vacía el contenido de la tabla
	 */
	public void vaciar() {
		
		this.modeloTabla.setNumRows(0);
	}
}