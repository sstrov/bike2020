<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fs-userRole.tld" prefix="roleUser" %>

<nav>
			<ul class="gnb-list">
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1910168793914' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">통계데이터</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/stats/form.do?key=1910168795224' />" target="_self">세종통계데이터</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/stats/visual/list.do?key=1910168795525' />" target="_self">지도기반데이터셋</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/twdcvln/anals/form.do?key=1911210355934' />" target="_self">대민분석서비스</a>
						</li>
						
					</ul>
					
				</li>
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1910168794317' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">시각화서비스</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/stats/mthpr/form.do?key=1911210358974' />" target="_self">통계월보</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/stats/examin/form.do?key=1911210359546?ty=2' />" target="_self">조사자료 통계</a>
						</li>
						
					</ul>
					
				</li>
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1910168794919' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">통계 간행물</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1910168795947' />" target="_self">통계연보</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1910168796261' />" target="_self">통계월보</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1910168796496' />" target="_self">주민등록인구통계</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1910168796725' />" target="_self">사업체 조사</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210363848' />" target="_self">광업, 제조업 조사</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210364730' />" target="_self">세종의 사회지표</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210365431' />" target="_self">교통량 조사</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210366547' />" target="_self">일자리 인식실태조사</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210367366' />" target="_self">청년통계</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210367810' />" target="_self">장애인 통계</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210368217' />" target="_self">청년사회경제 실태조사</a>
						</li>
						
					</ul>
					
				</li>
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1911210369753' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">한눈에보는 세종</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/sub.do?key=1911210370537' />" target="_self">기본현황</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210370900' />" target="_self">행정연혁</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210371157' />" target="_self">지표로본 세종</a>
						</li>
						
					</ul>
					
				</li>
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1911210372234' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">Open API</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210372903' />" target="_self">Open API 소개</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210373402' />" target="_self">Open API 사용방법</a>
						</li>
						
					</ul>
					
				</li>
				
				<li class="gnb-item">
					<a href="<c:url value='/sub.do?key=1911210374043' />" target="_self" class="btn btn-gnb-item" id="fs_top_menu">통계소식</a>
					
					<ul class="depth-list">
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210374488' />" target="_self">통계알림</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210374844' />" target="_self">통계자료실</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210375203' />" target="_self">경제동향</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/bbs/list.do?key=1911210375600' />" target="_self">기타 통계동향</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210376147' />" target="_self">관련사이트</a>
						</li>
						
						<li class="depth-item">
							<a href="<c:url value='/content.do?key=1911210376503' />" target="_self">통계공표일정</a>
						</li>
						
					</ul>
					
				</li>
				
			</ul>
		</nav>