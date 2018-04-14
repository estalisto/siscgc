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
    <ol class="Breadcrumb default">
        <div class="btn-group btn-breadcrumb">
            <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>                       
            <a href="#" class="active btn btn-default" onclick="roles();">ROLES</a>
        </div>                    
    </ol>
    <div class="row">
        <div class="col-lg-6">             
            <div class="col-lg-12" >
                <div class="box box-danger">

                   <form name="form" action="crear_rol" method="get" class="pannel pannel-body">
                      <div class="box-body">
                              <!-- Color Picker -->
                              <div class="form-group">
                                <label>Empresa:</label>
                                <select class="form-control" name="empresa" id="empresa"  required="required"  >
                                  <c:if test="${sessionScope.NivelAccesoRolID==0}" >
                                      <option value="">Seleccionar Empresa</option>
                                  </c:if>
                                  <c:forEach items="${empresas}" var="empresa">
                                     <option value="<c:out value="${empresa.getIdEmpresa()}" />" ><c:out value="${empresa.getRazonSocial()}" /></option>
                                  </c:forEach>  

                                </select>
                              </div>


                              <div class="form-group">
                                <label>Nombre Rol:</label>
                                <input type="text" class="form-control" id="rol" style="text-transform:uppercase;"  placeholder="Ingrese Nombre Rol" maxlength="20" required="required" name="rol" >
                              </div>

                              <div class=" form-group">
                                  <!--<button   type="button" class="btn btn-primary"  title="Crea los roles de la empresa">Registrar</button>-->
                                  <input id="btncrearrol"  type="submit" value="Registrar" class="btn btn-sm btn-success"  title="Crea los roles de la empresa">
                              </div>

                      </div>
                  </form> 
                </div>
        </div>
        </div>
        <div class="col-lg-6">               
                <div>   
                    <div class="row">
                      <div class="col-xs-12">
                        <div class="box box-success">
                          <div class="box-header">
                            <h3 class="box-title">Roles de la Empresa</h3>
                          </div>
                          <!-- /.box-header -->
                          <div class="box-body">
                            <table id="example1" class="table table-bordered table-hover">
                              <thead>
                              <tr  bgcolor='#A0F070'>
                                <th class="hidden">ID</th>
                                <th>Nombre Rol</th>
                                <th>Empresa</th>
                                <th>Acción </th>

                              </tr>
                              </thead>
                              <tbody >
                                  <c:forEach items="${datos}" var="dato">
                                      <tr>
                                          <td class="hidden"><c:out value="${dato.getIdRol()}" /> </td>
                                        <td><c:out value="${dato.getDescripcion()}" /> </td>
                                        <td><c:out value="${dato.getLcEmpresa().getRazonSocial()}" /> </td>
                                        <td><a onclick="ConnsultaDatosID(${dato.getIdRol()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                            <a onclick="deleterol(${dato.getIdRol()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>     
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
                </div>
        </div>
    </div>
 
 

 

<script src="dist/js/roles.js"></script> 
<script>
  $(function () {
    $("#example2").DataTable();
    $('#example1').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false,
                "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
    });
  });
</script>
</body>
</html>



