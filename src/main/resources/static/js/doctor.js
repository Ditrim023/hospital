function save(){
    var name = document.getElementById('name');
    var surname = document.getElementById('surname');
    var login = document.getElementById('login');
    var psw = document.getElementById('psw');
    var pos	= document.getElementById('pos');
    if(name.value==""){
        name.style.borderColor = "red";
        document.getElementById('aftername').innerHTML = " This field mast not be empty";
        return false;
    }if(surname.value==""){
        surname.style.borderColor = "red";
        document.getElementById('aftersurname').innerHTML = " This field mast not be empty";
        return false;
    }if(login.value==""){
        login.style.borderColor = "red";
        document.getElementById('afterlogin').innerHTML = " This field mast not be empty";
        return false;
    }if(psw.value.length<6){
        psw.style.borderColor = "red";
        document.getElementById('afterpsw').innerHTML = " At least 6 character";
        alert(" At least 6 character");
        return false;
    }if(pos.value==""){
        pos.style.borderColor = "red";
        document.getElementById('afterposition').innerHTML = " This field mast not be empty";
        return false;
    }
}