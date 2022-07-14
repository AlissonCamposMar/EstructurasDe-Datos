package generics.ejemplos;

public class UsoPareja {

	public static void main(String[] args) {
		
		MiClaseGenerica<String> generica1 = new MiClaseGenerica<String>();
		
		generica1.setPrimero("Juan");
		System.out.println(generica1.getPrimero());
		
		
		MiClaseGenerica<Persona> generica2 = new MiClaseGenerica<Persona>();
		
		Persona nuevaPersona = new Persona("Pedro");
		generica2.setPrimero(nuevaPersona);
		System.out.println(generica2.getPrimero());


		MiClaseGenerica<Empleado> generica3 = new MiClaseGenerica<Empleado>();
		Empleado nuevaEmpleado = new Empleado("Carlos");
		generica3.setPrimero(nuevaEmpleado);
		
		MiClaseGenerica<Administrador> generica4 = new MiClaseGenerica<Administrador>();
		Administrador nuevaAdmin= new Administrador("Carlos");
		generica4.setPrimero(nuevaAdmin);


		
		MiClaseGenerica.imprimirPersonas(generica4);		
		
	}

}

class Persona{
	
	private String nombre;
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

class Empleado extends Persona{

	public Empleado(String nombre) {
		super(nombre);
	}
}

class Administrador extends Empleado{

	public Administrador(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
}

class JefeCocina extends Empleado{

	public JefeCocina(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
}

class Cliente extends Persona{

	public Cliente(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
}





