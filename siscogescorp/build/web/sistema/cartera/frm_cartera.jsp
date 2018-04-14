<%-- 
    Document   : frm_cartera
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
                <li><a href="#"  onclick="cartera(this)">Lista Carteras Deudores</a></li>
                <li class="active"><a href="#"  onclick="frm_cartera(this)">Registrar Carteras Deudores</a></li>

            </ol>

            <div class="col-md-6">


                <!-- general form elements -->
                <div class="box box-danger">
                    <div class="box-header with-border bg-yellow">
                        <h3 class="box-title">Ingresar</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->

                    <form name="form" action="cartera" method="get" class="well">

                        <div class="box-body">

                            <div class="form-group hidden">
                                <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="registrar" required="required" name="accion" id="accion">
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" >
                                            <option value=''>Seleccionar Empresa</option>
                                            <c:forEach items="${empresas}" var="empresa">
                                                <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    </div> 
                                </div>
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>Clientes:</label>
                                        <select id="cliente" class="form-control" name="cliente" >
                                            <option value=''>Seleccionar Cliente</option>
                                            <c:forEach items="${clientes}" var="cliente">
                                                <option value="<c:out value="${cliente.getIdCliente()}" />"><c:out value="${cliente.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    </div>  
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nombre de la Cartera de Deudores:</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required="required">
                            </div> 
                            <div class="form-group">
                                <label>Observacion:</label>
                                <textarea id="observacion" class="form-control" rows="1" name="observacion" required="required"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Datos Adicionales:</label>
                                <textarea id="adicional" class="form-control" rows="1" name="adicional" required="required"></textarea>
                            </div>

                            <div class="form-group">
                                <input id="btncrearcartera" type="submit" value="Registrar" class="btn btn-primary"  title="Crear Cartera">
                            </div>

                        </div>
                    </form>
                    <!-- /.box -->

                </div>
            </div>
        </div>

        <script src="dist/js/cartera.js"></script>  
    </body>
</html>


