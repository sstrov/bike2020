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
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">년도별 접속 정보</h4>
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
									<th>년도</th>
									<th>
										전체년도 접속 수
									</th>
								</tr>
							</thead>
							<tbody id="dataList2">
								<c:if test="${ !empty yearList }">
									<c:forEach var="item" items="${ yearList }">
										<c:set var="bg"    value="" />
										<c:if test="${ item.statsYear eq tYear }">
											<c:set var="bg" value="fs_data_hbar_ov" />
										</c:if>
										
										<tr>
											<td>${ item.statsYear }년도</td>
											<td style="text-align:left;">
												<div class="fs_data_hbar ${ bg } bluedark_bg" style="width:${ item.tPer }%;"></div>
												${ item.tCount }(${ item.tPer }%)
											</td>
										</tr>
									</c:forEach>
								</c:if>
								
								<c:if test="${ empty yearList }">
									<tr>
										<td colspan="2" align="center" class="dataTables_empty" style="line-height:100px;">등록된 정보가 없습니다.</td>
									</tr>
								</c:if>
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