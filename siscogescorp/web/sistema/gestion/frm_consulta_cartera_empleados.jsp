<%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : Stalyn Granda
--%>
<%@page import="com.siscogescorp.servicios.ParametrosServicios"%>
<%@page import="com.siscogescorp.servicios.EmpleadosServicios"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

  <!DOCTYPE html>
  <% ParametrosServicios param = new ParametrosServicios(); 
  EmpleadosServicios emp = new EmpleadosServicios();
  HttpSession sesion = request.getSession(true);
  String Sidentificacion = "",SstrSexo="", id_empresa = "", USER_SESION="", NomEmpleados="",ApellidosEmpleados="",IdEmpleado="",RolEmpleado="", nom_empresa="",NivelAccesoRol="";
   
        
  if(sesion.getAttribute("SstrRolEmpleado").toString()==null){
       sesion.invalidate();
                  response.sendRedirect("login.jsp");
              //out.print("<script>location.replace('login.jsp');<script>");
              return;
  
  }
  IdEmpleado = sesion.getAttribute("SstrIdEmpleado").toString();
        RolEmpleado = sesion.getAttribute("SstrRolEmpleado").toString();
         id_empresa = sesion.getAttribute("Sstrempresa").toString();
         
  %>
<html>
  <head>      
        
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Minified Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">


<!-- Minified JS library 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Minified Bootstrap JS 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
  </head>
        <style type="text/css">
input:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
select:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
 textarea {
	resize: none;
                } 
 table th {
    bgcolor: #E0ECF8;
}
table tr {
    bgcolor: #E0ECF8;
}

</style>

<body>
    <ol class="Breadcrumb default">
                    <div class="btn-group btn-breadcrumb">
                        <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>
                        <a href="#" class=" btn btn-default" onclick="compropago();">COMPROMISOS DE PAGO</a>
                        <a href="#" class="active btn btn-default"onclick="hidden_cartera_cliente('false');">CONSULTA CARTERA</a>
                        <a href="#" class="btn btn-default" onclick="hidden_cartera_cliente('true');">GESTIÓN CLIENTE</a>
                        <a href="#" data-toggle="modal" class="btn btn-default" data-target="#myModalBusqueda" id="busqueda_deudor">BUSCAR CLIENTE</a>
                    </div>                    
    </ol>
    
    <div class="row">
        <div class="col-lg-12">
            <div class=" box box-danger">
                <h3>CONSULTA CARTERA POR EMPLEADO</h3>
                <hr> 
                <div class="row">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-4">
                        <select class="form-control " name="cartera" required="required" id="cartera" hidden ></select>
                    </div>
                </div>
                 <div class="row">
                     <div class="col-lg-1"></div>
                     <div class="col-lg-10">
                         <div id="mitable_cartera_empleado">
                            <table id="cartera_empleado" class="table table-striped table-bordered dt-responsive nowrap table-hover">
                                <thead>
                                    <tr bgcolor='#BFF3A0'> 
                                        <th>Empleados</th>
                                        <th>Cartera</th>
                                        <th>Asignados</th>
                                        <th>Recuperado</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>
                     </div>
                     <div class="col-lg-1"></div>
                
                
             </div>
        </div>
        
    </div>
    

     


<script src="dist/js/consultaxcatera.js"></script> 


<script src="dist/js/ValidaNumeros.js"></script>
<script src="dist/js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
    
    $(function(){
        

$('#datetimepicker10').datetimepicker({   format:'Y-m-d' });  
$('#datetimepicker11').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker12').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker13').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker14').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker15').datetimepicker({   format:'Y-m-d' }); 
$('#datepicker').datetimepicker({   format:'Y-m-d' }); 
$('#datepicker2').datetimepicker({   format:'Y-m-d' }); 
$('#monto').validCampoFranz('0123456789.');
$('#monto1').validCampoFranz('0123456789.');
$('#pagos').validCampoFranz('0123456789.');
$('#pagos1').validCampoFranz('0123456789.');
$('#tvencido').validCampoFranz('0123456789.');
$('#tvencido1').validCampoFranz('0123456789.');
$('#vcompromiso').validCampoFranz('0123456789.');
$('#vcompromiso1').validCampoFranz('0123456789.');
$('#identificacion2').validCampoFranz('0123456789.');
$('#saldos').validCampoFranz('0123456789.');
$('#saldos1').validCampoFranz('0123456789.');
$('#dia_mora').validCampoFranz('0123456789');
$('#dia_mora1').validCampoFranz('0123456789');  
$('#tTelefono').validCampoFranz('0123456789');
$('#newTelefono').validCampoFranz('0123456789');
$('#new_telefono_ref').validCampoFranz('0123456789'); 
$('#nombre_ref').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ ');
$('#nombre_deudor').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ ');
consultaMisClientes();
   
consulta_cartera_empleados();
});           
</script>

           

<script>
    
            ConsultasMisCarteras();
           
</script>
</body>
</html>

