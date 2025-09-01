<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="domain.User"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<title>会員情報編集</title>

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
			<h2>メンバー情報編集画面</h2>

			<c:if test="${errorMsg.size() > 0}">
				<ul>
					<c:forEach var="msg" items="${errorMsg}">
						<li style="color: red;">${msg}</li>
					</c:forEach>
				</ul>
			</c:if>

			<c:if test="${editError != null}">
				<div style="color: red;">
					<p>${editError}</p>
				</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/edit" method="post">
				<div class="form-group">
					<label for="editName">お名前</label> <input type="text" id="editName"
						name="editName" value="${user.name}" placeholder="1～10文字">
				</div>

				<div class="form-group password-group">
					<label for="editPassword">パスワード</label>
					<div class="password-input-container">
						<input type="password" id="editPassword" name="editPassword"
							placeholder="2～10文字"> <span
							class="material-symbols-outlined toggle-password-icon"
							id="togglePassword">visibility_off</span>
					</div>
				</div>
				<input type="submit" value="変更する（確認画面へ)" class="submit-button">
			</form>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="menu.jsp" />
</body>
</html>