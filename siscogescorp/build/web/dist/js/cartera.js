/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function frm_cartera()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cartera?accion=agregar",{},function(){ });
}
function cartera()
{       jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cartera?accion=listar",{},function(){ });
}
function validaDatos(empresa,nombre,cliente,observacion,adicional,accion){
         if(empresa !== ""){
            if(cliente !== ""){ 
                if(nombre.length > 1  ){
                    if(observacion.length > 1 ){
                        if(adicional.length > 0 ){
                            return true;
                        }else{ MsgSalidaModalA("Debe elegir una informacion adicional");}
                    }else{ MsgSalidaModalA("Debe elegir una observacion");}
                }else{ MsgSalidaModalA("Debe ingresar un Nombre");}
            }else{ MsgSalidaModalA("Debe elegir un cliente");} 
        }else{ MsgSalidaModalA("Debe elegir una empresa");}    
   return false; 
}
$('#btncrearcartera').click(function(e){
   e.preventDefault();
   
   var empresa = $("#empresa").val();
   var nombre  = $("#nombre").val();
   var cliente = $("#cliente").val();
   var observacion = $("#observacion").val();
   var adicional = $("#adicional").val();
   var accion = $("#accion").val();
   
   if(validaDatos(empresa,nombre,cliente,observacion,adicional,accion)){
      
          var parametros = {
              "accion" : accion,
                "empresa" : empresa,
                "nombre" : nombre,
                "cliente" : cliente,
                "observacion" : observacion,
                "adicional" : adicional
            };
        $.ajax({
                data:  parametros,
                url:   'cartera',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           frm_cartera();//vuelvo a llamar a la pantalla
                      }                        
                }
        });
   }
});

function deletecartera(data)
        
{      if(confirm("Realmente desea eliminar los datos")){
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cartera?accion=eliminar&id=" + data,{},function(){ });
            cartera();
    }
        
}

function ConnsultaDatosID(str)        
{  

  if(confirm("Realmente desea actualizar los datos")){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      jQuery("#page-wrapper").load("cartera?accion=buscaID&id=" + str,{},function(){ });
  }
    
}

$('#btnactcartera').click(function(e){
   e.preventDefault();
   
    var iddeuda = $("#iddeuda").val();
   var empresa = $("#empresa").val();
   var nombre  = $("#nombre").val();
   var cliente = $("#cliente").val();
   var observacion = $("#observacion").val();
   var adicional = $("#adicional").val();
   var accion = $("#accion").val();
   
             var parametros = {
              "accion" : accion,
                "iddeuda" : iddeuda,
                "empresa" : empresa,
                "nombre" : nombre,
                "cliente" : cliente,
                "observacion" : observacion,
                "adicional" : adicional
                
                 };
        $.ajax({
                data:  parametros,
                url:   'cartera',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           cartera();//vuelvo a llamar a la pantalla
                      }                        
                }
        });  
   
   
 
    
       
});


