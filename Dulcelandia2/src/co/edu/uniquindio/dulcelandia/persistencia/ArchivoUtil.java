package co.edu.uniquindio.dulcelandia.persistencia;

import java.beans.XMLDecoder;	
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import co.edu.uniquindio.dulcelandia.controllers.ModelFactoryController;


/**
 * Esta clase tiene metodo estaticos que permite usarlos sin crear instancias de la clase 
 * Lo que se hizo fue crear esta libreria para el manejo de los archivos
 * @author Admin
 *
 */
public  class ArchivoUtil {

	static String fechaSistema = "";
	public static final String path = "persistencia/archivos/";
	
	public static String generarTexto(String ruta) throws IOException {
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
	
	/**
	 * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
	 * @param ruta es la ruta o path donde esta ubicado el archivo
	 * @throws IOException 
	 */
	public static void guardarArchivo(String ruta,String contenido, boolean flagSobreEscribir) throws IOException {
		
		FileWriter fw = new FileWriter(ruta,flagSobreEscribir);
		BufferedWriter bfw = new BufferedWriter(fw); 
		
		//escribir en el archivo
		bfw.write(contenido);
		
		bfw.close();
		fw.close();
	}

	/**
	 * ESte metodo retorna el contenido del archivo ubicado en una ruta,con la lista de cadenas.
	 * @param ruta
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> leerArchivo(String ruta) throws IOException {

		ArrayList<String>  contenido = new ArrayList<>();
		FileReader fr=new FileReader(ruta);
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";
		
		while((linea = bfr.readLine())!=null) {
			contenido.add(linea);
		}
		
		bfr.close();
		fr.close();
		return contenido;
	}
	
	
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo) {
		String log = "";
		Logger LOGGER = Logger.getLogger(accion);
		FileHandler fileHandler =  null;
		
		cargarFechaSistema();

		try {

			fileHandler = new FileHandler(rutaArchivo,true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);

			switch (nivel) {
			case 1:
				LOGGER.log(Level.INFO,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			case 2:
				LOGGER.log(Level.WARNING,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			case 3:
				LOGGER.log(Level.SEVERE,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			default:
				break;
			}

		} catch (SecurityException e) {

			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		}
		finally {
			
			fileHandler.close();
		}

	}
	
	private static void cargarFechaSistema() {

		String diaN = "";
		String mesN = "";
		String anioN = "";

		Calendar cal1 = Calendar.getInstance();


		int dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int anio = cal1.get(Calendar.YEAR);
		int hora = cal1.get(Calendar.HOUR);
		int minuto = cal1.get(Calendar.MINUTE);


		if(dia < 10){
			diaN+="0"+dia;
		}
		else{
			diaN+=""+dia;
		}
		if(mes < 10){
			mesN+="0"+mes;
		}
		else{
			mesN+=""+mes;
		}

		//		fecha_Actual+= anio+"-"+mesN+"-"+ diaN;
		//		fechaSistema = anio+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
		fechaSistema = anio+"-"+mesN+"-"+diaN;
		//		horaFechaSistema = hora+"-"+minuto;
	}

	
	
	
	//------------------------------------SERIALIZACIÓN  y XML
	/**
	 * Escribe en el fichero que se le pasa el objeto que se le envia
	 * 
	 * @param rutaArchivo
	 *            path del fichero que se quiere escribir
	 * @throws IOException
	 */
	
	@SuppressWarnings("unchecked")
	public static Object cargarRecursoSerializado(String rutaArchivo)throws Exception {
		Object aux = null;
//		Empresa empresa = null;
		ObjectInputStream ois = null;
		try {
			// Se crea un ObjectInputStream
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
	
	
	public static void salvarRecursoSerializado(String rutaArchivo, Object object)	throws Exception {
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
	

	
	/**
	 * leer el archivo xml
	 * @param rutaArchivo
	 * @return
	 * @throws IOException
	 */
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
