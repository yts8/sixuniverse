(() => {

  $(document).ready(function () {
    $('.guest-reservation-cancel__next-btn').click(function () {

      const reservationId = $('.guest-reservation-cancel__reservation-id').val();

      $.ajax({
        url: '/api/reservation/guest/',
      });


    })

  });

})()