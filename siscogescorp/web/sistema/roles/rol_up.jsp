<%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : CIMA2015
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


    <!DOCTYPE html>
<html>
    <head></head>
  <!-- Content Wrapper. Contains page content -->
  <body>
  <div>
      <ol class="Breadcrumb default">
        <div class="btn-group btn-breadcrumb">
            <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>                       
            <a href="#" class="active btn btn-default" onclick="roles();">ROLES</a>
        </div>                    
    </ol>
      
      <div class="col-md-5">
          <div class="box box-danger">
            <div class="box-header with-border bg-default"   >
              <h3 class="box-title" >Actualizar Roles</h3>
            </div>
             <form name="form" action="roles" method="get" class="pannel pannel-body" >
                <c:forEach items="${roles}" var="rol">
                                                                     
                                           
                        <div class="box-body">

                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">
                            </div>
                            
                            <div class="col-xs-3 hidden">
                                    <div class="form-group">
                                        <label>Código:</label>
                                        <input type="text" class="form-control" id="idrol" name="idrol" value="<c:out value="${rol.getIdRol()}" />" required="required">
                                    </div> 
                                </div>
                        <!-- Color Picker -->
                        <div class="form-group">
                          <label>Empresa:</label>
                          <select class="form-control" name="empresa" id="empresa"  required="required" disabled="true">
                            <option value="<c:out value="${rol.getLcEmpresa().getIdEmpresa()}" />" ><c:out value="${rol.getLcEmpresa().getRazonSocial()}" /></option>
                             <c:forEach items="${empresas}" var="empresa">
                                 <option value="<c:out value="${empresa.getIdEmpresa()}" />" ><c:out value="${empresa.getRazonSocial()}" /></option>
                             </c:forEach>  
                            
                          </select>
                        </div>
                        
                        <div class="form-group hidden">
                          <label>Cargo</label>
                          <select class="form-control" name="cargo" required="required">
                               <option value="" >Seleccionar Cargo</option>
                            <c:forEach items="${cargos}" var="cargo">
                                 <option value="<c:out value="${cargo.getIdCargo()}" />" ><c:out value="${cargo.getCargo()}" /></option>
                             </c:forEach>  
                          </select>
                        </div>
                        
                        <div class="form-group">
                          <label>Nombre Rol:</label>
                          <input type="text" class="form-control" id="rol" style="text-transform:uppercase;" value="<c:out value="${rol.getDescripcion()}" />"  placeholder="Ingrese Nombre Rol" maxlength="20" required="required" name="rol" >
                        </div>
                        
                        <div class=" form-group">
                            <!--<button   type="button" class="btn btn-primary"  title="Crea los roles de la empresa">Registrar</button>-->
                            <input id="btnactrol"  type="submit" value="Actualizar" class="btn btn-primary"  title="Actualiza los roles de la empresa">
                        </div>

                </div>
                </c:forEach>
            </form> 
             
              <!-- /.box-body -->
          </div>

          <!-- /.box -->

        </div>
      
  </div>
  <!-- /.content-wrapper -->


<script src="dist/js/roles.js"></script>    
<!-- ./wrapper -->

</body>
</html>

