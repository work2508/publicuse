// /js/timer.js
const canvas = document.getElementById('timerCanvas');

// canvas要素がまだ存在しない場合があるため、nullチェックを行う
if (canvas) {
    const ctx = canvas.getContext('2d');

    const startButton = document.getElementById('startButton');
    const stopButton = document.getElementById('stopButton');
    const resetButton = document.getElementById('resetButton');

    let startTime = 0;
    let elapsedTime = 0;
    let timerInterval;
    let isRunning = false;

    // 初期描画
    drawTimer(0);

    // タイマー描画関数
    function drawTimer(currentElapsedTime) {
      ctx.clearRect(0, 0, canvas.width, canvas.height); // キャンバスをクリア

      let totalSeconds = Math.floor(currentElapsedTime / 1000);
      let hours = Math.floor(totalSeconds / 3600);
      let minutes = Math.floor((totalSeconds % 3600) / 60);
      let seconds = totalSeconds % 60;

      const pad = (num) => String(num).padStart(2, '0');
      const timeString = `${pad(hours)}:${pad(minutes)}:${pad(seconds)}`;

      ctx.font = 'bold 20px "Inter", sans-serif'; // フォントサイズはCSSに合わせて調整
      ctx.fillStyle = '#1a202c'; // 文字色
      ctx.textAlign = 'center';
      ctx.textBaseline = 'middle';

      // canvasの中心に時間を描画
      ctx.fillText(timeString, canvas.width / 2, canvas.height / 2);
    }

    // 開始ボタンのイベントリスナー
    startButton.addEventListener('click', () => {
      if (!isRunning) {
        startTime = Date.now() - elapsedTime; // 停止からの再開に対応
        timerInterval = setInterval(() => {
          elapsedTime = Date.now() - startTime;
          drawTimer(elapsedTime);
        }, 1000); // 1秒ごとに更新
        isRunning = true;
      }
    });

    // 停止ボタンのイベントリスナー
    stopButton.addEventListener('click', () => {
      clearInterval(timerInterval);
      isRunning = false;
    });

    // リセットボタンのイベントリスナー
    resetButton.addEventListener('click', () => {
      clearInterval(timerInterval);
      elapsedTime = 0;
      isRunning = false;
      drawTimer(0); // タイマーを00:00:00で描画
    });
}
