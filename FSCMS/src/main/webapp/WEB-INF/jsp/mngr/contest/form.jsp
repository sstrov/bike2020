<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resource/mngr/plugins/datetimepicker/jquery.datetimepicker.css">
<script src="/resource/mngr/plugins/datetimepicker/jquery.datetimepicker.js"></script>

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
<c:if test="${ !empty item.contestSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				/* if(!isEmpty(form.contestBgnde) && !isEmpty(form.contestEndde)) {
					// 날짜데이타 숫자만 추출
					var contestBgnde = getDateReplaceText(form.contestBgnde.value);
					var contestEndde = getDateReplaceText(form.contestEndde.value);
					
					// 시작일이 종료일보다 크면 경고 후 리턴
					if(contestBgnde > contestEndde) {
						alert("노출기간 시작일이 종료일보다 클 수 없습니다.");
						form.contestBgnde.value = "";
						form.contestEndde.value = "";
						form.contestBgnde.focus();
						return false;
					}
				} */
				
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/contest/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
		
		// START AND FINISH DATE
		$(".dateTimepicker").datetimepicker({
			format: "Y-m-d H:i",
			lang  : "ko",
		});
	</c:if>
});

// 목록 이동
function goList() {
	f.action = '/${ admURI }/contest/list.do?key=' + menuKey;
	f.submit();
}

function goCanCel(){
	f.action = '/${ admURI }/contest/cancel.do?key=' + menuKey;
	f.submit();
}
</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true" enctype="multipart/form-data">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>
			
			<input type="hidden" name="contestEstbsSn" value="${ preItem.contestEstbsSn }">
			<input type="hidden" name="contestSn" value="${ item.contestSn }">
			<form:hidden path="useAt"/>
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">공모전 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="contestNm" name="contestNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="공모전 명 입력" value="${ item.contestNm }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">공모전 내용 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="contestCn" name="contestCn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="공모전 내용 입력" style="height:150px;">${ item.contestCn }</textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">분류 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="contestType" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="IMG" <c:if test="${ item.contestType eq 'IMG' }">selected</c:if>>사진</option>
								<option value="UCC" <c:if test="${ item.contestType eq 'UCC' }">selected</c:if>>UCC</option>
							</select>
						</div>
					</div>
					
					<%-- <div class="form-group">
						<label class="control-label col-md-4 col-sm-4">공모전 기간 :</label>
						<div class="col-md-6 col-sm-6">
							<div class="input-group">
								<div class="input-group">
									<input class="dateTimepicker form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> type="text" id="contestBgnde" name="contestBgnde" placeholder="시작일자 입력" value="${ item.contestBgnde }" readonly="true" />
									<span class="input-group-addon">&sim;</span>
									<input class="dateTimepicker form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> type="text" id="contestEndde" name="contestEndde" placeholder="종료일자 입력" value="${ item.contestEndde }" readonly="true" />
								</div>
								<c:if test="${ !empty role && role.powW eq 'Y' }">
									<button type="button" class="btn btn-success" onclick="$('.dateTimepicker').val('');"><i class="fa fa-undo"></i> 초기화</button>
								</c:if>
							</div>
						</div>
					</div> --%>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이미지 첨부 :</label>
						<div class="col-md-3 col-sm-3">
							<input type="file" class="btn btn-default" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> name="uploadFile" onchange="chkFile(this, 'file', document.forms['frm'], false, 10);" />
						</div>
						<div class="col-md-3 col-sm-3">
							<c:if test="${ !empty item.atchmnflImage && !empty item.flpth }">
								<img src="${ item.flpth }/200x100_${ item.atchmnflImage }" alt="" />
								<label>
									<input type="checkbox" name="fileDelAt_img" value="Y" />
									선택 삭제
								</label>
							</c:if>
						</div>
					</div>
					
					<%-- <div class="form-group">
						<label class="control-label col-md-4 col-sm-4">링크 URL :</label>
						<div class="col-md-6 col-sm-6">
							<label class="input input-inline">
								<input type="text" id="bannerItnadr" name="bannerItnadr" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="링크 URL 입력" value="${ item.bannerItnadr }" />
							</label>
							<label class="select select-inline">
								<select name="bannerTrgt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									<option value="_self" <c:if test="${ item.bannerTrgt eq '_self' }">selected</c:if>>현재창</option>
									<option value="_blank" <c:if test="${ item.bannerTrgt eq '_blank' }">selected</c:if>>새창</option>
								</select>
							</label>
						</div>
					</div> --%>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">저장</button>
							</c:if>
							<c:if test="${ !empty item.contestSn }">
								<button type="button" class="btn btn-warning" onclick="goCanCel()">참여취소</button>
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