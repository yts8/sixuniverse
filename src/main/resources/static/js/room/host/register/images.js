(() => {
  // Element
  const roomImagesContainerEl = document.querySelector(".room-images-container-js");
  const roomImageLabelEls = document.querySelectorAll(".room-image-label-js");

  const roomIdEl = document.querySelector("#room-id");

  // CSRF
  const csrf = document.querySelector("#csrf").value;

  // Handler
  const handleFileChange = async (e, labelEl) => {
    const formData = new FormData();
    const roomImgId = e.target.dataset.roomImgId;

    formData.append("roomId", roomIdEl.value);
    formData.append("roomImg", e.target.files[0]);

    let url;
    if (!roomImgId) { // 등록
      url = `//${location.host}/api/host/room/register/images`;
    } else { // 수정
      url = `//${location.host}/api/host/room/register/images/update`;
      formData.append("roomImgId", roomImgId);
    }

    labelEl.style.opacity = '0.5';
    const res = await fetch(url, {
      method: "post",
      headers: {
        "X-CSRF-TOKEN": csrf
      },
      body: formData
    })
    try {
      const json = await res.json();
      const imgEl = labelEl.querySelector("img");
      imgEl.setAttribute("src", json.roomImg);
      e.target.dataset.roomImgId = json.roomImgId;

      if (!roomImgId) {
        if (json.roomImg != null) {
          const nextLabelEl = document.createElement("label");
          nextLabelEl.classList.add("room__images-wrap");
          nextLabelEl.classList.add("room-image-label-js");
          nextLabelEl.innerHTML = '<input type="file"/>' +
            '                   <img/>' +
            '                   <span>' +
            '                     <i class="fas fa-plus"></i>' +
            '                   </span>';
          roomImagesContainerEl.append(nextLabelEl);
          const fileInputEl = nextLabelEl.querySelector('input[type="file"]')
          fileInputEl.addEventListener("change", (e) => handleFileChange(e, nextLabelEl));
        } else {
          alert("잘못된 요청입니다.")
        }
      }
    } catch (error) {
      alert("잘못된 요청입니다.");
    }
    labelEl.style.opacity = '1';
  }

  // Initialize
  const init = () => {
    roomImageLabelEls.forEach(labelEl => {
      const fileInputEl = labelEl.querySelector('input[type="file"]')
      fileInputEl.addEventListener("change", (e) => handleFileChange(e, labelEl));
    })
  }
  init();

})();