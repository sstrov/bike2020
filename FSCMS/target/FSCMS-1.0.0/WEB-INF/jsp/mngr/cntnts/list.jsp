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
// 수정 페이지 이동
function goModify(key) {
	var f = document.forms['frm'];
	
	f.menuSn.value = key;
	f.action = "/${ admURI }/cntnts/form.do?key=${ param.key }";
	f.submit();
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" cssClass="form-horizontal">
	<input type="hidden" name="menuSn" />

	<div class="row">

		<div class="col-md-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">데이터 목록</h4>
				</div>
				<div class="panel-body">

					<div class="table-responsive">
						<table id="data-table" class="table table-striped table-bordered">
							<colgroup>
								<col width="300">
								<col width="180">
							</colgroup>
							<thead>
								<tr>
									<th>콘텐츠 명</th>
									<th><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>등록일</th>
								</tr>
							</thead>
							<tbody id="dataList">
								<c:if test="${ !empty contentList }">
									<c:forEach var="item" items="${ contentList }">
										<c:set var="pageSt"><small style="font-weight:bold; color:#ccc;">(빈메뉴)</small></c:set>
										<c:if test="${ item.menuCnncTy eq '1' }">
											<c:set var="pageSt"><small style="font-weight:bold; color:blue;">(링크URL)</small></c:set>
										</c:if>
										<c:if test="${ item.menuCnncTy eq '2' }">
											<c:set var="pageSt"><small style="font-weight:bold; color:green;">(콘텐츠)</small></c:set>
										</c:if>
										<c:if test="${ item.menuCnncTy eq '3' }">
											<c:set var="pageSt"><small style="font-weight:bold; color:ccc;">(게시판)</small></c:set>
										</c:if>
										<tr>
											<td class="left black size_15">
												<c:if test="${ item.menuDp > 1 }">
													<span class="title" style="padding-left:${ item.menuDp * 10 }px;"></span>
												</c:if>
												<span onclick="goModify('${ item.menuSn }');" style="cursor:pointer;">
													${ item.menuNm }
													${ pageSt }
												</span>
											</td>
											<td><span onclick="goModify('${ item.menuSn }');" style="cursor:pointer;">${ item.registDe }</span></td>
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