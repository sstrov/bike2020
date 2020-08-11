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
var actionUrl = 'insert';
<c:if test="${ !empty item.popupSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(!isEmpty(form.popupBgnde) && !isEmpty(form.popupEndde)) {
					// 날짜데이타 숫자만 추출
					var sDate = getDateReplaceText(form.popupBgnde.value);
					var eDate = getDateReplaceText(form.popupEndde.value);
					
					// 시작일이 종료일보다 크면 경고 후 리턴
					if(sDate > eDate) {
						alert("노출기간 시작일이 종료일보다 클 수 없습니다.");
						form.popupBgnde.value = "";
						form.popupEndde.value = "";
						form.popupBgnde.focus();
						return false;
					}
				}

				// 자동사이즈 여부 사용 안함 시
				if($('select[name="atmcSizeAt"] option:selected').val() == 'N') {
					if(isEmpty(form.widthLc)) {
						alert("가로 위치을 입력해 주세요.");
						form.widthLc.focus();
						return false;
					}
					if(isEmpty(form.vrticlLc)) {
						alert("세로 위치을 입력해 주세요.");
						form.vrticlLc.focus();
						return false;
					}
					if(isEmpty(form.widthSize)) {
						alert("가로 사이즈를 입력해 주세요.");
						form.widthSize.focus();
						return false;
					}
					if(isEmpty(form.vrticlSize)) {
						alert("세로 사이즈를 입력해 주세요.");
						form.vrticlSize.focus();
						return false;
					}
				}

				if($('#popupCn').summernote('isEmpty')) {
					alert("내용을 입력해 주세요.");
					return false;
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/popup/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			},
			rules : {
				widthLc: { digits: true },
				vrticlLc: { digits: true },
				widthSize: { digits: true },
				vrticlSize: { digits: true }
			},
			messages : {
				widthLc: { digits: '숫자만 입력 가능합니다.' },
				vrticlLc: { digits: '숫자만 입력 가능합니다.' },
				widthSize: { digits: '숫자만 입력 가능합니다.' },
				vrticlSize: { digits: '숫자만 입력 가능합니다.' }
			},
		});
	
		$('#popupCn').summernote(summNoteOp);
		
		$(".dateTimepicker").datetimepicker({
			format: "Y-m-d H:i",
			lang  : "ko",
		});
	</c:if>
	
	// 팝업 자동사용 여부 변경
	changeDisplay('popupAtmc', ($('select[name="atmcSizeAt"] option:selected').val() == 'N'));
});

// 목록 이동
function goList() {
	f.action = '/${ admURI }/popup/list.do?key=' + menuKey;
	f.submit();
}
</script>

<!-- 멀티 이미지파일 업로드 -->
<script type="text/javascript">
//업로드 파일 설정
var fileImgContentHtml = 'popupCn';				// 본문에 반영 컨텐츠 아이디
var fileImgUniqueId    = '${ uniqueId }';		// 일련번호
var fileImgUpKey       = 'popup';				// 업로드 타입
var fileImgLimit       = 10;					// 업로드 제한 사이즈
var fileImgWidth       = 100;					// 가로 사이즈
var fileImgHeight      = 100;					// 세로 사이즈
var fileImgCrop        = 'Y';					// 썸네일 생성여부 (가로x세로_파일명 형태로 썸네일 생성)
var fileImgThumbAt     = "N";
</script>
<jsp:include page="/WEB-INF/jsp/cmm/file/img_script.jsp" />
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

			<input type="hidden" name="popupSn"  value="${ item.popupSn }">
			<input type="hidden" name="uniqueId" value="${ uniqueId }" />
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">팝업 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="popupNm" name="popupNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="팝업 명 입력" value="${ item.popupNm }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">타입 * :</label>
						<div class="col-md-6 col-sm-6">
							<select id="popupTy" name="popupTy" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true">
								<option value="L" <c:if test="${ item.popupTy eq 'L' }">selected</c:if>>레이어</option>
								<option value="P" <c:if test="${ item.popupTy eq 'P' }">selected</c:if>>팝업</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">노출기간 :</label>
						<div class="col-md-6 col-sm-6">
							<div class="input-group">
								<div class="input-group">
									<input class="dateTimepicker form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> type="text" id="popupBgnde" name="popupBgnde" placeholder="시작일자 입력" value="${ item.popupBgnde }" readonly="true" />
									<span class="input-group-addon">&sim;</span>
									<input class="dateTimepicker form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> type="text" id="popupEndde" name="popupEndde" placeholder="종료일자 입력" value="${ item.popupEndde }" readonly="true" />
								</div>
								<c:if test="${ !empty role && role.powW eq 'Y' }">
									<button type="button" class="btn btn-success" onclick="$('.dateTimepicker').val('');"><i class="fa fa-undo"></i> 초기화</button>
								</c:if>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">자동사이즈 여부 :</label>
						<div class="col-md-6 col-sm-6">
							<select name="atmcSizeAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onchange="changeDisplay('popupAtmc', (this.value == 'Y')? false : true);">
								<option value="Y" <c:if test="${ item.atmcSizeAt eq 'Y' }">selected</c:if>>자동</option>
								<option value="N" <c:if test="${ item.atmcSizeAt eq 'N' }">selected</c:if>>수동</option>
							</select>
							
							<div class="col-md-12 col-sm-12 popupAtmc">
								<table id="data-table" class="table table-striped table-bordered">
									<colgroup>
										<col width="150">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<td>위치 (가로x세로)</td>
											<td>
												<div class="input-group">
													<input type="text" id="widthLc" name="widthLc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="가로 입력" value="${ item.widthLc }" />
													<span class="input-group-addon">x</span>
													<input type="text" id="vrticlLc" name="vrticlLc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="세로 입력" value="${ item.vrticlLc }" />
												</div>
											</td>
										</tr>
										<tr>
											<td>사이즈 (가로x세로)</td>
											<td>
												<div class="input-group">
													<input type="text" id="widthSize" name="widthSize" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="가로 입력" value="${ item.widthSize }" />
													<span class="input-group-addon">x</span>
													<input type="text" id="vrticlSize" name="vrticlSize" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="세로 입력" value="${ item.vrticlSize }" />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
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
						<div class="col-md-12 col-sm-12">
							<textarea id="popupCn" name="popupCn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:300px;">${ item.popupCn }</textarea>
						</div>
					</div>
					
					<c:if test="${ !empty role && role.powW eq 'Y' }">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">이미지 첨부 :</label>
							<div class="col-md-6 col-sm-6">
								<!-- 멀티파일 업로드 -->
								<jsp:include page="/WEB-INF/jsp/cmm/file/img_form.jsp" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">이미지 첨부 목록 :</label>
							<div class="col-md-6 col-sm-6" id="uploadFileList_img">
							</div>
						</div>
					</c:if>
					
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