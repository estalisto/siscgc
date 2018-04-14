<%-- 
    Document   : index
    Created on : 12-feb-2017, 22:28:05
    Author     : CIMA2015
--%>
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

      <br >
      <ol class="breadcrumb">
            <li><a href="#" onclick="empleados(this)" >Lista Empleados</a></li>
            <li class="active"><a href="#" onclick="frm_empleados(this)">Registrar Empleados</a></li>
             
      </ol>
      
      <div class="col-md-8">

       

          <div class="box box-danger">
            <div class="box-header with-border bg-yellow"   >
              <h3 class="box-title" >Actualizar Empleados</h3>
            </div>
              <form name="form" action="empleados" method="get" class="well">
                <c:forEach items="${emplea}" var="empleao">
                  <div class="box-body">
                        <!-- Color Picker -->
                        
                        <div class="form-group hidden">
                         <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">

                        </div>
                        <div class="col-xs-3 hidden">
                            <div class="form-group">
                                <label>Código:</label>
                                <input type="text" class="form-control" id="idempleado" name="idempleado" value="<c:out value="${empleao.getIdEmpleado()}" />" required="required">
                            </div> 
                        </div>
                        <div class="form-group">
                        <div class="row">
                         <div class="col-lg-6">     
                          <label>Empresa:</label>
                          <select class="form-control" name="empresa"  required="required" id="empresa" disabled="true">
                            <option value="<c:out value="${empleao.getLcEmpresa().getIdEmpresa()}"/>"><c:out value="${empleao.getLcEmpresa().getRazonSocial()}" /></option>
                             <c:forEach items="${empresas}" var="empresa">
                                 <option value="<c:out value="${empresa.getIdEmpresa()}" />" ><c:out value="${empresa.getRazonSocial()}" /></option>
                             </c:forEach>  
                            
                          </select>
                         </div>   
                          <div class="col-lg-6">    
                                        <label>Agencia:</label>
                                        <select class="form-control" name="sucursal"  required="required" id="sucursal">
                                            <option value="<c:out value="${empleao.getLcAgencia().getIdAgencia()}"/>"><c:out value="${empleao.getLcAgencia().getNombre()}" /></option>
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
                                        <option value="<c:out value="${empleao.getLcTiposIdentificacion().getIdTipoIdentificacion()}" />"><c:out value="${empleao.getLcTiposIdentificacion().getDescripcion()}" /></option>
                                        <c:forEach items="${tipIDE}" var="tipo">
                                         <option value="<c:out value="${tipo.getIdTipoIdentificacion()}"/>" ><c:out value="${tipo.getDescripcion()}" /></option>
                                     </c:forEach> 
                                   </select>
                                  </div>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="Ingrese Identificación" required="required" name="identificacion" id="identificacion"  onkeypress="ValidaSoloNumeros()" value="<c:out value="${empleao.getIdentificacion()}" />">
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
                          <label>Nombres y Apellidos:</label>
                            <div class="row">
                                <div class="col-lg-6">
                                  <input type="text" class="form-control" placeholder="Ingrese Nombres" required="required" name="nombres" onkeypress="txNombres()" id="nombres" value="<c:out value="${empleao.getNombres()}"/>">
                                </div>
                                <div class="col-lg-6">
                                  <input type="text" class="form-control" placeholder="Ingrese Apellidos" required="required" name="apellidos" onkeypress="txNombres()" id="apellidos" value="<c:out value="${empleao.getApellidos()}"/>">
                                </div>                                
                              </div>
                        </div>
                        

                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-3">
                                    <div class="form-group">
                                      <label>Lugar de Nacimiento</label>                          
                                      <input type="text" class="form-control" placeholder="Ingrese Lugar de Nacimiento" required="required" onkeypress="txNombres()" name="lugar_nac" id="lugar_nac" value="<c:out value="${empleao.getLugarNacimiento()}"/>">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                  <div class="form-group">
                                    <label>Fecha de nacimiento:</label>
                                    <div class="input-group date">
                                      <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                      </div>
                                        <input type="text" class="form-control pull-right" id="fecha_nac" name="fecha_nac" value="<c:out value="${empleao.getFechaNacimiento()}"/>">
                                    </div>
                                    <!-- /.input group -->
                                  </div>
                                </div>
                                <div class="col-lg-6">
                                  <div class="form-group">
                                    <label>E-Mail</label>
                                    <div class="input-group">
                                      <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                      <input type="email" class="form-control" placeholder="Email" name="email" id="email" value="<c:out value="${empleao.getEmail()}"/>">
                                    </div>
                                  </div>
                                </div>
                            </div>
                        </div>
                      
                      <!--
                      <div class="form-group">  
                        <div class="row">
                           <div class="col-lg-3">
                             <input type="text" class="form-control" placeholder=".col-xs-3">
                           </div>
                           <div class="col-lg-4">
                             <input type="text" class="form-control" placeholder=".col-xs-4">
                           </div>
                           <div class="col-lg-5">
                             <input type="text" class="form-control" placeholder=".col-xs-5">
                           </div>
                         </div>
                      </div> -->
                       <div class="form-group">  
                        <div class="row">
                           <div class="col-lg-3">
                                <div class="form-group">
                                  <label>Télefono:</label>

                                  <div class="input-group">
                                    <div class="input-group-addon">
                                      <i class="fa fa-phone"></i>
                                    </div>
                                    <input type="text" class="form-control" maxlength="15" data-inputmask='"mask": "(99) 999-9999"' data-mask name="telefono" onkeypress="ValidaSoloNumeros()" id="telefono" value="<c:out value="${empleao.getTelefonos() }"/>">
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
                                    <input type="text" class="form-control" maxlength="15" data-inputmask='"mask": "(99) 999-99999"' data-mask name="celular" onkeypress="ValidaSoloNumeros()" id="celular" value="<c:out value="${empleao.getCelular()}"/>">
                                  </div>
                                  <!-- /.input group -->
                                </div>
                           </div>
                           <div class="col-lg-3">
                               <div class="form-group ">                                  
                                  <label class="">Estado Civil:</label>
                                  <select class="form-control" name="est_civil" required="required" id="est_civil" > 
                                       <option value="<c:out value="${empleao.getLcEstadoCivil().getIdEstcivil()}"/>" ><c:out value="${empleao.getLcEstadoCivil().getDescripcion()}"/></option>
                                       <c:forEach items="${estacivil}" var="civil">
                                         <option value="<c:out value="${civil.getIdEstcivil()}" />" ><c:out value="${civil.getDescripcion()}" /></option>
                                     </c:forEach>                            
                                  </select>
                                </div>

                           </div>
                           <div class="col-lg-3">
                                
                                <div class="form-group ">
                                  <label class="">Genero:</label>
                                  <select class="form-control" name="genero" required="required" id="genero" >
                                       <option value="<c:out value="${empleao.getLcGenero().getIdGenero()}"/>" ><c:out value="${empleao.getLcGenero().getDescripcion()}"/></option>
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
                                  <input type="text" class="form-control" placeholder="Ingrese Profesión" required="required" name="profesion" onkeypress="txNombres()" id="profesion" value="<c:out value="${empleao.getProfesion()}"/>">
                                </div>                           
                         </div>
                           <div class="col-lg-3">                        
                                <div class="form-group ">
                                  <label class="">Cargo</label>
                                  <select class="form-control" name="cargo" required="required" id="cargo" disabled="true">
                                       <option value="<c:out value="${empleao.getLcCargos().getIdCargo()}"/>" ><c:out value="${empleao.getLcCargos().getCargo()}" /></option>
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
                                       <option value="<c:out value="${empleao.getIdJefeInmediato()}"/>" ><c:out value="${empleado.getNombres()} ${empleado.getApellidos()}" /></option>
                                        <c:forEach items="${empleados}" var="empleado">
                                            <option value="<c:out value="${empleado.getIdEmpleado()}" />" ><c:out value="${empleado.getNombres()} ${empleado.getApellidos()}" /></option>
                                        </c:forEach>  
                                  </select>
                                </div> 
                           </div>

                         </div>
                      </div>  

                        
                        <div class="form-group">
                          <label>Dirección Domicilio:</label>
                          <textarea id="dir_domicilio" class="form-control" rows="1"   name="dir_domicilio" value="<c:out value="${empleao.getDireccionDomicilio()}"/>"><c:out value="${empleao.getDireccionDomicilio()}"/></textarea>
                        </div>
                        
                        <div class="form-group">
                            <label>Observación:</label>
                            <textarea   id="observacion" class="form-control" rows="1"  name="observacion" value="<c:out value="${empleao.getObservacion()}"/>"><c:out value="${empleao.getObservacion()}" /></textarea>
                        </div>
                        
                        <div class=" form-group">
                           <input id="btnactempleados" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Empleados">
                        </div>
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
  <script src="dist/js/empleados.js"></script>
</body>
</html>

