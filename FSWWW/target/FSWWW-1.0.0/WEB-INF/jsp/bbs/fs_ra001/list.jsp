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
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/style.css" />
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/bootstrap.css" />

<script src="/resource/cmm/js/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/cmm/js/parsley/dist/parsley.js"></script>
<script src="/resource/cmm/js/parsley/src/i18n/ko.js"></script>

<script src="/resource/cmm/js/dates.js"></script>
<script src="/resource/cmm/js/utils.js"></script>
<script src="/resource/cmm/js/jquery_utils.js"></script>
<script src="/resource/brd/fs_ra001/js/brd.js"></script>

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
							url     : "/bbs/isExistPw.do?key=${ param.key }",
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
									f.action = "/bbs/view.do";
									f.submit();
								}
							}
						});
					}
				});
			} else {
				f.method = "get";
				f.bbsSn.value = key;
				f.action = "/bbs/view.do";
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
		f.action          = "/bbs/list.do";
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

	<!-- 게시판_목록 -->
	<div class="fs_list_box">
		<!--search_box-->
		<div class="search_warp">
			<div class="search_box_tb">
				<fieldset>
					<legend>게시물 검색</legend>
					
					<c:set var="swStyle" value="" />
					<c:if test="${ bbsEstbs.ctgryAt eq 'Y' && !empty cateList }">
						<c:set var="swStyle" value="width:39%;" />
						
						<div class="selectbox">
							<form:select path="sc_cate">
								<form:option value="">카테고리 전체</form:option>
								<c:if test="${ !empty cateList }">
									<c:forEach var="list" items="${ cateList }">
										<form:option value="${ list.bbsCtgrySn }">${ list.ctgryNm }</form:option>
									</c:forEach>
								</c:if>
							</form:select>
						</div>
					</c:if>
					
					<div class="selectbox">
						<form:select path="sc">
							<form:option value="">전체</form:option>
							<c:if test="${ !empty searchList }">
								<c:forEach var="list" items="${ searchList }">
									<c:if test="${ list.bbsFieldCode ne 'ctgryNm' }">
										<form:option value="${ str:getColumnNm(list.bbsFieldCode) }">${ list.bbsFieldNm }</form:option>
									</c:if>
								</c:forEach>
							</c:if>
						</form:select>
					</div>
					
					<form:input path="sw" cssClass="search_text" placeholder="검색어 입력" style="${ swStyle }" onkeypress="if(event.keyCode == 13){ fn_egov_link_page(1); return false; }" />
					<button type="button" class="btn" onclick="fn_egov_link_page(1);">검색</button>
				</fieldset>
			</div>
		</div>
		<!--//search_box-->

		<p class="ft_left">
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
			
			<strong>총</strong> : <span class="txt-color-darken">${ tCount + fn:length(notiList) }</span>건
			Page <span class="txt-color-darken">${ pageSIndex }</span> /
			<span class="text-primary"><fmt:formatNumber type="number">${ pageMIndex }</fmt:formatNumber></span>
		</p>

		<!--리스트-->
		<div class="board_list">
			<table class="board_list_table ta-c">
				<caption>
					<strong>게시판 목록</strong>
					<p>
						게시판 목록으로 번호, 
						<c:if test="${ !empty fieldList }">
							<c:forEach var="list" items="${ fieldList }" varStatus="i">
								<c:if test="${ !i.first }">, </c:if>${ list.bbsFieldNm }
							</c:forEach>
						</c:if> 
						에 대한 정보를 표시합니다.
					</p>
				</caption>
				<colgroup>
					<col width="70" />
					<c:if test="${ !empty fieldList }">
						<c:forEach var="list" items="${ fieldList }">
							<col width="${ list.fieldListSize }">
						</c:forEach>
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><!-- 번호 --></th>
						<c:if test="${ !empty fieldList }">
							<c:forEach var="list" items="${ fieldList }">
								<th scope="col">${ list.bbsFieldNm }</th>
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
										<td class="listNum"><img src="/resource/brd/fs_ra001/img/icon_notice.png" alt="중요 공지사항" /></td>
										<c:if test="${ !empty fieldList }">
											<c:forEach var="list" items="${ fieldList }">
												<c:set var="titleCd" value="nomal" />
												<c:if test="${ list.bbsFieldCode eq 'bbsSj' }">
													<c:set var="titleCd" value="bbsTitle" />
												</c:if>
												<c:if test="${ list.bbsFieldCode eq 'ctgryNm' }">
													<c:set var="titleCd" value="category" />
												</c:if>
												<c:if test="${ list.bbsFieldCode eq 'file' or list.bbsFieldCode eq 'rdcnt' }">
													<c:set var="titleCd" value="nomal txtHide" />
												</c:if>
												
												<td class="${ titleCd }" <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
												
													<span onclick="goView('${ item.bbsSn }', '');" style="cursor:pointer;">
														<c:choose>
															<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
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
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'registDe' }">
																<dt:format pattern="yyyy-MM-dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode]}</dt:parse>
																</dt:format>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'bbsSj' }">
																
																<c:choose>
																	<c:when test="${ item.deleteAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																	<c:otherwise>
																		<string:total len="${ bbsEstbs.sjLtLmtt }" tail="...">${ item[list.bbsFieldCode]}</string:total>
																	</c:otherwise>
																</c:choose>
															</c:when>
															
															<c:when test="${ list.bbsFieldCode eq 'file' }">
																<!-- 첨부파일 -->
																<c:if test="${ !empty item.fileExt }">
																	<img src="/resource/brd/fs_ra001/img/file/ico_${ item.fileExt }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt="">
																</c:if>
															</c:when>
															
															<c:otherwise>
																${ item[list.bbsFieldCode]}
															</c:otherwise>
														</c:choose>
													</span>
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
														<c:set var="titleCd" value="bbsTitle" />
													</c:if>
													<c:if test="${ list.bbsFieldCode eq 'ctgryNm' }">
														<c:set var="titleCd" value="category" />
													</c:if>
													<c:if test="${ list.bbsFieldCode eq 'file' or list.bbsFieldCode eq 'rdcnt' }">
														<c:set var="titleCd" value="nomal txtHide" />
													</c:if>
													
													<td class="${ titleCd }" <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
													
														<span onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;">
															<c:choose>
																<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
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
																</c:when>
																
																<c:when test="${ list.bbsFieldCode eq 'registDe' }">
																	<dt:format pattern="yyyy-MM-dd">
																		<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode]}</dt:parse>
																	</dt:format>
																</c:when>
																
																<c:when test="${ list.bbsFieldCode eq 'bbsSj' }">
																	<c:if test="${ item.bbsDp gt 1 }">
																		<c:set var="rePd" value="${ (item.bbsDp - 1) * 10 }" />
																		
																		<img src="/resource/brd/fs_ra001/img/icon_re.gif" alt="답변" style="padding-left:${ rePd }px;" />
																	</c:if>
																	
																	<c:if test="${ item.secretAt eq 'Y' }">
																		<!-- 비밀글 -->
																		<img src="/resource/brd/fs_ra001/img/icon_lock.gif" alt="비밀글" />
																	</c:if>
																	
																	<c:choose>
																		<c:when test="${ item.deleteAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																		<c:otherwise>
																			<c:if test="${ item.bbsDp gt 1 && item.upperDeleteAt eq 'Y' }">
																				<span style="color:#ccc;">[삭제된 글의 답변 입니다.]</span>
																			</c:if>
																			<string:total len="${ bbsEstbs.sjLtLmtt }" tail="...">${ item[list.bbsFieldCode]}</string:total>
																		</c:otherwise>
																	</c:choose>
																</c:when>
																
																<c:when test="${ list.bbsFieldCode eq 'file' }">
																	<!-- 첨부파일 -->
																	<c:if test="${ !empty item.fileExt }">
																		<img src="/resource/brd/fs_ra001/img/file/ico_${ item.fileExt }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt="">
																	</c:if>
																</c:when>
																
																<c:otherwise>
																	${ item[list.bbsFieldCode] }
																</c:otherwise>
															</c:choose>
														</span>
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

		<div class="btnArea">
			<c:if test="${ powWrite }">
				<a class="btn btn_write" href="/bbs/form.do?key=${ param.key }">쓰기</a>
			</c:if>
		</div>

		<div class="paging">
			<ui:pagination paginationInfo="${ paginationInfo }"
				type="ra2List"
				jsFunction="fn_egov_link_page" />
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
													<input title="비밀번호를 입력해주세요." type="password" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#element" placeholder="비밀번호 입력" />
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