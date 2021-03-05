(() => { // IIFE

  const csrf = document.querySelector("#csrf");
    await fetch(`http://localhost:8080/api/member/update/${id}`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrf
      },
      body: JSON.stringify({
        name: "이름"
      })
    })
  // Element
  const chatRoomEls = document.querySelectorAll(".chat-chatroom-list-frame-js");

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

})()
