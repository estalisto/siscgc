<%@page import="com.siscogescorp.utils.properties"%>
<%@page import="com.siscogescorp.modelo.LcInstitucion"%>
<%@page import="com.siscogescorp.modelo.LcFormapagoRecaudacion"%>
<%@page import="com.siscogescorp.modelo.LcEmpleados"%>
<%@page import="com.siscogescorp.servicios.EmpleadosServicios"%>
<%@page import="com.siscogescorp.modelo.LcDatosDeudores"%>
<%@page import="com.siscogescorp.servicios.RecaudacionServicios"%>
<%@page import="com.siscogescorp.modelo.LcRecaudaciones"%>
<%@page import="com.siscogescorp.utils.Numero_a_Letra"%>
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
 
 <%  /*  
CÓDIGO:			[001]
DESARROLLADO POR: 	SIS JIMMY GUARANDA
INGENIERO DE SOFTWARE: 	SIS STALYN GRANDA
OBJETIVOS:		OBTENGO LA RUTA DE LA IMG DEL REPORTE QUE SE ENCUENTRA CONFIGURADA EN EL ARCHIVO DE CONFIGURACION
			OBTENGO EL TOTAL DE LA RECAUDACION 
			OBTENGO DATOS DE LA FORMA DE PAGO
			TRANSFORMO EL TOTAL EN LETRAS */
 
 /*Importamos la clase "Conexion_Postgresql" y la instanciamos por el nombre conexion
con la siguiente linea de codigo*/
try
{

HttpSession sesion = request.getSession(true);
Conexion conexion=new Conexion();
 RecaudacionServicios rec = new RecaudacionServicios();
 EmpleadosServicios emp = new EmpleadosServicios();
 Numero_a_Letra conv = new Numero_a_Letra(); 
/*Establecemos la ruta del reporte*/ 
File reportFile = new File(application.getRealPath("sistema/reportes/ComprobantePago_1.jasper"));



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
//[001] JG. ini 


String cantidad="";
String cantidadletra = "";
String pagoefectivo="";
String pagocheque="";
String rutaimg="";
int idBanco;
String nombrebanco="";
String nombrebanco1="";
String numerocheque = "";
String numerocuenta = "";
int tipopago;
boolean mayusculas = true;
properties properties = null;
rutaimg =  properties.getInstance().getProperty(properties.CONFIG_RUTA_IMG);
int idRecaudacion= Integer.parseInt(request.getParameter("idRecaudacion"));
cantidad = rec.ObtenerCantidad(idRecaudacion);
List<LcFormapagoRecaudacion> forma = rec.ObtenerTipPago(idRecaudacion);
for(LcFormapagoRecaudacion mispagos:forma )
        {
            tipopago = mispagos.getLcTipoFormaPago().getIdTipoFp();
            if(tipopago == 1){
                pagoefectivo ="X";
            }
            if(tipopago == 2){
                numerocuenta = mispagos.getNumTarjetaCred();
                idBanco = mispagos.getIdEntidadFinanciera();
                List<LcInstitucion> name = rec.ObtenerInstitucion(idBanco);
                nombrebanco = name.get(0).getNombreInstitucion();
            }
            if(tipopago == 3){
                pagocheque ="X";
                numerocheque = mispagos.getNumCheque();
                idBanco = mispagos.getIdEntidadFinanciera();
                List<LcInstitucion> name = rec.ObtenerInstitucion(idBanco);
                nombrebanco = name.get(0).getNombreInstitucion();
            }
            
        }

cantidadletra = conv.Convertir(cantidad, mayusculas);
//[001] JG. fin 
List<LcRecaudaciones> datos = rec.getDatosRecaudacion(idRecaudacion);
         for(LcRecaudaciones misdatos:datos )
        {   
           fecha_trx=dateFormatter.format(misdatos.getFechaRegistro()).toString();
           identificacion=misdatos.getLcDatosDeudores().getIdentificacion().toString();
           nom_cliente=misdatos.getLcDatosDeudores().getNombres()+" "+misdatos.getLcDatosDeudores().getApellidos();
          List<LcEmpleados> empleado = emp.getDatosLCEmpleadosID(misdatos.getIdEmpleado());
          if(empleado.size()>0){
           nombre_sesion = empleado.get(0).getNombres()+" "+empleado.get(0).getApellidos();
          }else{
          nombre_sesion=sesion.getAttribute("SstrNombresEmpleado").toString()+ " " +sesion.getAttribute("SstrApellidosEmpleado").toString();
          }
        }

 //fecha_trx = request.getParameter("fecha_trx");
/*Digitamos la siguiente linea de codigo entre parentesis ira el parametro que agregamos en nuestro reporte
llamado $P{CODIGO}, pero solo se escribira "CODIGO", el String que capturamos lo colocamos, en este caso el 
reporte solo nos pide un parametro*/
parameters.put("rutaimg", application.getRealPath("dist/img/Laticobsa.jpg")); ////[001] JG. 
parameters.put("nom_cliente", nom_cliente); 
parameters.put("identificacion", identificacion); 
parameters.put("fecha_trx", fecha_trx); 
parameters.put("nombre_sesion", nombre_sesion); 
parameters.put("idRecaudacion", idRecaudacion); 
//[001] JG. ini 
parameters.put("cantidadletra", cantidadletra); 
if(pagoefectivo != ""){
parameters.put("pagoefectivo", pagoefectivo); 
}else{
parameters.put("pagoefectivo", " ");
}
if(pagocheque != ""){
parameters.put("pagocheque", pagocheque); 
parameters.put("numerocheque", numerocheque); 
parameters.put("nombrebanco", nombrebanco.substring(5));
}else{
parameters.put("pagocheque", " ");
parameters.put("numerocheque", " "); 
parameters.put("nombrebanco", " ");
}
if(numerocuenta != ""){
parameters.put("numerocuenta", numerocuenta); 
parameters.put("nombrebanco", nombrebanco.substring(5)); 
}else{
parameters.put("numerocuenta", " "); 
parameters.put("nombrebanco1", " "); 
}
//[001] JG. Fini 

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