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
var f       = document.forms['frm'];
var menuKey = '${ param.key }';

$(document).ready(function() {
	jQuery('#frm').validate({
		submitHandler: function(form) {
			if(confirm("비밀번호를 변경 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);
				$('input').prop('disabled', false);

				lodingFixedOn("변경중 입니다.", 300, 180);

				f.action = '/${ admURI }/mngr/password/save.do';
				f.submit();
			}
		},
		rules : {
			mngrPw2:  { equalTo: '#mngrPw' }
		},
		messages : {
			mngrPw2:  { equalTo: '비밀번호 항목과 일치하지 않습니다.' },
		}
	});
	
});
</script>
</head>
<body>
<h1 class="page-header">비밀번호 변경</h1>

<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" cssClass="form-horizontal form-bordered" data-parsley-validate="true">

			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">현재 비밀번호 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="password" id="old_mngrPw" name="old_mngrPw" class="form-control" data-parsley-required="true" placeholder="현재 비밀번호 입력" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">비밀번호 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="password" id="mngrPw" name="mngrPw" class="form-control" data-parsley-required="true" placeholder="비밀번호 입력" maxlength="20">
							<p class="note"><strong>Note:</strong> 비밀번호는 영문, 숫자, 특수문자 포함 4 &sim; 20자 이내로 입력을 권장합니다.</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">비밀번호 확인 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="password" id="mngrPw2" name="mngrPw2" class="form-control" data-parsley-required="true" placeholder="비밀번호 확인">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</div>
				
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>