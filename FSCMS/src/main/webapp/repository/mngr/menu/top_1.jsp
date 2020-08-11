<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<ul class="nav">
	<c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117192310' || currentMenu.menuUpperSn eq '1910117192310' || currentMenu.menuBestSn eq '1910117192310')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>사이트관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117193320' || currentMenu.menuUpperSn eq '1910117193320')) }">active</c:if>">
				<a href="/adm/popup/list.do?key=1910117193320" target="_self">
					
					팝업 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117193756' || currentMenu.menuUpperSn eq '1910117193756')) }">active</c:if>">
				<a href="/adm/banner/estbs/list.do?key=1910117193756" target="_self">
					
					배너 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176837709' || currentMenu.menuUpperSn eq '2006176837709')) }">active</c:if>">
				<a href="/adm/sub.do?key=2006176837709" target="_self">
					
					MAIN 배너관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176838598' || currentMenu.menuUpperSn eq '2006176838598')) }">active</c:if>">
				<a href="/adm/app/version/form.do?key=2006176838598" target="_self">
					
					앱버전관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176839478' || currentMenu.menuUpperSn eq '2006176839478')) }">active</c:if>">
				<a href="/adm/user/login/hist/list.do?key=2006176839478" target="_self">
					
					회원관리 접속로그
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2008035037595' || currentMenu.menuUpperSn eq '2008035037595')) }">active</c:if>">
				<a href="/adm/edu/list.do?key=2008035037595" target="_self">
					
					교육일정 관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117194253' || currentMenu.menuUpperSn eq '1910117194253' || currentMenu.menuBestSn eq '1910117194253')) }">active</c:if>">

		<a href="/adm/user/menu/form.do?key=1910117194253" target="_self">
			
			<span>메뉴 관리</span>
		</a>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117194592' || currentMenu.menuUpperSn eq '1910117194592' || currentMenu.menuBestSn eq '1910117194592')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>게시판 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006263814733' || currentMenu.menuUpperSn eq '2006263814733')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006263814733" target="_self">
					
					뉴스/공지
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006263815555' || currentMenu.menuUpperSn eq '2006263815555')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006263815555" target="_self">
					
					행사일정관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006263816068' || currentMenu.menuUpperSn eq '2006263816068')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006263816068" target="_self">
					
					자료실
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006263816385' || currentMenu.menuUpperSn eq '2006263816385')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006263816385" target="_self">
					
					자전거길 체험 후기
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006263816828' || currentMenu.menuUpperSn eq '2006263816828')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006263816828" target="_self">
					
					질의응답
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1912019129111' || currentMenu.menuUpperSn eq '1912019129111')) }">active</c:if>">
				<a href="/adm/bbs/estbs/list.do?key=1912019129111" target="_self">
					
					게시판 설정
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176759357' || currentMenu.menuUpperSn eq '2006176759357' || currentMenu.menuBestSn eq '2006176759357')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>자전거/인증 정보</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176760458' || currentMenu.menuUpperSn eq '2006176760458')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006176760458" target="_self">
					
					자전거 관련 법령
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176761406' || currentMenu.menuUpperSn eq '2006176761406')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2006176761406" target="_self">
					
					전기자전거 목록
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176762413' || currentMenu.menuUpperSn eq '2006176762413')) }">active</c:if>">
				<a href="/adm/auth/upload/list.do?key=2006176762413" target="_self">
					
					DB업로드
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117195303' || currentMenu.menuUpperSn eq '1910117195303' || currentMenu.menuBestSn eq '1910117195303')) }">active</c:if>">

		<a href="/adm/cntnts/list.do?key=1910117195303" target="_self">
			
			<span>콘텐츠 관리</span>
		</a>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007061957378' || currentMenu.menuUpperSn eq '2007061957378' || currentMenu.menuBestSn eq '2007061957378')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>공모전 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007061958022' || currentMenu.menuUpperSn eq '2007061958022')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2007061958022" target="_self">
					
					공모전 소식
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007061958578' || currentMenu.menuUpperSn eq '2007061958578')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=2007061958578" target="_self">
					
					작품보기
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007061959109' || currentMenu.menuUpperSn eq '2007061959109')) }">active</c:if>">
				<a href="/adm/contest/estbs/list.do?key=2007061959109" target="_self">
					
					공모전 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007061959910' || currentMenu.menuUpperSn eq '2007061959910')) }">active</c:if>">
				<a href="/adm/sub.do?key=2007061959910" target="_self">
					
					공모전 작품관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117195936' || currentMenu.menuUpperSn eq '1910117195936' || currentMenu.menuBestSn eq '1910117195936')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>회원 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117198547' || currentMenu.menuUpperSn eq '1910117198547')) }">active</c:if>">
				<a href="/adm/user/list.do?key=1910117198547" target="_self">
					
					회원 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2007247334380' || currentMenu.menuUpperSn eq '2007247334380')) }">active</c:if>">
				<a href="/adm/auth/list.do?key=2007247334380" target="_self">
					
					수첩인증관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117199072' || currentMenu.menuUpperSn eq '1910117199072')) }">active</c:if>">
				<a href="/adm/user/role/list.do?key=1910117199072" target="_self">
					
					회원 역할 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176749091' || currentMenu.menuUpperSn eq '2006176749091')) }">active</c:if>">
				<a href="/adm/user/dropList.do?key=2006176749091" target="_self">
					
					탈퇴회원관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176750352' || currentMenu.menuUpperSn eq '2006176750352')) }">active</c:if>">
				<a href="/adm/push/user/list.do?key=2006176750352" target="_self">
					
					안내발송관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176751006' || currentMenu.menuUpperSn eq '2006176751006')) }">active</c:if>">
				<a href="/adm/push/list.do?key=2006176751006" target="_self">
					
					발송내용관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117230564' || currentMenu.menuUpperSn eq '1910117230564' || currentMenu.menuBestSn eq '1910117230564')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>이력 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117231246' || currentMenu.menuUpperSn eq '1910117231246')) }">active</c:if>">
				<a href="/adm/mngr/login/hist/list.do?key=1910117231246" target="_self">
					
					관리자 로그인 이력
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117231656' || currentMenu.menuUpperSn eq '1910117231656')) }">active</c:if>">
				<a href="/adm/data/chghst/list.do?key=1910117231656" target="_self">
					
					데이터 변경 이력
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910161078978' || currentMenu.menuUpperSn eq '1910161078978')) }">active</c:if>">
				<a href="/adm/indvdlinfo/acces/hist/list.do?key=1910161078978" target="_self">
					
					개인정보 접근 이력
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910179784526' || currentMenu.menuUpperSn eq '1910179784526')) }">active</c:if>">
				<a href="/adm/error/hist/list.do?key=1910179784526" target="_self">
					
					오류 이력
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117197403' || currentMenu.menuUpperSn eq '1910117197403' || currentMenu.menuBestSn eq '1910117197403')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>통계</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117200275' || currentMenu.menuUpperSn eq '1910117200275')) }">active</c:if>">
				<a href="/adm/stat/tot.do?key=1910117200275" target="_self">
					
					접속자 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117200580' || currentMenu.menuUpperSn eq '1910117200580')) }">active</c:if>">
				<a href="/adm/stat/year.do?key=1910117200580" target="_self">
					
					년도별 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117200839' || currentMenu.menuUpperSn eq '1910117200839')) }">active</c:if>">
				<a href="/adm/stat/month.do?key=1910117200839" target="_self">
					
					월별 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117201131' || currentMenu.menuUpperSn eq '1910117201131')) }">active</c:if>">
				<a href="/adm/stat/day.do?key=1910117201131" target="_self">
					
					일별 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117201385' || currentMenu.menuUpperSn eq '1910117201385')) }">active</c:if>">
				<a href="/adm/stat/week.do?key=1910117201385" target="_self">
					
					요일별 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117201683' || currentMenu.menuUpperSn eq '1910117201683')) }">active</c:if>">
				<a href="/adm/stat/menu.do?key=1910117201683" target="_self">
					
					메뉴별 통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176757180' || currentMenu.menuUpperSn eq '2006176757180')) }">active</c:if>">
				<a href="/adm/sub.do?key=2006176757180" target="_self">
					
					기본통계
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2006176757669' || currentMenu.menuUpperSn eq '2006176757669')) }">active</c:if>">
				<a href="/adm/sub.do?key=2006176757669" target="_self">
					
					구글분석기
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910072181253' || currentMenu.menuUpperSn eq '1910072181253' || currentMenu.menuBestSn eq '1910072181253')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>시스템 환경설정</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910072213465' || currentMenu.menuUpperSn eq '1910072213465')) }">active</c:if>">
				<a href="/adm/site/form.do?key=1910072213465" target="_self">
					
					사이트 정보 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910081650856' || currentMenu.menuUpperSn eq '1910081650856')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					관리자 관리
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910081651592' || currentMenu.menuUpperSn eq '1910081651592')) }">class="active"</c:if>>
						<a href="/adm/mngr/list.do?key=1910081651592" target="_self">
							관리자 회원 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910081659511' || currentMenu.menuUpperSn eq '1910081659511')) }">class="active"</c:if>>
						<a href="/adm/mngr/role/list.do?key=1910081659511" target="_self">
							관리자 역할 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1812040854084' || currentMenu.menuUpperSn eq '1812040854084')) }">class="active"</c:if>>
						<a href="/adm/mngr/menu/form.do?key=1812040854084" target="_self">
							관리자 메뉴 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910161133572') }">class="active"</c:if>>
						<a href="/adm/mngr/estbs/form.do?key=1910161133572" target="_self">
							관리자 설정
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910109286303' || currentMenu.menuUpperSn eq '1910109286303')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					공통코드 관리
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910109286826' || currentMenu.menuUpperSn eq '1910109286826')) }">class="active"</c:if>>
						<a href="/adm/code/cl/list.do?key=1910109286826" target="_self">
							분류코드 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910109287296' || currentMenu.menuUpperSn eq '1910109287296')) }">class="active"</c:if>>
						<a href="/adm/code/list.do?key=1910109287296" target="_self">
							세부코드 관리
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1912019159652' || currentMenu.menuUpperSn eq '1912019159652')) }">active</c:if>">
				<a href="/adm/bbs/skn/form.do?key=1912019159652" target="_self">
					
					게시판 스킨 관리
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2002269592224' || currentMenu.menuUpperSn eq '2002269592224')) }">active</c:if>">
				<a href="/adm/atchmnfl/mngr/list.do?key=2002269592224" target="_self">
					
					파일 관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if>
</ul>