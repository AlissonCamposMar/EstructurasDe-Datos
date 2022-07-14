package generics.ejemplos;

import java.io.File;

public class UsoArrayList {

	public static void main(String[] args) {
		
		ArrayList lista1 = new ArrayList(5);
		
		lista1.add("Juan");
		lista1.add("Maria");
		lista1.add("Ana");
		lista1.add("Juan");
		
		String nombrePersona = (String) lista1.get(0);
		System.out.println(nombrePersona);
		
		
		ArrayList lista2 = new ArrayList(3);
		
		lista2.add(new File("src/archivo.txt"));
		
		lista1.add(new File("src/archivo.txt"));
		
		
		File nombreArchivo = (File)lista2.get(0);
		System.out.println(nombreArchivo);
		
		nombrePersona = (String) lista1.get(4);
		
	}
	
	
	
	
	


}
