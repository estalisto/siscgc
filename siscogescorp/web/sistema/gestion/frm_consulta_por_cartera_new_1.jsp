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
border: 1px solid #FAC197;
}
select:enabled {
background-color:#FDF9DB;
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

<body>
    <ol class="Breadcrumb default">
                    <div class="btn-group btn-breadcrumb">
                        <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>
                        <a href="#" class=" btn btn-default" onclick="compropago();">COMPROMISOS DE PAGO</a>
                        <a href="#" class="active btn btn-default"onclick="hidden_cartera_cliente('false');">CONSULTA CARTERA</a>
                        <a href="#" class="btn btn-default" onclick="hidden_cartera_cliente('true');">GESTIÓN CLIENTE</a>
                        <a href="#" data-toggle="modal" class="btn btn-default" data-target="#myModalBusqueda" id="busqueda_deudor">BUSCAR CLIENTE</a>
                    
                        <a href="#referencia" onclick="listo();" id="listoModal" data-toggle="modal" class="active btn btn-success " hidden>Referencia</a>
                        <a onclick="AddDirModal();" class="active btn btn-success " hidden>Agregar Dirección</a>
                        <a onclick="AddTelModal();" class="active btn btn-success " hidden>Agregar Teléfono</a>
                        <a href="#det_articulo" onclick="ComprasJson();" data-toggle="modal" class="active btn btn-success " hidden>Detalles Artículo</a>
                        <a href="#det_cuotas"  data-toggle="modal" class="active btn btn-success " hidden>Detalles Cuotas</a>
                        <a href="#historial_pagos"  data-toggle="modal" onclick="consulta_historial();" class="active btn btn-success " hidden>Historial Pagos</a>
                    
                    </div>                    
                </ol>


   <ol class="breadcrumb" hidden>
                    <li><a href="#" onclick="hidden_cartera_cliente('false');" >Consulta Cartera</a></li>
                    <li class="active"><a href="#" onclick="hidden_cartera_cliente('true');">Gestión Cliente</a></li>
                    <% 
                     try{
                            String cadena=param.getValorParametro("LB_VALIDA_BUSQUEDA_CLIENTE");
                            int intIndex = cadena.indexOf(sesion.getAttribute("SstrRolEmpleado").toString());
                            if(intIndex != - 1){
                                //response.getWriter().println("true");
                             %>
                            <li><a data-toggle="modal" data-target="#myModalBusqueda" id="busqueda_deudor">Buscar Cliente</a></li>
                            <%
                            }
            
                        }catch (Exception ex){
                            response.getWriter().println("flase");
                        }
                    
                    
                    %>
                    
                    
                    
                   
   </ol>
     <input  type="text" class="form-control input-sm hidden" id="hidden_ok" value="true">
     
<div id="cliente_cartera">    
<section class="panel panel-default well-lg margin" hidden>
    <div class="row">
        <div class="col-lg-12">
            <div class="col-sm-1">
                <label>Cliente Cartera:</label>
             </div>
            <!-- /*Codigo 007: Inicio cambio */
            /*Desarrollador: Jimmy Guaranda*/
            /*Objetivo: Nuevos filtros*/-->
            <!--div class="col-sm-2" id="filtro_cartera">
                 <select class="form-control " name="cartera" required="required" id="cartera" onchange="getipoCartera();"  >
                     <option value="0">Selecciones Cartera</option>
                  <c:forEach items="${carteras}" var="carter">
                      <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                  </c:forEach> 
                </select>
             </div-->                                             
            <!--Fin cambio Jimmy Guaranda -->
             <!--div class="col-sm-1">                        
                <button id="btnbuscar" type="submit" class="btn btn-primary btn-sm fa fa-search"> BUSCAR</button>                       
             </div-->
             <div class="col-sm-1" id="filtro_busqueta">                        
                <a href="#det_filtro" onclick="getTiposGestiones();"  data-toggle="modal" class="btn btn-primary  btn-sm fa fa-filter">  FILTRO  </a>
             </div>
             <div class="col-sm-8">
                 
                 
             </div>
             <div class="col-sm-0"> 
                 <center><img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="35" height="35"  style="display: none"></center>
             </div> 
        </div>
    </div>
</section>
    <div class="row">
        <div class="col-lg-4">
            
        </div>
        <div class="col-lg-8">
            
        </div>
        
    </div>
    
    
    <div class="box box-default">
      <div >
        <input  type="text" class="form-control input-sm hidden" id="IdDiasMora">
        <input  type="text" class="form-control input-sm hidden" id="IdTotalVenc">
        <input  type="text" class="form-control input-sm hidden" id="IdFechaUlt">
        <input  type="text" class="form-control input-sm hidden" id="IdIdentificacion">
        <input  type="text" class="form-control input-sm hidden" id="IdNombres">
        <input  type="text" class="form-control input-sm hidden" id="IdPagos">
        <input  type="text" class="form-control input-sm hidden" id="IdFecUltPagos">
        <input  type="text" class="form-control input-sm hidden" id="IdSaldo">
        <input  type="text" class="form-control input-sm hidden" id="IdValorComp">
        <input  type="text" class="form-control input-sm hidden" id="IdFechaComp">
        <input  type="text" class="form-control input-sm hidden" id="IdUltima">
        <input  type="text" class="form-control input-sm hidden" id="IdResultado">  
        <input  type="text" class="form-control input-sm hidden" id="input_query" >
        <input  type="text" class="form-control input-sm hidden" id="secuencia_query" >
         <input  type="text" class="form-control input-sm hidden" id="idNotas" value="0" >
      </div> 
      <form class="form-inline form-control-sm">
          <br>
                     <div class="input-group input-group-sm">
                        <span class="input-group-addon" id="ttvencidos" >Total Vencido: <strong>$0.00</strong></span>
                        <!--input type="text" class="form-control" placeholder="0.00" aria-describedby="sizing-addon3"-->
                      </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon" id="ttpagos" >Total Pagado: <strong>$0.00</strong></span>
                        <!--input type="text" class="form-control" placeholder="0.00" aria-describedby="sizing-addon3"-->
                      </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon"  id="ttsaldos" >Saldos: <strong>$0.00</strong></span>
                        <!--input type="text" class="form-control"  placeholder="0.00" aria-describedby="sizing-addon3"-->
                      </div>
                    <div class="input-group input-group-sm">
                      <span class="input-group-addon" id="cant_clientes_asignados"  >Asignados <strong>#0</strong></span>
                      <!--input type="text" class="form-control" id="cant_clientes_asignados" placeholder="0" aria-describedby="sizing-addon3"-->
                    </div>
                       <div class="input-group input-group-sm">
                            <span class="input-group-addon" >Cartera: </span>
                        <select class="form-control " name="cartera" required="required" id="cartera" onchange="getipoCartera();"  >
                            <option value="0">Selecciones Cartera</option>
                         <c:forEach items="${carteras}" var="carter">
                             <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                         </c:forEach> 
                       </select>
                      </div>
                      <div class="input-group input-group-sm">
                        <a href="#det_filtro" onclick="getTiposGestiones();"  data-toggle="modal" class="btn btn-primary  btn-sm glyphicon glyphicon-filter">  FILTRO  </a>
                      </div>
                       <div class="input-group input-group-sm">
                        <a  onclick="tableToExcel('tabla_div', 'cartera');"   class="btn btn-success  btn-sm glyphicon glyphicon-download-alt">  EXPORTAR  </a>
                      </div>
                    
           
          
            
                     
                  </form>
      <div class="pannel pannel-body"  style="overflow-x: auto">
          
          
          <div id="tabla_div">
              
          
            <table id="consul_cartera" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                <thead>
                    <tr bgcolor='#BFF3A0'>                                  
                        <th class="col-sm-1 text-left hidden" style="color: #3c8dbc">ID</th>                                                        
                        <th align="left" class="col-sm-1 text-left"><a id="IdentificacionID" onclick="orderIdent()">Identificación</a></th>
                        <th class="col-sm-2 text-left"><a id="NombresID" onclick="orderNombre()">Nombres</a></th>  
                        <th class="col-sm-1 text-left"><a id="DiasMoraID" onclick="orderDiasMora()" >Días Mora</a></th> 
                        <th class="col-sm-1 text-right"><a id="TotalID" onclick="orderTotalVenc()" >Total Vnc</a></th> 
                        <th align="center" class="col-sm-1 text-right"><a id="PagosID" onclick="orderPagos()">Pagos</a></th>
                        <th align="center" class="col-sm-1 text-right"><a id="FecUltPagosID" onclick="orderFechaUltPagos()">Fecha Ult. Pagos</a></th>
                        <th align="rigth" class="col-sm-1 text-right"><a id="SaldosID" onclick="orderSaldo()">Saldo</a></th> 
                        <th align="center" class="col-sm-1 text-right"><a id="ValorCompID" onclick="orderValorComp()">Valor Comp.</a></th> 
                        <th align="center" class="col-sm-2 text-center"><a id="FechaCompID" onclick="orderFechaComp()">Fecha Comp.</a></th>
                        <th align="center" class="col-sm-3"><a id="FechaID" onclick="orderFchGestion()" >Fecha Ult. Gestión</a></th> 
                        <th align="center" class="col-sm-3"><a id="UltimaID" onclick="orderUltima()">Ult. Gestión</a></th> 
                        <th align="center" class="col-sm-2"><a id="ResultadoID" onclick="orderResultado()">Resultado Gestión</a></th>
                        <th align="center" class="col-sm-2"><a id="ResultadoID" onclick="orderResultado()">Acciones</a></th>
                    </tr> 
                </thead>
                <tbody id="bodytable">
                     <%= request.getAttribute("Tablacartera") %>
                </tbody>
            </table>
           </div>
      </div> 
  </div>   
<div id="det_filtro" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="detalle_filtro">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
                <div class="modal-header">
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                    <h4 class="model-title">USUARIO:
                            <select class="form-control input-sm" id="mi_empleado">
                                <%  String s_empleados2=""; s_empleados2= emp.misEmpleadosALL(Integer.parseInt(id_empresa), RolEmpleado, Integer.parseInt(IdEmpleado)); %>
                                <%=s_empleados2%>
                            </select>

                    </h4>
                    <hr>
                    <div class="row">
                    <div class="col-lg-4" hidden>      
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="dias_mora">  Días Mora <br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="total_vencidos">  Total Vencido <br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="pagos">  Pagos<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fecha_ult_pagos">  Fecha último pagos<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="saldo">  Saldo<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="valor_compro">  Valor Compromiso<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fecha_comp">  Fecha Compromiso<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fech_ultima_gestion">  Fecha Última Gestión<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="ultima_gestion">  Última Gestión<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="resultado_gestion">  Resultado Gestión<br> 
                                    <br>
                                    <div class="" hidden>
                                            <dt>Cartera: </dt>
                                            <select class="form-control" name="SelectCartera" id="SelectCartera" >
                                                    <option value='1' >Cartera 1</option>
                                                    <option value='2' >Cartera 2</option>
                                                    <option value='3' >Cartera 3</option>                                            
                                            </select>
                                    </div> 
                                    <div class="" hidden>
                                            <dt>Sub-cartera: </dt>
                                            <select class="form-control" name="SelectSubCartera" id="SelectSubCartera" >
                                                    <option value='1' >Subcartera 1</option>
                                                    <option value='2' >Subcartera 2</option>
                                                    <option value='3' >Subcartera 3</option>                                            
                                            </select>
                                    </div>
                                    <div class="" hidden>
                                            <dt>Segmento: </dt>
                                            <select class="form-control" name="SelectSegmento" id="SelectSegmento" >
                                                    <option value='1' >Segmento 1</option>
                                                    <option value='2' >Segmento 2</option>
                                                    <option value='3' >Segmento 3</option>                                            
                                            </select>
                                    </div> 
                                    <div class="" hidden>
                                            <dt>Sub-Segmento: </dt>
                                            <select class="form-control" name="SelectSubSegmento" id="SelectSubSegmento" >
                                                    <option value='1' >SubSegmento 1</option>
                                                    <option value='2' >SubSegmento 2</option>
                                                    <option value='3' >SubSegmento 3</option>                                            
                                            </select>
                                    </div>                                                                                                            
                    </div>
                    <div class="col-lg-12">             
                                    <div class="row">
                                                            <div class="col-lg-6">
                                                                       
                                                                    <div class="form-inline">
                                                                                    <label>Pagos</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="pagos" name="idpagos" placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="pagos1" name="idpagos1" placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Total Vencido</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="tvencido" name="idtvencido"  placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="tvencido1" name="idtvencido1"  placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Valor Compromiso</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="vcompromiso" placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="vcompromiso1" placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Saldos</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="saldos"  placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="saldos1"  placeholder="$ 0.00">
                                                                    </div> 
                                                                    <span id="escogecliente"></span>
                                                                    <div class="">
                                                                            <dt>Gestión: </dt>
                                                                            <select class="form-control  input-sm" onchange="ConsultaTipoResultado();" name="tgestion" id="tgestion" ></select> 
                                                                    </div>
                                                                    <div class="">
                                                                            <dt>Resultado de Gestión: </dt>
                                                                            <select class="form-control  input-sm " name="tresultado_gestion" id="tresultado_gestion" ></select>
                                                                    </div>

                                                            
                                                            </div>  
                                                            <div class="col-lg-6">
                                                                    <div class="form-inline">
                                                                                    <label>Días Mora</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora" name="idmora" placeholder="$ 0.00" >
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora1" name="idmora1" placeholder="$ 0.00" >
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>F. Últ. Pago</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm "  id="datetimepicker10" name="ultimo_pagodesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm "  id="datetimepicker11" name="ultimo_pagohasta" placeholder="YYYY-MM-DD" >                                                                                                                        
                                                                    </div>
                                                                    <div class="form-inline">  
                                                                                    <label>F. Últ. Gestión</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker12" name="ultima_gestiondesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker13" name="ultimo_gestionhasta" placeholder="YYYY-MM-DD" > 
                                                                    </div>

                                                                    <div class="form-inline">  
                                                                                    <label>Fecha Compromiso</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker14" name="fecha_compromisodesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker15" name="fecha_copromisohasta" placeholder="YYYY-MM-DD" > 
                                                                    </div>
                                                                   <!-- /*Codigo 007: Inicio cambio */
                                                                    /*Desarrollador: Jimmy Guaranda*/
                                                                    /*Objetivo: Nuevos filtros*/-->
                                                                    <div class="" >
                                                                        <dt>Cartera: </dt>
                                                                        <!--select class="form-control  input-sm" onchange=" getipoSubCartera();" name="tcartera" id="tcartera" disabled="true"></select--> 
                                                                        <select class="form-control  input-sm " name="tcartera" id="tcartera" onchange="getipoSubCartera();"  disabled="true"></select>
                                                                    </div>
                                                                    <div class="" >
                                                                            <dt>Sub-cartera: </dt>
                                                                            <select class="form-control  input-sm " name="tsub_cartera" id="tsub_cartera" onchange="getipoSegmento();" disabled="true"></select>
                                                                    </div>
                                                                    <div class="">
                                                                            <dt>Segmento: </dt>
                                                                           <!--select class="form-control  input-sm" onchange="ConsultaSubsegmento();" name="tsegmento" id="tsegmento" ></select--> 
                                                                           <select class="form-control  input-sm " name="tsegmento" id="tsegmento" onchange="getiposubSegmento();" disabled="true"></select>
                                                                    </div> 
                                                                    <div class="" >
                                                                            <dt>Sub-Segmento: </dt>
                                                                            <select class="form-control  input-sm " name="tsub_segmento" id="tsub_segmento" disabled="true"></select>
                                                                    </div>
                                                                   <!-- Fin cambio -->
                                                                <input type="text"  class="form-control input-sm hidden" id="order_by" >


                                                            </div>                                                    


                                                    
                                                    <br>

                                                           
                                    </div>           
                            </div>
                 </div>
                </div>
            <div class='modal-footer'>
                <button type="button" id="btnconsultar" onclick="consulta_filtro_cartera();" class="btn btn-primary btn-sm fa fa-archive"> CONSULTAR </button>
                <button type="button" class="btn btn-primary btn-sm fa fa-close" data-dismiss="modal"> CERRAR </button>
            </div>
        </div>
    </div>
</div>
                            
</div> 
<div id="gestion_cliente" hidden>
                <div class="panel panel-success  margin">
                        <div class="row">
                            <div class="col-lg-4">
                                <form class="form-horizontal">
                                    <div class="col-xs-3 " style="display:none" >
                                        <div class="form-group">
                                            <label>Código:</label>
                                            <input type="text" class="form-control"  id="id_deudor" name="id_deudor"  required="required">
                                        </div> 
                                    </div>
                                                     
                                    <div class="col-xs-3 " style="display:none">
                                        <div class="form-group">
                                            <label>Cliente:</label>
                                            <input type="text" class="form-control" id="idcliente" name="idcliente">
                                            
                                        </div> 
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">C.I/RUC:</label>   
                                        <input class="col-sm-6 input-sm" type="text" placeholder="Número de identificación" required="required" name="identificacion" value="" id="identificacion" readonly>                                    
                                    </div>
                           
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Cuenta:</label>   
                                        <input class="col-sm-6 input-sm" type="text" placeholder="Número de cuenta" required="required" name="cuenta" id="cuenta" value="" readonly>                   
                                    </div>
                                </form>
                            </div>

                            <div class="col-lg-5">
                                <strong>  <center><h2 id="deudor">Nombre</h2></center></strong>
                                <center><h4 id="cliente" style="color: #0063dc"><b>cliente</b></h4></center>   
                            </div>

                            <div class="col-lg-2"> 
                                <button id="anterior" type="button" onclick="Antdeudor2()" class="btn btn-primary btn-sm glyphicon glyphicon-arrow-left"></button>
                                <button id="siguiente" type="button" onclick="Sgtedeudor2()" class="btn btn-primary btn-sm glyphicon glyphicon-arrow-right"> </button>
                            </div>  
                            <div class="col-lg-1">
                            <center><img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                            </div>  
                        </div>
                          
                        <div class="row">                           
                          
                            <form class="form-horizontal">
                               
                                <div class="form-group">
                                    <label class="col-sm-1 control-label" style="color: #F66C27">     Total Deuda: </label> 
                                    <label class="col-sm-1 control-label" style="color: #030303" id="labelTotalDeuda"><u>$ 00</u></label>
                                    <label class="col-sm-2 control-label" style="color: #F66C27">Total Vencido: </label> 
                                    <label class="col-sm-1 control-label" style="color: #030303" id="labelTotalVencido"><u>$ 0</u></label>
                                    <label class="col-sm-1 control-label" style="color: #F66C27">Pagos: </label> 
                                    <label class="col-sm-1 control-label" style="color: #030303" id="labelPagos"><u>$ 00</u></label>
                                    <label class="col-sm-1 control-label" style="color: #F66C27">Saldo: </label>    
                                    <label class="col-sm-1 control-label" style="color: #030303" id="labelSaldos"><u>$ 00</u>
                                    </label><label class="col-sm-1 control-label" style="color: #F66C27">Días Mora: </label>  
                                    <label class="col-sm-1 control-label" style="color: #030303" id="labelDiasMora"><u>00 Dias</u></label>
                                </div>    
                            </form>  
                        </div>  
                            <div class="col-xs-3 hidden">
                                        <div class="form-group">
                                            <label>Código:</label>
                                            <input type="text" class="form-control" id="idTransaccion" name="idTransaccion" value="" required="required">
                                        </div> 
                            </div>          
                                    
                        <div class="btn-toolbar" role="toolbar">                    
                            
                                    
                                    <div id="modalADDTel"></div>
                                     <div id="modalDireccion"></div>
                                    <div id="agrega_datos" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                                        <div class="modal-dialog" role="document">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                              <h4 class="modal-title" id="exampleModalLabel">Agregar Datos:  </h4>
                                            </div>
                                             
                                            <div class="modal-footer">
                                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                    
                                    
                                    
                                    
                                    
                                    <div id="referencia" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="actulizacion">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="box-body">
                                                    <div class="col-md-12">
                                                        <div class="box">
                                                            <div class="box-header with-border bg-yellow">
                                                                <h3 class="box-title">Actualización de Datos</h3>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>  <br>
                                                                <form class="form-horizontal" role="form" name="miFormulario">  
                                                                     <label>Ingrese Nombre de la Referencia: </label>
                                                                    <div class="form-group">
                                                                        <div class="col-lg-3">
                                                                            <select class=" form-control" id="IdtReferencia" >
                                                                                <option value='1' >Tio </option>
                                                                                <option value='2' >Vecino</option> 
                                                                                <option value='3' >Hermano</option>  
                                                                                <option value='4' >Papa</option>  
                                                                                <option value='5' >Abuelo</option>  
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-lg-9">
                                                                            <textarea class="form-control" id="nombre_ref" name="nombre_ref" rows="1" placeholder="Nombre de la Referencia" style="overflow-y:scroll;"></textarea> 
                                                                        </div>                                                                  
                                                                    </div>
                                                                    
                                                                    <input class="form-control" name="telefono" id="Cont" value="0"  type="hidden" > 
                                                                    <div id="addHtmlTelfRef">
                                                                        
                                                                    </div>
                                                                    <button type="button" onclick="addHtmlTelefonoRef();" id="addInputButton" class="btn btn-success">+ Agregar Telefono</button>

                                                                    <button type="button" class="btn btn-success" id="GuardarRef" onclick="AddReferencias();">Guardar</button>
                                                                    <!--button type="button" class="btn btn-primary" id="DatReferencias" onclick="MuestraDatosReferencias();">Datos</button-->
                                                                  <br><br>
                                                                  <div id="DatosReferencias">
                                                                      
                                                                  </div>            
                                                                </form>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                 
                                     <div id="det_articulo"  class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_articulo">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <br><div class="box box-danger">
                                                    <div class="box-header with-border bg-yellow">
                                                        <h3 class="box-title">Desglose de Artículos</h3>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    </div>
                                                    <div id="TablaCompras" class="box-body" style="overflow-y:scroll;">
                                                        <table id="detalle_articulos" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%"> 
                                                            <thead>
                                                                <tr>
                                                                    <th>Referencia de Compras/Artículo</th>
                                                                    <th>Descripción</th>
                                                                    <th>Valor Articulo</th>                                                                    
                                                                    <th>Fecha de Compra</th>															  
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                 
                                                            </tbody>
                                                            <tfoot>
                                                                
                                                            </tfoot>  
                                                            
                                                        </table>
                                                    </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>      
                                        </div>
                                    </div>            

                                    
                                    <div id="det_cuotas" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_cuota">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <br><div class="box box-danger">
                                                    <div class="box-header with-border bg-yellow">
                                                        <h3 class="box-title">Detalle Cuotas</h3>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    </div> 
                                                    <div id="TablaDetalleCuotas" class="box-body" style="overflow-y:scroll;">
                                                        <table id="detalle_cuotas" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%"> 
                                                            <thead>
                                                                <tr bgcolor="#FBF5EF">
                                                                    <th>Artículo</th>
                                                                    <th>NºCuota</th>
                                                                    <th>Interés</th>
                                                                    <th>Mora</th>
                                                                    <th>Gastos Cobranzas</th>
                                                                    <th>Gastos Adicionales</th>
                                                                    <th>Otros Gastos</th>
                                                                    <th>Valor Cuota</th>
                                                                    <th>Total</th>
                                                                    <th>Fecha max Pago</th>
                                                                    <th>Fecha Compra</th>
                                                                    <th>Pagos Realizados</th>
                                                                    <th>Fecha Pagos Realizados</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                
                                                               
                                                                
                                                            </tbody>
                                                            <tfoot>
                                                                
                                                            </tfoot> 
                                                        </table>
                                                    </div>
                                                    <div class="modal-footer">
                                                       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                     <!-- historial_pagos -->
                                
                           <div id="historial_pagos" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_cuota">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <br><div class="box box-danger">
                                                    <div class="box-header with-border bg-default">
                                                        <h3 class="box-title">Historial Pagos</h3>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    </div> 
                                                    <div id="TablaHistorial" class="box-body">
                                                        <table id="TablaHistorialPagos" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%"> 
                                                            <thead>
                                                                <tr bgcolor="#FBF5EF">
                                                                    <th>Monto</th>
                                                                    <th>Fecha Pago</th>                                                                    
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                            <tfoot>
                                                            </tfoot> 
                                                        </table>
                                                        
                                                    </div>
                                                    <label id="id_total_pagos">Total:$0</label>
                                                    
                                                    <div class="modal-footer">
                                                       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                              
                        </div>
                                    
                                    
                        <br>
                                   
                        <div class="row">   
                            <div id="pagedireccion">  
                            <div class="col-lg-5">
                                <div id="table_direccion" class="form-group">                                
                                    <dt>Dirección: </dt>
                                    <div id="TablaDirecciones" class="box table-responsive" >
                                        <table id="idAllDireccions" class=" table-striped table-bordered dt-responsive table-condensed nowrap table-hover" cellspacing="0" width="100%">                                           
                                            <thead>
                                                <tr  bgcolor="#FBF5EF" width="100%">
                                                    <th class="col-sm-2">Tipo</th>
                                                    <th class="col-sm-6">Dirección</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                
                                            </tbody>
                                        </table>
                                    </div>                                       
                                </div>  
                            </div>
                            </div>
                            <div id="pagetelefono">  
                            <div  class="col-lg-3">        
                                <dt>Telefonos: </dt>
                                <div id="table_telefono" class="box table-responsive">  
                                    <table id="idAllTelefonos" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                        <thead>
                                            <tr  bgcolor="#FBF5EF">
                                                <th class="col-sm-2">Tipo</th>
                                                <th class="col-sm-8">Télefonos</th>
                                                <th class="col-sm-2">Llamar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>    
                            <div class="form-group col-lg-4">
                                <div class="row">
                                    <div class="col-lg-7">                                 
                                    <dt>Ciudad [Parroquia]: </dt>
                                    <select class="input-sm form-control" id="Ciudad" name="Ciudad" required="required" disabled="true">
                                        
                                    </select>
                                    </div>
                                            
                                    <div class="col-lg-5">
                                        <dt>Volver a Llamar: </dt>     
                                    <form class="form-inline">
                                        <div class="input-group date">
                                            <input size="15" type="text" class="form-control input-sm datepicker" data-date-format="yyyy-mm-dd hh:mi:ss" id="datepicker2" onkeyup="verificaFecha();" name="recordatorio" placeholder="YYYY-MM-DD" disabled="true">
                                        </div> 
                                            <input size="4" type="text" class="form-control input-sm" id="hora" name="hora" placeholder="23:59" onkeyup="validaHora();" disabled="true">
                                    </form>     



                                  
                                   
                                    </div>         
                                               
                                </div>    
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <dt>NOTAS</dt>                                
                                            </div>
                                            <div class="col-sm-1">
                                                <dt style="color:#F5A9A9" id="contador_notas">500</dt>
                                            </div>                            
                                        </div>
						<textarea maxlength="500" id="txtnota" class="form-control input-sm " rows="2" onkeyup="ValidarNota2()"  placeholder="NOTAS" style="overflow-y:scroll; background-color:#FDF9DB;  font-size:14px; font-type:Arial" value="0"></textarea>
						<!--textarea maxlength="500" id="txtnotaAdmin" class="form-control input-sm " rows="1" onkeyup="ValidarNota2()"  placeholder="NOTAS ADMINISTRADOR" style="overflow-y:scroll; background-color:#EDFAC5;  font-size:14px; font-type:Arial" value="0" readonly></textarea-->
                                        </div>




                        </div>
                        <div class="row"> 
                            <form name="form" action="cobranzas" method="post"  id="data">                 
                                <div class="col-lg-4">                                  
                                    <dt>Tipo de Gestión: </dt>
                                    <select class="input-sm form-control" name="gestion" id="gestion" required="required" onchange="obtenerResultado()">
                                      
                                        
                                    </select>                                 
                                </div>
                                <div class="col-xs-3 hidden">
                                    <div class="form-group">
                                        <label>Tipo Resultado escogido</label>
                                        <input type="text" class="form-control" id="tiporesultado" name="tiporesultado" value="" required="required">
                                    </div> 
                                </div>    
                                <div class="col-lg-4">                                  
                                    <dt>Resultado: </dt>
                                    <select class="input-sm form-control" name="resultado" required="required" id="resultado">
                                      

                                    </select>                                 
                                </div>

                                <div class="col-lg-4" >
                                    <dt> Fecha Compromiso de Pago y Monto:</dt> 
                                        <div class="form-inline ">
                                                <input size="15" type="text" class="input-sm datepicker" onkeyup="verificaFecha2();" data-date-format="yyyy-mm-dd" id="datepicker" name="compromiso_pago" placeholder="YYYY-MM-DD" disabled="true">                                                        
                                                <input size="15" type="text" class="input-sm" id="monto_compromiso" name="monto_compro" onkeypress="ValidaSoloNumeros()" placeholder="$ 0.00" disabled="true">
                                                <span id="fechaCompro" ></span>  
                                        </div>
                                </div>
                        </div>            
                                
                        <div class="row">
                            <div class="col-sm-10">
                                <dt>Descripción:</dt>                                
                            </div>
                            <div class="col-sm-1">
                                <dt style="color:#F5A9A9" id="contador_descripcion">600</dt>
                            </div>
                            
                        </div>
                            
                        
                            <div class="row"> 
                                <div class="col-sm-11">
                                      <textarea maxlength="600" class="form-control input-sm" id="descripcion" name="descripcion" rows="2" placeholder="Descripción" style="overflow-y:scroll; background-color:#FDF9DB; font-size:18px; font-type:Arial" ></textarea> 
                                </div>
                                <div class="col-sm-1">
                                    <br><button  type="button" onclick="GuardarTransaccnormal()" class="btn btn-primary btn-lg fa fa-save"></button>
                                </div>
                            </div> 
                              
                            </form>
                            <section class="content ">
                                <div id="pagetable">  
                                    <div id="transaccion_table"  class="box table-responsive"  >
                                       
                                            <table id="allTrxGestiones" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr  bgcolor="#FBF5EF">
                                                        
                                                        <th class="col-lg-1">Tipo Gestión</th>
                                                        <th class="col-lg-1">Gestión</th>
                                                        <th class="col-lg-5">Descripción</th>
                                                        <th class="col-lg-3">Oficial</th>
                                                        <th class="col-lg-2">Fecha</th>
                                                       
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>                       
                                            </table>
                                      
                                    </div>
                                </div> 
                            </section>
                        </div>
                        
                </div>
                <br>
              </div>
<!-- Modal -->
<div id="myModalBusqueda" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Buscar Cliente</h4>
      </div>
      <div class="modal-body">
        <div class="panel panel-warning">

  <div class="panel-body">
    <form class="form-horizontal">
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-4 control-label">Cartera</label>
    <div class="col-sm-6" >
      <select class="form-control" id="idcartera">
        
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-4 control-label">Identificación:</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="identificacion2" placeholder="0922676945">
    </div>
  </div>
  <div class="form-group" >
    <label for="inputPassword3" class="col-sm-4 control-label">Nombres y Apellidos</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="nombre_deudor" placeholder="Ingrese Nombres">
    </div>
  </div> 
        <span id="mensaje_error"></span>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
        <button type="button" onclick="BuscarCliente();" class="btn btn-default">Buscar</button>
    </div>
  </div>
</form>
  </div>
</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<script src="dist/js/consultaxcatera.js"></script> 
<script src="dist/js/cobranzas.js"></script>

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
//$('#datepicker2').datetimepicker({   format:'Y-m-d H:i:s' });
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
var lv_query_cant_clientes="select count(*) from  vw_consulta_cartera s  where s.id_empleado=IDEmpleadoConsulta and s.estado != 'E'";
var lv_query_sum="select sum(s.total_vencidos) as total_vencidos, sum(pagos) as pagos, (sum(s.total_vencidos) -  sum(pagos)) as saldo, ("+lv_query_cant_clientes+") as total_clientes  from  vw_consulta_cartera s  where s.id_empleado=IDEmpleadoConsulta and s.estado != 'E'";

Totales_Suman(lv_query_sum, 0);

});           
</script>

<script type="text/javascript">
    $(document).ready(function(){

        var max_chars = 600;

        $('#max').html(max_chars);

        $('#descripcion').keyup(function() {
            var chars = $(this).val().length;
            var diff = max_chars - chars;
            $('#contador_descripcion').html(diff);   
        });
    }); 
</script>
           
<script type="text/javascript">
        $(document).ready(function(){

            var max_chars = 500;

            $('#max').html(max_chars);

            $('#txtnota').keyup(function() {
                var chars = $(this).val().length;
                var diff = max_chars - chars;
                $('#contador_notas').html(diff);   
            });
        }); 
</script>
<script>
    
            ConsultasMisCarteras();
             //mostrar_busqueda_deudor();

//BuscarCliente idcartera identificacion  nombre_deudor


   
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
   // window.location.href = uri + base64(format(template, ctx))
    
   
   
   
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
var today = yyyy+''+mm+''+dd;




    
    
     var link = document.createElement("a");
            link.download = "Cartera_"+today+".xls";
            link.href = uri + base64(format(template, ctx));
            link.click();
  }
})()




                
</script>
<script>
    //id="notif_cliente"
    //id="notif_deudor"
    var notif_cliente= document.getElementById("notif_cliente").value;
    var notif_deudor=document.getElementById("notif_deudor").value;
    //alert("notif_cliente"+notif_cliente);
    if(notif_cliente!=="" & notif_deudor!==""){
       GestionCliente(notif_cliente,notif_deudor); 
    }
     document.getElementById("notif_cliente").value="";
     document.getElementById("notif_deudor").value="";
     fnc_elimina_gestion();
</script>
</body>
</html>

