<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt"             prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"               prefix="string" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/acego.bootstrap.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/fonts/font-awesome.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/acgeo.board.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/skin.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/slick.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/common.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/sub-common.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/common/table.css' />">
<link rel="stylesheet" href="<c:url value='/resource/static/css/C-statisticalPublication/C2-board.css' />">
<script src="<c:url value='/resource/cmm/js/bootstrap/js/bootstrap.min.js' />"></script>

<script src="<c:url value='/resource/cmm/js/jquery-validate/jquery.validate.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/dist/parsley.js' />"></script>
<script src="<c:url value='/resource/cmm/js/parsley/src/i18n/ko.js' />"></script>

<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script src="<c:url value='/resource/cmm/js/utils.js' />"></script>
<script src="<c:url value='/resource/cmm/js/jquery_utils.js' />"></script>
<script src="<c:url value='/resource/brd/fs_ra001/js/brd.js' />"></script>

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
							url     : "<c:url value='/bbs/isExistPw.do' />?key=${ param.key }",
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
									f.action = "<c:url value='/bbs/form.do' />?key=${ param.key }";
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
							url     : "<c:url value='/bbs/isExistPw.do' />?key=${ param.key }",
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
										f.action = "<c:url value='/bbs/delete.do' />?key=${ param.key }";
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
				f.action = "<c:url value='/bbs/form.do' />?key=${ param.key }";
				f.submit();
			}
			
			// 정보 삭제
			function onDelete() {
				if(confirm("정보를 삭제 하시겠습니까?")) {
					f.action = "<c:url value='/bbs/delete.do' />?key=${ param.key }";
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
		f.action = "<c:url value='/bbs/form.do' />?key=${ param.key }";
		f.submit();
	}
</c:if>

//목록 이동
function goList() {
	f.bbsUpperSn.value = '';
	f.bbsSn.value = '';
	f.action = "<c:url value='/bbs/list.do' />?key=${ param.key }";
	f.submit();
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<form:hidden path="pageIndex" />
	<form:hidden path="orderBy" />
	<form:hidden path="sc" />
	<form:hidden path="sw" />
	
	<input type="hidden" name="bbsSn" value="${ item.bbsSn }" />
	<input type="hidden" name="bbsUpperSn" value="${ item.bbsSn }" />
	
	<div class="program--contents">
		<div class="ui bbs--view">
			<div class="ui bbs--view--header">
				<h2 class="ui bbs--view--tit">
					${ item.bbsSj }
				</h2>
				<span><i>작성자</i>${ item.registerNm }&nbsp;</span>
				<span class="inq_cnt">조회수: ${ item.rdcnt }</span>
				<span class="date"><i>등록일</i>${ fn:substring(item.registDe, 0, 10) }</span>
			</div>
			<c:if test="${ !empty fileList }">
				<div class="ui bbs--view--file">
					<c:forEach var="file" items="${ fileList }">
						<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1048576 }</fmt:formatNumber> MB</c:set>
						<c:if test="${ file.atchmnflSize / 1048576 le 0 }">
							<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1024 }</fmt:formatNumber> KB</c:set>
						</c:if>
						
						<a href="<c:url value='/cmm/fileDown.do' />?key=${ file.atchmnflSn }&type=${ file.accTy }" class="btn btn-file btn-on-ico">
							<i class="ir ir-bbs ir-file left ir-${ file.atchmnflExtsn }"></i>
							${ file.atchmnflNm } <c:if test="${ !empty file.atchmnflSize }">[${ atchmnflSize }]</c:if>
						</a><br/>
					</c:forEach>
				</div>
			</c:if>
			<div class="ui bbs--view--cont" data-text-content="true">
				<div class="ui bbs--detail--cont">
					<div class="ui bbs--view--content">
						${ xss:decode(item.bbsCn) }
					</div>
					<div class="text-center">
					</div>
				</div>
			</div>
			<div class="kogl-mask">
				<h3 class="kogl-title mobile-hidden">
					<img src="<c:url value='/resource/static/image/common/kogl-title-img.png' />" alt="공공저작물 자유이용 허락표시">
					<em>공공저작물 자유이용 허락 표시</em>
				</h3>
				<div class="inner">
					<img class="kogl-mark-icon" src="<c:url value='/resource/static/image/common/icon_open-m.png' />" alt="공공누리 4유형(공공저작물 자유이용허락-출처표시, 상업용금지, 변경금지)"></img>
					<div class="kogl--text">
						<a href="#this" target="_blank" title="새창열림"><span><i>2019년 9월
									세종통계월보</i> 저작물은 공공누리 출처표시 조건에 따라 <b>공공누리 4유형</b> <em> 출처표시 + 상업적 이용금지 + 변경금지</em>이용할 수
								있습니다.</span></a>
					</div>
				</div>
			</div>
		</div>
		<div class="box-footer">
			<div class="pull-right">
				<c:if test="${ powWrite }">
					<c:choose>
						<c:when test="${ !empty item.registPw }">
							<button type="button" class="btn btn-default btn-sm btn-list" onclick="goModify_pw();">수정</button>
							<button type="button" class="btn btn-default btn-sm btn-list" onclick="onDelete_pw();">삭제</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-default btn-sm btn-list" onclick="goModify();">수정</button>
							<button type="button" class="btn btn-default btn-sm btn-list" onclick="onDelete();">삭제</button>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:if test="${ bbsEstbs.answerAt eq 'Y' && item.noticeAt eq 'N' }">
					<button type="button" class="btn btn-default btn-sm btn-list" onclick="goReply();">답변</button>
				</c:if>
			</div>
			<button type="button" class="btn btn-default btn-sm btn-list" onclick="goList();"><i class="fa fa-list-ul" aria-hidden="true"></i>목록</button>
		</div>
	</div>
</form:form>


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
													<input title="비밀번호를 입력하세요." type="password" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#element" placeholder="비밀번호 입력" />
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