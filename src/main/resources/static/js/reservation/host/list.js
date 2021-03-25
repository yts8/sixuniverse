(() => {
  const setCollapsedState = () => {
    if (location.href.indexOf('upcoming') != -1) {
      $('.reservation__upcoming').addClass("host-reservation__underline");
      // $('.reservation__complete').removeClass("host-reservation__underline");
      // $('.reservation__cancel').removeClass("host-reservation__underline");
      // $('.reservation__update').removeClass("host-reservation__underline");
      // $('.reservation__all').removeClass("host-reservation__underline");
    } else if (location.href.indexOf('complete') != -1) {
      $('.reservation__complete').addClass("host-reservation__underline");
      // $('.reservation__upcoming').removeClass("host-reservation__underline");
      // $('.reservation__cancel').removeClass("host-reservation__underline");
      // $('.reservation__update').removeClass("host-reservation__underline");
      // $('.reservation__all').removeClass("host-reservation__underline");
    } else if (location.href.indexOf('cancel') != -1) {
      $('.reservation__cancel').addClass("host-reservation__underline");
      // $('.reservation__upcoming').removeClass("host-reservation__underline");
      // $('.reservation__complete').removeClass("host-reservation__underline");
      // $('.reservation__update').removeClass("host-reservation__underline");
      // $('.reservation__all').removeClass("host-reservation__underline");
    } else if (location.href.indexOf('update') != -1) {
      $('.reservation__update').addClass("host-reservation__underline");
      // $('.reservation__cancel').removeClass("host-reservation__underline");
      // $('.reservation__upcoming').removeClass("host-reservation__underline");
      // $('.reservation__complete').removeClass("host-reservation__underline");
      // $('.reservation__all').removeClass("host-reservation__underline");
    } else {
      $('.reservation__all').addClass("host-reservation__underline");
      // $('.reservation__upcoming').removeClass("host-reservation__underline");
      // $('.reservation__cancel').removeClass("host-reservation__underline");
      // $('.reservation__upcoming').removeClass("host-reservation__underline");
      // $('.reservation__complete').removeClass("host-reservation__underline");
    }

  }

  setCollapsedState();

  $('.reservation__upcoming').on('click', function () {
    location.href = '/reservation/host/list';
    $('.reservation__upcoming').toggleClass("host-reservation__underline");
  });

  $('.reservation__complete').on('click', function () {
    location.href = '/reservation/host/list';
    $('.reservation__complete').toggleClass("host-reservation__underline");

  });

  $('.reservation__cancel').on('click', function () {
    location.href = '/reservation/host/list';
    $('.reservation__cancel').toggleClass("host-reservation__underline");

  });

  $('.reservation__update').on('click', function () {
    location.href = '/reservation/host/list';
    $('.reservation__update').toggleClass("host-reservation__underline");

  });


})()