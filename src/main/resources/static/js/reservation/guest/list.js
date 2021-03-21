(() => {
  $(document).ready(function () {
    const setCollapsedState = () => {
      if (location.href.indexOf('upcoming') != -1) {
        $('.guest-reservation__upcoming').addClass("guest-reservation__active");
        $('.guest-reservation__complete').removeClass("guest-reservation__active");
        $('.guest-reservation__cancel').removeClass("guest-reservation__active");
        $('.guest-reservation__update').removeClass("guest-reservation__active");
      } else if (location.href.indexOf('complete') != -1) {
        $('.guest-reservation__complete').addClass("guest-reservation__active");
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__cancel').removeClass("guest-reservation__active");
        $('.guest-reservation__update').removeClass("guest-reservation__active");
      } else if (location.href.indexOf('cancel') != -1) {
        $('.guest-reservation__cancel').addClass("guest-reservation__active");
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__complete').removeClass("guest-reservation__active");
        $('.guest-reservation__update').removeClass("guest-reservation__active");
      } else if (location.href.indexOf('update') != -1) {
        $('.guest-reservation__update').addClass("guest-reservation__active");
        $('.guest-reservation__cancel').removeClass("guest-reservation__active");
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__complete').removeClass("guest-reservation__active");
      } else {
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__cancel').removeClass("guest-reservation__active");
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__complete').removeClass("guest-reservation__active");
      }

    }

    setCollapsedState();

    $('.guest-reservation__upcoming').on('click', function () {
      location.href = '/reservation/guest/list';
      $('.guest-reservation__upcoming').toggleClass("guest-reservation__active");
    });

    $('.guest-reservation__complete').on('click', function () {
      location.href = '/reservation/guest/list';
      $('.guest-reservation__complete').toggleClass("guest-reservation__active");

    });

    $('.guest-reservation__cancel').on('click', function () {
      location.href = '/reservation/guest/list';
      $('.guest-reservation__cancel').toggleClass("guest-reservation__active");

    });

    $('.guest-reservation__update').on('click', function () {
      location.href = '/reservation/guest/list';
      $('.guest-reservation__update').toggleClass("guest-reservation__active");

    });


    $('.guest-reservation__cancel-update').click(function () {

      const reservationId = $(this).children('.guest-reservation__id').val();
      const status = $('.guest-reservation__status').val();

      $.ajax({
        url: '/api/reservation/' + status + '/info/' + reservationId,
        type: 'get',
        data: {
          reservationId:reservationId
        },
        success: function (result) {
          if(result.status==='cancel') {
            $('.guest-reservation__modal-guest').html(result.adult + result.kid + result.infant);
          }
        }

      });

      $('#cancel-update-modal').show();

    });

    $('.guest-reservation__modal-cancel').bind('click', function () {
      $('#cancel-update-modal').hide();
    });


  });

})()