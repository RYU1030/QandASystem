<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Question, model.Answer, java.util.Date, java.util.List" %>
<% String errorMsgAnswer = (String) request.getAttribute("errorMsgAnswer"); %>
<% final String errorId = (String) request.getParameter("errorId"); %>
<% final String errorMsgKeyUnmatched = "「編集・削除キーが一致しません。」"; %>
<% String answererName = (String) request.getAttribute("answererName"); %>
<% String answerContent = (String) request.getAttribute("answerContent"); %>
<%
	@SuppressWarnings("unchecked")
	List<Answer> answerList = (List<Answer>) request.getAttribute("answerList");
	Question question = (Question) request.getAttribute("question");
%>
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
  <link rel="apple-touch-icon" sizes="180x180" href="./apple-touch-icon-180x180.png">
  <link rel="icon" type="image/x-icon" href="./favicon.ico">
</head>

<body>
  <header class="header-detailed-pages">
    <div class="header-container">
      <p class="site-name"><a href="list">みんなの質問部屋</a></p>
    </div>
  </header>
  <main>
    <section class="page-subtitle-container">
      <h1 class="page-subtitle">${question.title}</h1>
      <!-- 回答登録エラー発生時の処理 -->
      <% if (errorId != null && (errorId.equals("1") || errorId.equals("2"))) { %>
          <div class="error-msg-key-unmatched">
            <p><%= errorMsgKeyUnmatched %></p>
          </div>
      <% } %>
    </section>
    <section class="main-outer-wrapper-confirm">
      <div class="main-inner-wrapper-confirm">
        <div class="question-element-wrapper-confirm">
          <div class="question-element-row">
            <div class="question-element-title">
              <p>質問者名</p>
            </div>
            <div class="question-element">
              <p class="user-input questioner-name">${question.handleName}</p>
            </div>
          </div>
          <div class="question-element-row">
            <div class="question-element-title">
              <p>質問内容</p>
            </div>
            <div class="question-element">
              <p class="question-content">${question.contents}</p>
            </div>
          </div>
          <div class="question-element-row urgency-levels-confirmed">
            <div class="question-element-title">
              <p>緊急度</p>
            </div>
            <div class="question-element urgency-levels-user-input">
              <p class="confirmed-urgency">${question.urgencyMessage}</p>
            </div>
          </div>
          <div class="question-element-row">
            <div class="question-element-title">
              <p>編集・削除キー</p>
            </div>
            <div class="question-element edit-delete-execute">
              <form name="editForm" method="POST" action="edit">
                <p class="registered-key"><input type="text" name="edit-delete-key" class="user-input" placeholder="数字４桁以上"></p>
                <input name="question_id" type="hidden" value="${question.questionId}">
              </form>
              <button id="edit-btn" class="edit-execute action-btn">編集する</button>
              <button id="delete-btn" class="delete-execute action-btn">削除する</button>
            </div>
          </div>
        </div>
        <section class="answer-list-outer-wrapper">
          <div class="answer-list-wrapper">
          <% if (!answerList.isEmpty()) { %>
            <!-- 以降は後ほど繰り返し処理で描画する -->
            <c:forEach var="answer" items="${answerList}">
            <div class="each-answer">
              <p class="answerer-name answer-element">${answer.handleName}</p>
              <p class="posted-at answer-element">${answer.registDateTime}</p>
              <p class="answer answer-element">${answer.contents}</p>
            </div>
            </c:forEach>
          <!-- 未回答の場合は、下記を表示する -->
          <% } else { %>
            <p>未回答です。</p>
          <% } %>
          </div>
        </section>
        <section class="your-answer-outer-wrapper">
          <div class="your-answer-header">
            <h2 class="your-answer">あなたの回答</h2>
            <!-- 回答登録エラー発生時の処理 -->
            <% if (errorMsgAnswer != null) { %>
            <div class="error-msg">
              <p><%= errorMsgAnswer %></p>
            </div>
            <% } %>
          </div>
          <form class="answer-form" name="answerForm" action="answer?questionId=${question.questionId}" method="POST">
            <div class="your-answer-inner-wrapper">
              <div class="your-answer-element-row">
                <div class="your-answer-element-title">
                  <p class="your-name">名前（ハンドルネーム）</p>
                </div>
                <div class="your-answer-element">
                  <p><input type="text" class="user-input" name="answerer-name" placeholder="名前（ハンドルネーム）" value="<% if (answererName != null) { out.println(answererName); } %>"></p>
                </div>
              </div>
              <div class="your-answer-element-row">
                <div class="your-answer-element-title">
                  <p class="your-answer">内容</p>
                </div>
                <div class="your-answer-element your-answer-content">
                  <textarea name="answer-content" cols="30" rows="15" placeholder="内容"><% if (answerContent != null) { out.println(answerContent); } %></textarea>
                </div>
              </div>
            </div>
            <input name="question_id" type="hidden" value="${question.questionId}">
          </form>
          <div class="your-answer-element-row">
            <div class="your-answer-element-title">
              <p class="send-answer-left-filler"></p>
            </div>
            <div class="send-answer">
              <button class="send-answer-btn action-btn" id="send-answer-btn">回答する</button>
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
          <button id="edit-confirmed-btn" class="request-confirmed">編集する</button>
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
          <button class="request-confirmed">削除</button>
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
          <button id="answer-registry-btn" class="request-confirmed">回答送信</button>
          <button class="request-canceled" id="send-answer-cancel-btn">キャンセル</button>
        </div>
      </div>
      <div class="black-background" id="send-answer-popup-black-bg"></div>
    </div>
  </main>
  <script src="${pageContext.request.contextPath}/js/popup_confirm.js"></script>
</body>

</html>
