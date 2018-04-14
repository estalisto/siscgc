/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 function consulta_campanias(){
     
       
     document.getElementById("mi_tabla").innerHTML = "";
    var htmlTable="<table id='consulta_campanias' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-3 text-left' style='color: #3c8dbc'>Descripción</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Creación</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Desde</th><th class='col-sm-1 text-right'  style='color: #3c8dbc'>Fecha Hasta</th> <th align='center' class='col-sm-1 text-right'  style='color: #3c8dbc'>#Deudores</th><th align='center' class='col-sm-1 text-right'  style='color: #3c8dbc'>#Gestiones</th><th align='rigth' class='col-sm-1 text-right'  style='color: #3c8dbc'>Acción</th> </tr> </thead><tbody></tbody></table>";
    document.getElementById("mi_tabla").innerHTML = htmlTable;
                
    var table = $('#consulta_campanias').DataTable( {
        "ajax": {   
            "data": {"accion": "consultar"},
            "url": "campanias",
            "type": "GET"
            },
            "columns": [
                { "data": "id_campania"},
                { "data": "descripcion_carga" },
                { "data": "fecha_transaccion2" },
                { "data": "fecha_inicio2" },
                { "data": "fecha_fin2" }, 
                { "data": "num_deudores" },
                { "data": "num_gestiones" },               
                { "defaultContent":"<center><button type='button' title='Eliminar' class='eliminar btn btn-sm btn-danger'><i class='glyphicon glyphicon-minus'></i></button>&nbsp<button type='button' title='Editar' class='editar btn btn-sm btn-success'><i class='glyphicon glyphicon-pencil'></i></button></center>"}  
            ],
            "paging": true,
                "lengthChange": false,
                "info": false,
                "searching": true,
                order: [[ 0, 'desc' ]],
                "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
                    
        } );
        $('#consulta_campanias tbody').on( 'click', 'button.eliminar', function () {
          var data = table.row( $(this).parents('tr') ).data();
            //EliminarMaestrosEdi(data.id_edi_ing);
          //  alert(data.id_campania);
          ConfirmaModal(data.id_campania);
            $("#ModalConfirmacion").modal('show');
         } );
        $('#consulta_campanias tbody').on( 'click', 'button.editar', function () {
          var data = table.row( $(this).parents('tr') ).data();
            //EliminarMaestrosEdi(data.id_edi_ing);
          //  alert(data.id_campania);
          ConfirmaModalEdicion(data.id_campania,data.descripcion_carga);
          consultaQuery(data.id_campania);
            $("#ModalConfirmacion").modal('show');
         } );
        
}

function ConfirmaModal(id){
    var mimodal="<div id='ModalConfirmacion' class='modal fade' role='dialog'>"+
	"<div class='modal-dialog'>"+
		"<div class='modal-content'>"+
			"<div class='modal-header'>"+
				"<button type='button' class='close' data-dismiss='modal'>&times;</button>"+
				"<h4 class='modal-title'>Confirmar</h4>"+
			"</div>"+
			"<div class='modal-body'>"+
				"<p>¿Esta seguro que desea eliminar la campaña Nº 0"+id+"?</p>"+
			"</div>"+
			"<div class='modal-footer'>"+
				"<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>"+
				"<button type='button' class='btn btn-primary'  onclick='eliminar_campania("+id+");' >Eliminar</button>"+
			"</div>"+
		"</div>"+
	"</div>"+
"</div>";
 document.getElementById("mimodal").innerHTML  =""; 
 document.getElementById("mimodal").innerHTML  =mimodal;
}


function ConfirmaEliminarDetModal(id){
    var mimodal="<div id='ModalConfirmacion' class='modal fade' role='dialog'>"+
	"<div class='modal-dialog'>"+
		"<div class='modal-content'>"+
			"<div class='modal-header'>"+
				"<button type='button' class='close' data-dismiss='modal'>&times;</button>"+
				"<h4 class='modal-title'>Confirmar</h4>"+
			"</div>"+
			"<div class='modal-body'>"+
				"<p>¿Esta seguro que desea eliminar el deudor Nº 0"+id+"?</p>"+
			"</div>"+
			"<div class='modal-footer'>"+
				"<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>"+
				"<button type='button' class='btn btn-primary'  onclick='eliminar_det_campania("+id+");' >Eliminar</button>"+
			"</div>"+
		"</div>"+
	"</div>"+
"</div>";
 document.getElementById("mimodal").innerHTML  =""; 
 document.getElementById("mimodal").innerHTML  =mimodal;
}
function ConfirmaModalEdicion(id,descripcion_carga){
    var mimodal="<div id='ModalConfirmacion' class='modal fade' role='dialog'>"+
	"<div class='modal-dialog'>"+
		"<div class='modal-content'>"+
			"<div class='modal-header'>"+
				"<button type='button' class='close' data-dismiss='modal'>&times;</button>"+
				"<h4 class='modal-title'>Confirmar</h4>"+
			"</div>"+
			"<div class='modal-body'>"+
				"<p>¿Esta seguro que desea Modificar la campaña Nº 0"+id+"?</p>"+
			"</div>"+
			"<div class='modal-footer'>"+
				"<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>"+
				"<button type='button' class='btn btn-primary'  onclick=\"editar_campania("+id+",'"+descripcion_carga+"')\" >Modificar</button>"+
			"</div>"+
		"</div>"+
	"</div>"+
"</div>";
 document.getElementById("mimodal").innerHTML  =""; 
 document.getElementById("mimodal").innerHTML  =mimodal;
}
function ConfirmaModalAgregarEmpleado(id,id_empleado,nombres){
    var mimodal="<div id='ModalConfirmacion' class='modal fade' role='dialog'>"+
	"<div class='modal-dialog'>"+
		"<div class='modal-content'>"+
			"<div class='modal-header'>"+
				"<button type='button' class='close' data-dismiss='modal'>&times;</button>"+
				"<h4 class='modal-title'>Confirmar</h4>"+
			"</div>"+
			"<div class='modal-body'>"+
				"<p>¿Esta seguro que desea Agregar este empleado a la campaña Nº 0"+id+"?</p>"+
			"</div>"+
			"<div class='modal-footer'>"+
				"<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>"+
				"<button type='button' class='btn btn-primary'  onclick=\"agregar_empleado_campania("+id+","+id_empleado+",'"+nombres+"')\" >Agregar</button>"+
			"</div>"+
		"</div>"+
	"</div>"+
"</div>";
 console.log(">>"+id+id_empleado+nombres);
 document.getElementById("mimodal").innerHTML  =""; 
 document.getElementById("mimodal").innerHTML  =mimodal;
}

function eliminar_campania(id){
    
      $.ajax({
        data:{"accion":"eliminar", "id_campania":id},
        url: "campanias",
        type: 'POST',
        beforeSend: function () {
        },
        success: function (response) {
            $("#ModalConfirmacion").modal('hide');
        MsgSalidaModalA(response);
            
            

        }
    });
    $("#ModalConfirmacion").modal('hide');  
    consulta_campanias();
}

function agregar_campania(){
    var descripcion=$("#descripcion").val();
    var fecha_desde=$("#finicio").val();
    var fecha_hasta=$("#ffin").val();
     console.log(fecha_desde);
       console.log(fecha_hasta);
    if(descripcion===""){
         alert("Debe ingresar una Descripción");
         return;
    }
    if(fecha_desde===""){
         alert("Debe ingresar la fecha de Inicio de la Campaña");
         return;
    }
    if(fecha_hasta===""){
         alert("Debe ingresar la fecha de fin de la campaña");
         return;
    }
    
      $.ajax({
        data:{"accion":"nueva_campania", "descripcion":descripcion, "fecha_desde":fecha_desde, "fecha_hasta":fecha_hasta},
        url: "campanias",
        type: 'POST',
        beforeSend: function () {
        },
        success: function (response) {
        $("#modalAgregar").modal('hide');
        if(!response.toString("La campania fue eliminada exitosamente")){
            MsgSalidaModalA(response);
        }else{
            MsgSalidaModalA(response);
            document.getElementById("descripcion").value="";
            document.getElementById("finicio").value="";
            document.getElementById("ffin").value="";
        }
        
        
            
            

        }
    });
    $("#modalAgregar").modal('hide');  
    consulta_campanias();
}
function hidden_frm_det_campania(){
    
    document.getElementById("div_crear_det_campania").style.display = 'block'; 
    document.getElementById("div_crear_campania").style.display = 'none'; 
}
function editar_campania(id,descripcion){
    document.getElementById("num_campania").value=id;
    document.getElementById("text_descripcion").value=descripcion;
   // alert("ok");
    
 hidden_frm_det_campania();
   
      $("#ModalConfirmacion").modal('hide'); 
     consultaCartera();
     all_empleados_campanias();
     all_empleados_asig_campanias();
     consulta_datos_campania();
}


function getipoSubCartera(){
var  idcartera=$('#tcartera').val();   
    document.getElementById("tsub_cartera").disabled=true;
    document.getElementById("tsub_cartera").innerHTML="";
    $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
    if(idcartera === 0 || idcartera === "" ) {  
        
         return;
     }
  if(idcartera!==0){
      
     document.getElementById("tsub_cartera").disabled=false;
     document.getElementById("tsub_cartera").innerHTML="";
       $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
        $.getJSON("asignacioncartera", {"accion" : "Subcarteras","idcartera":idcartera}, function(result){
              $.each(result.tipo_subcartera, function(key, val){   
                 
              // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
               $("#tsub_cartera").append($("<option>",{value:val.idSubcartera,text:val.nombreSubcartera}));
               //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
              });
        });

//       document.getElementById("tsub_cartera").disabled=true;
//       $("#tSubCartera").append($("<option>",{value:"0",text:"Seleccione la SubCartera"}));
   }
} 
function getipoSegmento(){
var  idsubcartera=$('#tsub_cartera').val();  
document.getElementById("tsegmento").disabled=true;
document.getElementById("tsegmento").innerHTML="";
$("#tsegmento").append($("<option>",{value:"0",text:"Seleccione Segmento"}));
 if(idsubcartera === 0 || idsubcartera === "" ) {  
        
         return;
     }
  if(idsubcartera!==0){
      document.getElementById("tsegmento").disabled=false;
      document.getElementById("tsegmento").innerHTML="";
$("#tsegmento").append($("<option>",{value:"0",text:"Seleccione Segmento"}));
    $.getJSON("asignacioncartera", {"accion" : "TiposSegmentos","idsubcartera":idsubcartera}, function(result){
          $.each(result.tipo_segmento, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tsegmento").append($("<option>",{value:val.idSegmento,text:val.nombreSegmento}));
           //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
}
//   document.getElementById("tSubSegmento").disabled=true;
//   $("#tSubSegmento").append($("<option>",{value:"0",text:"Seleccione el SubSegmento"}));
}
function getiposubSegmento(){
var  idsegmento=$('#tsegmento').val();  
document.getElementById("tsub_segmento").disabled=true;
document.getElementById("tsub_segmento").innerHTML="";
$("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione Subsegmento"}));
 if(idsegmento === 0 || idsegmento === "" ) {  
        
         return;
     }
  if(idsegmento!==0){
      document.getElementById("tsub_segmento").disabled=false;
      document.getElementById("tsub_segmento").innerHTML="";
$("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione Subsegmento"}));
    $.getJSON("asignacioncartera", {"accion" : "Subsegmentos","idsegmento":idsegmento}, function(result){
          $.each(result.tipo_subsegmento, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tsub_segmento").append($("<option>",{value:val.IdSubsegmento,text:val.NombreSubsegmento}));
           //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
}
//   document.getElementById("tSubSegmento").disabled=true;
//   $("#tSubSegmento").append($("<option>",{value:"0",text:"Seleccione el SubSegmento"}));
}
consultaMisClientes();
function consultaMisClientes(){
document.getElementById("cartera").innerHTML="";
//  $("#cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("consultacartera", {"accion" : "AllClientes"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#cartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
           var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}

function getipoCartera(){
var  idcliente=$('#cartera').val();   
    
   
    if(idcliente === 0 || idcliente === "" ) {  
         return;
     }
  //if(idcliente!==0){
      document.getElementById("tcartera").disabled=false;
     document.getElementById("tcartera").innerHTML="";
    $("#tcartera").append($("<option>",{value:"0",text:"Seleccione Cartera"}));
        $.getJSON("asignacioncartera", {"accion" : "TiposCarteras","idcliente":idcliente}, function(result){
              $.each(result.tipo_cartera, function(key, val){             
              // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
               $("#tcartera").append($("<option>",{value:val.idCartera,text:val.nombreCartera}));
               //$("#tcartera").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
              });
        });
    //}
     
   document.getElementById("tsub_cartera").disabled=true;
   $("#tsub_cartera").append($("<option>",{value:"0",text:"Seleccione la Subcartera"}));
    document.getElementById("tsegmento").disabled=true;
   $("#tsegmento").append($("<option>",{value:"0",text:"Seleccione el Segmento"}));
    document.getElementById("tsub_segmento").disabled=true;
   $("#tsub_segmento").append($("<option>",{value:"0",text:"Seleccione el Subsegmento"}));
}


function ConsultaTipoResultado(){
  var IdTipoGestion=$("#tgestion").val();
  var idcliente=$("#cartera").val();
   // if(IdTipoGestion===0){
   
     document.getElementById("tresultado_gestion").disabled=true;
     document.getElementById("tresultado_gestion").innerHTML="";
     $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));  
  if(IdTipoGestion === 0 || IdTipoGestion === "0" ) {  
         return;
     }
  if(IdTipoGestion!==0){
      document.getElementById("tresultado_gestion").disabled=false;
      document.getElementById("tresultado_gestion").innerHTML="";
      $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Resultado"}));
     $.getJSON("consultacartera", {"accion" : "ALLTiposResulatdos","idcliente":idcliente}, function(result){
         console.log(result);
         console.log('size: '+result.tipos_resultado.tipos_resultado);
          $.each(result.tipos_resultado, function(key, val){             
           $("#tresultado_gestion").append($("<option>",{value:val.idTipoResultado,text:val.nombreTipoResultado}));
          });
    });
  }
    
}
//getTiposGestiones();


function getTiposGestiones(){
document.getElementById("escogecliente").innerHTML="";
  var IdCliente=$("#cartera").val();
          //alert(IdCliente);
    if (parseInt(IdCliente) === 0){
        //alert("Debe escoger un cliente");
        document.getElementById("escogecliente").innerHTML="Debe escoger un cliente";
        document.getElementById("escogecliente").style.color="red";
        return;
     
    } 
    
document.getElementById("tgestion").innerHTML="";
//document.getElementById("gestion").innerHTML="";
$("#tgestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
//$("#gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
    $.getJSON("consultacartera", {"accion" : "AllTiposGestiones","cliente":IdCliente}, function(result){
          $.each(result.tipo_gestion, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tgestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
           var valor_select = val.nombreTipoGestion;
          // alert(valor_select);
          /* if(valor_select === "LLAMADA CLIENTE"){
               
               $("#gestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
            }*/
          });
    });
    
   document.getElementById("tresultado_gestion").disabled=true;
   $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
   //getipoSubCartera();
 //  getipoCartera();
    
}

function consultaCartera(){
    var id_campania=document.getElementById("num_campania").value;
    console.log("id_campania."+id_campania);
       document.getElementById("tabla_div").innerHTML = "";
    var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th align='center' class='col-sm-1'><a  >Acciones</a></th><th class='col-sm-2 text-left'><a >Identificación</a></th><th class='col-sm-2 text-left'><a >Nombres</a></th>  <th class='col-sm-1 text-left'><a  >Días Mora</a></th> <th class='col-sm-1 text-right'><a >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a >Pagos</a></th><th align='center' class='col-sm-1 text-right'><a >Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a >Saldo</a></th> <th align='center' class='col-sm-1 text-right'><a >Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a >Fecha Comp.</a></th><th align='center' class='col-sm-3'><a  >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a >Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a  >Resultado Gestión</a></th></tr></thead><tbody ></tbody></table>";
    document.getElementById("tabla_div").innerHTML = htmlTable;
    var table2 = $('#consul_cartera').DataTable( {
        "ajax": {   
            "data":{"accion": "consulta_det_campania","id_campania":id_campania},
            "url": "campanias",
            "type": "GET"
            },
            "columns": [
                { "defaultContent":"<center><button type='button' title='Eliminar' class='eliminar btn btn-sm btn-danger'><i class='glyphicon glyphicon-minus'></i></button></center>"}  ,
                { "data": "identificacion" },  
                { "data": "nombres_completo" },  
                { "data": "dias_mora" },  
                { "data": "total_vencidos" },  
                { "data": "pagos" },  
                { "data": "fecha_ult_pagos" },  
                { "data": "saldo" },  
                { "data": "valor_compro" },  
                { "data": "fecha_comp" },  
                { "data": "fech_ultima_gestion" },  
                { "data": "ultima_gestion" },  
                { "data": "resultado_gestion" }
            ],
                    "paging": true,
                    "lengthChange": false,
                    "searching": true,
                    "ordering": true,
                    "info": true,
                    "autoWidth": true,
                    order: [[ 4, 'desc' ]],
                  "language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
                    
        } );
        $('#consul_cartera tbody').on( 'click', 'button.eliminar', function () {
          var data = table2.row( $(this).parents('tr') ).data();
            //EliminarMaestrosEdi(data.id_edi_ing);
           console.log(data.id_det_campanias);
           ConfirmaEliminarDetModal(data.id_det_campanias);
            $("#ModalConfirmacion").modal('show');
         
         } );
     
table2 = $('#consul_cartera').DataTable();
var orderNombre=0;
var orderIdentificacion=0;
var orderDiasMora=0;
var orderTotalVenc=1;
var orderPagos=0;
var orderFechaUltPagos=0;
var orderSaldo=0;
var orderValorComp=0;
var orderFechaComp=0;
var orderFchGestion=0;
var orderUltima=0;
var orderResultado=0;

$('#consul_cartera thead').on('click', 'th', function () {
 var index = table2.column(this).index();
console.log('consulta de columna: '+index);
     switch (index) {
    case 1:
       if(orderIdentificacion===1){
            document.getElementById("order_by").value="order by identificacion desc ";
            orderIdentificacion=0;
            console.log('orderIdentificacion: '+orderIdentificacion);
        }else{
             document.getElementById("order_by").value="order by identificacion asc ";
             orderIdentificacion=1;
             console.log('orderIdentificacion: '+orderIdentificacion);
        }
        break;
    case 2:
        if(orderNombre===1){
            document.getElementById("order_by").value="order by nombres_completo desc ";
            orderNombre=0;
            console.log('orderNombre: '+orderNombre);
        }else{
             document.getElementById("order_by").value="order by nombres_completo asc ";
             orderNombre=1;
             console.log('orderNombre: '+orderNombre);
        }
        break;
    case 3:
       
        if(orderDiasMora===1){
            document.getElementById("order_by").value="order by dias_mora desc ";
            orderDiasMora=0;
            console.log('dias_mora: '+orderDiasMora);
        }else{
             document.getElementById("order_by").value="order by dias_mora asc";
             orderDiasMora=1;
             console.log('dias_mora: '+orderDiasMora);
        }
        break;
    case 4:
        
        if(orderTotalVenc===1){
            document.getElementById("order_by").value="order by total_vencidos asc ";
            orderTotalVenc=0;
            console.log('orderTotalVenc: '+orderTotalVenc);
        }else{
             document.getElementById("order_by").value="order by total_vencidos desc ";
             orderTotalVenc=1;
             console.log('orderTotalVenc: '+orderTotalVenc);
        }
        break;
    case 5:
        
          if(orderPagos===1){
            document.getElementById("order_by").value="order by pagos desc ";
            orderPagos=0;
            console.log('orderPagos: '+orderPagos);
        }else{
             document.getElementById("order_by").value="order by pagos asc";
             orderPagos=1;
             console.log('orderPagos: '+orderPagos);
        }
        break;
    case 6:
         if(orderFechaUltPagos===1){
            document.getElementById("order_by").value="order by fecha_ult_pagos desc ";
            orderFechaUltPagos=0;
            console.log('orderFechaUltPagos: '+orderFechaUltPagos);
        }else{
             document.getElementById("order_by").value="order by fecha_ult_pagos asc ";
             orderFechaUltPagos=1;
             console.log('orderFechaUltPagos: '+orderFechaUltPagos);
        }
        
        break;
    case 7:
        if(orderFechaUltPagos===1){
            document.getElementById("order_by").value="order by fecha_ult_pagos desc";
            orderFechaUltPagos=0;
            console.log('orderFechaUltPagos: '+orderFechaUltPagos);
        }else{
             document.getElementById("order_by").value="order by fecha_ult_pagos asc ";
             orderFechaUltPagos=1;
             console.log('orderFechaUltPagos: '+orderFechaUltPagos);
        }
        break;
    case 8:
        
        if(orderValorComp===1){
            document.getElementById("order_by").value="order by valor_compro desc ";
            orderValorComp=0;
            console.log('orderValorComp: '+orderValorComp);
        }else{
             document.getElementById("order_by").value="order by valor_compro asc";
             orderValorComp=1;
             console.log('orderValorComp: '+orderValorComp);
        }
        break;
    case 9:
        
        if(orderFechaComp===1){
            document.getElementById("order_by").value="order by fecha_comp desc";
            orderFechaComp=0;
            console.log('orderFechaComp: '+orderFechaComp);
        }else{
             document.getElementById("order_by").value="order by fecha_comp asc ";
             orderFechaComp=1;
             console.log('orderFechaComp: '+orderFechaComp);
        }
        break;
    case 10:
        
          if(orderFchGestion===1){
            document.getElementById("order_by").value="order by fech_ultima_gestion desc ";
            orderFchGestion=0;
            console.log('orderFchGestion: '+orderFchGestion);
        }else{
             document.getElementById("order_by").value="order by fech_ultima_gestion asc";
             orderFchGestion=1;
             console.log('orderFchGestion: '+orderFchGestion);
        }
        break;
    case 11:
        
        if(orderUltima===1){
            document.getElementById("order_by").value="order by ultima_gestion desc";
            orderUltima=0;
            console.log('orderUltima: '+orderUltima);
        }else{
             document.getElementById("order_by").value="order by ultima_gestion asc ";
             orderUltima=1;
             console.log('orderUltima: '+orderUltima);
        }
        break;
    case 12:
        
         if(orderResultado===1){
            document.getElementById("order_by").value="order by resultado_gestion desc ";
            orderResultado=0;
            console.log('orderResultado: '+orderResultado);
        }else{
             document.getElementById("order_by").value="order by resultado_gestion asc ";
             orderResultado=1;
             console.log('orderResultado: '+orderResultado);
        }
        break;    
}

   
    $('#id_loader').css("display", "none");
 // alert('index : '+index);
});
}

function eliminar_det_campania(id){
    
      $.ajax({
        data:{"accion":"eliminar_det_campania", "id_det_campania":id},
        url: "campanias",
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            $("#ModalConfirmacion").modal('hide');
        MsgSalidaModalA(response);
           consultaCartera(); 
            

        }
    });
    $("#ModalConfirmacion").modal('hide');  
    
}
        
        
        

function consulta_filtro_cartera(){
document.getElementById("escogecliente").innerHTML="";


  var IdCliente=$("#cartera").val();
          //alert(IdCliente);
    if (parseInt(IdCliente) === 0){
        alert("Debe escoger un cliente");
        document.getElementById("escogecliente").innerHTML="Debe escoger un cliente";
        document.getElementById("escogecliente").style.color="red";
        return;
     
    } 
    var id_campania=document.getElementById("num_campania").value;
    var pagos_ini=$('#pagos').val();
    var pagos_fin=$('#pagos1').val();
    var tvencido_ini=$('#tvencido').val();
    var tvencido_fin=$('#tvencido1').val();
    var vcompromiso_ini=$('#vcompromiso').val();
    var vcompromiso_fin=$('#vcompromiso1').val();
    var saldos_ini=$('#saldos').val();
    var saldos_fin=$('#saldos1').val();
    var dia_mora_ini=$('#dia_mora').val();
    var dia_mora_fin=$('#dia_mora1').val();
    var fUltimo_pago_ini=$('#datetimepicker10').val();
    var fUltimo_pago_fin=$('#datetimepicker11').val();
    var fUltimo_gestion_ini=$('#datetimepicker12').val();
    var fUltimo_gestion_fin=$('#datetimepicker13').val();
    var fUltimo_compromiso_ini=$('#datetimepicker14').val();
    var fUltimo_compromiso_fin=$('#datetimepicker15').val();
    var SelectTipoGestion=$("#tgestion").val();
    var SelectTipoResultado=$("#tresultado_gestion").val();
    var carteras=$('#tcartera').val();
    var subcartera=$('#tsub_cartera').val();
    var segmento=$('#tsegmento').val();
    var subsegmento=$("#tsub_segmento").val();
    var cartera = $("#cartera").val();
    var accion = "nuevaConsulta";
    var order_by=$('#order_by').val();
    var lv_select=" select ";
    var lv_datos=" IDCAMPANIA,s.id_empleado, s.id_datos_deudor, s.id_cliente, s.id_cartera, s.id_sub_cartera, s.id_segmento, s.id_sub_segmento, s.identificacion, s.nombres_completo, s.dias_mora, s.total_vencidos, s.pagos, to_date(s.fecha_ult_pagos,'yyyy-mm-dd hh24:mi:ss'), s.saldo, s.valor_compro, to_date(s.fecha_comp,'yyyy-mm-dd hh24:mi:ss'), to_date(s.fech_ultima_gestion,'yyyy-mm-dd hh24:mi:ss'), s.ultima_gestion, s.resultado_gestion, s.estado   ";
    var lv_from=" from ";
    var lv_filtros=" where s.id_cliente="+IdCliente+"  and s.estado != 'E'";
    var lv_query=" vw_consulta_cartera s  ";
    
    var sqlQuery=lv_select+lv_datos+lv_from+lv_query+lv_filtros;
    
    //
   var fmontos="";
   /*valida critrios de pagos*/
   if(id_campania===""){
       alert("Debe tener un ID_CAMPANIA para seguir con la transacción");
       return;
   }
    if (pagos_ini.length !== 0 && pagos_fin.length !==0  && parseInt(pagos_ini) >= parseInt(pagos_fin)){        alert("El valor de PAGO Inicial debe ser MENOR a la PAGO final para realizar la consulta");        return; }
    if (pagos_ini.length !== 0 && pagos_fin.length !==0  && parseInt(pagos_ini) < parseInt(pagos_fin)){        fmontos+= " AND s.pagos >= "+pagos_ini+" AND s.pagos <= "+pagos_fin;    }
    if (pagos_ini.length !== 0 && pagos_fin.length === 0  && parseInt(pagos_ini) < 0){         alert("El valor en PAGOS ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (pagos_ini.length !== 0 && pagos_fin.length === 0  && parseInt(pagos_ini) > 0){        fmontos+= " AND s.pagos >= "+pagos_ini ;    }
    if (pagos_ini.length === 0 && pagos_fin.length !== 0  && parseInt(pagos_fin) < 0){         alert("El valor ingresdo en PAGOS debe ser mayo a 0 para realizar la consulta");        return;    }
    if (pagos_ini.length === 0 && pagos_fin.length !== 0  && parseInt(pagos_fin) > 0){        fmontos+= " AND s.pagos <= "+pagos_fin ;    }
     /*valida critrios de Total Deuda*/ 
    if (tvencido_ini.length !== 0 && tvencido_fin.length !==0  && parseInt(tvencido_ini) >= parseInt(tvencido_fin)){       alert("El valor de Deudoa Total Inicial debe ser MENOR a la PAGO final para realizar la consulta");        return; }
    if (tvencido_ini.length !== 0 && tvencido_fin.length !==0  && parseInt(tvencido_ini) < parseInt(tvencido_fin)){        fmontos+= " AND s.total_vencidos >= "+tvencido_ini+" AND s.total_vencidos <= "+tvencido_fin;    }
    if (tvencido_ini.length !== 0 && tvencido_fin.length === 0  && parseInt(tvencido_ini) < 0){        alert("El valor en Total Deuda ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (tvencido_ini.length !== 0 && tvencido_fin.length === 0  && parseInt(tvencido_ini) > 0){        fmontos+= " AND s.total_vencidos >= "+tvencido_ini ;    }
    if (tvencido_ini.length === 0 && tvencido_fin.length !== 0  && parseInt(tvencido_fin) < 0){        alert("El valor ingresdo en Total Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (tvencido_ini.length === 0 && tvencido_fin.length !== 0  && parseInt(tvencido_fin) > 0){        fmontos+= " AND s.pagtotal_vencidosos <= "+tvencido_fin ;    }
   
    /*valida critrios de Valor Compromiso*/ 
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length !==0  && parseInt(vcompromiso_ini) >= parseInt(vcompromiso_fin)){       alert("El valor de Compromiso de Pago Inicial debe ser MENOR a  Compromiso de Pago final para realizar la consulta");        return; }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length !==0  && parseInt(vcompromiso_ini) < parseInt(vcompromiso_fin)){        fmontos+= " AND s.valor_compro >= "+vcompromiso_ini+" AND s.valor_compro <= "+vcompromiso_fin;    }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length === 0  && parseInt(vcompromiso_ini) < 0){        alert("El valor en Compromiso de Pago ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (vcompromiso_ini.length !== 0 && vcompromiso_fin.length === 0  && parseInt(vcompromiso_ini) > 0){        fmontos+= " AND s.valor_compro >= "+vcompromiso_ini ;    }
    if (vcompromiso_ini.length === 0 && vcompromiso_fin.length !== 0  && parseInt(vcompromiso_fin) < 0){        alert("El valor Compromiso de Pago Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (vcompromiso_ini.length === 0 && vcompromiso_fin.length !== 0  && parseInt(vcompromiso_fin) > 0){        fmontos+= " AND s.valor_compro <= "+vcompromiso_fin ;    }
  
     /*valida critrios de Saldos*/ 
    if (saldos_ini.length !== 0 && saldos_fin.length !==0  && parseInt(saldos_ini) >= parseInt(saldos_fin)){       alert("El valor de Saldo Inicial debe ser MENOR al Saldo final para realizar la consulta");        return; }
    if (saldos_ini.length !== 0 && saldos_fin.length !==0  && parseInt(saldos_ini) < parseInt(saldos_fin)){        fmontos+= " AND s.saldo >= "+saldos_ini+" AND s.saldo <= "+saldos_fin;    }
    if (saldos_ini.length !== 0 && saldos_fin.length === 0  && parseInt(saldos_ini) < 0){        alert("El valor Saldo ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (saldos_ini.length !== 0 && saldos_fin.length === 0  && parseInt(saldos_ini) > 0){        fmontos+= " AND s.saldo >= "+saldos_ini ;    }
    if (saldos_ini.length === 0 && saldos_fin.length !== 0  && parseInt(saldos_fin) < 0){        alert("El valor Saldo Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (saldos_ini.length === 0 && saldos_fin.length !== 0  && parseInt(saldos_fin) > 0){        fmontos+= " AND s.saldo <= "+saldos_fin ;    }
  
     /*valida critrios de Dias de Mora*/ 
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length !==0  && parseInt(dia_mora_ini) >= parseInt(dia_mora_fin)){       alert("El valor de Dias Mora Inicial debe ser MENOR a Dias Mora final para realizar la consulta");        return; }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length !==0  && parseInt(dia_mora_ini) < parseInt(dia_mora_fin)){        fmontos+= " AND s.dias_mora >= "+dia_mora_ini+" AND s.dias_mora <= "+dia_mora_fin;    }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length === 0  && parseInt(dia_mora_ini) < 0){        alert("El valor Dias Mora ingresdo debe ser mayor a 0 para realizar la consulta.");        return;   }
    if (dia_mora_ini.length !== 0 && dia_mora_fin.length === 0  && parseInt(dia_mora_ini) > 0){        fmontos+= " AND s.dias_mora >= "+dia_mora_ini ;    }
    if (dia_mora_ini.length === 0 && dia_mora_fin.length !== 0  && parseInt(dia_mora_fin) < 0){        alert("El valor Dias Mora Deuda debe ser mayor a 0 para realizar la consulta");        return;    }
    if (dia_mora_ini.length === 0 && dia_mora_fin.length !== 0  && parseInt(dia_mora_fin) > 0){        fmontos+= " AND s.dias_mora <= "+dia_mora_fin ;    }
  
     /*valida critrios de Fecha Ultimo Pago*/ 
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length !==0  && Date.parse(fUltimo_pago_ini) >= Date.parse(fUltimo_pago_fin)){       alert("La fecha de Ultimo Pago Inicial debe ser MENOR a la fecha de Ultimo Pago final para realizar la consulta");        return; }
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length !==0  && Date.parse(fUltimo_pago_ini) < Date.parse(fUltimo_pago_fin)){        fmontos+= " AND s.fecha_ult_pagos >= '"+fUltimo_pago_ini+"' AND s.fecha_ult_pagos <= '"+fUltimo_pago_fin+"' ";    }
    if (fUltimo_pago_ini.length !== 0 && fUltimo_pago_fin.length === 0 ){ fmontos+= " AND s.fecha_ult_pagos >= '"+fUltimo_pago_ini+"' ";     }
    if (fUltimo_pago_ini.length === 0 && fUltimo_pago_fin.length !== 0 ){ fmontos+= " AND s.fecha_ult_pagos <= '"+fUltimo_pago_fin+"' ";    }
  
    /*valida critrios de Fecha Ultima Gestión*/ 
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length !==0  && Date.parse(fUltimo_gestion_ini) >= Date.parse(fUltimo_gestion_fin)){       alert("La fecha de Ultimo Gestión Inicial debe ser MENOR a la fecha de Ultimo Gestión final para realizar la consulta");        return; }
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length !==0  && Date.parse(fUltimo_gestion_ini) < Date.parse(fUltimo_gestion_fin)){        fmontos+= " AND s.fech_ultima_gestion >= '"+fUltimo_pago_ini+"' AND s.fech_ultima_gestion <= '"+fUltimo_gestion_fin+"' ";    }
    if (fUltimo_gestion_ini.length !== 0 && fUltimo_gestion_fin.length === 0 ){ fmontos+= " AND s.fech_ultima_gestion >= '"+fUltimo_gestion_ini+"' ";     }
    if (fUltimo_gestion_ini.length === 0 && fUltimo_gestion_fin.length !== 0 ){ fmontos+= " AND s.fech_ultima_gestion <= '"+fUltimo_gestion_fin+"' ";    }
  
    /*valida critrios de Fecha Ultima Compromiso*/ 
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length !==0  && Date.parse(fUltimo_compromiso_ini) >= Date.parse(fUltimo_compromiso_fin)){       alert("La fecha de Compromiso inicial debe ser MENOR a la fecha de Compromiso final para realizar la consulta");        return; }
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length !==0  && Date.parse(fUltimo_compromiso_ini) < Date.parse(fUltimo_compromiso_fin)){        fmontos+= " AND s.fecha_comp >='"+fUltimo_compromiso_ini+"' AND s.fecha_comp <= '"+fUltimo_compromiso_fin+"' ";    }
    if (fUltimo_compromiso_ini.length !== 0 && fUltimo_compromiso_fin.length === 0 ){ fmontos+= " AND s.fecha_comp >= '"+fUltimo_compromiso_ini+"'";     }
    if (fUltimo_compromiso_ini.length === 0 && fUltimo_compromiso_fin.length !== 0 ){ fmontos+= " AND s.fecha_comp <= '"+fUltimo_compromiso_fin+"' ";    }
  
//  alert(SelectTipoGestion);
//  return;
    if (SelectTipoGestion!== "0"){
       fmontos+= " AND s.ultima_gestion = '"+$('#tgestion').find('option:selected').text()+"'";         
    }  
    if (SelectTipoResultado!== "0"){
       fmontos+= " AND s.resultado_gestion = '"+$('#tresultado_gestion').find('option:selected').text()+"'";      
    } 
    if ((carteras!== "0")&&(carteras!== null)){
        
       fmontos+= " AND s.id_cartera = "+$('#tcartera').find('option:selected').val();         
    }
    if ((subcartera!== "0")&&(subcartera!==null)){
       
       fmontos+= " AND s.id_sub_cartera = "+$('#tsub_cartera').find('option:selected').val();         
    }
    if ((segmento!== "0")&&(segmento!==null)){
        
       fmontos+= " AND s.id_segmento = "+$('#tsegmento').find('option:selected').val();         
    }
    if ((subsegmento!== "0")&&(subsegmento!== null)){
        
       fmontos+= " AND s.id_sub_segmento = "+$('#tsub_segmento').find('option:selected').val();         
    }
    if (order_by!==""){
        
       order_by= " ORDER BY s."+order_by+" DESC";
    }
    
       $('#id_loader').css("display", "block");
       //arma el query para la consula
    sqlQuery=sqlQuery+fmontos;

    

// document.getElementById("input_query").value = "";
    //document.getElementById("input_query").value = sqlQuery;
    console.log("sqlQuery: "+sqlQuery);
    
    
     $.ajax({
        data:{"accion":"agregar_detalle_campania", "id_campania":id_campania,"sqlQuery":sqlQuery},
        url: "campanias",
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            
          console.log("okokoko: "+response);  
          consultaCartera();
          consulta_datos_campania();

        }
    });
    
    return;
    
   //var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left hidden' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' onclick='orderIdent()'>Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' onclick='orderNombre()'>Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID' onclick='orderDiasMora()' >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID' onclick='orderTotalVenc()' >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' onclick='orderPagos()'>Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' onclick='orderFechaUltPagos()'>Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' onclick='orderSaldo()'>Saldo</a></th><th align='center' class='col-sm-1 text-right'><a id='ValorCompID' onclick='orderValorComp()'>Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' onclick='orderFechaComp()'>Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' onclick='orderFchGestion()' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' onclick='orderUltima()'>Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' onclick='orderResultado()'>Resultado Gestión</a></th></tr> </thead><tbody>";
     var parametros = {
        "sqlQuery":sqlQuery,
        "cartera": cartera,
        "accion": accion
    };
     document.getElementById("tabla_div").innerHTML = "";
    var htmlTable="<table id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' >Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' >Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID'  >Días Mora</a></th> <th class='col-sm-1 text-right'><a id='TotalID'  >Total Vnc</a></th> <th align='center' class='col-sm-1 text-right'><a id='PagosID' >Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' >Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' >Saldo</a></th> <th align='center' class='col-sm-1 text-right'><a id='ValorCompID' >Valor Comp.</a></th> <th align='center' class='col-sm-2 text-center'><a id='FechaCompID' >Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' >Ult. Gestión</a></th> <th align='center' class='col-sm-2'><a id='ResultadoID' >Resultado Gestión</a></th></tr> </thead><tbody></tbody></table>";
    document.getElementById("tabla_div").innerHTML = htmlTable;
    
    $('#consul_cartera').DataTable( {
        "ajax": {   
            "data": {"accion": accion,"sqlQuery": sqlQuery,"cartera": cartera},
            "url": "consultacartera",
            "type": "GET"
            },
            "columns": [
                { "data": "id_datos_deudor","title":"ID", "visible": false },
                { "data": "identificacion" },
                { "data": "nombres_completo2" },
                { "data": "dias_mora" },
                { "data": "total_vencidos" },
                { "data": "pagos" },
                { "data": "fecha_ult_pagos" },
                { "data": "saldo" },
                { "data": "valor_compro" },
                { "data": "fecha_comp" },
                { "data": "fech_ultima_gestion" },
                { "data": "ultima_gestion" },
                { "data": "resultado_gestion" }
            ],
            paging: false
    } );
    $('#id_loader').css("display", "none");
    $('#det_filtro').modal('hide');   

   consulta_sec(sqlQuery,cartera);
    
table = $('#consul_cartera').DataTable();

$('#consul_cartera thead').on('click', 'th', function () {
 var index = table.column(this).index();
console.log('consulta de columna: '+index);
     switch (index) {
    case 1:
        orderIdent();
        break;
    case 2:
        orderNombre();
        break;
    case 3:
        orderDiasMora();
        break;
    case 4:
        orderTotalVenc();
        break;
    case 5:
        orderPagos();
        break;
    case 6:
        orderFechaUltPagos();
        break;
    case 7:
        orderSaldo();
        break;
    case 8:
        orderValorComp();
        break;
    case 9:
        orderFechaComp();
        break;
    case 10:
        orderFchGestion();
        break;
    case 11:
        orderUltima();
        break;
    case 12:
        orderResultado();
        break;    
}

   
    $('#id_loader').css("display", "none");
 // alert('index : '+index);
});

    // alert(sqlQuery); 
    
    var qSum=lv_select+" sum(s.total_vencidos) as total_vencidos, sum(s.pagos) as pagos, (sum(s.total_vencidos) -  sum(pagos)) as saldo "+lv_from+lv_query+lv_filtros+fmontos+order_by;
    console.log(qSum);
    Totales_Suman(qSum, IdCliente);
} 



function consultaQuery(num_campania){
  
document.getElementById("sqlQueryInput").value="";
document.getElementById("order_by").value="";
document.getElementById("sqlQueryInput").value="select * from lc_det_campanias where id_campanias= "+num_campania+" and estado = 'A' and asignacion='P' ";
document.getElementById("order_by").value="order by total_vencidos";

}
all_empleados();
function all_empleados(){
    
    
  document.getElementById("DivTbEmpelados").innerHTML = "";
  var htmlTable="<table id='all_empleados' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a>Empleado</a></th><th class='col-sm-2 text-left'><a>Acción</a></th></tr> </thead><tbody></tbody></table>";
  document.getElementById("DivTbEmpelados").innerHTML = htmlTable; 
    
    
 var table= $('#all_empleados').DataTable( {
        "ajax": {
            "data": {"accion": "ConsultaAllEmpleados"},
            "url": "asignacioncartera",
            "type": "GET"
            },
            "columns": [
                { "data": "id_empleado","title":"ID"},
                { "data": "empleado" },
                { "defaultContent":"<center><button type='button' title='agregar' class='agregar btn btn-sm btn-success'><i class='glyphicon glyphicon-share-alt'></i></button></center>"} 
            ],
            paging: false,
            "pageLength": 100,"language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
    } );
    $('#all_empleados tbody').on( 'click', 'button.agregar', function () {
          var data = table.row( $(this).parents('tr') ).data();
            //EliminarMaestrosEdi(data.id_edi_ing);
          //  alert(data.id_campania);
          
            var id_campania=document.getElementById("num_campania").value;
            //ConfirmaModalAgregarEmpleado(id_campania,data.id_empleado,data.empleado ); 
            console.log(id_campania+data.id_empleado+data.empleado);
            ConfirmaModalAgregarEmpleado(id_campania,data.id_empleado,data.empleado); 
            
            $("#ModalConfirmacion").modal('show');
         } );

}



function agregar_empleado_campania(id_campania,id_empleado,nombres){
    
      $.ajax({
        data:{"accion":"agregar_empleado_campania", "id_campania":id_campania,"id_empleado":id_empleado,"nombres":nombres},
        url: "campanias",
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
            
          console.log("okokoko: "+response);  
          $("#ModalConfirmacion").modal('hide');
        MsgSalidaModalA(response);
                 all_empleados_campanias(); 
                 consulta_datos_campania();

        }
    });
      
}

function all_empleados_campanias(){
     var id_campania=document.getElementById("num_campania").value;
    
  document.getElementById("DivTbEmpeladosCampanias").innerHTML = "";
  var htmlTable="<table id='empleado_campania' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a>Empleado</a></th><th class='col-sm-2 text-left'><a>Accion</a></th></tr> </thead><tbody></tbody></table>";
  document.getElementById("DivTbEmpeladosCampanias").innerHTML = htmlTable; 
    
    
 var table= $('#empleado_campania').DataTable( {
        "ajax": {
            "data": {"accion": "all_empleado_campania","id_campania":id_campania},
            "url": "campanias",
            "type": "GET"
            },
            "columns": [
                { "data": "id_empleado","title":"ID"},
                { "data": "nombre_empleado" },
                { "defaultContent":"<center><button type='button' title='Eliminar' class='eliminar btn btn-sm btn-success'><i class='glyphicon glyphicon-minus'></i></button></center>"} 
            ],
            paging: false,
            "pageLength": 100,"language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
    } );
    $('#empleado_campania tbody').on( 'click', 'button.eliminar', function () {
          var data = table.row( $(this).parents('tr') ).data();
            //EliminarMaestrosEdi(data.id_edi_ing);
          //  alert(data.id_campania);
          
              
              eliminar_empleado_campania(data.id_empleado_campania);
         } );

}

//eliminar_empleado_campania
function eliminar_empleado_campania(id_empleado_campania){
    
      $.ajax({
        data:{"accion":"eliminar_empleado_campania", "id_empleado_campania":id_empleado_campania},
        url: "campanias",
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
       $("#ModalConfirmacion").modal('hide');
        MsgSalidaModalA(response);
           all_empleados_campanias(); 
            

        }
    });

    
}

function procesar_asignación(){
    var id_campania=document.getElementById("num_campania").value;
     var sqlQueryInput=document.getElementById("sqlQueryInput").value;
      var order_by=document.getElementById("order_by").value;
    //alert(id_campania+" ->> "+sqlQueryInput+" "+order_by);
    var query=sqlQueryInput+order_by+'  limit 1 ';
     $('#id_loader').css("display", "block");
    $.ajax({
        data:{"accion":"procesar_asignación", "id_campania":id_campania,"query":query},
        url: "campanias",
        type: 'GET',
        beforeSend: function () {
        },
        success: function (response) {
       $("#ModalConfirmacion").modal('hide');
        MsgSalidaModalA(response);
         //  all_empleados_campanias(); 
           $('#id_loader').css("display", "none");
            

        }
    });
    
}

function all_empleados_asig_campanias(){
     var id_campania=document.getElementById("num_campania").value;
    
  document.getElementById("DivTbEmpeladosAsigCampanias").innerHTML = "";
  var htmlTable="<table id='empleado_asig_campania' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left ' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a>Empleado</a></th><th class='col-sm-2 text-right'><a>Total</a></th></tr> </thead><tbody></tbody></table>";
  document.getElementById("DivTbEmpeladosAsigCampanias").innerHTML = htmlTable; 
    
    
 var table= $('#empleado_asig_campania').DataTable( {
        "ajax": {
            "data": {"accion": "all_empleado_asig_campania","id_campania":id_campania},
            "url": "campanias",
            "type": "GET"
            },
            "columns": [
                { "data": "id_empleado","title":"ID"},
                { "data": "nombre_empleado" },
                { "data": "total" }
                
            ],
            paging: false,
            
            "pageLength": 100,"language": {
    				"emptyTable":			"No hay datos disponibles en la tabla.",
    				"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
    				"infoEmpty":			"Mostrando 0 registros de un total de 0.",
    				"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
    				"infoPostFix":			"(actualizados)",
    				"lengthMenu":			"Mostrar _MENU_ registros",
    				"loadingRecords":		"Cargando...",
    				"processing":			"Procesando...",
    				"search":			"Buscar:",
    				"searchPlaceholder":		"Dato para buscar",
    				"zeroRecords":			"No se han encontrado coincidencias.",
    				"paginate": {
    					"first":			"Primera", 
    					"last":				"Última",
    					"next":				"Siguiente",
    					"previous":			"Anterior"
    				},"aria": {
    					"sortAscending":	"Ordenación ascendente",
    					"sortDescending":	"Ordenación descendente"
    				}
    			}
    } );
   

}

function consulta_datos_campania(){
    var id_campania=document.getElementById("num_campania").value;
    
      $.getJSON("campanias", {"accion" : "ConsultaDatosCampania","id_campania":id_campania}, function(result){
         //console.log(result);
          $.each(result.datos, function(key, val){             
              document.getElementById("num_empleado").value  ="";
              document.getElementById("num_deudores").value  ="";
              document.getElementById("tVenvidos").value  ="";
              document.getElementById("tPagados").value  ="";
              document.getElementById("tSaldos").value  ="";
              document.getElementById("cant_proporcion").value  ="";
              
           
              document.getElementById("num_empleado").value  =val.num_empleado;
              document.getElementById("num_deudores").value  =val.canti_deudores;
              document.getElementById("tVenvidos").value  =val.total_vencidos;
              document.getElementById("tPagados").value  =val.pagos;
              document.getElementById("tSaldos").value  =val.saldo;
              document.getElementById("cant_proporcion").value  =val.proporcion;
              
            
            
            
          });
    });
    
}