<%-- 
    Document   : frm_orden_registro
    Created on : 20/04/2017, 11:35:09
    Author     : Stalyn Granda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        
        <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">
        <link href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.dataTables.min.css" rel="stylesheet" media="screen"> 
        <link href="https://cdn.datatables.net/select/1.2.2/css/select.dataTables.min.css" rel="stylesheet" media="screen">
       
    </head>
    <body>
        
     
        
        <div>
            
            <br><div class="box box-danger">
                <div class="box-header with-border bg-yellow">
                  <h3 class="box-title">Orden de Registro</h3>
                </div>
           
                <div class="panel">  
                                         
                        <table id="orden_registro" class="table-hover table-striped " width="100%">
                        
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
                                <td>22000</td>
                                <td>1/01/2017</td>
                                <td>xDirecto</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>BTiger</td>
                                <td>23000</td>
                                <td>1/01/2017</td>
                                <td>yDirecto</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>CTiger</td>
                                <td>24000</td>
                                <td>1/01/2017</td>
                                <td>zDirecto</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>DTiger</td>
                                <td>25000</td>
                                <td>1/01/2017</td>
                                <td>wDirecto</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>ETiger</td>
                                <td>2600</td>
                                <td>1/01/2017</td>
                                <td>tDirecto</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>FTiger</td>
                                <td>27000</td>
                                <td>1/01/2017</td>
                                <td>hDirecto</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>GTiger</td>
                                <td>28000</td>
                                <td>1/01/2017</td>
                                <td>kDirecto</td>
                                <td>4</td>
                            </tr>
                            <tr>
                                <td>HTiger</td>
                                <td>29000</td>
                                <td>1/01/2017</td>
                                <td>iDirecto</td>
                                <td>4</td>
                            </tr>
                            <tr>
                                <td>ITiger</td>
                                <td>21000</td>
                                <td>1/01/2017</td>
                                <td>lDirecto</td>
                                <td>5</td>
                            </tr>
                            <tr>
                                <td>JTiger</td>
                                <td>20000</td>
                                <td>1/01/2017</td>
                                <td>uDirecto</td>
                                <td>5</td>
                            </tr>
                            <tr>
                                <td>KTiger</td>
                                <td>21000</td>
                                <td>1/01/2017</td>
                                <td>iDirecto</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>LTiger</td>
                                <td>22000</td>
                                <td>1/01/2017</td>
                                <td>gIDirecto</td>
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
             
                            <div class=" form-group">
                                <center><a onclick="proceso_asignar()"><button id="asignar" type="submit" class="btn-lg btn-primary"> PROCESO DE ASIGNACIÓN </button></a></center>
                            </div> 
                    
                    </div>
                   
            </div> 
            
                <div id="preasig_simple" >        
                
                </div>
         
        </div>
                       

            <script>
    
$(document).ready(function() {
    $('#orden_registro').DataTable( {
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
