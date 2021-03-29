(() => {
  $(document).ready(function () {

    $('.reservation__save-date').click(function () {
      if ($('#check-in').val() != '' && $('#check-out').val() != '') {
        const checkIn = new Date($('#check-in').val());
        const checkOut = new Date($('#check-out').val());

        const price = $('.reservation__one-day-price').val();

        console.log(price)

        const days = (checkOut.getTime() - checkIn.getTime()) / 1000 / 60 / 60 / 24;

        const result = price * days;


        $('.reservation__days-price-info').html('₩' + result.toLocaleString('ko-KR'));
        $('.reservation__commission-info').html('₩' + Math.floor(result * 0.1).toLocaleString('ko-KR'));
        $('.reservation__days').html(days)
        $('#totalPrice').val(result);
        $('.reservation__total2').html('₩' + (result + Math.floor(result * 0.1)).toLocaleString('ko-KR'));

        $('.reservation__date').html($('#check-in').val() + ' ~ ' + $('#check-out').val());
        $('.reservation__check-in').val($('#check-in').val());
        $('.reservation__check-in').html($('#check-in').val());
        $('.reservation__check-out').val($('#check-out').val());
        $('.reservation__check-out').html($('#check-out').val());

        $('#date-modal').hide();
      }


    });


  });


})()