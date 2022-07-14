package generics.maleta.generics;

import java.util.Iterator;

import generics.maleta.Objeto;
import generics.maleta.Prenda;

public class Principal {
	
	public static void main(String[] args) {
		
		Maleta <Objeto> maleta = new Maleta<Objeto>(4);
		Objeto o = new Objeto("Regalo");
		Objeto o1 = new Objeto("dulce");
		Objeto o2 = new Objeto("Reloj");
		
		
		Prenda p1= new Prenda("Marca1");
		Prenda p2= new Prenda("Marca2");
		
		maleta.add(o);
		maleta.add(o1);
		maleta.add(o2);
//		maleta.add(p1);
//		maleta.add(p2);
	
		
		for (Objeto objeto: maleta) {
			System.out.println(objeto.getNombre());
		}
		
		
		
	}
}