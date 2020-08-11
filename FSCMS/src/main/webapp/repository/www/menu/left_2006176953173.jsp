<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177043576' || roleUser:use(currentMenu.menuSn, '2006177043576')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177043576" target="_self" class="deplink"><span>국토종주자전거길</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177044469' || roleUser:use(currentMenu.menuSn, '2006177044469')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177044469" target="_self" class="deplink"><span>지자체명품 자전거길</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177044979' || roleUser:use(currentMenu.menuSn, '2006177044979')) }">class="on"</c:if>>
			<a href="/content.do?key=2006177044979" target="_self" class="deplink"><span>바다를 품은 섬 자전거길 23선</span></a>
			
		</li>
		
	</ul>
	
	
</nav>