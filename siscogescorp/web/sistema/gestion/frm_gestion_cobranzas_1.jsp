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


                <div class="panel panel-default well margin">

                    <c:forEach items="${cobranzas}" var="cobro">
                        <div class="row">
                            <div class="col-lg-4">
                                <form class="form-horizontal">
                                    <div class="col-xs-3 hidden">
                                        <div class="form-group">
                                            <label>Código:</label>
                                            <input type="text" class="form-control" id="id" name="id" value="<c:out value="${cobro.getIdDatosDeudor()}"/>" required="required">
                                        </div> 
                                    </div>
                                                <c:forEach items="${transaccion}" var="transa">       
                                    <div class="col-xs-3 hidden">
                                        <div class="form-group">
                                            <label>Cliente:</label>
                                            <input type="text" class="form-control" id="idcliente" name="idcliente" value="<c:out value="${transa.getLcClientes().getIdCliente()}"/>" required="required">
                                            
                                        </div> 
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">C.I/RUC:</label>   
                                        <input class="col-sm-6 input-sm" type="text" placeholder="Número de identificación" required="required" name="identificacion" value="<c:out value='${cobro.getIdentificacion()}'/>" id="identificacion" readonly>                                    
                                    </div>
                           
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Cuenta:</label>   
                                        <input class="col-sm-6 input-sm" type="text" placeholder="Número de cuenta" required="required" name="cuenta" id="cuenta" value="<c:out value='${transa.getNumCuenta()}'/>" readonly>                   
                                    </div>
                                </form>
                            </div>

                            <div class="col-lg-5">
                                <strong>  <center><h2 id="deudor"> <c:out value='${cobro.getNombresCompleto()}'/></h2></center></strong>
                                <center><h4 id="cliente" style="color: #0063dc"><b><c:out value='${transa.getLcClientes().getRazonSocial()}'/></b></h4></center>   
                            </div>

                            <div class="col-lg-2"> 
                                <button id="anterior" type="button" onclick="Antdeudor(${cobro.getIdDatosDeudor()},${transa.getLcClientes().getIdCliente()})" class="btn btn-primary btn-lg glyphicon glyphicon-arrow-left"></button>
                                <button id="siguiente" type="button" onclick="Sgtedeudor(${cobro.getIdDatosDeudor()},${transa.getLcClientes().getIdCliente()})" class="btn btn-primary btn-lg glyphicon glyphicon-arrow-right"> </button>
                            </div>  
                            <div class="col-lg-1">
                            <center><img id="img_cargando" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                            </div>  
                        </div>
                          
                        <div class="row">                           
                          
                            <form class="form-horizontal">
                               
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Total Deuda: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u>$ <c:out value='${transa.getMontoAsignado()}'/></u></label>
                                    <label class="col-sm-2 control-label">Total Vencido: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u>$ <c:out value='${transa.getTotalVencidos()}'/></u></label>
                                    <label class="col-sm-1 control-label">Pagos: </label> 
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u>$ <%= request.getAttribute("valorPagado2") %></u></label>
                                    <label class="col-sm-1 control-label">Saldo: </label>    
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u>$ <%= request.getAttribute("ValorSaldo2") %></u>
                                    </label><label class="col-sm-1 control-label">Días Mora: </label>  
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u><c:out value='${transa.getDiasMora()} '/> Dias</u></label>
                                    

                                   <!-- <label class="col-sm-1 control-label">Último Pago:</label>
                                    <label class="col-sm-1 control-label" style="color: #FF3A2D"><u>$ <c:out value='${transa.getUltimoPago()}'/></u></label>-->
                                    
                                    
                                    
 
                                </div>    
                            </form>  
                        </div>  
                            <div class="col-xs-3 hidden">
                                        <div class="form-group">
                                            <label>Código:</label>
                                            <input type="text" class="form-control" id="idTransaccion" name="idTransaccion" value="<c:out value="${transa.getIdTransaccion()}"/>" required="required">
                                        </div> 
                            </div>          
                                    
                        <div class="btn-toolbar" role="toolbar">                    
                            
                                    <a href="#referencia" onclick="listo();" id="listoModal" data-toggle="modal" class="btn btn-primary">Referencia</a>
                                    <!--<a href="#agrega_datos"  data-toggle="modal" class="btn btn-primary">Agregar Datos</a>-->
                                    <a onclick="AddDirModal();" class="btn btn-primary">Agregar Dirección</a>
                                    <a onclick="AddTelModal();" class="btn btn-primary">Agregar Teléfono</a>
                                    
                                    <a href="#det_articulo"  data-toggle="modal" class="btn btn-primary">Detalles Artículo</a>
                                    <a href="#det_cuotas"  data-toggle="modal" class="btn btn-primary">Detalles Cuotas</a>
                                    <div id="modalADDTel"></div>
                                     <div id="modalDireccion"></div>
                                    <div id="agrega_datos" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                                        <div class="modal-dialog" role="document">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                              <h4 class="modal-title" id="exampleModalLabel">Agregar Datos:  <c:out value='${cobro.getNombres()} ${cobro.getApellidos()}'/></h4>
                                            </div>
                                              <!-- <div class="modal-body">
                                                <label>INGRESE NUEVO NÚMERO TELEFONICO:</label>
                                               <form action="#"  class="form-horizontal" role="form">                                                  
                                                <div class="form-group">
                                                     <div class="col-lg-3">
                                                        <select class="form-control" name="tipo_tel" id="tTelefono" >
                                                            <option value='1' >Casa</option>
                                                            <option value='2' >Trabajo</option>
                                                            <option value='3' >Personal</option>                                            
                                                        </select>
                                                    </div>
                                                    <div class="col-lg-7">
                                                        <input type="text" class="form-control" id="newTelefono" onkeypress="ValidaSoloNumeros()">   
                                                    </div>  
                                                    <div class="col-lg-1">
                                                        <button type="button" id="agregaTelf" class="btn btn-success">Agregar</button>  
                                                    </div>                                       
                                                </div>  
                                              </form>                                                 
                                                <hr />
                                                 <label>INGRESE NUEVA DIRECCIÓN:</label>
                                                 <form action="#" class="form-horizontal" role="form">  
                                                <div class="form-group">
                                                      <div class="col-lg-3">
                                                            <select class=" form-control" name="tipo_tel" id="tDireccion" >
                                                                  <option value='1' >Domicilio</option>
                                                                  <option value='2' >Trabajo</option>                                          
                                                          </select>
                                                      </div>
                                                     <div class="col-lg-7">                                          
                                                        <textarea class="form-control input-sm" rows="2" id="direccion_new"></textarea>
                                                     </div>   
                                                     <div class="col-lg-1">                                          
                                                        <button type="button" id="agregaDir" class="btn btn-success">Agregar</button>
                                                     </div>  
                                                </div>    
                                               
                                                
                                              </form> 
                                                
                                                
                                            </div>-->
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

                                 
                                    <div id="det_articulo" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_articulo">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <br><div class="box box-danger">
                                                    <div class="box-header with-border bg-yellow">
                                                        <h3 class="box-title">Desglose de Artículos</h3>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    </div>
                                                    <div class="box-body" style="overflow-y:scroll;">
                                                        <table id="detalle_articulos" class="table table-bordered table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Referencia de Artículo</th>
                                                                    <th>Descripción</th>
                                                                    <th>Valor Articulo</th>                                                                    
                                                                    <th>Fecha de Compra</th>															  
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                 <%= request.getAttribute("detArticulos") %> 
                                                            </tbody>
                                                            <tfoot>
                                                                <%= request.getAttribute("detTotal") %> 
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
                                                    <div class="box-body" style="overflow-y:scroll;">
                                                        <table id="detalle_cuotas" class="table table-bordered table-hover">
                                                            <thead>
                                                                <tr>
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
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%= request.getAttribute("detCuotas") %>
                                                               
                                                                
                                                            </tbody>
                                                            <tfoot>
                                                                <%= request.getAttribute("detTotalCuota") %> 
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
                        
                        </c:forEach>            
                        <div class="row">   
                            <div id="pagedireccion">  
                            <div class="col-lg-4">
                                <div id="table_direccion" class="form-group">                                
                                    <dt>Dirección: </dt>
                                    <div  class="box table-responsive" >
                                        <table id="idAllDireccions" class="table-striped table-hover">
                                            <thead>
                                                <tr  bgcolor="#FBF5EF">
                                                    <th class="col-sm-2">Tipo</th>
                                                    <th class="col-sm-5">Dirección</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${direcciones}" var="direccion">           
                                                    <tr  bgcolor="#E0ECF8" >
                                                        <td><c:out value="${direccion.getLcTiposDireccion().getNombreTipoDireccion()}" /> </td>
                                                        <td><c:out value="${direccion.getDireccionCompleta()}" /> </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>                                       
                                </div>  
                            </div>
                            </div>
                            <div id="pagetelefono">  
                            <div id="table_telefono" class="col-lg-4">        
                                <dt>Telefonos: </dt>
                                <div  class="box table-responsive">  
                                    <table id="idAllTelefonos" class="table-striped table-hover">
                                        <thead>
                                            <tr  bgcolor="#FBF5EF">
                                                <th class="col-sm-2">Tipo</th>
                                                <th class="col-sm-2">Télefonos</th>
                                                <th class="col-sm-2">Llamar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${telefonos}" var="phone">               
                                                <tr bgcolor="#E0ECF8" >
                                                    <td class="table-success"><c:out value="${phone.getLcTiposTelefono().getNombreTipoTlf()}" /></td>                    
                                                    <td class="table-success"><c:out value="${phone.getTelefono()}" /></td>
                                                    <td class="table-success"><a  href="#" ><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span></a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>    
                            <div class="form-group col-lg-4">
                                <div class="row">
                                    <div class="col-lg-5">                                 
                                    <dt>Ciudad: </dt>
                                    <select class="input-sm form-control" name="Ciudad" required="required" disabled="true">
                                        <option value="<c:out value="${cobro.getLcCiudad().getIdCiudad()}"/>"><c:out value="${cobro.getLcCiudad().getCiudad()}"/></option>
                                        <c:forEach items="${ciudades}" var="ciudad">
                                            <option value="<c:out value="${ciudad.getIdCiudad()}" />" ><c:out value="${ciudad.getCiudad()}" /></option>
                                        </c:forEach>    
                                    </select>
                                    </div>
                                            
                                    <div class="col-lg-7">
                                        <dt>Volver a Llamar: </dt>     
                                    <form class="form-inline">
                                        <div class="input-group date">
                                            <!-- <div class="input-sm input-group-addon">
                                                <i class="fa fa-calendar"></i>                   
                                            </div> -->
                                            <input size="8" type="text" class="form-control input-sm datepicker" data-date-format="yyyy-mm-dd" id="datepicker2" onkeyup="verificaFecha();" name="recordatorio" placeholder="YYYY-MM-DD" disabled="true">
                                            
                                            
                                        </div> 
                                            <input size="4" type="text" class="form-control input-sm" id="hora" name="hora" placeholder="23:59" onkeyup="validaHora();" disabled="true">
                                        
                                           <!--  <button id="grabar_recordatorio" type="button" class="btn btn-primary btn-sm glyphicon glyphicon-plus" onclick="GuardarRecordatorio()" ></button> <span id="MsgValFecha"></span><span id="MsgValHora"></span>   -->
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
                                    <c:if test="${!empty(notas)}">
                                   
                                        <c:forEach items="${notas}" var="nota">
                                            <input id="idNotas" type="text" class="form-control hidden" placeholder="Dirección Trabajo" name="act_trabajo"  value="<c:out value="${nota.getIdNota()}" />" >
                                                                                                                                                
                                               <textarea maxlength="500" id="txtnota" class="form-control input-sm " rows="3" onkeyup="ValidarNota2()"  placeholder="NOTAS" style="overflow-y:scroll; background-color:#FDF9DB;  font-size:18px; font-type:Arial" value="<c:out value="${nota.getIdNota()}"/>"><c:out value="${nota.getObservacion()}"/></textarea>
                                        </c:forEach> 
                                    </c:if>
                                      <c:if test="${empty(notas)}">
                                          <input id="idNotas" type="text" class="form-control hidden" placeholder="Dirección Trabajo" name="act_trabajo" value="0" />

                                                           
                                           <textarea maxlength="500" id="txtnota" class="form-control input-sm "  rows="3" onkeyup="ValidarNota2()"  placeholder="NOTAS" style="overflow-y:scroll;background-color:#FDF9DB;  font-size:18px; font-type:Arial" ></textarea>
                                        
                                    </c:if> 
                                                
                                        </div>




                        </div>
                        <div class="row"> 
                            <form name="form" action="cobranzas" method="post"  id="data">                 
                                <div class="col-lg-4">                                  
                                    <dt>Tipo de Gestión: </dt>
                                    <select class="input-sm form-control" name="gestion" id="gestion" required="required" onchange="obtenerResultado()">
                                        <option value='0'>Seleccione la Gestión</option>
                                        <c:forEach items="${gestiones}" var="gestion">
                                            <option value="<c:out value="${gestion.getIdTipoGestion()}"/>" ><c:out value="${gestion.getNombreTipoGestion()}" /></option>
                                        </c:forEach>
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
                                        <option value='0'>Seleccione Tipo Resultado</option>

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
                                       
                                            <table id="allTrxGestiones" class="table-striped table-hover">
                                                <thead>
                                                    <tr  bgcolor="#FBF5EF">
                                                        
                                                        <th class="col-sm-1">Tipo Gestión</th>
                                                        <th class="col-sm-1">Gestión</th>
                                                        <th class="col-sm-5">Descripción</th>
                                                        <th class="col-sm-1">Oficial</th>
                                                        <th class="col-sm-2">Fecha</th>
                                                       
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <%= request.getAttribute("GestionTRX") %>
                                                </tbody>                       
                                            </table>
                                      
                                    </div>
                                </div> 
                            </section>
                        </div>
                    </c:forEach>           
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

