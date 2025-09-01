<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<title>登録完了</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
	<div class="profile-container">
		<div class="profile">
			<h2 class="complete-title">ユーザー情報変更完了</h2>
			<p class="complete-message">登録内容の変更が完了しました</p>
			<div class="button-right">
				<a href="${pageContext.request.contextPath}/login" class="submit-button">
				ログイン画面に戻る
				</a>
			</div>
		</div>
	</div>
</body>
</html>