<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- css -->
<link type="text/css" rel="stylesheet" href="/resource/bike/js/plugins/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="/resource/bike/js/plugins/bootstrap/css/bootstrap-theme.css" />

<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" />
<link type="text/css" rel="stylesheet" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css" />
<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Myeongjo&display=swap" />

<link type="text/css" rel="stylesheet" href="/resource/bike/js/plugins/animate.css" /><!-- 애니메이션 효과 -->
<link type="text/css" rel="stylesheet" href="/resource/bike/css/html5_reset.css" /><!-- 리셋 -->
<link type="text/css" rel="stylesheet" href="/resource/bike/css/fs_component.css" /><!-- 반복사용 -->
<link type="text/css" rel="stylesheet" href="/resource/bike/css/fs_layout.css" /><!-- 전체 레이아웃 -->
<link type="text/css" rel="stylesheet" href="/resource/bike/css/fs_content.css" /><!-- 컨텐츠 -->
<link type="text/css" rel="stylesheet" href="/resource/bike/js/plugins/animate.css" /><!-- 애니메이션 효과 -->
<!-- //css -->

<!-- script -->
<script type="text/javascript" src="/resource/bike/js/jquery-1.11.3.min.js"></script><!-- 쿼리구동 기본파일 -->
<script type="text/javascript" src="/resource/bike/js/jquery.easing.1.3.js"></script><!-- 쿼리움직임효과 기본파일 -->
<script type="text/javascript" src="/resource/bike/js/fs_common.js"></script><!-- 공통스크립트 -->

<script type="text/javascript" src="/resource/bike/js/plugins/bootstrap/js/bootstrap.js"></script>

<div class="sub070101">

	<!-- //left -->
	<div class="mapinfo_left">	
		
		<h4>자전거길 지도정보 서비스</h4>

		<div class="tap_box">				
			<!-- 탭_버튼 -->
			<ul class="top clear tap_btn tap_btn4">
				<li class="on" rel="tap1"><a href="#none">국토종주 자전거길</a></li>
				<li rel="tap2"><a href="#none">지자체 명품 자전거길</a></li>
				<li rel="tap3"><a href="#none">자전거길 찾기</a></li>
			</ul>
			<!-- //탭_버튼 -->

			<!-- 탭_내용 -->
			<div class="tab_content">


				<!-- tap1 -->
				<div class="resul_con" id="tap1" >
					<h4 class="hide">탭1</h4>
					
					<!-- tap_box -->
					<div class="tap_box">

						<!-- 탭_내용 -->
						<div class="tab_content">

							<!-- tap1-1 -->
							<div class="resul_con" rel="tap1-1" >

								<dl class="accordion">

									<dt><a href="">아라자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>아라서해갑문에서 아라한강갑운까지 경인이라 뱃길을 따라 활주로처럼 일직선으로 달릴 수 있는 자전거길</p>
											<div class="">
												<strong>거리 : <span>20km</span>  /  예상시간 : <span>3시간 20분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />아라서해갑문인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />아라한강갑문인증센터</li>
												</ul>
											</div>
										</div>									
									</dd>

									<dt><a href="">한강종자전거길(서울구간)</a></dt>
									<dd>
										<div class="panel">
											<p>아라한강갑문에서 팔당대교까지 한강을 따라 도시 속 휴식처를 달리는 산책하듯 편안하고 아늑한 자전거길</p>
											<div class="">
												<strong>거리 : <span>56km</span>  /  예상시간 : <span>3시간 40분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />아라한강갑문인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />여의도서울마리나인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />뚝섬전망콤플렉스인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />광나루자전거공원인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon2.png" alt="" />팔당대교</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />충주댐인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">남한강자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>팔당대교에서부터 충주탄금대까지 옛 중앙선 폐철도 구간을 달리는 수려한 경관의 이색적인 명품자전거길</p>
											<div class="">
												<strong>거리 : <span>132km</span>  /  예상시간 : <span>8시간 50분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon2.png" alt="" />팔당대교</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />능내역인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />양평군립미술관인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />이포보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />여주보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />강천보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />비내섬인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon2.png" alt="" />목행교</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />충주탄금대인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">북한강자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>청평호반, 의암호반, 운길산, 출령산 등을 지나는 아름다운 절경을 느낄 수 있는 저전거길</p>
											<div class="">
												<strong>거리 : <span>99km</span>  /  예상시간 : <span>8시간 50분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />밝은광장</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />샛터삼거리</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />경강교</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />신매대교</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">새재 자전거길상</a></dt>
									<dd>
										<div class="panel">
											<p>충주탄금대에서 상주 상풍교까지 한강과 낙동강을 잇기 위해 이화령 고개를 넘는 짜릿한 자전거길</p>
											<div class="">
												<strong>거리 : <span>100km</span>  /  예상시간 : <span>6시간 40분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />충주탄금대인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />수안보온천인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />이화령휴게소인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />문경불정역인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />상주상풍교인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">낙동강 자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>상주 상풍교에서 낙동강 하구둑까지 국내에서 가장 긴 자전거길로 다양한 경험과 볼거리가 가득한 즐거운 자전거길</p>
											<div class="">
												<strong>거리 : <span>324km</span>  /  예상시간 : <span>21시간 30분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />안동댐인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />상주상풍교인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />상주보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />낙단보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />구미보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />칠곡보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />강정고령보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />달성보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />합천창녕보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />창녕함안보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />양산물문화관인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />낙동강하구둑인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">금강 자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>대청댐에서 금강 하구둑까지 백제의 숨결을 따라 자연의 조화로움을 느낄 수 있는 여유로운 자전거길</p>
											<div class="">
												<strong>거리 : <span>146km</span>  /  예상시간 : <span>9시간 40분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />대청댐인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />세종보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />공주보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />백제보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />익산성당포구인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />금강하구둑인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">영산강 자건거길</a></dt>
									<dd>
										<div class="panel">
											<p>담양댐부터 영상강 하구둑까지 남도풍경에 매료되는 시처럼 그림처럼 유유자적한 황홀한 자전거길</p>
											<div class="">
												<strong>거리 : <span>133km</span>  /  예상시간 : <span>8시간 50분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />담양댐인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />메타세쿼이아길인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />담양대나무숲인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />승촌보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />죽산보인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />느러지전망관람대인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />영산하구둑인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">섬진강자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>전북 임실 섬진강 생활체육공원에서 부터 전남 광양 배알도 해수욕장까지 자연미를 가장 잘 살린 자전거길</p>
											<div class="">
												<strong>거리 : <span>149km</span>  /  예상시간 : <span>9시간 40분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />섬진강댐 인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />장군목인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />향가유원지인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />횡탄정인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />사성암인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />남도대교인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />매화마을인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />배알도수변공원인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">오천자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>충북 괴산군에서 증평, 청원을 거쳐 세종시까지 다섯 개의 수려한 하천을 따라 조성된 자전거길</p>
											<div class="">
												<strong>거리 : <span>105km</span>  /  예상시간 : <span>6시간 30분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />합강공원인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />무심천교인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />행촌교차로인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />백로공원인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />괴강교인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">동해안자전거길(강원)</a></dt>
									<dd>
										<div class="panel">
											<p>통일전망대 ~ 고포마을을 잇는 싱싱한 수산물과 항구도시의 정취를 즐길 수 있는 동해안자전거길</p>
											<div class="">
												<strong>거리 : <span>242km</span>  /  예상시간 : <span>16시간 10분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />통일전망대(민통선내)</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />통일전망대(민통선외)</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />북천철교</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />봉포해변인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />영금정인증센타</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />동호해변</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />지경공원</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />경포해변</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />정동진</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />망상해변</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />추암촛대바위</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />한재공원</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />임원</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">동해안자전거길(경북)</a></dt>
									<dd>
										<div class="panel">
											<p>푸른 동해바다의 내음을 느낄 수 있는 동해안 자전거길</p>
											<div class="">
												<strong>거리 : <span>767km</span>  /  예상시간 : <span>5시간 15분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />울진은어다리 인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />망양휴계소 인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />월송정 인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />고래불해변 인증센터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />해맞이공원 인증센터</li>
												</ul>
											</div>
										</div>
									</dd>

									<dt><a href="">제주환상자전거길</a></dt>
									<dd>
										<div class="panel">
											<p>제주해안도로를 따라 제주도의 아름다운 해변과 송악산, 쇠소깍, 성산일출봉 등 멋진 자연경관을 감상할 수 있는 자전거길</p>
											<div class="">
												<strong>거리 : <span>234km</span>  /  예상시간 : <span>16시간 10분</span></strong>
												<ul>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />용두암</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />다락쉼터</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />해거름마을공원</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />송악산</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />법환바당</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />쇠소깍</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />표선해변</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />성산일출봉</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />김녕성세기해변</li>
													<li><img src="/resource/bike/img/content/sub07/certified_icon.png" alt="인증" />함덕서우봉해변</li>
												</ul>
											</div>
										</div>
									</dd>

								</dl>
							

								<script>
									(function($) {
										
									  var allPanels = $('.accordion > dd').hide();
										
									  $('.accordion > dt > a').click(function() {
										allPanels.slideUp();
										$(this).parent().next().slideDown();
										return false;
									  });

									})(jQuery);
								</script>


							</div>
							<!-- //tap1-1 -->
		 
						</div>
						<!-- //탭_내용 -->
					</div>
					<!-- //tap_box -->
				</div>
				<!-- //tap1 -->	
				<!-- tap1 -->
				<div class="resul_con" id="tap2" >
					<!-- tap1-2 -->
					<div class="resul_con" rel="tap1-2" >
						<ul>
							<li><a href="">강릉 경포호 산소길</a></li>
							<li><a href="">화천 파로호 100리 산소길</a></li>
							<li><a href="">옹진 덕적도 자전거길</a></li>
							<li><a href="">파주DMZ 자전거길</a></li>
							<li><a href="">옥천 향수 100리길</a></li>
							<li><a href="">정읍 정읍천 자전거길</a></li>
							<li><a href="">신안 증도 자전거섬</a></li>
							<li><a href="">경주 역사탐방 자전거길</a></li>
							<li><a href="">창원 주남저수지 자전거길</a></li>
							<li><a href="">제주 해맞이 해안로</a></li>
						</ul>
					</div>
					<!-- //tap1-2 -->
				</div>
			</div>
			<!-- //탭_내용 -->
		</div>
		<!-- //탭안의 탭 스타일 -->
	</div>
	<!-- //left -->


	<!-- mapinfo_center -->
	<div class="mapinfo_center">
	
		<div class="legend">
			<botton class="legend_btn">지도범례</botton>
			<div class="legend_subject">
				<ul>
					<li><img src="/resource/bike/img/content/sub07/bumrye_icon01.gif" alt="자전거길" />자전거길</li>
					<li><img src="/resource/bike/img/content/sub07/bumrye_icon02.gif" alt="주요거점" />주요거점</li>
					<li><img src="/resource/bike/img/content/sub07/bumrye_icon03.gif" alt="인증센터" />인증센터</li>
				</ul>
				<div>
					<h5>편의시설</h5>
					<ul>
						<li><input type="checkbox" id="bumrye01" /><label for="bumrye01">화장실</label></li>
						<li><input type="checkbox" id="bumrye02" /><label for="bumrye02">음수대</label></li>
						<li><input type="checkbox" id="bumrye03" /><label for="bumrye03">공기주입기</label></li>
					</ul>
				</div>
				<div>
					<h5>주변정보</h5>
					<ul>
						<li><input type="checkbox" id="bumrye04" /><label for="bumrye04">자전거길</label></li>
						<li><input type="checkbox" id="bumrye05" /><label for="bumrye05">주변상세정보</label></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="mapinfo_btn">
			<a href="" class="mapinfo_btn01"><img src="/resource/bike/img/content/sub07/mapinfo_btn01.png" alt="일반지도" />일반지도</a>
			<a href="" class="mapinfo_btn02"><img src="/resource/bike/img/content/sub07/mapinfo_btn02.png" alt="항공사진" />항공사진</a>
		</div>

		<div class="map_full">
			<img src="/resource/bike/img/content/sub07/map_full.png" alt="지도" />
		</div>

		<script>
			$(document).ready(function(){
			  $(".legend_btn").click(function(){
				$(".legend_subject").toggle(500);
			  });
			});
		</script>

	</div>
	<!-- mapinfo_center -->
</div>
</body>
</html>