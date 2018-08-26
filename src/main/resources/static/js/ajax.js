function showEditModal(index) {
    var editUrl = "/patient/comment/" + index;
    $.ajax({
        type: 'GET',
        url: editUrl,
        success: function (data) {
            if (data.includes("Sent from")){
                $('#update-id').val(index);
                $('#update-text').val(data).attr('readonly','readonly');
            }else{
                $('#update-id').val(index);
                $('#update-text').val(data);
            }

        }
    });
}


