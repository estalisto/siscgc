function frm_roles()
{

        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("agregar_rol",{},function(){    
         
        });
}
function roles()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("lista_roles",{},function(){ });
}


$('#btncrearrol').click(function(e){
e.preventDefault();
    var empresa=$('#empresa').val(); 
    var rol=$('#rol').val().toUpperCase(); 
  
    
    if (empresa === ""){
        //alert("");
           MsgSalidaModal('Alerta!..','Debe seleccionar una empresa','Cerrar');
        
        return true;
    }
      if (rol === ""){
       // alert("Debe ingresar un ROL");
          MsgSalidaModal('Alerta!..','Debe ingresar un ROL','Cerrar');
        return true;
    }
    
    
    
    
   /* if (empresa === ""){*/
     
         var parametros = {
                "empresa" : empresa,
                "rol" : rol
        };
        $.ajax({
                data:  parametros,
                url:   'crear_rol',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                          // alert(response);
                            MsgSalidaModal('Mensaje!..',response,'Cerrar');
                           roles();
                      }                        
                }
        });
    /*}
    if(empresa2 === ""){
        
        empresa2 = 0;
         var parametros = {
                "empresa" : empresa,
                "empresa2":empresa2,
                "rol" : rol
        };
        $.ajax({
                data:  parametros,
                url:   'crear_rol',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           alert(response);
                           frm_roles();//vuelvo a llamar a la pantalla
                      }                        
                }
        });
    }
*/
   
});



function enviarDatosRoles()
{
    var empresa=$('#empresa').val(); // document.rol..value;
    var rol=$('#rol').val().toUpperCase(); //document.rol.rol.value;
    
//    if (empresa == "" || empresa == null){
//        alert("Debe seleccionar una Empresa");
//        return false;  
//    }
    if($('#rol').val() == "" || $('#rol').val() == null){
        //alert("Debe Ingresar el ROL");
        MsgSalidaModal('Mensaje!..','Debe Ingresar el ROL','Cerrar');
        return false;  
    }
    
    var parametros = {
                "empresa" : empresa,
                "rol" : rol
        };
        $.ajax({
                data:  parametros,
                url:   'crear_rol',
                type:  'GET',
                beforeSend: function () {
                       // $("#info").html("<br/><br/><center><img alt='cargando' src='images/ajax-loader.gif' /><center>");
                } ,
               success:  function (response) {
                      
                        if(response){
                          // alert("Registrado Exitosamente..");
                           MsgSalidaModal('Mensaje!..','Registrado Exitosamente..','Cerrar');
                           frm_roles();//vuelvo a llamar a la pantalla
                         }                        
                }
        });
}

    function deleterol(data)        
{      if(confirm("Realmente desea eliminar los datos")){
      // jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
       //jQuery("#page-wrapper").load("roles?accion=eliminar&id=" + data,{},function(){ });
       var parametros = {
                "accion" : "eliminar",
                "id" : data
        };
       $.ajax({
                data:  parametros,
                url:   'roles',
                type:  'GET',
                beforeSend: function () {
                       // $("#info").html("<br/><br/><center><img alt='cargando' src='images/ajax-loader.gif' /><center>");
                } ,
               success:  function (response) {
                      
                        if(response){
                          // alert("Registrado Exitosamente..");
                           MsgSalidaModal('Mensaje!..',response,'Cerrar');
                          // frm_roles();//vuelvo a llamar a la pantalla
                          jQuery("#page-wrapper").load("lista_roles",{},function(){ }); 
                         }                        
                }
        });
        /*$.getJSON("roles", {"accion" : "eliminar","id":data}, function(result){
        
         //roles();
         console.log("rol eliminado");
         alert(result);
        });
         jQuery("#page-wrapper").load("lista_roles",{},function(){ }); */
        
        }
        
}

function ConnsultaDatosID(str)        
{  

  if(confirm("Realmente desea actualizar los datos")){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      jQuery("#page-wrapper").load("roles?accion=buscaID&id=" + str,{},function(){ });
     
    }
    
}

$('#btnactrol').click(function(e){
   e.preventDefault();
   
   var empresa = $("#empresa").val();
   var rol = $("#rol").val();

   
    var accion = $("#accion").val();
    var idrol= $("#idrol").val();
   

             var parametros = {
                "idrol":idrol,
                "accion" : accion,
                "empresa" : empresa,
                "rol" : rol
                  };
        $.ajax({
                data:  parametros,
                url:   'roles',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           //alert(response);
                            MsgSalidaModal('Mensaje!..',response,'Cerrar');
                           
                           roles();//vuelvo a llamar a la pantalla
                      }                        
                }
        });  
});