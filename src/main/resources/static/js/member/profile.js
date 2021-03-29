(() => {

  // Element
  const updateProfileImgEl = document.querySelector("#update-profile-img");
  const memberProfileImgEl = document.querySelector("#member-profile-img");
  const guestReviewBtnEl = document.querySelector(".guest-review-btn-js");
  const hostReviewBtnEl = document.querySelector(".host-review-btn-js");
  const reviewContentContainerEl = document.querySelector(".review-content-container-js");

  const profileMemberId = document.querySelector("#member-id").value;

  // CSRF
  const csrf = document.querySelector("#csrf").value;

  // Handler
  const handleProfileImgChange = async (e) => {
    const formData = new FormData();
    formData.append("profileImg", e.target.files[0]);

    memberProfileImgEl.style.opacity = '0.5';
    const res = await fetch(`//${location.host}/api/member/update/profile-img`, {
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
    const res = await fetch(`//${location.host}/api/review/${url}/about/${profileMemberId}`);
    const reviews = await res.json();
    reviewContentContainerEl.innerHTML = "";
    for (const review of reviews) {
      const {
        memberId,
        username,
        profileImg,
        reviewContent,
        reviewRegDate
      } = review;

      reviewContentContainerEl.innerHTML += `
          <div class="review-info__content-wrap">
            <div class="review-info__content-review-reg-date">${reviewRegDate}</div>
            <div class="review-info__content-review-content">
              ${reviewContent}
            </div>
            <div class="review-info__content-profile-container">
            <a href="./${memberId}">
              <img src="${profileImg}" alt="프로필" class="review-info__content-profile-img"/>
            </a>
            <div class="review-info__content-username">${username}</div>
            </div>
          </div>
      `;
    }
  }

  // Initialize
  const init = () => {
    updateProfileImgEl && updateProfileImgEl.addEventListener("change", handleProfileImgChange);
    guestReviewBtnEl.addEventListener("click", (e) => handleReviewBtnClick(e, "guest"));
    hostReviewBtnEl.addEventListener("click", (e) => handleReviewBtnClick(e, "host"));
    guestReviewBtnEl.click();
  }
  init();

})();