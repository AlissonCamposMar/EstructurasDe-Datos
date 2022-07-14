package co.edu.uniquindio.ejercicios.ejercicio03;

import co.edu.uniquindio.listaSimple.pilas.Pila;

public class Ejercicio03 {
	
	public static void main(String[] args) {
		
		Pila pila1 = new Pila();
		Pila pila2 = new Pila();
		Pila pilaConcatenada = new Pila();
		
		pila1 = llenarPila(pila1);
		pila2 = llenarPila(pila2);
		
		System.out.println("Numeros de la primera pila:");
		pila1.imprimir();
		System.out.println("Numeros de la segunda pila:");
		pila2.imprimir();
		
		pilaConcatenada = concatenarPilas(pila1, pila2);
		
		System.out.println("Pila concatenada:");
		pilaConcatenada.imprimir();
		
		
		
	}
	
	/*Función que recibe 2 pilas por parametro y añade la segunda pila a la primera.
	 * @param pila1, pila2
	 * @return pilaConcatenada
	 */
	public static Pila concatenarPilas(Pila pila1, Pila pila2){
		
		Pila pilaAux = pila2;
		pilaAux.agregar(pila1);
		
		return pilaAux;
	}
	
	public static Pila llenarPila(Pila pila){
		
		int aux = 0;
		for (int i=0; i<10; i++){
			
			aux = (int) (Math.random()*10);
			pila.push(aux);
			
		}
		
		return pila;
		
	}
}
