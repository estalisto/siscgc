<%-- 
    Document   : roles
    Created on : 27-feb-2017, 11:50:46
    Author     : CIMA2015
--%>

<%
    String Sidentificacion = "",SstrSexo="", id_empresa = "", USER_SESION="", NomEmpleados="",ApellidosEmpleados="",IdEmpleado="",RolEmpleado="", nom_empresa="",NivelAccesoRol="";
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
        SstrSexo = sesion.getAttribute("SstrSexo").toString();
        
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
<%@page import="com.siscogescorp.servicios.ParametrosServicios"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.siscogescorp.modelo.LcModuloRol"%>
<%@page import="java.util.List"%>
<%@page import="com.siscogescorp.servicios.DashcboardOk"%>
<%@page import="com.siscogescorp.servicios.EmpleadosServicios"%>



    <!DOCTYPE html>
<html>
<head>
     <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title><%=nom_empresa%></title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <link rel="icon" type="image/png" href="dist/img/icono4.png" />

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
  <!--link href="dist/css/bootstrap-datepicker.css" rel="stylesheet" /
  <link href="plugins/datepicker/bootstrap-datepicker.css" rel="stylesheet" /> --> 
  <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  </head>

        <style type="text/css">
input:enabled {
background-color:#FBE3BF;
border: 1px solid #FAC197;
}
select:enabled {
background-color:#FBE3BF;
border: 1px solid #FAC197;
}

 textarea {
	resize: none;
                } 
 table th {
    bgcolor: #EDFAC5;
}
table tr {
    bgcolor: #E0ECF8;
}

</style>

<body id="MisGraficas" style="background-color:#9e9998">
    <div  id="mensajeSalida"></div>
    
  
<nav class="topnav navbar navbar-inverse" style="background-color:#F98021; border-color: #E7E7E7;" >
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
        <a class="navbar-brand" style="color:white;"  href="home"><strong ><%=nom_empresa%></strong></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
           <% 
               Integer nivelPadre2=0,contador2=0;
               ParametrosServicios param = new ParametrosServicios(); 
           ModulosRoles mroles = new ModulosRoles();
            List<LcModuloRol> datos_modulos = mroles.getLcModulosRoles(Integer.parseInt(Sidentificacion),Integer.parseInt(id_empresa));
            List<LcModuloRol> datos_modulos2 = mroles.getLcModulosRolesROL(Integer.parseInt(Sidentificacion),Integer.parseInt(id_empresa));
           
           for(int k=0; k< datos_modulos.size(); k++) {
                if(datos_modulos.get(k).getNivelModulo().equals(nivelPadre2))
                {
                    %>
        <li class="dropdown" >
            <a class="dropdown-toggle " style="color:white;" data-toggle="dropdown" href="#"><i class="fa fa-navicon text-black"></i> <strong> <%=datos_modulos.get(k).getLcModulos().getMenuOpciones() %></strong><span class="caret"></span></a>
            <ul class="dropdown-menu">
                <%
                for(int l=0; l< datos_modulos2.size(); l++) {
                 if(datos_modulos2.get(l).getNivelModulo().equals(datos_modulos.get(k).getGrupoMod()))
                    {
                    %>
            
                    
                      <li><a href="#" onclick="<%=datos_modulos2.get(l).getLcModulos().getFunciones() %>" class="text-black"><i class="fa fa-th text-orange"></i> <%=datos_modulos2.get(l).getLcModulos().getMenuOpciones() %> </a></li>
                   
                <% 
                    }
                }
                %>
             </ul>
        </li>
         <%
                }
             contador2++;  
           }
           %>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" style="color:white;"><span class="glyphicon glyphicon-user text-black" ></span>   <%=NomEmpleados.toUpperCase()%> <%=ApellidosEmpleados.toUpperCase() %> </a></li>
        <li><a href="#" onclick="ConfiemaSalidaSistema();" style="color:white;">SALIR <span class="glyphicon glyphicon-log-in text-black"></span> </a></li>
      </ul>
    </div>
  </div>
</nav>
          <%  
               String permisos_roles_cal= param.Consulta_Parametro("LB_FILTROS_USUARIOS");                            
               String VarHidden="",VarHiddenOficial=""; 
               Boolean grafica=false;
               int col=0;
               if(!permisos_roles_cal.contains(RolEmpleado)){
                 VarHidden="hidden";
                 grafica=false;
                 col=6;
                }
               else{
                   VarHiddenOficial="hidden";
                   grafica=true;
                    col=4;
               }
              %>
     <div  id="mensajeSalida"></div>   
      <div id="page-wrapper" style="background-color:#9e9998" >
          
          
        <div class="panel panel-default <%=VarHidden%> ">
                 <div class="pannel panel-body">
                     <div class="row">
                                        <div class="col-lg-11" >
                                              <select class="input-sm text-bold" id="mi_empleado">
                                                <%   EmpleadosServicios emp = new EmpleadosServicios();
                                                    String s_empleados3=""; s_empleados3= emp.misEmpleadosALL(Integer.parseInt(id_empresa), RolEmpleado, Integer.parseInt(IdEmpleado)); %>
                                                <%=s_empleados3%>
                                            </select>
                                            <select class="input-sm text-bold"  id="idcartera" onchange="getTipoCarteras2();" >
                                            </select>
                                            <select class="input-sm text-bold <%=VarHidden%> "  id="tcartera" onchange="getipoSubCartera2();" >
                                            </select>
                                             <select class="input-sm text-bold <%=VarHidden%> "  id="tsub_cartera" >
                                            </select>
                                            <label class=" <%=VarHidden%>">Fecha Inicio:</label>
                                            <input id="fecha_ini_calidad2" type="text" class="input-sm text-bold <%=VarHidden%>" placeholder="YYYY-MM-DD" />
                                            <label class="<%=VarHidden%>">Fecha Fin:</label>
                                            <input id="fecha_fin_calidad2" type="text" class="input-sm text-bold <%=VarHidden%>" placeholder="YYYY-MM-DD" />
                                         </div>
                                        <div class="col-lg-1" >
                                            <a  onclick="miGraficaRecuperadoPorUsuario();miGraficaCarteraVsRecuperado();cuadro_eficiencia_porcentaje();miGraficaProduccion();"   class="btn btn-success   glyphicon glyphicon-cog">  FILTRAR  </a>
                                        </div>
                                    </div>
                                     
                     

                 </div>
        </div>
          
          <div class="row well-sm"  >
              
          
              <div class="col-lg-<%=col %>" id='grafica_gestion' >
                 
              </div>
               <div class='col-lg-<%=col %> ' id='grafico_dash' >
                  
				 
              </div>
              <div class="col-lg-<%=col %>" id='recuperado_gestor' >
                 
              </div>
             
              
              
             
          </div>
            
                
                
                            
        <div class="row well-sm  <%=VarHidden%> "  >
            <div class="col-lg-6" >
                   
                <div class="panel panel-default" hidden>
                             <div class="pannel panel-heading text-info text-bold lead" style="background-color:#FEC187;color:#302f2f;" > Asignación de Cartera por Empleado </div>
                             <div class="pannel panel-body">
                                 
                                 <div id="mitable_calidad">
                                    <table id="idCuadroCalidad" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                        <thead>
                                            <tr  bgcolor='#FEC187'>
                                                <th class="col-sm-2">Usuario</th>
                                                <th class="col-sm-2">T.Asignados</th>
                                                <th class="col-sm-2">Total Deuda</th>
                                                <th class="col-sm-2">Gestiones</th>
                                                <th class="col-sm-2">Recuperado</th>
                                                <th class="col-sm-1">Eficiencia</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                             </div>
                             
                         </div>
                
            </div>
            <div class="col-lg-12" >
                   
                <div class="panel panel-default">
                             <div class="pannel panel-heading text-info text-bold lead" style="background-color:#FEC187;color:#302f2f;" > Cuadro de Calidad  </div>
                             <a  onclick="tableToExcel('mitable_calidad2', 'Calidad');"   class="btn btn-success   glyphicon glyphicon-download-alt">  EXPORTAR  </a>
                             <div class="pannel panel-body">
                                
                                 <div id="mitable_calidad2">
                                    <table id="idCuadroCalidad2" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                        <thead>
                                            <tr  bgcolor='#FEC187'>
                                                <th class="col-sm-2">Usuario</th>
                                                <th class="col-sm-2">T.Asignados</th>
                                                <th class="col-sm-2">Total Deuda</th>
                                                <th class="col-sm-2">Gestiones</th>
                                                <th class="col-sm-2">Recuperado</th>
                                                <th class="col-sm-1">Eficiencia</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                             </div>
                             
                         </div>
                
            </div>
        </div>
          
          
          
          
      </div>
    
    <div class="wrapper" id="midashboard" hidden="true">

  <header class="main-header" >

    <!-- Logo -->
    
    <a href="home" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      
        
      <span class="logo-mini"><b><%=nom_empresa.substring(0, 1)%></b><%=nom_empresa.substring(1, 4)%> </span>
       <span class="logo-lg"><b></b> <%=nom_empresa%></span>
      <!-- logo for regular state and mobile devices  str.substring(0, 3) -->
      
    </a>
    <span class="logo-mini"><div class="user-panel" hidden>
        <div class="pull-left image">
            <% if(SstrSexo.toUpperCase().equals("MASCULINO")){
                %>
            <img src="dist/img/operadorM2.png" class="img-circle" alt="User Image">
            <%
            }else{
             %>
            <img src="dist/img/operadorf.png" class="img-circle" alt="User Image">
            <%
            }
            %>
          <!--img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image"-->
        </div>
        <div class="pull-left info">
            <p class="text-uppercase"><%=USER_SESION%></p>
          <a href="#"><i class="fa fa-circle text-green"></i> <%=RolEmpleado.toUpperCase()%></a>
           <input hidden id="id_RolEmpleado" value="<%=RolEmpleado.toUpperCase()%>"/>
        </div>
      </div></span>
    

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle success" data-toggle="dropdown">
                <i class="fa fa-navicon text-yellow"></i><span> CONFIGURACIÓN</span>             
            </a>
            <ul class="dropdown-menu">
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-th text-aqua"></i> EMPRESA
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-th text-aqua"></i> SUCURSAL
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-th text-yellow"></i> FUNCIONES
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> EMPLEADOS
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user-plus text-green"></i> GESTIÓN USUARIO                      
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user-plus text-green"></i> MIS CEDENTES                      
                    </a>
                  </li>
                </ul>
              </li>
              <!--li class="footer"><a href="#">View all</a></li-->
            </ul>
          </li>
           
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle success" data-toggle="dropdown">
                <i class="fa fa-navicon text-yellow"></i><span> </span>             
            </a>
            <ul class="dropdown-menu">
                
                  <li>
                    <a href="#" >
                      <i class="fa fa-th text-aqua"></i> 
                    </a>
                  </li>
                
            </ul>
          </li>
           
          <!-- Messages: style can be found in dropdown.less-->
          <!--li class="header" style="color:#ffffff; size: A4"><strong>Empresa:</strong></li-->
         <!-- Tasks: style can be found in dropdown.less -->
          <!-- User Account: style can be found in dropdown.less -->
          <%
               int    IdDeudor=0;
               int    IdClienteCartera=0;
               String datosIdDeudor="";
               String datosIdClienteCartera="";
                HttpSession sesion2 = request.getSession(true);
               if (sesion2.getAttribute("SstrIdDeudor")!= null && sesion2.getAttribute("SstrIdClienteCartera")!=null){
                datosIdDeudor=sesion2.getAttribute("SstrIdDeudor").toString();
                datosIdClienteCartera=sesion2.getAttribute("SstrIdClienteCartera").toString();
                %>
          <li> <input id="notif_deudor"  value="<%=datosIdDeudor %>" hidden></li>
          <li><input id="notif_cliente" value="<%=datosIdClienteCartera %>" hidden></li>
                <%
                }else{
                %>
          <li> <input id="notif_deudor" value="<%=datosIdDeudor %>" hidden></li>
          <li><input id="notif_cliente" value="<%=datosIdClienteCartera %>" hidden></li>
                <%
               }
           
          
          %>
          <li class="dropdown user user-menu hidden" hidden="true">
            <a href="#" class="dropdown-toggle " data-toggle="dropdown">
              <!-- <img src="dist/img/operadorM.png" class="user-image" alt="User Image">-->
              <!-- <img src="dist/img/operadorf.png" class="user-image" alt="User Image">-->
              <span class="hidden-xs text-black"><strong>  <%=NomEmpleados%> <%=ApellidosEmpleados%> </strong></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header " hidden>
                   <input value="<%=Sidentificacion%>" id="IDUserRol" hidden>
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                <p>
                  <strong> <%=NomEmpleados%> <%=ApellidosEmpleados%> </strong>                   
                  <small><strong><%=nom_empresa%> - <%=RolEmpleado.toUpperCase()%></strong></small>
                  <input type="text" id="id_empleado" value="<%=IdEmpleado %>"  hidden=""/>
                  <input type="text" id="rol_empleado" value="<%=RolEmpleado.toUpperCase()%>" hidden=""/>
                  
                </p>
              </li>
              <li hidden="true"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
              
              <!-- Menu Footer-->
              <li class="user-footer" hidden="">
                <div class="pull-right">
                  <a href="#"  class="btn btn-default btn-flat">Salir</a>
                </div>
              </li>
            </ul>
                <li><a href="#"><span class="glyphicon glyphicon-user text-green"></span> <%=NomEmpleados%> <%=ApellidosEmpleados%> </a></li>
                <li><a href="#" onclick="ConfiemaSalidaSistema();"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
          </li>
          
        </ul>
      
      </div>

    </nav>
  </header>
 <aside class="main-sidebar" >
    <!-- sidebar: style can be found in sidebar.less -->
    <!-- /.sidebar -->
    <input type="text" value="N" id="bandera_consulta_ascy" hidden/>
<section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
            <% if(SstrSexo.toUpperCase().equals("MASCULINO")){
                %>
            <img src="dist/img/operadorM2.png" class="img-circle" alt="User Image">
            <%
            }else{
             %>
            <img src="dist/img/operadorf.png" class="img-circle" alt="User Image">
            <%
            }
            %>
          <!--img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image"-->
        </div>
        <div class="pull-left info">
            
            <p class="text-uppercase"><%=USER_SESION%></p>
          <a href="#"><i class="fa fa-circle text-green"></i> <%=RolEmpleado.toUpperCase()%></a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form" hidden="true">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header"> <%=NomEmpleados%> <%=ApellidosEmpleados%> </li>
        
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
              <a href="#" class="text-info text-uppercase">
            <i class="fa fa-th text-red"></i>
            <span><%=modulos.get(i).getLcModulos().getMenuOpciones() %></span>
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
                                <li><a href="#" class="text-info text-bold" onclick="<%=modulos2.get(j).getLcModulos().getFunciones() %>"><i class="fa fa-circle-o text-info"></i> <%=modulos2.get(j).getLcModulos().getMenuOpciones() %></a></li>
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

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#9e9998" >
      <div id="page-wrapper" > 
          <br>
          <div  class="row">
              <div  class="col-lg-12 pannel panel-body ">                                                  
                                    <div class=" pannel panel-body">
                                        <form action="home" method="POST">
                                        <div class="row" class=" pannel panel-heading">
                                            <div class="col-lg-4"hidden>
                                                <div class="input-group input-group-sm" >
                                                    <span class="input-group-addon" id="acc">Empleados:</span>
                                                    <input name="accion" id="accion" value="consulta" />
                                                  </div>
                                                
                                            </div>
                                            
                                            <div class="col-lg-4">
                                                <div class="input-group input-group-sm" >
                                                    <span class="input-group-addon" id="sizing-addon3">Empleados:</span>
                                                    <select class="form-control input-sm" name="s_empleado"  id="s_empleado">                                          
                                                    </select>
                                                  </div>
                                                
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="input-group input-group-sm">
                                                    <span class="input-group-addon" id="sizing-addon3">Carteras: </span>
                                                    <select class="form-control  input-sm" name="s_cliente" id="s_cliente">                                         
                                                    </select>
                                                  </div>                                                
                                            </div>
                                            <div class="col-lg-2 " >
                                                 <div class="input-group input-group-sm ">
                                                    <span class="input-group-addon">Fecha:</span>
                                                    <input type="text" class="form-control" placeholder="YYYY-MM-DD" aria-describedby="sizing-addon3" name="fecha_cons" id="fecha_cons">
                                                  </div>
                                            </div>
                                            <div class="col-lg-1">
                                                <div class="input-group input-group-sm ">
                                                     <button type="submit" class="btn btn-sm btn-success"> Consultar </button>
                                                </div>
                                               
                                                <!--a type="button" class="btn btn-success" onclick="gestiones_diarias()"
                                                <a href="#" onclick="gestiones_diarias();" class="btn btn-success">Consultar</a>-->
                                            </div>
                                            
                                        </div>
                                      </form>
                                          <div id="container4" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

                                    </div>
                                  </div>
              
          </div>
          
          
                   
               <%
                        if (RolEmpleado.toUpperCase().equals("OPERADOR")){
                            %>
                            <form class="well">
                                <!--div class="row">

                                  <div class="col-lg-4">
                                    <div class="thumbnail">  

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
                                </div-->

                              <!--div class="row">  
                                <div class="col-lg-8">                                                  
                                    <div class="thumbnail">


                                          <div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

                                    </div>
                                  </div>
                                </div-->
                              <div class="row">
                                  
                                    <div class="col-lg-4">
                                                  <!--div class="row">

                                                      <div class="col-lg-6">
                                                        <div class="form-group">
                                                           <label for="sel1">Periodo:</label>
                                                           <select class="form-control" id="sel1">
                                                             <option>All</option>
                                                             <option>Enero</option>
                                                             <option>Febrero</option>
                                                             <option>Marzo</option>
                                                             <option>Abril</option>
                                                             <option>Mayo</option>
                                                             <option>Junio</option>
                                                             <option>Julio</option>
                                                             <option>Agosto</option>
                                                             <option>Septiembre</option>
                                                             <option>Octubre</option>
                                                             <option>Noviembre</option>
                                                             <option>Diciembre</option>
                                                           </select>
                                                         </div>
                                                      </div>
                                                  </div

                                                <div class="thumbnail">


                                                        <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                                        <button class="btn btn-default">Reporte</button>
                                                  </div>-->
                                                  <div class="thumbnail">


                                                        <div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                                        <!--button class="btn btn-default">Reporte</button-->
                                                  </div>
                                      </div>
                                </div>
           
          
                        </form> 

                            <%

                          }
                        %>
                  
                         
      </div>
         
      
      
      
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.0
    </div>
        <strong> <a href="#">estalisto.net</a>&copy; 2018. </strong>  COGESCORP S.A. - Todos los derechos reservados.

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

<script src="dist/js/jquery.datetimepicker.full.js"></script>


<script src="dist/code/highcharts.js"></script>
<script src="dist/code/modules/exporting.js"></script>

<%DashcboardOk gfc=new DashcboardOk();%>
    
    
 

     <script src="dist/js/jquery.datetimepicker.full.js"></script>
     <script>
         $('#fecha_cons').datetimepicker({   format:'Y-m-d' }); 
         consultaMisClientes();
         function consultaMisClientes(){
             
             var IDUserRol = $("#IDUserRol").val();
    document.getElementById("s_cliente").innerHTML="";
    console.log("IDUserRol"+IDUserRol);
     if(IDUserRol==="72" || IDUserRol==="7" || IDUserRol==="12"){
            $("#s_cliente").append($("<option>",{value:"0",text:"Seleccione la cartera"}));
     }
    //  $("#cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("consultacartera", {"accion" : "ClientesPorRol"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#s_cliente").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
           //var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}

//empleados
consultaMisEmpleados();
//ConsultasMisClientesXUsuario();
function consultaMisEmpleados(){
             
   var IDUserRol = $("#IDUserRol").val();
    document.getElementById("s_empleado").innerHTML="";
    console.log("IDUserRol"+IDUserRol);
    if(IDUserRol==="72" || IDUserRol==="7" || IDUserRol==="12"){
            $("#s_empleado").append($("<option>",{value:"0",text:"Seleccione el empleado"}));
     }
    //  $("#cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("empleados", {"accion" : "MisEmpleados"}, function(result){
          $.each(result.listaEmpleados, function(key, val){   
           $("#s_empleado").append($("<option>",{value:val.id_empleado,text:val.empleado}));
           //var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}/*
fncConsultaGestionesDiarias(0,0,0);
function fncConsultaGestionesDiarias(pn_id_empleado,pv_fecha,pn_id_cliente){
    document.getElementById("container4").innerHTML="";
    
     var parametros = {
        "accion": "GestionesDiarias"
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            console.log("container4"+response.toString());
              document.getElementById("container4").value=response.toString();
        }
    });
    
}
*/



     
     </script>
     <script>
         
function gestiones_diarias(){
    //var s_empleado=$("#s_empleado").val();  
    var s_empleado = $("#s_empleado").val();
    var s_cliente = $("#s_cliente").val();
    var fecha_cons = $("#fecha_cons").val();
    if(s_cliente==="0"){
        MsgSalidaModalA("Debe seleccionar una cartera");
        return;
        
    }
    $("#midashboard").load("home", {"accion":"consulta","s_empleado":s_empleado,"s_cliente":s_cliente,"fecha_cons":fecha_cons});
 
    
}
     </script>   
     
     <script>
         
         
         function consulta_push(){
                //var id_empleado = $("#id_empleado").val();             
                var accion = "notificaciones";
                console.log("consultando...  ");
                var parametros = {
                    //"id_empleado": id_empleado,
                    "accion": accion
                };
                var titulo=false;
                var item="";
               // console.log("id_empleado"+id_empleado);
             $.getJSON("home", {"accion" : "notificaciones"}, function(result){
              $.each(result.Notificaciones, function(key, val){   
                 
                 document.getElementById("num_notificaciones").innerHTML=val.cantidad;
                 if(!titulo){
                 if(val.cantidad>0 && val.cantidad<2){
                  document.getElementById("titulo_notificaciones").innerHTML="Tienes "+val.cantidad+" Notificaión";   
                 }else{
                     document.getElementById("titulo_notificaciones").innerHTML="Tienes "+val.cantidad+" Notificaiones";
                 }
                 titulo=true;                     
                 }
                 item = item+"<li><a href='#'><i class='"+val.icono+" text-aqua'></i> "+val.cantidad+" "+val.titulo+"</a></li>";
              
                    
                 
              // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
              // $("#tsub_cartera").append($("<option>",{value:val.idSubcartera,text:val.nombreSubcartera}));
             //  document.getElementById("num_notificaciones").value=val.cantidad;
               //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
              });
              document.getElementById("menu_notificaciones").innerHTML=item;
        });
                

}
   //  setTimeout(, 3000); 
   /*   
     consulta_bandera();
    
    function consulta_bandera(){
         var dato="";
         var parametros = {
        "accion": "consulta_parametro",
        "parametro": "LB_CONSULTA_NOTIFICACIONES"
    };
        $.ajax({
        data: parametros,
        url: 'parametros',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
           document.getElementById("bandera_consulta_ascy").value="";
              document.getElementById("bandera_consulta_ascy").value=response.toString();
               dato=response.toString();
               dato=$("#bandera_consulta_ascy").val();
               console.log(">>>>>>>>>ok"+dato);
        }
    });

     }*/
   
    

     </script>
      <script src="dist/js/push.min.js"></script>
    <script  type="text/javascript" >
	window.onload =function(){
		Push.Permission.request();
	}
/*
setInterval(function(){
	Push.create('Notificacion de Prueba',{
		body : 'Esta Notificacion de Prueba',
		icon : 'dist/img/call.png',
		timeout : 5000,
		vibrate : [100,100,100],
		onClick	: function(){
			alert("listo");
		}

	});},10000);*/

setInterval(function(){
   fnc_consulta_mis_notificaciones();
        // fnc_notificacion('VOLVER A LLAMAR','0922676945 \nSTALYN GRANDA \nBanco Pacifico','0922676945 \nSTALYN GRANDA \nBanco Pacifico');
},30000);
function fnc_notificacion(titulo,mensaje,IdDeudor,IdClienteCartera,id_recordatorio){
    Push.create(titulo,{
		body : mensaje,
		icon : 'dist/img/call.png',
		timeout : 5000,
		vibrate : [100,100,100],
		onClick	: function(){
                    
                       document.getElementById("notif_cliente").value="";
                       document.getElementById("notif_deudor").value="";
                       //document.getElementById("notif_cliente").value=14;
                       //document.getElementById("notif_deudor").value=5868;
                       fnc_registra_datos_gestion(IdDeudor,IdClienteCartera);
                       fnc_deudor_gestionado_ok(id_recordatorio);
                       window.open('home?IdDeudor='+IdDeudor+'&IdClienteCartera='+IdClienteCartera, '_blank');
                       this.close();
                       
                        
		}});
}

   validar_gestion();     
function validar_gestion(){
     var notif_cliente= document.getElementById("notif_cliente").value;
    var notif_deudor=document.getElementById("notif_deudor").value;
 if(notif_cliente!=="" & notif_deudor!==""){
      consulcarteras_sss(this);
    }
}

function fnc_registra_datos_gestion(IdDeudor,IdClienteCartera){
    var parametros = {
                "accion" : "registra_gestion",
                "IdDeudor" : IdDeudor,
                "IdClienteCartera" : IdClienteCartera
                };
        $.ajax({
                data:  parametros,
                url:   'autoriza',
                type:  'POST',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                        console.log(response.toString());
                      }                      
                }
        });
}
  function fnc_elimina_gestion(){
    var parametros = {
                "accion" : "elimina_registro_gestion"                
                };
        $.ajax({
                data:  parametros,
                url:   'autoriza',
                type:  'POST',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                        console.log(response.toString());
                      }                      
                }
        });
}	
</script> 
<script>
   // fnc_consulta_mis_notificaciones();
    
    function fnc_consulta_mis_notificaciones(){
        
        $.getJSON("home", {"accion":"ConsultaMisNotificaciones"}, function(result){

       
          $.each(result.Notificaciones, function(key, val){ 
                console.log("nombres_completo >>"+val.nombres_completo+" "+val.id_datos_deudor+" "+val.id_cliente);
                fnc_notificacion('VOLVER A LLAMAR','CED: '+val.identificacion+'\n'+val.nombres_completo+' \n'+val.cliente+'',val.id_datos_deudor,val.id_cliente,val.id_recordatorio);
                
    
            });
        });
    }
    function fnc_deudor_gestionado_ok(id_recordatorio){
var parametros = {
                "accion" : "deudor_gestionado_ok", 
                "id_recordatorio":id_recordatorio
                };
        $.ajax({
                data:  parametros,
                url:   'home',
                type:  'POST',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                        console.log(response.toString());
                      }                      
                }
        });
    

     }
</script>
<script>
    
      google.charts.load('current', {'packages':['corechart']});
   //   google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
            
          <%  String valores="";
          if(request.getAttribute("GraficaDiaria")!=null){
              valores=request.getAttribute("GraficaDiaria").toString();
          %>
      
           <%=valores%>
          <%
           }else{
                Integer mis_empleados=0;       
                String permisos_roles= param.Consulta_Parametro("LB_FILTROS_USUARIOS");                            
                if(!permisos_roles.contains(RolEmpleado)){
                mis_empleados=Integer.parseInt(IdEmpleado);
                }
      %>
              <%=gfc.fnc_ConsultaMisGestionesDiariasOK(Integer.parseInt(id_empresa),mis_empleados,"",0) %>
      <%    
      }
      
      %>
     
            
             
             
      ]);

    var options = {
      title : 'Producción Diaria',
      vAxis: {title: 'Gestiones'},
      hAxis: {title: 'Clientes'},
      seriesType: 'bars'
    };

    var chart = new google.visualization.ComboChart(document.getElementById('chart_gestiones_mensual'));
    chart.draw(data, options);
  }
</script>
<!--script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart3);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Clientes','eharo','jbanchon1','jbanchon2','jbanchon3','jbanchon4','jbanchon5','jbanchon6','jbanchon7',],
            ['ALMACENES PYCCA SA',1,4,0,0,2,2,2,2,],
            ['DEPRATI',4,0,5,7,0,0,0,0,],
        ]);

        var options = {
          chart: {
            title: 'Mis carteras',
			subtitle: 'Gestiones diaras',
            subtitle: 'Sales, Expenses, and Profit: 2014-2017',
          },
		   vAxis: {format: 'decimal'},
		   //height: 300,
		   //width: 500,
          bars: 'vertical' //horizontal vertical Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('chart_gestiones_diarias'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
    </script>
    <script>
        
          function GraficaMensuales(){
      //  var grafico_dash=$()
        
         document.getElementById("grafico_dash").innerHTML ="";
           document.getElementById("grafico_dash").innerHTML =
         "<div  id='grafico_dash' ><div class='box box-warning'>"+
"<div class='box-header with-border'><h3 class='box-title'>Valores Recuperado </h3><div class='box-tools pull-right'>"+
"<button type='button' class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>"+
"</div></div><div class='box-body'><div id='chart_gestiones_diarias666' ></div></div>"+
"<div class='box-footer clearfix'><!--a href='#' onclick='borrar_grafico()' class='btn btn-sm btn-default '>Consultar</a></div>"+
"</div></div>";
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart3);



}

        function drawChart3() {
    
   
        var data = google.visualization.arrayToDataTable([
           
        
                    
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
        
    </script-->
        <script>
   
            
    $('#fecha_gestion').datetimepicker({   format:'Y-m-d' });    
    $('#fecha_gestion_ini').datetimepicker({   format:'Y-m-d' }); 
    $('#fecha_gestion_fin').datetimepicker({   format:'Y-m-d' }); 
    $('#fecha_ini_calidad').datetimepicker({   format:'Y-m-d' }); 
    $('#fecha_fin_calidad').datetimepicker({   format:'Y-m-d' });   
    $('#fecha_ini_calidad2').datetimepicker({   format:'Y-m-d' }); 
    $('#fecha_fin_calidad2').datetimepicker({   format:'Y-m-d' });  
    
    
    </script>
    <script>
       // ConsultasMisCompromisosAll();
        miGraficaProduccion();
        miGraficaCarteraVsRecuperado();
       //cuadro_eficiencia_porcentaje();
        <% if(grafica){
            %>
             //consulta_cuadro_calidad();
             consulta_cuadro_calidad_eficiencia();
             //GraficaMensuales();
           miGraficaRecuperadoPorUsuario();
             <%
            }         
        %>
       
        
      
      
    function consulta_cuadro_calidad(){
    var fecha_inicial=$("#fecha_ini_calidad").val();
    var fecha_final=$("#fecha_fin_calidad").val();
     var accion = "cuadro_calidad2";
     console.log("fecha_inicial: "+fecha_inicial);
    // alert("Calidad");
    

 document.getElementById("mitable_calidad").innerHTML = "";
    var htmlTable="<table id='idCuadroCalidad' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-sm-2'>Usuario</th><th class='col-sm-2'>Cartera</th><th class='col-sm-2'>Sub Cartera</th><th class='col-sm-2'>T.Asignados</th><th class='col-sm-2'>Total Deuda</th><th class='col-sm-2'>Gestiones</th><th class='col-sm-2'>Recuperado</th><th class='col-sm-1'>Eficiencia</th></tr></thead><tbody></tbody></table></div>";
    document.getElementById("mitable_calidad").innerHTML = htmlTable;
    
    $('#idCuadroCalidad').DataTable( {
        "ajax": {   
            "data": {"accion": accion},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "usuario"},
                { "data": "cartera"},
                { "data": "sub_cartera"},
                { "data": "asignados",class:'text-right' },
                { "data": "deuda",class:'text-right' },
                { "data": "gestiones" ,class:'text-right'},
                { "data": "recuperado"  ,class:'text-right' },
                { "data": "eficiencia",class:'text-right' }
            ],
            paging: false,
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			},
                          
            scrollX:        true,
            scrollCollapse: true,
    } );
       
}

function consulta_cuadro_calidad_eficiencia(){
    var fecha_inicial=$("#fecha_ini_eficiencia").val();
    var fecha_final=$("#fecha_fin_eficiencia").val();
     var accion = "cuadro_calidad2";
     console.log("fecha_inicial: "+fecha_inicial);
    // alert("Calidad");
    

 document.getElementById("mitable_calidad").innerHTML = "";
    var htmlTable="<table id='idCuadroCalidad' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-sm-2'>Usuario</th><th class='col-sm-2'>Cartera</th><th class='col-sm-2'>Sub Cartera</th><th class='col-sm-2'>T.Asignados</th><th class='col-sm-2'>Total Deuda</th><th class='col-sm-2'>Gestiones</th><th class='col-sm-2'>Recuperado</th><th class='col-sm-1'>Eficiencia</th></tr></thead><tbody></tbody></table></div>";
    document.getElementById("mitable_calidad").innerHTML = htmlTable;
    
    $('#idCuadroCalidad').DataTable( {
        "ajax": {   
            "data": {"accion": accion},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "usuario"},
                { "data": "cartera"},
                { "data": "sub_cartera"},
                { "data": "asignados",class:'text-right' },
                { "data": "deuda",class:'text-right' },
                { "data": "gestiones" ,class:'text-right'},
                { "data": "recuperado"  ,class:'text-right' },
                { "data": "eficiencia",class:'text-right' }
            ],
            paging: false,
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			},
                          
            scrollX:        true,
            scrollCollapse: true,
    } );
       
}

/**
 * Comment
 */
limpiar_filtros_ini();
function limpiar_filtros_ini(){
        cuadro_eficiencia_porcentaje();
        document.getElementById("fecha_ini_calidad2").value = "";
        document.getElementById("fecha_fin_calidad2").value = "";
        ConsultasMisClientesXUsuario();
      //  ConsultasMisSubCarteras();
}
function cuadro_eficiencia_porcentaje(){
        var fecha_inicial=$("#fecha_ini_calidad2").val();
        var fecha_final=$("#fecha_fin_calidad2").val();
        var tcartera=$("#tcartera").val();
        var tsub_cartera=$("#tsub_cartera").val();
        
        var accion = "cuadro_eficiencia_porcentaje";

        document.getElementById("mitable_calidad2").innerHTML = "";
        var htmlTable="<table id='idCuadroCalidad2' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-sm-2'>Gestores</th><th class='col-sm-2'>Cartera</th><th class='col-sm-2'>Sub Cartera</th><th class='col-sm-2'>Client/Asig.</th><th class='col-sm-2'>#Gestiones</th><th class='col-sm-2'>#Gestionados</th><th class='col-sm-2'>#Clientes Contactados</th><th class='col-sm-2'>Mensaje Directo</th><th class='col-sm-2'>Compromisos Pago</th><th class='col-sm-2'>%Eficiencia Contactado</th><th class='col-sm-2'>%Eficiencia MD</th><th class='col-sm-2'>%Efic. Comp. Pago</th><th class='col-sm-1'>Cumplimiento CP</th></tr></thead><tbody></tbody></table></div>";
        document.getElementById("mitable_calidad2").innerHTML = htmlTable;
    
        
        $('#idCuadroCalidad2').DataTable({
        "ajax": {   
            "data": {"accion": accion,"fecha_inicial":fecha_inicial,"fecha_final":fecha_final,"tcartera":tcartera,"tsub_cartera":tsub_cartera},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "usuario"},
                 { "data": "cartera"},
                { "data": "subcartera"},
                { "data": "clientes_asignados",class:'text-right' },
                { "data": "gestiones_all",class:'text-right' },
                { "data": "cli_gestionados",class:'text-right' },
                { "data": "cli_contactado",class:'text-right' },
                { "data": "cli_contatado_direc",class:'text-right' },
                { "data": "cli_compromiso",class:'text-right' },
                { "data": "eficiencia_contac",class:'text-right' },
                { "data": "msm_directo",class:'text-right' },
                { "data": "eficiencia_compromiso",class:'text-right' },
                { "data": "compro_efect" ,class:'text-right'}
            ],
             
            paging: false,
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			},
            scrollX:        true,
            scrollCollapse: true
    } );
     
        
}





function consulta_cuadro_calidad_all(){
    var fecha_inicial=$("#fecha_ini_calidad2").val();
    var fecha_final=$("#fecha_fin_calidad2").val();
    var accion = "cuadro_calidad";
     //
     //console.log("fecha_inicial: "+fecha_inicial);
   /* if(fecha_inicial===""){
       alert("Debe Ingresar una fecha Desde");
       return;
    }
    
    if(fecha_final===""){
       alert("Debe Ingresar una fecha Hasta");
       return;
    }*/

 document.getElementById("mitable_calidad").innerHTML = "";
    var htmlTable="<table id='idCuadroCalidad' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-sm-2'>Usuario</th><th class='col-sm-2'>Cartera</th><th class='col-sm-2'>Sub Cartera</th><th class='col-sm-2'>T.Asignados</th><th class='col-sm-2'>Total Deuda</th><th class='col-sm-2'>Gestiones</th><th class='col-sm-2'>Recuperado</th><th class='col-sm-1'>Eficiencia</th></tr></thead><tbody></tbody></table></div>";
    document.getElementById("mitable_calidad").innerHTML = htmlTable;
    
    $('#idCuadroCalidad').DataTable( {
        "ajax": {   
            "data": {"accion": accion,"fecha_inicial":fecha_inicial,"fecha_final":fecha_final},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "usuario"},
                 { "data": "cartera"},
                { "data": "sub_cartera"},
                { "data": "asignados",class:'text-right' },
                { "data": "deuda",class:'text-right' },
                { "data": "gestiones" ,class:'text-right'},
                { "data": "recuperado"  ,class:'text-right' },
                { "data": "eficiencia",class:'text-right' }
            ],
             
            paging: false,
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			},
            scrollX:        true,
            scrollCollapse: true
    } );
       
}

    </script>
	 <script type="text/javascript">
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<!DOCTYPE html><html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><meta charset="utf-8" /><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML }
  window.location.href = uri + base64(format(template, ctx))
    
   
   
   
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!

var yyyy = today.getFullYear();
if(dd<10){
    dd='0'+dd;
} 
if(mm<10){
    mm='0'+mm;
} 
var today2 = yyyy+''+mm+''+dd;
console.log("today2"+today2);
     var link = document.createElement("a");
            link.download = "Cartera_"+today2+".xls";
            link.href = uri + base64(format(template, ctx));
            link.click();
  }
})()
         
</script>
</body>
</html>
