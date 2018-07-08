$('#add-form').validate({
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