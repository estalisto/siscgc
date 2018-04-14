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

    </head><!-- Content Wrapper. Contains page content -->
<body>
  <div>

      <br >
      <ol class="breadcrumb">
            <li><a onclick="cargos(this)">Lista Cargos</a></li>
            <li class="active"><a onclick="frm_cargos(this)">Registra Cargos</a></li>
             
      </ol>
      
      <div class="col-md-6">


          <!-- general form elements -->
          <div class="box box-danger">
            <div class="box-header with-border bg-yellow">
              <h3 class="box-title">Actualizar Cargos</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
             <form name="form" action="cargos" method="get" class="well">
                 
                 <c:forEach items="${cargos}" var="cargo">
            
                  
                <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">
                </div>
                
                <div class="col-xs-3 hidden">
                    <div class="form-group">
                        <label>Código:</label>
                        <input type="text" class="form-control" id="idcargo" name="idcargo" value="<c:out value="${cargo.getIdCargo()}" />" required="required">
                    </div> 
                </div>
                 <div class="form-group">
                  <label>Empresa:</label>
                 
                  <select class="form-control" name="empresa" required="required" id="empresa" disabled="true">
                    <option value="<c:out value="${cargo.getLcEmpresa().getIdEmpresa()}" />"><c:out value="${cargo.getLcEmpresa().getRazonSocial()}" /></option>
                      <c:forEach items="${empresas}" var="empresa">
                       <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                    </c:forEach>                 
                  
                    </select>
                </div>
                <div class="form-group">
                  <label for="exampleInputCargo1">Cargo</label>
                  <input type="text" class="form-control" placeholder="Ingrese el Cargo " required="required" name="cargo" id="cargo" value="<c:out value="${cargo.getCargo()}" />"> 
                </div>
                 
                <div class="form-group">
                    <label>Observacion:</label>
                    <textarea id="observacion" class="form-control" rows="3" name="observacion" required="required"><c:out value="${cargo.getObservacion()}" /></textarea>
                </div>
                  </c:forEach> 
              <div class="form-group">
                <input id="btnactcargo" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Cargos">
                                      
              </div>
            </form>
          </div>
          <!-- /.box -->

        </div>
      </div>
  
  <!-- /.content-wrapper -->

  
<script src="dist/js/cargos.js"></script>
</body>
</html>


