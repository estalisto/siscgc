/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function frm_zona()
{
    var empresa = $('#empresa2').val();
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("sectores?accion=agregar", {}, function () {
        if (empresa !== "") {
            $('#empresa').css("display", "none");
            $('#empresa2').css("display", "block");
            document.getElementById("empresa2").disabled = true;
        }
    });
}
function sectores()
{
    jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
    jQuery("#page-wrapper").load("sectores?accion=listar", {}, function () { });
}

function validaDatos(empresa, empresa2, nombre, pais, provincia, ciudad, descripcion, accion) {
    if (empresa !== "" || empresa2 !== "") {
        if (nombre.length > 1) {
            if (pais !== "") {
                if (provincia !== "") {
                    if (ciudad !== "") {
                        if (descripcion.length > 1 && descripcion.length < 100) {
                            return true;
                        } else {
                            MsgSalidaModalA("Debe ingresar una descripcion");
                        }
                    } else {
                        MsgSalidaModalA("Debe elegir una ciudad");
                    }
                } else {
                    MsgSalidaModalA("Debe elegir una provincia");
                }
            } else {
                MsgSalidaModalA("Debe elegir un pais");
            }
        } else {
            MsgSalidaModalA("Debe ingresar un Nombre");
        }
    } else {
        MsgSalidaModalA("Debe elegir una Empresa");
    }
    return false;
}

function deletezona(data)

{
    if (confirm("Realmente desea eliminar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("sectores?accion=eliminar&id=" + data, {}, function () { });
        sectores();
    }

}


function ConnsultaDatosID(str)
{

    if (confirm("Realmente desea actualizar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("sectores?accion=buscaID&id=" + str, {}, function () { });
    }

}


function elimino(url) {
    if (confirm("Realmente desea eliminar este registro")) {
        window.location = url;
    }
}

function obtenerProvincia() {
    var pais = $("#pais").val();

    if (pais !== "") {
        $.post("sistema/combo_prov.jsp", $("#data").serialize(), function (data) {
            $("#provincia").html(data);
        });
        document.getElementById("provincia").disabled = false;
    }
}

function obtenerCiudad() {
    var provincia = $("#provincia").val();

    if (provincia !== "") {
        $.post("sistema/combo_ciudad.jsp", $("#data").serialize(), function (data) {
            $("#ciudad").html(data);
        });
        document.getElementById("ciudad").disabled = false;
    }
}

$('#btncrearzona').click(function (e) {
    e.preventDefault();

    var empresa = $("#empresa").val();
    var nombre = $("#nombre").val();
    var pais = $("#pais").val();
    var provincia = $("#provincia").val();
    var ciudad = $("#ciudad").val();
    var descripcion = $("#descripcion").val();
    var accion = $("#accion").val();
    var empresa2 = $('#empresa2').val();
    if (empresa === "") {
        empresa = 0;
        if (validaDatos(empresa, empresa2, nombre, pais, provincia, ciudad, descripcion, accion)) {

            var parametros = {
                "accion": accion,
                "empresa": empresa,
                "empresa2": empresa2,
                "nombre": nombre,
                "pais": pais,
                "provincia": provincia,
                "ciudad": ciudad,
                "descripcion": descripcion

            };
            $.ajax({
                data: parametros,
                url: 'sectores',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    if (response) {
                        MsgSalidaModalM(response);
                        frm_zona();//vuelvo a llamar a la pantalla
                    } else {
                        MsgSalidaModalA("ZONA NO REGISTRADA");
                    }

                }
            });



        }
    }
    if(empresa2 === ""){
        empresa2 = 0;
    if (validaDatos(empresa, empresa2, nombre, pais, provincia, ciudad, descripcion, accion)) {

            var parametros = {
                "accion": accion,
                "empresa": empresa,
                "empresa2": empresa2,
                "nombre": nombre,
                "pais": pais,
                "provincia": provincia,
                "ciudad": ciudad,
                "descripcion": descripcion

            };
            $.ajax({
                data: parametros,
                url: 'sectores',
                type: 'GET',
                beforeSend: function () {
                },
                success: function (response) {
                    if (response) {
                        MsgSalidaModalM(response);
                        frm_zona();//vuelvo a llamar a la pantalla
                    } else {
                        MsgSalidaModalA("ZONA NO REGISTRADA");
                    }

                }
            });
        }
    }
});



$('#btnactzona').click(function (e) {
    e.preventDefault();



    var idzona = $("#idzona").val();
    var empresa = $("#empresa").val();
    var nombre = $("#nombre").val();
    var pais = $("#pais").val();
    var provincia = $("#provincia").val();
    var ciudad = $("#ciudad").val();
    var descripcion = $("#descripcion").val();
    var accion = $("#accion").val();

    var parametros = {
        "accion": accion,
        "idzona": idzona,
        "empresa": empresa,
        "nombre": nombre,
        "pais": pais,
        "provincia": provincia,
        "ciudad": ciudad,
        "descripcion": descripcion

    };
    $.ajax({
        data: parametros,
        url: 'sectores',
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            if (response) {
                MsgSalidaModalM(response);
                sectores();//vuelvo a llamar a la pantalla
            }
        }
    });





});
