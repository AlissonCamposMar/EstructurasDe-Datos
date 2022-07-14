package co.edu.uniquindio.ejercicios.ejercicio02;

import java.util.Iterator;

import co.edu.uniquindio.listaSimple.pilas.Nodo;
import co.edu.uniquindio.listaSimple.pilas.Pila;
import co.edu.uniquindio.listaSimpleGenerics.ListaSimple;

public class Ejercicio02 {

	public static void main(String[] args) {
		ListaSimple<Integer> numerosAleatorios = new ListaSimple<Integer>();;
		for (int i = 0; i < 10; i++) {
			int aux = (int) (Math.random()*50);
			numerosAleatorios.agregarInicio(aux);
		}
		
		Pila<Integer> pila = new Pila<Integer>();
		Iterator<Integer> iter = numerosAleatorios.iterator();
		System.out.println("Números de la lista enlazada simple"+"\n");
		while (iter.hasNext()) {
			int num = iter.next();
			System.out.println("Número:"+num);
			pila.push(num);
		}
		System.out.println("\n"+"Número de la pila"+"\n");
		pila.imprimir();
	}

}
