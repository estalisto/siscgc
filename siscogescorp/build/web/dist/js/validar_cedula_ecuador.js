
function valida_identificacion(){
  
     //var cedula = '0931811087';
     var cedula = $("#identificacion").val();
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
//            alert(digito);
//            alert(dig_ver);
        if ($.trim(digito) === $.trim(dig_ver)) {
            
            //alert("cédula válida");
            document.getElementById("btncrearempresa").disabled = false;
        } else {
           
            alert("cédula no válida");
        
        }
        //Obtenemos el digito de la region que sonlos dos primeros digitos
//        var digito_region = cedula.substring(0,2);
//        
//        //Pregunto si la region existe ecuador se divide en 24 regiones
//        if( digito_region >= 1 && digito_region <=24 ){
//          
//          // Extraigo el ultimo digito
//          var ultimo_digito   = cedula.substring(9,10);
//
//          //Agrupo todos los pares y los sumo
//          var pares = parseInt(cedula.substring(1,2)) + parseInt(cedula.substring(3,4)) + parseInt(cedula.substring(5,6)) + parseInt(cedula.substring(7,8));
//
//          //Agrupo los impares, los multiplico por un factor de 2, si la resultante es > que 9 le restamos el 9 a la resultante
//          var numero1 = cedula.substring(0,1);
//          var numero1 = (numero1 * 2);
//          if( numero1 > 9 ){ var numero1 = (numero1 - 9); }
//
//          var numero3 = cedula.substring(2,3);
//          var numero3 = (numero3 * 2);
//          if( numero3 > 9 ){ var numero3 = (numero3 - 9); }
//
//          var numero5 = cedula.substring(4,5);
//          var numero5 = (numero5 * 2);
//          if( numero5 > 9 ){ var numero5 = (numero5 - 9); }
//
//          var numero7 = cedula.substring(6,7);
//          var numero7 = (numero7 * 2);
//          if( numero7 > 9 ){ var numero7 = (numero7 - 9); }
//
//          var numero9 = cedula.substring(8,9);
//          var numero9 = (numero9 * 2);
//          if( numero9 > 9 ){ var numero9 = (numero9 - 9); }
//
//          var impares = numero1 + numero3 + numero5 + numero7 + numero9;
//
//          //Suma total
//          var suma_total = (pares + impares);
//
//          //extraemos el primero digito
//          var primer_digito_suma = String(suma_total).substring(0,1);
//
//          //Obtenemos la decena inmediata
//          var decena = (parseInt(primer_digito_suma) + 1)  * 10;
//
//          //Obtenemos la resta de la decena inmediata - la suma_total esto nos da el digito validador
//          var digito_validador = decena - suma_total;
//
//          //Si el digito validador es = a 10 toma el valor de 0
//          if(digito_validador === 10)
//            var digito_validador = 0;
//            
//          //Validamos que el digito validador sea igual al de la cedula
//          if(digito_validador === ultimo_digito){
//            alert('la cedula:' + cedula + ' es correcta');
//          }else{
//            alert('la cedula:' + cedula + ' es incorrecta');
//          }
//          
//        }else{
//          // imprimimos en consola si la region no pertenece
//          alert('Esta cedula no pertenece a ninguna region');
//        }
     }
//     else{
//        //imprimimos en consola si la cedula tiene mas o menos de 10 digitos
//        alert('Esta cedula tiene menos de 10 Digitos');
//     }    
     
//     if(cedula.length === 13){
//          var dto = cedula.length;
//            var valor;
//              var acu=0;
//            for (var i = 0; i < dto; i++) {
//                valor = cedula.substring(i, i + 1);
//                
//                if (valor === 0 || valor === 1 || valor === 2 || valor === 3 || valor === 4 || valor === 5 || valor === 6 || valor === 7 || valor === 8 || valor === 9) {
//                    acu = acu + 1;
//                    alert(acu);
//                }
//            }
////            alert(acu);
////            alert(dto);
//            if (acu !== dto) {
//                while ($.trim(cedula.substring(10, 13)) !== 001) {
//                    alert('Los tres últimos dígitos no tienen el código del RUC 001.');
//                    return;
//                }
//                while (cedula.substring(0, 2) > 24) {
//                    alert('Los dos primeros dígitos no pueden ser mayores a 24.');
//                    return;
//                }
//                alert('El RUC está escrito correctamente');
//                alert('Se procederá a analizar el respectivo RUC.');
//                var porcion1 = cedula.substring(2, 3);
//                if (porcion1 < 6) {
//                    alert('El tercer dígito es menor a 6, por lo \ntanto el usuario es una persona natural.\n');
//                } else {
//                    if (porcion1 === 6) {
//                        alert('El tercer dígito es igual a 6, por lo \ntanto el usuario es una entidad pública.\n');
//                    } else {
//                        if (porcion1 === 9) {
//                            alert('El tercer dígito es igual a 9, por lo \ntanto el usuario es una sociedad privada.\n');
//                        }
//                    }
//                }
//            }
//        
//    }

     if(cedula.length !== 10 && cedula.length !== 13){
         alert("Numero de identificación incorrecto");
         document.getElementById("btncrearempresa").disabled = true; 
     }
}
