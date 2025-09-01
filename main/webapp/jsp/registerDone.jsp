<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ja">

<head>
<title>会員登録完了</title>

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
			<h2 class="form-title">Success!</h2>
			<p style="text-align: center;">Your registration is complete!</p>
			<div class="button-group" style="justify-content: center;">
				<a href="${pageContext.request.contextPath}/login" class="button">
					BACK TO SIGN IN
				</a>
			</div>
		</div>
	</div>
</body>
</html>