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
        <div>

            <ol class="breadcrumb">
                <li><a href="#"  onclick="empresa(this)">Lista Empresas</a></li>
                <li class="active"><a href="#"  onclick="frm_empresa(this)">Registra Empresas</a></li>
            </ol> 
            <div class="col-md-6">



                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow"   >
                        <h3 class="box-title" >Editar Empresas</h3>
                    </div>
                    <form name="form" action="empresa" method="post" class="well" id="data">  
                        <c:forEach items="${enterprise}" var="empres">
                            <div class="box-body">
                                <!-- Color Picker -->
                                <div class="form-group hidden">
                                    <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">

                                </div>
                                <div class="col-xs-3 hidden">
                                    <div class="form-group">
                                        <label>Código:</label>
                                        <input type="text" class="form-control" id="idempresa" name="idempresa" value="<c:out value="${empres.getIdEmpresa()}" />" required="required">
                                    </div> 
                                </div>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <div class="form-group">
                                            <label>Tipo Identificación:</label>
                                            <select class="form-control" name="t_identificacion" required="required" id="t_identificacion" onchange="validaselector()">
                                                <option value="<c:out value="${empres.getLcTiposIdentificacion().getIdTipoIdentificacion()}" />"><c:out value="${empres.getLcTiposIdentificacion().getDescripcion()}"/></option>
                                                <c:forEach items="${tipIDE}" var="tipo">
                                                    <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                                </c:forEach> 
                                            </select>
                                        </div>          
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="form-group">
                                            <label>Identificación:</label>
                                            <input type="text" class="form-control" placeholder="Ingrese numero identificación" required="required" name="identificacion"  value="<c:out value="${empres.getIdentificacion()}" />" id="identificacion">
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
                                    <label>Nombre Empresa:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese Nombre Empresa" required="required" name="nombre" id="nombre" value="<c:out value="${empres.getRazonSocial()}" />">
                                </div>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label>País</label>
                                            <select class="form-control" name="pais" required="required" id="pais" onchange="obtenerProvincia()">
                                                <option value="<c:out value="${empres.getLcPais().getIdPais()}" />" ><c:out value="${empres.getLcPais().getPais()}" /></option>
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
                                                <option value="<c:out value="${empres.getLcProvincia().getIdProvincia()}" />" ><c:out value="${empres.getLcProvincia().getProvincia()}"/></option>
                                                <!--<c:forEach items="${provincias}" var="prov">
                                                <option value="<c:out value="${prov.getIdProvincia()}" />" ><c:out value="${prov.getProvincia()}" /></option>
                                                </c:forEach>-->

                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label>Ciudad</label>
                                            <select class="form-control" name="ciudad" id="ciudad" required="required" id="ciudad" >
                                                <option value="<c:out value="${empres.getLcCiudad().getIdCiudad()}" />" ><c:out value="${empres.getLcCiudad().getCiudad()}"/></option>
                                                <!--<c:forEach items="${ciudades}" var="ciudad">
                                                 <option value="<c:out value="${ciudad.getIdCiudad()}" />" ><c:out value="${ciudad.getCiudad()}" /></option>
                                                </c:forEach>-->
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Dirección:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese Dirección" required="required" name="direccion" id="direccion" value="<c:out value="${empres.getDireccion()}"/>">
                                </div>
                                <div class=" form-group">
                                    <label>Email de Contacto:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                        <input type="email" class="form-control" placeholder="Email" name="email" required="required" name="mail" id="mail" value="<c:out value="${empres.getEmail()}"/>">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <div class="form-group">     
                                            <label>Teléfono:</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-phone"></i>
                                                </div>
                                                <input type="text" class="form-control" maxlength="15" data-inputmask='"mask": "(99) 999-9999"' data-mask name="telefono" id="telefono" value="<c:out value="${empres.getTelefonos()}"/>">
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label>Teléfono 2:</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-phone"></i>
                                                </div>
                                                <input type="text" class="form-control" maxlength="15" data-inputmask='"mask": "(99) 999-9999"' data-mask name="telefono2" id="telefono2" value="<c:out value="${empres.getTelfonos2()}"/>">
                                            </div>                
                                        </div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div class="form-group">
                                            <label>Celular:</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-phone"></i>
                                                </div>
                                                <input type="text" class="form-control" maxlength="15" data-inputmask='"mask": "(99) 999-9999"' data-mask name="celular" id="celular" value="<c:out value="${empres.getCelular()}"/>">
                                            </div>
                                        </div>  
                                    </div>
                                </div>



                                <div class=" form-group">
                                    <!-- <button type="submit" class="btn btn-primary fa fa-save"> Registrar</button>-->
                                    <input id="btnactempresa" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Empresa">
                                </div>

                            </div>  
                        </c:forEach> 
                    </form>
                    <!-- /.box-body -->
                </div>




            </div>
            <!-- /.content-wrapper -->


        </div>
        <!-- /.content-wrapper -->
        <!-- ./wrapper -->
        <script src="dist/ruc_jquery_validator.min.js"></script>
        <script src="dist/js/empresa.js"></script> 
        <!--script src="dist/js/validar_cedula_ecuador.js"></script-->
    </body>
</html>


