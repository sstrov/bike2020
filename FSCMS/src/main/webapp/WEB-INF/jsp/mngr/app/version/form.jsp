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

<link rel="stylesheet" href="/resource/mngr/plugins/datetimepicker/jquery.datetimepicker.css">
<script src="/resource/mngr/plugins/datetimepicker/jquery.datetimepicker.js"></script>

<link rel="stylesheet" href="/resource/mngr/plugins/summernote-0.8.11/summernote-lite.css">
<script src="/resource/mngr/plugins/summernote-0.8.11/summernote-lite.js"></script>
<script src="/resource/mngr/plugins/summernote-0.8.11/lang/summernote-ko-KR.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
//var actionUrl = 'update';
var tt = "${role.powW}"; 
$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/app/version/update.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	</c:if>
});
</script>
</head>
<body>

<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">

			<input type="hidden" name="osSn"  value="${ item.osSn }"/>
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">OS * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="os" name="os" class="form-control" data-parsley-required="true" placeholder="팝업 명 입력" value="${ item.os }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">IOS * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="ios" name="ios" class="form-control" data-parsley-required="true" placeholder="IOS 버전 입력" value="${ item.ios }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">Android * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="android" name="android" class="form-control" data-parsley-required="true" placeholder="Android 버전 입력" value="${ item.android }" />
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">수정</button>
							</c:if>
						</div>
					</div>
				
				</div>
			</div>
		</form:form>
	</div>
</div>


</body>
</html>