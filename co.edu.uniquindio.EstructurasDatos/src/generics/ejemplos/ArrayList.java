package generics.ejemplos;

public class ArrayList {

	private Object[] datosElemento;
	private int i = 0;


	public ArrayList(int valor) {
		datosElemento = new Object[valor];
	}

	public Object get(int i) {
		return datosElemento[i];
	}

	public void add(Object objeto) {
		datosElemento[i] = objeto;
		i++;
	}

}
