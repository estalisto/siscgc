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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">   
        <style type="text/css">
             hr { 
   border-top: 3px solid #4bceb4; 
   border-bottom: 2px dashed #4bceb4; 
   border-left:none; 
   border-right:none; 
   height: 6px; 
 } 
 input {
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
        <title>JSP Page</title>
    </head>
    <body>
            <ol class="breadcrumb">
               
                <li><a href="#" onclick="listar_pagos();">Pagos Realizados</a></li>
                <li class="active"><a href="#" onclick="recaudacion();">Recaudación</a></li>
                
                
                 
            </ol>
                   
                <section class="panel panel-default well-lg margin">
                    <div class="row">                       
                        <div class="col-lg-12">
                         <div class=""> 
                            <form class="form-inline">   
                                <div  class="form-group has-feedback">
                                  <a href="#" onclick="recaudacion()" class="btn btn-success">Nuevo Pago +</a></div>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                   <div class="form-group has-feedback"> 
                                       <button type="button" class="btn btn-info " id="buscarCompromisos" onclick="consultaRecaudaciones();">
                                     <span class="glyphicon glyphicon-search"></span> <strong> Buscar </strong>
                                  </button>
                                   </div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   <div class="form-group has-feedback">
                                      <a href="#" class="btn btn-primary ">
                                        <span class="glyphicon glyphicon-print"></span> Print 
                                      </a>
                                    </div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   <div class="form-group has-feedback">
                                       <a href="#" class="btn btn-primary " onclick="generacuadre_caja();" >
                                        <span class="glyphicon glyphicon-share"></span> Generar Cuadre de Caja 
                                      </a>
                                    </div>
                                   <br> <hr>
                                     <div class="form-group has-feedback">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      Efectivo  &nbsp;&nbsp;<input id="valor_efec"  class="text-center" value="0.00" disabled=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      Cheque  &nbsp;&nbsp;<input id="valor_ch"  class="text-center" value="0.00" disabled=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      T./Crédito  &nbsp;&nbsp;<input id="valor_tar" class="text-center" value="0.00" disabled=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      Total  &nbsp;&nbsp;<strong><input id="valor_inpt" class="text-center" value="0.00" disabled=""></strong>
                                    </div>
                                 
                              </form>
                             
                         </div>
                             
                        </div>
                        <!--div class="col-lg-3" >
                             <label class="control-label ">Total Recaudado: </label>
                           <div class="panel panel-default margin"> 
                                <form class="form-inline">   
                                    <div class="form-group has-feedback">
                                   
                                        <label class="control-label "><h3><p  class="text-center text-primary "><input id="valor_inpt" class="text-center" value="0.00" disabled></p></h3></label>                                    
                                   </div>  
                                </form> 
                             </div>  
                        </div-->
                    </div>
                    <br>
                    <div class="row">
                    
                        
                        <div class="col-lg-12">
                            <div id="tableRecaudaciones" class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Lista de Pagos</h3>
                                </div>
                                <!-- /.box-header -->
                                <div id="consultaRecaudaciones">
                                        
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
                  
         
       
    </body>
 <script src="dist/js/jquery.datetimepicker.full.js"></script>
 <script src="dist/js/nuevoPago.js"></script>    
 <script src="dist/js/validaciones.js"></script>   
 <script>
     DetalleRecaudacionesHoy('','');
 </script>
     
</html>
