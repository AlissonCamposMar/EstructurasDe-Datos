package Parcial1.Punto5;

public class MetodosDelParcial {
 
	public int determinarMaximaDistancia(int valor, ListaEnlazada<Integer> enteros) {

		int cantX = 0;
		int maxDistancia = 0;
		int distancia = 0;
		int i = 0;

		enteros.getNodoPrimero();

		return determinarMaximaDistancia(valor, enteros, cantX, maxDistancia, distancia, i);

	}

	private int determinarMaximaDistancia(int valor, ListaEnlazada<Integer> enteros, int cantX, int maxDistancia,
			int distancia, int i) {

		if (i >= enteros.getTamanio()) {
			if (cantX <= 1) {
				return maxDistancia = 0;
			} else {
				return maxDistancia;
			}
		}

		else if (enteros.obtenerPosicionNodo(null) == valor) {
			enteros.iterator();
			if (distancia > maxDistancia) {
				maxDistancia = distancia;
				distancia = 0;

				return determinarMaximaDistancia(valor, enteros, cantX + 1, maxDistancia, distancia + 1, i + 1);
			} else {
				return determinarMaximaDistancia(valor, enteros, cantX + 1, maxDistancia, distancia + 1, i + 1);
			}

		} else {
			enteros.iterator();
			if (cantX == 0) {
				return determinarMaximaDistancia(valor, enteros, cantX, maxDistancia, distancia, i + 1);
			} else {
				return determinarMaximaDistancia(valor, enteros, cantX, maxDistancia, distancia + 1, i + 1);
			}
		}

	}
}
