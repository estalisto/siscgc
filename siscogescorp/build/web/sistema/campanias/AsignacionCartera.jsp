<%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : Stalyn Granda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

  <!DOCTYPE html>
<html>
  <head>      
        
           <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Minified Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">

<link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">
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
 table td {
    bgcolor: #E0ECF8;
}
</style>

<body>
    <br>
     <input  type="text" class="form-control input-sm hidden" id="hidden_ok" value="true">
     
     
 <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active text-blue"><a href="#tab_1" data-toggle="tab" aria-expanded="true" >Cartera Clientes</a></li>
              <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="false" >Operadores</a></li>
              <li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="false" onclick="cont_empleado()" >Distribución</a></li>
              <!--li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                  Consultar Cliente <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Consultar Cliente</a></li>
                </ul>
              </li-->
              <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="tab_1">
              <!--b class="margin">Consultar Cliente:</b-->  
                    
    <div class="row">
        
        <div class="col-lg-12">
            
             <div class="col-sm-2">
                 <label>Consultar Cliente:</label>
                 <select class="form-control" name="cartera" required="required" id="cartera" >
                  <c:forEach items="${carteras}" var="carter">
                      <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                  </c:forEach> 
                </select>
             </div>                                             
             <!--div class="col-sm-1">                        
                <button id="btnbuscar" type="submit" class="btn btn-primary btn-sm fa fa-search"> BUSCAR</button>                       
             </div-->
             <div class="col-sm-2"> 
                 <label> Consultar Filtros: </label>
                <a href="#det_filtro" onclick="llenarCombos();"  data-toggle="modal" class="btn btn-primary  btn-sm fa fa-filter">  FILTRAR CARTERAS  </a>
             </div> 
             <div class="col-sm-1" hidden>                        
                <a id="det_empleados" onclick="listarEmpleados();"  class="btn btn-success  btn-sm fa fa-users">  EMPLEADOS  </a>
             </div>
             
             <div class="col-sm-1">  
                <label>Total Vencidos:</label>
                <input  type="text" class="form-control input-sm " id="totalVencidos">  
             </div>
             <div class="col-sm-1"> 
                <label>Total Pagos:</label>
                <input  type="text" class="form-control input-sm " id="totalPagos">
             </div>
             <div class="col-sm-1">                        
                <label>Total Saldos:</label>
                <input  type="text" class="form-control input-sm " id="totalSaldos">
             </div>
             <div class="col-sm-1">  
                 <label>Valor Comp:</label>
                 <input  type="text" class="form-control input-sm " id="totalValorComp">
             </div>
             
        </div>
    </div>
              <br>
                     
<div id="cliente_cartera">    

  <div class="box box-info">
        <input  type="text" class="form-control input-sm " id="secuencia_query" >
      <div hidden>
        <input  type="text" class="form-control input-sm " id="IdDiasMora">
        <input  type="text" class="form-control input-sm " id="IdTotalVenc">
        <input  type="text" class="form-control input-sm " id="IdFechaUlt">
        <input  type="text" class="form-control input-sm " id="IdIdentificacion">
        <input  type="text" class="form-control input-sm " id="IdNombres">
        <input  type="text" class="form-control input-sm " id="IdPagos">
        <input  type="text" class="form-control input-sm " id="IdFecUltPagos">
        <input  type="text" class="form-control input-sm " id="IdSaldo">
        <input  type="text" class="form-control input-sm " id="IdValorComp">
        <input  type="text" class="form-control input-sm " id="IdFechaComp">
        <input  type="text" class="form-control input-sm " id="IdUltima">
        <input  type="text" class="form-control input-sm " id="IdResultado">  
        <input  type="text" class="form-control input-sm " id="input_query" >
        <!--input  type="text" class="form-control input-sm " id="secuencia_query" -->
        <input  type="text" class="form-control input-sm " id="idNotas" value="0" >
      </div> 
      <div class="panel panel-default well-lg margin"  style="overflow-x: auto">
          <div id="tabla_div">
          
            <table id="consul_cartera" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                <thead>
                    <tr bgcolor="#FBF5EF">                                  
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
                    <h4 class="model-title">Detalle Filtro</h4> 
                    
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
                                                                                                                                               
                    </div>
                    <div class="col-lg-12">             
                                    <div class="row">
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
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora" name="idmora" placeholder="Dias" >
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora1" name="idmora1" placeholder="Dias" >
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
                                                                    <div class="" >
                                                                        <dt>Cartera: </dt>
                                                                        <!--select class="form-control  input-sm" onchange=" getipoSubCartera();" name="tcartera" id="tcartera" disabled="true"></select--> 
                                                                        <select class="form-control  input-sm " name="tcartera" id="tcartera" onchange="getipoSubCartera();"  ></select>
                                                                </div> 
                                                                <div class="" >
                                                                        <dt>Sub-cartera: </dt>
                                                                        <select class="form-control  input-sm " name="tsub_cartera" id="tsub_cartera" onchange="getipoSegmento();"></select>
                                                                </div>
                                                                <div class="">
                                                                        <dt>Segmento: </dt>
                                                                       <!--select class="form-control  input-sm" onchange="ConsultaSubsegmento();" name="tsegmento" id="tsegmento" ></select--> 
                                                                       <select class="form-control  input-sm " name="tsegmento" id="tsegmento" onchange="getiposubSegmento();"></select>
                                                                </div> 
                                                                <div class="" >
                                                                        <dt>Sub-Segmento: </dt>
                                                                        <select class="form-control  input-sm " name="tsub_segmento" id="tsub_segmento" ></select>
                                                                </div> 

                                                                <input type="text"  class="form-control input-sm hidden" id="order_by" >


                                                            </div>                                                    


                                                    </div>
                                                    <br>

                                            </div>                  
                                    </div> 
                                    <div id="id_message_carter" class="form-group has-error" style="display: none"> 
                                        <br>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <span class="help-block">Debe seleccionar una cartera</span>
                                    </div>
                            </div>
                        
                        
                 </div>
                </div>
            <div class='modal-footer'>
                <button type="button" id="btnconsultar" onclick="consulta_mis_cartera();" class="btn btn-primary btn-sm fa fa-archive" > CONSULTAR </button>
                <button type="button" onclick="limpiar();" class="btn btn-primary btn-sm fa fa-circle" > Limpiar </button>
                <button type="button"  class="btn btn-primary btn-sm fa fa-close" data-dismiss="modal" > Cerrar </button>
            </div>
        </div>
    </div>
</div>
                            
</div> 

 
                
                </div>
                <div class="tab-pane" id="tab_2" style="overflow-y: auto">
                     

                    <div class="panel panel-default well-lg margin col-lg-8 "  style="overflow-y: auto">
                        
                        <!--div class="col-lg-5" id="selectCartera">
                             <label>Empleados Cartera</label>
                            <select class="form-control" name="carteraEmpleado" required="required" id="carteraEmpleado" onchange="empleados_carteras()" >
                                <option value=0>Todos los Empleados</option>                         
                                <c:forEach items="${carteras}" var="carter">
                                    <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                </c:forEach> 
                           </select>
                        </div--> 
                        <div class="col-sm-1">  
                             <label>All</label>
                             <label><input type="checkbox" id="checkTodos" /></label>
                         </div>
                        <br><br>
                        <form id="formid">
                            <div id="DivTbEmpelados">
                              <table id="tbempleados" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                  <thead>
                                      <tr bgcolor="#FBF5EF">                                  
                                          <th class="col-sm-1 text-left hidden" style="color: #3c8dbc">ID</th>                                                        
                                          <th class="col-sm-2 text-left"><a  >Nombres Empleado</a></th>  
                                          <th class="col-sm-1 text-left"><a >Cartera Asig.</a></th> 
                                          <th class="col-sm-1 text-right"><a >Acción</a></th> 
                                      </tr> 
                                  </thead>
                                  <tbody>

                                  </tbody>
                              </table>
                             </div>
                        </form>
                    </div>
                </div>
                <div class="tab-pane" id="tab_3" style="overflow-x: auto">
                    <div class="row">
                         <div class="col-sm-1">  
                            <label>Total Vencidos:</label>
                            <input  type="text" class="form-control input-sm " id="totalVencidos2">  
                         </div>
                         <div class="col-sm-1"> 
                            <label>Total Pagos:</label>
                            <input  type="text" class="form-control input-sm " id="totalPagos2">
                         </div>
                         <div class="col-sm-1">                        
                            <label>Total Saldos:</label>
                            <input  type="text" class="form-control input-sm " id="totalSaldos2">
                         </div>
                         <div class="col-sm-1">  
                             <label>Valor Comp:</label>
                             <input  type="text" class="form-control input-sm " id="totalValorComp2">
                         </div>
                        <div class="col-sm-1">  
                             <label>Cant. Emp.:</label>
                             <input  type="text" class="form-control input-sm " id="cant_emp">
                         </div>
                        <div class="col-sm-1">  
                             <label>Propor Indiv.:</label>
                             <input  type="text" class="form-control input-sm " id="proporcion_individual">
                         </div>
                         <div class="col-sm-1">  
                             <label>Procesar:</label>
                             <a onclick="procesar_asignacion()"  id="btnprocesar" class="btn btn-primary  btn-sm fa fa-circle">  Click  </a>
                         </div>
                        <div class="col-sm-1">  
                             <label>EmplSecu:</label>
                             <input  type="text" class="form-control input-sm " id="registra_emp_sec">
                         </div>
                        <div class="col-sm-2"> 
                            <center><img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="35" height="35"  style="display: none"></center>
                        </div> 
                    </div>
                    <br>
                      <div class="row">
                          <div class="col-lg-6">
                                <form id="formid2">
                                    <div id="DivTbEmpelados2">
                                      <table id="tbempleados2" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                          <thead>
                                              <tr bgcolor="#FBF5EF">                                  
                                                  <th class="col-sm-2 text-left"><a > Nombres Empleado</a></th>  
                                                  <th class="col-sm-1 text-left"><a > Cantidad Cliente Asig.</a></th> 
                                                  <th class="col-sm-1 text-right"><a> Valor Total Asig.</a></th> 
                                              </tr> 
                                          </thead>
                                          <tbody>

                                          </tbody>
                                      </table>
                                     </div>
                                </form>                              
                          </div>                              
                      </div>
                </div>
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
                
     
               
                <div id="lista_empleados" style="display:none">    
<section class="panel panel-default well-lg margin">
    <div class="row">
        <div class="col-lg-12">
            <div class="col-sm-1">
                <label>Empleados Cartera:</label>
             </div>
             <!--div class="col-sm-1">                        
                <button id="btnbuscar" type="submit" class="btn btn-primary btn-sm fa fa-search"> BUSCAR</button>                       
             </div-->
             <div class="col-sm-2">                        
               <input type="checkbox" name="ListarEmpleados" id="ListarEmpleados" onclick="listarcheck()"><b style="color:#00ca6d">  LISTAR TODOS </b><br>
               
             </div>
             <div class="col-sm-2">                        
                 <input type="checkbox" onclick="EstadoCheck();" name="FiltraEmpleados" id="FiltraEmpleados"><b style="color:#00ca6d">  FILTRAR CARTERAS</b><br>
                 <!--a href="#det_filtro" onclick=""  data-toggle="modal" class="btn btn-danger  btn-sm fa fa-filter">  FILTRAR CARTERAS  </a-->
                 <input style="display:none" name="checkbox_activar" id="checkbox_activar" type="button" value="Activar" />  
               <input style="display:none" name="checkbox_desactivar" id="checkbox_desactivar" type="button" value="Desactivar" />
             </div> 
               
            <div class="col-sm-2" style="display:none" id="btnConsultar">  
                 <a onclick="ConsultaEmpleados()"  data-toggle="modal" class="btn btn-danger  btn-sm fa fa-filter"> CONSULTAR </a>
             </div>
             
        </div>
    </div>
</section>
<div class="box box-info">
     
      <div class="panel panel-default well-lg margin"  style="overflow-x: auto">
          <div id="tabla_div">
          
            <table id="consul_cartera" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="50%">
                <thead>
                    <tr bgcolor="#FBF5EF">                                  
                        <th class="col-sm-1 text-left hidden" style="color: #3c8dbc">ID</th>                                                        
                        <th align="left" class="col-sm-1 text-left"><a id="Nombres" >Nombre Empleado</a></th>
                        <th class="col-sm-2 text-left"><a id="Cartera">Cartera</a></th>  
                        <th class="col-sm-2 text-left"><a id="Accion">Accion</a></th>  
                    </tr> 
                </thead>
                <tbody id="TableEmpleados">
                     
                </tbody>
            </table>
           </div>
      </div> 
  </div>   

                            
</div>                 

<script src="dist/js/asignacioncartera.js"></script> 
<script src="dist/js/cobranzas.js"></script>
<script src="dist/js/jquery.datetimepicker.full.js"></script>
<script src="dist/js/ValidaNumeros.js"></script>
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
$('#saldos').validCampoFranz('0123456789.');
$('#saldos1').validCampoFranz('0123456789.');
$('#dia_mora').validCampoFranz('0123456789');
$('#dia_mora1').validCampoFranz('0123456789');  
$('#tTelefono').validCampoFranz('0123456789');
$('#newTelefono').validCampoFranz('0123456789');
$('#new_telefono_ref').validCampoFranz('0123456789'); 
$('#proporcion_individual').validCampoFranz('0123456789.'); 
$('#totalSaldos2').validCampoFranz('0123456789.'); 
$('#cant_emp').validCampoFranz('0123456789');
$('#nombre_ref').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ ');

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
        all_empleados();
</script>
<script> 
$('document').ready(function(){
   $("#checkTodos").change(function () {
      $("input:checkbox").prop('checked', $(this).prop("checked"));
       
     
  });
 
});
function cont_empleado(){
     var cont=0;
     var insert="";
        $('#formid input[type=checkbox]').each(function(){
            if (this.checked) {
               cont++;
                console.log("cont: "+$(this).val());
                insert+="INSERT INTO public.lc_empleados_selec(secuencial, id_empleado, despachado)VALUES ("+document.getElementById("secuencia_query").value+","+$(this).val()+",''P'');";
                console.log("insert: "+insert);
            }
        });
        
        document.getElementById("registra_emp_sec").value=insert;
        document.getElementById("cant_emp").value=cont;
        //document.getElementById("proporcion_individual").value=cont;
        
        var ValorTotal = document.getElementById("totalSaldos2").value;
        var Valor=0;
        console.log("ValorTotal: "+ValorTotal);
        console.log("Valor: "+Valor);
        if (ValorTotal!==""){
            Valor= parseFloat(ValorTotal); 
           if (Valor>0 &&  cont>0  ){
               var monto=parseFloat(Valor) / parseInt(cont);
             document.getElementById("proporcion_individual").value=monto.toFixed(2);
            }
        }
        
}
function procesar_asignacion(){
    var registraemp=$("#registra_emp_sec").val();
    var accion="procesar_asignacion";
    var secuencia = $("#secuencia_query").val();
    var proporcionindv = parseFloat($("#proporcion_individual").val());    
    var cartera = $("#cartera").val();
     $('#id_loader').css("display", "block");
    console.log("procesar_asignacion: "+cartera);
    console.log("registraemp "+registraemp);
    document.getElementById("btnprocesar").disabled=true;

        var parametros = {
                "registraemp": registraemp,
                "proporcionindv":proporcionindv.toFixed(2),
                "secuencia": secuencia,
                "cartera": cartera,
                "accion": accion
            };
                $.ajax({
                data: parametros,
                url: 'asignacioncartera',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                     $('#id_loader').css("display", "none");
                         document.getElementById("btnprocesar").disabled=false;
                     if (response) {
                    console.log("response: "+response);
                    MsgSalidaModalA(response);
                    datos_asignados(secuencia,cartera);
                    }      
                }
            });
    
    
    
}



function datos_asignados(secuencia,cartera){
    var accion="consulta_empleados_asigados";
    //"[{"id_empleado":28,"totales":13735.05,"cantidad":42,"empleado":"Paola Villota"}, 


      $('#tbempleados2').DataTable( {
        "ajax": {
            "data": {"accion": accion,"secuencia": secuencia},
            "url": "asignacioncartera",
            "type": "GET"
            },
            "columns": [
                { "data": "empleado",},
                { "data": "cantidad" },
                { "data": "totales" }
            ],
            paging: false
    } );
}
</script> 
</body>
</html>

