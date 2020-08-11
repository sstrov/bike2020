<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177030437' || roleUser:use(currentMenu.menuSn, '2006177030437')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177030437" target="_self" class="deplink"><span>자전거 관련 법령</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177031327' || roleUser:use(currentMenu.menuSn, '2006177031327')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177031327" target="_self" class="deplink"><span>전기자전거</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177031908' || roleUser:use(currentMenu.menuSn, '2006177031908')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177031908" target="_self">전기자전거 소개</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177032900' || roleUser:use(currentMenu.menuSn, '2006177032900')) }">class="on"</c:if>>
					<a href="/bbs/list.do?key=2006177032900" target="_self">자전거도로 통행가능 자전거 정보</a>
				</li>
				
			</ul>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177033781' || roleUser:use(currentMenu.menuSn, '2006177033781')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177033781" target="_self" class="deplink"><span>지자체 자전거 정보</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008033864254' || roleUser:use(currentMenu.menuSn, '2008033864254')) }">class="on"</c:if>>
			<a href="/sub.do?key=2008033864254" target="_self" class="deplink"><span>자전거 새소식</span></a>
			
		</li>
		
	</ul>
	
	
</nav>