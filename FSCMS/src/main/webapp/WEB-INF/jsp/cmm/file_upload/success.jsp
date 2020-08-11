<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>파일업로드 완료처리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:choose>
	<c:when test="${ empty uploadFile }">
		<script type="text/javascript">
			alert("${ msg }");
			<c:if test="${ target eq 'opener'}">
				self.close();
			</c:if>
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			function UploadFile(fileName, serverFileName, relativePath, fileSize, fileExt, fileType) {
				this.fileName       = fileName;
				this.serverFileName = serverFileName;
				this.relativePath   = relativePath;
				this.fileSize       = fileSize;
				this.fileExt        = fileExt;
				this.fileType       = fileType;
			}
			var uploadFile = new UploadFile("${ uploadFile.fileName }", "${ uploadFile.serverFileName }", "${ uploadFile.relativePath }", "${ uploadFile.fileSize }", "${ uploadFile.fileExt }", "${ uploadFile.fileType }");

			if (!${ target }.${ handlerName }) {
				// [XXX]파일을 처리할 핸들러가 존재하지 않습니다.
				alert("[${ handlerName }]파일을 처리할 핸들러가 존재하지 않습니다.");
				<c:if test="${ target eq 'opener'}">
					self.close();
				</c:if>
			} else {
				${ target }.${ handlerName }(uploadFile);
				<c:if test="${ target eq 'opener'}">
					self.close();
				</c:if>
			}
		</script>
	</c:otherwise>
</c:choose>
</head>
</html>