<%-- 
    Document   : frm_gestion_cobranzas.jsp
    Created on : 12/04/2017, 11:51:39
    Author     : Stalyn Granda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            textarea {
	resize: none;
                }            
        </style>
        <!--link href="dist/css/bootstrap-datepicker.css" rel="stylesheet" /--> 
        <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">
             <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    </head>
    <body>
        <style>
input:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
select:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
</style>


<div>
    <div>
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
                            
                                    <a href="#referencia" onclick="listo();" id="listoModal" data-toggle="modal" class="btn btn-success btn-sm">Referencia</a>
                                    <!--<a href="#agrega_datos"  data-toggle="modal" class="btn btn-primary">Agregar Datos</a>-->
                                    <a onclick="AddDirModal();" class="btn btn-success btn-sm">Agregar Dirección</a>
                                    <a onclick="AddTelModal();" class="btn btn-success btn-sm">Agregar Teléfono</a>
                                    
                                    <a href="#det_articulo" onclick="ComprasJson();" data-toggle="modal" class="btn btn-success btn-sm">Detalles Artículo</a>
                                    <a href="#det_cuotas"  data-toggle="modal" class="btn btn-success btn-sm">Detalles Cuotas</a>
                                    <a href="#historial_pagos"  data-toggle="modal" onclick="consulta_historial();" class="btn btn-success btn-sm">Historial Pagos</a>
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


<!--script src="bootstrap/js/bootstrap-datetimepicker.min.js"></script
<script src="bootstrap/js/bootstrap-datepicker.js"></script>-->
<script src="dist/js/jquery.datetimepicker.full.js"></script>
<script src="dist/js/consultaxcatera.js"></script>  
<script src="dist/js/cobranzas.js"></script>
<script src="dist/js/validaciones.js"></script>
        

        <script>
            $(function () {

                $('#detalle_articulos').DataTable({
                    "paging": false,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": true,
                    "info": true,
                    "autoWidth": false
                });
                $('#detalle_cuotas').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": true,
                    "info": false,
                    "autoWidth": false
                });
               /* $('#datepicker').datepicker({
                    weekStart: 1,
                    color: 'red'
                });
                $('#datepicker2').datepicker({
                    weekStart: 1,
                    color: 'red'
                });*/
                $('#datepicker').datetimepicker({   format:'Y-m-d' }); 
                $('#datepicker2').datetimepicker({   format:'Y-m-d' }); 
                
                
                $('#idAllTelefonos').DataTable( {
                    scrollY:        100,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                     info: false,
                    searching: false,
                    
                    columnDefs: [ {
                        orderable: false,

                        targets:   2
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 2, 'asc' ]]
                } );  
                 $('#idAllDireccions').DataTable( {
                    scrollY:        100,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                     info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: false,

                        targets:   1
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 1, 'asc' ]]
                } );
                
                
                  $('#allTrxGestiones').DataTable( {
                    scrollY:        300,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                    info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: true,

                        targets:   4
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 4, 'desc' ]]
                } );
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
        
            <script src="dist/js/ValidaNumeros.js"></script>
            
          <script>
                $(function(){
                   $('#tTelefono').validCampoFranz('0123456789');
                   $('#newTelefono').validCampoFranz('0123456789');
                   $('#new_telefono_ref').validCampoFranz('0123456789');                   
                });                
            </script> 
            
            <script>
                $(function(){
                   $('#nombre_ref').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ '); 
                                    
                });                
            </script> 
    </body>
</html>

