package generics.maleta;

public class Principal {
	
	
	public static void main(String[] args) {
		
		Maleta maleta = new Maleta(5);
		Objeto o1 = new Objeto("Regalo");
		Objeto o2 = new Objeto("Dulce");
		Objeto o3 = new Objeto("Reloj");

		Prenda p1= new Prenda("Marca1");
		Prenda p2= new Prenda("Marca2");
		
		
		maleta.add(o1);
		maleta.add(o2);
		maleta.add(o3);
		maleta.add(p1);
		maleta.add(p2);
		
		for (Object o :maleta) 
		{
			if( o instanceof Objeto) {
				Objeto objeto= (Objeto)o;
				System.out.println(objeto.getNombre());
			}else {
				Prenda prenda= (Prenda)o;
				System.out.println(prenda.getMarca());
			}
		}
	}
}