(() => {
  $(document).ready(function () {
    let num = 0;
    let maxNum = 100;

    const pNum = function plusNum() {
      if (parseInt($('#header-adult-number').val()) + parseInt($('#header-kid-number').val()) < maxNum) {
        ++num;
      }
    }

    function total() {
      $('#header-total-guest').html('게스트 : ' + (parseInt($('#header-adult-number').val()) + parseInt($('#header-kid-number').val())) + '명');
    }

    $('#header-update-guest').bind('click', function () {

      $('#header-guest-modal').show();

    });


    if (parseInt($('#header-adult-number').val()) + parseInt($('#header-kid-number').val()) < maxNum) {
      $('#header-adult-plus').bind('click', function () {

        num = parseInt($('#header-adult-number').val());

        pNum();

        $('#header-adult-number').val(num);

        total();
      });

      $('#header-kid-plus').bind('click', function () {

        num = parseInt($('#header-kid-number').val());
        pNum();
        $('#header-kid-number').val(num);
        total();
      });

      $('#header-infant-plus').bind('click', function () {

        num = parseInt($('#header-infant-number').val());
        if (num < 5) {
          ++num;
        }
        $('#header-infant-number').val(num);
        total();
      });

    }


    $('#header-adult-minus').bind('click', function () {
      num = parseInt($('#header-adult-number').val());
      if (num > 1) {
        --num;
      }
      $('#header-adult-number').val(num);
      total();
    });

    $('#header-kid-minus').bind('click', function () {
      num = parseInt($('#header-kid-number').val());
      if (num > 0) {
        --num;
      }
      $('#header-kid-number').val(num);
      total();
    });

    $('#header-infant-minus').bind('click', function () {
      num = parseInt($('#header-infant-number').val());
      if (num > 0) {
        --num;
      }
      $('#header-infant-number').val(num);
      total();
    });



    $('.header__modal-cancel').bind('click', function () {
      $('#header-guest-modal').hide();
    });

  });

})()
