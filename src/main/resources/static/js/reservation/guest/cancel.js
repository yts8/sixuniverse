(() => {

  $(document).ready(function () {

    $('.guest-reservation-detail-info__cancel').click(function () {
      const date = new Date();

      const year = date.getFullYear();
      const month = date.getMonth() >= 10 ? date.getMonth() + 1 : "0" + (date.getMonth() + 1);
      const day = date.getDate();

      const today = year + "-" + month + "-" + day;

      console.log(today)
      console.log($('#checkIn').val())

      if ($('#checkIn').val() === today) {
        alert('체크인 날짜 당일에는 예약을 취소할 수 없습니다.');
        return;
      } else {
        location.href = `${location.protocol}//${location.host}/reservation/guest/cancel/reason/` + $('#reservationId').val();
      }


    })

  });

})()