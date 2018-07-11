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
  </head>
    <body>
        <ol class="Breadcrumb default">
                    <div class="btn-group btn-breadcrumb">
                        <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>
                        <a href="#" class="active btn btn-default" onclick="compropago();">COMPROMISOS DE PAGO</a>
                        <a href="#" class="btn btn-default" onclick="consulcarteras();">CARTERA ASIGNADA</a>
                    </div>                    
                </ol>
  

  <div> <!-- div inicio  -->
     <div id="cliente_cartera2" class="col-lg-3 box box-default">
        <form class="form">            
                    <div class="form-group has-feedback">
                        <label class="control-label"> Desde:</label>
                        <input type="text" class="form-control input-sm" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" id="datepicker"/>
                        <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
                    </div> 
                       <div class="form-group "></div> 
                    <div class="form-group has-feedback ">
                        <label class="control-label">Hasta: </label>
                        <input type="text" class="form-control input-sm" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" id="datepicker2"/>
                        <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
                    </div> 
                       <div class="form-group has-feedback"></div> 
                       <button type="button" class="btn btn-success btn-sm " id="buscarCompromisos">
                         <span class="glyphicon glyphicon-cog"></span> <strong> Consultar </strong>
                      </button>
                        <div id="example_wrapper" class="form-group"></div> 
                  </form>
    </div> 
    <div class="col-lg-9" id="cliente_cartera"> 

        <div >
            
      <div class="margin">           
            <div class="row">
                <div  class="box">
                     <div  class="box-body table-responsive" style="overflow-x: auto" >
                        <div id="busca_compromisos"> 
                          <table  id="compro_pago" class="table table-striped table-bordered table-hover " cellspacing="0" width="100%">                            
                            <thead>  
                            <tr bgcolor='#FEC187'>
                              <th class="col-sm-1 text-center" >N°</th>
                              <th class="col-sm-1  text-center">Fecha llamada</th>
                              <th class="col-sm-3">Acreedor</th>
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
                     
                    <div class="row well-sm">
                        <div class="col-lg-4 ">
                            <h3> <span class="small" style="color: #F66C27">CLIENTE:</span> <strong><label  id="deudor" >NOMBRE COMPLETO DEL DEUDOR  </label></strong>
                            <br><span class="small" style="color: #F66C27">CED./RUC:</span> <strong><label class="lead text-left text-blue"  id="identificacion">0922343456001 </label></strong></h3> 
                           
                            
                        </div>
                        <div class="col-lg-2">
                            <h4> <span class="small" style="color: #F66C27">CEDENTE:</span> <strong><label class="lead text-left text-blue"  id="cliente">RAZON SOCIAL DEL CEDENTE  </label></strong></h4> 
                            
                            
                        </div>
                        <div class="col-lg-1 text-right">
                            <img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle text-center " alt="User Image" width="20" style="display: none">
                        </div>
                        <div class="col-lg-5 text-right">                            
                            <ol class="Breadcrumb default">
                                <div class="btn-group btn-breadcrumb">
                                    <!-- style='color:#F98021;' -->
                                    <a style='background-color:#F98021;' href="#referencia" onclick="listo();" id="listoModal" data-toggle="modal" class="active btn btn-warning btn-lg" >Referencia</a>
                                    <a style='background-color:#F98021;' href="#det_articulo" onclick="ComprasJson();" data-toggle="modal" class="active btn btn-warning btn-lg " >Detalles Artículo</a>
                                    <a style='background-color:#F98021;' href="#det_cuotas"  data-toggle="modal" class="active btn btn-warning btn-lg" >Detalles Cuotas</a>
                                    <a style='background-color:#F98021;' href="#historial_pagos"  data-toggle="modal" onclick="consulta_historial();" class="active btn btn-warning btn-lg " >Historial Pagos</a>
                                    
                                </div>                    
                            </ol> 
                                <ol>
                                    <div class="btn-group btn-breadcrumb" hidden>
                                        <a style='background-color:#F98021;' id="anterior"  onclick="Antdeudor2()" class="btn btn-success btn-lg"><i class="fa fa-arrow-circle-left text-black"></i>  </a>
                                         <a style='background-color:#F98021;' id="siguiente"  onclick="Sgtedeudor2()" class="btn btn-success btn-lg ">  <i class="fa fa-arrow-circle-right text-black"></i> </a>                        
                                    </div>
                                </ol>
                        </div>                        
                    </div>
                    <div class="row well-sm">
                        <div class="col-lg-1 text-left"><p class="col-sm-6 " style="color: #F66C27">DEUDA:</p></div>
                        <div class="col-lg-1 text-left"><p class="col-sm-2 text-bold lead" style="color: #030303" id="labelTotalDeuda"><strong>$ 00</strong></p></div>
                        <div class="col-lg-1 text-right"><p class="col-sm-6 "style="color: #F66C27">VENCIDO: </p></div>
                        <div class="col-lg-1 text-left"><p class="col-sm-2 text-bold lead" style="color: #030303" id="labelTotalVencido"><strong>$ 0</strong></p></div>
                        <div class="col-lg-1 text-right"><p class="col-sm-6 " style="color: #F66C27">PAGOS: </p></div>
                        <div class="col-lg-1 text-left"><p class="col-sm-2 text-bold lead" style="color: #030303" id="labelPagos"><strong>$ 00</u></strong></div>
                        <div class="col-lg-1 text-right"><p class="col-sm-6 " style="color: #F66C27">SALDO: </p></div>
                        <div class="col-lg-1 text-left"><p class="col-sm-2 text-bold lead" style="color: #030303" id="labelSaldos"><strong>$ 00</strong></p></div>
                        <div class="col-lg-2 text-right"><p  class="col-sm-6" style="color: #F66C27">DÍAS MORA: </p></div>
                        <div class="col-lg-2 text-left"><p class="col-sm-1 text-bold lead" style="color: #030303" id="labelDiasMora"><strong>00 DÍAS</strong></p></div>
                     </div>
                    <div class="row well-sm">
                        <div class="col-lg-5">
                            <div id="pagedireccion">  
                            <div>
                                <div id="table_direccion" class="form-group">                                
                                    <dt><a href="#" class="text-aqua"  onclick="AddDirModal();"> Agregar <i class="fa fa-plus"></i></a> </dt>
                                    <div id="TablaDirecciones" class="box table-responsive" >
                                        <table id="idAllDireccions" class=" table-striped table-bordered dt-responsive table-condensed nowrap table-hover" cellspacing="0" width="100%">                                           
                                            <thead>
                                                <tr  bgcolor="#B5EE8E" width="100%">
                                                    <th class="col-sm-2">Tipo</th>
                                                    <th class="col-sm-6">Direcciones</th>
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
                            <div >        
                                <dt><a href="#" class="text-aqua " onclick="AddTelModal();"> Agregar <i class="fa fa-plus"></i> </a></dt>
                                <div id="table_telefono" class="box table-responsive">  
                                    <table id="idAllTelefonos" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="100%">
                                        <thead>
                                            <tr  bgcolor="#B5EE8E">
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
                        </div>
                        <div class="col-lg-7">
                            <div class="row">
                                <div class="col-lg-6">                             
                                        <dt>Localidad: </dt>
                                        <select class="input-sm form-control" id="Ciudad" name="Ciudad" required="required" disabled="true"></select>
                                  
                                        <dt>Recordatorio: </dt>
                                        <div class="row">
                                            <div class="col-lg-7">
                                                <input type="text" class="form-control input-sm datepicker" data-date-format="yyyy-mm-dd hh:mi:ss" id="datepickerRecordatorio" onkeyup="verificaFecha();" name="datepickerRecordatorio" placeholder="YYYY-MM-DD" disabled="true">
                                            </div>
                                            <div class="col-lg-5">
                                                <input type="text" class="form-control input-sm" id="hora" name="hora" placeholder="23:59" onkeyup="validaHora();" disabled="true">
                                            </div>
                                        </div>

                                            <dt> Fecha Compromiso y Monto:</dt> 
                                        <div class="row">
                                            <div class="col-lg-7">
                                                <input  type="text" class="form-control input-sm datepicker" onkeyup="verificaFecha2();" id="datepickerCompromiso" name="datepickerCompromiso" placeholder="YYYY-MM-DD" disabled="true"/>
                                            </div>
                                            <div class="col-lg-5">
                                                <input type="text" class="form-control input-sm" id="monto_compromiso" name="monto_compro" onkeypress="ValidaSoloNumeros()" placeholder="$ 0.00" disabled="true"/>
                                            </div>
                                        </div>                                            
                                        <span id="fechaCompro" ></span> 
                                        <!--  -->
                                        <dt>Gestión: </dt>
                                            <select class="input-sm form-control" name="gestion" id="gestion" required="required" onchange="obtenerResultadoCompromisos()"></select> 		         
                                        <dt>Respuesta: </dt>
                                        <select class="input-sm form-control" name="resultado2" required="required" id="resultado2"></select>                                 
                                        <div class="col-xs-3 hidden">
                                                <div class="form-group">
                                                    <label>Tipo Resultado escogido</label>
                                                    <input type="text" class="form-control" id="tiporesultado" name="tiporesultado" value="" required="required">
                                                </div> 
                                            </div>


                                    </div>

                                    <div class="col-lg-6">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <dt>Mensaje:</dt>                                
                                            </div>
                                            <div class="col-sm-1">
                                                <dt style="color:#F66C27" id="contador_notas">400</dt>
                                            </div>                            
                                        </div>
                                        <textarea maxlength="400" id="txtnota" class="form-control input-sm " rows="4" onkeyup="ValidarNota2()"  placeholder="MENSAJE" style="overflow-y:scroll; background-color:#FBE3BF;  font-size:14px; font-type:Arial" value="0"></textarea>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <dt>Notas Admin:</dt>                                
                                            </div>
                                                                       
                                        </div>
                                        <textarea maxlength="400" id="txtnota_admin" class="form-control input-sm " rows="3" onkeyup="ValidarNota2()"  placeholder="MENSAJE" style="overflow-y:scroll; background-color:#FBE3BF;  font-size:14px; font-type:Arial" value="0"></textarea>

                                             
                                        
                                    </div>
                                
                            </div>
                            <div class="row">
                                <div class="col-sm-9">
                                    <dt>Explicación</dt>                                
                                </div>
                                <div class="col-sm-3">
                                    <dt style="color:#F66C27" id="contador_descripcion">500</dt>
                                </div>

                            </div>
                            <div class="row"> 
                                    <div class="col-sm-10">
                                          <textarea maxlength="500" class="form-control input-sm" id="descripcion" name="descripcion" rows="4" placeholder="Historia" style="overflow-y:scroll; background-color:#FBE3BF; font-size:18px; font-type:Arial" ></textarea> 
                                    </div>
                                    <div class="col-sm-2">
                                        <br><a   onclick="GuardarTransaccnormalCompromisos()" class="btn btn-info btn-lg"><i class="fa fa-edit text-black"></i> GUARDAR</a>
                                    </div>
                            </div>     
                        </div>
                        
                    </div>
                    
                    
                        <div class="row" hidden="true">
                            <div class="col-lg-3">
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

                            <div class="col-lg-4">
                                <strong>  <center><h2 id="deudor">Nombre</h2></center></strong>
                                <center><h4 id="cliente" style="color: #0063dc"><b>cliente</b></h4></center>   
                            </div>

                            <div class="col-lg-2"> 
                                <button id="anterior" type="button" onclick="Antdeudor2()" class="btn btn-success btn-sm glyphicon glyphicon-arrow-left"></button>
                                <button id="siguiente" type="button" onclick="Sgtedeudor2()" class="btn btn-success btn-sm glyphicon glyphicon-arrow-right"> </button>
                                <center><img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="20" style="display: none"></center>
                            </div>  
                            <div class="col-lg-1" >
                            <center><img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                            </div>  
                        </div>
                          
                        <div class="row" hidden="true">                           
                          
                            <form class="form-horizontal">
                               
                                <div class="form-group well-sm">
                                    <label  class="col-sm-1 control-label" style="color: #F66C27">     Total Deuda: </label> 
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
                                   
                       
                              
                                
                        
                              
                            </form>
                            <section class="content ">
                                <div id="pagetable">  
                                    <div id="transaccion_table"  class="box table-responsive"  >
                                       
                                            <table id="allTrxGestiones" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr  bgcolor="#B5EE8E">
                                                        
                                                        <th class="col-lg-1">Gestión</th>
                                                        <th class="col-lg-1">Respuesta</th>
                                                        <th class="col-lg-5">Historia</th>
                                                        <th class="col-lg-3">Asesor</th>
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
                        
                </div>               <br>
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
$('#datepickerRecordatorio').datetimepicker({   format:'Y-m-d' });
$('#datepickerCompromiso').datetimepicker({   format:'Y-m-d' });
</script> 
<!-- ./wrapper -->
</body>
</html>


