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
<c:if test="${ !empty item.mngrSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(actionUrl == 'insert') {
					if(form.idCheck.value == "0") {
						alert("아이디를 체크해 주세요.");
						form.mngrId.focus();
						return false;
					}
					
					if(form.mngrSsoAt.value == "N") {
						if(isEmpty(form.accSn) && isEmpty(form.mngrPw)) {
							alert('비밀번호를 입력 해 주세요.');
							form.mngrPw.focus();
							return false;
						}
					}
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/mngr/' + actionUrl + '.do?key=' + menuKey;
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
	
		// 아이디 체크
		$("#mngrId").keyup(function() {
			$("#accSn").val('');
			$("#mngrPw").prop('disabled', false);
			$("#mngrPw2").prop('disabled', false);
			$.userValidate(this.value);
		});
		
		$.userValidate = function(v) {
			var f = document.forms["frm"];
			var altImg = "<strong>경고:</strong>";
			var okImg = "<strong>Success:</strong>";
			
			var accSn = $("#accSn").val();
			
			if(accSn == '' || accSn.length == 0) {
				if(v.length < 3) {
					$("#idStatus").html(altImg + " 아이디는 3자 이상이여야 합니다.");
					$("#idStatus").css("color", "red");
					f.idCheck.value = "0";
					return;
				}
				
				if(!idCheck(v)) {
					$("#idStatus").html(altImg + " 유효하지 않은 아이디 입니다.");
					$("#idStatus").css("color", "red");
					f.idCheck.value = "0";
					return;
				}
			}
			
			// 아이디 정보 조회
			$.ajax({
				url     : "/${ admURI }/mngr/isExistId.do?key=" + menuKey,
				type    : "POST",
				data    : {
					"mngrId" : v,
					"accSn"  : accSn
				},
				dataType: "json",
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
				success:function(result) {
					var item = result[0];
					if(item.msg != '') {
						$("#idStatus").html(altImg + item.msg);
						$("#idStatus").css("color", "red");
						f.idCheck.value = "0";
						return;
					} else {
						$("#idStatus").html(okImg + " 사용가능한 아이디 입니다.");
						$("#idStatus").css("color", "blue");
						f.idCheck.value = "1";
					}
				}
			});
		};
	</c:if>
});

// 목록 이동
function goList() {
	f.action = '/${ admURI }/mngr/list.do?key=' + menuKey;
	f.submit();
}

<c:if test="${ item.lockCo ge 5 }">
/**
 * 로그인 잠김 해제
 */
function setUnlockLogin(obj) {
	if(confirm("로그인 잠김을 해제 하시겠습니까?")) {
		$.ajax({
			url     : "/${ admURI }/mngr/unlockLogin.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"mngrSn" : "${ item.mngrSn }",
				"mngrId" : "${ item.mngrId }"
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				var item = result[0];
				if(item.msg != '') {
					alert(item.msg);
					return;
				} else {
					alert("잠김이 해제 되었습니다.");
					$(obj).parent().html("실패횟수(0)");
				}
				return;
			}
		});
	}
}
</c:if>

/**
 * 비밀번호 초기화
 */
function setPwReset(obj) {
	if(confirm("비밀번호를 초기화 하시겠습니까?")) {
		$.ajax({
			url     : "/${ admURI }/mngr/pwReset.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"mngrSn" : "${ item.mngrSn }",
				"mngrId" : "${ item.mngrId }"
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				var item = result[0];
				if(item.msg != '') {
					alert(item.msg);
					return;
				} else {
					alert("비밀번호가 초기화 되었습니다. (초기화 비밀번호 : ${ defPw })");
				}
				return;
			}
		});
	}
}

function setSsoAt(v) {
	if(v == "Y") {
		$(".pw").hide();
	} else {
		$(".pw").show();
	}
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

			<input type="hidden" name="idCheck" value="0">
			<input type="hidden" name="mngrSn"  value="${ item.mngrSn }">
			<input type="hidden" id="accSn"     name="accSn">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">SSO연동 여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="mngrSsoAt" class="form-control" onchange="setSsoAt(this.value);" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="N" <c:if test="${ item.mngrSsoAt eq 'N' }">selected</c:if>>연동안함</option>
								<option value="Y" <c:if test="${ item.mngrSsoAt eq 'Y' }">selected</c:if>>연동중</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">관리자 아이디 * :</label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ !empty item.mngrId }">
									<input type="text" id="mngrId" name="mngrId" value="${ item.mngrId }" class="form-control" disabled />
								</c:when>
								<c:otherwise>
									<input type="text" id="mngrId" name="mngrId" class="form-control" data-parsley-required="true" placeholder="아이디 입력" maxlength="20" />
									<p id="idStatus" class="note"><strong>Note:</strong> 아이디는 영문, 숫자 조합으로 3 ~ 20자 이내로 입력해 주세요. <small>(직접 입력 또는 사용자를 조회할 수 있습니다.)</small></p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					
					<c:choose>
						<c:when test="${ !empty item.mngrId }">
							<div class="form-group pw" <c:if test="${ item.mngrSsoAt eq 'Y' }">style="display:none;"</c:if>>
								<label class="control-label col-md-4 col-sm-4">새 비밀번호 :</label>
								<div class="col-md-6 col-sm-6">
									<input type="password" id="mngrPw" name="mngrPw" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="새 비밀번호 입력" maxlength="20" />
									<p class="note"><strong>Note:</strong> 비밀번호 변경시에만 이용해 주세요.</p>
									<button type="button" class="btn btn-danger" onclick="setPwReset(this);"><i class="fa fa-undo"></i> 비밀번호 초기화</button>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group pw" <c:if test="${ item.mngrSsoAt eq 'Y' }">style="display:none;"</c:if>>
								<label class="control-label col-md-4 col-sm-4">비밀번호 :</label>
								<div class="col-md-6 col-sm-6">
									<input type="password" id="mngrPw" name="mngrPw" class="form-control" placeholder="비밀번호 입력" maxlength="20" />
									<p class="note"><strong>Note:</strong> 비밀번호는 영문, 숫자, 특수문자 포함 4 &sim; 20자 이내로 입력을 권장합니다.</p>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					
					<div class="form-group pw" <c:if test="${ item.mngrSsoAt eq 'Y' }">style="display:none;"</c:if>>
						<label class="control-label col-md-4 col-sm-4">비밀번호 확인 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="password" id="mngrPw2" name="mngrPw2" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="비밀번호 확인" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">관리자 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="mngrNm" name="mngrNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="이름 입력" value="${ item.mngrNm }" maxlength="20" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">권한 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="roleSn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<c:if test="${ !empty roleList }">
									<c:forEach var="role" items="${ roleList }">
										<option value="${ role.roleSn }" <c:if test="${ item.roleSn eq role.roleSn }">selected</c:if>>${ role.roleNm }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">로그인 잠김여부 :</label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ item.lockCo ge 5 }">
									<button type="button" class="btn btn-danger" onclick="setUnlockLogin(this);"><i class="fa fa-undo"></i> 잠김해제</button>
								</c:when>
								<c:otherwise>
									실패횟수(<c:choose><c:when test="${ empty item.lockCo }">0</c:when><c:otherwise>${ item.lockCo }</c:otherwise></c:choose>)
								</c:otherwise>
							</c:choose>
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