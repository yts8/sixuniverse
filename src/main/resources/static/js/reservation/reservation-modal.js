(() => {
  $(document).ready(function () {
    let num = 0;
    let maxNum = parseInt($('.reservation__max-people').html());


    const pNum = function plusNum() {
      if (parseInt($('#adult-number').val()) + parseInt($('#kid-number').val()) < maxNum) {
        ++num;
      }
    }

    $('#update-guest').bind('click', function () {

      $('#my-modal').show();

    });


    if (parseInt($('#adult-number').val()) + parseInt($('#kid-number').val()) < maxNum) {
      $('#adult-plus').bind('click', function () {

        num = parseInt($('#adult-number').val());

        pNum();

        $('#adult-number').val(num);

      });

      $('#kid-plus').bind('click', function () {

        num = parseInt($('#kid-number').val());
        pNum();
        $('#kid-number').val(num);

      });

      $('#infant-plus').bind('click', function () {

        num = parseInt($('#infant-number').val());
        if (num < 5) {
          ++num;
        }
        $('#infant-number').val(num);

      });

    }


    $('#adult-minus').bind('click', function () {
      num = parseInt($('#adult-number').val());
      if (num > 1) {
        --num;
      }
      $('#adult-number').val(num);

    });

    $('#kid-minus').bind('click', function () {
      num = parseInt($('#kid-number').val());
      if (num > 0) {
        --num;
      }
      $('#kid-number').val(num);

    });

    $('#infant-minus').bind('click', function () {
      num = parseInt($('#infant-number').val());
      if (num > 0) {
        --num;
      }
      $('#infant-number').val(num);

    });



    $('.reservation__modal-cancel').bind('click', function () {
      $('#my-modal').hide();
    });

    $('.reservation__guest-update').click(function () {
      $('.reservation__guest-adult').html($('#adult-number').val());
      $('.reservation__guest-kid').html($('#kid-number').val());
      $('.reservation__guest-infant').html( $('#infant-number').val());

      $('.reservation__adult').val( $('#adult-number').val());
      $('.reservation__kid').val($('#kid-number').val());
      $('.reservation__infant').val( $('#infant-number').val());

      let totalGuest = parseInt($('#adult-number').val()) + parseInt($('#kid-number').val()) + parseInt($('#infant-number').val());

      $('.reservation__total-guest').html( totalGuest );


      $('#my-modal').hide();

    });


  });

})()
