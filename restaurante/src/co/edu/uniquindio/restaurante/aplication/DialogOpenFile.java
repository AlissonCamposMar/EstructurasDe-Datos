package co.edu.uniquindio.restaurante.aplication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
public class DialogOpenFile {

	String newCadena = new String("");
	BufferedReader buffer = null;
	
	public   DialogOpenFile() {
	    execute();
	}
	
	
	
	
	public void execute(){
		
		
		FileDialog dialog = new FileDialog(new Shell(),SWT.OPEN);
		dialog.open();
		
		String ruta = dialog.getFilterPath();
		String nombreArchivo = dialog.getFileName();
		
		if(ruta != "" && nombreArchivo!="")
		LeerArchivo(ruta, nombreArchivo);

	}
	
	
	public void LeerArchivo (String ruta , String nombre){
		
        try{
        	
        	File file = new File(ruta);
        	
            // Abrimos el archivo
            FileInputStream fstream = new FileInputStream(file+"\\"+nombre);
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea = new String();
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   {
                // Imprimimos la línea por pantalla
            	
            	newCadena+= strLinea+'\r';
                System.out.println (strLinea);
            }
            // Cerramos el archivo
            entrada.close();
        }catch (Exception e){ //Catch de excepciones
            System.err.println("Erro al abrir: ");
        }
		
		
	}




	public String getCadena() {
		return newCadena;
	}
	
	public BufferedReader getbuffer() {
		return buffer;
	}

	
}
