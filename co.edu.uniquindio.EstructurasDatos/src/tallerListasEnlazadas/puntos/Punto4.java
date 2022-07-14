package tallerListasEnlazadas.puntos;

import java.util.Iterator;

/**
 *
 * @author 
 */
public class Punto4 {

    /**
     * Imprime en consola la lista enlazada
     */
    public static void imprimirLista(ListaDoblementeEnlazada lista) {
        Iterator iterador = lista.iterator();
        while (iterador.hasNext()) {
            System.out.print(iterador.next() + "\t");
        }
        System.out.println();
    }

    /**
     * Imprime en consola la lista enlazada
     */
    public static void imprimirListaHaciaAtras(ListaDoblementeEnlazada lista) {
        Iterator iterador = lista.iterator(lista.fin);
        while (iterador.hasNext()) {
            System.out.print(iterador.next() + "\t");
        }
        System.out.println();
    }

    public class ListaDoblementeEnlazada<T> implements Iterable<T> {

        int tamanio = 0;
        NodoDoblementeEnlazado<T> inicio;
        NodoDoblementeEnlazado<T> fin;

        public NodoDoblementeEnlazado<T> getInicio() {
            return inicio;
        }

        public void setInicio(NodoDoblementeEnlazado<T> inicio) {
            this.inicio = inicio;
        }

        public NodoDoblementeEnlazado<T> getFin() {
            return fin;
        }

        public void setFin(NodoDoblementeEnlazado<T> fin) {
            this.fin = fin;
        }

        public boolean estaVacia() {
            return (inicio == null);
        }

        public void aniadirInicio(T valorNodo) {

            NodoDoblementeEnlazado<T> nuevoNodo = new NodoDoblementeEnlazado<>(valorNodo);

            if (estaVacia()) {
                inicio = nuevoNodo;
                fin = nuevoNodo;
            } else {
                nuevoNodo.setSiguiente(inicio);
                inicio.setAnterior(nuevoNodo);
                inicio = nuevoNodo;
            }
            tamanio++;
        }

        public int getTamanio() {
            return tamanio;
        }

        public void setTamanio(int tamanio) {
            this.tamanio = tamanio;
        }

        public Iterator<T> iterator() {

            return new IteradorListaSimple(inicio);
        }

        public Iterator<T> iterator(NodoDoblementeEnlazado nodo) {
            return new IteradorListaSimple(nodo);
        }

        protected class IteradorListaSimple implements Iterator<T> {

            private NodoDoblementeEnlazado<T> nodo;
            boolean haciaAtras;
            private int posicion;

            public IteradorListaSimple(NodoDoblementeEnlazado<T> nodo) {
                this.nodo = nodo;
                if (nodo.getSiguiente() == null) {
                    haciaAtras = true;
                }
                this.posicion = 0;
            }

            @Override
            public boolean hasNext() {
                return nodo != null;
            }

            @Override
            public T next() {
                T valor = nodo.getValor();
                nodo = (haciaAtras) ? nodo.getAnterior() : nodo.getSiguiente();
                posicion++;
                return valor;
            }

            /**
             * Posici�n actual de la lista
             *
             * @return posici�n
             */
            public int getPosicion() {
                return posicion;
            }

        }
    }

    public class NodoDoblementeEnlazado<T> {

        T valor;
        NodoDoblementeEnlazado<T> anterior;
        NodoDoblementeEnlazado<T> siguiente;

        public NodoDoblementeEnlazado(T valor, NodoDoblementeEnlazado<T> anterior, NodoDoblementeEnlazado<T> siguiente) {
            this.valor = valor;
            this.anterior = anterior;
            this.siguiente = siguiente;
        }

        public NodoDoblementeEnlazado(T valor) {
            this.valor = valor;
        }

        public T getValor() {
            return valor;
        }

        public void setValor(T valor) {
            this.valor = valor;
        }

        public NodoDoblementeEnlazado<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(NodoDoblementeEnlazado<T> anterior) {
            this.anterior = anterior;
        }

        public NodoDoblementeEnlazado<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoDoblementeEnlazado<T> siguiente) {
            this.siguiente = siguiente;
        }

    }

    public static void main(String[] args) {
        Punto4 pre = new Punto4();
        Punto4.ListaDoblementeEnlazada<Integer> listaEnteros;
        listaEnteros = pre.new ListaDoblementeEnlazada<>();
        listaEnteros.aniadirInicio(1);
        listaEnteros.aniadirInicio(2);
        listaEnteros.aniadirInicio(3);
        listaEnteros.aniadirInicio(4);
        listaEnteros.aniadirInicio(5);
        imprimirLista(listaEnteros);
        imprimirListaHaciaAtras(listaEnteros);
    }
}

