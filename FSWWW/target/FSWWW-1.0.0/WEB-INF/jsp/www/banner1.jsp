<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ taglib uri="/WEB-INF/tld/fs-string.tld"                     prefix="string" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<script src="<c:url value='/' />/resource/cmm/js/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<c:if test="${ !empty bannerList1 }">
	<div class="popup-container active">
		<div class="content">
			<div class="pop-box">
				<div class="popup-slide">
					<c:forEach var="item" items="${ bannerList1 }" varStatus="i">
						<div class="item item-0${ i.count }">
							<a href="<c:url value='/' />${ !empty item.bannerItnadr? item.bannerItnadr : '#none' }">
								<c:if test="${ !empty item.atchmnflImage }">
									<div class="image-box"><img src="<c:url value='/' />${ item.flpth }/${ item.atchmnflImage }" alt=""></div>
								</c:if>
								
								${ item.tagCn }
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="popup-toggle-btn">
				<span>POPUP</span>
				<img src="<c:url value='/resource/static/image/common/popup-open.png' />" alt="팝업열기 버튼 아이콘">
			</div>
		</div>
	</div>
	<script src="<c:url value='/' />/resource/static/js/common/jquery-migrate-1.4.1.js"></script>
	<script src="<c:url value='/' />/resource/static/js/common/slick.min.js"></script>
	<script src="<c:url value='/' />/resource/static/js/main/popup.js"></script>
</c:if>
</body>
</html>