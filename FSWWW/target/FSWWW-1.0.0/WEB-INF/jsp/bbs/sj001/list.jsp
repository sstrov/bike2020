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
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                        prefix="xss" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/acgeo.board.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/skin.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/slick.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/common.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/sub-common.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/table.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/C-statisticalPublication/C2-board.css' />">

<script src="<c:url value='/resource/cmm/js/jquery-validate/jquery.validate.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/dist/parsley.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/src/i18n/ko.js' />"></script>

<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script src="<c:url value='/resource/cmm/js/utils.js' />"></script>
<script src="<c:url value='/resource/cmm/js/jquery_utils.js' />"></script>
<script src="<c:url value='/resource/brd/fs_ra001/js/brd.js' />"></script>

<style>
select.form-control { padding-right:10px !important; background-image:none !important; -webkit-appearance:menulist; }
.border-container td { line-height:30px; }
</style>
<script>
// 보기 페이지 이동
function goView(key, stype) {
	<c:choose>
		<c:when test="${ powRead }">
			var f = document.forms['frm'];
			
			if(stype == 'Y') {
				f.bbsSn.value = key;
				$('#cmntPw_form').modal('show')
				jQuery('#cmntPwFrm').validate({
					submitHandler: function(form) {
						$.ajax({
							url     : "<c:url value='/bbs/isExistPw.do' />?key=${ param.key }",
							type    : "POST",
							data    : {
								'bbsSn' : f.bbsSn.value,
								'regPw'  : form.keyword.value
							},
							dataType: "json",
							error   : function(r, s, e) {
								alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
							},
							success:function(result) {
								var item = result[0];
								if(item.msg != '') {
									alert(item.msg);
									form.keyword.value = "";
									form.keyword.focus();
									return false;
								} else {
									f.method = "get";
									f.action = "<c:url value='/bbs/view.do' />";
									f.submit();
								}
							}
						});
					}
				});
			} else {
				f.method = "get";
				f.bbsSn.value = key;
				f.action = "<c:url value='/bbs/view.do' />";
				f.submit();
			}
		</c:when>
		<c:otherwise>
			alert("권한이 없습니다.");
		</c:otherwise>
	</c:choose>
}

<c:if test="${ !powList }">
	$(document).ready(function() {
		alert("권한이 없습니다.");
		history.back();
	});
</c:if>
/*
 * pagination 페이지 링크 function
 */
function fn_egov_link_page(pageNo){
	<c:if test="${ powList }">
		var f = document.forms['frm'];
	
		f.pageIndex.value = pageNo;
		f.action          = "<c:url value='/bbs/list.do' />";
		f.submit();
	</c:if>
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="bbsSn">
	<input type="hidden" name="key" value="${ param.key }">
	
	<form:hidden path="pageIndex" />
	
	<c:if test="${ bbsEstbs.hderCntntsAt eq 'Y' }">
		<jsp:include page="/repository/www/content/${ param.key }.jsp" />
	</c:if>
	
	<c:if test="${ !empty bbsEstbs.hderTag }">
		${ xss:decode(bbsEstbs.hderTag) }
	</c:if>
	
	<div class="border-container">
		<h2 class="hide">인구현황 내용 시작</h2>
		<div class="ui program--search">
			<div class="float-md-left">
				<c:set var="pageSIndex" value="${ searchVO.pageIndex }" />
				<c:if test="${ searchVO.pageIndex lt 1 }">
					<c:set var="pageSIndex" value="1" />
				</c:if>
				
				<c:set var="pageMIndex" value="${ tCount / searchVO.maxList }" />
				<c:choose>
					<c:when test="${ pageMIndex le 0 }">
						<c:set var="pageMIndex" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="pageMIndex" value="${ pageMIndex + (1-(pageMIndex%1))%1 }" />
					</c:otherwise>
				</c:choose>
				
				<div class="ui program--count">
					<span>총 게시물 <strong> ${ tCount + fn:length(notiList) }</strong></span>,
					<span class="ui program--division-line">페이지 <strong>${ pageSIndex }</strong>/ <fmt:formatNumber type="number">${ pageMIndex }</fmt:formatNumber>
					</span>
				</div>
			</div>
			<div class="search_inner clearfix float-md-right">
				<div class="fieldset">
					<div class="search--select">
						<!-- select추가시 -->
						<span>
							<form:select title="검색 카테고리를 선택하세요" path="sc" cssClass="form-control">
								<form:option value="">전체</form:option>
								<c:if test="${ !empty searchList }">
									<c:forEach var="list" items="${ searchList }">
										<c:if test="${ list.bbsFieldCode ne 'ctgryNm' }">
											<form:option value="${ str:getColumnNm(list.bbsFieldCode) }">${ list.bbsFieldNm }</form:option>
										</c:if>
									</c:forEach>
								</c:if>
							</form:select>
						</span>
						<!-- select추가시 -->
					</div>
					<div class="search--text">
						<span>
							<form:input path="sw" title="검색어를 넣어주세요" cssClass="form-control" placeholder="검색어를 넣어주세요" style="${ swStyle }" onkeypress="if(event.keyCode == 13){ fn_egov_link_page(1); return false; }" />
						</span>
					</div>
					<div class="search--btn">
						<span class="btn--submit">
							<input type="submit" value="검색" title="검색" onclick="fn_egov_link_page(1); return false;" />
						</span>
					</div>
					<div class="program--page">
						<label for="numPerPage">페이지당 게시물수</label>
						<div class="search--select">
							<span>
								<form:select title="페이지당 게시물 수를 선택하세요" path="maxList" cssClass="form-control">
									<form:option value="10">10</form:option>
									<form:option value="20">20</form:option>
									<form:option value="30">30</form:option>
									<form:option value="50">50</form:option>
								</form:select>
							</span>
						</div>
						<button type="button" title="적용" onclick="fn_egov_link_page(1); return false;">적용</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 리스트 시작 -->
		<div class="no-more-tables">
			<table class="board_list table table-default" data-rwdb="yes">
				<caption>
					${ bbsEstbs.bbsEstbsNm } 게시물 목록으로 번호, 
					<c:if test="${ !empty fieldList }">
						<c:forEach var="list" items="${ fieldList }" varStatus="i">
							<c:if test="${ !i.first }">, </c:if>${ list.bbsFieldNm }
						</c:forEach>
					</c:if> 
					에 대한 정보를 표시합니다.
				</caption>
				<colgroup>
					<col style="width:70px;">
					<c:if test="${ !empty fieldList }">
						<c:forEach var="list" items="${ fieldList }">
							<col style="width:${ list.fieldListSize };">
						</c:forEach>
					</c:if>
				</colgroup>
				<thead class="thead-light">
					<tr>
						<th scope="col">번호</th>
						<c:if test="${ !empty fieldList }">
							<c:forEach var="list" items="${ fieldList }">
								<c:choose>
									<c:when test="${ list.bbsFieldCode eq 'rdcnt' }">
										<th scope="col" class="tablet-hidden">${ list.bbsFieldNm }</th>
									</c:when>
									<c:otherwise>
										<th scope="col">${ list.bbsFieldNm }</th>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:set var="cols" value="1" />
					<c:if test="${ !empty fieldList }">
						<c:set var="cols" value="${ 1 + fn:length(fieldList) }" />
					</c:if>
					<c:choose>
						<c:when test="${ !powList }">
							<tr>
								<td colspan="${ cols }" class="listNum" style="line-height:100px;">목록 보기 권한이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:if test="${ !empty notiList }">
								<c:forEach var="item" items="${ notiList }">
									<tr>
										<td class="listNum"><img src="<c:url value='/resource/brd/fs_ra001/img/icon_notice.png' />" alt="중요 공지사항" /></td>
										<c:if test="${ !empty fieldList }">
											<c:forEach var="list" items="${ fieldList }">
												<c:set var="titleCd" value="nomal" />
												<c:if test="${ list.bbsFieldCode eq 'bbsSj' }">
													<c:set var="titleCd" value="subject" />
												</c:if>
												<c:if test="${ list.bbsFieldCode eq 'ctgryNm' }">
													<c:set var="titleCd" value="category" />
												</c:if>
												<c:if test="${ list.bbsFieldCode eq 'file' or list.bbsFieldCode eq 'rdcnt' }">
													<c:set var="titleCd" value="nomal txtHide" />
												</c:if>
												
												<td data-cell-header="${ list.bbsFieldNm }" class="${ titleCd }" <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
												
													<c:choose>
														<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
															<a href="#this" onclick="goView('${ item.bbsSn }', '');">
																<c:choose>
																	<c:when test="${ bbsEstbs.registerNmTy eq 'NM' }">
																		${ item.registerNm }
																	</c:when>
																	<c:when test="${ bbsEstbs.registerNmTy eq 'ID' }">
																		<c:choose>
																			<c:when test="${ !empty item.userId }">${ item.userId }</c:when>
																			<c:when test="${ !empty item.mngrId }">${ item.mngrId }</c:when>
																			<c:otherwise>${ item.registerNm }</c:otherwise>
																		</c:choose>
																	</c:when>
																	<c:when test="${ bbsEstbs.registerNmTy eq 'NMS' }">
																		${ item.registerNm }
																	</c:when>
																	<c:when test="${ bbsEstbs.registerNmTy eq 'IDS' }">
																		<c:choose>
																			<c:when test="${ !empty item.userId }">${ item.userId }</c:when>
																			<c:when test="${ !empty item.mngrId }">${ item.mngrId }</c:when>
																			<c:otherwise>${ item.registerNm }</c:otherwise>
																		</c:choose>
																	</c:when>
																</c:choose>
															</a>
														</c:when>
														
														<c:when test="${ list.bbsFieldCode eq 'registDe' }">
															<a href="#this" onclick="goView('${ item.bbsSn }', '');">
																<dt:format pattern="yyyy-MM-dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode]}</dt:parse>
																</dt:format>
															</a>
														</c:when>
														
														<c:when test="${ list.bbsFieldCode eq 'bbsSj' }">
															
															<c:choose>
																<c:when test="${ item.deleteAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																<c:otherwise>
																	<a href="#this" onclick="goView('${ item.bbsSn }', '');">
																		<string:total len="${ bbsEstbs.sjLtLmtt }" tail="...">${ item[list.bbsFieldCode]}</string:total>
																	</a>
																</c:otherwise>
															</c:choose>
														</c:when>
														
														<c:when test="${ list.bbsFieldCode eq 'file' }">
															<!-- 첨부파일 -->
															<c:if test="${ !empty item.fileExt }">
																<a href="#this" onclick="goView('${ item.bbsSn }', '');" title="${ item.bbsSj } 첨부파일 아이콘">
																	<span class="btn btn-file btn-not-ico">
																		<i class="ir ir-bbs ir-file left ir-${ item.fileExt }"></i>
																	</span>
																</a>
																	<%-- <img src="/resource/brd/fs_ra001/img/file/ico_${ item.fileExt }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt=""> --%>
															</c:if>
														</c:when>
														
														<c:when test="${ list.bbsFieldCode eq 'rdcnt' }">
															<a href="#this" onclick="goView('${ item.bbsSn }', '');"><span class="mobile-view">조회수: </span>${ item[list.bbsFieldCode] }</a>
														</c:when>
														
														<c:otherwise>
															<a href="#this" onclick="goView('${ item.bbsSn }', '');">${ item[list.bbsFieldCode]}</a>
														</c:otherwise>
													</c:choose>
												</td>
											</c:forEach>
										</c:if>
									</tr>
								</c:forEach>
							</c:if>
							
							<c:choose>
								<c:when test="${ !empty brdList }">
									<c:forEach var="item" items="${ brdList }" varStatus="i">
										<tr>
											<td class="listNum">${ tCount - ((pageSIndex - 1) * bbsEstbs.listIndictCo) - (i.count - 1) }</td>
											<c:if test="${ !empty fieldList }">
												<c:forEach var="list" items="${ fieldList }">
													<c:set var="titleCd" value="nomal" />
													<c:if test="${ list.bbsFieldCode eq 'bbsSj' }">
														<c:set var="titleCd" value="subject" />
													</c:if>
													<c:if test="${ list.bbsFieldCode eq 'ctgryNm' }">
														<c:set var="titleCd" value="category" />
													</c:if>
													<c:if test="${ list.bbsFieldCode eq 'file' or list.bbsFieldCode eq 'rdcnt' }">
														<c:set var="titleCd" value="nomal txtHide" />
													</c:if>
													
													<td data-cell-header="${ list.bbsFieldNm }" class="${ titleCd }" <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
														<c:choose>
															<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
																<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
																	<c:choose>
																		<c:when test="${ bbsEstbs.registerNmTy eq 'NM' }">
																			${ item.registerNm }
																		</c:when>
																		<c:when test="${ bbsEstbs.registerNmTy eq 'ID' }">
																			${ item.registerNm }
																		</c:when>
																		<c:when test="${ bbsEstbs.registerNmTy eq 'NMS' }">
																			${ item.registerNm }
																		</c:when>
																		<c:when test="${ bbsEstbs.registerNmTy eq 'IDS' }">
																			${ item.registerNm }
																		</c:when>
																	</c:choose>
																</a>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'registDe' }">
																<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
																	<dt:format pattern="yyyy-MM-dd">
																		<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode]}</dt:parse>
																	</dt:format>
																</a>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'bbsSj' }">
																<c:if test="${ item.bbsDp gt 1 }">
																	<c:set var="rePd" value="${ (item.bbsDp - 1) * 10 }" />
																	
																	<img src="<c:url value='/resource/brd/fs_ra001/img/icon_re.gif' />" alt="답변" style="padding-left:${ rePd }px;" />
																</c:if>
																
																<c:if test="${ item.secretAt eq 'Y' }">
																	<!-- 비밀글 -->
																	<img src="<c:url value='/resource/brd/fs_ra001/img/icon_lock.gif' />" alt="비밀글" />
																</c:if>
																
																<c:choose>
																	<c:when test="${ item.deleteAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																	<c:otherwise>
																		<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
																			<c:if test="${ item.bbsDp gt 1 && item.upperDeleteAt eq 'Y' }">
																				<span style="color:#ccc;">[삭제된 글의 답변 입니다.]</span>
																			</c:if>
																			<string:total len="${ bbsEstbs.sjLtLmtt }" tail="...">${ item[list.bbsFieldCode]}</string:total>
																		</a>
																	</c:otherwise>
																</c:choose>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'file' }">
																<!-- 첨부파일 -->
																<c:if test="${ !empty item.fileExt }">
																	<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;" title="${ item.bbsSj } 첨부파일 아이콘">
																		<span class="btn btn-file btn-not-ico">
																			<i class="ir ir-bbs ir-file left ir-${ item.fileExt }"></i>
																		</span>
																	</a>
																	<%-- <img src="/resource/brd/fs_ra001/img/file/ico_${ item.fileExt }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt=""> --%>
																</c:if>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'rdcnt' }">
																<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
																	<span class="mobile-view">조회수: </span>${ item[list.bbsFieldCode] }
																</a>
															</c:when>
															
															<c:otherwise>
																<a href="#this" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
																	${ item[list.bbsFieldCode] }
																</a>
															</c:otherwise>
														</c:choose>
													</td>
												</c:forEach>
											</c:if>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="${ cols }" class="listNum" style="line-height:100px;">등록된 정보가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- 리스트 끝 -->
	</div>
	
	<div class="btnArea">
		<c:if test="${ powWrite }">
			<a class="btn btn_write" href="<c:url value='/bbs/form.do' />?key=${ param.key }">쓰기</a>
		</c:if>
	</div>
	
	<div class="pagination-box">
		<div class="pagination">
			<ul>
				<ui:pagination paginationInfo="${ paginationInfo }"
					type="sj001List"
					jsFunction="fn_egov_link_page" />
			</ul>
		</div>
	</div>
	
	<c:if test="${ bbsEstbs.footCntntsAt eq 'Y' }">
		<jsp:include page="/repository/www/content/${ param.key }.jsp" />
	</c:if>
	
	<c:if test="${ !empty bbsEstbs.footTag }">
		${ xss:decode(bbsEstbs.footTag) }
	</c:if>

</form:form>

<div class="modal fade cmntPw_form" id="cmntPw_form" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">비밀번호 확인</h5>
			</div>
			<div class="modal-body">
				<div class="tab-content padding-10" style="margin-bottom:0;">
					<div class="tab-pane fade in active" id="tab1">
						<form id="cmntPwFrm" name="cmntPwFrm" method="post" class="form-horizontal" data-parsley-validate="true">
					
							<!-- # 검색 Start -->
							<fieldset class="form-horizontal" style="margin-top:10px;">
							
								<div class="form-group">
									<div class="col-md-12">
										<table border="0" cellspacing="0" cellpadding="0" style="float:left; width:100%; border:0;">
											<colgroup>
												<col />
												<col width="20%" />
											</colgroup>
											<tr>
												<td style="padding:0; border:0;">
													<input title="비밀번호를 입력하세요." type="password" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#element" placeholder="비밀번호 입력" />
													<span id="element"></span>
												</td>
												<td style="padding:0; border:0; vertical-align: top;">
													<button type="submit" class="btn btn-primary">확인</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							
							</fieldset>
						
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>