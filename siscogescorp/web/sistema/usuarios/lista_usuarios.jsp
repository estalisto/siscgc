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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
</head>
<body>
    <ol class="Breadcrumb default">
        <div class="btn-group btn-breadcrumb">
            <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>                       
            <a href="#" class=" btn btn-success" onclick="usuarios(this);">USUARIOS</a>
            <a href="#" class="active btn btn-default" onclick="frm_usuario(this);">CREAR USUARIO</a>
        </div>                    
    </ol>
<div> 
      <section class="content">
          
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Usuarios de la Empresa</h3>
            </div>
            <!-- /.box-header -->
            <div id="tbusuarios" class="box-body" style="overflow-x:scroll;">
              <table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr   bgcolor='#FEC187'>
                  <th class="hidden">ID</th>
                  <th>Empresa</th>
                  <th>Rol</th>
                  <th>Identificacion Empleados</th>                  
                  <th>Usuario </th>
                  <th>Fecha Ingreso </th>
                  <th>Observación </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>

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
<script src="dist/js/usuario.js"></script>   
<script>
 /* $(function () {
    $("#example2").DataTable();
    $('#example1').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });*/
     consulta_usuarios();
</script>
</body>
</html>



