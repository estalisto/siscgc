<%-- 
    Document   : table_direccion
    Created on : 28/04/2017, 03:52:41 PM
    Author     : ViewSoft
--%>

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
    </head>
    <body>
        <!-- Content Wrapper. Contains page content -->
        <div>
             <div class="col-lg-4">
                                <dt>Telefonos: </dt>
                                <div  class="box table-responsive">  
                                    <table id="idAllTelefonos" class="table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th class="col-sm-2">Tipo</th>
                                                <th class="col-sm-2">Télefonos</th>
                                                <th class="col-sm-1">Llamar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${telefonos}" var="phone">               
                                                <tr  bgcolor="#E0ECF8">
                                                    <td><c:out value="${phone.getLcTiposTelefono().getNombreTipoTlf()}" /></td>                    
                                                    <td><c:out value="${phone.getTelefono()}" /></td>
                                                    <td><a  href="#" ><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span></a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
            </div>

        </div>
        <script src="dist/js/consultaxcatera.js"></script>  
        <script src="dist/js/cobranzas.js"></script> 
        <script>
             $(function () {
         $('#idAllTelefonos').DataTable( {
                    scrollY:        100,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                     info: false,
                    searching: false,
                    
                    columnDefs: [ {
                        orderable: false,

                        targets:   2
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 2, 'asc' ]]
                } ); 
            });        
      </script>    
    </body>
</html>
