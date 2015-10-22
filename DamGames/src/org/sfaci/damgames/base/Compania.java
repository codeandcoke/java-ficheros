package org.sfaci.damgames.base;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa a una compañia
 * de una Compañia
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Compania {

    private String nombre;
    private String domicilio;
    private String pais;
    private Date fechaFundacion;

    private List<Juego> juegos;
    private List<Programador> programadores;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public List<Programador> getProgramadores() {
        return programadores;
    }

    public void setProgramadores(List<Programador> programadores) {
        this.programadores = programadores;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
