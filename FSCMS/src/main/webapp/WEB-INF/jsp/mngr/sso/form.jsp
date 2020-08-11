<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%-- ${ siteSession.siteNm }  --%>관리자 로그인 페이지</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<link rel="stylesheet" href="/resource/mngr/css/fonts.css" />
<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/mngr/css/style.min.css" />
</head>
<body>
<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<div id="page-container" class="fade">
	<div class="login bg-grey-900 animated fadeInDown">
		<div class="login-header">
			<div class="brand text-inverse">
				관리자 로그인
				<small class="p-l-5"><%-- ${ siteSession.siteNm }  --%>관리자</small>
			</div>
		</div>
		<div class="login-content">
			<form id="frm" name="frm" method="POST" class="margin-bottom-0">
				<input type="hidden" name="refer" value="${ refer }" />

				<div class="form-group m-b-20">
					<input type="text" id="userId" name="userId" class="form-control input-lg without-border inverse-mode" placeholder="아이디 입력" />
				</div>
				<div class="form-group m-b-20">
					<input type="password" id="userPw" name="userPw" class="form-control input-lg without-border inverse-mode" placeholder="비밀번호 입력" />
				</div>
				<div class="checkbox m-b-20">
					<label class="text-white">
						<input id="saveId" name="saveId" type="checkbox" /> 아이디 기억하기
					</label>
				</div>
				<div class="login-buttons">
					<button type="submit" class="btn btn-info btn-block btn-lg">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="/resource/mngr/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="/resource/mngr/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<!--[if lt IE 9]>
	<script src="/resource/mngr/crossbrowserjs/html5shiv.js"></script>
	<script src="/resource/mngr/crossbrowserjs/respond.min.js"></script>
	<script src="/resource/mngr/crossbrowserjs/excanvas.min.js"></script>
<![endif]-->

<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script>
$(document).ready(function() {
	var f = document.forms['frm'];

	$.when($('#page-loader').addClass('hide')).done(function() {
		$('#page-container').addClass('in');
	});

	$('#frm').validate({
		submitHandler: function(form) {
			
			$.ajax({
				url  : "/${ admURI }/sso/login.do",
				type : "POST",
				data : {
					userId : form.userId.value,
					userPw : form.userPw.value
				},
				dataType : "json",
				error : function(r, s, e) {
					alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
					//document.location.href = '/mngr/index.do';
				},
				success:function(result) {
					var item = result[0];
					if(item.msg != '') {
						if(item.msg.indexOf('중복') != -1) {
							if(confirm(item.msg)) {
								$.ajax({
									url  : "/${ admURI }/sso/login.do",
									type : "POST",
									data : {
										userId : form.userId.value,
										userPw : form.userPw.value,
										priAt  : "Y"
									},
									dataType : "json",
									error : function(r, s, e) {
										//alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
										document.location.href = '/${ admURI }/index.do';
									},
									success:function(result) {
										var item = result[0];
										if(item.msg != '') {
											alert(item.msg);
											return false;
										} else {
											if(form.saveId.checked) {
												SetCookie("LOGIN_ID_SAVE_ADM", $("#userId").val());
											}
											document.location.href = form.refer.value;
										}
									}
								});
							} else {
								return false;
							}
						} else {
							alert(item.msg);
							if(item.msg.indexOf('아이디') != -1) {
								$('#userId').val('');
								$('#userPw').val('');
								$('#userId').focus();
							} else {
								$('#userPw').val('');
								$('#userPw').focus();
							}
						}
					} else {
						if(form.saveId.checked) {
							SetCookie("LOGIN_ID_SAVE_ADM", $("#userId").val());
						}
						document.location.href = form.refer.value;
					}
					
					return false;
				}
			});
		},
		rules : {
			userId : { required:true },
			userPw : { required:true }
		},
		messages : {
			userId : { required:'필수 입력사항 입니다.' },
			userPw : { required:'필수 입력사항 입니다.' }
		}
	});
	
	var cid      = getCookie("LOGIN_ID_SAVE_ADM");
	var cookieId = cid;

	if(cookieId != null && cookieId.length > 0) {
		$('#saveId').prop('checked', true);
		$('#userId').val(cookieId);
		$('#userPw').focus();
	} else {
		$('#saveId').prop('checked', false);
		$('#userId').focus();
	}

});
</script>
</body>
</html>