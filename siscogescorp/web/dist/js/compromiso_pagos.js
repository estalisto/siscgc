function compropago()
{
     jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
     jQuery("#page-wrapper").load("compromisos_pagos?accion=listar",{},function(){ });
 }
 

$('#datepicker').datetimepicker({   format:'Y-m-d' }); 
$('#datepicker2').datetimepicker({   format:'Y-m-d' }); 
$('#buscarCompromisos').click(function(e){
        e.preventDefault();
        var fecha_inicio=$("#datepicker").val();
        var fecha_fin=$("#datepicker2").val();
        var accion="consultar";
             // MsgSalidaModalM("Debe ingresar la Fecha de inicio de la consulta");  
       if (fecha_inicio==""){
          MsgSalidaModalA("Debe ingresar la Fecha de inicio de la consulta");  
          return false;
       }
         if (fecha_fin==""){
          MsgSalidaModalA("Debe ingresar la Fecha de fin de la consulta");  
          return false;
       }
jQuery("#page-wrapper").html("<br/><br/><center><img alt='cargando' src='dist/img/hourglass.gif' /><center>"); 
jQuery("#page-wrapper").load("compromisos_pagos?accion=consultar&fecha_inicio="+fecha_inicio+"&fecha_fin="+fecha_fin,{},function(){ });
      
         

      });
    

    
