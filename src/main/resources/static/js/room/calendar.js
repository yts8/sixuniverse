

  document.addEventListener('DOMContentLoaded', function () {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
  headerToolbar: {
  left: 'prev,next today',
  right: "dayGridMonth",
  center: 'title',
  // right: 'dayGridMonth,timeGridWeek,timeGridDay'
},
  // initialDate: '2021-03-12', //숙소등록일 입력하기
  selectable: false,
  // displayEventEnd: true,
  // selectMirror: true,
  eventBackgroundColor: 'transparent',
  eventBorderColor: 'transparent',

  dayMaxEvents: true, // allow "more" link when too many events
  locale: 'ko', //언어 한국어

  select: function (arg) {

  alert('해당 날짜에 예약이 불가능합니까?');
  calendar.addEvent({
  title: '예약불가날짜',
  start: arg.start,
  end: arg.end,
  allDay: arg.allDay
})
  // calendar.unselect()  //선택취소 메서드

},

    // looks like multi-selection
//     dateClick: function (dateClickInfo) {
//   const gray = "#787878";
//
//   if (dateClickInfo.dayEl.style.backgroundColor) {
//   dateClickInfo.dayEl.style.backgroundColor = "";
// } else {
//   dateClickInfo.dayEl.style.backgroundColor = gray;
// }
// }
//   ,

  eventClick: function (arg) {
  if (confirm('예약가능 날짜를 취소 하시겠습니까?')) {
  arg.event.remove()


}
},
  //
  eventContent: {
  html: `<div><img src="/images/room/ok.png" class="event-icon" /></div>`,
},

  events: [
{
  title: 'event',
  start: '2021-03-07',
  // end: '2021-03-30',
  selectable: false,
},
{
  title: 'event',
  start: '2021-03-08',
  // end: '2021-03-30',
  selectable: true,
}, {
  title: 'event',
  start: '2021-03-09',
  // end: '2021-03-30',
  selectable: true,
}, {
  title: 'event',
  start: '2021-03-10',
  // end: '2021-03-30',
  selectable: true,
}, {
  title: 'event',
  start: '2021-03-11',
  // end: '2021-03-30',
  selectable: true,
}, {
  title: 'event',
  start: '2021-03-17',
  // end: '2021-03-30',
  selectable: true,
},
  ]


});

  calendar.render();

//
});
