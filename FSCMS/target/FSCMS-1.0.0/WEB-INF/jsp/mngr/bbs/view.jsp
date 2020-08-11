<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jstl/fmt"             prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"               prefix="string" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resource/adm/css/style.css">

<style type="text/css">
.panel-form label { padding-top:15px !important; }
#brdContent img { max-width:100%; }
</style>
<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script>
var f       = document.forms['frm'];
var menuKey = '${ param.key }';

<c:choose>
	<c:when test="${ item.deleteAt eq 'Y' }">
		// 정보 복원
		function onUse() {
			if(confirm("정보를 복원 하시겠습니까?")) {
				f.action = '/${ admURI }/bbs/use.do?key=' + menuKey;
				f.submit();
			}
		}
		
		// 완전 삭제
		function onDeleteAll() {
			if(confirm("정보를 완전삭제 하시겠습니까?\n삭제된 정보는 복구가 불가능 합니다.")) {
				f.action = '/${ admURI }/bbs/deleteAll.do?key=' + menuKey;
				f.submit();
			}
		}
	</c:when>
	<c:otherwise>
		//상세보기 이동
		function goModify() {
			f.action = '/${ admURI }/bbs/form.do?key=' + menuKey;
			f.submit();
		}
		
		// 정보 삭제
		function onDelete() {
			if(confirm("정보를 삭제 하시겠습니까?")) {
				f.action = '/${ admURI }/bbs/delete.do?key=' + menuKey;
				f.submit();
			}
		}
		
		<c:if test="${ bbsEstbs.answerAt eq 'Y' && item.noticeAt eq 'N' }">
			// 답변
			function goReply() {
				f.bbsUpperSn.value = f.bbsSn.value;
				f.bbsSn.value = '';
				f.action = '/${ admURI }/bbs/form.do?key=' + menuKey;
				f.submit();
			}
		</c:if>
	</c:otherwise>
</c:choose>

// 목록 이동
function goList() {
	f.bbsUpperSn.value = '';
	f.bbsSn.value = '';
	f.action = '/${ admURI }/bbs/list.do?key=' + menuKey;
	f.submit();
}
</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>

			<input type="hidden" name="bbsSn" value="${ item.bbsSn }" />
			<input type="hidden" name="bbsUpperSn" value="${ item.bbsSn }" />
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">상세보기 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<c:if test="${ item.noticeAt eq 'Y' }">
						<div class="form-group">
							<label class="control-label col-md-12 col-sm-12" style="text-align:left;"><i class="fa fa-bullhorn" style="font-size:13pt;"></i> 공지글 입니다.</label>
						</div>
					</c:if>
					
					<c:if test="${ !empty fieldList }">
						<c:forEach var="list" items="${ fieldList }">
							<c:choose>
								<c:when test="${ list.bbsFieldCode eq 'registerNm' }">
									<!-- 작성자 -->
									<div class="form-group">
										<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } :</label>
										<div class="col-md-6 col-sm-6">
											<c:choose>
												<c:when test="${ bbsEstbs.registerNmTy eq 'NM' }">
													${ item.registerNm }
												</c:when>
												<c:when test="${ bbsEstbs.registerNmTy eq 'ID' }">
													${ item.registerNm }
												</c:when>
												<c:when test="${ bbsEstbs.registerNmTy eq 'NMS' }">
													${ item.registerNm }
												</c:when>
												<c:when test="${ bbsEstbs.registerNmTy eq 'IDS' }">
													${ item.registerNm }
												</c:when>
											</c:choose>
										</div>
									</div>
								</c:when>
								
								<c:when test="${ list.bbsFieldCode eq 'file' }">
									<!-- 첨부파일 -->
									<div class="form-group">
										<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } :</label>
										<div class="col-md-6 col-sm-6">
											<c:if test="${ !empty fileList }">
												<c:forEach var="file" items="${ fileList }">
													<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1048576 }</fmt:formatNumber> MB</c:set>
													<c:if test="${ file.atchmnflSize / 1048576 le 0 }">
														<c:set var="atchmnflSize"><fmt:formatNumber pattern="0.00">${ file.atchmnflSize / 1024 }</fmt:formatNumber> KB</c:set>
													</c:if>
													
													<a href="/cmm/fileDown.do?key=${ file.atchmnflSn }&type=${ file.accTy }"><i class="fa fa-file"></i> ${ file.atchmnflNm } (${ atchmnflSize })</a><br/>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</c:when>
								
								<c:when test="${ list.bbsFieldCode eq 'bbsCn' }">
									<!-- 내용 -->
									<div id="brdContent" class="form-group">
										<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } :</label>
										<div class="col-md-6 col-sm-6">
											${ xss:decode(item[list.bbsFieldCode]) }
										</div>
									</div>
								</c:when>

								<c:otherwise>
									<div class="form-group">
										<label class="control-label col-md-4 col-sm-4">${ list.bbsFieldNm } :</label>
										<div class="col-md-6 col-sm-6">
											${ item[list.bbsFieldCode] }
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ item.deleteAt eq 'Y' }">
									<button type="button" class="btn btn-primary" onclick="onUse();">복원</button>
									<button type="button" class="btn btn-danger" onclick="onDeleteAll();">완전삭제</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-primary" onclick="goModify();">수정</button>
									<button type="button" class="btn btn-danger" onclick="onDelete();">삭제</button>
									<c:if test="${ bbsEstbs.answerAt eq 'Y' && item.noticeAt eq 'N' }">
										<button type="button" class="btn btn-grey" onclick="goReply();">답변</button>
									</c:if>
								</c:otherwise>
							</c:choose>
							
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