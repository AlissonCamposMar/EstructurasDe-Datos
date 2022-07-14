/*
Un polinomio se puede representar como una lista enlazada. El primer nodo de la lista
representa el primer término del polinomio, el segundo nodo al segundo término del
polinomio y así sucesivamente. Cada nodo tiene como campo dato el coeficiente del
término y el exponente.

Escribir un programa que permita dar entrada a polinomios en x, representándolos con
una lista enlazada simple. A continuación, obtener una tabla de valores del polinomio para
valores de x = 0.0, 0.5, 1.0, 1.5, ..., 5.0.
 */
package tallerListasEnlazadas.puntos;

import javax.swing.JOptionPane;

import tallerListasEnlazadas.listas.ListaSimple;
import tallerListasEnlazadas.listas.Nodo;

/**
 *
 * @author 
 */
public class Punto7 {

	static void menu() {
		JOptionPane.showMessageDialog(null, "Ingrese valor para el coeficiente de x y su exponente");
		String ecuacion = "";
		ListaSimple<Integer[]> lista = new ListaSimple<Integer[]>();

		do {
			int coeficiente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el coeficiente"));
			int exponente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el exponente"));
			
			lista.agregarfinal(new Integer[]{coeficiente, exponente});
			
			ecuacion += "(" + coeficiente + "x^" + exponente + ") + ";
			
		} while (JOptionPane.showConfirmDialog(null, "Ingresar otro?") == JOptionPane.YES_OPTION);
		System.out.println(ecuacion);
		ecuacion = ecuacion.substring(0, ecuacion.length() - 2);
		JOptionPane.showMessageDialog(null, "Para los valores de la ecuación \n "
				+ "" + ecuacion + "\t son \n"
				+ obtenerTablarValor(lista));
	}

    static String obtenerTablarValor(ListaSimple lista) {
        String resultado = "";
        double r = 0;
        
        for (double i = 0; i < 10; i += 0.5) {
            resultado += "\n Para x = " + i;
            r = 0;
            Nodo nodo = lista.getNodoPrimero();

            while (nodo != null) {
                Integer[] valores = (Integer[]) nodo.getValorNodo();
                r += valores[0] * Math.pow(i, valores[1]);
                nodo = nodo.getSiguienteNodo();
            }
            resultado += " el valor de y = " + r;
        }
        return resultado;
    }

	public static void main(String[] args) {
		ListaSimple<Integer[]> listaEnteros = new ListaSimple<>();
		menu();
	}

}

