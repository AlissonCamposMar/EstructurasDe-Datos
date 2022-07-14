package co.edu.uniquindio.restaurante.aplication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


public class DialogSaveFile {

	String newCadena = new String("");
	
	public   DialogSaveFile() {
		
	}
	
	
	
	public void execute(String datosPuerto){
		
		
		FileDialog dialog = new FileDialog(new Shell(),SWT.SAVE);
		
		dialog.setFileName("*.txt");
		dialog.open();
		
		
		String ruta = dialog.getFilterPath();
		String nombreArchivo = dialog.getFileName();
		if(ruta != "" && nombreArchivo!="")
	    salvarArchivo(datosPuerto, ruta, nombreArchivo);
	    
	}
	
	



	public void salvarArchivo(String datosPuerto, String ruta , String nombre){
		
		
		try
		{
		//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
		File archivo=new File(ruta+"\\"+nombre);

		//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
		FileWriter escribir=new FileWriter(archivo,true);

		//Escribimos en el archivo con el metodo write 
		escribir.write(datosPuerto);
		
		//Cerramos la conexion
		escribir.close();
		}

		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
		System.out.println("Error al Guardar");
		}
	}
}
