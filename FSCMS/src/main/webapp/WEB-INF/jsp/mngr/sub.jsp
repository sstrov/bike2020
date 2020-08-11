<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<c:if test="${ !empty currentMenu.menuNm }">${ currentMenu.menuNm } &minus;</c:if>
	${ siteSession.siteNm } 사이트 관리자
</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<link rel="stylesheet" href="/resource/mngr/css/fonts.css" />
<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/mngr/plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="/resource/mngr/css/animate.min.css" />
<link rel="stylesheet" href="/resource/mngr/css/style.css" />
<link rel="stylesheet" href="/resource/mngr/css/style-responsive.css" />

<script src="/resource/mngr/plugins/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>

	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed page-with-wide-sidebar">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-default navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-click="sidebar-toggled">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="/adm/index.do" class="navbar-brand">
						${ siteSession.siteNm } CMS 관리시스템
					</a>
				</div>
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<a href="#" data-toggle="nav-profile">
							<span class="image">
								<i class="fa fa-child fa-2x"></i>
							</span>
							<span class="info">
								${ admSession.mngrNm }
							</span>
						</a>
					</li>
				</ul>
				<!-- end sidebar user -->
				
				<ul class="nav" style="border-bottom:1px solid #ccc;">
					<li class="has-sub">
						<a href="/${ admPath }/index.do">
							<span>Dashboard</span>
						</a>
					</li>
					
					<li class="has-sub">
						<a href="#none" target="_self">
							<b class="caret pull-right"></b>
							<span>관리자 설정</span>
						</a>
						
						<ul class="sub-menu">
							<li class="">
								<a href="/${ admPath }/mngr/password/form.do" target="_self">
									비밀번호 변경
								</a>
								
							</li>
							<li class="">
								<a href="/${ admPath }/sso/logout.do" target="_self">
									로그아웃
								</a>
								
							</li>
						</ul>
						
					</li>
				</ul>

				<jsp:include page="/repository/mngr/menu/top_${ admSession.roleSn }.jsp" />
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<c:if test="${ !empty currentMenu.menuSn }">
					<jsp:include page="/repository/mngr/menu/navi_${ currentMenu.menuSn }.jsp" />
				</c:if>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<c:if test="${ !empty currentMenu.menuNm }">
				<h1 class="page-header">${ currentMenu.menuNm }</h1>
			</c:if>
			<!-- end page-header -->

			<decorator:body />
		</div>
		<!-- end #content -->

		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	
	<script src="/resource/mngr/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="/resource/mngr/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="/resource/mngr/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="/resource/mngr/crossbrowserjs/html5shiv.js"></script>
		<script src="/resource/mngr/crossbrowserjs/respond.min.js"></script>
		<script src="/resource/mngr/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="/resource/mngr/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="/resource/mngr/js/layout.js"></script>
	
	<decorator:head />
</body>
</html>