<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<link rel="stylesheet" href="/resource/mngr/plugins/summernote-0.8.11/summernote.css">
<script src="/resource/mngr/plugins/summernote-0.8.11/summernote.js"></script>
<script src="/resource/mngr/plugins/summernote-0.8.11/lang/summernote-ko-KR.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';

(function($){
	
	// 변경이력 목록 정보
	$.getList_log = function(v) {
		var frm = document.forms['logFrm'];
		var p = (v == '0')? 1 : v;
		
		frm.pageIndex.value = p;
		
		var tmpStr = "\n\t<tr>" +
		"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\"><img src=\"/resource/mng/img/loading.gif\" width=\"32\" alt=\"\" /> 정보 조회중 입니다.</td>" +
		"\n\t</tr>";
		tmpStr += "\n<div class=\"ui-widget-overlay ui-front loding_line\"></div>";
		$("#logList").html(tmpStr);
		
		$.ajax({
			url     : "/${ admURI }/cntnts/chghst/getList.do?key=${ param.key }",
			type    : "POST",
			data    : {
				"menuSn"   : "${ param.menuSn }",
				"pageIndex" : p,
				"maxList"   : frm.maxList.value
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				//document.location.href = '/mng/index.do';
				
				var tmpStr = "\n\t<tr>" +
				"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
				"\n\t</tr>";
				$("#logList").html(tmpStr);
			},
			success:function(result) {
				var tmpStr = "";
				
				var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					var tmpStr = "\n\t<tr>" +
					"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</td>" +
					"\n\t</tr>";
					$("#logList").html(tmpStr);
					return false;
				}

				if(result != null && result.length > 0) {
					tmpStr = "";
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						// 페이징 처리
						if(i == 0) {
							$.getPaging_log(item.tCount);
						}
						
						tmpStr += setAdd_log(item);
					}
				} else {
					tmpStr = "\n\t<tr>" +
					"\n\t\t<td colspan=\"3\" style=\"height:280px; text-align:center; vertical-align:middle;\">등록된 정보가 없습니다.</td>" +
					"\n\t</tr>";
					
					// 페이징 처리
					$.getPaging(0);
				}
				
				$("#logList").html(tmpStr);
			}
		});
	};
	
	$.getPaging_log = function(tCount) {
		var frm   = document.forms['logFrm'];
		var maxList   = Number(frm.maxList.value);
		var pageIndex = Number(frm.pageIndex.value);
		
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
						'<a href="#none" onclick="$.getList_log(0); return false;"><i class="fa fa-lg fa-step-backward"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button previous" id="data-table_previous" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_log(' + pre + '); return false;"><i class="fa fa-lg fa-caret-left"></i>&nbsp;</a>' +
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
						tmpStr += '<li tabindex="0" class="paginate_button" aria-controls="data-table"><a href="#none" onclick="$.getList_log(' + j + '); return false;">' + j + '</a></li>';
					}
				}
			}

			tmpStr += ((next <= maxPage)?
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_log(' + next + '); return false;"><i class="fa fa-lg fa-caret-right"></i>&nbsp;</a>' +
					'</li>' +
					'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
						'<a href="#none" onclick="$.getList_log(' + maxPage + '); return false;"><i class="fa fa-lg fa-step-forward"></i>&nbsp;</a>' +
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
		
		$("#logPageLine").html(tmpStr);
	};
	
})(jQuery);

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/cntnts/update.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	</c:if>
	
	$('#cntntsChghstCn').summernote(summNoteOp);
	
	$('#log_form').dialog({
		autoOpen : false,
		width    : 700,
		resizable: false,
		modal    : true,
		title    : "변경이력 조회",
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
});

/**
 * 조회된 변경이력 리스트
 */
function setAdd_log(item) {
	var tmpStr = "\n\t<tr style=\"cursor:pointer;\" onclick=\"setLog('" + item.cntntsChghstSn + "');\">" +
	"\n\t\t<td style=\"text-align:center;\">" + item.registDe + "</td>" +
	"\n\t\t<td style=\"text-align:center;\">" + item.registId + "</td>" +
	"\n\t\t<td style=\"text-align:center;\">" + item.registIp + "</td>" +
	"\n\t</tr>";
	
	return tmpStr;
}

/**
 * 변경이력 선택
 */
function setLog(key) {
	if(confirm("변경정보를 가져오시겠습니까?")) {
		$.ajax({
			url  : '/${ admURI }/cntnts/chghst/getObj.do?key=${ param.key }',
			type : 'POST',
			data : {
				'cntntsChghstSn' : key
			},
			dataType : "json",
			error : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				var item = result[0];
				
				var content = unhtmlspecialchars(item.msg);
				//var content = item.msg;
				$('#cntntsChghstCn').summernote('reset');
				$("#cntntsChghstCn").summernote("pasteHTML", content);
				$('#log_form').dialog('close');
			}
		});
	}
}

/**
 * 변경이력 레이어팝업 오픈
 */
function openForm_log() {
	$('#log_form').dialog('open');
	$.getList_log('1');
}

function getDiffContent() {
	if($("#contentList option:selected").val() == '') {
		alert('가져올 콘텐츠를 선택 해 주세요.');
		return false;
	}
	
	$.ajax({
		url  : '/${ admURI }/cntnts/getObj.do?key=${ param.key }',
		type : 'POST',
		data : {
			'menuSn' : $("#contentList option:selected").val()
		},
		dataType : "json",
		error : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		},
		success:function(result) {
			var item = result[0];
			
			//var content = unhtmlspecialchars(item.msg);
			var content = item.msg;
			$('#cntntsChghstCn').summernote('reset');
			$("#cntntsChghstCn").summernote("pasteHTML", content);
		}
	});
}

// 목록 이동
function goList() {
	f.action = '/${ admURI }/cntnts/list.do?key=' + menuKey;
	f.submit();
}
</script>

<div id="log_form" title="Dialog Simple Title">
	<div class="tab-content padding-10" style="margin-bottom:0;">
		<div class="tab-pane fade in active" id="tab1">
			<form id="logFrm" name="logFrm" method="post" class="form-horizontal" data-parsley-validate="true">
				<input type="hidden" name="pageIndex" id="logPageIndex" value="0" />
				<input type="hidden" name="maxList"   id="logMaxList"   value="10" />
			</form>
		</div>
	</div>
	
	<table id="dt_basic" class="table table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
		<colgroup>
			<col width="180">
			<col width="150">
			<col >
		</colgroup>
		<thead>
			<tr>
				<th>변경일자</th>
				<th>아이디</th>
				<th>아이피</th>
			</tr>
		</thead>
	</table>
	
	<div class="scrl">
		<table id="dt_basic" class="table table-hover table-striped table-bordered table-hover smart-form" style="margin-bottom:0;">
			<colgroup>
				<col width="180">
				<col width="150">
				<col >
			</colgroup>
			<tbody id="logList">
				<tr>
					<td colspan="2" style="height:280px; text-align:center; vertical-align:middle;">등록된 정보가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<ul id="logPageLine" class="pagination" style="float:left;"></ul>
	
</div>

<!-- 멀티 이미지파일 업로드 -->
<script type="text/javascript">
//업로드 파일 설정
var fileImgContentHtml = 'cntntsChghstCn';			// 본문에 반영 컨텐츠 아이디
var fileImgUniqueId    = '${ uniqueId }';		// 일련번호
var fileImgUpKey       = 'content';				// 업로드 타입
var fileImgLimit       = 10;					// 업로드 제한 사이즈
var fileImgWidth       = 100;					// 가로 사이즈
var fileImgHeight      = 100;					// 세로 사이즈
var fileImgCrop        = 'Y';					// 썸네일 생성여부 (가로x세로_파일명 형태로 썸네일 생성)
var fileImgThumbAt     = "N";
</script>
<jsp:include page="/WEB-INF/jsp/cmm/file/doc_script.jsp" />
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
			<input type="hidden" name="uniqueId" value="${ uniqueId }" />
			<input type="hidden" name="menuSn" value="${ param.menuSn }">
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">콘텐츠 명 * :</label>
						<div class="col-md-6 col-sm-6">
							${ item.menuNm }
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">변경이력 :</label>
						<div class="col-md-6 col-sm-6">
							<button type="button" class="btn btn-success" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="openForm_log();"><i class="fa fa-search"></i> 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">다른 콘텐츠 복사 :</label>
						<div class="col-md-6 col-sm-6">
							<select name="contentList" id="contentList" class="form-control" style="float:left; width:250px;">
								<option value="">선택</option>
								<c:if test="${ !empty contentList }">
									<c:forEach var="content" items="${ contentList }">
										<c:if test="${ item.menuSn ne content.menuSn }">
											<option value="${ content.menuSn }">
												<c:forEach begin="2" end="${ content.menuDp }" step="1">
													　
												</c:forEach>
												${ content.menuNm }
											</option>
										</c:if>
									</c:forEach>
								</c:if>
							</select>
							<button type="button" class="btn btn-success" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="getDiffContent();"><i class="fa fa-search"></i> 가져오기</button>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-12 col-sm-12">
							<textarea id="cntntsChghstCn" name="cntntsChghstCn" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> style="height:500px;">${ content }</textarea>
						</div>
					</div>
					
					<c:if test="${ !empty role && role.powW eq 'Y' }">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">파일 첨부 :</label>
							<div class="col-md-6 col-sm-6">
								<!-- 멀티파일 업로드 -->
								<jsp:include page="/WEB-INF/jsp/cmm/file/doc_form.jsp" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4">파일 첨부 목록 :</label>
							<div class="col-md-6 col-sm-6" id="uploadFileList_doc">
							</div>
						</div>
					</c:if>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">저장</button>
							</c:if>
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
						</div>
					</div>
				
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>