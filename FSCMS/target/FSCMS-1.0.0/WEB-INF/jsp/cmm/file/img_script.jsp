<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/resource/mngr/plugins/plupload-2.3.6/js/jquery.plupload.queue/css/jquery.plupload.queue.css" />
<script src="/resource/mngr/plugins/plupload-2.3.6/js/plupload.full.min.js"></script>
<script src="/resource/mngr/plugins/plupload-2.3.6/js/jquery.plupload.queue/jquery.plupload.queue.min.js"></script>
<script src="/resource/mngr/plugins/plupload-2.3.6/js/i18n/ko.js"></script>

<link href="/resource/mngr/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<style>
.dataTables_info { display:none; }
</style>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script>
var tableId = "";
$(document).ready(function() {
	getImgModal();
	getUploadList_img();
});

// 오늘 날짜
var fileImgToDate = replaceAll(getDatePlus(0), '-', '');

/**
 * 첨부된 사진파일 정보 출력
 */
function getUploadList_img() {
	
	lodingOnTarget($("#uploadFileList_img"), "정보 조회중 입니다.", 150);
	$.ajax({
		url  : "/cmm/file/getList.do",
		type : "POST",
		data : {
			"uniqueId": "${ uniqueId }",
			"fileEtc" : "img"
		},
		dataType : "json",
		error : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			// 로딩 이미지 제거
			alertOnTarget($("#uploadFileList_img"), "첨부된 사진파일 가져오기 오류", 150);
		},
		success:function(result) {
			if(result != null && result.length > 0) {
				var thumbDisplay    = "display:none;";
				var thumbDisplayTxt = "";
				if(fileImgThumbAt != null && fileImgThumbAt == "Y") {
					thumbDisplay = "";
					thumbDisplayTxt = "/대표 썸네일 여부";
				}
				
				var tmpStr = '<table id="data-table-img" data-reorder="true" class="table table-striped table-bordered">' +
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
										'<th>대체 텍스트' + thumbDisplayTxt + '</th>' +
										'<th>용량</th>' +
										'<th>기능</th>' +
									'</tr>' +
								'</thead>' +
								'<tbody>';
				
				for(var i=0; i<result.length; i++) {
					var item = result[i];

					var sName = replaceAll(item.atchmnflRealNm, '.' + item.atchmnflExtsn, '');
					
					var fullPath = item.flpth + ((item.accSn != null && item.accSn > 0)? "/" : "/tmp/" + fileImgToDate + "/") + item.atchmnflRealNm;
					var imgThumbSrc = item.flpth + ((item.accSn != null && item.accSn > 0)? "/100x100_" : "/tmp/" + fileImgToDate + "/100x100_") + sName + "." + item.atchmnflExtsn;

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
											'<img src="' + imgThumbSrc + '" alt="" width="100" height="100" />' +
										'</td>' +
										'<td>' +
											'<input type="text" class="form-control" name="fileName" value="' + item.atchmnflNm + '" /><br>' +
											'<label style="' + thumbDisplay + '"><input type="radio" name="fileRepFlag" value="' + item.atchmnflRealNm + '" ' + reprsntThumbAt + ' /> 대표여부</label>' +
										'</td>' +
										'<td>' + 
											atchmnflSize +
										'</td>' +
										'<td>' +
											'<button type="button" class="btn btn-info btn-sm form-control" onclick="setContent_img_summ(\'' + fileImgContentHtml + '\', \'' + fullPath + '\', $(this).parent().parent());">' +
												'<i class="fa fa-sign-in"></i> 본문에 반영' +
											'</button>' +
											'<button type="button" class="btn btn-danger btn-sm form-control" onclick="delUploadFile_img(this, \'' + item.atchmnflSn + '\', \'' + item.atchmnflRealNm + '\', \'' + fileImgContentHtml + '\', \'' + fileImgUpKey + '\', \'' + fileImgUniqueId + '\');">' +
												'<i class="fa fa-times"></i> 삭제' +
											'</button>' +
										'</td>' +
									'</tr>';
				}

				tmpStr += '</tbody>' +
							'</table>' +
							'<p class="note"><strong>Note:</strong> 드래그 앤 드롭으로 이미지 순서를 정할 수 있습니다. </p>';
				
				$("#uploadFileList_img").html(tmpStr);

				dtd();
			} else {
				alertOnTarget($("#uploadFileList_img"), "첨부된 사진파일이 없습니다.", 150);
			}
		}
	});
}

function delUploadFile_img(obj, fileKey, fileName, htmlNode, upKey, uniqueId) {
	if(confirm("선택한 파일을 삭제 하시겠습니까?")) {
		// 파일 및 DB 삭제
		if(isNaN(Number(fileKey))) {
			fileKey = null;
		}

		$.ajax({
			url     : "/cmm/file/delFile.do",
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
				
				getUploadList_img();
				alert("이미지를 제거 하였습니다.");
			}
		});
	}
}

/**
 * 이미지 첨부 화면 출력
 */
function getImgModal() {
	$('#fileReset').hide();
	setPlupload_imgManagement("imgUploader", "/cmm/file/uploadTmp.do?uniqueId=" + fileImgUniqueId + "&limitMegaByte=" + fileImgLimit + "&upKey=" + fileImgUpKey + "&type=img&etcType=img&w=" + fileImgWidth + "&h=" + fileImgHeight + "&crop=" + fileImgCrop, fileImgLimit);
	
	// 사진 첨부 완료 처리
	var imgUpload = $("#imgUploader").pluploadQueue();
	imgUpload.bind("UploadComplete", function() {
		if(imgUpload.files.length == (imgUpload.total.uploaded + imgUpload.total.failed)) {
			// 사진 첨부파일 재로드 및 업로드 창 갱신
			getUploadList_img();
			$('#fileReset').show();
		}
	});
}

function dtd() {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	tableId = $('#data-table-img').DataTable({
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