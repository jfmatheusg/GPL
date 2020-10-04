package main;

import java.io.FileReader;
import java.util.Properties;

import implementations.Grafo;

public class FabricaGrafo {

	
	public static Grafo getGrafo() {
				
		// obtiene la configuración
		// lee el archivo de configuraci�n
		Properties opciones = new Properties();
		
		try {
			opciones.load(new FileReader("config.properties"));			
		} catch (Exception e) {
			System.out.println("Error leyendo archivo de configración");
		}
		
		boolean directed = Boolean.valueOf(opciones.getProperty("Directed"));
		boolean weighted = Boolean.valueOf(opciones.getProperty("Weighted"));
		
		// crea el objeto de acuerdo a la opci�n configurada
		Grafo grafo = null;
		try {
			if(directed) {
				System.out.println("Se creara un grafo tipo Dirigido");
				grafo = (Grafo) Class.forName(opciones.getProperty("DirectedClass")).newInstance();}
			else if (weighted) {
				System.out.println("Se creara un grafo tipo Dirigido con Pesos");
				grafo = (Grafo) Class.forName(opciones.getProperty("WeightedClass")).newInstance();}
			else {
				System.out.println("Se creara un grafo tipo Normal");
				grafo = (Grafo) Class.forName(opciones.getProperty("UndirectedClass")).newInstance();}

		} catch (Exception e) {
			// No se pudo crear el algoritmo
			throw new RuntimeException("No se pudo crear el grafo");
		}
		return grafo;
	}
	
}
