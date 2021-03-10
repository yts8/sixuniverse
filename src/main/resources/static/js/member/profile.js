(() => {

  // Element
  const updateProfileImgEl = document.querySelector("#update-profile-img");
  const memberProfileImgEl = document.querySelector("#member-profile-img");

  // CSRF
  const csrf = document.querySelector("#csrf").value;


  // Handler
  const handleProfileImgChange = async (e) => {
    const formData = new FormData();
    formData.append("profileImg", e.target.files[0]);


    memberProfileImgEl.style.opacity = '0.5';
    const res = await fetch("http://localhost:8080/api/member/update/profile-img", {
      method: "post",
      headers: {
        "X-CSRF-TOKEN": csrf
      },
      body: formData
    });
    const json = await res.json()
    memberProfileImgEl.setAttribute("src", json.profileImg);
    memberProfileImgEl.style.opacity = '1';
  }

  // Initialize
  const init = () => {
    updateProfileImgEl.addEventListener("change", handleProfileImgChange);
  }
  init();

})();