<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/resource/mngr/plugins/jquery-validate/jquery.validate.min.js'/>"></script>
<script src="<c:url value='/resource/mngr/plugins/parsley/dist/parsley.js'/>"></script>
<script src="<c:url value='/resource/mngr/plugins/parsley/src/i18n/ko.js'/>"></script>

<script src="<c:url value='/resource/mngr/js/cmm/dates.js'/>"></script>
<script src="<c:url value='/resource/mngr/js/cmm/utils.js'/>"></script>
<script src="<c:url value='/resource/mngr/js/cmm/jquery_utils.js'/>"></script>

<script>
var f         = document.forms['frm'];
var admURI    = "${ admURI }";
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.atchmnflManageSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = "<c:url value='/${ admURI }/atchmnfl/mngr/" + actionUrl + ".do'/>" + "?key=" + menuKey;
					f.submit();
				}
			}
		});
	</c:if>
});

// 목록 이동
function goList() {
	f.action = "<c:url value='/${ admURI }/atchmnfl/mngr/list.do'/>" + "?key=" + menuKey;
	f.submit();
}
</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true" enctype="multipart/form-data">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>
			
			<input type="hidden" name="atchmnflManageSn" value="${ item.atchmnflManageSn }" />

			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">파일첨부 * :</label>
						<div class="col-md-3 col-sm-3">
							<input type="file" class="btn btn-default" name="uploadFile" data-parsley-required="true" onchange="chkFile(this, 'file', document.forms['frm'], false, 10);" />
						</div>
						<div class="col-md-3 col-sm-3">
							<c:if test="${ !empty item.atchmnflRealNm && !empty item.flpth }">
								${ item.atchmnflNm }
							</c:if>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">저장</button>
							</c:if>
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
						</div>
					</div>
				
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>