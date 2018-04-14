<%-- 
    Document   : combo_ciudad
    Created on : 04/04/2017, 11:26:17 AM
    Author     : ViewSoft
--%>

<%@page import="com.siscogescorp.modelo.LcCiudad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.siscogescorp.servicios.EmpresaServicios"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<select class="form-control" name="ciudad" required="required" id="ciudad" disabled="true">
    <option value='' >Seleccionar Ciudad</option>
 <%
     EmpresaServicios es =new EmpresaServicios();
  int provincia=Integer.parseInt(request.getParameter("provincia"));
        ArrayList<LcCiudad> ciudades = es.getLcEmpCiudadprov(provincia);
        request.setAttribute("ciudades", ciudades);
   for(int i=0; i< ciudades.size(); i++) {
   %>   
   
    <option value="<%=ciudades.get(i).getIdCiudad()%>" ><%=ciudades.get(i).getCiudad()%></option>
   
<%   
   }
%>
</select>