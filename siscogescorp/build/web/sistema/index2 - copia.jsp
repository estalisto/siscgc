<%-- 
    Document   : roles
    Created on : 27-feb-2017, 11:50:46
    Author     : CIMA2015
--%>

<%
    String Sidentificacion = "", id_empresa = "", USER_SESION="", NomEmpleados="",ApellidosEmpleados="",IdEmpleado="",RolEmpleado="", nom_empresa="",NivelAccesoRol="";
    HttpSession sesion = request.getSession(true);
    
    try {
     if (sesion.getAttribute("SstrUsuarioRol")== null && sesion.getAttribute("Sstrempresa")==null && sesion.getAttribute("SstrUSER")== null){
                  
                  sesion.invalidate();
                  response.sendRedirect("login.jsp");
              //out.print("<script>location.replace('login.jsp');<script>");
              return;
        }else {
        Sidentificacion = sesion.getAttribute("SstrUsuarioRol").toString();
        id_empresa = sesion.getAttribute("Sstrempresa").toString();
        nom_empresa = sesion.getAttribute("SstrNomEmpresa").toString();
        USER_SESION= sesion.getAttribute("SstrUSER").toString();
        IdEmpleado = sesion.getAttribute("SstrIdEmpleado").toString();
        NomEmpleados = sesion.getAttribute("SstrNombresEmpleado").toString();
        ApellidosEmpleados = sesion.getAttribute("SstrApellidosEmpleado").toString();
        RolEmpleado = sesion.getAttribute("SstrRolEmpleado").toString();
         NivelAccesoRol = sesion.getAttribute("NivelAccesoRolID").toString();
        
        }
        
    } catch (Exception e) {
        Sidentificacion = "000";
       response.sendRedirect("/siscogescorp/login"); 
    }
%>



<%@page import="com.siscogescorp.modelo.LcUsuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.siscogescorp.servicios.ValidaUsuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.siscogescorp.servicios.ModulosRoles"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.siscogescorp.modelo.LcModuloRol"%>
<%@page import="java.util.List"%>
<%@page import="com.siscogescorp.servicios.DashcboardOk"%>



    <!DOCTYPE html>
<html>
<head>
     <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>LatiCobsa S.A.</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" type="image/png" href="dist/img/favicon.png"/>

  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- jvectormap 
  <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">-->
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  <link href="dist/css/bootstrap-datepicker.css" rel="stylesheet" /> 
  <link href="plugins/datepicker/bootstrap-datepicker.css" rel="stylesheet" /> 
    

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  </head>
  <style type="text/css">
    input {
        border-radius: 12px 12px 12px 12px;
-moz-border-radius: 12px 12px 12px 12px;
-webkit-border-radius: 12px 12px 12px 12px;
border: 0px groove #000000;
     }
</style>
<body class="hold-transition skin-yellow sidebar-mini">
<div class="wrapper">

  <header class="main-header">

    <!-- Logo -->
    <a href="home" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b><%=nom_empresa.substring(0, 1)%></b><%=nom_empresa.substring(1, 4)%></span>
      <!-- logo for regular state and mobile devices  str.substring(0, 3) -->
      <span class="logo-lg"><b></b><%=nom_empresa%></span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!--li class="header" style="color:#ffffff; size: A4"><strong>Empresa:</strong></li-->
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-success">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">Tienes 10 Notificaciones</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 1 notificación
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-green"></i>  2 notificación
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i>  3 notificación
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-shopping-cart text-green"></i> 4 notificación
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user text-red"></i> Debes actuializar las credenciales de Usuario
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">Ver todas las Notificaciones</a></li>
            </ul>
          </li>

          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="dist/img/logosiscogescorp.png" class="user-image" alt="User Image">
              <span class="hidden-xs">Usuario:<strong>  <%=NomEmpleados%> <%=ApellidosEmpleados%> </strong></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="dist/img/logosiscogescorp.png" class="img-circle" alt="User Image">

                <p>
                  <strong> <%=NomEmpleados%> <%=ApellidosEmpleados%> </strong>                   
                  <small><strong><%=nom_empresa%> - <%=RolEmpleado.toUpperCase()%></strong></small>
                 
                </p>
              </li>
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Perfil</a>
                </div>
                <div class="pull-right">
                  <a href="#" onclick="ConfiemaSalidaSistema();" class="btn btn-default btn-flat">Salir</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button 
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>-->
        </ul>
      </div>

    </nav>
  </header>
 <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <!-- /.sidebar -->
  
<section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/logosiscogescorp.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><%=USER_SESION%></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->

      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">Menu de Navegación</li>
        
        <% Integer nivelPadre=0; 
           Integer contador=0;
           contador++;
           
           Integer RolID, EmpresaID;
           Integer rol=0, empresa=0;
           //empresa = sesion.getAttribute("strempresa"); 
           //rol = session.getAttribute("strUsuarioRol"); 
           // valida usuario -> rol-> modulos
           RolID =  Integer.parseInt(Sidentificacion);//Admin
           EmpresaID= Integer.parseInt(id_empresa);
           ModulosRoles mr = new ModulosRoles();
            List<LcModuloRol> modulos = mr.getLcModulosRoles(RolID,EmpresaID);
            List<LcModuloRol> modulos2 = mr.getLcModulosRolesROL(RolID,EmpresaID);
           
           for(int i=0; i< modulos.size(); i++) {
                if(modulos.get(i).getNivelModulo().equals(nivelPadre))
                {
                    %>
                        <li class="treeview">
                        <a href="#">
                            <i class="fa fa-cog"></i> <span><%=modulos.get(i).getLcModulos().getMenuOpciones() %></span>
                          <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                          </span>
                        </a>
                        <ul class="treeview-menu">
                            <%
                            for(int j=0; j< modulos2.size(); j++) {
                             if(modulos2.get(j).getNivelModulo().equals(modulos.get(i).getGrupoMod()))
                                {
                                %>
                                <li class=""><a href="#" onclick="<%=modulos2.get(j).getLcModulos().getFunciones() %>" ><i class="fa fa-circle-o text-yellow"></i> <%=modulos2.get(j).getLcModulos().getMenuOpciones() %></a></li>
                                <% 
                                }
                            }
                            %>
                        </ul>
                      </li>
           <%
                }
             contador++;  
           }
           %>
           
       
      </ul>
    </section>

  
  </aside>
  <div  id="mensajeSalida"></div>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
      <div id="page-wrapper" > 
          <br>
          <form class="well">
                <div class="row">
              
                  <div class="col-lg-4">
                    <div class="thumbnail">  
                        <!--label>
                             Compromisos de Pago
                        </label-->
                              <div id="piechart"></div>
                       
                      </div>

                  </div>
                  <div class="col-lg-4">
                    <div class="thumbnail">
                     
                            <div id="piechart2"></div>
                 
                      </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="thumbnail">
                        
                            <div id="columnchart_material"></div>
               
                      </div>
                  </div>
                </div>
           </form>          
      </div>
         
      
      
      
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.0
    </div>
        <strong> <a href="http://softwarefacturyec.com">SoftwareFactury</a>&copy; 2017. </strong>  Laticobsa - Todos los derechos reservados.

  </footer>

 
  <div class="control-sidebar-bg"></div>
  <div  id="mensajeSalida"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>    
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
  
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- 
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap4.min.js"></script> -->


<!--datapicker
<script src="bootstrap/js/bootstrap-datepicker.js"></script>-->
<!-- SlimScroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<script src="dist/js/menu.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

<!-- page script -->
<!--script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script-->
<script type="text/javascript" src="plugins/gstatic/graficos.js"></script>
<script src="dist/plugins/jquery.maskedinput.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        <%DashcboardOk gfc=new DashcboardOk();%>
      google.charts.load('current', {'packages':['corechart','bar']});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart2);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Meses', 'Compromisos de Pago'],           
              <%=gfc.ObtenerCompromisosPagos()%>
           ]);
        var options = {
           title: 'Compromisos de Pagos'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
      function drawChart2() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     5],         
          ['Sleep',    9]
        ]);
        var options = {
          title: 'My Daily Activities'
        };
        var chart2 = new google.visualization.PieChart(document.getElementById('piechart2'));
        chart2.draw(data, options);
      }
    
     
      google.charts.setOnLoadCallback(drawChart3);
      function drawChart3() {
        var data = google.visualization.arrayToDataTable([
          ['','Gestiones'],           
             <%=gfc.ObtenerGestiones()%> 
          ]);
/*
          ['2014', 1000, 400, 200],
          ['2015', 1170, 460, 250],
          ['2016', 660, 1120, 300],
          ['2017', 1030, 540, 350]*/
        var options = {
          chart: {
            title: 'Gestiones Realizadas por mes',
            
          }
        };
        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
</body>
</html>