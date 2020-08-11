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
<c:if test="${ !empty item.userSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(actionUrl == 'insert') {
					if(form.idCheck.value == "0") {
						alert("아이디를 체크해 주세요.");
						form.userId.focus();
						return false;
					}
					
					if(isEmpty(form.userPw)) {
						alert('비밀번호를 입력 해 주세요.');
						form.userPw.focus();
						return false;
					}
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/user/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			},
			rules : {
				userPw2:  { equalTo: '#userPw' }
			},
			messages : {
				userPw2:  { equalTo: '비밀번호 항목과 일치하지 않습니다.' },
			}
		});
	
		// 아이디 체크
		$("#userId").keyup(function() {
			$("#userPw").prop('disabled', false);
			$("#userPw2").prop('disabled', false);
			$.userValidate(this.value);
		});
		
		$.userValidate = function(v) {
			var f = document.forms["frm"];
			var altImg = "<strong>경고:</strong>";
			var okImg = "<strong>Success:</strong>";
			
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
			
			// 아이디 정보 조회
			$.ajax({
				url     : "/${ admURI }/user/isExistId.do?key=" + menuKey,
				type    : "POST",
				data    : {
					"userId" : v
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
	f.action = '/${ admURI }/user/list.do?key=' + menuKey;
	f.submit();
}

<c:if test="${ item.lockCo ge 5 }">
/**
 * 로그인 잠김 해제
 */
function setUnlockLogin(obj) {
	if(confirm("로그인 잠김을 해제 하시겠습니까?")) {
		$.ajax({
			url     : "/${ admURI }/user/unlockLogin.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"userSn" : "${ item.userSn }",
				"userId" : "${ item.userId }"
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
			url     : "/${ admURI }/user/pwReset.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"userSn" : "${ item.userSn }",
				"userId" : "${ item.userId }"
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
			<input type="hidden" name="userSn"  value="${ item.userSn }">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">아이디 * :</label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ !empty item.userId }">
									<input type="text" id="userId" name="userId" value="${ item.userId }" class="form-control" disabled />
								</c:when>
								<c:otherwise>
									<input type="text" id="userId" name="userId" class="form-control" data-parsley-required="true" placeholder="아이디 입력" maxlength="20" />
									<p id="idStatus" class="note"><strong>Note:</strong> 아이디는 영문, 숫자 조합으로 3 ~ 20자 이내로 입력해 주세요. <small>(직접 입력 또는 사용자를 조회할 수 있습니다.)</small></p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					
					<c:choose>
						<c:when test="${ !empty item.userId }">
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">새 비밀번호 :</label>
								<div class="col-md-6 col-sm-6">
									<input type="password" id="userPw" name="userPw" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="새 비밀번호 입력" maxlength="20" />
									<p class="note"><strong>Note:</strong> 비밀번호 변경시에만 이용해 주세요.</p>
									<button type="button" class="btn btn-danger" onclick="setPwReset(this);"><i class="fa fa-undo"></i> 비밀번호 초기화</button>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">비밀번호 :</label>
								<div class="col-md-6 col-sm-6">
									<input type="password" id="userPw" name="userPw" class="form-control" placeholder="비밀번호 입력" maxlength="20" />
									<p class="note"><strong>Note:</strong> 비밀번호는 영문, 숫자, 특수문자 포함 4 &sim; 20자 이내로 입력을 권장합니다.</p>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">비밀번호 확인 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="password" id="userPw2" name="userPw2" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="비밀번호 확인" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이름 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userNm" name="userNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="이름 입력" value="${ item.userNm }" maxlength="20" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">전화번호 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userTelno" name="userTelno" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="전화번호 입력" value="${ item.userTelno }" maxlength="30" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">휴대폰번호 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userMbtlnum" name="userMbtlnum" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="휴대폰번호 입력" value="${ item.userMbtlnum }" maxlength="30" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">휴대폰번호 수신여부 :</label>
						<div class="col-md-6 col-sm-6">
							<select id="userMbtlnumRecptnAt" name="userMbtlnumRecptnAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="N" <c:if test="${ item.userMbtlnumRecptnAt eq 'N' }">selected</c:if>>수신안함</option>
								<option value="Y" <c:if test="${ item.userMbtlnumRecptnAt eq 'Y' }">selected</c:if>>수신</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이메일 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userEmail" name="userEmail" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="이메일 입력" value="${ item.userEmail }" data-parsley-type="email" maxlength="255" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이메일 수신여부 :</label>
						<div class="col-md-6 col-sm-6">
							<select id="userEmailRecptnAt" name="userEmailRecptnAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="N" <c:if test="${ item.userEmailRecptnAt eq 'N' }">selected</c:if>>수신안함</option>
								<option value="Y" <c:if test="${ item.userEmailRecptnAt eq 'Y' }">selected</c:if>>수신</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">주소 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userZip" name="userZip" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> readonly value="${ item.userZip }" placeholder="우편번호 검색" style="float:left; width:180px;" />
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="button" class="btn btn-success" onclick="openForm_post('userZip', 'userAdresBass', 'userAdresDetail');"><i class="fa fa-search"></i> 우편번호 검색</button><br/>
							</c:if>
							<input type="text" id="userAdresBass" name="userAdresBass" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> readonly placeholder="우편번호 검색" value="${ item.userAdresBass }" />
							<input type="text" id="userAdresDetail" name="userAdresDetail" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="상세주소 입력" value="${ item.userAdresDetail }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">기타 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="userRm" name="userRm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:100px;" maxlength="4000">${ item.userRm }</textarea>
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