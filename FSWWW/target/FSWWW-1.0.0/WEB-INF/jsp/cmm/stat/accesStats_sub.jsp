<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script src="<c:url value='/resource/cmm/js/utils.js' />"></script>
<script>
var menuKey = "${ param.key }";

$(window).load(function() {
	if(menuKey != null && menuKey != "") {
		var statAt = getCookie("SITE_STAT_MENU_" + menuKey);
		if(statAt == null || statAt != "Y") {
			$.ajax({
				url     : "<c:url value='/conectr/stats/menu/insert.do' />",
				type    : "POST",
				data    : {
					"menuSn" : menuKey
				},
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
				success:function() {
					SetCookie("SITE_STAT_MENU_" + menuKey, "Y", new Date(getDatePlus(1)));
				}
			});
		}
	}
});
</script>