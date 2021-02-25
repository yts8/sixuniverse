
(() => {
  jQuery(document).ready(function() {
    $('#update-guest').bind('click', function () {
      $('#my-modal').show();
    });

    $('.reservation__modal-cancel').bind('click', function () {
      $('#my-modal').hide();
    });

  });

}) ()
