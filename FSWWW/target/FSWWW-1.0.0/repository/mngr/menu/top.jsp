<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-mngrRole.tld" prefix="roleMngr" %>

<ul class="nav">
	<c:if test="${ roleMngr:acc('1910117192310', admSession.roleSn) }">
	<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117192310' || roleMngr:use(currentMenu.menuSn, '1910117192310')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>메인 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ roleMngr:acc('1910117193320', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117193320' || roleMngr:use(currentMenu.menuSn, '1910117193320')) }">active</c:if>">
				<a href="/adm/popup/list.do?key=1910117193320" target="_self">
					
					팝업 관리
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910117193756', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117193756' || roleMngr:use(currentMenu.menuSn, '1910117193756')) }">active</c:if>">
				<a href="/adm/banner/estbs/list.do?key=1910117193756" target="_self">
					
					팝업존 관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ roleMngr:acc('1910117194253', admSession.roleSn) }">
	<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117194253' || roleMngr:use(currentMenu.menuSn, '1910117194253')) }">active</c:if>">

		<a href="/adm/user/menu/form.do?key=1910117194253" target="_self">
			
			<span>메뉴 관리</span>
		</a>
		
	</li>
	</c:if><c:if test="${ roleMngr:acc('1910117194592', admSession.roleSn) }">
	<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117194592' || roleMngr:use(currentMenu.menuSn, '1910117194592')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>게시판 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ roleMngr:acc('1912034653520', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1912034653520' || roleMngr:use(currentMenu.menuSn, '1912034653520')) }">active</c:if>">
				<a href="/adm/bbs/list.do?key=1912034653520" target="_self">
					
					공지사항
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1912034653844', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1912034653844' || roleMngr:use(currentMenu.menuSn, '1912034653844')) }">active</c:if>">
				<a href="/adm/bbs/estbs/list.do?key=1912034653844" target="_self">
					
					게시판 설정
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ roleMngr:acc('1910117195936', admSession.roleSn) }">
	<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117195936' || roleMngr:use(currentMenu.menuSn, '1910117195936')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>회원 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ roleMngr:acc('1910117198547', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117198547' || roleMngr:use(currentMenu.menuSn, '1910117198547')) }">active</c:if>">
				<a href="/adm/user/list.do?key=1910117198547" target="_self">
					
					회원 관리
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910117199072', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117199072' || roleMngr:use(currentMenu.menuSn, '1910117199072')) }">active</c:if>">
				<a href="/adm/user/role/list.do?key=1910117199072" target="_self">
					
					회원 역할 관리
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ roleMngr:acc('1910117230564', admSession.roleSn) }">
	<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117230564' || roleMngr:use(currentMenu.menuSn, '1910117230564')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>이력 관리</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ roleMngr:acc('1910117231246', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117231246' || roleMngr:use(currentMenu.menuSn, '1910117231246')) }">active</c:if>">
				<a href="/adm/mngr/login/hist/list.do?key=1910117231246" target="_self">
					
					관리자 로그인 이력
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910117231656', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910117231656' || roleMngr:use(currentMenu.menuSn, '1910117231656')) }">active</c:if>">
				<a href="/adm/data/chghst/list.do?key=1910117231656" target="_self">
					
					데이터 변경 이력
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910161078978', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910161078978' || roleMngr:use(currentMenu.menuSn, '1910161078978')) }">active</c:if>">
				<a href="/adm/indvdlinfo/acces/hist/list.do?key=1910161078978" target="_self">
					
					개인정보 접근 이력
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910179784526', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910179784526' || roleMngr:use(currentMenu.menuSn, '1910179784526')) }">active</c:if>">
				<a href="/adm/error/hist/list.do?key=1910179784526" target="_self">
					
					오류 이력
				</a>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if><c:if test="${ roleMngr:acc('1910072181253', admSession.roleSn) }">
	<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910072181253' || roleMngr:use(currentMenu.menuSn, '1910072181253')) }">active</c:if>">

		<a href="#none" target="_self">
			<b class="caret pull-right"></b>
			<span>시스템 환경설정</span>
		</a>
		
		<ul class="sub-menu">
			<c:if test="${ roleMngr:acc('1910072213465', admSession.roleSn) }">
			<li class=" <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910072213465' || roleMngr:use(currentMenu.menuSn, '1910072213465')) }">active</c:if>">
				<a href="/adm/site/form.do?key=1910072213465" target="_self">
					
					사이트 정보 관리
				</a>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910081650856', admSession.roleSn) }">
			<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910081650856' || roleMngr:use(currentMenu.menuSn, '1910081650856')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					관리자 관리
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ roleMngr:acc('1910081651592', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910081651592' || roleMngr:use(currentMenu.menuSn, '1910081651592')) }">class="active"</c:if>>
						<a href="/adm/mngr/list.do?key=1910081651592" target="_self">
							관리자 회원 관리
						</a>
					</li>
					</c:if><c:if test="${ roleMngr:acc('1910081659511', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910081659511' || roleMngr:use(currentMenu.menuSn, '1910081659511')) }">class="active"</c:if>>
						<a href="/adm/mngr/role/list.do?key=1910081659511" target="_self">
							관리자 역할 관리
						</a>
					</li>
					</c:if><c:if test="${ roleMngr:acc('1812040854084', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1812040854084' || roleMngr:use(currentMenu.menuSn, '1812040854084')) }">class="active"</c:if>>
						<a href="/adm/mngr/menu/form.do?key=1812040854084" target="_self">
							관리자 메뉴 관리
						</a>
					</li>
					</c:if><c:if test="${ roleMngr:acc('1910161133572', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910161133572' || roleMngr:use(currentMenu.menuSn, '1910161133572')) }">class="active"</c:if>>
						<a href="/adm/mngr/estbs/form.do?key=1910161133572" target="_self">
							관리자 설정
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if><c:if test="${ roleMngr:acc('1910109286303', admSession.roleSn) }">
			<li class="has-sub <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910109286303' || roleMngr:use(currentMenu.menuSn, '1910109286303')) }">active</c:if>">
				<a href="#none" target="_self">
					<b class="caret pull-right"></b>
					공통코드 관리
				</a>
				
				<ul class="sub-menu">
					<c:if test="${ roleMngr:acc('1910109286826', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910109286826' || roleMngr:use(currentMenu.menuSn, '1910109286826')) }">class="active"</c:if>>
						<a href="/adm/code/cl/list.do?key=1910109286826" target="_self">
							분류코드 관리
						</a>
					</li>
					</c:if><c:if test="${ roleMngr:acc('1910109287296', admSession.roleSn) }">
					<li <c:if test="${ !empty currentMenu && (currentMenu.menuSn eq '1910109287296' || roleMngr:use(currentMenu.menuSn, '1910109287296')) }">class="active"</c:if>>
						<a href="/adm/code/list.do?key=1910109287296" target="_self">
							세부코드 관리
						</a>
					</li>
					</c:if>
				</ul>
				
			</li>
			</c:if>
		</ul>
		
	</li>
	</c:if>
</ul>