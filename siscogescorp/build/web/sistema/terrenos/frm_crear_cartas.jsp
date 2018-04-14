    <%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : Stalyn Granda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <!DOCTYPE html>
<html>
<head>
     
  <!-- Content Wrapper. Contains page content -->
<section>
  <div >

      <br >
      
      
      <div class="col-md-6">

        <div class="box box-danger">
            <div class="box-header with-border bg-yellow"   >
              <h3 class="box-title" >Crear Plantillas de Documentos</h3>
            </div>
              <form name="form" action="empleados" method="post" class="well" id="data">
                
                        <!-- Color Picker -->
                        
                        <div class="row form-group">
                            <div class="col-sm-4">
                              <label>Empresa:</label>
                              <select class="form-control" name="empresa" required="required" id="empresa" onchange="Obtener_combos()">
                              <option value=''>Seleccionar Empresa</option>
                                <c:forEach items="${empresas}" var="empresa">
                                 <option value='<c:out value="${empresa.getIdEmpresa()}"/>'><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                </c:forEach> 
                              </select>
                            </div>

                            <div class="col-sm-4">
                                <label>Sucursal:</label>
                                <select class="form-control" id="sucursal" name="sucursal" required="required" disabled="true">
                                 <option value=''>Seleccione sucursal</option>
                                 <c:forEach items="${agencia}" var="sucursal">
                                 <option value='<c:out value="${sucursal.getIdAgencia()}"/>'><c:out value="${sucursal.getNombre()}" /> </option>                         
                                </c:forEach> 
                                </select>
                            </div>


                            <div class="col-sm-4">
                                <label>Cliente: </label>  
                                <select class="form-control" id="cliente" name="cliente" required="required" disabled="true">
                                    <option value='' >Seleccione cartera</option>
                                    <c:forEach items="${cartera}" var="cliente">
                                    <option value='<c:out value="${cliente.getIdCliente()}"/>'><c:out value="${cliente.getRazonSocial()}" /> </option>                         
                                   </c:forEach> 
                               </select>
                            </div>                               
                        </div>          
                   <div id="ingresoDoc">  
                        <div class="form-group">
                            <label>Nombre del Documento</label>
                            <input type="text" class="form-control" id="nombre_documento">
                        </div>
                        
                        <div class="form-group">
                            <textarea class="form-control" rows="3" placeholder="Saludo" id="saludo"></textarea>
                            <textarea class="form-control" rows="5" placeholder="Cuerpo" id="cuerpo"></textarea>
                            <textarea class="form-control" rows="3" placeholder="Despedida" id="despedida"></textarea>
                            <textarea class="form-control" rows="3" placeholder="Firma" id="firma"></textarea>
                        </div>
                        
                        <div class="form-group ">
                            <label>[Cedula]</label>
                            <label>[Nombre Cliente]</label>
                            <label>[valor_total]</label>
                            <label>[Cartera]</label>
                            <label>[Monto]</label>
                            <label>[Ciudad]</label>
                            <label>[dias_mora]</label>
                            <label>[nombre_empresa]</label>                            
                            <label>[Observaciones]</label>
                            
                        </div>
                        
                        <div class=" form-group">
                            <button type="button" class="btn btn-primary" onclick="Guardar_documento()">Guardar</button>  
                          <button type="button" class="btn btn-primary">Salir</button>
                        </div>
                        </div>
                        <div id="datosmodifica">
                            
                            <div id="EditarDoc" >
                            
                            </div>
                        
                        </div>
            </form> 
              <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      
  </div>
</section>
  <!-- /.content-wrapper -->
  
  <script src="dist/js/documento.js"></script>
  
  
</body>
</html>

