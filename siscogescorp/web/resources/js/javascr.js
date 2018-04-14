/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

function CargarResumenValores(id_almacen){
    var ajax = AJAX();
    ajax.open("POST", "servlet?", true);
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200)
            {
                var valor = ajax.responseText;
                var res = valor.split("|");
                
                document.getElementById('cobros').innerHTML = "$ "+parseFloat(res[0]).toFixed(2);
                document.getElementById('ventas').innerHTML = "$ "+parseFloat(res[1]).toFixed(2);
                document.getElementById('compras').innerHTML = "$ "+parseFloat(res[2]).toFixed(2);
                document.getElementById('pagos').innerHTML = "$ "+parseFloat(res[3]).toFixed(2);
                document.getElementById('anulaciones').innerHTML = "$ "+parseFloat(res[4]).toFixed(2);
            }
        }
    };
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send("opcion=obtener_valores&id_almacen=" + id_almacen); 
}

//    function lista_marcas(){
//            var ajax = AJAX();
//            ajax.open("POST", "servlet?", true);
//            ajax.onreadystatechange = function () {
//                if (ajax.readyState === 4) {
//                    if (ajax.status === 200)
//                    {
//                        var valor = ajax.responseText;
//                        alert("dfdf");
//                        document.getElementById('body-marcas').innerHTML = valor;
//                        $(document).on('ready', function (){
//                            $('example1').DataTable();
//                        }); 
//                    }
//                }
//            };
//            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            ajax.send("opcion=lista_marcas");
//    }


