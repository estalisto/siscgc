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
                <li><a href="#"  onclick="agencia(this)">Lista Agencia</a></li>
                <li class="active"><a href="#"  onclick="frm_agencia(this)">Registrar Agencia</a></li>
            </ol> 
            <div class="col-md-6">



                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow"   >
                        <h3 class="box-title" >Actualizar Agencia</h3>
                    </div>
                    <form name="form" action="agencia" method="get" class="well" >  
                        <c:forEach items="${agent}" var="agency">
                        <div class="box-body">
                            <!-- Color Picker -->
                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="editar" required="required" name="accion" id="accion">

                            </div>
                            
                            <div class="col-xs-3 hidden">
                            <div class="form-group">
                                <label>Código:</label>
                                <input type="text" class="form-control" id="idagencia" name="idagencia" value="<c:out value="${agency.getIdAgencia()}" />" required="required">
                            </div> 
                        </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" required="required" disabled="true">
                                            <option value="<c:out value="${agency.getLcEmpresa().getIdEmpresa()}" />"><c:out value="${agency.getLcEmpresa().getRazonSocial()}" /></option>
                                            <c:forEach items="${empresas}" var="empresa">
                                                <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    </div>          
                                </div>
                                <div class="col-xs-6">
                                    <div class="form-group">    
                                        <label>Opcion Agencia</label>
                                        <select class="form-control" name="opcion" required="required" id="opcion" >
                                            <option value="<c:out value="${agency.getOpcion()}" />"><c:out value="${agency.getOpcion()}" /></option>
                                            <option value='MATRIZ' >Matriz</option>
                                            <option value='SUCURSAL' >Sucursal</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nombre Agencia:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Nombre" required="required" name="nombre" id="nombre" value="<c:out value="${agency.getNombre()}" />">
                            </div>
                            <div class="form-group">
                                <label>Dirección:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Dirección" required="required" name="direccion" id="direccion" value="<c:out value="${agency.getDireccion()}" />">
                            </div>
                            <div class=" form-group">
                                <label>Email de Contacto:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input type="email" class="form-control" placeholder="Email" name="email" required="required"  id="mail" value="<c:out value="${agency.getEmail()}" />">
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
                                            <input type="text" class="form-control" data-inputmask='"mask": "(99) 999-9999"' data-mask name="telefono" id="telefono" value="<c:out value="${agency.getTelefono()}" />">
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
                                            <input type="text" class="form-control" data-inputmask='"mask": "(99) 999-9999"' data-mask name="telefono2" id="telefono2" value="<c:out value="${agency.getTelefono2()}" />">
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
                                            <input type="text" class="form-control" data-inputmask='"mask": "(99) 999-9999"' data-mask name="celular" id="celular" value="<c:out value="${agency.getCelular()}" />">
                                        </div>
                                    </div>  
                                </div>
                            </div>



                            <div class=" form-group">
                                <!-- <button type="submit" class="btn btn-primary fa fa-save"> Registrar</button>-->
                                <input id="btnactagencia" type="submit" value="Actualizar" class="btn btn-primary"  title="Actualizar Agencia">
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
        <script src="dist/js/agencia.js"></script> 
    </body>
</html>


