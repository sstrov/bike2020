<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<link rel="stylesheet" href="<c:url value='/resource/cmm/js/plupload-2.3.6/js/jquery.plupload.queue/css/jquery.plupload.queue.css' />" />
<script src="<c:url value='/resource/cmm/js/plupload-2.3.6/js/plupload.full.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/plupload-2.3.6/js/jquery.plupload.queue/jquery.plupload.queue.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/plupload-2.3.6/js/i18n/ko.js' />"></script>

<link href="<c:url value='/resource/cmm/js/DataTables/media/css/dataTables.bootstrap.min.css' />" rel="stylesheet" />
<link href="<c:url value='/resource/cmm/js/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css' />" rel="stylesheet" />
<link href="<c:url value='/resource/cmm/js/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css' />" rel="stylesheet" />
<script src="<c:url value='/resource/cmm/js/DataTables/media/js/jquery.dataTables.js' />"></script>
<script src="<c:url value='/resource/cmm/js/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js' />"></script>
<script src="<c:url value='/resource/cmm/js/DataTables/extensions/Responsive/js/dataTables.responsive.min.js' />"></script>

<style>
.dataTables_info { display:none; }
</style>

<script src="<c:url value='/resource/cmm/js/dates.js' />"></script>
<script>
var imgArray = Array("jpg", "jpeg", "gif", "png");
var tableId = "";
$(document).ready(function() {
	getFileModal();
	getUploadList_doc();
});

var fileImgToDate = replaceAll(getDatePlus(-1), '-', '');

/**
 * 첨부된 사진파일 정보 출력
 */
function getUploadList_doc() {
	
	lodingOnTarget($("#uploadFileList_doc"), "정보 조회중 입니다.", 150);
	$.ajax({
		url  : "<c:url value='/cmm/file/getList.do' />",
		type : "POST",
		data : {
			"uniqueId": "${ uniqueId }"
		},
		dataType : "json",
		error : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			// 로딩 이미지 제거
			alertOnTarget($("#uploadFileList_doc"), "첨부된 파일 가져오기 오류", 150);
		},
		success:function(result) {
			if(result != null && result.length > 0) {
				var tmpStr = '<table id="data-table-doc" data-reorder="true" class="table table-striped table-bordered">' +
								'<colgroup>' +
									'<col width="60">' +
									'<col width="130">' +
									'<col>' +
									'<col width="120">' +
									'<col width="180">' +
								'</colgroup>' +
								'<thead>' +
									'<tr>' +
										'<th>순서</th>' +
										'<th>이미지</th>' +
										'<th>대체 텍스트/대표 썸네일 여부</th>' +
										'<th>용량</th>' +
										'<th>기능</th>' +
									'</tr>' +
								'</thead>' +
								'<tbody>';
				
				for(var i=0; i<result.length; i++) {
					var item = result[i];
					
					var imgThumb = '';
					var imgChk = false;
					if(array_search(item.atchmnflExtsn.toLowerCase(), imgArray)) {
						imgChk = true;
						var sName = replaceAll(item.atchmnflRealNm, '.' + item.atchmnflExtsn, '');
						var imgThumbSrc = item.flpth + ((item.accSn != null && item.accSn > 0)? "/100x100_" : "/tmp/" + fileImgToDate + "/100x100_") + sName + "." + item.atchmnflExtsn;
						imgThumb = "<img src=\"<c:url value='" + imgThumbSrc + "' />\" alt=\"\" width=\"100\" height=\"100\" />";
					}

					var atchmnflSize = (Number(item.atchmnflSize) / 1048576).toFixed(2);
					if(atchmnflSize <= 0) {
						atchmnflSize = (Number(item.atchmnflSize) / 1024).toFixed(2) + " KB";
					} else {
						atchmnflSize += " MB";
					}
					
					var reprsntThumbAt = '';
					if(i == 0 || item.reprsntThumbAt == 'Y') {
						reprsntThumbAt = 'checked';
					}

					tmpStr += '<tr>' +
										'<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>' +
										'<td>' +
											'<input type="hidden" name="fileChkKey" value="' + item.atchmnflRealNm + '" />' +
											imgThumb +
										'</td>' +
										'<td>' +
											'<input title="" type="text" class="form-control" name="fileName" value="' + item.atchmnflNm + '" /><br>' +
											'<label><input title="대표여부" type="radio" name="fileRepFlag" value="' + item.atchmnflRealNm + '" ' + reprsntThumbAt + ' /> 대표여부</label>' +
										'</td>' +
										'<td>' +
											atchmnflSize +
										'</td>' +
										'<td>';
										
					if(imgChk && fileImgContentHtml != '') {
						var fullPath = item.flpth + ((item.accSn != null && item.accSn > 0)? "/" : "/tmp/" + fileImgToDate + "/") + item.atchmnflRealNm;
						tmpStr += '<button type="button" class="btn btn-info btn-sm form-control" onclick="setContent_img_summ(\'' + fileImgContentHtml + '\', \'' + fullPath + '\', $(this).parent().parent());">' +
												'<i class="fa fa-sign-in"></i> 본문에 반영' +
											'</button>';
					}
					
					tmpStr += '<button type="button" class="btn btn-danger btn-sm form-control" onclick="delUploadFile_doc(this, \'' + item.atchmnflSn + '\', \'' + item.atchmnflRealNm + '\', \'' + fileImgContentHtml + '\', \'' + fileImgUpKey + '\', \'' + fileImgUniqueId + '\');">' +
												'<i class="fa fa-times"></i> 삭제' +
											'</button>' +
										'</td>' +
									'</tr>';
				}

				tmpStr += '</tbody>' +
							'</table>' +
							'<p class="note"><strong>Note:</strong> 드래그 앤 드롭으로 이미지 순서를 정할 수 있습니다. </p>';
				
				$("#uploadFileList_doc").html(tmpStr);

				dtd();
			} else {
				alertOnTarget($("#uploadFileList_doc"), "첨부된 파일이 없습니다.", 150);
			}
		}
	});
}

function delUploadFile_doc(obj, fileKey, fileName, htmlNode, upKey, uniqueId) {
	if(confirm("선택한 파일을 삭제 하시겠습니까?")) {
		// 파일 및 DB 삭제
		if(isNaN(Number(fileKey))) {
			fileKey = null;
		}

		$.ajax({
			url     : "<c:url value='/cmm/file/delFile.do' />",
			type    : "POST",
			data    : {
				'fileKey'  : Number(fileKey),
				'fileSNm'  : fileName,
				'uniqueId' : uniqueId,
				'upKey'    : upKey
			},
			dataType : "json",
			error    : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				result = result[0];
				if(result != null && result.msg != null && result.msg.length > 0) {
					alert(result.msg);
					lodingOff(document.body);
					return false;
				}
				
				// 처리 완료 시 화면 이미지 제거
				if($("#" + htmlNode).prop('id') != undefined) {
					var tmpContent = "";
					var content = $('#' + htmlNode).summernote("code");
					
					var contentSplit = content.split(">");
					if(contentSplit != null && contentSplit.length > 0) {
						for(var i=0; i<contentSplit.length; i++) {
							var tmpStr = contentSplit[i];
							
							if(tmpStr.indexOf(fileName) == -1) {
								tmpContent += tmpStr + ((tmpStr != null && tmpStr.length > 0)? ">" : "");
							} else {
								var imgSplit = contentSplit[i].split("<img");
								
								if(imgSplit.length > 1) {
									tmpContent += imgSplit[0];
								} else {
									tmpContent += imgSplit[0] + ((tmpStr != null && tmpStr.length > 0)? ">" : "");
								}
							}
						}
					}

					$('#' + htmlNode).summernote('reset');
					$("#" + htmlNode).summernote("pasteHTML", tmpContent);
				}
				
				getUploadList_doc();
				alert("파일을 제거 하였습니다.");
			}
		});
	}
}

/**
 * 이미지 첨부 화면 출력
 */
function getFileModal() {
	if($('#fileReset_doc').prop('tagName') != undefined) {
		$('#fileReset_doc').hide();
		setPlupload_doc("fileUploader", "<c:url value='/cmm/file/uploadTmp.do' />?uniqueId=" + fileImgUniqueId + "&limitMegaByte=" + fileImgLimit + "&upKey=" + fileImgUpKey + "&type=file&etcType=file", fileImgLimit);
		
		// 사진 첨부 완료 처리
		var imgUpload = $("#fileUploader").pluploadQueue();
		imgUpload.bind("UploadComplete", function() {
			if(imgUpload.files.length == (imgUpload.total.uploaded + imgUpload.total.failed)) {
				// 사진 첨부파일 재로드 및 업로드 창 갱신
				getUploadList_doc();
				$('#fileReset_doc').show();
			}
		});
	}
}

function dtd() {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	tableId = $('#data-table-doc').DataTable({
		paging: false,
		searching: false,
		ordering: false,
		rowReorder: {
			update : false
		},
		language: {
			infoEmpty: ""
		}
	});
}
</script>