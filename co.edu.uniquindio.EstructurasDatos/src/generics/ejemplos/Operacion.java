package generics.ejemplos;

import java.util.List;

public class Operacion <T extends Number>
{
	
	public Number sumar(T a, T b) 
	{
		return a.doubleValue() + b.doubleValue();
	}
	
	
	public double sumar(List<? extends Number> list) {
		
		double sum = 0;
		for (Number number : list) {
			
			sum+= number.doubleValue();
		}
		return sum;
		
	}

}
