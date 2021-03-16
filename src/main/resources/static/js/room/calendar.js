//
//   var createDate= $('#createDate').val();
//   var renewDate= $('#renewDate').val();
(() => {
  let impossibleDayString = [] //예약불가능날짜 담을 리스트

  document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    const calendar = new FullCalendar.Calendar(calendarEl, {

      headerToolbar: {
        left: 'prev,next',
        right: "dayGridMonth",
        center: 'title',
      },
      // initialDate: '2021-03-12', //숙소등록일 입력하기
      selectable: true,
      // displayEventEnd: true,
      // selectMirror: true,
      eventBackgroundColor: 'transparent',
      eventBorderColor: 'transparent',

      dayMaxEvents: true, // allow "more" link when too many events
      locale: 'ko', //언어 한국어

      // select: function (arg) {
//
//   alert('해당 날짜에 예약이 불가능합니까?');
//   calendar.addEvent({
//   title: '예약불가날짜',
//   start: arg.start,
//   end: arg.end,
//   allDay: arg.allDay
// })
//   // calendar.unselect()  //선택취소 메서드
//

      // looks like multi-selection
      dateClick: function (dateClickInfo) {
        const gray = "#787878";
        const clickDate = dateClickInfo.dateStr;  //클릭한 날짜 변수에 저장

        if (dateClickInfo.dayEl.style.backgroundColor) {
          dateClickInfo.dayEl.style.backgroundColor = "";
          // alert('해당 날짜의 예약이 가능합니까?: ' + clickDate);
          impossibleDayString = impossibleDayString.filter(day => day !== clickDate);

        } else {
          dateClickInfo.dayEl.style.backgroundColor = gray;
          // alert('해당 날짜의 예약이 불가능합니까?: ' + clickDate);
          impossibleDayString.push(clickDate);
        }
      },


//   //캘린더에 아이콘만드는 코드
//   eventContent: {
//   html: `<div><img src="/images/room/ok.png" class="event-icon" /></div>`,
// },

      // events: []

    });

    calendar.render();
  });

  document.querySelector(".room-register-btn-js")
    .addEventListener("click", async () => {

      const csrf = document.querySelector("#csrf").value;

      const formData = new FormData();
      const roomIdEl = document.querySelector("#room-id");
      formData.append("roomId", roomIdEl.value);
      for (const day of impossibleDayString) {
        formData.append("impossibleDayString", day);
      }
      await fetch("http://localhost:8080/api/room/register/calendar", {
        method: "post",
        headers: {
          "X-CSRF-TOKEN": csrf
        },
        body: formData
      })
    })
})();
