/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function modulos()
{       jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("modulos?accion=listar",{},function(){ });
}

LlenarROl();
//$('#body_modulo-event').on('change', '#empresa', function () 
function LlenarROl(){
    var empresa = $("#empresa").val();
    
    if (empresa !== ""){
        $.post("sistema/modulos/combo_rol.jsp",$("#data").serialize(),function(data){$("#rol").html(data);
        });
        document.getElementById("rol").disabled = false;
        
    }
 

            
    }



function ActivaModulo(str,str_lvl,str_grp,str_ord){
    
    var id = str;
    var empresa = $("#empresa").val();
    var rol=$("#rol").val();
    var nivel = str_lvl;
    var grupo = str_grp;
    var orden = str_ord;
    
    if(document.getElementById('check_active'+id).checked){
      var accion="registrar";  
         var parametros = {
                "id" : id,
                "empresa" : empresa,
                "rol" : rol,
                "nivel" : nivel,
                "grupo": grupo,
                "orden": orden,
                "accion" : accion
                };
                $.ajax({
                        data:  parametros, 
                        url:   'modulos',
                        type:  'GET',
                        beforeSend: function () {                      
                        } ,
                       success:  function (response) {
                              if(response === "true"){
                                  //alert("Modulo ya se encuentra registrado");
                                  MsgSalidaModal('Alerta!..','Modulo ya se encuentra registrado','Cerrar');
                                  //
                                }else{
                                  //  alert("Modulo registrado exitosamente");
                                         MsgSalidaModal('Mensaje!..','Modulo registrado exitosamente','Cerrar');
                                   
                                  modulos_asignados(empresa,rol); 
                                }                        
                        }
                });  


    }else{
        //Desactivar(id);

    var accion2="desactivar";
    
    if(confirm("Desea desactivar el Modulo")){
     var parametros = {
                "id" : id,
                "empresa" : empresa,
                "rol" : rol,
                "accion" : accion2
                };
                $.ajax({
                        data:  parametros, 
                        url:   'modulos',
                        type:  'GET',
                        beforeSend: function () {                      
                        } ,
                       success:  function (response) {
                              if(response === "true"){
                                  //alert("Modulo eliminado");
                                  //
                                  console.log("pòr aqui paso");
                                }else{
                                    //alert("");
                                    MsgSalidaModal('Mensaje!..','Modulo eliminado exitosamente','Cerrar');
                                  modulos_asignados(empresa,rol); 
                                   
                                }                        
                        }
                });  

    //jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      //  jQuery("#page-wrapper").load("modulos?accion=desactivar&id=" + data,{},function(){ });
        }
    }
    
    
    //alert(grupo);
    }


function Desactivar(data)
{
    var id = data;
    var empresa = $("#empresa").val();
    var rol=$("#rol").val();
    var accion="desactivar";
    
    if(confirm("Desea desactivar el Modulo")){
     var parametros = {
                "id" : id,
                "empresa" : empresa,
                "rol" : rol,
                "accion" : accion
                };
                $.ajax({
                        data:  parametros, 
                        url:   'modulos',
                        type:  'GET',
                        beforeSend: function () {                      
                        } ,
                       success:  function (response) {
                              if(response === "true"){
                                  //alert("Modulo eliminado");
                                  //
                                   MsgSalidaModal('Alerta!..','Modulo eliminado','Cerrar');
                                }else{
                                   // alert("Modulo eliminado exitosamente");
                                   MsgSalidaModal('Alerta!..','Modulo eliminado exitosamente','Cerrar');
                                  modulos_asignados(empresa,rol); 
                                   
                                }                        
                        }
                });  

    //jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      //  jQuery("#page-wrapper").load("modulos?accion=desactivar&id=" + data,{},function(){ });
        }
        
}



function modulos_asignados(empresa,rol)
{   
    jQuery("#pagetable").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    jQuery("#pagetable").load("modulos?accion=listar_modulos&empresa="+empresa+"&rol="+rol,{},function(){ });
}

$('#btncrearmodulo').click(function(e){
   e.preventDefault();
   var empresa = $("#empresa").val();
   var rol = $("#rol").val();

  
   if(empresa !== ""){
       if(rol !== ""){
           
           //////////////////////////////////////
           var accion="existe_modulos";
            var parametros = {
                "empresa" : empresa,
                "accion" : accion
                };
                $.ajax({
                        data:  parametros, 
                        url:   'modulos',
                        type:  'GET',
                        beforeSend: function () {                      
                        } ,
                       success:  function (response) {
                     
                              if(response){
                                  //alert("La empresa no cuenta con modulos para Asiganr al ROL...\nComuniquese con el departamento de sistema... \n\n<<< CARGAR MODULOS A LA EMPRESA >>>");
                                MsgSalidaModal('Mensaje!...','La empresa no cuenta con modulos para Asiganr al ROL...\nComuniquese con el departamento de sistema... \n\n<<< CARGAR MODULOS A LA EMPRESA >>>','Cerrar');    
                               return true;
                                } else{
                                        $('#list_modulo').css("display", "block");
                                        $('#asig_modulo').css("display", "block");
                                        document.getElementById("empresa").disabled = true;
                                         document.getElementById("rol").disabled = true;
                                         document.getElementById("btncrearmodulo").disabled = true;
                                         modulos_asignados(empresa,rol);  
                                }                       
                        }
                });  
           
           //////////////////////////////////
           
    
       }else{ 
           //alert("Debe seleccionar un rol");
                MsgSalidaModal('Alerta!..','Debe seleccionar un ROL','Cerrar');
        }
    } else{
         MsgSalidaModal('Alerta!..','Debe seleccionar una Empresa','Cerrar');
        //alert("Debe seleccionar una empresa");
    }
       
});

