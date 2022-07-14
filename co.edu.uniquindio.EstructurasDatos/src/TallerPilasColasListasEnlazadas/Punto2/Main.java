package TallerPilasColasListasEnlazadas.Punto2;

import java.util.Random;

/**
 * @author acm91
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		ListaSimple lista = new ListaSimple();
		Pila listaPila = new Pila();
		Random rnd = new Random();
		
		System.out.println("Lista simple original");
     
	for (int i = 0; i < 10; i++) {
        	lista.agregarInicio((int)(rnd.nextDouble() * 10.0));
    	}
		lista.imprimirLista();
		
		//pasar la lista a una Pila
		while(!lista.estaVacia()) {
			listaPila.insertar(lista.retornarPrimero());
			lista.eliminarPrimero();
		}
		
		System.out.println("\n\nLista Pila:");
		while(!listaPila.pilaVacia()) {
			System.out.println(listaPila.quitar());
		}
	}
	
}
