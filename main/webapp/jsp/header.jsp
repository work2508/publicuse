<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/jsp/google-fonts.jsp"%>

<script src="https://cdn.tailwindcss.com"></script>

<!-- header.jsp -->

<header class="header-links fixed-header-timer">
	<%-- 既存のヘッダーのクラスとタイマー用の固定ヘッダークラスを両方適用 --%>
	<div class="header-left">
		<img src="${pageContext.request.contextPath}/img/homeicon.png" alt="home">
	</div>

	<!-- タイマーのHTML要素を既存のヘッダーの中央に追加 -->
	<canvas id="timerCanvas" width="120" height="40"></canvas>
	<div class="timer-control-buttons">
		<button id="startButton">
			<span class="material-symbols-outlined">play_arrow</span>
		</button>
		
		<button id="stopButton" class="stop-button">
			<span class="material-symbols-outlined">pause</span>
		</button>
		
		<button id="resetButton">
			<span class="material-symbols-outlined">restart_alt</span>
		</button>
	</div>

	<div class="header-right">
		<a href="${pageContext.request.contextPath}/home" class="icon-link">
			<span class="material-symbols-outlined">home</span>
		</a> 
		<a href="${pageContext.request.contextPath}/logout" class="icon-link">
			<span class="material-symbols-outlined">logout</span>
		</a>
	</div>
</header>