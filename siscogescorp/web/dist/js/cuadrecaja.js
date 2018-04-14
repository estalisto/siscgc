/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function cuadre_caja(){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("cuadrecaja?accion=listar",{},function(){ });
 
}
function buscar_cuadre(){
    var fecha_desde= $('#datepicker').val();
    var fecha_hasta= $('#datepicker2').val();
    var accion="consultacuadre";
     document.getElementById("cuadrecaja").disabled = true;
    var parametros = {
        "fecha_desde": fecha_desde,
        "fecha_hasta": fecha_hasta,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cuadrecaja',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                if(parseInt(response)===0){
                    $('#datacaja').css("display", "none");
                    $('#datacheque').css("display", "none");
                    $('#datatarjeta').css("display", "none");
                    var e = document.getElementById("suma_efectivo");
                    e.value = "$"+0;
                    var c = document.getElementById("suma_cheque");
                    c.value = "$"+0;
                    var t = document.getElementById("suma_tarjeta");
                    t.value = "$"+0;
                    var v = document.getElementById("valor_total");
                    v.value = 0;
                }else{
                    suma_total();
                }
            }else{
                alert("hola");
            }
        }
    });
}
function guardar_cuadre(){
 var contador_caja=$('#cont_caja').val();   
 var contador_cheque=$('#cont_cheque').val();
 var contador_tarjeta=$('#cont_tarjeta').val();
    if(contador_caja!==""){    
        for(var i=0;i<=contador_caja;i++){
     var id_recaudacion =$('#id_recaudacion_caja_'+i).val();
     var fecha_cobro =$('#fecha_cobro_caja_'+i).val();
     var nombres_completo =$('#nombres_completo_caja_'+i).val();
     var total_recaudado =$('#total_recaudado_caja_'+i).val();
     var id_deudor=$('#id_deudor_caja_'+i).val();
     var id_forma_pago=1;
     var accion="guardar_cuadre";
    
    var parametros = {
        "id_recaudacion": id_recaudacion,
        "fecha_cobro": fecha_cobro,
        "nombres_completo": nombres_completo,
        "total_recaudado": total_recaudado,
        "id_deudor": id_deudor,
        "id_forma_pago": id_forma_pago,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cuadrecaja',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //MsgSalidaModalM("Caja Guardada");
            }
        }
    });
     
     
     
 }
    }
    if(contador_cheque!==""){
        for(var j=0;j<=contador_cheque;j++){
     var id_recaudacion =$('#id_recaudacion_cheque_'+j).val();
     var fecha_cobro =$('#fecha_cobro_cheque_'+j).val();
     var nombres_completo =$('#nombres_completo_cheque_'+j).val();
     var total_recaudado =$('#total_recaudado_cheque_'+j).val();
     var id_deudor=$('#id_deudor_cheque_'+j).val();
     var id_forma_pago=2;
     var accion="guardar_cuadre";
    
    var parametros = {
        "id_recaudacion": id_recaudacion,
        "fecha_cobro": fecha_cobro,
        "nombres_completo": nombres_completo,
        "total_recaudado": total_recaudado,
        "id_deudor": id_deudor,
        "id_forma_pago": id_forma_pago,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cuadrecaja',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //MsgSalidaModalM("Caja Guardada");
            }
        }
    });
     
     
     
 }
    }
    if(contador_tarjeta!==""){
        for(var j=0;j<=contador_tarjeta;j++){
     var id_recaudacion =$('#id_recaudacion_tarjeta_'+j).val();
     var fecha_cobro =$('#fecha_cobro_tarjeta_'+j).val();
     var nombres_completo =$('#nombres_completo_tarjeta_'+j).val();
     var total_recaudado =$('#total_recaudado_tarjeta_'+j).val();
     var id_deudor=$('#id_deudor_tarjeta_'+j).val();
     var id_forma_pago=3;
     var accion="guardar_cuadre";
    
    var parametros = {
        "id_recaudacion": id_recaudacion,
        "fecha_cobro": fecha_cobro,
        "nombres_completo": nombres_completo,
        "total_recaudado": total_recaudado,
        "id_deudor": id_deudor,
        "id_forma_pago": id_forma_pago,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'cuadrecaja',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
                //MsgSalidaModalM("Caja Guardada");
            }
        }
    });
     
     
     
 }
    }
    alert(contador_caja);alert(contador_cheque);alert(contador_tarjeta);
    if((contador_caja >=1)||(contador_cheque>=1)||(contador_tarjeta>=1)){
        MsgSalidaModalM("Caja Guardada");
        document.getElementById("cuadrecaja").disabled = true;
        
    }else{
         MsgSalidaModalM("No existen datos de recaudacion");
    }
//    if((contador_caja!=="")&(contador_cheque!=="")&(contador_tarjeta!=="")){
//        MsgSalidaModalM("Caja Guardada");
//    }
}
function suma_total(){
 var contador_caja=$('#cont_caja').val();   
 var contador_cheque=$('#cont_cheque').val();
 var contador_tarjeta=$('#cont_tarjeta').val();
 
    var totales = 0;
    var totales1 = 0;
    var totales2 = 0;
    for (var i= 0; i<= contador_caja; i++) {
        if (($('#total_recaudado_caja_'+i).val()) !== undefined) {
            totales = totales + parseFloat($('#total_recaudado_caja_'+i).val());
            var e = document.getElementById("suma_efectivo");
            e.value = "$"+totales;
        }
    }
    for (var j= 0; j<= contador_cheque; j++) {
        if (($('#total_recaudado_cheque_'+j).val()) !== undefined) {
            totales1 = totales1 + parseFloat($('#total_recaudado_cheque_'+j).val());
             var c = document.getElementById("suma_cheque");
            c.value = "$"+totales1;
        }
    }
    for (var k= 0; k<= contador_tarjeta; k++) {
        if (($('#total_recaudado_cheque_'+k).val()) !== undefined) {
            totales2 = totales2 + parseFloat($('#total_recaudado_cheque_'+k).val());
            var t = document.getElementById("suma_tarjeta");
            t.value = "$"+totales2;
        }
    }
    var RecaudacionTotal=totales+totales1+totales2;
    
    var v = document.getElementById("valor_total");
    v.value = RecaudacionTotal;
    
}

function EliminaFila(strIdCuota){
    var cuota = strIdCuota;
     $("#fila_" + cuota).remove();
     suma_total();
}