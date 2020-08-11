<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177040381' || roleUser:use(currentMenu.menuSn, '2006177040381')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177040381" target="_self" class="deplink"><span>뉴스/공지</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177040987' || roleUser:use(currentMenu.menuSn, '2006177040987')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177040987" target="_self" class="deplink"><span>행사일정</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177041739' || roleUser:use(currentMenu.menuSn, '2006177041739')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177041739" target="_self" class="deplink"><span>자료실</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177042056' || roleUser:use(currentMenu.menuSn, '2006177042056')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177042056" target="_self" class="deplink"><span>자전거길 체험후기</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177042648' || roleUser:use(currentMenu.menuSn, '2006177042648')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177042648" target="_self" class="deplink"><span>질의/응답</span></a>
			
		</li>
		
	</ul>
	
	
</nav>