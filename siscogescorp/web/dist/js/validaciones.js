/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function AJAX()
{
    var xRequest = null;
    if (window.XMLHttpRequest) {
        xRequest = new XMLHttpRequest();
    } else if (typeof ActiveXObject !== "undefined") {
        xRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xRequest;
}

//document.getElementById("guardar").disabled = true;
/*
$('#cedula_valida').on('click', function () {
//   alert("Hola"); 
    var cedula = $('#sel_cedula').val();
    //var empresa =$('#empresa').val();
    
    var ajax = AJAX();
    ajax.open("POST", "usuarios?", true);
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200)
            {

                var valor = ajax.responseText;
                   
                    var separo = valor.split("|"); 
                    var empresas= separo[0];
                    var roles = separo[1];
                    var usuario = separo[2];
                    var clave = separo[3];
                    var observo = separo[4];
                    var registro = separo[5];
                  if(parseInt(registro) === 0){
                      $('#not_found').css("display", "block");
                      $('#found').css("display", "none");
                     document.getElementById("sel_cedula").disabled = false;
                    // document.getElementById("empresa").disabled = false;
//                     document.getElementById("rol").disabled = false;
                     document.getElementById("nusuario").disabled = true;  
                     document.getElementById("ncontrasenia").disabled = true;
                     document.getElementById("observaciones").disabled = true;
                     document.getElementById("btncrearusuario").disabled = true;

                    var x = document.getElementById("empresa");
                    var id_empresa = $.trim(empresas);
                    x.value = parseInt(id_empresa);
                    var y = document.getElementById("rol");
                     var id_rol = $.trim(roles);
                    y.value = parseInt(id_rol);
                    var a = document.getElementById("nusuario");
                     a.value = $.trim(usuario);
                     var b = document.getElementById("ncontrasenia");
                     b.value = $.trim(clave);
                     var c = document.getElementById("observaciones");
                     c.value=$.trim(observo);
                     
                  }else{
                      if(parseInt(valor) === 1){
                     $('#not_found').css("display", "none"); 
                     $('#found').css("display", "block"); 
                     document.getElementById("sel_cedula").disabled = true;
                     document.getElementById("empresa").disabled = false;
                     document.getElementById("rol").disabled = false;
                     document.getElementById("nusuario").disabled = false;  
                     document.getElementById("ncontrasenia").disabled = false;
                     document.getElementById("observaciones").disabled = false;
                     document.getElementById("btncrearusuario").disabled = false;
                 }
                  }
                  if(parseInt(valor) === 2){
                      MsgSalidaModalA("Empleado no registrado.");
                  }

            }
        }
    };
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send('accion=validar&identificacion=' + cedula);
    ComboEmpresa(cedula);
   
   
});
*/
function ExisteUsuario(){
    var identificacion=document.getElementById("sel_cedula").value;
    var accion="validar";
   
if(identificacion.length<1){
       MsgSalidaModalA("Debe ingresar la identificación a consultar");
       // console.log("respuesta"+identificacion);
       return true;
    }else if(identificacion.length < 10){
        MsgSalidaModalA("Verificar la identificación Ingresada");
        //console.log("respuesta2"+identificacion);
       return true;
    }
    
    var parametros = {
                         "identificacion":identificacion,
                         "accion":accion
                          };
                         $.ajax({
                         data:  parametros,
                         url:   'usuarios',
                         type:  'POST',
                         beforeSend: function () {                      
                         } ,
                         success:  function (response) {
                             
                             document.getElementById("sel_cedula").disabled = false;
                            // document.getElementById("empresa").disabled = false;
                    //                     document.getElementById("rol").disabled = false;
                             document.getElementById("nusuario").disabled = true;  
                             document.getElementById("ncontrasenia").disabled = true;
                             document.getElementById("observaciones").disabled = true;
                             document.getElementById("btncrearusuario").disabled = true;
                             
                             document.getElementById("id_msg").innerHTML="";
                             //var bandera=false;
                              console.log("respuesta: "+response);
                              var respuest=response.toString();
                                console.log("valor: "+respuest);
                                
                              if(parseInt(respuest)===1){
                                document.getElementById("id_msg").innerHTML="Identificación no se encuentra asociada a ningun empleado";  
                                document.getElementById("id_msg").style.color="red";
                              }else  if(parseInt(respuest)===2){
                               document.getElementById("id_msg").innerHTML="Existen más de un empleado con la misma identificación";    
                               document.getElementById("id_msg").style.color="red";
                              }else  if(parseInt(respuest)===3){
                                 document.getElementById("id_msg").innerHTML="Identificacion ya se encuentra asociada a un USUARIO";  
                                 document.getElementById("id_msg").style.color="red";
                              }else  if(parseInt(respuest)===4){
                                 document.getElementById("id_msg").innerHTML="Listo para crear el usuario";
                                 document.getElementById("id_msg").style.color="green";
                                       ComboEmpresa(identificacion);   
                                        document.getElementById("sel_cedula").disabled = true;
                                       document.getElementById("empresa").disabled = false;
                                       document.getElementById("rol").disabled = false;
                                       document.getElementById("nusuario").disabled = false;  
                                       document.getElementById("ncontrasenia").disabled = false;
                                       document.getElementById("observaciones").disabled = false;
                                       document.getElementById("btncrearusuario").disabled = false;
                               //  bandera=true;
                              }else  if(parseInt(respuest)===5){
                                 document.getElementById("id_msg").innerHTML="Identificacion ya se encuentra asociada a un USUARIO. Debe actualizar la contraseña al Usuario";  
                                 document.getElementById("id_msg").style.color="red";
                              }
                              /* 
                               if(response){
                                    MsgSalidaModalM(response);
                                    console.log("respuesta"+response);
                               }               
                  1 identificacion no se encuentra asociada a ningun empleado
                  2 Existen mas de un empleado con la misma identificación
                  3 Identificacion ya se encuentra asociada a un USUARIO
                  4 Listo para crear el usuario
                  */
                               
                               
                         }
                        }); 
    
    if(bandera){
      ComboEmpresa(identificacion);   
         document.getElementById("sel_cedula").disabled = true;
        document.getElementById("empresa").disabled = false;
        document.getElementById("rol").disabled = false;
        document.getElementById("nusuario").disabled = false;  
        document.getElementById("ncontrasenia").disabled = false;
        document.getElementById("observaciones").disabled = false;
        document.getElementById("btncrearusuario").disabled = false;
    }else{
         document.getElementById("sel_cedula").disabled = false;
        // document.getElementById("empresa").disabled = false;
//                     document.getElementById("rol").disabled = false;
         document.getElementById("nusuario").disabled = true;  
         document.getElementById("ncontrasenia").disabled = true;
         document.getElementById("observaciones").disabled = true;
         document.getElementById("btncrearusuario").disabled = true;
        
    }
    
    
}


$('#nombre_valida').on('click', function () {
 
    var nombre = $('#nombre').val();
    var ajax = AJAX();
    ajax.open("POST", "sectores?", true);
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200)
            {

                var valor = ajax.responseText;
                    //alert(valor);
                  if($.trim(valor) === "true"){
                      $('#not_found').css("display", "block");
                      $('#found').css("display", "none");
                      document.getElementById("descrip").disabled = true;
                     document.getElementById("guardar").disabled = true;
                      
                     
                  }else{
                     $('#not_found').css("display", "none"); 
                     $('#found').css("display", "block"); 
                     document.getElementById("descrip").disabled = false;
                    document.getElementById("guardar").disabled = false;
                  }

            }
        }
    };
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send('accion=validar&nombre=' + nombre);
    
});

$('#parametro_valida').on('click', function () {
 
    var parametro = $('#nombre').val();
    var ajax = AJAX();
    ajax.open("POST", "parametros?", true);
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200)
            {

                var valor = ajax.responseText;
                    //alert(valor);
                  if($.trim(valor) === "true"){
                      $('#not_found').css("display", "block");
                      $('#found').css("display", "none");
                      document.getElementById("valor").disabled = true;
                     document.getElementById("guardar").disabled = true;
                      
                     
                  }else{
                     $('#not_found').css("display", "none"); 
                     $('#found').css("display", "block"); 
                     document.getElementById("valor").disabled = false;
                    document.getElementById("guardar").disabled = false;
                  }

            }
        }
    };
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send('accion=validar&parametro=' + parametro);
    
});
$('#nusuario').on('change','input', function () {
    var usuario = ('#nusuario').val();
    MsgSalidaModalM(usuario);
});

//Validacion de correos 
function validarEmail(valor) {
    if (/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(valor)){
     MsgSalidaModalM("La dirección de email " + valor + " es correcta!.");
    } else {
     MsgSalidaModalA("La dirección de email es incorrecta!.");
    }
};
    
// Validacion que campos sean numericos
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}
//Validadacion que los campos sean texto
function txNombres() {
    if ((event.keyCode !== 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
        event.returnValue = false;
}


function ComboEmpresa(lv_identificacion){
    var identificacion = lv_identificacion;
    var accion="getEmpresa";    
     var parametros = {
                         "identificacion":identificacion,
                         "accion":accion
                          };
                         $.ajax({
                         data:  parametros,
                         url:   'usuarios',
                         type:  'GET',
                         beforeSend: function () {                      
                         } ,
                         success:  function (response) {
                             
                             if(response){
                               document.getElementById("IdSelect").innerHTML="";
                               document.getElementById("IdSelect").innerHTML=response.toString();  
                                 
                             }
                               
                         }
                        }); 
}
function ComboRolesEmpresa(lv_id_empresa){
    var IdEmpresa = lv_id_empresa;
    var accion="getRolesEmpresas";    
     var parametros = {
                         "IdEmpresa":IdEmpresa,
                         "accion":accion
                          };
                         $.ajax({
                         data:  parametros,
                         url:   'usuarios',
                         type:  'GET',
                         beforeSend: function () {                      
                         } ,
                         success:  function (response) {
                             
                             if(response){
                               document.getElementById("IdRolesEmpresa").innerHTML="";
                               document.getElementById("IdRolesEmpresa").innerHTML=response.toString();  
                                 
                             }
                               
                         }
                        }); 
}

function getRoles(){
   // alert($('#empresa2').val());
        var idEmpresa=$('#IdEmpresaHTML').val();
    ComboRolesEmpresa(idEmpresa);
 
} 
function validaFechaYYYYMMDD(nameInput){
    var fecha = $("#"+nameInput).val();       
    if(validarFormatoFecha(fecha)){
          if(existeFecha(fecha)){            
            if(validarFechaMenorActual(fecha)){
                 return 1;//correcta y menor
              }else{
                   return 2;//correcta y mayor
              }
          }else{
            return 3;//no existe
          }
    }else{
        return 4;//es incorrecta
    }
}
function validaFechaDDMMYYYY(nameInput){
    var fecha = $("#"+nameInput).val();       
    if(validarFormatoFecha2(fecha)){
      //  console.clear();
        // console.log("Formato Valido: "+fecha);
          if(existeFecha2(fecha)){   
          //       console.log("fecha Valida: "+fecha);
            if(validarFechaMenorActual2(fecha)){
            //       console.log("correcta y menor: "+fecha);
           
                  return 1;//correcta y menor
              }else{
              //     console.log("correcta y mayor: "+fecha);
                   return 2;//correcta y mayor
              }
          }else{
              //console.log("Fecha no existe: "+fecha);
              MsgSalidaModalA("La Fecha ingresada NO Existe o esta INCORRECTO EL FORMATO:  <strong>"+fecha+"</strong>");
            return 3;//no existe
          }
    }else{
         //  console.log("Fecha es incorrecta: "+fecha);
           MsgSalidaModalA("La Fecha ingresada es INCORRECTO EL FORMATO: <strong> "+fecha+"</strong>");
        return 4;//es incorrecta
    }
}

function validarFormatoFecha(campo) {
      var RegExPattern = /^\d{2,4}\-\d{1,2}\-\d{1,2}$/;
      if ((campo.match(RegExPattern)) && (campo!='')) {
            return true;
      } else {
            return false;
      }
}
function validarFormatoFecha2(campo) {
      var RegExPattern = /^\d{2,2}\-\d{1,2}\-\d{1,4}$/;
     
      if ((campo.match(RegExPattern)) && (campo!='')) {
            return true;
      } else {
            return false;
      }
}


function existeFecha(fecha){
      var fechaf = fecha.split("-");
      var year = fechaf[0];
      var month = fechaf[1];
      var day = fechaf[2];
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}
function existeFecha2(fecha){
      var fechaf = fecha.split("-");
       var day = fechaf[0];
      var year = fechaf[2];
      var month = fechaf[1];
     
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}
function validarFechaMenorActual(date){
      var x=new Date();
      var fecha = date.split("-");
      x.setFullYear(fecha[0],fecha[1]-1,fecha[2]);
      var today = new Date();
 
      if (x >= today)
        return false;
      else
        return true;
}
function validarFechaMenorActual2(date){
      var x=new Date();
      var fecha = date.split("-");
      x.setFullYear(fecha[2],fecha[1]-1,fecha[0]);
      var today = new Date();
 
      if (x >= today)
        return false;
      else
        return true;
}

function compare_dates(fecha, fecha2)  
      {  
        var xMonth=fecha.substring(3, 5);  
        var xDay=fecha.substring(0, 2);  
        var xYear=fecha.substring(6,10);  
        var yMonth=fecha2.substring(3, 5);  
        var yDay=fecha2.substring(0, 2);  
        var yYear=fecha2.substring(6,10);  
        if (xYear> yYear)  
        {  
            return(true)  
        }  
        else  
        {  
          if (xYear == yYear)  
          {   
            if (xMonth> yMonth)  
            {  
                return(true)  
            }  
            else  
            {   
              if (xMonth == yMonth)  
              {  
                if (xDay> yDay)  
                  return(true);  
                else  
                  return(false);  
              }  
              else  
                return(false);  
            }  
          }  
          else  
            return(false);  
        }  
    }