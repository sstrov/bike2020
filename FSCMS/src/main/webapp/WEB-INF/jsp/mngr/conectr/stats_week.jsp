<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript">
//검색 정보 변경
function changeSearch() {
	var f = document.forms['frm'];

	f.action = '/${ admURI }/stat/week.do?key=${ param.key }';
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
					<h4 class="panel-title">주별 접속 정보</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-hover table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="100">
								<col width="80">
								<col width="80">
								<col width="80">
								<col width="80">
								<col width="80">
								<col width="80">
								<col width="80">
							</colgroup>
							<thead>
								<tr>
									<th>주</th>
									<c:if test="${ !empty weekList }">
										<c:forEach var="item" items="${ weekList }">
											<th>${ item }</th>
										</c:forEach>
									</c:if>
								</tr>
							</thead>
							<tbody id="dataList1">
								<tr>
									<td>접속수</td>
									<c:if test="${ !empty weekList }">
										<c:forEach var="list" items="${ weekList }" varStatus="i">
											<c:set var="ov"    value="" />
											<c:set var="bg"    value="" />
											<c:set var="count" value="0" />
											<c:set var="per"   value="0" />
											
											<c:if test="${ i.index eq cWeek }">
												<c:set var="ov" value="ov" />
												<c:set var="bg" value="fs_data_hbar_ov" />
											</c:if>
											
											<c:if test="${ !empty itemList }">
												<c:forEach var="item" items="${ itemList }">
													<c:if test="${ item.statsWeek eq list }">
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
									</c:if>
								</tr>
								<tr>
									<td style="text-align:center;">접속비율</td>
									<c:if test="${ !empty weekList }">
										<c:forEach var="list" items="${ weekList }" varStatus="i">
											<c:set var="per" value="0" />
											
											<c:if test="${ !empty itemList }">
												<c:forEach var="item" items="${ itemList }">
													<c:if test="${ item.statsWeek eq list }">
														<c:set var="per" value="${ item.tPer }" />
													</c:if>
												</c:forEach>
											</c:if>
											
											<td style="text-align:center;">${ per }%</td>
										</c:forEach>
									</c:if>
								</tr>
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