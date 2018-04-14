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
            
             <form name="form" action="recepcion" method="get" class="well">
            
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Recepcion: </label> 
                     </div>
                        <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="recepcion"  name="recepcion">
                        </div>
                    </div>
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Ticket: </label>
                     </div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control input-sm" id="ticket" name="ticket">
                        </div>
                 </div>
                 <div class="row form-group">
                     <div class="col-sm-2">
                     <label>ID Deudor: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="id_deudor" name="id_deudor"> 
                        </div>
                     <div class="col-sm-2">
                         <label>Cofirmación Recepción: </label>
                     </div>
                     <div class="col-sm-2">                        
                         <input type="checkbox" name="recepcionSI" id="recepcionSI" onclick="listarcheck()"><b style="color:#00ca6d">SI</b><br>

                     </div>
                     <div class="col-sm-2">                        
                         <input type="checkbox" name="recepcionNO" id="recepcionNO" onclick="listarcheck()"><b style="color:#00ca6d">NO</b><br>

                     </div>
                 </div>
                   <div class="row form-group">
                     <div class="col-sm-2">
                     <label>Identificación: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="identificacion" name="identificacion"> 
                        </div>
                       <div class="col-sm-2">
                     <label>Nombres Completos: </label>
                     </div>
                       <div class="col-sm-4">
                            <input type="text" class=" form-control input-sm" id="nombres" name="nombres"> 
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
                                    <th class="col-sm-2 text-left" style="color: #3c8dbc">Documento</th>  
                                </tr> 
                            </thead>
                            <tbody id="TableRecepcion">

                            </tbody>
                        </table>
                    </div>
                </div> 
            </div> 
                <div class="form-group">
                <input id="btnrecepcion" type="submit" value="GUARDAR" class="btn btn-primary"  title="Confirmar Recepcion">
                </div>     
           </form> 
          </div>
          <!-- /.box -->

        </div>
      </div>
  
  <!-- /.content-wrapper -->

</body>
</html>