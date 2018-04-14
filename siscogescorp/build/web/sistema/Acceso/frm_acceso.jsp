<%-- 
    Document   : frm_acceso
    Created on : 09/03/2017, 01:33:10 PM
    Author     : ViewSoft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.jsp" flush="true" />
    </aside>
<div class="content-wrapper">
    <ol class="breadcrumb">
        <li><a href="accesos?accion=listar">Lista Accesos</a></li>
        <li class="active"><a href="">Registra Accesos</a></li>

    </ol>
    <section class="content-header">
        <div class="col-md-6">
            <!-- general form elements -->
            <div class="box box-danger">
                <div class="box-header with-border bg-yellow">
                    <h3 class="box-title">Asignar Módulos del Sistema por Nivel de Acceso</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->

                <form name="form" action="accesos" method="get" class="well">

                    <div class="box-body"> 
                        <div class="form-group hidden">
                            <input type="text" class="form-control" placeholder="Ingrese Nombre modulos" value="registrar" required="required" name="accion">

                        </div>
                        <div class="form-group">
                            <label>Empresa:</label>

                            <select class="form-control" name="empresa" required="required">
                                <option value=''>Seleccionar Empresa</option>
                                <c:forEach items="${empresas}" var="empresa">
                                    <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                </c:forEach>                 
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Nivel de Acceso:</label>
                            <select class="form-control" name="rol" required="required">
                                <option value="">Seleccionar rol</option>
                                <c:forEach items="${roles}" var="rol">
                                    <option value="<c:out value="${rol.getIdRol()}"/>" ><c:out value="${rol.getDescripcion()}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Modulos:</label>
                            <select class="form-control" name="modulo" required="required">
                                <option value=''>Seleccionar modulo</option>
                                <option value='1' >Usuario</option>
                                <option value='2' >Clientes</option>
                                <option value='3' >Roles</option>
                            </select>
                        </div>   
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Grabar</button>            
                        <a href="accesos?accion=listar" class="btn btn-primary">  Cancelar</a>                        
                    </div>
                </form>
                </section>
            </div>
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 1.1.0
                </div>
                <strong> <a href="http://softwarefacturyec.com">SoftwareFactury</a>&copy; 2017. </strong>  Laticobsa - Todos los derechos reservados.
            </footer>

        </div> 
        <!-- /.content-wrapper -->


        <!--div class="control-sidebar-bg"></div-->

        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js"></script>
        <!-- Sparkline -->
        <script src="plugins/sparkline/jquery.sparkline.min.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <!-- jvectormap -->
        <script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- ChartJS 1.0.1 -->
        <script src="plugins/chartjs/Chart.min.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="dist/js/pages/dashboard2.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
        <script src="dist/js/validaciones.js"></script>
        </body>
        </html>
