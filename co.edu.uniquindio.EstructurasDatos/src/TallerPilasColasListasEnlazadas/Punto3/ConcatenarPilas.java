package TallerPilasColasListasEnlazadas.Punto3;

import java.util.Random;
import java.util.Stack;

public class ConcatenarPilas {
	public static void main (String[] args) {
		Stack <String> pila1 = new Stack <String>();
		Stack <String> pila2 = new Stack <String>();
		Stack <String> pila3 = new Stack <String>();
		Stack <String> pilaAux = new Stack <String>();
	
		Random rnd = new Random();
		
		/**
		 * Apilar
		 */
		System.out.println("pila1");
		pila1.push(Integer.toString(8));
		pila1.push(Integer.toString(4));
		pila1.push(Integer.toString(7));
		System.out.println("pila2");
		pila2.push(Integer.toString(4));
		pila2.push(Integer.toString(4));
		pila2.push(Integer.toString(4));
		
		/**
		 * Desapilar
		 */
		while(!pila2.empty())
			pilaAux.push(Integer.toString(Integer.parseInt(pila2.pop())));
		while(!pila1.empty())
			pilaAux.push(Integer.toString(Integer.parseInt(pila1.pop())));
		while(!pila3.empty())
			pila3.push(Integer.toString(Integer.parseInt(pilaAux.pop())));
	
	System.out.print("Pilas concatenadas");
	}
}
