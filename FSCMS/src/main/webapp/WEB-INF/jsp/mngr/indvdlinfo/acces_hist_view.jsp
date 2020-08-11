<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
var f       = document.forms['frm'];
var menuKey = '${ param.key }';

// 목록 이동
function goList() {
	f.action = '/${ admURI }/indvdlinfo/acces/hist/list.do?key=' + menuKey;
	f.submit();
}
</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<!-- begin panel -->
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<h4 class="panel-title">상세정보 폼</h4>
			</div>
			<div class="panel-body panel-form">
				<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered">
					<c:if test="${ !empty searchVO.fieldList }">
						<c:forEach var="sec" items="${ searchVO.fieldList }">
							<form:hidden path="${ sec.name }" />
						</c:forEach>
					</c:if>
					
					<c:choose>
						<c:when test="${ !empty item.mngrId }">
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">관리자 아이디 :</label>
								<div class="col-md-6 col-sm-6" style="padding-top:22px;">
									${ item.mngrId }
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">사용자 아이디 :</label>
								<div class="col-md-6 col-sm-6" style="padding-top:22px;">
									${ item.userId }
								</div>
							</div>
						</c:otherwise>
					</c:choose>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">등록 일자 :</label>
						<div class="col-md-6 col-sm-6" style="padding-top:22px;">
							${ item.registDe }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">등록 정보 :</label>
						<div class="col-md-6 col-sm-6" style="padding-top:22px;">
							OS : ${ item.registPltfom }<br/>
							브라우저 : ${ item.registBrwsr }<br/>
							아이피 : ${ item.registIp }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">내용 :</label>
						<div class="col-md-6 col-sm-6" style="padding-top:22px;">
							${ item.accesHistCn }
						</div>
					</div>
					
					<c:if test="${ !empty item.dataChghstSn }">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">데이터 변경 이력 정보 :</label>
							<div class="col-md-6 col-sm-6">
								<button type="button" class="btn btn-success" onclick="document.location.href='/${ admURI }/data/chghst/view.do?key=1910117231656&dataChghstSn=${ item.dataChghstSn }';">변경정보 보기</button>
							</div>
						</div>
					</c:if>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>