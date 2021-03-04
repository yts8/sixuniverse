(() => {
  $(document).ready(function () {
    $('.guest-reservation-cancel__next-btn').click(function () {
      $.ajax({
        url: "./cancel/next",
        type: "GET",
        success: function(data){
          $('.guest-reservation-cancel__title').html('예약 취소 안내');
          $('.guest-reservation-cancel__small-box').hide();
        }

      });
    });

  });

})()