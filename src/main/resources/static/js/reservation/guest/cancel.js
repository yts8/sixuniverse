(() => {
  $(document).ready(function () {

    $('.guest-reservation-cancel__next-btn').click(function () {

      console.log($('select[name=reason]').val());

      if($('select[name=reason]').val() == null) {
        alert('선택해주세요');
        return;
      }

    });

  });

})()