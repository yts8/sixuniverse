(() => {
  // Elements
  const roomDeleteBtnEls = document.querySelectorAll(".room-delete-btn-js");
  const roomStopBtnEls = document.querySelectorAll(".room-stop-btn-js");
  const roomClearBtnEls = document.querySelectorAll(".room-clear-btn-js");
  const roomRenewBtnEls = document.querySelectorAll(".room-renew-btn-js");

  // CSRF
  const csrf = document.querySelector("#csrf").value;

  // Handler
  const handleBtnClick = async (e, message, url) => {
    if (!confirm(message)) {
      return;
    }
    const {dataset: {id}} = e.currentTarget;

    await fetch(`http://localhost:8080/api/host/room/${id}/${url}`, {
      method: "post",
      headers: {
        "X-CSRF-TOKEN": csrf
      },
      body: JSON.stringify({id})
    });
    location.reload();
  }


  // Initialize
  const init = () => {
    roomDeleteBtnEls.forEach(el =>
      el.addEventListener("click", e => handleBtnClick(e, "영구 삭제 하시겠습니까?", "delete")));
    roomStopBtnEls.forEach(el =>
      el.addEventListener("click", e => handleBtnClick(e, "숙소 운영을 중지 하시겠습니까?", "update/stop")));
    roomClearBtnEls.forEach(el =>
      el.addEventListener("click", e => handleBtnClick(e, "숙소를 재 운영 하시겠습니까?", "update/clear")));
    roomRenewBtnEls.forEach(el =>
      el.addEventListener("click", e => handleBtnClick(e, "숙소 운영을 연장 하시겠습니까?", "update/renew")));
  }
  init();
})();