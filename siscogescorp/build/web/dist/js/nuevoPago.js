/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function frm_actualizacion()
{

    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("sistema/caja/frm_recaudacion_up.jsp", {}, function () { });

}
function recaudacion()
{

    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("recaudacion?accion=listar", {}, function () { });

}
function nuevo_pago() {
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("nuevopago?accion=listar", {}, function () { });
}
function sumatotal(){
    var cont=$('&contvalores').val();
    var suma=0;
    for(var i=0; i<cont;i++){
       var valores = $('#fila_'+i).val(); 
       suma = suma+valores;
    }
    var x =document.getElementById("valor_inpt");
    x.value=suma;
}
function elminarPago(data) {
 var fechaDesde = document.getElementById('datepicker').value;//$("#datepicker").val;
 var fechaHasta = document.getElementById('datepicker2').value;//$("#datepicker2").val;   
 var comprobante = data;   
 var accion = "eliminar";
 //alert(comprobante);
                    var parametros2 = {
                        "id": comprobante,
                        "accion": accion
                    };
                    $.ajax({data: parametros2,
                            url: 'nuevopago',
                            type: 'GET',
                            beforeSend: function () {
                            },
                            success: function (response) {
                                if (response) {
                                    
                                }else{
                                   DetalleRecaudacionesHoy(fechaDesde,fechaHasta);
                                }
                            }});
   // if (confirm("Realmente desea eliminar los datos")) {
        
//        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
//        jQuery("#page-wrapper").load("nuevopago?accion=eliminar&id=" + data, {}, function () {
//            //nuevo_pago();
//        });
   // }
}
function autorizacion(data,contador){
    var modal=data;
    var cont=contador;
    //for(var i=0;i<cont;i++){
        document.getElementById("admini_"+cont).href="#Autoriza";
    //}
    var i = document.getElementById("idcomp");
        i.value = "";
        i.value = modal;
    
}

function comprobaruser(){
    var clave = $('#clave').val();
    var comprobante = $('#idcomp').val();
    var accion="Comprobar";
     var parametros = {
        "clave": clave,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'recaudacion',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                
                if(parseInt(response)==1){
                    $('#msm_error').css("display", "none");
                    $('#msm_nouser').css("display", "none");
                    $("#Autoriza .close").click();
                   elminarPago(comprobante);
                   
                }
                if(parseInt(response)==0){
                    $('#msm_error').css("display", "block");
                    $('#msm_nouser').css("display", "none");
                }
                if(parseInt(response)==2){
                    $('#msm_error').css("display", "none");
                    $('#msm_nouser').css("display", "block");
                }
            }
           // nuevo_pago();
        }
    });
}
/*Codigo 003: Inicio cambio */
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: Cambio este metodo de visualizacion*/
function VistaPago(strRecaudacion){
    var idRecaudacion = strRecaudacion;
    var accion = "visualizar";
      window.open("reportes?idRecaudacion=" + idRecaudacion , "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=700,height=1000");
}
 /*Codigo 003: Fin cambio */
function ConnsultaDatosID(strecaudo)
{

    if (confirm("Realmente desea actualizar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("nuevopago?accion=buscaID&idRecaudacion=" + strecaudo, {}, function () {
            mostrartable();
            TiposPago();
        });

    }

}

function mostrartable() {
    //var idCuota = $('#id_cuota').val();
    var idRecaudacion = $('#id_recaudacion').val();
    var cont = $('#Cont').val();

    var accion = "cuotatable";//id_recaudacion
    var parametros = {
        //"idCuota": idCuota,
        "idRecaudacion": idRecaudacion,
        "cont": cont,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                var cadena = "";
                cadena += "<table  class='table table-responsive table-bordered'>"
                        + response +
                        "</table>";

                document.getElementById("table1").innerHTML += cadena;

                var conti = $('#conter').val();

//                alert(conti);
                for (var i = 0; i < conti; i++) {
                    var valorpagar = $('#valor_pagar_' + conti).val();
                    //alert(valorpagar);
                    var suma = $('#pago').val();
//                    alert(suma);
                    var total = parseFloat(Number(suma)) + parseFloat(Number($('#valor_pagar_' + i).val()).toFixed(0));
//                    alert(total);
                    if (parseInt(conti) === 0) {
//                        alert("entrooo");
                        var v = document.getElementById("pago");
                        v.value = valorpagar;
                    } else {
//                        alert("entraaa");
                        var v = document.getElementById("pago");
                        v.value = "";
                        v.value = total;
                    }

                }


            }
        }
    });

    TiposPago();
}
function Validacontador(strecaudo) {
    var idRecaudacion = strecaudo;
    var accion = "contador";
    var parametros = {
        "idRecaudacion": idRecaudacion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                //alert(response);
                var t = document.getElementById("Cont");
                t.value = "";
                t.value = response;

            }

        }
    });


}
function TiposPago() {
    var idRecaudacion = $('#id_recaudacion').val();
    var accion = "tipopago";
    var parametros = {
        "idRecaudacion": idRecaudacion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
//                alert(response);
                var separo = response.split(" ");
                var valor = separo[0];
                var separo2 = valor.split("%");

                var primero = separo2[0];
                var segundo = separo2[1];
                var tercero = separo2[2];
                var separo_primero = primero.split("|");
                var idprimero = separo_primero[0];
//                alert(idprimero);
                var separo_segundo = segundo.split("|");
                var idsegundo = separo_segundo[0];
//                alert(idsegundo);
                if (tercero !== undefined) {
//                    alert("entro");
                    var separo_tercero = tercero.split("|");
                    var idtercero = separo_tercero[0];
                    if ((parseInt(idprimero) === 1) && (parseInt(idsegundo) === 2) && (parseInt(idtercero) === 3)) {
                        var valor_efectivo = separo_primero[1];
                        var x = document.getElementById("totalpago");
                        x.value = valor_efectivo;

                        var valor_tarjeta = separo_segundo[1];
                        var inst_tarjeta = separo_segundo[2];
                        var num_tarjeta = separo_segundo[3];
                        var y = document.getElementById("Valor");
                        y.value = valor_tarjeta;
                        var a = document.getElementById("institucion");
                        a.value = inst_tarjeta;
                        var b = document.getElementById("tarjeta");
                        b.value = num_tarjeta;

                        var valor_cheque = separo_tercero[1];
                        var inst_cheque = separo_tercero[2];
                        var num_cheque = separo_tercero[3];
                        var y = document.getElementById("Valor2");
                        y.value = valor_cheque;
                        var a = document.getElementById("institucion2");
                        a.value = inst_cheque;
                        var b = document.getElementById("cuenta");
                        b.value = num_cheque;
                    }
                }


                if ((parseInt(idprimero) === 1) && (parseInt(idsegundo) === 2)) {
                    var valor_efectivo = separo_primero[1];
                    var x = document.getElementById("totalpago");
                    x.value = valor_efectivo;

                    var valor_tarjeta = separo_segundo[1];
                    var inst_tarjeta = separo_segundo[2];
                    var num_tarjeta = separo_segundo[3];
                    var y = document.getElementById("Valor");
                    y.value = valor_tarjeta;
                    var a = document.getElementById("institucion");
                    a.value = inst_tarjeta;
                    var b = document.getElementById("tarjeta");
                    b.value = num_tarjeta;


                }
                if ((parseInt(idprimero) === 2) && (parseInt(idsegundo) === 1)) {
                    var valor_efectivo = separo_segundo[1];
                    var x = document.getElementById("totalpago");
                    x.value = valor_efectivo;

                    var valor_tarjeta = separo_primero[1];
                    var inst_tarjeta = separo_primero[2];
                    var num_tarjeta = separo_primero[3];
                    var y = document.getElementById("Valor");
                    y.value = valor_tarjeta;
                    var a = document.getElementById("institucion");
                    a.value = inst_tarjeta;
                    var b = document.getElementById("tarjeta");
                    b.value = num_tarjeta;


                }
                if ((parseInt(idprimero) === 1) && (parseInt(idsegundo) === 3)) {
                    var valor_efectivo = separo_primero[1];
                    var x = document.getElementById("totalpago");
                    x.value = valor_efectivo;

                    var valor_cheque = separo_segundo[1];
                    var inst_cheque = separo_segundo[2];
                    var num_cheque = separo_segundo[3];
                    var y = document.getElementById("Valor2");
                    y.value = valor_cheque;
                    var a = document.getElementById("institucion2");
                    a.value = inst_cheque;
                    var b = document.getElementById("cuenta");
                    b.value = num_cheque;
                }
                if ((parseInt(idprimero) === 3) && (parseInt(idsegundo) === 1)) {
                    var valor_efectivo = separo_segundo[1];
                    var x = document.getElementById("totalpago");
                    x.value = valor_efectivo;

                    var valor_cheque = separo_primero[1];
                    var inst_cheque = separo_primero[2];
                    var num_cheque = separo_primero[3];
                    var y = document.getElementById("Valor2");
                    y.value = valor_cheque;
                    var a = document.getElementById("institucion2");
                    a.value = inst_cheque;
                    var b = document.getElementById("cuenta");
                    b.value = num_cheque;
                }
                if ((parseInt(idprimero) === 2) && (parseInt(idsegundo) === 3)) {

                    var valor_tarjeta = separo_primero[1];
                    var inst_tarjeta = separo_primero[2];
                    var num_tarjeta = separo_primero[3];
                    var y = document.getElementById("Valor");
                    y.value = valor_tarjeta;
                    var a = document.getElementById("institucion");
                    a.value = inst_tarjeta;
                    var b = document.getElementById("tarjeta");
                    b.value = num_tarjeta;

                    var valor_cheque = separo_segundo[1];
                    var inst_cheque = separo_segundo[2];
                    var num_cheque = separo_segundo[3];
                    var y = document.getElementById("Valor2");
                    y.value = valor_cheque;
                    var a = document.getElementById("institucion2");
                    a.value = inst_cheque;
                    var b = document.getElementById("cuenta");
                    b.value = num_cheque;
                }
                if ((parseInt(idprimero) === 3) && (parseInt(idsegundo) === 2)) {

                    var valor_tarjeta = separo_segundo[1];
                    var inst_tarjeta = separo_segundo[2];
                    var num_tarjeta = separo_segundo[3];
                    var y = document.getElementById("Valor");
                    y.value = valor_tarjeta;
                    var a = document.getElementById("institucion");
                    a.value = inst_tarjeta;
                    var b = document.getElementById("tarjeta");
                    b.value = num_tarjeta;

                    var valor_cheque = separo_primero[1];
                    var inst_cheque = separo_primero[2];
                    var num_cheque = separo_primero[3];
                    var y = document.getElementById("Valor2");
                    y.value = valor_cheque;
                    var a = document.getElementById("institucion2");
                    a.value = inst_cheque;
                    var b = document.getElementById("cuenta");
                    b.value = num_cheque;
                }
                if ((parseInt(idprimero) === 1)) {

                    var valor_efectivo = separo_primero[1];
//                    alert(valor_efectivo);
                    var x = document.getElementById("totalpago");
                    x.value = valor_efectivo;
                }
                if ((parseInt(idprimero) === 2)) {
//                        alert("hola");
                    var valor_tarjeta = separo_primero[1];
                    var inst_tarjeta = separo_primero[2];
                    var num_tarjeta = separo_primero[3];
                    var y = document.getElementById("Valor");
                    y.value = valor_tarjeta;
                    var a = document.getElementById("institucion");
                    a.value = inst_tarjeta;
                    var b = document.getElementById("tarjeta");
                    b.value = num_tarjeta;
                }
                if (parseInt(idprimero) === 3) {
                    var valor_cheque = separo_primero[1];
                    var inst_cheque = separo_primero[2];
                    var num_cheque = separo_primero[3];
                    var y = document.getElementById("Valor2");
                    y.value = valor_cheque;
                    var a = document.getElementById("institucion2");
                    a.value = inst_cheque;
                    var b = document.getElementById("cuenta");
                    b.value = num_cheque;
                }

                document.getElementById("Actualizado").disabled = false;

            }
        }
    });
}
function Actualizar_saldo2(strsaldo, strcont) {
    var saldo = strsaldo;
    var total_saldo = 0;
    var cont = strcont;//
//    alert(saldo);
    var valor_pagar = $('#valor_pagar_' + cont).val();
//    alert(valor_pagar);
    if (parseFloat(valor_pagar) === parseFloat(saldo)) {
        
        var x = document.getElementById("saldo_" + cont);
        x.value = 0.00;
        document.getElementById("saldo_" + cont).style.color = "#00a65a";

    } else {
        total_saldo = saldo - ($('#valor_pagar_' + cont).val());

        document.getElementById("saldo_" + cont).disabled = true;
        var x = document.getElementById("saldo_" + cont);
        x.value = "";
        x.value = total_saldo;
        if (parseFloat(total_saldo) < 0) {
            document.getElementById("saldo_" + cont).style.color = "#f00";
        }
    }
    Suma_totales2();
}


function Suma_totales2() {

    var conti = $('#conter').val();
    //alert(cont);
    var sum = 0;
    var totales = 0;
    for (sum = 0; sum <= conti; sum++) {
        //alert("variable:"+$('#valor_pagar_' + sum).val());
        if (($('#valor_pagar_' + sum).val()) !== undefined) {

            totales = totales + parseFloat($('#valor_pagar_' + sum).val());
        }
    }

    var v = document.getElementById("pago");

    v.value = totales;
//    var t = document.getElementById("totalpago");
//    
//    t.value = totales;
}
function EliminaFila2(strIdCuota,strdetalle) {
    var cuota = strIdCuota;
    var idDetalle = strdetalle;
    var accion = "eliminafila";
    var parametros = {
        "idDetalle": idDetalle,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                
                $("#fila_" + cuota).remove();
                Suma_totales2();
            }
        }
    });
    
}
function CalculoCambio2() {
    var valorpagar = $('#pago').val();
    var cambio = ($('#recibido').val()) - valorpagar;
    var v = document.getElementById("cambio");
    // v.value = "";
    v.value = "$ " + cambio;
}
function ActualizarRecaudacion() {
    var idRecaudacion = $('#id_recaudacion').val();
    var valor_pagar = $('#pago').val();
    var accion = "Actcabecera";
    var parametros = {
        "idRecaudacion": idRecaudacion,
        "valor_pagar": valor_pagar,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                var conti = $('#conter').val();

                for (var i = 0; i <= conti; i++) {
//                    var idArticulo= $('#idarticulo_'+i).val();
//                    var idCuota = $('#idcuota_'+i).val();
//                    var valorCuota = $('#valorcuota_'+i).val();
//                    var interes = $('#interes_'+i).val();
//                    var mora = $('#valormora_'+i).val();
                    var valorPagar = $('#valor_pagar_' + i).val();
                    var saldo = $('#saldo_' + i).val();
//                    alert(i);
//                    alert(valorPagar);
//                    alert(saldo);
//                            if (idCuota !== undefined) {
//                                if (idArticulo !== undefined) {
//                                    if (valorCuota !== undefined) {
//                                        if (interes !== undefined) {
//                                            if (mora !== undefined) {
                    if (valorPagar !== undefined) {
                        if (saldo !== undefined) {
                            ActualizarDetalle(valorPagar, saldo);//idArticulo,idCuota,valorCuota,interes,mora,
                        }
                    }
//                                            }        
//                                        }
//                                    }
//                                }
//                            }
                }
                ActualizarTipoPago();
                nuevo_pago();
                alert("Registro Actualizado Correctamente");
            }
        }
    });
}
function ActualizarDetalle(strvalorPagar, strsaldo) {   //stridArticulo,stridCuota,strvalorCuota,strinteres,strmora,
    var idRecaudacion = $('#id_recaudacion').val();
    var valor_pagar = strvalorPagar;
//    var idArticulo = stridArticulo;
//    var idCuota = stridCuota;
//    var valCuota = strvalorCuota;
//    var interes = strinteres;
//    var mora = strmora;
    var saldo = strsaldo;
    var accion = "Actdetalle";
    var parametros = {
        "idRecaudacion": idRecaudacion,
//        "idArticulo": idArticulo,
//        "idCuota": idCuota,
//        "valCuota": valCuota,
//        "interes": interes,
//        "mora": mora,
        "valor_pagar": valor_pagar,
        "saldo": saldo,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'nuevopago',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                alert(response);
            }
        }
    });
}

function ActualizarTipoPago() {
    var idRecaudacion = $('#id_recaudacion').val();
    var valor_efectivo = $('#totalpago').val();
    var valor_tarjeta = $('#Valor').val();
    var num_tarjeta = $('#tarjeta').val();
    var inst_tarjeta = $('#institucion').val();
    var valor_cheque = $('#Valor2').val();
    var num_cheque=$("#cheque").val();
    var num_cuenta = $('#cuenta').val();
    var inst_cheque = $('#institucion2').val();
    var accion = "Actipago";
   
    if ((valor_cheque !== "") && (num_cheque !== "") && (inst_cheque !== "") && (valor_efectivo !== "") && (valor_tarjeta !== "") && (num_tarjeta !== "") && (inst_tarjeta !== "")) {  //Caso Todo

        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "inst_cheque": inst_cheque,
            "num_cuenta": num_cuenta,
//            "formaPagotarjeta": formaPagotarjeta,
//            "formaPagocheque": formaPagocheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });
    }
    if ((valor_cheque === "") && (num_cheque === "") && (inst_cheque === "")) {  //Caso Efectivo y tarjeta
        valor_cheque = 0.00;
        num_cheque = 0;num_cuenta=0;
        inst_cheque = 0;
        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "inst_cheque": inst_cheque,
            "num_cuenta": num_cuenta,
//            "formaPagoefectivo": formaPagoefectivo,
//            "formaPagotarjeta": formaPagotarjeta,
//            "formaPagocheque": formaPagocheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });
    }
    if ((valor_tarjeta === "") && (num_tarjeta === "") && (inst_tarjeta === "")) { //Caso efectivo y cheque
        valor_tarjeta = 0.00;
        num_tarjeta = 0;
        inst_tarjeta = 0;
        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "inst_cheque": inst_cheque,
            "num_cuenta": num_cuenta,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });
    }
    if ((valor_efectivo === "")) {  //caso tarjeta y cheque
        valor_efectivo = 0.00;

        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "num_cuenta": num_cuenta,
            "inst_cheque": inst_cheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });
    }

    if ((valor_efectivo === "") && (valor_tarjeta === "") && (num_tarjeta === "") && (inst_tarjeta === "")) { //Caso cheque
        valor_efectivo = 0.00;
        valor_tarjeta = 0.00;
        num_tarjeta = 0;
        inst_tarjeta = 0;
        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "num_cuenta": num_cuenta,
            "inst_cheque": inst_cheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });

    }
    if ((valor_efectivo === "") && (valor_cheque === "") && (num_cheque === "") && (inst_cheque === "")) { //Caso tarjeta
        valor_efectivo = 0.00;
        valor_cheque = 0.00;
        num_cheque = 0;num_cuenta=0;
        inst_cheque = 0;
        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "num_cuenta": num_cuenta,
            "inst_cheque": inst_cheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });

    }
    if ((valor_cheque === "") && (num_cheque === "") && (inst_cheque === "") && (valor_tarjeta === "") && (num_tarjeta === "") && (inst_tarjeta === "")) {
        //caso efectivo
        valor_cheque = 0.00;
        num_cheque = 0;
        inst_cheque = 0;num_cuenta=0;
        valor_tarjeta = 0.00;
        num_tarjeta = 0;
        inst_tarjeta = 0;
        var parametros = {
            "idRecaudacion": idRecaudacion,
            "valor_efectivo": valor_efectivo,
            "valor_tarjeta": valor_tarjeta,
            "num_tarjeta": num_tarjeta,
            "inst_tarjeta": inst_tarjeta,
            "valor_cheque": valor_cheque,
            "num_cheque": num_cheque,
            "num_cuenta": num_cuenta,
            "inst_cheque": inst_cheque,
            "accion": accion
        };
        $.ajax({
            data: parametros,
            url: 'nuevopago',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                if (response) {
                    alert(response);
                }
            }
        });
    }
}

function generacuadre_caja(){
    console.log("hola");
    var valor_efec=$("#valor_efec").val();
    var valor_ch=$("#valor_ch").val();
    var valor_tar=$("#valor_tar").val();
    var valor_inpt=$("#valor_inpt").val();
   window.open("misreportes?accion=CuadreCaja&valor_efec="+valor_efec+"&valor_ch="+valor_ch+"&valor_tar="+valor_tar+"&valor_inpt="+valor_inpt,"_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=1000,height=500");   
    
}

              
function consultaRecaudaciones(){
    var fechaDesde = document.getElementById('datepicker').value;//$("#datepicker").val;
    var fechaHasta = document.getElementById('datepicker2').value;//$("#datepicker2").val;
    var ValidaFD=0;
    var ValidaFH=0;
    var accion="consultaRecaudaciones";
    
   if(fechaDesde===""){
       MsgSalidaModalA("Fecha Desde: Existe valores vacios para realizar la busqueda");
       return;
   }
   if(fechaHasta===""){
      MsgSalidaModalA("Fecha Hasta: Existe valores vacios para realizar la busqueda");
     return;
   }
     ValidaFD = validaFechaDDMMYYYY("datepicker");
     ValidaFH = validaFechaDDMMYYYY("datepicker2");
   if ((ValidaFD>=3) &&(ValidaFD>=4)){
      return; 
   }
   if ((ValidaFH>=3) &&(ValidaFH>=3)){
      return; 
   }
     if(compare_dates(fechaDesde, fechaHasta) ){
           
             MsgSalidaModalA("La <strong>FECHA DESDE</strong> tiene que ser mayor  a <strong>FECHA HASTA</strong>");
          return;  
     }
  DetalleRecaudacionesHoy(fechaDesde,fechaHasta);

    
}


$('#datepicker').datetimepicker({   format:'d-m-Y' }); 
$('#datepicker2').datetimepicker({   format:'d-m-Y' }); 

/*Codigo 003: Inicio cambio 17/10/2017*/
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: En este metodo se agrego los nuevos campos usuario asignado, usuario sesion, identificacion deudor,usuario del sistema*/
function DetalleRecaudacionesHoy(fechaInicial,fechaFinal){
    var TablaCompras="<table id='example1' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'>  <thead><tr bgcolor='#FBF5EF'><th>Nº</th><th>Identificación</th><th>Nombre Deudor</th><th>Cliente</th><th>Tipo Pago</th><th>Recaudador</th><th>Usuario Sist.</th><th>Usuario Asignado.</th><th>Valor Total</th><th>Fecha Pago </th><th>Acción </th> </tr> </thead> <tbody></tbody> <tfoot></tfoot></table>";
    document.getElementById("consultaRecaudaciones").innerHTML  =""; 
    document.getElementById("consultaRecaudaciones").innerHTML  =TablaCompras;
    $(document).ready(function() {	
     $('#example1').DataTable( {
        "ajax": {
           "data": {"accion": "ConsultaRecaudaciones","fechaInicial": fechaInicial,"fechaFinal": fechaFinal},
           // "data": {"accion": "ConsultaRecaudaciones"},
            "url": "recaudacion",
            "type": "GET"
            },
            "columns": [
                { "data": "IdRecaudacion" },
                { "data": "IdentificacionDeudor" },
                { "data": "NombreDeudor" },
                { "data": "Cliente" },
                { "data": "TipoPago" }, 
                { "data": "Recaudador" },
                { "data": "UserSist" },
                { "data": "UserAsig" },
                { "data": "Valor", "className": "text-right" },
                { "data": "FechaPago", "className": "text-center" },                
                { "data": "Accion" }
                
            ],
            "paging": false,
            "lengthChange": false,
            "info": false,
            "searching": false
    } );        
  }); 
  SumaTotal(fechaInicial,fechaFinal);
  ConsultaTotales(fechaInicial, fechaFinal);
}
/*Codigo 003: Fin cambio 17/10/2017*/
function ConsultaTotales(fechaInicial, fechaFinal){
    var accion="ConsultaTotales";
  
       var parametros = {
            "fechaInicial": fechaInicial,
            "fechaFinal": fechaFinal,
            "accion": accion
        }; 
        
          $.getJSON("recaudacion", {"fechaInicial": fechaInicial,"fechaFinal": fechaFinal,"accion": accion}, function(result){
                console.log(result);
                  $.each(result.data, function(key, val){   
                    document.getElementById("valor_efec").innerHTML  ="";
                    document.getElementById("valor_ch").innerHTML  ="";
                    document.getElementById("valor_tar").innerHTML  ="";
                    if (val.efectivo===0){
                       document.getElementById("valor_efec").value="0.00"; 
                    }else{
                        document.getElementById("valor_efec").value=val.efectivo; 
                    }
                    if (val.cheque===0){
                       document.getElementById("valor_ch").value="0.00"; 
                    }else{
                        document.getElementById("valor_ch").value=val.cheque;
                    }
                    if (val.tcredito===0){
                         console.log('totales: '+val.tcredito);   
                       document.getElementById("valor_tar").value="0.00"; 
                    }else{
                        document.getElementById("valor_tar").value=val.tcredito;
                    }
                    console.log('totales: '+val.efectivo+' '+val.efectivo+' '+val.tcredito);   
                  });
              
          });
        
      /*  $.ajax({
            data: parametros,
            url: 'recaudacion',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                 if(response){
                     console.log(response.toString());
                               //document.getElementById("valor_inpt").value="";
                               //document.getElementById("valor_inpt").value=response.toString(); 
                             }
            }
        });*/
    
}

function SumaTotal(fechaInicial, fechaFinal){
    var accion="ConsultaTotalRecaudaciones";
  
       var parametros = {
            "fechaInicial": fechaInicial,
            "fechaFinal": fechaFinal,
            "accion": accion
        }; 
        $.ajax({
            data: parametros,
            url: 'recaudacion',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {
                 if(response){
                    // alert(response.toString());
                               document.getElementById("valor_inpt").value="";
                               document.getElementById("valor_inpt").value=response.toString(); 
                             }
            }
        });
    
}

