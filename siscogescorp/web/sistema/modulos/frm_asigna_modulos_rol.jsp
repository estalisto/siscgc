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
                  <ol class="Breadcrumb default">
                    <div class="btn-group btn-breadcrumb">
                        <a href="home" class="btn btn-default"><i class="glyphicon glyphicon-home"></i></a>
                        <a href="#" class="active btn btn-default">ADMINISTRAR ROLES</a>
                        <a href="#" class="btn btn-default" onclick="roles();">ROLES</a>
                    </div>                    
                </ol>

                    <div class="col-md-6">
                        <!-- general form elements -->
                        <div class="box box-danger">
                            <div class="box-header with-border bg-default">
                                <h3 class="box-title">Asignar2 Módulos del Sistema por Nivel de Acceso</h3>
                            </div>
                           <form name="form" action="modulos" method="post" class="pannel panel-body" id="data">

                                <div class="box-body"> 
                                    <!--div class="form-group hidden">
                                        <input type="text" class="form-control" placeholder="Ingrese Nombre Rol" value="listar" required="required" name="accion" id="accion">
                                    </div-->
                                    <div class="form-group">
                                        <label>Empresa:</label>
                                        <select id="empresa" class="form-control" name="empresa" onchange="LlenarROl()">
                                            <option>- Seleccionar -</option>
                                            <c:forEach items="${empresas}" var="empresa">
                                                <option value="<c:out value="${empresa.getIdEmpresa()}" />"><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                                            </c:forEach>                 
                                        </select>
                                    </div> 

                                    <div class="form-group">
                                        <label>Rol:</label>
                                        <select id="rol" class="form-control" name="rol" disabled="true">
                                            <option value=''>Seleccionar Rol</option>
                                            <!--<c:forEach items="${roles}" var="rol">
                                                <option value="<c:out value="${rol.getIdRol()}" />"><c:out value="${rol.getIdRol()}"/> </option>                         
                                            </c:forEach> -->
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <input id="btncrearmodulo" type="submit"  value="Ver Módulos"   class="btn btn-primary"  title="Ver modulos">
                                    </div>
                                </div>
                            </form>
                        </div>    
                    </div>
                    
                    <div id="list_modulo" class="col-md-6" style="display: none" >
                        <form name="form2" action="modulos" method="get" class="well">
                        <div class="box">
                          <div class="box-header">
                            <h3 class="box-title">Lista de Modulos</h3>
                          </div>
                          <!-- /.box-header -->
                          <div class="box-body" style="overflow-y:scroll;">

                            <table id="example1" class="table table-bordered table-hover">
                              <thead>
                              <tr>
                                  <th class="hidden">ID</th>
                                <th>Modulo</th>
                                <th class="hidden">Nivel</th>
                                <th class="hidden">Orden</th>
                                <th><center>Acción</center></th>
                              </tr>
                              </thead>
                              <tbody>

                                  <c:forEach items="${modu}" var="modulo">
                                      <tr>
                                          <td class="hidden"><c:if test="${modulo.getNivel()==0}"><strong><h5 style="color:#337ab7"></c:if>
                                                <c:out value="${modulo.getIdModulo()}" /> 
                                                <c:if test="${modulo.getNivel()==0}" ></h5></strong></c:if>
                                        </td>
                                        <td><c:if test="${modulo.getNivel()==0}" ><strong><h5 style="color:#337ab7"></c:if>
                                                <c:out value="${modulo.getMenuOpciones()}"/>
                                            <c:if test="${modulo.getNivel()==0}" ></h5></strong></c:if>
                                        </td>
                                        <td class="hidden">
                                            <c:if test="${modulo.getNivel()==0}" ><strong></c:if>
                                                <c:out value="${modulo.getNivel()}"/> 
                                            <c:if test="${modulo.getNivel()==0}" ></strong></c:if>
                                        </td>
                                        <td class="hidden">
                                            <c:if test="${modulo.getNivel()==0}" ><strong></c:if>
                                                <c:out value="${modulo.getOrden()}"/> 
                                            <c:if test="${modulo.getNivel()==0}" ></strong></c:if>
                                        </td>
                                        <td><c:if test="${modulo.getNivel()==0}" ><strong><h5 style="color:#337ab7"></c:if>
                                            
                                                <center><label>Activar &nbsp;</label><input id="check_active<c:out value="${modulo.getIdModulo()}" />" onclick="ActivaModulo(${modulo.getIdModulo()},${modulo.getNivel()},${modulo.getGrupo()},${modulo.getOrden()})" name="check_active"  type="checkbox">&nbsp;</center>
                                                <c:if test="${modulo.getNivel()==0}" ></h5></strong></c:if>
                                            </td> 
                                      </tr>     
                                  </c:forEach>  
                              </tbody>

                            </table>
                          </div>
                          <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                        </form>
                    </div>
                    <!-- Main content -->
                    
                    <section class="content ">
                        <div id="pagetable">  
                        </div> 
                    </section>
                    
                </div>
                <script src="dist/js/modulo_rol.js"></script>  
            <script>
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
          </script>    
        </body>
    </html>

