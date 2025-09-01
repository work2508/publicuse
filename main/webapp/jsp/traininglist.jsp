<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>

<%@ page import="domain.User"%>
<%@ page import="dto.ExercisesDTO"%>

<!-- ログイン失敗時のエラー表示 -->
<!-- セッションスコープからuserを取得し判断 -->
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"/>
</c:if>

<!DOCTYPE html>

<html lang="ja">

<head>
<title>エクササイズ一覧</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/training.css">
</head>

<body>
	<!-- Header -->
	<jsp:include page="/jsp/header.jsp" />

	<div class="exercise-list-header">
		<span class="material-symbols-outlined">fitness_center</span>
		<h1>ExerciseList</h1>
		<span class="material-symbols-outlined">fitness_center</span>
	</div>

	<c:if test="${not empty errorMsgList}">
		<div style="color: red; margin-bottom: 10px;">
			<c:forEach var="error" items="${errorMsgList}">
				<p>${error}</p>
			</c:forEach>
		</div>
	</c:if>

	<c:if test="${not empty successMessage}">
		<p style="color: green;">${successMessage}</p>
		<c:remove var="successMessage" scope="session" />
	</c:if>

	<div class="form-container">

		<form action="${pageContext.request.contextPath}/schedRegDo" method="post">

			<c:if test="${not empty exerciseslist}">

				<div class="form-group">
					<label><b>トレーニング種目を選択:</b></label>
						<select name="exerciseid">
							<option value="">--トレーニング種目を選択--</option>
								<c:forEach var="e" items="${exerciseslist}">
									<option value="${e.exerciseId}">${e.exerciseName}</option>
								</c:forEach>
						</select>
				</div>
			</c:if>


			<c:if test="${empty exerciseslist}">
				<p class="text-center text-lg mt-8">該当データなし</p>
			</c:if>

			<!-- 重量を選択 -->
			<div class="form-group">
				<label for="weight"><b>重量を選択:</b></label>
				<select id="weight"	name="weight">
					<option value="">--重量を選択--</option>
					<option value="3">3</option>
					<option value="3.5">3.5</option>
					<option value="4">4</option>
					<option value="4.5">4.5</option>
					<option value="5">5</option>
					<option value="5.5">5.5</option>
					<option value="6">6</option>
					<option value="6.5">6.5</option>
					<option value="7">7</option>
					<option value="7.5">7.5</option>
					<option value="8">8</option>
					<option value="8.5">8.5</option>
					<option value="9">9</option>
					<option value="9.5">9.5</option>
					<option value="10">10</option>
					<option value="12">12</option>
					<option value="14">14</option>
					<option value="16">16</option>
					<option value="18">18</option>
					<option value="20">20</option>
					<option value="25">25</option>
					<option value="30">30</option>
					<option value="35">35</option>
					<option value="40">40</option>
					<option value="45">45</option>
					<option value="50">50</option>
					<option value="60">60</option>
					<option value="70">70</option>
					<option value="80">80</option>
					<option value="90">90</option>
					<option value="100">100</option>
				</select>
			</div>

			<!-- 回数を選択 -->
			<div class="form-group">
				<label for="reps"><b>回数を選択:</b></label> <select id="reps"
					name="countnum">
					<option value="">--回数を選択--</option>
						<c:forEach var="i" begin="1" end="20">
					<option value="${i}">${i}</option>
						</c:forEach>
				</select>
			</div>

			<!-- セット数を選択 -->
			<div class="form-group">
				<label for="sets"><b>SET数を選択:</b></label>
				<select id="sets" name="setnum">
					<option value="">--SET数を選択--</option>
						<c:forEach var="i" begin="1" end="10">
					<option value="${i}">${i}</option>
						</c:forEach>
				</select>
			</div>

			<!-- 詳細 -->
			<div class="form-group">
				<label for="detail"><b>詳細:</b></label>
				<input type="text" id="detail" name="detail" maxlength="50" placeholder="詳細を記入...">
			</div>

			<!-- メモ -->
			<div class="form-group">
				<label for="memo"><b>メモ:</b></label>
				<textarea id="memo" name="memo" maxlength="50" placeholder="メモを記入..."></textarea>
			</div>

			<div class="button-container">
				<button type="submit" class="register-button mt-6">登録</button>
			</div>
		</form>
	</div>
	<!-- Footer -->
	<jsp:include page="menu.jsp" />
	<!-- Timer -->
	<script src="${pageContext.request.contextPath}/js/timer.js"></script>
</body>
</html>