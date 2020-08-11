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

<script>
//검색 정보 변경
function changeSearch() {
	var f = document.forms['frm'];

	f.action = '/${ admURI }/stat/tot.do?key=${ param.key }';
	f.submit();
}
</script>
<style type="text/css">
.datepicker table tr td, .datepicker table tr th { width:40px; }
.datepicker.dropdown-menu th, .datepicker.datepicker-inline th, .datepicker.dropdown-menu td, .datepicker.datepicker-inline td { padding:5px 5px; }
#data-table_info { display:none; }

#dataList1 tr:first-child td { text-align:center; height:100px; vertical-align:bottom; padding-top:20px; padding-bottom:0px; }
#dataList1 tr:first-child td:first-child { vertical-align:middle; padding:0; }
#dataList1 tr:first-child td.ov { color:#ff4e73; }
#dataList1 tr:first-child td div.fs_data_hbar { width:10px; margin:0 auto; background-color:#02253b !important; }
#dataList1 tr:first-child td div.fs_data_hbar_ov { background-color:#ff4e73 !important; }

#dataList2 td div.fs_data_hbar { height:10px; float:left; margin:4px 10px 0 0; background-color:#02253b !important; }
#dataList2 td div.fs_data_hbar_ov { background-color:#ff4e73 !important; }
</style>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">

	<div class="row">
		<!-- begin col-6 -->
		<div class="col-md-12">
			<!-- begin panel -->
			<div class="panel panel-inverse" data-sortable-id="form-stuff-1">
				<div class="panel-heading">
					<h4 class="panel-title">검색 창</h4>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 control-label">조회 일자</label>
						<div class="col-md-9">
							<label class="select" style="float:left; margin:0;">
								<form:select path="sc_year" cssClass="form-control">
									<c:forEach begin="${ minYear }" end="${ tYear }" step="1" var="list">
										<form:option value="${ list }">${ list }년</form:option>
									</c:forEach>
								</form:select>
							</label>
							
							<label class="select" style="float:left; margin:0;">
								<form:select path="sc_month" cssClass="form-control">
									<c:forEach begin="1" end="12" step="1" var="list">
										<c:set var="v" value="${ list }" />
										<c:if test="${ v lt 10 }">
											<c:set var="v" value="0${ v }" />
										</c:if>
										<form:option value="${ v }">${ list }월</form:option>
									</c:forEach>
								</form:select>
							</label>
							
							<label class="select" style="float:left; margin:0;">
								<form:select path="sc_date" cssClass="form-control">
									<c:forEach begin="1" end="${ maxDay }" step="1" var="list">
										<c:set var="v" value="${ list }" />
										<c:if test="${ v lt 10 }">
											<c:set var="v" value="0${ v }" />
										</c:if>
										<form:option value="${ v }">${ list }일</form:option>
									</c:forEach>
								</form:select>
							</label>

							<div class="btn-group">
								<button type="button" class="btn btn-info" onclick="$('#sc_year').val('${ tYear }'); $('#sc_month').val('${ tMonth }'); $('#sc_date').val('${ tDay }');"><i class="fa fa-refresh"></i> 초기화</button>
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
		
		<!-- begin col-6 -->
		<div class="col-md-12">
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">${ searchVO.sc_year }년 월별 접속 정보</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-hover table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="100">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
								<col width="70">
							</colgroup>
							<thead>
								<tr>
									<th>${ searchVO.sc_year }년</th>
									<th>1월</th>
									<th>2월</th>
									<th>3월</th>
									<th>4월</th>
									<th>5월</th>
									<th>6월</th>
									<th>7월</th>
									<th>8월</th>
									<th>9월</th>
									<th>10월</th>
									<th>11월</th>
									<th>12월</th>
								</tr>
							</thead>
							<tbody id="dataList1">
								<tr>
									<td>접속수</td>
									<c:forEach var="list" begin="1" end="12" step="1">
										<c:set var="ov"    value="" />
										<c:set var="bg"    value="" />
										<c:set var="count" value="0" />
										<c:set var="per"   value="0" />
										
										<c:set var="v" value="${ list }" />
										<c:if test="${ v lt 10 }">
											<c:set var="v" value="0${ v }" />
										</c:if>
										<c:if test="${ v eq searchVO.sc_month }">
											<c:set var="ov" value="ov" />
											<c:set var="bg" value="fs_data_hbar_ov" />
										</c:if>
										
										<c:if test="${ !empty monthList }">
											<c:forEach var="item" items="${ monthList }">
												<c:if test="${ item.statsMonth eq list }">
													<c:set var="count" value="${ item.tCount }" />
													<c:set var="per"   value="${ item.tPer }" />
												</c:if>
											</c:forEach>
										</c:if>
										
										<td class="${ ov }">
											${ count }
											<div class="fs_data_hbar ${ bg } bluedark_bg" style="height:${ per }%;"></div>
										</td>
									</c:forEach>
								</tr>
								<tr>
									<td style="text-align:center;">접속비율</td>
									<c:forEach var="list" begin="1" end="12" step="1">
										<c:set var="count" value="0" />
										<c:set var="per"   value="0" />
										
										<c:if test="${ !empty monthList }">
											<c:forEach var="item" items="${ monthList }">
												<c:if test="${ item.statsMonth eq list }">
													<c:set var="count" value="${ item.tCount }" />
													<c:set var="per"   value="${ item.tPer }" />
												</c:if>
											</c:forEach>
										</c:if>
										
										<td style="text-align:center;">${ per }%</td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-12">
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">${ searchVO.sc_year }년 ${ searchVO.sc_month }월 일별 접속 정보</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-hover table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="120">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>${ searchVO.sc_month }월</th>
									<th>
										${ searchVO.sc_year }년 ${ searchVO.sc_month }월 일별 접속 수
									</th>
								</tr>
							</thead>
							<tbody id="dataList2">
								<c:forEach var="list" begin="1" end="${ maxDay }" step="1">
									<c:set var="bg"    value="" />
									<c:set var="count" value="0" />
									<c:set var="per"   value="0" />
									
									<c:set var="v" value="${ list }" />
									<c:if test="${ v lt 10 }">
										<c:set var="v" value="0${ v }" />
									</c:if>
									<c:if test="${ v eq searchVO.sc_date }">
										<c:set var="bg" value="fs_data_hbar_ov" />
									</c:if>
									
									<c:if test="${ !empty dateList }">
										<c:forEach var="item" items="${ dateList }">
											<c:if test="${ item.statsDate eq list }">
												<c:set var="count" value="${ item.tCount }" />
												<c:set var="per"   value="${ item.tPer }" />
											</c:if>
										</c:forEach>
									</c:if>
									
									<tr>
										<td>${ list }일</td>
										<td style="text-align:left;">
											<div class="fs_data_hbar ${ bg } bluedark_bg" style="width:${ per }%;"></div>
											${ count }(${ per }%)
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-12">
			<div class="panel panel-inverse" data-sortable-id="form-validation-3">
				<div class="panel-heading">
					<h4 class="panel-title">${ searchVO.sc_year }년 ${ searchVO.sc_month }월 ${ searchVO.sc_date }시간대별 접속 정보</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-hover table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="120">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>${ searchVO.sc_month }월</th>
									<th>
										${ searchVO.sc_year }년 ${ searchVO.sc_month }월 일별 접속 수
									</th>
								</tr>
							</thead>
							<tbody id="dataList2">
								<c:forEach var="list" begin="0" end="23" step="1">
									<c:set var="bg"    value="" />
									<c:set var="count" value="0" />
									<c:set var="per"   value="0" />
									
									<c:set var="v" value="${ list }" />
									<c:if test="${ v lt 10 }">
										<c:set var="v" value="0${ v }" />
									</c:if>
									<c:set var="v2" value="${ list + 1 }" />
									<c:if test="${ v2 lt 10 }">
										<c:set var="v2" value="0${ v2 }" />
									</c:if>
									<c:if test="${ v eq tHour }">
										<c:set var="bg" value="fs_data_hbar_ov" />
									</c:if>
									
									<c:if test="${ !empty hourList }">
										<c:forEach var="item" items="${ hourList }">
											<c:if test="${ item.statsHour eq list }">
												<c:set var="count" value="${ item.tCount }" />
												<c:set var="per"   value="${ item.tPer }" />
											</c:if>
										</c:forEach>
									</c:if>
									
									<tr>
										<td>${ v } ~ ${ v2 }시</td>
										<td style="text-align:left;">
											<div class="fs_data_hbar ${ bg } bluedark_bg" style="width:${ per }%;"></div>
											${ count }(${ per }%)
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>