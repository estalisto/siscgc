package com.siscogescorp.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArchivoLog {

String dir;

FileWriter archivo; 

    public void grabaLog(String contenido) throws IOException {
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = new Date();
            properties properties = null;
            String Nfecha = formateador.format(fecha);
            String rutaArch ="";
            DateFormat FormatoHoraFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String NhoraFecha = FormatoHoraFecha.format(fecha);
            FileReader fr = null;
            FileWriter fw = null;
            File archivo;
            BufferedWriter bfw;
            PrintWriter ptf;
            
            rutaArch =  properties.getInstance().getProperty(properties.CONFIG_RUTA_LOG_PER); //"C:\\Users\\ViewSoft\\Downloads\\archiv\\archivo.log";

            try {
                File directorio = new File(rutaArch);
                
                if(!directorio.exists()){
                    directorio.mkdirs();
                }                
                
                String rutaGuardar = rutaArch+"Laticobsa_"+Nfecha+".txt";
                archivo = new File(rutaGuardar);
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
                fw = new FileWriter(archivo,true);
                ptf = new PrintWriter(fw);
                ptf.println(NhoraFecha+ "  " + contenido);
                fw.close();

            } catch (IOException e) {
                System.out.println(e.toString());
            }
   }
}