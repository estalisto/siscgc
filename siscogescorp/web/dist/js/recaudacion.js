/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function FuncionesCuotas(){
    var cont = $('#Cont').val();
    var verifica = "";
    for(var j=0;j<=cont;j++){
        var cuota=$('#idcuota_'+j).val();
        //alert(verifica);
        
                verifica=cuota;
                BuscarCuota(verifica);
         
    }
    
}
function BuscarCuota() {
    var cartera = $('#cartera').val();
    var tipo = $('#t_identificacion').val();
    var ide = $('#identificacion').val();
    var nombres = $('#nombres').val();
    var accion = "cuota";
    var cont = $('#Cont').val();
   // alert(cont);
   for(var j=0;j<cont;j++){
   var cuota=$('#idcuota_'+j).val();
 var almacen = almacen+"&"+cuota;
        //alert(almacen);   
 }

        var parametros = {
            "cartera": cartera,
            "tipo": tipo,
            "ide": ide,
            "nombres": nombres,
            "almacen": almacen,
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

                    var cadena = "";
                    cadena += "<div class='modal-dialog modal-lg' role='document'> \n\
                            <div class='modal-content'> \n\
                            <br><div class='box box-danger'> \n\
                            <div class='box-header with-border bg-yellow'> \n\
                            <h3 class='box-title'>Detalle Cuotas</h3> \n\
                            </div>  \n\
                            <div class='box-body' style='overflow-y:scroll;'> \n\
                            <table id='detalle_cuotas' class='table table-bordered table-hover'> \n\
                            <thead> \n\
                            <tr bgcolor='#FBF5EF'> \n\
                                    <th class='hidden'>idCuota</th> \n\
                                    <th>Artículo</th> \n\
                                    <th>Cuota</th> \n\
                                    <th>Interés</th> \n\
                                    <th>Capital </th> \n\
                                    <th>Mora Generada</th> \n\
                                    <th>Agregar</th> \n\
                                </tr> \n\
                            </thead> "
                            + response +
                            "</table> \n\
                                <div class='modal-footer'>\n\
                            <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>\n\
                             </div></div>";
                    cadena += "</div> \n\
                                    </div> \n\
                                </div> ";
                    document.getElementById("detalle_cuota").innerHTML = '';
                    document.getElementById("detalle_cuota").innerHTML += cadena;


                }
            }
        });
    

}
function Buscarecaudo() {
    var ide=$('#cedula').val();
    var cartera=$('#nomb_cartera').val();
    var nombre_completo=$('#nom_completos').val();
    var accion = "BuscarDeudores";
    
    if(cartera!==""){
        $('#id_message_carter').css("display", "none");
        if((ide==="")&&(nombre_completo==="")){
            //MsgSalidaModalA("Debe ingresar una identificacion o Nombre del deudor");
            $('#id_message').css("display", "block");
        }else{
            $('#id_message').css("display", "none");
            if(ide!==""){
               document.getElementById("nom_completos").disabled = true;
               $('#id_loader').css("display", "block");
                var parametros = {
                            "ide": ide,
                            "cartera": cartera,
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
                                    document.getElementById("tableDE").innerHTML = "";
                                    document.getElementById("tableDE").innerHTML = response;
                                    $('#id_loader').css("display", "none");
                                } else {
                                    MsgSalidaModalA("No existe Deudor con esa Identificación");
                                    document.getElementById("nom_completos").disabled = false;
                                    $('#id_loader').css("display", "none");
                                }
                            }
                        });
            }//else{MsgSalidaModalA("Debe ingresar una identificación");}
            if(nombre_completo!==""){
               document.getElementById("cedula").disabled = true;
               $('#id_loader').css("display", "block");
               var parametros = {
                            "nombre_completo": nombre_completo,
                            "cartera": cartera,
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
                                    document.getElementById("tableDE").innerHTML = "";
                                    document.getElementById("tableDE").innerHTML = response;
                                    $('#id_loader').css("display", "none");
                                } else {
                                    MsgSalidaModalA("No existe Deudor con esa Identificación");
                                    $('#id_loader').css("display", "none");
                                }
                            }
                        });
            }//else{MsgSalidaModalA("Debe ingresar minimo 20 caracteres para iniciar la búsqueda");}
        }
    }else{$('#id_message_carter').css("display", "block");
       // MsgSalidaModalA("Debe Seleccionar la Cartera");
    }
}
function ConsultaDeudor(cont){
    var identificacion = $('#IDentify_'+cont).val();
    var nombreDeudor = $('#NomDeudor_'+cont).val();
    var tipCartera = $('#TipCartera_'+cont).val();
    var ClientCartera = $('#ClientCartera_'+cont).val();
    var IdCliente = $('#idCliente_'+cont).val();
    var TipIde = $('#TipIde_'+cont).val();
    var idDeudor=$('#IdeDeudor_'+cont).val();
    var x = document.getElementById("t_identificacion");
    x.value = "";
    x.value = parseInt(TipIde);
    var i = document.getElementById("identificacion");
    i.value = "";
    i.value = identificacion;
    var n = document.getElementById("nombres");
    n.value = "";
    n.value = nombreDeudor;
    var t = document.getElementById("cartera");
    t.value = "";
    t.value = parseInt(IdCliente);
    var y = document.getElementById("tip_cartera");
    y.value = "";
    y.value = tipCartera;
    var d = document.getElementById("idDeudor");
    d.value = "";
    d.value = parseInt(idDeudor);
    document.getElementById("busc_cuota").disabled = false;
    document.getElementById("consulta_recaudador").disabled = false;
    
     $('#consulta').modal('hide');  
}
function Buscarecaudo2() {
    var cartera = $('#cartera').val();
    var oficial = $('#oficial').val();
    var tipo = $('#t_identificacion').val();
    var ide = $('#identificacion').val();
    var nombres = $('#nombres').val();
    var accion = "recaudo";

    if ((oficial === "") && (nombres === "")) {
        oficial = 0;
        nombres = "N";
        
        if (cartera !== "") {
            if (tipo !== "") {
                if (ide !== "") {
                    var parametros = {
                        "cartera": cartera,
                        "oficial": oficial,
                        "tipo": tipo,
                        "ide": ide,
                        "nombres": nombres,
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
                                var separo = response.split("|");
                                var nombres = separo[0];
                                var id = separo[1];
                                var x = document.getElementById("nombres");
                                x.value = nombres;
                                var i = document.getElementById("idDeudor");
                                i.value = "";
                                i.value = parseInt(id);
                                document.getElementById("busc_cuota").disabled = false;
                            } else {
                                MsgSalidaModalA("No existe Deudor con esa Identificación");
                            }
                        }
                    });
                } else {
                    MsgSalidaModalA("Debe ingresar una identificación");
                }
            } else {
                MsgSalidaModalA("Debe elegir un tipo de identificación");
            }
        } else {
            MsgSalidaModalA("Debe elegir una cartera");
        }
    }

    if ((oficial !== "") && (nombres === "")) {
        
        nombres = "N";
        if (cartera !== "") {
            if (tipo !== "") {
                if (ide !== "") {
                    var parametros = {
                        "cartera": cartera,
                        "oficial": oficial,
                        "tipo": tipo,
                        "ide": ide,
                        "nombres": nombres,
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
                                var separo = response.split("|");
                                var nombres = separo[0];
                                var id = separo[1];
                                var x = document.getElementById("nombres");
                                x.value = nombres;
                                var i = document.getElementById("idDeudor");
                                i.value = "";
                                i.value = parseInt(id);
                                document.getElementById("busc_cuota").disabled = false;
                            } else {
                                MsgSalidaModalA("No existe Deudor con esa Identificación");
                            }
                        }
                    });
                } else {
                    MsgSalidaModalA("Debe ingresar una identificación");
                }
            } else {
                MsgSalidaModalA("Debe elegir un tipo de identificación");
            }
        } else {
            MsgSalidaModalA("Debe elegir una cartera");
        }
    }
    
    if ((tipo === "") && (ide === "") && (oficial === "")) {
        tipo = 0;
        ide = "N";
        oficial = 0;
        if (cartera !== "") {
            var separo = nombres.split("|");
            var nombres = $.trim(separo[1]);

            var parametros = {
                "cartera": cartera,
                "oficial": oficial,
                "tipo": tipo,
                "ide": ide,
                "nombres": nombres,
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
                        //alert(response);
                        var separo = response.split("|");
                        var iden = separo[0];
                        var tipo = separo[1];
                        var nom = separo[2];
                        var id = separo[3];
                        var x = document.getElementById("identificacion");
                        x.value = "";
                        x.value = iden;
                        var y = document.getElementById("t_identificacion");
                        y.value = "";
                        y.value = parseInt(tipo);
                        var z = document.getElementById("nombres");
                        z.value="";
                        z.value = nom;
                        var i = document.getElementById("idDeudor");
                        i.value = "";
                        i.value = parseInt(id);
                        document.getElementById("busc_cuota").disabled = false;
                    }
                }
            });
        } else {
            MsgSalidaModalA("Debe elegir una cartera");
        }
    }
    
    if ((tipo === "") && (ide === "") && (oficial !== "")) {
        tipo = 0;
        ide = "N";
        
        if (cartera !== "") {
            var separo = nombres.split("|");
            var nombres = $.trim(separo[1]);
            var parametros = {
                "cartera": cartera,
                "oficial": oficial,
                "tipo": tipo,
                "ide": ide,
                "nombres": nombres,
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
                        //alert(response);
                        var separo = response.split("|");
                        var iden = separo[0];
                        var tipo = separo[1];
                        var nom = separo[2];
                        var id = separo[3];
                        var x = document.getElementById("identificacion");
                        x.value = "";
                        x.value = iden;
                        var y = document.getElementById("t_identificacion");
                        y.value = "";
                        y.value = parseInt(tipo);
                        var z = document.getElementById("nombres");
                        z.value = "";
                        z.value = nom;
                        var i = document.getElementById("idDeudor");
                        i.value = "";
                        i.value = parseInt(id);
                        document.getElementById("busc_cuota").disabled = false;
                    }
                }
            });
        } else {
            MsgSalidaModalA("Debe elegir una cartera");
        }
    }
}
function validaselector() {
    var tipo = $("#t_identificacion").val();
    if (parseInt(tipo) === 1) {
        document.getElementById("identificacion").disabled = false;
        document.getElementById("identificacion").maxlength = "10";
    }
    if (parseInt(tipo) === 2) {
        document.getElementById("identificacion").disabled = false;
        document.getElementById("identificacion").maxlength = "13";
    }
    if ((parseInt(tipo) === 1) && (parseInt(tipo) === 2)) {
        document.getElementById("identificacion").disabled = true;
    }
}
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || ((event.keyCode > 57) ))
    {
       
           event.returnValue = false; 
        
        
    }
}        
function filterFloat(evt,input){
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter(__val__){
    var preg = /^([0-9]+\.?[0-9]{0,2})$/; 
    if(preg.test(__val__) === true){
        return true;
    }else{
       return false;
    }
    
}


function txNombres() {
    if ((event.keyCode !== 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
        event.returnValue = false;
}
$(document).ready(function () {
    $("#nombres").keyup(function () {
        var cartera = $('#cartera').val();
        if (cartera !== "") {
            $.get("recaudacion?accion=autocomplete&cartera="+cartera, {company: $(this).val()}, function (data) {
                $("datalist").empty();
                $("datalist").html(data);

            });
        } else {
            MsgSalidaModalA("Debe seleccionar la cartera del cliente");
        }
    });
});
function checkeando(cont)
        {
          //  alert("checkeando");
            $(".check_"+cont).each(function(){
            //    alert("lo hizo");
                $(this).prop('checked',true);
            });
        }
function Recaudacion(stridCuota,conti) {
    var cont2=$('#contcuota').val();
    var cont = $('#Cont').val();
    if($('#check_active_'+conti).is(':checked')){
    var idCuota = stridCuota;
    
    var accion = "listarCuota";
    var parametros = {
        "idCuota": idCuota,
        "cont": cont,
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
                var cadena = "";
                cadena += "<table  class='table table-responsive table-bordered'>"
                        + response +
                        "</table>";

                $('#table2').css("display", "none");
                document.getElementById("table1").innerHTML += cadena;
                var valorpagar = $('#valor_pagar_' + cont).val();
                var suma = $('#pago').val();
                for (var i = 0; i <= cont; i++) {
                   
                        var total = parseFloat(suma) + parseFloat($('#valor_pagar_' + i).val());
                        
                        if(parseInt(cont) === 0){
                            var v = document.getElementById("pago");
                            v.value = Number(valorpagar).toFixed(2);
                        }else{
                            var v = document.getElementById("pago");
                            v.value = "";
                            v.value = Number(total).toFixed(2);
                        }
                }
                cont++;
                var c = document.getElementById("Cont");
                c.value = cont; 
            }
        }
    });
    //checkeando(conti);
     document.getElementById("recibido").disabled = false;
                document.getElementById("Guardado").disabled = false;
  }else{
      //$("#check_active").attr('checked', false);
      EliminaFilaCHECK(conti);
     // alert(cont);
      if(cont>0){
          cont--;
            var c = document.getElementById("Cont");
             c.value = cont; 
      }
      document.getElementById("recibido").disabled = true;
      document.getElementById("Guardado").disabled = true;
  }
//}
}
function EliminaFilaCHECK(conti){
    var contador = $("#contando").val();
  //  alert(conti);
    for(var i=0;i<=conti;i++){
     if(conti==i){
    //     alert(i);
      //   alert("entro");
        $("#fila_"+i).remove();
        Suma_totales();
     }
     
    }
}
function Actualizar_saldo(strsaldo, strcont) {
    var saldo = strsaldo;
    var total_saldo = 0;
    var cont = strcont;//1
    var valor_pagar = $('#valor_pagar_' + cont).val();

    if (parseFloat(valor_pagar) === parseFloat(saldo)) {
        
        var x = document.getElementById("saldo_" + cont);
        x.value = 0.00;
        document.getElementById("saldo_" + cont).style.color = "#00a65a";
        
    } else {
        total_saldo = saldo - ($('#valor_pagar_' + cont).val());
        
        document.getElementById("saldo_" + cont).disabled = true;
        var x = document.getElementById("saldo_" + cont);
        x.value = "";
        x.value = Number(total_saldo).toFixed(2);
        if(parseFloat(Number(total_saldo).toFixed(2)) < 0){
        document.getElementById("saldo_" + cont).style.color = "#f00";
        }else{document.getElementById("saldo_" + cont).style.color = "#00a65a";}
        }
        Suma_totales();
}


function Suma_totales() {

    var cont = $('#Cont').val();
    //alert(cont);
    var sum=0;
    var totales=0;
    for(sum=0;sum<=cont;sum++){
      
        if(($('#valor_pagar_' + sum).val()) !== undefined){
           
        totales=totales+ parseFloat($('#valor_pagar_' + sum).val());   
       } 
       if(($('#valor_pagar_' + sum).val())==""){
           totales="";
       }
    }
    
     var v = document.getElementById("pago");
        v.value = "";
        v.value = Number(totales).toFixed(2);
    var t = document.getElementById("totalpago");
    t.value = "";
    t.value = Number(totales).toFixed(2);
}
function CalculoCambio(){
    var valorpagar=$('#pago').val();
    
       if(valorpagar!=""){
           var cambio= ($('#recibido').val()) - valorpagar;
     var v = document.getElementById("cambio");
      // v.value = "";
       v.value = "$ "+Number(cambio).toFixed(2);
       }else{
           var v = document.getElementById("cambio");
           v.value = "$ "+0;
       }
}
function FormaPago() {
var cadena = '';
        cadena += "<div class='form-group'> \n\
            <div class='col-lg-12'> \n\
            <div class='col-lg-2'> \n\
            <label>Forma de Pago:</label> \n\
            </div> \n\
            <div class='col-lg-4'>\n\
            <select class='form-control' id='forma_pago2' onchange='OtroPago()'> \n\
            <option value='2' >Tarjeta de Credito</option> \n\
            <option value='3' >Cheque</option> \n\
            </select> \n\
            </div> \n\
            </div> \n\
            </div>";
        cadena +="<div class='form-group'> \n\
            <div class='col-lg-12'> \n\
            <div class='col-lg-3'><label>Valor:</label> \n\
            <input type='text' class='form-control' name='Valor' id='Valor'>\n\
            </div>\n\
           <div class='col-lg-3'><label>Número Tarjeta:</label> \n\
            <input type='text' class='form-control'  placeholder='N° Tarjeta' name='tarjeta' id='tarjeta'></div>\n\
            <div class='col-lg-3'><label>Número Cuenta:</label>\n\
            <input type='text' class='form-control'  placeholder='N° Cuenta' name='cuenta' id='cuenta'>";										
        cadena +="</div>\n\
            <div class='col-lg-3'><label>Nombre de Institucion:</label>\n\
            <input type='text'  class='form-control'  placeholder='Nombre de Institucion'  name='institucion' id='institucion'></div></div> \n\
            </div>";									
           
        document.getElementById("new_formpago").innerHTML += cadena; 
        document.getElementById("mas").disabled = true;
        //var forma_pago = $('#forma_pago2').val();
        var f = document.getElementById("forma_pago2");
        f.value = "";

}
function OtroPago(){
    var pago2= $('#forma_pago2').val();
    //alert(pago2);
     document.getElementById("tarjeta").disabled = true;
     document.getElementById("cuenta").disabled = true;
    if(parseInt(pago2) === 2){
     document.getElementById("tarjeta").disabled = false;   
    }
    if(parseInt(pago2) === 3){
    document.getElementById("cuenta").disabled = false;
    }
}
function GuardarRecaudacion(){
var cartera = $('#cartera').val();
var accion="guardaregistros";
var valorecaudacion = $('#pago').val();
var idDeudor = $('#idDeudor').val();
var cont = $('#Cont').val();
var PagoEfectivo=$("#totalpago").val();
var PagoCheque=$("#Valor2").val();
var PagoTarjCred=$("#Valor").val();
var numeroCta=$("#cuenta").val();
var numeroCtaCheque=$("#cheque").val();
var InstCtaCheque=$("#institucion2").val();
var id_empleado=$("#consulta_recaudador").val();

var numeroTarjetaCredito=$("#tarjeta").val();
var InstTarjetaCredito=$("#institucion").val();



if((PagoEfectivo==="")&&(PagoCheque === "")&&(PagoTarjCred === "")){
    MsgSalidaModalA("Debe ingresar una forma de Pago");
    return true;    
}
if((PagoCheque !== "")&&(numeroCtaCheque === "")){
    MsgSalidaModalA("Debe Ingresar el número de Cheque ");
    return true;    
}
if((PagoCheque !== "")&&(numeroCta === "")){
    MsgSalidaModalA("Debe Ingresar el número de Cuenta ");
    return true;    
}
if((PagoCheque !== "")&&(InstCtaCheque === "")){
    MsgSalidaModalA("Debe Ingresar el nombre de la Institucion Bancaria");
    return true;    
}

if((PagoTarjCred !== "")&&(numeroTarjetaCredito === "")){
    MsgSalidaModalA("Debe Ingresar el número de VOUCHER ");
    return true;    
}
if((PagoTarjCred !== "")&&(InstTarjetaCredito === "")){
    MsgSalidaModalA("Debe Ingresar el nombre de la Entidad Financiera de la Tarjeta");
    return true;    
}
//parseInt(PagoEfectivo)

if(PagoEfectivo===""){
    PagoEfectivo=0;
}
if(PagoCheque===""){
    PagoCheque=0;
}
if(PagoTarjCred===""){
    PagoTarjCred=0;
}

var tttotal = Number(PagoEfectivo)+Number(PagoCheque)+Number(PagoTarjCred);
//alert(tttotal);
if(Number(tttotal).toFixed(2)!==Number(valorecaudacion).toFixed(2)){
    MsgSalidaModalA("Los valores registrados en la FORMA DE PAGO deben ser igual al Total a Cancelar....");
    return true;  
}




var parametros = {
        "cartera": cartera,
        "idDeudor": idDeudor,
        "valorecaudacion": valorecaudacion,
        "id_empleado":id_empleado,
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
                    if (parseInt(response) > 0) {

                        for (var i = 0; i < cont; i++) {
                            
                            var idArticulo= $('#idarticulo_'+i).val();
                            var idCuota = $('#idcuota_'+i).val();
                            var valorCuota = $('#valorcuota_'+i).val();
                            var interes = $('#interes_'+i).val();
                            var mora = $('#valormora_'+i).val();
                            var valorPagar = $('#valor_pagar_'+i).val();
                            var saldo = $('#saldo_'+i).val();
                            
                            if (idCuota !== undefined) {
                                if (idArticulo !== undefined) {
                                    if (valorCuota !== undefined) {
                                        if (interes !== undefined) {
                                            if (mora !== undefined) {
                                                if (valorPagar !== undefined) {
                                                    if (saldo !== undefined) {
                                                       GuardarDet_Recaudacion(parseInt(response),idArticulo,idCuota,valorCuota,interes,mora,valorPagar,saldo);
                                                    }
                                                }
                                            }        
                                        }
                                    }
                                }
                            }
                        }
                        Guardar_FormaPago(parseInt(response));
                        var msg="Recaudacion Guardada Exitosamente\n Desea Imprimir el Comprobante";
                        
//                         var f = document.getElementById("id_reporte");
//                            f.value = "";
//                             f.value = response;
                             MsgSalidaModalcONF(msg,response);
                    }
                }
            }
        });
}
        
function imprimir(response){
    
    var nom_deudor=$("#nombres").val();
    var identificacion=$("#identificacion").val();                        
    var idRecaudacion=parseInt(response); 
    //alert(idRecaudacion);
    //window.open("reportes?idRecaudacion="+idRecaudacion+"&identificacion="+identificacion+"&nom_deudor="+nom_deudor, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=700,height=1000");
    window.open("reportes?idRecaudacion="+idRecaudacion);
}
function MsgSalidaModalcONF(msg,response){
var msg=msg;
var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header modal-header-warning'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'>MENSAJE!!!...</h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>"+msg+"</p> "+
        "</div> "+
        "<div class='modal-footer'> "+
        "<button type='button' class='btn btn-primary' onclick='imprimir("+response+");' data-dismiss='modal'>Aceptar</button> "+
          "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cancelar</button> "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}


function GuardarDet_Recaudacion(stridRecaudacion,stridArticulo,stridCuota,strvalorCuota,strinteres,strmora,strvalorPagar,strsaldo){
var idRecaudacion = stridRecaudacion;
var idArticulo = stridArticulo;
var idCuota = stridCuota;
var valorCuota = strvalorCuota;
var interes = strinteres;
var mora = strmora;
var valorPagar = strvalorPagar;
var saldo = strsaldo;
var accion="detalle";



var parametros = {
        "idRecaudacion": idRecaudacion,
        "idArticulo": idArticulo,
        "idCuota": idCuota,
        "valorCuota": valorCuota,
        "interes": interes,
        "mora": mora,
        "valorPagar": valorPagar,
        "saldo": saldo,
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
                    //alert(response);
                    //Guardar_FormaPago();
                }
            }
        });    
}
/*Codigo 003: Inicio cambio */
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: Se agrego el nuevo campo para el numero de cuenta del cheque*/
function Guardar_FormaPago(stridRecaudacion){
    /*Segundo tipo de pago Efectivo*/
    var idRecaudacion = stridRecaudacion;
    var formaPago = 1;
    var totalpago = $('#totalpago').val();
    /*Segundo tipo de pago Tarjeta/credito*/
    var formaPago2 = 2;
    var totalpago2 = $('#Valor').val();
    var institucion = $('#institucion').val();
    var num_tarjeta = $('#tarjeta').val();
    /*Tercer tipo de pago Cheque*/
    var formaPago3 = 3;
    var totalpago3 = $('#Valor2').val();
    var institucion2 = $('#institucion2').val();
    var num_cheque=$("#cheque").val();
    var num_cuenta = $('#cuenta').val();    
    var accion="Formapago";
    var valorecaudacion = $('#pago').val();
     
    if((totalpago !== "")&&(totalpago2 !== "")&&(totalpago3 !== "")){


        var suma = Number($('#Valor').val()) + Number($('#Valor2').val()) +Number($('#totalpago').val());

        if(Number(suma).toFixed(2) === Number(valorecaudacion).toFixed(2)){ 
        var parametros = {
        "totalpago": totalpago,
        "formaPago": formaPago,
        "idRecaudacion": idRecaudacion,
        
        "formaPago2": formaPago2,
        "institucion": institucion,
        "totalpago2": totalpago2,
        "num_tarjeta": num_tarjeta,
        "num_cheque" : num_cheque,
        "formaPago3": formaPago3,
        "totalpago3": totalpago3,
        "institucion2": institucion2,
        "num_cuenta": num_cuenta,                    
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
                   
                    //MsgSalidaModalM(response);
                    nuevo_pago();
                }
            }
        });
        }
        
    }
    if((totalpago === "")&&(totalpago2 !== "")&&(totalpago3 === "")){
        formaPago = 0; formaPago3 = 0;
        institucion2 = 0;
        totalpago = "0"; totalpago3 = "0";
        num_cuenta = "";num_cheque="";
        if(Number(totalpago2).toFixed(2) === Number(valorecaudacion).toFixed(2)){
        var parametros = {
        "totalpago": totalpago,
        "formaPago": formaPago,
        "idRecaudacion": idRecaudacion,
        
        "formaPago2": formaPago2,
        "institucion": institucion,
        "totalpago2": totalpago2,
        "num_tarjeta": num_tarjeta,
        "num_cheque" : num_cheque,
        "formaPago3": formaPago3,
        "totalpago3": totalpago3,
        "institucion2": institucion2,
        "num_cuenta": num_cuenta,                    
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
                    
                    //MsgSalidaModalA(response);
                    nuevo_pago();
                }
            }
        });
        }

    }
    if((totalpago === "")&&(totalpago2 === "")&&(totalpago3 !== "")){
        
        formaPago2 = 0; formaPago = 0;
        institucion = 0; 
        totalpago2 = "0"; totalpago = "0";
        num_tarjeta = "";
        if(Number(totalpago3).toFixed(2) === Number(valorecaudacion).toFixed(2)){
            
            var parametros = {
                "totalpago": totalpago,
                "formaPago": formaPago,
                "idRecaudacion": idRecaudacion,

                "formaPago2": formaPago2,
                "institucion": institucion,
                "totalpago2": totalpago2,
                "num_tarjeta": num_tarjeta,
                "num_cheque" : num_cheque,
                "formaPago3": formaPago3,
                "totalpago3": totalpago3,
                "institucion2": institucion2,
                "num_cuenta": num_cuenta,                    
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
                    //MsgSalidaModalM(response);
                    nuevo_pago();
                }
            }
        });
        }
    
    }
//        }else{alert("Debe ingresar un numero de Tarjeta Credito");}
//    }else{alert("Debe ingresar el nombre de la Institucion Financiera");}
    if((totalpago !== "")&&(totalpago2 === "")&&(totalpago3 === "")){
        formaPago2 = 0; formaPago3 = 0;
        institucion = 0; institucion2 = 0;
        totalpago2 = "0"; totalpago3 = "0";
        num_tarjeta = ""; num_cuenta = "";num_cheque="";
       // alert("Efectivo");
        if(Number(totalpago).toFixed(2) === Number(valorecaudacion).toFixed(2)){
            var parametros = {
                "totalpago": totalpago,
                "formaPago": formaPago,
                "idRecaudacion": idRecaudacion,
                "formaPago2": formaPago2,
                "institucion": institucion,
                "totalpago2": totalpago2,
                "num_cheque" : num_cheque,
                "num_tarjeta": num_tarjeta,
                "formaPago3": formaPago3,
                "totalpago3": totalpago3,
                "institucion2": institucion2,
                "num_cuenta": num_cuenta,
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
                        
                        //MsgSalidaModalM(response);
                        nuevo_pago();
                    }
                }
            });
        }
        
    }
    if((totalpago !== "")&&(totalpago2 !== "")&&(totalpago3 === "")){
        formaPago3 = 0;
        institucion2 = 0;
        totalpago3 = "0";
        num_cuenta = " ";num_cheque="";
	var suma = Number($('#totalpago').val()) + Number($('#Valor').val());
	if(Number(suma).toFixed(2) === Number(valorecaudacion).toFixed(2)){
	 var parametros = {
	 "totalpago": totalpago,
            "formaPago": formaPago,
            "idRecaudacion": idRecaudacion,

            "formaPago2": formaPago2,
            "institucion": institucion,
            "totalpago2": totalpago2,
            "num_tarjeta": num_tarjeta,
            "num_cheque" : num_cheque,
            "formaPago3": formaPago3,
            "totalpago3": totalpago3,
            "institucion2": institucion2,
            "num_cuenta": num_cuenta,                    
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
                        
                        //MsgSalidaModalM(response);
                        nuevo_pago();
                    }
                }
            });
        }

    }
    if((totalpago !== "")&&(totalpago2 === "")&&(totalpago3 !== "")){
        formaPago2 = 0;
        institucion = 0;
        totalpago2 = "0";
        num_tarjeta = " ";
        var suma = Number($('#totalpago').val()) + Number($('#Valor2').val());

        if(Number(suma).toFixed(2) === Number(valorecaudacion).toFixed(2)){ 
        var parametros = {
        "totalpago": totalpago,
        "formaPago": formaPago,
        "idRecaudacion": idRecaudacion,
        
        "formaPago2": formaPago2,
        "institucion": institucion,
        "totalpago2": totalpago2,
        "num_tarjeta": num_tarjeta,
        "num_cheque" : num_cheque,
        "formaPago3": formaPago3,
        "totalpago3": totalpago3,
        "institucion2": institucion2,
        "num_cuenta": num_cuenta,                    
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
                    
                   //MsgSalidaModalA(response);
                    nuevo_pago();
                }
            }
        });}else{MsgSalidaModalA("Valor debe ser igual al total de pago");}

    }
    if((totalpago === "")&&(totalpago2 !== "")&&(totalpago3 !== "")){
        formaPago = 0;
        
        totalpago = "0";
        
        var suma = Number($('#Valor').val()) + Number($('#Valor2').val());

        if(Number(suma).toFixed(2) === Number(valorecaudacion).toFixed(2)){ 
        var parametros = {
        "totalpago": totalpago,
        "formaPago": formaPago,
        "idRecaudacion": idRecaudacion,
        
        "formaPago2": formaPago2,
        "institucion": institucion,
        "totalpago2": totalpago2,
        "num_tarjeta": num_tarjeta,
        "num_cheque" : num_cheque,
        "formaPago3": formaPago3,
        "totalpago3": totalpago3,
        "institucion2": institucion2,
        "num_cuenta": num_cuenta,                    
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
                    
                   // MsgSalidaModalA(response);
                    nuevo_pago();
                }
            }
        });
        }
        
    }
  

}
 /*Codigo 003: Fin cambio */
function nuevo_pago() {
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("recaudacion?accion=listar1", {}, function () { });
}
function EliminaFila(strIdCuota){
    var cuota = strIdCuota;
     $("#fila_" + cuota).remove();
     Suma_totales();
}
function ConsultaRecaudadores(){
     document.getElementById("consulta_recaudador").innerHTML="";
     $.getJSON("recaudacion", {"accion" : "consulta_recaudador"}, function(result){
         console.log(result);
          $.each(result.datos, function(key, val){ 
              console.log(val.id_empleado);
           $("#consulta_recaudador").append($("<option>",{value:val.id_empleado,text:val.empleado}));
          });
    });

    
}