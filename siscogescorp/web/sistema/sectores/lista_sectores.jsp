<%-- 
    Document   : roles
    Created on : 27-feb-2017, 11:50:46
    Author     : CIMA2015
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


    <!DOCTYPE html>
<html>
<head>

  <div>
      
      <section class="content">
          <a href="#" onclick="frm_zona(this)" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-lg-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Lista de Zonas</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
                 
              <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                    <th class="hidden">ID</th>
                  <th>Empresa</th>
                  <th>Pais</th>
                  <th>Provincia </th>
                  <th>Ciudad </th>
                  <th>Nombre </th>
                  <th>Descripcion </th>
                  <th>Fecha Ingreso </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${zonas}" var="zona">
                        <tr>
                          <td class="hidden"><c:out value="${zona.getIdZona()}" /> </td>
                          <td><c:out value="${zona.getLcEmpresa().getRazonSocial()}" /> </td>
                          <td><c:out value="${zona.getLcPais().getPais()}" /> </td>
                          <td><c:out value="${zona.getLcProvincia().getProvincia()}" /> </td>
                          <td><c:out value="${zona.getLcCiudad().getCiudad()}" /> </td>
                          <td><c:out value="${zona.getNombreZona()}" /> </td>
                          <td><c:out value="${zona.getSectorDescripcion()}" /> </td>                 
                          <td><c:out value="${zona.getFechaCreacion()}" /> </td>
                          <td><a onclick="ConnsultaDatosID(${zona.getIdZona()})"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a  onclick="deletezona(${zona.getIdZona()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>      
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
  
  <script src="dist/js/zona.js"></script>    
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
