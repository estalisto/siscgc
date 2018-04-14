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

  <!-- Content Wrapper. Contains page content -->

          <!-- general form elements -->
          <br>
          
         
          
          
          <div class="col-sm-6"> 
          <div class="box box-danger">
            <div class="box-header with-border bg-yellow">
              <h3 class="box-title">GENERAR CARTAS</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->		
           
            <div class="panel panel-warning well">
             <form name="form" action="#" method="get" >
                                
                    <div class="row form-group">
                        <div class="col-sm-4">
                            <label>Cédula:</label>                            					
                            <input  maxlength="10" type="text" id="cedula" class="form-control input-sm" disabled>   
                        </div>
                        <div class="col-sm-8">
                            <label>Nombres y Apellidos: </label>
                            <input type="text" class="form-control input-sm"  disabled required="required" name="nombre">
                        </div>
                    </div>
                 
                    <div class="row form-group">
                        <div class="col-sm-4">
                            <label>Cartera: </label>  
                            <input type="text" class=" form-control input-sm" id="cartera1" disabled name="cartera1">
                        </div>
                        <div class="col-sm-4">
                            <label>Total Deuda: </label>
                            <input type="text" class="form-control input-sm" disabled name="total_deuda">
                        </div>
                        
                        <div class="col-sm-4">
                            <label>F. Último Pago: </label>
                            <input type="text" class=" form-control input-sm" id="ultimo_pago" disabled name="ultimo_pago"> 
                        </div>
                        
                    </div>
                            
                            
                <div class="row form-group">
                                       
                    <div class="col-sm-12">
                        <label>Nombre del Documento</label>
                        <select class="form-control input-sm" name="cliente" required="required" >
                            <option value='' >Seleccionar</option>
                            <option value='CARTA1' >Carta Solicitud</option>
                            <option value='CARTA2' >Carta a Deudor</option>
                            <option value='CARTA3' >Carta de Invitacion</option>
                            <option value='CARTA4' >Carta de Aviso</option>
                       </select>
                    </div>
                    
                </div>

                <div class="form-group">
                    <label>Observaciones: </label>
                    <textarea class="form-control input-sm" rows="2" placeholder="Observaciones ..."></textarea>				   
                </div>                     
                            
                
                 
                   <div id="test"></div>         
                            
            </form>
                 <div class="form-group">
                    <center>  
                        <a href="#" class="btn btn-primary fa fa-search"  data-toggle="modal" data-target="#myModal"> Buscar Cliente</a>
                        <a class="btn btn-primary fa fa-search-plus view-pdf" href="sistema/Documentos/LATICOBSA  S.pdf"> Ver</a>
                        <button class="btn btn-primary hidden-print fa fa-print" onclick="myFunction()"> Print</button>                   
                        <a href="#" class="btn btn-primary fa fa-close"> Cancelar</a>
                    </center>
                </div>
            </div>
                <div class="col-lg-4"></div>
            
               
                
          
    </div>
          
          </div>    
   
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Listar Cliente</h4>
        </div>
        <div class="modal-body">
       
                
                <div class="row form-group">
                    <div class="col-sm-3">
                        <label>Cartera: </label>  
                        <select class="form-control" name="cartera" required="required" >
                            <option value='' >Seleccionar</option>
                            <option value='SUC1' >Cartera 1</option>
                            <option value='SUC2' >Cartera 2</option>
                            <option value='SUC3' >Cartera 3</option>
                            <option value='SUC3' >Cartera 4</option>
                       </select>
                    </div>
                    <div class="col-sm-3">
                        <label>Cédula:</label>                            					
                        <input  maxlength="10" type="text" id="cedula" class="form-control  ">   
                           <!-- <div class="form-group has-error  "> <!--hidden-->
                               <!-- <span class="help-block">Usuario no se encuentra Registrado</span>
                            </div>  -->
                    </div>
                    
                    <div class="col-sm-4">
                        <label>Nombre y Apellido: </label>	
                        <input type="text" id="nombreapellido" class="form-control " >    
                    </div>
                    
                    <div class="form-group col-sm-1"><br>
                        <span><button type="button" class="btn-lg btn-primary fa fa-search"></button></span>
                    </div>
                </div>
               
                
            <div> 
              <table id="example1" class="table-striped table-hover">
                <thead>
                <tr>
                  <th class="col-sm-1"><center>Cédula</center></th>
                  <th class="col-sm-5"><center>Nombre y Apellido</center></th>
                  <th class="col-sm-1"><center>Cartera</center></th>
                  <th class="col-sm-4"><center>Tipo Cartera</center></th>                  
                  <th class="col-sm-1"><center>Accion</center></th>
                </tr>
                </thead>
                <tbody>                      
                        <tr>
                          <td><center>0912345678</center></td>
                          <td><center>David Farias</center></td>
                          <td><center>De Prati</center></td> 
                          <td><center>Cartera Alta</center></td>                                                                                    
                          <td><center><a  href="usuarios?accion=editar" ><span class="glyphicon glyphicon-share" aria-hidden="true"></span></a></center>
                          </td>         
                        </tr>
                        <tr>
                          <td><center>0912345678</center></td>
                          <td><center>David Farias</center></td>
                          <td><center>De Prati</center></td> 
                          <td><center>Cartera Baja</center></td>                                                                                     
                          <td><center><a  href="usuarios?accion=editar" ><span class="glyphicon glyphicon-share" aria-hidden="true"></span></a></center></td>         
                        </tr>
                        
                        
                </tbody>
              </table>
            </div> 
         
        
        
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  
  
  <!-- /.content-wrapper -->
 

<script src="dist/js/roles.js"></script>        
 <script src="dist/js/ValidaNumeros.js"></script>
     


<script>
    
$('#nombreapellido').validCampoFranz(' abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ0');
$('#cedula').validCampoFranz('0123456789');

    $('.view-pdf').on('click',function(){
        var pdf_link = $(this).attr('href');
        //var iframe = '<div class="iframe-container"><iframe src="'+pdf_link+'"></iframe></div>'
        //var iframe = '<object data="'+pdf_link+'" type="application/pdf"><embed src="'+pdf_link+'" type="application/pdf" /></object>'        
        var iframe = '<object type="application/pdf" data="'+pdf_link+'" width="100%" height="1000">No Support</object>'
        $.createModal({
            title:'Cobranzas',
            message: iframe,
            closeButton:true,
            scrollable:false
        });
        return false;   
    });    
    
    function myFunction() {
    window.print();
    }
    

     
$("#example1").DataTable(){
      "paging": false,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": false,
      "autoWidth": false
}); 
    

</script>
 

</body>
</html>


