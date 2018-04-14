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
              <h3 class="box-title">Registra Cargos</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
             <form name="form" action="cargos" method="get" class="well">
            
            
                  
                    <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">
                    </div>
                  
                 <div class="form-group">
                  <label>Empresa:</label>
                 
                  <select class="form-control" name="empresa" required="required" id="empresa">
                    <option value=''>Seleccionar Empresa</option>
                      <c:forEach items="${empresas}" var="empresa">
                       <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                    </c:forEach>                 
                  
                    </select>
                </div>
                    <div class="form-group">
                          <select class="form-control" name="empresa" id="empresa2"  required="required" style="display: none">
                             <c:forEach items="${empresao}" var="empresas">
                                 <option value="<c:out value="${empresas.getIdEmpresa()}" />" ><c:out value="${empresas.getRazonSocial()}" /></option>
                             </c:forEach>  
                            
                          </select>
                        </div>
                               
                 <div class="form-group">
                  <label for="exampleInputCargo1">Cargo</label>
                  <input type="text" class="form-control" placeholder="Ingrese el Cargo " required="required" name="cargo" id="cargo"> 
                </div>
                 
                 <div class="form-group">
                    <label>Observacion:</label>
                    <textarea id="observacion" class="form-control" rows="3" name="observacion" required="required"></textarea>
                </div>

              <div class="form-group">
                <input id="btncrearcargo" type="submit" value="Registrar" class="btn btn-primary"  title="Crea Cargo">
                                      
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


