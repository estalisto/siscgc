

<%@page import="com.siscogescorp.utils.EnviarEmail"%>
<%@page import="com.siscogescorp.modelo.LcDatosDeudores"%>
<%@page import="com.siscogescorp.servicios.RecaudacionServicios"%>
<%@page import="com.siscogescorp.modelo.LcRecaudaciones"%>
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
EnviarEmail em = new EnviarEmail();
 RecaudacionServicios rec = new RecaudacionServicios();
/*Establecemos la ruta del reporte*/ 
File reportFile = new File(application.getRealPath("sistema/reportes/cuadredecaja.jasper"));

/*Enviamos parámetros porque nuestro reporte los necesita asi que escriba 
y seguiremos el formato del método runReportToPdf*/ 
/*Con Map y el HaspMap nos servira para crear los paramentros*/
Map parameters = new HashMap();

/*Capturamos el valor de nuestra formulario que es el codigo del cliente que es un
varchar(5), lo almacenamos en una String*/
//String idcliente=request.getParameter("txtidcliente");


SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMddHHmmss" );
Date date= new Date();
System.out.println("Old Format :   " + dateFormatter.format(date));
System.out.println("New Format :   " + targetFormat.format(date));
String yyyyMMddHHmmss =targetFormat.format(date);
String yyyyMMdd =dateFormatter.format(date);

String NombreArchivo="CuadreDeCaja_"+yyyyMMddHHmmss;



int pn_empresa;
double pn_efectivo,pn_banco,pn_tcredito,pn_total;
/*
request.setAttribute("valor_efec",valor_efec);
request.setAttribute("valor_ch",valor_ch);
request.setAttribute("valor_tar",valor_tar);
request.setAttribute("valor_inpt",valor_inpt);

*/
String id_empresas;  
id_empresas = sesion.getAttribute("Sstrempresa").toString();
pn_empresa=Integer.parseInt(id_empresas);
pn_efectivo=Double.parseDouble(request.getParameter("valor_efec"));
pn_banco=Double.parseDouble(request.getParameter("valor_ch"));
pn_tcredito=Double.parseDouble(request.getParameter("valor_tar"));
pn_total=Double.parseDouble(request.getParameter("valor_inpt"));
//int idRecaudacion= Integer.parseInt(request.getParameter("idRecaudacion"));
 //fecha_trx = request.getParameter("fecha_trx");
/*Digitamos la siguiente linea de codigo entre parentesis ira el parametro que agregamos en nuestro reporte
llamado $P{CODIGO}, pero solo se escribira "CODIGO", el String que capturamos lo colocamos, en este caso el 
reporte solo nos pide un parametro*/



parameters.put("pn_empresa", pn_empresa); 
parameters.put("pn_efectivo", pn_efectivo); 
parameters.put("pn_banco", pn_banco); 
parameters.put("pn_tcredito", pn_tcredito); 
parameters.put("pn_total", pn_total); 

/*Enviamos la ruta del reporte, los parámetros y la conexión(objeto Connection)*/ 
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion.getconexion()); 
/*Indicamos que la respuesta va a ser en formato PDF*/ response.setContentType("application/pdf");
//JasperExportManager.exportReportToPdfFile(reportFile ,"D:/temporal/reporte.pdf");


/* momentaneo
String ruta = getServletContext().getRealPath("WEB-INF")+"\\CuadresCaja\\"+NombreArchivo+".pdf";
System.out.println(ruta);
JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, conexion.getconexion());
JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
*/

response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length);
 /*Limpiamos y cerramos flujos de salida*/ 
ouputStream.flush(); 
ouputStream.close();
/*
boolean okmail = em.SendEnviarEmail("Se envia el Cuadre de Caja", "Buenas tardes estimados, \nSe envia el cuadre de caja "+yyyyMMdd, "D:\\CuadreDeCaja_20170827165820.pdf", "barcestalyn@gmail.com");

if(okmail){
    System.out.println("Correo enviado correctamente");
    
}else {
    System.out.println("Fallo envio");
}
        */

}catch(Exception ex){

response.sendRedirect("/siscogescorp/login"); 
}
 







 %>
</body>
</html>