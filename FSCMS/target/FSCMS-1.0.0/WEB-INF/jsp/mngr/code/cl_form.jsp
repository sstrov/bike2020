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
<c:if test="${ !empty item.codeClId }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(actionUrl == 'insert') {
					if(form.idCheck.value == "0") {
						alert("분류코드를 체크해 주세요.");
						form.codeClId.focus();
						return false;
					}
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/code/cl/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	
		// 분류코드 체크
		$("#codeClId").keyup(function() {
			var f = document.forms["frm"];
			var altImg = "<strong>경고:</strong>";
			var okImg = "<strong>Success:</strong>";
			
			if(!codeClCheck(this.value)) {
				$("#idStatus").html(altImg + " 유효하지 않은 분류코드 입니다. (예 : SIT)");
				$("#idStatus").css("color", "red");
				f.idCheck.value = "0";
				return;
			}
			
			// 아이디 정보 조회
			$.ajax({
				url     : "/${ admURI }/code/cl/isExistId.do?key=" + menuKey,
				type    : "POST",
				data    : {
					"codeClId": this.value
				},
				dataType: "json",
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
				success:function(result) {
					var rt = result[0];
					if(rt != null && rt.msg != null && rt.msg.length > 0) {
						alert(rt.msg);
						return false;
					}
					
					var item = result[0];
					if(item.msg != '') {
						$("#idStatus").html(altImg + item.msg);
						$("#idStatus").css("color", "red");
						f.idCheck.value = "0";
						return;
					} else {
						$("#idStatus").html(okImg + " 사용가능한 분류코드 입니다.");
						$("#idStatus").css("color", "blue");
						f.idCheck.value = "1";
					}
				}
			});
		});
	</c:if>
});

function codeClCheck(v) {
	var regularID = /^[A-Z]{3}$/;
	return regularID.test(v);
}

// 목록 이동
function goList() {
	f.action = '/${ admURI }/code/cl/list.do?key=' + menuKey;
	f.submit();
}
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
				<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
					<c:if test="${ !empty searchVO.fieldList }">
						<c:forEach var="sec" items="${ searchVO.fieldList }">
							<form:hidden path="${ sec.name }" />
						</c:forEach>
					</c:if>

					<input type="hidden" name="idCheck" value="0" />

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">분류코드 아이디 * :</label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ !empty item.codeClId }">
									<input type="text" id="codeClId" name="codeClId" value="${ item.codeClId }" class="form-control" disabled />
								</c:when>
								<c:otherwise>
									<input type="text" id="codeClId" name="codeClId" class="form-control" placeholder="분류코드 아이디 입력" data-parsley-required="true" data-parsley-minlength="3" maxlength="3" />
									<p id="idStatus" class="note"><strong>Note:</strong> 분류코드 아이디는 영문 대문자 조합으로 3자리로 입력해 주세요.</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">분류코드 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="codeClNm" name="codeClNm" value="${ item.codeClNm }" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> class="form-control" data-parsley-required="true" placeholder="분류코드 명 입력" maxlength="15" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">분류코드 설명 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="codeClDc" name="codeClDc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="분류코드 설명 입력" rows="10" maxlength="200">${ item.codeClDc }</textarea>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">사용여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="useAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="Y" <c:if test="${ item.useAt eq 'Y' }">selected</c:if>>사용</option>
								<option value="N" <c:if test="${ item.useAt eq 'N' }">selected</c:if>>사용안함</option>
							</select>
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
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>