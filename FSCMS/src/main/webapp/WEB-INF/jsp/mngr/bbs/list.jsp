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

	<div class="row">
		<!-- begin col-6 -->
		<div class="col-md-12">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">검색 창</h4>
				</div>
				<div class="panel-body">

					<div class="form-group">
						<label class="col-md-3 control-label">키워드</label>
						<div class="col-md-9">
							<label class="select" style="float:left; margin:0;">
								<form:select path="sc" cssClass="form-control">
									<form:option value="">전체</form:option>
									<c:if test="${ !empty searchList }">
										<c:forEach var="list" items="${ searchList }">
											<form:option value="${ str:getColumnNm(list.bbsFieldCode) }">${ list.bbsFieldNm }</form:option>
										</c:forEach>
									</c:if>
								</form:select>
							</label>

							<label class="input">
								<form:input path="sw" cssClass="form-control" placeholder="검색어 입력" onkeypress="if(event.keyCode == 13){ fn_egov_link_page(1); return false; }" />
							</label>
						</div>
					</div>
					
					<!-- bbsEstbs.ctgryAt-->
					<c:if test="${ bbsEstbs.ctgryAt eq 'Y'}">
						<div class="form-group">
							<label class="col-md-3 control-label">카테고리</label>
							<div class="col-md-9">
								<label class="select" style="float:left; margin:0;">
									<form:select path="sc_ctgrySn" cssClass="form-control" onchange="subCtgry(this.value);">
										<form:option value="">전체</form:option>
										<c:if test="${ !empty ctgryList }">
											<c:forEach var="list" items="${ ctgryList }">
												<form:option value="${ list.bbsCtgrySn }">${ list.ctgryNm }</form:option>
											</c:forEach>
										</c:if>
									</form:select>
								</label>
								
								<label class="select" style="float:left; margin:0;">
									<form:select path="sc_ctgrySubSn" cssClass="form-control">
										<form:option value="">전체</form:option>
									</form:select>
								</label>
							</div>
						</div>
					</c:if>
					
					<div class="form-group">
						<div class="col-md-12" style="text-align:center;">
							<button type="button" class="btn btn-sm btn-primary m-r-5" onclick="fn_egov_link_page(1);"><i class="fa fa-search"></i> 검색</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end panel -->

		<div class="col-md-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">데이터 목록</h4>
				</div>
				<div class="panel-body">

					<div style="float:left; width:100%; text-align:right; margin-bottom:10px;">
						<button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/bbs/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button>
						<button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/bbs/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button>
					</div>

					<div class="table-responsive">
						<table id="data-table" class="table table-striped table-bordered">
							<colgroup>
								<col width="60">
								<c:if test="${ !empty fieldList }">
									<c:forEach var="list" items="${ fieldList }">
										<col width="${ !empty list.fieldListSize ? list.fieldListSize : '300' }">
									</c:forEach>
								</c:if>
							</colgroup>
							<thead>
								<tr>
									<th style="text-align:center;">
										<input type="checkbox" onclick="toggleCheck(this);" />
									</th>
									<c:if test="${ !empty fieldList }">
										<c:forEach var="list" items="${ fieldList }">
											<th <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>${ list.bbsFieldNm }</th>
										</c:forEach>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ !empty notiList }">
									<c:forEach var="item" items="${ notiList }">
										<tr>
											<td style="text-align:center;"><i class="fa fa-bullhorn" style="font-size:13pt;"></i></td>
											<c:if test="${ !empty fieldList }">
												<c:forEach var="list" items="${ fieldList }">
													<td <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
													
														<span onclick="goView('${ item.bbsSn }');" style="cursor:pointer;">
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
																		<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode] }</dt:parse>
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
										<c:forEach var="item" items="${ brdList }">
											<tr>
												<td style="text-align:center;"><input type="checkbox" name="chkKey" class="chkKey" value="${ item.bbsSn }" /></td>
												<c:if test="${ !empty fieldList }">
													<c:forEach var="list" items="${ fieldList }">
														<td <c:if test="${ !empty list.fieldListStyle }">style="${ list.fieldListStyle }"</c:if>>
														
															<span onclick="goView('${ item.bbsSn }');" style="cursor:pointer;">
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
																			<dt:parse pattern="yyyy-MM-dd HH:mm:ss">${ item[list.bbsFieldCode] }</dt:parse>
																		</dt:format>
																	</c:when>
																	
																	<c:when test="${ list.bbsFieldCode eq 'bbsSj' }">
																		<c:if test="${ item.bbsDp gt 1 }">
																			<c:set var="rePd" value="${ (item.bbsDp - 1) * 10 }" />
																			
																			<img src="/resource/mngr/img/brd/icon_re.gif" alt="답변" style="padding-left:${ rePd }px;" />
																		</c:if>
																		
																		<c:if test="${ item.secretAt eq 'Y' }">
																			<!-- 비밀글 -->
																			<i class="fa fa-lock"></i>
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
																		<i class="fa fa-file"></i>
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
									</c:when>
									<c:otherwise>
										<c:set var="cols" value="1" />
										<c:if test="${ !empty fieldList }">
											<c:set var="cols" value="${ 1 + fn:length(fieldList) }" />
										</c:if>
										<tr>
											<td colspan="${ cols }" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="col-sm-5">
							<div class="dataTables_info" id="data-table_info" role="status" aria-live="polite">
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
								
								
								총 <span id="pageCurrEIndex" class="txt-color-darken">${ tCount + fn:length(notiList) }</span>건
								Page <span id="pageCurrSIndex" class="txt-color-darken">${ pageSIndex }</span> /
								<span id="pageMaxIndex" class="text-primary"><fmt:formatNumber type="number">${ pageMIndex }</fmt:formatNumber></span>
							</div>
						</div>

						<div class="col-sm-7">
							<div class="dataTables_paginate paging_simple_numbers" id="data-table_paginate" style="text-align:right;">
								<ul id="pageLine" class="pagination" style="float:left;">
									<ui:pagination paginationInfo="${ paginationInfo }"
										type="admList"
										jsFunction="fn_egov_link_page" />
								</ul>

								<!-- # 정렬개수 -->
								<label>
									<form:select path="maxList" cssClass="form-control" onchange="fn_egov_link_page(1);">
										<form:option value="10">10</form:option>
										<form:option value="25">25</form:option>
										<form:option value="50">50</form:option>
										<form:option value="100">100</form:option>
									</form:select>
								</label>
							</div>
						</div>
					</div>

					<div style="float:left; width:100%; text-align:right;">
						<button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/bbs/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button>
						<button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/bbs/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>