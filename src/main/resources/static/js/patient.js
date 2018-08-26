$(function() {$("#sort").slimtable();});
$('#input-form').validate({
    rules:{
        name:{
            required:true
        },
        surname:{
            required:true
        },
        dateBirth:{
            required:true
        },
        doctorId:{
            required:true,
        }
    },
    messages:{
        name:{
            required:'Must not be empty'
        },
        surname:{
            required:'Must not be empty'
        },
        dateBirth:{
            required:'Must not be empty'
        },
        doctorId:{
            required:'Must not be empty'
        }
    },
    errorPlacement: function (error, element) {
        if (element.attr('type') == 'radio') {
            error.insertBefore(element.parent());
        } else {
            error.insertBefore(element);
        }
    }
});

$('#input-datebirth').datetimepicker({
    format: 'dd-mm-yyyy',
    autoclose: 1,
    minView: 2
});

function myFunctionId() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("findById");
    filter = input.value.toUpperCase();
    table = document.getElementById("sort");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function myFunctionSurName() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("findBySurName");
    filter = input.value.toUpperCase();
    table = document.getElementById("sort");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function myFunctionDoctor() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("findByDoctor");
    filter = input.value.toUpperCase();
    table = document.getElementById("sort");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[4];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
$(function() {
    $("#sort").slimtable();
});