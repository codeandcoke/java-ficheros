package org.sfsoft.ficheros_serializar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Programa principal. 
 * Serializa y deserializa un objeto de un fichero
 * Busca un objeto dentro de una lista
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Principal {

	/**
	 * Escribe un objeto en un fichero con ubicación fija
	 * @param objeto
	 */
	public void escribirObjeto(Object objeto) {
	
		FileOutputStream fichero = null;
		ObjectOutputStream serializador = null;
		
		try {
			fichero = new FileOutputStream("archivo.dat");
			serializador = new ObjectOutputStream(fichero);
			
			serializador.writeObject(objeto);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) {
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Lee un fichero, deserializa el objeto que almacena y lo devuelve
	 * @return El objeto almacenado en el fichero
	 */
	public Object leerObjeto() {
		
		FileInputStream fichero = null;
		ObjectInputStream serializador = null;
		Object objeto = null;
		
		try {
			fichero = new FileInputStream("archivo.dat");
			serializador = new ObjectInputStream(fichero);
			
			objeto = serializador.readObject();
		
		} catch (FileNotFoundException fnfe) {
			System.out.println("No se encuentra el fichero especificado");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Tipo de objeto no conocido");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) 
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
		
		return objeto;
	}
	
	/**
	 * Busca un objeto persona (a través del nombre y apellidos) en una lista
	 * @param listaPersonas
	 * @param nombre
	 * @param apellidos
	 * @return
	 */
	public Persona buscaPersona(ArrayList<Persona> listaPersonas, String nombre, String apellidos) {
		
		Persona persona = null;
		boolean encontrada = false;
		
		for (int i = 0;(i < listaPersonas.size() && !encontrada); i++) {
			
			persona = listaPersonas.get(i);
			
			if (persona.getNombre().equals(nombre) && persona.getApellidos().equals(apellidos)) {
				encontrada = true;
			}
		}
		
		if (encontrada) 
			return persona;
		
		return null;
	}
	
	/**
	 * Programa principal. Prueba los métodos anteriores
	 * @param args
	 */
	public static void main(String args[]) {
		
		ArrayList<Persona> listaObjetos = null;
		
		Persona persona1 = new Persona("Persona", "1", 10);
		Persona persona2 = new Persona("Persona", "2", 20);
		Persona persona3 = new Persona("Persona", "3", 30);
		
		listaObjetos = new ArrayList<Persona>();
		listaObjetos.add(persona1);
		listaObjetos.add(persona2);
		listaObjetos.add(persona3);
		
		Principal principal = new Principal();
		
		persona1 = principal.buscaPersona(listaObjetos, "Persona", "3");
		if (persona1 == null) {
			System.out.println("No encontrado");
		} else {
			System.out.println("encontrado");
		}
		
		principal.escribirObjeto(listaObjetos);
		
		listaObjetos = (ArrayList<Persona>) principal.leerObjeto();		
	}
}
