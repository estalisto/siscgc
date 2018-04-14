<%-- 
    Document   : combo
    Created on : 04/04/2017, 11:19:06 AM
    Author     : ViewSoft
--%>

<%@page import="com.siscogescorp.servicios.ConsultaxCarteraServicios"%>
<%@page import="com.siscogescorp.modelo.LcTipoResultado"%>
<%@page import="java.util.List"%>

<%@page import="java.util.ArrayList"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<select class="input-sm form-control" name="resultado" required="required" id="resultado" disabled="true">
    <option value=''>Resultado</option>
 <%
     ConsultaxCarteraServicios cd = new ConsultaxCarteraServicios();
  int idGestion=Integer.parseInt(request.getParameter("gestion"));
        ArrayList<LcTipoResultado> resultado = cd.getLcTipoResultado(idGestion);
         request.setAttribute("resultado", resultado);
   for(int i=0; i< resultado.size(); i++) {
   %>   
   
    <option value="<%=resultado.get(i).getIdTipoResultado()%>" ><%=resultado.get(i).getNombreTipoResultado()%></option>
   
<%   
   }
%>
</select>
                        
                                                                
                        