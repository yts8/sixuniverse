(() => {
  $(document).ready(function(){
    $('.guest-reservation-update__back-btn').click(function () {
      history.back();
    });


    $('.guest-reservation-update__next-btn').click(function () {
      $.ajax({
        url: "/reservation/guest/update/complete",
        type: "GET",
        success: function(data){
          $('.guest-reservation-update__title').html('회원님이 요청하신 변경 내용');
          $('.guest-reservation-update__btn').append('<div class="guest-reservation-update__reply">예약 변경 요청이 호스트님에게 전송되었습니다.</div>')
          $('.guest-reservation-update__back-btn').css('display', 'none');
          $('.guest-reservation-update__next-btn').css('display', 'none');
      }

      });
    });






  });

})()