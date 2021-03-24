(() => {
  let ws;

  function wsOpen() {

    ws = new WebSocket(`ws://${location.host}/chat`);
    console.log(ws)

    wsEvt();
  }

  wsOpen();

  function wsEvt() {
    ws.onopen = function (data) {
      console.log("open")
      //소켓이 열리면 동작
    }

    ws.onmessage = function (data) {
      //메시지를 받으면 동작
      let msg = data.data;
      if (msg != null && msg.trim() !== '') {

        let d = JSON.parse(msg);

        console.log(d);
        if (d.chatRef === parseInt($("#chatRef").val())) {
          if (d.memberId === parseInt($("#memberId").val())) {
            $(".chatroom-content-js").append("<div class='chat__send-chat-container'><img class=\"chat__sender-img\" src=\"https://a0.muscache.com/defaults/user_pic-225x225.png?v=3\"> <div class='chat__send-chat-content'>" + "<span class='chat__sender'>" + d.username + "</span>" + "<span class='chat__send-time'>" + d.date + "</span> <div class='chat__send-chat'>" + d.msg + "</div> </div> </div>");
          } else {
            $(".chatroom-content-js").append("<div class='chat__receive-chat-container'><div class='chat__receive-chat-content'><div class='chat__receive-chat-content'><div><span class='chat__receive-time'> " + d.date + "</span><span class='chat__receiver\'> " + d.username + "</span><div class='chat__receive-chat'>" + d.msg + "</div></div></div></div><img class=\"chat__sender-img\" src=\"https://a0.muscache.com/defaults/user_pic-225x225.png?v=3\"></div>")
          }
        }
      }
    }


    document.addEventListener("keypress", function (e) {
      if (e.keyCode == 13) { //enter press
        send();
      }
    });

  }

  function send() {
    $(".form-chat-js").show();


    let option = {
      memberId: $("#memberId").val(),
      chatRef: $('#chatRef').val(),
      msg: $(".message-js").val()
    }

    ws.send(JSON.stringify(option))
    $('.message-js').val("");
  }


})()



