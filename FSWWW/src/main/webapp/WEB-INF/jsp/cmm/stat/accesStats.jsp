<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script src="<c:url value='/resource/cmm/js/utils.js' />"></script>
<script>
$(window).load(function() {
	var statAt = getCookie("SITE_STAT");
	if(statAt == null || statAt != "Y") {
		$.ajax({
			url     : "<c:url value='/conectr/stats/insert.do' />",
			type    : "POST",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function() {
				SetCookie("SITE_STAT", "Y", new Date(getDatePlus(1)));
			}
		});
	}
});
</script>