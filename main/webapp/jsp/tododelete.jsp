<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
<c:choose>
    <c:when test="${not empty dto}">
	
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/modal.css">
	
	<div class="modal-body">
		<button class="modal-close">
			<span class="material-symbols-outlined">close</span>
		</button>
	
		<p>本当にこの予定を削除しますか？</p>
		<h2>種目:${dto.exerciseName}</h2>
		<div class="delete-actions">
			<form action="${pageContext.request.contextPath}/schedDelete" method="post">
					<input type="hidden" name="id" value="${dto.id}">
					<button type="submit" class="delete-button-style">削除</button>
			</form>
				    <button class="cancel-button-style" type="button">キャンセル</button>	
		</div>
	</div>
	</c:when>
    <c:otherwise>
        <p>予定情報が取得できません</p>
    </c:otherwise>
</c:choose>