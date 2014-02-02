package org.sfsoft.serializar.util;

import javax.swing.JOptionPane;

/**
 * Recopilación de métodos de utilidad para el proyecto
 * @author Santiago Faci
 *
 */
public class Util {

	/**
	 * Muestra un mensaje en una ventana emergente
	 * @param mensaje El mensaje mostrado
	 */
	public static void mensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	/**
	 * Muestra un mensaje de error en una ventana emergente
	 * @param mensaje El mensaje mostrado
	 */
	public static void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Muestra un mensaje de confirmación en una ventana emergente
	 * @param mensaje El mensaje mostrado al usuario
	 * @return El valor seleccionado por el usuario (Si/No)
	 */
	public static int mensajeConfirmacion(String mensaje) {
		
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
	}
}
