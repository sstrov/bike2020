<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<link rel="stylesheet" href="/resource/mngr/plugins/summernote-0.8.11/summernote.css">
<script src="/resource/mngr/plugins/summernote-0.8.11/summernote.js"></script>
<script src="/resource/mngr/plugins/summernote-0.8.11/lang/summernote-ko-KR.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.bbsSn }">
	actionUrl = 'update';
</c:if>

$(document).ready(function() {
	jQuery('#frm').validate({
		submitHandler: function(form) {
			
			if($('#bbsCn').prop('tagName') != undefined) {
				if($('#bbsCn').attr('data-required') == "true") {
					if($('#bbsCn').summernote('isEmpty')) {
						alert("내용을 입력해 주세요.");
						return false;
					}
				}
			}

			if(confirm("저장 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);
				$('input').prop('disabled', false);

				lodingFixedOn("저장중 입니다.", 300, 180);

				f.action = '/${ admURI }/bbs/' + actionUrl + '.do?key=' + menuKey;
				f.submit();
			}
		}
	});
	
	if($('.datepicker').prop('class') != undefined) {
		$('.datepicker').datepicker({
			format: 'yyyy-mm-dd',
			language: 'kr',
			todayBtn: true,
			todayHighlight: true
		});
	}
	
	if($('#bbsCn').prop('tagName') != undefined) {
		$('#bbsCn').summernote(summNoteOp);
	}
});

// 목록 이동
function goList() {
	f.bbsUpperSn.value = '';
	f.bbsSn.value = '';
	f.action = '/${ admURI }/bbs/list.do?key=' + menuKey;
	f.submit();
}
</script>

<%-- <jsp:include page="/WEB-INF/jsp/common/post/popup.jsp" /> --%>

<c:if test="${ bbsEstbs.atchAt eq 'Y' }">
<!-- 멀티 이미지파일 업로드 -->
<script>
//업로드 파일 설정
var fileImgContentHtml = 'bbsCn';					// 본문에 반영 컨텐츠 아이디
var fileImgUniqueId    = '${ uniqueId }';			// 일련번호
var fileImgUpKey       = 'bbs';						// 업로드 타입
var fileImgLimit       = ${ bbsEstbs.atchSize };	// 업로드 제한 사이즈
var fileImgWidth       = 100;						// 가로 사이즈
var fileImgHeight      = 100;						// 세로 사이즈
var fileImgCrop        = 'Y';						// 썸네일 생성여부 (가로x세로_파일명 형태로 썸네일 생성)
</script>

<!-- 멀티 첨부파일 업로드 -->
<jsp:include page="/WEB-INF/jsp/cmm/file/doc_script.jsp" />
</c:if>
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
			
			<input type="hidden" name="uniqueId"   value="${ uniqueId }" />
			<input type="hidden" name="bbsSn"      value="${ item.bbsSn }" />
			<input type="hidden" name="bbsUpperSn" value="<c:if test="${ !empty reply }">${ reply.bbsSn }</c:if>" />

			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<c:if test="${ bbsEstbs.noticeAt eq 'Y' && empty reply }">
						<!-- 공지여부 -->
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">공지글 여부 * :</label>
							<div class="col-md-6 col-sm-6">
								<label style="float:left; margin-right:20px;">
									<input type="radio" name="noticeAt" value="Y" <c:if test="${ item.noticeAt eq 'Y' }">checked</c:if> />
									공지글
								</label>
								
								<label style="float:left; margin-right:20px;">
									<input type="radio" name="noticeAt" value="N" <c:if test="${ item.noticeAt eq 'N' || empty item.noticeAt }">checked</c:if> />
									일반글
								</label>
							</div>
						</div>
					</c:if>
					
					<c:if test="${ bbsEstbs.secretAt eq 'Y' && bbsEstbs.secretAtAlways eq 'N' }">
						<!-- 비밀글여부 -->
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">비밀글 여부 :</label>
							<div class="col-md-6 col-sm-6">
								<label>
									<input type="checkbox" name="secretAt" value="Y" <c:if test="${ item.secretAt eq 'Y' }">checked</c:if> />
									선택 시 비밀글
								</label>
							</div>
						</div>
					</c:if>
					
					<c:if test="${ !empty fieldList }">
						<c:forEach var="list" items="${ fieldList }">
							<c:if test="${ list.bbsFieldCode ne 'registDe' && list.bbsFieldCode ne 'updtDe' && list.bbsFieldCode ne 'rdcnt' }">
								<div class="form-group">
									<c:choose>
										<c:when test="${ list.bbsFieldCode eq 'ctgryNm' }">
											<!-- 카테고리 -->
											<div class="form-group">
												<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if> :</label>
												<div class="col-md-6 col-sm-6">
													<select id="bbsCtgrySn" name="bbsCtgrySn" class="form-control" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>
														<option value="">선택</option>
														<c:if test="${ !empty cateList }">
															<c:forEach var="cate" items="${ cateList }">
																<option value="${ cate.bbsCtgrySn }" <c:if test="${ item.bbsCtgrySn eq cate.bbsCtgrySn }">selected</c:if>>${ cate.ctgryNm }</option>
															</c:forEach>
														</c:if>
													</select>
												</div>
											</div>
										</c:when>
										
										<c:when test="${ list.bbsFieldCode eq 'bbsCn' }">
											<!-- 게시판 내용 -->
											<c:if test="${ !empty reply }">
												<!-- 답변 -->
												<div class="form-group">
													<label class="control-label col-md-4 col-sm-4">원본글 :</label>
													<div class="col-md-6 col-sm-6">
														${ xss:decode(reply.bbsCn) }
													</div>
												</div>
											</c:if>
											
											<div class="form-group">
												<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if> :</label>
												<div class="col-md-6 col-sm-6">
													<textarea id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" style="height:250px;" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-required="true"</c:if>>${ item[list.bbsFieldCode] }</textarea>
												</div>
											</div>
										</c:when>
										
										<c:when test="${ list.bbsFieldCode eq 'file' }">
											<!-- 첨부 -->
											<div class="form-group">
												<label class="control-label col-md-4 col-sm-4">파일 첨부 :</label>
												<div class="col-md-6 col-sm-6">
													<!-- 멀티파일 업로드 -->
													<jsp:include page="/WEB-INF/jsp/cmm/file/doc_form.jsp" />
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-md-4 col-sm-4">파일 첨부 목록 :</label>
												<div class="col-md-6 col-sm-6" id="uploadFileList_doc">
												</div>
											</div>
										</c:when>
										<c:otherwise>
										
											<div class="form-group">
												<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if> :</label>
												<div class="col-md-6 col-sm-6">
													<c:choose>
														<c:when test="${ list.fieldTy eq '1' }">
															<!-- 숫자만 입력 -->
															<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" data-parsley-type="number" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
														</c:when>
														
														<c:when test="${ list.fieldTy eq '2' }">
															<!-- 한줄 입력 -->
															<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
														</c:when>
														
														<c:when test="${ list.fieldTy eq '3' }">
															<!-- 여러 줄 입력 -->
															<textarea id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" style="height:250px;" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>${ item[list.bbsFieldCode] }</textarea>
														</c:when>
														
														<c:when test="${ list.fieldTy eq '4' }">
															<!-- 단일 선택(radio) -->
															<c:set var="textArr" value="${ fn:split(list.fieldTyText, ',') }" />
															
															<c:if test="${ !empty textArr }">
																<c:forEach var="arr" items="${ textArr }" varStatus="i">
																	<label class="" style="float:left; margin-right:20px;">
																		<input type="radio" name="${ list.bbsFieldCode }" value="${ arr }" <c:if test="${ item[list.bbsFieldCode] eq arr || (empty item[list.bbsFieldCode] && i.first) }">checked</c:if> />
																		${ arr }
																	</label>
																</c:forEach>
															</c:if>
														</c:when>
														
														<c:when test="${ list.fieldTy eq '5' }">
															<!-- 단일 선택(select) -->
															<c:set var="textArr" value="${ fn:split(list.fieldTyText, ',') }" />
															
															<select id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>
																<option value="">선택</option>
																<c:if test="${ !empty textArr }">
																	<c:forEach var="arr" items="${ textArr }" varStatus="i">
																		<option value="${ arr }" <c:if test="${ item[list.bbsFieldCode] eq arr }">selected</c:if>>${ arr }</option>
																	</c:forEach>
																</c:if>
															</select>
														</c:when>
														
														<c:when test="${ list.fieldTy eq '6' }">
															<!-- 다중 선택(checkbox) -->
															<c:set var="textArr" value="${ fn:split(list.fieldTyText, ',') }" />
															
															<c:if test="${ !empty textArr }">
																<c:forEach var="arr" items="${ textArr }" varStatus="i">
																	<label class="" style="float:left; margin-right:20px;">
																		<input type="checkbox" name="${ list.bbsFieldCode }" value="${ arr }" <c:if test="${ item[list.bbsFieldCode] eq arr }">checked</c:if> />
																		${ arr }
																	</label>
																</c:forEach>
															</c:if>
														</c:when>
														
														<c:when test="${ list.fieldTy eq '7' }">
															<!-- 주소 검색 -->
															<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
															<button type="button" class="btn btn-success" onclick="openForm_post('', '${ list.bbsFieldCode }', '');"><i class="fa fa-search"></i> 우편번호 검색</button>
														</c:when>
														
														<c:when test="${ list.fieldTy eq '8' }">
															<!-- 날짜 -->
															<div class="input-group">
																<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control datepicker" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true" data-parsley-errors-container="#dateElement_${ list.bbsFieldCode }"</c:if> style="float:left; width:150px;" />
																<button type="button" class="btn btn-success" onclick="$('#${ list.bbsFieldCode }').val('');"><i class="fa fa-undo"></i> 초기화</button><br/>
																<span id="dateElement_${ list.bbsFieldCode }"></span>
															</div>
														</c:when>
													</c:choose>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
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