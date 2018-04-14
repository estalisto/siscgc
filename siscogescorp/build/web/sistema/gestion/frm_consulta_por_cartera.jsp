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
        
           <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Minified Bootstrap CSS 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">-->

<link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">
<!-- Minified JS library 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Minified Bootstrap JS 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
  </head>

<body>

  <div> 
      
      <br><div class="box box-danger">
        <div class="box-header with-border bg-yellow">
          <h3 class="box-title">Consultas por Cartera</h3>
        </div> 
          <div class="row">
                    <div class="col-lg-8"> <br>

                          <div class="row">
                              <div class="col-lg-12">                    
                                  <div class="row">
                                       <div class="col-sm-2">
                                          <label>Cliente Cartera:</label>
                                       </div>
                                      <div class="col-sm-6">
                                           <select class="form-control" name="cartera" onchange="alert('ok');" required="required" id="cartera" >

                                          <c:forEach items="${carteras}" var="carter">
                                              <option value="<c:out value="${carter.getIdCliente()}" />"><c:out value="${carter.getRazonSocial()}" /> </option>                         
                                          </c:forEach> 
                                          </select>
                                       </div>                                             
                                      <div class="col-sm-2">                        
                                          <button id="btnbuscar" type="submit" class="btn btn-primary btn-sm fa fa-search"> BUSCAR</button>                       
                                      </div>
                                      <div class="col-sm-2">                        
                                          <a href="#det_filtro" onclick="getTiposGestiones();"  data-toggle="modal" class="btn btn-primary  btn-sm fa fa-filter">  FILTRO  </a>
                                      </div>
                                  </div>
                              </div>                  
                          </div>

                  </div> 
                  <div class="col-lg-4">             
                          <div class="row">
                              <div class="col-lg-10">               
                                  <div class="row"></div>
                              </div> 
                              <div class="col-lg-2"> 
                               <center><img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="50" style="display: none"></center>
                              </div>                   
                          </div>           
                  </div> 
          </div>
                    <input  type="text" class="form-control input-sm hidden" id="IdDiasMora">
                    <input  type="text" class="form-control input-sm hidden" id="IdTotalVenc">
                    <input  type="text" class="form-control input-sm hidden" id="IdFechaUlt">
                    <input  type="text" class="form-control input-sm hidden" id="IdIdentificacion">
                    <input  type="text" class="form-control input-sm hidden" id="IdNombres">
                    <input  type="text" class="form-control input-sm hidden" id="IdPagos">
                    <input  type="text" class="form-control input-sm hidden" id="IdFecUltPagos">
                    <input  type="text" class="form-control input-sm hidden" id="IdSaldo">
                    <input  type="text" class="form-control input-sm hidden" id="IdValorComp">
                    <input  type="text" class="form-control input-sm hidden" id="IdFechaComp">
                    <input  type="text" class="form-control input-sm hidden" id="IdUltima">
                    <input  type="text" class="form-control input-sm hidden" id="IdResultado">
           
                <div class="row">
                    <div class="col-lg-12">   
                       <div class="box"> 
                           
                        <section class="content ">
                         <div id="pagetable">  
                            <div class="row">
                                <div  class="col-xs-12" >
                                        <form name="form1" action="consultacartera" method="get">                                
                                         <div>                        
                                            <div  class="box-body table-responsive" style="overflow-x: auto" >
                                         <table id="consul_cartera" class="table-striped table-hover " >
                                                            <thead>
                                                                    <tr bgcolor="#FBF5EF">                                  
                                                                        <th class="col-sm-1 text-left hidden" style="color: #3c8dbc">ID</th>                                                        
                                                                        <th align="left" class="col-sm-1 text-left"><a id="IdentificacionID" onclick="orderIdent()">Identificación</a></th>
                                                                        <th class="col-sm-2 text-left"><a id="NombresID" onclick="orderNombre()">Nombres</a></th>  
                                                                        <th class="col-sm-1 text-left"><a id="DiasMoraID" onclick="orderDiasMora()" >Días Mora</a></th> 
                                                                        <th class="col-sm-1 text-right"><a id="TotalID" onclick="orderTotalVenc()" >Total Vnc</a></th> 
                                                                        <th align="center" class="col-sm-1 text-right"><a id="PagosID" onclick="orderPagos()">Pagos</a></th>
                                                                        <th align="center" class="col-sm-1 text-right"><a id="FecUltPagosID" onclick="orderFechaUltPagos()">Fecha Ult. Pagos</a></th>
                                                                        <th align="rigth" class="col-sm-1 text-right"><a id="SaldosID" onclick="orderSaldo()">Saldo</a></th> 
                                                                        <th align="center" class="col-sm-1 text-right"><a id="ValorCompID" onclick="orderValorComp()">Valor Comp.</a></th> 
                                                                        <th align="center" class="col-sm-2 text-center"><a id="FechaCompID" onclick="orderFechaComp()">Fecha Comp.</a></th>
                                                                        <th align="center" class="col-sm-3"><a id="FechaID" onclick="orderFchGestion()" >Fecha Ult. Gestión</a></th> 
                                                                        <th align="center" class="col-sm-3"><a id="UltimaID" onclick="orderUltima()">Ult. Gestión</a></th> 
                                                                        <th align="center" class="col-sm-2"><a id="ResultadoID" onclick="orderResultado()">Resultado Gestión</a></th>
                                                                    </tr>  
                                                            </thead>
                                                            <tbody id="bodytable">                                
                                                               
                                                            
                                                                 <%= request.getAttribute("Tablacartera") %>
                                                            </tbody>
                                                    </table>
                                            </div>
                                         </div>
                                        </form>
                                </div>
                            </div>
                        </div> 
                        
                        </section>
                        </div>                          
                    </div>
                </div>
           
      </div> 
  </div> 

<div id="det_filtro" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="detalle_filtro">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
                <div class="modal-header">
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                    <h4 class="model-title">Detalle Filtro</h4>
                    <hr>
                    <div class="row">
                    <div class="col-lg-4" >      
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="dias_mora">  Días Mora <br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="total_vencidos">  Total Vencido <br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="pagos">  Pagos<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fecha_ult_pagos">  Fecha último pagos<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="saldo">  Saldo<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="valor_compro">  Valor Compromiso<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fecha_comp">  Fecha Compromiso<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="fech_ultima_gestion">  Fecha Última Gestión<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="ultima_gestion">  Última Gestión<br>
                                    <input type="radio" name="ordenar" onclick="fnc_order_by(this.value)" value="resultado_gestion">  Resultado Gestión<br> 
                                    <br>
                                    <div class="" hidden>
                                            <dt>Cartera: </dt>
                                            <select class="form-control" name="SelectCartera" id="SelectCartera" >
                                                    <option value='1' >Cartera 1</option>
                                                    <option value='2' >Cartera 2</option>
                                                    <option value='3' >Cartera 3</option>                                            
                                            </select>
                                    </div> 
                                    <div class="" hidden>
                                            <dt>Sub-cartera: </dt>
                                            <select class="form-control" name="SelectSubCartera" id="SelectSubCartera" >
                                                    <option value='1' >Subcartera 1</option>
                                                    <option value='2' >Subcartera 2</option>
                                                    <option value='3' >Subcartera 3</option>                                            
                                            </select>
                                    </div>
                                    <div class="" hidden>
                                            <dt>Segmento: </dt>
                                            <select class="form-control" name="SelectSegmento" id="SelectSegmento" >
                                                    <option value='1' >Segmento 1</option>
                                                    <option value='2' >Segmento 2</option>
                                                    <option value='3' >Segmento 3</option>                                            
                                            </select>
                                    </div> 
                                    <div class="" hidden>
                                            <dt>Sub-Segmento: </dt>
                                            <select class="form-control" name="SelectSubSegmento" id="SelectSubSegmento" >
                                                    <option value='1' >SubSegmento 1</option>
                                                    <option value='2' >SubSegmento 2</option>
                                                    <option value='3' >SubSegmento 3</option>                                            
                                            </select>
                                    </div>                                                                                                            
                    </div>
                    <div class="col-lg-8">             
                                    <div class="row">
                                            <div class="col-lg-12">               
                                                    <div class="row">
                                                            <div class="col-lg-6">
                                                                    <!--div class="form-inline">                                                       
                                                                                    <label>Monto</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="monto" placeholder="$ 0.00" >
                                                                                    <input size="5" type="text" class="form-control input-sm" id="monto1" placeholder="$ 0.00" >                                                               
                                                                    </div-->
                                                                    <div class="form-inline">
                                                                                    <label>Pagos</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="pagos" name="idpagos" placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="pagos1" name="idpagos1" placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Total Vencido</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="tvencido" name="idtvencido"  placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="tvencido1" name="idtvencido1"  placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Valor Compromiso</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="vcompromiso" placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="vcompromiso1" placeholder="$ 0.00">
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>Saldos</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="saldos"  placeholder="$ 0.00">
                                                                                    <input size="5" type="text" class="form-control input-sm" id="saldos1"  placeholder="$ 0.00">
                                                                    </div> 
                                                                    <div class="">
                                                                            <dt>Gestión: </dt>
                                                                            <select class="form-control  input-sm" onchange="ConsultaTipoResultado();" name="tgestion" id="tgestion" ></select> 
                                                                    </div>
                                                                    <div class="">
                                                                            <dt>Resultado de Gestión: </dt>
                                                                            <select class="form-control  input-sm " name="tresultado_gestion" id="tresultado_gestion" ></select>
                                                                    </div>

                                                            </div>

                                                            <div class="col-lg-6">
                                                                    <div class="form-inline">
                                                                                    <label>Días Mora</label><br>
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora" name="idmora" placeholder="$ 0.00" >
                                                                                    <input size="5" type="text" class="form-control input-sm" id="dia_mora1" name="idmora1" placeholder="$ 0.00" >
                                                                    </div>
                                                                    <div class="form-inline">
                                                                                    <label>F. Últ. Pago</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm "  id="datetimepicker10" name="ultimo_pagodesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm "  id="datetimepicker11" name="ultimo_pagohasta" placeholder="YYYY-MM-DD" >                                                                                                                        
                                                                    </div>
                                                                    <div class="form-inline">  
                                                                                    <label>F. Últ. Gestión</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker12" name="ultima_gestiondesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker13" name="ultimo_gestionhasta" placeholder="YYYY-MM-DD" > 
                                                                    </div>

                                                                    <div class="form-inline">  
                                                                                    <label>Fecha Compromiso</label><br>
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker14" name="fecha_compromisodesde" placeholder="YYYY-MM-DD" >                                                        
                                                                                    <input size="12" type="text" class="form-control input-sm" id="datetimepicker15" name="fecha_copromisohasta" placeholder="YYYY-MM-DD" > 
                                                                    </div>
                                                                    <div class="form-inline">
                                                                               <label></label><br><br><br>

                                                                    </div> 
                                                                <input type="text"  class="form-control input-sm hidden" id="order_by" >


                                                            </div>                                                    


                                                    </div>
                                                    <br>

                                            </div>                  
                                    </div>           
                            </div>
                 </div>
                </div>
            <div class='modal-footer'>
                <button type="button" id="btnconsultar" onclick="consulta_filtro_cartera();" class="btn btn-primary btn-sm fa fa-archive"> CONSULTAR </button>
                <button type="button" class="btn btn-primary btn-sm fa fa-close" data-dismiss="modal"> CERRAR </button>
            </div>
        </div>
    </div>
</div>
                            
                           


<script src="dist/js/consultaxcatera.js"></script> 
<script src="dist/js/cobranzas.js"></script>
<script src="dist/js/jquery.datetimepicker.full.js"></script>
 <script src="dist/js/ValidaNumeros.js"></script>
<script type="text/javascript">
    $(function(){
$('#datetimepicker10').datetimepicker({   format:'Y-m-d' });  
$('#datetimepicker11').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker12').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker13').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker14').datetimepicker({   format:'Y-m-d' }); 
$('#datetimepicker15').datetimepicker({   format:'Y-m-d' }); 
$('#monto').validCampoFranz('0123456789.');
$('#monto1').validCampoFranz('0123456789.');
$('#pagos').validCampoFranz('0123456789.');
$('#pagos1').validCampoFranz('0123456789.');
$('#tvencido').validCampoFranz('0123456789.');
$('#tvencido1').validCampoFranz('0123456789.');
$('#vcompromiso').validCampoFranz('0123456789.');
$('#vcompromiso1').validCampoFranz('0123456789.');
$('#saldos').validCampoFranz('0123456789.');
$('#saldos1').validCampoFranz('0123456789.');
$('#dia_mora').validCampoFranz('0123456789');
$('#dia_mora1').validCampoFranz('0123456789');   

  });
     
</script>
</body>
</html>

