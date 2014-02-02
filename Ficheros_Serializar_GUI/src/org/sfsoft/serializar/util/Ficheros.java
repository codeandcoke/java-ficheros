package org.sfsoft.serializar.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Recopilaci�n de m�todos para trabajar con ficheros
 * @author Santiago Faci
 */
public class Ficheros {

	/**
	 * Serializa un objeto a un fichero cuya ruta se pasa como par�metro
	 * @param objeto El objeto que se quiere serializar
	 * @param rutaFichero La ruta al fichero
	 * @throws IOException En caso de fallo de escritura
	 */
	public static void escribirObjeto(Object objeto, String rutaFichero) 
		throws IOException {
		
		FileOutputStream fichero = null;
		ObjectOutputStream serializador = null;
		
		fichero = new FileOutputStream(rutaFichero);
		serializador = new ObjectOutputStream(fichero);
		
		serializador.writeObject(objeto);
		
		if (serializador != null) {
			serializador.close();
		}
	}
	
	/**
	 * Devuelve un objeto al deserializarlo de un fichero cuya ruta se para como par�metro
	 * @param rutaFichero La ruta al fichero
	 * @return El objeto que hab�a serializado en el fichero
	 * @throws FileNotFoundException En caso de que no se encuentre el fichero especificado
	 * @throws ClassNotFoundException En caso de que no pueda tratarse la clase serializada en el fichero
	 * @throws IOException En caso de fallo de lectura de disco
	 */
	public static Object leerObjeto(String rutaFichero) 
		throws FileNotFoundException, ClassNotFoundException, IOException {
		
		FileInputStream fichero = null;
		ObjectInputStream serializador = null;
		Object objeto = null;
		
		fichero = new FileInputStream(rutaFichero);
		serializador = new ObjectInputStream(fichero);
		
		objeto = serializador.readObject();
		
		if (serializador != null) {
			serializador.close();
		}
		
		return objeto;
	}
}
