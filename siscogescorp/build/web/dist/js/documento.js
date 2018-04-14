function crear_cartas()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("documentos?accion=crear",{},function(){ });
 
}
function Obtener_combos(){
    obtenerSucursal();
    obtenerCartera();
}
function obtenerSucursal(){
   var empresa  = $("#empresa").val(); 
   
    if (empresa !== ""){
        $.post("sistema/terrenos/combo_sucursal.jsp",$("#data").serialize(),function(data){$("#sucursal").html(data);});
        document.getElementById("sucursal").disabled = false;
    }
} 
function obtenerCartera(){
   var empresa  = $("#empresa").val(); 
   
    if (empresa !== ""){
        $.post("sistema/terrenos/combo_cartera.jsp",$("#data").serialize(),function(data){$("#cliente").html(data);});
        document.getElementById("cliente").disabled = false;
    }
} 
function Guardar_documento(){
var empresa = $('#empresa').val(); 
var sucursal = $('#sucursal').val();
var cartera = $('#cliente').val();
var nombre_doc = $('#nombre_documento').val();
var saludo = $('#saludo').val();
var cuerpo = $('#cuerpo').val();
var despedida = $('#despedida').val();
var firma = $('#firma').val();
var accion = "GuardaDoc";    

if(empresa!=""){
    if(sucursal!=""){
        if(cartera!=""){
            if(nombre_doc!=""){
                if(saludo!=""){
                    if(cuerpo!=""){
                        if(despedida!=""){
                            if(firma!=""){
                                var parametros = {
                                    "sucursal": sucursal,
                                    "cartera": cartera,
                                    "empresa": empresa,
                                    "nombre_doc": nombre_doc,
                                    "saludo": saludo,
                                    "cuerpo": cuerpo,
                                    "despedida": despedida,
                                    "firma": firma,
                                    "accion": accion
                            };    
                                    $.ajax({
                                    data: parametros,
                                    url: 'documentos',
                                    type: 'GET',
                                            beforeSend: function () {
                                            },
                                            success: function (response) {

                                            if (response) {
                                                MsgSalidaModalM(response);
                                                crear_cartas();
                                            }
                                        }
                                    }); 
                            }else{MsgSalidaModalM("Debe Ingresar la firma del Documento");}
                        }else{MsgSalidaModalM("Debe Ingresar la despedida del Documento");}
                    }else{MsgSalidaModalM("Debe Ingresar el cuerpo del Documento");}
                }else{MsgSalidaModalM("Debe Ingresar un saludo del Documento");}
            }else{MsgSalidaModalM("Debe Ingresar el nombre del Documento");}
        }else{MsgSalidaModalM("Debe seleccionar una Cartera");}
    }else{MsgSalidaModalM("Debe seleccionar una Sucursal");}
}else{MsgSalidaModalM("Debe seleccionar una Empresa");}
    
        
} 
function Act_cartas(data)
{

     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
        jQuery("#page-wrapper").load("documentos?accion=crear",{},function(){
            document.getElementById("ingresoDoc").style.display = 'none';
            document.getElementById("datosmodifica").style.display = 'block';
            //document.getElementById("EditarDoc").style.display = 'block';
            document.getElementById("EditarDoc").innerHTML="";
            document.getElementById("EditarDoc").innerHTML=data;
                var idEmpresa=$('#EmpresaAct').val();
                var idAgencia = $('#AgenciaAct').val(); 
                var idCartera = $('#ClienteAct').val();
                document.getElementById("sucursal").disabled = false;
                document.getElementById("cliente").disabled = false;
                var e = document.getElementById("empresa");
                //e.onchange();  datosmodifica
                e.value = idEmpresa;
                var s = document.getElementById("sucursal");
//                s.disabled=false;
                s.value = idAgencia;
                var c = document.getElementById("cliente");
                //c.disabled=false;
                c.value = idCartera;
        });
 
}
function EditarDoc(IdDocumento){
    var idDoc=IdDocumento;
    var accion = "ObtenerData"; 
    var parametros = {
                "idDoc": idDoc,
                "accion": accion
        };    
                $.ajax({
                data: parametros,
                url: 'documentos',
                type: 'GET',
                        beforeSend: function () {
                        },
                        success: function (response) {

                        if (response) {
                            Act_cartas(response);
                            
                        }
                    }
                }); 
}
function ActualizarDoc(){
    var idDoc=$('#idDocAct').val();
    var empresa = $('#empresa').val(); 
    var sucursal = $('#sucursal').val();
    var cartera = $('#cliente').val();
    var nombre_doc = $('#nombre_documentoAct').val();
    var saludo = $('#saludoAct').val();
    var cuerpo = $('#cuerpoAct').val();
    var despedida = $('#despedidaAct').val();
    var firma = $('#firmaAct').val();
    var accion="ActualizarDoc";
    
    if(empresa!=""){
    if(sucursal!=""){
        if(cartera!=""){
            if(nombre_doc!=""){
                if(saludo!=""){
                    if(cuerpo!=""){
                        if(despedida!=""){
                            if(firma!=""){
                                var parametros = {
                                    "idDoc": idDoc,
                                    "sucursal": sucursal,
                                    "cartera": cartera,
                                    "empresa": empresa,
                                    "nombre_doc": nombre_doc,
                                    "saludo": saludo,
                                    "cuerpo": cuerpo,
                                    "despedida": despedida,
                                    "firma": firma,
                                    "accion": accion
                            };    
                                    $.ajax({
                                    data: parametros,
                                    url: 'documentos',
                                    type: 'GET',
                                            beforeSend: function () {
                                            },
                                            success: function (response) {

                                            if (response) {
                                                MsgSalidaModalM(response);
                                            }
                                        }
                                    }); 
                            }else{MsgSalidaModalM("Debe Ingresar la firma del Documento");}
                        }else{MsgSalidaModalM("Debe Ingresar la despedida del Documento");}
                    }else{MsgSalidaModalM("Debe Ingresar el cuerpo del Documento");}
                }else{MsgSalidaModalM("Debe Ingresar un saludo del Documento");}
            }else{MsgSalidaModalM("Debe Ingresar el nombre del Documento");}
        }else{MsgSalidaModalM("Debe seleccionar una Cartera");}
    }else{MsgSalidaModalM("Debe seleccionar una Sucursal");}
}else{MsgSalidaModalM("Debe seleccionar una Empresa");}
}

function ConsultaClientes(){
document.getElementById("cartera").innerHTML="";
//  $("#cartera").append($("<option>",{value:"0",text:"Seleccione el cliente"}));
     $.getJSON("consultacartera", {"accion" : "Clientes"}, function(result){
          $.each(result.listaClientes, function(key, val){   
           $("#cartera").append($("<option>",{value:val.id_cliente,text:val.razon_social}));
           var valor_select = val.razon_social;
          // alert(valor_select);
       
          });
    });  
    
}
function getMisTiposGestiones(){
    
document.getElementById("tgestion").innerHTML="";
document.getElementById("gestion").innerHTML="";
$("#tgestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
$("#gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
    $.getJSON("consultacartera", {"accion" : "TiposGestiones"}, function(result){
          $.each(result.tipo_gestion, function(key, val){             
          // $("#tgestion").append('<option id="' + val.idTipoGestion + '">' + val.nombreTipoGestion +'</option>');
           $("#tgestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
           $("#gestion").append($("<option>",{value:val.idTipoGestion,text:val.nombreTipoGestion}));
          });
    });
    
   document.getElementById("tresultado_gestion").disabled=true;
   $("#tresultado_gestion").append($("<option>",{value:"0",text:"Seleccione Tipo Gestión"}));
}

function ConsultaMisTipoResultado(){
  var IdTipoGestion=$("#tgestion").val();
  var idcliente="";
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
     $.getJSON("consultacartera", {"accion" : "MisTiposResulatdos","idcliente":idcliente}, function(result){
         console.log(result);
         console.log('size: '+result.tipos_resultado.tipos_resultado);
          $.each(result.tipos_resultado, function(key, val){             
           $("#tresultado_gestion").append($("<option>",{value:val.id_tipo_resultado,text:val.nombre_tipo_resultado}));
          });
    });
  }
    
}


function consulta_filtros_carteras(){
document.getElementById("escogecliente").innerHTML="";
  var IdCliente=$("#cartera").val();
          //alert(IdCliente);
    if (parseInt(IdCliente) === 0){
        alert("Debe escoger un cliente");
        document.getElementById("escogecliente").innerHTML="Debe escoger un cliente";
        document.getElementById("escogecliente").style.color="red";
        return;
     
    } 
    
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
    var cartera = $("#cartera").val();
    var accion = "nuevaConsulta";
    var order_by=$('#order_by').val();
    //var sqlQuery=" select * from vw_consulta_cartera s  where s.id_cliente=IDClienteConsulta and s.id_empleado=IDEmpleadoConsulta and s.estado != 'E'";
    var sqlQuery=" select * from vw_consulta_clientes s  where s.id_cliente=IDClienteConsulta  and s.estado != 'E'";

    //
   var fmontos="";
   /*valida critrios de pagos*/
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
    
    if (order_by!==""){
        
       order_by= " ORDER BY s."+order_by+" DESC";
    }
    
       $('#id_loader').css("display", "block");
       //arma el query para la consula
    sqlQuery=sqlQuery+fmontos+order_by;
    document.getElementById("input_query").value = "";
    document.getElementById("input_query").value = sqlQuery;
   // alert(sqlQuery);
   
   var StringTablaCabecera="<table name='consul_cartera' id='consul_cartera' class='table table-striped table-bordered dt-responsive nowrap table-hover' cellspacing='0' width='100%'><thead><tr bgcolor='#FBF5EF'><th class='col-sm-1 text-left hidden' style='color: #3c8dbc'>ID</th><th align='left' class='col-sm-1 text-left'><a id='checkbox'>checkbox</a></th><th align='left' class='col-sm-1 text-left'><a id='IdentificacionID' onclick='orderIdent()'>Identificación</a></th><th class='col-sm-2 text-left'><a id='NombresID' onclick='orderNombre()'>Nombres</a></th>  <th class='col-sm-1 text-left'><a id='DiasMoraID' onclick='orderDiasMora()' >Días Mora</a></th><th class='col-sm-1 text-right'><a id='TotalID' onclick='orderTotalVenc()' >Total Vnc</a></th><th align='center' class='col-sm-1 text-right'><a id='PagosID' onclick='orderPagos()'>Pagos</a></th><th align='center' class='col-sm-1 text-right'><a id='FecUltPagosID' onclick='orderFechaUltPagos()'>Fecha Ult. Pagos</a></th><th align='rigth' class='col-sm-1 text-right'><a id='SaldosID' onclick='orderSaldo()'>Saldo</a></th><th align='center' class='col-sm-1 text-right'><a id='ValorCompID' onclick='orderValorComp()'>Valor Comp.</a></th><th align='center' class='col-sm-2 text-center'><a id='FechaCompID' onclick='orderFechaComp()'>Fecha Comp.</a></th><th align='center' class='col-sm-3'><a id='FechaID' onclick='orderFchGestion()' >Fecha Ult. Gestión</a></th> <th align='center' class='col-sm-3'><a id='UltimaID' onclick='orderUltima()'>Ult. Gestión</a></th>			<th align='center' class='col-sm-2'><a id='ResultadoID' onclick='orderResultado()'>Resultado Gestión</a></th>		</tr> 	</thead>	<tbody>	</tbody></table>";

    document.getElementById("tabla_div").innerHTML  =""; 
    document.getElementById("tabla_div").innerHTML  =StringTablaCabecera;
      var parametros = {
        "sqlQuery":sqlQuery,
        "cartera": cartera,
        "accion": accion
    };
      
    $(document).ready(function() {	
     //var parametros = {"accion": accion,"idCliente": idCliente,"idDeudor": idDeudor};<input type="checkbox" id="checkTodos" />
    $('#consul_cartera').DataTable( {
        "ajax": {
            "data": {"accion": accion,"sqlQuery": sqlQuery,"cartera": cartera},
            "url": "documentos",
            "type": "GET"
            },
            "columns": [
                { "data": "id_datos_deudor","title":"ID", "visible": false },
                { "data": "checkbox"  },
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
            paging:         false
    } );
 
  });      
 
         $('#id_loader').css("display", "none");  
    $('#det_filtro').modal('hide');  
    
    
   //consulta_sec(sqlQuery,cartera);
 
}

function ConsultaTicket(){
    var accion = "new_ticket";
    
    var parametros = {
            "accion": accion
          
        };
        $.ajax({
            data: parametros,
            url: 'documentos',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                document.getElementById("ticket").innerHTML="";
                document.getElementById("ticket").value=response;
                
                
            }
        });
    
}

function registra_trama(trama,ticket,idCliente,IDDocumento){
    
      var accion = "registra_trama";
    
    var parametros = {
            "accion": accion,
            "trama": trama,
            "ticket":ticket
        };
        $.ajax({
            data: parametros,
            url: 'documentos',
            type: 'GET',
            beforeSend: function () {
            },
            success: function (response) {  
                MsgSalidaModalA("ticket: "+response);
                console.log("ticket: "+response);
                
                window.open("generardocumentos?accion=genera_doc_masivos&ticket="+ticket+"&IDDocumento="+IDDocumento);
                
            }
        });
    
    
}
function VisualizarDoc(IdDocumento,idcliente){
    var idDoc=IdDocumento;
    var idCliente = idcliente;
    var accion = "VerDoc"; 
    //alert();
    window.open("documentos?accion=VerDoc&idDoc="+idDoc+"&idCliente="+idCliente);

}
function  genera_reporte(ticket,idCliente,IDDocumento){
    console.log("Cliente: "+idCliente);
     window.open("generardocumentos?accion=genera_doc_masivos&ticket="+ticket+"&IDDocumento="+IDDocumento);   
}

function listar_documentos()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("documentos?accion=listar",{},function(){ });
 
}
function EliminarDoc(data){
    if (confirm("Realmente desea eliminar los datos")) {
        jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>");
        jQuery("#page-wrapper").load("documentos?accion=eliminar&id=" + data, {}, function () { });
        listar_documentos();
    }
}