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
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="icon" type="image/png" href="dist/img/icono4.png" />
<style>
  body { 
  background: url(dist/img/city-wallpaper-18.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.panel-default {
opacity: 0.9;
margin-top:30px;
}
.form-group.last { margin-bottom:0px; } 
</style>
  
</head>

<body>


<!------ Include the above in your HEAD tag ---------->

<img src="dist/img/COGESCORP.png" class="img-rounded" height="100"/>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> COGESCORP S.A.</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                       
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">
                            Empresa:</label>
                        <div class="col-sm-9">
                           
                            <select class="form-control input-sm" name="empresa" required="required" id="empresa">
                                <c:forEach items="${empresas}" var="empresa">
                                 <option value='<c:out value="${empresa.getIdEmpresa()}"/>'><c:out value="${empresa.getRazonSocial()}" /> </option>                         
                              </c:forEach>
                          </select>
                        </div>
                    </div>    
                        
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">
                            Usuario:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="usuario" placeholder="Ingrese su Usuario" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">
                            Contraseña:</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password" placeholder="Ingrese su Contraseña" required>
                        </div>
                    </div>
                        <br>
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-12">
                            <button type="submit" class="btn btn-success btn-lg"   id="btn_logeo">
                                Iniciar</button>
                                 
                        </div>
                    </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <a  id="idMensajeError"  href="#">DDA </a></div>
            </div>
        </div>
    </div>
</div>
</body>
        <script src="dist/js/usuario.js"></script>
<script>

  $(function () {
         document.getElementById("idMensajeError").innerHTML ="";
     var URLactual = window.location;
                var url =URLactual.toString();
                if(/login.jsp/.test(URLactual)){                 
                   url=url.replace("login.jsp","login");                 
                  location.href=url;
                }
  });
</script>

</html>