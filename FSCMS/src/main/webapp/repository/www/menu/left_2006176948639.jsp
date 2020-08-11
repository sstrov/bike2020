<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176967425' || roleUser:use(currentMenu.menuSn, '2006176967425')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006176967425" target="_self" class="deplink"><span>국토종주 자전거길 소개</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008068854028' || roleUser:use(currentMenu.menuSn, '2008068854028')) }">class="on"</c:if>>
					<a href="/content.do?key=2008068854028" target="_self">국토종주 자전거길 소개</a>
				</li>
				
			</ul>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176968322' || roleUser:use(currentMenu.menuSn, '2006176968322')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006176968322" target="_self" class="deplink"><span>국토종주 자전거길 정보</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176973258' || roleUser:use(currentMenu.menuSn, '2006176973258')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176973258&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=44" target="_self">아라자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176973579' || roleUser:use(currentMenu.menuSn, '2006176973579')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176973579&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=45" target="_self">한강종주자전거길(서울구간)</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176974165' || roleUser:use(currentMenu.menuSn, '2006176974165')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176974165&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=46" target="_self">남한강자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008068328326' || roleUser:use(currentMenu.menuSn, '2008068328326')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2008068328326&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=47" target="_self">북한강(경춘선)자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176974602' || roleUser:use(currentMenu.menuSn, '2006176974602')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176974602&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=48" target="_self">새재자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176974907' || roleUser:use(currentMenu.menuSn, '2006176974907')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176974907&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=49" target="_self">낙동강자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008068569235' || roleUser:use(currentMenu.menuSn, '2008068569235')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2008068569235&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=50" target="_self">금강자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176975501' || roleUser:use(currentMenu.menuSn, '2006176975501')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176975501&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=51" target="_self">영산강자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176976562' || roleUser:use(currentMenu.menuSn, '2006176976562')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176976562&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=52" target="_self">섬진강자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176977035' || roleUser:use(currentMenu.menuSn, '2006176977035')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176977035&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=53" target="_self">오천 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176977436' || roleUser:use(currentMenu.menuSn, '2006176977436')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176977436&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=54" target="_self">동해안자전거길(강원)</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176978430' || roleUser:use(currentMenu.menuSn, '2006176978430')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176978430&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=55" target="_self">동해안자전거길(경북)</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176979144' || roleUser:use(currentMenu.menuSn, '2006176979144')) }">class="on"</c:if>>
					<a href="/bbs/road.do?key=2006176979144&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=56" target="_self">제주환상자전거길</a>
				</li>
				
			</ul>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176998516' || roleUser:use(currentMenu.menuSn, '2006176998516')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006176998516" target="_self" class="deplink"><span>국토종주 인증</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176999024' || roleUser:use(currentMenu.menuSn, '2006176999024')) }">class="on"</c:if>>
					<a href="/content.do?key=2006176999024" target="_self">종주인증제 안내</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176999425' || roleUser:use(currentMenu.menuSn, '2006176999425')) }">class="on"</c:if>>
					<a href="/content.do?key=2006176999425" target="_self">인증센터 안내</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176999884' || roleUser:use(currentMenu.menuSn, '2006176999884')) }">class="on"</c:if>>
					<a href="/content.do?key=2006176999884" target="_self">인증수첩 안내</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177000231' || roleUser:use(currentMenu.menuSn, '2006177000231')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177000231" target="_self">사이버인증 안내</a>
				</li>
				
			</ul>
			
		</li>
		
	</ul>
	
	
</nav>