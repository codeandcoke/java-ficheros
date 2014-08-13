package org.sfsoft.serializar.base;

import java.io.Serializable;

/**
 * Clase que representa a un equipo de fútbol
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String patrocinador;
	private String color1;
	private String color2;
	private String categoria;
	
	public Equipo() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
