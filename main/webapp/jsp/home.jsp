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
<title>ホーム</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>

<%@ include file="/jsp/google-fonts.jsp"%>

<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/main.min.css'
	rel='stylesheet' />
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/homecalendar.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/homepc.css"
	media="screen and (min-width: 769px)">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/homemobile.css"
	media="screen and (max-width: 768px)">
</head>
<body>
	<!-- Header -->
	<jsp:include page="/jsp/header.jsp" />
	
	<!-- Calendar -->
	<div class="calendar-container">
		<jsp:include page="/jsp/calendar.jsp" />
	</div>
	
	<!-- Footer -->
	<jsp:include page="/jsp/menu.jsp" />
	
	<!-- Timer -->
	<script src="${pageContext.request.contextPath}/js/timer.js"></script>
</body>
</html>