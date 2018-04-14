<%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : CIMA2015
--%>
<%@page import="com.siscogescorp.modelo.LcEmpleados"%>
<%@page import="java.util.List"%>
<%@page import="com.siscogescorp.servicios.EmpleadosServicios"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <!DOCTYPE html>
    <html>
        <head>

        </head>
        <body>
            <!-- Content Wrapper. Contains page content -->
            <div>
<ol class="Breadcrumb default">
        <div class="btn-group btn-breadcrumb">
            <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>                       
            <a href="#" class=" btn btn-default" onclick="personal(this);">EMPLEADOS</a>
            <a href="#" class="active btn btn-success" onclick="frm_empleados(this);">CREAR EMPLEADO</a>
        </div>                    
    </ol>
                <div class="row">   
                    <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="box box-danger">
                                <form name="form" action="empleados" method="get" class="pannel panel-body panel-primary">
                                    <div class="box-body">
                                        <div class="form-group hidden">
                                            <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                            <div class="col-lg-6">    
                                                <label>Empresa:</label>
                                                <select class="form-control" name="empresa" onchange="ClearOptionsFast('sucursal');" required="required" id="empresa">
                                                     <c:if test="${sessionScope.NivelAccesoRolID==0}" >
                                                        <option value="">Seleccionar Empresa</option>
                                                    </c:if>

                                                    <c:forEach items="${empresas}" var="empresa">
                                                        <option value="<c:out value="${empresa.getIdEmpresa()}" />" ><c:out value="${empresa.getRazonSocial()}" /></option>
                                                    </c:forEach>  
                                                </select>

                                            </div>
                                            <div class="col-lg-6">    
                                                <label>Agencia:</label>
                                                <select class="form-control" name="sucursal"  required="required" id="sucursal" >

                                                     <c:if test="${sessionScope.NivelAccesoRolID==0}" >
                                                        <option value="">Seleccionar Agencia</option>
                                                    </c:if>
                                                    <c:forEach items="${agencias}" var="agencia">
                                                        <option value="<c:out value="${agencia.getIdAgencia()}" />" ><c:out value="${agencia.getNombre()}" /></option>
                                                    </c:forEach>  
                                                </select>
                                            </div>
                                            </div>    
                                        </div>

                                        <div class="form-group ">
                                            <label class="">Tipo de Identificación</label>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <select class="form-control" name="t_identificacion" required="required" id="t_identificacion" onchange="validaselector()">
                                                        <option value='' >Seleccionar tipo de identificación</option>
                                                        <c:forEach items="${tipIDE}" var="tipo">
                                                            <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                                        </c:forEach>  

                                                    </select>
                                                </div>
                                                <div class="col-lg-6">
                                                    <input type="text" maxlength="13" class="form-control" placeholder="Ingrese Identificación" onkeyup="validaLongitudCED_RUC_HTML();" required="required" name="identificacion" id="identificacion" disabled="true">
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
                                            <label>Nombres y Apellidos:</label>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" placeholder="Ingrese Nombres" required="required" name="nombres" id="nombres" onkeypress="txNombres()">
                                                </div>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" placeholder="Ingrese Apellidos" required="required" name="apellidos" id="apellidos" onkeypress="txNombres()">
                                                </div>                                
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <div class="form-group">
                                                        <label>Lugar de Nacimiento</label>                          
                                                        <input type="text" class="form-control" placeholder="Ingrese Lugar de Nacimiento" required="required" name="lugar_nac" id="lugar_nac" onkeypress="txNombres()">
                                                    </div>
                                                </div>
                                                <div class="col-lg-3">
                                                    <div class="form-group">
                                                        <label>Fecha de nacimiento:</label>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <input type="text" class="form-control pull-right" placeholder="YYYY-MM-DD" id="fecha_nac" name="fecha_nac">
                                                        </div>
                                                        <!-- /.input group -->
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>E-Mail</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                                            <input type="email" class="form-control" onkeyup="ValidaEmailOk2();"  placeholder="email@correo.com" name="email" id="email">
                                                        </div><span id="emailOK"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">  
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <div class="form-group">
                                                        <label>Télefono:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-phone"></i>
                                                            </div>
                                                            <input type="text" class="form-control" maxlength="15" placeholder="049999999" name="telefono" id="telefono" >
                                                        </div>
                                                        <!-- /.input group -->
                                                    </div>
                                                </div>
                                                <div class="col-lg-3">
                                                    <div class="form-group">
                                                        <label>Celular: </label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-phone"></i>
                                                            </div>
                                                            <input type="text" class="form-control" maxlength="15"  placeholder="0999999999"  name="celular" id="celular" >
                                                        </div>
                                                        <!-- /.input group -->
                                                    </div>
                                                </div>
                                                <div class="col-lg-3">
                                                    <div class="form-group ">                                  
                                                        <label class="">Estado Civil:</label>
                                                        <select class="form-control" name="est_civil" required="required" id="est_civil"> 
                                                            <option value='' >Seleccionar el Estado Civil</option>
                                                            <c:forEach items="${estacivil}" var="civil">
                                                                <option value="<c:out value="${civil.getIdEstcivil()}" />" ><c:out value="${civil.getDescripcion()}" /></option>
                                                            </c:forEach>                            
                                                        </select>
                                                    </div>

                                                </div>
                                                <div class="col-lg-3">

                                                    <div class="form-group ">
                                                        <label class="">Genero:</label>
                                                        <select class="form-control" name="genero" required="required" id="genero">
                                                            <option value='' >Seleccionar genero</option>
                                                            <c:forEach items="${generos}" var="genero">
                                                                <option value="<c:out value="${genero.getIdGenero()}" />" ><c:out value="${genero.getDescripcion()}" /></option>
                                                            </c:forEach>                          
                                                        </select>
                                                    </div>  

                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">  
                                            <div class="row">

                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Profesión:</label>
                                                        <input type="text" class="form-control" placeholder="Ingrese Profesión" required="required" name="profesion" id="profesion" onkeypress="txNombres()">
                                                    </div>                           
                                                </div>
                                                <div class="col-lg-3">                        
                                                    <div class="form-group ">
                                                        <label class="">Cargo</label>
                                                        <select class="form-control" name="cargo" required="required" id="cargo">
                                                            <option value='' >Seleccionar</option>
                                                            <c:forEach items="${cargos}" var="cargo">
                                                                <option value="<c:out value="${cargo.getIdCargo()}" />" ><c:out value="${cargo.getCargo()}" /></option>
                                                            </c:forEach>  
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3">                        
                                                    <div class="form-group ">
                                                        <label class="">Jefe Directo:</label>
                                                        <select class="form-control" name="jefe_directo" required="required" id="jefe_directo">
                                                            <option value='0' >NINGUNO</option>
                                                            <%  String id_empresas;    
                                                                HttpSession sesion = request.getSession(true);
                                                                id_empresas = sesion.getAttribute("Sstrempresa").toString();
                                                                int EmpresaID= Integer.parseInt(id_empresas);

                                                                EmpleadosServicios emp = new EmpleadosServicios();
                                                                if(EmpresaID==1){
                                                                List<LcEmpleados> empleados = emp.getLCEmpleados2();
                                                                for (int i = 0; i < empleados.size(); i++) {
                                                                    System.out.print("listo" + empleados.get(i).getNombres());

                                                                    if (empleados.get(i).getLcCargos().getCargo().toString().toUpperCase().equals("GERENTE")) {
                                                            %>
                                                            <option value='<%=empleados.get(i).getIdEmpleado()%>' ><%=empleados.get(i).getNombres() + " " + empleados.get(i).getApellidos()%></option>
                                                            <%
                                                                    }
                                                                }
                                                                }else{
                                                                       List<LcEmpleados> empleados = emp.getLCEmpleados3(EmpresaID);
                                                                for (int i = 0; i < empleados.size(); i++) {
                                                                    System.out.print("listo" + empleados.get(i).getNombres());

                                                                    if (empleados.get(i).getLcCargos().getCargo().toString().toUpperCase().equals("GERENTE")) {
                                                            %>
                                                            <option value='<%=empleados.get(i).getIdEmpleado()%>' ><%=empleados.get(i).getNombres() + " " + empleados.get(i).getApellidos()%></option>
                                                            <%
                                                                    }
                                                                }
                                                                }    
                                                            %> 
                                                        </select>
                                                    </div> 
                                                </div>

                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label>Dirección Domicilio:</label>
                                            <textarea class="form-control" rows="1" id="dir_domicilio" placeholder="Ingrese Dirección Domicilio" name="dir_domicilio"></textarea>
                                        </div>

                                        <div class="form-group">
                                            <label for="comment">Observación:</label>
                                            <textarea class="form-control" rows="1" id="observacion" placeholder="Ingrese Observación" name="observacion"></textarea>
                                        </div>

                                        <div class=" form-group">
                                            <input id="btncrearempleados" type="submit" value="Registrar" class="btn btn-primary"  title="Crear Empleados">
                                        </div>
                                    </div>
                                </form> 
                            </div>
                        </div>
                </div>
            </div>
            <!-- /.content-wrapper -->
            <script src="dist/ruc_jquery_validator.min.js"></script>
            <script src="dist/js/empleados.js"></script>
            <script src="dist/js/ValidaNumeros.js"></script>
            <script src="dist/js/ValidaEmailOk.js"></script>            
             <script type="text/javascript">
            $(function(){
                //Para escribir solo numeros    
                $('#celular').validCampoFranz('0123456789');    
                $('#telefono').validCampoFranz('0123456789');  
                  $('#identificacion').validCampoFranz('0123456789'); 
                  
            });
            
            
        </script> 
        </body>
    </html>

