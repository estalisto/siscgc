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
        <!-- Content Wrapper. Contains page content -->
        <div >

            <br >
            <ol class="breadcrumb">
                <li><a href="#" onclick="clientes();">Nuestros Clientes</a></li>
                <li class="active"><a href="#" onclick="frm_clientes();">Registra Clientes</a></li>             
            </ol>

            <div class="col-md-6">
                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow"   >
                        <h3 class="box-title" >Clientes</h3>
                    </div>
                    <form name="form" action="clientes" method="post" class="well" id="data">
                        <c:forEach items="${client}" var="cliento">
                            <div class="form-group hidden">
                                <input type="text" class="form-control " placeholder="Ingrese Nombre Usuario" value="editar" required="required" name="accion" id="accion">
                            </div>
                            <div class="col-xs-3 hidden">
                                <div class="form-group">
                                    <label>Código:</label>
                                    <input type="text" class="form-control" id="idcliente" name="idcliente" value="<c:out value="${cliento.getIdCliente()}" />" required="required">
                                </div> 
                            </div>
                            <div class="form-group">
                                <label>Empresa:</label>

                                <select class="form-control" name="empresa" required="required" id="empresa" disabled="true">
                                    <option value="<c:out value="${cliento.getLcEmpresa().getIdEmpresa()}" />"><c:out value="${cliento.getLcEmpresa().getRazonSocial()}" /></option>
                                    <c:forEach items="${empresas}" var="empresa">
                                        <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                    </c:forEach>                 
                                </select>
                            </div>

                            <div class="form-group">  
                                <div class="row">
                                    <div class="col-lg-6">
                                        <label>Tipo de Identificación:</label>
                                        <select class="form-control" name="Tipo_Identificacion" required="required" id="Tipo_Identificacion" onchange="validaselector()">
                                            <option value="<c:out value="${cliento.getLcTiposIdentificacion().getIdTipoIdentificacion()}" />"><c:out value="${cliento.getLcTiposIdentificacion().getDescripcion()}" /></option>
                                            <c:forEach items="${tipIDE}" var="tipo">
                                                <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                            </c:forEach> 
                                        </select>
                                    </div>
                                    <div class="col-lg-6">
                                        <label>Número de Identificación:</label>
                                        <input type="text" class="form-control" placeholder="Número de Identificación" name="identificacion" id="identificacion" onkeypress="ValidaSoloNumeros()"  value="<c:out value="${cliento.getIdentificacion()}" />">
                                        <div id="valido" class="form-group has-success" style="display: none"> <!--hidden-->
                                            <span class="help-block">Identificación Válida.</span>
                                        </div>
                                        <div id="no_value" class="form-group has-error" style="display: none"> <!--hidden-->
                                            <span class="help-block">Identificación Inválida.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Razón Social: </label>
                                <input type="text" class="form-control" placeholder="Ingrese Razón Social " required="required" name="razon" id="razon" onkeypress="txNombres()" value="<c:out value="${cliento.getRazonSocial()}" />">
                            </div>

                            <div class="form-group">
                                <label>Dirección: </label>
                                <input type="text" class="form-control" placeholder="Ingrese Dirección " required="required" name="direccion" id="direccion" value="<c:out value="${cliento.getDireccion()}" />">
                            </div>

                            <div class="form-group">  
                                <div class="row">
                                    <div class="col-lg-4">
                                        <label>País:</label>
                                        <select class="form-control" name="pais" required="required" id="pais" onchange="obtenerProvincia()">
                                            <option value="<c:out value="${cliento.getLcPais().getIdPais()}" />"><c:out value="${cliento.getLcPais().getPais()}" /></option>
                                            <c:forEach items="${paises}" var="pais">
                                                <option value="<c:out value="${pais.getIdPais()}" />" ><c:out value="${pais.getPais()}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-lg-4">
                                        <label>Provincia:</label>


                                        <select class="form-control" name="provincia" required="required" id="provincia" disabled="true" onchange="obtenerCiudad()">
                                            <option value="<c:out value="${cliento.getLcProvincia().getIdProvincia()}" />"><c:out value="${cliento.getLcProvincia().getProvincia()}" /></option>
                                        </select>
                                    </div>
                                    <div class="col-lg-4">
                                        <label>Ciudad:</label>
                                        <select class="form-control" name="ciudad" required="required" id="ciudad" disabled="true">
                                            <option value="<c:out value="${cliento.getLcCiudad().getIdCiudad()}" />"><c:out value="${cliento.getLcCiudad().getCiudad()}" /></option>

                                        </select>                              
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">  
                                <div class="row">
                                    <div class="col-lg-6">
                                        <label>Contacto:</label>
                                        <input type="text" class="form-control" placeholder="Ingrese el Contacto " required="required" name="contacto" id="contacto" value="<c:out value="${cliento.getContacto()}" />">
                                    </div>
                                    <div class="col-lg-6">
                                        <label>Email:</label>
                                        <input  type="email" class="form-control" placeholder="Ingrese el Email " required="required" name="email" id="email" value="<c:out value="${cliento.getEmail()}" />">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">  
                                <div class="row">
                                    <div class="col-lg-4">
                                        <label>Teléfono:</label>
                                        <input type="text" class="form-control" placeholder="Teléfono 1 " required="required" name="fono1" id="fono1" onkeypress="ValidaSoloNumeros()" value="<c:out value="${cliento.getTelefono()}" />">
                                    </div>
                                    <div class="col-lg-4">
                                        <label>Extensión:</label>
                                        <input type="text" class="form-control" placeholder="EXT "  name="ext" id="ext" onkeypress="ValidaSoloNumeros()" value="<c:out value="${cliento.getExtensioon()}" />">
                                    </div>
                                    <div class="col-lg-4">
                                        <label>Celular:</label>
                                        <input type="text" class="form-control" placeholder="Celular"  name="celular" id="celular" onkeypress="ValidaSoloNumeros()" value="<c:out value="${cliento.getCelular()}" />">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <input id="btnactclientes" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Clientes">
                            </div>
                        </c:forEach>
                    </form>

                    <!-- /.box-body -->
                </div>

                <!-- /.box -->

            </div>

        </div>
        <!-- /.content-wrapper -->
        <script src="dist/ruc_jquery_validator.min.js"></script>
        <script src="dist/js/clientes.js"></script>

    </body>
</html>


