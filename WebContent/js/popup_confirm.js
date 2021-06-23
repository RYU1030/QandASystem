// 質問内容確認画面、編集ボタンを押した際のポップアップ機能
function editPopup() {
  const popup = document.getElementById('edit-popup');
  if (!popup) return;

  const blackBg = document.getElementById('edit-popup-black-bg');
  const closeBtn = document.getElementById('edit-popup-close-btn');
  const cancelBtn = document.getElementById('edit-cancel-btn')
  const showBtn = document.getElementById('edit-btn');

  const editConfirmedBtn = document.getElementById('edit-confirmed-btn');
  const deleteConfirmedBtn = document.getElementById('delete-confirmed-btn');

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
  sendEditForm(editConfirmedBtn);
  function sendEditForm(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {
      document.editDeleteForm.action = "edit";
      document.editDeleteForm.submit();
    });
  }
  sendDeleteForm(deleteConfirmedBtn);
  function sendDeleteForm(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {
      document.editDeleteForm.action = "delete";
      document.editDeleteForm.submit();
    });
  }
}

// 質問内容確認画面、削除ボタンを押した際のポップアップ機能
function deletePopup() {
  const popup = document.getElementById('delete-popup');
  if (!popup) return;

  const blackBg = document.getElementById('delete-popup-black-bg');
  const closeBtn = document.getElementById('delete-popup-close-btn');
  const cancelBtn = document.getElementById('delete-cancel-btn')
  const showBtn = document.getElementById('delete-btn');

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
}

// 質問内容確認画面、回答送信ボタンを押した際のポップアップ機能
function sendAnswerPopup() {
  const popup = document.getElementById('send-answer-popup');
  if (!popup) return;

  const blackBg = document.getElementById('send-answer-popup-black-bg');
  const closeBtn = document.getElementById('send-answer-popup-close-btn');
  const cancelBtn = document.getElementById('send-answer-cancel-btn')
  const showBtn = document.getElementById('send-answer-btn');

  const answerRegistryBtn = document.getElementById('answer-registry-btn');

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
  sendAnswerForm(answerRegistryBtn);
  function sendAnswerForm(elem) {
    if (!elem) return;
    elem.addEventListener('click', function () {
      document.answerForm.submit();
    });
  }
}

editPopup();
deletePopup();
sendAnswerPopup();
