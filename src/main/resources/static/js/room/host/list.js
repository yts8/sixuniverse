(() => {
  // Elements
  const roomDeleteBtnEls = document.querySelectorAll(".room-delete-btn-js");

  // CSRF
  const csrf = document.querySelector("#csrf").value;

  // Handler
  const handleDeleteBtnClick = async (e) => {
    const {dataset: {id}} = e.currentTarget;

    await fetch(`http://localhost:8080/api/host/room/${id}/delete`, {
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
    roomDeleteBtnEls.forEach(el => el.addEventListener("click", handleDeleteBtnClick))

  }
  init();
})();