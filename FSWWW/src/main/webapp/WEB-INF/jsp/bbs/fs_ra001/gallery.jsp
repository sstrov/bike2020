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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" id="no-fouc">
<head>
<link rel="stylesheet" type="text/css" href="/resource/brd/fs_ra001/css/style.css" />
<link rel="stylesheet" type="text/css" href="/resource/brd/fs_ra001/css/bootstrap.css" />
<script src="/resource/mng/plugins/bootstrap/js/bootstrap.min.js"></script>

<script text="text/javascript" src="/resource/mng/plugins/jquery-validate/jquery.validate.min.js"></script>
<script text="text/javascript" src="/resource/mng/plugins/parsley/dist/parsley.js"></script>
<script text="text/javascript" src="/resource/mng/plugins/parsley/src/i18n/ko.js"></script>

<script type="text/javascript" src="/resource/mng/js/common/utils.js"></script>
<script type="text/javascript" src="/resource/mng/js/common/jquery_utils.js"></script>
<script type="text/javascript" src="/resource/brd/fs_ra001/js/brd.js"></script>

<script type="text/javascript">
<c:if test="${ !empty msg }">
	alert('${ msg }');
</c:if>

// 보기 페이지 이동
function goView(key, stype) {
	<c:choose>
		<c:when test="${ powRead }">
			var f = document.forms['frm'];
			
			if(stype == 'Y') {
				f.brdKey.value = key;
				$('#cmntPw_form').modal('show')
				jQuery('#cmntPwFrm').validate({
					submitHandler: function(form) {
						$.ajax({
							url     : "/common/brd/isExistPw.do",
							type    : "POST",
							data    : {
								'brdKey' : f.brdKey.value,
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
									f.action = "/brd/view.do";
									f.submit();
								}
							}
						});
					}
				});
			} else {
				f.method = "get";
				f.brdKey.value = key;
				f.action = "/brd/view.do";
				f.submit();
			}
		</c:when>
		<c:otherwise>
			alert("로그인하여 주십시오.");
		</c:otherwise>
	</c:choose>
}

<c:if test="${ !powList }">
$(document).ready(function() {
	alert("로그인하여 주십시오.");
	document.location.href = "/login/form.do?key=1906103371519";
});
</c:if>

/*
 * pagination 페이지 링크 function
 */
function fn_egov_link_page(pageNo){
	<c:if test="${ powList }">
		var f = document.forms['frm'];
	
		f.pageIndex.value = pageNo;
		f.action          = "/brd/list.do?key=${ param.key }";
		f.submit();
	</c:if>
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="brdKey" />
	<input type="hidden" name="key" value="${ param.key }" />
	
	<form:hidden path="pageIndex" />
	
	<c:if test="${ brdMng.headContentAt eq 'Y' }">
		<jsp:include page="/repository/www/content/${ param.key }.jsp" />
	</c:if>
	
	<c:if test="${ !empty brdMng.headHtml }">
		${ xss:decode(brdMng.headHtml) }
	</c:if>

	<!-- 게시판_목록 -->
	<div class="fs_list_box">
		<!--search_box-->
		<div class="search_warp">
			<div class="search_box_tb">
				<fieldset>
					<legend>게시물 검색</legend>
					
					<c:set var="swStyle" value="" />
					<c:if test="${ brdMng.cateAt eq 'Y' && !empty cateList }">
						<c:set var="swStyle" value="width:39%;" />
						
						<div class="selectbox">
							<form:select path="sc_cate">
								<form:option value="">카테고리 전체</form:option>
								<c:if test="${ !empty cateList }">
									<c:forEach var="list" items="${ cateList }">
										<form:option value="${ list.brdCateKey }">${ list.cateNm }</form:option>
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
									<c:if test="${ list.brdFidCd ne 'brdCateNm' }">
										<form:option value="${ str:getColumnNm(list.brdFidCd) }">${ list.brdFidNm }</form:option>
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
		<c:choose>
			<c:when test="${ !powList }">
				<div class="board_list">
					<table class="board_list_table ta-c">
						<caption>
							<strong>게시판 목록</strong>
							<p>
								게시판 목록으로 번호, 
								<c:if test="${ !empty fieldList }">
									<c:forEach var="list" items="${ fieldList }" varStatus="i">
										<c:if test="${ !i.first }">, </c:if>${ list.brdFidNm }
									</c:forEach>
								</c:if> 
								에 대한 정보를 표시합니다.
							</p>
						</caption>
						<colgroup>
							<col width="70" />
							<c:if test="${ !empty fieldList }">
								<c:forEach var="list" items="${ fieldList }">
									<col width="${ list.brdFidListSize }">
								</c:forEach>
							</c:if>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<c:if test="${ !empty fieldList }">
									<c:forEach var="list" items="${ fieldList }">
										<th scope="col">${ list.brdFidNm }</th>
									</c:forEach>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="${ cols }" class="listNum" style="line-height:100px;">목록 보기 권한이 없습니다.</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${ !empty notiList }">
					<div class="board_list">
						<table class="board_list_table ta-c">
							<caption>
								<strong>게시판 목록</strong>
								<p>
									게시판 목록으로 번호,
									<c:if test="${ !empty fieldList }">
										<c:forEach var="list" items="${ fieldList }" varStatus="i">
											<c:if test="${ !i.first }">, </c:if>${ list.brdFidNm }
										</c:forEach>
									</c:if> 
									에 대한 정보를 표시합니다.
								</p>
							</caption>
							<colgroup>
								<col width="70" />
								<c:if test="${ !empty fieldList }">
									<c:forEach var="list" items="${ fieldList }">
										<col width="${ list.brdFidListSize }">
									</c:forEach>
								</c:if>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<c:if test="${ !empty fieldList }">
										<c:forEach var="list" items="${ fieldList }">
											<th scope="col">${ list.brdFidNm }</th>
										</c:forEach>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:set var="cols" value="1" />
								<c:if test="${ !empty fieldList }">
									<c:set var="cols" value="${ 1 + fn:length(fieldList) }" />
								</c:if>
								
								<c:forEach var="item" items="${ notiList }">
									<tr>
										<td class="listNum"><img src="/resource/brd/fs_ra001/img/icon_notice.png" alt="중요 공지사항" /></td>
										<c:if test="${ !empty fieldList }">
											<c:forEach var="list" items="${ fieldList }">
												<c:set var="titleCd" value="nomal" />
												<c:if test="${ list.brdFidCd eq 'brdSubject' }">
													<c:set var="titleCd" value="title" />
												</c:if>
												<c:if test="${ list.brdFidCd eq 'brdCateNm' }">
													<c:set var="titleCd" value="category" />
												</c:if>
												
												<td class="${ titleCd }" <c:if test="${ !empty list.brdFidListStyle }">style="${ list.brdFidListStyle }"</c:if>>
												
													<span onclick="goView('${ item.brdKey }', '');" style="cursor:pointer;">
														<c:choose>
															<c:when test="${ list.brdFidCd eq 'regNm' }">
																<c:choose>
																	<c:when test="${ brdMng.regNmType eq 'NM' }">
																		${ item.regNm }
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'ID' }">
																		<c:choose>
																			<c:when test="${ !empty item.userId }">${ item.userId }</c:when>
																			<c:otherwise>${ item.regNm }</c:otherwise>
																		</c:choose>
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'NMS' }">
																		${ item.regNmSec }
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'IDS' }">
																		<c:choose>
																			<c:when test="${ !empty item.userId }">${ item.userIdSec }</c:when>
																			<c:otherwise>${ item.regNmSec }</c:otherwise>
																		</c:choose>
																	</c:when>
																</c:choose>
															</c:when>
															
															<c:when test="${ list.brdFidCd eq 'wDate' }">
																<dt:format pattern="yyyy-MM-dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.brdFidCd]}</dt:parse>
																</dt:format>
															</c:when>
															
															<c:when test="${ list.brdFidCd eq 'brdSubject' }">
																
																<c:choose>
																	<c:when test="${ item.delAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																	<c:otherwise>
																		<string:total len="${ brdMng.brdMngTitleLimit }" tail="...">${ item[list.brdFidCd]}</string:total>
																	</c:otherwise>
																</c:choose>
															</c:when>
															
															<c:when test="${ list.brdFidCd eq 'fileCnt' }">
																<!-- 첨부파일 -->
																<c:set var="fileList" value="${ fn:split(item.files, ',') }" />
																<c:set var="fileExtList" value="${ fn:split(item.filesExt, ',') }" />
																
																<c:forEach var="file" items="${ fileList }" varStatus="i">
																	<c:if test="${ !empty file }">
																		<c:set var="ext" value="${ fileExtList[i.index] }" />
																		
																		<a href="/common/fileDown.do?key=${ file }&type=brd">
																			<img src="/resource/brd/fs_ra001/img/file/ico_${ ext }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt="파일 다운로드">
																		</a>
																	</c:if>
																</c:forEach>
															</c:when>
															
															<c:otherwise>
																${ item[list.brdFidCd]}
															</c:otherwise>
														</c:choose>
													</span>
												</td>
											</c:forEach>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				
				<div class="board_gallery" id="gallery" <c:if test="${ !empty notiList }">style="margin-top:0;"</c:if>>
					<ul class="gallery">
						<c:choose>
							<c:when test="${ !empty brdList }">
								<c:forEach var="item" items="${ brdList }" varStatus="i">
									<li style="cursor:pointer;">
										<a href="#none" onclick="goView('${ item.brdKey }', '${ item.secAt }');">
											<span class="g_img">
												<img src="/common/thumb.do?key=${ item.brdKey }&amp;type=brd&amp;name=thumb" alt="">
											</span>
											
											<c:if test="${ !empty fieldList }">
												<c:forEach var="list" items="${ fieldList }">
													<c:set var="titleCd" value="nomal" />
													<c:if test="${ list.brdFidCd eq 'brdSubject' }">
														<c:set var="titleCd" value="title" />
													</c:if>
													<c:if test="${ list.brdFidCd eq 'brdCateNm' }">
														<c:set var="titleCd" value="category" />
													</c:if>
														
													<c:choose>
														<c:when test="${ list.brdFidCd eq 'regNm' }">
															<span class="${ titleCd }" <c:if test="${ !empty list.brdFidListStyle }">style="${ list.brdFidListStyle }"</c:if>>
																<c:choose>
																	<c:when test="${ brdMng.regNmType eq 'NM' }">
																		${ item.regNm }
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'ID' }">
																		${ item.userId }
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'NMS' }">
																		${ item.regNmSec }
																	</c:when>
																	<c:when test="${ brdMng.regNmType eq 'IDS' }">
																		${ item.userIdSec }
																	</c:when>
																</c:choose>
															</span>
														</c:when>
														
														<c:when test="${ list.brdFidCd eq 'wDate' }">
															<span class="g_date" <c:if test="${ !empty list.brdFidListStyle }">style="${ list.brdFidListStyle }"</c:if>>
																<dt:format pattern="yyyy-MM-dd">
																	<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.brdFidCd]}</dt:parse>
																</dt:format>
															</span>
														</c:when>
														
														<c:when test="${ list.brdFidCd eq 'brdSubject' }">
															<strong class="g_title" style="width:300px; <c:if test="${ !empty list.brdFidListStyle }">${ list.brdFidListStyle }</c:if>">
																<c:if test="${ item.brdG gt 1 }">
																	<c:set var="rePd" value="${ (item.brdG - 1) * 10 }" />
																	
																	<img src="/resource/mng/img/brd/icon_re.gif" alt="답변" style="padding-left:${ rePd }px;" />
																</c:if>
																
																<c:if test="${ item.secAt eq 'Y' }">
																	<img src="/resource/mng/img/brd/icon_lock.gif" alt="비밀글" />
																</c:if>
																
																<c:choose>
																	<c:when test="${ item.delAt eq 'Y' }"><span style="color:#ccc;">삭제된 데이터 입니다.</span></c:when>
																	<c:otherwise>
																		<c:if test="${ item.brdG gt 1 && item.upperDelAt eq 'Y' }">
																			<span style="color:#ccc;">[삭제된 글의 답변 입니다.]</span>
																		</c:if>
																		<string:total len="${ brdMng.brdMngTitleLimit }" tail="...">${ item[list.brdFidCd]}</string:total>
																	</c:otherwise>
																</c:choose>
															</strong>
														</c:when>
														
														<c:otherwise>
															<span class="${ titleCd }" <c:if test="${ !empty list.brdFidListStyle }">style="${ list.brdFidListStyle }"</c:if>>
																${ item[list.brdFidCd] }
															</span>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:if>
										</a>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="listNum" style="width:100%; line-height:100px; text-align:center;">
									등록된 정보가 없습니다.
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="btnArea">
			<c:if test="${ powWrite }">
				<a class="btn btn_write" href="/brd/form.do?key=${ param.key }">쓰기</a>
			</c:if>
		</div>

		<div class="paging">
			<ui:pagination paginationInfo="${ paginationInfo }"
				type="ra2List"
				jsFunction="fn_egov_link_page" />
		</div>
	</div>
	
	<c:if test="${ brdMng.footContentAt eq 'Y' }">
		<jsp:include page="/repository/www/content/${ param.key }.jsp" />
	</c:if>
	
	<c:if test="${ !empty brdMng.footHtml }">
		${ xss:decode(brdMng.footHtml) }
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