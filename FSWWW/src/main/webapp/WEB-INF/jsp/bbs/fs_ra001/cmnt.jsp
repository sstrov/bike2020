<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">
(function($){
	// 댓글 목록 정보
	$.getList_cmnt = function(v) {
		var cmntFrm = document.forms['cmntFrm'];
		var p = (v == '0')? 1 : v;
		
		cmntFrm.pageIndex.value = p;
		
		var tmpStr = "<div style=\"width:100%; height:280px; line-height:280px; text-align:center; vertical-align:middle;\"><img src=\"/resource/mng/img/loading.gif\" width=\"32\" alt=\"\" /> 정보 조회중 입니다.</div>";
		tmpStr += "\n<div class=\"ui-widget-overlay ui-front loding_line\"></div>";
		$("#cmntList").html(tmpStr);
		
		$.ajax({
			url     : "/common/brd/cmnt/getList.do",
			type    : "POST",
			data    : {
				"cmntType"  : 'Y',
				"brdKey"    : ${ item.brdKey },
				"brdMngKey" : ${ item.brdMngKey },
				"brdCmntG"  : 1,
				"pageIndex" : p,
				"maxList"   : cmntFrm.maxList.value
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				//document.location.href = '/mng/index.do';
				
				var tmpStr = "<div style=\"width:100%; height:280px; line-height:280px; text-align:center; vertical-align:middle;\">시스템 오류!!</div>"
				$("#cmntList").html(tmpStr);
			},
			success:function(result) {
				var tmpStr = "";

				if(result != null && result.length > 0) {
					tmpStr = "";
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						
						// 페이징 처리
						if(i == 0) {
							<c:if test="${ brdMng.cmntPageAt eq 'Y' }">
								$.getPaging_cmnt(item.tCount);
							</c:if>
						}
						
						tmpStr += setCmnt(item);
					}
				} else {
					tmpStr = "<div style=\"width:100%; height:280px; line-height:280px; text-align:center; vertical-align:middle;\">검색된 정보가 없습니다.</div>";
					
					<c:if test="${ brdMng.cmntPageAt eq 'Y' }">
						// 페이징 처리
						$.getPaging_cmnt(0);
					</c:if>
				}
				
				$("#cmntList").html(tmpStr);
			}
		});
	};
	
	<c:if test="${ brdMng.cmntPageAt eq 'Y' }">
		$.getPaging_cmnt = function(tCount) {
			var cmntFrm     = document.forms['cmntFrm'];
			var maxList   = Number(cmntFrm.maxList.value);
			var pageIndex = Number(cmntFrm.pageIndex.value);
			
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
							'<a href="#none" onclick="$.getList_cmnt(0); return false;"><i class="fa fa-lg fa-step-backward"></i>&nbsp;</a>' +
						'</li>' +
						'<li tabindex="0" class="paginate_button previous" id="data-table_previous" aria-controls="data-table">' +
							'<a href="#none" onclick="$.getList_cmnt(' + pre + '); return false;"><i class="fa fa-lg fa-caret-left"></i>&nbsp;</a>' +
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
							tmpStr += '<li tabindex="0" class="paginate_button" aria-controls="data-table"><a href="#none" onclick="$.getList_cmnt(' + j + '); return false;">' + j + '</a></li>';
						}
					}
				}
	
				tmpStr += ((next <= maxPage)?
						'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
							'<a href="#none" onclick="$.getList_cmnt(' + next + '); return false;"><i class="fa fa-lg fa-caret-right"></i>&nbsp;</a>' +
						'</li>' +
						'<li tabindex="0" class="paginate_button next" id="data-table_next" aria-controls="data-table">' +
							'<a href="#none" onclick="$.getList_cmnt(' + maxPage + '); return false;"><i class="fa fa-lg fa-step-forward"></i>&nbsp;</a>' +
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
			
			$("#pageLine_cmnt").html(tmpStr);
		};
	</c:if>
})(jQuery);

$(document).ready(function() {
	$.getList_cmnt(0);
});

// 답글 목록 가져오기
function openCmntReply(obj, key, g) {
	var cmntFrm = document.forms['cmntFrm'];
	
	if($('.reply_' + key).prop("tagName") != undefined) {
		if($('.reply_' + key).css('display') == "none") {
			$('.reply_' + key).show();
		} else {
			$('.reply_' + key).hide();
		}
		return false;
	}
	
	$.ajax({
		url     : "/common/brd/cmnt/getList.do",
		type    : "POST",
		data    : {
			"cmntType"    : 'Y',
			"brdKey"      : ${ item.brdKey },
			"brdMngKey"   : ${ item.brdMngKey },
			"brdCmntPKey" : key
		},
		dataType: "json",
		error   : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			//document.location.href = '/mng/index.do';
		},
		success:function(result) {
			<c:if test="${ !powCmnt }">
			if(result.length == 0) {
				return false;
			}
			</c:if>
				
			var color = "bg_g";
			if((g + 1) % 2 != 0) {
				color = "bg_w";
			}
			
			var tmpStr = '<div class="recmn_box reply_' + key + '">' +
				'<div class="recmn_box1 ' + color + '">' +
					'<ul>';

			if(result != null && result.length > 0) {
				for(var i=0; i<result.length; i++) {
					var item = result[i];
					
					tmpStr += setCmntChild(item, key);
				}
			}
			
			<c:if test="${ powCmnt }">
			tmpStr += '<li class="write">' +
					'<div class="text">' +
						'<div class="ip_box">' +
							'<input title="이름을 입력해주세요" type="text" id="cmntRegNm_' + key + '" name="cmntRegNm" class="name" placeholder="이름" value="<c:if test="${ !empty userSession.userNm }">${ userSession.userNm }</c:if>" />' +
							'<input title="비밀번호를 입력해주세요" type="password" id="cmntRegPw_' + key + '" name="cmntRegPw" class="pwd" placeholder="비밀번호" />' +
						'</div>' +
						'<span class="con">' +
							'<textarea id="cmntContent_' + key + '" name="cmntContent" placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요." title="타인을 배려 하는 마음을 담아 댓글을 달아주세요." class="textarea"></textarea>' +
						'</span>' +
						'<div class="cmnt_btn">' +
							'<a href="#none" onclick="setCmntAdd_child(this, ' + key + ', ' + g + ');">댓글 등록</a>' +
						'</div>' +
					'</div>' +
				'</li>';
			</c:if>
			
			tmpStr += '</ul></div></div>';
			$(obj).parent().parent().after(tmpStr);
		}
	});
}

/**
 * 조회된 댓글 리스트
 */
function setCmnt(item) {
	var date    = item.wDate.substring(0, 16);
	var regNm   = item.regNm;
	var userKey = '<c:if test="${ !empty userSession.userKey }">${ userSession.userKey }</c:if>';
	
	<c:if test="${ brdMng.cmntNmAt eq 'N' }">
		<c:choose>
			<c:when test="${ brdMng.regNmType eq 'NM' }">
				regNm = item.regNm;
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'ID' }">
				if(item.userId != null && item.userId.length > 0) {
					regNm = item.userId;
				} else {
					regNm = item.regNm;
				}
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'NMS' }">
				regNm = item.regNmSec;
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'IDS' }">
				if(item.userId != null && item.userId.length > 0) {
					regNm = item.userIdSec;
				} else {
					regNm = item.regNmSec;
				}
			</c:when>
		</c:choose>
	</c:if>
	
	var btn = "";
	<c:if test="${ powCmnt }">
	if(userKey != '' && userKey == item.userKey && item.userType == "W") {
		btn = ' <a href="#none" class="btn-modify" onclick="setModifyCmnt(this, ' + item.brdCmntKey + ')">수정</a>';
		btn += ((btn != '' && btn.length > 0)? ' ' : '') + '<a href="#none" onclick="setDeleteCmnt(this, ' + item.brdCmntKey + ', \'\')">삭제</a>';
	} else if(userKey == '' && item.regPw != null && item.regPw.length > 0) {
		// 비번이 있을때 비번 묻기
		btn = ' <a href="#none" class="btn-modify" onclick="setModifyCmnt_pw(this, ' + item.brdCmntKey + ')">수정</a>';
		btn += ((btn != '' && btn.length > 0)? ' ' : '') + '<a href="#none" onclick="setDeleteCmnt_pw(this, ' + item.brdCmntKey + ', \'\')">삭제</a>';
	}
	</c:if>
	
	var tmpStr = '<li>' +
		'<div class="text">' +
			'<span class="name">' + regNm + '</span>' +
			'<span class="con cmnt_' + item.brdCmntKey + '">' + replaceAll(replaceAll(item.brdCmntContent, '\n', '<br/>'), '\r', '&nbsp;') + '</span>' +
			'<span class="date">' + date + '</span>' +
			'<div class="cmnt_btn">';

	<c:if test="${ brdMng.cmntReplyAt eq 'Y' }">
		tmpStr += '<a href="#none" class="recmn replyCmnt_' + item.brdCmntKey + '" onclick="openCmntReply(this, ' + item.brdCmntKey + ', 1);">답글 <strong>' + item.replyCnt + '</strong></a>';
	</c:if>
	
	tmpStr += btn + '</div></div></li>';
	
	return tmpStr;
}

/**
 * 조회된 댓글 리스트 - 답글
 */
function setCmntChild(item, key) {
	var date    = item.wDate.substring(0, 16);
	var regNm   = item.regNm;
	var userKey = '<c:if test="${ !empty userSession.userKey }">${ userSession.userKey }</c:if>';
	
	<c:if test="${ brdMng.cmntNmAt eq 'N' }">
		<c:choose>
			<c:when test="${ brdMng.regNmType eq 'NM' }">
				regNm = item.regNm;
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'ID' }">
				if(item.userId != null && item.userId.length > 0) {
					regNm = item.userId;
				} else {
					regNm = item.regNm;
				}
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'NMS' }">
				regNm = item.regNmSec;
			</c:when>
			<c:when test="${ brdMng.regNmType eq 'IDS' }">
				if(item.userId != null && item.userId.length > 0) {
					regNm = item.userIdSec;
				} else {
					regNm = item.regNmSec;
				}
			</c:when>
		</c:choose>
	</c:if>
	
	var btn = "";
	<c:if test="${ powCmnt }">
	if(userKey == item.userKey && item.userType == "A") {
		btn = ' <a href="#none" class="btn-modify" onclick="setModifyCmnt(this, ' + item.brdCmntKey + ')">수정</a>';
		btn += ((btn != '' && btn.length > 0)? ' ' : '') + '<a href="#none" onclick="setDeleteCmnt(this, ' + item.brdCmntKey + ', \'\')">삭제</a>';
	} else if(userKey == '' && item.regPw != null && item.regPw.length > 0) {
		// 비번이 있을때 비번 묻기
		btn = ' <a href="#none" class="btn-modify" onclick="setModifyCmnt_pw(this, ' + item.brdCmntKey + ')">수정</a>';
		btn += ((btn != '' && btn.length > 0)? ' ' : '') + '<a href="#none" onclick="setDeleteCmnt_pw(this, ' + item.brdCmntKey + ', \'\')">삭제</a>';
	}
	</c:if>
	
	var tmpStr = '<li>' +
			'<div class="text">' +
				'<span class="name">' + regNm + '</span>' +
				'<span class="con cmnt_' + item.brdCmntKey + '">' +
					replaceAll(replaceAll(item.brdCmntContent, '\n', '<br/>'), '\r', '&nbsp;') +
				'</span>' +
				'<span class="date">' + date + '</span>' +
				'<div class="cmnt_btn">';

	<c:if test="${ brdMng.cmntReplyAt eq 'Y' }">
		tmpStr += '<a href="#none" class="recmn replyCmnt_' + item.brdCmntKey + '" onclick="openCmntReply(this, ' + item.brdCmntKey + ', ' + item.brdCmntG + ');">답글 <strong>' + item.replyCnt + '</strong></a>';
	</c:if>
	
	tmpStr += btn + '</div></div></li>';
	
	return tmpStr;
}

<c:if test="${ powCmnt }">
// 댓글 수정
function setModifyCmnt(obj, cmntKey) {
	var cnt     = $(".cmnt_" + cmntKey);
	var cntHtml = replaceAll(replaceAll($(cnt).html(), "<br>", "\n"), "&nbsp;", "\r");
	
	var keyup = "";
	<c:if test="${ brdMng.cmntSizeAt eq 'Y' }">
		keyup = "onkeyup=\"getStrLength(this, 'byteChk" + cmntKey + "', ${ brdMng.cmntSize });\"";
	</c:if>
	
	var tmpStr = '<textarea name="cmntModifyContent_bak" class="hide" style="display:none;">' + cntHtml + '</textarea>';
	tmpStr += '<textarea placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요." title="타인을 배려 하는 마음을 담아 댓글을 달아주세요." name="cmntModifyContent" class="textarea" ' + keyup + '>' + cntHtml + '</textarea>';
	
	<c:if test="${ brdMng.cmntSizeAt eq 'Y' }">
		var len = calculateBytes(cntHtml);
		tmpStr += "<span id=\"byteChk" + cmntKey + "\">(" + len + "/${ brdMng.cmntSize })</span><br/>";
	</c:if>
	
	$('.btn-modify').text("수정");
	$('.btn-modify').each(function() {
		if($(this).attr('data-onclick') != undefined) {
			$(this).attr("onclick", $(this).attr("data-onclick"));
		}
	});

	$(obj).html("수정취소");
	$(obj).attr("data-onclick", $(obj).attr("onclick"));
	$(obj).attr("onclick", "cancelCmnt(this, " + cmntKey + ");");
	$(cnt).html(tmpStr);
	$(cnt).parent().find('.cmnt_btn').prepend('<a href="#none" class="save_" onclick="__setCmntModify(this, ' + cmntKey + '); return false;">저장</a>');
}

// 댓글 수정 비밀번호 입력
function setModifyCmnt_pw(obj, cmntKey) {
	var cmntPwFrm = document.forms['cmntPwFrm'];
	
	cmntPwFrm.brdCmntKey.value = cmntKey;
	$(cmntPwFrm).find('button').prop('disabled', false);
	$('#cmntPw_form').modal('show');
	
	jQuery('#cmntPwFrm').validate({
		submitHandler: function(form) {
			
			$.ajax({
				url     : "/common/brd/cmnt/isExistPw.do",
				type    : "POST",
				data    : {
					'brdCmntKey' : form.brdCmntKey.value,
					'regPw'      : form.keyword.value
				},
				dataType: "json",
				error   : function(r, s, e) {
					alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
				},
				success:function(result) {
					var item = result[0];
					if(item.msg != '') {
						alert(item.msg);
						form.keyword.value = "";
						form.keyword.focus();
						return false;
					} else {
						$(form).find('button').prop('disabled', true);
						
						var cnt     = $(".cmnt_" + form.brdCmntKey.value);
						var cntHtml = replaceAll(replaceAll($(cnt).html(), "<br>", "\n"), "&nbsp;", "\r");
						
						var keyup = "";
						<c:if test="${ brdMng.cmntSizeAt eq 'Y' }">
							keyup = "onkeyup=\"getStrLength(this, 'byteChk" + form.brdCmntKey.value + "', ${ brdMng.cmntSize });\"";
						</c:if>
						
						var tmpStr = '<textarea name="cmntModifyContent_bak" class="hide" style="display:none;">' + cntHtml + '</textarea>';
						tmpStr += '<textarea placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요." title="타인을 배려 하는 마음을 담아 댓글을 달아주세요." name="cmntModifyContent" class="textarea" ' + keyup + '>' + cntHtml + '</textarea>';
						
						<c:if test="${ brdMng.cmntSizeAt eq 'Y' }">
							var len = calculateBytes(cntHtml);
							tmpStr += "<span id=\"byteChk" + form.brdCmntKey.value + "\">(" + len + "/${ brdMng.cmntSize })</span><br/>";
						</c:if>
						
						$('.btn-modify').text("수정");
						$('.btn-modify').each(function() {
							if($(this).attr('data-onclick') != undefined) {
								$(this).attr("onclick", $(this).attr("data-onclick"));
							}
						});

						$(obj).html("수정취소");
						$(obj).attr("data-onclick", $(obj).attr("onclick"));
						$(obj).attr("onclick", "cancelCmnt(this, " + form.brdCmntKey.value + ");");
						$(cnt).html(tmpStr);
						$(cnt).parent().find('.cmnt_btn').prepend('<a href="#none" class="save_" onclick="__setCmntModify(this, ' + form.brdCmntKey.value + '); return false;">저장</a>');
						$('#cmntPw_form').modal('hide')
					}
				}
			});
		}
	});
}

// 댓글 정보수정 취소
function cancelCmnt(obj, key) {
	var cnt = $('.cmnt_' + key);
	var cntHtml = replaceAll(replaceAll($(cnt).find("textarea[name='cmntModifyContent_bak']").val(), "\n", "<br>"), "\r", "&nbsp;");

	$('.btn-modify').text("수정");
	$('.btn-modify').each(function() {
		if($(this).attr('data-onclick') != undefined) {
			$(this).attr("onclick", $(this).attr("data-onclick"));
		}
	});

	$(cnt).html(cntHtml);
	$(cnt).parent().find('.save_').remove();
}

/*
| Description: 댓글 정보 삭제
| obj : 선택된 Object
| key : brdCmntKey
| type: 비밀번호 입력 여부
*/
function setDeleteCmnt(obj, key, type) {
	var tmpStr = "";
	if(type == "F") {
		tmpStr = "완전";
	}
	if(confirm("댓글을 " + tmpStr + "삭제 하시겠습니까?")) {
		// 삭제 프로세스
		__delCmnt(key, type);
	}
}

/*
| Description: 댓글 정보 삭제 비밀번호 확인
| obj : 선택된 Object
| key : brdCmntKey
| type: 비밀번호 입력 여부
*/
function setDeleteCmnt_pw(obj, key, type) {
	$('#cmntPw_form').modal('show');
	var cmntPwFrm = document.forms['cmntPwFrm'];
	
	cmntPwFrm.brdCmntKey.value = key;
	
	jQuery('#cmntPwFrm').validate({
		submitHandler: function(form) {
			
			$.ajax({
				url     : "/common/brd/cmnt/isExistPw.do",
				type    : "POST",
				data    : {
					'brdCmntKey' : form.brdCmntKey.value,
					'regPw'      : form.keyword.value
				},
				dataType: "json",
				error   : function(r, s, e) {
					alert('code:' + r.status + '\nmessage:' + r.responseText + '\nerror:' + e);
				},
				success:function(result) {
					var item = result[0];
					if(item.msg != '') {
						alert(item.msg);
						form.keyword.value = "";
						form.keyword.focus();
						return false;
					} else {
						if(confirm("댓글을 삭제 하시겠습니까?")) {
							// 삭제 프로세스
							__delCmnt(form.brdCmntKey.value, type);
							$('#cmntPw_form').modal('hide')
						}
					}
				}
			});
		}
	});
}

function __delCmnt(key, type) {
	var cmntFrm = document.forms['cmntFrm'];

	lodingFixedOn("댓글 삭제 중 입니다.", 300, 180);
	$.ajax({
		url  : "/common/brd/cmnt/delete.do",
		type : "POST",
		data : {
			"brdCmntType" : type,
			"brdCmntKey"  : key,
			"brdKey"      : ${ item.brdKey },
			"brdMngKey"   : ${ item.brdMngKey },
		},
		error : function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			lodingOff(document.body);
		},
		success:function() {
			// 덧글을 삭제 하였습니다
			alert("댓글을 삭제 하였습니다.");
			lodingOff(document.body);
			$.getList_cmnt(cmntFrm.pageIndex.value);
		}
	});
}

// 댓글 등록 하기
function setCmntAdd() {
	var cmntFrm = document.forms['cmntFrm'];
	
	if(isEmpty(cmntFrm.cmntRegNm)) {
		alert("이름을 입력해 주세요.");
		cmntFrm.cmntRegNm.focus();
		return false;
	}
	
	if(cmntFrm.cmntRegPw != undefined && isEmpty(cmntFrm.cmntRegPw)) {
		alert("비밀번호를 입력해 주세요.");
		cmntFrm.cmntRegPw.focus();
		return false;
	}
	
	if(isEmpty(cmntFrm.cmntContent)) {
		alert("댓글 내용을 입력해 주세요.");
		cmntFrm.cmntContent.focus();
		return false;
	}
	
	if(confirm("댓글을 등록 하시겠습니까?")) {
		lodingFixedOn("댓글 등록 중 입니다.", 300, 180);
		
		$.ajax({
			url     : "/common/brd/cmnt/insert.do",
			type    : "POST",
			data    : {
				"brdKey"         : ${ item.brdKey },
				"brdMngKey"      : ${ item.brdMngKey },
				"regNm"          : cmntFrm.cmntRegNm.value,
				"regPw"          : (cmntFrm.cmntRegPw != undefined)? cmntFrm.cmntRegPw.value : "",
				"brdCmntContent" : cmntFrm.cmntContent.value
			},
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				//document.location.href = '/mng/index.do';
				lodingOff(document.body);
			},
			success:function() {
				alert("댓글이 등록 되었습니다.");
				$.getList_cmnt(0);
				cmntFrm.cmntContent.value = "";
				$("#byteChk").html("(0/${ brdMng.cmntSize })");
				lodingOff(document.body);
			}
		});
	}
}

// 댓글 등록 하기 - 답글
function setCmntAdd_child(obj, key, g) {
	
	if($('#cmntRegNm_' + key).val() == '') {
		alert("이름을 입력해 주세요.");
		$('#cmntRegNm_' + key).focus();
		return false;
	}
	
	if($('#cmntRegPw_' + key).prop('tagName') != undefined &&  $('#cmntRegPw_' + key).val() == '') {
		alert("비밀번호를 입력해 주세요.");
		$('#cmntRegPw_' + key).focus();
		return false;
	}
	
	if($('#cmntContent_' + key).val() == '') {
		alert("댓글 내용을 입력해 주세요.");
		$('#cmntContent_' + key).focus();
		return false;
	}
	
	if(confirm("댓글을 등록 하시겠습니까?")) {
		lodingFixedOn("댓글 등록 중 입니다.", 300, 180);
		
		$.ajax({
			url     : "/common/brd/cmnt/insert.do",
			type    : "POST",
			data    : {
				"brdKey"         : ${ item.brdKey },
				"brdMngKey"      : ${ item.brdMngKey },
				"brdCmntPKey"    : key,
				"regNm"          : $('#cmntRegNm_' + key).val(),
				"regPw"          : ($('#cmntRegPw_' + key).prop('tagName') != undefined)? $('#cmntRegPw_' + key).val() : "",
				"brdCmntContent" : $('#cmntContent_' + key).val()
			},
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				//document.location.href = '/mng/index.do';
				lodingOff(document.body);
			},
			success:function() {
				alert("댓글이 등록 되었습니다.");
				$('.reply_' + key).remove();
				openCmntReply($('.replyCmnt_' + key), key, g);
				lodingOff(document.body);
			}
		});
	}
}

//댓글 정보 수정
function __setCmntModify(obj, key) {
	var cmntFrm = document.forms['cmntFrm'];

	var cntObj = $(obj).parent().parent().find("textarea[name='cmntModifyContent']");
	var content = $(cntObj).val();

	if(content == null || content.length == 0) {
		alert('수정할 댓글을 입력해 주세요.');
		cntObj.focus();
		return false;
	}
	
	if(confirm("댓글을 수정 하시겠습니까?")) {
		// 로딩 이미지 호출
		lodingFixedOn("댓글 수정 중 입니다.", 300, 180);
	
		$.ajax({
			url  : "/common/brd/cmnt/update.do",
			type : "POST",
			data : {
				'brdCmntKey'    : key,
				"brdKey"        : ${ item.brdKey },
				"brdMngKey"     : ${ item.brdMngKey },
				'brdCmntContent': content
			},
			error : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				// 로딩 이미지 제거
				lodingOff(document.body);
			},
			success:function() {
				alert("댓글이 수정 되었습니다.");
				
				// 로딩 이미지 제거
				lodingOff(document.body);
	
				$(obj).parent().parent().find("textarea[name='cmntModifyContent_bak']").val(content);
				cancelCmnt(obj, key);
			}
		});
	}
}
</c:if>
</script>

<form:form id="cmntFrm" name="cmntFrm" method="post" cssClass="form-horizontal form-bordered">
	<input type="hidden" name="pageIndex" id="pageIndex" value="0" />
	<input type="hidden" name="maxList"   id="maxList"   value="${ brdMng.cmntListCnt }" />

	<div class="comment">
		<!-- 댓글입력 -->
		<ul class="form">
			<c:choose>
				<c:when test="${ powCmnt }">
					<li>
						<div class="ip_box">
							<input type="text" title="이름을 입력하세요." name="cmntRegNm" value="${ userSession.userNm }" placeholder="이름" />
							<c:if test="${ empty userSession.userNm }">
								<input type="password" title="비밀번호를 입력하세요." name="cmntRegPw" class="pwd" placeholder="비밀번호" />
							</c:if>
						</div>
					</li>
					<li>
						<textarea class="textarea" name="cmntContent" title="타인을 배려 하는 마음을 담아 댓글을 달아주세요." placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요." <c:if test="${ brdMng.cmntSizeAt eq 'Y' }">onkeyup="getStrLength(this, 'byteChk', ${ brdMng.cmntSize });"</c:if>></textarea>
						<c:if test="${ brdMng.cmntSizeAt eq 'Y' }">
							<span id="byteChk">(0/${ brdMng.cmntSize })</span><br/>
						</c:if>
					</li>
					<li>
						<a href="#none" class="write" onclick="setCmntAdd();">등록</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						
					</li>
					<li>
						<textarea class="textarea" name="cmntContent" disabled title="댓글 쓰기 권한이 없습니다." placeholder="댓글 쓰기 권한이 없습니다."></textarea>
					</li>
					<li>
						<a href="#none" class="write" onclick="alert('댓글 쓰기 권한이 없습니다.');">등록</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
		
		<div class="cmtTitle">댓글 <span>[${ item.cmntCnt }]</span></div>
		
		<ul id="cmntList" class="cmntList"></ul>
		
		<c:if test="${ brdMng.cmntPageAt eq 'Y' }">
			<ul id="pageLine_cmnt" class="pagination" style="float:left;"></ul>
		</c:if>
	</div>

</form:form>