(() => {
  $(document).ready(function () {
    const setCollapsedState = () => {
      if (location.href.indexOf('upcoming') != -1) {
        $('.guest-reservation__upcoming').addClass("guest-reservation__active");
      } else if (location.href.indexOf('complete') != -1) {
        $('.guest-reservation__complete').addClass("guest-reservation__active");
      } else if (location.href.indexOf('cancel') != -1) {
        $('.guest-reservation__cancel').addClass("guest-reservation__active");
      } else if (location.href.indexOf('update') != -1) {
        $('.guest-reservation__update').addClass("guest-reservation__active");
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

    let before = null;
    let after = null;
    let afterPrice = null;
    let roomId;
    let hostId;

    $('.guest-reservation__cancel-update').click(function () {

      const reservationId = $(this).children('.guest-reservation__id').val();
      const status = $('.guest-reservation__status').val();

      $('.guest-reservation__cancel-update-id').val(reservationId);

      $.ajax({
        url: '/api/reservation/' + status + '/info/' + reservationId,
        type: 'get',
        data: {
          reservationId: reservationId
        },
        success: function (result) {
          console.log(result)

          if (result.length > 1) {
            before = result[0];
            after = result[1];
            afterPrice = result[2];
            roomId = result[0].roomId;
            hostId = result[0].hostId;

            $('.guest-reservation__modal-room-image').attr('src', before.roomImg);
            $('.guest-reservation__modal-guest').html(before.adult + before.kid + before.infant);
            $('.guest-reservation__modal-address').html(before.address);
            $('.guest-reservation__modal-date').html(before.checkIn + ' ~ ' + before.checkOut);
            $('#room-name').html(before.title);
            $('.guest-reservation__modal-price').html('변경 전 결제한 요금 : ₩' + before.price.toLocaleString('ko-KR'));
            $('.guest-reservation__modal-before-adult').html(before.adult);
            $('.guest-reservation__modal-before-kid').html(before.kid);
            $('.guest-reservation__modal-before-infant').html(before.infant);
            $('.guest-reservation__modal-before-total').html(before.adult + before.kid + before.infant);

            $('.guest-reservation__modal-after-adult').html(after.adult);
            $('.guest-reservation__modal-after-kid').html(after.kid);
            $('.guest-reservation__modal-after-infant').html(after.infant);
            $('.guest-reservation__modal-after-total').html(after.adult + after.kid + after.infant);
            if (afterPrice.price === 0) {
              $('.guest-reservation__modal-price').append('<div>재결제 또는 부분환불 없음</div><div class="guest-reservation__important-price">호스트가 수락하면 자동으로 업데이트 됩니다.</div>');
            } else if (before.price < afterPrice.price) {
              $('.guest-reservation__modal-price').append(
                '<div>변경 후 결제할 요금 : ' +
                '<span class="guest-reservation__important-price">₩' + afterPrice.price.toLocaleString('ko-KR') +
                '</span> / 수수료 10% : <span class="guest-reservation__important-price">₩' + afterPrice.commission.toLocaleString('ko-KR') + '</span></div>' +
                '<input type="hidden" class="guest-reservation__commission" value="' + afterPrice.commission +
                '"><input type="hidden" class="guest-reservation__price" value="' + afterPrice.price + '">'
              );

              if (after.status === 'update-ok') {
                $('.guest-reservation__modal-price').append('<button class="guest-reservation__again-payment">재결제하기</button>');
                $('.guest-reservation__again-payment-id').val(before.paymentId);
              }
            } else if (before.price > afterPrice.price) {
              $('.guest-reservation__modal-price').append(
                '<div>변경 후 필요한 요금 : ₩' + afterPrice.price.toLocaleString('ko-KR') + ' / 수수료 10% : ₩' + afterPrice.commission.toLocaleString('ko-KR') + '</div>' +
                '<div>부분 환불될 요금 : <span class="guest-reservation__important-price">₩' + (before.price - afterPrice.price - afterPrice.commission).toLocaleString('ko-KR') +
                '</span></div>' +
                '<input type="hidden" class="guest-reservation__commission" value="' + afterPrice.commission +
                '"><input type="hidden" class="guest-reservation__price" value="' + afterPrice.price + '">' +
                '<input type="hidden" class="guest-reservation__refund-price" value="' + (before.price - afterPrice.price - afterPrice.commission) + '">'
              );
              if (after.status === 'update-ok') {
                $('.guest-reservation__modal-price').append('<button class="guest-reservation__partial-refund">부분 환불하기</button>');
                $('.guest-reservation__again-payment-id').val(before.paymentId);
              }

            }

            $('.guest-reservation__modal-host-name').html(before.username);
            $('.guest-reservation__modal-before-check-in-out').html(before.checkIn + ' ~ ' + before.checkOut);
            $('.guest-reservation__modal-after-check-in-out').html(after.checkIn + ' ~ ' + after.checkOut);
          } else {
            roomId = result.roomId;
            hostId = result.hostId;
            $('.guest-reservation__modal-room-image').attr('src', result.roomImg);
            $('.guest-reservation__modal-guest').html(result.adult + result.kid + result.infant);
            $('.guest-reservation__modal-address').html(result.address);
            $('.guest-reservation__modal-date').html(result.checkIn + ' ~ ' + result.checkOut);
            $('#room-name').html(result.title);
            $('.guest-reservation__modal-host-name').html(result.username);
            $('.guest-reservation__modal-price').html('₩' + result.price.toLocaleString('ko-KR'));
            $('.guest-reservation__modal-refund').html('₩' + result.price.toLocaleString('ko-KR'));

          }

        }

      });

      $('#cancel-update-modal').show();

    });

    $('#cancel-update-modal').click(function (e) {
      const container = $('#cancel-update-modal');
      if (e.target == e.currentTarget) {
        container.hide();
        return;
      }
    });

    $('.guest-reservation__modal-cancel').bind('click', function () {
      $('#cancel-update-modal').hide();
    });

    $('.guest-reservation__modal-room-detail').click(function () {
      location.href = '/room/detail/' + roomId;
    })

    $('.guest-reservation__modal-host-name').click(function () {
      location.href = '/member/profile/' + hostId;
    })

  });

})()