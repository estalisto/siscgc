<%@page import="com.siscogescorp.utils.ArchivoLog"%>
<%@page import="com.siscogescorp.utils.properties"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@page import="com.siscogescorp.utils.Conexion"%>
    <!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>LatiCobsa S.A.</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" type="image/png" href="..dist/img/favicon.png"/>
 <!--link rel="icon" type="image/png" href="http://localhost:8080/siscogescorp/dist/img/favicon.png"/-->
</head>
<body>
 
 <%  /*Importamos la clase "Conexion_Postgresql" y la instanciamos por el nombre conexion
con la siguiente linea de codigo*/
      ArchivoLog grb = new ArchivoLog();
try
{

properties properties = null;
String rutaimg="";
rutaimg =  properties.getInstance().getProperty(properties.CONFIG_RUTA_IMG);// OBTENGO LA RUTA DE LA IMG DEL REPORTE QUE SE ENCUENTRA CONFIGURADA EN EL ARCHIVO DE CONFIGURACION
Conexion conexion=new Conexion();
File reportFile = new File(application.getRealPath("sistema/reportes/NotFound.jasper"));    
Map parameters = new HashMap();
parameters.put("rutaimg", rutaimg); 
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion.getconexion()); 
/*Indicamos que la respuesta va a ser en formato PDF*/ response.setContentType("application/pdf");
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length);
 /*Limpiamos y cerramos flujos de salida*/ 
ouputStream.flush(); 
ouputStream.close(); 
}catch(Exception ex){
try{grb.grabaLog("rpt_not_found Error Java: "+ex.getMessage());}catch(Exception e){}    

}

 %>
</body>
</html>