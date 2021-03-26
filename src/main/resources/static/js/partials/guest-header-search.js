(() => {
  $(document).ready(function () {
    let num = 0;
    let maxNum = 100;
    let adult = $('#header-adult-number');
    let kid = $('#header-kid-number');
    let infant = $('#header-infant-number');

    const pNum = function plusNum() {
      if (parseInt(adult.val()) + parseInt(kid.val()) < maxNum) {
        ++num;
      }
    }

    function total() {
      const adultPlusKid = parseInt(adult.val()) + parseInt(kid.val());
      $('#header-total-guest').html(
        '게스트 : ' + adultPlusKid + '명'
      );

      if(parseInt(infant.val())!=0) {
        $('#header-total-guest').html(
          '게스트 : ' + adultPlusKid + '명' +
          ', 유아 : ' + parseInt(infant.val()) + '명'
        );
      }

      $('#total-guest').val(adultPlusKid + parseInt(infant.val()));
    }

    $('#header-update-guest').click(function (e) {

      if(e.target.className !== 'header__search-icon' && e.target.className !== 'fas fa-search') {

        $('#header-guest-modal').show();
      }


    });


    if (parseInt(adult.val()) + parseInt(kid.val()) < maxNum) {
      $('#header-adult-plus').bind('click', function () {

        num = parseInt(adult.val());

        pNum();

        adult.val(num);

        total();

      });

      $('#header-kid-plus').bind('click', function () {

        num = parseInt(kid.val());
        pNum();
        kid.val(num);
        total();

      });

      $('#header-infant-plus').bind('click', function () {

        num = parseInt(infant.val());
        if (num < 5) {
          ++num;
        }
        infant.val(num);
        total();

      });

    }


    $('#header-adult-minus').bind('click', function () {
      num = parseInt(adult.val());
      if (num > 1) {
        --num;
      }
      adult.val(num);
      total();

    });

    $('#header-kid-minus').bind('click', function () {
      num = parseInt(kid.val());
      if (num > 0) {
        --num;
      }
      kid.val(num);
      total();

    });

    $('#header-infant-minus').bind('click', function () {
      num = parseInt(infant.val());
      if (num > 0) {
        --num;
      }
      infant.val(num);
      total();
    });


    $('.header__modal-cancel').bind('click', function () {
      $('#header-guest-modal').hide();
    });

  });

})()
