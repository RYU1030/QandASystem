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
</head>

<body>
  <header class="header-detailed-pages">
    <div class="header-container">
      <p class="site-name"><a href="list">みんなの質問部屋</a></p>
    </div>
  </header>
  <main>
    <section class="page-subtitle-container">
      <h1 class="page-subtitle">質問編集</h1>
    </section>
    <section class="main-outer-wrapper-common">
      <div class="main-inner-wrapper-common">
        <div class="question-element-row">
          <div class="question-element-title">
            <p>名前（ハンドルネーム）</p>
          </div>
          <div class="question-element">
            <p class="questioner-name"><input type="text" class="user-input" placeholder="名前（ハンドルネーム）"></p>
          </div>
        </div>
        <div class="question-element-row">
          <div class="question-element-title">
            <p>タイトル</p>
          </div>
          <div class="question-element">
            <p class="question-title"><input type="text" class="user-input" placeholder="タイトル"></p>
          </div>
        </div>
        <div class="question-element-row">
          <div class="question-element-title">
            <p>内容</p>
          </div>
          <div class="question-element">
            <textarea class="question-content" cols="30" rows="15" placeholder="内容"></textarea>
          </div>
        </div>
        <div class="question-element-row urgency-levels">
          <div class="question-element-title">
            <p>緊急度</p>
          </div>
          <div class="question-element urgency-levels-user-input">
            <p class="urgency">
              <label for="urgent"><input type="radio" name="urgency" value="urgent" id="urgent">急いでいます</label>
            </p>
            <p class="urgency">
              <label for="advisable"><input type="radio" name="urgency" value="advisable" id="advisable">困っています</label>
            </p>
            <p class="urgency">
              <label for="anytime"><input type="radio" name="urgency" value="anytime" id="anytime">いつでも</label>
            </p>
          </div>
        </div>
        <div class="question-element-row">
          <div class="question-element-title">
            <p>編集・削除キー</p>
          </div>
          <div class="register-cancel">
            <p class="register-cancel-key"><input type="text" class="user-input" placeholder="数字4桁以上"></p>
          </div>
        </div>
      </div>
      <div class="register-cancel-execute">
        <button class="register-btn" id="js-trigger">更新</button>
        <p class="cancel-btn"><a href="list">キャンセル</a></p>
      </div>
    </section>
    <div class="popup" id="js-popup">
      <div class="popup-inner">
        <div class="close-btn" id="js-close-btn"><i class="fas fa-times"></i></div>
        <div class="request-confirmation">
          <p>更新します。よろしいですか。</p>
          <button class="request-confirmed">更新</button>
          <button>キャンセル</button>
        </div>
      </div>
      <div class="black-background" id="js-black-bg"></div>
    </div>
  </main>
  <script src="${pageContext.request.contextPath}/js/popup.js"></script>
</body>

</html>
