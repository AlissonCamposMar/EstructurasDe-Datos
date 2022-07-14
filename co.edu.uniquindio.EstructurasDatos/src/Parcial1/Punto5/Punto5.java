package Parcial1.Punto5;

import java.util.Random;

public class Punto5{
public static void main(String[] args) {
	// TODO Auto-generated method stub
	ListaEnlazada<Integer> enteros = new ListaEnlazada<Integer>();
	Random rnd = new Random();

	
	for (int i = 0; i < 10; i++) {
    	enteros.agregarInicio((int)(rnd.nextDouble() * 10.0));
	}
	enteros.imprimirLista();
	System.out.println(enteros);
	int valor = 5;
	System.out.println("\nLa mayor distancia del valor " + valor + " es:\n");
	
	MetodosDelParcial ejercicio5 = new MetodosDelParcial();
	
	
	int distancia = ejercicio5.determinarMaximaDistancia(valor, enteros);
	System.out.println(distancia);
	

}
			
}