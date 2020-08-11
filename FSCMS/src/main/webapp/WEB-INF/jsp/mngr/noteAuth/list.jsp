<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/resource/mngr/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script text="text/javascript" src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script text="text/javascript" src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script text="text/javascript" src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script src="/resource/mngr/js/cmm/table_list.js"></script>
<!-- ================== END PAGE LEVEL STYLE ================== -->

<script>
(function($){
	
	// 메뉴 목록 정보
	$.getList = function(v) {
		var f = document.forms['frm'];
		var p = (v == '0')? 1 : v;
		f.pageIndex.value = p;
		
		if(f.noteSn.value == null || f.noteSn.value == ''){
			alert("수첩번호를 입력해주세요.");
			return false;
		}
		lodingFixedOn("정보 조회중 입니다.", 300, 180);
		
		if($("select[name=oldData]").val() == 'UPLOAD'){
			$.ajax({
				url     : "/${ admURI }/auth/upload/getList.do?key=${ param.key }",
				type    : "POST",
				data    : $("#frm").serialize(),
				dataType: "json",
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					lodingOff(document.body);
				},
				success:function(result) {
					var tmpStr = "";
					var tbl = "";
					tbl += '<table id="data-table" data-reorder="true" class="table table-striped table-bordered">';
					tbl += '<colgroup>';
					tbl += '<col width="80">';
					tbl += '<col width="150">';
					tbl += '<col width="80">';
					tbl += '<col width="120">';
					tbl += '<col width="80">';
					tbl += '</colgroup>';
					tbl += '<thead>';
					tbl += '<tr>';
					tbl += '<th>이름</th>';
					tbl += '<th>종주명</th>';
					tbl += '<th>상태</th>';
					tbl += '<th>인증센터</th>';
					tbl += '<th>날짜</th>';
					tbl += '</tr>';
					tbl += '</thead>';
					tbl += '<tbody id="dataList">';
					
					if(result != null && result.length > 0) {
						
						for(var i=0; i<result.length; i++) {
							var item = result[i];
							
							//* / 페이징 처리
							if(i == 0) {
								$.getPaging(item.tCount);
							}
							var tmp = "";
							tmp += '<tr>';
							tmp += '<td><span>' + item.userNm + '</span></td>';
							tmp += '<td><span>' + item.courseNm + '</span></td>';
							tmp += '<td><span>' + item.authStatus + '</span></td>';
							tmp += '<td><span>' + item.authCenter + '</span></td>';
							tmp += '<td><span>' + item.pubDe + '</span></td>';
							tmp += '</tr>';
							tbl += tmp;
						}
						
						
					} else {
						tbl += '<tr class="odd">' +
								'<td colspan="5" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>' +
							'</tr>';
						
						// 페이징 처리
						$.getPaging(0);
					}
					tbl += '</tbody>';
					tbl += '</table>';
					
					$('#table-responsive').html(tbl);
					// 로딩 이미지 제거
					lodingOff(document.body);
				}
			});
		}else{
			$.ajax({
				url     : "/${ admURI }/auth/web/getList.do?key=${ param.key }",
				type    : "POST",
				data    : $("#frm").serialize(),
				dataType: "json",
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					lodingOff(document.body);
				},
				success:function(result) {
					var tmpStr = "";
					var tbl = "";
					tbl += '<table id="data-table" data-reorder="true" class="table table-striped table-bordered">';
					tbl += '<colgroup>';
					tbl += '<col width="80">';
					tbl += '<col width="120">';
					tbl += '<col width="100">';
					tbl += '<col width="150">';
					tbl += '<col width="150">';
					tbl += '<col width="80">';
					tbl += '</colgroup>';
					tbl += '<thead>';
					tbl += '<tr>';
					tbl += '<th>이름</th>';
					tbl += '<th>아이디</th>';
					tbl += '<th>상태</th>';
					tbl += '<th>인증길</th>';
					tbl += '<th>인증센터</th>';
					tbl += '<th>날짜</th>';
					tbl += '</tr>';
					tbl += '</thead>';
					tbl += '<tbody id="dataList">';
					
					if(result != null && result.length > 0) {
						
						for(var i=0; i<result.length; i++) {
							var item = result[i];
							
							//* / 페이징 처리
							if(i == 0) {
								$.getPaging(item.tCount);
							}
							var tmp = "";
							tmp += '<tr>';
							tmp += '<td><span>' + item.userNm + '</span></td>';
							tmp += '<td><span>' + item.userId + '</span></td>';
							tmp += '<td><span>' + 'Y' + '</span></td>';
							tmp += '<td><span>' + item.roadNm + '</span></td>';
							tmp += '<td><span>' + item.authNm + '</span></td>';
							tmp += '<td><span>' + item.registDe + '</span></td>';
							tmp += '</tr>';
							tbl += tmp;
						}
						
						
					} else {
						tbl += '<tr class="odd">' +
								'<td colspan="6" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>' +
							'</tr>';
						
						// 페이징 처리
						$.getPaging(0);
					}
					tbl += '</tbody>';
					tbl += '</table>';
					
					$('#table-responsive').html(tbl);
					// 로딩 이미지 제거
					lodingOff(document.body); 
				}
			});
			
			
		}
	};
	
})(jQuery);

$(document).ready(function() {
	
	//$.getList('${ searchVO.pageIndex }');	// 목록 조회
	orderByReset('${ searchVO.orderBy }');	// 정렬 변경
});

// 검색 정보 변경
function changeSearch() {
	var f = document.forms['frm'];
	
	// 정보 호출
	$.getList(1);
}


</script>
</head>
<body>

<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	
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
								<form:select path="oldData" cssClass="form-control">
									<form:option value="WEB">웹인증</form:option>
									<form:option value="UPLOAD">수자원인증</form:option>
								</form:select>
							</label>
							

							<label class="input">
								<form:input path="noteSn" cssClass="form-control" placeholder="수첩번호 입력" onkeypress="if(event.keyCode == 13){ changeSearch(); return false; }" />
							</label>
						</div>
					</div>
					
					

					<%-- <div class="form-group">
						<label class="col-md-3 control-label">등록일</label>
						<div class="col-md-9">
							<div class="input-group input-daterange" style="float:left;">
								<form:input path="sc_wDateS" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd" cssStyle="width:110px !important; min-width:110px !important; margin:0;" placeholder="시작날짜 선택" />
								<span class="input-group-addon">&sim;</span>
								<form:input path="sc_wDateE" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd" cssStyle="width:110px !important; min-width:110px !important;" placeholder="종료날짜 선택" />
							</div>

							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodSel_del('sc_wDateS', 'sc_wDateE');">전체</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodSel('sc_wDateS', 'sc_wDateE', 7);">7일</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodSel('sc_wDateS', 'sc_wDateE', 15);">15일</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodMSel('sc_wDateS', 'sc_wDateE', 1);">1개월</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodMSel('sc_wDateS', 'sc_wDateE', 3);">3개월</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodMSel('sc_wDateS', 'sc_wDateE', 6);">6개월</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-info" onclick="periodYSel('sc_wDateS', 'sc_wDateE', 1);">1년</button>
								</div>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
						<div class="col-md-12" style="text-align:center;">
							<button type="button" class="btn btn-sm btn-primary m-r-5" onclick="changeSearch();"><i class="fa fa-search"></i> 검색</button>
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

					<c:if test="${ !empty role.powW && role.powW eq 'Y' }">
						<div style="float:left; width:100%; text-align:right; margin-bottom:10px;">
							<%-- <button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/auth/upload/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button> --%>
							<%-- <button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/auth/upload/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button> --%>
						</div>
					</c:if>

					<div class="table-responsive" id="table-responsive">
						
					</div>

					<div class="row">
						<div class="col-sm-5">
							<div class="dataTables_info" id="data-table_info" role="status" aria-live="polite">
								총 <span id="pageCurrEIndex" class="txt-color-darken"></span>건
								Page <span id="pageCurrSIndex" class="txt-color-darken"></span> /
								<span id="pageMaxIndex" class="text-primary"></span>
							</div>
						</div>

						<div class="col-sm-7">
							<div class="dataTables_paginate paging_simple_numbers" id="data-table_paginate" style="text-align:right;">
								<ul id="pageLine" class="pagination" style="float:left;"></ul>

								<!-- # 정렬개수 -->
								<label>
									<form:select path="maxList" cssClass="form-control" onchange="changeMaxList();">
										<form:option value="10">10</form:option>
										<form:option value="25">25</form:option>
										<form:option value="50">50</form:option>
										<form:option value="100">100</form:option>
									</form:select>
								</label>
							</div>
						</div>
					</div>

					<c:if test="${ !empty role.powW && role.powW eq 'Y' }">
						<div style="float:left; width:100%; text-align:right;">
							<%-- <button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/auth/upload/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button> --%>
							<%-- <button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/auth/upload/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button> --%>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</form:form>

<jsp:include page="/WEB-INF/jsp/mngr/upload/upload_form.jsp" />
</body>
</html>