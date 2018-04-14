<%-- 
    Document   : RecepcionDocumento
    Created on : 29/08/2017, 09:19:53 AM
    Author     : ViewSoft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">   
    </head><!-- Content Wrapper. Contains page content -->
<body>
  <div>

      <br >
      <!--ol class="breadcrumb">
            <li><a onclick="cargos(this)">Lista Cargos</a></li>
            <li class="active"><a onclick="frm_cargos(this)">Registra Cargos</a></li>
             
      </ol-->
      
      <div class="col-md-8">


          <!-- general form elements -->
          <div class="box box-danger">
            <div class="box-header with-border bg-yellow">
              <h3 class="box-title">Recepcion Documentos</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
             <form name="form" action="recepcion" method="post" class="well">
            
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Recepcion: </label> 
                     </div>
                        <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="recepcion"  name="recepcion">
                        </div>
                     <div class="col-sm-2">
                    <input id="btnconsultar" type="button" value="CONSULTAR" class="btn btn-primary" onclick="ConsultarQr()" title="Consultar Recepcion">
                    </div>
                     <div class="col-sm-3">
                        <div id="id_msm_doc" class="form-group has-error" style="display: none"> 
                        <span class="help-block"> Codigo no válido</span>
                        </div>
                        <div id="id_msm_falta" class="form-group has-error" style="display: none"> 
                        <span class="help-block"> Ingrese Cod de documento</span>
                        </div> 
                    </div> 
                    </div>
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Ticket: </label>
                     </div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control input-sm" id="ticket" name="ticket" readonly>
                            <input type="text" class="form-control input-sm" style="display: none" id="idDoc" name="idDoc">
                            <input type="text" class="form-control input-sm" style="display: none" id="ruta" name="ruta">
                        </div>
                 </div>
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>ID Deudor: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="id_deudor" name="id_deudor" readonly> 
                        </div>
                     <div class="col-sm-2">
                         <label>Cofirmación Recepción: </label>
                     </div>
                     <div class="col-sm-2">                        
                         <input type="checkbox" data-target="#data_recepcion" name="recepcionSI" id="recepcionSI" onclick="DatosRecibidos()" disabled="true" class="btn btn-success" data-toggle="modal" ><b style="color:#00ca6d">SI</b><br>

                     </div>
                     <div class="col-sm-2">                        
                         <input type="checkbox" data-target="#data_recepcion" name="recepcionNO" id="recepcionNO" onclick="DatosRecibidos()" disabled="true" class="btn btn-success" data-toggle="modal"><b style="color:#00ca6d">NO</b><br>

                     </div>
                 </div>
                   <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Identificación: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="identificacion" name="identificacion" readonly> 
                        </div>
                       <div class="col-sm-2">
                     <label>Nombres Completos: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="nombres" name="nombres" readonly> 
                        </div>
                 </div>
              <div class="box box-info">

                <div class="panel panel-default well-lg margin"  style="overflow-x: auto">
                    <div id="tabla_div">

                        <table id="consul_cartera" class="table table-striped table-bordered dt-responsive nowrap table-hover" cellspacing="0" width="50%">
                            <thead>
                                <tr bgcolor="#FBF5EF">                                  
                                    <th class="col-sm-1 text-left" style="color: #3c8dbc">Cedente</th>                                                        
                                    <th class="col-sm-1 text-left" style="color: #3c8dbc">Total Deuda</th>
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Dias Mora</th>  
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Abono Recaudado</th>  
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Persona Recibe</th> 
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Identificacion</th>  
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Fecha Recepción</th> 
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Documento</th>  
                                </tr> 
                            </thead>
                            <tbody id="TableRecepcion">

                            </tbody>
                        </table>
                    </div>
                </div> 
            </div> 
           </form> 
          </div>
          <!-- /.box -->

        </div>
      </div>
    <div id="data_recepcion" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="data_recepcion">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <br><div class="box box-danger">
                    <div class="box-header with-border bg-yellow">
                        <h3 class="box-title">Datos de Recepción</h3>
                        <button type="button" class="close" data-dismiss="modal" onclick="Quitarcheck()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label>* Identificación:</label>
                            <input type="text" class="form-control" maxlength="13" onkeypress="ValidaSoloNumeros()" placeholder="Ingrese Identificación " required="required" name="cedula" id="cedula">

                        </div>
                        <div class="form-group">	
                            <label>* Nombres y Apellidos:</label>
                            <input type="text" class="form-control" placeholder="Ingresar Nombres y Apellidos" name="nom_completos"  id="nom_completos">
                            <!--datalist id="myCompanies"></datalist-->
                        </div>
                        
                        <div class="form-group has-feedback">
                            <label class="control-label">* Fecha Recepcion:</label>
                            <input type="text" class="form-control" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy" id="datepicker"/>
                            <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
                        </div>
                        <div class="form-group">
                            <label>Observacion:</label>
                            <textarea id="observacion" class="form-control" rows="3" name="observacion" required="required"></textarea>
                        </div>
                    </div>
                    <div id="id_message" class="form-group has-error" style="display: none"> 
                        <br>&nbsp;&nbsp;&nbsp;
                        <span class="help-block center">&nbsp;&nbsp;Falta llenar los datos de ingreso</span>
                    </div>
                    <div class="modal-footer">
                        <input id="btnrecepcion" type="button" value="GUARDAR" class="btn btn-primary" onclick="GuardarRecepcion()"  title="Confirmar Recepcion">
                        <button type="button" class="btn btn-default" onclick="Quitarcheck()" data-dismiss="modal">Close</button>
                    </div>
                    
                </div>
            </div>      
        </div>
    </div>   
  <!-- /.content-wrapper -->
  <script src="dist/js/jquery.datetimepicker.full.js"></script>
<script src="dist/js/recepcion_documento.js"></script> 
<script>
$(function () {
   if($('#data_recepcion').modal('hide')){
       $("#recepcionSI").prop('checked', false);
       $("#recepcionNO").prop('checked', false);
   }
});
</script>
</body>
</html>