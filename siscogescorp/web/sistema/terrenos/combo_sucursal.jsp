<%-- 
    Document   : combo
    Created on : 04/04/2017, 11:19:06 AM
    Author     : ViewSoft
--%>

<%@page import="com.siscogescorp.modelo.LcAgencia"%>
<%@page import="com.siscogescorp.servicios.DocumentosServicios"%>
<%@page import="com.siscogescorp.servicios.ConsultaxCarteraServicios"%>
<%@page import="com.siscogescorp.modelo.LcTipoResultado"%>
<%@page import="java.util.List"%>

<%@page import="java.util.ArrayList"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<select class="input-sm form-control" name="sucursal" required="required" id="sucursal" disabled="true">
    <option value=''>Seleccione Sucursal</option>
 <%
     
     DocumentosServicios doc = new DocumentosServicios();
        int idEmpresa=Integer.parseInt(request.getParameter("empresa"));
        List<LcAgencia> resultado = doc.getLcAgenciasxEmpresa(idEmpresa);
         request.setAttribute("resultado", resultado);
   for(int i=0; i< resultado.size(); i++) {
   %>   
   
    <option value="<%=resultado.get(i).getIdAgencia()%>" ><%=resultado.get(i).getNombre()%></option>
   
<%   
   }
%>
</select>
                        
                                                                
                        