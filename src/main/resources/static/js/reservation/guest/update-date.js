(() => {


  $(document).ready(function () {

    $('#update-date').click(function () {

      $.ajax({
        url: "/reservation/guest/update/ajax",
        type: "GET",
        data: {checkIn : $('.reservation__check-in').html()},
        success: function (data) {
          if(data) {
            $('.guest-reservation-update__msg').html(data);
            $('.guest-reservation-update__reservation-date').css('box-shadow', '0 0 5px red');
          } else {

            $.getScript('reservation-date-modal.js');

          }
        }


      })

    });


  });


})()