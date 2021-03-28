(() => {
  /* 이전 스크롤 좌표 */

  let lastScrollTop = 0;
  let easeEffect = 'easeInQuint';

  /* 1. 스크롤 이벤트 발생*/
  $(window).scroll(function () { /*1) 스크롤 이벤트 최초 발생 */

    /* 현재 스크롤 좌표*/
    let currentScroolTop = $(window).scrollTop();

    /* --------->다운 스크롤<----------- */
    if (currentScroolTop - lastScrollTop > 0) {
      console.log("down-scroll");

      if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
        let lastChatId = $(".chatroom-content-js:last").attr("data-chatId");


      /* 3. ajax로 서버에 게시물 데이터 요청*/
      $.ajax({
        type: 'post',
        url: '', /* 요청할 서버의 url */
        headers: {
          "Content-Type": "application/json",
          "X-HTTP-Method-Override": "POST"
        },
        dataType: 'json',
        data: JSON.stringify({
          chatId: lastChatId
        }),
        success: function (data) {
          let str = "";

          if (data != "") {
            $(data).each(function () {
              console.log(this);

              str += "<div class=" + "'chat-content-js'"+">"
                + "<img class="+"'chat__sender-img'" + this.ProfileImg+">"
                +  "<div class="+"'send-chat-content-js'" + "><div><span "+this.username+"class='chat__sender'></span>"
                + "<span class='chat__send-time'>"+this.data+"</span></div>"
                +"<div class='chat__send-chat'>"+this.content+"</div></img></div></div>";
            });

            $(".").empty(); /* 전체 데이터 영역을 비워주고,*/
            $(".").after(str); /* 추가로 데이터를 뿌릴 영역*/
          }else{ /* 서버로부터 받아올 데이터가 없을 때*/
            alert("더 이상 불러올 채팅이 없습니다.");
          }
        } // success
      }); // ajax
      /* 4. DOM 핸들링*/
      }

  });




})()