package TallerPilasColasListasEnlazadas.Punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindromo2 {
	public static boolean esPalindromo(String cadena) {
		if (cadena.length() <2) return true;
		else return (
		(cadena.charAt(0) == cadena.charAt(cadena.length()-1) )
		&& (esPalindromo(cadena.substring(1,cadena.length()-1)))
		);
		}

		public static void main(String args []) throws IOException {
		System.out.println("Introduzca una palabra:");
		
		BufferedReader userIn = new BufferedReader(new InputStreamReader( System.in ) );
		String n=userIn.readLine();
		System.out.println((esPalindromo(n)?"E":"No e")+"s palíndromo");
		}
}