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

	f.action = '/${ admURI }/stat/month.do?key=${ param.key }';
	f.submit();
}
</script>
<style type="text/css">
.datepicker table tr td, .datepicker table tr th { width:40px; }
.datepicker.dropdown-menu th, .datepicker.datepicker-inline th, .datepicker.dropdown-menu td, .datepicker.datepicker-inline td { padding:5px 5px; }
#data-table_info { display:none; }

#dataList2 td div.fs_data_hbar { height:10px; float:left; margin:4px 10px 0 0; background-color:#02253b !important; }
#dataList2 td div.fs_data_hbar_ov { background-color:#ff4e73 !important; }
</style>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">

	<div class="row">
		<div class="col-md-12">
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

							<div class="btn-group">
								<button type="button" class="btn btn-info" onclick="$('#sc_year').val('${ tYear }');"><i class="fa fa-refresh"></i> 초기화</button>
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
			
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">${ searchVO.sc_year }년 월별 접속 정보</h4>
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
									<th>${ searchVO.sc_year }년</th>
									<th>
										${ searchVO.sc_year }년 월별 접속 수
									</th>
								</tr>
							</thead>
							<tbody id="dataList2">
								<c:forEach var="list" begin="1" end="12" step="1">
									<c:set var="bg"    value="" />
									<c:set var="count" value="0" />
									<c:set var="per"   value="0" />
									
									<c:set var="v" value="${ list }" />
									<c:if test="${ v lt 10 }">
										<c:set var="v" value="0${ v }" />
									</c:if>
									<c:if test="${ v eq tMonth }">
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
									
									<tr>
										<td>${ list }월</td>
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