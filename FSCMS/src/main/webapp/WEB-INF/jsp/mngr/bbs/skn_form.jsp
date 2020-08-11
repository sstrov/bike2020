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
(function($){
	var orderFlag = "N";
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		orderFlag = "Y";
	</c:if>
	$.ed_jstree.settings({
		"jstreeObj" : "#jstree",
		"urlList"   : "/${ admURI }/bbs/skn/getList.do?key=${ param.key }",
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
			url     : "/${ admURI }/bbs/skn/setInsert.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'bbsSknSn' : targetKey,
				'bbsSknNm' : nodeName
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
			url     : "/${ admURI }/bbs/skn/setName.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'bbsSknSn' : nodeKey,
				'bbsSknNm' : nodeName
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
			url     : "/${ admURI }/bbs/skn/setOrder.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'bbsSknSn'     : nodeKey,
				'bbsSknUpperSn': pKey,
				'bbsSknOrdr'   : order
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
			url     : "/${ admURI }/bbs/skn/deletion.do?key=${ param.key }",
			type    : "POST",
			data    : {
				'bbsSknSn' : nodeKey
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
		
		var key   = menu.bbsSknSn;
		var name  = unhtmlspecialchars(menu.bbsSknNm);
		var useAt = menu.useAt;
		
		var icon = "{\"icon\" : \"/resource/mngr/img/tree/menu.gif\"}";

		if(useAt != "Y") {
			icon = "{\"icon\" : \"fa fa-times\"}";
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
			url  : "/${ admURI }/bbs/skn/getObj.do?key=${ param.key }",
			type : "POST",
			data : {
				"bbsSknSn" : nodeKey
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
					
					// 사용 설정
					if(result.useAt == "Y") $("#useAtY").click();		// 사용
					else                    $("#useAtN").click();		// 사용안함
					
					// 공통뷰 사용 설정
					if(result.cmmnViewAt == "Y") $("#cmmnViewAtY").click();		// 사용
					else                         $("#cmmnViewAtN").click();	// 사용안함
					
					$("#bbsSknSn").val(result.bbsSknSn);
					$("#bbsSknNm").val(unhtmlspecialchars(result.bbsSknNm));
					$("#bbsSknCode").val(unhtmlspecialchars(result.bbsSknCode));
					
					if(result.atchmnflImage != null && result.atchmnflImage.length > 0) {
						$("#skinImg").html('<img src="' + result.flpth + '/' + result.atchmnflImage + '" width="100%" alt="" /><button type="button" class="btn btn-primary btn-sm m-l-5" onclick="del_imgFile();">이미지 삭제</button>');
					} else {
						$("#skinImg").html("");
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
			if(isEmpty(form.bbsSknNm)) {
				alert("스킨명을 입력해 주세요.");
				$("#name").parent().parent().addClass("has-warning has-feedback");
				form.bbsSknNm.focus();
				return false;
			}
			
			if(confirm("저장 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);

				var key    = form.bbsSknSn.value;
				var name   = form.bbsSknNm.value;
				var code   = form.bbsSknCode.value;
				var viewAt = $("input:radio[name='cmmnViewAt']:checked").val();
				var useAt  = $("input:radio[name='useAt']:checked").val();
				
				lodingFixedOn("저장중 입니다.", 300, 180);
				$.ajax({
					url     : "/${ admURI }/bbs/skn/setInfo.do?key=${ param.key }",
					type    : "POST",
					data    : {
						'bbsSknSn'   : key,
						'bbsSknNm'   : name,
						'bbsSknCode' : code,
						'cmmnViewAt' : viewAt,
						'useAt'      : useAt
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
								} else {
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

<c:if test="${ !empty role && role.powW eq 'Y' }">
//파일 업로드
function __setFileData(f) {
	if(confirm("파일을 저장 하시겠습니까?")) {
		lodingFixedOn("업로드중 입니다.", 300, 180);

		f.action = "/cmm/file/imgUpload.do";
		f.target = "imgUploadIframe";
		f.submit();
	} else {
		f.reset();
	}
}

/**
 * 파일 업로드 핸들러
 */
var uploadHandler = function(uploadFile) {
	var f   = document.forms['imgFrm'];
	var src = uploadFile.relativePath + "/" + uploadFile.serverFileName;
	
	$.ajax({
		url      : "/${ admURI }/bbs/skn/setFile.do?key=${ param.key }",
		type     : "POST",
		dataType : "json",
		data     : {
			'bbsSknSn' : $('#bbsSknSn').val(),
			'fileNm'   : uploadFile.serverFileName,
			'filePath' : uploadFile.relativePath
		},
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			if(result != null && result.msg != null && result.msg.length > 0) {
				alert(result.msg);
				lodingOff(document.body);
				return false;
			}
			
			lodingOff(document.body);
			$("#skinImg").html('<img src="' + src + '" width="100%" alt="" /><button type="button" class="btn btn-primary btn-sm m-l-5" onclick="del_imgFile();">이미지 삭제</button>');
			f.reset();
		}
	});
}

// 파일 삭제
function del_imgFile() {
	if(confirm('이미지를 삭제 하시겠습니까?')) {
		lodingFixedOn("삭제중 입니다.", 300, 180);
		$.ajax({
			url      : "/${ admURI }/bbs/skn/setFile.do?key=${ param.key }",
			type     : "POST",
			dataType : "json",
			data     : {
				'bbsSknSn' : $('#bbsSknSn').val(),
				'fileNm'   : '',
				'filePath' : ''
			},
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					lodingOff(document.body);
					return false;
				}
				
				lodingOff(document.body);

				$("#skinImg").html("");
			}
		});
	}
}
</c:if>
</script>
</head>
<body>
<div class="row">

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
				<li id="tab_link2" class=""><a href="#tab2" data-toggle="tab">이미지</a></li>
			</ul>
	
			<div class="tab-content">
				
				<!-- # 기본설정 -->
				<div class="tab-pane fade in active" id="tab1">
					<form id="frm" name="frm" class="form-horizontal">
						<input type="hidden" id="bbsSknSn" name="bbsSknSn" />
						
						<div class="im-msg">* 표시항목은 필수입력항목입니다.</div>
						<div class="form-group">
							<label class="col-md-3 control-label">스킨명 *</label>
							<div class="col-md-9 col-sm-9">
								<input type="text" id="bbsSknNm" name="bbsSknNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="메뉴명 입력" maxlength="20">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">코드</label>
							<div class="col-md-9 col-sm-9">
								<input type="text" id="bbsSknCode" name="bbsSknCode" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="코드 입력" maxlength="20">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">공통뷰 사용여부 *</label>
							<div class="col-md-9 col-sm-9">
								<label class="control-label" style="margin-right:10px;">
									<input type="radio" id="cmmnViewAtY" name="cmmnViewAt" value="Y" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
									사용
								</label>
								<label class="control-label">
									<input type="radio" id="cmmnViewAtN" name="cmmnViewAt" value="N" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
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
				
				<!-- # 연결기능 -->
				<div class="tab-pane fade" id="tab2">
					<form id="imgFrm" name="imgFrm" method="post" class="form-horizontal" enctype="multipart/form-data">
						<input type="hidden" name="handlerName" value="uploadHandler" />
						<input type="hidden" name="upKey"       value="bbsSkn" />
						
						<div class="form-group">
							<label class="col-md-3 control-label">참고이미지</label>
							<div class="col-md-9">
								<input type="file" class="btn btn-default" name="uploadFile" onchange="chkFile(this, 'img', document.forms['imgFrm'], true);" />
								<p class="help-block">
									이미지(jpg, gif, png등)파일만 첨부가 가능 합니다.
								</p>
							</div>
						</div>
						
						<div class="form-group">
							<div id="skinImg" class="col-md-12"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<iframe id="imgUploadIframe" name="imgUploadIframe" style="width:100%; height:0; border:0;"></iframe>
</body>
</html>