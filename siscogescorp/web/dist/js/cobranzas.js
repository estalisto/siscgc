/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Sgtedeudor(stridDeudor, stridCliente) {
    var idDeudor = stridDeudor;
    var idCliente = stridCliente;
    var accion = "deudor";
    $('#img_cargando').css("display", "block");
    var parametros = {
        "idCliente": idCliente,
        "idDeudor": idDeudor,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                if(parseInt(response) === 0){
                   document.getElementById("siguiente").disabled = true;  
		   $('#img_cargando').css("display", "none");
                }else{
                     cobranzas2(idCliente, parseInt(response));
                }
            }
        }
    });
}
function Sgtedeudor2() {
    var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
    var secQuery = document.getElementById("secuencia_query").value;

              
    var accion = "deudor";
    $('#img_cargando').css("display", "block");
    var parametros = {
        "idCliente": idCliente,
        "idDeudor": idDeudor,
        "secQuery": secQuery,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                if(parseInt(response) === 0){
                   document.getElementById("siguiente").disabled = true;  
		   $('#img_cargando').css("display", "none");
                }else{
                     GestionCliente(idCliente, parseInt(response));
                      $('#img_cargando').css("display", "none");
                }
            }
        }
    });
}
function Antdeudor(stridDeudor, stridCliente) {
    var idDeudor = stridDeudor;
    var idCliente = stridCliente;
    var accion = "anterior";
    $('#img_cargando').css("display", "block");
    var parametros = {
        "idCliente": idCliente,
        "idDeudor": idDeudor,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //alert(response);
                if(parseInt(response) === 0){
                 document.getElementById("anterior").disabled = true;   
		  $('#img_cargando').css("display", "none");
                }else{
                cobranzas2(idCliente, parseInt(response));
                
            }
            }
        }
    });
}
function Antdeudor2() {
    var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
    var secQuery = document.getElementById("secuencia_query").value;
    var accion = "anterior";
    $('#img_cargando').css("display", "block");
    var parametros = {
        "idCliente": idCliente,
        "secQuery":secQuery,
        "idDeudor": idDeudor,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //alert(response);
                if(parseInt(response) === 0){
                 document.getElementById("anterior").disabled = true;   
		  $('#img_cargando').css("display", "none");
                }else{
                GestionCliente(idCliente, parseInt(response));
                 $('#img_cargando').css("display", "none");
            }
            }
        }
    });
}
function cobranzas2(stridCliente, stride){
var idCliente = stridCliente;
var idDeudor= stride;
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("cobranzas?accion=envio&idCliente="+idCliente+"&idDeudor="+idDeudor,{},function(){ });
        

}

function obtenerResultado() {
    var idcliente = $("#idcliente").val();
     var gestion = $("#gestion").val();
    
     document.getElementById("resultado").innerHTML="";
     $("#resultado").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));  
   //  alert(gestion);
     if(gestion === 0 || gestion === "0" ) {  
         return;
     }
    //if(gestion !== 0 || gestion !== "0" ) {   
        $.getJSON("consultacartera", {"accion" : "TiposResulatdos","idcliente":idcliente}, function(result){
             //console.log(result);
            // console.log('size: '+result.tipos_resultado.tipos_resultado);
              $.each(result.tipos_resultado, function(key, val){             
               $("#resultado").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
              });
        });
    //}
    
    
    
}
function obtenerResultado3() {
    var idcliente = $("#idcliente").val();
     var gestion = $("#gestion").val();
    
     document.getElementById("resultado2").innerHTML="";
     $("#resultado2").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));  
   //  alert(gestion);
     if(gestion === 0 || gestion === "0" ) {  
         return;
     }
    //if(gestion !== 0 || gestion !== "0" ) {   
        $.getJSON("consultacartera", {"accion" : "TiposResulatdos","idcliente":idcliente}, function(result){
             //console.log(result);
            // console.log('size: '+result.tipos_resultado.tipos_resultado);
              $.each(result.tipos_resultado, function(key, val){             
               $("#resultado").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
              });
        });
    //}
    
    
    
}

function GuardarRecordatorio()
{
    var id = $('#id').val();
    var accion = "guardar";
    var hora = $('#hora').val();
    var compromiso_pago = $('#datepicker2').val();
    var cliente = $('#idcliente').val();
    
     if(verificaFecha3('datepicker2')!==2){        
          if(verificaFecha3('datepicker2')===1){
              MsgSalidaModalA("La Fecha de Recordatorio tiene que se igual o mayor a la fecha actual");
          }else   if(verificaFecha3('datepicker2')===3){
             MsgSalidaModalA("La Fecha de recordatorio  no valida o no existe."); 
          }else   if(verificaFecha3('datepicker2')===4){
             MsgSalidaModalA("El Formato de la fecha es incorrecto."); 
          }        
      return true;  
    }
    
    
    if (compromiso_pago !== "") {
        if (hora !== "") {
    var parametros = {
        "accion": accion,
        "id": id,
        "hora": hora,
        "compromiso_pago": compromiso_pago,
        "cliente": cliente
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                MsgSalidaModalM(response);
                       // $('#transaccion_table').css("display", "none");
                transacciones_pendientes(cliente,id);
            }
        }
    });
        } else {
            alert("Debe Escoger Hora para el Recordatorio");
        }
    } else {
        alert("Debe elegir una Fecha");
    }

}

function GuardarTransaccion() {
    var id = $('#id').val();
    var accion = "guardar";
    var hora = $('#hora').val();
    var compromiso_pago = $('#datepicker2').val();
    var cliente = $('#idcliente').val();
    var parametros = {
        "accion": accion,
        "id": id,
        "hora": hora,
        "compromiso_pago": compromiso_pago
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                MsgSalidaModalM(response);
               // $('#transaccion_table').css("display", "none");
               //  transacciones_pendientes(cliente,id);
            }
        }
    });
}
function ValidarNota2(){
    var idNota = $('#idNotas').val();
    var idCliente = $('#idcliente').val();
    var idDeudor = $('#id_deudor').val();
    var accion = "notas";
    var txtnota = $('#txtnota').val();
    //alert("Notas: "+txtnota+" Cliente: "+idCliente+" Deudor: "+idDeudor+" accion: "+accion);
         var parametros = {
        "accion": accion,
        "idNota": idNota,
        "idDeudor": idDeudor,
        "txtnota": txtnota,
        "idCliente": idCliente
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                var x = document.getElementById("idNotas");
                x.value = parseInt(response);
            }
           // console.log("MisNotas: "+response.toString());
        }
    });
    
}
function ValidarNota(strcliente,strdeudor){
    var idNota = strnota;
    var idCliente = strcliente;
    var idDeudor = strdeudor;
    var accion = "notas";
    var txtnota = $('#txtnota').val();
    //alert(txtnota);
    if(idNota === ""){
        idNota=0;
        var parametros = {
        "accion": accion,
        "idNota": idNota,
        "idDeudor": idDeudor,
        "txtnota": txtnota,
        "idCliente": idCliente
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //alert(response);
            }
        }
    });
    }else{
        var parametros = {
        "accion": accion,
        "idNota": idNota,
        "idDeudor": idDeudor,
        "txtnota": txtnota,
        "idCliente": idCliente
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //alert(response);
            }
        }
    });
    }
    
}
function validaDatos( resultado, gestion, descripcion,accion,id,cliente) {
    if (gestion !== "") {
        if (resultado !== "") {
            if (descripcion.length > 1 ) {
                
                    return true;
                
            } else {
                MsgSalidaModalM("Debe ingresar una descripcion");
            }
        } else {
            MsgSalidaModalM("Debe elegir un Resultado");
        }
    } else {
        MsgSalidaModalM("Debe elegir una Gestion");
    }
    return false;
}

function obtenerResultado2() {
    var gestion = $("#idcliente").val();

    if (gestion !== "") {
       /* $.post("sistema/gestion/combo_resultado.jsp", $("#data").serialize(), function (data) {
            $("#resultado").html(data);
        });
        document.getElementById("resultado").disabled = false;*/
    $.getJSON("consultacartera", {"accion" : "TiposResulatdos","IdTipoGestion":IdTipoGestion}, function(result){
        // console.log(result);
        // console.log('size: '+result.tipos_resultado.tipos_resultado);
          $.each(result.tipos_resultado, function(key, val){             
           $("#resultado").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
          });
    });
    
    }else {
        
    
    }
}

function GuardarTransaccnormal() {
    var id = $('#id_deudor').val();
    var accion = "transaccion";
    var descripcion = $('#descripcion').val();
    var resultado = $('#resultado').val();
    var tipo_resultado = $('#tiporesultado').val();
    var gestion = $('#gestion').val();
    var cliente = $('#idcliente').val();
    //JG ini
    var monto_compromiso = $('#monto_compromiso').val();
    var comp_pago = $('#datepicker').val();
    var idTransaccion = $('#idTransaccion').val();
    var hora = $('#hora').val();
    var Fechacompromiso_pago = $('#datepicker2').val();
    var xdisabled = document.getElementById("datepicker2").disabled;
    var adisabled = document.getElementById("hora").disabled;
    var bdisabled = document.getElementById("monto_compromiso").disabled;
    var cdisabled = document.getElementById("datepicker").disabled;
     //JG fin

     
   if(gestion === 0 || gestion === "0"){       MsgSalidaModalA("Debe elegir un tipo gestion...");       return;   } 
   if(resultado === 0 || resultado === "0"){   MsgSalidaModalA("Debe elegir un tipo resultado...");     return;   }
   
   
    if (validaDatos(resultado, gestion, descripcion, accion, id, cliente)) {
      //JG ini
        if (resultado === tipo_resultado) {
            
            if ((bdisabled === false) && (cdisabled === false)) {
        if (comp_pago !== "") {
            if (verificaFecha3('datepicker') !== 2) {
                    if (verificaFecha3('datepicker') === 1) {
                        MsgSalidaModalA("La Fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual");
                    } else if (verificaFecha3('datepicker') === 3) {
                        MsgSalidaModalA("La Fecha de Compromiso de Pago no existe.");
                    } else if (verificaFecha3('datepicker') === 4) {
                        MsgSalidaModalA("El Formato de la fecha es incorrecto.");
                    }
                    return true;
                }
               //  alert(idTransaccion);
                   $('#img_cargando').css("display", "block");
            if(monto_compromiso !== ""){
               if(monto_compromiso!=0){
                var parametros = {
                    "accion": accion,
                    "id": id,
                    "descripcion": descripcion,
                    "gestion": gestion,
                    "resultado": resultado,
                    "cliente": cliente,
                    "idTransaccion": idTransaccion,
                    "monto_compromiso": monto_compromiso,
                            "comp_pago": comp_pago,
                            "hora": hora,
                            "tipo_resultado": tipo_resultado,
                            "Fechacompromiso_pago": Fechacompromiso_pago
                };
                $.ajax({
                    data: parametros,
                    url: 'cobranzas',
                    type: 'GET',
                    beforeSend: function () {
                    },
                    success: function (response) {
                    $('#img_cargando').css("display", "none"); 
                        if (response) {
                         
                            MsgSalidaModalM(response);
                            var x = document.getElementById("descripcion");
                            x.value = "";
                            var y = document.getElementById("resultado");
                            y.value = "";
                            var z = document.getElementById("gestion");
                            z.value = "";
                            var A = document.getElementById("datepicker");//monto_compromiso
                            A.value = "";
                            var B = document.getElementById("monto_compromiso");
                            B.value = "";
                                    var c = document.getElementById("datepicker2");
                                    c.value = "";
                                    var j = document.getElementById("hora");
                                    j.value = "";
                                    GestionesJson(cliente,id); 
                                    //transacciones_pendientes(cliente, id);
                                    document.getElementById("monto_compromiso").disabled = true;
                                    document.getElementById("datepicker").disabled = true;
                                    var tip = document.getElementById("tiporesultado");
                                    tip.value = "";
                               // alert("Paso");    
                               // GestionesJson(cliente,id);     
                                } else {
                                    MsgSalidaModalM("Compromiso de pago no Guardado");

                                }
                            }
                        });
                     }else{ MsgSalidaModalM("Debe Ingresar un Monto mayor a 0");}   
                    } else {
                        MsgSalidaModalM("Debe Ingresar un Monto de Compromiso De Pago");
                    }
                } else {
                    MsgSalidaModalM("Debe Ingresar una Fecha de Compromiso De Pago");
                }
            }
            if ((xdisabled === false) && (adisabled === false)) {
                
                if (Fechacompromiso_pago !== "") {
                    if (verificaFecha3('datepicker2') !== 2) {
                        if (verificaFecha3('datepicker2') === 1) {
                            MsgSalidaModalA("La Fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual");
                        } else if (verificaFecha3('datepicker2') === 3) {
                            MsgSalidaModalA("La Fecha de Compromiso de Pago no existe.");
                        } else if (verificaFecha3('datepicker2') === 4) {
                            MsgSalidaModalA("El Formato de la fecha es incorrecto.");
                        }
                        return true;
                    }
                    if (hora !== "") {

                        var parametros = {
                            "accion": accion,
                            "id": id,
                            "descripcion": descripcion,
                            "gestion": gestion,
                            "resultado": resultado,
                            "cliente": cliente,
                            "idTransaccion": idTransaccion,
                            "monto_compromiso": monto_compromiso,
                            "comp_pago": comp_pago,
                            "hora": hora,
                            "tipo_resultado": tipo_resultado,
                            "Fechacompromiso_pago": Fechacompromiso_pago
                        };
                        $.ajax({
                            data: parametros,
                            url: 'cobranzas',
                            type: 'GET',
                            beforeSend: function () {
                            },
                            success: function (response) {

                                if (response) {
                                    MsgSalidaModalM(response);
                                    var x = document.getElementById("descripcion");
                                    x.value = "";
                                    var y = document.getElementById("resultado");
                                    y.value = "";
                                    var z = document.getElementById("gestion");
                                    z.value = "";
                                    var A = document.getElementById("datepicker");//monto_compromiso
                                    A.value = "";
                                    var B = document.getElementById("monto_compromiso");
                                    B.value = "";
                                    var c = document.getElementById("datepicker2");
                                    c.value = "";
                                    var j = document.getElementById("hora");
                                    j.value = "";
                                    GestionesJson(cliente,id); 
                                    //transacciones_pendientes(cliente, id);
                                    document.getElementById("datepicker2").disabled = true;
                                    document.getElementById("hora").disabled = true;
                                    var tip = document.getElementById("tiporesultado");
                                    tip.value = "";
                                  
                                } else {
                                    MsgSalidaModalM("Compromiso de pago no Guardado");

                                }
                            }
                        });
                    } else {
                        MsgSalidaModalM("Debe Ingresar Hora de Recordatorio");
                    }
                } else {
                    MsgSalidaModalM("Debe Ingresar una Fecha de Recordatorio de llamada");
                }
            }
        }
        if (tipo_resultado === "") {
            tipo_resultado = 0;
            //JG fin

          $('#img_cargando').css("display", "block"); 
            var parametros = {
                "accion": accion,
                "id": id,
                "descripcion": descripcion,
                "gestion": gestion,
                "resultado": resultado,
                "tipo_resultado": tipo_resultado,
                "cliente": cliente,
                "idTransaccion": idTransaccion//,
//                "monto_compromiso": monto_compromiso,
//                "comp_pago": comp_pago
            };
            $.ajax({
                data: parametros,
                url: 'cobranzas',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    $('#img_cargando').css("display", "none"); 
                    if (response) {
                        MsgSalidaModalM(response);
                        //$('#transaccion_table').css("display", "none");
                      //JG ini
		        var x = document.getElementById("descripcion");
                        x.value = "";
                        var y = document.getElementById("resultado");
                        y.value = "";
                        var z = document.getElementById("gestion");
                        z.value = "";
                        var A = document.getElementById("datepicker");//monto_compromiso
                        A.value = "";
                        var B = document.getElementById("monto_compromiso");
                        B.value = "";
                        var c = document.getElementById("datepicker2");
                        c.value = "";
                        var j = document.getElementById("hora");
                        j.value = "";    
			    //JG fin
                        //transacciones_pendientes(cliente, id);
                        GestionesJson(cliente,id); 
                        var tip = document.getElementById("tiporesultado");
                        tip.value = "";
                       
                    } else {
                        MsgSalidaModalM("Compromiso de pago no Guardado");

                    }
                }
            });
            }
        }
        
          
}
    
$('#resultado').change(function(e){
   e.preventDefault();
   var resultado = $('#resultado').val();
   var accion="ResulParametro";
  
   var parametros = {
        "accion": accion,
        "resultado": resultado
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
//               alert(response);
               var separo = response.split("|"); 
               var id_reconoce= separo[0];
               var tipo = separo[1];
                var z = document.getElementById("tiporesultado");
                z.value = "";
                z.value = id_reconoce;
                 console.log("tipo resultado: "+parseInt(id_reconoce));
                if(resultado!==parseInt(id_reconoce)){
                    if(parseInt(tipo)==1){
                        console.log("tipo  resultado: "+parseInt(tipo));
                        document.getElementById("monto_compromiso").disabled = false;
                        document.getElementById("datepicker").disabled = false;
                        document.getElementById("datepicker2").disabled = true;
                        document.getElementById("hora").disabled = true;
                        var c = document.getElementById("datepicker2");
                        c.value = "";
                        var j = document.getElementById("hora");
                        j.value = "";
//                        var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                    if(parseInt(tipo)==2){
                    document.getElementById("datepicker2").disabled = false;
                    document.getElementById("hora").disabled = false;
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepicker").disabled = true;
                    var A = document.getElementById("datepicker");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
//                    var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                }
                
            }else{
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepicker").disabled = true;
                    document.getElementById("datepicker2").disabled = true;
                    document.getElementById("hora").disabled = true;
                    var A = document.getElementById("datepicker");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
                    var c = document.getElementById("datepicker2");
                    c.value = "";
                    var j = document.getElementById("hora");
                    j.value = ""; 
                    var tip = document.getElementById("tiporesultado");
                    tip.value = "";
                } 
        }
    });
});

$('#resultado2').change(function(e){
   e.preventDefault();
   var resultado = $('#resultado2').val();
   var accion="ResulParametro";
  
   var parametros = {
        "accion": accion,
        "resultado": resultado
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
//               alert(response);
               var separo = response.split("|"); 
               var id_reconoce= separo[0];
               var tipo = separo[1];
                var z = document.getElementById("tiporesultado");
                z.value = "";
                z.value = id_reconoce;
                 console.log("tipo resultado: "+parseInt(id_reconoce));
                if(resultado!==parseInt(id_reconoce)){
                    if(parseInt(tipo)==1){
                        console.log("tipo  resultado: "+parseInt(tipo));
                        document.getElementById("monto_compromiso").disabled = false;
                        document.getElementById("datepickerCompromiso").disabled = false;
                        document.getElementById("datepickerRecordatorio").disabled = true;
                        document.getElementById("hora").disabled = true;
                        var c = document.getElementById("datepickerRecordatorio");
                        c.value = "";
                        var j = document.getElementById("hora");
                        j.value = "";
//                        var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                    if(parseInt(tipo)==2){
                    document.getElementById("datepickerRecordatorio").disabled = false;
                    document.getElementById("hora").disabled = false;
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepickerCompromiso").disabled = true;
                    var A = document.getElementById("datepickerCompromiso");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
//                    var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                }
                
            }else{
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepickerCompromiso").disabled = true;
                    document.getElementById("datepickerRecordatorio").disabled = true;
                    document.getElementById("hora").disabled = true;
                    var A = document.getElementById("datepickerCompromiso");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
                    var c = document.getElementById("datepickerRecordatorio");
                    c.value = "";
                    var j = document.getElementById("hora");
                    j.value = ""; 
                    var tip = document.getElementById("tiporesultado");
                    tip.value = "";
                } 
        }
    });
});


function selecciona_resultado(){

   var resultado = $('#resultado2').val();
   var accion="ResulParametro";
  
   var parametros = {
        "accion": accion,
        "resultado": resultado
    };
    $.ajax({
        data: parametros,
        url: 'cobranzas',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
//               alert(response);
               var separo = response.split("|"); 
               var id_reconoce= separo[0];
               var tipo = separo[1];
                var z = document.getElementById("tiporesultado");
                z.value = "";
                z.value = id_reconoce;
                 console.log("tipo resultado: "+parseInt(id_reconoce));
                if(resultado!==parseInt(id_reconoce)){
                    if(parseInt(tipo)==1){
                        console.log("tipo  resultado: "+parseInt(tipo));
                        document.getElementById("monto_compromiso").disabled = false;
                        document.getElementById("datepicker4").disabled = false;
                        document.getElementById("datepicker3").disabled = true;
                        document.getElementById("hora").disabled = true;
                        var c = document.getElementById("datepicker3");
                        c.value = "";
                        var j = document.getElementById("hora");
                        j.value = "";
//                        var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                    if(parseInt(tipo)==2){
                    document.getElementById("datepicker3").disabled = false;
                    document.getElementById("hora").disabled = false;
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepicker").disabled = true;
                    var A = document.getElementById("datepicker4");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
//                    var tip = document.getElementById("tiporesultado");
//                    tip.value = "";
                    }
                }
                
            }else{
                    document.getElementById("monto_compromiso").disabled = true;
                    document.getElementById("datepicker4").disabled = true;
                    document.getElementById("datepicker3").disabled = true;
                    document.getElementById("hora").disabled = true;
                    var A = document.getElementById("datepicker4");//monto_compromiso
                    A.value = "";
                    var B = document.getElementById("monto_compromiso");
                    B.value = "";
                    var c = document.getElementById("datepicker3");
                    c.value = "";
                    var j = document.getElementById("hora");
                    j.value = ""; 
                    var tip = document.getElementById("tiporesultado");
                    tip.value = "";
                } 
        }
    });
}
function transacciones_pendientes(cliente,id)
{   
    jQuery("#pagetable").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    jQuery("#pagetable").load("cobranzas?accion=listar_transaccion&idDeudor="+id+"&idCliente="+cliente,{},function(){ });
}

function GuardarCompPago() {
 var comp_pago = $('#datepicker').val();
    var accion = "compromiso";
    var idCliente = $('#idcliente').val();
    var idDeudor = $('#id').val();
    //console.log("valida compromiso: "+verificaFecha3('datepicker'));
    if(verificaFecha3('datepicker')!==2){        
          if(verificaFecha3('datepicker')===1){
              MsgSalidaModalA("La Fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual");
          }else   if(verificaFecha3('datepicker')===3){
             MsgSalidaModalA("La Fecha de Compromiso de Pago no existe."); 
          }else   if(verificaFecha3('datepicker')===4){
             MsgSalidaModalA("El Formato de la fecha es incorrecto."); 
          }        
      return true;  
    }
     //console.log("paso compromiso ");
    //return;
    
    if (comp_pago !== "") {
        var parametros = {
            "accion": accion,
            "idDeudor": idDeudor,
            "idCliente": idCliente,
            "comp_pago": comp_pago
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {

                if (response) {
                    MsgSalidaModalM(response);
                     transacciones_pendientes(idCliente,idDeudor);
                } else {
                    MsgSalidaModalM("Compromiso de pago no Guardado");

                }
            }
        });
    } else {
        MsgSalidaModalM("Debe Ingresar una Fecha de Compromiso De Pago");
    }
}
        
function agregarDireccionModal(){
     var cliente = $('#idcliente').val();
    var idDeudor = $('#id_deudor').val();
    var tDireccion = $('#tDireccion').val();
    var direccion_new = $('#direccion_new').val();
    var accion = "agraga_direccion";
   // alert("agraga_telef?cliente: "+cliente+"Deudor: "+idDeudor+"Telefono: "+tipoTelefono+"New Tele: "+newTelefono+accion);

    if (direccion_new === "") {
        alert("Ingrese una dirección válida");
        return;  
      }
var parametros = {
            "accion": accion,
            "idDeudor": idDeudor,
            "cliente": cliente,
            "tDireccion": tDireccion,
            "direccion_new": direccion_new
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {

                if (response) {
                    alert(response);
                  document.getElementById("direccion_new").value = "";
               // $('#table_direccion').css("display", "none");
               // table_direccion(cliente, idDeudor);
               DireccionesJson(cliente, idDeudor);
                }
            }
        });
    
}   
        


function agregarTelefonosModal(){
    var cliente = $('#idcliente').val();
    var idDeudor = $('#id_deudor').val();
    var tipoTelefono = $('#tTelefono').val();
    var newTelefono = $('#newTelefono').val();
    var accion = "agraga_telef";
    //alert("agraga_telef?cliente: "+cliente+"Deudor: "+idDeudor+"Telefono: "+tipoTelefono+"New Tele: "+newTelefono+accion);
//console.log("okoko"+);
    if (newTelefono === "") {
        alert("Ingrese un numero de telefono");
        return;  
      }
var parametros = {
            "accion": accion,
            "idDeudor": idDeudor,
            "cliente": cliente,
            "tipoTelefono": tipoTelefono,
            "newTelefono": newTelefono
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {

                if (response) {
                    alert(response);
                      document.getElementById("newTelefono").value = "";

                TelefonosJson(cliente, idDeudor);
                }
                
            }
        });
}


function table_telefono(cliente, id)
{
    jQuery("#pagetelefono").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#pagetelefono").load("cobranzas?accion=listar_telefono&idDeudor=" + id + "&idCliente=" + cliente, {}, function () { });
}
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}

function addHtmlTelefonoRef() {
    
    var cont=$("#Cont").val();
    var a=document.getElementById("Cont");
    var cadena = [];
    var elementos="";
    //alert("addHtmlTelfRef "+cont);
    if(cont>0){
        var y=0;
         for (y=0;y<cont;y++){ 
             
            var z=0;
              z=y+1;
          elementos+=" <div class='form-group'> \n\
                             <div class='col-lg-3'> \n\
                                <select class=' form-control' name='tipo_tel_ref' id='tReferenciaRef"+z+"'>\n\
                                    <option value='1' >Domicilio</option>\n\
                                    <option value='2' >Trabajo</option>\n\
                                    <option value='3' >Celular</option>\n\
                                </select>\n\
                             </div>\n\
                             <div class='col-lg-3'>\n\
                                    <input type='text' id='new_telefono_ref"+cont+"' maxlength='10' class='form-control' onkeyup=solo_numeros('new_telefono_ref"+z+"')   value='"+$("#new_telefono_ref"+z).val()+"'>  <div id='MSMnew_telefono_ref"+cont+"'></div>\n\
                             </div>\n\
                       </div>";
          
           
         }
       
    }
    cont++;
    document.getElementById("addHtmlTelfRef").innerHTML="";
    
    document.getElementById("addHtmlTelfRef").innerHTML+=elementos+" <div class='form-group'> <div class='col-lg-3'> \n\
                                                            <select class=' form-control' id='tReferenciaRef"+cont+"' >\n\
                                                                <option value='1' >Domicilio </option>\n\
                                                                <option value='2' >Trabajo</option>\n\
                                                                <option value='3' >Celular</option>\n\
                                                            </select></div>\n\
                                                            <div class='col-lg-3'>\n\
                                                                <input maxlength='10' type='text' class='form-control'  onkeyup=solo_numeros('new_telefono_ref"+cont+"') id='new_telefono_ref"+cont+"'><div id='MSMnew_telefono_ref"+cont+"'></div>    \n\
                                                            </div>\n\
                                                            ";
    a.value=cont;                                                                               
   if(cont==2){
       document.getElementById("addInputButton").disabled = true;
   }
  
  
}
function solo_numeros(id_telefono){
    var nombre_telefono=id_telefono;
    $("#"+nombre_telefono).validCampoFranz('0123456789');
    var input= $("#"+nombre_telefono).val();
      var str =  document.getElementById(nombre_telefono).value;
      var n = str.length;
      document.getElementById("MSM"+nombre_telefono).innerHTML="";
      
      console.log("(0,1): "    + str.substr(0,1));  

       if ((str.substr(0,1)!="0") && (str.substr(0,1)!="")){
        document.getElementById("MSM"+nombre_telefono).innerHTML="Ingrese el formato correcto, debe empezar con 0";              
       }

   
    
    
    
}
function AddReferencias() {
 var cont= $("#Cont").val();
 var cliente = $('#idcliente').val();
 var idDeudor = $('#id_deudor').val();
 var nombre_ref=$("#nombre_ref").val();
 var TipoRef=$("#IdtReferencia").val();

 var accion = "addReferncia";
 if(nombre_ref===""){
     alert("Debe Ingresar el nombre de la referencia.");
     return;
 }
 
 if(cont>0){
     
      document.getElementById("GuardarRef").disabled=true;
     
     var parametros = {
            "accion": accion,
            "idDeudor": idDeudor,
            "cliente": cliente,
            "nombre_ref": nombre_ref,
            "TipoRef": TipoRef
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
               
                if (response) {
                    var i=0;
                    cont++;
                    for (i=1;i<cont;i++){ 
                      var tipo_telef = $('#tReferenciaRef'+i).val();  
                      var NewTelefono = $('#new_telefono_ref'+i).val();

                      if ((typeof NewTelefono !== 'undefined')|| (!NewTelefono.length)||(!NewTelefono)){
                       NewTelefonoReferencia(response,tipo_telef,NewTelefono);
                      }
                    }
                 document.getElementById("GuardarRef").disabled=false;
                 $('#listoModal').modal('hide');
                  alert("Referencia Ingresada exitosamente...");
                  jQuery("#DatosReferencias").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
                   //MuestraDatosReferencias();
                   MuestraDatosReferencias(cliente,idDeudor)
                   var A = document.getElementById("nombre_ref");
                    A.value = "";
                    document.getElementById("addInputButton").disabled = false;
                }
                
            }
        });
     
 }else{
     alert("Debe registrar un número de Teléfono");
 }
  
 
    
    
}




function NewTelefonoReferencia(idReferencia1,tipoTelefono1,newTelefono1){

    var accion = "agraga_telef_ref";
    var idReferencia=idReferencia1;
    var tipoTelefono=tipoTelefono1;
    var newTelefono=newTelefono1;
 //   alert("accion "+accion+" idReferencia: "+idReferencia+" tipoTelefono: "+tipoTelefono+"  newTelefono: "+newTelefono);


    if (typeof newTelefono == 'undefined'){
        alert("Ingrese un numero de telefono");
        return;  
     }
var parametros = {
            "accion": accion,
            "idReferencia": idReferencia,
            "tipoTelefono": tipoTelefono,
            "newTelefono": newTelefono
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {               
                
            }
        });

    
 
    
    
    
}
function MuestraDatosReferencias(idCliente,idDeudor){
    var accion = "tablaReferencia";
    //var idCliente = $('#idcliente').val();
    //var idDeudor = $('#id_deudor').val();
    
    var parametros = {
            "accion": accion,
            "idCliente": idCliente,
            "idDeudor": idDeudor
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                var tablaString="";
                tablaString= response.toString();
                document.getElementById("DatosReferencias").innerHTML="";
                document.getElementById("DatosReferencias").innerHTML+=tablaString;
                
                
            }
        });
    
}

$("#listoModal").click(function(e){
   e.preventDefault();
   var idCliente = $('#idcliente').val();
    var idDeudor = $('#id_deudor').val();
    MuestraDatosReferencias(idCliente,idDeudor)
});


/*
$("#datepicker2").mask("9999-99-99 99:99", {

    // Generamos un evento en el momento que se rellena
    completed:function(){
        $("#datepicker2").addClass("ok");
    }
});*/
$("#datepicker").mask("9999-99-99", {

    // Generamos un evento en el momento que se rellena
    completed:function(){
        $("#datepicker2").addClass("ok");
    }
});
$("#hora").mask("99:99", {

    // Generamos un evento en el momento que se rellena
    completed:function(){
        $("#hora").addClass("ok");
    }
});




function existeFecha(fecha){
      var fechaf = fecha.split("-");
      var year = fechaf[0];
      var month = fechaf[1];
      var day = fechaf[2];
      var date = new Date(year,month,'0');
     // console.log("existe "+date);
      //console.log("existe 2"+date.getDate());
      //console.log("existe 2"+day);
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}
 
function existeFecha2 (fecha) {
        var fechaf = fecha.split("-");
        var y = fechaf[0];
        var m = fechaf[1];
        var d = fechaf[2];
       // console.log("existe "+(new Date(y, m, 0)).getDate());
        return m > 0 && m < 13 && y > 0 && y < 32768 && d > 0 && d <= (new Date(y, m, 0)).getDate();
}   

function validarFechaMenorActual(date){
      var x=new Date();
      var fecha = date.split("-");
      x.setFullYear(fecha[0],fecha[1]-1,fecha[2]);
      //console.log("existe v: "+x);
      var today = new Date();
 
      if (x >= today)
        return false;
      else
        return true;
}
function validarFormatoFecha(campo) {
      var RegExPattern = /^\d{2,4}\-\d{1,2}\-\d{1,2}$/;
      if ((campo.match(RegExPattern)) && (campo!='')) {
            return true;
      } else {
            return false;
      }
}

function verificaFecha(){
  //  console.log("valida");
    var fecha = $("#datepicker2").val();
    //  console.log("fecha: "+fecha);
        var MsgValFecha=document.getElementById("MsgValFecha");  
         document.getElementById("MsgValFecha").innerHTML="";
    if(validarFormatoFecha(fecha)){
          if(existeFecha(fecha)){            
                      if(validarFechaMenorActual(fecha)){
               // alert("La fecha introducida es correcta.");
                     // console.log("La fecha introducida es correcta y es menor.");
                      document.getElementById("MsgValFecha").innerHTML+="La fecha introducida es correcta y es menor."; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
                        }else{
                             console.log("La fecha introducida es correcta y es mayor.");
                        }
          }else{
                //alert("La fecha introducida no existe.");
                     // console.log("La fecha introducida no existe.");
                               document.getElementById("MsgValFecha").innerHTML+="La fecha introducida no existe."; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
          }
    }else{
          //alert("El formato de la fecha es incorrecto.");
             // console.log("El formato de la fecha es incorrecto.");
                       document.getElementById("MsgValFecha").innerHTML+="El formato de la fecha es incorrecto."; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
    }
   
    
    
}

function verificaFecha2(){
  //  console.log("valida");
    var fecha = $("#datepicker").val();
    //  console.log("fecha: "+fecha);
        var MsgValFecha=document.getElementById("fechaCompro");  
         document.getElementById("fechaCompro").innerHTML="";
    if(validarFormatoFecha(fecha)){
          if(existeFecha(fecha)){            
                      if(validarFechaMenorActual(fecha)){
               // alert("La fecha introducida es correcta.");
                     // console.log("La fecha introducida es correcta y es menor.");
                      document.getElementById("fechaCompro").innerHTML+="La fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual"; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
                        }else{
                             console.log("La fecha introducida es correcta y es mayor.");
                        }
          }else{
                //alert("La fecha introducida no existe.");
                     // console.log("La fecha introducida no existe.");
                               document.getElementById("fechaCompro").innerHTML+="La fecha introducida no existe."; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
          }
    }else{
          //alert("El formato de la fecha es incorrecto.");
             // console.log("El formato de la fecha es incorrecto.");
                       document.getElementById("fechaCompro").innerHTML+="El formato de la fecha es incorrecto."; 
                    MsgValFecha.style.color="#f00";
                    MsgValFecha.style.display = '';
    }
   
    
    
}
 function validaHora(){
      var hora = $("#hora").val();
       var horaf = hora.split(":");
        var hh = horaf[0];
        var mm = horaf[1];
        var mensaje=null;
        // console.log("hora: "+hh+" minutos "+mm);
          var MsgValFechaHora=document.getElementById("MsgValHora");  
          //document.getElementById("MsgValHora").innerHTML="";
      if(hh>=0 && hh<=23){
           if(mm>=0 && mm<=59){
         //  console.log("El formato de la Hora es correcto.");
           mensaje="El formato de la Hora es correcto.";
            //document.getElementById("MsgValFechaHora").innerHTML+=mensaje; 
            }else{
            //    console.log("El formato de la Hora es incorrecto.");
                   mensaje="El formato de la Hora es incorrecto.";
                    document.getElementById("MsgValHora").innerHTML+=mensaje; 
                    MsgValFechaHora.style.color="#f00";
                    MsgValFechaHora.style.display = '';
            }
      }else{
         //  console.log("El formato de la Hora es incorrecto.");
              mensaje="El formato de la Hora es incorrecto.";
               document.getElementById("MsgValHora").innerHTML+=mensaje; 
               MsgValFechaHora.style.color="#f00";
       MsgValFechaHora.style.display = '';
      }
     verificaFecha();
     
 }
 
 function verificaFecha3(nameInput){
    var fecha = $("#"+nameInput).val();       
    if(validarFormatoFecha(fecha)){
          if(existeFecha(fecha)){            
            if(validarFechaMenorActual(fecha)){
                  return 1;
              }else{
                   return 2;
              }
          }else{
            return 3;
          }
    }else{
        return 4;
    }
}

function AddTelModal(){
    var AddTelefono="<div id='agrega_datos2' class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel'> "+
                                       " <div class='modal-dialog' role='document'>"+
                                       "   <div class='modal-content'>"+
                                        "    <div class='modal-header'>"+
                                       "       <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+
                                      "        <h4 class='modal-title' id='exampleModalLabel'>Agregar Datos:  <c:out value='${cobro.getNombres()} ${cobro.getApellidos()}'/></h4>"+
                                       "     </div>"+
                                   "         <div class='modal-body'>"+
                                      "          <label>INGRESE NUEVO NÚMERO TELEFONICO:</label>"+
                                         "      <form action='#'  class='form-horizontal' role='form'>  "   +                                             
                                        "        <div class='form-group'>"+
                                            "         <div class='col-lg-3'>"+
                                             "           <select class='form-control' name='tipo_tel' id='tTelefono' >"+
                                               "             <option value='1' >Domicilio</option>"+
                                                  "          <option value='2' >Trabajo</option>"+
                                                  "          <option value='3' >Celular</option>   "    +                                     
                                                   "     </select>"+
                                                  "  </div>"+
                                                   " <div class='col-lg-7'>"+
                                                   "     <input maxlength='10' type='text' class='form-control' id='newTelefono' placeholder='0999999999'>   "+
                                                "    </div>  "+
                                                 "   <div class='col-lg-1'>"+
                                                  "      <button type='button' onclick='agregarTelefonosModal()' class='btn btn-success'>Agregar</button> " +
                                                  "  </div>  "      +                               
                                              "  </div>  </div>"+
                                         "   <div class='modal-footer'>"+
                                            "  <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"+
                                         "   </div>"+
                                       "   </div>"+
                                      "  </div>"+
                                     " </div>" ;
                document.getElementById("modalADDTel").innerHTML = AddTelefono; 
 $("#agrega_datos2").modal();
   $('#newTelefono').validCampoFranz('0123456789');    
    
}

function AddDirModal(){
    var AddDireccion="<div id='agrega_direccion' class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel'> "+
                                       " <div class='modal-dialog' role='document'>"+
                                         " <div class='modal-content'>"+
                                           " <div class='modal-header'>"+
                                            "  <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+
                                            "  <h4 class='modal-title' id='exampleModalLabel'>Agregar Datos:  <c:out value='${cobro.getNombres()} ${cobro.getApellidos()}'/></h4>"+
                                          "  </div>"+
                                           " <div class='modal-body'>       "      +                                                                                 
                                            
                                             "    <div class='row'><div class='col-sm-9'><dt>INGRESE NUEVA DIRECCIÓN:</dt></div>"+
                                             "<div class='col-sm-1'><dt style='color:#F5A9A9' id='contador_direccion'>300</dt></div></div>"+
                                           "      <form action='#' class='form-horizontal' role='form'>  "+
                                            "    <div class='form-group'>"+
                                              "        <div class='col-lg-3'>"+
                                                        "    <select class=' form-control' name='tipo_tel' id='tDireccion' >"+
                                                              "    <option value='1' >Domicilio</option>"+
                                                                "  <option value='2' >Trabajo</option>         "   +                              
                                                         " </select>"+
                                                     " </div>"+
                                                 "    <div class='col-lg-7'>              "   +                         
                                                   "     <textarea maxlength='300' class='form-control input-sm' rows='2' id='direccion_new'></textarea>"+
                                                    " </div>   "+
                                                  "   <div class='col-lg-1'>      "        +                            
                                                     "   <button type='button'  onclick='agregarDireccionModal()' class='btn btn-success'>Agregar</button>"+
                                                  "   </div>  "+
                                              "  </div>    "+
                                            "  </form> "+
                                            "</div>"+
                                           " <div class='modal-footer'>"+
                                           "   <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"+
                                           " </div>"+
                                        "  </div>"+
                                      "  </div>"+
                                     " </div>" ;
                document.getElementById("modalDireccion").innerHTML = AddDireccion; 
                 var max_chars = 300;

                    $('#max').html(max_chars);

                    $('#direccion_new').keyup(function() {
                        var chars = $(this).val().length;
                        var diff = max_chars - chars;
                        $('#contador_direccion').html(diff);   
                    });
 $("#agrega_direccion").modal();
   $('#direccion_new').validCampoFranz('abcdefghijklmnñopqrstuvwxyzáéíóúüABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ0123456789/-# ');    
    
}





function GestionCliente(stridCliente, stride){
var idCliente = stridCliente;
var idDeudor= stride;
var accion="GestionCliente";
hidden_cartera_cliente('true',stride);
document.getElementById("siguiente").disabled = false; 
document.getElementById("anterior").disabled = false; 


  $.getJSON("cobranzas", {"accion" : accion,"idCliente":idCliente,"idDeudor":idDeudor}, function(result){
         console.log(result);
          $.each(result.ClienteDeudor, function(key, val){             
              document.getElementById("deudor").innerHTML  ="";
              document.getElementById("cliente").innerHTML  ="";
              document.getElementById("labelTotalDeuda").innerHTML  ="";
              document.getElementById("labelTotalVencido").innerHTML  ="";
              document.getElementById("labelPagos").innerHTML  ="";
              document.getElementById("labelSaldos").innerHTML  ="";
              document.getElementById("labelDiasMora").innerHTML  ="";
              //grupo_sub_grupo
              document.getElementById("id_deudor").value=val.IdDeudor;
              document.getElementById("idcliente").value=val.IdCliente;
              console.log('NombresCompletos: '+val.NombresCompletos);            
             document.getElementById("identificacion").innerHTML =val.Identificacion; 
             document.getElementById("deudor").innerHTML  = val.NombresCompletos; 
             document.getElementById("cliente").innerHTML = val.RazonSocialCliente; 
             document.getElementById("grupo_sub_grupo").innerHTML = val.Grupo; 
             document.getElementById("cuenta").value = val.NumCuenta; 
  
           
             document.getElementById("labelTotalVencido").innerHTML = val.TotalVencido; 
             document.getElementById("labelPagos").innerHTML = val.Pago; 
             document.getElementById("labelSaldos").innerHTML = val.Saldo; 
             document.getElementById("labelDiasMora").innerHTML = val.DiasMora; 
             $("#Ciudad").append($("<option>",{value:val.IDCiudad,text:val.Ciudad}));
             document.getElementById("txtnota").value=val.Notas;
             //NotasAdm
             document.getElementById("txtnota_admin").value=val.NotasAdm;
             document.getElementById("idNotas").value=val.IDNotas;
             document.getElementById("idTransaccion").value=val.IDTransaccion;
             document.getElementById("nom_empleado").innerHTML=val.NombreEmpleado;
            
            
            
          });
    });


document.getElementById("gestion").innerHTML="";
document.getElementById("resultado").innerHTML="";
 $("#resultado").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));
 //document.getElementById("idNotas").value="0";
getTiposGestiones();
DireccionesJson(idCliente, idDeudor);
TelefonosJson(idCliente, idDeudor);
GestionesJson(idCliente, idDeudor); 
DetalleCuotasJson(idCliente, idDeudor);
console.log("MuestraDatosReferencias Cliente: "+idCliente+" Id_deudor: "+idDeudor );
MuestraDatosReferencias(idCliente,idDeudor);
//ComprasJson(idCliente, idDeudor);
}
function formatearNumero(nStr) { nStr += ''; x = nStr.split(','); x1 = x[0]; x2 = x.length > 1 ? '.' + x[1] : ''; var rgx = /(\d+)(\d{3})/; while (rgx.test(x1)) { x1 = x1.replace(rgx, '$1' + ',' + '$2'); } return x1 + x2; }
function DireccionesJson(idCliente, idDeudor){
  
    var StringTablaCabecera="<table id='idAllDireccions' class=' table-striped table-bordered dt-responsive table-condensed table-hover' ><thead><tr  bgcolor='#FEC187' width='100%'><th >Tipo</th><th class='sorting_asc' tabindex='0' aria-controls='idAllDireccions' rowspan='1' colspan='1' style='width: 767px;' aria-label='Dirección: activate to sort column descending' aria-sort='ascending'>Dirección</th> <th>Acciones</th></tr></thead><tbody></tbody></table>";
    var accion="listar_direccion";
    document.getElementById("TablaDirecciones").innerHTML  =""; 
    document.getElementById("TablaDirecciones").innerHTML  =StringTablaCabecera;
   
  $(document).ready(function() {	
     //var parametros = {"accion": accion,"idCliente": idCliente,"idDeudor": idDeudor};
    $('#idAllDireccions').DataTable( {
        "ajax": {
            "data": {"accion": "listar_direccion","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "cobranzas",
            "type": "GET"
            },
            "columns": [
                { "data": "TipoDireccion" },
                { "data": "Direccion" },
                { "data": "acciones" }
            ],
            scrollY:        110,
            scrollX:        false,
            scrollCollapse: false,
            paging:         false,
            info: false,
            searching: false,
            columnDefs: [ {
                orderable: true,

                targets:   1
            } ],
            order: [[ 1, 'desc' ]]
    } );
        
  });      
        
}

function TelefonosJson(idCliente, idDeudor){
    
    var TablaTelefonos="<table id='idAllTelefonos' class='table table-striped table-bordered dt-responsive   table-condensed  table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-sm-2'>Tipo</th><th class='col-sm-8'>Télefonos</th><th class='col-sm-2'>Acciones</th></tr></thead><tbody></tbody></table>";
    document.getElementById("table_telefono").innerHTML  =""; 
    document.getElementById("table_telefono").innerHTML  =TablaTelefonos;
      $(document).ready(function() {	
     //var parametros = {"accion": accion,"idCliente": idCliente,"idDeudor": idDeudor};
    $('#idAllTelefonos').DataTable( {
        "ajax": {
            "data": {"accion": "listar_telefono","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "cobranzas",
            "type": "GET"
            },
            "columns": [
                { "data": "TipoTelefono" },
                { "data": "Telefono" },
                { "data": "Llamar" }
            ],
            scrollY:        80,
            scrollX:        false,
            scrollCollapse: false,
            paging:         false,
            info: false,
            searching: false,
            columnDefs: [ {
                orderable: true,

                targets:   1
            } ],
            order: [[ 1, 'desc' ]]
    } );
        
  });   
    
    
    /*$('#idAllTelefonos tbody').remove();
     $.getJSON("cobranzas", {"accion" : "listar_telefono","idCliente":idCliente,"idDeudor":idDeudor}, function(result){
        
          $.each(result.TelefonosDeudor, function(key, val){    
                      // console.log("TelefonosDeudor: "+val.Direccion);
                      // console.log("TelefonosDeudor: "+val.TipoDireccion);
                        $('#idAllTelefonos').append(
                    function() {
                        return "<tr bgcolor='#E0ECF8' width='100%'>"+
                                    "<td>"+val.TipoTelefono+"</td>"+
                                    "<td>"+val.Telefono+"</td>"+
                                    "<td><a  href='#' ><span class='glyphicon glyphicon-phone-alt' aria-hidden='true'></span></a></td>"+                                    
                                "</tr>"; 
                    }
                );
            

//             $("#Ciudad").append($("<option>",{value:val.IDCiudad,text:val.Ciudad}));
           
         });
    });*/
   // DataTableTelefonos();
    
    
}


function GestionesJson(idCliente, idDeudor){
    var TablaTelefonos="<table id='allTrxGestiones' class='table table-striped table-bordered table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FEC187'><th class='col-lg-1'>Gestión</th><th class='col-lg-1'>Respuesta</th><th class='col-lg-5'>Explicación</th><th class='col-lg-1'>Asesor</th><th class='col-lg-1'>Fecha</th></tr></thead><tbody></tbody> </table>";
    document.getElementById("transaccion_table").innerHTML  =""; 
    document.getElementById("transaccion_table").innerHTML  =TablaTelefonos;
        $(document).ready(function() {	
     $('#allTrxGestiones').DataTable( {
        "ajax": {
            "data": {"accion": "listar_transaccion","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "cobranzas",
            "type": "GET"
            },
            "columns": [
                { "data": "TipoGestion" },
                { "data": "Gestion" },
                 { "data": "Descripcion" },
                  { "data": "Oficial" },
                { "data": "fecha" }
            ],
            scrollY:        200,
            scrollX:        false,
            scrollCollapse: false,
            paging:         false,
            info: false,
            searching: false,
            columnDefs: [ {
                orderable: true,

                targets:   4
            } ],
            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            order: [[ 4, 'desc' ]]
    } );
        
  }); 
    
     /* $('#allTrxGestiones tbody').remove();
     $.getJSON("cobranzas", {"accion" : "listar_transaccion","idCliente":idCliente,"idDeudor":idDeudor}, function(result){
        
          $.each(result.GestionesDeudor, function(key, val){    
                      // console.log("TelefonosDeudor: "+val.Direccion);
                      // console.log("TelefonosDeudor: "+val.TipoDireccion);
                        $('#allTrxGestiones').append(
                    function() {
                        return "<tr bgcolor='#E0ECF8' width='100%'>"+
                                    "<td>"+val.TipoGestion+"</td>"+
                                     "<td>"+val.Gestion+"</td>"+
                                      "<td>"+val.Descripcion+"</td>"+
                                       "<td>"+val.Oficial+"</td>"+
                                    "<td>"+val.fecha+"</td>"+                                       
                                "</tr>"; 
                    }
                );
            

//             $("#Ciudad").append($("<option>",{value:val.IDCiudad,text:val.Ciudad}));
           
         });
    }); */
 //   DataTableGestiones();
    
}

function DataTableTelefonos(){
    console.log("idAllTelefonos");
$('#idAllTelefonos').DataTable( {
                     scrollY:        110,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                    info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: true,

                        targets:   1
                    } ],
                    order: [[ 1, 'desc' ]]
                } ); 
        }
function DataTableDirecciones(){
    console.log("idAllDireccions");
$('#idAllDireccions').DataTable({
                   scrollY:        110,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                    info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: true,

                        targets:   1
                    } ],
                    order: [[ 1, 'desc' ]]
                } );
        }
        
function DataTableGestiones(){
    console.log("allTrxGestiones");
           $('#allTrxGestiones').DataTable( {
                    scrollY:        200,
                    scrollX:        false,
                    scrollCollapse: false,
                    paging:         false,
                    info: false,
                    searching: false,
                    columnDefs: [ {
                        orderable: true,

                        targets:   4
                    } ],
                    select: {
                        style:    'os',
                        selector: 'td:first-child'
                    },
                    order: [[ 4, 'desc' ]]
                } );
        }
        
        
        

function ComprasJson(){
    var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
   // console.log("ComprasJson Cliente ini: "+idCliente+" Id_deudor: "+idDeudor );
    var TablaCompras2="<table id='detalle_articulos' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'> <thead><tr bgcolor='#B5EE8E'><th>Referencia de Compras/Artículos</th><th>Descripción de la Compra</th><th>Valor Compra</th><th>Fecha de Compra</th></tr></thead><tbody>";
    document.getElementById("TablaCompras").innerHTML  =""; 
    
    var parametros = {
            "accion": "DetalleCompras",
            "idCliente": idCliente,
            "idDeudor": idDeudor
          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                var tablaString="";
                tablaString= response.toString();
                document.getElementById("TablaCompras").innerHTML+=TablaCompras2+tablaString+"</tbody><tfoot></tfoot>  </table>";
                
              //console.log(TablaCompras2+tablaString+"</tbody><tfoot></tfoot>  </table>");  
            }
        });
    
    
    //document.getElementById("TablaCompras").innerHTML  =TablaCompras2;
 /*
      $(document).ready(function() {	
     $('#detalle_articulos').DataTable({
        "ajax": {
            "data": {"accion": "DetalleCompras","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "cobranzas",
            "type": "GET"
            },
            "columns": [
                { "data": "ReferenciaCompra" },
                { "data": "Descripcion" },
                { "data": "ValorCompra" },
                { "data": "Fecha" }],
            "paging": false,
            "lengthChange": false,
            "info": false,
            "searching": false
    });        
  }); 
    */
    
      //console.log("ComprasJson Cliente fin: "+idCliente+" Id_deudor: "+idDeudor );
}


function DetalleCuotasJson(idCliente, idDeudor){
   // ComprasJson(idCliente, idDeudor);
    var TablaCompras="<table id='detalle_cuotas' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'> <thead><tr bgcolor='#B5EE8E'><th>Artículo</th><th>NºCuota</th><th>Interés</th><th>Mora</th><th>Gastos Cobranzas</th><th>Gastos Adicionales</th><th>Otros Gastos</th><th>Valor Cuota</th><th>Total</th><th>Fecha max Pago</th><th>Fecha Registro</th><th>Pagos Realizados</th><th>Fecha Pagos Realizados</th></tr></thead><tbody></tbody><tfoot></tfoot></table>";
    document.getElementById("TablaDetalleCuotas").innerHTML  =""; 
    document.getElementById("TablaDetalleCuotas").innerHTML  =TablaCompras;
        $(document).ready(function() {	
     $('#detalle_cuotas').DataTable( {
        "ajax": {
            "data": {"accion": "DetalleCuotas","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "cobranzas",
            "type": "GET"
            },
            "columns": [
                { "data": "ReferenciaCompra" },
                { "data": "NumCuota" },
                { "data": "Interes" },
                { "data": "Mora" },
                { "data": "GastosCobranzas" },
                { "data": "GastosAdicionales" },
                { "data": "OtrosGastos" },
                { "data": "ValorCuota" },
                { "data": "Total" },
                { "data": "FechaMaxPago" },
                { "data": "Fecha" },
                { "data": "PagosRealizado" },
                { "data": "FechaPagoRealizado" }
                
            ],
            "paging": false,
            "lengthChange": false,
            "info": false,
            "searching": false
    } );        
  }); 
  
}
function consulcarteras2(stridCliente, stride){      
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("consultacartera?accion=listarR",{},function(){
        // $("#data_recepcion .close").click(); 
         GestionRecaudo(stridCliente, stride);
     });
}


function GestionRecaudo(stridCliente, stride){
var idCliente = stridCliente;
var idDeudor= stride;
var accion="GestionCliente";
hidden_cartera_cliente('true');
document.getElementById("siguiente").disabled = false; 
document.getElementById("anterior").disabled = false; 


  $.getJSON("cobranzas", {"accion" : accion,"idCliente":idCliente,"idDeudor":idDeudor}, function(result){
         //console.log(result);
          $.each(result.ClienteDeudor, function(key, val){             
              document.getElementById("deudor").innerHTML  ="";
              document.getElementById("cliente").innerHTML  ="";
              document.getElementById("labelTotalDeuda").innerHTML  ="";
              document.getElementById("labelTotalVencido").innerHTML  ="";
              document.getElementById("labelPagos").innerHTML  ="";
              document.getElementById("labelSaldos").innerHTML  ="";
              document.getElementById("labelDiasMora").innerHTML  ="";              
              document.getElementById("id_deudor").value=val.IdDeudor;
              document.getElementById("idcliente").value=val.IdCliente;
              //console.log('NombresCompletos: '+val.NombresCompletos);            
             document.getElementById("identificacion").value = val.Identificacion; 
             document.getElementById("deudor").innerHTML  = val.NombresCompletos; 
             document.getElementById("cliente").innerHTML = val.RazonSocialCliente; 
             document.getElementById("cuenta").value = val.NumCuenta; 
             document.getElementById("labelTotalDeuda").innerHTML = val.TotalDeuda; 
             document.getElementById("labelTotalVencido").innerHTML = val.TotalVencido; 
             document.getElementById("labelPagos").innerHTML = val.Pago; 
             document.getElementById("labelSaldos").innerHTML = val.Saldo; 
             document.getElementById("labelDiasMora").innerHTML = val.DiasMora; 
             $("#Ciudad").append($("<option>",{value:val.IDCiudad,text:val.Ciudad}));
             document.getElementById("txtnota").value=val.Notas;
             document.getElementById("idNotas").value=val.IDNotas;
             document.getElementById("idTransaccion").value=val.IDTransaccion;
             
             
            
            
            
          });
    });


document.getElementById("gestion").innerHTML="";
document.getElementById("resultado").innerHTML="";
 $("#resultado").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));
 //document.getElementById("idNotas").value="0";
getTiposGestiones();
DireccionesJson(idCliente, idDeudor);
TelefonosJson(idCliente, idDeudor);
GestionesJson(idCliente, idDeudor); 
DetalleCuotasJson(idCliente, idDeudor);
console.log("MuestraDatosReferencias Cliente: "+idCliente+" Id_deudor: "+idDeudor );
MuestraDatosReferencias(idCliente,idDeudor);
//ComprasJson(idCliente, idDeudor);
}

function hidden_cartera_cliente(valor,id){
     
    if(document.getElementById("id_deudor").value==="" && id===""){
         alert("Debe Seleccionar un Cliente");
       return;
    }
   if(valor==="true"){
       document.getElementById("cliente_cartera").style.display = 'none';
        document.getElementById("gestion_cliente").style.display = 'block';
   }
   if(valor==="false"){
         document.getElementById("cliente_cartera").style.display = 'block';
        document.getElementById("gestion_cliente").style.display = 'none'; 
   }
}
function hidden_cartera_cliente2(valor){
   
   if(valor==="true"){
       document.getElementById("cliente_cartera").style.display = 'none';
        document.getElementById("cliente_cartera2").style.display = 'none';
        document.getElementById("gestion_cliente").style.display = 'block';
   }
   if(valor==="false"){
         document.getElementById("cliente_cartera").style.display = 'block';
         document.getElementById("cliente_cartera2").style.display = 'block';
        document.getElementById("gestion_cliente").style.display = 'none'; 
   }
}
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

function LlenarComboBuscarCliente(){
document.getElementById("idcartera").innerHTML="";
  
     $.getJSON("consultacartera", {"accion" : "AllClientesROL"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#idcartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
          // var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}

function consulta_historial(){
    var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
     var TablaCompras="<table id='TablaHistorialPagos' class='table table-striped table-bordered table-hover' cellspacing='0'> <thead><tr bgcolor='#B5EE8E'><th align='left' class='col-sm-1 text-left'>Monto</th><th align='left' class='col-sm-1 text-left'>Fecha Pago</th></tr></thead><tbody></tbody></table>";
    document.getElementById("TablaHistorial").innerHTML  =""; 
    document.getElementById("TablaHistorial").innerHTML  =TablaCompras;
     sum_histrial(idDeudor,idCliente);
        $(document).ready(function() {	
     $('#TablaHistorialPagos').DataTable( {
        "ajax": {
            "data": {"accion": "consulta_historial_recaudaciones","idCliente": idCliente,"idDeudor": idDeudor},
            "url": "recaudacion",
            "type": "GET"
            },
            "columns": [
                { "data": "valor_recaudado" },
                { "data": "fecha_cobro" }                
            ],
            "paging": false,
            "lengthChange": false,
            "info": false,
            "searching": false,
            columnDefs: [ {className: 'dt-body-right', targets: '_all'}],
            "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
    } );        
  });
}
function sum_histrial(idDeudor,idCliente){
    document.getElementById("id_total_pagos").innerHTML  =""; 
    $.getJSON("recaudacion", {"accion" : "consulta_sum_historial_recaudaciones","idDeudor":idDeudor,"idCliente":idCliente}, function(result){
          $.each(result.data, function(key, val){   
         // $("#idcartera").append($("<option>",{value:val.valor_recaudado,text:val.razon_social}));
           document.getElementById("id_total_pagos").innerHTML  ="Total: $"+val.valor_recaudado; 
 // var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}
function GestionClienteCompromisos(stridCliente, stride){
var idCliente = stridCliente;
var idDeudor= stride;
var accion="GestionCliente";
hidden_cartera_cliente2('true');
document.getElementById("siguiente").disabled = false; 
document.getElementById("anterior").disabled = false; 


  $.getJSON("cobranzas", {"accion" : accion,"idCliente":idCliente,"idDeudor":idDeudor}, function(result){
         console.log(result);
          $.each(result.ClienteDeudor, function(key, val){             
              document.getElementById("deudor").innerHTML  ="";
              document.getElementById("cliente").innerHTML  ="";
              document.getElementById("labelTotalDeuda").innerHTML  ="";
              document.getElementById("labelTotalVencido").innerHTML  ="";
              document.getElementById("labelPagos").innerHTML  ="";
              document.getElementById("labelSaldos").innerHTML  ="";
              document.getElementById("labelDiasMora").innerHTML  ="";
              
              document.getElementById("id_deudor").value=val.IdDeudor;
              document.getElementById("idcliente").value=val.IdCliente;
              console.log('NombresCompletos: '+val.NombresCompletos);            
             document.getElementById("identificacion").innerHTML = val.Identificacion; 
             document.getElementById("deudor").innerHTML  = val.NombresCompletos; 
             document.getElementById("cliente").innerHTML = val.RazonSocialCliente; 
             document.getElementById("cuenta").value = val.NumCuenta; 
             document.getElementById("labelTotalDeuda").innerHTML = val.TotalDeuda; 
             document.getElementById("labelTotalVencido").innerHTML = val.TotalVencido; 
             document.getElementById("labelPagos").innerHTML = val.Pago; 
             document.getElementById("labelSaldos").innerHTML = val.Saldo; 
             document.getElementById("labelDiasMora").innerHTML = val.DiasMora; 
             $("#Ciudad").append($("<option>",{value:val.IDCiudad,text:val.Ciudad}));
             document.getElementById("txtnota").value=val.Notas;
           //  document.getElementById("idNotas").value=val.IDNotas;
           //NotasAdm
             document.getElementById("txtnota_admin").value=val.NotasAdm;
             document.getElementById("idTransaccion").value=val.IDTransaccion;
            
            
            
          });
    });


document.getElementById("gestion").innerHTML="";
 //document.getElementById("idNotas").value="0";

DireccionesJson(idCliente, idDeudor);
TelefonosJson(idCliente, idDeudor);
GestionesJson(idCliente, idDeudor); 
DetalleCuotasJson(idCliente, idDeudor);
console.log("MuestraDatosReferencias Cliente: "+idCliente+" Id_deudor: "+idDeudor );
MuestraDatosReferencias(idCliente,idDeudor);
//ComprasJson(idCliente, idDeudor);

// *******************

document.getElementById("gestion").innerHTML="";
document.getElementById("resultado2").innerHTML="";
 $("#resultado2").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));

$("#gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
    $.getJSON("consultacartera", {"accion" : "TiposGestiones"}, function(result){
          $.each(result.tipo_gestion, function(key, val){             
           var valor_select = val.nombreTipoGestion;
           if(valor_select === "LLAMADA CLIENTE"){
               
               $("#gestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
            }
          });
    });
    
  // document.getElementById("tresultado_gestion").disabled=true;
   //$("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
   //getipoSubCartera();
  // getipoCartera();
//***********



}

function obtenerResultadoCompromisos() {
    var idcliente = $("#idcliente").val();
     var gestion = $("#gestion").val();
    
     document.getElementById("resultado2").innerHTML="";
     $("#resultado2").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));  
   //  alert(gestion);
     if(gestion === 0 || gestion === "0" ) {  
         return;
     }
    //if(gestion !== 0 || gestion !== "0" ) {   
        $.getJSON("consultacartera", {"accion" : "TiposResulatdos","idcliente":idcliente}, function(result){
             //console.log(result);
            // console.log('size: '+result.tipos_resultado.tipos_resultado);
              $.each(result.tipos_resultado, function(key, val){             
               $("#resultado2").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
              });
        });
    //}
    
    
    
}

function GuardarTransaccnormalCompromisos() {
    var id = $('#id_deudor').val();
    var accion = "transaccion";
    var descripcion = $('#descripcion').val();
    var resultado = $('#resultado2').val();
    var tipo_resultado = $('#tiporesultado').val();
    var gestion = $('#gestion').val();
    var cliente = $('#idcliente').val();
    //JG ini
    var monto_compromiso = $('#monto_compromiso').val();
    var comp_pago = $('#datepickerCompromiso').val();
    var idTransaccion = $('#idTransaccion').val();
    var hora = $('#hora').val();
    var Fechacompromiso_pago = $('#datepickerRecordatorio').val();
    var xdisabled = document.getElementById("datepickerRecordatorio").disabled;
    var adisabled = document.getElementById("hora").disabled;
    var bdisabled = document.getElementById("monto_compromiso").disabled;
    var cdisabled = document.getElementById("datepickerCompromiso").disabled;
     //JG fin

     
   if(gestion === 0 || gestion === "0"){       MsgSalidaModalA("Debe elegir un tipo gestion...");       return;   } 
   if(resultado === 0 || resultado === "0"){   MsgSalidaModalA("Debe elegir un tipo resultado...");     return;   }
   
   
    if (validaDatos(resultado, gestion, descripcion, accion, id, cliente)) {
      //JG ini
        if (resultado === tipo_resultado) {
            
            if ((bdisabled === false) && (cdisabled === false)) {
        if (comp_pago !== "") {
            if (verificaFecha3('datepickerCompromiso') !== 2) {
                    if (verificaFecha3('datepickerCompromiso') === 1) {
                        MsgSalidaModalA("La Fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual");
                    } else if (verificaFecha3('datepickerCompromiso') === 3) {
                        MsgSalidaModalA("La Fecha de Compromiso de Pago no existe.");
                    } else if (verificaFecha3('datepickerCompromiso') === 4) {
                        MsgSalidaModalA("El Formato de la fecha es incorrecto.");
                    }
                    return true;
                }
               //  alert(idTransaccion);
                   $('#img_cargando').css("display", "block");
            if(monto_compromiso !== ""){
               if(monto_compromiso!=0){
                var parametros = {
                    "accion": accion,
                    "id": id,
                    "descripcion": descripcion,
                    "gestion": gestion,
                    "resultado": resultado,
                    "cliente": cliente,
                    "idTransaccion": idTransaccion,
                    "monto_compromiso": monto_compromiso,
                            "comp_pago": comp_pago,
                            "hora": hora,
                            "tipo_resultado": tipo_resultado,
                            "Fechacompromiso_pago": Fechacompromiso_pago
                };
                $.ajax({
                    data: parametros,
                    url: 'cobranzas',
                    type: 'GET',
                    beforeSend: function () {
                    },
                    success: function (response) {
                    $('#img_cargando').css("display", "none"); 
                        if (response) {
                         
                            MsgSalidaModalM(response);
                            var x = document.getElementById("descripcion");
                            x.value = "";
                            var y = document.getElementById("resultado2");
                            y.value = "";
                            var z = document.getElementById("gestion");
                            z.value = "";
                            var A = document.getElementById("datepickerCompromiso");//monto_compromiso
                            A.value = "";
                            var B = document.getElementById("monto_compromiso");
                            B.value = "";
                                    var c = document.getElementById("datepickerRecordatorio");
                                    c.value = "";
                                    var j = document.getElementById("hora");
                                    j.value = "";
                                    GestionesJson(cliente,id); 
                                    //transacciones_pendientes(cliente, id);
                                    document.getElementById("monto_compromiso").disabled = true;
                                    document.getElementById("datepickerCompromiso").disabled = true;
                                    var tip = document.getElementById("tiporesultado");
                                    tip.value = "";
                               // alert("Paso");    
                               // GestionesJson(cliente,id);     
                                } else {
                                    MsgSalidaModalM("Compromiso de pago no Guardado");

                                }
                            }
                        });
                     }else{ MsgSalidaModalM("Debe Ingresar un Monto mayor a 0");}   
                    } else {
                        MsgSalidaModalM("Debe Ingresar un Monto de Compromiso De Pago");
                    }
                } else {
                    MsgSalidaModalM("Debe Ingresar una Fecha de Compromiso De Pago");
                }
            }
            if ((xdisabled === false) && (adisabled === false)) {
                
                if (Fechacompromiso_pago !== "") {
                    if (verificaFecha3('datepickerRecordatorio') !== 2) {
                        if (verificaFecha3('datepickerRecordatorio') === 1) {
                            MsgSalidaModalA("La Fecha de Compromisos de Pago tiene que se igual o mayor a la fecha actual");
                        } else if (verificaFecha3('datepickerRecordatorio') === 3) {
                            MsgSalidaModalA("La Fecha de Compromiso de Pago no existe.");
                        } else if (verificaFecha3('datepickerRecordatorio') === 4) {
                            MsgSalidaModalA("El Formato de la fecha es incorrecto.");
                        }
                        return true;
                    }
                    if (hora !== "") {

                        var parametros = {
                            "accion": accion,
                            "id": id,
                            "descripcion": descripcion,
                            "gestion": gestion,
                            "resultado": resultado,
                            "cliente": cliente,
                            "idTransaccion": idTransaccion,
                            "monto_compromiso": monto_compromiso,
                            "comp_pago": comp_pago,
                            "hora": hora,
                            "tipo_resultado": tipo_resultado,
                            "Fechacompromiso_pago": Fechacompromiso_pago
                        };
                        $.ajax({
                            data: parametros,
                            url: 'cobranzas',
                            type: 'GET',
                            beforeSend: function () {
                            },
                            success: function (response) {

                                if (response) {
                                    MsgSalidaModalM(response);
                                    var x = document.getElementById("descripcion");
                                    x.value = "";
                                    var y = document.getElementById("resultado2");
                                    y.value = "";
                                    var z = document.getElementById("gestion");
                                    z.value = "";
                                    var A = document.getElementById("datepickerCompromiso");//monto_compromiso
                                    A.value = "";
                                    var B = document.getElementById("monto_compromiso");
                                    B.value = "";
                                    var c = document.getElementById("datepickerRecordatorio");
                                    c.value = "";
                                    var j = document.getElementById("hora");
                                    j.value = "";
                                    GestionesJson(cliente,id); 
                                    //transacciones_pendientes(cliente, id);
                                    document.getElementById("datepickerRecordatorio").disabled = true;
                                    document.getElementById("hora").disabled = true;
                                    var tip = document.getElementById("tiporesultado");
                                    tip.value = "";
                                  
                                } else {
                                    MsgSalidaModalM("Compromiso de pago no Guardado");

                                }
                            }
                        });
                    } else {
                        MsgSalidaModalM("Debe Ingresar Hora de Recordatorio");
                    }
                } else {
                    MsgSalidaModalM("Debe Ingresar una Fecha de Recordatorio de llamada");
                }
            }
        }
        if (tipo_resultado === "") {
            tipo_resultado = 0;
            //JG fin

          $('#img_cargando').css("display", "block"); 
            var parametros = {
                "accion": accion,
                "id": id,
                "descripcion": descripcion,
                "gestion": gestion,
                "resultado": resultado,
                "tipo_resultado": tipo_resultado,
                "cliente": cliente,
                "idTransaccion": idTransaccion//,
//                "monto_compromiso": monto_compromiso,
//                "comp_pago": comp_pago
            };
            $.ajax({
                data: parametros,
                url: 'cobranzas',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    $('#img_cargando').css("display", "none"); 
                    if (response) {
                        MsgSalidaModalM(response);
                        //$('#transaccion_table').css("display", "none");
                      //JG ini
		        var x = document.getElementById("descripcion");
                        x.value = "";
                        var y = document.getElementById("resultado2");
                        y.value = "";
                        var z = document.getElementById("gestion");
                        z.value = "";
                        var A = document.getElementById("datepickerCompromiso");//monto_compromiso
                        A.value = "";
                        var B = document.getElementById("monto_compromiso");
                        B.value = "";
                        var c = document.getElementById("datepickerRecordatorio");
                        c.value = "";
                        var j = document.getElementById("hora");
                        j.value = "";    
			    //JG fin
                        //transacciones_pendientes(cliente, id);
                        GestionesJson(cliente,id); 
                        var tip = document.getElementById("tiporesultado");
                        tip.value = "";
                       
                    } else {
                        MsgSalidaModalM("Compromiso de pago no Guardado");

                    }
                }
            });
            }
        }
        
          
}
function inactivaTelefono(idTelefono, telefono){
       var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
   var r = confirm("Esta Seguro de eliminar este numero de telefono "+telefono+"?");
    if (r == true) {
       var parametros = {
            "accion": "InactivaTelefono",
            "idTelefono": idTelefono          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                alert(response.toString());
                TelefonosJson(idCliente, idDeudor);
            }
        });
    
      
    }
    
}

function inactivarDireccion(idDireccion){
    
     var idDeudor = document.getElementById("id_deudor").value;
    var idCliente = document.getElementById("idcliente").value;
   var r = confirm("Esta Seguro que desea eliminar este tipo de Dirección?");
    if (r == true) {
       var parametros = {
            "accion": "inactivarDireccion",
            "idDireccion": idDireccion          
        };
        $.ajax({
            data: parametros,
            url: 'cobranzas',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                alert(response.toString());
               DireccionesJson(idCliente, idDeudor);
            }
        });
    
      
    }
}
function ConsultasTiposTelefonos(){
           document.getElementById("idcartera").innerHTML="";
           $("#idcartera").append($("<option>",{value:"0",text:"- CEDENTE -"}));
           $.getJSON("consultacartera", {"accion" : "AllClientes"}, function(result){
                $.each(result.listaClientes, function(key, val){   
                 $("#idcartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
                });
           }); 
       }