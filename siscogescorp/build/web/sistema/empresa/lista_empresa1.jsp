<%-- 
    Document   : roles
    Created on : 27-feb-2017, 11:50:46
    Author     : CIMA2015
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>LatiCobsa S.A.</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" type="image/png" href="dist/img/favicon.png"/>

  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- jvectormap 
  <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">-->
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
 
  </head>

<body>
<div>



     
      
      <section class="content">
          <a href="#" onclick="frm_empresa(this)" class="btn btn-success"  >Agregar +</a>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Lista de Empresas</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;">
              <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th>ID</th>
                  <th>Identificación</th>
                  <th>Nombre</th>
                  <th>Dirección </th>
                  <th>Télefonos </th>
                  <th>E-Mail </th>
                  <th>Fecha Ingreso </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${empresas}" var="empresa">
                        <tr>
                          <td><c:out value="${empresa.getIdEmpresa()}" /> </td>
                          <td><c:out value="${empresa.getIdentificacion()}" /> </td>
                          <td><c:out value="${empresa.getRazonSocial()}" /> </td>
                          <td><c:out value="${empresa.getDireccion()}" /> </td>
                          <td><c:out value="${empresa.getTelefonos()}" /> </td>
                          <td><c:out value="${empresa.getEmail()}" /> </td>
                          <td><c:out value="${empresa.getFechaCreacion()}" /> </td>                 
                          <td><a  href="empleados?accion=editar" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a href="empleados?accion=eliminar"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></td></a>      
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
   
      
      
      

  <!-- /.content-wrapper -->




</div>

     <script src="dist/js/empresa.js"></script>    


<script>
  $(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
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


</f:view>

