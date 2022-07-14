package Parcial1.Punto3;

public class Main {

	public static void main(String[] args) {
		
		ListaSimple<Integer> lista = new ListaSimple<Integer>();
		ListaSimple<Integer> listaAux = new ListaSimple<Integer>();
		for (int i = 0; i < 3; i++) {
			int aux;
			aux = (int) (Math.random()*50);
			lista.agregarfinal(aux);
		}
		System.out.println("Lista ordenada:"+"\n");
		lista.imprimirLista();
		System.out.println("\n"+"lista invertida:"+"\n");
		listaAux = lista.invertirLista(lista, lista.getNodoPrimero());
		listaAux.imprimirLista();
	}
}
