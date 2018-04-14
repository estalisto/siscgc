<%-- 
    Document   : NuevoPago
    Created on : 17/05/2017, 02:02:03 PM
    Author     : ViewSoft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/jquery.datetimepicker.css">   
        <style type="text/css">
             hr { 
   border-top: 3px solid #4bceb4; 
   border-bottom: 2px dashed #4bceb4; 
   border-left:none; 
   border-right:none; 
   height: 6px; 
 } 
 input {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
select:enabled {
background-color:#FDF9DB;
border: 2px solid #CB8B07;
}
 textarea {
	resize: none;
                } 
 table td {
    bgcolor: #E0ECF8;
}

        </style>
        <title>JSP Page</title>
    </head>
    <body>
            <ol class="breadcrumb">
                <li><a href="#" onclick="listar_pagos();">Recaudaciones</a></li>            
            </ol>
        
<div class="panel panel-warning">
  <div class="panel-heading">
    <h3 class="panel-title">Buscar Cliente</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Identificación:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="identificacion" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Nombres y Apellidos</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="nombre_deudor" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Cartera</label>
    <div class="col-sm-10" >
      <select class="form-control" id="idcartera">
        
      </select>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Buscar</button>
    </div>
  </div>
</form>
  </div>
</div>
        
    </body>
    <script>
        ConsultasMisCarteras();
        function ConsultasMisCarteras(){
document.getElementById("idcartera").innerHTML="";
  $("#idcartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("consultacartera", {"accion" : "AllClientes"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#idcartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
          // var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}
        
        
    </script>
</html>