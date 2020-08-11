<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>


<!-- fs_top_menu -->
<nav class="fs_top_menu" id="fs_top_menu">
	<h2 class="hide">주요메뉴</h2>
	<!-- global -->
	<div class="mglobal_box">
		<div class="global_btn">
			<ul>
				<li class="mg1"><a href="#none"><span>로그인</span></a></li>
				<li class="mg2"><a href="#none">회원가입</a></li>
				<li class="mg3"><a href="#none">ENGLISH</a></li>
			</ul>
		</div>
	</div>
	<!-- //global -->
	<ul class="lnb">
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>국토종주자전거길</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">국토종주자전거길</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/sub.do?key=2006176967425' />" target="_self" >국토종주 자전거길 소개</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2008068854028' />" target="_self">국토종주 자전거길 소개</a></li>
								
							</ul>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006176968322' />" target="_self" >국토종주 자전거길 정보</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176973258&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=44' />" target="_self">아라자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176973579&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=45' />" target="_self">한강종주자전거길(서울구간)</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176974165&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=46' />" target="_self">남한강자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2008068328326&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=47' />" target="_self">북한강(경춘선)자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176974602&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=48' />" target="_self">새재자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176974907&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=49' />" target="_self">낙동강자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2008068569235&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=50' />" target="_self">금강자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176975501&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=51' />" target="_self">영산강자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176976562&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=52' />" target="_self">섬진강자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176977035&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=53' />" target="_self">오천 자전거길</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176977436&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=54' />" target="_self">동해안자전거길(강원)</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176978430&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=55' />" target="_self">동해안자전거길(경북)</a></li>
								
								<li><a href="<c:url value='/bbs/road.do?key=2006176979144&amp;bbsCtgrySn=8&amp;bbsCtgrySubSn=56' />" target="_self">제주환상자전거길</a></li>
								
							</ul>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006176998516' />" target="_self" >국토종주 인증</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2006176999024' />" target="_self">종주인증제 안내</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006176999425' />" target="_self">인증센터 안내</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006176999884' />" target="_self">인증수첩 안내</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177000231' />" target="_self">사이버인증 안내</a></li>
								
							</ul>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>추천 자전거 여행길</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">추천 자전거 여행길</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/sub.do?key=2006176990912' />" target="_self" >아름다운 자전거길 100선</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2008069223071' />" target="_self">아름다운 자전거길 100선</a></li>
								
							</ul>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006176991587' />" target="_self" >지자체명품 자전거길</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2006176992038' />" target="_self">지자체명품자전거길 소개</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177003952' />" target="_self">강릉 경포호 산소길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177006206' />" target="_self">화천 파로호 100리 산소길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177006946' />" target="_self">옹진 덕적도 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177007822' />" target="_self">파주 DMZ 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177008547' />" target="_self">옥천 향수 100리길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177009037' />" target="_self">정읍 정읍천 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177009732' />" target="_self">신안 증도 자전거섬</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006177010271' />" target="_self">경주 역사탐방 자전거길</a></li>
								
								<li><a href="<c:url value='/sub.do?key=2006177010856' />" target="_self">제주 해맞이해안로</a></li>
								
							</ul>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006177001903' />" target="_self" >바다를 품은 섬 자전거길 23선</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2006184064416' />" target="_self">강화군(지붕 없는 박물관) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184065839' />" target="_self">옹진의 아름다운 시시모도 자전거 여행길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184066964' />" target="_self">군산 고군산도 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184067538' />" target="_self">여수 금오도 해안도로 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184068393' />" target="_self">고흥군 (거금도~소록도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184069640' />" target="_self">완도 수목원 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184070497' />" target="_self">느림의 미학 완도군 청산도 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2008069226642' />" target="_self">항상 새로운 섬 완도군 생일도 자전거</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184071624' />" target="_self">쉬미향~청용삼거리 저전거길(진도군)</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184072948' />" target="_self">신안군(입해도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184074041' />" target="_self">신안군(중도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184075290' />" target="_self">신안군(임자도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184076249' />" target="_self">신안군(자은&middot;임태도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184078056' />" target="_self">신안군(팔금&middot;인좌도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184079381' />" target="_self">신안군(비금&middot;도초도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184080490' />" target="_self">신안군(흑산도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184082213' />" target="_self">신안군(하의&middot;신의도) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184106471' />" target="_self">우리島 Ulleung道 꿈이 있는 자전거吉</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184097472' />" target="_self">환상의 사천시 신수도 바다 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184099443' />" target="_self">경남 남해(남해대교~남해읍 선소) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184102142' />" target="_self">제주도(구좌읍 해맞이 해안로) 자전거길</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184103222' />" target="_self">제주 환상 자전거길(오조리~성산리)</a></li>
								
								<li><a href="<c:url value='/content.do?key=2006184104351' />" target="_self">제주도(상모리~사계리) 자전거길</a></li>
								
							</ul>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>자전거 안전교육</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">자전거 안전교육</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/content.do?key=2006177027144' />" target="_self" >교육자료</a>
							
						</li>
						
						<li><a href="<c:url value='/content.do?key=2006177027535' />" target="_self" >자전거 안전 이용수칙</a>
							
						</li>
						
						<li><a href="<c:url value='/content.do?key=2006177028109' />" target="_self" >교육일정</a>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>저전거 정책</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">저전거 정책</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177030437' />" target="_self" >자전거 관련 법령</a>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006177031327' />" target="_self" >전기자전거</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/content.do?key=2006177031908' />" target="_self">전기자전거 소개</a></li>
								
								<li><a href="<c:url value='/bbs/list.do?key=2006177032900' />" target="_self">자전거도로 통행가능 자전거 정보</a></li>
								
							</ul>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006177033781' />" target="_self" >지자체 자전거 정보</a>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2008033864254' />" target="_self" >자전거 새소식</a>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>공모전</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">공모전</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177036193' />" target="_self" >공모전 소식</a>
							
						</li>
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177036600' />" target="_self" >수상작보기</a>
							
						</li>
						
						<li><a href="<c:url value='/sub.do?key=2006177037007' />" target="_self" >공모전 참여</a>
							
							<ul class="lnb_layer02">
								
								<li><a href="<c:url value='/sub.do?key=2006177037654' />" target="_self">신청하기</a></li>
								
								<li><a href="<c:url value='/sub.do?key=2006177037918' />" target="_self">수정하기</a></li>
								
								<li><a href="<c:url value='/sub.do?key=2006177038216' />" target="_self">참여완료</a></li>
								
							</ul>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>소통마당</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">소통마당</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177040381' />" target="_self" >뉴스/공지</a>
							
						</li>
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177040987' />" target="_self" >행사일정</a>
							
						</li>
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177041739' />" target="_self" >자료실</a>
							
						</li>
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177042056' />" target="_self" >자전거길 체험후기</a>
							
						</li>
						
						<li><a href="<c:url value='/bbs/list.do?key=2006177042648' />" target="_self" >질의/응답</a>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
		<li>
			<a href="#none' />" target="_self" class="lnb_title deplink" id="fs_top_menu"><span>자전거길 지도 정보</span></a>
			<div class="lnb_bg">
				<div class="lnb_wrap">
					<div class="tit">자전거길 지도 정보</div>
					
					<ul class="lnb_layer01">
						
						<li><a href="<c:url value='/content.do?key=2006177043576' />" target="_self" >국토종주자전거길</a>
							
						</li>
						
						<li><a href="<c:url value='/content.do?key=2006177044469' />" target="_self" >지자체명품 자전거길</a>
							
						</li>
						
						<li><a href="<c:url value='/content.do?key=2006177044979' />" target="_self" >바다를 품은 섬 자전거길 23선</a>
							
						</li>
						
					</ul>
					
				</div>
			</div>
		</li>
		
	</ul>
</nav>
<!-- //fs_top_menu -->
<!-- fs_top_menu -->
<nav class="fs_mtop_menu" id="fs_mtop_menu"></nav>
<!-- //fs_top_menu -->	

