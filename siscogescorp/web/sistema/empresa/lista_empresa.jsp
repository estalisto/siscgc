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
          <a href="#" onclick="frm_empresa(this)" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-lg-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Lista de Empresas</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
                 <!--div class=" form-group">
                     <a><label/>FILTRAR: </a></br> </br>
                <input name="cbipeliculas" type="checkbox" /> ACTIVOS &nbsp; &nbsp;   
                <input name="cbilibros" type="checkbox"  /> INACTIVOS &nbsp; &nbsp;
                <input name="cbilibros" type="checkbox"  /> TODOS </br>
                 </div-->
              <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th class="hidden">ID</th>
                  <th>Identificación</th>
                  <th>Nombre</th>
                  <th>Dirección </th>
                  <th>Télefonos </th>
                  <th>E-Mail </th>
                  <th>Fecha Ingreso </th>
                  <th>Estado</th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${empresas}" var="empresa">
                        <tr>
                          <td class="hidden"><c:out value="${empresa.getIdEmpresa()}" /> </td>
                          <td><c:out value="${empresa.getIdentificacion()}" /> </td>
                          <td><c:out value="${empresa.getRazonSocial()}" /> </td>
                          <td><c:out value="${empresa.getDireccion()}" /> </td>
                          <td><c:out value="${empresa.getTelefonos()} - ${empresa.getTelfonos2()}" /> </td>
                          <td><c:out value="${empresa.getEmail()}" /> </td>
                          <td><c:out value="${empresa.getFechaCreacion()}" /> </td>                 
                          <td><c:out value="${empresa.getEstado()}" /> </td>
                          <td><a  onclick="ConnsultaDatosID(${empresa.getIdEmpresa()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletempresa(${empresa.getIdEmpresa()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>    
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
  
  <script src="dist/js/empresa.js"></script>    
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
