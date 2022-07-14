package TallerPilasColasListasEnlazadas.Punto2;

import java.util.Iterator;


public class ListaSimple implements Iterable<Nodo> {

    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
	private Nodo primerNodo;
    // Variable para registrar el tamaño de la lista.
    private int tamanio;
    
    /**
     * Constructor por defecto.
     */
    public void ListaSimple() {
	   primerNodo = null;
	   tamanio = 0;
   }

    /**
     * Consulta si la lista esta vacia.
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
	public boolean estaVacia() {
		if (primerNodo == null) {
			return true;
		} else {
			return false;
		}
	}
	
    /**
     * Consulta cuantos elementos (nodos) tiene la lista.
     * @return numero entero entre [0,n] donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getTamanio(){
        return tamanio;
    }
	
    
    /**
     * Agrega un nuevo nodo al inicio de la lista.
     * @param valor a agregar.
     */ 
	public void agregarInicio(int valorNodo) {
		Nodo nuevoNodo = new Nodo();
		nuevoNodo.setValorNodo(valorNodo);
		
		if(estaVacia())
			primerNodo = nuevoNodo;
		else {
			nuevoNodo.setSiguienteNodo(primerNodo);
			primerNodo = nuevoNodo;
		}
		tamanio++;
	}

    /**
     * Agrega un nuevo nodo al final de la lista.
     * @param valor a agregar.
     */
    public void agregarAlFinal(int valorNodo){
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.setValorNodo(valorNodo);
        
        if (estaVacia())
            primerNodo = nuevoNodo;
        else{
            Nodo aux = primerNodo;
            while(aux.getSiguienteNodo() != null){
                aux = aux.getSiguienteNodo();
            }
            aux.setSiguienteNodo(nuevoNodo);
        }
        tamanio++;
    }
	
    /**
     * 
     * @param valorNodo valor del nodo el cual se desea eliminar
     * @return
     */
    public int eliminar(int valorNodo) {
    	Nodo nodo = primerNodo;
    	Nodo previo = null;
    	Nodo siguiente = null;
    	boolean encontrado = false;
    	
    	//Buscar el nodo previo
    	while(nodo != null) {
    		if(nodo.getValorNodo() == valorNodo) {
    			encontrado = true;
    			break;
    		}
    		previo = nodo;
    		nodo = nodo.getSiguienteNodo();
    	}
    	
    	if(encontrado == true) {
    		siguiente = nodo.getSiguienteNodo();
    		
    		if(previo == null) {
    			primerNodo = siguiente;
    			nodo = null;
    		} else {
    			previo.setSiguienteNodo(siguiente);
    			nodo = null;
    		}
    	}
    	return valorNodo;
    }

	//Elimina el primer nodo de la lista
	public int eliminarPrimero() {
		
		if( !estaVacia() ) {
			Nodo n = primerNodo;
		    int valor = n.getValorNodo();
			primerNodo = n.getSiguienteNodo();
			
			if(primerNodo==null) {
//				nodoUltimo = null;
			}
			
			tamanio--;
			return valor;
		}
		
		throw new RuntimeException("Lista vacía");		
	}
	
	public int retornarPrimero () {
		return(primerNodo.getValorNodo());
	}
    
	/**
	 * Imprime en consola la lista enlazada
	 */
	public void imprimirLista() {
		
		if(!estaVacia()) {
			Nodo aux = primerNodo;
			
			while(aux!=null) {
				System.out.print("[" + aux.getValorNodo() + "]");
				aux = aux.getSiguienteNodo();
			}
		}
	}
	

	
    @Override
	public Iterator<Nodo> iterator() {
		// TODO Auto-generated method stub
		return new IteradorListaSimple(primerNodo);
	}
    
    public class IteradorListaSimple implements Iterator<Nodo> {
    	private Nodo nodo;
    	private int posicion;
    	
		public IteradorListaSimple(Nodo nodo) {
			this.nodo = nodo;
			this.posicion = 0;
		}
		
		public boolean hasNext() {
			return nodo != null;
		}
		
    	public Nodo next() {
    		Nodo nodoActual = nodo;
    		nodo = nodo.getSiguienteNodo();
    		posicion++;
    		return nodoActual;
    	}
    }

}
