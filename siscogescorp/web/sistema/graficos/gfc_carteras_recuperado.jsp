<%-- 
    Document   : gfc_carteras_recuperado
    Created on : 28/06/2018, 19:25:10
    Author     : STALYN GRANDA <https://estalisto.wordpress.com/>
--%>
<%@page import="com.siscogescorp.servicios.ConsultaxCarteraServicios"%>
<%@page import="com.siscogescorp.servicios.ParametrosServicios"%>
<%
  
    HttpSession sesion = request.getSession(true);
String GSGEmpresaID = "",GSmi_empleado="",GSSucursalID="",GStcartera="",GStsub_cartera="",GSfecha_ini="",GSfecha_fin="";
  String RolEmpleado="";

%>
<%@page import="com.siscogescorp.servicios.DashcboardOk"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
     DashcboardOk gfc=new DashcboardOk();
 ParametrosServicios param = new ParametrosServicios(); 
 ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
 String datos="";
 GSGEmpresaID = sesion.getAttribute("GSGEmpresaID").toString();
 GSmi_empleado = sesion.getAttribute("GSmi_empleado").toString();
 GSSucursalID = sesion.getAttribute("GSSucursalID").toString();
 GStcartera = sesion.getAttribute("GStcartera").toString();
 GStsub_cartera = sesion.getAttribute("GStsub_cartera").toString();
 GSfecha_ini = sesion.getAttribute("GSfecha_ini").toString();
 GSfecha_fin = sesion.getAttribute("GSfecha_fin").toString();
  RolEmpleado = sesion.getAttribute("SstrRolEmpleado").toString();

int iDGSGEmpresaID=Integer.parseInt(GSGEmpresaID);
int iDGSmi_empleado=Integer.parseInt(GSmi_empleado);
int iDGSSucursalID=Integer.parseInt(GSSucursalID);
int iDGStcartera=Integer.parseInt(GStcartera);
int iDGStsub_cartera=Integer.parseInt(GStsub_cartera);
// iDGSGEmpresaID  iDGSmi_empleado iDGSSucursalID iDGStcartera  iDGStsub_cartera  GSfecha_ini  GSfecha_fin

String permisos_roles_cal= param.Consulta_Parametro("LB_FILTROS_USUARIOS");                            
             

if(!permisos_roles_cal.contains(RolEmpleado)){
    int idCartera= cd.getEmpleadoCartera(iDGSmi_empleado);
    if(idCartera==0){
        datos="['','NINGUNA',],['RECUPERADO',0,],";  
    }else{
     datos = gfc.fnc_ConsultaRecuperadoxUsuario(iDGSGEmpresaID,iDGSSucursalID,0,idCartera,iDGStsub_cartera,GSfecha_ini,GSfecha_fin);                
    }
 
 
} else{
  datos = gfc.fnc_ConsultaRecuperado(iDGSGEmpresaID,iDGSSucursalID,iDGSmi_empleado,iDGStcartera,iDGStsub_cartera,GSfecha_ini,GSfecha_fin);
              // gfc.fnc_ConsultaRecuperado  (iDGSGEmpresaID,iDGSSucursalID,0,iDGStcartera,iDGStsub_cartera,GSfecha_ini,GSfecha_fin)
}

%>

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
<div class='col-lg-12' id='grafico_dash' >
    <!--div  id='grafico_dash' -->
        <div class='box box-warning'>
            <div class='box-header with-border'>
                <h3 class='box-title'>Valores Recuperado Por Cartera</h3>
                    <div class='box-tools pull-right'>
                        <button type='button' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                    </div>
            </div>
        <div class='box-body'>
            <div id='chart_gestiones_diarias666' >               
            </div>                
        </div>
        <div class='box-footer clearfix'>
        </div>
        </div>            
    <!--</div>-->
</div>
        
<!--div class='box box-warning'>
  <div class='box-header with-border'>
    <h3 class='box-title'>Valores Recuperado Por Cartera</h3>
    <div class='box-tools pull-right'>
      <button type='button' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
    </div>
  </div>
  <div class='box-body'>
    <div id='chart_gestiones_diarias666' ></div>
  </div>
  <div class='box-footer clearfix'>
  </div>
</div-->
				 
              


<script>
       // GraficaCarteraRecuperado();
         //function GraficaCarteraRecuperado(){
      //  var grafico_dash=$()
        
         /*document.getElementById("grafico_dash2").innerHTML ="";
           document.getElementById("grafico_dash2").innerHTML =
         "<div  id='grafico_dash' ><div class='box box-warning'>"+
"<div class='box-header with-border'><h3 class='box-title'>Valores Recuperado Por Cartera</h3><div class='box-tools pull-right'>"+
"<button type='button' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>"+
"</div></div><div class='box-body'><div id='chart_gestiones_diarias666' ></div></div>"+
"<div class='box-footer clearfix'></div>"+
"</div></div>";
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChartRecaudacionPorCartera);*/
//}
  google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChartRecaudacionPorCartera);

        function drawChartRecaudacionPorCartera() {

        var data = google.visualization.arrayToDataTable([
         <%=datos%>                    
        ]);

        var options = {
          chart: {          
          },
		   vAxis: {format: 'decimal'},
		   //height: 300,
		   //width: 500,
          bars: 'vertical' //horizontal vertical Required for Material Bar Charts.
        };
        var chart = new google.charts.Bar(document.getElementById('chart_gestiones_diarias666'));
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
</script>
</body>
</html>



