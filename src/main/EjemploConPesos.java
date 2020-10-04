package main;

import java.util.List;

import commons.Nodo;
import implementations.Grafo;

public class EjemploConPesos {

	public static void main(String[] args) {
		
		try {
			
			Grafo g = FabricaGrafo.getGrafo();
			
			g.addNodo("A");
			g.addNodo("B");
			g.addNodo("C");
			
			g.addArco("A", "B",2);
			g.addArco("B", "C",3);
			g.addArco("A", "C",3);

			List<Nodo> camino = g.buscarRuta("A", "C");

			if (camino == null) {
				System.out.println("Camino A -> C no encontrado");
			} else {
				System.out.println("Camino A -> C encontrado");
				for (Nodo nodo: camino) {
					System.out.println(nodo.getNombre());
				}				
			}
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			
			Grafo g = FabricaGrafo.getGrafo();
			
			g.addNodo("A");
			g.addNodo("B");
			g.addNodo("C");
			g.addNodo("D");
			g.addNodo("E");
			
			g.addArco("A", "B",1);
			g.addArco("B", "C",5);
			g.addArco("C", "D",4);
			g.addArco("E", "D",9);
			g.addArco("A", "C",10);
			
			List<Nodo> camino = g.buscarRuta("A", "E");
			
			if (camino == null) {
				System.out.println("Camino A -> E no encontrado");
			} else {
				System.out.println("Camino A -> E encontrado");
				for (Nodo nodo: camino) {
					System.out.println(nodo.getNombre());
				}				
			}
			System.out.println();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
