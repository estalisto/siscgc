/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global parseFloat */

function AJAX()
{
    var xRequest=null;
    if(window.XMLHttpRequest){
        xRequest=new XMLHttpRequest();
    }
    else if(typeof ActiveXObject != "undefined"){
        xRequest=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xRequest;  
} 


$(document).on('ready', function (){
   

    /*--------------------------------------------------------------------------------------
      ------------------------------Creacion empresas---------------------------------------
      --------------------------------------------------------------------------------------*/
 alert("hola");                                                                                                                                                                                                                                                                                                                                                                                                             
                                                                                                                                                                                                                                                                                                                                                                                                                            
$('#body-div-principal').on('click', '#btn-crear-empresa', function () {
 alert("hola");
        if ($("#identificacion").val().length > 1) {
            if( $("#razon").val()!== null) {
              if ($("#telefono").val() !== null) {
                    if ($("#mail").val() !== null) {
                            if ($("#direccion").val() !== null) {
//                                  if( $("#contacto").val()!== null) {
//                                     if ($("#email").val() !== null) {
//                                        if ($("#tel_fijo").val() !== null) {
                                            
                                    var identificacion = $("#identificacion").val();
                                    var razon = $("#razon").val();
                                    var telefono = $("#telefono").val();
                                    var mail = $("#mail").val();
                                    var direccion = $("#direccion").val();;
                                                             
                                
                                    var ajax = AJAX();
                                    ajax.open("GET", "servlet?", true);
                                    ajax.onreadystatechange = function () {
                                        if (ajax.readyState === 4) {
                                            if (ajax.status === 200)
                                            {
                                               
                                                var valor = ajax.responseText;
                                                
                                                if (parseInt(valor) === 1) {
                                                    swal({
                                                        title: "El registro fue ingresado",
                                                        type: "info",
                                                        showConfirmButton: false,
                                                        text: '',
                                                        timer: 2000
                                                    });
//                                                    $('#add-proveedor-moadal').modal('hide');
//                                                    $('#btn-proveedores').fadeOut(6000, function () {
//                                                        $('#btn-proveedores').click();
//                                                        $('#btn-proveedores').fadeIn();
                                                    //});

                                                } else {
                                                    if (parseInt(valor) === 2) {
                                                       
                                                        $("#alert-general").addClass("has-error has-feedback");
                                                        $("#gnr_pro_msg").each(function () {
                                                            $(this).removeClass("hidden");
                                                        });
                                                    } else {
                                                        swal("Error al registrar", "intentelo nuevamente", "error");
                                                        setTimeout(location.reload(), 20000);
                                                    }
                                                }
                                                
                                            }
                                        }
                                    };
                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                    ajax.send('opcion=crear_empresa&identificacion='+identificacion+'&razon='+razon+'&telefono='+telefono+'&mail='+mail+'&direccion='+direccion);
                                    
                                    
//                                } else {
//                                    $("#nom_pro").addClass("has-error has-feedback");
//                                    $("#nom_pro_msg").each(function () {
//                                        $(this).removeClass("hidden");
//                                    });
//                                }
//                            } else {
//                                $("#preced_pro").addClass("has-error has-feedback");
//                                $("#prece_pro_msg").each(function () {
//                                    $(this).removeClass("hidden");
//                                });
//                            }} 
                             }
                        } 
                    } 
                } 
            } }); 
 });


