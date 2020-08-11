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

<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script>
var f = document.forms['frm'];

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/site/save.do?key=${ param.key }';
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
		<!-- begin panel -->
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<h4 class="panel-title">등록 폼</h4>
			</div>
			<div class="panel-body panel-form">
				<form:form id="frm" name="frm" method="post" cssClass="form-horizontal form-bordered" data-parsley-validate="true">

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">사이트 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="siteNm" name="siteNm" value="${ item.siteNm }" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="사이트 명 입력" maxlength="20" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">오픈 일자 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="openDe" name="openDe" value="${ item.openDe }" class="form-control datepicker" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-provide="datepicker" data-date-format="yyyy-mm-dd" placeholder="오픈일자 선택" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">중복 로그인 여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="dplctLoginAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="Y" <c:if test="${ item.dplctLoginAt eq 'Y' }">selected</c:if>>중복 로그인 불가</option>
								<option value="N" <c:if test="${ item.dplctLoginAt eq 'N' }">selected</c:if>>중복 로그인 가능</option>
							</select>
							<p id="idStatus" class="note"><strong>Note:</strong> 해당 설정은 관리자/사용자 통합 기능 입니다.</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">로그인 실패 잠금 개수 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="loginFailrLockCo" name="loginFailrLockCo" value="${ item.loginFailrLockCo }" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" data-parsley-type="number" placeholder="잠금 개수 선택" maxlength="3" />
							<p id="idStatus" class="note"><strong>Note:</strong> 해당 설정은 관리자/사용자 통합 기능 입니다.<br/>0 입력 시 무제한</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">활성여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="actvtyAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<c:if test="${ !empty actList }">
									<c:forEach var="code" items="${ actList }">
										<option value="${ code.cd }" <c:if test="${ item.actvtyAt eq code.cd }">selected</c:if>>${ code.nm }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">SNS 사용여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="snsUseAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="Y" <c:if test="${ item.snsUseAt eq 'Y' }">selected</c:if>>사용</option>
								<option value="N" <c:if test="${ item.snsUseAt eq 'N' }">selected</c:if>>사용안함</option>
							</select>
						</div>
					</div>

					<c:if test="${ !empty role && role.powW eq 'Y' }">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</div>
					</c:if>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>