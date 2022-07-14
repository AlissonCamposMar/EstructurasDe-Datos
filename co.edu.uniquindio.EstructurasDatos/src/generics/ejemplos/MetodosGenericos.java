package generics.ejemplos;

public class MetodosGenericos {

	public static void main(String[] args) {
		
		
		String nombres [] = {"Pedro","Jose","Maria"};
		
		String elementos = MiMatriz.getElemntos(nombres);
		System.out.println(elementos);
		
		Persona listaPersonas[] = {
				new Persona("Juan"),
				new Persona("Pedro"),
				new Persona("Maria"),
				new Persona("Carlos")
		};
		
		String elementos2 = MiMatriz.getElemntos(listaPersonas);
		System.out.println(elementos2);

	}

}

class MiMatriz {
	
	public static <T> String getElemntos(T[] a) {
		
		return "El array tiene "+ a.length + " Elementos";
	}
}
