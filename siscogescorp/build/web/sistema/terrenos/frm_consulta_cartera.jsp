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
   <ol class="breadcrumb">
                    <li><a href="#" onclick="" > Consulta Cartera</a></li>

   </ol>
     <input  type="text" class="form-control input-sm hidden" id="hidden_ok" value="true">
     
<div id="cliente_cartera">    
<section class="panel panel-default well-lg margin">
    <div class="row">
        <div class="col-lg-12">
            <div class="col-sm-1">
                <label>Cliente Cartera:</label>
             </div>
             <div class="col-sm-2">
                 <select class="form-control" name="cartera" required="required" id="cartera" onchange="SeleccionaCliente();"  >
                </select>
             </div>                                             
           
             <div class="col-sm-1">                        
                <a href="#det_filtro" onclick="getMisTiposGestiones();"  data-toggle="modal" class="btn btn-primary  btn-sm fa fa-filter">  BUSCAR CLIENTES</a>
             </div>
            <div class="col-sm-1">  
                 <a id="enviar" class="btn btn-success  btn-sm fa fa-print">  IMPRIMIR</a>
                
             </div>
            <div class="col-sm-1">  
               
                 <label><input type="checkbox" id="checkTodos" />Todos</label>
             </div>
             <div class="col-sm-1">
                 <input type="text" class="form-control" id="insert_deudores">
             </div>
             <div class="col-sm-1">  
                 <input type="text" class="form-control" id="ticket" value="400">
             </div>
            
            
             
             <div class="col-sm-1">                        
             </div>
             <div class="col-sm-2"> 
                 <center><img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="35" height="35"  style="display: none"></center>
             </div> 
        </div>
    </div>
</section>
  <div class="box box-info">
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
      <form id="formid">
            <div class="panel panel-default well-lg margin"  style="overflow-x: auto">
                <div id="tabla_div">

                  <table name="consul_cartera" id="consul_cartera" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                      <thead>
                          <tr bgcolor="#FBF5EF">                                  
                              <th class="col-sm-1 text-left hidden" style="color: #3c8dbc">ID</th>                                                        
                              <th align="left" class="col-sm-1 text-left"><a id="IdentificacionID" onclick="">Identificación</a></th>
                              <th class="col-sm-2 text-left"><a id="NombresID" onclick="">Nombres</a></th>  
                              <th class="col-sm-1 text-left"><a id="DiasMoraID" onclick="" >Días Mora</a></th> 
                              <th class="col-sm-1 text-right"><a id="TotalID" onclick="" >Total Vnc</a></th> 
                              <th align="center" class="col-sm-1 text-right"><a id="PagosID" onclick="">Pagos</a></th>
                              <th align="center" class="col-sm-1 text-right"><a id="FecUltPagosID" onclick="">Fecha Ult. Pagos</a></th>
                              <th align="rigth" class="col-sm-1 text-right"><a id="SaldosID" onclick="">Saldo</a></th> 
                              <th align="center" class="col-sm-1 text-right"><a id="ValorCompID" onclick="">Valor Comp.</a></th> 
                              <th align="center" class="col-sm-2 text-center"><a id="FechaCompID" onclick="">Fecha Comp.</a></th>
                              <th align="center" class="col-sm-3"><a id="FechaID" onclick="" >Fecha Ult. Gestión</a></th> 
                              <th align="center" class="col-sm-3"><a id="UltimaID" onclick="">Ult. Gestión</a></th> 
                              <th align="center" class="col-sm-2"><a id="ResultadoID" onclick="">Resultado Gestión</a></th>
                          </tr> 
                      </thead>
                      <tbody id="bodytable">

                      </tbody>
                  </table>
                 </div>
            </div> 
      </form>
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
                                                                            <select class="form-control  input-sm" onchange="ConsultaMisTipoResultado();" name="tgestion" id="tgestion" ></select> 
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
                                                                    <div class="form-inline">
                                                                               <label></label><br><br><br>

                                                                    </div> 
                                                                <input type="text"  class="form-control input-sm hidden" id="order_by" >


                                                            </div>                                                    


                                                    </div>
                                                    <br>

                                            </div>                  
                                    </div>           
                            </div>
                 </div>
                </div>
            <div class='modal-footer'>
                <button type="button" id="btnconsultar" onclick="consulta_filtros_carteras();" class="btn btn-primary btn-sm fa fa-archive"> CONSULTAR </button>
                <button type="button" class="btn btn-primary btn-sm fa fa-close" data-dismiss="modal"> CERRAR </button>
            </div>
        </div>
    </div>
</div>
                            
</div> 
<div id="gestion_cliente" hidden>
                <div class="panel panel-default well margin">
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
                                <button id="anterior" type="button" onclick="Antdeudor2()" class="btn btn-primary btn-lg glyphicon glyphicon-arrow-left"></button>
                                <button id="siguiente" type="button" onclick="Sgtedeudor2()" class="btn btn-primary btn-lg glyphicon glyphicon-arrow-right"> </button>
                            </div>  
                            <div class="col-lg-1">
                            <center><img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                            </div>  
                        </div>
                          
                        <div class="row">                           
                          
                            <form class="form-horizontal">
                               
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Total Deuda: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D" id="labelTotalDeuda"><u>$ 00</u></label>
                                    <label class="col-sm-2 control-label">Total Vencido: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D" id="labelTotalVencido"><u>$ 0</u></label>
                                    <label class="col-sm-1 control-label">Pagos: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D" id="labelPagos"><u>$ 00</u></label>
                                    <label class="col-sm-1 control-label">Saldo: </label>    
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D" id="labelSaldos"><u>$ 00</u>
                                    </label><label class="col-sm-1 control-label">Días Mora: </label>  
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D" id="labelDiasMora"><u>00 Dias</u></label>
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
                            
                                    <a href="#referencia" onclick="listo();" id="listoModal" data-toggle="modal" class="btn btn-primary">Referencia</a>
                                    <!--<a href="#agrega_datos"  data-toggle="modal" class="btn btn-primary">Agregar Datos</a>-->
                                    <a onclick="AddDirModal();" class="btn btn-primary">Agregar Dirección</a>
                                    <a onclick="AddTelModal();" class="btn btn-primary">Agregar Teléfono</a>
                                    
                                    <a href="#det_articulo" onclick="ComprasJson();" data-toggle="modal" class="btn btn-primary">Detalles Artículo</a>
                                    <a href="#det_cuotas"  data-toggle="modal" class="btn btn-primary">Detalles Cuotas</a>
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
                                    <div class="col-lg-5">                                 
                                    <dt>Ciudad: </dt>
                                    <select class="input-sm form-control" id="Ciudad" name="Ciudad" required="required" disabled="true">
                                        
                                    </select>
                                    </div>
                                            
                                    <div class="col-lg-7">
                                        <dt>Volver a Llamar: </dt>     
                                    <form class="form-inline">
                                        <div class="input-group date">
                                            <input size="8" type="text" class="form-control input-sm datepicker" data-date-format="yyyy-mm-dd" id="datepicker2" onkeyup="verificaFecha();" name="recordatorio" placeholder="YYYY-MM-DD" disabled="true">
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
						<textarea maxlength="500" id="txtnotaAdmin" class="form-control input-sm " rows="1" onkeyup="ValidarNota2()"  placeholder="NOTAS ADMINISTRADOR" style="overflow-y:scroll; background-color:#FDF9DB;  font-size:14px; font-type:Arial" value="0" readonly></textarea>
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
</div>


<!--<script src="dist/js/consultaxcatera.js"></script> 
script src="dist/js/cobranzas.js"></script-->
<script src="dist/js/jquery.datetimepicker.full.js"></script>
<script src="dist/js/ValidaNumeros.js"></script>
<script src="dist/js/documento.js"></script>
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
$('#nombre_ref').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ ');
ConsultaClientes();
ConsultaTicket();
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
$('document').ready(function(){
   $("#checkTodos").change(function () {
      $("input:checkbox").prop('checked', $(this).prop("checked"));
  });
});
</script> 
<script type="text/javascript">
$(document).ready(function() {
    $('#enviar').click(function(){
        var selected = '';    
        document.getElementById("insert_deudores").value="";
        
        var ticket=document.getElementById("ticket").value;
          console.log("ticket: >>>"+ticket);
        var idCliente=$("#cartera").val();
        var cont=0;
        $('#formid input[type=checkbox]').each(function(){
            if (this.checked) {
               //selected += $(this).val()+', ';
                document.getElementById("insert_deudores").value+="insert into lc_deudores_documentos(id_ticket, id_deudores) values ("+ticket+","+$(this).val()+");";
                selected+="insert into lc_deudores_documentos(id_ticket, id_deudores) values ("+ticket+","+$(this).val()+");";
                console.log(selected);
            }
        }); 

        if (selected != ''){ 
            console.log('Has seleccionado: '+selected+' cliente: '+idCliente); 
            //registra_trama(selected,ticket);
            registra_trama(selected,ticket,idCliente,1);
            //genera_reporte(ticket,idCliente,1);
        }else{
            MsgSalidaModalA('Debes seleccionar al menos una un cliente para Generar Documento.');
        }

        return false;
    });  
    
 
});    
</script>

</body>
</html>

