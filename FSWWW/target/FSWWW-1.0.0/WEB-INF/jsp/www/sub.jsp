<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${ currentMenu.menuNm }-세종통계포털</title>
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/acego.bootstrap.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/fonts/font-awesome.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/skin.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/slick.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/common.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/sub-common.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/A-stateData/A-1-tab.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/common/table.css' />">
	<link rel="stylesheet" href="<c:url value='/resource/static/css/A-stateData/register.css' />">
	
	<script src="<c:url value='/resource/static/js/common/includeHTML.js' />"></script>
	
	<script src="<c:url value='/resource/cmm/js/fs_sns.js' />"></script>
	
	<script src="<c:url value='/resource/cmm/js/jquery/jquery-1.9.1.min.js' />"></script>
	
	<!-- 폰트깜빡임 수정 -->
	<script type="text/javascript">
	/*$(document).ready(function() {
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
	<a href="#fs_contents" class="skip_nav">본문영역 바로가기</a>
	<a href="#fs_top_menu" class="skip_nav">메인메뉴 바로가기</a>
	<a href="#fs_footer" class="skip_nav">하단링크 바로가기</a>
	<!-- //반복영역 건너뛰기 -->
	
	<div class="wrap">
		<!-- 팝업 시작 -->
		<jsp:include page="/www/banner1.do?key=${ param.key }" />
		<!-- 팝업 종료 -->

		<!-- 메인 화면 시작 -->
		<section>
			<header class="pc-header" data-on="0">
				<div class="header-wrap">
					<h1>
						<a href="<c:url value='/index.do' />">
							<img src="<c:url value='/resource/static/image/common/logo.png' />" alt="세종특별자치시 로고">
							<span>세종통계</span>
						</a>
					</h1>
					
					<jsp:include page="/repository/www/menu/top.jsp" />
					
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
			<header class="mobile-header">
				<button class="menu-open">
					<img src="<c:url value='/resource/static/image/common/menu-icon.png' />" alt="메뉴열기버튼">
				</button>
				<a href="#this">
					<img src="<c:url value='/resource/static/image/common/logo.png' />" alt="세종특별자치시 로고">
					<span>세종통계</span>
				</a>
			</header>

			<div class="content-container">
				<div class="title">
					<!-- 메인메뉴 이름 -->
					<h2>${ currentMenu.fNm }</h2>
					<p>세종특별자치시 세종통계홈페이지 방문을 환영합니다.</p>
				</div>

				<div class="sub-menu">
					<ul class="sub-ul">
						<li class="sub-item">
							<a href="<c:url value='/index.do' />">
								<img src="<c:url value='/resource/static/image/common/icon_home.png' />" alt="홈링크아이콘">
								HOME
							</a>
						</li>
						
						<jsp:include page="/repository/www/menu/leftTab_${ currentMenu.menuSn }.jsp" />
					</ul>
					
					<div class="btn-box">
						<c:if test="${ siteSession.snsUseAt eq 'Y' }">
						<button type="button" class="share-open">
							<img src="<c:url value='/resource/static/image/common/icon_share.png' />" alt="sns공유하기">
						</button>
						</c:if>
						<c:if test="${ siteSession.snsUseAt eq 'Y' }">
						<div class="sns-wrap">
							<button class="fa" onclick="openFacebook();" title="페이스북 공유하기 새창열림"></button>
							<button class="tw" onclick="openTwitter('세종시 통계포털');" title="트위터 공유하기 새창열림"></button>
						</div>
						</c:if>
						<button type="button" onclick="bodyPrint();" class="middle-hidden" title="새창열림">
							<img src="<c:url value='/resource/static/image/common/icon_print.png' />" alt="프린트하기">
						</button>
						<button type="button" class="middle-hidden" data-clipboard-action="copy" onclick="copy_to_clipboard();" title="새창열림">
							<img src="<c:url value='/resource/static/image/common/icon_copy.png' />" alt="복사하기">
						</button>
						<textarea name="copytextarea" class="cont_box" id="copytextarea" title="주소복사 내용" style="position:absolute;top:-8888888px;width:0; height:0;" readonly></textarea>
						<script src="<c:url value='/resource/cmm/js/jquery-print/dist/jQuery.print.min.js' />"></script>
						<script>
						function bodyPrint() {
							$("#fs_contents").print({
								globalStyles : true,
								mediaPrint : false,
								iframe : true,
								noPrintSelector : ".avoid-this",
								//deferred: $.Deferred().done(function() { console.log('Printing done', arguments); })
							});
						}
						
						function copy_to_clipboard() {
							if (navigator.userAgent.match(/ipad|ipod|iphone/i)) {
								$("#copytextarea").val(document.location.href);
								var textArea = $("#clipboardUrl");

								var range = document.getElementById("copytextarea");
								range.selectNodeContents(textArea);
								var selection = window.getSelection();
								selection.removeAllRanges();
								selection.addRange(range);
								textArea.setSelectionRange(0, 999999);
								document.execCommand('copy');
								alert('복사 되었습니다.');
							}else{
								try {
									$("#copytextarea").val(document.location.href);
									$(".cont_box").select();
									document.execCommand('copy');
									alert('복사 되었습니다.');
								} catch(err) {
									alert('이 브라우저는 지원하지 않습니다.');
								}
							}
							
						}
						</script>
					</div>
				</div>

				<!-- 콘텐츠 시작 -->
				<div id="fs_contents" class="content">
					<div class="sub-title">
						<!-- 서브메뉴 이름 -->
						<h3>${ currentMenu.menuNm }</h3>
						<!-- <a href="#this" class="btn btn-primary btn-icon tablet-hidden register-open">
							담당자 통계등록
							<i class="arrow"></i>
						</a> -->
					</div>

					<decorator:body />
				</div>
				<!-- 콘텐츠 끝 -->
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
						<p>대표전화 (044) 120 <a href="http://as82.kr/sejong/" target="_blank" title="새창열림"><img src="<c:url value='/resource/static/image/common/footer_image.png' />"
								alt="세종시원격접속 상담"></a></p>
						<p>Copyright &copy; 세종특별자치시. All Rights Reserved.</p>
							<p class="footer-wa" style="position:absolute; top:0; right:0;">
								<a title="새창" href="http://www.wa.or.kr/board/list.asp?search=title&SearchString=%BC%BC%C1%BE%C6%AF%BA%B0%C0%DA%C4%A1%BD%C3&BoardID=0006" target="_blank">
									<img title="국가 공인 인증기관 : 사단법인 한국장애인총연합회 한국웹접근성인증평가원" alt="(사)한국장애인단체총연합회 한국웹접근성인증평가원 웹 접근성 우수사이트 인증마크(WA인증마크)" src="<c:url value='/resource/static/image/accessibility_img.gif' />" style="width:80px;">
								</a>
							</p>
						</div>
					</div>
				</footer>
			</div>
		</section>
	</div>
	
	<script src="<c:url value='/resource/static/js/common/jquery-migrate-1.4.1.js' />"></script>
	<!-- <script src="/resource/static/js/common/slick.min.js"></script>
	<script src="/resource/static/js/main/popup.js"></script> -->
	<script src="<c:url value='/resource/static/js/common/header.js' />"></script>
	<script src="<c:url value='/resource/static/js/common/share-toggle.js' />"></script>
	<script src="<c:url value='/resource/static/js/common/tab2.js' />"></script>
	
	<decorator:head />

	<!-- 통계 저장 -->
	<%@ include file="/WEB-INF/jsp/cmm/stat/accesStats.jsp" %>
	<%@ include file="/WEB-INF/jsp/cmm/stat/accesStats_sub.jsp" %>
</body>
</html>