(() => {
  let ws;

  function wsOpen() {

    ws = new WebSocket(`wss://${location.host}/chat`);
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

        if (d.chatRef === parseInt($("#chatRef").val())) {

          const {
            chatRef,
            memberId,
            msg,
            profileImg,
            username,
            date:
              {
                date:
                  {year, month, day},
                time:
                  {hour, minute}
              }
          } = d
          if (d.memberId === parseInt($("#memberId").val())) {
            $(".chatroom-content-js").append(`<div><div class="chat__send-chat-container"><img src="${profileImg}" class="chat__sender-img"/> <div class="chat__send-chat-content"> <span class="chat__sender">${username}</span> <span class="chat__send-time">${year}-${month}-${day} ${hour}:${minute}</span> <div class="chat__send-chat">${msg}</div> </div> </div></div>`);
            $('.chatroom-content-js').scrollTop($('.chatroom-content-js')[0].scrollHeight);
          } else {
            $(".chatroom-content-js").append(`<div><div class="chat__receive-chat-container"><div class="chat__receive-chat-content"><div><span class="chat__receive-time">${year}-${month}-${day} ${hour}:${minute}</span><span  class="chat__receiver">${username}</span><div  class="chat__receive-chat">${msg}</div></div><img src="${profileImg}" class="chat__receiver-img"/></div></div></div></div>`)
            $('.chatroom-content-js').scrollTop($('.chatroom-content-js')[0].scrollHeight);
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
      msg: $(".message-js").val(),
    }

    ws.send(JSON.stringify(option))
    $('.message-js').val("");
  }


})()



