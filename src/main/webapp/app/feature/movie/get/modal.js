$('#myModal').on('show.bs.modal', function(event) {
    var button = $(event.relatedTarget);
    var link = button.data('img');
    var title = button.data('title');
    var desc  = button.data('desc');
    var modal = $(this);
    modal.find('.modal-img').attr('src', link);
    modal.find('.film-title').text(title);
    modal.find('.film-description').text(desc);
});