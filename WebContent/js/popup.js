// 質問登録/編集画面のポップアップ機能
function popup() {
  const popup = document.getElementById('js-popup');

  if (!popup) return;

  const blackBg = document.getElementById('js-black-bg');
  const closeBtn = document.getElementById('js-close-btn');
  const cancelBtn = document.getElementById('request-canceled');
  const showBtn = document.getElementById('js-trigger');

  const questionRegistryBtn = document.getElementById('question-registry-btn');

  closePopUp(blackBg);
  closePopUp(closeBtn);
  closePopUp(cancelBtn);
  closePopUp(showBtn);
  function closePopUp(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {
      popup.classList.toggle('is-show');
    });
  }

  sendQuestionForm(questionRegistryBtn);
  function sendQuestionForm(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {
      document.questionForm.submit();
    })
  }
}
popup();
