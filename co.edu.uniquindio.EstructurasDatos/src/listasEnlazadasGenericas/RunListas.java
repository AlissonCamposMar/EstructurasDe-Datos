package listasEnlazadasGenericas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import collections.clases.Producto;
import listasEnlazadasGenericas.ListaSimple.IteradorListaSimple;

public class RunListas {

	public static void main(String[] args) {


		ArrayList lista1 = new ArrayList();	
		ListaSimple<Integer> listaEnteros = new ListaSimple<>();
		Random rnd = new Random();
		
		
        listaEnteros.agregarInicio(0);
        listaEnteros.agregarInicio(7);
        listaEnteros.agregarInicio(5);
        listaEnteros.agregarInicio(4);
        listaEnteros.agregarInicio(8);
    	
	
//		
//		
		borrarPosicionesParesOImpares(listaEnteros);
		listaEnteros.imprimirLista();
		ListaSimple<Producto> listaProductos = new ListaSimple<>();
		
		Producto producto = new Producto();
		producto.setNombre("Arroz");
		
		listaProductos.agregarInicio(producto);
		
		producto = new Producto();
		producto.setNombre("Leche");
		
		listaProductos.agregarInicio(producto);
		
		
		
		Iterator<Producto> iter = listaProductos.iterator();
		
		while (iter.hasNext()) {
			Producto producto2 = iter.next();
			System.out.println(producto2.getNombre());
			
			if(iter.hasNext()) {
				System.out.println("existe");
			}
			
		}
		
		listaProductos.eliminar(producto);
		
		
//		IteradorListaSimple iteradorListaSimple = new IteradorListaSimple(listaProductos.getNodoPrimero());
		
		
		
	}
	
	static <T> boolean borrarParesOImpares (Nodo<T> nodo) {
		boolean resul;
		Nodo<T> aux;
		if (nodo != null) {
			aux = nodo;
			nodo = nodo.getSiguienteNodo();
			resul = borrarParesOImpares (nodo);
			if (!resul && (nodo != null))
			{
				aux.setSiguienteNodo(nodo.getSiguienteNodo());
				resul = !resul;
			}

		}
		else resul = true;
		return resul;
	}
	
	static <T> void borrarPosicionesParesOImpares (ListaSimple<T> lista){
		boolean resul = borrarParesOImpares (lista.getNodoPrimero());
		if (!resul)
			lista.setNodoPrimero(lista.getNodoPrimero().getSiguienteNodo());
	}
	
	
	

}
