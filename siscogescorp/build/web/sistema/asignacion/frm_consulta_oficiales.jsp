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
          <h3 class="box-title">Consulta de Oficiales</h3>
        </div> 
         
        <div class="col-lg-6"> 
            <div class="panel panel-footer margin">
                <div class="row">
                    <div class="col-lg-12">                    
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Por Gestionar:</label>
                             </div>
                             <div class="col-sm-6">
                                <select class="form-control" name="cartera" required="required">
                                <option value=''>Seleccionar Rango</option>
                                <option value='1' >0 - 200</option>
                                <option value='1' >201 - 400</option>
                                <option value='1' >401 - 600</option>
                                <option value='1' >601 - 800</option>
                                <option value='1' >801 - 1000</option>
                                <option value='1' > + 1001</option>
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
             <br><br><br><br><br>
             
            <div class="panel panel-default well-lg">
                
                        <div class="box box-body">
                                             
                            <table id="consulta_oficiales" class="table-hover table-striped" width="100%">
                                 
                                 <thead>
                                    <tr bgcolor='#FEC187'>    
                                       <th>Seleccionar</th>
                                       <th>Nombre</th>
                                       <th>Deudores Asignados</th>
                                       <th>Deudores Gestionados</th>                                       
                                       <th>Deudores por Gestionar</th>                                       
                                    </tr>  
                                 </thead>
                                <tbody>                                
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Jose</td>
                                  <td>5000</td>
                                  <td>2000</td>
                                  <td>3000</td>
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Francico</td>
                                  <td>6000</td>
                                  <td>3000</td>
                                  <td>2000</td>                              
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Jose</td>
                                  <td>5000</td>
                                  <td>2000</td>
                                  <td>3000</td>
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Francico</td>
                                  <td>6000</td>
                                  <td>3000</td>
                                  <td>2000</td>                              
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Jose</td>
                                  <td>5000</td>
                                  <td>2000</td>
                                  <td>3000</td>
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Francico</td>
                                  <td>6000</td>
                                  <td>3000</td>
                                  <td>2000</td>                              
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Jose</td>
                                  <td>5000</td>
                                  <td>2000</td>
                                  <td>3000</td>
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Francico</td>
                                  <td>6000</td>
                                  <td>3000</td>
                                  <td>2000</td>                              
                                </tr>
                                <tr>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Jose</td>
                                  <td>5000</td>
                                  <td>2000</td>
                                  <td>3000</td>
                                </tr>
                                <tr><center>                                 
                                  <td><input type="checkbox"></td>
                                  <td>Francico</td>
                                  <td>6000</td>
                                  <td>3000</td>
                                  <td>2000</td>                          
                                </tr>
                               </tbody>
                              </table>
                                       
                        </div> 
                        <div class=" form-group">
                                <center><a onclick="orden_registro()"><button id="oficial" type="submit" class="btn-lg btn-primary">  ASIGNAR CARTERA</button></a></center>
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
    $('#consulta_oficiales').DataTable( {
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

