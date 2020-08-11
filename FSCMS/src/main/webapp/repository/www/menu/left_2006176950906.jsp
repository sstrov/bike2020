<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177027144' || roleUser:use(currentMenu.menuSn, '2006177027144')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177027144" target="_self" class="deplink"><span>교육자료</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177027535' || roleUser:use(currentMenu.menuSn, '2006177027535')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177027535" target="_self" class="deplink"><span>자전거 안전 이용수칙</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177028109' || roleUser:use(currentMenu.menuSn, '2006177028109')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177028109" target="_self" class="deplink"><span>교육일정</span></a>
			
		</li>
		
	</ul>
	
	
</nav>