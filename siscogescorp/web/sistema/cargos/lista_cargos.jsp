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
            <a href="#" class="active btn btn-success" onclick="cargos(this);">CARGOS</a>
        </div>                    
    </ol>
      
      <section class="content">
     
      <div class="row">
          
          <div class="col-lg-6">
                <div class="box box-danger">
                    <form name="form" action="cargos" method="get" class="pannel pannel-body">   
                        <div class="box-body">
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
                        </div>
          </div>
        <div class="col-lg-6">
           <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr  bgcolor='#A0F070'>
                  <th>Id</th>
                  <th>Cargos</th>
                  <th>Empresa</th>
                  <th>Acción</th>
                  
                </tr>
                </thead>
                <tbody>
                      <c:forEach items="${cargos}" var="cargo">
                        <tr >
                          <td><c:out value="${cargo.getIdCargo()}" /> </td>
                          <td><c:out value="${cargo.getCargo()}" /> </td>                
                          <td><c:out value="${cargo.getLcEmpresa().getRazonSocial()}" /> </td>  
                          <td>
                              <a  onclick="ConnsultaDatosID(${cargo.getIdCargo()})" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"> </span></a>
                              
                              <a onclick="deletecargo(${cargo.getIdCargo()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>  
                          
                        </tr>     
                    </c:forEach>  
             
                </tbody>
               
              </table>
            </div>
            <!-- /.box-body -->
          </div>

          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
   
      
      
      
  </div>
  <!-- /.content-wrapper -->
<script src="dist/js/cargos.js"></script>   
<!-- page script -->
<script>
  $(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
</body>
</html>


</f:view>
