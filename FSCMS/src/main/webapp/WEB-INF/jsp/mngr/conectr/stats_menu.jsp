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

	f.action = '/${ admURI }/stat/menu.do?key=${ param.key }';
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
			
			<div class="panel panel-inverse" data-sortable-id="form-validation-2">
				<div class="panel-heading">
					<h4 class="panel-title">메뉴별 접속 정보</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-hover table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
							<colgroup>
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>메뉴명</th>
									<th>
										메뉴별 접속 수
									</th>
								</tr>
							</thead>
							<tbody id="dataList2">
								<c:if test="${ !empty contentList }">
									<c:forEach var="list" items="${ contentList }">
										<c:set var="count" value="0" />
										<c:set var="per"   value="0" />
										
										<c:if test="${ !empty itemList }">
											<c:forEach var="item" items="${ itemList }">
												<c:if test="${ item.menuSn eq list.menuSn }">
													<c:set var="count" value="${ item.tCount }" />
													<c:set var="per"   value="${ item.tPer }" />
												</c:if>
											</c:forEach>
										</c:if>
										
										<tr>
											<td>
												<c:if test="${ list.menuDp > 1 }">
													<span class="title" style="padding-left:${ list.menuDp * 10 }px;"></span>
												</c:if>
												<span onclick="goModify('${ list.menuSn }');" style="cursor:pointer;">
													${ list.menuNm }
												</span>
											</td>
											<td style="text-align:left;">
												<div class="fs_data_hbar bluedark_bg" style="width:${ per }%;"></div>
												${ count }(${ per }%)
											</td>
										</tr>
									</c:forEach>
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