/*(() => { */// IIFE

/* const csrf = document.querySelector("#csrf");
  await fetch(`http://localhost:8080/api/member/update/${id}`, {
     method: "post",
     headers: {
       "Content-Type": "application/json",
       "X-CSRF-TOKEN": csrf
     },
     body: JSON.stringify({
       name: "이름"
     })
   })*/
// Element
/*  const chatRoomEls = document.querySelectorAll(".chat-chatroom-list-frame-js");

  // Handler
  const handleChatRoomClick = async (e) => {
    const chatRoomEl = e.currentTarget;
    const roomId = chatRoomEl.dataset.roomId;

    const res = await fetch(`http://localhost:8080/api/chat/chatroom/${roomId}`, {
      method: "GET",
    })
    const chats = await res.json();

    for (let chat of chats) {
      console.log(chat.chatId)
    }

  }

  const init = () => {
    chatRoomEls.forEach(chatRoomEl => {
      chatRoomEl.addEventListener("click", handleChatRoomClick)
    })
  }
  init();

})()*/
(() => {
  var ws;

  function wsOpen() {

    ws = new WebSocket("ws://" + location.host + "/chating/" + $("#chatroomId").val());
    wsEvt();
  }

  wsOpen();

  function wsEvt() {
    ws.onopen = function (data) {
      //소켓이 열리면 동작
    }

    ws.onmessage = function (data) {
      //메시지를 받으면 동작
      var msg = data.data;
      if (msg != null && msg.trim() != '') {
        var d = JSON.parse(msg);
        if (d.memberId === $("#memberId").val()) {
          /*여기 수정할 것*/
          $(".chat__chatroom-content-js").append("<div class='chat__send-chat-container'><img class=\"chat__sender-img\" src=\"https://a0.muscache.com/defaults/user_pic-225x225.png?v=3\"> <div class='chat__send-chat-content'>" + "<span class='chat__sender'>" + d.userName +"</span>" + "<span class='chat__send-time'>" + d.date + "</span> <div class='chat__send-chat'>" + d.msg + "</div> </div> </div>");
        } else {
          $(".chat__chatroom-content-js").append("<div class='chat__receive-chat-container'><div class='chat__receive-chat-content'><div><div class='chat__send-chat-content'><span class='chat__receive-time'> "+d.date+"</span><span class='chat__receiver\'> "+d.userName+"</span><div class='chat__receive-chat'>"+d.msg+"</div></div></div></div><img class=\"chat__sender-img\" src=\"https://a0.muscache.com/defaults/user_pic-225x225.png?v=3\"></div>")
        }
      } else {
        console.warn("unknown type!")
      }
    }
    /* 버튼클릭 이벤트 추가할 것*/
    document.addEventListener("keypress", function (e) {
      if (e.keyCode == 13) {
        send();
      }
    });
  }


  function send() {
     $(".chat__form-chat-js").show();
    var option = {
      memberId: $("#memberId").val(),
      userName: $("#userName").val(),
      chatroomId: $("#chatroomId").val(),
      date: $("#date").val(),
      msg: $(".chat__message-js").val()
    }

    ws.send(JSON.stringify(option))
    $('.chat__message-js').val("");
  }
})()



