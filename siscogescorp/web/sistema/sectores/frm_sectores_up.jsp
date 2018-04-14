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
                        <h3 class="box-title">Editar Zonas o Sectores</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->

                    <form name="form" action="sectores" method="post" class="well" id="data">
                     <c:forEach items="${zonas}" var="zona">
                                                                     
                                           
                        <div class="box-body">

                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">
                            </div>
                            
                            <div class="col-xs-3 hidden">
                                    <div class="form-group">
                                        <label>Código:</label>
                                        <input type="text" class="form-control" id="idzona" name="idzona" value="<c:out value="${zona.getIdZona()}" />" required="required">
                                    </div> 
                                </div>
                            
                            <div class="row">
                                 
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" disabled="true" >
                                            <option value="<c:out value="${zona.getLcEmpresa().getIdEmpresa()}" />" ><c:out value="${zona.getLcEmpresa().getRazonSocial()}" /></option>
                                            <c:forEach items="${empresas}" var="empresa">
                                                <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    </div> 
                                </div>
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Nombre zona:</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre" required="required" value="<c:out value="${zona.getNombreZona()}" />">
                                    </div> 
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>País</label>
                                        <select class="form-control" name="pais" required="required" id="pais" onchange="obtenerProvincia()">
                                            <option value="<c:out value="${zona.getLcPais().getIdPais()}"/>"><c:out value="${zona.getLcPais().getPais()}"/></option>
                                            <c:forEach items="${paises}" var="pais">
                                            <option value="<c:out value="${pais.getIdPais()}" />" ><c:out value="${pais.getPais()}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>      
                                </div>    
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>Provincia</label>
                                        <select class="form-control" name="provincia" required="required" id="provincia" onchange="obtenerCiudad()">
                                            <option value="<c:out value="${zona.getLcProvincia().getIdProvincia()}" />"><c:out value="${zona.getLcProvincia().getProvincia()}" /></option>
                                            <!-- <c:forEach items="${provincias}" var="prov">
                                            <option value="<c:out value="${prov.getIdProvincia()}" />" ><c:out value="${prov.getProvincia()}" /></option>
                                            </c:forEach>-->

                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label>Ciudad</label>
                                        <select class="form-control" name="ciudad" required="required" id="ciudad" >
                                            <option value="<c:out value="${zona.getLcCiudad().getIdCiudad()}" />"><c:out value="${zona.getLcCiudad().getCiudad()}" /></option>
                                            <!--<c:forEach items="${ciudades}" var="ciudad">
                                            <option value="<c:out value="${ciudad.getIdCiudad()}" />" ><c:out value="${ciudad.getCiudad()}" /></option>
                                            </c:forEach>-->
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Descripcion Zona:</label>
                                <textarea id="descripcion" class="form-control" rows="3" name="descripcion" required="required" ><c:out value="${zona.getSectorDescripcion()}" /></textarea>
                            </div>

                            <div class="form-group">
                                <input id="btnactzona" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Zona">
                            </div>

                        </div>
                        </c:forEach> 
                    </form>
                    <!-- /.box -->

                </div>
            </div>
        </div>

        <script src="dist/js/zona.js"></script>  
    </body>
</html>


