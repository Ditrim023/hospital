function showEditModal(index) {
    var editUrl = "/patient/comment/" + index;
    $.ajax({
        type: 'GET',
        url: editUrl,
        success: function (data) {
            $('#update-id').val(index);
            $('#update-text').val(data);
        }
    });
}







