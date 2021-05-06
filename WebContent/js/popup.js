// 質問登録/編集画面のポップアップ機能
function popup() {
  const popup = document.getElementById('js-popup');

  if (!popup) return;

  const blackBg = document.getElementById('js-black-bg');
  const closeBtn = document.getElementById('js-close-btn');
  const cancelBtn = document.getElementById('request-canceled');
  const showBtn = document.getElementById('js-trigger');

  closePopUp(blackBg);
  closePopUp(closeBtn);
  closePopUp(cancelBtn);
  closePopUp(showBtn);
  function closePopUp(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {

      const questionerName = document.getElementById('questioner-name').value;
      const questionTitle = document.getElementById('question-title').value;
      const questionContent = document.getElementById('question-content').value;
      const radio = document.getElementsByName("urgency");
      var questionUrgency = 0;
      for (i = 0; i < radio.length; i++) {
        if (radio[i].checked) {
          // ラジオボタン選択値の取得
          questionUrgency = radio[i].value;
        }
      }
      const questionEditDeleteKey = document.getElementById('register-cancel-key').value;

      document.getElementById('user-input-questioner-name').value = questionerName;
      document.getElementById('user-input-question-title').value = questionTitle;
      document.getElementById('user-input-question-content').value = questionContent;
      document.getElementById('user-input-question-urgency').value = questionUrgency;
      document.getElementById('user-input-register-cancel-key').value = questionEditDeleteKey;

      popup.classList.toggle('is-show');
    });
  }
}
popup();
