package generics.ejemplos;

public class MiClaseGenerica<T> {
	
	private T primero;
	
	public MiClaseGenerica() {
		primero = null;
	}

	
	public void setPrimero(T nuevoValor) {
		primero = nuevoValor;
	}
	
	public T getPrimero() {
		return primero;
	}
	
	public static void imprimirPersonas(MiClaseGenerica<? extends Empleado> p) {
		
		Empleado empleado  = p.getPrimero();
		System.out.println(empleado);
		
	}
	
}
