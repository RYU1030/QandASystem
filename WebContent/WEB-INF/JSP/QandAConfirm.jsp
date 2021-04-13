<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>みんなの質問部屋</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QAndACommon.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QAndAConfirm.css">
</head>

<body>
  <header class="header-detailed-pages">
    <div class="header-container">
      <p class="site-name"><a href="list">みんなの質問部屋</a></p>
    </div>
  </header>
  <main>
    <section class="page-subtitle-container">
      <h1 class="page-subtitle">PHPの変数に入った値をブラウザに表示させたい</h1>
    </section>
    <section class="main-outer-wrapper-confirm">
      <div class="main-inner-wrapper-confirm">
        <div class="question-element-wrapper-confirm">
          <div class="question-element-row">
            <div class="question-element-title">
              <p>質問者名</p>
            </div>
            <div class="question-element">
              <p class="user-input questioner-name">山田太郎</p>
            </div>
          </div>
          <div class="question-element-row">
            <div class="question-element-title">
              <p>質問内容</p>
            </div>
            <div class="question-element">
              <p class="question-content">作りたいものはDBからランダムなidでのお題をとってきて、それをhtmlに表示させるというものを想定しています。
                phpの変数の値をhtml側で表示させようとしているのですが、phpの変数に値が入っていないというエラーが出てきてしまいます。console.logやvar_dumpで確認するとhtml側のphpの変数にきちんと値が入っているのですがブラウザでは値が入っていないと判断されエラーが出てきてしまいます。

                どのようにすればブラウザに変数に値が入っているかわかってもらえるのでしょうか？

                よろしくお願いします。</p>
            </div>
          </div>
          <div class="question-element-row urgency-levels-confirmed">
            <div class="question-element-title">
              <p>緊急度</p>
            </div>
            <div class="question-element urgency-levels-user-input">
              <p class="confirmed-urgency">急いでいます</p>
            </div>
          </div>
          <div class="question-element-row">
            <div class="question-element-title">
              <p>編集・削除キー</p>
            </div>
            <div class="question-element edit-delete-execute">
              <p class="registered-key"><input type="text" class="user-input" placeholder="数字４桁以上"></p>
              <button id="edit-btn" class="edit-execute action-btn">編集する</button>
              <button id="delete-btn" class="delete-execute action-btn">削除する</button>
            </div>
          </div>
        </div>
        <section class="answer-list-outer-wrapper">
          <div class="answer-list-wrapper">
            <!-- 以降は後ほど繰り返し処理で描画する -->
            <div class="each-answer">
              <p class="answerer-name answer-element">Aさん</p>
              <p class="posted-at answer-element">2021/03/11 9:00</p>
              <p class="answer answer-element">私は、○○だと思います。</p>
            </div>
            <div class="each-answer">
              <p class="answerer-name answer-element">Bさん</p>
              <p class="posted-at answer-element">2021/03/11 9:00</p>
              <p class="answer answer-element">私は、○○だと思います。</p>
            </div>
            <div class="each-answer">
              <p class="answerer-name answer-element">Cさん</p>
              <p class="posted-at answer-element">2021/03/11 9:00</p>
              <p class="answer answer-element">私は、○○だと思います。</p>
            </div>
          </div>
        </section>
        <section class="your-answer-outer-wrapper">
          <h2 class="your-answer">あなたの回答</h2>
          <div class="your-answer-inner-wrapper">
            <div class="your-answer-inner-wrapper">
              <div class="your-answer-element-row">
                <div class="your-answer-element-title">
                  <p class="your-name">名前（ハンドルネーム）</p>
                </div>
                <div class="your-answer-element">
                  <p><input type="text" class="user-input" placeholder="名前（ハンドルネーム）"></p>
                </div>
              </div>
              <div class="your-answer-element-row">
                <div class="your-answer-element-title">
                  <p class="your-answer">内容</p>
                </div>
                <div class="your-answer-element your-answer-content">
                  <textarea cols="30" rows="15" placeholder="内容"></textarea>
                </div>
              </div>
              <div class="your-answer-element-row">
                <div class="your-answer-element-title">
                  <p class="send-answer-left-filler"></p>
                </div>
                <div class="send-answer">
                  <button class="send-answer-btn action-btn" id="send-answer-btn">回答する</button>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </section>
    <div class="popup" id="edit-popup">
      <div class="popup-inner">
        <div class="close-btn" id="edit-popup-close-btn"><i class="fas fa-times"></i></div>
        <div class="request-confirmation">
          <p>編集画面に移ります。よろしいですか。</p>
          <a class="request-confirmed" href="edit">編集する</a>
          <button class="request-canceled" id="edit-cancel-btn">キャンセル</button>
        </div>
      </div>
      <div class="black-background" id="edit-popup-black-bg"></div>
    </div>
    <div class="popup" id="delete-popup">
      <div class="popup-inner">
        <div class="close-btn" id="delete-popup-close-btn"><i class="fas fa-times"></i></div>
        <div class="request-confirmation">
          <p>削除します。よろしいですか。</p>
          <a class="request-confirmed" href="delete">削除</a>
          <button class="request-canceled" id="delete-cancel-btn">キャンセル</button>
        </div>
      </div>
      <div class="black-background" id="delete-popup-black-bg"></div>
    </div>
    <div class="popup" id="send-answer-popup">
      <div class="popup-inner">
        <div class="close-btn" id="send-answer-popup-close-btn"><i class="fas fa-times"></i></div>
        <div class="request-confirmation">
          <p>回答を送信します。よろしいですか。</p>
          <a class="request-confirmed" href="answer">回答送信</a>
          <button class="request-canceled" id="send-answer-cancel-btn">キャンセル</button>
        </div>
      </div>
      <div class="black-background" id="send-answer-popup-black-bg"></div>
    </div>
  </main>
  <script src="${pageContext.request.contextPath}/js/popup_confirm.js"></script>
</body>

</html>
