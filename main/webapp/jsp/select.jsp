<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="domain.User"%>
<%@ page import="java.util.List"%>

<!-- ログイン失敗時のエラー表示 -->
<!-- セッションスコープからuserを取得し判断 -->
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<title>部位・種別選択</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/img.css">
</head>

<body>
<!-- Header -->
<jsp:include page="/jsp/header.jsp" />

<div class="select-list-header">
		<span class="material-symbols-outlined">fitness_center</span>
		<h1>select</h1>
		<span class="material-symbols-outlined">fitness_center</span>
	</div>
	
	<c:if test="${not empty errorMsgList}">
	    <div style="color: red; margin-bottom: 10px; text-align: center; padding-top: 80px;">
	        <c:forEach var="error" items="${errorMsgList}">
	            <p>${error}</p>
	        </c:forEach>
	    </div>
	</c:if>

		<form action="${pageContext.request.contextPath}/schedRegChose"
			method="post" style="text-align: center; margin-top: 200px;">

			<p>
				部位: <span class="material-symbols-outlined">Accessibility</span>
			</p>

				<div class="parts-selector">

					<input type="radio" name="partsId" id="part-1" value="1">
					<label for="part-1">胸</label>

					<input type="radio" name="partsId" id="part-2" value="2">
					<label for="part-2">背中</label>

					<input type="radio" name="partsId" id="part-3" value="3">
					<label for="part-3">腕・肩</label>

					<input type="radio" name="partsId" id="part-4" value="4">
					<label for="part-4">脚</label>
				</div>
			<p>
			<br>
			<br>
			<br>種別: <span class="material-symbols-outlined">Exercise</span>
			</p>
				<div class="type-selector">
					<input type="radio" name="typeId" id="type-1" value="1">
					<label for="type-1">マシン</label>
					<input type="radio" name="typeId" id="type-2" value="2">
					<label for="type-2">ケーブルマシン</label>
					<input type="radio" name="typeId" id="type-3" value="3">
					<label for="type-3">フリーウエイト</label>
					<input type="radio" name="typeId" id="type-4" value="4">
					<label for="type-4">ダンベル</label>
				<div class="fixed-button-container">
				<button type="submit" class="cute-button">Show List</button>
				</div>
		</form>
	<!-- Footer -->
	<jsp:include page="/jsp/menu.jsp" />
	<!-- Timer -->
	<script src="${pageContext.request.contextPath}/js/timer.js"></script>
</body>
</html>