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
    </head>
    <body>
        <br >
        <ol class="breadcrumb">
            <li><a href="#" onclick="listar_pagos();">Pagos Realizados</a></li>
            <li class="active"><a href="#" onclick="recaudacion();">Recaudación</a></li>    
        </ol>
        <div>
            <section class="content">
                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow">
                        <h3 class="box-title">Consultas y Recaudación </h3> 
                    </div>

                    <div class="col-lg-12">
                        <form name="form" action="#" class="">
                        
                        <div class="form-group">		
                            <div class="form-group">	
                                <label>Datos Deudores</label>   
                                <div class="col-lg-12">
                                    <div class="col-lg-1">
                                        <a href="#consulta"  id="listoModal" data-toggle="modal" class="btn btn-primary fa fa-search">Consulta</a>
                                    </div>
                                    <div class="col-lg-1">
                                        <select class="form-control input-sm" name="t_identificacion" required="required" id="t_identificacion" onchange="validaselector()" disabled >
                                            <option value='' >Tipo de identificación</option>
                                            <c:forEach items="${tipIDE}" var="tipo">
                                                <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                            </c:forEach> 
                                        </select>
                                    </div>
                                    <div class="col-lg-1">
                                        <!--label>Identificación:</label-->
                                        <input type="text" class=" form-control input-sm" maxlength="13" onkeypress="ValidaSoloNumeros()" placeholder="Identificación " name="identificacion" id="identificacion" disabled>

                                    </div>
                                    <div class="col-lg-3">	
                                        <!--label>Nombres y Apellidos:</label-->
                                        <input type="text" class="form-control input-sm"  placeholder="Nombres y Apellidos" name="nombres" id="nombres" disabled>
                                    </div>
                                    
                                    <div class="col-lg-0 hidden">	
                                        <label>ID_deudor:</label>
                                        <input type="text" class="form-control input-sm" placeholder="id" name="idDeudor" id="idDeudor" disabled>
                                    </div>
                                    <div class="col-lg-1">
                                        <select class="form-control input-sm" id="cartera" name="cartera" required="required" disabled>
                                            <option value=''>Cartera</option>    
                                            <c:forEach items="${clientes}" var="carter">
                                                <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                            </c:forEach> 
                                        </select>					 
                                    </div>
                                    <div class="col-lg-2">	
                                        <input type="text" class="form-control input-sm" id="tip_cartera" placeholder="Tipo Cartera" disabled>
                                    </div>
                                    <div class="col-lg-1">
                                        <div class="form-group">
                                            <!--button id="Buscar" type="button" onclick="Buscarecaudo()" class="btn btn-primary btn-lg fa fa-search"> Buscar</button-->
                                            <button id="busc_cuota" href="#detalle_cuota" onclick="BuscarCuota()" type="button" class="btn btn-success input-sm" data-toggle="modal" disabled="true">Consulta Cuotas</button>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <select class="form-control input-sm" name='consulta_recaudador' id='consulta_recaudador' disabled="true">
                                             
                                        </select>
                                    </div>
                                </div>
                            </div> 
                            <div class="form-group" hidden>
                                <label>Casa Comercial</label>
                                <div class="col-lg-12">
                                    <div class="col-lg-4">

                                        <select class="form-control" id="cartera" name="cartera" required="required" disabled>
                                            <option value=''>Cartera</option>    
                                            <c:forEach items="${clientes}" var="carter">
                                                <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                            </c:forEach> 
                                        </select>					 
                                    </div>
                                    <div class="col-lg-4">	

                                        <input type="text" class="form-control" id="tip_cartera" placeholder="Tipo Cartera" disabled>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <!--button id="Buscar" type="button" onclick="Buscarecaudo()" class="btn btn-primary btn-lg fa fa-search"> Buscar</button-->
                                            <button id="busc_cuota" href="#detalle_cuota" onclick="FuncionesCuotas()" type="button" class="btn btn-success" data-toggle="modal" disabled="true">Consulta Detalle Cuotas</button>
                                        </div>
                                    </div>
                                </div>
                                </div>
                                <div class="col-lg-4">											
                                            <div id="detalle_cuota" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_cuotas">
                                                
                                            </div> 
                                </div>
                                <div class="form-group">
                                    <input type="text" class="hidden" name="Cont" id="Cont" value="0">										
                                        <section class="content ">
                                            <div id="pagetable">  
                                                <div id="transaccion_table"  class="box table-responsive" style="overflow-y:scroll;" >
                                                    <div id="table_graf1" style="overflow-x:scroll;">
                                                        <table  class="table table-responsive table-bordered">
                                                            <thead>
                                                                <tr bgcolor="#FBF5EF">
                                                                <th class="hidden">IdCuota</th>
                                                                <th class="hidden">IdArticulo</th>
                                                                    <th>Articulo</th>
                                                                    <th>N°Cuota</th>
                                                                    <th>Cuota</th>
                                                                    <th>Interes</th>
                                                                    <th>Mora</th>
                                                                    <th>Total</th>
                                                                    <th>Valor Pagar</th>
                                                                    <th>Saldo</th>
                                                                    <th>Opcion</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="table1"></tbody>
                                                            <tbody id="table2">
                                                               
                                                                    <tr> 
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                    </tr>                                      
                                                            </tbody>                       
                                                        </table>
                                                    </div>
                                                </div>
                                            </div> 
                                        </section> 
                                </div>
                            </div>
                    </form>
                        </div>
                        
                                                  
                        <div class="panel panel-default">
                            <div class="row">
                                <div class="form-group"> 
                                    <div class="col-lg-12">

                                        <div class="col-lg-4">
                                            <label>Valor Total:</label>
                                            <input type="text" class="form-control" placeholder="ValorPagar" disabled="true" required="required" name="pago" id="pago">										
                                        </div>
                                        <div class="col-lg-4">
                                            <label>Valor Recibido:</label>										
                                            <input type="text" class="form-control" onkeyup="CalculoCambio()" placeholder="ValorRecibido" disabled="true" required="required" name="recibido" id="recibido">										
                                        </div>
                                        <div class="col-lg-4">
                                            <label>Cambio:</label>
                                            <input type="text"  class="form-control" placeholder="cambio" disabled="true" required="required" name="cambio" id="cambio">										
                                        </div>									 
                                    </div>
                                </div>									 
                            </div><br>
                        </div>
                        
                        <div class="panel panel-default well margin">	
                            <div class="row">
               
                        <div class="row">
                                <div class="form-group"> 
                                    <div class="col-lg-12">
                                        <div class="col-lg-1">
                                             <label>Efectivo</label>
                                            <!--input class="form-control" id="forma_pago" value='1'-->
                                        </div>
                                         <div class="col-lg-1">
                                            <input type="text"  class="form-control"   name="totalpago" id="totalpago">										
                                        </div>
                                        <!--div class="col-lg-1">
                                            <button id="mas" type="button" class="btn btn-block btn-success fa fa-plus" onclick="FormaPago()"></button>
                                        </div-->
                                    </div>                                
                                </div>
                                <div class="form-group" > 
                                    <div class="col-lg-12">
                                        
                                        <div class="col-lg-4">
                                            
                                            <!--select class="form-control" id="forma_pago2" >
                                                <option value='2' >Tarjeta Credito</option>
                                            </select-->
                                        </div>
                                    </div>                                
                                </div>
                                <div class='form-group'> 
                                    <div class='col-lg-12'> 
                                        <div class='col-lg-1'><!--label>Número Cuenta:</label>
                                            <input type='text' class='form-control'  placeholder='N° Cuenta' name='cuenta' id='cuenta'-->										
                                            <label>Tarjeta Credito</label>
                                        </div>
                                        <div class='col-lg-1'><label>Valor:</label> 
                                            <input type='text' class='form-control' name='Valor' id='Valor'>
                                        </div>
                                        <div class='col-lg-3'><label>Número Tarjeta:</label> 
                                            <input type='text' class='form-control'  placeholder='N° Tarjeta' name='tarjeta' id='tarjeta'>
                                        </div>

                                        <div class='col-lg-3'><label>Nombre de Institucion:</label>
                                        <select class="form-control" name='institucion' id='institucion'>
                                            <option value=''>Seleccionar Institución</option>
                                            <c:forEach items="${instituciones}" var="institucion">
                                                <option value="<c:out value="${institucion.getIdInstitucion()}" />"><c:out value="${institucion.getNombreInstitucion()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                        </div>
                                    </div> 
                                </div>
                                <div class="form-group"> 
                                    <div class="col-lg-12">
                                        
                                        <div class="col-lg-4">
                                            <!--select class="form-control" id="forma_pago3" >
                                                <option value='3' >Cheque</option>
                                            </select-->
                                        </div>
                             
                                </div>
                                <div class='form-group'> 
                                    <div class='col-lg-12'> 
                                        <div class='col-lg-1'><!--label>Número Cuenta:</label>
                                            <input type='text' class='form-control'  placeholder='N° Cuenta' name='cuenta' id='cuenta'-->										
                                            <label>Cheque</label>
                                        </div>
                                        <div class='col-lg-1'><label>Valor:</label> 
                                            <input type='text' class='form-control' name='Valor2' id='Valor2'>
                                        </div>
                                        <div class='col-lg-3'><label>Número Cheque</label> 
                                            <input type='text' class='form-control'  placeholder='N° cheque' name='cheque' id='cheque'>
                                        </div>
										<!-- Se agrego este nuevo campo de ingreso -->
                                        <div class='col-lg-3'><label>Número Cuenta</label> 
                                            <input type='text' class='form-control'  placeholder='N° cuenta' name='cuenta' id='cuenta'>
                                        </div>

                                        <div class='col-lg-3'><label>Nombre de Institucion:</label>
                                            <select class="form-control" name='institucion2' id='institucion2'>
                                                <option value=''>Seleccionar Institución</option>
                                                <c:forEach items="${instituciones}" var="institucion">
                                                    <option value="<c:out value="${institucion.getIdInstitucion()}" />"><c:out value="${institucion.getNombreInstitucion()}" /> </option>                         
                                                </c:forEach>                 
                                            </select>
                                        </div>
                                    </div> 
                                </div>
                                                              
                            </div>  
                        </div>
                  
                        

                        <center><div class="panel panel-default well margin">
                                <div class="form-group">
                                    <button id="Guardado" type="button" class="btn btn-primary btn-lg" disabled="true" onclick="GuardarRecaudacion()">GUARDAR</button>            
                                    <!--a href="#" type="button" class="btn btn-primary btn-lg">CANCELAR</a-->                  
                                </div>
                            </div>
                        </center>
                        </form>        
                    </div>
                </div>  
            </section>
            <div class="btn-toolbar" role="toolbar">      
                <div id="consulta" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="consulta">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="box box-danger">

                                <div class="box"><br>
                                    <div class="box-header with-border bg-green-active" >
                                        <h3 class="box-title">Deudores de recaudaciones</h3>
                                    </div>  <br>
                                    <div class="section">	
                                        <label>Consulta Deudor</label>   
                                        <div class="col-lg-12">
                                            <div class="col-lg-3">
                                                <select class="form-control" id="nomb_cartera" name="nomb_cartera" required="required" >
                                                    <option value=''>Seleccionar Cartera</option>    
                                                    <c:forEach items="${clientes}" var="carter">
                                                        <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                                    </c:forEach> 
                                                </select>
                                            </div>
                                            <div class="col-lg-3">
                                                <!--label>Identificación:</label-->
                                                <input type="text" class="form-control" maxlength="13" onkeypress="ValidaSoloNumeros()" placeholder="Ingrese Identificación " required="required" name="cedula" id="cedula">

                                            </div>
                                            <div class="col-lg-3">	
                                                <!--label>Nombres y Apellidos:</label-->
                                                <input type="text" class="form-control" placeholder="Ingresar Nombres y Apellidos" name="nom_completos"  id="nom_completos">
                                                <!--datalist id="myCompanies"></datalist-->
                                            </div>
                                            <div class="col-lg-0 hidden">	
                                                <label>ID_deudor:</label>
                                                <input type="text" class="form-control" placeholder="id" name="idDeudor" id="idDeudor">
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <button id="Buscar" type="button" onclick="Buscarecaudo()" class="btn btn-primary btn-lg fa fa-search"> Buscar</button>
                                                </div>
                                            </div>
                                            <div class="col-lg-1">
                                                <center><img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                                            </div>  
                                            <div id="id_message_carter" class="form-group has-error" style="display: none"> 
                                            <br>&nbsp;
                                            <span class="help-block">Debe seleccionar una cartera</span>
                                        </div>
                                        <div id="id_message" class="form-group has-error" style="display: none"> 
                                            <br>&nbsp;
                                            <span class="help-block">Debe ingresar una identificacion o Nombre del deudor</span>
                                        </div>
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="form-group">
                                        <section class="content ">
                                            <div id="pagetable"> 
                                                <div id="transaccion_table"  class="box table-responsive" style="overflow-y:scroll;" >
                                                    <div id="table_graf1" style="overflow-x:scroll;">
                                                        <table  class="table table-responsive table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th>Cédula</th>
                                                                    <th>Nombres Completos</th>
                                                                    <th>Cliente</th>
                                                                    <th>Cartera</th>
                                                                    <th>Acción</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="tableDE"></tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div></section></div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>   
                        </div>
                    </div>
            </div>  
        </div> 
        <script src="dist/js/recaudacion.js"></script>
        <script>
            $(function () {
                $('#detalle_cuotas').DataTable({
                    "paging": false,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": true,
                    "info": false,
                    "autoWidth": false
                });
            });
            ConsultaRecaudadores();
        </script> 
        <!-- ./wrapper -->
    </body>
</html>