<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ja">

<head>
<title>会員登録確認</title>
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
			<h2 class="form-title">Confirmation</h2>
			<form action="${pageContext.request.contextPath}/registerExecute"
				method="post" class="form-flex">
				
				<div class="form-group">
					<label>LoginID</label>
					<p><c:out value='${user.loginId}'/></p>
					<input type="hidden" name="loginId"  value = "${user.loginId}">
				</div>

				<div class="form-group">
					<label>Password</label>
					<p>********</p>
					<input type="hidden" name="password"value = "${user.password}">
				</div>

				<div class="form-group">
					<label>Name</label>
					<p><c:out value='${user.name}' /></p>
					<input type="hidden" name="name" value = "${user.name}">
				</div>

				<div class="button-group">
					<button type="submit" class="button confirm-button">
					CREATE
					</button>
					
					<a href="${pageContext.request.contextPath}/register"
						class="button back-button">
						BACK
				   </a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>