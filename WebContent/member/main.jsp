<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- core library를 사용하기 위해 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 세션이 있으면 -->
<c:if test="${sessionScope.id != null}">
	${sessionScope.id}님 환영합니다.<br><br>
	<a href="<%=request.getContextPath()%>/UpdateMember.do">회원정보 수정</a> <br><br>
	<a href="<%=request.getContextPath()%>/Logout.do">로그아웃</a> <br><br>
	<a href="">회원 삭제</a> <br><br>
</c:if>

<!-- 세션이 없으면 -->
<c:if test="${sessionScope.id == null}">
	<a href="<%=request.getContextPath()%>/MemberForm.do">회원가입</a> <br><br>
	<a href="<%=request.getContextPath()%>/LoginForm.do">로그인</a> <br><br>
</c:if>