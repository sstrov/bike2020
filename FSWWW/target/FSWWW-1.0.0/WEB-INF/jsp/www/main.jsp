<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"                     prefix="string" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                        prefix="xss" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>세종통계포털</title>
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/fonts/font-awesome.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/skin.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/slick.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/common.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/main/main.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/sub-common.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/A-stateData/A-1-tab.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/common/table.css" />">
	<link rel="stylesheet" href="<c:url value="/resource/static/css/A-stateData/register.css" />">
	<script src="<c:url value="/resource/static/js/common/includeHTML.js" />"></script>
	
	<!-- 폰트깜빡임 수정 -->
	<script type="text/javascript">
	/*
		$(document).ready(function() {
			 $("html").css("opacity", "0");
			
			$(document).ready(function () {
				$("html").delay(150).animate({"opacity":"1"}, 150);
			});
			 
		});*/
			
	</script>
	<!-- //폰트깜빡임 수정 -->
</head>
<body>
	<!-- 반복영역 건너뛰기 -->
	<a href="#main-container" class="skip_nav">본문영역 바로가기</a>
	<a href="#fs_top_menu" class="skip_nav">메인메뉴 바로가기</a>
	<a href="#fs_footer" class="skip_nav">하단링크 바로가기</a>
	<!-- //반복영역 건너뛰기 -->
	
	<div class="wrap">
		<jsp:include page="/www/banner1.do?key=${ param.key }" />
		
		<section>
			<header class="pc-header" data-on="-1">
				<div class="header-wrap">
					<h1>
						<a href="#this">
							<img src="<c:url value='/resource/static/image/common/logo.png' />" alt="세종특별자치시 로고">
							<span>세종통계</span>
						</a>
					</h1>
					
					<div>
					<jsp:include page="/repository/www/menu/top.jsp" />
					</div>
					
					<div class="login-container">
						<c:choose>
							<c:when test="${ !empty userSession }">
								<div>
									<a href="<c:url value='/exsignon/sso/logout.do' />">
										<img src="<c:url value='/resource/static/image/main/login.png' />" alt="로그인 아이콘">
										<span>로그아웃</span>
									</a>
								</div>
								<div>
									<a href="<c:url value='/mypage/scrap/list.do' />?key=2001272674229">
										<img src="<c:url value='/resource/static/image/main/sign-up.png' />" alt="회원가입 아이콘">
										<span>마이페이지</span>
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<a href="<c:url value='/exsignon/sso/sso_idp_login.jsp' />">
										<img src="<c:url value='/resource/static/image/main/login.png' />" alt="로그인 아이콘">
										<span>로그인</span>
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<button class="header-close-btn"><img src="<c:url value='/resource/static/image/common/header-close.png' />" alt=""></button>
			</header>
			
			<!-- 모바일 헤더 시작 -->
			<header class="mobile-header">
				<button class="menu-open">
					<img src="<c:url value='/resource/static/image/common/menu-icon.png' />" alt="메뉴열기버튼">
				</button>
				<a href="#this">
					<img src="<c:url value='/resource/static/image/common/logo.png' />" alt="세종특별자치시 로고">
					<span>세종통계</span>
				</a>
			</header>
			<!-- 모바일 헤더 끝 -->
			
			<div class="slide-container" id="main-container">
				<div class="main-slide">
					<c:if test="${ !empty bannerList4 }">
						<c:forEach var="item" items="${ bannerList4 }">
							<c:set var="img">${ item.flpth }/${ item.atchmnflImage }</c:set>
							<div class="main-slide-item">
								<c:if test="${ !empty item.atchmnflImage }">
									<div class="image-box"><img src="<c:url value='${ img }' />" alt=""></div>
								</c:if>
							</div>
						</c:forEach>
					</c:if>
				</div>
				
				<div class="service-link">
					<a href="<c:url value='/twdcvln/anals/form.do?key=1911210355934' />" class="link-item">
						<img src="<c:url value='/resource/static/image/main/icon-main-01.png' />" alt="세종시 전입/전출/출산/사망통계를 통한 세종시 인구변화">
						<div class="text">
							세종시 전입/전출/출산/사망<br>
							통계를 통한 세종시 인구변화
						</div>
					</a>
					<a href="<c:url value='/twdcvln/anals/tfcacd/form.do?key=1911210355934' />" class="link-item">
						<img src="<c:url value='/resource/static/image/main/icon-main-02.png' />" alt="세종시 사고다발지역 및 교통사고 통계분석">
						<div class="text">
							세종시 사고다발지역 및<br>
							교통사고 통계분석
						</div>
					</a>
					<a href="<c:url value='/twdcvln/anals/odsn/form.do?key=1911210355934' />" class="link-item">
						<img src="<c:url value='/resource/static/image/main/icon-main-03.png' />" alt="지역/연령별 노인 복지/의료/정책 현황">
						<div class="text">
							지역/연령별 <br>
							노인 복지/의료/정책 현황
						</div>
					</a>
					<a href="<c:url value='/twdcvln/anals/job/form.do?key=1911210355934' />" class="link-item">
						<img src="<c:url value='/resource/static/image/main/icon-main-04.png' />" alt="세종시 유형별 일자리 분석">
						<div class="text">
							세종시 유형별<br>
							일자리 분석
						</div>		
					</a>
				</div>
					</div>
			<!-- 메인 슬라이더 끝 -->

			<!-- 메인 컨테이너 시작 -->
			<div class="main-container">
				<div class="main-content">
				
					<div class="category">
						<ul class="category-list">
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=0" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-01.png' />" alt="토지 및 기후" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-01.png' />" alt="토지 및 기후" class="icon-on">
									</span>
									<span class="icon-title">토지 및 기후</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=1" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-02.png' />" alt="인구" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-02.png' />" alt="인구" class="icon-on">
									</span>
									<span class="icon-title">인구</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=2" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-03.png' />" alt="노동" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-03.png' />" alt="노동" class="icon-on">
									</span>
									<span class="icon-title">노동</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=3" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-04.png' />" alt="사업체" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-04.png' />" alt="사업체" class="icon-on">
									</span>
									<span class="icon-title">사업체</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=4" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-05.png' />" alt="농림업" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-05.png' />" alt="농림업" class="icon-on">
									</span>
									<span class="icon-title">농림업</span>
								</a>
							</li>
						</ul>
						<ul class="category-list">
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=5" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-06.png' />" alt="광업/제조업 및 에너지" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-06.png' />" alt="광업/제조업 및 에너지" class="icon-on">
									</span>
									<span class="icon-title">광업/제조업 <br/>및 에너지</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=6" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-07.png' />" alt="전기/가스/수도" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-07.png' />" alt="전기/가스/수도" class="icon-on">
									</span>
									<span class="icon-title">전기/가스/<br/>수도</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=7" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-08.png' />" alt="유통/금융/보험 및 기타" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-08.png' />" alt="유통/금융/보험 및 기타" class="icon-on">
									</span>
									<span class="icon-title">유통/금융/<br/>보험 및 기타</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=8" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-09.png' />" alt="주택/건설" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-09.png' />" alt="주택/건설" class="icon-on">
									</span>
									<span class="icon-title align-middle">주택/건설</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=9" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-10.png' />" alt="교통/관광/정보통신" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-10.png' />" alt="교통/관광/정보통신" class="icon-on">
									</span>
									<span class="icon-title">교통/관광/<br/>정보통신</span>
								</a>
							</li>
						</ul>
						<ul class="category-list">
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=10" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-11.png' />" alt="보건/사회보장" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-11.png' />" alt="보건/사회보장" class="icon-on">
									</span>
									<span class="icon-title">보건/<br/>사회보장</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=11" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-12.png' />" alt="환경" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-12.png' />" alt="환경" class="icon-on">
									</span>
									<span class="icon-title align-middle">환경</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=12" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-13.png' />" alt="교육/문화" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-13.png' />" alt="교육/문화" class="icon-on">
									</span>
									<span class="icon-title align-middle">교육/문화</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=13" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-14.png' />" alt="재정" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-14.png' />" alt="재정" class="icon-on">
									</span>
									<span class="icon-title align-middle">재정</span>
								</a>
							</li>
							<li class="category-item">
								<a href="<c:url value='/stats/form.do' />?key=1910168795224&tabIdx=14" class="category-anchor">
									<span class="icon-box">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-off-15.png' />" alt="공공행정/사법" class="icon-off">
										<img src="<c:url value='/resource/static/image/common/icon/cate-icon-on-15.png' />" alt="공공행정/사법" class="icon-on">
									</span>
									<span class="icon-title align-middle">공공행정/사법</span>
								</a>
							</li>
						</ul>
					</div>
					<!-- 카테고리 끝 -->

					<div class="content-item">
						<p class="title">통계소식</p>
						<div class="inner">
							<div class="tab-container">
								<div class="tab-name-box">
									<a href="#this" onclick="$('.more-btn').attr('href', '<c:url value='/bbs/list.do' />?key=1911210374488');" class="tab-name">통계알림</a>
									<a href="#this" onclick="$('.more-btn').attr('href', '<c:url value='/bbs/list.do' />?key=1911210374844');" class="tab-name">통계자료실</a>
									<a href="#this" onclick="$('.more-btn').attr('href', '<c:url value='/bbs/list.do' />?key=1910168796261');" class="tab-name">통계월보</a>
								</div>
								<div class="tab-content-box">
									<div class="tab-content">
										<h2 class="hide">통계 알림 내용 시작</h2>
										<ul class="list list-type-a">
											<c:if test="${ !empty bbsList1 }">
												<c:forEach var="item" items="${ bbsList1 }">
													<li class="list-item">
														<a href="<c:url value='/bbs/view.do' />?key=1911210374488&bbsSn=${ item.bbsSn }">
															<p>${ item.bbsSj }</p>
															<span>
																<dt:format pattern="yyyy.MM.dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item.registDe}</dt:parse>
																</dt:format>
															</span>
														</a>
													</li>
												</c:forEach>
											</c:if>
										</ul>
										<a href="<c:url value='/bbs/list.do' />?key=1911210374488" title="통계 알림 더보기" class="more-btn">
											<img src="<c:url value='/resource/static/image/main/more.png' />" alt="더보기버튼">
										</a>
									</div>
									<div class="tab-content">
										<h2 class="hide">통계 자료실 내용 시작</h2>
										<ul class="list list-type-a">
											<c:if test="${ !empty bbsList2 }">
												<c:forEach var="item" items="${ bbsList2 }">
													<li class="list-item">
														<a href="<c:url value='/bbs/view.do' />?key=1911210374844&bbsSn=${ item.bbsSn }">
															<p>${ item.bbsSj }</p>
															<span>
																<dt:format pattern="yyyy.MM.dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item.registDe}</dt:parse>
																</dt:format>
															</span>
														</a>
													</li>
												</c:forEach>
											</c:if>
										</ul>
										<a href="<c:url value='/bbs/list.do' />?key=1911210374488" title="통계 자료실 더보기" class="more-btn">
											<img src="<c:url value='/resource/static/image/main/more.png' />" alt="더보기버튼">
										</a>
									</div>
									<div class="tab-content">
										<h2 class="hide">통계월보 내용 시작</h2>
										<ul class="list list-type-a">
											<c:if test="${ !empty bbsList3 }">
												<c:forEach var="item" items="${ bbsList3 }">
													<li class="list-item">
														<a href="<c:url value='/bbs/view.do' />?key=1910168796261&bbsSn=${ item.bbsSn }">
															<p>${ item.bbsSj }</p>
															<span>
																<dt:format pattern="yyyy.MM.dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item.registDe}</dt:parse>
																</dt:format>
															</span>
														</a>
													</li>
												</c:forEach>
											</c:if>
										</ul>
										<a href="<c:url value='/bbs/list.do' />?key=1911210374488" title="통계월보 더보기" class="more-btn">
											<img src="<c:url value='/resource/static/image/main/more.png' />" alt="더보기버튼">
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<c:set var="ipChk" value="false" />
					<c:if test="${ fn:indexOf(pageContext.request.remoteAddr, '0:0:0:0:0:0:0:1') ne -1 }">
						<c:set var="ipChk" value="true" />
					</c:if>
					<div class="content-item">
						<p class="title">통계지원서비스</p>
						<div class="inner <c:if test="${ ipChk }">flex</c:if>">
							<div class="column-list-box <c:if test="${ ipChk }">type-02</c:if>">
								<ul class="column-list">
									<li>
										<a href="<c:url value='/stats/form.do' />?key=1910168795224">통계데이터</a>
									</li>
									<li>
										<a href="<c:url value='/stats/public/list.do' />?key=2003306176542">공공데이터</a>
									</li>
									<li>
										<a href="<c:url value='/stats/lclty/ix/form.do' />?key=1910168795224">e-지방지표</a>
									</li>
									<li>
										<a href="<c:url value='/stats/visual/list.do' />?key=1910168795525">지도기반데이터셋</a>
									</li>
								</ul>
								<ul class="column-list">
									<li>
										<a href="<c:url value='/stats/compno/form.do' />?key=1910168795224">복수통계</a>
									</li>
									<li>
										<a href="<c:url value='/stats/mthpr/form.do' />?key=1911210358974">시각화서비스</a>
									</li>
									<li>
										<a href="<c:url value='/sub.do' />?key=1910168794919">통계간행물</a>
									</li>
								</ul>		
							</div>
							<c:if test="${ ipChk }">
								<a href="<c:url value='/keylssues/popltn/01.do' />" class="key-issues">
									<img src="<c:url value='/resource/static/image/main/icon-chart.png' />" alt="세종시 주요현안">
									<div class="text">세종주요시정</div>
								</a>
							</c:if>
						</div>
					</div>

					<div class="content-item popupzone">
						<p class="title">Popupzone</p>
						<div class="slide-button-box popupzone-btn-box">
							<button class="slick-pause"><img src="<c:url value='/resource/static/image/main/btn_pause.png' />" alt="Popupzone 슬라이드 일시정지"></button>
						</div>
						<div class="inner">
							<div class="popupzone-slide">
								<c:if test="${ !empty bannerList2 }">
									<c:forEach var="item" items="${ bannerList2 }">
										<c:set var="href">${ !empty item.bannerItnadr? item.bannerItnadr : '#none' }</c:set>
										<c:set var="img">${ item.flpth }/${ item.atchmnflImage }</c:set>
										<a href="<c:url value='${ href }' />" target="_blank" title="새창으로 열림">
											<c:if test="${ !empty item.atchmnflImage }">
												<img src="<c:url value='${ img }' />" alt="${ item.tagCn }">
											</c:if>
										</a>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				
					<div class="content-item">
						<div class="inner">
							<div class="banner-box">
								<p class="title">배너모음</p>
								<div class="slide-button-box banner-btn-box">
									<button class="slick-pause"><img src="<c:url value='/resource/static/image/main/btn_pause.png' />" alt="배너모음 슬라이드 일시정지"></button>
								</div>
								<div class="banner-slide-container">
									<div class="banner-slide">
										<c:if test="${ !empty bannerList3 }">
											<c:forEach var="item" items="${ bannerList3 }">
												<c:set var="href">${ !empty item.bannerItnadr? item.bannerItnadr : '#none' }</c:set>
												<c:set var="img">${ item.flpth }/${ item.atchmnflImage }</c:set>
												<a href="<c:url value='${ href }' />" target="_blank" title="새창으로 열림">
													<c:if test="${ !empty item.atchmnflImage }">
														<img src="<c:url value='${ img }' />" alt="${ item.bannerNm }">
													</c:if>
												</a>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 푸터 시작-->
				<footer>
					<div class="footer-top" id="fs_footer">
						<p>소통하는 세종</p>
						<span>행복도시 세종 SNS로 소통해요</span>
						<ul>
						<li><a href="https://www.facebook.com/sejongstory" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/icon_sns_01.png' />" alt="페이스북"></a></li>
						<li><a href="http://sejongstory.kr" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/icon_sns_02.png' />" alt="네이버 블로그"></a></li>
						<li><a href="https://band.us/band/63502525" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/icon_sns_03.png' />" alt="네이버 밴드"></a></li>
						<li><a href="https://www.instagram.com/sejongstory/" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/icon_sns_04.png' />" alt="인스타그램"></a></li>
						</ul>
					</div>

					<div class="footer-bottom">
						<div class="footer-logo">
						<img src="<c:url value='/resource/static/image/common/footer-logo.png' />" alt="세종특별자치시">
						</div>
						<div class="link-box">
						<a href="https://www.sejong.go.kr/kor/sitemap_06.do" target="_blank" title="새창열림">개인정보처리방침</a>
						<a href="https://www.sejong.go.kr/kor/sitemap_07.do" target="_blank" title="새창열림">저작권정책</a>
						<a href="https://www.sejong.go.kr/kor/sitemap_08.do" target="_blank" title="새창열림">이메일주소무단수집거부</a>
						<a href="https://www.sejong.go.kr/html/cyber/hall/index.html" target="_blank" title="새창열림">청사안내</a>
						<a href="https://www.sejong.go.kr/kor/sub01_0104.do" target="_blank" title="새창열림">오시는길</a>
						<a href="https://www.sejong.go.kr/kor/sitemap_09.do" target="_blank" title="새창열림">뷰어프로그램 다운로드</a>
						<a href="https://www.sejong.go.kr/prog/bbs/402/kor/10/list.do" target="_blank" title="새창열림">홈페이지개선의견</a>
						</div>
						<div class="footer-info">
						<p>세종특별자치시 한누리대로 2130 (보람동 626-5) (우)30151</p>
						<p>대표전화 (044) 120 <a href="http://as82.kr/sejong/" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/footer_image.png' />" alt="세종시원격접속 상담"></a></p>
						<p>Copyright &copy; 세종특별자치시. All Rights Reserved.</p>
						
							<p class="footer-wa" style="position:absolute; top:0; right:0;">
								<a title="새창" href="http://www.wa.or.kr/board/list.asp?search=title&SearchString=%BC%BC%C1%BE%C6%AF%BA%B0%C0%DA%C4%A1%BD%C3&BoardID=0006" target="_blank">
									<img title="국가 공인 인증기관 : 사단법인 한국장애인총연합회 한국웹접근성인증평가원" alt="(사)한국장애인단체총연합회 한국웹접근성인증평가원 웹 접근성 우수사이트 인증마크(WA인증마크)" src="<c:url value='/resource/static/image/accessibility_img.gif' />" style="width:80px;">
								</a>
							</p>
						</div>
					</div>
				</footer>
				<!-- 푸터 끝-->
			</div>
		</section>
		<!-- 메인 화면 끝 -->
	</div>
	<script src="<c:url value="/resource/static/js/common/jquery.min.js" />"></script>
	<script src="<c:url value="/resource/static/js/common/jquery-migrate-1.4.1.js" />"></script>
	<script src="<c:url value="/resource/static/js/common/slick.min.js" />"></script>
	<script src="<c:url value="/resource/static/js/common/header.js" />"></script>
	<script src="<c:url value="/resource/static/js/main/mainSlide.js" />"></script>
	<script src="<c:url value="/resource/static/js/common/tab.js" />"></script>
	<script src="<c:url value="/resource/static/js/main/main-content.js" />"></script>
	
	<!-- 통계 저장 -->
	<%@ include file="/WEB-INF/jsp/cmm/stat/accesStats.jsp" %>
	
	<style>
	@media all and (max-width: 1280px) {
		.footer-wa { position:static !important; }
	}
	
	@media all and (max-width: 1000px) {
		.footer-wa { position:absolute !important; }
	}
	
	@media all and (max-width: 480px) {
		.footer-wa { position:static !important; }
	}
	</style>
</body>
</html>