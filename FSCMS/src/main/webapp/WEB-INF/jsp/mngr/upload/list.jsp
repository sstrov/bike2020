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

<style>
#data-table_info { display:none; }
</style> 
<!-- ================== END PAGE LEVEL STYLE ================== -->

<script>
$('#sc_sub').css('display', 'none');
(function($){
	
	// 메뉴 목록 정보
	$.getList = function(v) {
		lodingFixedOn("정보 조회중 입니다.", 300, 180);
		
		var f = document.forms['frm'];
		var p = (v == '0')? 1 : v;
		
		f.pageIndex.value = p;
		
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
				
				/* var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					lodingOff(document.body);
					return false;
				} */

				if(result != null && result.length > 0) {
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						//* / 페이징 처리
						if(i == 0) {
							$.getPaging(item.tCount);
						}
						
						// 체크박스 생성
						var chkBox = '<input type="checkbox" name="chkKey" class="chkKey" value="' + item.auSn + '" />';
						
						var tableLayout = $("#data-table tfoot").html();
						tableLayout = replaceAll(tableLayout, '{key}',      item.auSn);
						tableLayout = replaceAll(tableLayout, '{chkBox}',   chkBox);
						tableLayout = replaceAll(tableLayout, '{userNm}',   item.userNm);
						tableLayout = replaceAll(tableLayout, '{noteSn}',   item.noteSn);
						tableLayout = replaceAll(tableLayout, '{authSn}',   item.authSn);
						tableLayout = replaceAll(tableLayout, '{courseNm}', item.courseNm);
						tableLayout = replaceAll(tableLayout, '{pubDe}',    item.pubDe);
						tableLayout = replaceAll(tableLayout, '{wDate}',    item.registDe);

						tmpStr += tableLayout; 
					}
				} else {
					tmpStr = '<tr class="odd">' +
							'<td colspan="7" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>' +
						'</tr>';
					
					// 페이징 처리
					$.getPaging(0);
				}
				
				$("#dataList").html(tmpStr);
				
				// 로딩 이미지 제거
				lodingOff(document.body);
			}
		});
	};
	
})(jQuery);

$(document).ready(function() {
	
	$.getList('${ searchVO.pageIndex }');	// 목록 조회
	orderByReset('${ searchVO.orderBy }');	// 정렬 변경
});

// 검색 정보 변경
function changeSearch() {
	var f = document.forms['frm'];
	
	// 정보 호출
	$.getList(1);
}

// 수정 페이지 이동
function goModify(key) {
	var f = document.forms['frm'];
	
	f.auSn.value = key;
	f.action = "/${ admURI }/auth/upload/form.do?key=${ param.key }";
	f.submit();
}

function swChange(v){
	if(v == 'COURSE_NM'){
		$('#sc_sub').css('display', 'block');
	}else{
		$('#sc_sub').css('display', 'none');
	}
}

function excelUpload(){
	var formData = new FormData($('#frm1')[0]);
	
	var f = document.forms['frm1'];
	if(f.uploadFile.value == null || f.uploadFile.value == ''){
		alert("파일을 등록해주세요.");
		return false;
	}
	
	lodingFixedOn("업로드 중 입니다.", 300, 180);
	$.ajax({
		type		: 'POST',
		url			: '/${ admURI }/auth/upload/excel.do?key=${ param.key }',
		data 		: formData,
		contentType : false,
		processData : false,
		error		: function (error){
			// 로딩 이미지 제거
			lodingOff(document.body);
			alert("파일 업로드 중 오류가 발생했습니다.");},
		success		: function (result){
			var msg = JSON.parse(result).msg;
			// 로딩 이미지 제거
			lodingOff(document.body);
			alert(msg);}
	});
}

</script>
</head>
<body>
<form:form id="frm1" name="frm1" method="post" enctype="multipart/form-data" cssClass="form-horizontal">
	
	<div class="row">
		<!-- begin col-6 -->
		<div class="col-md-12">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">파일 업로드</h4>
				</div>
				<div class="panel-body">

					<div class="form-group">
						<label class="col-md-3 control-label">파일 업로드</label>
						<div class="col-md-9">
							<label class="custom-file-label" for="file">
								<input type="file" id="uploadFile" name="uploadFile">
							</label>
							
							<button type="button" class="btn btn-success btn- m-r-3" onclick="excelUpload();"><i class="fa fa-search"></i> 업로드</button>
							
							<button type="button" class="btn btn-success btn- m-r-3" onclick="openForm_auth();">인증정보 변경</button>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<!-- end panel -->
	</div>
	
</form:form>

<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="auSn" />
	
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
								<form:select path="sc" cssClass="form-control" onchange="swChange(this.value);">
									<form:option value="">전체</form:option>
									<form:option value="COURSE_NM">종주구분</form:option>
									<form:option value="USER_NM">이름</form:option>
									<form:option value="NOTE_SN">수첩번호</form:option>
								</form:select>
							</label>
							
							<label class="select" style="float:left; margin:0;">
								<form:select path="sc_sub" cssClass="form-control">
									<form:option value="">전체</form:option>
									<form:option value="그랜드">그랜드슬램종주</form:option>
									<form:option value="국토">국토종주</form:option>
									<form:option value="4대강">4대강종주</form:option>
									<form:option value="한강">수계종주(한강)</form:option>
									<form:option value="금강">수계종주(금강)</form:option>
									<form:option value="영산강">수계종주(영산강)</form:option>
									<form:option value="낙동강">수계종주(낙동강)</form:option>
									<form:option value="남한강">수계종주(남한강)</form:option>
									<form:option value="북한강">수계종주(북한강)</form:option>
									<form:option value="섬진강">수계종주(섬진강)</form:option>
									<form:option value="오천">오천종주</form:option>
									<form:option value="제주">제주환상종주</form:option>
								</form:select>
							</label>

							<label class="input">
								<form:input path="sw" cssClass="form-control" placeholder="검색어 입력" onkeypress="if(event.keyCode == 13){ changeSearch(); return false; }" />
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

					<div class="table-responsive">
						<table id="data-table" data-reorder="true" class="table table-striped table-bordered">
							<colgroup>
								<col width="60">
								<col width="150">
								<col width="120">
								<col width="120">
								<col width="120">
								<col width="100">
								<col width="100">
							</colgroup>
							<thead>
								<tr>
									<!-- <th style="text-align:center;">
										<input type="checkbox" onclick="toggleCheck(this);" />
									</th> -->
									<th>No</th>
									<th>이름</th>
									<th>수첩번호</th>
									<th>인증번호</th>
									<th>종주명</th>
									<th>발행일</th>
									<th id="REGIST_DE"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>등록일</th>
								</tr>
							</thead>
							<tbody id="dataList"></tbody>

							<tfoot class="hide">
								<tr>
									<td style="text-align:center;">{key}</td>
									<td><span>{userNm}</span></td>
									<td><span>{noteSn}</span></td>
									<td><span>{authSn}</span></td>
									<td><span>{courseNm}</span></td>
									<td><span>{pubDe}</span></td>
									<td><span>{wDate}</span></td>
								</tr>
							</tfoot>
						</table>
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