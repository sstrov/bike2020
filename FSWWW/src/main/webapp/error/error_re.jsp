<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${ !empty messageCode }">
	<c:set var="pageTitle"><spring:message code="${ messageCode }" arguments="${ exception.message }" /></c:set>
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="format-detection" content="telephone=no,email=no,address=no">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<meta name="author" content="">
<meta name="Keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="">
<title>${ !empty pageTitle? pageTitle : '요청하신 페이지를 찾을 수 없습니다.' }</title>

<script src="/resource/mngr/plugins/jquery/jquery-1.9.1.min.js"></script>
<script>
var msg      = "${ pageTitle }";
var backFlag = "${ backFlag }";

if(msg != null && msg != "") {
	alert(msg);
}

if(backFlag == "Y") {
	//history.back();
}

$(window).on("load", function() {
	document.location.href = "/error/error.html;
});
</script>
</head>
<body>
</body>
</html>