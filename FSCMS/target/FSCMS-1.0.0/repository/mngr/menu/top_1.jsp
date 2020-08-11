<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<ul class="nav">
	<c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117192310' || currentMenu.menuUpperSn eq '1910117192310' || currentMenu.menuBestSn eq '1910117192310')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>메인 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117193756' || currentMenu.menuUpperSn eq '1910117193756')) }">active</c:if>">
				<a href="/adm/banner/estbs/list.do?key=1910117193756" target="_self">
					
					팝업존 관리
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
			<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2001026095712' || currentMenu.menuUpperSn eq '2001026095712')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					통계 간행물
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026096978') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026096978" target="_self">
							통계월보
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026097216') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026097216" target="_self">
							주민등록인구통계
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026097628') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026097628" target="_self">
							사업체 조사
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026097986') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026097986" target="_self">
							광업,제조업 조사
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026098514') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026098514" target="_self">
							세종의 사회지표
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026099156') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026099156" target="_self">
							일자리 인식실태조사
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026099577') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026099577" target="_self">
							청년통계
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026099863') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026099863" target="_self">
							장애인 통계
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026100183') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026100183" target="_self">
							청년사회경제 실태조사
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026100629') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026100629" target="_self">
							여성통계
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2001026112270' || currentMenu.menuUpperSn eq '2001026112270')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					한눈에보는 세종
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026113276') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026113276" target="_self">
							인구현황
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1912019128669' || currentMenu.menuUpperSn eq '1912019128669')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					통계소식
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026116817') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026116817" target="_self">
							통계알림
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026118872') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026118872" target="_self">
							통계자료실
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026119212') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026119212" target="_self">
							경제동향
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '2001026119494') }">class="active"</c:if>>
						<a href="/adm/bbs/list.do?key=2001026119494" target="_self">
							기타 통계동향
						</a>
					</li>
					</c:if>
				</ul>
				
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
	<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '1910117195303' || currentMenu.menuUpperSn eq '1910117195303' || currentMenu.menuBestSn eq '1910117195303')) }">active</c:if>">

		<a href="/adm/cntnts/list.do?key=1910117195303" target="_self">
			
			<span>콘텐츠 관리</span>
		</a>
		
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
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910081651592') }">class="active"</c:if>>
						<a href="/adm/mngr/list.do?key=1910081651592" target="_self">
							관리자 회원 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910081659511') }">class="active"</c:if>>
						<a href="/adm/mngr/role/list.do?key=1910081659511" target="_self">
							관리자 역할 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1812040854084') }">class="active"</c:if>>
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
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910109286826') }">class="active"</c:if>>
						<a href="/adm/code/cl/list.do?key=1910109286826" target="_self">
							분류코드 관리
						</a>
					</li>
					</c:if><c:if test="${ true }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910109287296') }">class="active"</c:if>>
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
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2001208764318' || currentMenu.menuUpperSn eq '2001208764318')) }">active</c:if>">
				<a href="/adm/ix/estbs/form.do?key=2001208764318" target="_self">
					
					e-지방지표 설정
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2002022495094' || currentMenu.menuUpperSn eq '2002022495094')) }">active</c:if>">
				<a href="/adm/emd/estbs/list.do?key=2002022495094" target="_self">
					
					읍면동 설정
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
	</c:if><c:if test="${ true }">
	<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2001218595962' || currentMenu.menuUpperSn eq '2001218595962' || currentMenu.menuBestSn eq '2001218595962')) }">active</c:if>">

		<a href="/adm/stat/saver.do?key=2001218595962" target="_self">
			
			<span>통계데이터입력</span>
		</a>
		
	</li>
	</c:if><c:if test="${ true }">
	<li class="has-sub <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2004233211751' || currentMenu.menuUpperSn eq '2004233211751' || currentMenu.menuBestSn eq '2004233211751')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>내부현안</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2004233213037' || currentMenu.menuUpperSn eq '2004233213037')) }">active</c:if>">
				<a href="/adm/trnsfrn/cmpntrt/form.do?key=2004233213037" target="_self">
					
					전입자수/사유별 구성비
				</a>
				
			</li>
			</c:if><c:if test="${ true }">
			<li class=" <c:if test="${ !empty currentMenu && ((currentMenu.menuSn eq '2004259185381' || currentMenu.menuUpperSn eq '2004259185381')) }">active</c:if>">
				<a href="/adm/drct/list.do?key=2004259185381" target="_self">
					
					시장님 지시사항 관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if>
</ul>