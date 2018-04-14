<%-- 
    Document   : frm_sectores
    Created on : 09/03/2017, 03:02:43 PM
    Author     : ViewSoft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
        <!-- Content Wrapper. Contains page content -->
        <div>
            <ol class="breadcrumb">
                <li><a href="#"  onclick="sectores(this)">Lista Zonas</a></li>
                <li class="active"><a href="#"  onclick="frm_zona(this)">Registrar Zonas</a></li>

            </ol>

            <div class="col-md-6">


                <!-- general form elements -->
                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow">
                        <h3 class="box-title">Ingresar Zonas o Sectores</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->

                    <form name="form" action="sectores" method="post" class="well" id="data">

                        <div class="box-body">

                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" >
                                            <option value=''>Seleccionar Empresa</option>
                                            <c:forEach items="${empresas}" var="empresa">
                                                <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    
                                    <div class="form-group">
                                        <select class="form-control" name="empresa" id="empresa2"  required="required" style="display: none">
                                            <c:forEach items="${empresao}" var="empresas">
                                                <option value="<c:out value="${empresas.getIdEmpresa()}" />" ><c:out value="${empresas.getRazonSocial()}" /></option>
                                            </c:forEach>  

                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Nombre zona:</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre" required="required">
                                    </div> 
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>País</label>
                                        <select class="form-control" name="pais" required="required" id="pais" onchange="obtenerProvincia()">
                                            <option value='' >Seleccionar País</option>
                                            <c:forEach items="${paises}" var="pais">
                                                <option value="<c:out value="${pais.getIdPais()}" />" ><c:out value="${pais.getPais()}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>      
                                </div>    
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>Provincia</label>
                                        <select class="form-control" name="provincia" required="required" id="provincia" disabled="true" onchange="obtenerCiudad()">
                                            <option value='' >Seleccionar Provincia</option>
                                            <!-- <c:forEach items="${provincias}" var="prov">
                                             <option value="<c:out value="${prov.getIdProvincia()}" />" ><c:out value="${prov.getProvincia()}" /></option>
                                            </c:forEach>-->

                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>Ciudad</label>
                                        <select class="form-control" name="ciudad" required="required" id="ciudad" disabled="true">
                                            <option value='' >Seleccionar Ciudad</option>
                                            <!--<c:forEach items="${ciudades}" var="ciudad">
                                            <option value="<c:out value="${ciudad.getIdCiudad()}" />" ><c:out value="${ciudad.getCiudad()}" /></option>
                                            </c:forEach>-->
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Descripcion Zona:</label>
                                <textarea id="descripcion" class="form-control" rows="3" name="descripcion" required="required"></textarea>
                            </div>

                            <div class="form-group">
                                <input id="btncrearzona" type="submit" value="Registrar" class="btn btn-primary"  title="Crear Zona">
                            </div>

                        </div>
                    </form>
                    <!-- /.box -->

                </div>
            </div>
        </div>

        <script src="dist/js/zona.js"></script>  
    </body>
</html>


