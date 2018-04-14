<%-- 
    Document   : frm_preasignacion_simple
    Created on : 20/04/2017, 11:35:30
    Author     : Stalyn Granda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
       
    </head>
    <body>
                <br><div class="box box-danger">
                    <div class="box-header with-border bg-yellow">
                    <h3 class="box-title">Detalle de Preasignación</h3>
                </div>
            
            
                <div class="panel">  
                                         
                        <table id="proceso_asignar" class="table-hover table-striped" width="100%">
                        
                        <thead>
                            <tr>                                
                                <th>Nombre</th>
                                <th>Monto</th>
                                <th>Fecha Ingreso</th>
                                <th>Tipo Crédito</th>
                                <th>XXXXX</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>ATiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>BTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>CTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>DTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>ETiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>FTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>GTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>4</td>
                            </tr>
                            <tr>
                                <td>HTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>4</td>
                            </tr>
                            <tr>
                                <td>ITiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>5</td>
                            </tr>
                            <tr>
                                <td>JTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>5</td>
                            </tr>
                            <tr>
                                <td>KTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>LTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>Directo</td>
                                <td>1</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td><strong>TOTALES</strong></td>
                            <td><strong>300000</strong></td>
                                <td></td>
                                <td></td>
                                <td></td>
                        </tfoot>
                    </table>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary"> OK </button>

                            <a href="#" class="btn btn-primary">Cancelar</a>

                        </div>
                            
                    
                    </div>
                   
  <script>
    
$(document).ready(function() {
    $('#proceso_asignar').DataTable( {
        scrollY:        100,
        scrollX:        false,
        scrollCollapse: false,
        paging:         false,
        
        
        
     } );
} ); 
    
            </script>
     </body>
    
</html>
          
            
            
  
