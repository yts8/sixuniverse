(() => {

  // Element
  const profileImgEl = document.querySelector("#profile-img");
  const memberProfileImgEl = document.querySelector("#member-profile-img");

  // CSRF
  const csrf = document.querySelector("#csrf").value;


  // Handler
  const handleProfileImgChange = async (e) => {
    const formData = new FormData();
    formData.append("profileImg", e.target.files[0]);

    const res = await fetch("http://localhost:8080/api/member/update/profile-img", {
      method: "post",
      headers: {
        "X-CSRF-TOKEN": csrf
      },
      body: formData
    });
    const fileUrl = await res.json()
    memberProfileImgEl.setAttribute("src", fileUrl.profileImg);
  }

  // Initialize
  const init = () => {
    profileImgEl.addEventListener("change", handleProfileImgChange);
  }
  init();

})();