package generics.ejemplos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main1 {

	public static void main(String[] args) 
	{

		
		
//		ejemplo1();
//		ejemplo2();
//		ejemplo3_Box();
		ejemplo4_Box();
		

//		Operacion<Number> operacion = new Operacion<Number>();
//		List<Number> listNumber = new ArrayList<Number>();
//		
//		listNumber.add(5);
//		listNumber.add(6);
//		System.out.println(operacion.sumar(2, 3));
//		System.out.println(operacion.sumar(5.1, 3.4));
//		System.out.println(operacion.sumar(listNumber));
		
		
		//Comodin ? =>bounded wildcard (comodín delimitado superior).
//		List<Integer> integerList = Arrays.asList(1, 2, 3);
//		System.out.println("sum = " + sum(integerList));
//
//		List<Double> doubleList = Arrays.asList(1.2, 2.3, 3.5);
//		System.out.println("sum = " + sum(doubleList));
		
		
		
		//Comodin ilimitado 
		List<Integer> integerList2 = Arrays.asList(1, 2, 3);
		printAll(integerList2);
		
		List<Double> doubleList2 = Arrays.asList(1.2, 2.3, 3.5);
		printAll(doubleList2);
		
		List<String> stringList2 = Arrays.asList("hola","hola2","hola3");
		printAll(stringList2);
		

	}

	private static void ejemplo1() {

		ArrayList lista = new ArrayList();		
		
		lista.add(22);
//		lista.add("Hola mundo");
		lista.add("Estructuras de datos");
		lista.add("Universidad del Quindío");
		
		String cadena = (String)lista.get(0);
		System.out.println(cadena);

	}
	
	private static void ejemplo1_ArraysDefindos() {

		
		ArrayList<String> lista = new ArrayList<String>();		
		
//		
		lista.add("Hola mundo");
		lista.add("Estructuras de datos");
		lista.add("Universidad del Quindío");
//		lista.add(22);
		
		String cadena = (String)lista.get(0);
		System.out.println(cadena);

	}

	
	
	private static void ejemplo2() {

		// Instanciación de la clase genérica para Integer
		// No se puede usar int porque no es una clase
		Par<Integer> p = new Par<Integer>(1,2);
		// Instanciación de la clase genérica para Character 	
		Par<Character> p1 = new Par<Character>('a','b');
		
		Par<String> p2 = new Par<String>("hola","mundo");
		
		p = p.swap();
		p1 = p1.swap();	
		p2 = p2.swap();
	}
	
	
	
	private static void ejemplo3_Box() {
		
		Box<Integer> integerBox1 = new Box<Integer>();
		Box<Integer> integerBox2 = new Box<>();
		
		integerBox1.get();
		integerBox2.get();
		
		Box rawBox=new Box();
		
		rawBox.get();
	}
	
	
	private static void ejemplo4_Box() {

		Box2<Integer, String> box = new Box2<Integer, String>();
		box.add(Integer.valueOf(10),"Hello World");
		System.out.printf("Integer Value :%d\n", box.getFirst());
		System.out.printf("String Value :%s\n", box.getSecond());

		Pair<String, Integer> pair = new Pair<String, Integer>(); 
		pair.addKeyValue("1", Integer.valueOf(10));
		System.out.printf("(Pair)Integer Value :%d\n", pair.getValue("1"));

		CustomList<Box2> list = new CustomList<Box2>();
		list.addItem(box);
		System.out.printf("(CustomList)Integer Value :%d\n", list.getItem(0).getFirst());
	}

	
	public static double sum(List<? extends Number> numberlist){
		double sum = 0.0;
		for (Number n : numberlist) sum += n.doubleValue();
		return sum;
	}


	public static void printAll(List<?> list) {
		for (Object item : list)
			System.out.println(item + " ");
	}
	
}

class Box2<T, S> {
	   private T t;
	   private S s;

	   public void add(T t, S s) {
	      this.t = t;
	      this.s = s;
	   }

	   public T getFirst() {
	      return t;
	   } 

	   public S getSecond() {
	      return s;
	   } 
	}

	class Pair<K,V>{
	   private Map<K,V> map = new HashMap<K,V>();

	   public void addKeyValue(K key, V value) {
	      map.put(key, value);
	   }

	   public V getValue(K key) {
	      return map.get(key);
	   }
	}

	class CustomList<E>{
		
	   private List<E> list = new ArrayList<E>();

	   public void addItem(E value) {
	      list.add(value);
	   }

	   public E getItem(int index) {
	      return list.get(index);
	   }
	}
