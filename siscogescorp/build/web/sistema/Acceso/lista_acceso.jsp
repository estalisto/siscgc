<%-- 
    Document   : lista_acceso
    Created on : 09/03/2017, 01:33:31 PM
    Author     : ViewSoft
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="../header.jsp" flush="true" />
    </aside>
 
        <!-- Main content -->
<div class="content-wrapper">
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        <a href="accesos?accion=agregar" class="btn btn-success">  Agregar +</a>
     
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Asignar Módulos del Sistema por Nivel de Acceso</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example3" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th>Empresa</th>
                  <th>Módulos</th>
                  <th>Fecha Asignada</th>
                  <th>Estado</th>
                  <th><center>Opción</center></th>
                </tr>
                </thead>
                <tbody>
                
                <tr>
                  <td>Laticobsa</td>
                  <td><b>SEGURIDAD</b></td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td>                
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Administrar Roles</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Asignar Módulos a Roles</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Cambio Clave</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Recuperación de Contraseña</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td><b>ADMINISTRACIÓN</b></td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Empresa</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Cargos</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Personal</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Usuarios</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
                <tr>
                  <td>Laticobsa</td>
                  <td> > Parámetros</td>
                  <td>2017-01-05</td>
                  <td>Activo</td>
                  <td><center><input type="checkbox"></center></td> 
                </tr>
                
             </div>
                                
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
    <!-- /.content -->
</div>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.0
    </div>
        <strong> <a href="http://softwarefacturyec.com">SoftwareFactury</a>&copy; 2017. </strong>  Laticobsa - Todos los derechos reservados.

  </footer>

 
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- page script -->
<script>
  $(function () {
    $("#example2").DataTable();
    $('#example1').DataTable({
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
