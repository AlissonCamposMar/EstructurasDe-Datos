package TallerPilasColasListasEnlazadas.Punto1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindromo {
	public static void main(String [] a) {
		PilaLineal pilaChar;
		boolean esPalindromo;
		String pal;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			// crea pila vac�a
			pilaChar = new PilaLineal();
			System.out.print("Teclea la palabra" + " a verificar si es pal�ndromo: ");
			pal = entrada.readLine();
			
			// se crea la pila con los caracteres de la palabra
			for (int i = 0; i < pal.length(); ) {
				Character c;
				c = new Character(pal.charAt(i++));
				pilaChar.insertar(c);
			}

			// se comprueba si es pal�ndromo
			// charAt() devuelve al caracter con el indice definido
			esPalindromo = true;
			for (int j = 0; esPalindromo && !pilaChar.pilaVacia(); ) {
				Character c;
				c = (Character) pilaChar.quitar();
				esPalindromo = pal.charAt(j++) == c.charValue();
			}
			pilaChar.limpiarPila();
			if (esPalindromo)
				System.out.println("La palabra " + pal + " es un pal�ndromo \n");
			else
				System.out.println("La palabra " + pal + " no es un pal�ndromo \n");
		} catch (Exception er) {
			System.err.println("Excepcion: " + er);
		}
	}
}