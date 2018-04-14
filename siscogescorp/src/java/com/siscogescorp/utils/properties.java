/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.utils;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author ViewSoft
 */
public class properties {
     Properties properties = null;
        
    public final static String CONFIG_FILE_NAME             = "com/laticobsa/utils/Configuracion.properties"; 
//    public final static String CONFIG_RUTA_XSD_FA           = "rutaxsdfa";
//    public final static String CONFIG_RUTA_XSD_NC           = "rutaxsdnc";
//    public final static String CONFIG_RUTA_XSD_ND           = "rutaxsdnd";
//    public final static String CONFIG_RUTA_XSD_GR           = "rutaxsdgr";
    public final static String CONFIG_RUTA_IMG              = "rutaimg";//[001] JG. RUTA AGREGADA PARA LA IMAGEN DEL REPORTE
    public final static String CONFIG_RUTA_LOG_PER          = "rutalog";
//    public final static String CONFIG_RUTA_SERVER           = "rutaserver";
//    public final static String CONFIG_IVA                   = "porciva";
//    
 
    private properties() 
    {
        this.properties = new Properties();
        try {
            properties.load(properties.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
        }
    }
 
    /**
     * Implementando Singleton
     *
     * @return
     */
    public static properties getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
 
    private static class ConfigurationHolder { 
        private static final properties INSTANCE = new properties();
    }
 
    /**
     * Retorna la propiedad de configuración solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
