<%-- 
    Document   : table_empleados
    Created on : 04/04/2017, 04:02:31 PM
    Author     : ViewSoft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table id="example1" class="table table-bordered table-hover">
                <thead>
                <tr bgcolor='#FEC187'>
                  <th>ID</th>
                  <th>Identificación</th>
                  <th>Nombre</th>
                  <th>Cargo </th>
                  <th>Profesión </th>
                  <th>E-Mail </th>
                  <th>Estado/Civil </th>
                  <th>Lugar/Nacimiento </th>
                  <th>Fecha </th>
                  <th>Télefonos </th>
                  <th>Fecha Ingreso </th>
                  <th>Observación </th>
                  <th>Acción </th>
                </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${empleados}" var="empleado">
                        <tr>
                          <td><c:out value="${empleado.getIdEmpleado()}" /> </td>
                          <td><c:out value="${empleado.getIdentificacion()}" /> </td>
                          <td><c:out value="${empleado.getNombres()}  ${empleado.getApellidos()}" /> </td>
                          <td><c:out value="${empleado.getLcCargos().getCargo()}" /> </td>
                          <td><c:out value="${empleado.getProfesion()}" /> </td>
                          <td><c:out value="${empleado.getEmail()}" /> </td>
                          <td><c:out value="${empleado.getLcEstadoCivil().getDescripcion()}" /> </td>
                          <td><c:out value="${empleado.getLugarNacimiento()}" /> </td>
                          <td><c:out value="${empleado.getFechaNacimiento()}" /> </td>
                          <td><c:out value="${empleado.getTelefonos()} - ${empleado.getCelular()}" /> </td> 
                          <td><c:out value="${empleado.getFechaCreacion()}" /> </td>
                          <td><c:out value="${empleado.getObservacion()}" /> </td>                         
                          <td><a  onclick="ConnsultaDatosID(${empleado.getIdEmpleado()})" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                              <a onclick="deletempleado(${empleado.getIdEmpleado()})"> <span  class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>     
                        </tr>     
                    </c:forEach>  

            

           
                </tbody>

              </table>
