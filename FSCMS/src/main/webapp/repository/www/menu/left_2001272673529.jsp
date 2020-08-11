<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177051280' || roleUser:use(currentMenu.menuSn, '2006177051280')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177051280" target="_self" class="deplink"><span>개인정보 수정</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177052761' || roleUser:use(currentMenu.menuSn, '2006177052761')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177052761" target="_self" class="deplink"><span>인증수첩관리</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177053179' || roleUser:use(currentMenu.menuSn, '2006177053179')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177053179" target="_self" class="deplink"><span>나의 게시판</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177053496' || roleUser:use(currentMenu.menuSn, '2006177053496')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177053496" target="_self" class="deplink"><span>회원탈퇴</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008034087094' || roleUser:use(currentMenu.menuSn, '2008034087094')) }">class="on"</c:if>>
			<a href="/sub.do?key=2008034087094" target="_self" class="deplink"><span>비밀번호 확인</span></a>
			
		</li>
		
	</ul>
	
	
</nav>