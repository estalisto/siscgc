/**

 **/





function outlogin()
{      

 var URLactual = window.location;
  // console.log(URLactual);
   var url =URLactual.toString();
                if(/home#/.test(URLactual)){
                 // console.log(URLactual);
                  
                  // console.log(url);
                    url=url.replace("home#","autoriza?accion=outlogin");
                //  console.log('reemplazo');
                 // console.log(url);
                  // location.href="http://localhost:8080/laticobsa/";
                }else if(/home/.test(URLactual)){
                 // console.log(URLactual);                  
                 //  console.log(url);
                    url=url.replace("home","autoriza?accion=outlogin");
                 // console.log('reemplazo');
                //  console.log(url);
                  // location.href="http://localhost:8080/laticobsa/";
                }
              
    //if(confirm("¿Esta seguro que desea Salir?")){
         console.log(url); 
         console.clear()
    location.href=url;//"http://localhost:8080/laticobsa/autoriza?accion=outlogin";
    //}
}

function empresa()
{       jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empresa?accion=listar",{},function(){ });
}

function usuarios()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=listar",{},function(){ });
}
function roles()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("lista_roles",{},function(){ });
}
function modulos()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("modulos?accion=listar",{},function(){ });
}
function cambio_clave()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=cambio_clave",{},function(){ });
}
function reset_clave()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("usuarios?accion=recupera_clave",{},function(){ });
}
function cargos()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cargos?accion=listar",{},function(){ });
}
function personal()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("empleados?accion=listar",{},function(){ });
}
function clientes()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("clientes?accion=listar",{},function(){ });
}
function parametros()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("parametros?accion=listar",{},function(){ });
}

function agencia()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("agencia?accion=listar",{},function(){ });
}

function sectores()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sectores?accion=listar",{},function(){ });
}

function cartera()
{
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cartera?accion=listar",{},function(){ });
        datatableok();
}



function cobranzas()
{

     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("cobranzas?accion=listar",{},function(){ });
 
}
function compropago()
{

     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("compromisos_pagos?accion=listar",{},function(){ });
 
}

function listar_pagos(){
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("recaudacion?accion=listar1",{},function(){ });
}

function recaudacion()
{

     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("recaudacion?accion=listar",{},function(){ });
 
}

function consulcarteras()
{       
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    
     jQuery("#page-wrapper").load("consultacartera?accion=listar",{},function(){});
    //console.log('consultaMisClientes'); 
    //consultaMisClientes();
 //alert("consultaMisClientes");
    
 
}
function consulcarteras_sss()
{       
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
    
     jQuery("#page-wrapper").load("consultacartera?accion=listar",{},function(){});
    //console.log('consultaMisClientes'); 
    //consultaMisClientes();
 //alert("consultaMisClientes");
   // GestionCliente(14,5868);
 
}


function comprobante_pago()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/caja/frm_comprobante_pago.jsp",{},function(){ });
 
}

function cuadre_caja()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("cuadrecaja?accion=listar",{},function(){ });
 
}



function consulta_cartera_asignar()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/asignacion/frm_consulta_cartera_asignar.jsp",{},function(){ });
 
}

function consulta_oficiales()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/asignacion/frm_consulta_oficiales.jsp",{},function(){ });
 
}

function orden_registro()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/asignacion/frm_orden_registro.jsp",{},function(){ });
 
}

function preasignacion_simple()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/asignacion/frm_preasignacion_simple.jsp",{},function(){ });
 
}

function preasignacion_automatica()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/asignacion/frm_preasignacion_automatica.jsp",{},function(){ });
 
}

function proceso_asignar()
{
     jQuery("#preasig_simple").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#preasig_simple").load("sistema/asignacion/frm_proceso_asignar.jsp",{},function(){ });
 
}

function generar_cartas()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("sistema/terrenos/frm_consulta_cartera.jsp",{},function(){ });
 
}

function listar_documentos()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("documentos?accion=listar",{},function(){ });
 
}
function asignacion_cartera()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("asignacioncartera?accion=listar",{},function(){ });
     console.clear();
 
}

function recepcion_documento()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("recepcion?accion=listar",{},function(){ });
}

function consulta_cliente()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("consultacliente?accion=listar",{},function(){ });
 
}



function documentos_generados()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("sistema/terrenos/frm_documentos_generados.jsp",{},function(){ });
 
}
function tipos_documentos()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("documentos?accion=listar",{},function(){ });
 
}
function consulta_deuda_cliente()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("sistema/caja/consulta_cliente.jsp",{},function(){ });
 
}

function list_campanias()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("campanias?accion=listar",{},function(){ });
 
}

function list_notificaciones_pago()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("notificacionespagos?accion=listar",{},function(){ });
 
}
function consulta_cliente_empleado()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("consultacartera?accion=consultar_cliente_empleado",{},function(){ });
 
}

function MsgSalidaModal(title,msg,btname){
var title=title;
var msg=msg;
var btname=btname;

var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header modal-header-warning'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'>"+title+"</h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>"+msg+"</p> "+
        "</div> "+
        "<div class='modal-footer'> "+
          "<button type='button' class='btn btn-primary' data-dismiss='modal'>"+btname+"</button> "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}

function MsgSalidaModalA(msg){
var msg=msg;
var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header modal-header-warning'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'>ALERTA!!!...</h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>"+msg+"</p> "+
        "</div> "+
        "<div class='modal-footer'> "+
          "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cerrar</button> "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}


function MsgSalidaModalM(msg){
var msg=msg;
var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header modal-header-warning'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'>MENSAJE!!!...</h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>"+msg+"</p> "+
        "</div> "+
        "<div class='modal-footer'> "+
          "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cerrar</button> "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}


function ConfiemaSalidaSistema(){

var modal="<div class='modal fade' id='myModal' role='dialog'> "+
    "<div class='modal-dialog'> "+
      "<div class='modal-content'> "+
        "<div class='modal-header modal-header-warning'> "+
          "<button type='button' class='close' data-dismiss='modal'>&times;</button> "+
          "<h4 class='modal-title'><strong> MENSAJE!...</strong></h4> "+
        "</div> "+
        "<div class='modal-body'> "+
          "<p>¿Está Seguro que desea salir del Sistema...?</p> "+
        "</div> "+
        "<div class='modal-footer'> "+
          "<button type='button' onclick='outlogin();' class='btn btn-primary' data-dismiss='modal'>Salir</button>\n\
            <button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>  "+
        "</div> "+
     " </div> "+      
   " </div> "+
  "</div>";
document.getElementById("mensajeSalida").innerHTML = modal; 
 $("#myModal").modal();

}

function ConsultaGestionesDiariasAll(){
    //var s_empleado=$("#s_empleado").val();  
    var s_empleado = $("#mi_empleado").val();
    var s_cliente = $("#idcartera").val();
    var fecha_cons = $("#fecha_gestion").val();
   // alert("s_empleado: "+s_empleado+" s_cliente: "+s_cliente+" fecha_cons: "+fecha_cons);
    
    $("#MisGraficas").load("home", {"accion":"consultaGestionDiaria","s_empleado":s_empleado,"s_cliente":s_cliente,"fecha_cons":fecha_cons});
  
    
}
    function ConsultasMisCompromisosAll(){
        document.getElementById("id_compromisos").innerHTML ="0";
           // $("#id_sub_cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
            $.getJSON("consultacartera", {"accion" : "mis_comprimisos_all"}, function(result){
                 $.each(result.misCompromisos, function(key, val){  
                     document.getElementById("id_compromisos").innerHTML =val.compromisos;
                 // $("#id_sub_cartera").append($("<option>",{value:val.id_subcartera,text:val.subcarteras}));
                 // var valor_select = val.razon_social;
                 // alert(valor_select);

                 });
           });      
    }  
    
  
function drawChart2() {
    
     

        var data = google.visualization.arrayToDataTable([
        
        ]);

        var options = {
          chart: {
          
          },
		   vAxis: {format: 'decimal'},
		   //height: 300,
		   //width: 500,
          bars: 'vertical' //horizontal vertical Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('chart_gestiones_diarias666'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
function ConsultasMisClientesXUsuario(){
           document.getElementById("idcartera").innerHTML="";
           $("#idcartera").append($("<option>",{value:"0",text:"- CEDENTE -"}));
           $.getJSON("consultacartera", {"accion" : "AllClientes"}, function(result){
                $.each(result.listaClientes, function(key, val){   
                 $("#idcartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
                });
           }); 
       }

function ConsultasMisSubCarteras(){
      document.getElementById("id_sub_cartera").innerHTML="";
      alert("ffjdkfjdsk");
      $("#id_sub_cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
      $.getJSON("consultacartera", {"accion" : "AllMisSubCartera"}, function(result){
           $.each(result.listaClientes, function(key, val){   
            $("#id_sub_cartera").append($("<option>",{value:val.id_subcartera,text:val.subcarteras}));
           });
     });      
}     
 
getTipoCarteras2();
function getTipoCarteras2(){
var  idcliente=$('#idcartera').val();   

    if(idcliente === 0 || idcliente === "" ) {  
         return;
     }
     document.getElementById("tcartera").disabled=false;
     document.getElementById("tcartera").innerHTML="";
    $("#tcartera").append($("<option>",{value:"0",text:"- CARTERA -"}));
        $.getJSON("asignacioncartera", {"accion" : "TiposCarteras","idcliente":idcliente}, function(result){
              $.each(result.tipo_cartera, function(key, val){                           
               $("#tcartera").append($("<option>",{value:val.idCartera,text:val.nombreCartera}));               
              });
        });
   document.getElementById("tsub_cartera").innerHTML="";
   document.getElementById("tsub_cartera").disabled=true;
   $("#tsub_cartera").append($("<option>",{value:"0",text:"- SUB CARTERA -"}));
}

function getipoSubCartera2(){
var  idcartera=$('#tcartera').val();   
    document.getElementById("tsub_cartera").disabled=true;
    document.getElementById("tsub_cartera").innerHTML="";
    $("#tsub_cartera").append($("<option>",{value:"0",text:"- SUB CARTERA -"}));
    if(idcartera === 0 || idcartera === "" ) {          
         return;
     }
  if(idcartera!==0){      
     document.getElementById("tsub_cartera").disabled=false;
     document.getElementById("tsub_cartera").innerHTML="";
       $("#tsub_cartera").append($("<option>",{value:"0",text:"- SUB CARTERA -"}));
        $.getJSON("asignacioncartera", {"accion" : "Subcarteras","idcartera":idcartera}, function(result){
              $.each(result.tipo_subcartera, function(key, val){   
               $("#tsub_cartera").append($("<option>",{value:val.idSubcartera,text:val.nombreSubcartera}));               
              });
        });
   }
}

function miGraficaCarteraVsRecuperado()
{
    var mi_empleado=$("#mi_empleado").val();
    var tcartera=$("#tcartera").val();
    var tsub_cartera=$("#tsub_cartera").val();
    var fecha_ini=$("#fecha_ini_calidad2").val();
    var fecha_fin=$("#fecha_fin_calidad2").val();
    
    //fecha_fin fecha_ini mi_empleado tcartera tsub_cartera
    
    
        jQuery("#grafico_dash").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#grafico_dash").load("consultacartera?accion=miGraficaCarteraVsRecuperado&mi_empleado="+mi_empleado+"&tcartera="+
                tcartera+"&tsub_cartera="+tsub_cartera+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin,{},function(){ });
      //  console.clear();
 
}

function miGraficaProduccion()
{
    var mi_empleado=$("#mi_empleado").val();
    var tcartera=$("#tcartera").val();
    var tsub_cartera=$("#tsub_cartera").val();
    var fecha_ini=$("#fecha_ini_calidad2").val();
    var fecha_fin=$("#fecha_fin_calidad2").val();
    document.getElementById("grafica_gestion").innerHTML="";
   // alert("hooohooh");
   var id_RolEmpleado=document.getElementById("id_RolEmpleado").value;
    console.log(">>>>>> id_RolEmpleado: "+id_RolEmpleado+" mi_empleado="+mi_empleado+"&tcartera="+tcartera+"&tsub_cartera="+tsub_cartera+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin);
    
        jQuery("#grafica_gestion").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#grafica_gestion").load("sistema/graficos/gfc_gestion_produccion.jsp?mi_empleado="+mi_empleado+"&tcartera="+
                tcartera+"&tsub_cartera="+tsub_cartera+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin,{},function(){ });
       // console.clear();
 
}

function miGraficaRecuperadoPorUsuario()
{
    var mi_empleado=$("#mi_empleado").val();
    var tcartera=$("#tcartera").val();
    var tsub_cartera=$("#tsub_cartera").val();
    var fecha_ini=$("#fecha_ini_calidad2").val();
    var fecha_fin=$("#fecha_fin_calidad2").val();
    
    //fecha_fin fecha_ini mi_empleado tcartera tsub_cartera
    
    
        jQuery("#recuperado_gestor").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#recuperado_gestor").load("consultacartera?accion=miGraficaRecuperadoPorGestor&mi_empleado="+mi_empleado+"&tcartera="+
                tcartera+"&tsub_cartera="+tsub_cartera+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin,{},function(){ });
      //  console.clear();
 
}