function frm_empresa()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empresa?accion=agregar",{},function(){ });
}
function empresa()
{       jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empresa?accion=listar",{},function(){ });
}

function deletempresa(data)
        
{      if(confirm("Realmente desea eliminar los datos")){
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empresa?accion=eliminar&id=" + data,{},function(){ });
        empresa();
    }
        
}
function obtenerProvincia(){
   var pais  = $("#pais").val(); 
   
    if (pais !== ""){
        $.post("sistema/combo_prov.jsp",$("#data").serialize(),function(data){$("#provincia").html(data);});
        document.getElementById("provincia").disabled = false;
    }
}

function obtenerCiudad(){
   var provincia  = $("#provincia").val(); 
   
    if (provincia !== ""){
        $.post("sistema/combo_ciudad.jsp",$("#data").serialize(),function(data){$("#ciudad").html(data);});
        document.getElementById("ciudad").disabled = false;
    }
}
function validaDatos(celular,telefono2,telefono,mail,direccion,ciudad,provincia,pais,nombre,identificacion,t_identificacion,accion){
         if(t_identificacion !== "" ){
              
             if(identificacion.length > 1  ){
                 if(nombre.length > 1 ){
                      if(pais !== ""){
                            if(provincia !== "" ){
                                if(ciudad !== "" ){
                                     if(direccion.length > 1 && direccion.length < 100 ){
                                         if(celular.length > 1 || telefono2.length > 1 || telefono.length > 1 ){
                                             return true;
                                           }else{ MsgSalidaModalA("Debe Ingresar al menos 1 Télefono");}  
                                     }else{ MsgSalidaModalA("Ingrese el campo Dirección");}  
                                 }else{ MsgSalidaModalA("Debe seleccionar una Ciudad");}
                           }else{ MsgSalidaModalA("Debe seleccionar una Provincia");}
                       }else{ MsgSalidaModalA("Debe seleccionar una País");}    
                    }else{ MsgSalidaModalA("Debe ingresar Nombre");}
             }else{ MsgSalidaModalA("Debe ingresar3 Identificación");}
         }else{ MsgSalidaModalA("Debe seleccionar una Tipo Identificación");}        
   return false; 
}

function ConnsultaDatosID(str)        
{  

  if(confirm("Realmente desea actualizar los datos")){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      jQuery("#page-wrapper").load("empresa?accion=buscaID&id=" + str,{},function(){ });
  }
    
}

$('#btncrearempresa').click(function(e){
   e.preventDefault();
   var celular = $("#celular").val();
   var telefono2  = $("#telefono2").val();
   var telefono = $("#telefono").val();
   var mail = $("#mail").val();
   var direccion = $("#direccion").val();
   var ciudad  = $("#ciudad").val();
   var provincia = $("#provincia").val();
   var pais  = $("#pais").val();
   var nombre = $("#nombre").val();
   var identificacion = $("#identificacion").val();
   var t_identificacion = $("#t_identificacion").val();
   var accion = $("#accion").val();
   var sucursal='1';
  
  if(!validaLongitudCED_RUC_OK()){
      MsgSalidaModalA("Favor verifique la IDENTIFICACIÓN ingresada para el tipo de identificación seleccionada...");  
        return;
    }

    valido = document.getElementById('emailOK');
    //valMail=document.getElementById('mail').value;
    emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    //Se muestra un texto a modo de ejemplo, luego va a ser un icono
    if (emailRegex.test(mail)) {
      valido.innerText = "válido";
      valido.style.color="#31B404";

    } else {
      valido.innerText = "incorrecto";
      valido.style.color="#f00";
      MsgSalidaModalA("Email Ingresado es incorrecto");
      return true;
    }
    celular=celular.replace(" ", "");
    celular=celular.replace(" ", "");
    telefono=telefono.replace("(", "");
    telefono=telefono.replace(")", "");
    telefono=telefono.replace("-", "");
    telefono=telefono.replace(" ", "");
   telefono2=telefono2.replace("(", "");
    telefono2=telefono2.replace(")", "");
    telefono2=telefono2.replace("-", "");
    telefono2=telefono2.replace(" ", "");
    if(celular.length >10){
       MsgSalidaModalA("El numero de Celular tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
     if(telefono2.length >10){
       MsgSalidaModalA("El numero de teléfono2 tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
     if(telefono.length >10){
       MsgSalidaModalA("El numero de teléfono tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }


   if(validaDatos(celular,telefono2,telefono,mail,direccion,ciudad,provincia,pais,nombre,identificacion,t_identificacion,accion)){
      
          var parametros = {
                "accion" : accion,
                "t_identificacion" : t_identificacion,
                "identificacion" : identificacion,
                "nombre" : nombre,
                "pais" : pais,
                "provincia" : provincia,
                "ciudad" : ciudad,
                "direccion" : direccion,
                "mail" : mail,
                "telefono" : telefono,
                "telefono2" : telefono2,
                "celular":celular,
                "sucursal" : sucursal };
        $.ajax({
                data:  parametros,
                url:   'empresa',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                          MsgSalidaModalM(response);  
                          frm_empresa();
                      }else{
                          //alert(response);
                          MsgSalidaModalA("Ya existe una empresa con esa Identificacion");
                           
                      }                        
                }
        });
       
       
        
   }
    
});

$('#btnactempresa').click(function(e){
   e.preventDefault();
   
   var celular = $("#celular").val();
   var telefono2  = $("#telefono2").val();
   var telefono = $("#telefono").val();
   var mail = $("#mail").val();
   var direccion = $("#direccion").val();
   var ciudad  = $("#ciudad").val();
   var provincia = $("#provincia").val();
   var pais  = $("#pais").val();
   var nombre = $("#nombre").val();
   var identificacion = $("#identificacion").val();
   var t_identificacion = $("#t_identificacion").val();
   var accion = $("#accion").val();
   var sucursal='1';
   var idempresa= $("#idempresa").val();
   //alert(t_identificacion);
             var parametros = {
                "idempresa":idempresa,
                "accion" : accion,
                "t_identificacion" : t_identificacion,
                "identificacion" : identificacion,
                "nombre" : nombre,
                "pais" : pais,
                "provincia" : provincia,
                "ciudad" : ciudad,
                "direccion" : direccion,
                "mail" : mail,
                "telefono" : telefono,
                "telefono2" : telefono2,
                "celular":celular,
                "sucursal" : sucursal 
                
                 };
        $.ajax({
                data:  parametros,
                url:   'empresa',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           empresa();//vuelvo a llamar a la pantalla
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
    if ((event.keyCode !== 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
        event.returnValue = false;
}
function validaselector(){
   var t_identificacion = $("#t_identificacion").val();
    if(t_identificacion !== "" ){
      document.getElementById("identificacion").disabled = false;  
    }
     
}
/*
 $("#identificacion").validarCedulaEC({
  onValid: function () {
    $('#no_value').css("display", "none");
    $('#valido').css("display", "block");
    //document.getElementById("btncrearempresa").disabled = false; 
    //console.log(this);
  },
  onInvalid: function () {
    $('#no_value').css("display", "block");
    $('#valido').css("display", "none");
    //document.getElementById("btncrearempresa").disabled = true; 
    //console.log(this);
  }
}); */ 



function ValidaEmailOk() {
    
  var mail = $("#mail").val();
  valido = document.getElementById('emailOK');
    //valMail=document.getElementById('mail').value;
  emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
  //alert(mail);
    if (emailRegex.test(mail)) {
        valido.innerText = "válido";
        valido.style.color="#31B404";

    } else {
       valido.innerText = "incorrecto";
       valido.style.color="#f00";
    }  
}

function justNumbers()
{
    //alert("pilas");
   if ((event.keyCode < 48) || (event.keyCode > 57)) 
  event.returnValue = false;
}

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