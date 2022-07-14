package tallerListasEnlazadas.puntos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import tallerListasEnlazadas.listas.ListaDoble;

public class Punto8 {

	public static void main( String[] args ) {
		
		ListaDoble<Double> numeros = new ListaDoble<>();
		
		llenarListaConArchivo( numeros );
		
		double media = hallarMedia( numeros );
		
		double de = hallarDesviacionEstandar( numeros, media );
		
		JOptionPane.showMessageDialog(null, "la media y la desviación estandar son: " + media + " y " + de + " respectivamente", "Info", JOptionPane.INFORMATION_MESSAGE);

	}
	
	private static void llenarListaConArchivo( ListaDoble<Double> lista ) {
		JFileChooser fileChooser = new JFileChooser( "Elija el archivo con los números" );
		fileChooser.showOpenDialog( null );
		File archivo = fileChooser.getSelectedFile();
		BufferedReader br;
		FileReader fr;
		String datos = "";
		String aux = "";
		
		try {
			fr = new FileReader( archivo );
			br = new BufferedReader( fr );
			
			while( ( aux = br.readLine() ) != null ) {
				datos += aux + "\n";
				System.out.println( "." + aux );
			}
			
			br.close();
			
			String[] numeros = datos.split( "\n" );
			for ( int i = 0; i < numeros.length; i++ ) {
				lista.agregarfinal( Double.parseDouble( numeros[i] ) );
			}
			
		} catch( Exception e ) {
			JOptionPane.showMessageDialog( null, "Ha ocurrido un error al leer el archivo", "Error",  JOptionPane.ERROR_MESSAGE );
		}
	}
	
	private static double hallarMedia( ListaDoble<Double> lista ) {
		Iterator<Double> it = lista.iterator();
		double suma = 0;
		
		while( it.hasNext() ) {
			suma += it.next();
		}
		
		return suma / lista.getTamanio();
	}
	
	private static double hallarDesviacionEstandar( ListaDoble<Double> lista, double media ) {
		Iterator<Double> it = lista.iterator();
		double suma = 0;
		double aux = 0;
		
		while( it.hasNext() ) {
			suma += Math.pow( ( it.next() - media ), 2 );
		}
		
		aux = suma / ( lista.getTamanio() - 1 );
		
		return Math.sqrt( aux );
	}

}
