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
try
{

HttpSession sesion = request.getSession(true);
Conexion conexion=new Conexion();
/*Establecemos la ruta del reporte*/ 
File reportFile = new File(application.getRealPath("sistema/reportes/documentosmasivos.jasper"));



/*Enviamos parámetros porque nuestro reporte los necesita asi que escriba 
y seguiremos el formato del método runReportToPdf*/ 
/*Con Map y el HaspMap nos servira para crear los paramentros*/
Map parameters = new HashMap();

/*Capturamos el valor de nuestra formulario que es el codigo del cliente que es un
varchar(5), lo almacenamos en una String*/
//String idcliente=request.getParameter("txtidcliente");


SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String nom_cliente=""; 
String identificacion="";//request.getParameter("identificacion");
String fecha_trx= "";//dateFormatter.format(fecha_actual).toString();  //"2017-05-12 17:05:00";
String nombre_sesion="";

 //fecha_trx = request.getParameter("fecha_trx");
/*Digitamos la siguiente linea de codigo entre parentesis ira el parametro que agregamos en nuestro reporte
llamado $P{CODIGO}, pero solo se escribira "CODIGO", el String que capturamos lo colocamos, en este caso el 
reporte solo nos pide un parametro*/
String pruebas="hola";
int  ticket=Integer.parseInt(request.getParameter("ticket"));
int  IDDocumento=Integer.parseInt(request.getParameter("IDDocumento"));  


parameters.put("pruebas", pruebas); 
parameters.put("ticket", ticket); 
parameters.put("IDDocumento", IDDocumento); 

/*Enviamos la ruta del reporte, los parámetros y la conexión(objeto Connection)*/ 
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion.getconexion()); 
/*Indicamos que la respuesta va a ser en formato PDF*/ response.setContentType("application/pdf");

response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length);
 /*Limpiamos y cerramos flujos de salida*/ 
ouputStream.flush(); 
ouputStream.close();
}catch(Exception ex){

response.sendRedirect("/siscogescorp/login"); 
}
 
 %>
</body>
</html>