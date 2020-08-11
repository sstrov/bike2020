<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
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
<c:if test="${ !empty item.bannerEstbsSn }">
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
	
					f.action = '/${ admURI }/banner/estbs/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	</c:if>
});

// 목록 이동
function goList() {
	f.action = '/${ admURI }/banner/estbs/list.do?key=' + menuKey;
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

			<input type="hidden" name="bannerEstbsSn" value="${ item.bannerEstbsSn }">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">팝업존 설정 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="bannerEstbsNm" name="bannerEstbsNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="팝업존 설정 명 입력" value="${ item.bannerEstbsNm }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이미지 사이즈 :</label>
						<div class="col-md-6 col-sm-6">
							<div class="input-group">
								<input type="text" id="widthSize" name="widthSize" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-type="number" placeholder="가로사이즈 입력" value="${ item.widthSize }" />
								<span class="input-group-addon">x</span>
								<input type="text" id="vrticlSize" name="vrticlSize" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-type="number" placeholder="세로사이즈 입력" value="${ item.vrticlSize }" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">업로드 용량 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="uploadLmttCpcty" name="uploadLmttCpcty" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" data-parsley-type="number" placeholder="업로드 용량 입력" value="${ item.uploadLmttCpcty }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">옵션 :</label>
						<div class="col-md-6 col-sm-6">
							<label class="checkbox checkbox-inline">
								<input type="checkbox" name="optnTagAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ item.optnTagAt eq 'Y' }">checked</c:if> />
								태그 내용
							</label>
							<label class="checkbox checkbox-inline">
								<input type="checkbox" name="optnImageAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ item.optnImageAt eq 'Y' }">checked</c:if> />
								이미지 업로드
							</label>
							<label class="checkbox checkbox-inline">
								<input type="checkbox" name="optnBcrnImageAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ item.optnBcrnImageAt eq 'Y' }">checked</c:if> />
								배경이미지 업로드
							</label>
							<label class="checkbox checkbox-inline">
								<input type="checkbox" name="optnClassAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ item.optnClassAt eq 'Y' }">checked</c:if> />
								클레스명 입력
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">팝업존 설명 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="bannerEstbsDc" name="bannerEstbsDc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:300px;">${ item.bannerEstbsDc }</textarea>
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