<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="domain.User"%>

<!-- ログイン失敗時のエラー表示 -->
<!-- セッションスコープからuserを取得し判断 -->
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp" />
</c:if>


<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css">

<title>プロフィール</title>

</head>
<body>
	<!-- Header -->
	<jsp:include page="/jsp/header.jsp" />
	<div class="profile-container">
		<div class="profile">
			<img class="profile__img"
				src="${pageContext.request.contextPath}/img/profileicon.png">
			<div class="profile__name">
				<c:out value="${user.name}" />
				<a href="${pageContext.request.contextPath}/edit" class="edit-link">
					<span class="material-symbols-outlined"> edit </span>
				</a> <a href="${pageContext.request.contextPath}/delete"
					class="delete-link"> <span class="material-symbols-outlined">
						delete </span>
				</a>
			</div>
			<div class="profile__desc">ここに簡単な自己紹介が入ります。ここに簡単な自己紹介が入ります。ここに簡単な自己紹介が入ります。ここに簡単な自己紹介が入ります。</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="menu.jsp" />
	<!-- Timer -->
	<script src="${pageContext.request.contextPath}/js/timer.js"></script>
</body>
</html>