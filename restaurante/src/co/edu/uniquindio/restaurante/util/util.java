package co.edu.uniquindio.restaurante.util;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import co.edu.uniquindio.restaurante.controllers.ModelFactoryController;


public class util {
	private static final Logger LOGGER = Logger.getLogger(util.class.getName());	
	
	public static final String path = "persistencia/archivos/";
	public static final String pathBackup = "persistencia/respaldo/";
	public static final String formato = "%s@@%s@@%s@@%s\n";
	
	String newCadena = new String("");		 
	
	public static void crearLogger() {	
		FileHandler archivo, archivoBackup;
		
		try {
			archivo = new FileHandler("persistencia/log/restaurante_log.txt", true);
			archivoBackup = new FileHandler(pathBackup+"/restaurante_log.txt", true);	
			
			LOGGER.setUseParentHandlers(false);	
			
			LOGGER.addHandler(archivo);			
			LOGGER.addHandler(archivoBackup);		
			archivo.setFormatter(new SimpleFormatter());
			archivoBackup.setFormatter(new SimpleFormatter());
			
			
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}						
	}
	
	public static void logging(String INFO,String WARNING,String SEVERE) {
		LOGGER.log(Level.INFO, "INFO: "+INFO);
		LOGGER.log(Level.WARNING, "INFO: "+WARNING);
		LOGGER.log(Level.SEVERE, "INFO: "+SEVERE);
	}		
	
	public static String linealizarArchivo(String ruta) throws IOException {
		FileReader fileReader = new FileReader(path+ruta);
		BufferedReader buffer = new BufferedReader(fileReader);	
		String linea = "";
		String contenido = "";
		contenido += "Reporte de listado de "+ruta+"\n";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 		
		contenido += formatter.format(date)+"\n";
		
		ModelFactoryController modelFactoryController;
		modelFactoryController = ModelFactoryController.getInstance();
		contenido += "Reporte realizado por: "+ modelFactoryController.getAutentificacion()+ "\n" + "\n";
				
		contenido += "Información del reporte: "+"\n";
		
		while ((linea = buffer.readLine()) != null) { 
			contenido += String.join(" - ", linea.split("@@")) + "\n";
		}
		
		buffer.close();
		fileReader.close();
		
		return contenido;	
	}
	
	public static void exportarArchivo(String datosPuerto){
		FileDialog dialog = new FileDialog(new Shell(),SWT.SAVE);
		
		dialog.setFileName("*.txt");
		dialog.open();
				
		String ruta = dialog.getFilterPath();
		String nombreArchivo = dialog.getFileName();
		if(ruta != "" && nombreArchivo!="")
	    salvarArchivo(datosPuerto, ruta, nombreArchivo);
	    
	}
	public static void salvarArchivo(String datosPuerto, String ruta , String nombre){
		try {
			File archivo=new File(ruta+"\\"+nombre);
			FileWriter escribir=new FileWriter(archivo,true);
			escribir.write(datosPuerto);
			escribir.close();
		}

		catch(Exception e) {
			System.out.println("Error al Guardar");
		}
	}
	
	public static void escribirArchivo(String ruta, String... args) {
		FileWriter archivoSalida, archivoBackup;
		
		try {
			archivoSalida = new FileWriter(path+ruta, true); 					
			util.almacenarDatos(archivoSalida, formato, args[0], args[1], args[2], args[3]);			
			
			archivoBackup = new FileWriter(pathBackup+ruta, true); 
			util.almacenarDatos(archivoBackup, formato, args[0], args[1], args[2], args[3]);	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
	
	public static void actualizarArchivo(String ruta, String fijo, boolean omitir, String... elementos) throws IOException {
		ArrayList<String> contenido = util.leerArchivo(path+ruta);
		String[] contenidoSplit;
		FileWriter archivoSalida, archivoBackup;
		
		try {
			archivoSalida = new FileWriter(path+ruta, false);
			archivoBackup = new FileWriter(pathBackup+ruta, false);
			
			for (String linea : contenido) { 
				contenidoSplit = linea.split("@@");
				
				if (contenidoSplit[2].equalsIgnoreCase(fijo)) {
					if (!omitir) util.escribirArchivo(ruta, elementos[0], elementos[1], elementos[2], elementos[3]);
				} else {
					util.escribirArchivo(ruta, contenidoSplit[0], contenidoSplit[1], contenidoSplit[2], contenidoSplit[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}							
	}
	
	public static void almacenarDatos(FileWriter rutaArchivo, String formato, Object... datos) throws IOException {
		Formatter archivo;
		
		archivo = new Formatter(rutaArchivo);
		archivo.format(formato, datos[0], datos[1], datos[2], datos[3]);
		archivo.flush();
		archivo.close();				
	}
	

	public static ArrayList<String> leerArchivo(String ruta) throws IOException {
		ArrayList<String> contenido = new ArrayList<String>();
		FileReader fileReader = new FileReader(ruta);
		BufferedReader buffer = new BufferedReader(fileReader);
		
		String linea = "";
		while ((linea = buffer.readLine()) != null) {
			contenido.add(linea);
		}
		
		buffer.close();
		fileReader.close();
		
		return contenido;		
	}
	
	@SuppressWarnings("unchecked")
	public static Object cargarRecursoSerializado(String rutaArchivo)throws Exception 
	{
		Object aux = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

			aux = ois.readObject();

		} catch (Exception e2) {
			throw e2;
		} finally {
			if (ois != null)
				ois.close();
		}
		return aux;
	}
	
	
	public static void salvarRecursoSerializado(String rutaArchivo, Object object) throws Exception {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo)); 
			oos.writeObject(object);
		} catch (Exception e) {
			throw e;
		} finally {
			if (oos != null)
				oos.close();
		}
	}
	

	
	
	public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

		XMLDecoder decodificadorXML;
		Object objetoXML;
		
		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
		objetoXML = decodificadorXML.readObject();
		decodificadorXML.close();
		return objetoXML;
		
	}

	public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {
		
		XMLEncoder codificadorXML;
		
		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.writeObject(objeto);
		codificadorXML.close();
		
	}
	
}
