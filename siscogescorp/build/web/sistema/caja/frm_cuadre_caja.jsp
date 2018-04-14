<%-- 
    Document   : NuevoPago
    Created on : 17/05/2017, 02:02:03 PM
    Author     : ViewSoft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <br >
            <ol class="breadcrumb">
                <li><a href="#" onclick="cuadre_caja();">Cuadre de Caja</a></li>            
            </ol>
        <section class="content">
                    <div class="row">
                       
                        <div class="col-lg-9">
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
                                   <div class="form-group has-feedback"> 
                                       <button type="button" class="btn btn-success " id="buscarecaudo" onclick="buscar_cuadre()">
                                           <span class="glyphicon glyphicon-search" ></span> <strong> Buscar </strong>
                                  </button>
                                   </div>
                                   &nbsp;&nbsp;&nbsp;
                                    <div class="form-group has-feedback">
                                      <a href="#" class="btn btn-primary ">
                                        <span class="glyphicon glyphicon-print"></span> Print 
                                      </a>
                                    </div>
                                <div class="form-group has-feedback"> 
                                       <button type="button" class="btn btn-danger " id="cuadrecaja" onclick="guardar_cuadre()">
                                           <span class="glyphicon glyphicon-download" ></span> <strong> Cuadre Caja</strong>
                                  </button>
                                   </div>    
                                 
                              </form>
                             
                         </div>
                             
                        </div>
                        <div class="col-lg-3">
                             <label class="control-label ">Total Recaudado: </label>
                           <div class="panel panel-default margin"> 
                                <form class="form-inline">   
                                    <div class="form-group has-feedback">
                                   
                                    <label class="control-label "><h3><p  class="text-center text-primary "><input id="valor_total" class="text-center" value="$<%= request.getAttribute("sumatotal")%>" readonly></p></h3></label>                                    
                                   </div>  
                                </form> 
                             </div>  
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div  class="box box-warning">
                                <!-- /.box-header 
                                <div class="box-header">
                                    <h3 class="box-title">Pagos Efectivo</h3>
                                </div>-->
                                <!-- /.box-header -->
                                <div id="tableCuadreCaja">
                                        <div class="box-body" style="overflow-x:scroll;">
                                            <table id="example1" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>N° Comprobante</th>  
                                                        <th>Fecha Pago </th>
                                                        <th>Nombre Deudor</th>
                                                        <th>Valor Total</th>
                                                        <th>Acción </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="datacaja">
                                                    <%= request.getAttribute("Tablacaja")%>
                                                </tbody>

                                            </table>
                                        </div>
                                </div>
                                <!-- /.box-body 
                                
                            </div>
                            <br>
                             <div  class="box box-warning">
                                <div class="box-header">
                                    <h3 class="box-title">Pagos Cheque</h3>
                                </div>
                                <div id="tableCuadreCaja">
                                        <div class="box-body" style="overflow-x:scroll;">
                                            <table id="example1" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>

                                                        <th>N° Comprobante</th>
                                                        <th>Nombre Deudor</th>
                                                        <th>Valor Total</th>
                                                        <th>Fecha Pago </th>
                                                        <th>Acción </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="datacheque">
                                                      
                                                </tbody>

                                            </table>
                                        </div>
                                </div>
                                <!-- /.box-body >
                                
                                </div>
                             <div  class="box box-warning">
                                <div class="box-header">
                                    <h3 class="box-title">Pagos Tarjeta Crédito</h3>
                                </div>
                                <!-- /.box-header >
                                <div id="tableCuadreCaja">
                                        <div class="box-body" style="overflow-x:scroll;">
                                            <table id="example1" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>

                                                        <th>N° Comprobante</th>
                                                        <th>Nombre Deudor</th>
                                                        <th>Valor Total</th>
                                                        <th>Fecha Pago </th>
                                                        <th>Acción </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="datatarjeta">
                                                      
                                                </tbody>

                                            </table>
                                        </div>
                                </div>
                                <!-- /.box-body -->
                                
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
         
        
        <script src="dist/js/jquery.datetimepicker.full.js"></script>
        <script src="dist/js/cuadrecaja.js"></script>  
        <script src="dist/js/nuevoPago.js"></script>    
        <script src="dist/js/validaciones.js"></script>   
    </body>

</html>
