window.onload = function(){
    var position = document.getElementById("position");
    var out = document.getElementById("bar");
    var info = document.getElementById("doc-info");
    if(position.innerHTML!="Admin"){
        out.outerHTML = ' <h2>Hello Doctor</h2>';
        info.outerHTML = '<h2>Doctor Info</h2>';
    }
}