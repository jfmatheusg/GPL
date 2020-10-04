package implementations;

import java.util.List;

import commons.Nodo;


public interface EstregiaBusqueda {
	public Boolean buscarRuta(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino);

}
