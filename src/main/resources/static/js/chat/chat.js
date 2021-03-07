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

var ws;

function wsOpen(){
  //웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
  ws = new WebSocket("ws://" + location.host + "/chating/"+$("#roomNumber").val());
  wsEvt();
}

function wsEvt() {
  ws.onopen = function(data){
    //소켓이 열리면 동작
  }

  ws.onmessage = function(data) {
    //메시지를 받으면 동작
    var msg = data.data;
    if(msg != null && msg.trim() != ''){
      var d = JSON.parse(msg);
      if(d.type == "getId"){
        var si = d.sessionId != null ? d.sessionId : "";
        if(si != ''){
          $("#sessionId").val(si);
        }
      }else if(d.type == "message"){
        if(d.sessionId == $("#sessionId").val()){
          $("#chating").append("<p class='me'>나 :" + d.msg + "</p>");
        }else{
          $("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
        }

      }else{
        console.warn("unknown type!")
      }
    }
  }

  document.addEventListener("keypress", function(e){
    if(e.keyCode == 13){ //enter press
      send();
    }
  });
}

function chatName(){
  var userName = $("#userName").val();
  if(userName == null || userName.trim() == ""){
    alert("사용자 이름을 입력해주세요.");
    $("#userName").focus();
  }else{
    wsOpen();
    $("#yourName").hide();
    $("#yourMsg").show();
  }
}

function send() {
  var option ={
    type: "message",
    roomNumber: $("#roomNumber").val(),
    sessionId : $("#sessionId").val(),
    userName : $("#userName").val(),
    msg : $("#chatting").val()
  }
  ws.send(JSON.stringify(option))
  $('#chatting').val("");
}