<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	.scrl { overflow-y:auto; overflow-x:hidden; }
</style>

<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script type="text/javascript">
(function($){
	$.getList_auth = function(v) {
		var meeting = document.forms['authFrm'];
		var p = (v == '0')? 1 : v;
		
		authFrm.pageIndex.value = p;
		
		
		$.ajax({
			url     : "/${ admURI }/auth/upload/getNoteList.do?key=${ param.key }",
			type    : "POST", 
			data    : $("#authFrm").serialize(),
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				
				var tmpStr = "\n\t<tr>" +
				"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
				"\n\t</tr>";
				$("#authList").html(tmpStr);
			},
			success:function(result) {

				var tmpStr = "";
				if(result != null && result.length > 0) {
					tmpStr = "";
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						//var noteSn = item.noteSn;
						tmpStr += "<tr>"
						+ "<td>"+item.noteSn+"</td>"
						+ "<td><input type=\"text\" name=\"cName\" value=\"" + item.userNm + "\"></td>" 
						+ "<td><button type=\"button\" onclick=\"saveAuth('" + item.auSn + "', '" + i + "', '" + item.noteSn + "', '" + item.userNm +"' );\" class=\"btn btn-success\"> 수정</button></td>"
						+ "</tr>"
					}
				} else {
					tmpStr = "\n\t<tr>" +
					"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">검색된 정보가 없습니다.</td>" +
					"\n\t</tr>";
					
					// 페이징 처리
					$.getPaging(0);
				}
				
				$("#authList").html(tmpStr);
			}
		});
	};
	
})(jQuery);

$(document).ready(function() {
	$('#auth_form').dialog({
		autoOpen : false,
		width    : 700,
		resizable: false,
		modal    : true,
		title    : "인증정보 변경",
		draggable: true,
		drag: function(event, ui) {
			var fixPix = $(document).scrollTop();
			iObj = ui.position;
			iObj.top = iObj.top - fixPix;
			$(this).closest(".ui-dialog").css("top", iObj.top + "px");
		},
		buttons : [{
			"html" : "<i class='fa fa-close'></i>&nbsp; 닫기",
			"class": "btn btn-default",
			"click": function() {
				$(this).dialog("close");
			}
		}]
	});
	
	$("#authFrm").validate({
		submitHandler: function(form) {
			
			if(form.sw.value == null || form.sw.value == ''){
				alert("인증수첩번호를 입력해주세요.");
				return false;
			}
			$.getList_auth('1');
			
			return false;
		},
		rules : {
		},
		messages : {
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element.parent());
		}
	});
});

function openForm_auth() {
	$('#auth_form').dialog('open');
}

function saveAuth(sn, no, noteSn, od){
	//alert(sn + "//" + no + "//" + noteSn + "//" + od);
	var nm = $("input[name=cName]").eq(no).val();
	$.ajax({
		url     : "/${ admURI }/auth/upload/updateAuth.do?key=${ param.key }", 
		type    : "POST", 
		data    : { auSn : sn, cName : nm, noteSn : noteSn, oldData : od },
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			
			var tmpStr = "\n\t<tr>" +
			"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
			"\n\t</tr>";
			$("#authList").html(tmpStr);
		},
		success:function(result) {
			//var msg = JSON.parse(result).msg;
			//alert(result);
			alert("수정이 완료되었습니다.");
		}
	});
	//alert(tt);
	
}
</script>

<div id="auth_form" title="Dialog Simple Title">
	<div class="tab-content padding-10" style="margin-bottom:0;">
		<div class="tab-pane fade in active" id="tab1">
			<form id="authFrm" name="authFrm" method="post" class="form-horizontal" data-parsley-validate="true">
				<input type="hidden" name="pageIndex" id="pageIndex" value="0" />
				<input type="hidden" name="maxList"   id="maxList"   value="10" />
		
				<!-- # 검색 Start -->
				<fieldset class="form-horizontal" style="margin-top:10px;">
				
					<div class="form-group">
						<label class="col-md-2 control-label">인증수첩번호</label>
						<div class="col-md-9">
							<input type="text" name="sw" id="sw" class="form-control" placeholder="인증수첩 번호" style="float:left; width:50%;" />
							<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 검색</button>
						</div>
					</div>
				
				</fieldset>
			
			</form>
		</div>
	</div>
	
	<table id="dt_basic" class="table table-bordered table-hover smart-form" style="margin-bottom:0;">
		<colgroup>
			<col width="40%">
			<col width="40%">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>인증수첩번호</th>
				<th>사용자명</th>
				<th>수정</th>
			</tr>
		</thead>
	</table>
	
	<div class="scrl">
		<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
			<colgroup>
				<col width="40%">
				<col width="40%">
				<col>
			</colgroup>
			<tbody id="authList">
				<tr>
					<td colspan="3" style="height:280px; text-align:center; vertical-align:middle;">선택할 정보를 검색해 주세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<ul id="pageLine_meeting" class="pagination" style="float:left;"></ul>
</div>