/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ConsultarQr(){
    var recepcion=$('#recepcion').val();
    var accion="consultadatos";
    if(recepcion!==""){
      $.getJSON("recepcion", {"accion" : accion,"recepcion":recepcion}, function(result){
        
        if((result.RecepcionDoc).valueOf()==""){
              $('#id_msm_doc').css("display", "block");
              document.getElementById("ticket").value  ="";
              document.getElementById("id_deudor").value  ="";
              document.getElementById("identificacion").value  ="";
              document.getElementById("nombres").value  ="";
              document.getElementById("TableRecepcion").innerHTML  ="";
              document.getElementById("idDoc").value  ="";
              document.getElementById("ruta").value  ="";
              document.getElementById("recepcionSI").disabled = true;
             document.getElementById("recepcionNO").disabled = true;
             }
          $.each(result.RecepcionDoc, function(key, val){             
              document.getElementById("ticket").innerHTML  ="";
              document.getElementById("id_deudor").innerHTML  ="";
              document.getElementById("identificacion").innerHTML  ="";
              document.getElementById("nombres").innerHTML  ="";
              document.getElementById("TableRecepcion").innerHTML  ="";
//              document.getElementById("nom_completos").innerHTML  ="";
//              document.getElementById("cedula").innerHTML  ="";
              //document.getElementById("datepicker").innerHTML  ="";
//              document.getElementById("observacion").innerHTML  ="";
              document.getElementById("idDoc").innerHTML  ="";
              document.getElementById("ruta").innerHTML  ="";
              
              document.getElementById("idDoc").value = val.IdDocGenerado;
              document.getElementById("ticket").value=val.ticket;
              document.getElementById("id_deudor").value=val.IdDeudor;
              document.getElementById("identificacion").value = val.identificacion;
             document.getElementById("nombres").value = val.nombres; 
             document.getElementById("TableRecepcion").innerHTML  = val.table; 
             document.getElementById("ruta").value = val.rutaDoc; 
             document.getElementById("recepcionSI").disabled = false;
             document.getElementById("recepcionNO").disabled = false;
             $('#id_msm_doc').css("display", "none");
//             document.getElementById("nom_completos").value=val.name_recepcion;
//              document.getElementById("cedula").value = val.Ide_recepcion;
//             //document.getElementById("datepicker").value = val.fecha_recepcion; 
//             document.getElementById("observacion").value = val.observacion_recepcion; 
              
            });
            
          });
    }else{
         MsgSalidaModalA("Debe Ingresar un Código de Documento");
    }
}
function EnviarRutass(){
    var page = $('#ruta').val();
    var accion ="buscarPDF";
    //alert(page);
    var parametros = {
            "page" : page,
            "accion" : accion
    };
    $.ajax({
            data:  parametros,
            url:   'recepcion',
            type:  'GET',
            beforeSend: function () {                      
            } ,
            success:  function (response){
                if(response){
                   
                }                         
            }
    });
    
}
$('#datepicker').datetimepicker({   format:'Y-m-d h:00', 
                                    minDate: ge_today_date(new Date())}); 
  function ge_today_date(date) {
     var day = date.getDate();
     var month = date.getMonth() + 1;
     var year = date.getFullYear().toString().slice(2);
     return day + '-' + month + '-' + year;
 }                               
function RecargaTable(){
    var recepcion=$('#recepcion').val();
    var accion="consultadatos";
    if(recepcion!==""){
      $.getJSON("recepcion", {"accion" : accion,"recepcion":recepcion}, function(result){
        
          $.each(result.RecepcionDoc, function(key, val){             
              
              document.getElementById("TableRecepcion").innerHTML  ="";
             
             document.getElementById("TableRecepcion").innerHTML  = val.table; 

              
            });
            
          });
    }
}
function DatosRecibidos(){
    var recepcion=$('#recepcion').val();
    var accion="datosrecibidos";
   
      $.getJSON("recepcion", {"accion" : accion,"recepcion":recepcion}, function(result){
        
          $.each(result.Recepcionrecibido, function(key, val){             
              
              document.getElementById("nom_completos").innerHTML  ="";
              document.getElementById("cedula").innerHTML  ="";
              document.getElementById("datepicker").innerHTML  ="";
              document.getElementById("observacion").innerHTML  ="";
              var observ=val.observacion_recepcion;
             //alert(observ);
             document.getElementById("nom_completos").text=val.name_recepcion;
              document.getElementById("cedula").value = val.Ide_recepcion;
             document.getElementById("datepicker").value = val.fecha_recepcion; 
             document.getElementById("observacion").value = val.observacion_recepcion; 
              
            });
            
          });
    
}
function Quitarcheck(){
    $("#recepcionSI").prop('checked', false);
    $("#recepcionNO").prop('checked', false);
}
function GuardarRecepcion(){
    var recepcion=$('#idDoc').val();
    var Ide_recepcion=$('#cedula').val();
    var name_recepcion=$('#nom_completos').val();
    var fecha_recepcion=$('#datepicker').val();
    var observacion=$('#observacion').val();
    var accion="guardar_recepcion";
    var condicionSi = $("#recepcionSI").is(":checked");
    var condicionNo = $("#recepcionNO").is(":checked");
    var condicional="";
    
    if(Ide_recepcion!==""){
        if(name_recepcion!==""){
            if(fecha_recepcion!==""){
    if(condicionSi==true){
       condicional="1";
    }
    if(condicionNo==true){
        condicional="0";
    }
    var parametros = {
              "accion" : accion,
              "recepcion" : recepcion,
              "Ide_recepcion":Ide_recepcion,
              "name_recepcion" : name_recepcion,
              "fecha_recepcion" : fecha_recepcion,
              "condicional" : condicional,
              "observacion" : observacion
    };
    $.ajax({
            data:  parametros,
            url:   'recepcion',
            type:  'GET',
            beforeSend: function () {                      
            } ,
            success:  function (response){
                if(response){
                                //alert(response);
                               if(response==1){
                                   //$('#data_recepcion').addClass("data-dismiss");
                                   $("#data_recepcion .close").click();
                                   MsgSalidaModalA("Documento Recibido");
                                   $("#recepcionSI").prop('checked', false);
                                   $("#recepcionNO").prop('checked', false);
                                    document.getElementById("nom_completos").value="";
                                    document.getElementById("cedula").value = "";
                                    document.getElementById("datepicker").value = ""; 
                                    document.getElementById("observacion").value = ""; 
                                    $('#id_message').css("display", "none");
                                    RecargaTable();
                                 }else{
                                     $("#data_recepcion .close").click();
                                    MsgSalidaModalA("Documento No Recibido");
                                    $("#recepcionSI").prop('checked', false);
                                    $("#recepcionNO").prop('checked', false);
                                    document.getElementById("nom_completos").value="";
                                    document.getElementById("cedula").value = "";
                                    document.getElementById("datepicker").value = ""; 
                                    document.getElementById("observacion").value = ""; 
                                    $('#id_message').css("display", "none");
                                    RecargaTable();
                                 } 
                            } else{
                            }                         
            }
    }); 
            }else{$('#id_message').css("display", "block");}
        }else{$('#id_message').css("display", "block");}
    }else{$('#id_message').css("display", "block");}
    
    
  
}

function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}


function EnviarRuta()
{
    var ruta = $('#ruta').val();
    var accion = "buscarPDF";
    //alert(ruta);
        window.open("recepcion?ruta=" + ruta + "&accion=" + accion, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=700,height=1000");
}
//   if(valor==="true"){
//       document.getElementById("cliente_cartera").style.display = 'none';
//        document.getElementById("gestion_cliente").style.display = 'block';
//  
//       
//   }
//   if(valor==="false"){
//         document.getElementById("cliente_cartera").style.display = 'block';
//        document.getElementById("gestion_cliente").style.display = 'none'; 
//   }
//}
