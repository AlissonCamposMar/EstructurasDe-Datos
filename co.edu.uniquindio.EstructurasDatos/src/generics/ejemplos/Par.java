package generics.ejemplos;

public class Par<T> {
	private T a, b;
	
	public Par(T a, T b){
		this.a= a;
		this.b= b;
	}
	public Par<T> swap (){
		return new Par<T>(b, a);
	}
}