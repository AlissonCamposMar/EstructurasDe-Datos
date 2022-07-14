package mcd;

import java.util.Scanner;

public class MCD {

	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		System.out.println("Máximo común divisor de dos números");
		System.out.println("Ingrese el primer valor");
		int num1 = leer.nextInt();
		System.out.println("Ingrese el segundo valor");
		int num2 = leer.nextInt();
		
		System.out.println("El MCD de "+num1+" y "+num2+" es: "+mcd(num1, num2));
	}
	
	static int mcd(int num1, int num2) {
		//num1=15	num2=10
		if(num1==0 || num2==0) {
			return 0;
		}
		if (num1==num2) {
			return num1;
		}
		
		if (num1>num2) {
			return mcd(num1-num2, num2);
		} else {
			return mcd(num1, num2-num1);
		}
	}
}
