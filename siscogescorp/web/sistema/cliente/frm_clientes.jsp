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
                <li class="active"><a href="#" onclick="frm_clientes();">Registrar Clientes</a></li>             
            </ol>

            <div class="col-md-6">
                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow"   >
                        <h3 class="box-title" >Clientes</h3>
                    </div>
                    <form name="form" action="clientes" method="post" class="well" id="data">
                        <div class="form-group hidden">
                            <input type="text" class="form-control " placeholder="Ingrese Nombre Usuario" value="registrar" required="required" name="accion" id="accion">

                        </div>
                        <div class="form-group">
                            <label>Empresa:</label>

                            <select class="form-control" required="required" id="empresa">
                                <option value=''>Seleccionar Empresa</option>
                                <c:forEach items="${empresas}" var="empresa">
                                    <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                </c:forEach>                 
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control" id="empresa2"  required="required" style="display: none">
                                <c:forEach items="${empresao}" var="empresas">
                                    <option value="<c:out value="${empresas.getIdEmpresa()}" />" ><c:out value="${empresas.getRazonSocial()}" /></option>
                                </c:forEach>  

                            </select>
                        </div>
                        <div class="form-group">  
                            <div class="row">
                                <div class="col-lg-6">
                                    <label>Tipo de Identificación:</label>
                                    <select class="form-control" name="Tipo_Identificacion" required="required" id="Tipo_Identificacion" onchange="validaselector()">
                                        <option value=''>Tipo de Identificación</option>
                                        <c:forEach items="${tipIDE}" var="tipo">
                                            <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                        </c:forEach> 
                                    </select>
                                </div>
                                <div class="col-lg-6">
                                    <label>Número de Identificación:</label>
                                    <input type="text" maxlength="13" class="form-control" placeholder="Número de Identificación" name="identificacion" onkeyup="validaLongitudCED_RUC_HTML();" id="identificacion" disabled="true">
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
                            <label>Razón Social: </label>
                            <input type="text" class="form-control" placeholder="Ingrese Razón Social " required="required" name="razon" id="razon" onkeypress="txNombres()">
                        </div>

                        <div class="form-group">
                            <label>Dirección: </label>
                            <input type="text" class="form-control" placeholder="Ingrese Dirección " required="required" name="direccion" id="direccion">
                        </div>

                        <div class="form-group">  
                            <div class="row">
                                <div class="col-lg-4">
                                    <label>País:</label>
                                    <select class="form-control" name="pais" required="required" id="pais" onchange="obtenerProvincia()">
                                        <option value='' >Seleccionar País</option>
                                        <c:forEach items="${paises}" var="pais">
                                            <option value="<c:out value="${pais.getIdPais()}" />" ><c:out value="${pais.getPais()}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-4">
                                    <label>Provincia:</label>
                                    <select class="form-control" name="provincia" required="required" id="provincia" disabled="true" onchange="obtenerCiudad()">
                                        <option value='' >Seleccionar Provincia</option>
                                        <!-- <c:forEach items="${provincias}" var="prov">
                                         <option value="<c:out value="${prov.getIdProvincia()}" />" ><c:out value="${prov.getProvincia()}" /></option>
                                        </c:forEach>-->

                                    </select>
                                </div>
                                <div class="col-lg-4">
                                    <label>Ciudad:</label>
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
                            <div class="row">
                                <div class="col-lg-6">
                                    <label>Contacto:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese el Contacto " required="required" name="contacto" id="contacto">
                                </div>
                                <div class="col-lg-6">
                                    <label>Email:</label>
                                    <input  type="email" onkeyup="ValidaEmailOk2();"  class="form-control" placeholder="email@correo.com" required="required" name="email" id="email">
                                <span id="emailOK"></span></div>
                            </div>
                        </div>

                        <div class="form-group">  
                            <div class="row">
                                <div class="col-lg-4">
                                    <label>Teléfono:</label>
                                    <input type="text" class="form-control" maxlength="15" placeholder="(04) 2555   555" required="required" name="fono1" id="fono1" >
                                </div>
                                <div class="col-lg-4">
                                    <label>Extensión:</label>
                                    <input type="text" class="form-control"  placeholder="090909"   name="ext" id="ext" >
                                </div>
                                <div class="col-lg-4">
                                    <label>Celular:</label>
                                    <input type="text" class="form-control" maxlength="15" placeholder="099 999 9999"  name="celular" id="celular" >
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <input id="btncrearclientes" type="submit" value="Registrar" class="btn btn-primary"  title="Crear Clientes">
                        </div>
                    </form>

                    <!-- /.box-body -->
                </div>

                <!-- /.box -->

            </div>

        </div>
        <!-- /.content-wrapper -->
        <script src="dist/ruc_jquery_validator.min.js"></script>
        <script src="dist/js/clientes.js"></script>
        <script src="dist/js/ValidaNumeros.js"></script>
        <script src="dist/js/ValidaEmailOk.js"></script>  
         <script type="text/javascript">             
            $(function(){
                //Para escribir solo numeros    
                $('#celular').validCampoFranz('0123456789');    
                $('#ext').validCampoFranz('0123456789');  
                $('#fono1').validCampoFranz('0123456789');  
                  $('#identificacion').validCampoFranz('0123456789'); 
                
            });
            
        </script>
        <script type="text/javascript">
        jQuery(function($){
            $("#fono1").mask("(99) 999-9999", {
 
                // Generamos un evento en el momento que se rellena
                completed:function(){
                    $("#fono1").addClass("ok");
                }
            });
            $("#celular").mask("999 999 9999", {
 
                // Generamos un evento en el momento que se rellena
                completed:function(){
                    $("#celular").addClass("ok");
                }
            });
           // $("#ext").mask("(9999)", {
 
                // Generamos un evento en el momento que se rellena
                //completed:function(){
                  //  $("#ext").addClass("ok");
                //}
            //});
         });
    </script>
    </body>
</html>


