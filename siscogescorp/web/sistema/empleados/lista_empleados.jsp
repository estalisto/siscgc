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
    
  <!-- Content Wrapper. Contains page content -->
  <div>
<ol class="Breadcrumb default">
        <div class="btn-group btn-breadcrumb">
            <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>                       
            <a href="#" class="active btn btn-success" onclick="personal(this);">EMPLEADOS</a>
            <a href="#" class="active btn btn-default" onclick="frm_empleados(this);">CREAR EMPLEADO</a>
        </div>                    
    </ol>
     
      
      <section class="content">
         
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Empleados de la Empresa</h3>
            </div>
        <!--<form name="form" action="empleados" method="post" class="well" id="data">-->      
            <!-- /.box-header -->
            <div class="box-body" style="overflow-x:scroll;" >
                <div class=" form-group">
                <a>FILTRAR: </a>&nbsp; &nbsp; 
                <input id="check_activo" name="check_activo" type="checkbox"  onclick="ListarFiltroActivo()"/> ACTIVOS &nbsp; &nbsp;   
                <input id="check_inactivo" name="check_inactivo" type="checkbox"  onclick="ListarFiltroInactivo()"/> INACTIVOS &nbsp; &nbsp;
                <!--input id="check_todos" name="check_todos" type="checkbox" onclick="ListarFiltro()"  /> TODOS </br-->
                </div>
                <table id="example1" class="table table-responsive table-bordered table-hover" style="display: block">
                <thead>
                <tr  bgcolor='#FEC187'>
                  <th class="hidden">ID</th>
                  <th>Agencia</th>
                  <th>Identificación</th>
                  <th>Nombres y Apellidos</th>
                  <th>Cargo </th>
                  <th>Profesión </th>
                 
                  <th>E-Mail </th>
                  <th>Estado/Civil </th>
                  <th>Lugar/Nacimiento </th>
                  <th>Fecha/Nacimiento </th>
                  <th>Télefonos </th>
                  <!--th>Fecha Ingreso </th-->
                  <th>Observación </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${empleados}" var="empleado">
                        <tr>
                          <td class="hidden"><c:out value="${empleado.getIdEmpleado()}" /> </td>
                          <td><c:out value="${empleado.getLcAgencia().getNombre()}" /> </td>
                          <td><c:out value="${empleado.getIdentificacion()}" /> </td>
                          <td><c:out value="${empleado.getNombres()}  ${empleado.getApellidos()}" /> </td>
                          <td><c:out value="${empleado.getLcCargos().getCargo()}" /> </td>
                          <td><c:out value="${empleado.getProfesion()}" /> </td>
                          <td><c:out value="${empleado.getEmail()}" /> </td>
                          <td><c:out value="${empleado.getLcEstadoCivil().getDescripcion()}" /> </td>
                          <td><c:out value="${empleado.getLugarNacimiento()}" /> </td>
                          <td><c:out value="${empleado.getFechaNacimiento()}" /> </td>
                          <td><c:out value="${empleado.getTelefonos()} - ${empleado.getCelular()}" /> </td> 
                          <!--td><c:out value="${empleado.getFechaCreacion()}" /> </td-->
                          <td><c:out value="${empleado.getObservacion()}" /> </td>                         
                          <td><a  onclick="ConnsultaDatosID(${empleado.getIdEmpleado()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletempleado(${empleado.getIdEmpleado()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>     
                        </tr>     
                    </c:forEach>  
                </tbody>
                </table>
                
                <table id="table_active" class="table table-bordered table-hover" style="display: none">
                <thead>
                <tr>
                  <th class="hidden">ID</th>
                  <th>Agencia</th>
                  <th>Identificación</th>
                  <th>Nombre</th>
                  <th>Cargo </th>
                  <th>Profesión </th>
                  <th>E-Mail </th>
                  <th>Estado/Civil </th>
                  <th>Lugar/Nacimiento </th>
                  <th>Fecha </th>
                  <th>Télefonos </th>
                  <th>Fecha Ingreso </th>
                  <th>Observación </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${Activos}" var="active">
                        <tr>
                          <td class="hidden"><c:out value="${active.getIdEmpleado()}" /> </td>
                          <td><c:out value="${active.getLcAgencia().getNombre()}" /> </td>
                          <td><c:out value="${active.getIdentificacion()}" /> </td>
                          <td><c:out value="${active.getNombres()}  ${active.getApellidos()}" /> </td>
                          <td><c:out value="${active.getLcCargos().getCargo()}" /> </td>
                          <td><c:out value="${active.getProfesion()}" /> </td>
                          <td><c:out value="${active.getEmail()}" /> </td>
                          <td><c:out value="${active.getLcEstadoCivil().getDescripcion()}" /> </td>
                          <td><c:out value="${active.getLugarNacimiento()}" /> </td>
                          <td><c:out value="${active.getFechaNacimiento()}" /> </td>
                          <td><c:out value="${active.getTelefonos()} - ${active.getCelular()}" /> </td> 
                          <td><c:out value="${active.getFechaCreacion()}" /> </td>
                          <td><c:out value="${active.getObservacion()}" /> </td>                         
                          <td><a  onclick="ConnsultaDatosID(${active.getIdEmpleado()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletempleado(${active.getIdEmpleado()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>     
                        </tr>     
                    </c:forEach>  
                </tbody>
                </table>  
                
                <table id="table_inactivos" class="table table-bordered table-hover" style="display: none">
                <thead>
                <tr>
                  <th class="hidden">ID</th>
                  <th>Agencia</th>
                  <th>Identificación</th>
                  <th>Nombre</th>
                  <th>Cargo </th>
                  <th>Profesión </th>
                  <th>E-Mail </th>
                  <th>Estado/Civil </th>
                  <th>Lugar/Nacimiento </th>
                  <th>Fecha </th>
                  <th>Télefonos </th>
                  <th>Fecha Ingreso </th>
                  <th>Observación </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${Inactivo}" var="inac">
                        <tr>
                          <td class="hidden"><c:out value="${inac.getIdEmpleado()}" /> </td>
                          <td><c:out value="${inac.getLcAgencia().getNombre()}" /> </td>
                          <td><c:out value="${inac.getIdentificacion()}" /> </td>
                          <td><c:out value="${inac.getNombres()}  ${inac.getApellidos()}" /> </td>
                          <td><c:out value="${inac.getLcCargos().getCargo()}" /> </td>
                          <td><c:out value="${inac.getProfesion()}" /> </td>
                          <td><c:out value="${inac.getEmail()}" /> </td>
                          <td><c:out value="${inac.getLcEstadoCivil().getDescripcion()}" /> </td>
                          <td><c:out value="${inac.getLugarNacimiento()}" /> </td>
                          <td><c:out value="${inac.getFechaNacimiento()}" /> </td>
                          <td><c:out value="${inac.getTelefonos()} - ${inac.getCelular()}" /> </td> 
                          <td><c:out value="${inac.getFechaCreacion()}" /> </td>
                          <td><c:out value="${inac.getObservacion()}" /> </td>                         
                          <td><a  onclick="ConnsultaDatosID(${inac.getIdEmpleado()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletempleado(${inac.getIdEmpleado()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>     
                        </tr>     
                    </c:forEach>  
                </tbody>
                </table>  
            </div>
            <!-- /.box-body -->
        <!--</form>-->    
        </div>
          <!-- /.box -->

          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
   
      
      
      
  </div>
  <!-- /.content-wrapper -->

  <script src="dist/js/empleados.js"></script>    
<!-- page script -->
<script>
  $(function () {
    
    $('#example1').DataTable({
      "paging": false,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": false,
      "autoWidth": false
    });

  });
</script>
</body>
</html>


</f:view>
