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

<script type="text/javascript">
(function($){
	
	// 메뉴 목록 정보
	$.getList = function(v) {
		lodingFixedOn("정보 조회중 입니다.", 300, 180);
		
		var f = document.forms['frm'];
		var p = (v == '0')? 1 : v;
		
		f.pageIndex.value = p;
		
		$.ajax({
			url     : "/${ admURI }/indvdlinfo/acces/hist/getList.do?key=${ param.key }",
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
						
						var tableLayout = $("#data-table tfoot").html();
						tableLayout = replaceAll(tableLayout, '{key}',    item.accesHistSn);
						tableLayout = replaceAll(tableLayout, '{userId}', (item.mngrId != null)? item.mngrId : item.userId);
						tableLayout = replaceAll(tableLayout, '{cn}',     item.accesHistCn);
						tableLayout = replaceAll(tableLayout, '{plf}',    item.registPltfom);
						tableLayout = replaceAll(tableLayout, '{brow}',   item.registBrwsr);
						tableLayout = replaceAll(tableLayout, '{ip}',     item.registIp);
						tableLayout = replaceAll(tableLayout, '{wDate}',  item.registDe);

						tmpStr += tableLayout;
					}
				} else {
					tmpStr = '<tr class="odd">' +
							'<td colspan="6" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>' +
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
	
	if(isEmpty(f.sc_wDateS) || isEmpty(f.sc_wDateE)) {
		alert("등록일 정보는 필수로 입력하여야 합니다.");
		return false;
	}

	// 정보 호출
	$.getList(1);
}

// 수정 페이지 이동
function goView(key) {
	var f = document.forms['frm'];
	
	f.accesHistSn.value = key;
	f.action = "/${ admURI }/indvdlinfo/acces/hist/view.do?key=${ param.key }";
	f.submit();
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="accesHistSn" />
	
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
									<form:option value="USER_ID">등록자 아이디</form:option>
									<form:option value="ACCES_HIST_CN">내용</form:option>
									<form:option value="REGIST_IP">등록 아이피</form:option>
								</form:select>
							</label>

							<label class="input">
								<form:input path="sw" cssClass="form-control" placeholder="검색어 입력" onkeypress="if(event.keyCode == 13){ changeSearch(); return false; }" />
							</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">등록일</label>
						<div class="col-md-9">
							<div class="input-group input-daterange" style="float:left;">
								<form:input path="sc_wDateS" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd" cssStyle="width:110px !important; min-width:110px !important; margin:0;" placeholder="시작날짜 선택" />
								<span class="input-group-addon">&sim;</span>
								<form:input path="sc_wDateE" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd" cssStyle="width:110px !important; min-width:110px !important;" placeholder="종료날짜 선택" />
							</div>

							<div class="btn-group">
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
					</div>
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
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">데이터 목록</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="120">
								<col width="400">
								<col width="120">
								<col width="120">
								<col width="120">
								<col width="180">
							</colgroup>
							<thead>
								<tr>
									<th>등록자 아이디</th>
									<th>내용</th>
									<th>등록 OS</th>
									<th>등록 브라우저</th>
									<th>등록 아이피</th>
									<th id="REGIST_DE"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>등록일</th>
								</tr>
							</thead>
							<tbody id="dataList"></tbody>

							<tfoot class="hide">
								<tr>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{userId}</span></td>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{cn}</span></td>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{plf}</span></td>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{brow}</span></td>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{ip}</span></td>
									<td><span onclick="goView('{key}');" style="cursor:pointer;">{wDate}</span></td>
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
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>