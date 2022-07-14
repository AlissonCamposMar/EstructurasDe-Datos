package co.edu.uniquindio.ejercicios.ejercicio04;

import co.edu.uniquindio.colas.Cola;
import co.edu.uniquindio.listaSimple.pilas.Pila;

public class Ejercicio04 {

	public static void main(String[] args) {
		Pila<Integer> pila = new Pila<Integer>();
		Cola<Integer> cola = new Cola<Integer>();
		Pila<Integer> pilaAux = new Pila<Integer>();
		for (int i = 0; i < 10; i++) {
			int aux;
			aux = (int) (Math.random()*50);
			System.out.println(aux);
			pila.push(aux);
		}
		
		for (int i = 0; i < 10; i++) {
			int aux = pila.pop();
			boolean res = isPar(aux);
			if (res) {
				cola.encolar(aux);
			}else{
				pilaAux.push(aux);
			}
		}
		
		pila.agregar(pilaAux);
		imprimir(pila, cola);
	}
	
	public static boolean isPar(int num){
		if (num%2==0) {
			return true;
		}
		return false;
	}
	
	public static void imprimir(Pila<Integer> pila, Cola<Integer> cola) {
		System.out.println("Numeros de la pila que no son pares"+"\n");
		pila.imprimir();
		System.out.println("\n"+"Numeros de la pila que son pares"+"\n");
		
		cola.imprimir();
	}

}
