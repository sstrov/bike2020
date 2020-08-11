<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"                     prefix="string" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                        prefix="xss" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>

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

	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/animate.css' />" /><!-- 애니메이션 효과 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/html5_reset.css' />" /><!-- 리셋 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_component.css' />" /><!-- 반복사용 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_layout.css' />" /><!-- 전체 레이아웃 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/css/fs_main.css' />" /><!-- 메인 -->
	<link type="text/css" rel="stylesheet" href="<c:url value='/resource/bike/js/plugins/animate.css' />" /><!-- 애니메이션 효과 -->
	<!-- //css -->

	<!-- script -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/jquery-1.11.3.min.js' />"></script><!-- 쿼리구동 기본파일 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/jquery.easing.1.3.js' />"></script><!-- 쿼리움직임효과 기본파일 -->
	<script type="text/javascript" src="<c:url value='/resource/bike/js/fs_common.js' />"></script><!-- 공통스크립트 -->

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
	</style>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#no-fouc, #fs_header").delay(250).animate({"opacity":"1"}, 250);
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
			<!-- fs_main_visual -->
			<section class="fs_main_visual">
				<!-- 비주얼 -->
				<div class="slides_box">
					<ul class="slides01">
						<li style="background:url(/resource/bike/img/main/mvis_img01.jpg) no-repeat center top;background-size:cover;">
							<div class="one main_visual_text">
								<span class="sta">MAKE A GREEN LIFE</span>
								<span class="stb"><em class="blue">지역</em>과 <em class="blue">지역</em>, <em class="green">사람</em>과 <em class="green">사람</em>을 <br />이어주는 <em class="yellow">희망의 길</em></span>
							</div>
						</li>
						<li style="background:url(/resource/bike/img/main/mvis_img02.jpg) no-repeat center top;background-size:cover;">
							<div class="one main_visual_text">
								<span class="sta">MAKE A GREEN LIFE</span>
								<span class="stb"><em class="blue">지역</em>과 <em class="blue">지역</em>, <em class="green">사람</em>과 <em class="green">사람</em>을 <br />이어주는 <em class="yellow">희망의 길</em></span>
							</div>
						</li>
						<li style="background:url(/resource/bike/img/main/mvis_img03.jpg) no-repeat center top;background-size:cover;">
							<div class="one main_visual_text">
								<span class="sta">MAKE A GREEN LIFE</span>
								<span class="stb"><em class="blue">지역</em>과 <em class="blue">지역</em>, <em class="green">사람</em>과 <em class="green">사람</em>을 <br />이어주는 <em class="yellow">희망의 길</em></span>
							</div>
						</li>
					</ul>
					<!-- 컨트롤 -->
					<div class="mvis_controll_box">
						<div class="mvis_pager"></div><!-- 페이징 -->
						<div class="mvis_auto"></div><!-- 재생/정지 -->
					</div>
					<!-- //컨트롤 -->
				</div>
				<!-- //비주얼 -->
			</section>
			<!-- //fs_main_visual -->
			<!-- fs_main_contents -->
			<section class="fs_main_contents">
				<!-- 링크버튼 -->
				<div class="con_box con_box1">
					<ul class="btn_link">
						<li><a href="#none"><span class="icon_main"></span> 국토종주 <em>인증제 안내</em></a></li>
						<li><a href="#none"><span class="icon_main"></span> 통행가능 <em>전기자전거 정보</em></a></li>
						<li><a href="#none"><span class="icon_main"></span> 안전교육</a></li>
						<li><a href="#none"><span class="icon_main"></span> 2년 재동의 안내</a></li>
						<li><a href="#none"><span class="icon_main"></span> 질의/응답</a></li>
					</ul>
				</div>
				<!-- //링크버튼 -->
				<!-- 행사일정, 게시판 -->
				<div class="con_box con_box2">
					<!-- 행사일정 -->
					<div class="event_box">
						<div class="tit_box">
							<h3 class="tit">이달의 <br />주요 <br /><strong>행사일정</strong></h3>
							<a href="#none" class="more1">자세히보기</a>
						</div>
						<div class="cont_box">
							<ul>
								<li>
									<a href="#none">
										<div class="date">
											<em class="month">04</em>
											<em class="day">08</em>
										</div>
										<div class="con">
											<div class="etit">주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다.</div>
											<div class="econ">
												<em class="place">사천시청 노을광장</em>
												<em class="time">07:00</em>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#none">
										<div class="date">
											<em class="month">04</em>
											<em class="day">16</em>
										</div>
										<div class="con">
											<div class="etit">주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다.</div>
											<div class="econ">
												<em class="place">잠실종합운동장</em>
												<em class="time">11:00</em>
											</div>
										</div>
									</a>
								</li>
								<li>
									<a href="#none">
										<div class="date">
											<em class="month">04</em>
											<em class="day">31</em>
										</div>
										<div class="con">
											<div class="etit">주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다. 주요일정이 들어갑니다.</div>
											<div class="econ">
												<em class="place">DMZ</em>
												<em class="time">10:00</em>
											</div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- //행사일정 -->
					<!-- 게시판 -->
					<div class="notice_box">
						<div>
							<ul class="tap_ul clear">
								<li class="on"><a href="#none" class="tap1">뉴스/공지</a></li>
								<li><a href="#none" class="tap2">전기자전거 목록</a></li>
								<li><a href="#none" class="tap3">자료실</a></li>
							</ul>
							<!-- 뉴스/공지 -->
							<div class="tap_cont_box" id="tap1">
								<div class="notice">
									<h3 class="hide">뉴스/공지 내용시작</h3>
									<ul class="notice_list">
										<li class="top_news">
											<a href="#none">
												<div class="tdate">2020.04<span class="day">01</span></div>
												<div class="tcon">
													<div class="nttit"><span class="new">N</span>제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</div>
													<div class="ntcon">내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다.</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
									</ul>
								</div>
								<a href="#none" class="more">뉴스/공지 더보기</a>
							</div>
							<!-- //뉴스/공지 -->
							<!-- 전기자전거 목록 -->
							<div class="tap_cont_box" id="tap2">
								<div class="notice">
									<h3 class="hide">전기자전거 목록 내용시작</h3>
									<ul class="notice_list">
										<li class="top_news">
											<a href="#none">
												<div class="tdate">2020.04<span class="day">01</span></div>
												<div class="tcon">
													<div class="nttit"><span class="new">N</span>제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</div>
													<div class="ntcon">내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다.</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
									</ul>
									<a href="#none" class="more">전기자전거 목록 더보기</a>
								</div>
							</div>
							<!-- //전기자전거 목록 -->
							<!-- 자료실 -->
							<div class="tap_cont_box" id="tap3">
								<div class="notice">
									<h3 class="hide">자료실 내용시작</h3>
									<ul class="notice_list">
										<li class="top_news">
											<a href="#none">
												<div class="tdate">2020.04<span class="day">01</span></div>
												<div class="tcon">
													<div class="nttit"><span class="new">N</span>제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</div>
													<div class="ntcon">내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다. 내용이 들어갑니다.</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
										<li>
											<a href="#none" class="ntit">제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</a>
											<span class="date">2020-03-04</span>
										</li>
									</ul>
									<a href="#none" class="more">자료실 더보기</a>
								</div>
							</div>
							<!-- //자료실 -->
						</div>
						<script type="text/javascript">
							var param = $(".tap_ul");
							var cont = ".tap_cont_box";
							cont_tap2(param,cont);
						</script>
					</div>
					<!-- //게시판 -->
				</div>
				<!-- //행사일정, 게시판 -->
				<!-- 주요 자전거길 정보, 국토 종주 인증안내, 유용한 자전거 정책 -->
				<div class="con_box3">
					<div class="con_box">
						<div class="top_box">
							<!-- 주요 자전거길 정보 -->
							<div class="winfo_box">
								<div class="winfo">
									<div class="tit_box">
										<h3 class="tit">주요 자전거길 정보</h3>
										<p>국내 주요 자전거길에 대해 안내해드립니다.</p>
									</div>
									<ul class="winfo_list">
										<li>
											<a href="#none">
												<div class="cont"><span class="ico_box"><img src="/resource/bike/img/main/con3_ico1.png" alt=""></span>국토종주<br />자전거길</div>
											</a>
										</li>
										<li>
											<a href="#none">
												<div class="cont"><span class="ico_box"><img src="/resource/bike/img/main/con3_ico2.png" alt=""></span>지자체 명품<br />자전거길</div>
											</a>
										</li>
										<li>
											<a href="#none">
												<div class="cont"><span class="ico_box"><img src="/resource/bike/img/main/con3_ico3.png" alt=""></span>바다를 품음<br />섬 자전거길</div>
											</a>
										</li>
									</ul>
								</div>
							</div>
							<!-- //주요 자전거길 정보 -->
							<!-- 국토 종주 인증안내 -->
							<div class="certif_box">
								<div class="certif">
									<div class="tit_box">
										<h3 class="tit">국토 종주 인증안내</h3>
										<p>국토종주 자전거길을 달린 뿌듯함과 추억을 간직하세요.</p>
									</div>
									<ul class="certif_list">
										<li>
											<a href="#none">
												<span class="ico_box"><img src="/resource/bike/img/main/con3_ico4.png" alt=""></span>
												<em>인증센터</em>
											</a>
										</li>
										<li>
											<a href="#none">
												<span class="ico_box"><img src="/resource/bike/img/main/con3_ico5.png" alt=""></span>
												<em>인증수첩</em>
											</a>
										</li>
										<li>
											<a href="#none">
												<span class="ico_box"><img src="/resource/bike/img/main/con3_ico6.png" alt=""></span>
												<em>사이버 인증안내<br />1577-4359</em>
											</a>
										</li>
									</ul>
								</div>
							</div>
							<!-- //국토 종주 인증안내 -->
						</div>
						<!-- 유용한 자전거 정책 -->
						<div class="policy_box">
							<div class="policy">
								<div class="tit_box">
									<h3 class="tit">유용한 <strong>자전거 정책</strong></h3>
								</div>
								<ul class="policy_list">
									<li>
										<a href="#none">
											<span>자전거 도로 통행 가능한 자전거 정보</span>
											<em class="more1">자세히보기</em>
										</a>
									</li>
									<li>
										<a href="#none">
											<span>자전거 관련 법령</span>
											<em class="more1">자세히보기</em>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- //유용한 자전거 정책 -->
					</div>
				</div>
				<!-- 주요 자전거길 정보, 국토 종주 인증안내, 유용한 자전거 정책 -->
				<!-- 체험후기, 공모전 작품보기 -->
				<div class="con_box4">
					<div class="con_box">
						<div class="gall_box">
							<div>
								<ul class="tap_ul2 clear">
									<li class="on"><a href="#none" class="tap4">체험후기</a></li>
									<li><a href="#none" class="tap5">공모전 작품보기</a></li>
								</ul>
								<!-- 체험후기 -->
								<div class="tap_cont_box2" id="tap4">
									<div class="gall">
										<h3 class="hide">체험후기 내용시작</h3>
										<ul class="gall_list gall_list1">
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img1.jpg" alt="섬진강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit"><span class="new">N</span>섬진강 자전거길 종주</div>
														<div class="gstit">M2ce**** / 2020-04-01 / 50</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img2.jpg" alt="남한강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다.</div>
														<div class="gstit">firs**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img3.jpg" alt="새재 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 제목이 들어갑니다.</div>
														<div class="gstit">hong**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img1.jpg" alt="섬진강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit"><span class="new">N</span>체험후기 섬진강 자전거길 종주</div>
														<div class="gstit">M2ce**** / 2020-04-01 / 50</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="img/main/con4_img2.jpg" alt="남한강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다.</div>
														<div class="gstit">firs**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img3.jpg" alt="새재 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 체험후기 제목이 들어갑니다. 제목이 들어갑니다.</div>
														<div class="gstit">hong**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
										</ul>
									</div>
									<!-- 컨트롤 -->
									<div class="gall_controll_box gall_controll_box1">
										<a href="#none" class="gall_prev"><span>이전</span></a><!-- 이전 -->
										<a href="#none" class="gall_next"><span>다음</span></a><!-- 다음 -->
									</div>
									<!-- //컨트롤 -->
									<a href="#none" class="more">체험후기 더보기</a>
								</div>
								<!-- //체험후기 -->
								<!-- 공모전 작품보기 -->
								<div class="tap_cont_box2" id="tap5">
									<div class="gall">
										<h3 class="hide">공모전 작품보기 내용시작</h3>
										<ul class="gall_list gall_list2">
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img1.jpg" alt="섬진강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit"><span class="new">N</span>공모전 섬진강 자전거길 종주</div>
														<div class="gstit">M2ce**** / 2020-04-01 / 50</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img2.jpg" alt="남한강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">공모전 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</div>
														<div class="gstit">firs**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img3.jpg" alt="새재 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">공모전 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다. 제목이 들어갑니다.</div>
														<div class="gstit">hong**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img1.jpg" alt="섬진강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit"><span class="new">N</span>공모전 섬진강 자전거길 종주</div>
														<div class="gstit">M2ce**** / 2020-04-01 / 50</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img2.jpg" alt="남한강 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">공모전 제목이 들어갑니다. 공모전 제목이 들어갑니다. 공모전 제목이 들어갑니다. 제목이 들어갑니다.</div>
														<div class="gstit">firs**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
											<li>
												<a href="#none">
													<div class="img_box"><img src="/resource/bike/img/main/con4_img3.jpg" alt="새재 자전거길 종주"></div>
													<div class="txt_box">
														<div class="gtit">공모전 제목이 들어갑니다. 공모전 제목이 들어갑니다. 공모전 제목이 들어갑니다. 공모전 제목이 들어갑니다.</div>
														<div class="gstit">hong**** / 2020-04-01 / 조회수</div>
													</div>
												</a>
											</li>
										</ul>
									</div>
									<!-- 컨트롤 -->
									<div class="gall_controll_box gall_controll_box2">
										<a href="#none" class="gall_prev"><span>이전</span></a><!-- 이전 -->
										<a href="#none" class="gall_next"><span>다음</span></a><!-- 다음 -->
									</div>
									<!-- //컨트롤 -->
									<a href="#none" class="more">공모전 작품 더보기</a>
								</div>
								<!-- //공모전 작품보기 -->
							</div>
							<script type="text/javascript">
								var param = $(".tap_ul2");
								var cont = ".tap_cont_box2";
								cont_tap2(param,cont);
							</script>
						</div>
					</div>
				</div>
				<!-- 체험후기, 공모전 작품보기 -->
			</section>
			<!-- //fs_main_contents -->
		</div>
		<!-- //fs_container_wrap -->
		<!-- 배너존 -->
		<div class="banner_box">
			<div class="banner">
				<h3 class="h3">배너존</h3>
				<div class="banner_list_warp">
					<ul class="banner_list">
						<li><a href="https://www.riverguide.go.kr/kor/index.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img1.jpg" alt="강따라 물따라 우리강 이용도우미"/></a></li>
						<li><a href="https://www.mois.go.kr/frt/a01/frtMain.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img2.jpg" alt="행정안전부"/></a></li>
						<li><a href="http://www.molit.go.kr/portal.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img3.jpg" alt="국토교통부"/></a></li>
						<li><a href="https://www.bikeseoul.com/" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img4.jpg" alt="서울자전거"/></a></li>
						<li><a href="https://www.riverguide.go.kr/kor/index.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img1.jpg" alt="강따라 물따라 우리강 이용도우미"/></a></li>
						<li><a href="https://www.mois.go.kr/frt/a01/frtMain.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img2.jpg" alt="행정안전부"/></a></li>
						<li><a href="http://www.molit.go.kr/portal.do" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img3.jpg" alt="국토교통부"/></a></li>
						<li><a href="https://www.bikeseoul.com/" target="_blank" title="새창열림"><img src="/resource/bike/img/main/banner_img4.jpg" alt="서울자전거"/></a></li>
					</ul>
				</div>
				<!-- 컨트롤 -->
				<div class="banner_controll_box">
					<a href="#none" class="banner_prev">이전</a><!-- 이전 -->
					<a href="#none" class="banner_pause">정지</a><!-- 정지 -->
					<a href="#none" class="banner_play">재생</a><!-- 재생 -->
					<a href="#none" class="banner_next">다음</a><!-- 다음 -->
				</div>
				<!-- //컨트롤 -->
			</div>
		</div>
		<!-- //배너존 -->
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
	<script type="text/javascript" src="/resource/bike/js/plugins/jquery.bxslider.js"></script><!-- jquery.bxslider -->
	<link rel="stylesheet" type="text/css" href="/resource/bike/js/plugins/slick.css"><!-- slick -->
	<script type="text/javascript" src="/resource/bike/js/plugins/slick.js"></script><!-- slick -->
	<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet"><!-- 스크롤 효과 -->
	<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script><!-- 스크롤 효과 -->
	<script type="text/javascript">
		$(document).ready(function(){

			//비주얼
			var slider = $(".slides01").bxSlider({
				mode:"fade",//"horizontal", "vertical", "fade"
				auto:true,//자동,수동
				controls:true,//좌우버튼
				pager:true,//롤링버튼
				autoControls:true,//재생/정지버튼
				autoHover:false,//오버시 정지
				speed:800,//슬라이드 전환시 슬라이드 이미지 전환 속도를 설정
				pause:5000,//슬라이드 전환 속도를 설정
				//moveSlides:1,//슬라이드 이동시 개수
				//maxSlides:1,//최대 슬라이더 갯수
				//minSlides:1,//최소 슬라이더 갯수
				//slideWidth:147,//가로사이즈
				//slideMargin:26//여백사이즈
				prevSelector:".mvis_prev",
				nextSelector:".mvis_next",
				prevText:"이전",//객체 텍스트
				nextText:"다음",//객체 텍스트
				pagerSelector:".mvis_pager",
				autoControlsSelector:".mvis_auto",//재생/정지
				pagerType:"full",//full : 롤링, short : 페이징 ex)1/1
				//pagerShortSeparator :"<span></span>"//페이징 ex)1/1
			});
			// 클릭시 멈춤 현상 해결 //
			$(document).on('click','.fs_main_visual .mvis_next .bx-next, .fs_main_visual .mvis_prev .bx-prev',function() {
				$(".fs_main_visual .bx-start").hide();
				$(".fs_main_visual .bx-stop").show();
				//slider.stopAuto();
				slider.startAuto();
			});
			$(".fs_main_visual .mvis_prev .bx-prev,.fs_main_visual .mvis_next .bx-next").click(function(){
				$(".fs_main_visual .bx-start").show();
				$(".fs_main_visual .bx-stop").hide();
				slider.startAuto();
				return false;
			});
			$(".fs_main_visual .bx-start").hide();
			$(".fs_main_visual .bx-stop").click(function(){
				$(".fs_main_visual .bx-start").show();
				$(".fs_main_visual .bx-stop").hide();
				slider.stopAuto();
				return false;
			});
			$(".fs_main_visual .bx-start").click(function(){
				$(".fs_main_visual .bx-start").hide();
				$(".fs_main_visual .bx-stop").show();
				slider.startAuto();
				return false;
			});

			//체험후기
			$('.gall_list1').slick({
				infinite: true, //양방향 무한 모션
				autoplay:true,//자동롤링
				//centerMode:true,//가운데모드
				//centerPadding:'10%',//객체간 간격
				slidesToShow:3,//한번에 보여질개수
				slidesToScroll:1,// 한번에 슬라이드 시킬 아이템 개수
				//variableWidth:true,//가변 너비 슬라이드
				cssEase:'cubic-bezier(0.77, 0, 0.175, 1)',//이징 속도(좌:가로,세로, 우:가로,세로)
				speed: 400,//슬라이드 전화시 속도
				nextArrow: $(".gall_controll_box1 .gall_next"),
				prevArrow: $(".gall_controll_box1 .gall_prev"),
				//dots:true,//하단 paging버튼 노출 여부
				responsive:[//반응형 일때 실행
					{
						breakpoint:1199,
						settings:{
							slidesToShow:2,//한번에 보여질개수
						}
					},
					{
						breakpoint:850,
						settings:{
							slidesToShow:1,//한번에 보여질개수
						}
					},
					{
						breakpoint:768,
						settings:{
							slidesToShow:1,//한번에 보여질개수
						}
					}
				]
			});
			//포토갤러리
			$('.gall_list2').slick({
				infinite: true, //양방향 무한 모션
				autoplay:true,//자동롤링
				//centerMode:true,//가운데모드
				//centerPadding:'10%',//객체간 간격
				slidesToShow:3,//한번에 보여질개수
				slidesToScroll:1,// 한번에 슬라이드 시킬 아이템 개수
				//variableWidth:true,//가변 너비 슬라이드
				cssEase:'cubic-bezier(0.77, 0, 0.175, 1)',//이징 속도(좌:가로,세로, 우:가로,세로)
				speed: 400,//슬라이드 전화시 속도
				nextArrow: $(".gall_controll_box2 .gall_next"),
				prevArrow: $(".gall_controll_box2 .gall_prev"),
				//dots:true,//하단 paging버튼 노출 여부
				responsive:[//반응형 일때 실행
					{
						breakpoint:1199,
						settings:{
							slidesToShow:2,//한번에 보여질개수
						}
					},
					{
						breakpoint:850,
						settings:{
							slidesToShow:1,//한번에 보여질개수
						}
					},
					{
						breakpoint:768,
						settings:{
							slidesToShow:1,//한번에 보여질개수
						}
					}
				]
			});
			
			//탭클릭시 slick슬라이드 초기화
			$(".tap_ul2").click(function(){
				$('.tap_cont_box2 > div > ul').slick('setPosition');
			});

			//배너
			$('.banner_list').slick({
				infinite: true, //양방향 무한 모션
				autoplay:true,//자동롤링
				//centerMode:true,//가운데모드
				//centerPadding:'10%',//객체간 간격
				slidesToShow:4,//한번에 보여질개수
				//variableWidth:true,//가변 너비 슬라이드
				cssEase:'cubic-bezier(0.77, 0, 0.175, 1)',//이징 속도(좌:가로,세로, 우:가로,세로)
				speed: 400,//슬라이드 전화시 속도
				nextArrow: $(".banner_controll_box .banner_next"),
				prevArrow: $(".banner_controll_box .banner_prev"),
				responsive:[//반응형 일때 실행
					{
						breakpoint:1199,
						settings:{
							slidesToShow:4,//한번에 보여질개수
						}
					},
					{
						breakpoint:1024,
						settings:{
							slidesToShow:3,//한번에 보여질개수
						}
					},
					{
						breakpoint:768,
						settings:{
							slidesToShow:2,//한번에 보여질개수
						}
					},
					{
						breakpoint:520,
						settings:{
							slidesToShow:1,//한번에 보여질개수
						}
					}
				]
			});
			$('.banner_play').hide();
			$('.banner_pause').on('click', function() {
				$('.banner_list').slick('slickPause');
				$(this).hide();
				$('.banner_play').show();
			});
			$('.banner_play').on('click', function() {
				$('.banner_list').slick('slickPlay');
				$(this).hide();
				$('.banner_pause').show();
			});

		});

		//스크롤 애니메이션
		AOS.init({
			easing:'ease-In-Out',
			once:true,//한번만실행
		});
		
		/*
		//스크롤 애니메이션
		document.addEventListener('DOMContentLoaded', function(){
			var trigger = new ScrollTrigger({
				//centerVertical:true,
				addHeight:true,
				once:true //한번만 실행
			});
		});
		*/
	</script>
</body>
</html>
