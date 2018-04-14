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
                        <h3 class="box-title" >Registrar Empresas</h3>
                    </div>
                    <form name="form" action="empresa" method="post" class="well" id="data">  
                        <div class="box-body">
                            <!-- Color Picker -->
                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">

                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Tipo Identificación:</label>
                                        <select class="form-control" name="t_identificacion" required="required" id="t_identificacion" onchange="validaselector()">
                                            <option value='' >Seleccionar tipo de identificación</option>
                                            <c:forEach items="${tipIDE}" var="tipo">
                                                <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                            </c:forEach> 
                                        </select>
                                    </div>          
                                </div>
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Identificación:</label>
                                        <input type="text" class="form-control" maxlength="13" placeholder="Ingrese numero identificación" required="required" name="identificacion" id="identificacion" onkeyup="validaLongitudCED_RUC_HTML();"  disabled="true">
                                        <!--input type="text" class="form-control" maxlength="13" placeholder="Ingrese numero identificación" required="required" name="identificacion" id="identificacion" onchange="ValidaIdentificacion()" onkeypress="ValidaSoloNumeros();" disabled="true" style="display: none">
                                        <input type="text" class="form-control" maxlength="6" placeholder="Ingrese numero identificación" required="required" name="identificacion" id="identificacion" onchange="ValidaIdentificacion()"  disabled="true" style="display: none"-->
                                        <div id="valido" class="form-group has-success" style="display: none"> <!--hidden-->
                                            <span class="help-block">Identificación Válida.</span>
                                        </div>
                                        <div id="no_value" class="form-group has-error" style="display: none"> <!--hidden-->
                                            <span class="help-block">Identificación Inválida.</span>
                                        </div><span id="validaLength"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nombre Empresa:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Empresa" required="required" name="nombre" id="nombre">
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
                                <label>Dirección:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Dirección" required="required" name="direccion" id="direccion">
                            </div>
                            <div class=" form-group">
                                <label>Email de Contacto:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input type="email" onkeyup="ValidaEmailOk();"  class="form-control" placeholder="Email"  required="required" name="mail" id="mail">
                                  
                                </div>  <span id="emailOK"></span>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <div class="form-group">     
                                        <label>Teléfono:</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-phone"></i>
                                            </div>
                                            <input type="number" maxlength="10" class="form-control" placeholder="029999999" name="telefono" id="telefono"   >
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
                                            <input type="number" maxlength="10" class="form-control"  placeholder="029999999"   name="telefono2" id="telefono2" >
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
                                            <input type="number" SIZE="10" class="form-control"  placeholder="0999999999"  name="celular" id="celular" >
                                        </div>
                                    </div>  
                                </div>
                            </div>
                            <div class=" form-group">
                                <!-- <button type="submit" class="btn btn-primary fa fa-save"> Registrar</button>-->
                                <input id="btncrearempresa" type="submit" value="Registrar" class="btn btn-primary"  title="Crea Empresa">
                            </div>
                        </div>  
                    </form>
                </div>
            </div>
            <!-- /.content-wrapper -->
        </div>
        <script src="dist/ruc_jquery_validator.min.js"></script>
        <script src="dist/js/empresa.js"></script> 
        <script src="dist/js/validar_cedula_ecuador.js"></script>
        <script src="dist/js/ValidaNumeros.js"></script>
        <script type="text/javascript">
            $(function(){
                //Para escribir solo numeros    
                $('#celular').validCampoFranz('0123456789');    
                $('#telefono').validCampoFranz('0123456789');  
                $('#telefono2').validCampoFranz('0123456789');  
               
                
            });
        </script> 
       
    </body>
</html>


