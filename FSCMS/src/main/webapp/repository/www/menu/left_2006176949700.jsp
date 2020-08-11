<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav class="snb_menu">
	<h2 class="hide">좌측메뉴</h2>
	
	
	<ul class="dep1">
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176990912' || roleUser:use(currentMenu.menuSn, '2006176990912')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006176990912" target="_self" class="deplink"><span>아름다운 자전거길 100선</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008069223071' || roleUser:use(currentMenu.menuSn, '2008069223071')) }">class="on"</c:if>>
					<a href="/content.do?key=2008069223071" target="_self">아름다운 자전거길 100선</a>
				</li>
				
			</ul>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176991587' || roleUser:use(currentMenu.menuSn, '2006176991587')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006176991587" target="_self" class="deplink"><span>지자체명품 자전거길</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006176992038' || roleUser:use(currentMenu.menuSn, '2006176992038')) }">class="on"</c:if>>
					<a href="/content.do?key=2006176992038" target="_self">지자체명품자전거길 소개</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177003952' || roleUser:use(currentMenu.menuSn, '2006177003952')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177003952" target="_self">강릉 경포호 산소길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177006206' || roleUser:use(currentMenu.menuSn, '2006177006206')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177006206" target="_self">화천 파로호 100리 산소길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177006946' || roleUser:use(currentMenu.menuSn, '2006177006946')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177006946" target="_self">옹진 덕적도 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177007822' || roleUser:use(currentMenu.menuSn, '2006177007822')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177007822" target="_self">파주 DMZ 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177008547' || roleUser:use(currentMenu.menuSn, '2006177008547')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177008547" target="_self">옥천 향수 100리길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177009037' || roleUser:use(currentMenu.menuSn, '2006177009037')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177009037" target="_self">정읍 정읍천 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177009732' || roleUser:use(currentMenu.menuSn, '2006177009732')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177009732" target="_self">신안 증도 자전거섬</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177010271' || roleUser:use(currentMenu.menuSn, '2006177010271')) }">class="on"</c:if>>
					<a href="/content.do?key=2006177010271" target="_self">경주 역사탐방 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177010856' || roleUser:use(currentMenu.menuSn, '2006177010856')) }">class="on"</c:if>>
					<a href="/sub.do?key=2006177010856" target="_self">제주 해맞이해안로</a>
				</li>
				
			</ul>
			
		</li>
		
		<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006177001903' || roleUser:use(currentMenu.menuSn, '2006177001903')) }">class="on"</c:if>>
			<a href="/sub.do?key=2006177001903" target="_self" class="deplink"><span>바다를 품은 섬 자전거길 23선</span></a>
			
			<ul class="dep2">
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184064416' || roleUser:use(currentMenu.menuSn, '2006184064416')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184064416" target="_self">강화군(지붕 없는 박물관) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184065839' || roleUser:use(currentMenu.menuSn, '2006184065839')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184065839" target="_self">옹진의 아름다운 시시모도 자전거 여행길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184066964' || roleUser:use(currentMenu.menuSn, '2006184066964')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184066964" target="_self">군산 고군산도 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184067538' || roleUser:use(currentMenu.menuSn, '2006184067538')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184067538" target="_self">여수 금오도 해안도로 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184068393' || roleUser:use(currentMenu.menuSn, '2006184068393')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184068393" target="_self">고흥군 (거금도~소록도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184069640' || roleUser:use(currentMenu.menuSn, '2006184069640')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184069640" target="_self">완도 수목원 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184070497' || roleUser:use(currentMenu.menuSn, '2006184070497')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184070497" target="_self">느림의 미학 완도군 청산도 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2008069226642' || roleUser:use(currentMenu.menuSn, '2008069226642')) }">class="on"</c:if>>
					<a href="/content.do?key=2008069226642" target="_self">항상 새로운 섬 완도군 생일도 자전거</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184071624' || roleUser:use(currentMenu.menuSn, '2006184071624')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184071624" target="_self">쉬미향~청용삼거리 저전거길(진도군)</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184072948' || roleUser:use(currentMenu.menuSn, '2006184072948')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184072948" target="_self">신안군(입해도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184074041' || roleUser:use(currentMenu.menuSn, '2006184074041')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184074041" target="_self">신안군(중도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184075290' || roleUser:use(currentMenu.menuSn, '2006184075290')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184075290" target="_self">신안군(임자도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184076249' || roleUser:use(currentMenu.menuSn, '2006184076249')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184076249" target="_self">신안군(자은&middot;임태도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184078056' || roleUser:use(currentMenu.menuSn, '2006184078056')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184078056" target="_self">신안군(팔금&middot;인좌도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184079381' || roleUser:use(currentMenu.menuSn, '2006184079381')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184079381" target="_self">신안군(비금&middot;도초도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184080490' || roleUser:use(currentMenu.menuSn, '2006184080490')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184080490" target="_self">신안군(흑산도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184082213' || roleUser:use(currentMenu.menuSn, '2006184082213')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184082213" target="_self">신안군(하의&middot;신의도) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184106471' || roleUser:use(currentMenu.menuSn, '2006184106471')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184106471" target="_self">우리島 Ulleung道 꿈이 있는 자전거吉</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184097472' || roleUser:use(currentMenu.menuSn, '2006184097472')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184097472" target="_self">환상의 사천시 신수도 바다 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184099443' || roleUser:use(currentMenu.menuSn, '2006184099443')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184099443" target="_self">경남 남해(남해대교~남해읍 선소) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184102142' || roleUser:use(currentMenu.menuSn, '2006184102142')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184102142" target="_self">제주도(구좌읍 해맞이 해안로) 자전거길</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184103222' || roleUser:use(currentMenu.menuSn, '2006184103222')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184103222" target="_self">제주 환상 자전거길(오조리~성산리)</a>
				</li>
				
				<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2006184104351' || roleUser:use(currentMenu.menuSn, '2006184104351')) }">class="on"</c:if>>
					<a href="/content.do?key=2006184104351" target="_self">제주도(상모리~사계리) 자전거길</a>
				</li>
				
			</ul>
			
		</li>
		
	</ul>
	
	
</nav>