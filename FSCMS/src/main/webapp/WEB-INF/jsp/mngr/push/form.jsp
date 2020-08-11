<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jstl/fmt"             prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"               prefix="string" %>

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

<link rel="stylesheet" href="/resource/mngr/plugins/summernote-0.8.11/summernote-lite.css">
<script src="/resource/mngr/plugins/summernote-0.8.11/summernote-lite.js"></script>
<script src="/resource/mngr/plugins/summernote-0.8.11/lang/summernote-ko-KR.js"></script>

<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.pushSn }">
	actionUrl = 'update';
</c:if>
$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {

				if($('#pushCn').summernote('isEmpty')) {
					alert("내용을 입력해 주세요.");
					return false;
				}
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/push/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
		
		$('#pushCn').summernote(summNoteOp);
	</c:if>
	
});
// 목록 이동
function goList() {
	f.action = '/${ admURI }/push/list.do?key=' + menuKey;
	f.submit();
}

</script>
<!-- 멀티 이미지파일 업로드 -->
<script type="text/javascript">
//업로드 파일 설정
var fileImgContentHtml = 'pushCn';				// 본문에 반영 컨텐츠 아이디
var fileImgUniqueId    = '${ uniqueId }';		// 일련번호
var fileImgUpKey       = 'push';				// 업로드 타입
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

			<input type="hidden" name="pushSn"  value="${ item.pushSn }">
			<input type="hidden" name="uniqueId" value="${ uniqueId }" />
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">제목 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="pushSj" name="pushSj" class="form-control" data-parsley-required="true" placeholder="제목 입력" value="${ item.pushSj }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">작성자 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="registId" name="registId" class="form-control" disabled value="${ registId }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">내용 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="pushCn" name="pushCn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:100px;" maxlength="4000">${ xss:decode(item.pushCn) }</textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">발송분류 :</label>
						<div class="col-md-6 col-sm-6">
							<select id="pushType" name="pushType" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="S" <c:if test="${ item.pushType eq 'S' }">selected</c:if>>SMS</option>
								<option value="M" <c:if test="${ item.pushType eq 'M' }">selected</c:if>>이메일</option>
							</select>
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