function frm_usuario()
{
   var empresa = $('#empresa2').val();
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=agregar",{},function(){ 
         if (empresa !== "") {
            $('#empresa').css("display", "none");
            $('#empresa2').css("display", "block");
            document.getElementById("empresa2").disabled = true;
        }
        });
}
function usuarios()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=listar",{},function(){ });
}
function cambio_clave()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=cambio_clave",{},function(){ });
}

function deleteusuario(data)
        
{      if(confirm("Realmente desea eliminar los datos")){
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=eliminar&id=" + data,{},function(){ });
    usuarios();    
    }
        
}

function validaDatos(sel_cedula,empresa,empresa2,rol,nusuario,ncontrasenia,observaciones,accion){
        if(sel_cedula.length > 1 ){
            if(empresa !=="" || empresa2!==""){
              if(rol !=="" ){  
                if(nusuario.length > 1 ){
                    if(ncontrasenia.length > 1 ){
                        if(observaciones.length > 0 ){
                            return true;
                        }else{ MsgSalidaModalA("Debe ingresar una observacion");}    
                    }else{ MsgSalidaModalA("Debe ingresar contraseña");}    
                }else{ MsgSalidaModalA("Debe ingresar Usuario");}
               }else{ MsgSalidaModalA("Debe elegir un rol");} 
           }else{ MsgSalidaModalA("Debe elegir una empresa");}
        }else{ MsgSalidaModalA("Debe ingresar Cedula de empleado");}        
   return false; 
}

function ConnsultaDatosID(str)        
{  

  if(confirm("Realmente desea actualizar los datos")){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
      jQuery("#page-wrapper").load("usuarios?accion=buscaID&id=" + str,{},function(){ });
  }
    
}

$('#btncrearusuario').click(function(e){
   e.preventDefault();
   var sel_cedula = $("#sel_cedula").val();
   var empresa2=$('#empresa2').val();
   var empresa = $("#empresa").val();
   var rol = $("#rol").val();
   var nusuario = $("#nusuario").val();
   var ncontrasenia  = $("#ncontrasenia").val();
   var observaciones = $("#observaciones").val();
   var accion = $("#accion").val();
   if (empresa === ""){
        empresa = 0;
    if(validaDatos(sel_cedula,empresa,rol,nusuario,ncontrasenia,observaciones,accion)){
      
          var parametros = {
                "accion" : accion,
                "sel_cedula" : sel_cedula,
                "empresa2":empresa2,
                "empresa" : empresa,
                "rol" : rol,
                "nusuario" : nusuario,
                "ncontrasenia" : ncontrasenia,
                "observaciones" : observaciones };
        $.ajax({
                data:  parametros,
                url:   'usuarios',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                          frm_usuario();//vuelvo a llamar a la pantalla
                          
                      }   else{
                       $('#user_found').css("display", "block");   
                       MsgSalidaModalA("Ya existe ese Usuario");
                      }                     
                }
        });
    }
   }if(empresa2 === ""){
        empresa2 = 0;
    if(validaDatos(sel_cedula,empresa,rol,nusuario,ncontrasenia,observaciones,accion)){
      
          var parametros = {
                "accion" : accion,
                "sel_cedula" : sel_cedula,
                "empresa2":empresa2,
                "empresa" : empresa,
                "rol" : rol,
                "nusuario" : nusuario,
                "ncontrasenia" : ncontrasenia,
                "observaciones" : observaciones };
        $.ajax({
                data:  parametros,
                url:   'usuarios',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                          frm_usuario();//vuelvo a llamar a la pantalla
                      }   else{
                          MsgSalidaModalA("Ya existe ese Usuario");
                      }                     
                }
        });
    }
}
});

$('#btnactusuario').click(function(e){
   e.preventDefault();
   
   var sel_cedula = $("#sel_cedula").val();
   
   var empresa = $("#empresa").val();
   var rol = $("#rol").val();
   var nusuario = $("#nusuario").val();
   var ncontrasenia  = $("#ncontrasenia").val();
   var observaciones = $("#observaciones").val();
   var accion = $("#accion").val();
   var idusuario= $("#idusuario").val();
   
             var parametros = {
                "idusuario":idusuario,
                 "accion" : accion,
                "sel_cedula" : sel_cedula,
                
                "empresa" : empresa,
                "rol" : rol,
                "nusuario" : nusuario,
                "ncontrasenia" : ncontrasenia,
                "observaciones" : observaciones
                 };
        $.ajax({
                data:  parametros,
                url:   'usuarios',
                type:  'GET',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                           MsgSalidaModalM(response);
                           usuarios();//vuelvo a llamar a la pantalla
                      }                        
                }
        });  
});
function ValidaSoloNumeros() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}
//Validadacion que los campos sean texto
function txNombres() {
    if ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
        event.returnValue = false;
}



function validacambio(nombre,newclave,confirmaclave){
    if(nombre !== "" ){
        if(newclave.length > 1){
            if(confirmaclave.length > 1){
                return true;
            }else{MsgSalidaModalA("Debe ingresar una contraseña");}
        }else{MsgSalidaModalA("Debe ingresar una contraseña");}
    }else{MsgSalidaModalA("Debe escoger un usuario");}
    return false;
}

$('#btncambio_clave').click(function(e){
   e.preventDefault();
   
   var nombre = $("#nombre").val();
   var newclave = $("#newclave").val();
   var confirmaclave = $("#confirmaclave").val();
   var accion = "cambio";
   
  if(validacambio(nombre,newclave,confirmaclave)) {
                if(newclave === confirmaclave){
                        var parametros = {
                         "nombre":nombre,
                         "newclave" : newclave,
                         "accion":accion
                          };
                         $.ajax({
                         data:  parametros,
                         url:   'usuarios',
                         type:  'GET',
                         beforeSend: function () {                      
                         } ,
                        success:  function (response) {
                               if(response){
                                    MsgSalidaModalM(response);
                                    cambio_clave();//vuelvo a llamar a la pantalla
                               }                        
                         }
                        });  
                }else{MsgSalidaModalA("Error Contraseñas no coinciden");}
     }     
});



function valida_identificacion(){
  
     //var cedula = '0931811087';
     var cedula = $("#sel_cedula").val();
     //Preguntamos si la cedula consta de 10 digitos
//     alert(cedula);
     if(cedula.length === 10){
        var num = cedula.substring(9,0);  
//        alert(num);
        var digito = cedula.substring(10,9);
        //alert(digito);
        var char_inverso = "";
        var cont = 0;
        var dig_ver = 0;
        var modulo = 0;



        for (var i = 0; i < num.length; i++) {
            char_inverso = num.charAt(i);

            var valida = (i + 1) % 2;

            if (valida === 0) {
                modulo = char_inverso * 1;
                if (modulo > 9) {
                    modulo = modulo - 9;
                }
            } else {
                modulo = char_inverso * 2;
                if (modulo > 9) {
                    modulo = modulo - 9;
                }
            }

            cont = cont + modulo;
        }

        cont = cont % 10;

        if (cont > 0) {
            dig_ver = 10 - cont;
        } else {
            dig_ver = cont;
        }

        if ($.trim(digito) === $.trim(dig_ver)) {
            $("#rol").val("");
            $("#nusuario").val("");
            $("#ncontrasenia").val("");
            $("#observaciones").val("");
            //alert("cédula válida");
            //document.getElementById("btncrearusuario").disabled = false;
        } else {
           
            MsgSalidaModalM("cédula no válida");
        
        }
  
    }

     if(cedula.length !== 10 && cedula.length !== 13){
         MsgSalidaModalA("Numero de identificación incorrecto");
         document.getElementById("btncrearusuario").disabled = true; 
     }
}



$('#btn_logeo').click(function(e){
   e.preventDefault();
    var empresa = $('#empresa').val();
    var usuario = $('#usuario').val();
    var password = $('#password').val();
    var accion = "autorizar";
 
    var parametros = {
                "accion" : accion,
                "empresa" : empresa,
                "usuario" : usuario,
                "password" : password
                };
        $.ajax({
                data:  parametros,
                url:   'autoriza',
                type:  'POST',
                beforeSend: function () {                      
                } ,
               success:  function (response) {
                      if(response){
                          // MsgSalidaModal('Alerta!..',response+'...','Cerrar');
                         // $('#DivMensajeError').style.display="block";
                        // document.getElementById("DivMensajeError").style="block";
                        console.log(response.toString());
                           document.getElementById("idMensajeError").innerHTML =response.toString();
                           
                           if(/Usuario no Autorizado./.test(response.toString())){                 
                                document.getElementById("idMensajeError").style.color = "red"; 
                           }else{
                                 document.getElementById("idMensajeError").style.color = "green"; 
                           }
                              
                          
                          
                           
                          
                          } else{
                              location.reload(true);
                          }                      
                }
        });
    
});


function logeando()
{
        //jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").reload("autoriza?accion=recarga",{},function(){ });
}


function MsgSalidaModal(title,msg,btname){
var title=title;
var msg=msg;
var btname=btname;

var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'>"+title+"</h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>"+msg+"</p> "+
        "</div> "+
        "<div class='modal-foote'> "+
          "<button type='button' class='btn btn-danger btn-default pull-left' data-dismiss='modal'><span class='glyphicon glyphicon-remove'></span>"+btname+"</button> "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}



function consulta_usuarios(){
    
      document.getElementById("tbusuarios").innerHTML = "";
    var htmlTable="<table id='example1' class='table table-bordered table-hover'><thead><tr  bgcolor='#A0F070'><th class=''>ID</th><th>Empresa</th><th>Rol</th><th>Identificacion Empleados</th> <th>Usuario </th><th>Fecha Ingreso </th><th>Estado </th><th>Acción </th></tr></thead><tbody></tbody></table>";
    document.getElementById("tbusuarios").innerHTML = htmlTable;
    
    $('#example1').DataTable( {
        "ajax": {
            "data": {"accion": "ConsultaALLUsuarios"},
            "url": "usuarios",
            "type": "GET"
            },
            "columns": [
                { "data": "idUsuario","title":"ID" },
                { "data": "empresa" },
                { "data": "rol" },
                { "data": "identificacion" },
                { "data": "usuario" },                
                { "data": "fecha_ingreso" },
                { "data": "estado" },
                { "data": "accion" }
            ],
            paging: true,
            order: [[ 7, 'asc' ]]
    } );
   

}