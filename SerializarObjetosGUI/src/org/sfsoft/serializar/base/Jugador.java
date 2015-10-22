package org.sfsoft.serializar.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa a un jugador
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private Equipo equipo;
	
	public Jugador() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	
}
