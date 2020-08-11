<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.eduSn }">
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
	
					f.action = '/${ admURI }/edu/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
		
	</c:if>
	
});
// 목록 이동
function goList() {
	f.action = '/${ admURI }/edu/list.do?key=' + menuKey;
	f.submit();
}

</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>

			<input type="hidden" name="eduSn"  value="${ item.eduSn }">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">주관 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduHost" name="eduHost" class="form-control" data-parsley-required="true" placeholder="주관 입력" value="${ item.eduHost }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">교육내용 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduNm" name="eduNm" class="form-control" data-parsley-required="true" placeholder="교육내용 입력" value="${ item.eduNm }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">교육대상 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduTarget" name="eduTarget" class="form-control" data-parsley-required="true" placeholder="교육대상 입력" value="${ item.eduTarget }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">교육일자 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduDay" name="eduDay" class="form-control" data-parsley-required="true" placeholder="교육일자 입력" value="${ item.eduDay }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">교육장소 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduPlace" name="eduPlace" class="form-control" data-parsley-required="true" placeholder="교육장소 입력" value="${ item.eduPlace }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">교육문의 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="eduTel" name="eduTel" class="form-control" placeholder="교육문의 입력" value="${ item.eduTel }" />
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