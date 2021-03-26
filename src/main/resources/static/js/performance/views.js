(() => { // IIFE
  const ctx = document.getElementById("viewsChart").getContext('2d');

//y축 최근 조회수 담을 리스트
  const hitsList = [];
  const weeklyHitsEls = document.querySelectorAll(".weekly-hits-js");
  for (const weeklyHitsEl of weeklyHitsEls) {
    hitsList.push(weeklyHitsEl.value * 1);
  }

  //x축 최근 7일 날짜 담을 리스트
  const now = new Date();
  let dayOfWeek = [];
  for (let i = 0; i < 7; i++) {
    const date = new Date(now.setDate(now.getDate() - i));
    const month = ("0" + (1 + date.getMonth())).slice(-2);
    const day = ("0" + date.getDate()).slice(-2);
    dayOfWeek.push(month + "-" + day);
  }

  const viewsChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [dayOfWeek[6], dayOfWeek[5], dayOfWeek[4], dayOfWeek[3]
        , dayOfWeek[2], dayOfWeek[1], dayOfWeek[0]],
      datasets: [{
        label: '지난 7일간 조회수',
        data: [hitsList[6], hitsList[5], hitsList[4], hitsList[3], hitsList[2], hitsList[1], hitsList[0],
        ],
        borderColor: "rgba(255, 201, 14, 1)",
        backgroundColor: "rgba(255, 201, 14, 0.5)",
        fill: true,
        lineTension: 0
      }]
    },

    options: {
      responsive: true,
      title: {
        display: true,
        text: ''
      },
      tooltips: {
        mode: 'index',
        intersect: false,
      },
      hover: {
        mode: 'nearest',
        intersect: true
      },
      scales: {
        xAxes: [{
          display: true,
          scaleLabel: {
            display: true,
            labelString: '최근 7일'
          }
        }],
        yAxes: [{
          display: true,
          ticks: {
            suggestedMin: 0,
          },
          scaleLabel: {
            display: true,
            labelString: '조회수'
          }
        }]
      }
    }
  });

})()
