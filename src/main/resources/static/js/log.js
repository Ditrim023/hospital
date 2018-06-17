window.onload = function(){
     var j = document.getElementById('position').value;
    var out = document.getElementById('bar');
    if(position.innerHTML!="Admin"){
        out.outerHTML = ' <h2>Hello Doctor<h2>';
    }
}
