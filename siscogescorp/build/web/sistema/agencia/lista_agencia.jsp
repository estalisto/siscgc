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
</head>
<body>
  <div>
      
      <section class="content">
          <a href="#" onclick="frm_agencia(this)" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-lg-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Lista de Agencias</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
                 
              <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th class="hidden">ID</th>
                  <th>Empresa</th>
                  <th>Nombre</th>
                  <th>Opcion </th>
                  <th>Direccion </th>
                  <th>Telefonos </th>
                  <th>Celular </th>
                  <th>Email </th>
                  <th>Fecha Ingreso </th>
                  <th>Estado</th>
                  <th>Acción </th>
                </tr>
                </thead>
                
                    
                    <c:forEach items="${agencias}" var="agencia">
                        <tr>
                          <td class="hidden"><c:out value="${agencia.getIdAgencia()}" /> </td>
                          <td><c:out value="${agencia.getLcEmpresa().getRazonSocial()}" /> </td>
                          <td><c:out value="${agencia.getNombre()}" /> </td>
                          <td><c:out value="${agencia.getOpcion()}" /> </td>
                          <td><c:out value="${agencia.getDireccion()}" /> </td>
                          <td><c:out value="${agencia.getTelefono()}" /> </td>
                          <td><c:out value="${agencia.getCelular()}" /> </td>                 
                          <td><c:out value="${agencia.getEmail()}" /> </td>
                          <td><c:out value="${agencia.getFechaCreacion()}" /> </td>
                          <td><c:out value="${agencia.getEstado()}" /> </td>
                          <td><a  onclick="ConnsultaDatosID(${agencia.getIdAgencia()})"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a href="javascript: void(0);" onclick="deleteagencia(${agencia.getIdAgencia()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>      
                        </tr>     
                    </c:forEach>  
                

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
  
  <script src="dist/js/agencia.js"></script>    
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
