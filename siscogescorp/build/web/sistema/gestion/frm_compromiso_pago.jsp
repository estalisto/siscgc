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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">  
<!--link rel="stylesheet" href="dist/css/jquery.datetimepicker.css"-->   
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
  </head>
    <body>
        <ol class="Breadcrumb default">
                    <div class="btn-group btn-breadcrumb">
                        <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>
                        <a href="#" class="active btn btn-default" onclick="compropago();">COMPROMISOS DE PAGO</a>
                        <a href="#" class="btn btn-default" onclick="consulcarteras();">CONSULTA CARTERA</a>
                    </div>                    
                </ol>
  

  <div> <!-- div inicio  -->
      
    <div class="col-lg-12" id="cliente_cartera"> 
        <br>
        <div class="box box-default">
            
      <div class="panel panel-default well-lg margin"> 
           <form class="form-inline">            
            <div class="form-group has-feedback">
                <label class="control-label"> Fecha Desde:</label>
                <input type="text" class="form-control" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" id="datepicker"/>
                <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
            </div> 
               <div class="form-group has-feedback"></div> 
            <div class="form-group has-feedback">
                <label class="control-label">Fecha Hasta: </label>
                <input type="text" class="form-control" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" id="datepicker2"/>
                <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
            </div> 
               <div class="form-group has-feedback"></div> 
               <button type="button" class="btn btn-primary " id="buscarCompromisos">
                 <span class="glyphicon glyphicon-search"></span> <strong> Consultar </strong>
              </button>
                <div id="example_wrapper" class="form-group has-feedback"></div> 
          </form>
          <br>
          
            <div class="row">
                <div  class="box">
                     <div  class="box-body table-responsive" style="overflow-x: auto" >
                        <div id="busca_compromisos"> 
                          <table  id="compro_pago" class="table table-striped table-bordered table-hover " cellspacing="0" width="100%">                            
                            <thead>  
                            <tr bgcolor='#A0F070'>
                              <th class="col-sm-1 text-center" >N°</th>
                              <th class="col-sm-1  text-center">Fecha llamada</th>
                              <th class="col-sm-3">Deudor</th>
                              <th class="col-sm-1 text-center">Fech. Compromiso</th>
                              <th class="col-sm-1">Monto Compromiso</th>
                              <th class="col-sm-3">Observación</th>
                            </tr>
                             </thead>         
                            
                                        <%= request.getAttribute("Tablacompromiso") %>

                            
                          </table>
                        </div>
                     </div>
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
                                                     
                                    <div class="col-xs-3 " style="display:none" >
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
                                            <input size="15" type="text" class="form-control input-sm" id="datepicker3" onkeyup="verificaFecha();" name="recordatorio" placeholder="YYYY-MM-DD">
                                        </div> 
                                            <input size="4" type="text"  class="form-control input-sm" id="hora" onkeyup="validaHora();" name="hora"  placeholder="23:59"  >
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
                                    <select class="input-sm form-control" name="gestion" id="gestion" required="required" onchange="obtenerResultadoCompromisos()">
                                      
                                        
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
                                    <select class="input-sm form-control" name="resultado2" onclick="selecciona_resultado();" required="required" id="resultado2">
                                      

                                    </select>                                 
                                </div>

                                <div class="col-lg-4" >
                                    <dt> Fecha Compromiso de Pago y Monto:</dt> 
                                        <div class="form-inline ">
                                                <input size="15" type="text" class="input-sm datepicker" onkeyup="verificaFecha2();" data-date-format="yyyy-mm-dd" id="datepicker4" name="compromiso_pago" placeholder="YYYY-MM-DD" disabled="true">                                                        
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
  </div>  <!-- fin de div de inicio  --> 
       
  
  
  <!-- /.content-wrapper 
<script src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="dist/js/jquery.datetimepicker.full.js"></script>-->
  <script src="dist/js/jquery.datetimepicker.full.js"></script>
<script src="dist/js/compromiso_pagos.js"></script> 
<script src="dist/js/cobranzas.js"></script>



<!-- DataTables
<script src="plugins/datatables/jquery.dataTables.min.js"></script> 
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
--> 
<script>    
$(function() {
 $("#compro_pago").DataTable({
    "paging": true,
    "lengthChange": false,
    "lengthMenu": [10],
    "ordering": false,
    "info": false,
    "autoWidth": false
  });
   
 


    
});


</script> 
<script>
$('#datepicker4').datetimepicker({   format:'Y-m-d' }); 
$('#datepicker3').datetimepicker({   format:'Y-m-d' }); 
//$('#datepicker3').datetimepicker({   format:'Y-m-d H:i:s' }); 
</script> 
<!-- ./wrapper -->
</body>
</html>


