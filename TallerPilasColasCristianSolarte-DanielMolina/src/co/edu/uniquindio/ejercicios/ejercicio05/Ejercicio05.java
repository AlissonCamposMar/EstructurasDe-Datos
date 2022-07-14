package co.edu.uniquindio.ejercicios.ejercicio05;

import co.edu.uniquindio.listaSimple.pilas.Pila;

public class Ejercicio05 {

	public static void main(String[] args) {
	
		Pila pila = new Pila();
		
		pila = llenarPila(pila);
		
		System.out.println("Números de la pila:");
		pila.imprimir();
		
		pila = removeValue(pila, 7);
		
		System.out.println("Números de la pila despues de haber removido el dato seleccionado");
		pila.imprimir();
		
	}
	
/*Función que elimina de la pila aquellos valores que son iguales a un valor que le llega por
 * parametro, en este caso lo vamos a ejemplificar con un valor de tipo entero.
 * @param Pila pila,  int valor.
 * @return pila.
 */
	public static Pila removeValue(Pila pila, int valor){
		
		int aux;
		Pila pilaAux = new Pila();
		
		while(!pila.estaVacia()){
			
			aux = (int) pila.pop();
			
			if( aux!=valor ){
				pilaAux.push(aux);
			}
			
		}
		
		while (!pilaAux.estaVacia()){
			pila.push(pilaAux.pop());
		}
		
		return pila;
	}
	
	public static Pila llenarPila(Pila pila){
		
		int aux = 0;
		
		pila.push(7);
		pila.push(9);
		pila.push(8);
		pila.push(7);
		pila.push(5);
		pila.push(3);
		pila.push(7);
		pila.push(1);
		pila.push(2);
		pila.push(4);
		
		return pila;
		
	}
	
}

