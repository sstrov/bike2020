<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jstl/fmt"             prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"               prefix="string" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/style.css" />
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/bootstrap.css" />
<!-- <link type="text/css" rel="stylesheet" href="/resource/cmm/js/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="/resource/cmm/js/bootstrap/css/bootstrap-theme.css" /> -->
<script src="/resource/cmm/js/bootstrap/js/bootstrap.min.js"></script>

<script src="/resource/cmm/js/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/cmm/js/parsley/dist/parsley.js"></script>
<script src="/resource/cmm/js/parsley/src/i18n/ko.js"></script>

<script src="/resource/cmm/js/dates.js"></script>
<script src="/resource/cmm/js/utils.js"></script>
<script src="/resource/cmm/js/jquery_utils.js"></script>
<script src="/resource/brd/fs_ra001/js/brd.js"></script>

<script>
var f       = document.forms['frm'];
var menuKey = '${ param.key }';

<c:if test="${ !empty msg }">
	alert('${ msg }');
</c:if>

<c:if test="${ powWrite }">
	<c:choose>
		<c:when test="${ !empty item.registPw }">
			//상세보기 이동
			function goModify_pw() {
				$('#cmntPw_form').modal('show')
				jQuery('#cmntPwFrm').validate({
					submitHandler: function(form) {
						
						$.ajax({
							url     : "/bbs/isExistPw.do?key=${ param.key }",
							type    : "POST",
							data    : {
								'bbsSn' : ${ item.bbsSn },
								'regPw'  : form.keyword.value
							},
							dataType: "json",
							error   : function(r, s, e) {
								alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
							},
							success:function(result) {
								var item = result[0];
								if(item.msg != '') {
									alert(item.msg);
									form.keyword.value = "";
									form.keyword.focus();
									return false;
								} else {
									f.action = '/bbs/form.do?key=${ param.key }';
									f.submit();
								}
							}
						});
					}
				});
			}
			
			// 정보 삭제
			function onDelete_pw() {
				$('#cmntPw_form').modal('show')
				jQuery('#cmntPwFrm').validate({
					submitHandler: function(form) {
						
						$.ajax({
							url     : "/bbs/isExistPw.do?key=${ param.key }",
							type    : "POST",
							data    : {
								'bbsSn' : ${ item.bbsSn },
								'regPw'  : form.keyword.value
							},
							dataType: "json",
							error   : function(r, s, e) {
								alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
							},
							success:function(result) {
								var item = result[0];
								if(item.msg != '') {
									alert(item.msg);
									form.keyword.value = "";
									form.keyword.focus();
									return false;
								} else {
									if(confirm("정보를 삭제 하시겠습니까?")) {
										f.action = '/bbs/delete.do?key=${ param.key }';
										f.submit();
									}
								}
							}
						});
					}
				});
			}
		</c:when>
		<c:otherwise>
			//상세보기 이동
			function goModify() {
				f.action = '/bbs/form.do?key=${ param.key }';
				f.submit();
			}
			
			// 정보 삭제
			function onDelete() {
				if(confirm("정보를 삭제 하시겠습니까?")) {
					f.action = '/bbs/delete.do?key=${ param.key }';
					f.submit();
				}
			}
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${ bbsEstbs.answerAt eq 'Y' && item.noticeAt eq 'N' }">
	// 답변
	function goReply() {
		f.bbsUpperSn.value = f.bbsSn.value;
		f.bbsSn.value = '';
		f.action = '/bbs/form.do?key=${ param.key }';
		f.submit();
	}
</c:if>

//목록 이동
function goList() {
	f.bbsUpperSn.value = '';
	f.bbsSn.value = '';
	f.action = '/bbs/list.do?key=${ param.key }';
	f.submit();
}
</script>
</head>
<body>
<div class="board_view">
	<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
		<form:hidden path="pageIndex" />
		<form:hidden path="orderBy" />
		<form:hidden path="sc" />
		<form:hidden path="sw" />
		
		<input type="hidden" name="bbsSn" value="${ item.bbsSn }" />
		<input type="hidden" name="bbsUpperSn" value="${ item.bbsSn }" />
		
		<table class="table boardTable" style="table-layout:fixed;">
			<caption>
				<strong>게시글 상세페이지</strong>
				게시글 상세페이지입니다.
			</caption>
			<colgroup>
				<col width="16%" class="colgroup_pc">
				<col width="35%" class="colgroup_mo">
				<col>
			</colgroup>
			<tbody>
				<c:if test="${ item.noticeAt eq 'Y' }">
					<tr>
						<th scope="row" class="span3" colspan="2"><img src="/resource/brd/fs_ra001/img/icon_notice.png" alt="중요 공지사항" /> 공지글 입니다.</th>
					</tr>
				</c:if>
				
				<c:if test="${ !empty fieldList }">
					<c:forEach var="list" items="${ fieldList }">
						<c:choose>
							<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
								<!-- 작성자 -->
								<tr>
									<th scope="row" class="span3">${ list.bbsFieldNm }</th>
									<td>
										<c:choose>
											<c:when test="${ bbsEstbs.registerNmTy eq 'NM' }">
												${ item.registerNm }
											</c:when>
											<c:when test="${ bbsEstbs.registerNmTy eq 'ID' }">
												<c:choose>
													<c:when test="${ !empty item.userId }">${ item.userId }</c:when>
													<c:when test="${ !empty item.mngrId }">${ item.mngrId }</c:when>
													<c:otherwise>${ item.registerNm }</c:otherwise>
												</c:choose>
											</c:when>
											<c:when test="${ bbsEstbs.registerNmTy eq 'NMS' }">
												${ item.registerNm }
											</c:when>
											<c:when test="${ bbsEstbs.registerNmTy eq 'IDS' }">
												<c:choose>
													<c:when test="${ !empty item.userId }">${ item.userId }</c:when>
													<c:when test="${ !empty item.mngrId }">${ item.mngrId }</c:when>
													<c:otherwise>${ item.registerNm }</c:otherwise>
												</c:choose>
											</c:when>
										</c:choose>
									</td>
								</tr>
							</c:when>
							
							<c:when test="${ list.bbsFieldCode eq 'file' }">
								<!-- 첨부파일 -->
								<c:if test="${ !empty fileList }">
									<tr>
										<th scope="row" class="span3">${ list.bbsFieldNm }</th>
										<td>
											<div class="case_cont">
												<c:forEach var="file" items="${ fileList }">
													<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1048576 }</fmt:formatNumber> MB</c:set>
													<c:if test="${ file.atchmnflSize / 1048576 le 0 }">
														<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1024 }</fmt:formatNumber> KB</c:set>
													</c:if>
													
													<a href="/cmm/fileDown.do?key=${ file.atchmnflSn }&type=${ file.accTy }">
														<img src="/resource/brd/fs_ra001/img/file/ico_${ file.atchmnflExtsn }.gif" onerror="this.src='/resource/brd/fs_ra001/img/file/ico_html.gif';" alt="${ file.atchmnflNm }">
														${ file.atchmnflNm } <c:if test="${ !empty file.atchmnflSize }">(${ atchmnflSize })</c:if>
													</a><br/>
												</c:forEach>
											</div>
										</td>
									</tr>
								</c:if>
							</c:when>
							
							<c:when test="${ list.bbsFieldCode eq 'bbsCn' }">
								<!-- 내용 -->
								<tr>
									<td id="brdContent" class="content" colspan="2">
										${ xss:decode(item[list.bbsFieldCode]) }
									</td>
								</tr>
							</c:when>
							
							<c:otherwise>
								<tr>
									<th scope="row" class="span3">${ list.bbsFieldNm }</th>
									<td>
										${ item[list.bbsFieldCode] }
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- 자세히보기_내용 -->
		
		<!-- 자세히보기_내용버튼 -->
		<div class="btnArea btnArea_ex">
			<c:if test="${ powWrite }">
				<c:choose>
					<c:when test="${ !empty item.registPw }">
						<a class="btn btn_list" href="#none" onclick="goModify_pw();">수정</a>
						<a class="btn btn_list" href="#none" onclick="onDelete_pw();">삭제</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn_list" href="#none" onclick="goModify();">수정</a>
						<a class="btn btn_list" href="#none" onclick="onDelete();">삭제</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			
			<c:if test="${ bbsEstbs.answerAt eq 'Y' && item.noticeAt eq 'N' }">
				<a class="btn btn_list" href="#none" onclick="goReply();">답변</a>
			</c:if>
			<a class="btn btn_write" href="#none" onclick="goList();">목록</a>
		</div>
		<!-- //자세히보기_내용버튼 -->
	</form:form>

	<!-- 댓글 -->
	<c:if test="${ bbsEstbs.cmAt eq 'Y' && item.noticeAt eq 'N' }">
		<jsp:include page="/WEB-INF/jsp/bbs/fs_ra001/cmnt.jsp" />
	</c:if>
	<!-- //댓글 -->
	
	<!-- 이전/다음 -->
	<table class="table table_nextprev mt60" style="table-layout: fixed;">
		<caption>
			<strong>이전다음 게시물보기</strong>
			<p>이전글과 다음글을 확인하실 수 있습니다.</p>
		</caption>
		<colgroup>
			<col width="16%" class="colgroup_pc">
			<col class="colgroup_mo">
		</colgroup>
		<tbody>
			<tr class="notice_next">
				<th scope="row">
					<c:choose>
						<c:when test="${ !empty prevItem }">
							<a href="/bbs/view.do?bbsSn=${ prevItem.bbsSn }&key=${ param.key }">이전글</a>
						</c:when>
						<c:otherwise>
							<a href="#none">이전글</a>
						</c:otherwise>
					</c:choose>
				</th>
				<td scope="row">
					<c:choose>
						<c:when test="${ !empty prevItem }">
							<a href="/bbs/view.do?bbsSn=${ prevItem.bbsSn }&key=${ param.key }">${ prevItem.bbsSj }</a>
						</c:when>
						<c:otherwise>
							<span style="color:#ccc;">이전글이 없습니다.</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr class="notice_prev">
				<th scope="row">
					<c:choose>
						<c:when test="${ !empty nextItem }">
							<a href="/bbs/view.do?bbsSn=${ nextItem.bbsSn }&key=${ param.key }">다음글</a>
						</c:when>
						<c:otherwise>
							<a href="#none">다음글</a>
						</c:otherwise>
					</c:choose>
				</th>
				<td scope="row">
					<c:choose>
						<c:when test="${ !empty nextItem }">
							<a href="/bbs/view.do?bbsSn=${ nextItem.bbsSn }&key=${ param.key }">${ nextItem.bbsSj }</a>
						</c:when>
						<c:otherwise>
							<span style="color:#ccc;">다음글이 없습니다.</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="modal fade cmntPw_form" id="cmntPw_form" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">비밀번호 확인</h5>
			</div>
			<div class="modal-body">
				<div class="tab-content padding-10" style="margin-bottom:0;">
					<div class="tab-pane fade in active" id="tab1">
						<form id="cmntPwFrm" name="cmntPwFrm" method="post" class="form-horizontal" data-parsley-validate="true">
							<input type="hidden" name="brdCmntKey" value="" />
							<input type="hidden" name="bbsSn" value="" />
					
							<!-- # 검색 Start -->
							<fieldset class="form-horizontal" style="margin-top:10px;">
							
								<div class="form-group">
									<div class="col-md-12">
										<table border="0" cellspacing="0" cellpadding="0" class="table" style="float:left; width:100%; border:0;">
											<colgroup>
												<col />
												<col width="20%" />
											</colgroup>
											<tr>
												<td style="padding:0; border:0;">
													<input title="비밀번호를 입력해주세요." type="password" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#element" placeholder="비밀번호 입력" />
													<span id="element"></span>
												</td>
												<td style="padding:0; border:0; vertical-align: top;">
													<button type="submit" class="btn btn-primary">확인</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							
							</fieldset>
						
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>