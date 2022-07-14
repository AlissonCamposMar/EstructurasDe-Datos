package mayor.min.div.vencer;

public class MayMenDivVen {
	
	public static void main(String[] args) {
		
		int[] arreglo = {1, 2, 3, 4, 5, -9, 0, -10};
		
		int min = hallarMinimo(0, arreglo.length - 1, arreglo);
		int max = hallarMaximo(0, arreglo.length - 1, arreglo);
		
		int[] minMax = minMax(0, arreglo.length - 1, arreglo);
		
		System.out.println("Minimo (fcuncion1): " + min);
		System.out.println("Maximo (fcuncion2): " + max);
		
		System.out.println("Minimo (fcuncion3): " + minMax[0]);
		System.out.println("Minimo (fcuncion4): " + minMax[1]);
	}
	
	//
	public static int hallarMinimo(int inicio, int fin, int[] arreglo) {
		
		if(inicio == fin) {
			return arreglo[inicio];
		} else {
			
			int mitad = (inicio + fin)/2;
			int x = hallarMinimo(inicio, mitad, arreglo);
			int y = hallarMinimo(mitad +1, fin, arreglo);
		
			if(x < y) {
				return x;
			} else {
				return y;
			}
		}
	}
	
	public static int hallarMaximo(int inicio, int fin, int[] arreglo) {
		
		if(inicio == fin) {
			return arreglo[inicio];
		} else {
			
			int mitad = (inicio + fin)/2;
			int x = hallarMaximo(inicio, mitad, arreglo);
			int y = hallarMaximo(mitad +1, fin, arreglo);
		
			if(x > y) {
				return x;
			} else {
				return y;
			}
		}
	}
	
	//devuelve dos numeros para ser comparados
	public static int[] minMax(int inicio, int fin, int[] arreglo) {
		
		//secogen dos posiciones
		int[] minMax = new int [2];
		
		//si se cumple se pueden coger los dos valores, uno será el mayor(fin) y el otro el menor(inicio)
		if(fin - inicio <= 1) {
			
			//menor
			minMax[0] = arreglo[inicio];
			
			//mayor
			minMax[1] = arreglo[fin];
			
			//se intercambian valores si minMax[0] no llegara a ser el menor
			//se tiene en cuenta que en l posicion 0 siempre debe estar el menor
			if(minMax[0] > minMax[1]) {
				
				int aux = minMax[0];
				minMax[0] = minMax[1];
				minMax[1] = aux;
			}
			

			
		} else {
			
			int mitad = (inicio + fin)/2;
			int[] minMax1 = minMax(inicio, mitad, arreglo);
			int[] minMax2 = minMax(mitad +1, fin, arreglo);

			//comparar en los dos el menor
			//ejm: la posición 0 del minMax 1 y 2 es el menor
			//el minMax debe guardar el minimo
			
			//se compara minimo con minimo para guardarlo en la posicion 0
			
			if(minMax1[0] < minMax2[0]) {
				minMax[0] = minMax1[0];
				
			} else {
				minMax[0] = minMax2[0];
			}
			
			//se compara maximo con maximo
			if(minMax1[1] > minMax2[1]) {
				minMax[1] = minMax1[1];
				
			} else {
				minMax[1] = minMax2[1];
			}
		}
		return minMax;
	}

}
