<%-- 
    Document   : modulos_asignados
    Created on : 22/03/2017, 10:21:05 PM
    Author     : ViewSoft
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <!DOCTYPE html>
    <html>
        <head>
        </head>
        <body>
            <!-- Content Wrapper. Contains page content -->
                <div>
                    <section class="content ">
                        
                        <div>  
                        <div class="row">
                            <div id="asig_modulo" class="col-xs-12" >
                                <form name="form1" action="modulos" method="get" class="well">
                                <!--a href="#" class="btn btn-success " >  Asignar +</a
                                <input id="btn_nuevo" onclick="modulos()" type="submit" value="Nuevo" class="btn btn-success"  title="Asignar modulos" >-->
                                <div class="box ">
                                    <div class="box-header">
                                        <h3 class="box-title"  > Módulos del sistema asignado por Rol:</h3> 
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <table id="example1" class="table table-bordered table-hover " >
                                            <thead>
                                                <tr bgcolor='#FEC187'>
                                                    <th>Empresa</th>
                                                    <th>Rol</th>
                                                    <th>Módulos</th>
                                                    <th class="hidden">Orden</th>
                                                    <!--th><center>Opción</center></th-->
                                                </tr>
                                            </thead>
                                            <tbody>
                                               <c:forEach items="${moduloss}" var="asignar">
                                                    <tr>
                                                      <td><c:out value="${asignar.getLcEmpresa().getRazonSocial()}" /></td>
                                                      <td><c:out value="${asignar.getLcRoles().getDescripcion()}" /></td>
                                                      <td><c:out value="${asignar.getLcModulos().getMenuOpciones()}"/></td>
                                                      <td class="hidden"><c:out value="${asignar.getOrdenReg()}"/></td>
                                                      <!--td><center><label>Inactivar &nbsp;</label> <input id="check_inactive" onclick="Desactivar(${asignar.getIdModuloRol()})" name="check_inactive"  type="checkbox"></center></td--> 
                                                    </tr>     
                                                </c:forEach> 
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                </form>
                            </div>
                            <!-- /.col -->
                        </div>
                        </div> 
                    </section>
                    
                </div>
               <script src="dist/js/modulo_rol.js"></script>  
            <!--script>
            $(function () {
              $("#example2").DataTable();
              $('#example1').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false
              });
            });
          </script-->    
        </body>
    </html>
