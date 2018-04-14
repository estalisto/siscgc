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
  <!-- Content Wrapper. Contains page content -->
  <body>  
  <div>

      <br >
      <ol class="breadcrumb">
          <li><a href="#" onclick="usuarios();">Usuarios</a></li>
          <li><a href="#" onclick="frm_usuario();" >Crear Usuario</a></li>
      </ol>
      
     <div class="col-md-6">
          <!-- general form elements -->
          <div class="box box-danger">
            <div class="box-header with-border bg-yellow">
              <h3 class="box-title">Registrar Usuario</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            

             <form name="form" action="usuarios" method="get" class="well">
            
                        <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">

                        </div>
                  <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="1" required="required" name="id_persona" id="id_persona">

                        </div>
                    <!-- /input-group -->
                <div class="form-group">
                    <label>Identificación:</label>
                    <div class="input-group">                    
                                <input type="numeric" class="form-control" id="sel_cedula"/>
                                
                    <span class="input-group-btn">
                        <button id="cedula_valida" type="button" onclick="ExisteUsuario();" class="btn btn-info btn-flat">Go!</button>
                    </span>
                    
                    </div>
                             <div id="not_found" class="form-group has-error" style="display: none"> <!--hidden-->
                                <span class="help-block text-danger">Empleado Ya posee un Usuario Asignado</span>
                             </div>
                            <div id="found" class="form-group has-success" style="display: none"> <!--hidden-->
                                <span class="help-block text-danger">Empleado listo para crear Usuario</span>
                            </div>
                            <div id="nada" class="form-group has-warning" style="display: none"> <!--hidden-->
                                <span class="help-block text-danger">Empleado listo para crear Usuario</span>
                            </div> <span id="id_msg"></span>
                </div>

                <div class="form-group">
                  <label>Empresa:</label>
                 
                    <select id="empresa" class="form-control" name="empresa" required="required" disabled="true">
                    <option value=''>Seleccionar Empresa</option>
                     <c:forEach items="${empresas}" var="empresa">
                             <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                     </c:forEach>  
                    </select>
                </div>
                  <div id="IdSelect" class="form-group">
                      <select class="form-control" name="empresa" id="empresa2"   required="required" style="display: none">
                          <c:forEach items="${empresao}" var="empresas">
                              <option value="<c:out value="${empresas.getIdEmpresa()}" />" ><c:out value="${empresas.getRazonSocial()}" /></option>
                          </c:forEach>  

                      </select>
                  </div>               
                <div class="form-group">
                    <label>Rol: </label>
                    <div id="IdRolesEmpresa">
                            <select id="rol" class="form-control" name="rol" required="required" disabled="true">
                                <option value="">Seleccionar rol</option>
                                
                            </select>
                    </div>            
                </div>
              
                           
                <div class="form-group">
                            <label>Nombre Usuario:</label>
                            <input id="nusuario" type="text" class="form-control" placeholder="Ingrese Usuario" required="required" name="nusuario" disabled="true">
                                
                </div>
                 <div id="user_found" class="form-group has-error" style="display: none"> <!--hidden-->
                                <span class="help-block">Usuario Existente. Ingrese uno nuevo.</span>
                 </div>  
                <div class="form-group">
                            <label>Ingrese Contraseña:</label>
                            <input id="ncontrasenia" type="password" class="form-control" placeholder="Ingrese Contraseña" required="required" name="ncontrasenia" disabled="true">
                                
                </div> 
                    
                        <div id="not_found" class="form-group has-error hidden "> <!--hidden-->
                        <span class="help-block">Ingrese Usuario a Verificar</span>
                </div>
                            
                  
                <div class="form-group">
                  <label>Observaciones: </label>
                  <textarea id="observaciones" class="form-control" rows="3" name="observaciones" placeholder="Observaciones" disabled="true"></textarea>
                   
                </div>

                <div class="form-group">
                    <input id="btncrearusuario" type="submit" value="Registrar" class="btn btn-primary"  title="Crear Usuarios" disabled="true">
                </div>
            </form>
          </div>
     </div>
      
  </div>
  <!-- /.content-wrapper -->

  
 
<!-- ./wrapper -->
<script src="dist/js/usuario.js"></script>   
<script src="dist/js/validaciones.js"></script>  
 <script src="dist/js/ValidaNumeros.js"></script>
 <script type="text/javascript">
            $(function(){
                //Para escribir solo numeros    
                $('#sel_cedula').validCampoFranz('0123456789');    
                  
            });
            
            
        </script> 
</body>
</html>


