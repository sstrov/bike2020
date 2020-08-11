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

//상세보기 이동
function goModify() {
	f.action = '/${ admURI }/push/form.do?key=' + menuKey;
	f.submit();
}

// 정보 삭제
function onDelete() {
	if(confirm("정보를 삭제 하시겠습니까?")) {
		f.action = '/${ admURI }/push/delete.do?key=' + menuKey;
		f.submit();
	}
}

// 목록 이동
function goList() {
	f.action = '/${ admURI }/push/list.do?key=' + menuKey;
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
							${ item.pushSj }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">작성자 * :</label>
						<div class="col-md-6 col-sm-6">
							${ registId }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">내용 :</label>
						<div class="col-md-6 col-sm-6">
							${ xss:decode(item.pushCn) }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">발송분류 :</label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ item.pushType eq 'S' }">SMS</c:if>
							<c:if test="${ item.pushType eq 'M' }">이메일</c:if>
						</div>
					</div>
					
						
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">이미지 첨부 목록 :</label>
						<div class="col-md-6 col-sm-6" id="uploadFileList_img">
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
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="button" class="btn btn-primary" onclick="goModify();">수정</button>
							<button type="button" class="btn btn-danger" onclick="onDelete();">삭제</button>
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