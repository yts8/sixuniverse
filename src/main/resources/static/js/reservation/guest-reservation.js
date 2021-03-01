(() => {
  $(document).ready(function(){
    const setCollapsedState = ( )=> {
      if (location.href.indexOf('upcoming') != -1) {
          $('.guest-reservation__upcoming').addClass("guest-reservation__active");
          $('.guest-reservation__complete').removeClass("guest-reservation__active");
      } else if (location.href.indexOf('complete') != -1) {
          $('.guest-reservation__complete').addClass("guest-reservation__active");
          $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
      } else {
        $('.guest-reservation__upcoming').removeClass("guest-reservation__active");
        $('.guest-reservation__complete').removeClass("guest-reservation__active");
      }

    }

    setCollapsedState( );

    $('.guest-reservation__upcoming').on('click',function(){
      location.href='./guest-reservation-list?status=upcoming';
      $('.guest-reservation__upcoming').toggleClass("guest-reservation__active");
    });

    $('.guest-reservation__complete').on('click',function(){
      location.href='./guest-reservation-list?status=complete';
      $('.guest-reservation__complete').toggleClass("guest-reservation__active");

    });

  });

})()