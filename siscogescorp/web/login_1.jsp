<%-- 
    Document   : index
    Created on : 03/02/2018, 18:39:26
    Author     : STALYN GRANDA <https://estalisto.wordpress.com/>
--%>
<%
    String Sidentificacion = "", id_empresa = "", USER_SESION="";
    HttpSession sesion = request.getSession(true);
    
    try {
     if (sesion.getAttribute("SstrUsuarioRol")!= null && sesion.getAttribute("Sstrempresa")!=null && sesion.getAttribute("SstrUSER")!= null){
                  
                  //sesion.invalidate();
                  response.sendRedirect("/siscogescorp/home");
              //out.print("<script>location.replace('login.jsp');<script>");
              return;

        }
        
    } catch (Exception e) {
        Sidentificacion = "000";
       response.sendRedirect("/siscogescorp/login"); 
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es" >

<head>
  <meta charset="UTF-8">
  <title>COGESCORP S.A.</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link href='https://fonts.googleapis.com/css?family=Raleway:300,200' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="dist/css/style.css">


<link rel="icon" type="image/png" href="dist/img/icono4.png" />

  
</head>

<body>

  <div class="menu">
  <ul class="mainmenu clearfix">
    <li class="menuitem">Well</li>
    <li class="menuitem">how</li>
    <li class="menuitem">about</li>
    <li class="menuitem">that?</li>
  </ul>
</div>
<button id="findpass" hidden></button>
<form>
<div class="form">
  <div class="forceColor"></div>
  <div class="topbar">
    <div class="spanColor"></div>
	<select class="input" name="empresa" required="required" id="empresa">
                        <c:forEach items="${empresas}" var="empresa">
                         <option value='<c:out value="${empresa.getIdEmpresa()}"/>'><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                      </c:forEach>
                  </select>
    <input type="text" class="input" id="usuario" placeholder="Usuario"/>
	<input type="password" class="input" id="password" placeholder="Password"/>
  </div>
  <button class="submit" id="btn_logeo" >Login</button>
</div></form><form>
<div class="form">
     <div class="topbar">

 <div  id="mensajeSalida" class="text">Usuario Incorrecto</div>
 <label>okoko</label></div></div></form>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  





</body>
        <script src="dist/js/usuario.js"></script>
<script>
  $(function () {
       
     var URLactual = window.location;
                var url =URLactual.toString();
                if(/login.jsp/.test(URLactual)){                 
                   url=url.replace("login.jsp","login");                 
                  location.href=url;
                }
  });
</script>

</html>