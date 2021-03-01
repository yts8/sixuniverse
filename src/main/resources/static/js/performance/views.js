(() => { // IIFE
  const ctx = document.getElementById("viewsChart").getContext('2d');

  const viewsChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['1', '2', '3', '4', '5', '6', '7'],
      datasets: [{
        label: '테스트 데이터셋',
        data: [
          10,
          3,
          30,
          23,
          10,
          5,
          50
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
        text: '라인 차트 테스트'
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
            labelString: 'x축'
          }
        }],
        yAxes: [{
          display: true,
          ticks: {
            suggestedMin: 0,
          },
          scaleLabel: {
            display: true,
            labelString: 'y축'
          }
        }]
      }
    }
  });

})()
