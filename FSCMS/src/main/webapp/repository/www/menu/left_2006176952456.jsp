<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177036193' || roleUser:use(currentMenu.menuSn, '2006177036193')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177036193" target="_self" class="deplink"><span>공모전 소식</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177036600' || roleUser:use(currentMenu.menuSn, '2006177036600')) }">class="on"</c:if>>
			<a href="/bbs/list.do?key=2006177036600" target="_self" class="deplink"><span>수상작보기</span></a>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177037007' || roleUser:use(currentMenu.menuSn, '2006177037007')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177037007" target="_self" class="deplink"><span>공모전 참여</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177037654' || roleUser:use(currentMenu.menuSn, '2006177037654')) }">class="on"</c:if>>
					<a href="/sub.do?key=2006177037654" target="_self">신청하기</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177037918' || roleUser:use(currentMenu.menuSn, '2006177037918')) }">class="on"</c:if>>
					<a href="/sub.do?key=2006177037918" target="_self">수정하기</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177038216' || roleUser:use(currentMenu.menuSn, '2006177038216')) }">class="on"</c:if>>
					<a href="/sub.do?key=2006177038216" target="_self">참여완료</a>
				</li>
				
			</ul>
			
		</li>
		
	</ul>
	
	
</nav>