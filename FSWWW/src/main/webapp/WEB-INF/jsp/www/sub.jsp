<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!doctype html>
<html lang="ko" id="no-fouc">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv='X-UA-Compatible' content='IE=edge' /><!-- 호환성 -->
	<meta name="generator" content="EditPlus®" /><!-- 사용프로그램 -->
	<meta name="format-detection" content="telephone=no,email=no,address=no" /><!-- 전화번호, 주소, 메일등 링크로 인식방지 -->
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1" /><!-- 화면맞춤 -->
	<meta name="author" content="저작자" /><!-- 저작자 -->
	<meta name="keywords" content="키워드" /><!-- 키워드 -->
	<meta name="description" content="설명" /><!-- 웹페이지의 축약된 설명을 제공_최대45자(네이버 웹마스터도구 기준) -->
	<meta name="copyright" content="Copyright &copy; 2020 CHEONGNAMDAE. All Rights Researved" /><!-- 저작권 -->
	<!-- 오픈그래프_소셜미디어로 공유될 때 우선적으로 활용되는 정보 -->
	<meta property="og:type" content="website" />
	<meta property="og:title" content="타이틀" />
	<meta property="og:description" content="설명" />
	<meta property="og:image" content="http://홈페이지주소/경로/파일이름" />
	<meta property="og:url" content="http://홈페이지주소" />
	<!-- //오픈그래프_소셜미디어로 공유될 때 우선적으로 활용되는 정보 -->
	<title>자전거행복나눔</title><!-- 최대15자(네이버 웹마스터도구 기준) -->
	<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" /><!-- 파비콘 -->

	<!-- css -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/bootstrap/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/bootstrap/css/bootstrap-theme.css' />" />

	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" />
	<link type="text/css" rel="stylesheet" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css" />
	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Myeongjo&display=swap" />

	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/animate.css' />" /><!-- 애니메이션 효과 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/html5_reset.css' />" /><!-- 리셋 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_component.css' />" /><!-- 반복사용 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_layout.css' />" /><!-- 전체 레이아웃 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_content.css' />" /><!-- 컨텐츠 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/animate.css' />" /><!-- 애니메이션 효과 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/jquery.bxslider.css' />"><!-- //슬라이드 -->
	<!-- //css -->

	<!-- script -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/jquery-1.11.3.min.js' />"></script><!-- 쿼리구동 기본파일 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/jquery.easing.1.3.js' />"></script><!-- 쿼리움직임효과 기본파일 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/fs_common.js' />"></script><!-- 공통스크립트 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/jquery.bxslider.js' />"></script><!-- //슬라이드 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/map.js' />"></script><!-- //지도관련-->

	<script type="text/javascript" src="<c:url value='/resource/bike/js/plugins/bootstrap/js/bootstrap.js' />"></script>
	<!-- //script -->

	<!-- 익스8일때 브라우저 업데이트 상단공지 -->
	<!--[if lte IE 8]>
	<style type="text/css">
		#old_browser_popup {position:fixed;top:0;left:0;z-index:11000;width:100%;background:#000;text-align:center;z-index:100000}
		#old_browser_container {width:650px;margin:0 auto;}
		#old_browser_popup img {float:left;margin-top:12px}
		#old_browser_popup p {padding:15px;color:#fff;font-size:20px;line-height:150%;margin:0}
		#old_browser_popup a {padding:0 5px;color:#fff}
		#old_browser_popup a:hover {text-decoration:underline}
		#old_browser_close {position:absolute;top:12px;right:20px;font-size:20px;z-index:12000;color:#fff}
	</style>
	<div id="old_browser_popup">
		<div id="old_browser_container">
			<p>
				현재 브라우저에서는 정상적인 이용이 불가능 하오니<br />
				브라우저를 업데이트 해주세요.<br />
				<b>최신 브라우저 다운로드 :</b> <br />
				<a href="http://windows.microsoft.com/ko-kr/internet-explorer/download-ie" target="_blank">Explorer</a>
				<a href="https://www.google.com/intl/ko/chrome/browser/" target="_blank">Chrome</a>
				<a href="http://support.apple.com/ko_KR/downloads/#safari" target="_blank">Safari</a>
				<a href="http://www.mozilla.or.kr/ko/" target="_blank">Firefox</a>
				<a href="http://www.opera.com/ko/computer" target="_blank">Opera</a>
				<a href="http://swing-browser.com/" target="_blank">Swing</a>
				<a href="http://whale.naver.com/" target="_blank">Whale</a>
			</p>
		</div>
		<a href="#none" id="old_browser_close">닫기</a>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#old_browser_close").click(function(){
				$("#old_browser_popup").hide();
			});
		});
	</script>
	<![endif]-->
	<!-- //익스8일때 브라우저 업데이트 상단공지 -->

	<!-- 폰트깜빡임 수정 -->
	<style type="text/css">
		#no-fouc, #fs_header {opacity:0;}
		.weather_box {display:none;}
		
	</style>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#no-fouc, #fs_header").delay(250).animate({"opacity":"1"}, 250);
			$('.slider').bxSlider();
		});
	</script>
	<!-- //폰트깜빡임 수정 -->
</head>
<body>
	<!-- 반복영역 건너뛰기 -->
	<a href="#fs_container_wrap" class="skip_nav">본문영역 바로가기</a>
	<a href="#fs_top_menu" class="skip_nav">메인메뉴 바로가기</a>
	<a href="#fs_footer" class="skip_nav">하단링크 바로가기</a>
	<!-- //반복영역 건너뛰기 -->
	<!-- fs_wrap -->
	<div id="fs_wrap">
		<!-- fs_header -->
		<header id="fs_header">
			<!-- fs_head -->
			<div class="fs_head">
				<!-- logo -->
				<h1 class="logo"><a href="index.do"><span class="icon_layout">자전거행복나눔</span></a></h1>
				<!-- //logo -->
				<!-- global -->
				<div class="global_box">
					<ul>
						<!-- 로그인 전 -->
						<li class="gal1_1"><a href="#none" onclick="openMap('', '', '1', '${param.key}')">지도 테스트</a></li>
						<li class="gal1_1"><a href="#none">로그인</a></li>
						<li class="gal1_2"><a href="#none">회원가입</a></li>
						<li class="gal1_3"><a href="#none">ENGLISH</a></li>
						<li class="gal1_4"><a href="#none">사이트맵</a></li>
						<!-- //로그인 전 -->

						<!-- 로그인 후 --
						<li class="gal2_1"><a href="#none">로그아웃</a></li>
						<li class="gal2_2"><a href="#none">마이페이지</a></li>
						<li class="gal2_3"><a href="#none">ENGLISH</a></li>
						<li class="gal2_4"><a href="#none">사이트맵</a></li>
						<!-- //로그인 후 -->
					</ul>
				</div>
				<!-- //global -->
				<div class="mbtn_box">
					<a href="#none" class="menu_open"><span>모바일 메뉴</span></a>
				</div>
			</div>
			<!-- //fs_head -->
			
			<jsp:include page="/repository/www/menu/top.jsp" />

		</header>
		<!-- //fs_header -->
		<!-- fs_container_wrap -->
		<div id="fs_container_wrap">
			<!-- fs_sub_visual -->
			<section class="fs_sub_visual" style="background:url(/resource/bike/img/layout/sub_01.jpg) no-repeat center top;">
				<div class="snb_vis_box">
					<div class="stc"><h3>${ currentMenu.pNm }</h3></div>
					<p class="sta">자전거 행복나눔과 함께하는 행복 안전 라이딩</p>
				</div>
			</section>
			<!-- //fs_sub_visual -->
			<!-- loca_box -->
			<div class="fs_loca_box">
				<div class="loca_box">
					<jsp:include page="/repository/www/menu/navi_${ currentMenu.menuSn }.jsp" />
				</div>
			</div>
			<!-- //loca_box -->
			<!-- fs_sub_contents -->
			<section class="fs_snb_contents">
				<div class="fs_snb_contents_box">
					<!-- snb_menu_box -->
					<div class="fs_snb_box">
						<!-- snb_title -->
						<div class="snb_title">
							<h3>${ currentMenu.fNm }</h3>
						</div>
						<!-- //snb_title -->
						<!-- snb_menu -->
						<jsp:include page="/repository/www/menu/left_${ currentMenu.menuBestSn }.jsp" />
						<!-- //snb_menu -->
						<div class="weather_box">
							<div class="tit">구간 현재 날씨</div>
							<ul>
								<li id="weather1">
									<div class="stit">사천진항</div>
									<div class="wimg"><img src="/resource/bike/img/layout/weather_sunny.png" alt="맑음"></div>
									<div class="wspeed">3.6㎧</div>
									<div class="temper">12℃</div>
								</li>
								<li id="weather2">
									<div class="stit">사천진항</div>
									<div class="wimg"><img src="/resource/bike/img/layout/weather_sunrain.png" alt="가끔 비, 한때 비"></div>
									<div class="wspeed">5.0㎧</div>
									<div class="temper">11℃</div>
								</li>
							</ul>
						</div>
					</div>
					<!-- //snb_menu_box -->
					<!-- fs_content -->
					<div class="fs_content" id="fs_content">
						<!-- con_header -->
						<div class="con_header">
							<h3>${ currentMenu.menuNm }</h3>
							<!-- sns_box -->
							<div class="sns_box clear">
								<a class="sns_open_btn" href="#none"><span class="icon_layout">sns공유하기</span></a>
								<div class="sns_open clear">
									<a class="facebook" href="#none"><span class="icon_layout">페이스북</span></a>
									<a class="twitter" href="#none"><span class="icon_layout">유튜브</span></a>
									<a class="instagram" href="#none"><span class="icon_layout">인스타그램</span></a>
								</div>
								<a class="print_btn" href="#none"><span class="icon_layout">인쇄하기</span></a>
							</div>
							<!-- //sns_box -->
						</div>
						<!-- con_header -->
						<!-- con_body -->
						<div class="con_body">
							<decorator:body />
						</div>
						<!-- //con_body -->
					</div>
					<!-- //fs_content -->
				</div>
			</section>
			<!-- //fs_sub_contents -->
		</div>
		<!-- //fs_container_wrap -->
		<!-- fs_footer -->
		<footer id="fs_footer">
			<div class="fs_footer_box clear">
				<div class="footer_link_box">
					<ul class="footer_link">
						<li><a href="#none" class="blue">개인정보처리방침</a></li>
						<li><a href="#none">이용약관</a></li>
						<li><a href="#none">2년 재동의 안내</a></li>
						<li><a href="#none">질의/응답</a></li>
					</ul>
				</div>
				<div class="footer_txt_box">
					<div class="footer_logo"><span class="icon_layout">행정안전부</span></div>
					<div class="foot_text">
						<ul>
							<li>30116 세종특별자치시 한누리대로 411(어진동)</li>
							<li>홈페이지, 사이버인증 문의 : 1522-8643  / 인증수첩문의 : 1577-4359(우리강이용도우미)</li>
							<li>본 홈페이지에 게시된 이메일 주소가 무단 수집되는 것을 거부하며, 위반시 정보통신망법에 의해 처벌됨을 알려 드립니다.</li>
						</ul>
						<address class="copyright">COPYRIGHT @ 행정안전부 ALL RIGHT RESRVED.</address>
					</div>
				</div>
			</div>
			<!--btn_top-->
			<a class="btn_top" href="#fs_wrap"><span class="icon_layout">맨위로 이동</span></a>
			<!--//btn_top-->
		</footer>
		<!-- //fs_footer -->
	</div>
	<!-- //wrap -->
	
<jsp:include page="/WEB-INF/jsp/www/weather.jsp" />
</body>
</html>
