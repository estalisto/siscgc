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
  <!-- Content Wrapper. Contains page content -->
  <div>

     
      
      <section class="content">
          <a href="#" onclick="frm_clientes();" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Nuestros Clientes</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
                           <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                    <th class="hidden">ID</th>
                  <th>Empresa</th>
                  <th>Identificacion</th>
                  <th>Razon social</th>
                  <th>Direccion </th>
                  <th>Contacto</th>
                  <th>E-Mail</th>
                  <th>Télefonos </th>
                  <th>Fecha Ingreso </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${clientes}" var="cliente">
                        <tr>
                          <td class="hidden"><c:out value="${cliente.getIdCliente()}" /> </td>
                          <td><c:out value="${cliente.getLcEmpresa().getRazonSocial()}" /> </td>
                          <td><c:out value="${cliente.getIdentificacion()}" /> </td>
                          <td><c:out value="${cliente.getRazonSocial()}" /> </td>
                          <td><c:out value="${cliente.getDireccion()}" /> </td>
                          <td><c:out value="${cliente.getContacto()}" /> </td>
                          <td><c:out value="${cliente.getEmail()}" /> </td>
                          <td><c:out value="${cliente.getTelefono()}" /> </td>
                          <td><c:out value="${cliente.getFechaCreacion()}" /> </td>
                          <td><a  onclick="ConnsultaDatosID(${cliente.getIdCliente()})"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletecliente(${cliente.getIdCliente()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>      
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

 <script src="dist/js/clientes.js"></script>    
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

