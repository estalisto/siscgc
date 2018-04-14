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
            <a href="#" class=" btn btn-default" onclick="frm_roles(this)" >CREAR ROL</a>
        </div>                    
    </ol>
      
      <div class="col-md-5">
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

