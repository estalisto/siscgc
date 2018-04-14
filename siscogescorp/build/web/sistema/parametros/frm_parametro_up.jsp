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
</head>
 <body> 
  <div>

      <br >
      <ol class="breadcrumb">
          <li><a href="#" onclick="parametros();">Parámetros</a></li>
          <li class="active"><a href="#" onclick="frm_parametros();">Registra Parámetros</a></li>
             
      </ol>
      
      <div class="col-md-6">
          <div class="box box-danger">
            <div class="box-header with-border bg-yellow"   >
              <h3 class="box-title" >Actualizar Parámetros</h3>
            </div>
              <form name="form" action="parametros" method="get" class="well">
                <c:forEach items="${agent}" var="paro">
                    <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">
                    </div>
                    
                    <div class="col-xs-3 hidden">
                        <div class="form-group">
                            <label>id:</label>
                            <input type="text" class="form-control" id="id" name="id" value="<c:out value="${paro.getId()}" />">
                        </div> 
                    </div>
                    <div class="form-group">
                  <label>Código:</label>
                  <input type="text" class="form-control"  placeholder="Ingrese Codigo de Cargo " required="required" name="codigo" id="codigo" value="<c:out value="${paro.getIdParametro()}"/>" > 
                </div> 
                  
                    <div class="form-group">
                  <label>Parámetro:</label>
                  <input type="text" class="form-control"  placeholder="Ingrese el Cargo " required="required" name="nombre" id="nombre" value="<c:out value="${paro.getParametro()}"/>"> 
                </div> 
                
                    <div class="form-group">
                  <label>Valor:</label>
                  <textarea class="form-control" rows="1"  placeholder="Ingrese los valores del Parámetro" name="valor" id="valor" value="<c:out value="${paro.getValor()}"/>"><c:out value="${paro.getValor()}"/></textarea>
                 
                </div> 
                  <div class="form-group">
                  <label>Descripcion:</label>
                  <textarea class="form-control" rows="3"  placeholder="Ingrese una descripcion del Parámetro" name="descripcion" id="descripcion" value="<c:out value="${paro.getDescripcion()}"/>"><c:out value="${paro.getDescripcion()}"/></textarea>
                 
                </div>
                    <div class="form-group">
                <input id="btnactparametro" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Parametros">
              </div>
                </c:forEach> 
            </form>
              <!-- /.box-body -->
          </div>

          <!-- /.box -->

        </div>
      
  </div>
  <!-- /.content-wrapper -->

  
<!-- ./wrapper -->
<script src="dist/js/parametros.js"></script>     
</body>
</html>


