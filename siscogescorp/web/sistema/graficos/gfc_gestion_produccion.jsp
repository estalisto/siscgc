<%-- 
    Document   : gfc_carteras_recuperado
    Created on : 28/06/2018, 19:25:10
    Author     : STALYN GRANDA <https://estalisto.wordpress.com/>
--%>
<%@page import="com.siscogescorp.servicios.ConsultaxCarteraServicios"%>
<%@page import="com.siscogescorp.servicios.ParametrosServicios"%>
<%
  String IdEmpleado="",RolEmpleado="",id_empresa="";
    HttpSession sesion = request.getSession(true);
//String GSGEmpresaID = "",GSmi_empleado="",GSSucursalID="",GStcartera="",GStsub_cartera="",GSfecha_ini="",GSfecha_fin="";
 RolEmpleado = sesion.getAttribute("SstrRolEmpleado").toString();
 IdEmpleado = sesion.getAttribute("SstrIdEmpleado").toString();
 id_empresa = sesion.getAttribute("Sstrempresa").toString();
%>
<%@page import="com.siscogescorp.servicios.DashcboardOk"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


    <!DOCTYPE html>
<html>
    
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
    <div class='col-lg-12' id='grafica_gestion' >
                  <div class="box box-warning">
                    <div class="box-header with-border">
                      <h3 class="box-title">Producción Diaria</h3>
                      <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                       
                      </div>
                    </div>
                    <div class="box-body">
                      <div id="produccion_diaria" ></div>
                    </div>
                    <div class="box-footer clearfix ">
                         
                    </div>
                  </div>
           
				 
              </div>
<%
    DashcboardOk gfc=new DashcboardOk();
    ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
 ParametrosServicios param = new ParametrosServicios(); 
//String idCaretraOK=request.getParameter("tcartera");
String idCaretraOK=request.getParameter("tcartera");
String datos="";
//mi_empleado="+mi_empleado+"&tcartera="+tcartera+"&tsub_cartera="+tsub_cartera+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin);
int IDEMPRESA=1;
int IDAGENCIA=1;

int IDEMPLEADO=Integer.parseInt(request.getParameter("mi_empleado"));
int IDCARTERA = Integer.parseInt(request.getParameter("tcartera"));
int IDSUBCARTERA=Integer.parseInt(request.getParameter("tsub_cartera"));
String fecha_ini=request.getParameter("fecha_ini");
String fecha_fin=request.getParameter("fecha_fin");

String permisos_roles_cal= param.Consulta_Parametro("LB_FILTROS_USUARIOS");                            
             

if(!permisos_roles_cal.contains(RolEmpleado)){
    int idCartera= cd.getEmpleadoCartera(Integer.parseInt(IdEmpleado));
     if(idCartera==0){
        datos="['','NINGUNA',],['GESTIONES',0,],";   
    }else{
  datos = gfc.fnc_ConsultaGestionProduccionOPER(IDEMPRESA,IDAGENCIA,0,idCartera,IDSUBCARTERA,fecha_ini,fecha_fin);  
     }
} else{
 datos = gfc.fnc_ConsultaGestionProduccion2(IDEMPRESA,IDAGENCIA,IDEMPLEADO,IDCARTERA,IDSUBCARTERA,fecha_ini,fecha_fin);
                // (id_empresa,id_agencia, id_empleado, idcartera, idSubCartera, fecha_ini,fecha_fin){
}

%>

<script>
     google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
         
     <%=datos%>
      ]);

    var options = {
      title : 'Producción Diaria',
      vAxis: {title: 'Gestiones'},
      hAxis: {title: 'Clientes'},
      seriesType: 'bars'
    };

    var chart = new google.visualization.ComboChart(document.getElementById('produccion_diaria'));
    chart.draw(data, options);
  }
     
</script>
</body>
</html>
  
         

