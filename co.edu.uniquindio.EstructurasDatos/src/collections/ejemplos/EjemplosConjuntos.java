package collections.ejemplos;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import collections.clases.Cliente;

public class EjemplosConjuntos {

	public static void main(String[] args) {

//		ejemplo1_HashSet();
//		ejemplo2_HashSet();

//		ejemplo1_TreeSet();
//		ejemplo2_TreeSet();
//		ejemplo3_TreeSet();

		ejemplo_HashSet_TreeSet_LinkedHashSet();

	}

	private static void ejemplo1_HashSet() {


		Set<Cliente> listaClientes = new HashSet<>();


		Cliente cliente1 = new Cliente();
		cliente1.setNombres("Juan");

		Cliente cliente2 = new Cliente();
		cliente2.setNombres("juan");

		Cliente cliente3 = new Cliente();
		cliente3.setNombres("Alberto");

		listaClientes.add(cliente1);
		listaClientes.add(cliente2);
		listaClientes.add(cliente3);


		for (Cliente cliente : listaClientes) {
			System.out.println("Clientes: " +cliente.getNombres());

		}



	}

	private static void ejemplo2_HashSet() {


		Set<String> hs = new HashSet<>();
		System.out.println("aca");
		hs.add("Alonso");
		hs.add("alonso");
		hs.add("Conchi");
		hs.add("Emilio");
		hs.add("Gómez");
		hs.add("manuela");
		hs.add("Manuela");

		System.out.println(hs);

	}

	private static void ejemplo1_TreeSet() {

		class Comparacion implements Comparator {
			public int compare(Object o1, Object o2) {
				//Compara cadenas ignorando las may/min
				int resultado = ((String) o1).compareToIgnoreCase((String) o2);
				return resultado;
			}
		}

//		TreeSet ts = new TreeSet(new Comparacion());
		TreeSet ts = new TreeSet();
		System.out.println("aca");
		ts.add("Alonso");
		ts.add("alonso");
		ts.add("Conchi");
		ts.add("Emilio");
		ts.add("Gómez");
		ts.add("manuela");
		ts.add("Manuela");
		System.out.println(ts);

	}



	private static void ejemplo2_TreeSet() {
//		Crear dos conjuntos ordenados cuyos elementos sean cadenas de caracteres (String). Realizar
//		operaciones de insertar, eliminar, obtener subconjunto

//		class Compara implements Comparator
//		{
//			public int compare (Object x1, Object x2)
//			{
//				String c1 = (String) x1;
//				String c2 = (String) x2;
//				c1 = c1.toUpperCase();
//				c2 = c2.toUpperCase();
//				return c1.compareTo(c2);
//			}
//		}

		String [] cad1 = {"Libro", "Mesa", "mes", "Papel", "Papelera","armario", "globo"};
		String [] cad2 = {"lupi", "Maria", "angela", "Julian","Esperanza", "Miguel", "maite", "marta"};




		TreeSet<String> conjunto1, conjunto2;
		conjunto1 = new TreeSet<String>();
//		conjunto2 = new TreeSet(new Compara());


		for (int i = 0; i < cad1.length; i++)
			conjunto1.add(cad1[i]);

//		for (int i = 0; i < cad2.length; i++)
//			conjunto2.add(cad2[i]);

		System.out.println("Conjunto ordenado, sin comparador: "+ conjunto1);
//		System.out.println("Conjunto ordenado, con comparador: "+ conjunto2);
		System.out.println("-----------------------------------------------"+"\n");

		// eliminar un elemento
		if (conjunto1.remove("Mes"))
			System.out.println("Eliminado - Mes - de conjunto 1 "+ conjunto1);
		else
			System.out.println("No se encuentra - Mes - en conjunto 1 "+ conjunto1);
		System.out.println("-----------------------------------------------"+"\n");


//		if (conjunto2.remove("MAITE"))
//			System.out.println("Eliminado - MAITE - de conjunto 2 "+ conjunto2);
//		else
//			System.out.println("No se encuentra - MAITE - en conjunto 2" + conjunto2);
//
//		System.out.println("-----------------------------------------------"+"\n");

		// Obtener un subconjunto y operaciones
		Set sc1;
		sc1 = conjunto1.subSet("Libro", "armario");
		System.out.println("Subconjunto : " + sc1);
		sc1.add("Marta");
		System.out.println("Subconjunto modificado: " + sc1);
		System.out.println("Conjunto origen modificado: " + conjunto1);
		conjunto1.add("lupi"); conjunto1.add("Luna");
		System.out.println("Subconjunto modificado: " + sc1);
		System.out.println("Conjunto origen modificado: " + conjunto1);
	}

	private static void ejemplo3_TreeSet() {
//		Crear dos conjuntos ordenados cuyos elementos sean cadenas de caracteres (String). Realizar
//		operaciones de insertar, eliminar, obtener subconjunto

		class Compara implements Comparator
		{
			public int compare (Object x1, Object x2)
			{
				String c1 = (String) x1;
				String c2 = (String) x2;
				c1 = c1.toUpperCase();
				c2 = c2.toUpperCase();
				int resultado = c1.compareTo(c2);
				return resultado;
			}
		}

		String [] cad2 = {"lupi", "Maria", "maria", "Julian","Esperanza", "Miguel", "maite", "marta"};


		TreeSet<String> conjunto2;
		conjunto2 = new TreeSet(new Compara());


		for (int i = 0; i < cad2.length; i++)
			conjunto2.add(cad2[i]);

		System.out.println("Conjunto ordenado, con comparador: "+ conjunto2);
		System.out.println("-----------------------------------------------"+"\n");



		if (conjunto2.remove("MAITE"))
			System.out.println("Eliminado - MAITE - de conjunto 2 "+ conjunto2);
		else
			System.out.println("No se encuentra - MAITE - en conjunto 2" + conjunto2);

		System.out.println("-----------------------------------------------"+"\n");

	}

	private static void ejemplo_HashSet_TreeSet_LinkedHashSet() {


		 Set<Integer> conjunto1 = new HashSet<Integer>();
	        conjunto1.add(20);
	        conjunto1.add(10);
	        conjunto1.add(1);
	        conjunto1.add(5);
	        // El valor 20 no se inserta en el conjunto ya que se encuentra repetido
	        conjunto1.add(20);
	        // La impresión no asegura un orden específico
	        for (int elemento : conjunto1)
	            System.out.print(elemento + " - ");
	        System.out.println();

	        Set<Integer> conjunto2 = new TreeSet<Integer>();
	        conjunto2.add(20);
	        conjunto2.add(10);
	        conjunto2.add(1);
	        conjunto2.add(5);
	        // El valor 20 no se inserta en el conjunto ya que se encuentra repetido
	        conjunto2.add(20);
	        // Los elementos se muestran de menor a mayor
	        for (int elemento : conjunto2)
	            System.out.print(elemento + " - ");
	        System.out.println();

	        Set<Integer> conjunto3 = new LinkedHashSet<Integer>();
	        conjunto3.add(20);
	        conjunto3.add(10);
	        conjunto3.add(1);
	        conjunto3.add(5);
	        // El valor 20 no se inserta en el conjunto ya que se encuentra repetido
	        conjunto3.add(20);
	        // Los elementos se muestran en el orden que se insertaron
	        for (int elemento : conjunto3)
	            System.out.print(elemento + " - ");
	        System.out.println();

	}


}