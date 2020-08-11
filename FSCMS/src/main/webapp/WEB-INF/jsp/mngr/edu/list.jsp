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
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />

<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />

<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script src="/resource/mngr/js/cmm/table_list.js"></script>
<!-- ================== END PAGE LEVEL STYLE ================== -->

<script>
(function($){
	
	// 메뉴 목록 정보
	$.getList = function(v) {
		lodingFixedOn("정보 조회중 입니다.", 300, 180);
		
		var f = document.forms['frm'];
		var p = (v == '0')? 1 : v;
		
		f.pageIndex.value = p;
		
		$.ajax({
			url     : "/${ admURI }/edu/getList.do?key=${ param.key }",
			type    : "POST",
			data    : $("#frm").serialize(),
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				var tmpStr = "";
				
				var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					lodingOff(document.body);
					return false;
				}

				if(result != null && result.length > 0) {
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						// 페이징 처리
						if(i == 0) {
							$.getPaging(item.tCount);
						}
						
						// 체크박스 생성
						var chkBox = '<input type="checkbox" name="chkKey" class="chkKey" value="' + item.pushSn + '" />';

						var tableLayout = $("#data-table tfoot").html();
						tableLayout = replaceAll(tableLayout, '{key}',      item.eduSn);
						tableLayout = replaceAll(tableLayout, '{chkBox}',   chkBox);
						tableLayout = replaceAll(tableLayout, '{eduHost}',  item.eduHost);
						tableLayout = replaceAll(tableLayout, '{eduNm}',    item.eduNm);
						tableLayout = replaceAll(tableLayout, '{target}',   item.eduTarget);
						tableLayout = replaceAll(tableLayout, '{eduDay}',   item.eduDay);
						tableLayout = replaceAll(tableLayout, '{eduPlace}', item.eduPlace);
						tableLayout = replaceAll(tableLayout, '{eduTel}',   item.eduTel);
						tableLayout = replaceAll(tableLayout, '{wNm}',      item.registId);
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
function changeSearch(v) {
	var f = document.forms['frm'];

	// 정보 호출
	$.getList(1);
}

// 수정 페이지 이동
function goModify(key) {
	var f = document.forms['frm'];
	
	f.pushSn.value = key;
	f.action = "/${ admURI }/push/form.do?key=${ param.key }";
	f.submit();
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="eduSn" />
	
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
									<form:option value="EDU_HOST">주관</form:option>
									<form:option value="EDU_NM">교육내용</form:option>
								</form:select>
							</label>

							<label class="input">
								<form:input path="sw" cssClass="form-control" placeholder="검색어 입력" onkeypress="if(event.keyCode == 13){ changeSearch(); return false; }" />
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-10" style="text-align:center;">
							<button type="button" class="btn btn-sm btn-primary m-r-5" onclick="changeSearch('');"><i class="fa fa-search"></i> 검색</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end panel -->

		<div class="col-md-12">
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">데이터 목록</h4>
				</div>
				<div class="panel-body">

					<c:if test="${ !empty role.powW && role.powW eq 'Y' }">
						<div style="float:left; width:100%; text-align:right; margin-bottom:10px;">
							<button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/edu/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button>
							<button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/edu/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button>
						</div>
					</c:if>

					<div class="table-responsive">
						<table id="data-table" class="table table-striped table-bordered">
							<colgroup>
								<col width="60">
								<col width="90">
								<col width="180">
								<col width="180">
								<col width="150">
								<col width="150">
								<col width="120">
								
							</colgroup>
							<thead>
								<tr>
									<th style="text-align:center;">
										<input type="checkbox" onclick="toggleCheck(this);" />
									</th>
									<th>주관</th>
									<th>교육내용</th>
									<th>교육대상</th>
									<th>교육일자</th>
									<th>교육장소</th>
									<th>교육문의</th>
								</tr>
							</thead>
							<tbody id="dataList"></tbody>

							<tfoot class="hide">
								<tr>
									<td style="text-align:center;">{chkBox}</td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{eduHost}</span></td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{eduNm}</span></td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{target}</span></td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{eduDay}</span></td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{eduPlace}</span></td>
									<td><span onclick="goModify('{key}');" style="cursor:pointer;">{eduTel}</span></td>
									
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
							<button type="button" class="btn btn-success btn-sm" onclick="goForm('/${ admURI }/edu/form.do?key=${ param.key }');"><i class="fa fa-pencil"></i> 등록</button>
							<button type="button" class="btn btn-danger btn-sm" onclick="goDeleteForList('/${ admURI }/edu/deleteForList.do?key=${ param.key }');"><i class="fa fa-trash-o"></i> 선택삭제</button>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>