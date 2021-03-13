//
//   var createDate= $('#createDate').val();
//   var renewDate= $('#renewDate').val();
(() => {
  let impossibleDayString = [] //예약불가능날짜 담을 리스트
  const today = $('#renew-date').val();

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

      // displayEventEnd: true, //?

      eventBackgroundColor: 'transparent',
      eventBorderColor: 'transparent',

      // dayMaxEvents: true, // allow "more" link when too many events
      locale: 'ko', //언어 한국어

      events: [
        {
          id: 'past',
          title: 'past',
          start: '0000-00-00',
          end: '2021-03-14',
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

      ],


      //취소날짜 선택
      dateClick: function (dateClickInfo) {

        // alert(info.event.title);
        const gray = "#787878";
        const clickDate = dateClickInfo.dateStr;  //클릭한 날짜 변수에 저장

        const event = calendar.getEventById('today') // an event object!
        const today2 = event.start // a property (a Date object)- 참고

        // const future= 수정일+설정한 기간
        // if(dateClickInfo.date < today && dateClickInfo.date>future){
        if (dateClickInfo.date < today2) {
          alert("비활성화된 기간입니다.")
        } else {

          if (dateClickInfo.dayEl.style.backgroundColor) {
            dateClickInfo.dayEl.style.backgroundColor = "";
            // alert('해당 날짜의 예약이 가능합니까?: ' + clickDate);
            impossibleDayString = impossibleDayString.filter(day => day !== clickDate);

          } else {
            dateClickInfo.dayEl.style.backgroundColor = gray;
            // alert('해당 날짜의 예약이 불가능합니까?: ' + clickDate);
            impossibleDayString.push(clickDate);
            // console.log(dateClickInfo.dateStr)
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
