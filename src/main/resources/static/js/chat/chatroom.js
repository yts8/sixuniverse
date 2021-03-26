(() => {

/*

  /!* 스크롤 이벤트 최초 발생*!/
  $(window).scroll(function () {
    /!* 이전 스크롤 좌표 *!/
    let lastScrollTop = 0;

    /!*현재 스크롤 좌표 *!/
    let currentScrollTop = $(window).scrollTop();
    if(lastScrollTop - lastScrollTop >0){
      lastScrollTop = currentScrollTop;
    }else{
      lastScrollTop=currentScrollTop;
    }
    let lastbno = $(".scrolling:last").attr("");
    /!* 현재 스크롤의 위치가 화면의 보이는 위치보다 크다면*!/
    if ($(window).scrollTop() >= $(document).height() - $(window).height()) {


      /!* ajax 로 서버에 게시물 데이터를 요청한다.*!/
      $.ajax({
        type:'post',
        url : '',
        headers : {
          "Content-Type" : "application/json",
          "X-HTTP-Method-Override" : "POST"
        },
        dataType : 'json',
        data : JSON.stringify({
          btn : lastbno
        }),
        success : function (data) {
          let str = "";
          if(data != ""){

          }

        }
      })


    }
  });*/

})()