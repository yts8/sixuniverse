(() => {
  $(document).ready(function () {

    $('.reservation__save-date').click(function () {
      const checkIn = new Date($('.reservation__check-in').html());
      const checkOut = new Date($('.reservation__check-out').html());

      const days = (checkOut.getTime() - checkIn.getTime()) / 1000 / 60 / 60 / 24;

      const header = "X-CSRF-TOKEN";
      const csrf = document.querySelector("#csrf").value;

      $.ajax({
        url: "/api/reservation/before",
        data: JSON.stringify({
          checkIn: $('.reservation__check-in').html(),
          checkOut: $('.reservation__check-out').html(),
          roomId: $('#room-id').val()
        }),
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function (result) {
          console.log(result);
          $('#reservation-box-subject').html('₩' + result.toLocaleString('ko-KR') + '/' + days + '박')
          $('#before-btn').hide();
          $('#reservation-btn').show();
          $('.room__detail-price-info-box').show();
          $('.room__detail-days').html(days + '박');
          $('.room__detail-total-price').html('₩' + result.toLocaleString('ko-KR'));
          $('.room__detail-price-commission').html('₩' + (result * 0.1).toLocaleString('ko-KR'));

          $('input[name=checkIn]').val($('.reservation__check-in').html());
          $('input[name=checkOut]').val($('.reservation__check-out').html());
          $('input[name=totalPrice]').val(result);
          $('input[name=commission]').val(result * 0.1);

        }
        // error:function(request,status,error){
        //   alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        // }
      });
    });

    $('.reservation__guest-update').click(function () {
      $('input[name=adult]').val(parseInt($('.reservation__guest-adult').html()));
      $('input[name=kid]').val(parseInt($('.reservation__guest-kid').html()));
      $('input[name=infant]').val(parseInt($('.reservation__guest-infant').html()));
    });

    //   $('.room__detail-reservation-check-btn').click(function () {
    //
    //   const header = "X-CSRF-TOKEN";
    //   const csrf = document.querySelector("#csrf").value;
    //
    //   const data = {
    //     checkIn:$('.reservation__check-in').html(),
    //     checkOut:$('.reservation__check-out').html(),
    //     roomId:$('#room-id').val()
    //   };
    //   const json = JSON.stringify(data);
    //
    //   $.ajax({
    //     url: "/api/reservation/before",
    //     data: json,
    //     type: 'post',
    //     contentType: 'application/json; charset=utf-8',
    //     beforeSend: function (xhr) {
    //       xhr.setRequestHeader(header, csrf);
    //     },
    //     success: function (result) {
    //       console.log(result);
    //       $('#before-btn').hide();
    //       $('#reservation-btn').show();
    //       $('.room__detail-price-info-box').show();
    //       $('.room__detail-total-price').html(result);
    //       $('.room__detail-price-commission').html(result * 0.1);
    //
    //       $('input[name=checkIn]').val($('.reservation__check-in').html());
    //       $('input[name=checkOut]').val($('.reservation__check-out').html());
    //       $('input[name=adult]').val(parseInt($('.reservation__guest-adult').html()));
    //       $('input[name=kid]').val(parseInt($('.reservation__guest-kid').html()));
    //       $('input[name=infant]').val(parseInt($('.reservation__guest-infant').html()));
    //
    //     }
    //     // error:function(request,status,error){
    //     //   alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
    //     // }
    //   });
    //
    // });


  });

})()