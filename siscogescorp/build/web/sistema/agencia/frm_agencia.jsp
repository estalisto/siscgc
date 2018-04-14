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
                        <h3 class="box-title" >Registrar Agencia</h3>
                    </div>
                    <form name="form" action="agencia" method="get" class="well" >  
                        <div class="box-body">
                            <!-- Color Picker -->
                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">

                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" required="required">
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
                                        <label>Opcion Agencia</label>
                                        <select class="form-control" name="opcion" required="required" id="opcion" >
                                            <option value='' >Seleccionar tipo Agencia</option>
                                            <option value='MATRIZ' >Matriz</option>
                                            <option value='SUCURSAL' >Sucursal</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nombre Agencia:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Nombre" required="required" name="nombre" id="nombre">
                            </div>
                            <div class="form-group">
                                <label>Dirección:</label>
                                <input type="text" class="form-control" placeholder="Ingrese Dirección" required="required" name="direccion" id="direccion">
                            </div>
                            <div class=" form-group">
                                <label>Email de Contacto:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input type="email" class="form-control" onkeyup="ValidaEmailOk();"  placeholder="email@correo.com" name="email" required="required"  id="mail">
                                </div><span id="emailOK"></span>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <div class="form-group">     
                                        <label>Teléfono:</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-phone"></i>
                                            </div>
                                            <input type="text" class="form-control" placeholder="042555555"  name="telefono" id="telefono">
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
                                            <input type="text" class="form-control" placeholder="042555555" name="telefono2" id="telefono2" >
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
                                            <input type="text" class="form-control" placeholder="0999999999" name="celular" id="celular" >
                                        </div>
                                    </div>  
                                </div>
                            </div>



                            <div class=" form-group">
                                <!-- <button type="submit" class="btn btn-primary fa fa-save"> Registrar</button>-->
                                <input id="btncrearagencia" type="button" value="Registrar" class="btn btn-primary"  title="Crear Agencia">
                            </div>

                        </div>  
                    </form>
                    <!-- /.box-body -->
                </div>




            </div>
            <!-- /.content-wrapper -->


        </div>
        <!-- /.content-wrapper -->
        <!-- ./wrapper -->
        <script src="dist/js/agencia.js"></script> 
        <script src="dist/js/ValidaNumeros.js"></script>
        <script src="dist/js/ValidaEmailOk.js"></script>            
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


