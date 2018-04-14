/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function frm_agencia()
{
   // var empresa = $('#empresa2').val();
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("agencia?accion=agregar", {}, function () {
       /* if (empresa !== "") {
            $('#empresa').css("display", "none");
            $('#empresa2').css("display", "block");
            document.getElementById("empresa2").disabled = true;
        }*/
    });
}
function agencia()
{
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("agencia?accion=listar", {}, function () { });
}

function deleteagencia(data)

{
    if (confirm("Realmente desea eliminar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("agencia?accion=eliminar&id=" + data, {}, function () { });
    }
    agencia();
}

function validaDatos(empresa, empresa2, nombre, opcion, direccion, telefono, telefono2, celular, mail, accion) {

    if (opcion.length > 1) {
        if (nombre.length > 1) {
            if (direccion.length > 1) {
                if (mail.length > 1) {
                    if (celular.length > 1 || telefono2.length > 1 || telefono.length > 1) {

                        return true;

                    } else {
                        //alert("Debe Ingresar al menos 1 Télefono");
                         //MsgSalidaModal('Alerta!..','Debe Ingresar al menos 1 Télefono','Cerrar');
                         MsgSalidaModalA('Debe Ingresar al menos 1 Télefono');
                    }
                } else {
                   // alert("Debe Ingresar un correo");
                    MsgSalidaModalA('Debe Ingresar un correo');
                }
            } else {
                //alert("Debe ingresar una Direccion");
                MsgSalidaModalA('Debe ingresar una Direccion');
            }
        } else {
            //alert("Debe ingresar un Nombre");
            MsgSalidaModalA('Debe ingresar un Nombre');
        }
    } else {
        //alert("Debe elegir tipo de Agencia");
        MsgSalidaModalA('Debe elegir tipo de Agencia');
    }

    return false;
}

function ConnsultaDatosID(str)
{

    if (confirm("Realmente desea actualizar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("agencia?accion=buscaID&id=" + str, {}, function () { });
    }

}


$('#btncrearagencia').click(function (e) {
    e.preventDefault();

    var empresa = $("#empresa").val();
    var nombre = $("#nombre").val();
    var opcion = $("#opcion").val();
    var direccion = $("#direccion").val();
    var telefono = $("#telefono").val();
    var telefono2 = $("#telefono2").val();
    var celular = $("#celular").val();
    var mail = $("#mail").val();
    var accion = $("#accion").val();
    
    
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
      //alert("Email Ingresado es incorrecto");
      MsgSalidaModalA('Email Ingresado es incorrecto');
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
       //alert("El numero de Celular tiene más de 10 digitos, ingrese un numero correcto"); 
       MsgSalidaModalA('El numero de Celular tiene más de 10 digitos, ingrese un numero correcto');
       return true;
    }
     
     if(telefono.length >10){
       MsgSalidaModalA("El numero de teléfono tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
    if(telefono2.length >10){
       MsgSalidaModalA("El numero de teléfono2 tiene más de 10 digitos, ingrese un numero correcto"); 
       return true;
    }
    
    
    
    

        if (validaDatos(empresa, nombre, opcion, direccion, telefono, telefono2, celular, mail, accion)) {

            var parametros = {
                "accion": accion,
                "empresa": empresa,
               // "empresa2": empresa2,
                "nombre": nombre,
                "opcion": opcion,
                "direccion": direccion,
                "telefono": telefono,
                "telefono2": telefono2,
                "celular": celular,
                "mail": mail

            };
            $.ajax({
                data: parametros,
                url: 'agencia',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    if (response) {
                        MsgSalidaModalM(response);
                        frm_agencia();//vuelvo a llamar a la pantalla
                    }
                }
            });



        }
   /* }
     if(empresa2 === ""){
        empresa2 = 0;
    if (validaDatos(empresa, nombre, opcion, direccion, telefono, telefono2, celular, mail, accion)) {

            var parametros = {
                "accion": accion,
                "empresa": empresa,
                "empresa2": empresa2,
                "nombre": nombre,
                "opcion": opcion,
                "direccion": direccion,
                "telefono": telefono,
                "telefono2": telefono2,
                "celular": celular,
                "mail": mail

            };
            $.ajax({
                data: parametros,
                url: 'agencia',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    if (response) {
                        alert(response);
                        frm_agencia();//vuelvo a llamar a la pantalla
                    }
                }
            });



        }
    } */  
});

$('#btnactagencia').click(function (e) {
    e.preventDefault();

    var empresa = $("#empresa").val();
    var nombre = $("#nombre").val();
    var opcion = $("#opcion").val();
    var direccion = $("#direccion").val();
    var telefono = $("#telefono").val();
    var telefono2 = $("#telefono2").val();
    var celular = $("#celular").val();
    var mail = $("#mail").val();
    var accion = $("#accion").val();
    var idagencia = $("#idagencia").val();


    var parametros = {
        "idagencia": idagencia,
        "accion": accion,
        "empresa": empresa,
        "nombre": nombre,
        "opcion": opcion,
        "direccion": direccion,
        "telefono": telefono,
        "telefono2": telefono2,
        "celular": celular,
        "mail": mail};
    $.ajax({
        data: parametros,
        url: 'agencia',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                MsgSalidaModalA(response);

                agencia();//vuelvo a llamar a la pantalla
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



$("#telefono").mask("(99) 999-9999", {

               // Generamos un evento en el momento que se rellena
               completed:function(){
                   $("#telefono").addClass("ok");
               }
           });
$("#telefono2").mask("(99) 999-9999", {

               // Generamos un evento en el momento que se rellena
               completed:function(){
                   $("#telefono2").addClass("ok");
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
            
            