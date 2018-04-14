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
                        <h3 class="box-title">Actualizar Usuario</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->


                    <form name="form" action="usuarios" method="get" class="well">
                        <c:forEach items="${user}" var="usuar">
                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">

                            </div>
                            <!--div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="1" required="required" name="id_persona" id="id_persona">

                            </div-->
                            <div class="col-xs-3 hidden">
                                    <div class="form-group">
                                        <label>Código:</label>
                                        <input type="text" class="form-control" id="idusuario" name="idusuario" value="<c:out value="${usuar.getIdUsuario()}"/>" required="required">
                                    </div> 
                                </div>
                            <!-- /input-group -->
                            <div class="form-group">
                                <label>Cédula:</label>
                                <div class="input-group">                    
                                    <input type="text" class="form-control" id="sel_cedula" name="sel_cedula" onkeypress="ValidaSoloNumeros()" disabled="true" value="<c:out value="${usuar.getLcEmpleados().getIdentificacion()}"/>">
                                    <span class="input-group-btn">
                                        <button id="cedula_valida" type="button"  class="btn btn-info btn-flat" disabled="true">Go!</button>
                                    </span>

                                </div>
                                <div id="not_found" class="form-group has-error" style="display: none"> <!--hidden-->
                                <span class="help-block">Empleado Registrado.Ya posee un Usuario Asignado</span>
                             </div>
                            <div id="found" class="form-group has-success" style="display: none"> <!--hidden-->
                                <span class="help-block">Empleado Registrado.Asignar Usuario</span>
                            </div>
                            </div>

                            <!--div class="form-group">
                                <label>Nombre: </label>
                                <input id="nom_user" type="text" class="form-control" placeholder="Ingrese el Nombre "  name="nombre" onkeypress="txNombres()" value="<c:out value="${usuar.getLcEmpleados().getIdEmpleado()}"/>" disabled="true">
                            </div-->

                            <div class="form-group">
                                <label>Empresa:</label>

                                <select id="empresa" class="form-control" name="empresa" required="required" disabled="true">
                                    <option value="<c:out value="${usuar.getLcEmpresa().getIdEmpresa()}"/>"><c:out value="${usuar.getLcEmpresa().getRazonSocial()}"/></option>
                                    <c:forEach items="${empresas}" var="empresa">
                                        <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                    </c:forEach>  
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Rol: </label>
                                <select id="rol" class="form-control" name="rol" required="required" >
                                    <option value="<c:out value="${usuar.getLcRoles().getIdRol()}"/>"><c:out value="${usuar.getLcRoles().getDescripcion()}"/></option>
                                    <c:forEach items="${roles}" var="rol">
                                        <option value="<c:out value="${rol.getIdRol()}"/>" ><c:out value="${rol.getDescripcion()}" /></option>
                                    </c:forEach>
                                </select>

                            </div>


                            <div class="form-group">
                                <label>Nombre Usuario:</label>
                                <input id="nusuario" type="text" class="form-control" placeholder="Ingrese Usuario" required="required" name="nusuario" value="<c:out value="${usuar.getUsuario()}"/>" >

                            </div>
                            <div class="form-group" hidden>
                                <label>Ingrese Contraseña:</label>
                                <input id="ncontrasenia" type="password" class="form-control" placeholder="Ingrese Contraseña" required="required" name="ncontrasenia" value="<c:out value="${usuar.getContrasenia()}"/>" >

                            </div> 

                            <div id="not_found" class="form-group has-error hidden "> <!--hidden-->
                                <span class="help-block">Ingrese Usuario a Verificar</span>
                            </div>


                            <div class="form-group">
                                <label>Observaciones: </label>
                                <textarea id="observaciones" class="form-control" rows="3" name="observaciones" placeholder="Observaciones" value="<c:out value="${usuar.getObservacion()}"/>" ><c:out value="${usuar.getObservacion()}"/></textarea>

                            </div>

                            <div class="form-group">
                                <input id="btnactusuario" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Usuarios" >
                            </div>
                        </c:forEach>
                    </form>
                </div>
            </div>

        </div>
        <!-- /.content-wrapper -->



        <!-- ./wrapper -->
        <script src="dist/js/usuario.js"></script>   
        <script src="dist/js/validaciones.js"></script>  
    </body>
</html></div>


