<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<title>変更情報確認</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css">
</head>

<body>
	<a href="javascript:history.back()" class="back-icon-link"> <span
		class="material-symbols-outlined back-icon">arrow_back</span>
	</a>

	<div class="profile-container">
		<div class="profile">
			<h2>メンバー情報確認画面</h2>
			<p class="confirm-message">下記の内容でよろしければ、変更ボタンを押してください。</p>
			<form action="${pageContext.request.contextPath}/editConfirm"
				method="post">
				<div class="form-group">
					<label>Name</label>
					<p class="confirm-value"><c:out value="${editName}" /></p>
					<input type="hidden" name="editName" value="${editName}">
				</div>
				<div class="form-group">
					<label>Password</label>
					<p class="confirm-value">••••••••</p>
					<input type="hidden" name="editPassword" value="${editPassword}">
				</div>
				<input type="hidden" name="loginId" value="${user.loginId}" >
				<input type="submit" value="変更する" class="submit-button">
			</form>
		</div>
	</div>
	<!-- Footer -->
<jsp:include page="menu.jsp" />
</body>