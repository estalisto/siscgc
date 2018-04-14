function frm_empleados()
{
   //var empresa = $('#empresa2').val();
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empleados?accion=agregar",{},function(){ 
        /*if (empresa !== "") {
            $('#empresa').css("display", "none");
            $('#empresa2').css("display", "block");
            document.getElementById("empresa2").disabled = true;
        }*/
        });
}

function empleados()
{       jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empleados?accion=listar",{},function(){ });
}

function deletempleado(data)
        
{      if(confirm("Realmente desea eliminar los datos")){
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empleados?accion=eliminar&id=" + data,{},function(){ });
        empleados();
    }
        
}
function ListarFiltroInactivo(){
    if(document.getElementById('check_inactivo').checked){
        MsgSalidaModalA("Empleados Inactivos");
        $('#table_active').css("display", "none");
        $('#example1').css("display", "none");
        $('#table_inactivos').css("display", "block");
//        document.getElementById("check_todos").disabled = true;
        document.getElementById("check_activo").disabled = true;
    }
    else{
        $('#table_inactivos').css("display", "none");
        $('#example1').css("display", "block");
//        document.getElementById("check_todos").disabled = false;
        document.getElementById("check_activo").disabled = false;
        
    }
}


function ListarFiltroActivo(){
   
    if(document.getElementById('check_activo').checked){
        MsgSalidaModalA("Empleados Activos");
        $('#table_active').css("display", "block");
        $('#example1').css("display", "none");
        $('#table_inactivos').css("display", "none");
//        document.getElementById("check_todos").disabled = true;
        document.getElementById("check_inactivo").disabled = true;

    }else{
        $('#table_active').css("display", "none");
        $('#example1').css("display", "block");
//        document.getElementById("check_todos").disabled = false;
        document.getElementById("check_inactivo").disabled = false;

    }  
}
    
    

function validaDatos(empresa,t_identificacion,identificacion,nombres,apellidos, lugar_nac, fecha_nac,email,telefono,
                        celular,dir_domicilio,est_civil, genero, profesion,cargo,jefe_directo){
         if(empresa !==""){
            if(t_identificacion !=="" ){
                 if(identificacion.length > 1 ){
                      if(nombres.length > 1 ){
                            if(apellidos.length > 0 ){
                                if(lugar_nac.length > 1 ){
                                     //if(fecha_nac.length > 1 ){
                                        if(email.length > 1 ){
                                            if(celular.length > 1 || telefono.length > 1){
                                                 
                                                      if(est_civil !=="" ){
                                                          if(genero !=="" ){
                                                                if(profesion.length > 1 ){
                                                                    if(cargo !=="" ){
                                                                        if(jefe_directo !=="" ){
                                                                            if(dir_domicilio.length > 1 ){
                                                                            return true;
                                                                            }else{ MsgSalidaModalA("Debe Ingresar la Direccion");}  
                                                                        }else{ MsgSalidaModalA("Debe Elegir un Jefe");}
                                                                    }else{ MsgSalidaModalA("Debe Elegir un cargo");}
                                                                }else{ MsgSalidaModalA("Debe ingresar Profesion");} 
                                                           }else{ MsgSalidaModalA("Debe Elegir un genero");}
                                                    }else{ MsgSalidaModalA("Debe Elegir un estado civil");}
                                                
                                              }else{ MsgSalidaModalA("Ingrese al menos un numero de telefono");} 
                                           }else{ MsgSalidaModalA("Debe Ingresar Email");}
                                        //}else{ MsgSalidaModalA("Debe ingresar Fecha Nacimiento");}
                                    }else{ MsgSalidaModalA("Debe ingresar Lugar Nacimiento");}
                                }else{ MsgSalidaModalA("Debe ingresar Apellidos");}    
                            }else{ MsgSalidaModalA("Debe ingresar Nombres");}
                        }else{ MsgSalidaModalA("Debe ingresar Identificacion");}
                    }else{ MsgSalidaModalA("Debe seleccionar Tipo Identificacion");}
                }else{ MsgSalidaModalA("Debe seleccionar una Empresa");}
   return false; 
}

function ConnsultaDatosID(str)        
{  

  if(confirm("Realmente desea actualizar los datos")){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      jQuery("#page-wrapper").load("empleados?accion=buscaID&id=" + str,{},function(){ });
  }
    
}

$('#btncrearempleados').click(function(e){
   e.preventDefault();
   var empresa = $("#empresa").val();
   var t_identificacion = $("#t_identificacion").val();
   var identificacion = $("#identificacion").val();
   var nombres = $("#nombres").val();
   var apellidos  = $("#apellidos").val();
   var lugar_nac  = $("#lugar_nac").val();
   var fecha_nac = $("#fecha_nac").val();
   var email = $("#email").val();
   var telefono = $("#telefono").val();
   var celular=document.getElementById('celular').value;
   var est_civil  = $("#est_civil").val();
   var genero  = $("#genero").val();
   var profesion  = $("#profesion").val();
   var cargo = $("#cargo").val();
   var jefe_directo = $("#jefe_directo").val();
   var dir_domicilio = $("#dir_domicilio").val();
   var observacion = $("#observacion").val();
   var sucursal = $("#sucursal").val();
    var accion = $("#accion").val();
    
    if(!validaLongitudCED_RUC_OK()){
      MsgSalidaModalA("Favor verifique la IDENTIFICACIÓN ingresada...");  
        return;
    }
    
    
    valido = document.getElementById('emailOK');
    //valMail=document.getElementById('mail').value;
    emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    //Se muestra un texto a modo de ejemplo, luego va a ser un icono
    if (emailRegex.test(email)) {
      valido.innerText = "válido";
      valido.style.color="#31B404";

    } else {
      valido.innerText = "incorrecto";
      valido.style.color="#f00";
      MsgSalidaModalA("Email Ingresado es incorrecto");
      return true;
    }
   console.log("celular: "+celular.length);
    if(celular.length >12){
       MsgSalidaModalA("El numero de Celular tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
     
     if(telefono.length >13){
       MsgSalidaModalA("El numero de teléfono tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
    celular=celular.replace(" ", "");
    celular=celular.replace(" ", "");
    telefono=telefono.replace("(", "");
    telefono=telefono.replace(")", "");
    telefono=telefono.replace("-", "");
    telefono=telefono.replace(" ", "");
 console.log("celular: "+celular+"Telefono: "+telefono);
   if(validaDatos(empresa,t_identificacion,identificacion,nombres,apellidos, lugar_nac, fecha_nac,email,telefono,
                        celular,dir_domicilio,est_civil, genero, profesion,cargo,jefe_directo)){
      //alert("pilas"); 
          var parametros = {
                "accion" : accion,
                "empresa" : empresa,
                //"empresa2":empresa2,
                "t_identificacion" : t_identificacion,
                "identificacion" : identificacion,
                "nombres" : nombres,
                "apellidos" : apellidos,
                "lugar_nac" : lugar_nac,
                "fecha_nac" : fecha_nac,
                "email" : email,
                "telefono" : telefono,
                "celular" : celular,
                "est_civil" : est_civil,
                "genero":genero,
                "profesion" : profesion,
                "cargo" : cargo,
                "jefe_directo" : jefe_directo,
                "dir_domicilio":dir_domicilio,
                "observacion" : observacion,
                "sucursal":sucursal};
        $.ajax({
                data:  parametros,
                url:   'empleados',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           frm_empleados();//vuelvo a llamar a la pantalla
                      } else{
                          MsgSalidaModalA("Este empleado ya se encuentra registrado");
                      }                          
                }
        });
       
       
        
   }
       
});

$('#btnactempleados').click(function(e){
   e.preventDefault();
   
   var empresa = $("#empresa").val();
   var t_identificacion = $("#t_identificacion").val();
   var identificacion = $("#identificacion").val();
   var nombres = $("#nombres").val();
   var apellidos  = $("#apellidos").val();
   var lugar_nac  = $("#lugar_nac").val();
   var fecha_nac = $("#fecha_nac").val();
   var email = $("#email").val();
   var telefono = $("#telefono").val();
   var celular=$("#celular").val();
   var est_civil  = $("#est_civil").val();
   var genero  = $("#genero").val();
   var profesion  = $("#profesion").val();
   var cargo = $("#cargo").val();
   var jefe_directo = $("#jefe_directo").val();
   var dir_domicilio = $("#dir_domicilio").val();
   var observacion = $("#observacion").val();
    var accion = $("#accion").val();
   var idempleado= $("#idempleado").val();
   var sucursal = $("#sucursal").val();

// console.log("celular: "+celular.length);
    if(celular.length >12){
       MsgSalidaModalA("El numero de Celular tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
     
     if(telefono.length >13){
       MsgSalidaModalA("El numero de teléfono tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
    celular=celular.replace(" ", "");
    celular=celular.replace(" ", "");
    telefono=telefono.replace("(", "");
    telefono=telefono.replace(")", "");
    telefono=telefono.replace("-", "");
    telefono=telefono.replace(" ", "");



             var parametros = {
                "idempleado":idempleado,
                "accion" : accion,
                "empresa" : empresa,
                "t_identificacion" : t_identificacion,
                "identificacion" : identificacion,
                "nombres" : nombres,
                "apellidos" : apellidos,
                "lugar_nac" : lugar_nac,
                "fecha_nac" : fecha_nac,
                "email" : email,
                "telefono" : telefono,
                "celular" : celular,
                "est_civil" : est_civil,
                "genero":genero,
                "profesion" : profesion,
                "cargo" : cargo,
                "jefe_directo" : jefe_directo,
                "dir_domicilio":dir_domicilio,
                "observacion" : observacion,
                "sucursal":sucursal};
        $.ajax({
                data:  parametros,
                url:   'empleados',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           
                           empleados();//vuelvo a llamar a la pantalla
                      }                        
                }
        });  
});
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}
//Validadacion que los campos sean texto
function txNombres() {
    if ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
        event.returnValue = false;
}
function validaselector(){
   var Tipo_Identificacion = $("#t_identificacion").val();
    if(Tipo_Identificacion !== "" ){
      document.getElementById("identificacion").disabled = false;  
    }}

  /*  
  $("#identificacion").validarCedulaEC({
  onValid: function () {
    $('#no_value').css("display", "none");
    $('#valido').css("display", "block");

  },
  onInvalid: function () {
    $('#no_value').css("display", "block");
    $('#valido').css("display", "none");

  }
}); */



function validaLongitudCED_RUC_HTML(){
                var Tipo_Identificacion = $("#t_identificacion").val();
                 var Identificacion = document.getElementById('identificacion').value;
                  
                 var  validaLength = document.getElementById('validaLength');
                  validaLength.innerText ="";
                  validaLength.style.display = 'none';
                var longuitud=Identificacion.length;
                  // console.log(">> Identificacion.length: "+longuitud);
                if (Tipo_Identificacion==="1"){
                   // console.log(">> Celuda"+Identificacion);
                    if(Identificacion.length>10){
                     //    console.log(">> Celuda >10"+Identificacion);
                       validaLength.innerText = "La cédula debe contener máximo 10 digitos";
                       validaLength.style.color="#f00";
                       validaLength.style.display = 'Block';
                     return;
                    }else{
                          $("#identificacion").validarCedulaEC({
                            onValid: function () {
                              var okok = Identificacion = document.getElementById('identificacion').value;
                               if((Tipo_Identificacion==="1")&&(okok.length===10)){
                                   //console.log("paso por aqui ced"+Tipo_Identificacion+" long: "+Identificacion.length+" okok "+okok.length);
                                    validaLength.innerText = "La cédula es Válida";
                                     validaLength.style.color="#31B404";
                                     validaLength.style.display = '';
                               }else{
                                     validaLength.innerText = "La cédula es inválida";
                                     validaLength.style.color="#f00";
                                    validaLength.style.display = '';
                               }
                              

                            },
                            onInvalid: function () {
                             // $('#no_value').css("display", "block");
                              //$('#valido').css("display", "none");
                               validaLength.innerText = "La cédula es inválida";
                               validaLength.style.color="#f00";
                              validaLength.style.display = '';
                            }
                          }); 
                    }
                    
                }
                 if (Tipo_Identificacion==="2"){
                    if(Identificacion.length>13){
                       validaLength.innerText = "El RUC debe contener máximo 13 digitos";
                       validaLength.style.color="#f00";
                        validaLength.style.display = '';
                        return;
                    }else if(Identificacion.length===10){
                       validaLength.innerText = "El RUC debe contener 13 digitos";
                       validaLength.style.color="#f00";
                        validaLength.style.display = '';
                        return;
                    }else{
                        $("#identificacion").validarCedulaEC({
                            onValid: function () {
                                var okok2 = Identificacion = document.getElementById('identificacion').value;
                                 if((Tipo_Identificacion==="2")&&okok2.length===13){
                                   console.log("paso por aqui RUC");
                                  validaLength.innerText = "El R.U.C. es Válido";
                                  validaLength.style.color="#31B404";
                                  validaLength.style.display = '';  
                                }else{
                                    validaLength.innerText = "El R.U.C. es inválida";
                                    validaLength.style.color="#f00";
                                    validaLength.style.display = '';
                                }
                              

                            },
                            onInvalid: function () {
                               validaLength.innerText = "La R.U.C. es inválida";
                               validaLength.style.color="#f00";
                              validaLength.style.display = '';
                            }
                          }); 
                        
                    }
                    
                }
               
}




function ClearOptionsFast(id)
            {
                var empresa=$("#empresa").val();
                var accion="ConsultaAgencia";
                document.getElementById(id).options.length = 0;
                document.getElementById(id).disabled=true;
                  var parametros = {
                "accion": accion,
                "empresa": empresa

            };
            $.ajax({
                data: parametros,
                url: 'agencia',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    if (response) {
                        document.getElementById(id).innerHTML = response;
                        document.getElementById(id).disabled=false;
                    }else{
                        MsgSalidaModalA("No existe Agencias creadas para esta Empresa... \nComuniquese con Departamento de SISTEMAS...");
                    }
                }
            });
                 
            }
function validaLongitudCED_RUC_OK(){               
    var  validaLength = document.getElementById('validaLength').innerHTML;
     var t_ident= document.getElementById('t_identificacion').value;
          var ident= document.getElementById('identificacion').value;
    if((validaLength==="La cédula es Válida")&&(t_ident==="1")&&(ident.length ===10)){
        return true;
    }else  if((validaLength==="El R.U.C. es Válido")&&(t_ident==="2")&&(ident.length ===13)){
     return true;
    }
    else if(validaLength===""){
        return false;
    }else{
         return false;
    }      
}

$("#telefono").mask("(99) 999-9999", {

               // Generamos un evento en el momento que se rellena
               completed:function(){
                   $("#telefono").addClass("ok");
               }
           });
$("#celular").mask("999 999 9999", {

    // Generamos un evento en el momento que se rellena
    completed:function(){
        $("#celular").addClass("ok");
    }
});

$("#fecha_nac").mask("9999-99-99", {

    // Generamos un evento en el momento que se rellena
    completed:function(){
        $("#fecha_nac").addClass("ok");
    }
});
            
            