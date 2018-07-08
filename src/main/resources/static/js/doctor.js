$('#add-form').validate({
    rules:{
        name:{
            required:true
        },
        surname:{
            required:true
        },
        login:{
            required:true,
            minlength: 5
        },
        password:{
            required:true,
            minlength: 6
        },
        position:{
            required:true
        },
        dateBirth:{
            required:true
        }
    },
    messages:{
        name:{
            required:'Must not be empty'
        },
        surname:{
            required:'Must not be empty'
        },
        login:{
            required:'Must not be empty',
            minlength:'At least 5 character'
        },
        password:{
            required:'Must not be empty',
            minlength:'At least 6 character'
        },
        position:{
            required:'Must not be empty'
        },
        dateBirth:{
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