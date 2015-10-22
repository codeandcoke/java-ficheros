package org.sfsoft.serializar.bean;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

/**
 * Clase que representa una barra de estado
 * Es un Panel con dos cajas de texto, los cuales puedo cambiar/leer mediantes getters y setters
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class PanelEstado extends JPanel {

	/**
	 * Panel de estado con soporte para dos líneas de mensaje
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbMensaje = new JLabel();
	private JLabel lbEstado = new JLabel();
	
	public PanelEstado() {
		
		inicializar();
	}
	
	/**
	 * Muestra un mensaje en el lado izquierdo del panel
	 * @param mensaje El mensaje
	 */
	public void setMensaje(String mensaje) {
		this.lbMensaje.setText(mensaje);
	}
	
	/**
	 * Muestra un mensaje en el lado derecho del panel
	 * @param estado El mensaje
	 */
	public void setEstado(String estado) {
		this.lbEstado.setText(estado);
	}
	
	/**
	 * Devuelve el mensaje de estado del panel
	 * @return El mensaje
	 */
	public String getEstado() {
		return this.lbEstado.getText();
	}
	
	/**
	 * Modifica el color del texto del estado
	 * @param color
	 */
	public void setColorEstado(Color color) {
		this.lbEstado.setForeground(color);
	}
	
	/**
	 * Inicializa el apartado gráfico del panel
	 */
	public void inicializar() {
		
		lbMensaje.setPreferredSize(new Dimension(500, 20));
		lbEstado.setPreferredSize(new Dimension(500, 20));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		add(lbMensaje, BorderLayout.WEST);
		lbEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbEstado, BorderLayout.EAST);
	}
}
