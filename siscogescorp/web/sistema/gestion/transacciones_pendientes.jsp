<%-- 
    Document   : modulos_asignados
    Created on : 22/03/2017, 10:21:05 PM
    Author     : ViewSoft
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html>
    <head>
           
                 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    </head>
    <body>
        <!-- Content Wrapper. Contains page content -->
        <div>
            <section class="content ">

                    <div class="row">
                        <div id="asig_transaccion" class="col-xs-12" >
                          
                               <div  class="box table-responsive" style="overflow-y:scroll;" >
                                        <div style="overflow-x:scroll;">
                                    <table id="allTrxGestiones2"  class="table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                
                                                <th class="col-sm-1">Tipo Gestión</th>
                                                <th class="col-sm-1">Gestión</th>
                                                <th class="col-sm-5">Descripción</th>
                                                <th class="col-sm-1">Oficial</th> 
                                                <th class="col-sm-2">Fecha</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <%= request.getAttribute("GestionTRX") %>
                                                </tbody>                       
                                            </table>
                                        </div>
                                    </div>
                           
                        </div>
                    </div>
            </section>

        </div>
        <script src="dist/js/consultaxcatera.js"></script>  
        <script src="dist/js/cobranzas.js"></script> 
        <script>
        $(function () {
          $('#allTrxGestiones2').DataTable( {
                    scrollY:        300,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                     info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: true,

                        targets:   4
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 4, 'desc' ]]
                } );
            });
      </script>    
    </body>
</html>
