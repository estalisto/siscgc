<%-- 
    Document   : lista_cartera
    Created on : 23/03/2017, 06:55:03 PM
    Author     : ViewSoft
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
  <div>

     
      
      <section class="content">
          <a onclick="frm_cartera();" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Cartera Clientes Deudores</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
                           <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th>ID</th>
                  <th>Empresa</th>
                  <th>Cliente</th>
                  <th>Nombre_Cartera</th>
                  <th>Observacion </th>
                  <th>Datos Adicionales</th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${deudor}" var="cartera">
                        <tr>
                          <td><c:out value="${cartera.getIdDeudor()}" /> </td>
                          <td><c:out value="${cartera.getLcEmpresa().getRazonSocial()}" /> </td>
                          <td><c:out value="${cartera.getLcClientes().getRazonSocial()}" /> </td>
                          <td><c:out value="${cartera.getNombreCartera()}" /> </td>
                          <td><c:out value="${cartera.getObservacion()}" /> </td>
                          <td><c:out value="${cartera.getDatosAdicional()}" /> </td>
                          <td><a onclick="ConnsultaDatosID(${cartera.getIdDeudor()})"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletecartera(${cartera.getIdDeudor()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>      
                        </tr>     
                    </c:forEach>  
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
   
      
      
      
  </div>
  <!-- /.content-wrapper -->

 <script src="dist/js/cartera.js"></script>    
<script>
  $(function () {
    $("#example2").DataTable();
    $('#example1').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
</body>
</html>

