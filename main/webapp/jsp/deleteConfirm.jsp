<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<title>削除</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
<a href="javascript:history.back()" class="back-icon-link"><span
   class="material-symbols-outlined back-icon">arrow_back</span>
</a>
	<div class="profile-container">
	<div class="profile">
	<div class="profile__name">会員情報削除</div>
	<div class="profile__desc">下記の内容でよろしければ、退会ボタンを押してください。</div>
		<c:if test="${not empty deleteError}">
			<div style="color: red; margin-top: 10px;">
				<p>${deleteError}</p>
			</div>
		</c:if>
				<h2>会員情報</h2>
				<form action="${pageContext.request.contextPath}/delete" method="post">
					<table class="profile-table">
						<tr>
							<th>お名前</th>
								<td><c:out value="${user.name}" /></td>
						</tr>
					</table>
						<input type="hidden" name="deleteLoginId" value="<c:out value='${user.loginId}' />">
						<input type="hidden" name="deleteUserId" value="<c:out value='${user.userId}' />">
					      	<div class="button-group">
							    <input type="submit" value="退会する" class="delete-button">
							   	<a href="${pageContext.request.contextPath}/home" class="cancel-button">キャンセル</a>
							</div>
				</form>
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="menu.jsp" />
	</body>
</html>