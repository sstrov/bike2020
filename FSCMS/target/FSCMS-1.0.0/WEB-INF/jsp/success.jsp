<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ msg }</title>
</head>
<body>
<c:choose>
	<c:when test="${ !empty formAt && formAt eq 'Y' }">
		<form:form id="frm" name="frm" method="post" commandName="searchVO">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>
		</form:form>
		
		<script>
			<c:if test="${ !empty msg }">
				alert('${ msg }');
			</c:if>
			var f = document.forms['frm'];
			f.action = "${ url }";
			f.submit();
		</script>
	</c:when>
	<c:otherwise>
		<form:form id="frm" name="frm" method="post"></form:form>
		<script>
			<c:if test="${ !empty msg }">
				alert('${ msg }');
			</c:if>
			<c:if test="${ !empty url }">
				var f = document.forms['frm'];
				f.action = "${ url }";
				f.submit();
			</c:if>
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>