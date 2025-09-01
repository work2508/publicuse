<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="dto.ToDoDTO"%>

<c:choose>
	<c:when test="${not empty dto}">

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/modal.css">

			<div class="modal-body">
			<!-- フォーム: データ送信部分 -->
			<form id="todo-edit-form" method="post">
				<!-- ログイン中ユーザーを特定するなら userId も -->
				<input type="hidden" name="id" value="${dto.id}">
				<input type="hidden" name="userId" value="${dto.userId}">
				<input type="hidden" name="exerciseId" value="${dto.exerciseId}">

				<div id="error-message" style="color: red; margin-bottom: 10px;"></div>

				<h2>種目:${dto.exerciseName}</h2>
				
				<p>
					重量: <select name="weight">
									<option value="">--重量を選択--</option>
							  <c:forEach var="w" items="${[3,3.5,4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10]}">
	    							<option value="${w}" <c:if test="${dto.weight eq w}"> selected="selected"</c:if>>${w}</option>
							</c:forEach>
    					 </select>
				</p>

				<p>
					回数: <select name="countnum">
						<option value="">--回数を選択--</option>
						<c:forEach var="i" begin="1" end="20">
							<option value="${i}"
								${dto.countNum == i ? 'selected="selected"' : ''}>${i}</option>
						</c:forEach>
					</select>
				</p>

				<p>
					SET: <select name="setnum">
						<option value="">--セット数を選択--</option>
						<c:forEach var="i" begin="1" end="10">
							<option value="${i}"
								${dto.setNum == i ? 'selected="selected"' : ''}>${i}</option>
						</c:forEach>
					</select>
				</p>

				<p>
					詳細:
					<textarea name="detail">${dto.detail}</textarea>
				</p>

				<p>
					メモ:
					<textarea name="memo">${dto.memo}</textarea>
				</p>

				<div class="form-actions">
					<button type="submit" class="save-button">保存</button>
				</div>

			</form>
			<button class="modal-close">
				<span class="material-symbols-outlined">close</span>
			</button>
		</div>
	</c:when>
	<c:otherwise>
		<p>予定情報が取得できません</p>
	</c:otherwise>
</c:choose>