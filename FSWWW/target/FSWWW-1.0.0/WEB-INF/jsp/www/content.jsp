<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table style="width:100%; table-layout:fixed;">
	<tbody>
		<tr>
			<td style="border:0; padding:0;">
				<div id="contentHtml" style="float:left; width:100%;">
					<jsp:include page="/repository/www/content/${ param.key }.jsp" />
				</div>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>