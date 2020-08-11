<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt"                   prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form"       prefix="form" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui"                prefix="ui" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"                     prefix="string" %>
<%@ taglib uri="/WEB-INF/tld/fs-str.tld"                        prefix="str" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                        prefix="xss" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/style.css" />
<link rel="stylesheet" href="/resource/brd/fs_ra001/css/bootstrap.css" />

<script src="/resource/cmm/js/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/cmm/js/parsley/dist/parsley.js"></script>
<script src="/resource/cmm/js/parsley/src/i18n/ko.js"></script>

<script src="/resource/cmm/js/dates.js"></script>
<script src="/resource/cmm/js/utils.js"></script>
<script src="/resource/cmm/js/jquery_utils.js"></script>
<script src="/resource/brd/fs_ra001/js/brd.js"></script>

<script>
// 보기 페이지 이동
function goView(key, stype) {
	<c:choose>
		<c:when test="${ powRead }">
			var f = document.forms['frm'];
			
			if(stype == 'Y') {
				f.bbsSn.value = key;
				$('#cmntPw_form').modal('show')
				jQuery('#cmntPwFrm').validate({
					submitHandler: function(form) {
						$.ajax({
							url     : "/bbs/isExistPw.do?key=${ param.key }",
							type    : "POST",
							data    : {
								'bbsSn' : f.bbsSn.value,
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
									f.method = "get";
									f.action = "/bbs/view.do";
									f.submit();
								}
							}
						});
					}
				});
			} else {
				f.method = "get";
				f.bbsSn.value = key;
				f.action = "/bbs/view.do";
				f.submit();
			}
		</c:when>
		<c:otherwise>
			alert("권한이 없습니다.");
		</c:otherwise>
	</c:choose>
}

<c:if test="${ !powList }">
	$(document).ready(function() {
		alert("권한이 없습니다.");
		history.back();
	});
</c:if>
/*
 * pagination 페이지 링크 function
 */
function fn_egov_link_page(pageNo){
	<c:if test="${ powList }">
		var f = document.forms['frm'];
	
		f.pageIndex.value = pageNo;
		f.action          = "/bbs/list.do";
		f.submit();
	</c:if>
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal">
	<input type="hidden" name="bbsSn">
	<input type="hidden" name="key" value="${ param.key }">
	
	<form:hidden path="pageIndex" />
	
	<jsp:include page="/repository/www/content/${ param.key }.jsp" />
	
	<div class="review">
		<ul>
			<c:forEach var="item" items="${ brdList }" varStatus="vs">
			<li>
				<a href="#none" onclick="goView('${ item.bbsSn }', '${ item.secretAt }');" style="cursor:pointer;" >${ item.bbsSj }</a>
				<p>
					${ xss:decode(item.bbsCn) }
				</p>
				<span>${ item.registDe }</span>
			</li>
			</c:forEach>
			<!-- <li>
				<a href="" >구글 플레이스토어에서 자전거행복나눔을 검색...[1]</a>
				<p>
					구글 플레이스토어에서 자전거행복나눔을 검색하면 자전거행복나눔 앱이 검색되는것이 아니라... “우리강이용 도우미”가 검색되네요... 왜 그런건가요?
				</p>
				<span>2020-05-26</span>
			</li>
			<li>
				<a href="" >구글 플레이스토어에서 자전거행복나눔을 검색...[1]</a>
				<p>
					구글 플레이스토어에서 자전거행복나눔을 검색하면 자전거행복나눔 앱이 검색되는것이 아니라... “우리강이용 도우미”가 검색되네요... 왜 그런건가요?
				</p>
				<span>2020-05-26</span>
			</li> -->
		</ul>
		<a href="" class="more_btn"><img src="./../../img/content/sub01/more_btn.png" alt="" /></a>
	</div>
</div><!-- div_sub_end -->

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
					
							<!-- # 검색 Start -->
							<fieldset class="form-horizontal" style="margin-top:10px;">
							
								<div class="form-group">
									<div class="col-md-12">
										<table border="0" cellspacing="0" cellpadding="0" style="float:left; width:100%; border:0;">
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