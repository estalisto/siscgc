<%-- 
    Document   : newjsf
    Created on : 11-dic-2017, 23:39:20
    Author     : CIMA2015
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
             <style type="text/css">
input:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
textarea:enabled {
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
 table th {
    bgcolor: #E0ECF8;
}
table tr {
    bgcolor: #E0ECF8;
}

</style>
        </head>
        <body>
            <div id="div_crear_campania">
                <ol class="breadcrumb">
                        <li class="active"><a href="#" onclick="list_campanias();" >Lista Campaña</a></li>
                </ol>

                <div class="panel panel-warning well-sm">
                    <center><h3>CREAR PAQUETE DE NOTIFICACIONES DE SALDOS PENDIENTES</h3></center>
                    <hr>
                    <div>

                       <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalAgregar">+ Crear Nueva Notificaciones</button>
                    </div>

                      <br>
                    <div class="panel panel-warning well-sm">
                        <div id="mi_tabla">
                            <table id='consulta_campanias' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-3 text-left' style='color: #3c8dbc'>Descripción</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Creación</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Desde</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Hasta</th> <th align='center' class='col-sm-1 text-right'  style='color: #3c8dbc'>#Deudores</th><th align='center' class='col-sm-1 text-right'  style='color: #3c8dbc'>#Gestiones</th><th align='rigth' class='col-sm-1 text-right'  style='color: #3c8dbc'>Acción</th> </tr> </thead><tbody></tbody></table>
                        </div>
                    </div>

                </div>
            
                
            </div>
            <div id="div_crear_det_campania" hidden >
                <ol class="breadcrumb">
                        <li class="active"><a href="#" onclick="list_campanias()" >Lista Campaña</a></li>
                </ol>


                <div >
                    
                    
                    <section class="panel panel-default well-lg margin">
                        <center><h3>Agregar Datos a la Campaña</h3></center> 
                        <form>
                        <div class="form-row align-items-center">
                          
                          <div class="col-sm-2">
                            
                            <div class="input-group mb-2 mb-sm-0">
                              <div class="input-group-addon">Nº Campaña:</div>
                              <input type="text" class="form-control" id="num_campania" disable>
                            </div>
                          </div>
                          <div class="col-sm-5">
                            
                            <div class="input-group mb-2 mb-sm-0">
                              <div class="input-group-addon">Descripción:</div>
                              <textarea class="form-control " rows="1" id="text_descripcion" placeholder="Ingrese una descripción de la Campaña"></textarea>
                            </div>
                          </div>
                          <div class="col-auto">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#det_filtro"> + Cartera</button>
                          </div>
                        </div>
                      </form>
                    </section>
                    
                </div>
                
                
                
                
                <div class="panel panel-warning well-sm">                    
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" onclick="muestra_deudores()" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Deudores</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" id="profile-tab" data-toggle="tab" onclick="ocultar_deudores()" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Empleados</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" id="contact-tab" data-toggle="tab" onclick="ocultar_deudores()"  href="#contact" role="tab" aria-controls="contact" aria-selected="false">Asignación</a>
                        </li>
                      </ul>
                      <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active in" aria-expanded="true" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <div id="deudores">
                              <div><input id="sqlQueryInput"/></div>
                              <div><input id="order_by"/></div>
                              <div id="tabla_div" style="overflow-y: auto"></div> 
                              </div>  
                        </div>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div id="DivTbEmpelados">
                                                      
                                    </div>
                                    
                                </div>
                                <div class="col-lg-6">
                                    <div id="DivTbEmpeladosCampanias">
                                                      
                                    </div>
                                    
                                </div>

                            </div>




                        </div>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                            <div class="row">
                                <div class="col-lg-11">
                                    <form>
                                        <div class="form-row align-items-center">
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">Nº Empleado:</div>
                                              <input type="text" class="form-control" id="num_empleado" disable>
                                            </div>
                                          </div>
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">Nº Deudores:</div>
                                              <input type="text" class="form-control" id="num_deudores" disable>
                                            </div>
                                          </div>
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">T. Vencido:</div>
                                              <input type="text" class="form-control" id="tVenvidos" disable>
                                            </div>
                                          </div>
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">T. Pagado:</div>
                                              <input type="text" class="form-control" id="tPagados" disable>
                                            </div>
                                          </div>
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">T. Saldos:</div>
                                              <input type="text" class="form-control" id="tSaldos" disable>
                                            </div>
                                          </div>
                                          <div class="col-sm-2">
                                            <div class="input-group mb-2 mb-sm-0">
                                              <div class="input-group-addon">Proporción</div>
                                              <input type="text" class="form-control" id="cant_proporcion" disable>
                                            </div>
                                          </div>                                  
                                        </div>
                                      </form>
                                </div>
                                <div class="col-lg-1">
                                    <button type="button" class="btn btn-default" onclick="procesar_asignación()"  > Procesar
                                        <img id="id_loader" src="resources/dist/img/loader.gif" class="img-circle" alt="User Image" width="12" height="12" style="display: none">
                                    </button>
                                </div>
                              
                                
                            </div>
                             
                            <br><br>
                            <div id="DivTbEmpeladosAsigCampanias">
                                                      
                            </div>
                        </div>
                      </div>                               
                </div>
            </div>
        </body>
        <div id="mimodal"></div>
        <div>
             <div class="modal fade" id="modalAgregar" role="dialog">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Crear Notificación de Saldos Pendientes</h4>
                    </div>
                    <div class="modal-body">
                       <form class="form-horizontal">
                        <div class="form-group">
                          <label class="control-label col-sm-3">Descripción:</label>
                          <div class="col-sm-9">
                              <textarea class="form-control" rows="2" id="descripcion" placeholder="Ingrese una descripción de la Campaña"></textarea>
                          </div>
                        </div>
                           <div class="form-group">
                          <label class="control-label col-sm-3" >Tipo Documento</label>
                          <div class="col-sm-9">
                              <select  class="form-control" id="id_documento">
                                  
                              </select>
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-sm-offset-2 col-sm-10">
                              <button type="button" class="btn btn-primary" onclick="agregar_notificaciones_pago();">Crear Notificación de Pago</button>
                          </div>
                        </div>
                      </form> 
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
                            
                            <div class="col-lg-12">             
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
                                                                            <span id="escogecliente"></span>
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
                                                                           <!-- /*Codigo 007: Inicio cambio */
                                                                            /*Desarrollador: Jimmy Guaranda*/
                                                                            /*Objetivo: Nuevos filtros*/-->
                                                                            <div class="" >
                                                                                <dt>Clientes: </dt>
                                                                                <!--select class="form-control  input-sm" onchange=" getipoSubCartera();" name="tcartera" id="tcartera" disabled="true"></select--> 
                                                                                <select class="form-control  input-sm " name="tcartera" id="cartera" onchange="getipoCartera();getTiposGestiones();"  ></select>
                                                                            </div>
                                                                           <div class="" >
                                                                                <dt>Cartera: </dt>
                                                                                <!--select class="form-control  input-sm" onchange=" getipoSubCartera();" name="tcartera" id="tcartera" disabled="true"></select--> 
                                                                                <select class="form-control  input-sm " name="tcartera" id="tcartera" onchange="getipoSubCartera();"  disabled="true"></select>
                                                                            </div>
                                                                            <div class="" >
                                                                                    <dt>Sub-cartera: </dt>
                                                                                    <select class="form-control  input-sm " name="tsub_cartera" id="tsub_cartera" onchange="getipoSegmento();" disabled="true"></select>
                                                                            </div>
                                                                            <div class="">
                                                                                    <dt>Segmento: </dt>
                                                                                   <!--select class="form-control  input-sm" onchange="ConsultaSubsegmento();" name="tsegmento" id="tsegmento" ></select--> 
                                                                                   <select class="form-control  input-sm " name="tsegmento" id="tsegmento" onchange="getiposubSegmento();" disabled="true"></select>
                                                                            </div> 
                                                                            <div class="" >
                                                                                    <dt>Sub-Segmento: </dt>
                                                                                    <select class="form-control  input-sm " name="tsub_segmento" id="tsub_segmento" disabled="true"></select>
                                                                            </div>
                                                                           <!-- Fin cambio -->
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
        
      
        <script charset="UTF-8" src="dist/js/notificaciones_pago.js"></script>
        <script>
          consulta_notificaciones_pagos();
          consulta_mis_documentos();
          $('#finicio').datetimepicker({   format:'Y-m-d H:i' }); 
           $('#ffin').datetimepicker({   format:'Y-m-d H:i' }); 
           function muestra_deudores(){
               document.getElementById("tabla_div").style.display='block';
           }
           function ocultar_deudores(){
               document.getElementById("tabla_div").style.display='none';
           }
           
        </script>
    </html>

