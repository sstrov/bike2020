<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<c:set var="disp" value="N"/>
<c:set var="startName" value="" />
<c:set var="endName" value="" />
<c:choose>
    <c:when test="${param.key eq '2006176973258' }"> <!-- 아라 -->
        <c:set var="startX" value="37.62"/> 
        <c:set var="startY" value="126.71"/>
        <c:set var="endX" value="37.59832"/>
        <c:set var="endY" value="126.800594"/>
        <c:set var="startName" value="아라서해갑문" />
        <c:set var="endName" value="아라한강갑문" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_001" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176973579' }"> <!--한강  -->
        <c:set var="startX" value="37.59832"/>
        <c:set var="startY" value="126.800594"/>
        <c:set var="endX" value="37.547197"/>
        <c:set var="endY" value="127.240648"/>
        <c:set var="startName" value="아라한강갑문" />
        <c:set var="endName" value="팔당대교" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_006" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176974165' }"> <!-- 남한강 -->
        <c:set var="startX" value="37.547197"/>
        <c:set var="startY" value="127.240648"/>
        <c:set var="endX" value="36.988046"/>
        <c:set var="endY" value="127.897960"/>
        <c:set var="startName" value="팔당대교" />
        <c:set var="endName" value="충주탄금대" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_002" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '9999' }"><!-- 북한강 -->
        <c:set var="startX" value="36.9870966"/>
        <c:set var="startY" value="127.8991464"/>
        <c:set var="endX" value="36.499034"/>
        <c:set var="endY" value="128.252944"/>
        <c:set var="startName" value="밝은광장" />
        <c:set var="endName" value="춘천 신매대교" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_004" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176974602' }"><!-- 새재 -->
        <c:set var="startX" value="36.9870966"/>
        <c:set var="startY" value="127.8991464"/>
        <c:set var="endX" value="36.499034"/>
        <c:set var="endY" value="128.252944"/>
        <c:set var="startName" value="충주탄금대" />
        <c:set var="endName" value="상주 상풍교" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_004" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176974907' }"> <!-- 낙동강 -->
        <c:set var="startX" value="36.5782802"/>
        <c:set var="startY" value="128.7509122"/>
        <c:set var="endX" value="35.1082572"/>
        <c:set var="endY" value="128.947433"/>
        <c:set var="startName" value="안동댐" />
        <c:set var="endName" value="낙동강 하구둑" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_003" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2008068569235' }"> <!-- 금강 -->
        <c:set var="startX" value="36.474314"/>
        <c:set var="startY" value="127.4816415"/>
        <c:set var="endX" value="36.0227534"/>
        <c:set var="endY" value="126.7427377"/>
        <c:set var="startName" value="대청댐" />
        <c:set var="endName" value="금강 하구둑" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_005" />
		<c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176975501' }"> <!-- 영산강 -->
        <c:set var="startX" value="35.3692155"/>
        <c:set var="startY" value="127.0156091"/>
        <c:set var="endX" value="34.806664"/>
        <c:set var="endY" value="126.445354"/>
        <c:set var="startName" value="담양댐" />
        <c:set var="endName" value="영산강 하구둑" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_007" />    
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2008068328326' }"> <!-- 북한강(경춘선) -->
        <c:set var="startX" value="37.553846"/> <!-- 37.553846, 127.312460 -->
        <c:set var="startY" value="127.312460"/>
        <c:set var="endX" value="37.920737"/>
        <c:set var="endY" value="127.713074"/>
        <c:set var="startName" value="밝은광장" />
        <c:set var="endName" value="신매대교" /> <!-- 37.920737, 127.713074 -->
        <c:set var="load_cd" value="ROAD_TYPE_NATION_008" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176976562' }"> <!-- 섬진강 -->
        <c:set var="startX" value="35.5132293"/>
        <c:set var="startY" value="127.1474565"/>
        <c:set var="endX" value="34.7343266"/>
        <c:set var="endY" value="127.7232383"/>
        <c:set var="startName" value="생활체육공원" />
        <c:set var="endName" value="수변공원" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_009" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176977035' }"> <!-- 오천 -->
        <c:set var="startX" value="36.7670008"/>
        <c:set var="startY" value="128.0163853"/>
        <c:set var="endX" value="36.5163355"/>
        <c:set var="endY" value="127.3375750"/>
        <c:set var="startName" value="행촌교차로" />
        <c:set var="endName" value="합강공원" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_009" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176977436' }"><!-- 동해안 강원 -->
        <c:set var="startX" value="38.5184505"/>
        <c:set var="startY" value="128.4131141"/>
        <c:set var="endX" value="37.1253889"/>
        <c:set var="endY" value="129.3591691"/>
        <c:set var="startName" value="통일전망대" />
        <c:set var="endName" value="고포마을" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_009" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006176979144' }"> <!-- 제주환상 -->
        <c:set var="startX" value="33.5114842"/>
        <c:set var="startY" value="126.5117553"/>
        <c:set var="endX" value="33.4621467"/>
        <c:set var="endY" value="126.9364270"/>
        <c:set var="startName" value="용두암" />
        <c:set var="endName" value="성산일출봉" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_010" />
        <c:set var="disp" value="Y"/>
    </c:when>

    <c:when test="${param.key eq '2006176978430' }"><!-- 동해안 경북 -->
        <c:set var="startX" value="36.977906"/>
        <c:set var="startY" value="129.402785"/>
        <c:set var="endX" value="36.268592"/>
        <c:set var="endY" value="129.374776"/>
        <c:set var="startName" value="울진대교" />
        <c:set var="endName" value="큰지경" />
        <c:set var="load_cd" value="ROAD_TYPE_NATION_013" />
        <c:set var="disp" value="Y"/>
    </c:when>
    <c:when test="${param.key eq '2006177003952' }"> <!-- 강릉경포호 -->
		<%-- <c:set var="startX" value="92"/>
		<c:set var="startY" value="133"/>
		<c:set var="endX" value="93"/>
		<c:set var="endY" value="132"/> --%>
		<c:set var="startX" value="37.821096"/>
        <c:set var="startY" value="128.863078"/>
        <c:set var="endX" value="37.773317"/>
        <c:set var="endY" value="128.919328"/>
		<c:set var="startName" value="사천진항" />
		<c:set var="endName" value="강릉항" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177006206' }"> <!-- 화천 파로호 -->
		<%-- <c:set var="startX" value="71"/>
		<c:set var="startY" value="138"/>
		<c:set var="endX" value="77"/>
		<c:set var="endY" value="139"/> --%>
		<c:set var="startX" value="38.079077"/>
        <c:set var="startY" value="127.642508"/>
        <c:set var="endX" value="38.118946"/>
        <c:set var="endY" value="127.995803"/>
		<c:set var="startName" value="연꽃단지" />
		<c:set var="endName" value="파로호" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177006946' }"> <!-- 옹진 덕적도 -->
		<%-- <c:set var="startX" value="46"/>
		<c:set var="startY" value="119"/>
		<c:set var="endX" value="46"/>
		<c:set var="endY" value="119"/> --%>
		<c:set var="startX" value="37.215759"/>
        <c:set var="startY" value="126.173591"/>
        <c:set var="endX" value="37.215759"/>
        <c:set var="endY" value="126.173591"/>
		<c:set var="startName" value="서포리해변" />
		<c:set var="endName" value="진리석착장" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177007822' }"> <!-- 파주DMZ -->
		<%-- <c:set var="startX" value="56"/>
		<c:set var="startY" value="133"/>
		<c:set var="endX" value="56"/>
		<c:set var="endY" value="133"/> --%>
		<c:set var="startX" value="37.858681"/>
        <c:set var="startY" value="126.759893"/>
        <c:set var="endX" value="37.858681"/>
        <c:set var="endY" value="126.759893"/>
		<c:set var="startName" value="임진각" />
		<c:set var="endName" value="65T통문" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177008547' }"> <!-- 옥천 향수 -->
		<%-- <c:set var="startX" value="72"/>
		<c:set var="startY" value="101"/>
		<c:set var="endX" value="71"/>
		<c:set var="endY" value="99"/> --%>
		<c:set var="startX" value="36.373528"/>
        <c:set var="startY" value="127.656263"/>
        <c:set var="endX" value="36.282528"/>
        <c:set var="endY" value="127.596888"/>
		<c:set var="startName" value="장계관광지" />
		<c:set var="endName" value="금강유원지" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177009037' }"> <!-- 정읍 정읍천 -->
		<%-- <c:set var="startX" value="58"/>
		<c:set var="startY" value="85"/>
		<c:set var="endX" value="58"/>
		<c:set var="endY" value="83"/> --%>
		<c:set var="startX" value="35.648677"/>
        <c:set var="startY" value="126.847109"/>
        <c:set var="endX" value="35.556914"/>
        <c:set var="endY" value="126.845916"/>
		<c:set var="startName" value="만석보" />
		<c:set var="endName" value="내장사" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177009732' }"> <!-- 신안 증도 -->
		<%-- <c:set var="startX" value="46"/>
		<c:set var="startY" value="71"/>
		<c:set var="endX" value="46"/>
		<c:set var="endY" value="71"/> --%>
		<c:set var="startX" value="35.010304"/>
        <c:set var="startY" value="126.167771"/>
        <c:set var="endX" value="35.010304"/>
        <c:set var="endY" value="126.167771"/>
		<c:set var="startName" value="갯벌생태전시관" />
		<c:set var="endName" value="보물섬전망대" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177010271' }"> <!-- 경주 역사탐방 -->
		<%-- <c:set var="startX" value="99"/>
		<c:set var="startY" value="90"/>
		<c:set var="endX" value="101"/>
		<c:set var="endY" value="89"/> --%>
		<c:set var="startX" value="35.830904"/>
        <c:set var="startY" value="129.172191"/>
        <c:set var="endX" value="35.78133"/>
        <c:set var="endY" value="129.283037"/>
		<c:set var="startName" value="태종무열왕릉" />
		<c:set var="endName" value="불국사" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '23' }"> <!-- ??? -->
		<c:set var="startX" value="35.294174"/>
        <c:set var="startY" value="128.696512"/>
        <c:set var="endX" value="35.294174"/>
        <c:set var="endY" value="128.696512"/>
		<c:set var="startName" value="람사르박물관" />
		<c:set var="endName" value="호반농로" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006177010856' }"> <!-- 제주해맞이해안로 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>


	<c:when test="${param.key eq '2006184064416' }"> <!-- 강화군(지붕 없는 박물관) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184065839' }"> <!-- 옹진의 아름다운 시시모도 자전거 여행길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184066964' }"> <!-- 군산 고군산도 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184067538' }"> <!-- 여수 금오도 해안도로 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184068393' }"> <!-- 고흥군 (거금도~소록도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184069640' }"> <!-- 완도 수목원 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184070497' }"> <!-- 느림의 미학 완도군 청산도 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2008069226642' }"> <!-- 항상 새로운 섬 완도군 생일도 자전거 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184071624' }"> <!-- 쉬미향~청용삼거리 저전거길(진도군) -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184072948' }"> <!-- 신안군(입해도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184074041' }"> <!-- 신안군(중도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184075290' }"> <!-- 신안군(임자도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184076249' }"> <!-- 신안군(자은ㆍ임태도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184078056' }"> <!-- 신안군(팔금ㆍ인좌도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184079381' }"> <!-- 신안군(비금ㆍ도초도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184080490' }"> <!-- 신안군(흑산도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184082213' }"> <!-- 신안군(하의ㆍ신의도) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184106471' }"> <!-- 우리島 Ulleung道 꿈이 있는 자전거吉 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184097472' }"> <!-- 환상의 사천시 신수도 바다 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184099443' }"> <!-- 경남 남해(남해대교~남해읍 선소) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184102142' }"> <!-- 제주도(구좌읍 해맞이 해안로) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184103222' }"> <!-- 제주 환상 자전거길(오조리~성산리) -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	<c:when test="${param.key eq '2006184104351' }"> <!-- 제주도(상모리~사계리) 자전거길 -->
		<c:set var="startX" value="33.498519"/>
        <c:set var="startY" value="126.874588"/>
        <c:set var="endX" value="33.452431"/>
        <c:set var="endY" value="126.928611"/>
		<c:set var="startName" value="김녕해수욕장" />
		<c:set var="endName" value="오조" />
		<c:set var="disp" value="Y"/>
	</c:when>
	
</c:choose>


<script type="text/javascript">
$(document).ready(function(){
	if('${startName}' != null && '${startName}' != ''){
		realTimeWeather('${startX}','${startY}',{id:'weather1'}, '${startName}');
		realTimeWeather('${endX}','${endY}',{id:'weather2'}, '${endName}');
	}
	if('${disp}' == 'Y'){
		$('.weather_box').css("display", "block");
	}
});

function realTimeWeather(x,y,options,plc){
var today=new Date();
var week=new Array('일','월','화','수','목','금','토');
var year=today.getFullYear();
var month=today.getMonth()+1;
var day=today.getDate();
var hours=today.getHours();
var minutes=today.getMinutes();
if(hours<10){hours='0'+hours;}	
if(month<10){month='0'+month;}
if(day<10){day='0'+day;}
today=year+"-"+month+"-"+day;

var _nx=x;
var _ny=y;
var chkArray=null;
var temperature=null;
var windDirection=null;
var windSpeed=null;
var $div=null;
var tempInfo=null;
var imgSrc=null;
var alt = null; 
if(hours>=24&&hours<3){hour='00';}
else if(hours>=3&&hours<6){hour='03';}
else if(hours>=6&&hours<9){hour='06';}
else if(hours>=9&&hours<12){hour='09';}
else if(hours>=12&&hours<15){hour='12';}
else if(hours>=15&&hours<18){hour='15';}
else if(hours>=18&&hours<21){hour='18';}
else if(hours>=21&&hours<24){hour='21';}


$.ajax({
	type:"GET",
	url:"https://api.openweathermap.org/data/2.5/weather?APPID=ecb8019e7071b4b4eaafc48012ba8ae2&lat="+_nx+"&lon="+_ny+"&mode=json&cnt=7&units=metric",
	dataType:"json",
	async:false,
	success:function(msg){
		console.log(msg);
		temperature=msg.main.temp;
		windSpeed=msg.wind.speed+'㎧';
		$div=jQuery('.'+options.id);
		tempInfo=temperature+'&#176;';
		imgSrc='https://openweathermap.org/img/w/'+msg.weather[0].icon+'.png'
		alt = msg.weather[0].description;
		if(windSpeed){
			$('#'+options.id).find('.wspeed').text(windSpeed);
		}
		if(!temperature){
			$('#'+options.id).find('.temper').html("날씨 데이터 갱신 중");
		}else{
			$('#'+options.id).find('.temper').html(tempInfo);
		}
		if(imgSrc){
			$('#'+options.id).find('img').attr('src',imgSrc);
			$('#'+options.id).find('img').attr('alt',alt);
		}
		//alert(options.id+"//" + temperature + "//" + windSpeed);
}});


}
</script>