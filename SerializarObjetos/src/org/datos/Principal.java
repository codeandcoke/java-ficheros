package org.datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Clase Principal.
 * Almacena una lista de objetos y la carga para comprobar que funciona
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Principal {

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
		
		principal.escribirObjeto(listaObjetos);
		
		listaObjetos = (ArrayList<Persona>) principal.leerObjeto();		
	}
}
