
/* global ActiveXObject */

function AJAX()
{
    var xRequest = null;
    if (window.XMLHttpRequest) {
        xRequest = new XMLHttpRequest();
    } else if (typeof ActiveXObject !== "undefined") {
        xRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xRequest;
}

function ingresarEmpresa(){
    
    //var iden = document.getElementById('identificacion').value;
    
    alert("hola");
    
     if ($("#identificacion").val().length > 1) {
            if ($("#razon").val() !== null) {
                if ($("#direccion").val() !== null) {
                    if ($("#telefonos").val() !== null) {
                        if ($("#email").val() !== null) {

                            var identificacion = $("#identificacion").val();
                            var razon = $("#razon").val();
                            var direccion = $("#direccion").val();
                            var telefono = $("#telefonos").val();
                            var mail = $("#email").val();
                        alert("iden");
                            var ajax = AJAX();
                            ajax.open("POST", "../controlador?", true);
                            ajax.onreadystatechange = function () {
                                if (ajax.readyState === 4) {
                                    if (ajax.status === 200)
                                    {

                                        var valor = ajax.responseText;
                                        alert(valor);
                                        if (parseInt(valor) === 1) {
                                            swal({
                                                title: "Cliente registrado",
                                                type: "info",
                                                showConfirmButton: false,
                                                text: '',
                                                timer: 2000
                                            });
//                                                    $('#add-clientes-moadal').modal('hide');
//                                                    $('#btn-clientes').fadeOut(6000, function () {
//                                                        $('#btn-clientes').click();
//                                                        $('#btn-clientes').fadeIn();
//                                                    });

                                        } else {
                                            if (parseInt(valor) === 2) {

                                                $("#alert-general").addClass("has-error has-feedback");
                                                $("#gnr_clt_msg").each(function () {
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
                            ajax.send('opcion=crear_empresa&identificacion=' + identificacion + '&razon=' + razon + '&direccion=' + direccion + '&telefono=' + telefono + '&mail=' + mail);
                        } 

                    }
                }
            }
        }
}


$(document).on('ready', function () {
    
    $('#btn-login').on('click', function () {
        var name = $('#usuario').val();
        var password = $('#password').val();

        if (name.length > 1) {
            //alert("name");
            if (password.length > 1) {
                //alert("pass");
                var ajax = AJAX();
                ajax.open("POST", "controlador?", true);
                ajax.onreadystatechange = function () {
                    if (ajax.readyState === 4) {
                                    if (ajax.status === 200)
                                    {
                            var valor =ajax.responseText;
                            var res = valor.split("|");                           
//                            if(res[0] === '1'){
//                                swal({
//                                    title: res[1],
//                                    type: 'info',
//                                    showConfirmButton: false,
//                                    text: '',
//                                    timer: 2000
//                                });
                    
                                document.location.href = 'index.jsp';
//                            }else{
//                                 swal({
//                                    title: 'Contrasena erronea',
//                                    type: 'warning',
//                                    showConfirmButton: false,
//                                    text: 'Ingrese su contrasena!',
//                                    timer: 2000
//                                 });
//                            }
                                    }
                                }
                };
                ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                ajax.send("opcion=autentificacion&usuario=" + name + "&password=" + password);
            } else {
                swal({
                    title: 'Debes ingresar una contraseña',
                    type: 'info',
                    showConfirmButton: false,
                    text: '',
                    timer: 2000
                });
            }
        } else {
            swal({
                title: 'El campo usuario es necesario para el ingreso',
                type: 'info',
                showConfirmButton: false,
                text: '',
                timer: 2000
            });
        }

    });

});