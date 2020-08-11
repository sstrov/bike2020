<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="<c:url value='/resource/brd/fs_ra001/css/style.css' />" />

<script src="<c:url value='/resource/cmm/js/jquery-validate/jquery.validate.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/dist/parsley.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/src/i18n/ko.js' />"></script>

<link href="<c:url value='/resource/cmm/js/bootstrap-datepicker/css/datepicker.css' />" rel="stylesheet" />
<link href="<c:url value='/resource/cmm/js/bootstrap-datepicker/css/datepicker3.css' />" rel="stylesheet" />
<script src="<c:url value='/resource/cmm/js/bootstrap-datepicker/js/bootstrap-datepicker.js' />"></script>
<script src="<c:url value='/resource/cmm/js/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js' />" charset="UTF-8"></script>

<link rel="stylesheet" href="<c:url value='/resource/cmm/js/summernote-0.8.11/summernote-lite.css' />">
<script src="<c:url value='/resource/cmm/js/summernote-0.8.11/summernote-lite.js' />"></script>
<script src="<c:url value='/resource/cmm/js/summernote-0.8.11/lang/summernote-ko-KR.js' />"></script>

<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script src="<c:url value='/resource/cmm/js/utils.js' />"></script>
<script src="<c:url value='/resource/cmm/js/jquery_utils.js' />"></script>

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

				f.action = "<c:url value='/bbs/" + actionUrl + ".do' />?key=" + menuKey;
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

//목록 이동
function goList() {
	f.bbsUpperSn.value = '';
	f.bbsSn.value      = '';
	f.action           = "<c:url value='/bbs/list.do' />";
	f.submit();
}
</script>
<%-- <jsp:include page="/WEB-INF/jsp/common/post/popup_modal.jsp" /> --%>

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

<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
	<form:hidden path="pageIndex" />
	<form:hidden path="orderBy" />
	<form:hidden path="sc" />
	<form:hidden path="sw" />
	
	<input type="hidden" name="key"        value="${ param.key }" />
	<input type="hidden" name="uniqueId"   value="${ uniqueId }" />
	<input type="hidden" name="bbsSn"      value="${ item.bbsSn }" />
	<input type="hidden" name="bbsUpperSn" value="<c:if test="${ !empty reply }">${ reply.bbsSn }</c:if>" />
	
	<div class="board_write">
		<table class="table boardTable" style="table-layout:fixed;">
			<caption>
				<strong>게시글 작성</strong>
				<p>제목, 내용등을 입력해주세요.</p>
			</caption>
			<colgroup>
				<col width="16%" class="colgroup_pc">
				<col width="35%" class="colgroup_mo">
				<col>
			</colgroup>
			<tbody>
				<c:if test="${ bbsEstbs.noticeAt eq 'Y' && empty reply }">
					<tr>
						<th class="span3">공지글 여부</th>
						<td>
							<label class="radio-inline">
								<input title="일반글" name="noticeAt" id="noticeAtN" type="radio" value="N" <c:if test="${ item.noticeAt eq 'N' || empty item.noticeAt }">checked</c:if> data-parsley-ui-enabled="false" />
								일반글
							</label>
							<label class="radio-inline">
								<input title="공지글" name="noticeAt" id="noticeAtY" type="radio" value="Y" <c:if test="${ item.noticeAt eq 'Y' }">checked</c:if> data-parsley-ui-enabled="false" />
								공지글
							</label>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${ bbsEstbs.secretAt eq 'Y' && bbsEstbs.secretAtAlways eq 'N' }">
					<tr>
						<th class="span3">비밀글 여부</th>
						<td>
							<label>
								<input title="선택 시 비밀글" type="checkbox" name="secretAt" value="Y" <c:if test="${ item.secretAt eq 'Y' }">checked</c:if> />
								선택 시 비밀글
							</label>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${ empty userSession && empty item.registPw }">
					<tr>
						<th class="span3">비밀번호 *</th>
						<td>
							<input title="비밀번호를 입력해주세요." type="password" id="registPw" name="registPw" class="form-control" value="" placeholder="비밀번호 입력" data-parsley-required="true" style="width:50%;" />
						</td>
					</tr>
				</c:if>
				
				<c:if test="${ !empty fieldList }">
					<c:forEach var="list" items="${ fieldList }">
						<c:if test="${ list.bbsFieldCode ne 'registDe' && list.bbsFieldCode ne 'updtDe' && list.bbsFieldCode ne 'rdcnt' }">
							<c:choose>
								<c:when test="${ list.bbsFieldCode eq 'ctgryNm' }">
									<!-- 카테고리 -->
									<tr>
										<th class="span3">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if></th>
										<td>
											<select title="선택하세요." id="bbsCtgrySn" name="bbsCtgrySn" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>
												<option value="">선택</option>
												<c:if test="${ !empty cateList }">
													<c:forEach var="cate" items="${ cateList }">
														<option value="${ cate.bbsCtgrySn }" <c:if test="${ item.bbsCtgrySn eq cate.bbsCtgrySn }">selected</c:if>>${ cate.ctgryNm }</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
								</c:when>
								
								<c:when test="${ list.bbsFieldCode eq 'bbsCn' }">
									<!-- 게시판 내용 -->
									<c:if test="${ !empty reply }">
										<!-- 답변 -->
										<tr>
											<th class="span3">원본글</th>
											<td>
												${ xss:decode(reply.bbsCn) }
											</td>
										</tr>
									</c:if>
									
									<tr>
										<th class="span3">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if></th>
										<td>
											<textarea id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" style="width: 100%; height:250px;" title="${ list.bbsFieldNm } 입력" placeholder="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-required="true"</c:if>>${ item[list.bbsFieldCode] }</textarea>
										</td>
									</tr>
								</c:when>
								
								<c:when test="${ list.bbsFieldCode eq 'file' }">
									<!-- 첨부 -->
									<tr>
										<th class="span3">파일 첨부</th>
										<td>
											<!-- 멀티파일 업로드 -->
											<jsp:include page="/WEB-INF/jsp/cmm/file/doc_form.jsp" />
										</td>
									</tr>
									
									<tr>
										<th class="span3">파일 첨부 목록</th>
										<td>
											<div id="uploadFileList_doc"></div>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
								
									<tr>
										<th class="span3">${ list.bbsFieldNm } <c:if test="${ list.fieldEssntlAt eq 'Y' }">*</c:if></th>
										<td>
											<c:choose>
												<c:when test="${ list.fieldTy eq '1' }">
													<!-- 숫자만 입력 -->
													<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" title="${ list.bbsFieldNm } 입력" data-parsley-type="number" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
												</c:when>
												
												<c:when test="${ list.fieldTy eq '2' }">
													<!-- 한줄 입력 -->
													<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" title="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
												</c:when>
												
												<c:when test="${ list.fieldTy eq '3' }">
													<!-- 여러 줄 입력 -->
													<textarea id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" style="height:250px;" placeholder="${ list.bbsFieldNm } 입력" title="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>${ item[list.bbsFieldCode] }</textarea>
												</c:when>
												
												<c:when test="${ list.fieldTy eq '4' }">
													<!-- 단일 선택(radio) -->
													<c:set var="textArr" value="${ fn:split(list.fieldTyText, ',') }" />
													
													<c:if test="${ !empty textArr }">
														<c:forEach var="arr" items="${ textArr }" varStatus="i">
															<label class="" style="float:left; margin-right:20px;">
																<input title="${ arr }" type="radio" name="${ list.bbsFieldCode }" value="${ arr }" <c:if test="${ item[list.bbsFieldCode] eq arr || (empty item[list.bbsFieldCode] && i.first) }">checked</c:if> />
																${ arr }
															</label>
														</c:forEach>
													</c:if>
												</c:when>
												
												<c:when test="${ list.fieldTy eq '5' }">
													<!-- 단일 선택(select) -->
													<c:set var="textArr" value="${ fn:split(list.fieldTyText, ',') }" />
													
													<select title="선택하세요." id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if>>
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
																<input title="${ arr }" type="checkbox" name="${ list.bbsFieldCode }" value="${ arr }" <c:if test="${ item[list.bbsFieldCode] eq arr }">checked</c:if> />
																${ arr }
															</label>
														</c:forEach>
													</c:if>
												</c:when>
												
												<c:when test="${ list.fieldTy eq '7' }">
													<!-- 주소 검색 -->
													<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control" style="float:left; width:50%;" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" title="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true"</c:if> />
													<button type="button" class="btn btn-success" onclick="openForm_post('', '${ list.bbsFieldCode }', '');"><i class="fa fa-search"></i> 우편번호 검색</button>
												</c:when>
												
												<c:when test="${ list.fieldTy eq '8' }">
													<!-- 날짜 -->
													<div class="input-group">
														<input type="text" id="${ list.bbsFieldCode }" name="${ list.bbsFieldCode }" class="form-control datepicker" value="${ item[list.bbsFieldCode] }" placeholder="${ list.bbsFieldNm } 입력" title="${ list.bbsFieldNm } 입력" <c:if test="${ list.fieldEssntlAt eq 'Y' }">data-parsley-required="true" data-parsley-errors-container="#dateElement_${ list.bbsFieldCode }"</c:if> style="float:left; width:150px;" />
														<button type="button" class="btn btn-success" onclick="$('#${ list.bbsFieldCode }').val('');"><i class="fa fa-undo"></i> 초기화</button><br/>
														<span id="dateElement_${ list.bbsFieldCode }"></span>
													</div>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!--버튼-->
		<div class="btnArea">
			<a class="btn btn_list" href="#none" onclick="goList();">목록</a>
			<button type="submit" class="btn btn_write">저장</button>
		</div>
		<!--//버튼-->
	</div>
</form:form>
</body>
</html>