(() => {

  $(document).ready(function () {

    $('.reservation__btn').click(function () {

      const header = "X-CSRF-TOKEN";
      const csrf = document.querySelector("#csrf").value;

      const data = $('.reservation__room-id').val();
      const json = JSON.stringify(data);

      $.ajax({
        url: "/api/reservation/room/member/check",
        data: json,
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function (result) {
          if (result) {
            alert("자신이 등록한 숙소는 예약하실 수 없습니다.");
            history.back();
            return;
          } else {
            $('#reservation-fr').submit();
          }
        }

      });

    });

  });

})()