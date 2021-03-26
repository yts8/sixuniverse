//
//   var createDate= $('#createDate').val();
//   var renewDate= $('#renewDate').val();
(() => {
  let impossibleDayString = [] //예약불가능날짜 담을 리스트
  const today = $('#renew-date').val();
  const expiryDate = $('#expiry_date').val();
  const expiryDay = $('#expiry_day').val();


  const now = new Date();
  const date = new Date(now.setDate(now.getDate()));
  const year=("0" + (date.getYear())).slice(-2);
  const month = ("0" + (1 + date.getMonth())).slice(-2);
  const day = ("0" + date.getDate()).slice(-2);
  const today2= ("20"+year+"-"+month + "-" + day);
  // alert(today2);

  document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    const calendar = new FullCalendar.Calendar(calendarEl, {

      headerToolbar: {
        left: 'prev,next',
        right: "dayGridMonth",
        center: 'title',
      },
      initialDate: today,
      selectable: true,
      eventBackgroundColor: 'transparent',
      eventBorderColor: 'transparent',
      locale: 'ko', //언어 한국어
      events: [
        {
          id: 'past',
          title: 'past',
          start: '0000-00-00',
          end: today2,
          display: 'background',
          color: '#787878',
          selectable: false,
          editable: false,
        }, {
          id: 'expiryDay',
          title: 'expiryDayAfter',
          start: expiryDay,
          end: '3000-11-11',
          display: 'background',
          color: '#787878',
          selectable: false,
          editable: false,
        },
        {
          id: 'today',
          title: 'today',
          start: today,
          end: today,
          color: '#787878',
        },
        {
          id: 'expiryDate',
          title: '갱신일',
          start: expiryDate,
          end: expiryDate,
          color: '#787878',
        },

      ],


      //취소날짜 선택
      dateClick: function (dateClickInfo) {

        // alert(info.event.title);
        const gray = "#787878";
        const clickDate = dateClickInfo.dateStr;  //클릭한 날짜 변수에 저장

        const event = calendar.getEventById('today') // an event object!
        const today2 = event.start // a property (a Date object)- 참고

        const event2 = calendar.getEventById('expiryDate') // an event object!
        const expiry2 = event2.start // a property (a Date object)- 참고

        if (dateClickInfo.date < today2 || dateClickInfo.date > expiry2) {
          alert("비활성화된 기간입니다.")
        } else {

          if (dateClickInfo.dayEl.style.backgroundColor) {
            dateClickInfo.dayEl.style.backgroundColor = "";
            impossibleDayString = impossibleDayString.filter(day => day !== clickDate);

          } else {
            dateClickInfo.dayEl.style.backgroundColor = gray;
            impossibleDayString.push(clickDate);
          }
        }

      },

//   //캘린더에 아이콘만드는 코드
//   eventContent: {
//   html: `<div><img src="/images/room/ok.png" class="event-icon" /></div>`,
// },


    });
    calendar.render();
  });

  const submitBtnEl = document.querySelector(".room-register-btn-js");
  submitBtnEl.addEventListener("click", async () => {
    const csrf = document.querySelector("#csrf").value;

    const formData = new FormData();
    const roomIdEl = document.querySelector("#room-id");
    formData.append("roomId", roomIdEl.value);
    for (const day of impossibleDayString) {
      formData.append("impossibleDayString", day);
    }
    await fetch("http://localhost:8080/api/host/room/register/calendar", {
      method: "post",
      headers: {
        "X-CSRF-TOKEN": csrf
      },
      body: formData
    })
    location.href = `../price/${roomIdEl.value}`;
  })
})();
