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
<head>
    
  <!-- Content Wrapper. Contains page content -->
  <div>

<ol class="breadcrumb">
          <li><a href="#" onclick="usuarios();">Usuarios</a></li>
          <li class="active"><a href="#" onclick="reset_clave();" >Recuperar Contraseña</a></li>
      </ol>
      
      <div class="col-md-6">

       

          <div class="box box-danger">
            <div class="box-header with-border bg-yellow"   >
              <h3 class="box-title" >Recuperar Contraseña</h3>
            </div>
               <form name="form" action="#" method="get" class="well">
                        <div class="form-group">
                          <label>Empresa:</label>

                           <select class="form-control" name="empresa"  required="required">
                            <option value="">Seleccionar</option>
                             <c:forEach items="${empresas}" var="empresa">
                                 <option value="<c:out value="${empresa.getIdEmpresa()}" />" ><c:out value="${empresa.getRazonSocial()}" /></option>
                             </c:forEach>  
                            
                          </select>
                        </div>
                
                        <div class="form-group">
                          <label for="exampleInputUsuario1">Usuario: </label>
                          <input type="text" class="form-control" id="exampleInputUsuario1" placeholder="Ingrese el Usuario " required="required" name="usuario">                                
                        </div>
                        
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Generar</button>
                            <a href="#" class="btn btn-primary">  Cancelar</a>
                        </div>             
                  
                  
            </form>
              <!-- /.box-body -->
          </div>
                 
          <!-- /.box -->

        </div>
      
  </div>
  <!-- /.content-wrapper -->

</body>
</html>


