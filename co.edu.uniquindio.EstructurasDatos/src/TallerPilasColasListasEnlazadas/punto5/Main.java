package TallerPilasColasListasEnlazadas.punto5;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Pila pila = new Pila();
		Random rnd = new Random();
		
		pila = llenarPila(pila, rnd);
		
		System.out.println("Números de la pila:");
		pila.imprimir();
		
		pila = removeValue(pila, 5);
		
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
			
			if( aux!=valor )
				pilaAux.push(aux);
		}
		
		while (!pilaAux.estaVacia())
			pila.push(pilaAux.pop());
		
		return pila;
	}
	
	public static Pila llenarPila(Pila pila, Random rnd){
		
		int aux = 0;

		for (int i = 0; i < 5; i++) {
			pila.push((int)(rnd.nextDouble()*10.0));
		}
		
		return pila;

	}

}
