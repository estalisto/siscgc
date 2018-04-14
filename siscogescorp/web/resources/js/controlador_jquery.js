/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global parseFloat */

function AJAX()
{
    var xRequest=null;
    if(window.XMLHttpRequest){
        xRequest=new XMLHttpRequest();
    }
    else if(typeof ActiveXObject != "undefined"){
        xRequest=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xRequest;  
} 


$(document).on('ready', function (){
   
//    $('.btn-login').click( function (){
//        var name = $('#usuario').val();
//        var password = $('#password').val();
//        
//        if(name.length > 1){
//            if(password.length > 1){
//                var ajax = AJAX();
//                ajax.open("POST","servlet?",true);
//                ajax.onreadystatechange = function(){
//                    if(ajax.readyState === 4){
//                        if(ajax.status === 200)
//                        {                
//                            var valor =ajax.responseText;
//                            var res = valor.split("|");
//                            
//                            if(res[0] === '1'){
//                                swal({
//                                    title: res[1],
//                                    type: 'info',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 2000
//                                });
//                                document.location.href = 'principal.jsp';
//                            }else{
//                                 swal({
//                                    title: 'Contrase�a erronea',
//                                    type: 'warning',
//                                    showConfirmButton: false,
//                                    text: 'Ingrese su contrase�a!',
//                                    timer: 2000
//                });
//                            }
//                            
//                            
//                        }
//                    }
//                };
//                ajax.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=autentificacion&usuario="+name+"&password="+password); 
//            }else{
//               swal({
//                    title: 'Debes ingresar una contraseña',
//                    type: 'info',
//                    showConfirmButton: false,
//                    text: '',
//                    timer: 2000
//                });
//            }
//        }
//        else{
//            swal({
//                title: 'El campo usuario es necesario para el ingreso',
//                type: 'info',
//                showConfirmButton: false,
//                text: '',
//                timer: 2000
//            });
//        }
//    });
//    $('#btn-change-pass').on('click', function (){
//        
//         var user = $('#id_user').val();
//         var pass = $('#id_pass').val();
//         var pass1 = $('#id_pass1').val();
//         var pass2 = $('#id_pass2').val();
//       
//       if(pass1 === pass2){
//            if(user.length > 1){
//            if(pass.length > 1){
//                var ajax = AJAX();
//                ajax.open("POST","servlet?",true);
//                ajax.onreadystatechange = function(){
//                    if(ajax.readyState === 4){
//                        if(ajax.status === 200)
//                        {                
//                            var valor =ajax.responseText;
//                            var res = valor.split("|");
//                            
//                            if(res[0] === '0'){
//                                
//                                 swal({
//                                    title: 'Cambio realizado con exito',
//                                    type: 'info',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 4000
//                                });
//                                
//                            }else{
//                                 swal({
//                                    title: 'El usuario o contraseña es incorrecto',
//                                    type: 'warning',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 4000
//                                }); 
//                                
//                            }
//                        }
//                    }
//                };
//                ajax.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=cambiar_pass&user="+user+"&pass="+pass+"&pass1="+pass1+"&pass2="+pass2);
//            }
//        }
//                
//            
//       }else{
//         
//       swal({
//                title: 'Las contraseñas no coinciden',
//                type: 'warning',
//                showConfirmButton: false,
//                text: '',
//                timer: 2000
//            });
//       }
//       /*
//        if(name.length > 1){
//            if(password.length > 1){
//                var ajax = AJAX();
//                ajax.open("POST","servlet?",true);
//                ajax.onreadystatechange = function(){
//                    if(ajax.readyState === 4){
//                        if(ajax.status === 200)
//                        {                
//                            var valor =ajax.responseText;
//                            var res = valor.split("|");
//                            
//                            if(res[0] === '1'){
//                                swal({
//                                    title: res[1],
//                                    type: 'info',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 2000
//                                });
//                                document.location.href = 'principal.jsp';
//                            }else{
//                                 swal({
//                                    title: 'Contrase�a erronea',
//                                    type: 'warning',
//                                    showConfirmButton: false,
//                                    text: 'Ingrese su contrase�a!',
//                                    timer: 2000
//                });
//                            }
//                            
//                            
//                        }
//                    }
//                };
//                ajax.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=autentificacion&usuario="+name+"&password="+password); 
//            }else{
//               swal({
//                    title: 'Debes ingresar una contraseña',
//                    type: 'info',
//                    showConfirmButton: false,
//                    text: '',
//                    timer: 2000
//                });
//            }
//        }
//        else{
//            swal({
//                title: 'El campo usuario es necesario para el ingreso',
//                type: 'info',
//                showConfirmButton: false,
//                text: '',
//                timer: 2000
//            });
//        }
//       */
//       
//    });
//    /***********************************************************************/
//    /********                   Articulos-Marca                 ************/
//    /***********************************************************************/
//    $('#body-div-principal').on('click', '#td-btn-marca button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#example1 tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//            
//            if(campo1 === pr[1]){
//                var ajax = AJAX();
//                ajax.open("POST","servlet?",true);
//                ajax.onreadystatechange = function(){
//                    if(ajax.readyState === 4){
//                        if(ajax.status === 200)
//                        {                
//                            var valor =ajax.responseText;
//                            document.getElementById("modal-update-div").innerHTML = valor;
//                            $('#update-marca-moadal').modal({
//                                backdrop: 'static',
//                                keyboard: true, 
//                                show: true
//                            }); 
//                            $('#update-marca-moadal').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_marca&id="+campo1+"&nombre="+campo2+"&estado="+campo3); 
//            }
//        });
//    });    
//    $('#body-div-principal').on('click', '#btn_update_marca', function (){
//       if($("#name_marca").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each( function() {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each( function() {
//                $(this).removeClass("hidden"); 
//                $(this).addClass('glyphicon-remove');
//            }); 
//        }
//        else{
//            var estado = $("#estado_marca").val();
//            var nombre = $("#name_marca").val();
//            var id = $("#id_marca").val();
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#update-marca-moadal').modal('hide');
//                        $('#marca-btn').fadeOut(1000, function () {
//                            $('#marca-btn').click();
//                            $('#marca-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_marca&id=" + id + "&nombre=" + nombre + "&estado=" + estado); 
//        }
//    });   
//    $('#body-div-principal').on('click', '#add-marca', function (){
//       $('#add-marca-moadal').modal({
//            backdrop: 'static',
//            keyboard: true, 
//            show: true
//        }); 
//        
//    });    
//    $('#body-div-principal').on('click','#marca-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('body-marcas').innerHTML = valor;
//                        $('#example1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                        $('#add-marca').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-marca button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_marcas");
//    });    
//    $('#body-div-principal').on('click', '#btn_agr_marca', function (){
//        if($("#nombre").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each( function() {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each( function() {
//                $(this).removeClass("hidden"); 
//                $(this).addClass('glyphicon-remove');
//            }); 
//        }
//        else{
//            var estado = $("#estado").val();
//            var nombre = $("#nombre").val();
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        if(parseInt(valor) === 1){
//                                $(".form-group").addClass("has-success has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('Dato correcto');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-ok');
//                                });
//
//                                 swal({
//                                    title: 'El registro '+ nombre+' fue ingresado!',
//                                    type: 'success',
//                                    showConfirmButton: false,
//                                    text: 'Exitosamente'+ nombre,
//                                    timer: 5000
//                                  });
//                                  /*se aplica calback para que el proceso cordine con las opcciones a ejecutarse*/
//                                  $('#add-marca-moadal').modal('hide');
//                                  $('#marca-btn').fadeOut(6000, function (){
//                                      $('#marca-btn').click();
//                                      $('#marca-btn').fadeIn();
//                                  });
//                                  
//                        }
//                        if(parseInt(valor) === 2){
//                                $(".form-group").addClass("has-error has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('El registro ya existe (intentelo nuevamente)');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-remove');
//                                });
//                        }
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=cargar_marca&nombre="+nombre+"&estado="+estado);
//        }        
//    });
//    /**********************************************************************/
//    /********                   Articulos-Categoria             ************/
//    /***********************************************************************/    
//    $('#body-div-principal').on('click','#categoria-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('tbl_body_cate').innerHTML = valor;
//                        $('#example1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                        $('#add-categoria').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-categoria button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_categoria");
//    });    
//    $('#body-div-principal').on('click', '#add-categoria', function (){
//       $('#add-categoria-moadal').modal({
//            backdrop: 'static',
//            keyboard: true, 
//            show: true
//        }); 
//        
//    });   
//    $('#body-div-principal').on('click', '#btn_agr_cat', function(){
//        if($("#nombre").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each( function() {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each( function() {
//                $(this).removeClass("hidden"); 
//                $(this).addClass('glyphicon-remove');
//            }); 
//        }
//        else{
//            var estado = $("#estado").val();
//            var nombre = $("#nombre").val();
//
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        if(parseInt(valor) === 1){
//                                $(".form-group").addClass("has-success has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('Dato correcto');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-ok');
//                                });
//
//                                 swal({
//                                    title: 'El registro '+ nombre+' fue ingresado!',
//                                    type: 'success',
//                                    showConfirmButton: false,
//                                    text: 'Exitosamente'+ nombre,
//                                    timer: 5000
//                                  });
//                                  /*se aplica calback para que el proceso cordine con las opcciones a ejecutarse*/
//                                  $('#add-categoria-moadal').modal('hide');
//                                  $('#categoria-btn').fadeOut(6000, function (){
//                                      $('#categoria-btn').click();
//                                      $('#categoria-btn').fadeIn();
//                                  });
//                                  
//                        }
//                        if(parseInt(valor) === 2){
//                                $(".form-group").addClass("has-error has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('El registro ya existe (intentelo nuevamente)');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-remove');
//                                });
//                        }
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=cargar_categoria&nombre="+nombre+"&estado="+estado);
//        }
//    });    
//    $('#body-div-principal').on('click', '#td-btn-categoria button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#example1 tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-update-div").innerHTML = valor;
//                            $('#update-marca-categoria').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#update-marca-categoria').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_categoria&id=" + campo1 + "&nombre=" + campo2 + "&estado=" + campo3);
//            }
//        });
//    });    
//    $('#body-div-principal').on('click', '#btn_update_categoria', function () {
//        if ($("#name_categoria").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var estado = $("#estado_categoria").val();
//            var nombre = $("#name_categoria").val();
//            var id = $("#id_categoria").val();
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#update-marca-categoria').modal('hide');
//                        $('#categoria-btn').fadeOut(1000, function () {
//                            $('#categoria-btn').click();
//                            $('#categoria-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_categoria&id=" + id + "&nombre=" + nombre + "&estado=" + estado);
//        }
//    });
//    /***********************************************************************/
//    /********                   Articulos-Unidad             ***************/
//    /***********************************************************************/     
//    $('#body-div-principal').on('click', '#unidad-btn', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById('tbl_body_unidad').innerHTML = valor;
//                    $('#example1').DataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "No se encontraron datos",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                    $('#add-unidad').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    $('#td-btn-unidad button').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_unidad");
//    });    
//    $('#body-div-principal').on('click', '#add-unidad', function () {
//        $('#add-unidad-moadal').modal({
//            backdrop: 'static',
//            keyboard: true,
//            show: true
//        });
//
//    });    
//    $('#body-div-principal').on('click', '#btn_agr_unidad', function () {
//        if ($("#nombre").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var estado = $("#estado").val();
//            var nombre = $("#nombre").val();
//
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        if (parseInt(valor) === 1) {
//                            $(".form-group").addClass("has-success has-feedback");
//                            $("#msg-alert").each(function () {
//                                $(this).removeClass("hidden");
//                                $(this).empty().append('Dato correcto');
//                            });
//                            $("#glyphicon-input").each(function () {
//                                $(this).removeClass("hidden");
//                                $(this).addClass('glyphicon-ok');
//                            });
//
//                            swal({
//                                title: 'El registro ' + nombre + ' fue ingresado!',
//                                type: 'success',
//                                showConfirmButton: false,
//                                text: 'Exitosamente' + nombre,
//                                timer: 5000
//                            });
//                            /*se aplica calback para que el proceso cordine con las opcciones a ejecutarse*/
//                            $('#add-unidad-moadal').modal('hide');
//                            $('#unidad-btn').fadeOut(6000, function () {
//                                $('#unidad-btn').click();
//                                $('#unidad-btn').fadeIn();
//                            });
//
//                        }
//                        if (parseInt(valor) === 2) {
//                            $(".form-group").addClass("has-error has-feedback");
//                            $("#msg-alert").each(function () {
//                                $(this).removeClass("hidden");
//                                $(this).empty().append('El registro ya existe (intentelo nuevamente)');
//                            });
//                            $("#glyphicon-input").each(function () {
//                                $(this).removeClass("hidden");
//                                $(this).addClass('glyphicon-remove');
//                            });
//                        }
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=cargar_unidad&nombre=" + nombre + "&estado=" + estado);
//        }
//    });    
//    $('#body-div-principal').on('click', '#td-btn-unidad button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#example1 tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-update-div").innerHTML = valor;
//                            $('#update-unidad-modal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#update-unidad-modal').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_unidad&id=" + campo1 + "&nombre=" + campo2 + "&estado=" + campo3);
//            }
//        });
//    });    
//    $('#body-div-principal').on('click', '#btn_update_unidad', function () {
//        if ($("#name_unidad").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var estado = $("#estado_unidad").val();
//            var nombre = $("#name_unidad").val();
//            var id = $("#id_unidad").val();
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#update-unidad-modal').modal('hide');
//                        $('#unidad-btn').fadeOut(1000, function () {
//                            $('#unidad-btn').click();
//                            $('#unidad-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_unidad&id=" + id + "&nombre=" + nombre + "&estado=" + estado);
//        }
//    });
//    /************************************************************************/
//    /********                   Articulos-Producto             ***************/
//    /*************************************************************************/  
//    $('#body-div-principal').on('click', '#articulo-btn', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById('tbl_body_articulos').innerHTML = valor;
//                    $('#tbl_cab_articulo').DataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "No se encontraron datos",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                    $('#add-articulo').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    $('#td-btn-marca button').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    
//                    $('#td_inventario button').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    
//                    $('#td_provedor button').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_articulo");
//    });
//    $('#body-div-principal').on('click', '#add-articulo', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    var resp = valor.split("|");
//                    
//                    
//                    document.getElementById('marca').innerHTML = resp[0]; 
//                    document.getElementById('cat').innerHTML = resp[1];
//                    document.getElementById('und').innerHTML = resp[2];
//                    
//                    $('#add-articulo-moadal').modal({
//                        backdrop: 'static',
//                        keyboard: true,
//                        show: true
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=modal_articulos_add");
//    });
//    $('#body-div-principal').on('keypress', '#upd_cos_pro', function (){
//   // $('#precio').on('keypress', function (e) {
//        // Backspace = 8, Enter = 13, ’0′ = 48, ’9′ = 57, ‘.’ = 46
//        var field = $(this);
//        key = e.keyCode ? e.keyCode : e.which;
// 
//        if (key == 8) return true;
//        if (key > 47 && key < 58) {
//          if (field.val() === "") return true;
//          var existePto = (/[.]/).test(field.val());
//          if (existePto === false){
//              regexp = /.[0-9]{10}$/;
//          }
//          else {
//            regexp = /.[0-9]{2}$/;
//          }
// 
//          return !(regexp.test(field.val()));
//        }
//        if (key == 46) {
//          if (field.val() === "") return false;
//          regexp = /^[0-9]+$/;
//          return regexp.test(field.val());
//        }
//        return false;
//    });
//    
//    $('#body-div-principal').on('click', '#btn_crear_articulo', function () {
//        if ($("#codigo").val().length > 1) {
//            if ($("#nombre").val().length > 1) {
//                if ($("#cat").val() !== null) {
//                    if ($("#und").val() !== null) {
//                        if ($("#und").val() !== null) {
//                            if ($("#marca").val() !== null) {
//                                if ($("#valor").val().length > 1) {
//                                    var codigo = $("#codigo").val();
//                                    var nombre = $("#nombre").val();
//                                    var detalle = $("#detalle").val();
//                                    var valor = $("#valor").val();
//                                    var cat = $("#cat").val();
//                                    var modelo = $("#codigo").val();;
//                                    var marca = $("#marca").val();
//                                    var estante = "1";
//                                    //var estante = $("#codigo").val();
//                                    var estado = $("#estado").val();
//                                    var und = $("#und").val(); 
//                                    
//                                    
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                                var valor = ajax.responseText;
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "El registro fue ingresado",
//                                                        type: "info",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                    $('#add-articulo-moadal').modal('hide');
//                                                    $('#articulo-btn').fadeOut(6000, function () {
//                                                        $('#articulo-btn').click();
//                                                        $('#articulo-btn').fadeIn();
//                                                    });
//
//                                                } else {
//                                                    if (parseInt(valor) === 2) {
//                                                       // alert("por aki");
//                                                        $("#alert-general").addClass("has-error has-feedback");
//                                                        $("#gnr_art_msg").each(function () {
//                                                            $(this).removeClass("hidden");
//                                                        });
//                                                    } else {
//                                                        swal("Error al registrar", "intentelo nuevamente", "error");
//                                                        setTimeout(location.reload(), 20000);
//                                                    }
//                                                }
//                                                
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=add_articulo&nombre='+nombre+'&codigo='+codigo+'&detalle='+detalle+'&valor='+valor+'&cat='+cat+'&modelo='+modelo+'&marca='+marca+'&estante='+estante+'&estado='+estado+'&und='+und);
//                                    
//                                    
//                                } else {
//                                    $("#valor_art").addClass("has-error has-feedback");
//                                    $("#valor_art_msg").each(function () {
//                                        $(this).removeClass("hidden");
//                                    });
//                                }
//                            } else {
//                                $("#marca_form").addClass("has-error has-feedback");
//                                $("#marca_msg").each(function () {
//                                    $(this).removeClass("hidden");
//                                });
//                            }
//                        } else {
//                            $("#unidad_form").addClass("has-error has-feedback");
//                            $("#unidad_msg").each(function () {
//                                $(this).removeClass("hidden");
//                            });
//                        }
//                    } else {
//                        $("#unidad_form").addClass("has-error has-feedback");
//                        $("#unidad_msg").each(function () {
//                            $(this).removeClass("hidden");
//                        });
//                    }
//                } else {
//                    $("#categoria_form").addClass("has-error has-feedback");
//                    $("#msg_cate").each(function () {
//                        $(this).removeClass("hidden");
//                    });
//                }
//            } else {
//                $("#nombre_art").addClass("has-error has-feedback");
//                $("#nombre_art_msg").each(function () {
//                    $(this).removeClass("hidden");
//                });
//            }
//        } else {
//            $("#cod_art").addClass("has-error has-feedback");
//            $("#cod_art_msg").each(function () {
//                $(this).removeClass("hidden");
//            });
//        }
//            
//    });
//    $('#body-div-principal').on('click', '#td-btn-articulo button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");        
//        $('#tbl_cab_articulo tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break; 
//                    case 2:
//                        campo3 = $(this).text();
//                        break; 
//                    }
//            });
//            if (campo1 === pr[1]) {
//               
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            document.getElementById('modal-update-articulos').innerHTML = valor; 
////                            var resp = valor.split("|");
////
////                    document.getElementById('upd_mar_pro').innerHTML = resp[0]; 
////                    document.getElementById('upd_cat_pro').innerHTML = resp[1];
////                    document.getElementById('upd_uni_pro').innerHTML = resp[2];
//                    
//                      $('#upd-art-modal').modal({
//                        backdrop: 'static',
//                        keyboard: true,
//                        show: true
//                    });
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_articulo&id_pro="+campo1+"&codigo_pro="+campo3);
//            }
//        });
//    });
//    $('#body-div-principal').on('click', '#btn_update_producto', function () {
//         if ($("#upd_cod_pro").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            
//            var id_pro      = $("#upd_id_pro").val();
//            var cod_pro     = $("#upd_cod_pro").val();
//            var nombre_pro  = $("#upd_nom_pro").val();
//            var id_cate     = $("#upd_cat_pro").val();
//            var ide_unidad  = $("#upd_uni_pro").val();
//            var id_marca    = $("#upd_mar_pro").val();
//            var id_costo    = $("#upd_cos_pro").val();
//            var detalle     = $("#upd_det_pro").val();
//            
//            //alert(id_pro+" "+cod_pro+nombre_pro+" "+id_cate+" "+ide_unidad+" "+id_marca+" "+id_costo+" "+detalle);
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#upd-art-modal').modal('hide');
//                        $('#articulo-btn').fadeOut(1000, function () {
//                            $('#articulo-btn').click();
//                            $('#articulo-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=upda_productos&id_pro=" + id_pro + "&cod_pro=" + cod_pro + "&nombre_pro="+ nombre_pro + "&id_cate="+ id_cate + "&ide_unidad="+ ide_unidad + "&id_marca="+ id_marca + "&id_costo="+ id_costo + "&detalle="+ detalle);
//             
//        }
//    });  
//    $('#body-div-principal').on('click', '#td_inventario button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//       //alert(pr[1]);
//        $('#tbl_cab_articulo tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                         case 3:
//                    campo4 = $(this).text();
//                        break;
//                }
//            });
//            if (campo1 === pr[1]) {               
//              //alert(campo4);
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-inv").innerHTML = valor;
//                            $('#add-inventario-modal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_asigna_inventario&id=" + pr[1]+"&id_categoria="+campo2+"&codigo_articulo="+campo3+"&nombre_x="+campo4);
//            }
//        });
//    }); 
//    $('#body-div-principal').on('click', '#td_provedor button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#tbl_cab_articulo tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-prov").innerHTML = valor;
//                            $('#add-proveedor-modal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#add-proveedor-modal').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_provedor_articulo&idarticulo="+campo1);
//            }
//        });
//    });    
//    $('#body-div-principal').on('click', '#btn_asignar_articulo', function () {
//        var id_art = $("#id_art").val();
//        var codigox = $("#codigox").val();
//        var nombrex = $("#nombrex").val();
//        var cat = $("#categ").val();
//        var almacen = $("#alm").val();
//        var stock = $("#stocke").val();
//        var stock_min = $("#stock_mine").val();
//        var pv = $("#pve").val();
//        var pmy = $("#pm_y").val();
//        /***********************validar campos*************************************/
//        if (almacen !== null) {
//            //alert(nombrex);
//            if ($("#stocke").val().length > 0) {
//                if ($("#stock_mine").val().length > 0) {
//                    if ($("#pve").val().length > 0) {
//                        /***************************despues de validar procesamos los datos***********************************/
//                        var ajax = AJAX();
//                        ajax.open("POST", "servlet?", true);
//                        ajax.onreadystatechange = function () {
//                            if (ajax.readyState === 4) {
//                                if (ajax.status === 200)
//                                {
//                                    var valor = ajax.responseText;
//                                    if (parseInt(valor) === 2) {
//                                        swal({
//                                            title: 'El registro ' + codigox + ' se agrego al inventario!',
//                                            type: 'success',
//                                            showConfirmButton: false,
//                                            text: 'Exitosamente',
//                                            timer: 2000
//                                        });
//                                        $('#add-inventario-modal').modal('hide');
//                                        $('#articulo-btn').fadeOut(1000, function () {
//                                            $('#articulo-btn').click();
//                                            $('#articulo-btn').fadeIn();
//                                        });
//
//
//                                    }
//                                }
//                            }
//                        };
//                        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                        ajax.send("opcion=agregar_inventario&id_art=" + id_art + '&codigox=' + codigox + '&nombrex=' + nombrex + '&cat=' + cat + '&almacen=' + almacen + '&stock=' + stock + '&stock_min=' + stock_min + '&pv=' + pv + '&pmy=' + pmy);
//                        /*****************************/
//                    } else {
//                        $("#pve_error").addClass("has-error has-feedback");
//                        $('#pve_msg').removeClass("hidden");
//                    }
//                } else {
//                    $("#stock_mine_error").addClass("has-error has-feedback");
//                    $('#stock_mine_msg').removeClass("hidden");
//                }
//            } else {
//                $("#stocke_art").addClass("has-error has-feedback");
//                $('#stocke_art_msg').removeClass("hidden");
//            }
//        } else {
//            $("#almacenado").addClass("has-error has-feedback");
//            $('#almacenado_msg').removeClass("hidden");
//        }
//    });  
//    $('#body-div-principal').on('click', '#btn_asigna_provee button', function (){
//        var id = $(this).attr('id');
//        var pr = id.split("-");  
//        var provee=$('#select_provee').val();
//            var ajax = AJAX();
//         ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                        $('#add-proveedor-modal').modal('hide');
//                        $('#articulo-btn').fadeOut(1000, function () {
//                            $('#articulo-btn').click();
//                            $('#articulo-btn').fadeIn();
//                        });
//                    }
//                }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=asign_pro_provee&idarticulo="+pr[1]+"&prove="+provee);
//                  
//    });
//    /************************************************************************/
//    /********                   Articulos-Inventario           ***************/
//    /*************************************************************************/
//    $('#body-div-principal').on('click','#inventarios-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;   
//                         var pr = valor.split("|");
//                       
//                          document.getElementById('tbl_body_inv1').innerHTML = pr[1];
//                           $('#tbl_inv_1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//
//                        document.getElementById('tbl_body_inv2').innerHTML = pr[0];;
//                        $('#tbl_inv_2').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                     
//                        $('#add-inventarios').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-inventarios button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_inventarios");
//    });    
//    $('#body-div-principal').on('click', '#td-btn-inventarios button', function () {
//       
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        
//        $('#tbl_inv_2 tbody tr').each(function (index) {
//
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                     case 3:
//                        campo4 = $(this).text();
//                        break;
//                    case 4:
//                        campo5 = $(this).text();
//                        break;
//                    case 5:
//                        campo6 = $(this).text();
//                        break;
//                     case 6:
//                        campo7 = $(this).text();
//                        break;
//                    case 7:
//                        campo8 = $(this).text();
//                        break;
//                    case 8:
//                        campo9 = $(this).text();
//                        break;
//                    case 9:
//                        campo10 = $(this).text();
//                        break;    
//                }
//            });
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-update-inventarios").innerHTML = valor;
//                           $('#modal-upd-inventarios').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#modal-upd-inventarios').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_inventarios&id_inventario=" + campo1 + "&codigo_articulo=" + campo2 + "&id_articulo="+ campo3 + "&pv="+ campo4 + "&stock="+ campo5 + "&id_almacen_i="+ campo6 + "&stock_min="+ campo7 + "&pmy="+ campo8 + "&cat="+ campo9);
//       
//            }
//        });
//    });
//    $('#body-div-principal').on('click', '#btn_update_inventarios', function () {
//       
//         if ($("#codigo_articulo").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var id_inventario   = $("#id_inventario").val();
//            var codigo_ariculo  = $("#codigo_articulo").val();
//            var id_cat          = $("#id_cat").val();
//            var ide_almacen     = $("#ide_almacen").val();
//            var id_stock        = $("#id_stock").val();
//            var id_stock_min    = $("#id_stock_min").val();
//            var id_pv           = $("#id_pv").val();
//            var id_pmy          = $("#id_pmy").val();
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#modal-upd-inventarios').modal('hide');
//                        $('#inventarios-btn').fadeOut(1000, function () {
//                            $('#inventarios-btn').click();
//                            $('#inventarios-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//             ajax.send("opcion=update_inventarios&id_inventario=" + id_inventario + "&codigo_articulo=" + codigo_ariculo + "&pv="+ id_pv + "&stock="+ id_stock + "&id_almacen_i="+ ide_almacen + "&stock_min="+ id_stock_min + "&pmy="+ id_pmy + "&cat="+ id_cat);
//             
//        }
//    });  
//
// 
//    /***********************************************************************/
//    /********                   Ventas                          ************/
//    /***********************************************************************/
//    $('#body-div-principal').on('click', '#btn-ventas', function () {
//           
//        var valueTipo = 1;
//
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    var resp = valor.split("|");
//                    document.getElementById("buscar_clientes").innerHTML = resp[0];
//                    document.getElementById("buscar_articulos_venta").innerHTML = resp[1];
//                    
//                    $(".select2").select2();
//  
//                    if(resp[2].length > 3){
//                        document.getElementById("tb_ventas").innerHTML = resp[2];
//                    }else{
//                        $('#example2').DataTable({
//                            "paging":   false,
//                            "ordering": false,
//                            "infoEmpty": false,
//                            "searching": false,
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });   
//                    }
//                    document.getElementById("subtotal-now").innerHTML = '$ '+resp[3];
//                    document.getElementById("iva-final").innerHTML = '$ '+resp[4];
//                    document.getElementById("totalcompras").innerHTML = '$ '+resp[5];
//                    if(resp[6].length > 3){
//                        document.getElementById("cliente_detalle").innerHTML = resp[6];
//                        $("#facturar").removeAttr("disabled");
//                    }else{
//                        $('#example1').DataTable({
//                            "paging":   false,
//                            "ordering": false,
//                            "infoEmpty": false,
//                            "searching": false,
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                    }
//                    
//                    var tipoVenta = $("#tipov").text();
//                    if($.trim(tipoVenta) === 'CREDITO')
//                    {
//                        valueTipo = 2;
//                    }
//
//                    $('#tipov').editable({
//                        send: 'never',
//                        value: valueTipo,
//                        source: [
//                            {value: 1, text: 'CONTADO'},
//                            {value: 2, text: 'CREDITO'}
//                        ],
//                        success: function (response, newValue) {
//                            var _tipo = $("#title-proceso").text();
//                            var ajax = AJAX();
//                            ajax.open("POST", "servlet?", true);
//                            ajax.onreadystatechange = function () {
//                                if (ajax.readyState === 4) {
//                                    if (ajax.status === 200)
//                                    {
//                                        var valor = ajax.responseText;
//                                        
//                                        if($.trim(valor) === 'Compras'){
//                                            $('#btn-compras').click();
//                                        }else{
//                                            $('#btn-ventas').click();
//                                        }
//
//                                    }
//                                }
//                            };
//                            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                            ajax.send("opcion=tipo_venta&tipo=" + newValue+'&ttipo='+_tipo);
//                        }
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_de_clientes");
//     });     
//    $('#body-div-principal').on('click', '#td-change-precio a', function () {
//        var id = '#' + $(this).attr('id');
//        var codigo = id.split("_");
//        
//        var tipo = $('#title-proceso').text();
//
//        
//        $(id).editable({
//            send: 'never',
//            success: function (response, newValue) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            /*alert(valor);*/
//                            if($.trim(valor)==="6"){
//                                 swal({
//                                            title: 'La cantidad sobrepasa el stock del producto!',
//                                            type: 'warning',
//                                            showConfirmButton: false,
//                                            text: '',
//                                            timer: 2000
//                                        });
//                               // $('#td-change-precio a').click();
//                            }else{
//                            if($.trim(valor)  ===  'Compras'){
//                                $('#btn-compras').click();
//                            }else{
//                               $('#btn-ventas').click(); 
//                            }  
//                        }
//                    }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CambiarCantidad&cantidad=" + newValue + "&codigo=" + codigo[1]+'&tipo='+tipo);
//            }
//        });
//    });
//    
//    $('#body-div-principal').on('click', '#btn-obsequio', function () {
//           
//        var valueTipo = 1;
//
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
////                    var resp = valor.split("|");
////                    document.getElementById("buscar_articulos_venta").innerHTML = resp[1];
//                    var tot = document.getElementById("totalcompras").innerHTML;                   
//                   var vt= tot.substr(2,8);
//                    var lim=500;
//                    
//                    if(parseFloat(vt) >= parseFloat(lim)){
////                        alert("hola mundo");
//                        
//                        $('#example2 tbody tr').each(function (index) {
//            
//                            $(this).children("td").each(function (index2) {
//                                if(index2 > 1){
//                                switch (index2)
//                                {
//                                    case 0:
//                                        campo1 = $(this).text();
//                                        break;
//                                    case 1:
//                                        campo2 = $(this).text();
//                                        break;
//                                    case 2:
//                                        campo3 = $(this).text();
//                                        break;
//                                    case 3:
//                                        campo4 = $(this).text();
//                                        break;
//                                    case 4:
//                                        campo5 = $(this).text();
//                                        var acum=0;
//                                        var vt2= campo5.substr(2,8);
//                                        acum += parseFloat(vt2);
//                                        
//                                        break;
//                                    case 5:
//                                        campo6 = $(this).text();
//                                        break;
//                                    case 6:
//                                        campo7 = $(this).text();
//                                        break;
//                                    case 7:
//                                        campo8 = $(this).text();
//                                        break;
//                                }}
//                            });
//                            });
//                    }                    
//                    
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_de_clientes");
//     });     
//    
//    
//    
//    $('#body-div-principal').on('click', '#btn-consulta-venta', function () {
//        var fch_desde = $('#fecha').val();
//        var fch_hasta = $('#fecha2').val();
//        var tipo = $('#tip').val();
//        
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById("div-consulta").innerHTML = valor;
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=consulta_venta&fch_desde=" + fch_desde + "&fch_hasta=" + fch_hasta + "&tipo=" + tipo);
//        
//    });   
//    $('#body-div-principal').on('click', '#btn_eliminar_venta button', function () {
//       var id = $(this).attr('id');
//       var pr = id.split("-");     
//       var id_vta= pr[1];
//           
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#btn-ventas').fadeOut(1000, function () {
//                            $('#btn-ventas').click();
//                            $('#btn-ventas').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=eliminarvta&id_vta=" + id_vta );
//             
//        
//    }); 
//    $('#body-div-principal').on('change','#buscar_articulos_venta', function () {
//        var id_art = $('#buscar_articulos_venta').val();
//        var usuario = $('#usu').val();
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    valor = valor.split("|");
//                    ///alert(valor[0]);
//                    if (valor[0].length > 3) {
//                        
//                        document.getElementById("tb_ventas").innerHTML = valor[0];
//                    } else {
//                        swal({
//                            title: 'No hay stock para este producto',
//                            type: 'warning',
//                            showConfirmButton: false,
//                            text: 'Gracias',
//                            timer: 2000
//                        });
//                    }
//                    
//                    document.getElementById("subtotal-now").innerHTML = '$ '+valor[1];
//                    document.getElementById("iva-final").innerHTML = '$ '+valor[2];
//                    document.getElementById("totalcompras").innerHTML = '$ '+valor[3];
//                    
//                    
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarArticulo&id=" + id_art + "&usuario=" + usuario);
//
//    });
//    $('#body-div-principal').on('change','#buscar_clientes', function () {
//        var id_cliente = $('#buscar_clientes').val();
//        var valueTipo = 1;
//        var usuario = $('#usu').val();
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                   var valor = ajax.responseText;
//                    //alert(valor);
//                    var tipoVenta = $("#tipov").text();
//                    if($.trim(tipoVenta) === 'CREDITO')
//                    {
//                        valueTipo = 2;
//                    }
//                   document.getElementById("cliente_detalle").innerHTML = valor;
//                   $("#facturar").removeAttr("disabled");
//                   $('#tipov').editable({
//                        send: 'never',
//                        value: 1,
//                        source: [
//                            {value: 1, text: 'CONTADO'},
//                            {value: 2, text: 'CREDITO'}
//                        ],
//                        success: function (response, newValue) {
//                            var id = $('#id_cliente_tmp').val();
//                            var _tipo = $("#title-proceso").text();
//                            var ajax = AJAX();
//                            ajax.open("POST", "servlet?", true);
//                            ajax.onreadystatechange = function () {
//                                if (ajax.readyState === 4) {
//                                    if (ajax.status === 200)
//                                    {
//                                      var valor = ajax.responseText;
//                                        
//                                        if($.trim(valor) === 'Compras'){
//                                            $('#btn-compras').click();
//                                        }else{
//                                            $('#btn-ventas').click();
//                                        }
//
//                                    }
//                                }
//                            };
//                            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                            ajax.send("opcion=tipo_venta&tipo=" + newValue+'&ttipo='+_tipo);
//                        }
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarClienteVenta&id=" + id_cliente + "&usuario=" + usuario);
//         
//     });    
//    $('#body-div-principal').on('click', '#facturar', function () {
//          
//         var formapago =  $("#tipov").text();
//         var total = $("#tot").text();
//         var nombre = $("#nameCliente").text();
//
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById("modal-facturar").innerHTML = valor;
//                    $('#modal-detalle').modal('show');
//                    document.getElementById("total-pagar").innerHTML = total;
//                    document.getElementById("forPago").innerHTML = formapago;
//                    document.getElementById("name_cliente").innerHTML = nombre;
//
//
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarDetalleVentas&forma=" + formapago+"&sub="+ total);
//
//    });
//    $('#body-div-principal').on('click', '#btn-facturar', function () {
//        //alert()
//        if ($('#efectivo').val().length > 0) {
//            var iva = $("#iva-final").text();
//            var subtotal = $("#subtotal-now").text();
//            var total = $("#tot").text();
//            var descuento = $("#descuento").text();
//            var efectivo = $("#efectivo").val();
//            var identificacion = $("#indent_cliente").val();
//            var tipoVenta = $("#tipov").text();
//            var entrada =  $("#entrada").val();
//            var vp =  $("#vta_vp").val();
//            var diferido =  $("#vta_dif").val();
//            var interes =  $("#vta_interes").val();
//            var tot =   $("#vta_tp").val();
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#modal-detalle').modal('hide');
//
//
//                        $('#btn-ventas-hidden').fadeOut(4000, function () {
//                            $('#btn-ventas-hidden').attr('href', 'modulos/venta/factura.jsp?iva=' + iva + '&sub=' + subtotal + '&total=' + total + '&descuento=' + descuento + '&efectivo=' + efectivo + '&ident_cliente=' + identificacion + '&tipoVenta=' + tipoVenta + '&entrada=' + entrada + '&vp=' + vp + '&diferido=' + diferido + '&interes=' + interes + '&tot=' + tot);
//                            $('#btn-ventas-hidden').click();
//                            $('#btn-ventas-hidden').fadeIn();
//                        });
//                        $('#body-div-principal').removeClass('modal-open');
//                        $('.modal-backdrop').remove();
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=factura&iva=" + iva + "&subtotal=" + subtotal + "&total=" + total);
//        } else {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#valor_msg").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//
//        }
//
//
//    });
//    $('#body-div-principal').on('click', '#btn-nueva-venta', function () {
//        $('#btn-ventas').click();
//    });
//        
//     $('#body-div-principal').on('change', '#entrada', function (){
//       //  alert("entro");
//     var num = $("#total-pagar").text();
//     num= num.replace('$','');
//     // 
//      var den = $("#entrada").val();
//      var resultado= parseFloat(num)-parseFloat(den);
//     // alert(resultado);
//     
//      $("#vta_vp").val(parseFloat(resultado).toFixed(2));
//       $('#entrada').val(parseFloat(den).toFixed(2));
//    });  
//     $('#body-div-principal').on('change','#vta_dif', function (){
//       var por_meses = $("#vta_dif").val();
//       var valor_pagar = $("#vta_vp").val();
//       var interes= parseFloat(valor_pagar) / parseFloat(por_meses);
//       var tota=parseFloat(valor_pagar) + parseFloat(interes);
//       $('#vta_interes').val(parseFloat(interes).toFixed(2));
//       $('#vta_tp').val(parseFloat(tota).toFixed(2));
//              
//    }); 
//    
//    $('#body-div-principal').on('change','#vta_dif2', function (){
//       var por_meses = $("#vta_dif2").val();
//       var valor_pagar = $("#vta_vp").val();
//       var interes2= parseFloat(valor_pagar) * parseFloat(por_meses);
//       //articulo precio $1000
//       var totalo=parseFloat(valor_pagar) + parseFloat(interes2);
//       $('#vta_interes').val(parseFloat(interes2).toFixed(2));
//       $('#vta_tp').val(parseFloat(totalo).toFixed(2));
//       if(por_meses == 0.20)
//       {
//           var mes = 3;
//       var cuota = (parseFloat(valor_pagar)+parseFloat(interes2))/parseFloat(mes);
//       $('#vta_cuota').val(parseFloat(cuota).toFixed(2));
//       }
//       else{
//           var mes=6;
//        var cuota = (parseFloat(valor_pagar)+parseFloat(interes2))/parseFloat(mes);
//       $('#vta_cuota').val(parseFloat(cuota).toFixed(2));   
//       }
//    });
//    $('#body-div-principal').on('click', '#btn-imprimir-fac', function () {
//       var nombre = $('#name').text();
//            nombre = nombre.replace('Cliente:','');
//            
//            var ruc = $('#ruc').text();
//            ruc = ruc.replace('RUC/C.I:','');
//            
//            
//            
//            var dir = $('#dir').text();
//            dir = dir.replace('Dirección:','');
//            
//            var fecha = $('#fecha').text();
//            fecha = fecha.replace('Fecha:','');
//            
//            var tel = $('#tel').text();
//            tel = tel.replace('Telefono:','');
//            
//            var ciudad = $('#ciudad').text();
//            ciudad = ciudad.replace('Ciudad:','');
//            
//            var iva =  $('#iva').text();
//            var total = $('#totl').text();
//            var sub = $('#sub').text();
//            var desc = $('#des').text();
//            
//            url = 'modulos/venta/formatoImpresion.jsp?nombre='+nombre+"&ruc="+ruc+"&dir="+dir+"&fecha="+fecha+"&tel="+tel+"&ciudad="+ciudad+"&iva="+iva+"&sub="+sub+"&total="+total+"&desc="+desc;
//            window.open(url, '_blank');
//            
//            return false;
//    });
//    $('#body-div-principal').on('click', '#search', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=SerchArticulo");
//    });
//    $('#body-div-principal').on('click', '#cn_artci_venta', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    valor = valor.split("|");
//                    document.getElementById("nav-fill").innerHTML = valor[0];
//                    document.getElementById("conten-tab").innerHTML = valor[1];
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=consulta_categoria");
//    });
//    $('#body-div-principal').on('click', '#td-btn-detalle-fac .btn-info', function () {
//        var id = $(this).attr('id');
//        $('#tbl-detalle tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo1 === id) {
//               // alert("ingreso");
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                          //  alert("ingreso");
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=DetallesFac&id=" + campo1);
//            }
//        });
//
//    });
//    $('#body-div-principal').on('click', '#td-btn-detalle-fac .btn-danger', function () {
//        var id = $(this).attr('id');
//        $('#tbl-detalle tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo1 === id) {
////                alert(campo1);
////                alert(campo3);
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                         //   alert("ingreso");
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=AnularFactura&factura=" + campo1+'&identificion='+campo3);
//            }
//        });
//
//    });
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION DETALLES DE VENTAS                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/         
//      $('#body-div-principal').on('click','#btn-ventas-detalle', function () {
//       // alert("prueba");
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                   
//                    document.getElementById("buscar_num_factura").innerHTML = valor; 
//                    $(".select2").select2();
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarNumeroFactura");
//
//    });    
//      $('#body-div-principal').on('change','#buscar_num_factura', function () {
//   
//        var num_fact = $('#buscar_num_factura').val();
//      // alert(num_fact);
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                  var valor = ajax.responseText;
//                    document.getElementById("tbody_detalle_vta").innerHTML = valor;
//                    $("#tbl_detalle_vta").dataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "Seleccione de la lista",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                   
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarDetalleVtas&id_fact=" + num_fact);
//         
//     }); 
//      $('#body-div-principal').on('click', '#anular_Factura', function () {
//        var id_factura = $('#buscar_num_factura').val();
//        //alert(id_factura);
//        
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    if(parseInt(valor)===1){
//                         swal({
//                        title: "Factura Anulada",
//                        type: "info",
//                        showConfirmButton: false,
//                        text: '',
//                        timer: 2000
//                    });
//                $('#btn-ventas-detalle').click();                                                                  
//                    }else{
//                          swal({
//                                                        title: "Error al realizar la anulacion",
//                                                        type: "warning",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                        
//                    }
//                    //document.getElementById("div-consulta").innerHTML = valor;
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=AnularFactura_Venta&factura=" + id_factura);
//        
//    });   
//    
//    
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION COMPRAS                                            */
///****************************************************************************************************************/
///****************************************************************************************************************/   
//    
//    $('#body-div-principal').on('click', '#procesar_compras', function () {
//
//        if ($('#nun_fac').val().length > 14) {
//            var usuario = $('#usu').val();
//            var nun_factura = $('#nun_fac').val(); 
//            swal({
//                title: "Esta Seguro?",
//                html: "Que desea realizar esta operacion <strong>" + usuario + "</strong> una vez procesada no podra ser eliminada",
//                type: "info",
//                showCancelButton: true,
//                confirmButtonColor: "#00c0ef",
//                confirmButtonText: "Si, Procesarla!",
//                cancelButtonText: "No, Cancelar!",
//                closeOnConfirm: false,
//                closeOnCancel: false
//            }).then(function () {
//
//                var tot = $('#totalcompras').text();
//                var iva = $('#iva-final').text();
//                var desc = $('#descuento').text();
//                var tipoVenta = $("#tipov").text();
//
//                var url = 'modulos/compra/factura.jsp?valor_recibido=' + tot + '&iva=' + iva + '&descuento=' + desc+'&fac='+nun_factura+'&tipoVenta='+tipoVenta;
//                window.location.hash = url;
//                LoadAjaxContent(url);
//
//
//
//                LoadAjaxContent("ajax_url");
//            }, function (dismiss) {
//                if (dismiss === 'cancel') {
//                    swal({
//                        title: 'Cancelado!',
//                        type: 'info',
//                        showConfirmButton: false,
//                        text: 'No se procedio a realizar la compra ' + "name",
//                        timer: 2000
//                    });
//                }
//            });
//        } else {
//            if($('#nun_fac').val().length > 0){
//                swal({
//                        title: 'El numero de factura no esta completo!',
//                        type: 'info',
//                        showConfirmButton: false,
//                        text: '',
//                        timer: 2000
//                    });
//            }else{
//                swal({
//                        title: 'Debes ingresar el numero de la Factura',
//                        type: 'info',
//                        showConfirmButton: false,
//                        text: '',
//                        timer: 2000
//                    });
//            }
//
//        }
//    });
//    $('#body-div-principal').on('click', '#btn-compras', function () {
//        var valueTipo = 1;
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    var resp = valor.split("|");
//                    document.getElementById("buscar_provedores").innerHTML = resp[0];
//                    document.getElementById("buscar_articulos_compra").innerHTML = resp[1];
//
//                    $(".select2").select2();
//
//                    if (resp[2].length > 3) {
//                        document.getElementById("tb_ventas").innerHTML = resp[2];
//                    } else {
//                        $('#example2').DataTable({
//                            "paging": false,
//                            "ordering": false,
//                            "infoEmpty": false,
//                            "searching": false,                        
//                            "scrollCollapse": true,
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                    }
//                    document.getElementById("subtotal-now").innerHTML = '$ ' + resp[3];
//                    document.getElementById("iva-final").innerHTML = '$ ' + resp[4];
//                    document.getElementById("totalcompras").innerHTML = '$ ' + resp[5];
//                    if (resp[6].length > 3) {
//                        document.getElementById("proveedor_detalle").innerHTML = resp[6];
//                        $("#procesar_compras").removeAttr("disabled");
//                    } else {
//                        $('#example1').DataTable({
//                            "paging": false,
//                            "ordering": false,
//                            "infoEmpty": false,
//                            "searching": false,
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                    }
//
//                    var tipoVenta = $("#tipov").text();
//                    if ($.trim(tipoVenta) === 'CREDITO')
//                    {
//                        valueTipo = 2;
//                    }
//                    
//                    
//                    
//                    
//
//                    $('#tipov').editable({
//                        send: 'never',
//                        value: valueTipo,
//                        source: [
//                            {value: 1, text: 'CONTADO'},
//                            {value: 2, text: 'CREDITO'}
//                        ],
//                        success: function (response, newValue) {
//                            var _tipo = $("#title-proceso").text();
//                            var ajax = AJAX();
//                            ajax.open("POST", "servlet?", true);
//                            ajax.onreadystatechange = function () {
//                                if (ajax.readyState === 4) {
//                                    if (ajax.status === 200)
//                                    {
//                                        var valor = ajax.responseText;
//                                        
//                                        if($.trim(valor) === 'Compras'){
//                                            $('#btn-compras').click();
//                                        }else{
//                                            $('#btn-ventas').click();
//                                        }
//                                        
//
//                                    }
//                                }
//                            };
//                            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                            ajax.send("opcion=tipo_venta&tipo=" + newValue+'&ttipo='+_tipo);
//                        }
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_de_provedores");
//
//    });
//    $('#body-div-principal').on('click', '#btn_eliminar_compra button', function () {
//       var id = $(this).attr('id');
//       var pr = id.split("-");     
//       var id_compra= pr[1];
//           
//     //  alert(id_compra) ;    
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#btn-compras').fadeOut(1000, function () {
//                            $('#btn-compras').click();
//                            $('#btn-compras').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=eliminarcompra&id_compra=" + id_compra );
//             
//        
//    }); 
//    $('#body-div-principal').on('change','#buscar_articulos_compra', function () {
//        var id_art = $('#buscar_articulos_compra').val();
//        var usuario = $('#usu').val();
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    valor = valor.split("|");
//                    if (valor[0].length > 3) {
//                        document.getElementById("tb_ventas").innerHTML = valor[0];
//                    } 
////                    else {
////                        swal({
////                            title: 'No hay stock para este producto',
////                            type: 'consulte el inventario',
////                            showConfirmButton: false,
////                            text: 'Gracias',
////                            timer: 2000
////                        });
////                    }
//
//                    document.getElementById("subtotal-now").innerHTML = '$ ' + valor[1];
//                    document.getElementById("iva-final").innerHTML = '$ ' + valor[2];
//                    document.getElementById("totalcompras").innerHTML = '$ ' + valor[3];
//
//
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarArticuloCompra&id=" + id_art + "&usuario=" + usuario);
//
//    });
//    $('#body-div-principal').on('click', '#btn-nueva-compra', function () {
//        $('#btn-compras').click();
//    });
//    $('#body-div-principal').on('change','#buscar_provedores', function () {
//        var id_cliente = $('#buscar_provedores').val();
//        var valueTipo = 1;
//        var usuario = $('#usu').val();
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    var tipoVenta = $("#tipov").text();
//                    if ($.trim(tipoVenta) === 'CREDITO')
//                    {
//                        valueTipo = 2;
//                    }
//                    document.getElementById("proveedor_detalle").innerHTML = valor;
//                    $("#procesar_compras").removeAttr("disabled");
//                    $('#tipov').editable({
//                        send: 'never',
//                        value: 1,
//                        source: [
//                            {value: 1, text: 'CONTADO'},
//                            {value: 2, text: 'CREDITO'}
//                        ],
//                        success: function (response, newValue) {
//                            var id = $('#id_cliente_tmp').val();
//                            var _tipo = $("#title-proceso").text();
//                            var ajax = AJAX();
//                            ajax.open("POST", "servlet?", true);
//                            ajax.onreadystatechange = function () {
//                                if (ajax.readyState === 4) {
//                                    if (ajax.status === 200)
//                                    {
//                                        var valor = ajax.responseText;
//                                        
//                                        if($.trim(valor) === 'Compras'){
//                                            $('#btn-compras').click();
//                                        }else{
//                                            $('#btn-ventas').click();
//                                        }
//
//                                    }
//                                }
//                            };
//                            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                            ajax.send("opcion=tipo_venta&tipo=" + newValue+'&ttipo='+_tipo);
//                        }
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=CargarProveedoresCompras&id=" + id_cliente + "&usuario=" + usuario);
//
//    });
//    $('#body-div-principal').on('click', '#td-change-compra_cant a', function () {
//        var id = '#' + $(this).attr('id');
//        var codigo = id.split("_");
//        
//        var tipo = $('#title-proceso').text();
//
//        
//        $(id).editable({
//            send: 'never',
//            success: function (response, newValue) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            if($.trim(valor)  ===  'Compras'){
//                                $('#btn-compras').click();
//                            }else{
//                               $('#btn-ventas').click(); 
//                            }  
//                        
//                    }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CambiarCantidad&cantidad=" + newValue + "&codigo=" + codigo[1]+'&tipo='+tipo);
//            }
//        });
//    });
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION COMPRAS DETALLE                                            */
///****************************************************************************************************************/
///****************************************************************************************************************/       
//  $('#body-div-principal').on('click', '#btn-compras-detalle', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById("tbl_detalles_compras").innerHTML = valor;
//                    $("#tbl-detalle").dataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "Seleccione de la lista",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=DetalleCompras");
//    });  
//    
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION COBROS Y PAGOS                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/   
//     $('#body-div-principal').on('click','#btn-cobro-pago', function (){
//        // alert("prueba");  
//         var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        var resul = valor.split("|");
//                        document.getElementById('cp_cco').innerHTML = resul[0];
//                        document.getElementById('cp_ccr').innerHTML = resul[1];
//                        document.getElementById('vta_cco').innerHTML = resul[2];
//                        document.getElementById('vta_ccr').innerHTML = resul[3];
//                        document.getElementById('vta_anul').innerHTML = resul[4];
//                        document.getElementById('tbody_cp').innerHTML = resul[5];
//                          $("#tbl_cp").dataTable({
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                        document.getElementById('transacc').innerHTML = resul[6];
//                        
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=datos_cobro_pago");
//    });   
//     $('#body-div-principal').on('click','#add-forma_pago button', function () {
//       //alert("prueba");
//         var id = $(this).attr('id');  
//        var part = id.split("-");
//        var id_cxc = part[0];
//        var valor_cxc = part[1];
//         var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                     //alert(valor);
//                        document.getElementById('modal-add-cxcfp').innerHTML = valor;
//                          $('#add-forma_pagocxc-modal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#add-forma_pagocxc-modal').modal();                     
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=obtener_modal_forma_pago&id="+id_cxc+"&valor="+valor_cxc);
//    });   
//     $('#body-div-principal').on('click', '#btn_asignar_fp', function () {
//        var fecha = $("#fech_cxc").val();
//        var id = $("#id_cxc").val();
//        var valor = $("#valor_cxc").val();
//        var tipo = $("#tipo_pago_cxc").val();
//        var meses = $("#meses_cxc").val();
//        var localidad = $("#localidad_cxc").val();
//        var numero = $("#numero_cxc").val();
//       
//        /***********************validar campos*************************************/
//         // alert(fecha+"-"+id+"-"+valor+"-"+tipo+"-"+meses+"-"+localidad+"-"+numero)  
//                        /***************************despues de validar procesamos los datos***********************************/
//                        var ajax = AJAX();
//                        ajax.open("POST", "servlet?", true);
//                        ajax.onreadystatechange = function () {
//                            if (ajax.readyState === 4) {
//                                if (ajax.status === 200)
//                                {
//                                    var valor = ajax.responseText;
//                                    if (parseInt(valor) === 2) {
//                                        swal({
//                                            title: 'El registro ' + codigox + ' se agrego al inventario!',
//                                            type: 'success',
//                                            showConfirmButton: false,
//                                            text: 'Exitosamente',
//                                            timer: 2000
//                                        });
//                                        $('#add-inventario-modal').modal('hide');
//                                        $('#articulo-btn').fadeOut(1000, function () {
//                                            $('#articulo-btn').click();
//                                            $('#articulo-btn').fadeIn();
//                                        });
//
//
//                                    }
//                                }
//                            }
//                        };
//                        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                        ajax.send("opcion=agregar_pagos&id=" + id + '&fecha=' + fecha + '&valor=' + valor + '&tipo=' + tipo + '&meses=' + meses + '&localidad=' + localidad + '&numero=' + numero);
//                });
//     $('#body-div-principal').on('click','#consulta_transa', function () {
//        var fecha_1 = $('#fecha').val();
//        var fecha_2 = $('#fecha2').val();
//        var trx = $('#transacc').val();
//        //alert(fecha_1+'-'+fecha_2+'-'+trx);
//         var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200)
//                    {
//                       var valor = ajax.responseText;                    
//                        document.getElementById('tbody_cp').innerHTML = valor;
//
//                                          
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=ob_con_trx&fecha_1="+fecha_1+"&fecha_2="+fecha_2+"&trx="+trx);
//    }); 
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION CUENTAS X PAGAR                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/     
//     $('#body-div-principal').on('click','#btn_cuentas_pagar', function (){
//        // alert("prueba");  
//         var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('selec_prov').innerHTML = valor;
//                        
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=ctas_pagar_provedores");
//    }); 
//     $('#body-div-principal').on('click','#consulta_ctas_pagar', function () {
//        var fecha_1 = $('#fecha').val();
//        var fecha_2 = $('#fecha2').val();
//        var provee = $('#selec_prov').val();
//        //alert(fecha_1+'-'+fecha_2+'-'+provee);
//         var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200)
//                    {
//                       var valor = ajax.responseText;                    
//                        document.getElementById('tbody_cpp').innerHTML = valor;
//                            $("#tbl_cpp").dataTable({
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                                          
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=obt_ctas_pagar&fecha_1="+fecha_1+"&fecha_2="+fecha_2+"&provee="+provee);
//    }); 
//     $('#body-div-principal').on('click','#btn_pagar_cxp button', function () {
//     
//         var info = $(this).attr('id'); 
//        var id_contable = info.split("-");
//        $('#tbl_cpp tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                }
//            });
//            
//            if (campo1 === id_contable[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                       document.getElementById("mostrar_data_cxp").innerHTML = valor;
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CrearHtmlCXP&id_cont=" + campo1+ "&ide_factura="+campo2+"&ide_fch_reg="+campo3+"&valor_pagar_cpp="+campo4);
//            }
//           });
//    });
//     $('#body-div-principal').on('click','#btn_con_pa_cxp button', function () {
//          //alert("boton azul");
//         var info = $(this).attr('id'); 
//        var id_contable = info.split("-");
//       // alert(id_contable);
//        $('#tbl_cpp tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                }
//            });
//            if (campo1 === id_contable[1]) {    
//                         //   alert(campo1+'-'+campo2+'-'+campo3+'-'+campo4);
//
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            var resul = valor.split("|");
//   
//                document.getElementById("mostrar_data_cxp").innerHTML = resul[0];
//               //   alert(resul[1]);   
//                //  alert(resul[2]);
//                 //  alert(resul[3]);
//           
//                document.getElementById('tbl_cp').innerHTML = resul[1];
//                //alert(resul[3]);
//                $('#valr_in').val(resul[2]);
//                 document.getElementById('selt_cr').innerHTML = resul[3];
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CrearConsultaCXP&id_cont=" + campo1+ "&ide_factura="+campo2+"&ide_fch_reg="+campo3+"&valor_pagar_cpp="+campo4);
//            }
//           });
//    });
//     $('#body-div-principal').on('click','#amortizar_deuda', function () {
//        var ide_cont = $('#ide_cont').val();
//        var ide_factura = $('#ide_factura').val();
//        var fcha_r = $('#ide_fch_reg').val();
//        var valor_pp = $('#valor_pagar_cpp').val();
//        var diferido = $('#select_cred').val();
//       // var interes = $('#valor_interes').val();
//        
//        var value = parseFloat($('#valor_interes').val())/100;
//        var interes =value.toFixed(2) ;
//        
//        //alert(ide_cont+'-'+ide_factura+'-'+fcha_r+'-'+valor_pp+'-'+diferido+'-'+interes);
//         var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200)
//                    {
//                       var valor = ajax.responseText;                    
//                       //alert(valor);
//                        document.getElementById('tbody_cp').innerHTML = valor;
//                            $("#tbl_cp").dataTable({
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                       $('#amortizar_deuda').attr("disabled", "disabled");                   
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=amortizar_cxp&ide_cont="+ide_cont+"&ide_factura="+ide_factura+"&fcha_r="+fcha_r+"&valor_pp="+valor_pp+"&diferido="+diferido+"&interes="+interes);
//    }); 
//     $('#body-div-principal').on('click','#aceptar_pago', function () {
//     var id_contable = $('#ide_cont').val();
//     //alert(id_contable);
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                                var valor = ajax.responseText;
//                                                //alert(valor);
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "Pagos registrados",
//                                                        type: "info",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                } 
//                                                
//                                               $('#consulta_ctas_pagar').click(); 
//                                             document.getElementById("mostrar_data_cxp").innerHTML ="" ;
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=graba_amort');
//             });
//     $('#body-div-principal').on('click','#btn_pago_cxp_i button', function () {
//     
//        var info = $(this).attr('id'); 
//        var id_p = info.split("-");
//        var id_contable =id_p[1]
//        var id_tbla = id_p[2]
//        //alert(id_p);
//        $('#tbl_cp tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                }
//            });
//             //alert(campo1+''+campo2+''+campo3+''+campo4);
//            if (campo2 === id_contable) {
//     //alert(id_contable+''+id_tbla);
//      swal({
//  title: 'Are you sure?',
//  text: "You won't be able to revert this!",
//  type: 'warning',
//  showCancelButton: true,
//  confirmButtonColor: '#3085d6',
//  cancelButtonColor: '#d33',
//  confirmButtonText: 'Yes, delete it!',
//  cancelButtonText: 'No, cancel!',
//  confirmButtonClass: 'btn btn-success',
//  cancelButtonClass: 'btn btn-danger',
//  buttonsStyling: false
//}).then(function() {
//                 var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                          // alert(valor);
//                            //var id_evento= "con_cx-"+id_contable; 
//                             $('#con_cxp-'+id_contable).click();
//                             swal(        
//                                'Confirmado!',
//                                'Su pago se ha realizado con exito.',
//                                'success'
//                            );
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=UpdatPagoCXP&id_contable=" + id_contable+ "&id_tbla="+id_tbla);
// 
//}, function(dismiss) {
//  // dismiss can be 'cancel', 'overlay', 'close', 'timer'
//  if (dismiss === 'cancel') {
//    swal(
//      'Cancelled',
//      'Your imaginary file is safe :)',
//      'error'
//    );
//  }
//})
//
//            }
//           });
//    });
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION CUENTAS X COBRAR                                       */
///****************************************************************************************************************/
///****************************************************************************************************************/   
//$('#body-div-principal').on('click','#btn_cuentas_cobrar', function (){
//         var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('selec_cliente').innerHTML = valor;
//                          $("#selec_cliente").select2();  
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=llenacliente_cc");
//});     
//$('#body-div-principal').on('click','#consulta_ctas_cobrar', function () {
//        var fecha_1 = $('#fecha_cli_ini').val();
//        var fecha_2 = $('#fecha_cli_fin').val();
//        var cliente = $('#selec_cliente').val();
//         var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200){
//                       var valor = ajax.responseText;                    
//                        document.getElementById('tbody_cxc').innerHTML = valor;
//                            $("#tbl_encabezado_cxc").dataTable({
//                            "order": [[0, "desc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                                          
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=obt_ctas_cobrar&fecha_1="+fecha_1+"&fecha_2="+fecha_2+"&cliente="+cliente);
//    });
//$('#body-div-principal').on('click','#btn_cobrar_cxc button', function () {
//     
//        var info = $(this).attr('id'); 
//        var id_contable = info.split("-");
//        $('#tbl_encabezado_cxc tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                     case 4:
//                        campo5 = $(this).text();
//                        break;
//                }
//            });
//            
//            if (campo1 === id_contable[1]) {
//                //alert(campo4);  
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                           // alert(valor);
//                       document.getElementById("mostrar_data_cxc").innerHTML = valor;
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CrearHtmlCXC&id_cont=" + campo1+ "&ide_factura="+campo2+"&ide_fch_reg="+campo3+"&valor_cobrar_cpc="+campo5);
//            }
//           });
//    });
//$('#body-div-principal').on('click','#amortizar_cobro', function () {
//        var ide_cont = $('#ide_cont1').val();
//        var ide_factura = $('#ide_factura1').val();
//        var fcha_r = $('#ide_fch_reg1').val();
//        var valor_cpc = $('#valor_cobrar_cpc').val();
//        var diferido = $('#select_cobro').val();
//        
//        var value = 0;
//        var interes =value.toFixed(2) ;
//        alert(interes);
//        var ajax = AJAX();
//           ajax.open("POST", "servlet?", true);
//           ajax.onreadystatechange = function () {
//               if (ajax.readyState === 4) {
//                   if (ajax.status === 200)
//                    {
//                       var valor = ajax.responseText;                    
//                        document.getElementById('tbody_cxc_1').innerHTML = valor;
//                            $("#tbl_encabezado_cxc_1").dataTable({
//                            "order": [[0, "asc"]],
//                            "language": {
//                                "lengthMenu": "Mostrar _MENU_ entradas",
//                                "zeroRecords": "Nothing found - sorry",
//                                "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                "infoEmpty": "Seleccione de la lista",
//                                "infoFiltered": "(filtered from _MAX_ total records)"
//                            }
//                        });
//                       $('#amortizar_cobro').attr("disabled", "disabled");                   
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=amortizar_cobro&ide_cont="+ide_cont+"&ide_factura="+ide_factura+"&fcha_r="+fcha_r+"&valor_cpc="+valor_cpc+"&diferido="+diferido+"&interes="+interes);
//    }); 
//$('#body-div-principal').on('click','#aceptar_cobro', function () {
//     var id_contable = $('#ide_cont1').val();
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                                var valor = ajax.responseText;
//                                                //alert(valor);
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "Cobros registrados",
//                                                        type: "info",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                } 
//                                                
//                                               $('#consulta_ctas_cobrar').click(); 
//                                             document.getElementById("mostrar_data_cxc").innerHTML ="" ;
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=graba_amort_cxc');
//             });
//$('#body-div-principal').on('click','#btn_con_co_cxc button', function () {
//var info = $(this).attr('id'); 
//        var id_contable = info.split("-");
//        $('#tbl_encabezado_cxc tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                }
//            });
//            if (campo1 === id_contable[1]) {    
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                            var resul = valor.split("|");
//   
//        document.getElementById("mostrar_data_cxc").innerHTML = resul[0];
//        document.getElementById('tbody_cxc_1').innerHTML = resul[1];
//        $('#valor_interes1').val(resul[2]);
//        document.getElementById('select_cobro').innerHTML = resul[3];
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=CrearConsultaHtmlCXC&id_cont=" + campo1+ "&ide_factura="+campo2+"&ide_fch_reg="+campo3+"&valor_cobrar_cxc="+campo4);
//            }
//           });
//    });
//$('#body-div-principal').on('click','#btn_cobro_cxc_i button', function () {
//     
//        var info = $(this).attr('id'); 
//        var id_p = info.split("-");
//        var id_contable =id_p[1]
//        var id_tbla = id_p[2]
//        $('#tbl_encabezado_cxc_1 tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                }
//            });
//             //alert(campo1+''+campo2+''+campo3+''+campo4);
//            if (campo2 === id_contable) {
//     //alert(id_contable+''+id_tbla);
//      swal({
//  title: 'Estas seguro?',
//  text: "No se podra revertir la transaccion!",
//  type: 'warning',
//  showCancelButton: true,
//  confirmButtonColor: '#3085d6',
//  cancelButtonColor: '#d33',
//  confirmButtonText: 'Si, desea realizar la transaccion!',
//  cancelButtonText: 'No, cancel!',
//  confirmButtonClass: 'btn btn-success',
//  cancelButtonClass: 'btn btn-danger',
//  buttonsStyling: false
//}).then(function() {
//                 var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                          // alert(valor);
//                            //var id_evento= "con_cx-"+id_contable; 
//                             $('#con_cxc-'+id_contable).click();
//                             swal(        
//                                'Confirmado!',
//                                'Su pago se ha realizado con exito.',
//                                'success'
//                            );
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=UpdatCobroCXC&id_contable1=" + id_contable+ "&id_tbla1="+id_tbla);
// 
//}, function(dismiss) {
//  // dismiss can be 'cancel', 'overlay', 'close', 'timer'
//  if (dismiss === 'cancel') {
//    swal(
//      'Cancelled',
//      'Your imaginary file is safe :)',
//      'error'
//    );
//  }
//})
//
//            }
//           });
//    });    
//    /***********************************************************************/
//    /********                   Adiministracion Empresa         ************/
//    /***********************************************************************/   
//    $('#body-div-principal').on('click','#empresa-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        var resul = valor.split("|");
//                        document.getElementById('sec1').innerHTML = resul[0];
//                        document.getElementById('sec2').innerHTML = resul[1];
//                        $('#btn_update_empresa').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-categoria button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_empresa");
//    });   
//    $('#body-div-principal').on('click', '#btn_update_empresa', function () {
//               
//                var campo1= $('#id_empre').val();
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                        {
//                            var valor = ajax.responseText;
//                           
//                            document.getElementById("modal-update-empresa").innerHTML = valor;
//                            $('#add-empresa').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#add_empresa').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_empresa&id=" + campo1);
//          
//    
//        
//    });        
//    $('#body-div-principal').on('click', '#btn_act_empresa', function () {
//       
//    var codigo = $("#codigo").val();
//    var nombre = $("#empresa").val();
//    var direccion = $("#direccion").val();
//    var pais = $("#pais").val();
//    var ciudad = $("#ciudad").val();
//    var email = $("#email").val();;
//    var web = $("#web").val();
//    var ruc = $("#ruc").val();
//    var telefono = $("#telefono").val();
//    var fax = $("#fax").val();
//    var moneda = $("#moneda").val();
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                                var valor = ajax.responseText;
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "La informacion de su empresa se actualizo",
//                                                        type: "Exitosamente",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                    $('#add-empresa').modal('hide');
//                                                    $('#empresa-btn').fadeOut(6000, function () {
//                                                        $('#empresa-btn').click();
//                                                        $('#empresa-btn').fadeIn();
//                                                    });
//
//                                                } else {
//                                                    if (parseInt(valor) === 2) {
//                                                        //alert("por aki");
//                                                        $("#alert-general").addClass("has-error has-feedback");
//                                                        $("#gnr_art_msg").each(function () {
//                                                            $(this).removeClass("hidden");
//                                                        });
//                                                    } else {
//                                                        swal("Error al registrar", "intentelo nuevamente", "error");
//                                                        setTimeout(location.reload(), 20000);
//                                                    }
//                                                }
//                                                
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=actualizar_empresa&codigo='+codigo+'&nombre='+nombre+'&direccion='+direccion+'&pais='+pais+'&ciudad='+ciudad+'&email='+email+'&web='+web+'&ruc='+ruc+'&telefono='+telefono+'&fax='+fax+'&moneda='+moneda);
//                                    
//                           
//            
//    });   
//    /*Administracion Empleados*/
//     $('#body-div-principal').on('click','#empleados-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('tbl_body_empleados').innerHTML = valor;
//                        $('#example1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                        $('#add-empleados').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-empleados button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_empleados");
//    });    
//     $('#body-div-principal').on('click', '#td-btn-empleados button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#example1 tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                     case 3:
//                        campo4 = $(this).text();
//                        break;
//                    case 4:
//                        campo5 = $(this).text();
//                        break;
//                    case 5:
//                        campo6 = $(this).text();
//                        break;
//                     case 6:
//                        campo7 = $(this).text();
//                        break;
//                    case 7:
//                        campo8 = $(this).text();
//                        break;
//                    case 8:
//                        campo9 = $(this).text();
//                        break;
//                    case 9:
//                        campo10 = $(this).text();
//                        break;    
//                }
//            });
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-update-empleados").innerHTML = valor;
//                           $('#modal-upd-empleados').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#modal-upd-empleados').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_empleados&id=" + campo1 + "&nombre=" + campo2 + "&direccion="+campo3 + "&celular="+campo4 +"&identificacion="+campo5 +"&cargo="+campo6 +"&sueldo="+campo7 +"&telefono="+campo8 +"&sexo="+campo9 +"&estado="+campo10 );
//            }
//        });
//    });
//     $('#body-div-principal').on('click', '#btn_update_empleados', function () {
//       
//         if ($("#nombre_empleados").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var id              = $("#id_empleados").val();
//            var nombre          = $("#nombre_empleados").val();
//            var direccion       = $("#direccion_empleados").val();
//            var identificacion  = $("#identificacion_empleados").val();
//            var telefono        = $("#tel_empleados").val();
//            var celular         = $("#celular_empleados").val();
//            var cargo           = $("#cargo_empleados").val();
//            var sueldo          = $("#sueldo_empleados").val();
//            var sexo            = $("#sexo_empleados").val();
//            var estado          = $("#estado_empleados").val();
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#modal-upd-empleados').modal('hide');
//                        $('#empleados-btn').fadeOut(1000, function () {
//                            $('#empleados-btn').click();
//                            $('#empleados-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_empleados&id=" + id + "&nombre=" + nombre + "&direccion="+ direccion + "&identificacion="+ identificacion + "&telefono="+ telefono + "&celular="+ celular + "&cargo="+ cargo + "&sueldo="+ sueldo + "&sexo="+ sexo +"&estado=" + estado);
//        }
//    });  
//     $('#body-div-principal').on('click', '#add-empleados', function (){
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-add-empleados").innerHTML = valor;
//                           $('#modal-agg-empleados').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#modal-agg-empleados').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_add_empleados");
//        
//    });
//     $('#body-div-principal').on('click', '#btn_add_empleados', function (){
//       if ($("#nombre_empleados_1").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } 
//        else{
//            //var id              = $("#id_empleados").val();
//            var nombre          = $("#nombre_empleados_1").val();
//            var direccion       = $("#direccion_empleados").val();
//            var identificacion  = $("#identificacion_empleados").val();
//            var telefono        = $("#tel_empleados").val();
//            var celular         = $("#celular_empleados").val();
//            var cargo           = $("#cargo_empleados").val();
//            var sueldo          = $("#sueldo_empleados").val();
//            var sexo            = $("#sexo_empleados").val();
//            var estado          = $("#estado_empleados").val();
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        if(parseInt(valor) === 1){
//                                $(".form-group").addClass("has-success has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('Dato correcto');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-ok');
//                                });
//
//                                 swal({
//                                    title: 'El registro '+ nombre+' fue ingresado!',
//                                    type: 'success',
//                                    showConfirmButton: false,
//                                    text: 'Exitosamente'+ nombre,
//                                    timer: 5000
//                                  });
//                                  /*se aplica calback para que el proceso cordine con las opcciones a ejecutarse*/
//                                  $('#modal-agg-empleados').modal('hide');
//                                  $('#empleados-btn').fadeOut(6000, function (){
//                                      $('#empleados-btn').click();
//                                      $('#empleados-btn').fadeIn();
//                                  });
//                                  
//                        }
//                        if(parseInt(valor) === 2){
//                                $(".form-group").addClass("has-error has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('El registro ya existe (intentelo nuevamente)');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-remove');
//                                });
//                        }
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//              ajax.send("opcion=add_empleados&nombre=" + nombre + "&direccion="+ direccion + "&identificacion="+ identificacion + "&telefono="+ telefono + "&celular="+ celular + "&cargo="+ cargo + "&sueldo="+ sueldo + "&sexo="+ sexo +"&estado=" + estado);
//         }        
//    });
//    
//    /*Administracion Usuarios*/
//     $('#body-div-principal').on('click','#usuarios-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('tbl_body_usuarios').innerHTML = valor;
//                        $('#example1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                        $('#add-usuarios').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-usuarios button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_usuarios");
//    });    
//     $('#body-div-principal').on('click', '#td-btn-usuarios button', function () {
//        var id = $(this).attr('id');
//        var pr = id.split("-");
//        $('#example1 tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                     case 3:
//                        campo4 = $(this).text();
//                        break;
//                    case 4:
//                        campo5 = $(this).text();
//                        break;
//                    case 5:
//                        campo6 = $(this).text();
//                        break; 
//                     case 6:
//                        campo7 = $(this).text();
//                        break;
//                    case 7:
//                        campo8 = $(this).text();
//                        break;    
//                }
//            });
//            if (campo1 === pr[1]) {
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-update-usuarios").innerHTML = valor;
//                           $('#modal-upd-usuarios').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#modal-upd-usuarios').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_update_usuarios&id=" + campo1 + "&ide=" + campo2 + "&nombre="+campo4 + "&cargo="+campo5 +"&almacen="+campo6 +"&estado="+campo7+"&tipo="+campo8);
//            }
//        });
//    });
//     $('#body-div-principal').on('click', '#btn_update_usuarios', function () {
//       
//         if ($("#ide_usuarios").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var id           = $("#id_usuarios").val();
//            var ide          = $("#ide_usuarios").val();
//            var nombre       = $("#nombre_usuarios").val();
//            var cargo        = $("#tipo_usuarios").val();
//            var tipo         = $("#tipo_usuarios").val();
//            var almacen         = $("#almacen_usuarios").val();
//            var estado           = $("#estado_usuarios").val();
//          
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#modal-upd-usuarios').modal('hide');
//                        $('#usuarios-btn').fadeOut(1000, function () {
//                            $('#usuarios-btn').click();
//                            $('#usuarios-btn').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_usuarios&id=" + id + "&ide=" + ide + "&nombre="+ nombre + "&cargo="+ cargo + "&tipo="+ tipo + "&almacen="+ almacen + "&estado="+ estado);
//        }
//    });  
//     $('#body-div-principal').on('click', '#add-usuarios', function (){
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            var valor = ajax.responseText;
//                            document.getElementById("modal-add-usuarios").innerHTML = valor;
//                           $('#modal-agg-usuarios').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                            });
//                            $('#modal-agg-usuarios').modal();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=modal_add_usuarios");
//        
//    });
//     $('#body-div-principal').on('click', '#btn_add_usuarios', function (){
//         if ($("#ide_usuarios_1").val().length < 1) {
//             $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } 
//        else{
//            //var id              = $("#id_empleados").val();
//             var ide          = $("#ide_usuarios_1").val();
//            var nombre       = $("#nombre_usuarios").val();
//            var cargo  = $("#tipo_usuarios").val();
//            var tipo        = $("#tipo_usuarios").val();
//            var almacen         = $("#almacen_usuarios").val();
//            var estado           = $("#estado_usuarios").val();
//          
//            
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        
//                        if(parseInt(valor) === 0){
//                                $(".form-group").addClass("has-success has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('Dato correcto');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-ok');
//                                });
//
//                                 swal({
//                                    title: 'El registro '+ nombre+' fue ingresado!',
//                                    type: 'success',
//                                    showConfirmButton: false,
//                                    text: 'Exitosamente'+ nombre,
//                                    timer: 5000
//                                  });
//                                  /*se aplica calback para que el proceso cordine con las opcciones a ejecutarse*/
//                                  $('#modal-agg-usuarios').modal('hide');
//                                  $('#usuarios-btn').fadeOut(6000, function (){
//                                      $('#usuarios-btn').click();
//                                      $('#usuarios-btn').fadeIn();
//                                  });
//                                  
//                        }
//                        if(parseInt(valor) === 2){
//                                $(".form-group").addClass("has-error has-feedback");
//                                $("#msg-alert").each( function() {
//                                    $(this).removeClass("hidden");
//                                    $(this).empty().append('El registro ya existe (intentelo nuevamente)');
//                                });
//                                $("#glyphicon-input").each( function() {
//                                    $(this).removeClass("hidden"); 
//                                    $(this).addClass('glyphicon-remove');
//                                });
//                        }
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=add_usuarios&ide=" + ide + "&nombre="+ nombre + "&cargo="+ cargo + "&tipo="+ tipo + "&almacen="+ almacen + "&estado="+ estado);
//         }        
//    });
//   
//   /*Administracion Permisos perfil*/
//     $('#body-div-principal').on('click','#permisos-btn', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('tbl_body_usuarios').innerHTML = valor;
//                        $('#example1').DataTable({
//                            "order": [[ 0, "desc" ]],
//                            "language": {
//                                            "lengthMenu": "Mostrar _MENU_ entradas",
//                                            "zeroRecords": "Nothing found - sorry",
//                                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                                            "infoEmpty": "No se encontraron datos",
//                                            "infoFiltered": "(filtered from _MAX_ total records)"
//                                        }
//                        });
//                        $('#add-permisos').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                        $('#td-btn-permisos button').hover( function (){
//                            $(this).tooltip('show');
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_permiso");
//    });
//     $('#body-div-principal').on('click','#view-perfil', function (){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        document.getElementById('perfil_vi').innerHTML = valor;
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_perfil");
//    });  
//     $('#body-div-principal').on('click','#id_li a', function (){
//        var id = $(this).attr('id');
//        var pr = id.split("-"); 
//        var perfil = pr[1];
//         var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        
//                        document.getElementById('menu_prin').innerHTML = valor;
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_permisos&id_per="+perfil);
//    }); 
//    $('#body-div-principal').on('click', '#td-btn-permisos button', function () {
//       var identificador = $(this).attr('id');
//        var pr = identificador.split("-");
//        var valor="";
//       swal({
//                                    title: "Actualizando.....",
//                                    type: 'info',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 2000
//                                });
//       
//        $('#exa tbody tr').each(function (index) {
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                      campo1 = $(this).text();
//                        break;                     
//                    case 1:
//                    var s="#myche_"+campo1;
//                    if($(s).prop('checked')) {
//                       campo2= 's';
//                        }else{
//                        campo2='n';
//                       }
//                   
//break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                }
//            });
//        
//          var perfil = pr[1];
//                var ajax = AJAX();
//                ajax.open("POST", "servlet?", true);
//                ajax.onreadystatechange = function () {
//                    if (ajax.readyState === 4) {
//                        if (ajax.status === 200)
//                       {
//                            valor = ajax.responseText;
//                         //  alert(valor);
//                         xxx=1;
////                            document.getElementById("modal-update-usuarios").innerHTML = valor;
//
////                        
//                                  
////                           $('#modal-upd-usuarios').modal({
////                                backdrop: 'static',
////                                keyboard: true,
////                                show: true
////                            });
////                            $('#modal-upd-usuarios').click();
////                              $('#modal-upd-usuarios').click();
//                        }
//                    }
//                };
//                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                ajax.send("opcion=upd_permi&id=" + campo1 + "&estado=" + campo2 + "&perfil=" + perfil);
//            
//        });
//    });
//
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION CLIENTES                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/   
//    $('#body-div-principal').on('click', '#btn-clientes', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    
//                    document.getElementById('tbl_cliente').innerHTML = valor;
//                    $('#example2').DataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "No se encontraron datos",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                    
//                    $('#add-clientes').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_clientes");
//    });      
//    $('#body-div-principal').on('click', '#add-clientes', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    var resp = valor.split("|");
//                    
//                    
//                   // document.getElementById('Nombre').innerHTML = resp[0]; 
//                    //document.getElementById('Direccion').innerHTML = resp[1];
//                    //document.getElementById('Dui').innerHTML = resp[2];
//                    
//                    $('#add-clientes-moadal').modal({
//                        backdrop: 'static',
//                        keyboard: true,
//                        show: true
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=cargar_combos");
//    });
//    $('#body-div-principal').on('click', '#btn-crear-cliente', function () {
//        if ($("#nombre").val().length > 1) {
//            if ($("#dir").val() !== null) {
//                if ($("#dui").val()!== null) {
//                        if ($("#tel").val() !== null) {
//                                   if( $("#ciudad").val()!== null) {
//                                    
//                                    var nombre = $("#nombre").val();
//                                    var codigo = $("#dui").val();
//                                    var direccion = $("#dir").val();
//                                    var telefono = $("#tel").val();
//                                    var cedula = $("#nrc").val();;
//                                    var ciudad = $("#ciudad").val();
//                                    var estado = $("#estado").val();
//                                    var sexo = $("#sexo").val();
//                                                               
//                                
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                               
//                                                var valor = ajax.responseText;                                        
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "Cliente registrado",
//                                                        type: "info",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                    $('#add-clientes-moadal').modal('hide');
//                                                    $('#btn-clientes').fadeOut(6000, function () {
//                                                        $('#btn-clientes').click();
//                                                        $('#btn-clientes').fadeIn();
//                                                    });
//
//                                                } else {
//                                                    if (parseInt(valor) === 2) {
//                                                       
//                                                        $("#alert-general").addClass("has-error has-feedback");
//                                                        $("#gnr_clt_msg").each(function () {
//                                                            $(this).removeClass("hidden");
//                                                        });
//                                                    } else {
//                                                        swal("Error al registrar", "intentelo nuevamente", "error");
//                                                        setTimeout(location.reload(), 20000);
//                                                    }
//                                                }
//                                                
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=add_clientes&nombre='+nombre+'&dui='+codigo+'&dir='+direccion+'&tel='+telefono+'&nrc='+codigo+'&ciudad='+ciudad+'&estado='+estado+'&sexo='+sexo+'&ciudad='+ciudad);
//                                    
//                                    
//                                } else {
//                                    $("#cod_cliente").addClass("has-error has-feedback");
//                                    $("#cod_cliente_msg").each(function () {
//                                        $(this).removeClass("hidden");
//                                    });
//                                }
//                           
//                        } 
//                    } 
//                } 
//            } });
//    $('#body-div-principal').on('click', '#btn_update_cli button', function () {
//        var doc = $(this).attr('id');
//     
//        var pr = doc.split("-");
//        //alert(pr);
//        $('#example2 tbody tr').each(function (index) {
//             
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                          case 4:
//                        campo5 = $(this).text();
//                        break;
//                }
//            });
//
//            if (campo2 === pr[1]) {
//
//                 $('#act-clientes-moadal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                 });
//                 
//                $('#ide').val(campo1);
//                 $('#doc').val(campo2);
//                $('#name').val(campo3);
//                $('#direc').val(campo4);
//                $('#tele').val(campo5);
//            }
//        });
//    });    
//    $('#body-div-principal').on('click', '#btn-actual-cliente', function () {
//        if ($("#doc").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var ide = $("#ide").val();
//            var telefono = $("#tele").val();
//            var nombre = $("#name").val();
//            var documento = $("#doc").val();
//            var direccion = $("#direc").val();
//            var estatu = $("#estado_cli").val();
//         
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#act-clientes-moadal').modal('hide');
//                        $('#btn-clientes').fadeOut(1000, function () {
//                        $('#btn-clientes').click();
//                        $('#btn-clientes').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_clientes&ide=" +ide +"&dui=" +documento +"&tel=" + telefono+ "&dir=" + direccion + "&nombre=" + nombre+"&estado="+estatu);
//        }
//    });     
///****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION PROVEEDORES                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/   
//
//    $('#body-div-principal').on('click', '#btn-proveedores', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    
//                    document.getElementById('tbl_proveedores').innerHTML = valor;
//                    $('#example2').DataTable({
//                        "order": [[0, "desc"]],
//                        "language": {
//                            "lengthMenu": "Mostrar _MENU_ entradas",
//                            "zeroRecords": "Nothing found - sorry",
//                            "info": "Mostrar la pagina _PAGE_ de _PAGES_",
//                            "infoEmpty": "No se encontraron datos",
//                            "infoFiltered": "(filtered from _MAX_ total records)"
//                        }
//                    });
//                    
//                    $('#add-proveedor').hover(function () {
//                        $(this).tooltip('show');
//                    });
//                    
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=lista_proveedores");
//    });      
//    $('#body-div-principal').on('click', '#add-proveedor', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    $('#add-proveedor-moadal').modal({
//                        backdrop: 'static',
//                        keyboard: true,
//                        show: true
//                    });
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=cargar_combos");
//    });     
//    $('#body-div-principal').on('click', '#btn-crear-proveedor', function () {
//        if ($("#nombre").val().length > 1) {
//            if( $("#dir").val()!== null) {
//              if ($("#tel").val() !== null) {
//                    if ($("#fax").val() !== null) {
//                            if ($("#nit").val() !== null) {
//                                  if( $("#contacto").val()!== null) {
//                                     if ($("#email").val() !== null) {
//                                        if ($("#tel_fijo").val() !== null) {
//                                            if( $("#cel").val()!== null) {
//                                                
//                                            }
//                                    var nombre = $("#nombre").val();
//                                    var direccion = $("#dir").val();
//                                    var telefono = $("#tel").val();
//                                    var fax = $("#fax").val();
//                                    var nit = $("#nit").val();;
//                                    var contacto = $("#contacto").val();
//                                    var email = $("#email").val();
//                                    var tel_fijo = $("#tel_fijo").val();
//                                    var celular= $("#cel").val();
//                                    var estado = $("#estado").val();                           
//                                
//                                    var ajax = AJAX();
//                                    ajax.open("POST", "servlet?", true);
//                                    ajax.onreadystatechange = function () {
//                                        if (ajax.readyState === 4) {
//                                            if (ajax.status === 200)
//                                            {
//                                               
//                                                var valor = ajax.responseText;
//                                                
//                                                if (parseInt(valor) === 1) {
//                                                    swal({
//                                                        title: "El registro fue ingresado",
//                                                        type: "info",
//                                                        showConfirmButton: false,
//                                                        text: '',
//                                                        timer: 2000
//                                                    });
//                                                    $('#add-proveedor-moadal').modal('hide');
//                                                    $('#btn-proveedores').fadeOut(6000, function () {
//                                                        $('#btn-proveedores').click();
//                                                        $('#btn-proveedores').fadeIn();
//                                                    });
//
//                                                } else {
//                                                    if (parseInt(valor) === 2) {
//                                                       
//                                                        $("#alert-general").addClass("has-error has-feedback");
//                                                        $("#gnr_pro_msg").each(function () {
//                                                            $(this).removeClass("hidden");
//                                                        });
//                                                    } else {
//                                                        swal("Error al registrar", "intentelo nuevamente", "error");
//                                                        setTimeout(location.reload(), 20000);
//                                                    }
//                                                }
//                                                
//                                            }
//                                        }
//                                    };
//                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                                    ajax.send('opcion=add_proveedor&nombre='+nombre+'&dir='+direccion+'&tel='+telefono+'&fax='+fax+'&nit='+nit+'&contacto='+contacto+'&email='+email+'&tel_fijo='+tel_fijo+'&cel='+celular+'&estado='+estado);
//                                    
//                                    
//                                } else {
//                                    $("#nom_pro").addClass("has-error has-feedback");
//                                    $("#nom_pro_msg").each(function () {
//                                        $(this).removeClass("hidden");
//                                    });
//                                }
//                            } else {
//                                $("#preced_pro").addClass("has-error has-feedback");
//                                $("#prece_pro_msg").each(function () {
//                                    $(this).removeClass("hidden");
//                                });
//                            }}  }
//                        } 
//                    } 
//                } 
//            } }); 
//    $('#body-div-principal').on('click', '#btn_update_prov button', function () {
//        
//         var nit = $(this).attr('id');
//         
//        var pr = nit.split("-");
//         //alert(pr);
//        $('#example2 tbody tr').each(function (index) {
//            
//            $(this).children("td").each(function (index2) {
//                switch (index2)
//                {
//                    case 0:
//                        campo1 = $(this).text();
//                        break;
//                    case 1:
//                        campo2 = $(this).text();
//                        break;
//                    case 2:
//                        campo3 = $(this).text();
//                        break;
//                    case 3:
//                        campo4 = $(this).text();
//                        break;
//                    case 4:
//                        campo5 = $(this).text();
//                        break;
//                    case 5:
//                        campo6 = $(this).text();
//                        break;
//                    case 6:
//                        campo7 = $(this).text();
//                        break;
//                    case 7:
//                        campo8 = $(this).text();
//                        break;
//                }
//            });
//            
//            if (campo2 === pr[1]) {
//           
//                 $('#act-proveedor-moadal').modal({
//                                backdrop: 'static',
//                                keyboard: true,
//                                show: true
//                 });
//                 
//                $('#ide').val(campo1); 
//                $('#nit2').val(campo2);
//                $('#name').val(campo3);
//                $('#tele').val(campo4);
//                $('#direc').val(campo5);
//               // alert(campo8);
//                if(campo8==="Activo"){
//                    
//                     $('#estado_proveed').val("s");
//                }else{
//                     $('#estado_proveed').val("n");
//                }
//                 //alert(campo8);
//               
//                
//            }
//                   });
//    });
//    $('#body-div-principal').on('click', '#btn-actual-proveedor', function () {
//        if ($("#nit2").val().length < 1) {
//            $(".form-group").addClass("has-error has-feedback");
//            $("#msg-alert").each(function () {
//                $(this).removeClass("hidden");
//                $(this).empty().append('Es necesario llenar el campo');
//            });
//            $("#glyphicon-input").each(function () {
//                $(this).removeClass("hidden");
//                $(this).addClass('glyphicon-remove');
//            });
//        } else {
//            var telefono = $("#tele").val();
//            var nombre = $("#name").val();
//            var nit2 = $("#nit2").val();
//            var direccion = $("#direc").val();
//            var ide=$("#ide").val();
//            var estado_proveed=$("#estado_proveed").val();
//            var ajax = AJAX();
//            //alert(estado_proveed);
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        $('#act-proveedor-moadal').modal('hide');
//                        $('#btn-proveedores').fadeOut(1000, function () {
//                        $('#btn-proveedores').click();
//                        $('#btn-proveedores').fadeIn();
//                        });
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=update_proveedores&ide=" +ide +"&nit=" +nit2 +"&tel=" + telefono+ "&dir=" + direccion + "&nombre=" + nombre+ "&estado_proveed=" + estado_proveed);
//        }
//    });
//    
// /****************************************************************************************************************/
///****************************************************************************************************************/
///*                                                SECCION REPORTES                                           */
///****************************************************************************************************************/
///****************************************************************************************************************/ 
//
//
//$('#body-div-principal').on('click', '#fd', function () {
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    valor = valor.split("|");
//                    document.getElementById("nav-fill").innerHTML = valor[0];
//                    document.getElementById("conten-tab").innerHTML = valor[1];
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=consulta_categoria");
//    });
//
//$('#body-div-principal').on('click', '#btn-consulta-venta', function () {
//        var fch_desde = $('#fecha').val();
//        var fch_hasta = $('#fecha2').val();
//        var tipo = $('#tip').val();
//        
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById("div-consulta").innerHTML = valor;
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=consulta_venta&fch_desde=" + fch_desde + "&fch_hasta=" + fch_hasta + "&tipo=" + tipo);
//        
//    });
//    
//  $('#body-div-principal').on('click', '#list-report-x-cat', function () {
//        var fch_desde = $('#fecha').val();
//        var fch_hasta = $('#fecha2').val();
//        var tipo = $('#tip').val();
//        
//        var ajax = AJAX();
//        ajax.open("POST", "servlet?", true);
//        ajax.onreadystatechange = function () {
//            if (ajax.readyState === 4) {
//                if (ajax.status === 200)
//                {
//                    var valor = ajax.responseText;
//                    document.getElementById("div-consulta").innerHTML = valor;
//                }
//            }
//        };
//        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        ajax.send("opcion=consulta_venta&fch_desde=" + fch_desde + "&fch_hasta=" + fch_hasta + "&tipo=" + tipo);
//        
//    });
//    
//    
//    $('#body-div-principal').on('click', '#li_lista_cat_rep a', function () {  
//        var id_cat = $(this).attr('id'); 
//        $('.box-header button').attr('id', id_cat);      
//    });
//    
//    
//    $('#body-div-principal').on('click', '.box-header buttong', function () {  
//       
//        var id_cat = $('.box-header button').attr('id');
//        document.location.href = 'modulos/reporte/Report/ArticulosCategoria.jsp?id_compr='+id_cat;
//    });   
    
    /*--------------------------------------------------------------------------------------
      ------------------------------Creacion empresas---------------------------------------
      --------------------------------------------------------------------------------------*/
alert("hola");                                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                            
$('#body-div-principal').on('click', '#btn-crear-empresa', function () {
 alert("hola");
        if ($("#identificacion").val().length > 1) {
            if( $("#razon").val()!== null) {
              if ($("#telefono").val() !== null) {
                    if ($("#mail").val() !== null) {
                            if ($("#direccion").val() !== null) {
//                                  if( $("#contacto").val()!== null) {
//                                     if ($("#email").val() !== null) {
//                                        if ($("#tel_fijo").val() !== null) {
                                            
                                    var identificacion = $("#identificacion").val();
                                    var razon = $("#razon").val();
                                    var telefono = $("#telefono").val();
                                    var mail = $("#mail").val();
                                    var direccion = $("#direccion").val();;
                                                             
                                
                                    var ajax = AJAX();
                                    ajax.open("GET", "servlet?", true);
                                    ajax.onreadystatechange = function () {
                                        if (ajax.readyState === 4) {
                                            if (ajax.status === 200)
                                            {
                                               
                                                var valor = ajax.responseText;
                                                
                                                if (parseInt(valor) === 1) {
                                                    swal({
                                                        title: "El registro fue ingresado",
                                                        type: "info",
                                                        showConfirmButton: false,
                                                        text: '',
                                                        timer: 2000
                                                    });
//                                                    $('#add-proveedor-moadal').modal('hide');
//                                                    $('#btn-proveedores').fadeOut(6000, function () {
//                                                        $('#btn-proveedores').click();
//                                                        $('#btn-proveedores').fadeIn();
                                                    //});

                                                } else {
                                                    if (parseInt(valor) === 2) {
                                                       
                                                        $("#alert-general").addClass("has-error has-feedback");
                                                        $("#gnr_pro_msg").each(function () {
                                                            $(this).removeClass("hidden");
                                                        });
                                                    } else {
                                                        swal("Error al registrar", "intentelo nuevamente", "error");
                                                        setTimeout(location.reload(), 20000);
                                                    }
                                                }
                                                
                                            }
                                        }
                                    };
                                    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                    ajax.send('opcion=crear_empresa&identificacion='+identificacion+'&razon='+razon+'&telefono='+telefono+'&mail='+mail+'&direccion='+direccion);
                                    
                                    
//                                } else {
//                                    $("#nom_pro").addClass("has-error has-feedback");
//                                    $("#nom_pro_msg").each(function () {
//                                        $(this).removeClass("hidden");
//                                    });
//                                }
//                            } else {
//                                $("#preced_pro").addClass("has-error has-feedback");
//                                $("#prece_pro_msg").each(function () {
//                                    $(this).removeClass("hidden");
//                                });
//                            }} 
                             }
                        } 
                    } 
                } 
            } }); 
 });


