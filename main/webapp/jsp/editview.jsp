<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="dto.ToDoDTO" %>

<c:choose>
    <c:when test="${not empty dto}">
        <div class="modal-body">
            <button class="modal-close" type="button">
                <span class="material-symbols-outlined">close</span>
            </button>
		   		<h2>種目: ${dto.exerciseName}</h2>
				<p>重量: <fmt:formatNumber value="${dto.weight}" pattern="#.##" /></p>
		        <p>回数: ${dto.countNum}</p>
		  		<p>SET: ${dto.setNum}</p>
		  		<p>詳細: ${dto.detail}</p>
		  		<p>メモ: ${dto.memo}</p>
				<p> 
				<a href="javascript:void(0)" id="editLink"><span class="material-symbols-outlined">edit</span></a>
				<a href="javascript:void(0)" id="deleteLink"><span class="material-symbols-outlined">delete</span></a>
				</p>
	 	</div>
</c:when>
    <c:otherwise>
        <p>予定情報が取得できません</p>
    </c:otherwise>
</c:choose>