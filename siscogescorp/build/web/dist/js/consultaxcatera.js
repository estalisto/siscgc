/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ListarFiltro(strpg,strnumreg){
    var posicion = strpg;
    var registro = strnumreg;
   // alert(posicion);
    //alert(registro);
    var accion = "posicion";

    var parametros = {
        "posicion": posicion,
        "registro": registro,
        "accion": accion

    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {

            if (response) {
              // modulos_lista();
            }else{
                
                $('#consul_cartera').css("display", "none");
                $('#consultoria').css("display", "block");
               // modulos_lista();
            }
        }
    });
}
function modulos_lista()
{   
    jQuery("#pagetable").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    jQuery("#pagetable").load("consultacartera?accion=listando",{},function(){ });
}
function validaDatos(cartera){
         if(cartera !== "" ){
                return true;

         }else{ MsgSalidaModalA("Debe seleccionar un cliente de la cartera");}        
   return false; 
}


$('#btnbuscar').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();

   if(validaDatos(cartera)){
       modulos_asignados(cartera);
   }
    
});
function modulos_asignados(cartera)
{   
    jQuery("#pagetable").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    jQuery("#pagetable").load("consultacartera?accion=listar_datos&cartera="+cartera,{},function(){ });
}

$('#btnordenAsc').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenAsc";
   var opcion=2;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
       
               
            }
        }
    });
});


              

$('#ordenDesc').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenDesc";
   var opcion=0;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
                
            }
        }
    });
});
//
$('#btnordenAsc1').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenAsc";
   var opcion=3;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
                var x = document.getElementById("bodytable");
          
            }
        }
    });
});

$('#btnordenDesc1').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenDesc";
   var opcion=4;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
                
            }
        }
    });
});  

$('#btnordenAsc2').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenAsc";
   var opcion=6;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
                var x = document.getElementById("bodytable");
                
            }
        }
    });
});

$('#btnordenDesc2').click(function(e){
   e.preventDefault();
   var cartera = $("#cartera").val();
   var accion = "ordenDesc";
   var opcion=5;
   var parametros = {
        "cartera": cartera,
        "opcion": opcion,
        "accion": accion
    };
    $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                
                document.getElementById("bodytable").innerHTML = "";
                document.getElementById("bodytable").innerHTML = response;
                
            }
        }
    });
});    


function orderDiasMora(){
    var orden_dia=  document.getElementById("IdDiasMora").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosDiasMora";
   var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
   
    if(orden_dia==="0" || orden_dia===""){
       document.getElementById("IdDiasMora").value="1"; //ascendente    Menor a Mayor      
     orden_dia=document.getElementById("IdDiasMora").value;
     order_by = " ORDER BY s.dias_mora ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdDiasMora").value="0";   //descendente    Mayor a Menor  
    orden_dia=document.getElementById("IdDiasMora").value;
    order_by = " ORDER BY s.dias_mora DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("DiasMoraID").disabled="true";
    
            sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("DiasMoraID").disabled="false";
    /*var parametros = {
        "orden_dia": orden_dia,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("DiasMoraID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    });*/
    
    
    
}
function orderFchGestion(){
   var orden_Fecha=  document.getElementById("IdFechaUlt").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosDiasFecha";
   
   var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
   
    if(orden_Fecha==="0" || orden_Fecha===""){
       document.getElementById("IdFechaUlt").value="1"; //ascendente    Menor a Mayor      
     orden_Fecha=document.getElementById("IdFechaUlt").value;
      order_by = " ORDER BY s.fech_ultima_gestion ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdFechaUlt").value="0";   //descendente    Mayor a Menor  
    orden_Fecha=document.getElementById("IdFechaUlt").value;
     order_by = " ORDER BY s.fech_ultima_gestion DESC, s.id_datos_deudor";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("FechaID").disabled="true";
    
        sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("FechaID").disabled="false";
    /*var parametros = {
        "orden_Fecha": orden_Fecha,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("FechaID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}

function orderTotalVenc(){
   var orden_total=  document.getElementById("IdTotalVenc").value;
    var cartera=$("#cartera").val();
    //var accion="filtrosTotalVenc";
   
   var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
   
    if(orden_total==="0" || orden_total===""){
       document.getElementById("IdTotalVenc").value="1"; //ascendente    Menor a Mayor      
     orden_total=document.getElementById("IdTotalVenc").value;
      order_by = " ORDER BY s.total_vencidos ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdTotalVenc").value="0";   //descendente    Mayor a Menor  
    orden_total=document.getElementById("IdTotalVenc").value;
     order_by = " ORDER BY s.total_vencidos DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("TotalID").disabled="true";
    
    sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("TotalID").disabled="false";
   /* var parametros = {
        "orden_total": orden_total,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("TotalID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderIdent(){
   var orden_IDE=  document.getElementById("IdIdentificacion").value;
    var cartera=$("#cartera").val();
    //var accion="filtrosIDE";
    var order_by="";
    var sqlQuery=$("#input_query").val();

    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_IDE==="0" || orden_IDE===""){
       document.getElementById("IdIdentificacion").value="1"; //ascendente    Menor a Mayor      
     orden_IDE=document.getElementById("IdIdentificacion").value;
     order_by = " ORDER BY s.identificacion ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdIdentificacion").value="0";   //descendente    Mayor a Menor  
    orden_IDE=document.getElementById("IdIdentificacion").value;
    order_by = " ORDER BY s.identificacion DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("IdentificacionID").disabled="true";
   
     sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("IdentificacionID").disabled="false";
    /* var parametros = {
        "orden_IDE": orden_IDE,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("IdentificacionID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderNombre(){
   var orden_Nombre=  document.getElementById("IdNombres").value;
    var cartera=$("#cartera").val();
    //var accion="filtrosNombre";
    var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
   
    if(orden_Nombre==="0" || orden_Nombre===""){
       document.getElementById("IdNombres").value="1"; //ascendente    Menor a Mayor      
     orden_Nombre=document.getElementById("IdNombres").value;
      order_by = " ORDER BY s.nombres_completo ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdNombres").value="0";   //descendente    Mayor a Menor  
    orden_Nombre=document.getElementById("IdNombres").value;
     order_by = " ORDER BY s.nombres_completo DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("NombresID").disabled="true";
    
    
     sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("NombresID").disabled="false";
    
    /*var parametros = {
        "orden_Nombre": orden_Nombre,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("NombresID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderPagos(){
   var orden_Pago=  document.getElementById("IdPagos").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosPagos";
     var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_Pago==="0" || orden_Pago===""){
       document.getElementById("IdPagos").value="1"; //ascendente    Menor a Mayor      
     orden_Pago=document.getElementById("IdPagos").value;
     order_by = " ORDER BY s.pagos ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdPagos").value="0";   //descendente    Mayor a Menor  
    orden_Pago=document.getElementById("IdPagos").value;
    order_by = " ORDER BY s.pagos DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("PagosID").disabled="true";
    
    
     sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("PagosID").disabled="false";
    /*var parametros = {
        "orden_Pago": orden_Pago,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("PagosID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderSaldo(){
   var orden_Saldo=  document.getElementById("IdSaldo").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosSaldo";
     var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
   
   
    if(orden_Saldo==="0" || orden_Saldo===""){
       document.getElementById("IdSaldo").value="1"; //ascendente    Menor a Mayor      
     orden_Saldo=document.getElementById("IdSaldo").value;
     order_by = " ORDER BY s.saldo ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdSaldo").value="0";   //descendente    Mayor a Menor  
    orden_Saldo=document.getElementById("IdSaldo").value;
    order_by = " ORDER BY s.saldo DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("SaldosID").disabled="true";
   
      sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("SaldosID").disabled="false";
    
    
    
    /* var parametros = {
        "orden_Saldo": orden_Saldo,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("SaldosID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderValorComp(){
   var orden_ValorComp=  document.getElementById("IdValorComp").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosValorComp";
    var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_ValorComp==="0" || orden_ValorComp===""){
       document.getElementById("IdValorComp").value="1"; //ascendente    Menor a Mayor      
     orden_ValorComp=document.getElementById("IdValorComp").value;
     order_by = " ORDER BY s.valor_compro ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdValorComp").value="0";   //descendente    Mayor a Menor  
    orden_ValorComp=document.getElementById("IdValorComp").value;
    order_by = " ORDER BY s.valor_compro DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("ValorCompID").disabled="true";
   
     sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("ValorCompID").disabled="false";
    
    
    /* var parametros = {
        "orden_ValorComp": orden_ValorComp,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("ValorCompID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderFechaComp(){
   var orden_FechaComp=  document.getElementById("IdFechaComp").value;
    var cartera=$("#cartera").val();
    //var accion="filtrosFechaComp";
   var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_FechaComp==="0" || orden_FechaComp===""){
       document.getElementById("IdFechaComp").value="1"; //ascendente    Menor a Mayor      
       orden_FechaComp=document.getElementById("IdFechaComp").value;
        order_by = " ORDER BY s.fecha_comp ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdFechaComp").value="0";   //descendente    Mayor a Menor  
        orden_FechaComp=document.getElementById("IdFechaComp").value;
         order_by = " ORDER BY s.fecha_comp DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("FechaCompID").disabled="true";
   
    sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
   // consulta_query(sqlQuery,cartera);
    consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("FechaCompID").disabled="false";
    
    
    /*var parametros = {
        "orden_FechaComp": orden_FechaComp,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("FechaCompID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderUltima(){
   var orden_Ultima=  document.getElementById("IdUltima").value;
    var cartera=$("#cartera").val();
   // var accion="filtrosUltima";
    var order_by="";
    var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_Ultima==="0" || orden_Ultima===""){
       document.getElementById("IdUltima").value="1"; //ascendente    Menor a Mayor      
       orden_Ultima=document.getElementById("IdUltima").value;
       order_by = " ORDER BY s.ultima_gestion ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdUltima").value="0";   //descendente    Mayor a Menor  
         orden_Ultima=document.getElementById("IdUltima").value;
         order_by = " ORDER BY s.ultima_gestion DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("UltimaID").disabled="true";
    
     sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("UltimaID").disabled="false";
    /*var parametros = {
        "orden_Ultima": orden_Ultima,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("UltimaID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderResultado(){
   var orden_Resultado=  document.getElementById("IdResultado").value;
    var cartera=$("#cartera").val();
    var accion="filtrosResultado";
    var order_by="";
   var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    if(orden_Resultado==="0" || orden_Resultado===""){
       document.getElementById("IdResultado").value="1"; //ascendente    Menor a Mayor      
     orden_Resultado=document.getElementById("IdResultado").value;
        order_by = " ORDER BY s.resultado_gestion ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdResultado").value="0";   //descendente    Mayor a Menor  
    orden_Resultado=document.getElementById("IdResultado").value;
       order_by = " ORDER BY s.resultado_gestion DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("ResultadoID").disabled="true";
    
    sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("ResultadoID").disabled="false";
    /*var parametros = {
        "orden_Resultado": orden_Resultado,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("ResultadoID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
function orderFechaUltPagos(){
   var orden_FechaUltPagos=  document.getElementById("IdFecUltPagos").value;
    var cartera=$("#cartera").val();
    var accion="filtrosFechaUltPagos";
   var order_by="";
   var sqlQuery=$("#input_query").val();
   
    if(sqlQuery===""){
         MsgSalidaModalA("Debe realizar la consulta en la pantalla de filtros");
    return;    
    }
    
    if(orden_FechaUltPagos==="0" || orden_FechaUltPagos===""){
      document.getElementById("IdFecUltPagos").value="1"; //ascendente    Menor a Mayor      
     orden_FechaUltPagos=document.getElementById("IdFecUltPagos").value;
      order_by = " ORDER BY s.fecha_ult_pagos ASC, s.id_datos_deudor ";
    }else{
         document.getElementById("IdFecUltPagos").value="0";   //descendente    Mayor a Menor  
    orden_FechaUltPagos=document.getElementById("IdFecUltPagos").value;
     order_by = " ORDER BY s.fecha_ult_pagos DESC, s.id_datos_deudor ";
    }
    $('#id_loader').css("display", "block");
    document.getElementById("FecUltPagosID").disabled="true";
    
    sqlQuery=sqlQuery+order_by;
    console.log(sqlQuery);
    //realiza la consulta
    //consulta_query(sqlQuery,cartera);
     consulta_sec(sqlQuery,cartera,order_by);
    document.getElementById("FecUltPagosID").disabled="false";
    /*
    var parametros = {
        "orden_FechaUltPagos": orden_FechaUltPagos,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
                $('#id_loader').css("display", "none");
             document.getElementById("FecUltPagosID").disabled="false";
            var respuesta=response.toString();
              document.getElementById("bodytable").innerHTML = "";
              document.getElementById("bodytable").innerHTML = respuesta;
        }
    }); */
}
/*Codigo 007: Inicio cambio */
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: Funciones modificadas y adaptadas para los nuevos filtros*/

function consulta_filtro_cartera(){
document.getElementById("escogecliente").innerHTML="";
  var IdCliente=$("#cartera").val();
          //alert(IdCliente);
    if (parseInt(IdCliente) === 0){
        alert("Debe escoger un cliente");
        document.getElementById("escogecliente").innerHTML="Debe escoger un cliente";
        document.getElementById("escogecliente").style.color="red";
        return;
     
    } 
    
    var pagos_ini=$('#pagos').val();
    var pagos_fin=$('#pagos1').val();
    var tvencido_ini=$('#tvencido').val();
    var tvencido_fin=$('#tvencido1').val();
    var vcompromiso_ini=$('#vcompromiso').val();
    var vcompromiso_fin=$('#vcompromiso1').val();
    var saldos_ini=$('#saldos').val();
    var saldos_fin=$('#saldos1').val();
    var dia_mora_ini=$('#dia_mora').val();
    var dia_mora_fin=$('#dia_mora1').val();
    var fUltimo_pago_ini=$('#datetimepicker10').val();
    var fUltimo_pago_fin=$('#datetimepicker11').val();
    var fUltimo_gestion_ini=$('#datetimepicker12').val();
    var fUltimo_gestion_fin=$('#datetimepicker13').val();
    var fUltimo_compromiso_ini=$('#datetimepicker14').val();
    var fUltimo_compromiso_fin=$('#datetimepicker15').val();
    var SelectTipoGestion=$("#tgestion").val();
    var SelectTipoResultado=$("#tresultado_gestion").val();
    var carteras=$('#tcartera').val();
    var subcartera=$('#tsub_cartera').val();
    var segmento=$('#tsegmento').val();
    var subsegmento=$("#tsub_segmento").val();
    var cartera = $("#cartera").val();
    console.log("Paso1: >>>"+cartera);
    var accion = "nuevaConsulta";
    var order_by=$('#order_by').val();
    var lv_select=" select";
    var lv_datos=" '<a href=\"#\" onclick=\"GestionCliente('||s.id_cliente||','||s.id_datos_deudor||');\" >'||s.nombres_completo||'</a>' nombres_completo2,s.*  ";
    var lv_from=" from ";
  
    var lv_mi_empleado=$('#mi_empleado').val();//"IDEmpleadoConsulta";
    var lv_query=" vw_consulta_cartera s  ";
    var IDEmpleadoConsulta="";
    if(lv_mi_empleado===""){
        IDEmpleadoConsulta="and s.id_empleado=IDEmpleadoConsulta";
        
    }else{
        IDEmpleadoConsulta="and s.id_empleado="+lv_mi_empleado;
    }
     if(lv_mi_empleado==="0"){
            var txt;
            var r = confirm("Esta seguro que desea consultar todos los Gestores \nRecuerde que esta consulta puede detener los procesos actuales del Sistema. \nProcure utilizar los filtros");
            if (r == false) {
               return;
            } 
         
          IDEmpleadoConsulta="";
     }
    
    
      var lv_filtros=" where s.id_cliente=IDClienteConsulta "+IDEmpleadoConsulta+" and s.estado != 'E'";
    var sqlQuery=lv_select+lv_datos+lv_from+lv_query+lv_filtros;
    
    //
   var fmontos="";
   /*valida critrios de pagos*/
    if (pagos_ini.length !== 0 && pagos_fin.length !==0  && parseInt(pagos_ini) >= parseInt(pagos_fin)){        alert("El valor de PAGO Inicial debe ser MENOR a la PAGO final para realizar la consulta");        return; }
    if (pagos_ini.length !== 0 && pagos_fin.length !==0  && parseInt(pagos_ini) < parseInt(pagos_fin)){        fmontos+= " AND s.pagos >= "+pagos_ini+" AND s.pagos <= "+pagos_fin;    }
    if (pagos_ini.length !== 0 && pagos_fin.length === 0  && parseInt(pagos_ini) < 0){         alert("El valor en PAGOS ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (pagos_ini.length !== 0 && pagos_fin.length === 0  && parseInt(pagos_ini) > 0){        fmontos+= " AND s.pagos >= "+pagos_ini ;    }
    if (pagos_ini.length === 0 && pagos_fin.length !== 0  && parseInt(pagos_fin) < 0){         alert("El valor ingresdo en PAGOS debe ser mayo a 0 para realizar la consulta");        return;    }
    if (pagos_ini.length === 0 && pagos_fin.length !== 0  && parseInt(pagos_fin) > 0){        fmontos+= " AND s.pagos <= "+pagos_fin ;    }
     /*valida critrios de Total Deuda*/ 
    if (tvencido_ini.length !== 0 && tvencido_fin.length !==0  && parseInt(tvencido_ini) >= parseInt(tvencido_fin)){       alert("El valor de Deudoa Total Inicial debe ser MENOR a la PAGO final para realizar la consulta");        return; }
    if (tvencido_ini.length !== 0 && tvencido_fin.length !==0  && parseInt(tvencido_ini) < parseInt(tvencido_fin)){        fmontos+= " AND s.total_vencidos >= "+tvencido_ini+" AND s.total_vencidos <= "+tvencido_fin;    }
    if (tvencido_ini.length !== 0 && tvencido_fin.length === 0  && parseInt(tvencido_ini) < 0){        alert("El valor en Total Deuda ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (tvencido_ini.length !== 0 && tvencido_fin.length === 0  && parseInt(tvencido_ini) > 0){        fmontos+= " AND s.total_vencidos >= "+tvencido_ini ;    }
    if (tvencido_ini.length === 0 && tvencido_fin.length !== 0  && parseInt(tvencido_fin) < 0){        alert("El valor ingresdo en Total Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (tvencido_ini.length === 0 && tvencido_fin.length !== 0  && parseInt(tvencido_fin) > 0){        fmontos+= " AND s.pagtotal_vencidosos <= "+tvencido_fin ;    }
   
    /*valida critrios de Valor Compromiso*/ 
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length !==0  && parseInt(vcompromiso_ini) >= parseInt(vcompromiso_fin)){       alert("El valor de Compromiso de Pago Inicial debe ser MENOR a  Compromiso de Pago final para realizar la consulta");        return; }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length !==0  && parseInt(vcompromiso_ini) < parseInt(vcompromiso_fin)){        fmontos+= " AND s.valor_compro >= "+vcompromiso_ini+" AND s.valor_compro <= "+vcompromiso_fin;    }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length === 0  && parseInt(vcompromiso_ini) < 0){        alert("El valor en Compromiso de Pago ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length === 0  && parseInt(vcompromiso_ini) > 0){        fmontos+= " AND s.valor_compro >= "+vcompromiso_ini ;    }
    if (vcompromiso_ini.length === 0 && vcompromiso_fin.length !== 0  && parseInt(vcompromiso_fin) < 0){        alert("El valor Compromiso de Pago Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (vcompromiso_ini.length === 0 && vcompromiso_fin.length !== 0  && parseInt(vcompromiso_fin) > 0){        fmontos+= " AND s.valor_compro <= "+vcompromiso_fin ;    }
  
     /*valida critrios de Saldos*/ 
    if (saldos_ini.length !== 0 && saldos_fin.length !==0  && parseInt(saldos_ini) >= parseInt(saldos_fin)){       alert("El valor de Saldo Inicial debe ser MENOR al Saldo final para realizar la consulta");        return; }
    if (saldos_ini.length !== 0 && saldos_fin.length !==0  && parseInt(saldos_ini) < parseInt(saldos_fin)){        fmontos+= " AND s.saldo >= "+saldos_ini+" AND s.saldo <= "+saldos_fin;    }
    if (saldos_ini.length !== 0 && saldos_fin.length === 0  && parseInt(saldos_ini) < 0){        alert("El valor Saldo ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (saldos_ini.length !== 0 && saldos_fin.length === 0  && parseInt(saldos_ini) > 0){        fmontos+= " AND s.saldo >= "+saldos_ini ;    }
    if (saldos_ini.length === 0 && saldos_fin.length !== 0  && parseInt(saldos_fin) < 0){        alert("El valor Saldo Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (saldos_ini.length === 0 && saldos_fin.length !== 0  && parseInt(saldos_fin) > 0){        fmontos+= " AND s.saldo <= "+saldos_fin ;    }
  
     /*valida critrios de Dias de Mora*/ 
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length !==0  && parseInt(dia_mora_ini) >= parseInt(dia_mora_fin)){       alert("El valor de Dias Mora Inicial debe ser MENOR a Dias Mora final para realizar la consulta");        return; }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length !==0  && parseInt(dia_mora_ini) < parseInt(dia_mora_fin)){        fmontos+= " AND s.dias_mora >= "+dia_mora_ini+" AND s.dias_mora <= "+dia_mora_fin;    }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length === 0  && parseInt(dia_mora_ini) < 0){        alert("El valor Dias Mora ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length === 0  && parseInt(dia_mora_ini) > 0){        fmontos+= " AND s.dias_mora >= "+dia_mora_ini ;    }
    if (dia_mora_ini.length === 0 && dia_mora_fin.length !== 0  && parseInt(dia_mora_fin) < 0){        alert("El valor Dias Mora Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (dia_mora_ini.length === 0 && dia_mora_fin.length !== 0  && parseInt(dia_mora_fin) > 0){        fmontos+= " AND s.dias_mora <= "+dia_mora_fin ;    }
  
     /*valida critrios de Fecha Ultimo Pago*/ 
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length !==0  && Date.parse(fUltimo_pago_ini) >= Date.parse(fUltimo_pago_fin)){       alert("La fecha de Ultimo Pago Inicial debe ser MENOR a la fecha de Ultimo Pago final para realizar la consulta");        return; }
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length !==0  && Date.parse(fUltimo_pago_ini) < Date.parse(fUltimo_pago_fin)){        fmontos+= " AND s.fecha_ult_pagos >= '"+fUltimo_pago_ini+"' AND s.fecha_ult_pagos <= '"+fUltimo_pago_fin+"' ";    }
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length === 0 ){ fmontos+= " AND s.fecha_ult_pagos >= '"+fUltimo_pago_ini+"' ";     }
    if (fUltimo_pago_ini.length === 0 && fUltimo_pago_fin.length !== 0 ){ fmontos+= " AND s.fecha_ult_pagos <= '"+fUltimo_pago_fin+"' ";    }
  
    /*valida critrios de Fecha Ultima Gestión*/ 
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length !==0  && Date.parse(fUltimo_gestion_ini) >= Date.parse(fUltimo_gestion_fin)){       alert("La fecha de Ultimo Gestión Inicial debe ser MENOR a la fecha de Ultimo Gestión final para realizar la consulta");        return; }
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length !==0  && Date.parse(fUltimo_gestion_ini) < Date.parse(fUltimo_gestion_fin)){        fmontos+= " AND s.fech_ultima_gestion >= '"+fUltimo_pago_ini+"' AND s.fech_ultima_gestion <= '"+fUltimo_gestion_fin+"' ";    }
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length === 0 ){ fmontos+= " AND s.fech_ultima_gestion >= '"+fUltimo_gestion_ini+"' ";     }
    if (fUltimo_gestion_ini.length === 0 && fUltimo_gestion_fin.length !== 0 ){ fmontos+= " AND s.fech_ultima_gestion <= '"+fUltimo_gestion_fin+"' ";    }
  
    /*valida critrios de Fecha Ultima Compromiso*/ 
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length !==0  && Date.parse(fUltimo_compromiso_ini) >= Date.parse(fUltimo_compromiso_fin)){       alert("La fecha de Compromiso inicial debe ser MENOR a la fecha de Compromiso final para realizar la consulta");        return; }
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length !==0  && Date.parse(fUltimo_compromiso_ini) < Date.parse(fUltimo_compromiso_fin)){        fmontos+= " AND s.fecha_comp >='"+fUltimo_compromiso_ini+"' AND s.fecha_comp <= '"+fUltimo_compromiso_fin+"' ";    }
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length === 0 ){ fmontos+= " AND s.fecha_comp >= '"+fUltimo_compromiso_ini+"'";     }
    if (fUltimo_compromiso_ini.length === 0 && fUltimo_compromiso_fin.length !== 0 ){ fmontos+= " AND s.fecha_comp <= '"+fUltimo_compromiso_fin+"' ";    }
  
//  alert(SelectTipoGestion);
//  return;
    if (SelectTipoGestion!== "0"){
       fmontos+= " AND s.ultima_gestion = '"+$('#tgestion').find('option:selected').text()+"'";         
    }  
    if (SelectTipoResultado!== "0"){
       fmontos+= " AND s.resultado_gestion = '"+$('#tresultado_gestion').find('option:selected').text()+"'";      
    } 
    if ((carteras!== "0")&&(carteras!== null)){
        
       fmontos+= " AND s.id_cartera = "+$('#tcartera').find('option:selected').val();         
    }
    if ((subcartera!== "0")&&(subcartera!==null)){
       
       fmontos+= " AND s.id_sub_cartera = "+$('#tsub_cartera').find('option:selected').val();         
    }
    if ((segmento!== "0")&&(segmento!==null)){
        
       fmontos+= " AND s.id_segmento = "+$('#tsegmento').find('option:selected').val();         
    }
    if ((subsegmento!== "0")&&(subsegmento!== null)){
        
       fmontos+= " AND s.id_sub_segmento = "+$('#tsub_segmento').find('option:selected').val();         
    }
    if (order_by!==""){
        
       order_by= " ORDER BY s."+order_by+" DESC";
    }
    
       $('#id_loader').css("display", "block");
       //arma el query para la consula
    sqlQuery=sqlQuery+fmontos+order_by;
    document.getElementById("input_query").value = "";
    document.getElementById("input_query").value = sqlQuery;
    console.log(sqlQuery);
   //var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left hidden' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' onclick='orderIdent()'>Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' onclick='orderNombre()'>Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID' onclick='orderDiasMora()' >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID' onclick='orderTotalVenc()' >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' onclick='orderPagos()'>Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' onclick='orderFechaUltPagos()'>Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' onclick='orderSaldo()'>Saldo</a></th><th align='center' class='col-sm-1 text-right'><a id='ValorCompID' onclick='orderValorComp()'>Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' onclick='orderFechaComp()'>Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' onclick='orderFchGestion()' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' onclick='orderUltima()'>Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' onclick='orderResultado()'>Resultado Gestión</a></th></tr> </thead><tbody>";
     var parametros = {
        "sqlQuery":sqlQuery,
        "cartera": cartera,
        "accion": accion
    };
    console.log("Paso2: >>>"+cartera);
     document.getElementById("tabla_div").innerHTML = "";
    var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FEC187'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' >Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' >Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID'  >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID'  >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' >Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' >Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' >Saldo</a></th> <th align='center' class='col-sm-1 text-right'><a id='ValorCompID' >Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' >Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' >Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' >Resultado Gestión</a></th></tr> </thead><tbody></tbody></table>";
    document.getElementById("tabla_div").innerHTML = htmlTable;
    
    $('#consul_cartera').DataTable( {
        "ajax": {   
            "data": {"accion": accion,"sqlQuery": sqlQuery,"cartera": cartera},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "id_datos_deudor","title":"ID", "visible": false },
                { "data": "identificacion" },
                { "data": "nombres_completo2" },
                { "data": "dias_mora" },
                { "data": "total_vencidos" },
                { "data": "pagos" },
                { "data": "fecha_ult_pagos" },
                { "data": "saldo" },
                { "data": "valor_compro" },
                { "data": "fecha_comp" },
                { "data": "fech_ultima_gestion" },
                { "data": "ultima_gestion" },
                { "data": "resultado_gestion" }
            ],"language": {
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
    			},
            paging: false
    } );
    $('#id_loader').css("display", "none");
    $('#det_filtro').modal('hide');   

   consulta_sec(sqlQuery,cartera,"");
    
table = $('#consul_cartera').DataTable();

$('#consul_cartera thead').on('click', 'th', function () {
 var index = table.column(this).index();
console.log('consulta de columna: '+index);
     switch (index) {
    case 1:
        orderIdent();
        break;
    case 2:
        orderNombre();
        break;
    case 3:
        orderDiasMora();
        break;
    case 4:
        orderTotalVenc();
        break;
    case 5:
        orderPagos();
        break;
    case 6:
        orderFechaUltPagos();
        break;
    case 7:
        orderSaldo();
        break;
    case 8:
        orderValorComp();
        break;
    case 9:
        orderFechaComp();
        break;
    case 10:
        orderFchGestion();
        break;
    case 11:
        orderUltima();
        break;
    case 12:
        orderResultado();
        break;    
}

   
    $('#id_loader').css("display", "none");
 // alert('index : '+index);
});

    // alert(sqlQuery); 
    
    var qCantClientes=lv_select+" count(*)  "+lv_from+lv_query+lv_filtros+fmontos+order_by;
    
    var qSum=lv_select+" sum(s.total_vencidos) as total_vencidos, sum(s.pagos) as pagos, (sum(s.total_vencidos) -  sum(pagos)) as saldo, ("+qCantClientes +") as total_clientes "+lv_from+lv_query+lv_filtros+fmontos+order_by;
    console.log(qSum);
    Totales_Suman(qSum, IdCliente);
} 

function getipoSubCartera(){
var  idcartera=$('#tcartera').val();   
    document.getElementById("tsub_cartera").disabled=true;
    document.getElementById("tsub_cartera").innerHTML="";
    $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
    if(idcartera === 0 || idcartera === "" ) {  
        
         return;
     }
  if(idcartera!==0){
      
     document.getElementById("tsub_cartera").disabled=false;
     document.getElementById("tsub_cartera").innerHTML="";
       $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
        $.getJSON("asignacioncartera", {"accion" : "Subcarteras","idcartera":idcartera}, function(result){
              $.each(result.tipo_subcartera, function(key, val){   
                 
              // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
               $("#tsub_cartera").append($("<option>",{value:val.idSubcartera,text:val.nombreSubcartera}));
               //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
              });
        });

//       document.getElementById("tsub_cartera").disabled=true;
//       $("#tSubCartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
   }
} 
function getipoSegmento(){
var  idsubcartera=$('#tsub_cartera').val();  
document.getElementById("tsegmento").disabled=true;
document.getElementById("tsegmento").innerHTML="";
$("#tsegmento").append($("<option>",{value:"0",text:"Seleccione Segmento"}));
 if(idsubcartera === 0 || idsubcartera === "" ) {  
        
         return;
     }
  if(idsubcartera!==0){
      document.getElementById("tsegmento").disabled=false;
      document.getElementById("tsegmento").innerHTML="";
$("#tsegmento").append($("<option>",{value:"0",text:"Seleccione Segmento"}));
    $.getJSON("asignacioncartera", {"accion" : "TiposSegmentos","idsubcartera":idsubcartera}, function(result){
          $.each(result.tipo_segmento, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tsegmento").append($("<option>",{value:val.idSegmento,text:val.nombreSegmento}));
           //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
}
//   document.getElementById("tSubSegmento").disabled=true;
//   $("#tSubSegmento").append($("<option>",{value:"0",text:"Seleccione el SubSegmento"}));
}
function getiposubSegmento(){
var  idsegmento=$('#tsegmento').val();  
document.getElementById("tsub_segmento").disabled=true;
document.getElementById("tsub_segmento").innerHTML="";
$("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione Subsegmento"}));
 if(idsegmento === 0 || idsegmento === "" ) {  
        
         return;
     }
  if(idsegmento!==0){
      document.getElementById("tsub_segmento").disabled=false;
      document.getElementById("tsub_segmento").innerHTML="";
$("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione Subsegmento"}));
    $.getJSON("asignacioncartera", {"accion" : "Subsegmentos","idsegmento":idsegmento}, function(result){
          $.each(result.tipo_subsegmento, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tsub_segmento").append($("<option>",{value:val.IdSubsegmento,text:val.NombreSubsegmento}));
           //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
}
//   document.getElementById("tSubSegmento").disabled=true;
//   $("#tSubSegmento").append($("<option>",{value:"0",text:"Seleccione el SubSegmento"}));
} 
/*Fin cambio Jimmy Guaranda*/
function valores_totales(sqlQuery, cartera){
var sqlQuery = sqlQuery;
var cartera= cartera;
var accion="nuevaConsulta";
var arreglo=[];
var cont=0;
var total=0;
var arreglos="";
var valor=0;
  $.getJSON("consultacartera", {"accion" : accion,"sqlQuery":sqlQuery,"cartera":cartera}, function(result){
         
          $.each(result.data, function(key, val){             
//              document.getElementById("tcompromiso").innerHTML  ="";
//              document.getElementById("tvencidos").innerHTML  ="";
//              document.getElementById("tpagos").innerHTML  ="";
              //document.getElementById("labelTotalVencido").innerHTML  ="";
              
//               var total_vencido += val.total_vencidos;
//              var totalv=total_vencido+val.total_vencidos;
              //document.getElementById("tcompromiso").value=totalv;
//              for(var i=0;;i++){
//                  
//              }
              
               arreglo=val;
               var valor=JSON.stringify(arreglo);
               sumando(arreglo);
//               $.each(arreglos,function(){
//                   alert("entro");
//                    valor = parseInt(arreglos[cont].amount);
//                    cont++;
//                    total = total+valor;
//                 });
//              alert(total);
              
              //document.getElementById("tvencidos").value += Number(val.total_vencidos).toFixed(2);
//              document.getElementById("tpagos").value=val.IdDeudor;
              //document.getElementById("idcliente").value=val.IdCliente;
            
            
            
          });
    });

}
function sumando(arreglo){
    var cont=0;
    var total=0;
    var valor=0;
    var areglo1=[];
    //console.log(arreglo);
    var totales1="";
    $.each(arreglo,function(){
                   //alert("entro");
                   totales1=arreglo.total_vencidos;
                   var jsonArray=JSON.stringify(totales1);
                   areglo1=jsonArray;
                   console.log(jsonArray);
                    valor = parseInt(areglo1.indexOf(cont));
                    console.log(valor);
                    cont++;
                    total = parseInt(total)+parseInt(valor);
                    
                 });
                 
                 document.getElementById("tvencidos").value += total;
}
function consulta_query(sqlQuery,cartera){
 
    var accion="nuevaConsulta";
       var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left hidden' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' onclick='orderIdent()'>Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' onclick='orderNombre()'>Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID' onclick='orderDiasMora()' >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID' onclick='orderTotalVenc()' >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' onclick='orderPagos()'>Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' onclick='orderFechaUltPagos()'>Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' onclick='orderSaldo()'>Saldo</a></th><th align='center' class='col-sm-1 text-right'><a id='ValorCompID' onclick='orderValorComp()'>Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' onclick='orderFechaComp()'>Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' onclick='orderFchGestion()' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' onclick='orderUltima()'>Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' onclick='orderResultado()'>Resultado Gestión</a></th></tr> </thead><tbody>";
     var parametros = {
        "sqlQuery":sqlQuery,
        "cartera": cartera,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
             $('#id_loader').css("display", "none");
             if (response) {
              $("#consul_cartera").remove();           
              // $("#consul_cartera").append(response.toString());
           document.getElementById("tabla_div").innerHTML = "";
           document.getElementById("tabla_div").innerHTML = htmlTable+response.toString()+"</tbody></table>";
           datatableok();
               
            }      
        }
    });
     //consulta_sec(sqlQuery,cartera);
}


function fnc_order_by(orden) {
    document.getElementById("order_by").value = "";
    document.getElementById("order_by").value = orden;
}
/*Codigo 007: Inicio cambio */
/*Desarrollador: Jimmy Guaranda*/
/*Objetivo: Funciones modificadas y adaptadas para los nuevos filtros*/
function carterasFunction(){
    console.log("SeleccionaCliente");
    SeleccionaCliente();
    console.log("getipoCartera");
    getipoCartera();
     console.log(">>>>>>>>>");
}
function getipoCartera(){
var  idcliente=$('#cartera').val();   
    
    document.getElementById("escogecliente").innerHTML="";
    if(idcliente === 0 || idcliente === "" ) {  
         return;
     }
  //if(idcliente!==0){
      document.getElementById("tcartera").disabled=false;
     document.getElementById("tcartera").innerHTML="";
    $("#tcartera").append($("<option>",{value:"0",text:"Seleccione Cartera"}));
        $.getJSON("asignacioncartera", {"accion" : "TiposCarteras","idcliente":idcliente}, function(result){
              $.each(result.tipo_cartera, function(key, val){             
              // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
               $("#tcartera").append($("<option>",{value:val.idCartera,text:val.nombreCartera}));
               //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
              });
        });
    //}
     
   document.getElementById("tsub_cartera").disabled=true;
   $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la Subcartera"}));
    document.getElementById("tsegmento").disabled=true;
   $("#tsegmento").append($("<option>",{value:"0",text:"Seleccione el Segmento"}));
    document.getElementById("tsub_segmento").disabled=true;
   $("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione el Subsegmento"}));
} 
/*Fin cambio Jimmy Guaranda*/
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
function existeFecha(fecha){
      var fechaf = fecha.split("-");
      var year = fechaf[0];
      var month = fechaf[1];
      var day = fechaf[2];
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}

function getTiposGestiones(){
document.getElementById("escogecliente").innerHTML="";
  var IdCliente=$("#cartera").val();
          //alert(IdCliente);
    if (parseInt(IdCliente) === 0){
        //alert("Debe escoger un cliente");
        document.getElementById("escogecliente").innerHTML="Debe escoger un cliente";
        document.getElementById("escogecliente").style.color="red";
        return;
     
    } 
    
document.getElementById("tgestion").innerHTML="";
document.getElementById("gestion").innerHTML="";
$("#tgestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
$("#gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
    $.getJSON("consultacartera", {"accion" : "TiposGestiones"}, function(result){
          $.each(result.tipo_gestion, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tgestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
           var valor_select = val.nombreTipoGestion;
          // alert(valor_select);
           if(valor_select === "LLAMADA CLIENTE"){
               
               $("#gestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
            }
          });
    });
    
   document.getElementById("tresultado_gestion").disabled=true;
   $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
   //getipoSubCartera();
   getipoCartera();
    
}



function SeleccionaCliente(){
    var IdCliente=$("#cartera").val();
    var accion="GuardaClienteSession";
   
    
     var parametros = {
        "IdCliente":IdCliente,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            
            if(response){
               var reponde=response; 
              // console.log(reponde);
            }
             
        }
    });
    
 //  return reponde; 
    
}


function consultaMisClientes(){
document.getElementById("cartera").innerHTML="";
console.log("Ingreso aqui>>>>");
//  $("#cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("consultacartera", {"accion" : "MisClientes"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#cartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
           var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}


function ConsultaTipoResultado(){
  var IdTipoGestion=$("#tgestion").val();
  var idcliente="";
   // if(IdTipoGestion===0){
   
     document.getElementById("tresultado_gestion").disabled=true;
     document.getElementById("tresultado_gestion").innerHTML="";
     $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));  
  if(IdTipoGestion === 0 || IdTipoGestion === "0" ) {  
         return;
     }
  if(IdTipoGestion!==0){
      document.getElementById("tresultado_gestion").disabled=false;
      document.getElementById("tresultado_gestion").innerHTML="";
      $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));
     $.getJSON("consultacartera", {"accion" : "TiposResulatdos","idcliente":idcliente}, function(result){
         console.log(result);
         console.log('size: '+result.tipos_resultado.tipos_resultado);
          $.each(result.tipos_resultado, function(key, val){             
           $("#tresultado_gestion").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
          });
    });
  }
    
}

function hidden_cartera_cliente(valor){
   
   if(valor==="true"){
       document.getElementById("cliente_cartera").style.display = 'none';
        document.getElementById("gestion_cliente").style.display = 'block';
   }
   if(valor==="false"){
         document.getElementById("cliente_cartera").style.display = 'block';
        document.getElementById("gestion_cliente").style.display = 'none'; 
   }
}
function datatableok(){
    console.log("consulta cartera");
           $("#consul_cartera").DataTable({
                    "paging": false,
                    "lengthChange": false,
                    "searching": true,
                    "ordering": false,
                    "info": false,
                    "autoWidth": true
                  }); 
        }
        
function consulta_sec(sqlQuery,cartera,ordenQuery){
    var accion="consulta_secuencia";
     console.log('consulta_sec>>> '+sqlQuery);
     
     
     var parametros = {
        "sqlQuery":sqlQuery,
        "cartera":cartera,
        "ordenQuery":ordenQuery,
        "accion": accion
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
             document.getElementById("secuencia_query").value = "";   
             document.getElementById("secuencia_query").value = response.toString(); 
             
        }
    });
    
    
    
}


function Totales_Suman(lv_query_sum, cartera){
 var accion="suman_totales";
 
     $.getJSON("consultacartera", {"accion": accion,"sqlQuery": lv_query_sum,"cartera":cartera}, function(result){
         console.log(result);
        // console.log('data: '+result.data.total_vencidos);
          $.each(result.data, function(key, val){  
              console.log('data: '+val.total_vencidos);
               document.getElementById("ttvencidos").innerHTML=val.total_vencidos;
               document.getElementById("ttpagos").innerHTML=val.pagos;
               document.getElementById("ttsaldos").innerHTML=val.saldo;
               document.getElementById("cant_clientes_asignados").innerHTML=val.total_clientes;
          // $("#tresultado_gestion").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
          });
    });
   

    
}


function BuscarCliente(){
    console.log("BuscarCliente...");
    var idcartera=$("#idcartera").val();
    var identificacion=$("#identificacion2").val();
    var nombre_deudor=$("#nombre_deudor").val();
    var accion = "nuevaConsulta";
    
    var lv_select=" select";
    var lv_datos=" '<a href=\"#\" onclick=\"GestionCliente('||s.id_cliente||','||s.id_datos_deudor||');\" >'||s.nombres_completo||'</a>' nombres_completo2,s.*  ";
    var lv_from=" from ";
    var lv_filtros=" where s.id_cliente=IDClienteConsulta and s.id_empleado=IDEmpleadoConsulta and s.estado != 'E'";
    var lv_query=" vw_consulta_cartera s  ";
    var order_by="";
     var sqlQuery="";
    
    document.getElementById("mensaje_error").innerHTML="";
    if(idcartera==="0"){       
        document.getElementById("mensaje_error").innerHTML="Debe escoger el CEDENTE";
        document.getElementById("mensaje_error").style.color="red"; 
        return;        
    }
    if (identificacion.length<9 && nombre_deudor ===""){
       document.getElementById("mensaje_error").innerHTML="La identificación no puede ser menor a 10 caracteres";
        document.getElementById("mensaje_error").style.color="red"; 
        return;   
    }
    
    if (identificacion>5 && nombre_deudor ===""){
        document.getElementById("mensaje_error").innerHTML="Se ha realizado la Busqueda por Identificación";
        document.getElementById("mensaje_error").style.color="blue";
      sqlQuery=lv_select+lv_datos+lv_from+lv_query+ " where s.id_cliente="+idcartera+" and identificacion='"+identificacion+"' and s.estado != 'E'"; 
    }
    if (identificacion==="" && nombre_deudor !==""){
        document.getElementById("mensaje_error").innerHTML="Se ha realizado la Busqueda por Nombre del Deudor";
        document.getElementById("mensaje_error").style.color="blue"; 
      sqlQuery=lv_select+lv_datos+lv_from+lv_query+ " where s.id_cliente="+idcartera+" and nombres_completo like '%"+nombre_deudor+"%' and s.estado != 'E'"; 
    }
    
     if (order_by!==""){
        
       order_by= " ORDER BY s."+order_by+" DESC";
    }
    
    //   $('#id_loader').css("display", "block");
       //arma el query para la consula
    sqlQuery=sqlQuery+order_by;
    console.log("Nueva Consulta>>"+sqlQuery);
    document.getElementById("input_query").value = "";
    document.getElementById("input_query").value = sqlQuery;
    console.log(sqlQuery);
   //var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left hidden' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' onclick='orderIdent()'>Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' onclick='orderNombre()'>Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID' onclick='orderDiasMora()' >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID' onclick='orderTotalVenc()' >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' onclick='orderPagos()'>Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' onclick='orderFechaUltPagos()'>Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' onclick='orderSaldo()'>Saldo</a></th><th align='center' class='col-sm-1 text-right'><a id='ValorCompID' onclick='orderValorComp()'>Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' onclick='orderFechaComp()'>Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' onclick='orderFchGestion()' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' onclick='orderUltima()'>Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' onclick='orderResultado()'>Resultado Gestión</a></th></tr> </thead><tbody>";
     var parametros = {
        "sqlQuery":sqlQuery,
        "cartera": idcartera,
        "accion": accion
    };
     document.getElementById("tabla_div").innerHTML = "";
    var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#B5EE8E'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' >Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' >Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID'  >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID'  >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' >Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' >Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' >Saldo</a></th> <th align='center' class='col-sm-1 text-right'><a id='ValorCompID' >Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' >Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' >Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' >Resultado Gestión</a></th></tr> </thead><tbody></tbody></table>";
    document.getElementById("tabla_div").innerHTML = htmlTable;
    
    $('#consul_cartera').DataTable( {
        "ajax": {   
            "data": {"accion": accion,"sqlQuery": sqlQuery,"cartera": idcartera},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "id_datos_deudor","title":"ID", "visible": false },
                { "data": "identificacion" },
                { "data": "nombres_completo2" },
                { "data": "dias_mora" },
                { "data": "total_vencidos" },
                { "data": "pagos" },
                { "data": "fecha_ult_pagos" },
                { "data": "saldo" },
                { "data": "valor_compro" },
                { "data": "fecha_comp" },
                { "data": "fech_ultima_gestion" },
                { "data": "ultima_gestion" },
                { "data": "resultado_gestion" }
            ],
            paging: false,
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar Cliente:",
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
       
  hidden_cartera_cliente('false');
    $('#myModalBusqueda').modal('hide'); 
  
}

function mostrar_busqueda_deudor(){
    var rol=$("#rol_empleado").val();
    console.log("rol del empleado es "+rol);

        document.getElementById("busqueda_deudor").style.display  = "none";  
         document.getElementById("filtro_busqueta").style.display = "block"; 
        document.getElementById("filtro_cartera").style.display = "block";   
        
        
    if(rol.toString()=== "RECAUDADOR" ){
        console.log("rol del empleado es2 "+rol);
        document.getElementById("busqueda_deudor").style.display = "block";            
        document.getElementById("filtro_busqueta").style.display = "none"; 
        document.getElementById("filtro_cartera").style.display = "none";  
        document.getElementById("siguiente").style.display = "none"; 
        document.getElementById("anterior").style.display = "none";
        
         
    }
   /* if(rol.toString()==="ADMIN" || rol.toString()==="SUPERADMIN" || rol.toString()==="DIGITADOR"){
          document.getElementById("busqueda_deudor").style.display = "block"; 
    }*/
     var parametros = {
        "valida_rol":rol.toString(),
        "accion": "OPCION_BUSCAR_CLIENTE"
    };
        $.ajax({
        data: parametros,
        url: 'consultacartera',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            console.log("response>>"+response);
             if(response.toString()==="true"){
                 document.getElementById("busqueda_deudor").style.display = "block";                  
             }
             
        }
    });
      
}
/*
 * 
 * 
 * "data": {"accion": accion,"sqlQuery": sqlQuery,"cartera": idcartera},
            "url": "consultacartera",
            "type": "GET",
           "columns": [
                { "data": "id_datos_deudor","title":"ID", "visible": false },
                { "data": "identificacion" },
                { "data": "nombres_completo2" },
                { "data": "dias_mora" },
                { "data": "total_vencidos" },
                { "data": "pagos" },
                { "data": "fecha_ult_pagos" },
                { "data": "saldo" },
                { "data": "valor_compro" },
                { "data": "fecha_comp" },
                { "data": "fech_ultima_gestion" },
                { "data": "ultima_gestion" },
                { "data": "resultado_gestion" }
            ],
 */
function consulta_cartera_empleados(){
 var accion = "consultar_cliente_empleado1";
 document.getElementById("mitable_cartera_empleado").innerHTML = "";
    var htmlTable="<table id='cartera_empleado' class='table table-striped table-bordered dt-responsive nowrap table-hover'><thead><tr bgcolor='#FEC187'> <th>Empleados</th><th>Cartera</th><th>Asignados</th><th>Recuperado</th></tr></thead><tbody></tbody></table>";
    document.getElementById("mitable_cartera_empleado").innerHTML = htmlTable;
    
    $('#cartera_empleado').DataTable( {
        "ajax": {   
            "data": {"accion": accion},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "empleado"},
                { "data": "razon_social" },
                { "data": "asignados" },
                { "data": "valor_recuperado" }
            ],
            paging: false,
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
       
}

function consulta_cedentes_subcartera(){
 var accion = "consulta_subcartera_cendente";
 document.getElementById("cedente_subcartera").innerHTML = "";
    var htmlTable="<table id='subcaretras' class='table table-striped table-bordered dt-responsive nowrap table-hover'><thead><tr bgcolor='#FEC187'><th>Cendente</th><th>Sub Carteras</th><th>Total Clientes</th></tr></thead><tbody></tbody></table>";
    document.getElementById("cedente_subcartera").innerHTML = htmlTable;
    
    $('#subcaretras').DataTable( {
        "ajax": {   
            "data": {"accion": accion},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "cliente"},
                { "data": "sub_cartera" },
                { "data": "cant_clientes" }
            ],
            paging: false,
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
       
}