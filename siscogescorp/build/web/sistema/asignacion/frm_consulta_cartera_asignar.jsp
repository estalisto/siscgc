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
  
 <!-- Content Wrapper. Contains page content -->
  <div> <!-- div inicio -->
      
      <br><div class="box box-danger">
        <div class="box-header with-border bg-yellow">
          <h3 class="box-title">Consultas de Cartera</h3>
        </div> 
          
        <div class="col-lg-6"> 
            <div class="panel panel-footer well-lg margin">
                <div class="row">
                    <div class="col-lg-12">                    
                        <div class="row">
                             <div class="col-sm-2">
                                <label>Cliente Cartera:</label>
                             </div>
                             <div class="col-sm-6">
                                <select class="form-control" name="cartera" required="required">
                                <option value=''>Seleccionar Cliente</option>
                                <option value='1' >DePrati</option>
                                <option value='1' >Cliente2</option>
                                <option value='1' >Cliente3</option>
                                <option value='1' >Cliente4</option>
                                </select>
                             </div>                                             
                            <div class="col-sm-2">                        
                               <button id="buscar" type="submit" class="btn btn-primary btn-lg fa fa-search">BUSCAR</button>                       
                            </div>
                        </div>
                    </div>                  
                </div>
            </div>
        </div>
              
            <div class="panel panel-default well-lg">
                <div class="row">
                    <div class="col-lg-12">   
                        <div class="box">
                        
                        <div  class="box-body" style="overflow-y:scroll;"  >
                           
                             <table id="consulta_cartera" class="table-hover table-striped" cellspacing="0" width="100%">
                                 
                                 <thead>
                                    <tr>                                  
                                       <th>Seleccionar</th>
                                       <th>Cartera</th>
                                       <th>Cantidad</th>
                                       <th>Monto</th>
                                       <th>Asignado</th>
                                       <th>Por Gestionar</th>                                       
                                    </tr>  
                                 </thead>
                                <tbody>                                
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>                                  
                                </tr>
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                                <tr>                                  
                                  <td><input type="checkbox"></td>
                                  <td>De Prati</td>
                                  <td>5000</td>
                                  <td>125000</td>
                                  <td>200</td>
                                  <td>300</td>
                                </tr>
                               </tbody>
                              </table>                           
                        </div> 
                        
                            <div class=" form-group">
                                <center><a onclick="consulta_oficiales()"><button id="asignar" type="submit" class="btn-lg btn-primary">  ASIGNAR EMPLEADOS</button></a></center>
                            </div> 
                        </div>
                         
                    </div>
                
                           
                   
                    
                </div>
            </div>
      </div>
                                								 
                        

   </div>   <!-- div de inicio -->
   
   



<!-- page script -->
<script>
    
   $(document).ready(function() {
    $('#consulta_cartera').DataTable( {
        scrollY:        100,
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        fixedColumns:   {
            leftColumns: 2
        },
        columnDefs: [ {
            orderable: false,
            
            targets:   3
        } ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        order: [[ 2, 'asc' ]]
    } );
} ); 
    
</script>
</body>
</html>

