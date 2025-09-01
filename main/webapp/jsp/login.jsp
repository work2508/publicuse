
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ja">

<head>
<title>LOGIN</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/startmobile.css"
	media="screen and (max-width: 768px)">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/startpc.css"
	media="screen and (min-width: 769px)">
</head>

<body>
	<h1>
		<img src="${pageContext.request.contextPath}/img/icon.png" alt="ログイン">
	</h1>

	<!-- ログイン失敗時のエラ表示 -->
	<!-- {loginError} = コントローラー側でリクエストスコープへ保存済み -->
	<c:if test="${loginError != null}">
		<div style="color: red;">
			<p>${loginError}</p>
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/login" method="post">

		<div class="login-group-container">
			
			<div class="input-fields-group">
				
				<div class="input-group">

					<label>UserID</label> <input type="text" name="loginId">
				</div>
				<div class="input-group">
					<label>PASSWORD</label> <input type="password" name="password">
				</div>
			</div>
			
			<button type="submit" class="login-button">
				<span class="material-symbols-outlined">Login</span> LOGIN
			</button>
		</div>
	</form>
	<div class="fixed-button-container">
		<a href="${pageContext.request.contextPath}/register"
		class="register-button"> <span class="material-symbols-outlined">person_add</span>
		Sign up here!
		</a>
	</div>
</body>
</html>