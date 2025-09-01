<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ja">

<head>
<title>新規会員登録</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<!-- PC用のスタイルシート -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/registerpc.css"
	media="screen and (min-width: 769px)">

<!-- スマホ用のスタイルシート -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/registermobile.css"
	media="screen and (max-width: 768px)">
</head>
<body>
	<div class="form-wrapper">
		<div class="register-card">
			<h2 class="form-title">Sign Up</h2>

			<c:if test="${errorMsg.size() > 0}">
				<div class="error-container">
					<ul>
						<c:forEach var="msg" items="${errorMsg}">
							<li>${msg}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		
			<form action="${pageContext.request.contextPath}/register"
				method="post" class="contact-form">
				
				<div class="form-group">
					<label for="loginId">LoginID</label>
					<input 
						type="text"
						id="loginId"
						name="loginId"
						value="${user.loginId}"
						placeholder="1~8文字"
						required>
				</div>
				
				<div class="form-group">
					<label for="password">Password</label>
					<input 
						type="password"
						id="password"
						name="password"
						value="${user.password}"
						placeholder="2~10文字"
						required>
				</div>
				
				<div class="form-group">
					<label for="name">Name</label>
					<input
						type="text"
						id="name"
						name="name"
						value="${user.name}"
						placeholder="1~10文字"
						required>
				</div>
				
				<div class="button-group">
					<button type="submit" class="button create-button">
					CreateMyAccount
					</button>
				</div>
			</form>
		</div>
	</div>

	<a href="${pageContext.request.contextPath}/login"
		class="back-to-login">
		<span class="material-symbols-outlined">arrow_back</span>
		ログイン画面に戻る
	</a>
</body>
</html>