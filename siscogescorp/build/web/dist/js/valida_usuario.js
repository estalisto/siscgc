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


function validador(){
    var nombre = $("#nombre").val();
    var accion = "recuperar";

                    var parametros = {
                         "nombre":nombre,
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
                                    MsgSalidaModalM(response);
                                    document.getElementById("newclave").disabled = false;  
                                    document.getElementById("confirmaclave").disabled = false;
                                    
                                    document.getElementById("btncambio_clave").disabled = false;
                               }else{MsgSalidaModalA("USUARIO NO EXISTENTE");
                               document.getElementById("newclave").disabled = true;  
                                document.getElementById("confirmaclave").disabled = true;}                      
                         }
                        }); 
}