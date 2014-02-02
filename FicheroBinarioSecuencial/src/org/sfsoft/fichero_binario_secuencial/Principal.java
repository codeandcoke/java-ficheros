package org.sfsoft.fichero_binario_secuencial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Ejemplo que escribe y lee un fichero binario secuencial
 * TODO Que el nombre del fichero a la hora de escribir/leer se pase como parámetro
 * TODO Implementar una interfaz sencilla para cargar y almacenar información en un fichero binario secuencial
 * @author Santiago Faci
 *
 */
public class Principal {
	
	/**
	 * Escribe en un fichero la información que se pasa como parámetro
	 * @param nombre
	 * @param apellidos
	 * @param puntos
	 */
	public void escribirFichero(String nombre, String apellidos, int puntos) {
		
		FileOutputStream fichero = null;
		DataOutputStream datos = null;
		
		try {
			fichero = new FileOutputStream("archivo.dat");
			datos = new DataOutputStream(fichero);
			
			// Los datos se almacenan uno a uno
			datos.writeUTF(nombre);
			datos.writeUTF(apellidos);
			datos.writeInt(puntos);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (datos != null) 
				try {
					datos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
	}
	
	/**
	 * Lee un fichero binario de forma secuencial
	 */
	public void leerFichero() {
		
		FileInputStream fichero = null;
		DataInputStream datos = null;
		String nombre = null, apellidos = null;
		int puntos = 0;
		
		try {
			fichero = new FileInputStream("archivo.dat");
			datos = new DataInputStream(fichero);
			
			// Los datos se leen en el mismo orden en que fueron escritos
			nombre = datos.readUTF();
			apellidos = datos.readUTF();
			puntos = datos.readInt();
			
			System.out.println(nombre + " " + apellidos + " "  + puntos);
			
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException ioe) {
			
		} finally {
			if (datos != null) {
				try {
					datos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
	}
	
	/*
	 * Programa principal
	 */
	public static void main(String args[]) {
		
		Principal principal = new Principal();
		
		principal.escribirFichero("Santiago", "Faci", 10);
		principal.leerFichero();
	}
}
