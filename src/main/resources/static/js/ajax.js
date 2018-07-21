function showEditModal(index) {
    var editUrl = "/patient/comment/" + index;
    var id = index;
    console.log(id);
    $.ajax({
      type:'GET',
        url:editUrl,
        success: function (data) {
             $('#update-id').val(index);
             $('#update-text').val(data);
        }
    });

}
