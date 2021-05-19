<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Question, java.util.Date, java.util.List" %>
<%
	@SuppressWarnings("unchecked")
	List<Question> questionList = (List<Question>) request.getAttribute("questionList");
%>
<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QandAList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QAndACommon.css">
  <link rel="apple-touch-icon" sizes="180x180" href="./apple-touch-icon-180x180.png">
  <link rel="icon" type="image/x-icon" href="./favicon.ico">
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
        <c:forEach var="question" items="${questionList}">
          <tr>
            <td><c:out value="${question.questionId}" /></td>
            <td><c:out value="${question.urgencyMessage}" /></td>
            <td style="width: 450px;"><a href="confirm?questionId=${question.questionId}"><c:out value="${question.title}" /></a></td>
            <td style="width: 200px;"><c:out value="${question.handleName}" /></td>
            <td><c:out value="${question.registeredDate}" /></td>
            <td><c:out value="${question.updatedDate}" /></td>
          </tr>
        </c:forEach>
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
