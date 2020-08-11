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
<c:if test="${ !empty item.roleSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(actionUrl == 'insert') {
					if(form.idCheck.value == "0") {
						alert("역할명을 체크해 주세요.");
						form.roleNm.focus();
						return false;
					}
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/mngr/role/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	
		// 역할명 체크
		$("#roleNm").keyup(function() {
			var f = document.forms["frm"];
			var altImg = "<strong>경고:</strong>";
			var okImg = "<strong>Success:</strong>";
			
			// 아이디 정보 조회
			$.ajax({
				url     : "/${ admURI }/mngr/role/isExistId.do?key=" + menuKey,
				type    : "POST",
				data    : {
					"roleNm" : this.value
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
						$("#idStatus").html(okImg + " 사용가능한 명칭 입니다.");
						$("#idStatus").css("color", "blue");
						f.idCheck.value = "1";
					}
				}
			});
		});
	</c:if>
});

// 목록 이동
function goList() {
	f.action = '/${ admURI }/mngr/role/list.do?key=' + menuKey;
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

			<input type="hidden" name="idCheck" value="0">
			<input type="hidden" name="roleSn" value="${ item.roleSn }">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">역할 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="roleNm" name="roleNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="역할 명 입력" value="${ item.roleNm }" maxlength="10" />
							<p id="idStatus" class="note"><strong>Note:</strong> 역할 명을 입력해 주세요.</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">권한 :</label>
						<div class="col-md-6 col-sm-6">
							<table id="data-table" class="table table-striped table-bordered">
								<colgroup>
									<col>
									<col width="90">
									<col width="90">
								</colgroup>
								<thead>
									<tr>
										<th>메뉴명</th>
										<th><label><input type="checkbox" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="$('.powR').prop('checked', this.checked);" /> 읽기</label></th>
										<th><label><input type="checkbox" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="$('.powW').prop('checked', this.checked);" /> 쓰기</label></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${ !empty contentList }">
										<c:forEach var="content" items="${ contentList }">
											<tr>
												<td class="left black size_15">
													<c:if test="${ content.menuDp > 1 }">
														<span class="title" style="padding-left:${ content.menuDp * 10 }px;"></span>
													</c:if>
													${ content.menuNm }
												</td>
												<td><label><input type="checkbox" name="powR" class="powR" value="${ content.menuSn }" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ !empty content.authorRedng && content.authorRedng eq 'Y' }">checked</c:if> /> 읽기</label></td>
												<td><label><input type="checkbox" name="powW" class="powW" value="${ content.menuSn }" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> <c:if test="${ !empty content.authorRegist && content.authorRegist eq 'Y' }">checked</c:if> /> 쓰기</label></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
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