(() => {

  // Element
  const updateProfileImgEl = document.querySelector("#update-profile-img");
  const memberProfileImgEl = document.querySelector("#member-profile-img");
  const guestReviewBtnEl = document.querySelector(".guest-review-btn-js");
  const hostReviewBtnEl = document.querySelector(".host-review-btn-js");

  const memberId = document.querySelector("#member-id").value;

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

  const handleReviewBtnClick = async (e, url) => {
    const res = await fetch(`http://localhost:8080/api/review/${url}/about`);
    const map = await res.json();
    console.log(map);
  }

  // Initialize
  const init = () => {
    updateProfileImgEl && updateProfileImgEl.addEventListener("change", handleProfileImgChange);
    guestReviewBtnEl.addEventListener("click", (e) => handleReviewBtnClick(e, "guest"));
    hostReviewBtnEl.addEventListener("click", (e) => handleReviewBtnClick(e, "host"));
  }
  init();

})();