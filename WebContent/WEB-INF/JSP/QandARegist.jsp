<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<% String questionerName = (String) request.getAttribute("questionerName"); %>
<% String questionTitle = (String) request.getAttribute("questionTitle"); %>
<% String questionContent = (String) request.getAttribute("questionContent"); %>
<% String EditDeleteKey = (String) request.getAttribute("EditDeleteKey"); %>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>みんなの質問部屋</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fontawesome.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/QAndACommon.css">
<link rel="apple-touch-icon" sizes="180x180"
	href="./apple-touch-icon-180x180.png">
<link rel="icon" type="image/x-icon" href="./favicon.ico">
</head>

<body>
	<header class="header-detailed-pages">
		<div class="header-container">
			<p class="site-name">
				<a href="list">みんなの質問部屋</a>
			</p>
		</div>
	</header>
	<main>
		<section class="page-subtitle-container">
			<h1 class="page-subtitle">質問登録</h1>
			<% if (errorMsg != null) { %>
			<div class="error-msg">
				<p><%= errorMsg %></p>
			</div>
			<% } %>
		</section>
		<section class="main-outer-wrapper-common">
			<div class="main-inner-wrapper-common">
				<form name="questionForm" action="regist" method="POST">
					<div class="question-element-row">
						<div class="question-element-title">
							<p>名前（ハンドルネーム）</p>
						</div>
						<div class="question-element">
							<p class="questioner-name">
								<input type="text" class="user-input" name="questioner-name"
									placeholder="名前（ハンドルネーム）"
									value="<% if (questionerName != null) { out.println(questionerName); } %>">
							</p>
						</div>
					</div>
					<div class="question-element-row">
						<div class="question-element-title">
							<p>タイトル</p>
						</div>
						<div class="question-element">
							<p class="question-title">
								<input type="text" class="user-input" name="question-title"
									placeholder="タイトル"
									value="<% if (questionTitle != null) { out.println(questionTitle); } %>">
							</p>
						</div>
					</div>
					<div class="question-element-row">
						<div class="question-element-title">
							<p>内容</p>
						</div>
						<div class="question-element">
							<textarea class="question-content" name="question-content"
								cols="30" rows="15" placeholder="内容">
								<% if (questionContent != null) { out.println(questionContent); } %>
							</textarea>
						</div>
					</div>
					<div class="question-element-row urgency-levels">
						<div class="question-element-title">
							<p>緊急度</p>
						</div>
						<div class="question-element urgency-levels-user-input">
							<p class="urgency">
								<label for="urgent"><input type="radio" name="urgency"
									value="1" id="urgent">急いでいます</label>
							</p>
							<p class="urgency">
								<label for="advisable"><input type="radio"
									name="urgency" value="2" id="advisable">困っています</label>
							</p>
							<p class="urgency">
								<label for="anytime"><input type="radio" name="urgency"
									value="3" id="anytime">いつでも</label>
							</p>
						</div>
					</div>
					<div class="question-element-row">
						<div class="question-element-title">
							<p>編集・削除キー</p>
						</div>
						<div class="register-cancel">
							<p class="register-cancel-key">
								<input type="text" class="user-input" name="register-cancel-key"
									placeholder="例）1234, abc"
									value="<% if (EditDeleteKey != null) { out.println(EditDeleteKey); } %>">
							</p>
						</div>
					</div>
				</form>
			</div>

			<div class="register-cancel-execute">
				<button class="register-btn action-btn" id="js-trigger">登録</button>
				<p class="cancel-btn">
					<a class="action-btn" href="list">キャンセル</a>
				</p>
			</div>
		</section>
		<div class="popup" id="js-popup">
			<div class="popup-inner">
				<div class="close-btn" id="js-close-btn">
					<i class="fas fa-times"></i>
				</div>
				<div class="request-confirmation">
					<p>登録します。よろしいですか。</p>
					<button id="question-registry-btn" class="request-confirmed">登録</button>
					<button class="request-canceled" id="request-canceled">キャンセル</button>
				</div>
			</div>
			<div class="black-background" id="js-black-bg"></div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/popup.js"></script>
</body>

</html>
