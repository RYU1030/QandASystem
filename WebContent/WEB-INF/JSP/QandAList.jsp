<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QandAList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QAndACommon.css">
  <title>みんなの質問部屋</title>
</head>

<body>
  <header>
    <div class="header-container">
      <p class="site-name">みんなの質問部屋</p>
    </div>
  </header>
  <main>
    <section class="top-wrapper">
      <div class="top-message-wrapper">
        <div class="top-message-inner-wrapper">
          <h1 class="top-message">一人で悩まず、<br>誰かに質問してみよう:)</h1>
          <p class="ask-questions"><a class="action-btn" href="regist">質問する</a></p>
        </div>
      </div>
    </section>
    <section class="questions-list-wrapper">
      <h2>質問一覧</h2>
      <table class="questions-list">
        <tr>
          <th>No.</th>
          <th>緊急度</th>
          <th>タイトル</th>
          <th>登録者</th>
          <th>登録日</th>
          <th class="last-th">更新日</th>
        </tr>
        <!-- 以下は後から繰り返し処理で描画する -->
        <tr>
          <td>1</td>
          <td>いつでも</td>
          <td><a href="confirm" class="article-title">PHPの変数に入った値をブラウザに表示させたい</a></td>
          <td>山田太郎</td>
          <td>3/7</td>
          <td>3/7</td>
        </tr>
        <tr>
          <td>2</td>
          <td>いつでも</td>
          <td><a href="#" class="article-title">〇〇の件について</a></td>
          <td>山田太郎</td>
          <td>3/7</td>
          <td>3/7</td>
        </tr>
        <tr>
          <td>3</td>
          <td>いつでも</td>
          <td><a href="#" class="article-title">〇〇の件について</a></td>
          <td>山田太郎</td>
          <td>3/7</td>
          <td>3/7</td>
        </tr>
        <tr class="fake-row">
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </section>
    <div class="page-bottom-wrapper">
      <p class="ask-questions-bottom"><a class="action-btn" href="regist">質問する</a></p>
    </div>
  </main>
</body>
</html>
