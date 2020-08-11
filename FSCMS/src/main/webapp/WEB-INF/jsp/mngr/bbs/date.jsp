<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt"                   prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form"       prefix="form" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui"                prefix="ui" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"                     prefix="string" %>
<%@ taglib uri="/WEB-INF/tld/fs-str.tld"                        prefix="str" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/resource/mngr/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />

<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script type="text/javascript">
<c:if test="${ !empty msg }">
	alert('${ msg }');
</c:if>

// 보기 페이지 이동
function goView(key) {
	var f = document.forms['frm'];
	
	f.bbsSn.value = key;
	f.action = "/${ admURI }/bbs/view.do?key=${ param.key }";
	f.submit();
}

/**
 * 등록 페이지 이동
 */
function goForm(url) {
	var f = document.forms['frm'];
	
	f.bbsSn.value = '';
	f.action = url;
	f.submit();
}

/**
 * 선택된 내용 삭제
 */
function goDeleteForList(url) {
	if($("input:checkbox[class='chkKey']:checked").length == 0) {
		alert("삭제할 내용을 1개이상 선택해 주세요.");
		return;
	}
	
	if(confirm("삭제한 자료는 복구가 불가능 합니다.\n삭제 하시겠습니까?")) {
		var f = document.forms['frm'];
		
		// 로딩 이미지 호출
		lodingOn(document.body, "삭제중 입니다.", 200, 150);
		f.action = url;
		f.submit();
	}
}

/*
 * pagination 페이지 링크 function
 */
function fn_egov_link_page(pageNo){
	var f = document.forms['frm'];

	f.pageIndex.value = pageNo;
	f.action          = "/${ admURI }/bbs/list.do?key=${ param.key }";
	f.submit();
}

function subCtgry(v){
	lodingFixedOn("정보 조회중 입니다.", 300, 180);
	
	var f = document.forms['frm'];
	var p = (v == '0')? 1 : v;
	
	f.pageIndex.value = p;
	
	$.ajax({
		url     : "/${ admURI }/bbs/getCtgrySubList.do?key=${ param.key }",
		type    : "POST",
		data    : {'bbsCtgrySn' : v},
		dataType: "json",
		async	: true,
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			lodingOff(document.body);
		},
		success:function(result) {
			if(result != null && result.length > 0) {
				
				$('#sc_ctgrySubSn').empty();
				$('#sc_ctgrySubSn').append('<option>전체</option>');
				for(var i=0; i<result.length; i++) {
					var item = result[i];
					
					var opt = $('<option value='+item.bbsCtgrySubSn+'>'+item.ctgrySubNm+'</option>');
					$('#sc_ctgrySubSn').append(opt);
				}
			} else {
				
			}
			
			// 로딩 이미지 제거
			lodingOff(document.body);
		}
	});
	
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="bbsSn" />
	
	<form:hidden path="pageIndex" />
	<form:hidden path="orderBy" />

	<div class="sub0302">
		<div class="contentBody">
			<div id="board">
				<div class="calendar">
					<div class="date_ctrl">
						<div class="prev_ctrl">
							<a href="/${ admURI }/bbs/list.do?key=${ param.key }&sYear=${ searchVO.sYear - 1 }&sMonth=${ searchVO.sMonth }">이전 년</a>
							<a href="/${ admURI }/bbs/list.do?key=${ param.key }&sYear=${ searchVO.sYear }&sMonth=${ searchVO.sMonth - 1 }">이전 월</a>
						</div>
						<div class="date_info">${ searchVO.sYear }년 ${ searchVO.sMonth }월</div>
						<div class="next_ctrl">
							<a href="/${ admURI }/bbs/list.do?key=${ param.key }&sYear=${ searchVO.sYear }&sMonth=${ searchVO.sMonth + 1 }" id="nextMonth">다음 월</a>
							<a href="/${ admURI }/bbs/list.do?key=${ param.key }&sYear=${ searchVO.sYear + 1 }&sMonth=${ searchVO.sMonth }">다음 년</a>
						</div>
					</div>
				</div>
				<table class="rent_calendar" summary="요일별 대관현황 정보를 제공하는 달력입니다">
					<caption>
					대관 달력
					</caption>
					<colgroup>
						<col width="14.28%">
						<col width="14.28%">
						<col width="14.28%">
						<col width="14.28%">
						<col width="14.28%">
						<col width="14.28%">
						<col width="14.28%">
					</colgroup>
					<thead>
						<tr>
							<th class="sun" scope="col">일</th>
							<th scope="col">월</th>
							<th scope="col">화</th>
							<th scope="col">수</th>
							<th scope="col">목</th>
							<th scope="col">금</th>
							<th class="sat" scope="col">토</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="dayCnt" value="1" />
						<c:forEach begin="1" end="${ searchVO.maxWeeks }" varStatus="idxOut">
							<tr>
								<c:forEach begin="1" end="7" varStatus="idxIn">
									<c:set var="isDay" value="N"/>
									<c:set var="tdCls" value="m_none" />
									
									<c:if test="${idxIn.first}">
										<c:set var="tdCls" value="sun m_none" />
									</c:if>
									<c:if test="${idxIn.last}">
										<c:set var="tdCls" value="sat" />
									</c:if>
									
									<td class="${ tdCls }">
										<c:choose>
											<c:when test="${idxOut.first}">
												<c:if test="${idxIn.count ge searchVO.firstDayOfWeek}">
													<c:set var="isDay" value="Y"/>
												</c:if>
											</c:when>
											<c:when test="${idxOut.last}">
												<c:if test="${idxIn.count le searchVO.lastDayOfWeek}">
													<c:set var="isDay" value="Y"/>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:set var="isDay" value="Y"/>
											</c:otherwise>
										</c:choose>
										
										<c:if test="${isDay eq 'Y'}">
											${dayCnt}
											
											<fmt:formatNumber var="fSyear" pattern="0000">${searchVO.sYear}</fmt:formatNumber>
											<fmt:formatNumber var="fSmonth" pattern="00">${searchVO.sMonth}</fmt:formatNumber>
											<fmt:formatNumber var="fSday" pattern="00">${dayCnt}</fmt:formatNumber>
											
											<div class="cont">
												<ul class="able_rsvt">
													<li>
														<c:set var="fBgnde" value="${fSyear}-${fSmonth}-${fSday}"/>
						
														<c:forEach var="result" items="${brdList}" varStatus="status">
															<c:if test="${fBgnde eq result.bbsEtc1}">
																<c:url var="viewUrl" value="/${ admURI }/bbs/view.do">
																	<c:param name="key">${ param.key }</c:param>
																	<c:param name="bbsSn">${ result.bbsSn }</c:param>
																	<c:param name="sYear">${ result.sYear }</c:param>
																	<c:param name="sMonth">${ result.sMonth }</c:param>
																</c:url>
																<a href="${fn:replace(viewUrl, '&', '&amp;')}" >
																	<span class="user_nm"><c:out value="${result.bbsSj}"/></span><%-- <span class="time">(<c:out value="${result.bbsEtc2}"/>시 ~ <c:out value="${result.bbsEtc3}"/>시)</span> --%>
																</a>
															</c:if>
														
														</c:forEach>
													</li>
												</ul>
											</div>
											<c:set var="dayCnt" value="${dayCnt+1}"/>
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="panel panel-inverse">
			<div class="panel-body">
				<div style="float:left; width:100%; text-align:right;">
					<button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/bbs/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>