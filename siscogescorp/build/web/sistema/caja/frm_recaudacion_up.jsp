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
    </head>
    <body>
        <div>
            <div class="col-md-8">
                <!-- general form elements -->
                <br>
                    <div class="box-header with-border bg-yellow">
                        <h3 class="box-title">Número Recaudación:</h3>
                        <c:forEach items="${datos1}" var="datas1">
                        <strong><h3 class="box-title left" style="color: #003399; font-size: 20px">&nbsp;# <c:out value="${datas1.getIdRecaudacion()}" /> </h3></strong>
                        <input type="text" class="hidden" name="id_recaudacion" id="id_recaudacion" value="<c:out value="${datas1.getIdRecaudacion()}" />">
                    
                    </div>
                    <form name="form" action="recaudacion" method="get" class="well">
                        <div class="form-group">		
                            <div class="row">  
                                <c:forEach items="${datos}" var="datas">
                                <div class="col-lg-12">
                                    <div class="col-lg-6">
                                        <label>Cartera:</label>
                                        <select class="form-control" id="cartera" name="cartera" required="required">
                                            <option value="<c:out value="${datas.getLcClientes().getIdCliente()}"/>"><c:out value="${datas.getLcClientes().getRazonSocial()}" /></option>    
                                            <c:forEach items="${clientes}" var="carter">
                                                <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                            </c:forEach> 
                                        </select>					 
                                    </div>
                                    <c:forEach items="${oficial}" var="oficc">            
                                    <div class="col-lg-6">
                                        <label>Oficial de Cuenta:</label>
                                        <select class="form-control" id="oficial" name="oficial" >
                                            <option value="<c:out value="${oficc.getIdEmpleado()}"/>"><c:out value="${oficc.getNombres()} ${oficc.getApellidos()}" /></option>
                                            <c:forEach items="${empleados}" var="oficial">
                                                <option value="<c:out value="${oficial.getIdEmpleado()}" />"><c:out value="${oficial.getNombres()} ${oficial.getApellidos()}"/> </option>                         
                                            </c:forEach>
                                        </select>
                                    </div>
                                    </c:forEach>
                                </div>


                                <div class="form-group">	
                                    <div class="col-lg-12">
                                        <c:forEach items="${tipo}" var="tipos">
                                        <div class="col-lg-6">
                                            <label>Tipo Identificacion:</label>
                                            <select class="form-control" name="t_identificacion" required="required" id="t_identificacion" onchange="validaselector()">
                                                <option value="<c:out value="${tipos.getIdTipoIdentificacion()}"/>" ><c:out value="${tipos.getDescripcion()}" /></option>
                                                <c:forEach items="${tipIDE}" var="tipo">
                                                    <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                                </c:forEach> 
                                            </select>
                                        </div>
                                        </c:forEach>        
                                        <div class="col-lg-6">	
                                            <label>Nombres y Apellidos:</label>
                                            <input type="text" class="form-control"  list="myCompanies" placeholder="Ingresar Nombres y Apellidos: " name="company" id="nombres" value="<c:out value="${datas.getNombresCompleto()}"/>">
                                            <datalist id="myCompanies"></datalist>
                                        </div>
                                        <div class="col-lg-0 hidden">	
                                            <label>ID_deudor:</label>
                                            <input type="text" class="form-control" placeholder="id" name="idDeudor" id="idDeudor">
                                        </div>
                                    </div>
                                </div>
                                
                                    
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <div class="col-lg-6">
                                            <label>Identificación:</label>
                                            <input type="text" class="form-control" maxlength="13" onkeypress="ValidaSoloNumeros()" placeholder="Ingrese Identificación " required="required" name="identificacion" id="identificacion" disabled="true" value="<c:out value="${datas.getIdentificacion()}"/>">
                                            <div id="valido" class="form-group has-success" style="display: none"> <!--hidden-->
                                                <span class="help-block">Identificación Válida.</span>
                                            </div>
                                            <div id="no_value" class="form-group has-error" style="display: none"> <!--hidden-->
                                                <span class="help-block">Identificación Inválida.</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 ">
                                            <div class="form-group hidden">
                                                <br><button  id="Buscar" type="button" onclick="Buscarecaudo()" class="btn btn-primary btn-lg fa fa-search "  disabled="true"> Buscar</button>
                                                &nbsp;<button  id="busc_cuota" href="#detalle_cuota" onclick="BuscarCuota()" type="button" class="btn btn-primary " data-toggle="modal">Detalle Cuotas</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                </c:forEach>
                                <div class="col-lg-4">											
                                            <div id="detalle_cuota" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="detalle_cuotas">
                                                
                                            </div> 
                                </div>
                                
                                
                                <div class="form-group">
                                    									
                                        <section class="content ">
                                            <div id="pagetable">  
                                                <div id="transaccion_table"  class="box table-responsive" style="overflow-y:scroll;" >
                                                    <div id="table_graf1" style="overflow-x:scroll;">
                                                        <table  class="table table-responsive table-bordered">
                                                            <thead>
                                                                <tr>
                                                                
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
                                                             
                                                                         
                                                        </table>
                                                    </div>
                                                </div>
                                            </div> 
                                        </section> 
                                </div>
                            </div>
                        </div>
                        
                                                  
                        <div class="panel panel-default well margin">
                            <div class="row">
                                <div class="form-group"> 
                                    <div class="col-lg-12">

                                        <div class="col-lg-4">
                                            <label>Valor Total:</label>
                                            <input type="text" class="form-control" placeholder="ValorPagar" disabled="true" required="required" name="pago" id="pago" >										
                                        </div>
                                        <div class="col-lg-4">
                                            <label>Valor Recibido:</label>										
                                            <input type="text" class="form-control" onkeyup="CalculoCambio2()" placeholder="ValorRecibido"  required="required" name="recibido" id="recibido">										
                                        </div>
                                        <div class="col-lg-4">
                                            <label>Cambio:</label>
                                            <input type="text"  class="form-control" placeholder="cambio" disabled="true" required="required" name="cambio" id="cambio">										
                                        </div>									 
                                    </div>
                                </div>									 
                            </div>
                        </div>
                        </c:forEach>
                        
                        <div class="panel panel-default well margin">	
                            <div class="row">
                                <div class="col-lg-2">
                                    <b><label style="color: #0063dc"><h4>Forma de Pago:</h4></label></b>	  
                                </div>
                                <div class="form-group"> 
                                    <div class="col-lg-12">
                                        <div class="col-lg-2">
                                             <label>Efectivo</label>
                                            <!--input class="form-control" id="forma_pago" value='1'-->
                                        </div>
                                         <div class="col-lg-2">
                                            <input type="text"  class="form-control"   name="totalpago" id="totalpago" >										
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
                                        <div class='col-lg-2'><!--label>Número Cuenta:</label>
                                            <input type='text' class='form-control'  placeholder='N° Cuenta' name='cuenta' id='cuenta'-->										
                                            <label>Tarjeta Credito</label>
                                        </div>
                                        <div class='col-lg-3'><label>Valor:</label> 
                                            <input type='text' class='form-control' name='Valor' id='Valor'>
                                        </div>
                                        <div class='col-lg-3'><label>Número Tarjeta:</label> 
                                            <input type='text' class='form-control' maxlength="13" onkeypress="ValidaSoloNumeros()" placeholder='N° Tarjeta' name='tarjeta' id='tarjeta'>
                                        </div>

                                        <div class='col-lg-3'><label>Nombre de Institucion:</label>
                                             <select class="form-control" name='institucion' id='institucion'>
                                                 <option></option>
                                                <c:forEach items="${instituciones}" var="institucion">
                                                    <option value="<c:out value="${institucion.getIdInstitucion()}" />"><c:out value="${institucion.getNombreInstitucion()}" /> </option>                         
                                                </c:forEach>                 
                                            </select>
                                            <!--input type='text'  class='form-control'  placeholder='Nombre de Institucion'  name='institucion' id='institucion'-->
                                        </div>
                                    </div> 
                                </div>
                              
                                <input type="text" class="hidden" name="Cont" id="Cont" value="0">
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
                                        <div class='col-lg-2'><!--label>Número Cuenta:</label>
                                            <input type='text' class='form-control'  placeholder='N° Cuenta' name='cuenta' id='cuenta'-->										
                                            <label>Cheque</label>
                                        </div>
                                        <div class='col-lg-3'><label>Valor:</label> 
                                            <input type='text' class='form-control' name='Valor2' id='Valor2' >
                                        </div>
                                        <div class='col-lg-3'><label>Número Cuenta</label> 
                                            <input type='text' class='form-control' maxlength="13" onkeypress="ValidaSoloNumeros()"  placeholder='N° cuenta' name='cuenta' id='cuenta' >
                                        </div>

                                        <div class='col-lg-3'><label>Nombre de Institucion:</label>
                                            <select class="form-control" name='institucion2' id='institucion2'>
                                                <option ></option>
                                                <c:forEach items="${instituciones}" var="institucion">
                                                    <option value="<c:out value="${institucion.getIdInstitucion()}" />"><c:out value="${institucion.getNombreInstitucion()}" /> </option>                         
                                                </c:forEach>                 
                                            </select>
                                            <!--input type='text'  class='form-control'  placeholder='Nombre de Institucion'  name='institucion2' id='institucion2'-->
                                        </div>
                                    </div> 
                                </div>
                              
                               
                            </div>  
                        </div>
                  
                          

                        <center><div class="panel panel-default well margin">
                                <div class="form-group">
                                    <button id="Actualizado" type="button" class="btn btn-primary btn-lg fa fa-edit" disabled="true" onclick="ActualizarRecaudacion()">  Actualizar</button>            
                                    <!--a href="#" type="button" class="btn btn-primary btn-lg">CANCELAR</a-->                  
                                </div>
                            </div>
                        </center>
                    </form>        
                </div>
                        
              
        </div> 
        </div>    
        <script src="dist/js/recaudacion.js"></script>
        <script src="dist/js/nuevoPago.js"></script>  
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
        </script> 
        <!-- ./wrapper -->
    </body>
</html>