<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
(function($){
	
	// 분류코드 목록 정보
	$.getList_codeCl = function(v) {
		var clFrm = document.forms['clFrm'];
		var p = (v == '0')? 1 : v;
		
		clFrm.pageIndex.value = p;
		
		var tmpStr = "\n\t<tr>" +
		"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\"><img src=\"/resource/mng/img/loading.gif\" width=\"32\" alt=\"\" /> 정보 조회중 입니다.</td>" +
		"\n\t</tr>";
		tmpStr += "\n<div class=\"ui-widget-overlay ui-front loding_line\"></div>";
		$("#clList").html(tmpStr);
		
		$.ajax({
			url     : "/" + admURI + "/code/cl/getList.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"sw"        : clFrm.keyword.value,
				"sc_useAt"  : "Y",
				"pageIndex" : p,
				"maxList"   : clFrm.maxList.value
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				
				var tmpStr = "\n\t<tr>" +
				"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
				"\n\t</tr>";
				$("#clList").html(tmpStr);
			},
			success:function(result) {
				var tmpStr = "";
				
				var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					return false;
				}

				if(result != null && result.length > 0) {
					tmpStr = "";
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						// 페이징 처리
						if(i == 0) {
							$.getPaging_codeCl(item.tCount);
						}
						
						tmpStr += setAdd(item);
					}
				} else {
					tmpStr = "\n\t<tr>" +
					"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">검색된 정보가 없습니다.</td>" +
					"\n\t</tr>";
					
					// 페이징 처리
					$.getPaging_codeCl(0);
				}
				
				$("#clList").html(tmpStr);
			}
		});
	};
	
	$.getPaging_codeCl = function(tCount) {
		var clFrm     = document.forms['clFrm'];
		var maxList   = Number(clFrm.maxList.value);
		var pageIndex = Number(clFrm.pageIndex.value);
		
		if(pageIndex < 1) pageIndex = 1;
		var showNo    = maxList;											// 한페이지에 보여줄 개수
		var pageViews = 5;													// 넘버링 개수
		var maxPage   = Math.ceil(tCount/showNo);							// 마지막 페이지
		var i         = Math.floor((pageIndex-1)/pageViews)*pageViews+1;	// 페이지 넘기기 설정
		var next      = pageIndex + 1;
		var pre       = pageIndex - 1;
		
		if(maxPage == 0) maxPage = 1;
		
		var currIndex  = (pageIndex - 1) * maxList + 1;
		var currEIndex = currIndex + maxList - 1;
		
		if(currEIndex > tCount) {
			currEIndex = tCount;
		}
		
		var tmpStr = "";
		if(maxPage > 1) {
			tmpStr += ((pre > 0)?
					'<li tabindex="0" class="paginate_button previous" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_codeCl(0); return false;"><i class="fa fa-lg fa-step-backward"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button previous" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_codeCl(' + pre + '); return false;"><i class="fa fa-lg fa-caret-left"></i>&nbsp;</a>' +
					'</li>'
				:
					'<li tabindex="0" class="paginate_button previous disabled" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none"><i class="fa fa-lg fa-step-backward"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button previous disabled" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none"><i class="fa fa-lg fa-caret-left"></i>&nbsp;</a>' +
					'</li>'
				);

			for(var j=i; j<=maxPage; j++) {
				if(j < i + Number(pageViews)) {
					if(j == pageIndex) {
						tmpStr += '<li tabindex="0" class="paginate_button active" aria-controls="data-table"><a href="#none">' + j + '</a></li>';
					} else {
						tmpStr += '<li tabindex="0" class="paginate_button" aria-controls="data-table"><a href="#none" onclick="$.getList_codeCl(' + j + '); return false;">' + j + '</a></li>';
					}
				}
			}

			tmpStr += ((next <= maxPage)?
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_codeCl(' + next + '); return false;"><i class="fa fa-lg fa-caret-right"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_codeCl(' + maxPage + '); return false;"><i class="fa fa-lg fa-step-forward"></i>&nbsp;</a>' +
					'</li>'
				:
					'<li tabindex="0" class="paginate_button next disabled" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none"><i class="fa fa-lg fa-caret-right"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button next disabled" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none"><i class="fa fa-lg fa-step-forward"></i>&nbsp;</a>' +
					'</li>'
				);
		}
		
		$("#pageLine_codeCl").html(tmpStr);
	};
	
})(jQuery);

$(document).ready(function() {
	$('#cl_form').dialog({
		autoOpen : false,
		width    : 700,
		resizable: false,
		modal    : true,
		title    : "분류코드 조회",
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
				$(document.body).css("overflow-y", "auto");
			}
		}]
	});
	
	$("#clFrm").validate({
		submitHandler: function(form) {
			
			$.getList_codeCl('1');
			
			return false;
		}
	});
	
	$.getList_codeCl('1');
});

/**
 * 조회된 정보 목록
 */
function setAdd(item) {
	var tmpStr = "\n\t<tr style=\"cursor:pointer;\" onclick=\"setCl('" + item.codeClId + "', '" + item.codeClNm + "');\">" +
	"\n\t\t<td style=\"text-align:center;\">" + item.codeClId + "</td>" +
	"\n\t\t<td>" + item.codeClNm + "</td>" +
	"\n\t\t<td>" + calculateValueDot(item.codeClDc, 50) + "</td>" +
	"\n\t</tr>";
	
	return tmpStr;
}

function setCl(codeClId, codeClNm) {
	$('#codeClId').val(codeClId);
	$('#codeClNm').val(codeClId + ' (' + codeClNm + ')');
	$('#cl_form').dialog('close');
	$('#codeId').select();
	$(document.body).css("overflow-y", "auto");
}

/**
 * 분류코드 조회 레이어팝업 오픈
 */
function openClForm() {
	$('#cl_form').dialog('open');
	$(document.body).css("overflow-y", "hidden");
}
</script>

<div id="cl_form" title="Dialog Simple Title">
	<div class="tab-content padding-10" style="margin-bottom:0;">
		<div class="tab-pane fade in active" id="tab1">
			<form id="clFrm" name="clFrm" method="post" class="form-horizontal" data-parsley-validate="true">
				<input type="hidden" name="pageIndex" id="pageIndex" value="0" />
				<input type="hidden" name="maxList"   id="maxList"   value="10" />
		
				<!-- # 검색 Start -->
				<fieldset class="form-horizontal" style="margin-top:10px;">
				
					<div class="form-group">
						<label class="col-md-2 control-label">키워드</label>
						<div class="col-md-9">
							<input type="text" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#clKeywordElement" placeholder="키워드 입력.." style="float:left; width:50%;" />
							<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 검색</button>
							<div id="clKeywordElement"></div>
						</div>
					</div>
				
				</fieldset>
			
			</form>
		</div>
	</div>
	
	<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
		<colgroup>
			<col width="130">
			<col width="200">
			<col >
		</colgroup>
		<thead>
			<tr>
				<th>분류코드 아이디</th>
				<th>분류코드 명</th>
				<th>분류코드 설명</th>
			</tr>
		</thead>
	</table>
	
	<div class="scrl">
		<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
			<colgroup>
				<col width="130">
				<col width="200">
				<col >
			</colgroup>
			<tbody id="clList">
				<tr>
					<td colspan="3" style="height:280px; text-align:center; vertical-align:middle;">선택할 정보를 검색해 주세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<ul id="pageLine_codeCl" class="pagination" style="float:left;"></ul>
	
</div>