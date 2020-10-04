package implementations;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import commons.Arco;
import commons.Nodo;


public class GrafoNormal implements Grafo{
	List<Nodo> nodos = new ArrayList<>();
	List<Arco> arcos = new ArrayList<>();
	private static EstregiaBusqueda buscador;
	
	
	private void setEstrategia() {
		// obtiene la configuración
		// lee el archivo de configuraci�n
		Properties opciones = new Properties();
		
		try {
			opciones.load(new FileReader("config.properties"));			
		} catch (Exception e) {
			// ignora cualquier error leyendo el archivo
		}
		
		String estrategia = opciones.getProperty("Search");
		if(estrategia.equals("BFS"))
			buscador = new BusquedaBFS();
		else if (estrategia.equals("DFS"))
			buscador = new BusquedaDFS();
		else
			throw new RuntimeException("Configuración Invalida");
	}

	public void addNodo(String nombre)
	{
		Nodo nodo = new Nodo();
		nodo.setNombre(nombre);
		nodos.add(nodo);
	}
	
	
	public void addArco(String origen, String destino) throws Exception
	{
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la B�squeda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la B�squeda: NodoOrigen no encontrado");
		}
		
		Arco arco = new Arco();
		arco.setOrigen(nodoOrigen);
		arco.setDestino(nodoDestino);
		arcos.add(arco);
		nodoOrigen.agregarArco(arco);
		
		arco = new Arco();
		arco.setOrigen(nodoDestino);
		arco.setDestino(nodoOrigen);
		arcos.add(arco);
		nodoDestino.agregarArco(arco);
	}
	
	public Nodo buscarNodo(String nombre) 
	{
		for (Nodo nodo: nodos) {
			if (nodo.getNombre().equals(nombre)) {
				return nodo;
			}
		}
		return null;
	}
	
	public boolean existeRuta(String origen, String destino) throws Exception {
		if (buscarRuta(origen, destino) != null) {
			return true;
		}
		return false;
	}
	
	public List<Nodo> buscarRuta(String origen, String destino) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		List<Nodo> nodosRuta = new ArrayList<>();
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la B�squeda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la B�squeda: NodoOrigen no encontrado");
		}
		
		if(Objects.isNull(buscador)) this.setEstrategia();
		
		if (buscador.buscarRuta(nodosRuta, nodoOrigen, nodoDestino)) {
			return nodosRuta;
		} else {
			return null;
		}
	}
	

	public void addArco(String origen, String destino, int peso) throws Exception {
		throw new UnsupportedOperationException("No se manejan arcos con pesos para esta configuración");
		
	}


}
