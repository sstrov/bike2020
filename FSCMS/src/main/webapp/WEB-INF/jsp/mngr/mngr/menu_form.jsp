<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resource/mngr/plugins/jstree3.0/dist/themes/default/style.min.css">

<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/jstree3.0/dist/jstree.min.js"></script>
<script src="/resource/mngr/js/jstree.js"></script>

<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script src="/resource/mngr/js/cmm/jstree_utils.js"></script>
<style>
#tree_panel { float:left; margin-left:10px; width:400px; }
	#jstree { height:600px; border:1px solid silver; overflow-y:auto; }

#editor_panel { float:left; width:530px; margin-left:10px; }
#form-panel { float:left; width:100%; display:none; }
	.tab-content { background-color:#fff; border:1px solid #ddd; border-top:0; padding:10px; }
		.im-msg { float:left; width:100%; text-align:right; margin:5px 0; font-size:11px; font-weight:bold; color:red; }
</style>

<script>
var admURI  = "${ admURI }";
var menuKey = '${ param.key }';
(function($){
	var orderFlag = "N";
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		orderFlag = "Y";
	</c:if>
	$.ed_jstree.settings({
		"jstreeObj" : "#jstree",
		"urlList"   : "/${ admURI }/mngr/menu/getList.do?key=${ param.key }",
		"orderFlag" : orderFlag
	});
	
	// 로딩 이미지 호출
	lodingFixedOn("정보 조회중 입니다.", 300, 180);
	$.ed_jstree.getList();
	
	<c:if test="${ !empty role && role.powW eq 'Y' }">
	// 메뉴 정보 저장
	$.createNode = function(targetKey, node, nodeName, data) {
		lodingFixedOn("메뉴 생성중 입니다.", 300, 180);

		$.ajax({
			url     : "/${ admURI }/mngr/menu/setInsert.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'menuSn' : targetKey,
				'menuNm' : nodeName
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					lodingOff(document.body);
					return false;
				}
				
				if(result.key != null && result.key.length > 0) {
					data.instance.set_id(node, "menu_" + result.key);
				}

				lodingOff(document.body);
			}
		});
	};
	
	// 메뉴명 수정
	$.renameNode = function(nodeKey, nodeName) {
		lodingFixedOn("이름변경중 입니다.", 300, 180);

		$.ajax({
			url     : "/${ admURI }/mngr/menu/setName.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'menuSn' : nodeKey,
				'menuNm' : nodeName
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					lodingOff(document.body);
					return false;
				}
				
				lodingOff(document.body);

				$.treeClick();
			}
		});
	}
	
	// 순서 수정
	$.moveNode =  function(nodeKey, pKey, order) {
		lodingFixedOn("순서변경중 입니다.", 300, 180);

		$.ajax({
			url     : "/${ admURI }/mngr/menu/setOrder.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'menuSn'     : nodeKey,
				'menuUpperSn': pKey,
				'menuOrdr'   : order
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
				}
				lodingOff(document.body);
			}
		});
	};
	
	// 메뉴 정보 삭제
	$.deleteNode = function(node) {
		var nodeKey = node.id.replace('root_', '').replace('menu_', '');
	
		var ref = $("#jstree").jstree(true),
			sel = ref.get_selected();
	
		if(node.children != "") {
			if(confirm("하위메뉴가 존재합니다.\n삭제시 하위 메뉴도 삭제처리 됩니다.\n삭제 하시겠습니까?")) {
				$.deleteAll(ref, node, nodeKey);
			}
		} else {
			if(confirm("삭제 하시겠습니까?")) {
				$.deleteAll(ref, node, nodeKey);
			}
		}
		
		return false;
	};

	/*
	 * 정보 삭제
	 */
	$.deleteAll = function(ref, node, nodeKey) {
		lodingFixedOn("삭제중 입니다.", 300, 180);
		
		// 메뉴 삭제 하위 정보도 삭제
		$.ajax({
			url     : "/${ admURI }/mngr/menu/deletion.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'menuSn' : nodeKey
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					lodingOff(document.body);
					return false;
				}
				
				$("#jstree").jstree("deselect_all");
				// 레이아웃 활성 설정
				$("#menuTip1").css("display", "block");
				$("#form-panel").css("display", "none");
				ref.delete_node(node);
				
				lodingOff(document.body);
			}
		});
	}
	</c:if>
	
	// 메뉴 리스트 생성
	$.makeTreeFirst = function(selector) {
		if($(selector).children("ul").length == 0) {
			$("<ul></ul>").appendTo(selector);
		}
		
		selector = $("ul:first", selector);
		selector = $("<li data-jstree='{\"icon\" : \"/resource/mngr/img/tree/root.png\"}'> 전체메뉴</li>").attr("id", "root_").appendTo(selector);
		$(selector).attr("class", "open");
		return selector;
	};
	
	$.makeTree = function(selector, menu) {
		if($(selector).children("ul").length == 0) {
			$("<ul></ul>").appendTo(selector);
		}

		selector = $("ul:first", selector);
		
		var key   = menu.menuSn;
		var name  = unhtmlspecialchars(menu.menuNm);
		var actAt = menu.actvtyAt;
		var useAt = menu.useAt;
		
		var icon = "{\"icon\" : \"/resource/mngr/img/tree/menu.gif\"}";

		if(useAt != "Y") {
			icon = "{\"icon\" : \"fa fa-times\"}";
		} else if(actAt == 'N') {
			icon = "{\"icon\" : \"fa fa-eye-slash\"}";
		} else {
			if(menu.childList != null && menu.childList.length > 0) {
				icon = "";
			}
		}
		
		selector = $("<li data-jstree='" + icon + "'>" + name + "</li>").attr("id", "menu_" + key).appendTo(selector);
		
		// 선택된 메뉴의 하위 메뉴 정보 추출
		if(menu.childList != null && menu.childList.length > 0) {
			$(selector).attr("class", "open");
			for(var i=0; i<menu.childList.length; i++) {
				$.makeTree(selector, menu.childList[i]);
			}
		}
	};
	
	// 메뉴 선택 정보 출력
	$.treeClick = function() {
		if($("#jstree").jstree("get_selected") == "" || $("#jstree").jstree("get_selected") == "root_") {
			// 레이아웃 활성 설정
			$("#menuTip1").css("display", "block");
			$("#form-panel").css("display", "none");
			return false;
		}
		
		// 관리자 메뉴 Key
		var id = "" + $("#jstree").jstree("get_selected");
		var nodeKey = id.split("_")[1];
		
		lodingFixedOn("메뉴 조회중 입니다.", 300, 180);
		
		// 선택된 관리자 메뉴 조회
		$.ajax({
			url  : "/${ admURI }/mngr/menu/getObj.do?key=${ param.key }",
			type : "POST",
			data : {
				"menuSn" : nodeKey
			},
			dataType : "json",
			error : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null) {
					if(result != null && result.msg != null && result.msg.length > 0) {
						alert(result.msg);
						lodingOff(document.body);
						return false;
					}
					
					// 연결방법에 따른 페이지 설정
					changePage(result.menuCnncTy);
					
					// 활성 설정
					if(result.actvtyAt == "Y") $("#actvtyAtY").click();		// 사용
					else                       $("#actvtyAtN").click();		// 사용안함

					// 사용 설정
					if(result.useAt == "Y") $("#useAtY").click();		// 사용
					else                    $("#useAtN").click(); 		// 사용안함
					
					if(result.menuCnncTy == "3") {
						// 게시판
						$('#bbsEstbsSn').val(result.accSn);
						$('#bbsEstbsNm').val(unhtmlspecialchars(result.accNm));
					} else {
						$('#bbsEstbsSn').val('');
						$('#bbsEstbsNm').val('');
					}

					$("#menuSn").val(result.menuSn);
					$("#menuNm").val(unhtmlspecialchars(result.menuNm));
					$("#menuCnncTy").val(result.menuCnncTy);
					$("#menuItnadr").val(unhtmlspecialchars(result.menuItnadr));
					$("#menuItnadrTy").prop('checked', (result.menuItnadrTy == 'Y')? true : false);
					$("#menuTrgt").val((result.menuTrgt != null && result.menuTrgt.length > 0)? result.menuTrgt : "_self");
					$("#menuParamtr").val(unhtmlspecialchars(result.menuParamtr));

					if(result.menuLink != null) {
						$("#realUrl").html(result.menuLink + '<br/><a href="' + result.menuLink + '" class="btn btn-primary btn-sm"><i class="fa fa-link"></i> 링크 바로가기</a>');
					} else {
						$("#realUrl").html('/${ admURI }/sub.do?key=' + result.menuSn + '<br/><a href="/${ admURI }/sub.do?key=' + result.menuSn + '" class="btn btn-primary btn-sm"><i class="fa fa-link"></i> 링크 바로가기</a>');
					}

					// 1번텝으로 이동
					$(".tab-pane, .nav-tabs li").removeClass("in");
					$(".tab-pane, .nav-tabs li").removeClass("active");
					$("#tab1, #tab_link1").addClass("in active");

					// 레이아웃 활성 설정
					$("#menuTip1").css("display", "none");
					$("#form-panel").css("display", "block");
				}
				
				lodingOff(document.body);
			}
		});
	};
})(jQuery);

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
	// 메뉴 정보 저장
	$("#frm").validate({
		submitHandler: function(form) {
			// 아이디 체크
			if(isEmpty(form.menuNm)) {
				alert("메뉴명을 입력해 주세요.");
				$("#name").parent().parent().addClass("has-warning has-feedback");
				form.menuNm.focus();
				return false;
			}
			
			// 링크 Url시 URL 등록 체크
			if(form.menuCnncTy.value == "1") {
				if(isEmpty(form.menuItnadr)) {
					alert("URL을 입력해 주세요.");
					$("#menuItnadr").parent().parent().addClass("has-warning has-feedback");
					form.menuItnadr.focus();
					return false;
				}
				if(isEmpty(form.menuTrgt)) {
					alert("타겟을 입력해 주세요.");
					form.menuTrgt.focus();
					return false;
				}
			}

			if(confirm("저장 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);

				var key     = form.menuSn.value;
				var name    = form.menuNm.value;
				var page    = $("#menuCnncTy").val();
				var url     = form.menuItnadr.value;
				var urlType = (form.menuItnadrTy.checked)? 'Y' : 'N';
				var target  = $("#menuTrgt").val();
				var param   = form.menuParamtr.value;
				var actAt   = $("input:radio[name='actvtyAt']:checked").val();
				var useAt   = $("input:radio[name='useAt']:checked").val();
				
				var accKey = "";
				if(page == '3') {
					// 게시판
					accKey = form.bbsEstbsSn.value;
				}
				
				lodingFixedOn("저장중 입니다.", 300, 180);
				$.ajax({
					url     : "/${ admURI }/mngr/menu/setInfo.do?key=${ param.key }",
					type    : "POST",
					data    : {
						'menuSn'      : key,
						'accSn'       : accKey,
						'menuNm'      : name,
						'menuCnncTy'  : page,
						'menuItnadr'  : url,
						'menuItnadrTy': urlType,
						'menuTrgt'    : target,
						'menuParamtr' : param,
						'actvtyAt'    : actAt,
						'useAt'       : useAt
					},
					dataType : "json",
					error   : function(request, status, error) {
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						$('button[type="submit"]').prop('disabled', false);
						lodingOff(document.body);
					},
					success:function(result) {
						if(result != null && result.msg != null && result.msg.length > 0) {
							alert(result.msg);
							$('button[type="submit"]').prop('disabled', false);
							lodingOff(document.body);
							return false;
						}
						
						$("#menu_" + key + " a").each(function(idx, item) {
							if(idx == 0) {
								var cls = "jstree-icon jstree-themeicon";
								var style = "";

								if(useAt != "Y") {
									cls = "jstree-icon jstree-themeicon fa fa-times jstree-themeicon-custom";
								} else if(actAt != "Y") {
									cls = "jstree-icon jstree-themeicon fa fa-eye-slash jstree-themeicon-custom";
								}else {
									if($("#menu_" + key).find("ul").length == 0) {
										cls = "jstree-icon jstree-themeicon jstree-themeicon-custom";
										style = "background-image:url('/resource/mngr/img/tree/menu.gif'); background-position-x:center; background-position-y:center; background-size:auto;";
									}
								}
								
								$(this).html("<i class=\"" + cls + "\" style=\"" + style + "\"></i>" + name);
								
								// 텍스트 정보 저장
								var ref = $("#jstree").jstree(true),
								sel = ref.get_selected();
								
								sel = sel[0];
								ref.set_text(sel, name);
							}
						});

						$('button[type="submit"]').prop('disabled', false);
						alert("저장 하였습니다.");
						$.treeClick();
						lodingOff(document.body);
					}
				});
			}
			
			return false;
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element.parent());
		}
	});
	</c:if>
});

/**
 * 연결방법 변경시
 */
function changePage(v) {
	switch(v) {
	// 빈메뉴, 콘텐츠
	case "0":
	case "2":
		$(".menu-link").hide();
		$(".menu-brdMng").hide();
		break;
	// 링크Url
	case "1":
		$(".menu-link").show();
		$(".menu-brdMng").hide();
		break;
	// 게시판
	case "3":
		$(".menu-link").hide();
		$(".menu-brdMng").show();
		break;
	}
}

<c:if test="${ !empty role && role.powW eq 'Y' }">
//사이트 배치
function setSiteRecycle() {
	if(confirm("사이트 배치를 진행 하겠습니까?")) {
		lodingFixedOn("처리중 입니다.", 300, 180);
		
		$.ajax({
			url     : "/${ admURI }/mngr/menu/setBatch.do?key=${ param.key }",
			type    : "POST",
			dataType : "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				lodingOff(document.body);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					$('button[type="submit"]').prop('disabled', false);
					lodingOff(document.body);
					return false;
				}
				
				alert("사이트 배치가 완료 되었습니다.");
				lodingOff(document.body);
			}
		});
	}
}
</c:if>
</script>

<c:if test="${ !empty role && role.powW eq 'Y' }">
	<jsp:include page="/WEB-INF/jsp/mngr/bbs/inc_form.jsp" />
</c:if>
</head>
<body>
<div class="row">

	<c:if test="${ !empty role && role.powW eq 'Y' }">
	<div class="col-md-12">
		<!-- begin panel -->
		<div class="panel panel-inverse" data-sortable-id="form-stuff-1">
			<div class="panel-heading">
				<h4 class="panel-title">설정</h4>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<div class="col-md-12">
						<button type="button" class="btn btn-info" onclick="setSiteRecycle();"><i class="fa fa-recycle"></i> 사이트 배치</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</c:if>

	<!-- # 메뉴 트리 -->
	<div id="tree_panel">
		<div class="div_def btn-group btn-group-justified">
			<div class="btn-group">
				<button id="menu_create" type="button" class="btn btn-default" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>><i class="fa fa-plus"></i> 추가</button>
			</div>
			<div class="btn-group">
				<button id="menu_rename" type="button" class="btn btn-default" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>><i class="fa fa-pencil"></i> 이름변경</button>
			</div>
			<div class="btn-group">
				<button id="menu_close" type="button" class="btn btn-default"><i class="fa fa-sort"></i> 펼침/닫힘</button>
			</div>
		</div>
		<!-- # 메뉴 정보 트리 -->
		<div id="jstree" class="div_def"></div>
		
		<div class="div_def btn-group btn-group-justified">
			<div class="btn-group">
				<button id="menu_deselect" type="button" class="btn btn-default"><i class="fa fa-eye-slash"></i> 선택해제</button>
			</div>
			<div class="btn-group">
				<button id="menu_remove" type="button" class="btn btn-default" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>><i class="fa fa-trash-o"></i> 선택삭제</button>
			</div>
		</div>
	</div>
	
	<div id="editor_panel">
		<!-- # 메뉴 정보 -->
		<div id="menuTip1" class="alert alert-info">
			<p>왼쪽트리메뉴에서 메뉴를 <strong>선택</strong>하시면 해당메뉴의 정보를 조회/관리하실수 있습니다.</p>
			<p><strong>드래그앤드랍</strong>으로 선택된 메뉴를 이동시킬수 있습니다.</p>
			<p><strong>최대 Depth제한 없이</strong> 메뉴를 추가 하실수 있습니다.</p>
		</div>
	
		<!-- # 설정 영역 -->
		<div id="form-panel">
			<ul class="nav nav-tabs">
				<li id="tab_link1" class="active"><a href="#tab1" data-toggle="tab">기본설정</a></li>
			</ul>
	
			<div class="tab-content">
				
				<!-- # 기본설정 -->
				<div class="tab-pane fade in active" id="tab1">
					<form id="frm" name="frm" class="form-horizontal">
						<input type="hidden" id="menuSn" name="menuSn" />
						
						<div class="im-msg">* 표시항목은 필수입력항목입니다.</div>
						<div class="form-group">
							<label class="col-md-3 control-label">메뉴명 *</label>
							<div class="col-md-9 col-sm-9">
								<input type="text" id="menuNm" name="menuNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="메뉴명 입력" maxlength="20">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">연결 *</label>
							<div class="col-md-9 col-sm-9">
								<label class="select select-inline">
									<select id="menuCnncTy" name="menuCnncTy" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onchange="changePage(this.value);">
										<option value="0">빈메뉴</option>
										<option value="1">링크Url</option>
										<option value="3">게시판</option>
									</select>
								</label>
								
								<!-- # 링크Url -->
								<label class="input input-inline menu-link">
									<input type="text" id="menuItnadr" name="menuItnadr" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="URL 입력" maxlength="200">
								</label>
								
								<label class="select select-inline menu-link">
									<select id="menuTrgt" name="menuTrgt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
										<option value="_self">현재창</option>
										<option value="_blank">새창</option>
									</select>
								</label>

								<label class="input input-inline menu-link">
									<input type="checkbox" id="menuItnadrTy" name="menuItnadrTy" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									내부링크 여부(선택 시 링크 앞 '/${ admURI }', 뒤 '/메뉴일련번호' 추가)
								</label>
								
								<div class="menu-brdMng">
									<input type="hidden" id="bbsEstbsSn" name="bbsEstbsSn" value="">
									<input type="text" id="bbsEstbsNm" name="bbsEstbsNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="" placeholder="게시판 검색" style="float:left; width:250px;" readonly>
									<button type="button" class="btn btn-success" onclick="openForm_brd();"><i class="fa fa-search"></i> 게시판 검색</button>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">파라미터</label>
							<div class="col-md-9 col-sm-9">
								<input type="text" id="menuParamtr" name="menuParamtr" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="파라미터 입력" maxlength="200">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">실제 연결URL</label>
							<div class="col-md-9 col-sm-9 pt_7" id="realUrl" style="padding-top:7px;"></div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">활성여부 *</label>
							<div class="col-md-9 col-sm-9">
								<label class="control-label" style="margin-right:10px;">
									<input type="radio" id="actvtyAtY" name="actvtyAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									사용
								</label>
								<label class="control-label">
									<input type="radio" id="actvtyAtN" name="actvtyAt" value="N" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									사용안함
								</label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">사용여부 *</label>
							<div class="col-md-9 col-sm-9">
								<label class="control-label" style="margin-right:10px;">
									<input type="radio" id="useAtY" name="useAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									사용
								</label>
								<label class="control-label">
									<input type="radio" id="useAtN" name="useAt" value="N" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									사용안함
								</label>
							</div>
						</div>
						
						<c:if test="${ !empty role && role.powW eq 'Y' }">
							<div class="panel-footer text-right">
								<button type="submit" class="btn btn-primary btn-sm m-l-5">
									<i class="fa fa-save"></i> 저장
								</button>
							</div>
						</c:if>
					
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>