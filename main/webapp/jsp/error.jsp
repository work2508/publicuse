<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">
<head>
<title>エラー</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/error.css"
	media="screen and (max-width: 768px)">

<%@ include file="/jsp/google-fonts.jsp"%>
</head>
<body>
		<div class="error-container">
			<div class="error-icon">
				<span role="img" aria-label="警告">⚠️</span>
			</div>
					<h1 class="error-title">エラー</h1>
						<p class="error-message">
						読み込み中に何らかの問題が発生しました。<br> 少し時間をおいてから、もう一度お試しください。
						</p>
							<button class="retry-button" onclick="location.reload()">
								<span class="material-symbols-outlined"> refresh </span>
							</button>
				<a href="${pageContext.request.contextPath}/home" class="icon-button">
					<span class="material-symbols-outlined"> home </span>
				</a>
		</div>
	</body>
</html>