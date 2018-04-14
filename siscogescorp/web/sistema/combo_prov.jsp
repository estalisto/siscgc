<%-- 
    Document   : combo
    Created on : 04/04/2017, 11:19:06 AM
    Author     : ViewSoft
--%>

<%@page import="com.siscogescorp.modelo.LcProvincia"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.siscogescorp.servicios.EmpresaServicios"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<select class="form-control" name="provincia" required="required" id="provincia" disabled="true">
    <option value='' >Seleccionar Provincia</option>
 <%
     EmpresaServicios es =new EmpresaServicios();
  int IdPais=Integer.parseInt(request.getParameter("pais"));
        ArrayList<LcProvincia> provincias = es.getLcEmpProv(IdPais);
        request.setAttribute("provincias", provincias);
   for(int i=0; i< provincias.size(); i++) {
   %>   
   
    <option value="<%=provincias.get(i).getIdProvincia()%>" ><%=provincias.get(i).getProvincia()%></option>
   
<%   
   }
%>
</select>
