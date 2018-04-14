/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ValidaEmailOk() {
    
  var mail = $("#mail").val();
  valido = document.getElementById('emailOK');
    //valMail=document.getElementById('mail').value;
  emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
  //alert(mail);
    if (emailRegex.test(mail)) {
        valido.innerText = "válido";
        valido.style.color="#31B404";

    } else {
       valido.innerText = "incorrecto";
       valido.style.color="#f00";
    }  
}
    
    function ValidaEmailOk2() {
    
  var mail = $("#email").val();
  valido = document.getElementById('emailOK');
    //valMail=document.getElementById('mail').value;
  emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
  //alert(mail);
    if (emailRegex.test(mail)) {
        valido.innerText = "válido";
        valido.style.color="#31B404";

    } else {
       valido.innerText = "incorrecto";
       valido.style.color="#f00";
    }  
}