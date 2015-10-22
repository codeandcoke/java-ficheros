package org.sfaci.damgames.base;

import java.util.List;

/**
 * Clase que representa a un programador
 * de una Compañia
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Programador {

    private String nombre;
    private String apellidos;
    private String email;
    private float salario;

    private List<Juego> juegos;
    private Compania compania;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
