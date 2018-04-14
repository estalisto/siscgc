/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
                            url: 'consultacliente',
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
                            url: 'consultacliente',
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
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || ((event.keyCode > 57) ))
    {
       
           event.returnValue = false; 
        
        
    }
}
function hidden_cartera(){
   
  
       document.getElementById("cliente_cartera").style.display = 'none';
        document.getElementById("gestion_cliente").style.display = 'block';
  
   
    // $('#lista_empleados').css("display", "none");   

}
function GestionCliente2(stridCliente, stride){
var idCliente = stridCliente;
var idDeudor= stride;
var accion="GestionCliente";
hidden_cartera();

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
             document.getElementById("identificacion").value = val.Identificacion; 
             document.getElementById("deudor").innerHTML  = val.NombresCompletos; 
             document.getElementById("cliente").innerHTML = val.RazonSocialCliente; 
             document.getElementById("cuenta").value = val.NumCuenta; 
             document.getElementById("labelTotalDeuda").innerHTML = val.TotalDeuda; 
             document.getElementById("labelTotalVencido").innerHTML = val.TotalVencido; 
             document.getElementById("labelPagos").innerHTML = val.Pago; 
             document.getElementById("labelSaldos").innerHTML = val.Saldo; 
             document.getElementById("labelDiasMora").innerHTML = val.DiasMora+" Días"; 
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
getTiposGestiones2();
DireccionesJson2(idCliente, idDeudor);
TelefonosJson2(idCliente, idDeudor);
GestionesJson2(idCliente, idDeudor); 
DetalleCuotasJson2(idCliente, idDeudor);
console.log("MuestraDatosReferencias Cliente: "+idCliente+" Id_deudor: "+idDeudor );
MuestraDatosReferencias2(idCliente,idDeudor);
//ComprasJson(idCliente, idDeudor);
}
function getTiposGestiones2(){
    
document.getElementById("gestion").innerHTML="";
document.getElementById("gestion").innerHTML="";
//$("#tgestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
$("#gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
    $.getJSON("consultacartera", {"accion" : "TiposGestiones"}, function(result){
          $.each(result.tipo_gestion, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tgestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
           $("#gestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
    
   //document.getElementById("tresultado_gestion").disabled=true;
   //$("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
}
function DireccionesJson2(idCliente, idDeudor){
  
    var StringTablaCabecera="<table id='idAllDireccions' class=' table-striped table-bordered dt-responsive table-condensed table-hover' ><thead><tr  bgcolor='#FBF5EF' width='100%'><th >Tipo</th><th class='sorting_asc' tabindex='0' aria-controls='idAllDireccions' rowspan='1' colspan='1' style='width: 767px;' aria-label='Dirección: activate to sort column descending' aria-sort='ascending'>Dirección</th></tr></thead><tbody></tbody></table>";
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
                { "data": "Direccion" }
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
function TelefonosJson2(idCliente, idDeudor){
    
    var TablaTelefonos="<table id='idAllTelefonos' class='table table-striped table-bordered dt-responsive   table-condensed  table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FBF5EF'><th class='col-sm-2'>Tipo</th><th class='col-sm-8'>Télefonos</th><th class='col-sm-2'>Llamar</th></tr></thead><tbody></tbody></table>";
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
function GestionesJson2(idCliente, idDeudor){
    var TablaTelefonos="<table id='allTrxGestiones' class='table table-striped table-bordered table-hover' cellspacing='0' width='100%'><thead><tr  bgcolor='#FBF5EF'><th class='col-lg-1'>Tipo Gestión</th><th class='col-lg-1'>Gestión</th><th class='col-lg-5'>Descripción</th><th class='col-lg-1'>Oficial</th><th class='col-lg-1'>Fecha</th></tr></thead><tbody></tbody> </table>";
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
function DetalleCuotasJson2(idCliente, idDeudor){
   // ComprasJson(idCliente, idDeudor);
    var TablaCompras="<table id='detalle_cuotas' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'> <thead><tr bgcolor='#FBF5EF'><th>Artículo</th><th>NºCuota</th><th>Interés</th><th>Mora</th><th>Gastos Cobranzas</th><th>Gastos Adicionales</th><th>Otros Gastos</th><th>Valor Cuota</th><th>Total</th><th>Fecha max Pago</th><th>Fecha Registro</th><th>Pagos Realizados</th><th>Fecha Pagos Realizados</th></tr></thead><tbody></tbody><tfoot></tfoot></table>";
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
function MuestraDatosReferencias2(idCliente,idDeudor){
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