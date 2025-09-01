<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ja">

<head>
<title>会員情報削除</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
	<div class="profile-container">
		<div class="profile">
			<div class="profile__name">会員情報削除完了</div>
				<div class="profile__desc">
					会員情報の削除が完了しました
				</div>
			<a href="${pageContext.request.contextPath}/login" class="edit-link">ログイン画面に戻る</a>
	 </div>
 </div>
</body>
</html>