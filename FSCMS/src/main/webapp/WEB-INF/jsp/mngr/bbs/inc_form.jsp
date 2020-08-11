<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
(function($){
	// 사이트 목록 정보
	$.getList_brdMng = function(v) {
		var brdMngFrm = document.forms['brdMngFrm'];
		var p = (v == '0')? 1 : v;
		
		brdMngFrm.pageIndex.value = p;
		
		var tmpStr = "\n\t<tr>" +
		"\n\t\t<td style=\"height:280px; text-align:center; vertical-align:middle;\"><img src=\"/resource/mngr/img/loading.gif\" width=\"32\" alt=\"\" /> 정보 조회중 입니다.</td>" +
		"\n\t</tr>";
		tmpStr += "\n<div class=\"ui-widget-overlay ui-front loding_line\"></div>";
		$("#brdMngList").html(tmpStr);
		
		$.ajax({
			url     : "/" + admURI + "/bbs/estbs/getList.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"sw"        : brdMngFrm.keyword.value,
				"sc_useAt"  : "Y",
				"pageIndex" : p,
				"maxList"   : brdMngFrm.maxList.value
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				
				var tmpStr = "\n\t<tr>" +
				"\n\t\t<td style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
				"\n\t</tr>";
				$("#brdMngList").html(tmpStr);
			},
			success:function(result) {
				var tmpStr = "";
				
				var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					lodingOff(document.body);
					return false;
				}

				if(result != null && result.length > 0) {
					tmpStr = "";
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						// 페이징 처리
						if(i == 0) {
							$.getPaging_brdMng(item.tCount);
						}
						
						tmpStr += setBrdMngAdd(item);
					}
				} else {
					tmpStr = "\n\t<tr>" +
					"\n\t\t<td style=\"height:280px; text-align:center; vertical-align:middle;\">검색된 정보가 없습니다.</td>" +
					"\n\t</tr>";
					
					// 페이징 처리
					$.getPaging_brdMng(0);
				}
				
				$("#brdMngList").html(tmpStr);
			}
		});
	};
	
	$.getPaging_brdMng = function(tCount) {
		var brdMngFrm     = document.forms['brdMngFrm'];
		var maxList   = Number(brdMngFrm.maxList.value);
		var pageIndex = Number(brdMngFrm.pageIndex.value);
		
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
						'<a href="#none" onclick="$.getList_brdMng(0); return false;"><i class="fa fa-lg fa-step-backward"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button previous" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_brdMng(' + pre + '); return false;"><i class="fa fa-lg fa-caret-left"></i>&nbsp;</a>' +
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
						tmpStr += '<li tabindex="0" class="paginate_button" aria-controls="data-table"><a href="#none" onclick="$.getList_brdMng(' + j + '); return false;">' + j + '</a></li>';
					}
				}
			}

			tmpStr += ((next <= maxPage)?
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_brdMng(' + next + '); return false;"><i class="fa fa-lg fa-caret-right"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_brdMng(' + maxPage + '); return false;"><i class="fa fa-lg fa-step-forward"></i>&nbsp;</a>' +
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
		
		$("#pageLine_brdMng").html(tmpStr);
	};
})(jQuery);

$(document).ready(function() {
	$('#brdMng_form').dialog({
		autoOpen : false,
		width    : 700,
		resizable: false,
		modal    : true,
		title    : "게시판 조회",
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
	
	$("#brdMngFrm").validate({
		submitHandler: function(form) {
			
			$.getList_brdMng('1');
			
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

/**
 * 조회된 우편정보 리스트
 */
function setBrdMngAdd(item) {
	var tmpStr = "\n\t<tr style=\"cursor:pointer;\" onclick=\"setBrdMng('" + item.bbsEstbsSn + "', '" + item.bbsEstbsNm + "');\">" +
	"\n\t\t<td>" + item.bbsEstbsNm + "</td>" +
	"\n\t</tr>";
	
	return tmpStr;
}

function setBrdMng(bbsEstbsSn, bbsEstbsNm) {
	$('#bbsEstbsSn').val(bbsEstbsSn);
	$('#bbsEstbsNm').val(bbsEstbsNm);
	$('#brdMng_form').dialog('close');
}

/**
 * 게시판 조회 레이어팝업 오픈
 */
function openForm_brd() {
	$('#brdMng_form').dialog('open');
	$.getList_brdMng('1');
}
</script>

<div id="brdMng_form" title="Dialog Simple Title">
	<div class="tab-content padding-10" style="margin-bottom:0;">
		<div class="tab-pane fade in active" id="tab1">
			<form id="brdMngFrm" name="brdMngFrm" method="post" class="form-horizontal" data-parsley-validate="true">
				<input type="hidden" name="pageIndex" id="pageIndex" value="0" />
				<input type="hidden" name="maxList"   id="maxList"   value="10" />
		
				<!-- # 검색 Start -->
				<fieldset class="form-horizontal" style="margin-top:10px;">
				
					<div class="form-group">
						<label class="col-md-2 control-label">키워드</label>
						<div class="col-md-9">
							<input type="text" name="keyword" class="form-control" data-parsley-required="true" data-parsley-errors-container="#brdMngElement" placeholder="키워드 입력.." style="float:left; width:50%;" />
							<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 검색</button>
							<span id="brdMngElement"></span>
						</div>
					</div>
				
				</fieldset>
			
			</form>
		</div>
	</div>
	
	<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
		<colgroup>
			<col >
		</colgroup>
		<thead>
			<tr>
				<th>게시판 명</th>
			</tr>
		</thead>
	</table>
	
	<div class="scrl">
		<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
			<colgroup>
				<col >
			</colgroup>
			<tbody id="brdMngList">
				<tr>
					<td style="height:280px; text-align:center; vertical-align:middle;">선택할 정보를 검색해 주세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<ul id="pageLine_brdMng" class="pagination" style="float:left;"></ul>
	
</div>