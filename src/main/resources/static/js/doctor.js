$.validator.addMethod("duplicate_login", isDuplicate, "This login already exist");
$('#input-form').validate({
    rules: {
        name: {
            required: true
        },
        surname: {
            required: true
        },
        login: {
            required: true,
            minlength: 5,
            duplicate_login: true
        },
        password: {
            required: true,
            minlength: 6
        },
        position: {
            required: true
        },
        dateBirth: {
            required: true
        }
    },
    messages: {
        name: {
            required: 'Must not be empty'
        },
        surname: {
            required: 'Must not be empty'
        },
        login: {
            required: 'Must not be empty',
            minlength: 'At least 5 character'
        },
        password: {
            required: 'Must not be empty',
            minlength: 'At least 6 character'
        },
        position: {
            required: 'Must not be empty'
        },
        dateBirth: {
            required: 'Must not be empty'
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

var result = true;

// function getResult() {
//     return result;
// }

function isDuplicate() {
    var login = $('#input-login').val();
    var url = "/is-duplicate-user-login/" + login;
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            result = data;
        }
    });
    return result;
}




