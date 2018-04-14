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
                                          
                        <div>  
                        <div class="row">
                            <div  class="col-xs-12" >
                                <form name="form1" action="consultacartera" method="get">                                
                                 <div>                        
                                     <div  class="box-body table-responsive" style="overflow-y:scroll;"  >

                                         <table id="consul_cartera" class="table table-responsive table-hover">
                                             <thead>
                                                 <tr>                                  
                                                     <th>ID</th>
                                                     <th>Nombres</th>
                                                     <th>Identificación</th>
                                                     <th>Cliente</th>
                                                     <!--th>Referecia</th-->
                                                     <th>Tipo Crédito</th>
                                                     <th>Status</th>
                                                 </tr>  
                                             </thead>
                                             <tbody>                                
                                                 <c:forEach items="${datas}" var="data">
                                                     <tr onclick="cobranzas(${data.getLcClientes().getIdCliente()},${data.getIdentificacion()});">
                                                         <td><c:out value="${data.getIdDatosDeudor()}" /> </td>
                                                         <td><c:out value="${data.getNombres()} - ${data.getApellidos()} " /> </td>
                                                         <td><c:out value="${data.getIdentificacion()}" /> </td>
                                                         <td><c:out value="${data.getLcClientes().getRazonSocial()}" /> </td>
                                                         <td><c:out value="${data.getLcTipoCredito().getDescripcion()}" /> </td>
                                                         <td><c:out value="${data.getLcEstatus().getDescripcion()}" /> </td>              
                                                     </tr>     
                                                 </c:forEach> 

                                             </tbody>
                                         </table>

                                     </div> 

                                 </div>
                                </form>
                            </div>
                        </div>
                        </div>                  
                </div>
               <script src="dist/js/consultaxcatera.js"></script>  
               <script src="dist/js/cobranzas.js"></script>
          <script>
  $(function () {
    $("#example2").DataTable();
    $('#consul_cartera').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
        </body>
    </html>
