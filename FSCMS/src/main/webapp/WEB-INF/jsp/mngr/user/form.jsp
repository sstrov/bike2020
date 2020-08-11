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
$('#noteSn').data('isCheck', true);
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

				if(!$('#noteSn').data('isCheck')){
					alert("인증수첩번호를 확인해 주시기 바랍니다.");
					return false;
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

function addNoteSn(){
	var noteSn = $('#noteSn').val();
	var userNm = $('#userNm').val();
	
	if(noteSn == '' && noteSn == null){
		alert("인증 수첩번호를 입력해주세요.");
		return false;
	}
	
	$.ajax({
		url     : "/${ admURI }/auth/note/checkNoteSn.do?key=" + menuKey,
		type    : "POST",
		data    : {
			"noteSn" : noteSn,
			"userNm" : userNm
		},
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			//alert(result.state);
			if(result.state != 'Y'){
				$('#noteSn').data('isCheck', false);
				alert(result.msg);
			}else{
				if(confirm("등록가능한 번호입니다. 사용하시겠습니까?")){
					$('#noteSn').data('isCheck', true);	
				}else{
					return false;
				}
			}
		}
	});
}

function deleteNoteSn(){
	var ns = $('select[name=noteSn]').val();
	var us = "${ item.userSn }";
	
	//alert(ns + "//" + us);
	if(ns != null && ns != ''){
		alert("인증수첩번호를 입력해주세요");
		return false;
	}
	if(!confirm("정말 삭제하시겠습니까?")){
		return false;
	}
	if(ns == null && ns == ''){
		alert("노트 값 오류입니다.");
		return false;
	}
	$.ajax({
		url     : "/${ admURI }/auth/note/deleteNoteSn.do?key=" + menuKey,
		type    : "POST",
		data    : {
			"noteSn" : ns,
			"userSn" : us
		},
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			alert(result.msg);
			
			location.reload(true);
		}
	});
}

function registAuthNote(){
	var ns = $('select[name=noteSn]').val();
	var us = "${ item.userSn }";
	
	//alert(ns + "//" + us);
	
	if(ns == null && ns == ''){
		alert("노트 값 오류입니다.");
		return false;
	}
	$.ajax({
		url     : "/${ admURI }/auth/note/registAuthNoteSn.do?key=" + menuKey,
		type    : "POST",
		data    : {
			"noteSn" : ns,
			"userSn" : us
		},
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			alert(result.msg);
			
			location.reload(true);
		}
	});
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
						<label class="control-label col-md-4 col-sm-4">이메일 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userEmail" name="userEmail" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="이메일 입력" value="${ item.userEmail }" data-parsley-type="email" maxlength="255" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">2년 재동의 알람 이메일 수신여부 :</label>
						<div class="col-md-6 col-sm-6">
							<p class="note"><strong></strong> 자전거행복나눔에서 제공하는 2년 재동의 만료 30일 미만의 안내를 이메일로 수신하시겠습니까?</p>
							<select id="userEmailRecptnAt" name="userEmailRecptnAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="N" <c:if test="${ item.userEmailRecptnAt eq 'N' }">selected</c:if>>수신안함</option>
								<option value="Y" <c:if test="${ item.userEmailRecptnAt eq 'Y' }">selected</c:if>>수신</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">2년 재동의 알람 SMS 수신여부 :</label>
						<div class="col-md-6 col-sm-6">
							<p class="note"><strong></strong> 자전거행복나눔에서 제공하는 2년 재동의 만료 30일 미만의 안내를 SMS로 수신하시겠습니까?</p>
							<select id="userMbtlnumRecptnAt" name="userMbtlnumRecptnAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="N" <c:if test="${ item.userMbtlnumRecptnAt eq 'N' }">selected</c:if>>수신안함</option>
								<option value="Y" <c:if test="${ item.userMbtlnumRecptnAt eq 'Y' }">selected</c:if>>수신</option>
							</select>
						</div>
					</div>
					
					<%-- <div class="form-group">
						<label class="control-label col-md-4 col-sm-4">주소 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="userZip" name="userZip" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> readonly value="${ item.userZip }" placeholder="우편번호 검색" style="float:left; width:180px;" />
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="button" class="btn btn-success" onclick="openForm_post('userZip', 'userAdresBass', 'userAdresDetail');"><i class="fa fa-search"></i> 우편번호 검색</button><br/>
							</c:if>
							<input type="text" id="userAdresBass" name="userAdresBass" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> readonly placeholder="우편번호 검색" value="${ item.userAdresBass }" />
							<input type="text" id="userAdresDetail" name="userAdresDetail" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="상세주소 입력" value="${ item.userAdresDetail }" />
						</div>
					</div> --%>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">비밀번호 힌트 :</label>
						<div class="col-md-6 col-sm-6">
							<select id="hint" name="hint" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="" <c:if test="${ item.hint eq '' }">selected</c:if>>선택해주세요.</option>
								<option value="PASS_QST_TYPE_001" <c:if test="${ item.hint eq 'PASS_QST_TYPE_001' }">selected</c:if>>아버지 성함은?</option>
								<option value="PASS_QST_TYPE_002" <c:if test="${ item.hint eq 'PASS_QST_TYPE_002' }">selected</c:if>>어머니 성함은?</option>
								<option value="PASS_QST_TYPE_003" <c:if test="${ item.hint eq 'PASS_QST_TYPE_003' }">selected</c:if>>어린시절 별명은?</option>
								<option value="PASS_QST_TYPE_004" <c:if test="${ item.hint eq 'PASS_QST_TYPE_004' }">selected</c:if>>가보고 싶은 곳은?</option>
								<option value="PASS_QST_TYPE_005" <c:if test="${ item.hint eq 'PASS_QST_TYPE_005' }">selected</c:if>>가장 소중한 물건은?</option>
								<option value="PASS_QST_TYPE_006" <c:if test="${ item.hint eq 'PASS_QST_TYPE_006' }">selected</c:if>>강명깊게 읽은 책은?</option>
								<option value="PASS_QST_TYPE_099" <c:if test="${ item.hint eq 'PASS_QST_TYPE_099' }">selected</c:if>>기타</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">힌트 답변 :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="hintAnswer" name="hintAnswer" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="힌트 답변 입력" value="${ item.hintAnswer }" maxlength="200" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">국토종주 인증 수첩번호 :</label>
						<div class="col-md-6 col-sm-6">
							${ item.noteSn }
							<p class="note"><strong></strong> 인증 수첩은 1개만 등록이 가능합니다.</p>
							<input type="text" id="noteSn" name="noteSn" class="form-control" value="${ item.noteSn }" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="수첩번호 입력" style="float:left; width:220px;"  maxlength="14" />
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="button" class="btn btn-primary" onclick="addNoteSn();" ><i class="fa fa-search"></i> 번호확인</button><br/>
							</c:if>
						</div>
						<label class="control-label col-md-4 col-sm-4"></label>
						<%-- <div class="col-md-6 col-sm-6">
							
							<p class="note"><strong></strong> 인증 수첩은 1개만 등록이 가능합니다.</p>
							<select id="noteSn" name="noteSn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<c:forEach var="result" items="${ noteList }" varStatus="vs">
									<option value="${ result.noteSn }" <c:if test="${ result.authYn eq 'Y' }">selected</c:if>>
									${ result.noteSn } <c:if test="${ result.authYn eq 'Y' }" > *인증 </c:if>
									</option>
								</c:forEach>
							</select>
							<button type="button" class="btn btn-danger" onclick="deleteNoteSn();">선택삭제</button>
							<button type="button" class="btn btn-default" onclick="registAuthNote();">인증수첩 등록</button> 
						</div> --%>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">추가 인증 수첩번호</label>
						<div class="col-md-6 col-sm-6">
							<select id="noteSn" name="noteSn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<c:forEach var="result" items="${ noteList }" varStatus="vs">
									<c:if test="${ result.authYn ne 'Y' }">
										<option value="${ result.noteSn }">	${ result.noteSn }</option>
									</c:if>
								</c:forEach>
							</select>
							<button type="button" class="btn btn-danger" onclick="deleteNoteSn();">선택삭제</button>
							<button type="button" class="btn btn-default" onclick="registAuthNote();">인증수첩 등록</button> 
						</div>
					</div>
					
					
					<%-- <div class="form-group">
						<label class="control-label col-md-4 col-sm-4">기타 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="userRm" name="userRm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:100px;" maxlength="4000">${ item.userRm }</textarea>
						</div>
					</div> --%>

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
					
					<%-- <div class="form-group">
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
					</div> --%>
					<c:if test="${ not empty item.userSn }">
					<c:forEach var="road" items="${ roadList }" varStatus="vs">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">
								${ road.roadNm }
							</label>
							<div class="col-md-6 col-sm-6">
								<c:forEach var="auth" items="${ authList }" varStatus="v">
									<c:if test="${ road.roadCd eq auth.roadCd }">
										<c:if test="${auth.authOrdr ne 1}" >, </c:if>${ auth.authNm } 
									</c:if>
								</c:forEach>
								<c:forEach var="con" items="${ confirmWeb }" varStatus="v">
									<c:if test="${ road.roadCd eq con.roadCd }">
										<c:if test="${con.authOrdr ne 1}" >, </c:if>${ con.authNm } 
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
					</c:if>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ not empty item.noteSn }">
								<button type="button" class="btn btn-primary" onclick="openForm_auth()">인증현황수정</button>
							</c:if>
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

<form:form id="authFrm" name="authFrm" method="post">
	<input type="hidden" name="userSn"  value="${ item.userSn }">
	<input type="hidden" name="noteSn"  value="${ item.noteSn }">
	<c:forEach var="a" items="${ authList }">
		<input type="hidden" name="auth"  value="${ a.authCd }">
	</c:forEach>
	<div class="scrl">
		<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
			<colgroup>
				<col width="20%">
				<col>
			</colgroup>
			<tbody id="authList">
				<c:forEach var="road" items="${ roadList }" varStatus="vs">
					<tr>
						<td>${ road.roadNm }</td>
						<td>
							<c:forEach var="center" items="${ centerList }" varStatus="v">
								<c:if test="${ road.roadCd eq center.roadCd }">
									<input type="checkbox" name="chkbox" value="${ center.authCd },${ center.roadCd }" ${ center.checked } ${ center.disabled }/>
									${ center.authNm } 
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form:form>

<script type="text/javascript">
function addAuthCenter(){
	var userSn = "${item.userSn}";
	var noteSn = "${item.noteSn}";
	if(userSn == null){
		alert("유저정보가 없습니다.");
		return false;
	}
	$.ajax({
		url     : "/${ admURI }/road/auth/authConfirm.do?key=" + menuKey,
		type    : "POST",
		data    : $("#authFrm").serialize(),
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			alert(result.msg);
			$('#authFrm').dialog('close');
			location.reload(true);
		}
	});
}

function openForm_auth() {
	$('#authFrm').dialog('open');
}
$(document).ready(function() {
	$('#authFrm').dialog({
		autoOpen : false,
		width    : 1200,
		resizable: false,
		modal    : true,
		title    : "인증정보 변경",
		draggable: true,
		drag: function(event, ui) {
			var fixPix = $(document).scrollTop();
			iObj = ui.position;
			iObj.top = iObj.top - fixPix;
			$(this).closest(".ui-dialog").css("top", iObj.top + "px");
		},
		buttons : [{
			"html" : "<i class='fa fa-pencil'></i>&nbsp; 인증정보등록",
			"class": "btn btn-default",
			"click": function() {
				addAuthCenter();
			}
		},{
			"html" : "<i class='fa fa-close'></i>&nbsp; 닫기",
			"class": "btn btn-default",
			"click": function() {
				$(this).dialog("close");
			}
		}]
	});
	
});

</script>
</body>
</html>