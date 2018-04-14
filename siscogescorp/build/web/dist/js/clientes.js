function frm_clientes()
{
   // var empresa = $('#empresa2').val();
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("clientes?accion=agregar", {}, function () {
        /*if (empresa !== "") {
            $('#empresa').css("display", "none");
            $('#empresa2').css("display", "block");
            document.getElementById("empresa2").disabled = true;
        }*/
    });
}

function clientes()
{
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("clientes?accion=listar", {}, function () { });
}

function deletecliente(data)

{
    if (confirm("Realmente desea eliminar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("clientes?accion=eliminar&id=" + data, {}, function () { });
        clientes();
    }

}
function obtenerProvincia() {
    var pais = $("#pais").val();

    if (pais !== "") {
        $.post("sistema/combo_prov.jsp", $("#data").serialize(), function (data) {
            $("#provincia").html(data);
        });
        document.getElementById("provincia").disabled = false;
    }
}

function obtenerCiudad() {
    var provincia = $("#provincia").val();

    if (provincia !== "") {
        $.post("sistema/combo_ciudad.jsp", $("#data").serialize(), function (data) {
            $("#ciudad").html(data);
        });
        document.getElementById("ciudad").disabled = false;
    }
}

function validaDatos(empresa, Tipo_Identificacion, identificacion, razon, direccion, pais, provincia, ciudad, contacto,
        email, fono1, ext, celular, accion) {
    if (empresa !== "") {
        if (Tipo_Identificacion !== "") {
            if (identificacion.length > 1) {
                if (razon.length > 1) {
                    if (direccion.length > 0) {
                        if (pais !== "") {
                            if (provincia !== "") {
                                if (ciudad !== "") {
                                    if (contacto.length > 1) {
                                        if (email.length > 1) {
                                            if (fono1.length > 1 || celular.length > 1) {
                                                if (ext.length > 1) {

                                                    return true;

                                                } else {
                                                    MsgSalidaModalA("Debe Ingresar la extension");
                                                }
                                            } else {
                                                MsgSalidaModalA("Ingrese al menos un numero de telefono");
                                            }
                                        } else {
                                            MsgSalidaModalA("Ingrese un email valido");
                                        }
                                    } else {
                                        MsgSalidaModalA("Ingrese un contacto");
                                    }
                                } else {
                                    MsgSalidaModalA("Debe seleccionar una ciudad");
                                }
                            } else {
                                MsgSalidaModalA("Debe seleccionar una provincia");
                            }
                        } else {
                            MsgSalidaModalA("Debe seleccionar un pais");
                        }
                    } else {
                        MsgSalidaModalA("Debe ingresar una direccion");
                    }
                } else {
                    MsgSalidaModalA("Debe ingresar la razon");
                }
            } else {
                MsgSalidaModalA("Debe ingresar Identificacion");
            }
        } else {
            MsgSalidaModalA("Debe seleccionar Tipo Identificacion");
        }
    } else {
        MsgSalidaModalA("Debe seleccionar una Empresa");
    }
    return false;
}

function ConnsultaDatosID(str)
{

    if (confirm("Realmente desea actualizar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("clientes?accion=buscaID&id=" + str, {}, function () { });

    }

}

$('#btncrearclientes').click(function (e) {
    e.preventDefault();
    var empresa = $("#empresa").val();
    var Tipo_Identificacion = $("#Tipo_Identificacion").val();
    var identificacion = $("#identificacion").val();
    var razon = $("#razon").val();
    var direccion = $("#direccion").val();
    var pais = $("#pais").val();
    var provincia = $("#provincia").val();
    var ciudad = $("#ciudad").val();
    var contacto = $("#contacto").val();
    var email = $("#email").val();
    var fono1 = $("#fono1").val();
    var ext = $("#ext").val();
    var celular = $("#celular").val();
   // var empresa2=$('#empresa2').val();
    var accion = $("#accion").val();

    /*if(!validaLongitudCED_RUC_OK()){
      MsgSalidaModalA("Favor verifique la IDENTIFICACIÓN ingresada...");  
        return;
    }  */
    alert("ok");
    
        celular=celular.replace(" ", "");
    celular=celular.replace(" ", "");
    fono1=fono1.replace("(", "");
    fono1=fono1.replace(")", "");
    fono1=fono1.replace("-", "");
    fono1=fono1.replace(" ", "");
   ext=ext.replace("(", "");
    ext=ext.replace(")", "");
    ext=ext.replace("-", "");
    ext=ext.replace(" ", "");
    

    if (validaDatos(empresa, Tipo_Identificacion, identificacion, razon, direccion, pais, provincia, ciudad, contacto,
            email, fono1, fono1, celular, accion)) {

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

    if(celular.length >10){
       MsgSalidaModalA("El numero de Celular tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
     if(fono1.length >10){
       MsgSalidaModalA("El numero de teléfono tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
    
    
        var parametros = {
            "accion": accion,
            "empresa": empresa,
            "Tipo_Identificacion": Tipo_Identificacion,
            "identificacion": identificacion,
            "razon": razon,
            "direccion": direccion,
            "pais": pais,
            "provincia": provincia,
            "ciudad": ciudad,
            "contacto": contacto,
            "email": email,
            "fono1": fono1,
            "ext": ext,
            "celular": celular};
        $.ajax({
            data: parametros,
            url: 'clientes',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    MsgSalidaModalM(response);
                    frm_clientes();//vuelvo a llamar a la pantalla
                } else {
                    MsgSalidaModalM("Ya existe un Cliente con esa Identificacion");
                }
            }
        });



    }
   
});

$('#btnactclientes').click(function (e) {
    e.preventDefault();

    var empresa = $("#empresa").val();
    var Tipo_Identificacion = $("#Tipo_Identificacion").val();
    var identificacion = $("#identificacion").val();
    var razon = $("#razon").val();
    var direccion = $("#direccion").val();
    var pais = $("#pais").val();
    
    var provincia = $("#provincia").val();
    var ciudad = $("#ciudad").val();
    var contacto = $("#contacto").val();
    var email = $("#email").val();
    var fono1 = $("#fono1").val();
    var ext = $("#ext").val();
    var celular = $("#celular").val();
    
    var accion = $("#accion").val();
    var idcliente = $("#idcliente").val();

  
    var parametros = {
        "idcliente": idcliente,
        "accion": accion,
        "empresa": empresa,
        "Tipo_Identificacion": Tipo_Identificacion,
        "identificacion": identificacion,
        "razon": razon,
        "direccion": direccion,
        "pais": pais,
        "provincia": provincia,
        "ciudad": ciudad,
        "contacto": contacto,
        "email": email,
        "fono1": fono1,
        "ext": ext,
        "celular": celular};
    $.ajax({
        data: parametros,
        url: 'clientes',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                MsgSalidaModalM(response);

                clientes();//vuelvo a llamar a la pantalla
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
function validaselector() {
    var Tipo_Identificacion = $("#Tipo_Identificacion").val();
    if (Tipo_Identificacion !== "") {
        document.getElementById("identificacion").disabled = false;
    }
}
/*
 $("#identificacion").validarCedulaEC({
  onValid: function () {
   $('#no_value').css("display", "none");
   $('#valido').css("display", "block");
 validaLongitudCED_RUC_HTML();
  },
  onInvalid: function () {
    $('#no_value').css("display", "block");
    $('#valido').css("display", "none");
   validaLongitudCED_RUC_HTML();
  }
}); */


function validaLongitudCED_RUC_HTML(){
                var Tipo_Identificacion = $("#Tipo_Identificacion").val();
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

function validaLongitudCED_RUC(){
                var Tipo_Identificacion = $("#Tipo_Identificacion").val();
                 var Identificacion = $('#identificacion').val();
                  var  validaLength = document.getElementById('validaLength');
                  validaLength.innerText ="";
                  validaLength.style.display = 'none';
                if (Tipo_Identificacion==="1"){
                    if(Identificacion.length>10){
                       validaLength.innerText = "La cédula debe contener máximo 10 digitos";
                       validaLength.style.color="#f00";
                        validaLength.style.display = '';
                     
                    }
                    
                }
                 if (Tipo_Identificacion==="2"){
                    if(Identificacion.length>13){
                       validaLength.innerText = "El RUC debe contener máximo 13 digitos";
                       validaLength.style.color="#f00";
                        validaLength.style.display = '';
                    }
                    if(Identificacion.length===10){
                       validaLength.innerText = "El RUC debe contener 13 digitos";
                       validaLength.style.color="#f00";
                        validaLength.style.display = '';
                    }
                    
                }
            }
            
            
function validaLongitudCED_RUC_OK(){               
    var  validaLength = document.getElementById('validaLength').innerHTML;
  var t_ident= document.getElementById('t_identificacion').value;
    if((validaLength==="La cédula es Válida")&&(t_ident==="1")){
        return true;
    }else  if((validaLength==="El R.U.C. es Válido")&&(t_ident==="2")){
     return true;
    }
    else if(validaLength===""){
        return false;
    }else{
         return false;
    }      
    
  
}
function validaLongitudCED_RUC_OK22(){               
    var  validaLength = document.getElementById('validaLength').innerHTML;
  var t_ident= document.getElementById('Tipo_Identificacion').value;
    if((validaLength==="La cédula es Válida")&&(t_ident==="1")){
        return true;
    }else  if((validaLength==="El R.U.C. es Válido")&&(t_ident==="2")){
     return true;
    }
    else if(validaLength===""){
        return false;
    }else{
         return false;
    }      
    
  
}