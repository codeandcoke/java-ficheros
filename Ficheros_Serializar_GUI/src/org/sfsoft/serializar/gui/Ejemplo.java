package org.sfsoft.serializar.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.sfsoft.serializar.base.Equipo;
import org.sfsoft.serializar.base.Jugador;
import org.sfsoft.serializar.base.Partido;
import org.sfsoft.serializar.bean.PanelEstado;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import org.sfsoft.serializar.bean.ComboEquipo;
import org.sfsoft.serializar.util.Constantes;
import org.sfsoft.serializar.util.Ficheros;
import org.sfsoft.serializar.util.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import org.sfsoft.serializar.bean.TablaEquipo;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Interfaz principal de la aplicación
 * Se han modificado algunas preferencias en la generación de código para que la declaración de los controles
 * gráficos se haga a nivel de clase y no localmente en el método initialize()
 * @author Santiago Faci
 * @version curso 2014-2015
 * 
 * Están todas las funcionalidades implementadas para la pestaña de Equipos
 * 
 * TODO Hacer que la barra de menú haga algo, como salir de la aplicación o mostrar una ventana con el Acerca de
 * TODO Alta/Baja/Modificar/Eliminar Mostrar Partidos
 * TODO	Baja/Modificar/Eliminar/Mostrar Jugadores
 * TODO Que a la hora de dar de alta cualquier objeto compruebe si no existe ya (si se repiten o no los valores obligatorios
 */

public class Ejemplo {

	private JFrame frmPcFutbolDam;
	private JTextField txtNombreEquipo;
	private PanelEstado panelEstado;
	private JTextField txtPatrocinador;
	private JTextField txtColor1;
	private JTextField txtColor2;
	private JTextField txtNombreJugador;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private ComboEquipo cbEquipo; 
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCategoria;
	private JSeparator separator;
	private JSeparator separator_1;
	private JScrollPane scrollPane;
	private JButton btEliminarEquipo;
	private TablaEquipo tablaEquipos;
	private JButton btModificarEquipo;
	private JButton btNuevoEquipo;
	private JButton btGuardarEquipo;
	private JButton btCancelarEquipo;
	private JTextField txtFiltro;
	private JButton btCancelarFiltro;
	
	private ArrayList<Equipo> equipos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Partido> partidos;
	
	// Enumeración para especificar la acción del usuario al pulsar en el botón 'Guardar'
	private enum Accion {NUEVO, MODIFICAR};
	private Accion accion;
	
	public static void main(String[] args) {
		
		try {
            // Modifica el tema de la aplicación
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	        	//UIManager.getCrossPlatformLookAndFeelClassName());
	    } catch (Exception e) { }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo window = new Ejemplo();
					window.frmPcFutbolDam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ejemplo() {
		initialize();
		inicializar();
	}
	
	private void inicializar() {
	
		this.equipos = new ArrayList<Equipo>();
		this.jugadores = new ArrayList<Jugador>();
		this.partidos = new ArrayList<Partido>();
		
		// Carga de ficheros
		try {
			if (new File(Constantes.FICHERO_EQUIPOS).exists())
				this.equipos = (ArrayList<Equipo>) Ficheros.leerObjeto(Constantes.FICHERO_EQUIPOS);
			if (new File(Constantes.FICHERO_JUGADORES).exists()) 
				this.jugadores = (ArrayList<Jugador>) Ficheros.leerObjeto(Constantes.FICHERO_JUGADORES);
			if (new File(Constantes.FICHERO_PARTIDOS).exists())
				this.partidos = (ArrayList<Partido>) Ficheros.leerObjeto(Constantes.FICHERO_PARTIDOS);
		} catch (FileNotFoundException fnfe) {
			Util.mensajeError("No se ha podido encontrar el fichero de datos");
		} catch (ClassNotFoundException cnfe) {
			Util.mensajeError("El formato del fichero no es correcto");
		} catch (IOException ioe) {
			Util.mensajeError("Error al leer del disco");
		}
		
		// Inicializa el ComboEquipo
		this.cbEquipo.inicializar(this.equipos);
		this.cbEquipo.listar();
		
		// Inicializa la TablaEquipo
		this.tablaEquipos.inicializar(this.equipos);
		this.tablaEquipos.listar();
	}
	
	// Da de alta un nuevo jugador. Método invocado por el botón correspondiente
	// FIXME No comprueba si se dan de alta jugadores repetidos (mismo nombre y apellidos)
	private void nuevoJugador() {
	
		Jugador jugador = null;
		DateFormat formatFecha = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
		
		// Nombre y apellidos son datos obligatorios
		if ((this.txtNombreJugador.equals("")) || (this.txtApellidos.getText().equals(""))) {
			Util.mensajeError("Debes rellenar los campos obligatorios");
			return;
		}
		
		try {
			jugador = new Jugador();
			jugador.setNombre(this.txtNombreJugador.getText());
			jugador.setApellidos(this.txtApellidos.getText());
			if (this.txtFechaNacimiento.getText().equals("")) {
				jugador.setFechaNacimiento(null);
			} else {
				jugador.setFechaNacimiento(formatFecha.parse(this.txtFechaNacimiento.getText()));
			}
			jugador.setDireccion(this.txtDireccion.getText());
			jugador.setTelefono(this.txtTelefono.getText());
			jugador.setEquipo(this.cbEquipo.getEquipo());
			
			this.jugadores.add(jugador);
			Ficheros.escribirObjeto(this.jugadores, Constantes.FICHERO_JUGADORES);
			
			this.panelEstado.setMensaje("Jugador dado de alta correctamente");
			this.limpiarCamposJugador();
			this.txtNombreJugador.requestFocusInWindow();
		
		} catch (ParseException pe) { // En caso de que la fecha no sea correcta
			Util.mensajeError("Comprueba que la fecha tiene el formato correcto");
		} catch (IOException ioe) { // En caso de que haya problemas de escritura
			Util.mensajeError("No se ha podido escribir a disco. Vuelva a intentarlo");
		}
	}
	
	private void eliminaJugador() {
		/* TODO Eliminar jugadores seleccionados en la tabla donde se muestran los jugadores
		 * Habría que crear y añadir una TablaJugador (similar a TablaEquipo)
		 */
	}
	
	/*
	 * Prepara el interfaz para que el usuario pueda dar de alta un nuevo Equipo
	 */
	private void nuevoEquipo() {
		
		this.txtNombreEquipo.setEditable(true);
		this.txtNombreEquipo.setText("");
		this.txtPatrocinador.setEditable(true);
		this.txtPatrocinador.setText("");
		this.txtColor1.setEditable(true);
		this.txtColor1.setText("");
		this.txtColor2.setEditable(true);
		this.txtColor2.setText("");
		this.txtCategoria.setEditable(true);
		this.txtCategoria.setText("");
		
		this.btNuevoEquipo.setEnabled(false);
		this.btGuardarEquipo.setEnabled(true);
		this.btCancelarEquipo.setEnabled(true);
				
		this.tablaEquipos.setEnabled(false);
		
		this.accion = Accion.NUEVO;
		this.panelEstado.setColorEstado(Color.GREEN);
		this.panelEstado.setEstado("NUEVO");
	}
	
	/*
	 * Guarda los datos que ha introducido el usuario
	 * Registrará un nuevo equipo si el usuario había pulsado 'Nuevo' anteriormente
	 * Modificará los datos de un equipo si el usuario había pulsado 'Modificar' anteriormente
	 * 
	 * FIXME: No comprueba que haya equipos repetidos al registrarlo como nuevos o modificarlos
	 */
	private void guardarEquipo() {
		
		Equipo equipo = null;
		
		// Evalúa que acción debe realizar: NUEVO, MODIFICAR
		switch (this.accion) {
		case NUEVO:
			
			// El nombre del equipo es un dato obligatorio
			if (this.txtNombreEquipo.getText().equals("")) {
				Util.mensajeError("Debes indicar el nombre del equipo");
				return;
			}
			
			equipo = new Equipo();
			equipo.setNombre(this.txtNombreEquipo.getText());
			equipo.setPatrocinador(this.txtPatrocinador.getText());
			equipo.setColor1(this.txtColor1.getText());
			equipo.setColor2(this.txtColor2.getText());
			equipo.setCategoria(this.txtCategoria.getText());
			
			this.equipos.add(equipo);
			
			try {
				Ficheros.escribirObjeto(equipos, Constantes.FICHERO_EQUIPOS);
				
				this.panelEstado.setMensaje("El equipo se ha dado de alta correctamente");
				this.limpiarCamposEquipo();
				
				// El foco se coloca en la caja de texto
				this.txtNombreEquipo.requestFocusInWindow();
				
				// Refresco el combo y la tabla donde los muestro
				this.cbEquipo.listar();
				this.tablaEquipos.listar();
				
			} catch (IOException ioe) { // En caso de que haya problemas de escritura
				Util.mensajeError("No se ha podido escribir a disco. Vuelta a intentarlo");
				ioe.printStackTrace();
			}
			break;
		case MODIFICAR:
			
			if (Util.mensajeConfirmacion("¿Estás seguro?") == JOptionPane.NO_OPTION)
				return;
			
			equipo = this.tablaEquipos.getEquipoSeleccionado();
			equipo.setNombre(this.txtNombreEquipo.getText());
			equipo.setPatrocinador(this.txtPatrocinador.getText());
			equipo.setColor1(this.txtColor1.getText());
			equipo.setColor2(this.txtColor2.getText());
			equipo.setCategoria(this.txtCategoria.getText());
			
			try {
				Ficheros.escribirObjeto(this.equipos, Constantes.FICHERO_EQUIPOS);
			} catch (IOException ioe) {
				Util.mensajeError("No se han podido guardar los cambios");
			}
			
			this.tablaEquipos.setEnabled(true);
			
			break;
		default:
			break;
		}
		
		this.apagarControlesEquipo();
		this.tablaEquipos.listar();
		this.cbEquipo.listar();
	}
	
	/*
	 * Desactiva los controles del interfaz de manera que sólo se pueden visualizar datos
	 */
	private void apagarControlesEquipo() {
		
		this.txtNombreEquipo.setEditable(false);
		this.txtPatrocinador.setEditable(false);
		this.txtColor1.setEditable(false);
		this.txtColor2.setEditable(false);
		this.txtCategoria.setEditable(false);
		
		this.btNuevoEquipo.setEnabled(true);
		this.btGuardarEquipo.setEnabled(false);
		this.btCancelarEquipo.setEnabled(false);
	}
	
	/*
	 * Carga en el interfaz del usuario el equipo que haya seleccionado en la tabla
	 */
	private void cargaEquipoDeTabla() {
		Equipo equipo = null;
		
		equipo = this.tablaEquipos.getEquipoSeleccionado();
		
		this.txtNombreEquipo.setText(equipo.getNombre());
		this.txtPatrocinador.setText(equipo.getPatrocinador());
		this.txtColor1.setText(equipo.getColor1());
		this.txtColor2.setText(equipo.getColor2());
		this.txtCategoria.setText(equipo.getCategoria());
	}
	
	/*
	 * Cancela la edición del equipo, tanto de alta de nuevos equipos como de modificación de existentes
	 */
	private void cancelarEquipo() {
	
		this.apagarControlesEquipo();
		
		switch (this.accion) {
		case NUEVO:
			this.txtNombreEquipo.setText("");
			this.txtPatrocinador.setText("");
			this.txtColor1.setText("");
			this.txtColor2.setText("");
			this.txtCategoria.setText("");
			break;
		case MODIFICAR:		
			this.cargaEquipoDeTabla();
			break;
		default:	
			break;
		}
			
		this.tablaEquipos.setEnabled(true);
		this.accion = null;
		this.panelEstado.setEstado("");
	}
	
	/*
	 * Prepara el interfaz cuando el usuario selecciona un equipo de la tabla
	 */
	private void seleccionarEquipo() {
		
		this.cargaEquipoDeTabla();
		
		this.btModificarEquipo.setEnabled(true);
		this.btEliminarEquipo.setEnabled(true);
	}
	
	/*
	 * Prepara el interfaz para que el usuario pueda modificar los datos de un equipo
	 */
	private void modificarEquipo() {
	
		this.txtNombreEquipo.setEditable(true);
		this.txtPatrocinador.setEditable(true);
		this.txtColor1.setEditable(true);
		this.txtColor2.setEditable(true);
		this.txtCategoria.setEditable(true);
		
		this.btNuevoEquipo.setEnabled(false);
		this.btGuardarEquipo.setEnabled(true);
		this.btCancelarEquipo.setEnabled(true);
		this.btEliminarEquipo.setEnabled(false);
		
		this.tablaEquipos.setEnabled(false);
		
		this.accion = Accion.MODIFICAR;
		
		this.panelEstado.setColorEstado(Color.GREEN);
		this.panelEstado.setEstado("MODIFICAR");
	}
	
	/*
	 * Elimina el equipo que el usuario ha seleccionado previamente de la tabla
	 */
	private void eliminarEquipo() {
		
		String nombreEquipo;
		
		nombreEquipo = this.tablaEquipos.getNombreEquipoSeleccionado();
		if (nombreEquipo == null) {
			Util.mensajeError("No se ha seleccionado ningún equipo");
			return;
		}
		
		if ((Util.mensajeConfirmacion("¿Estás seguro de que quieres eliminar el equipo " + nombreEquipo)) ==
				(JOptionPane.NO_OPTION)) {
			return;
		}
		
		try {
			this.equipos.remove(this.tablaEquipos.getEquipoSeleccionado());
			Ficheros.escribirObjeto(this.equipos, Constantes.FICHERO_EQUIPOS);
			this.tablaEquipos.listar();
			this.cbEquipo.listar();
			
			this.panelEstado.setMensaje("El equipo " + nombreEquipo + " se ha eliminado con éxito");
		} catch (IOException ioe) {
			Util.mensajeError("No se ha podido eliminar el equipo. Vuelva a intentarlo");
		}
	}
	
	private void aplicarFiltroEquipo() {
		
		if (this.txtFiltro.getText().length() == 0)
			this.tablaEquipos.listar();
		
		if (this.txtFiltro.getText().length() <= 1) {
			return;
		}
		
		this.tablaEquipos.listar(this.txtFiltro.getText());
	}
	
	private void cancelarFiltroEquipo() {
		
		this.txtFiltro.setText("");
		this.tablaEquipos.listar();
	}
	
	private void nuevoPartido() {
		// TODO Dar de alta un partido
	}
	
	private void eliminarPartido() {
		// TODO Eliminar un partido
	}
	
	// Limpia todas las cajas de texto del panel de Jugador
	private void limpiarCamposJugador() {
		
		this.txtNombreJugador.setText("");
		this.txtApellidos.setText("");
		this.txtFechaNacimiento.setText("");
		this.txtDireccion.setText("");
		this.txtTelefono.setText("");
		this.cbEquipo.setSelectedIndex(0);
	}
	
	// Limpia todas las cajas de texto del panel de Equipo
	private void limpiarCamposEquipo() {
		
		this.txtNombreEquipo.setText("");
		this.txtPatrocinador.setText("");
		this.txtColor1.setText("");
		this.txtColor2.setText("");
		this.txtCategoria.setText("");
	}

	private void initialize() {
		frmPcFutbolDam = new JFrame();
		this.frmPcFutbolDam.setTitle("PC Futbol DAM");
		this.frmPcFutbolDam.setResizable(false);
		frmPcFutbolDam.setBounds(100, 100, 617, 419);
		frmPcFutbolDam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelEstado = new PanelEstado();
		panelEstado.setEstado("");
		panelEstado.setMensaje("");
		frmPcFutbolDam.getContentPane().add(panelEstado, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmPcFutbolDam.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Jugadores", null, panel, null);
		
		txtNombreJugador = new JTextField();
		txtNombreJugador.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		
		cbEquipo = new ComboEquipo();
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Nac.");
		
		JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n");
		
		JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono");
		
		JLabel lblEquipo = new JLabel("Equipo");
		
		JButton btnInsertar_1 = new JButton("Insertar");
		btnInsertar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoJugador();
			}
		});
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		
		this.separator = new JSeparator();
		this.separator.setOrientation(SwingConstants.VERTICAL);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblApellidos)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblEquipo))
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(this.cbEquipo, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(this.txtTelefono, Alignment.LEADING)
									.addComponent(this.txtDireccion, Alignment.LEADING)
									.addComponent(this.txtNombreJugador, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
									.addComponent(this.txtApellidos, Alignment.LEADING)
									.addComponent(this.txtFechaNacimiento, Alignment.LEADING))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(53)
							.addComponent(btnInsertar_1)
							.addGap(18)
							.addComponent(btnCancelar_1)))
					.addGap(18)
					.addComponent(this.separator, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(288, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtNombreJugador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellidos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.cbEquipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEquipo))
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsertar_1)
						.addComponent(btnCancelar_1))
					.addContainerGap(111, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(this.separator, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Equipos", null, panel_1, null);
		
		txtNombreEquipo = new JTextField();
		this.txtNombreEquipo.setEditable(false);
		txtNombreEquipo.setColumns(10);
		
		txtPatrocinador = new JTextField();
		this.txtPatrocinador.setEditable(false);
		txtPatrocinador.setColumns(10);
		
		txtColor1 = new JTextField();
		this.txtColor1.setEditable(false);
		txtColor1.setColumns(10);
		
		txtColor2 = new JTextField();
		this.txtColor2.setEditable(false);
		txtColor2.setColumns(10);
		
		txtCategoria = new JTextField();
		this.txtCategoria.setEditable(false);
		txtCategoria.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPatrocinador = new JLabel("Patrocinador");
		
		JLabel lblCamiseta = new JLabel("Camiseta 1");
		
		JLabel lblCamiseta_1 = new JLabel("Camiseta 2");
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		
		btGuardarEquipo = new JButton("Guardar");
		this.btGuardarEquipo.setEnabled(false);
		btGuardarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarEquipo();
			}
		});
		
		btCancelarEquipo = new JButton("Cancelar");
		this.btCancelarEquipo.setEnabled(false);
		btCancelarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarEquipo();
			}
		});
		
		this.separator_1 = new JSeparator();
		this.separator_1.setOrientation(SwingConstants.VERTICAL);
		
		this.scrollPane = new JScrollPane();
		
		this.btEliminarEquipo = new JButton("Eliminar");
		this.btEliminarEquipo.setEnabled(false);
		this.btEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarEquipo();
			}
		});
		
		this.btModificarEquipo = new JButton("Modificar");
		this.btModificarEquipo.setEnabled(false);
		this.btModificarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEquipo();
			}
		});
		
		this.btNuevoEquipo = new JButton("Nuevo");
		this.btNuevoEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoEquipo();
			}
		});
		
		this.txtFiltro = new JTextField();
		this.txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				aplicarFiltroEquipo();
			}
		});
		this.txtFiltro.setColumns(10);
		
		this.btCancelarFiltro = new JButton("X");
		this.btCancelarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarFiltroEquipo();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblPatrocinador)
								.addComponent(lblCamiseta)
								.addComponent(lblCamiseta_1)
								.addComponent(lblCategora))
							.addGap(16)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(this.txtCategoria, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
									.addComponent(this.txtNombreEquipo, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
									.addComponent(this.txtPatrocinador)
									.addComponent(this.txtColor1)
									.addComponent(this.txtColor2))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(this.btNuevoEquipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btGuardarEquipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btCancelarEquipo)))
					.addGap(29)
					.addComponent(this.separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(this.btModificarEquipo)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(this.btEliminarEquipo)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(this.txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btCancelarFiltro)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(this.separator_1, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtNombreEquipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtPatrocinador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPatrocinador))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtColor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCamiseta))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtColor2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCamiseta_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCategora))
							.addGap(32)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.btCancelarEquipo)
								.addComponent(this.btGuardarEquipo)
								.addComponent(this.btNuevoEquipo)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.btCancelarFiltro))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.btEliminarEquipo)
								.addComponent(this.btModificarEquipo))))
					.addGap(11))
		);
		
		this.tablaEquipos = new TablaEquipo();
		this.tablaEquipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tablaEquipos.getSelectedRow() != -1) {
					seleccionarEquipo();
				}
			}
		});
		this.scrollPane.setViewportView(this.tablaEquipos);
		panel_1.setLayout(gl_panel_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmPcFutbolDam.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmSalir);
		
		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnNewMenu_1.add(mntmAcercaDe);
	}
}
