(() => {
  $(document).ready(function () {
    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;

    const update = function (status) {
      console.log(status)

      $.ajax({
        url: '/api/reservation/host/update/' + status,
        data: JSON.stringify({
          reservationId: $('#reservation-id').val(),
          status: status
        }),
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function (result) {
          if(result==='ok') {
            alert('수락하였습니다.');
          } else {
            alert('거절하였습니다.')
          }
        }
      }).fail(function () {
        alert('오류 발생');
      })
    }

    $('.reservation__host-update-ok').click(function () {
      update('update-ok');
    })

    $('.reservation__host-update-no').click(function () {
      update('update-no');
    })



  });

})()