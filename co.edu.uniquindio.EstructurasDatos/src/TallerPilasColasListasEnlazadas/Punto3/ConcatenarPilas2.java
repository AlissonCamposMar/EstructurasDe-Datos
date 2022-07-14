package TallerPilasColasListasEnlazadas.Punto3;

import java.util.Random;
import java.util.Stack;

public class ConcatenarPilas2 {

	static Stack <String> pila1 = new Stack <String>();
	static Stack <String> pila2 = new Stack <String>();
	static Stack <String> pila3 = new Stack <String>();
	static Stack <String> pilaAux = new Stack <String>();
	static Stack <String> pilaAux2 = new Stack <String>();
	
	public static boolean buscar(int dato) {
		boolean band = false;
		int d;
		while(pilaAux.size() >0) {
			d = Integer.parseInt(pilaAux.pop());
			if(dato ==d)
				band = true;
			pilaAux2.push(Integer.toString(d));
		}
		while (pilaAux2.size() > 0) {
			pilaAux.push(pilaAux2.pop());
		}
		return band;
	}
	
	public static void main (String[] args) {
	
		int dato;
		Random rnd = new Random();
		
		/**
		 * Apilar
		 */
		
//		 pila1.push(Integer.toString(8));
//		 pila1.push(Integer.toString(4));
//		 pila1.push(Integer.toString(7));
//		 pila2.push(Integer.toString(4));
//		 pila2.push(Integer.toString(4));
//		 pila2.push(Integer.toString(4));
		 

		/**
		 * El método Java.util.Stack.push (elemento E) se utiliza para insertar un elemento en la pila.
		 * El elemento se coloca en la parte superior de la pila.
		 * Sintaxis:STACK.push ( elemento E )
		 * Parámetros: el método acepta un elemento de parámetro de tipo Pila y se refiere al elemento que se
		 * va a insertar en la pila.
		 * Valor de retorno: el método devuelve el argumento pasado
		 */
		//(int)(rnd.nextDouble()*10.0) genera números enteros aleatorios
		for(int i = 0; i < 5; i++) {
			pila1.push(Integer.toString((int)(rnd.nextDouble()*10.0)));
		}
		
		for(int i = 0; i < 5; i++) {
			pila2.push(Integer.toString((int)(rnd.nextDouble()*10.0)));
		}
		
		/**
		 * Desapilar
		 */
		while(pila1.size() > 0) {
			dato = Integer.parseInt(pila1.pop());
			pila3.push(Integer.toString(dato));
			pilaAux.push(Integer.toString(dato));
		}
		
		while(pila2.size() > 0) {
			dato = Integer.parseInt(pila2.pop());
			
			if(!buscar(dato))
				pilaAux.push(Integer.toString(dato));
		}
		while(pila3.size() > 0)
			System.out.print("Dato: " + Integer.parseInt(pila3.pop()) + "\n");
	
	System.out.print("Pilas concatenadas");
	}
}
