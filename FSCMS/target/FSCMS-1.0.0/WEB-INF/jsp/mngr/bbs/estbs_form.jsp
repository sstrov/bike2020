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

<link href="/resource/mngr/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.bbsEstbsSn }">
	actionUrl = 'update';
</c:if>

var tableId = "";
$(document).ready(function() {
	jQuery('#frm').validate({
		submitHandler: function(form) {
			if(isEmpty(form.sknSnDp1)) {
				alert("사용 스킨을 선택해 주세요.");
				tabChoose(1);
				form.sknSnDp1.focus();
				return false;
			}
			// 게시판 종류
			if(isEmpty(form.sknSnDp2)) {
				alert("게시판 유형을 선택해 주세요.");
				tabChoose(1);
				form.sknSnDp2.focus();
				return false;
			}
			// 게시판 명
			if(isEmpty(form.bbsEstbsNm)) {
				alert("게시판 명을 입력해 주세요..");
				tabChoose(1);
				form.bbsEstbsNm.focus();
				return false;
			}
			// 리스트 제목 길이 입력 체크
			if(isEmpty(form.sjLtLmtt)) {
				alert("리스트 제목 길이를 입력해 주세요.");
				tabChoose(1);
				form.sjLtLmtt.focus();
				return false;
			}
			// 리스트 제목 길이 숫자 입력 체크
			if(isNaN(form.sjLtLmtt.value)) {
				alert("리스트 제목 길이는 숫자만 입력가능 합니다.");
				tabChoose(1);
				form.sjLtLmtt.select();
				return false;
			}
			// 페이지 목록 수 입력 체크
			if(isEmpty(form.listIndictCo)) {
				alert("페이지 목록수를 입력해 주세요.");
				tabChoose(1);
				form.listIndictCo.focus();
				return false;
			}
			// 페이지 목록 수 숫자 입력 체크
			if(isNaN(form.listIndictCo.value)) {
				alert("페이지 목록수는 숫자만 입력가능 합니다.");
				tabChoose(1);
				form.listIndictCo.select();
				return false;
			}
			// 새글 표시 기간 입력 체크
			if(isEmpty(form.indictDayNw)) {
				alert("새글 표시 기간을 입력해 주세요.");
				tabChoose(1);
				form.indictDayNw.focus();
				return false;
			}
			// 새글 표시 기간 숫자 입력 체크
			if(isNaN(form.indictDayNw.value)) {
				alert("새글 표시 기간은 숫자만 입력가능 합니다.");
				tabChoose(1);
				form.indictDayNw.select();
				return false;
			}
			
			// 카테고리 사용 체크
			if(form.ctgryAt[0].checked) {
				if($('#cateTable tbody tr').length == 0) {
					alert("카테고리명을 입력해 주세요.");
					tabChoose(6);
					return false;
				}
			}
			
			// 업로드 용량 숫자 체크
			if(form.atchAt[0].checked ) {
				if(isEmpty(form.atchSize)) {
					alert("파일첨부 용량을 입력해 주세요.");
					tabChoose(2);
					form.atchSize.focus();
					return false;
				}
				if(isNaN(form.atchSize.value)) {
					alert("파일첨부 용량은 숫자만 입력가능 합니다.");
					tabChoose(2);
					form.atchSize.select();
					return false;
				}
			}
			// 댓글 사용 여부
			if(form.cmAt[0].checked) {
				// 댓글 사이즈 숫자 체크
				if(form.cmLtLmttAt[0].checked) {
					if(isEmpty(form.cmLtLmttSize)) {
						alert("댓글 사이즈를 입력해 주세요.");
						tabChoose(2);
						form.cmLtLmttSize.focus();
						return false;
					}
					if(isNaN(form.cmLtLmttSize.value)) {
						alert("댓글 사이즈는 숫자만 입력가능 합니다.");
						tabChoose(2);
						form.cmLtLmttSize.select();
						return false;
					}
				}
				// 댓글 페이지 기능 사용
				if(form.cmPgngAt[0].checked) {
					if(isEmpty(form.cmListIndictCo)) {
						alert("댓글 페이지 목록수를 입력해 주세요.");
						tabChoose(2);
						form.cmListIndictCo.focus();
						return false;
					}
					// 댓글 페이지당 목록 수 숫자 체크
					if(isNaN(form.cmListIndictCo.value)) {
						alert("댓글 페이지당 목록수는 숫자만 입력가능 합니다.");
						tabChoose(2);
						form.cmListIndictCo.select();
						return false;
					}
				}
			}

			if(confirm("저장 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);
				$('*').prop('disabled', false);

				lodingFixedOn("저장중 입니다.", 300, 180);
				
				// 목록 보기 권한 설정
				var powStr = "";
				$("#bListPow option").each(function(idx, item) {
					powStr += "<input type=\"hidden\" name=\"powLists\" value=\"" + item.value + "\" />";
				});
				$("#powList").html(powStr);
				// 읽기 권한 설정
				powStr = "";
				$("#bReadPow option").each(function(idx, item) {
					powStr += "<input type=\"hidden\" name=\"powReads\" value=\"" + item.value + "\" />";
				});
				$("#powRead").html(powStr);
				// 쓰기 권한 설정
				powStr = "";
				$("#bWritePow option").each(function(idx, item) {
					powStr += "<input type=\"hidden\" name=\"powWrites\" value=\"" + item.value + "\" />";
				});
				$("#powWrite").html(powStr);
				// 답변 권한 설정
				powStr = "";
				$("#bReplyPow option").each(function(idx, item) {
					powStr += "<input type=\"hidden\" name=\"powReplys\" value=\"" + item.value + "\" />";
				});
				$("#powReply").html(powStr);
				// 댓글 권한 설정
				powStr = "";
				$("#bCmntPow option").each(function(idx, item) {
					powStr += "<input type=\"hidden\" name=\"powCmnts\" value=\"" + item.value + "\" />";
				});
				$("#powCmnt").html(powStr);
				
				// 멀티 셀렉트박스 정보 전달
				$('#listOrdr option').attr('selected', 'selected');
				$('#viewOrdr option').attr('selected', 'selected');
				
				// 체크 박스는 선택되지 않으면 배열에 담기지 않으므로 히든으로 값을 넘긴다.
				$('#fieldTable tbody tr').each(function(idx) {
					$('.flag').eq(idx).val(($('.flags').eq(idx).prop('checked'))? 'Y' : 'N');
					$('.necFlag').eq(idx).val(($('.necFlags').eq(idx).prop('checked'))? 'Y' : 'N');
					$('.shFlag').eq(idx).val(($('.shFlags').eq(idx).prop('checked'))? 'Y' : 'N');
					$('.viewFlag').eq(idx).val(($('.viewFlags').eq(idx).prop('checked'))? 'Y' : 'N');
					$('.listFlag').eq(idx).val(($('.listFlags').eq(idx).prop('checked'))? 'Y' : 'N');
				});

				f.action = '/${ admURI }/bbs/estbs/' + actionUrl + '.do?key=' + menuKey;
				f.submit();
			}
		}
	});
	
	// 스킨 변경시 스킨 종류 호출
	$("#sknSnDp1").change(function() {
		getSkinList($(this).val(), "");
	});
	$("#sknSnDp2").change(function() {
		getSkinList($("#sknSnDp1").val(), $(this).val());
	});

	// 화면 로드시 파일 첨부 사용 여부
	var uploadFlag = "${ item.atchAt }";
	var checked = (uploadFlag != null && uploadFlag.length > 0 && uploadFlag == "Y")? true : false;
	changeDisplay('upload', checked);

	var uploadThumbFlag = "${ item.thumbAt }";
	var checked = (uploadThumbFlag != null && uploadThumbFlag.length > 0 && uploadThumbFlag == "Y")? true : false;
	changeDisplay('uploadThumb', checked);

	// 화면 로드시 비밀글 사용 여부
	var secFlag = "${ item.secretAt }";
	var checked = (secFlag != null && secFlag.length > 0 && secFlag == "Y")? true : false;
	changeDisplay('secOnly', checked);

	// 화면 로드시 댓글 사이즈 사용 여부
	var cmLtLmttSize = "${ item.cmLtLmttAt }";
	var checked = (cmLtLmttSize != null && cmLtLmttSize.length > 0 && cmLtLmttSize == "Y")? true : false;
	changeDisplay('cmLtLmttSize', checked);

	// 화면 로드시 댓글 사용 여부
	var cmntFlag = "${ item.cmAt }";
	var checked = (cmntFlag != null && cmntFlag.length > 0 && cmntFlag == "Y")? true : false;
	changeDisplay('cmnt', checked);

	// 화면 로드시 댓글 페이지 사용 여부
	var cmntPageFlag = "${ item.cmPgngAt }";
	var checked = (cmntPageFlag != null && cmntPageFlag.length > 0 && cmntPageFlag == "Y")? true : false;
	changeDisplay('cmntPage', checked);

	// 화면 로드시 목록보기 접근 회원 그룹 설정 활성 여부
	var listPow = "${ item.authorList }";
	var checked = (listPow != null && listPow.length > 0 && listPow == "A")? true : false;
	changeDisplay('listPow', checked);

	// 화면 로드시 읽기 접근 회원 그룹 설정 활성 여부
	var readPow = "${ item.authorRedng }";
	var checked = (readPow != null && readPow.length > 0 && readPow == "A")? true : false;
	changeDisplay('readPow', checked);

	// 화면 로드시 쓰기 접근 회원 그룹 설정 활성 여부
	var writePow = "${ item.authorRegist }";
	var checked = (writePow != null && writePow.length > 0 && writePow == "A")? true : false;
	changeDisplay('writePow', checked);

	// 화면 로드시 답변 접근 회원 그룹 설정 활성 여부
	var replyPow = "${ item.authorAnswer }";
	var checked = (replyPow != null && replyPow.length > 0 && replyPow == "A")? true : false;
	changeDisplay('replyPow', checked);

	// 화면 로드시 댓글 접근 회원 그룹 설정 활성 여부
	var cmntPow = "${ item.authorCm }";
	var checked = (cmntPow != null && cmntPow.length > 0 && cmntPow == "A")? true : false;
	changeDisplay('cmntPow', checked);
	
	// 필드 목록 설정
	__getField();
});

//필드 목록 설정
function __getField() {
	if(actionUrl == 'update') {
		<c:choose>
			<c:when test="${ !empty fieldList_s }">
				var i = 0;
				var tmpStr = '';
				<c:forEach var="fid" items="${ fieldList_s }">
					var num     = (i + 1);															// 순번
					var fidKey  = "${ fid.bbsFieldSn }";
					var fCode   = "${ fid.bbsFieldCode }";											// 코드 명
					var name    = "${ fid.bbsFieldNm }";											// 항목 명
					
					var tDTxt   = "${ fid.fieldTyActvtyAt }";										// 항목 속성 활성값
					var tD      = (tDTxt == 'Y')? 'disabled' : '';									// 항목 속성 활성여부
					var t       = '{type' + "${ fid.fieldTy }".toLowerCase() + '}';					// 항목 선택 값
					var tTDTxt  = "${ fid.fieldTyTextActvtyAt }";									// 타입별 항목 속성 활성값
					var tTD     = (tTDTxt == 'Y')? 'disabled' : '';									// 타입별 항목 속성 활성여부
					var tTV     = "${ fid.fieldTyText }";											// 타입별 항목 값
	
					var flag    = ("${ fid.fieldUseAt }" == 'Y')? 'checked' : '';					// 사용여부
					var flagTxt = "${ fid.fieldUseActvtyAt }";										// 사용여부 활성값
					var flagD   = (flagTxt == 'Y')? 'disabled' : '';								// 사용여부 활성여부
	
					var nFlag    = ("${ fid.fieldEssntlAt }" == 'Y')? 'checked' : '';				// 필수여부
					var nFlagTxt = "${ fid.fieldEssntlActvtyAt }";									// 필수여부 활성값
					var nFlagD   = (nFlagTxt == 'Y')? 'disabled' : '';								// 필수여부 활성여부
	
					var sFlag    = ("${ fid.fieldSearchAt }" == 'Y')? 'checked' : '';				// 검색여부
					var sFlagTxt = "${ fid.fieldSearchActvtyAt }";									// 검색여부 활성값
					var sFlagD   = (sFlagTxt == 'Y')? 'disabled' : '';								// 검색여부 활성여부
	
					var lFlag    = ("${ fid.fieldListAt }" == 'Y')? 'checked' : '';					// 목록여부
					var lFlagTxt = "${ fid.fieldListActvtyAt }";									// 목록여부 활성값
					var lFlagD   = (lFlagTxt == 'Y')? 'disabled' : '';								// 목록여부 활성여부
					var lFlagS   = ("${ fid.fieldListAt }" == 'Y')? '' : 'style="display:none;"';	// 목록 사이즈 활성여부
					var lFlagSV  = "${ fid.fieldListSize }";										// 목록 사이즈 값
					var lFlagSS  = "${ fid.fieldListStyle }";										// 목록 스타일 값
					
					var vFlag    = ("${ fid.fieldViewAt }" == 'Y')? 'checked' : '';					// 보기여부
					var vFlagTxt = "${ fid.fieldViewActvtyAt }";									// 보기여부 활성값
					var vFlagD   = (vFlagTxt == 'Y')? 'disabled' : '';								// 보기여부 활성여부
	
					var btnFlag  = (fCode.indexOf('bbsEtc') != -1)? '' : 'hide';					// 버튼 활성(비활성:hide)
	
					// 필드 설정
					tmpStr += __getFieldContent(num, fCode, name, tDTxt, tD, t, tTDTxt, tTD, tTV, flag, flagTxt, flagD, nFlag, nFlagTxt, nFlagD, sFlag, sFlagTxt, sFlagD, lFlag, lFlagTxt, lFlagD, lFlagS, lFlagSV, lFlagSS, vFlag, vFlagTxt, vFlagD, btnFlag, fidKey, false);
					
					i++;
				</c:forEach>
				
				$('#fieldTable tbody').html(tmpStr);
			</c:when>
			<c:otherwise>
				$('#fieldTable tbody').html(__getFieldFirst());
			</c:otherwise>
		</c:choose>
	} else {
		$('#fieldTable tbody').html(__getFieldFirst());
	}
}

//필드 초기화
function __getFieldFirst() {
	var tmpStr = '';

	// 값이 없으면 기본 셋팅 생성
	var fieldArr = new Array(
		//		필드명,				코드,				속성활성,	항목활성,	사용체크,	사용활성,	필수체크,	필수활성,	검색체크,	검색활성,	목록체크,	목록활성,	버튼활성,	속성선택,	목록사이즈,보기체크,	보기활성	스타일
		new Array('카테고리',			'ctgryNm',		true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	'5',	'100',	false,	false,	''),
		new Array('제목',				'bbsSj',		false,	false,	true,	true,	true,	true,	true,	false,	true,	false,	true,	'2',	'',		true,	false,	'text-align:left;'),
		new Array('작성자',			'registerNm',	true,	true,	true,	false,	true,	true,	true,	false,	true,	false,	true,	'2',	'130',	true,	false,	''),
		new Array('조회수',			'rdcnt',		true,	true,	true,	false,	false,	true,	false,	true,	true,	false,	true,	'2',	'80',	true,	false,	''),
		new Array('등록일',			'registDe',		true,	true,	true,	false,	false,	true,	false,	true,	true,	false,	true,	'2',	'100',	true,	false,	''),
		new Array('수정일',			'updtDe',		true,	true,	false,	false,	false,	true,	false,	true,	false,	false,	true,	'2',	'100',	false,	false,	''),
		new Array('내용',				'bbsCn',		false,	false,	true,	false,	true,	false,	true,	false,	false,	false,	true,	'3',	'',		true,	false,	''),
		new Array('첨부',				'file',			true,	true,	false,	false,	false,	true,	false,	true,	false,	false,	true,	'2',	'45',	false,	false,	'')
	);

	for(var i=0; i<fieldArr.length; i++) {
		var item = fieldArr[i];

		var num     = (i + 1);										// 순번
		var fCode   = item[1];										// 코드 명
		var name    = item[0];										// 항목 명
		
		var tDTxt   = ((item[2])? 'Y' : 'N');						// 항목 속성 활성값
		var tD      = ((item[2])? 'disabled' : '');					// 항목 속성 활성여부
		var t       = '{type' + item[13] + '}';						// 항목 선택 값
		var tTDTxt  = (item[3])? 'Y' : 'N';							// 타입별 항목 속성 활성값
		var tTD     = (item[3])? 'disabled' : '';					// 타입별 항목 속성 활성여부
		var tTV     = '';											// 타입별 항목 값

		var flag    = (item[4])? 'checked' : '';					// 사용여부
		var flagTxt = (item[5])? 'Y' : 'N';							// 사용여부 활성값
		var flagD   = (item[5])? 'disabled' : '';					// 사용여부 활성여부

		var nFlag    = (item[6])? 'checked' : '';					// 필수여부
		var nFlagTxt = (item[7])? 'Y' : 'N';						// 필수여부 활성값
		var nFlagD   = (item[7])? 'disabled' : '';					// 필수여부 활성여부

		var sFlag    = (item[8])? 'checked' : '';					// 검색여부
		var sFlagTxt = (item[9])? 'Y' : 'N';						// 검색여부 활성값
		var sFlagD   = (item[9])? 'disabled' : '';					// 검색여부 활성여부

		var lFlag    = (item[10])? 'checked' : '';					// 목록여부
		var lFlagTxt = (item[11])? 'Y' : 'N';						// 목록여부 활성값
		var lFlagD   = (item[11])? 'disabled' : '';					// 목록여부 활성여부
		var lFlagS   = (item[10])? '' : 'style="display:none;"';	// 목록 사이즈 활성여부
		var lFlagSV  = item[14];									// 목록 사이즈 값
		var lFlagSS  = item[17];									// 목록 스타일 값
		
		var vFlag    = (item[15])? 'checked' : '';					// 보기여부
		var vFlagTxt = (item[16])? 'Y' : 'N';						// 보기여부 활성값
		var vFlagD   = (item[16])? 'disabled' : '';					// 보기여부 활성여부
		
		var btnFlag  = (item[12])? 'hide' : '';						// 버튼 활성(비활성:hide)

		// 필드 설정
		tmpStr += __getFieldContent(num, fCode, name, tDTxt, tD, t, tTDTxt, tTD, tTV, flag, flagTxt, flagD, nFlag, nFlagTxt, nFlagD, sFlag, sFlagTxt, sFlagD, lFlag, lFlagTxt, lFlagD, lFlagS, lFlagSV, lFlagSS, vFlag, vFlagTxt, vFlagD, btnFlag, '', true);
	}

	return tmpStr;
}

//필드 추가
function addField() {
	var f = document.forms['frm'];
	var code = 'bbsEtc';

	if(isEmpty(f.fidNm)) {
		alert('항목명은 필수 입력사항 입니다.');
		f.fidNm.focus();
		return false;
	}

	for(var i=1; i<=20; i++) {
		var bln = true;
		for(var j=0; j<$('#fieldTable tbody tr').length; j++) {
			if($('#fieldTable tbody tr .fcode').eq(j).val().indexOf('bbsEtc' + i) != -1) {
				bln = false;
				break;
			}
		}

		if(bln) {
			code = 'bbsEtc' + i;
			break;
		}
	}

	if(code == 'bbsEtc') {
		alert('추가 필드는 20개까지 등록 가능 합니다.');
		return false;
	}

	var num     = $('#fieldTable tbody tr').length + 1;							// 순번
	var fCode   = code;															// 코드 명
	var name    = f.fidNm.value;												// 항목 명
	var tDTxt   = 'N';															// 항목 속성 활성값
	var tD      = '';															// 항목 속성 활성여부
	var t       = '{type' + f.fidType.value.toLowerCase() + '}';				// 항목 선택 값
	var tTDTxt  = 'N';															// 타입별 항목 속성 활성값
	var tTD     = '';															// 타입별 항목 속성 활성여부
	var tTV     = f.fidTypeText.value;											// 타입별 항목 값

	var flag    = (f.fidAt.checked)? 'checked' : '';							// 사용여부
	var flagTxt = 'N';															// 사용여부 활성값
	var flagD   = '';															// 사용여부 활성여부

	var nFlag    = (f.fidNecAt.checked)? 'checked' : '';						// 필수여부
	var nFlagTxt = 'N';															// 필수여부 활성값
	var nFlagD   = '';															// 필수여부 활성여부

	var sFlag    = (f.fidShAt.checked)? 'checked' : '';							// 검색여부
	var sFlagTxt = 'N';															// 검색여부 활성값
	var sFlagD   = '';															// 검색여부 활성여부

	var lFlag    = (f.fidListAt.checked)? 'checked' : '';						// 목록여부
	var lFlagTxt = 'N';															// 목록여부 활성값
	var lFlagD   = '';															// 목록여부 활성여부
	var lFlagS   = (f.fidListAt.checked)? '' : 'style="display:none;"';			// 목록 사이즈 활성여부
	var lFlagSV  = f.fidListSize.value;											// 목록 사이즈 값
	var lFlagSS  = f.fidListStyle.value;										// 목록 스타일 값
	
	var vFlag    = (f.fidViewAt.checked)? 'checked' : '';						// 보기여부
	var vFlagTxt = 'N';															// 보기여부 활성값
	var vFlagD   = '';															// 보기여부 활성여부

	var btnFlag  = '';															// 버튼 활성(비활성:hide)

	// 필드 설정
	var tmpStr = __getFieldContent(num, fCode, name, tDTxt, tD, t, tTDTxt, tTD, tTV, flag, flagTxt, flagD, nFlag, nFlagTxt, nFlagD, sFlag, sFlagTxt, sFlagD, lFlag, lFlagTxt, lFlagD, lFlagS, lFlagSV, lFlagSS, vFlag, vFlagTxt, vFlagD, btnFlag, '', true);
	$('#fieldTable tbody').append(tmpStr);

	alert('필드가 추가 되었습니다.');
}

//필드 설정
function __getFieldContent(num, fCode, name, tDTxt, tD, t, tTDTxt, tTD, tTV, flag, flagTxt, flagD, nFlag, nFlagTxt, nFlagD, sFlag, sFlagTxt, sFlagD, lFlag, lFlagTxt, lFlagD, lFlagS, lFlagSV, lFlagSS, vFlag, vFlagTxt, vFlagD, btnFlag, fidKey, orderAt) {
	var tableLayout = $('#fieldTable tfoot').html();

	tableLayout = replaceAll(tableLayout, '{num}',               num);
	tableLayout = replaceAll(tableLayout, '{key}',               fidKey);
	tableLayout = replaceAll(tableLayout, '{field}',             fCode);
	tableLayout = replaceAll(tableLayout, '{name}',              name);

	tableLayout = replaceAll(tableLayout, '{typedisatxt}',       tDTxt);
	tableLayout = replaceAll(tableLayout, '{typedisa}',          tD);
	tableLayout = replaceAll(tableLayout, t,                      'selected');

	tableLayout = replaceAll(tableLayout, '{typetextdisatxt}',   tTDTxt);
	tableLayout = replaceAll(tableLayout, '{typetextdisa}',      tTD);
	tableLayout = replaceAll(tableLayout, '{typetext}',          tTV);

	tableLayout = replaceAll(tableLayout, '{flag}',              flag);
	tableLayout = replaceAll(tableLayout, '{flagdisatxt}',       flagTxt);
	tableLayout = replaceAll(tableLayout, '{flagdisa}',          flagD);

	tableLayout = replaceAll(tableLayout, '{necflag}',           nFlag);
	tableLayout = replaceAll(tableLayout, '{necflagdisatxt}',    nFlagTxt);
	tableLayout = replaceAll(tableLayout, '{necflagdisa}',       nFlagD);

	tableLayout = replaceAll(tableLayout, '{searchflag}',        sFlag);
	tableLayout = replaceAll(tableLayout, '{searchflagdisatxt}', sFlagTxt);
	tableLayout = replaceAll(tableLayout, '{searchflagdisa}',    sFlagD);

	tableLayout = replaceAll(tableLayout, '{listflag}',          lFlag);
	tableLayout = replaceAll(tableLayout, '{listflagdisatxt}',   lFlagTxt);
	tableLayout = replaceAll(tableLayout, '{listflagdisa}',      lFlagD);

	tableLayout = replaceAll(tableLayout, '{listflagd}',         lFlagS);
	tableLayout = replaceAll(tableLayout, '{listflagval}',       lFlagSV);
	tableLayout = replaceAll(tableLayout, '{listflagStyle}',     lFlagSS);
	
	tableLayout = replaceAll(tableLayout, '{viewflag}',          vFlag);
	tableLayout = replaceAll(tableLayout, '{viewflagdisatxt}',   vFlagTxt);
	tableLayout = replaceAll(tableLayout, '{viewflagdisa}',      vFlagD);
	
	tableLayout = replaceAll(tableLayout, '{btn}',               btnFlag);
	
	if(orderAt && lFlag == 'checked') {
		// 목록 순서 변경에 필드 추가
		addSelect('#listOrdr', fCode, name, false);
	}
	
	if(orderAt && vFlag == 'checked') {
		// 보기 순서 변경에 필드 추가
		addSelect('#viewOrdr', fCode, name, false);
	}

	return tableLayout;
}

/**
 * 체크 여부에 따라 보기/등록 순서 변경 필드에서 등록/삭제
 */
function setOrderOpAt(objNm, checked, fCode, name) {
	if(checked) {
		addSelect(objNm, fCode, name, false);
	} else {
		$(objNm).find('option').each(function() {
			if(fCode == $(this).val()) {
				$(this).remove();
			}
		});
	}
}

// 삭제 필드 목록 제거
function __setFieldNum(fCode) {
	setOrderOpAt('#listOrdr', false, fCode, '');
	setOrderOpAt('#viewOrdr', false, fCode, '');
}

/**
 * 카테고리 추가
 */
function addCate() {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	var f = document.forms['frm'];

	if(isEmpty(f.cateNm)) {
		alert('카테고리 명은 필수 입력사항 입니다.');
		f.cateNm.focus();
		return false;
	}
	
	var num = $('#cateTable tbody tr').length + 1;

	var tableLayout = $('#cateTable tfoot').html();

	tableLayout = replaceAll(tableLayout, '{num}',  num);
	tableLayout = replaceAll(tableLayout, '{name}', f.cateNm.value);
	$('#cateTable tbody').append(tableLayout);
	
	f.cateNm.select();
	
	tableId = $('#cateTable').DataTable({
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

/**
 * 탭 변경
 */
function tabChoose(v) {
	$(".tab-pane, .nav-tabs li").removeClass("in");
	$(".tab-pane, .nav-tabs li").removeClass("active");
	$("#tab" + v + ", #tab_link" + v).addClass("in active");
}

/**
 * 스킨 종류 호출
 */
function getSkinList(v, type) {
	if(v != null && v.length > 0) {
		$.ajax({
			url     : "/${ admURI }/bbs/skn/getList.do?key=" + menuKey,
			type    : "POST",
			data    : {
				"bbsSknUpperSn": v
			},
			dataType: "json",
			error   : function(request, status, error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			success:function(result) {
				var rt = result[0];
				if(rt != null && rt.msg != null && rt.msg.length > 0) {
					alert(rt.msg);
					return false;
				}
				
				delSelect("#sknSnDp2");
				$("#skinImg").html("");
				if(result != null && result.length > 0) {
					var bln = true;
					for(var i=0; i<result.length; i++) {
						var item = result[i];
						if(item.useAt == 'Y') {
							var selected = (item.bbsSknSn == type || (type.length == 0 && i == 0))? true : false;
							addSelect("#sknSnDp2", item.bbsSknSn, item.bbsSknNm, selected);
		
							// 스킨 이미지 호출
							if(selected || bln) {
								var src = item.flpth + "/" + item.atchmnflImage;
								var sHTML = "<img src=\""+src+"\" style=\"border: 1px #cccccc solid; max-height:200px;\" />";
								$("#skinImg").html(sHTML);

								bln = false;
							}
						}
					}
				}
			}
		});
	} else {
		delSelect("#sknSnDp2");
		addSelect("#sknSnDp2", "", ":: 스킨 유형 ::", "");
		$("#skinImg").html("");
	}
}

// 썸네일 기능 추가
function addThumb() {
	var tableLayout = $("#thumbList tfoot").html();
	$("#thumbList tbody").append(tableLayout);
}

//제목 지정 삭제
function removeFix(obj) {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	$(obj).parent().parent().remove();
	
	tableId = $('#cateTable').DataTable({
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

// 목록 이동
function goList() {
	f.action = '/${ admURI }/bbs/estbs/list.do?key=' + menuKey;
	f.submit();
}
</script>
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>

			<input type="hidden" name="bbsEstbsSn" value="${ item.bbsEstbsSn }" />
			
			<div id="powList"></div>
			<div id="powRead"></div>
			<div id="powWrite"></div>
			<div id="powReply"></div>
			<div id="powCmnt"></div>
			
			<div class="panel panel-inverse">
				<div class="panel-body panel-form">
	
					<ul class="nav nav-tabs">
						<li id="tab_link1" class="active"><a href="#tab1" data-toggle="tab">기본 설정</a></li>
						<li id="tab_link2" class="tab_link"><a href="#tab2" data-toggle="tab">부가 기능</a></li>
						<li id="tab_link3" class="tab_link"><a href="#tab3" data-toggle="tab">필드 설정</a></li>
						<li id="tab_link4" class="tab_link"><a href="#tab4" data-toggle="tab">목록 순서 변경</a></li>
						<li id="tab_link5" class="tab_link"><a href="#tab5" data-toggle="tab">보기/등록 순서 변경</a></li>
						<li id="tab_link6" class="tab_link"><a href="#tab6" data-toggle="tab">카테고리 관리</a></li>
						<li id="tab_link7" class="tab_link"><a href="#tab7" data-toggle="tab">권한 설정</a></li>
					</ul>
					
					<div class="tab-content">
						<!-- 기본 설정 -->
						<div class="tab-pane fade active in" id="tab1">
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">사용 스킨 * :</label>
								<div class="col-md-6 col-sm-6">
									<label class="select" style="float:left; margin:0;">
										<select id="sknSnDp1" name="sknSnDp1" class="form-control">
											<option value="">:: 스킨 명 ::</option>
											<c:if test="${ !empty sknList1 }">
												<c:forEach var="skin" items="${ sknList1 }">
													<option value="${ skin.bbsSknSn }" <c:if test="${ skin.bbsSknSn eq item.sknSnDp1 }">selected</c:if>>${ skin.bbsSknNm }</option>
												</c:forEach>
											</c:if>
										</select>
									</label>
		
									<label class="select">
										<select id="sknSnDp2" name="sknSnDp2" class="form-control">
											<c:choose>
												<c:when test="${ !empty sknList2 }">
													<c:forEach var="skin" items="${ sknList2 }">
														<option value="${ skin.bbsSknSn }" <c:if test="${ skin.bbsSknSn eq item.sknSnDp2 }">selected</c:if>>${ skin.bbsSknNm }</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<option value="">:: 유형 명 ::</option>
												</c:otherwise>
											</c:choose>
										</select>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">스킨 이미지 :</label>
								<div class="col-md-6 col-sm-6">
									<section id="skinImg" style="min-height:200px;">
										<c:if test="${ !empty sknList2 && !empty item.sknSnDp2 }">
											<c:forEach var="skin" items="${ sknList2 }">
												<c:if test="${ skin.bbsSknSn eq item.sknSnDp2 }">
													<img src="${ skin.flpth }/${ skin.atchmnflImage }" style="border: 1px #cccccc solid; max-height:200px;" />
												</c:if>
											</c:forEach>
										</c:if>
									</section>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">게시판 명 * :</label>
								<div class="col-md-6 col-sm-6">
									<input type="text" id="bbsEstbsNm" name="bbsEstbsNm" class="form-control" value="${ item.bbsEstbsNm }" placeholder="게시판 명 입력" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">리스트 제목 길이 * :</label>
								<div class="col-md-6 col-sm-6">
									<input type="text" id="sjLtLmtt" name="sjLtLmtt" class="form-control" value="${ item.sjLtLmtt }" placeholder="제목 길이 입력" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">페이지 목록 개수 * :</label>
								<div class="col-md-6 col-sm-6">
									<input type="text" id="listIndictCo" name="listIndictCo" class="form-control" value="${ item.listIndictCo }" placeholder="목록 개수 입력" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">새글 표시 기간 * :</label>
								<div class="col-md-6 col-sm-6">
									<label class="input input-inline" style="float:left;">
										<input type="text" id="indictDayNw" name="indictDayNw" class="form-control" value="${ item.indictDayNw }" placeholder="숫자 입력" />
									</label>
									<label class="input input-inline">
										일 이내의 글
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
								
						</div>
						<!-- END : tab1 -->
						
						<!-- 부가기능 -->
						<div class="tab-pane fade" id="tab2">
							<div class="col-md-6">
								<div class="panel panel-inverse">
									<div class="panel-heading">
										<h4 class="panel-title">옵션 등록</h4>
									</div>
									<div class="panel-body panel-form">
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">카테고리 * :</label>
											<div class="col-md-6 col-sm-6">
												<label class="" style="float:left; margin-right:20px;">
													<input type="radio" id="ctgryAtY" name="ctgryAt" value="Y" <c:if test="${ item.ctgryAt eq 'Y' }">checked</c:if> />
													사용
												</label>
												<label class="">
													<input type="radio" id="ctgryAtN" name="ctgryAt" value="N" <c:if test="${ item.ctgryAt eq 'N' }">checked</c:if> />
													사용안함
												</label>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">파일첨부 * :</label>
											<div class="col-md-6 col-sm-6">
												<label class="" style="float:left; margin-right:20px;">
													<input type="radio" id="atchAtY" name="atchAt" value="Y" <c:if test="${ item.atchAt eq 'Y' }">checked</c:if> onclick="changeDisplay('upload', true);" />
													사용
												</label>
												<label class="">
													<input type="radio" id="atchAtN" name="atchAt" value="N" <c:if test="${ item.atchAt eq 'N' }">checked</c:if> onclick="changeDisplay('upload', false);" />
													사용안함
												</label>
											</div>
										</div>
										
										<div class="form-group upload">
											<table class="table table-striped table-bordered">
												<colgroup>
													<col width="120">
													<col >
												</colgroup>
												<tbody>
													<tr>
														<th scope="row">용량 *</th>
														<td>
															<label class="input input-inline" style="float:left;">
																<input name="atchSize" type="text" placeholder="제한용량 입력" value="${ item.atchSize }" style="width:80px;" class="form-control" />
															</label>
															<label class="input input-inline">
																MB
															</label>
														</td>
													</tr>
													
													<tr>
														<th scope="row">첨부파일  유형 *</th>
														<td>
															<input name="" type="text" placeholder="제한용량 입력" value="jpg,jpeg,bmp,png,gif" class="form-control" />
															<p class="note"><strong>Note:</strong> 허용할 확장자 목록</p>
														</td>
													</tr>
		
													<tr>
														<th scope="row">썸네일 기능</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="thumbAtY" name="thumbAt" value="Y" <c:if test="${ item.thumbAt eq 'Y' }">checked</c:if> onclick="changeDisplay('uploadThumb', true);" />
																사용
															</label>
															<label class="">
																<input type="radio" id="thumbAtN" name="thumbAt" value="N" <c:if test="${ item.thumbAt eq 'N' }">checked</c:if> onclick="changeDisplay('uploadThumb', false);" />
																사용안함
															</label>
		
															<p class="note"><strong>Note:</strong> 썸네일코드 'thumb'는 목록 대표 썸네일 입니다.</p>
															<table id="thumbList" class="table table-striped table-bordered uploadThumb">
																<colgroup>
																	<col width="150" />
																	<col />
																	<col width="80" />
																</colgroup>
																<thead>
																	<tr>
																		<th>썸네일코드</th>
																		<th>사이즈</th>
																		<th><button type="button" class="btn btn-default btn-sm" onclick="addThumb();"><i class="fa fa-plus"></i> 추가</button></th>
																	</tr>
																</thead>
																<tbody>
																	<c:choose>
																		<c:when test="${ !empty thumbList }">
																			<c:forEach var="thumb" items="${ thumbList }">
																				<tr>
																					<td>
																						<input type="hidden" name="bbsThumbSn" value="${ thumb.bbsThumbSn }" />
																						<input name="thumbNm" type="text" placeholder="썸네일코드" class="form-control" value="${ thumb.thumbNm }" <c:if test="${ thumb.thumbNm eq 'thumb' }">readonly</c:if> /></td>
																					<td>
																						<div class="input-group">
																							<input name="widthSize" type="text" placeholder="가로길이" class="form-control" value="${ thumb.widthSize }" />
																							<span class="input-group-addon">x</span>
																							<input name="vrticlSize" type="text" placeholder="세로길이" class="form-control" value="${ thumb.vrticlSize }" />
																						</div>
																					</td>
																					<td><button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button></td>
																				</tr>
																			</c:forEach>
																		</c:when>
																		<c:otherwise>
																			<tr>
																				<td>
																					<input type="hidden" name="bbsThumbSn" value="" />
																					<input name="thumbNm" type="text" placeholder="썸네일코드" class="form-control" value="thumb" readonly />
																				</td>
																				<td>
																					<div class="input-group">
																						<input name="widthSize" type="text" placeholder="가로길이" class="form-control" value="" />
																						<span class="input-group-addon">x</span>
																						<input name="vrticlSize" type="text" placeholder="세로길이" class="form-control" value="" />
																					</div>
																				</td>
																				<td><button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button></td>
																			</tr>
																		</c:otherwise>
																	</c:choose>
																</tbody>
																<tfoot class="hide">
																	<tr>
																		<td>
																			<input type="hidden" name="bbsThumbSn" value="" />
																			<input name="thumbNm" type="text" placeholder="썸네일명 입력" class="form-control" />
																		</td>
																		<td>
																			<div class="input-group">
																				<input name="widthSize" type="text" placeholder="가로길이" class="form-control" />
																				<span class="input-group-addon">x</span>
																				<input name="vrticlSize" type="text" placeholder="세로길이" class="form-control" />
																			</div>
																		</td>
																		<td><button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button></td>
																	</tr>
																</tfoot>
															</table>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">댓글 기능 * :</label>
											<div class="col-md-6 col-sm-6">
												<label class="" style="float:left; margin-right:20px;">
													<input type="radio" id="cmAtY" name="cmAt" value="Y" <c:if test="${ item.cmAt eq 'Y' }">checked</c:if> onclick="changeDisplay('cmnt', true);" />
													사용
												</label>
												<label class="">
													<input type="radio" id="cmAtN" name="cmAt" value="N" <c:if test="${ item.cmAt eq 'N' }">checked</c:if> onclick="changeDisplay('cmnt', false);" />
													사용안함
												</label>
											</div>
										</div>
										
										<div class="form-group cmnt">
											<table class="table table-striped table-bordered" summary="" role="grid" aria-describedby="data-table_info">
												<colgroup>
													<col width="120">
													<col >
												</colgroup>
												<tbody>
													<tr>
														<th scope="row">답변 기능</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="cmAnswerAtY" name="cmAnswerAt" value="Y" <c:if test="${ item.cmAnswerAt eq 'Y' }">checked</c:if> />
																사용
															</label>
															<label class="">
																<input type="radio" id="cmAnswerAtN" name="cmAnswerAt" value="N" <c:if test="${ item.cmAnswerAt eq 'N' }">checked</c:if> />
																사용안함
															</label>
														</td>
													</tr>
		
													<tr>
														<th scope="row">사이즈</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="cmLtLmttAtY" name="cmLtLmttAt" value="Y" <c:if test="${ item.cmLtLmttAt eq 'Y' }">checked</c:if> onclick="changeDisplay('cmLtLmttSize', true);" />
																사용
															</label>
															<label class="">
																<input type="radio" id="cmLtLmttAtN" name="cmLtLmttAt" value="N" <c:if test="${ item.cmLtLmttAt eq 'N' }">checked</c:if> onclick="changeDisplay('cmLtLmttSize', false);" />
																사용안함
															</label>
															
															<p class="cmLtLmttSize">
																<label class="input input-inline" style="float:left;">
																	<input name="cmLtLmttSize" type="text" class="form-control" placeholder="길이 입력" value="${ item.cmLtLmttSize }" style="width:80px;" />
																</label>
																<label class="input input-inline">
																	Byte (글자수 제한, 알파벳은 1자당 1Byte, 한글은 1자당 2Byte)
																</label>
															</p>
														</td>
													</tr>
		
													<tr>
														<th scope="row">이름표시</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="cmNmchangeAtY" name="cmNmchangeAt" value="Y" <c:if test="${ item.cmNmchangeAt eq 'Y' }">checked</c:if> />
																보이기(직접 입력 가능)
															</label>
															<label class="">
																<input type="radio" id="cmNmchangeAtN" name="cmNmchangeAt" value="N" <c:if test="${ item.cmNmchangeAt eq 'N' }">checked</c:if> />
																안보이기(*작성자 이름 표현에 설정된 명칭으로 표기)
															</label>
														</td>
													</tr>
		
													<tr>
														<th scope="row">페이지 기능</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="cmPgngAtY" name="cmPgngAt" value="Y" <c:if test="${ item.cmPgngAt eq 'Y' }">checked</c:if> onclick="changeDisplay('cmntPage', true);" />
																사용
															</label>
															<label class="">
																<input type="radio" id="cmPgngAtN" name="cmPgngAt" value="N" <c:if test="${ item.cmPgngAt eq 'N' }">checked</c:if> onclick="changeDisplay('cmntPage', false);" />
																사용안함
															</label>
															
															<p class="cmntPage">
																<label class="input input-inline" style="float:left; padding-top:6px;">
																	페이지당 목록수:
																</label>
																<label class="input input-inline">
																	<input name="cmListIndictCo" type="text" class="form-control" placeholder="길이 입력" value="${ item.cmListIndictCo }" style="width:80px;" />
																</label>
															</p>
														</td>
													</tr>
		
													<tr>
														<th scope="row">정렬</th>
														<td>
															<label class="" style="float:left; margin-right:20px;">
																<input type="radio" id="cmSortAsc" name="cmSort" value="ASC" <c:if test="${ item.cmSort eq 'ASC' }">checked</c:if> />
																오름차순(최근글이 아래로 출력)
															</label>
															<label class="">
																<input type="radio" id="cmSortDesc" name="cmSort" value="DESC" <c:if test="${ item.cmSort eq 'DESC' }">checked</c:if> />
																내림차순(최근글이 위로 출력)
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">작성자 이름 표현방식 * :</label>
											<div class="col-md-6 col-sm-6">
												<select id="registerNmTy" name="registerNmTy" class="form-control">
													<c:if test="${ !empty regNmTypeList }">
														<c:forEach var="code" items="${ regNmTypeList }">
															<option value="${ code.cd }" <c:if test="${ code.cd eq item.registerNmTy }">selected</c:if>>${ code.nm }</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">조회수 중복사용 * :</label>
											<div class="col-md-6 col-sm-6">
												<label class="" style="float:left; margin-right:20px;">
													<input type="radio" id="rdcntDplctAtY" name="rdcntDplctAt" value="Y" <c:if test="${ item.rdcntDplctAt eq 'Y' }">checked</c:if> />
													조회마다
												</label>
												<label class="">
													<input type="radio" id="rdcntDplctAtN" name="rdcntDplctAt" value="N" <c:if test="${ item.rdcntDplctAt eq 'N' }">checked</c:if> />
													중복방지
												</label>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-4 col-sm-4" style="padding-top:15px;">사용여부 옵션 :</label>
											<div class="col-md-6 col-sm-6">
												<label class="" style="float:left; margin-right:20px;">
													<input type="checkbox" name="noticeAt" value="Y" <c:if test="${ item.noticeAt eq 'Y' }">checked</c:if> />
													공지글
												</label>
												
												<label class="" style="float:left; margin-right:20px;">
													<input type="checkbox" name="secretAt" value="Y" <c:if test="${ item.secretAt eq 'Y' }">checked</c:if> />
													비밀글
												</label>
												
												<label class="" style="float:left; margin-right:20px;">
													<input type="checkbox" name="answerAt" value="Y" <c:if test="${ item.answerAt eq 'Y' }">checked</c:if> />
													답변
												</label>
												
												<label class="" style="float:left; margin-right:20px;">
													<input type="checkbox" name="secretAtAlways" value="Y" <c:if test="${ item.secretAtAlways eq 'Y' }">checked</c:if> />
													모든 게시글 비밀글 설정
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="panel panel-inverse">
									<div class="panel-heading">
										<h4 class="panel-title">태그 등록</h4>
									</div>
									<div class="panel-body panel-form">
										<div class="form-group">
											<div class="col-md-12 col-sm-12">
												<pre><strong>상단 태그</strong></pre>
												<label>
													<input type="checkbox" name="hderCntntsAt" value="Y" <c:if test="${ item.hderCntntsAt eq 'Y' }">checked</c:if> />
													콘텐츠 내용으로 대체
												</label>
												<textarea id="hderTag" name="hderTag" class="form-control" rows="10">${ item.hderTag }</textarea>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-md-12 col-sm-12">
												<pre><strong>하단 태그</strong></pre>
												<label>
													<input type="checkbox" name="footCntntsAt" value="Y" <c:if test="${ item.footCntntsAt eq 'Y' }">checked</c:if> />
													콘텐츠 내용으로 대체
												</label>
												<textarea id="footTag" name="footTag" class="form-control" rows="10">${ item.footTag }</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
						
						<!-- 필드 설정 -->
						<div class="tab-pane fade" id="tab3">
							<div class="form-group table-responsive">
								<table class="table table-striped table-bordered">
									<colgroup>
										<col width="200">
										<col width="180">
										<col width="280">
										<col width="280">
										<col width="250">
									</colgroup>
									<thead>
										<tr>
											<th scope="row">항목명 *</th>
											<th scope="row">항목속성</th>
											<th scope="row">타입별 항목</th>
											<th scope="row">스타일</th>
											<th scope="row">기능</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="text" name="fidNm" placeholder="항목명 입력" value="" class="form-control" />
											</td>
											<td>
												<select name="fidType" class="form-control">
													<c:if test="${ !empty fieldList_cd }">
														<c:forEach var="code" items="${ fieldList_cd }">
															<option value="${ code.cd }">${ code.nm }</option>
														</c:forEach>
													</c:if>
												</select>
											</td>
											<td>
												<input type="text" name="fidTypeText" placeholder="타입 항목명 입력" value="" class="form-control" />
												<p class="note"><strong>Note:</strong> 콤마(,)로 구분</p>
											</td>
											<td>
												<input type="text" name="fidListStyle" placeholder="스타일 입력" value="" class="form-control" />
											</td>
											<td>
												<label style="float:left; margin-right:20px;"><input type="checkbox" name="fidAt" value="Y" /> 사용</label>
												<label style="float:left; margin-right:20px;"><input type="checkbox" name="fidNecAt" value="Y" /> 필수</label>
												<label style="float:left; margin-right:20px;"><input type="checkbox" name="fidShAt" value="Y" /> 검색</label>
												<label style="float:left; margin-right:20px;"><input type="checkbox" name="fidViewAt" value="Y" /> 보기</label>
												<label style="float:left; margin-right:20px;"><input type="checkbox" name="fidListAt" value="Y" onclick="changeDisplay('listSize', this.checked);" /> 목록</label>
		
												<input type="text" name="fidListSize" class="listSize form-control" style="display:none;" placeholder="목록표시 사이즈 입력" value="" />
											</td>
										</tr>
									</tbody>
								</table>
								
								<div style="float:left; width:100%; text-align:right; margin-bottom:10px;">
									<button type="button" class="btn btn-default btn-sm" onclick="addField();"><i class="fa fa-plus"></i> 추가</button>
								</div>
							</div>
							
							<div class="form-group table-responsive">
								<table id="fieldTable" class="table table-striped table-bordered">
									<colgroup>
										<col width="60">
										<col width="200">
										<col width="180">
										<col width="280">
										<col width="280">
										<col width="250">
										<col width="80">
									</colgroup>
									<thead>
										<tr>
											<th scope="row">번호</th>
											<th scope="row">항목명</th>
											<th scope="row">항목속성</th>
											<th scope="row">타입별 항목</th>
											<th scope="row">스타일</th>
											<th scope="row">기능</th>
											<th scope="row">버튼</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
		
									<tfoot class="hide">
										<tr>
											<th scope="col">{num}</th>
											<td>
												<input type="hidden" name="bbsFieldSn" value="{key}" />
												<input type="hidden" name="bbsFieldCode" class="fcode" value="{field}" />
												<input type="text" name="bbsFieldNm" class="form-control" placeholder="항목명 입력" value="{name}" />
											</td>
											<td>
												<input type="hidden" name="fieldTyActvtyAt" value="{typedisatxt}" />
												<select name="fieldTy" {typedisa} class="form-control">
													<c:if test="${ !empty fieldList_cd }">
														<c:forEach var="code" items="${ fieldList_cd }">
															<option value="${ code.cd }" {type${ code.cd }}>${ code.nm }</option>
														</c:forEach>
													</c:if>
												</select>
											</td>
											<td>
												<input type="hidden" name="fieldTyTextActvtyAt" value="{typetextdisatxt}" />
												<input type="text" name="fieldTyText" placeholder="타입 항목명 입력" class="form-control" value="{typetext}" {typetextdisa} />
												<p class="note"><strong>Note:</strong> 콤마(,)로 구분</p>
											</td>
											<td>
												<input type="text" name="fieldListStyle" placeholder="스타일 입력" class="form-control" value="{listflagStyle}" />
											</td>
											<td>
												<input type="hidden" name="fieldUseActvtyAt" value="{flagdisatxt}" />
												<input type="hidden" name="fieldUseAt" class="flag" />
												<label style="float:left; margin-right:20px;">
													<input type="checkbox" name="fieldUseAts" class="flags" value="Y" {flag} {flagdisa} /> 사용
												</label>
												
												<input type="hidden" name="fieldEssntlActvtyAt" value="{necflagdisatxt}" />
												<input type="hidden" name="fieldEssntlAt" class="necFlag" />
												<label style="float:left; margin-right:20px;">
													<input type="checkbox" name="fieldEssntlAts" class="necFlags" value="Y" {necflag} {necflagdisa} /> 필수
												</label>
												
												<input type="hidden" name="fieldSearchActvtyAt" value="{searchflagdisatxt}" />
												<input type="hidden" name="fieldSearchAt" class="shFlag" />
												<label style="float:left; margin-right:20px;">
													<input type="checkbox" name="fieldSearchAtS" class="shFlags" value="Y" {searchflag} {searchflagdisa} /> 검색
												</label>
												
												<input type="hidden" name="fieldViewActvtyAt" value="{viewflagdisatxt}" />
												<input type="hidden" name="fieldViewAt" class="viewFlag" />
												<label style="float:left; margin-right:20px;">
													<input type="checkbox" name="fieldViewAts" class="viewFlags" value="Y" {viewflag} {viewflagdisa} onclick="setOrderOpAt('#viewOrdr', this.checked, '{field}', '{name}');" /> 보기
												</label>
												
												<input type="hidden" name="fieldListActvtyAt" value="{listflagdisatxt}" />
												<input type="hidden" name="fieldListAt" class="listFlag" />
												<label style="float:left; margin-right:20px;">
													<input type="checkbox" name="fieldListAts" class="listFlags" value="Y" {listflag} {listflagdisa} onclick="changeDisplay('listSize{num}', this.checked); setOrderOpAt('#listOrdr', this.checked, '{field}', '{name}');" /> 목록
												</label>
		
												<input type="text" name="fieldListSize" class="form-control listSize{num}" {listflagd} placeholder="목록표시 사이즈 입력" value="{listflagval}" />
											</td>
											<td>
												<button type="button" class="btn btn-danger btn-sm {btn}" onclick="removeFix(this); __setFieldNum('{field}');"><i class="fa fa-times"></i> 삭제</button>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
						
						<!-- 목록 순서 변경 -->
						<div class="tab-pane fade" id="tab4">
							<div class="form-group">
								<select id="listOrdr" name="listOrdr" class="form-control" multiple="multiple" style="height:300px;">
									<c:if test="${ !empty fieldList_l }">
										<c:forEach var="list" items="${ fieldList_l }">
											<option value="${ list.bbsFieldCode }">${ list.bbsFieldNm }</option>
										</c:forEach>
									</c:if>
								</select>
								<button type="button" class="btn btn-grey" onclick="selectUps('listOrdr');"><i class="fa fa-angle-double-up"></i> 맨 위로</button>
								<button type="button" class="btn btn-grey" onclick="selectUp('listOrdr');"><i class="fa fa-angle-up"></i> 위로</button>
								<button type="button" class="btn btn-grey" onclick="selectDn('listOrdr');"><i class="fa fa-angle-down"></i> 아래로</button>
								<button type="button" class="btn btn-grey" onclick="selectDns('listOrdr');"><i class="fa fa-angle-double-down"></i> 맨 아래로</button>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
						
						<!-- 보기/등록 순서 변경 -->
						<div class="tab-pane fade" id="tab5">
							<div class="form-group">
								<select id="viewOrdr" name="viewOrdr" class="form-control" multiple="multiple" style="height:300px;">
									<c:if test="${ !empty fieldList_v }">
										<c:forEach var="list" items="${ fieldList_v }">
											<option value="${ list.bbsFieldCode }">${ list.bbsFieldNm }</option>
										</c:forEach>
									</c:if>
								</select>
								<button type="button" class="btn btn-grey" onclick="selectUps('viewOrdr');"><i class="fa fa-angle-double-up"></i> 맨 위로</button>
								<button type="button" class="btn btn-grey" onclick="selectUp('viewOrdr');"><i class="fa fa-angle-up"></i> 위로</button>
								<button type="button" class="btn btn-grey" onclick="selectDn('viewOrdr');"><i class="fa fa-angle-down"></i> 아래로</button>
								<button type="button" class="btn btn-grey" onclick="selectDns('viewOrdr');"><i class="fa fa-angle-double-down"></i> 맨 아래로</button>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
						
						<!-- 카테고리 관리 -->
						<div class="tab-pane fade" id="tab6">
							<div class="form-group">
								<table class="table table-striped table-bordered">
									<colgroup>
										<col >
										<col width="250">
									</colgroup>
									<thead>
										<tr>
											<th scope="row">카테고리 명 *</th>
											<th scope="row">기능</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="text" name="cateNm" placeholder="카테고리 명 입력" value="" class="form-control" />
											</td>
											<td>
												<button type="button" class="btn btn-default btn-sm" onclick="addCate();"><i class="fa fa-plus"></i> 추가</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div class="form-group">
								<table id="cateTable" data-reorder="true" class="table table-striped table-bordered">
									<colgroup>
										<col width="60">
										<col >
										<col width="80">
									</colgroup>
									<thead>
										<tr>
											<th>순서</th>
											<th scope="row">카테고리 명</th>
											<th scope="row">버튼</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ !empty cateList }">
											<c:forEach var="cate" items="${ cateList }" varStatus="idx">
												<tr>
													<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
													<td>
														<input type="hidden" name="bbsCtgrySn" value="${ cate.bbsCtgrySn }" />
														<input type="text" name="ctgryNm" class="form-control" placeholder="카테고리 명 입력" value="${ cate.ctgryNm }" />
													</td>
													<td>
														<button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
		
									<tfoot class="hide">
										<tr>
											<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
											<td>
												<input type="hidden" name="bbsCtgrySn" value="" />
												<input type="text" name="ctgryNm" class="form-control" placeholder="카테고리 명 입력" value="{name}" />
											</td>
											<td>
												<button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
						
						<!-- 권한 생성 -->
						<div class="tab-pane fade" id="tab7">
							<div class="form-group">
								<table class="table table-striped table-bordered">
									<caption>정보 등록</caption>
									<colgroup>
										<col width="16%">
										<col >
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">목록 보기 권한</th>
											<td>
												<c:if test="${ !empty roleList }">
													<c:forEach var="code" items="${ roleList }">
														<c:set var="displayListPow" value="false" />
														<c:if test="${ code.cd eq 'A' }">
															<c:set var="displayListPow" value="true" />
														</c:if>
														
														<label class="" style="float:left; margin-right:20px;">
															<input type="radio" name="authorList" value="${ code.cd }" <c:if test="${ item.authorList eq code.cd }">checked</c:if> onclick="changeDisplay('listPow', ${ displayListPow });" />
															${ code.nm }
														</label>
													</c:forEach>
												</c:if>
		
												<table class="table table-striped table-bordered listPow" summary="" role="grid" aria-describedby="data-table_info">
													<colgroup>
														<col >
													</colgroup>
													<tbody>
														<tr>
															<td>
																<label class="select select-inline" style="float:left; width:200px;">
																	회원 그룹
																	<select id="aListPow" name="aListPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'LIST' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
																<div class="input input-inline" style="float:left; padding:50px 5px 0 5px;">
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('aListPow', 'bListPow');"><i class="fa fa-chevron-right"></i></button><br/><br/>
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('bListPow', 'aListPow');"><i class="fa fa-chevron-left"></i></button>
																</div>
																<label class="select select-inline" style="float:left; width:200px;">
																	접근 권한
																	<select id="bListPow" name="bListPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'LIST' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ !roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										
										<tr>
											<th scope="row">읽기 권한</th>
											<td>
												<c:if test="${ !empty roleList }">
													<c:forEach var="code" items="${ roleList }">
														<c:set var="displayReadPow" value="false" />
														<c:if test="${ code.cd eq 'A' }">
															<c:set var="displayReadPow" value="true" />
														</c:if>
														
														<label class="" style="float:left; margin-right:20px;">
															<input type="radio" name="authorRedng" value="${ code.cd }" <c:if test="${ item.authorRedng eq code.cd }">checked</c:if> onclick="changeDisplay('readPow', ${ displayReadPow });" />
															${ code.nm }
														</label>
													</c:forEach>
												</c:if>
		
												<table class="table table-striped table-bordered readPow" summary="" role="grid" aria-describedby="data-table_info">
													<colgroup>
														<col >
													</colgroup>
													<tbody>
														<tr>
															<td>
																<label class="select select-inline" style="float:left; width:200px;">
																	회원 그룹
																	<select id="aReadPow" name="aReadPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'READ' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
																<div class="input input-inline" style="float:left; padding:50px 5px 0 5px;">
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('aReadPow', 'bReadPow');"><i class="fa fa-chevron-right"></i></button><br/><br/>
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('bReadPow', 'aReadPow');"><i class="fa fa-chevron-left"></i></button>
																</div>
																<label class="select select-inline" style="float:left; width:200px;">
																	접근 권한
																	<select id="bReadPow" name="bReadPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'READ' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ !roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										
										<tr>
											<th scope="row">쓰기 권한</th>
											<td>
												<c:if test="${ !empty roleList }">
													<c:forEach var="code" items="${ roleList }">
														<c:set var="displayWritePow" value="false" />
														<c:if test="${ code.cd eq 'A' }">
															<c:set var="displayWritePow" value="true" />
														</c:if>
														
														<label class="" style="float:left; margin-right:20px;">
															<input type="radio" name="authorRegist" value="${ code.cd }" <c:if test="${ item.authorRegist eq code.cd }">checked</c:if> onclick="changeDisplay('writePow', ${ displayWritePow });" />
															${ code.nm }
														</label>
													</c:forEach>
												</c:if>
		
												<table class="table table-striped table-bordered writePow" summary="" role="grid" aria-describedby="data-table_info">
													<colgroup>
														<col >
													</colgroup>
													<tbody>
														<tr>
															<td>
																<label class="select select-inline" style="float:left; width:200px;">
																	회원 그룹
																	<select id="aWritePow" name="aWritePow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'WRITE' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
																<div class="input input-inline" style="float:left; padding:50px 5px 0 5px;">
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('aWritePow', 'bWritePow');"><i class="fa fa-chevron-right"></i></button><br/><br/>
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('bWritePow', 'aWritePow');"><i class="fa fa-chevron-left"></i></button>
																</div>
																<label class="select select-inline" style="float:left; width:200px;">
																	접근 권한
																	<select id="bWritePow" name="bWritePow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'WRITE' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ !roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										
										<tr>
											<th scope="row">답변 권한</th>
											<td>
												<c:if test="${ !empty roleList }">
													<c:forEach var="code" items="${ roleList }">
														<c:set var="displayReplyPow" value="false" />
														<c:if test="${ code.cd eq 'A' }">
															<c:set var="displayReplyPow" value="true" />
														</c:if>
														
														<label class="" style="float:left; margin-right:20px;">
															<input type="radio" name="authorAnswer" value="${ code.cd }" <c:if test="${ item.authorAnswer eq code.cd }">checked</c:if> onclick="changeDisplay('replyPow', ${ displayReplyPow });" />
															${ code.nm }
														</label>
													</c:forEach>
												</c:if>
		
												<table class="table table-striped table-bordered replyPow" summary="" role="grid" aria-describedby="data-table_info">
													<colgroup>
														<col >
													</colgroup>
													<tbody>
														<tr>
															<td>
																<label class="select select-inline" style="float:left; width:200px;">
																	회원 그룹
																	<select id="aReplyPow" name="aReplyPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'REPLY' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
																<div class="input input-inline" style="float:left; padding:50px 5px 0 5px;">
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('aReplyPow', 'bReplyPow');"><i class="fa fa-chevron-right"></i></button><br/><br/>
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('bReplyPow', 'aReplyPow');"><i class="fa fa-chevron-left"></i></button>
																</div>
																<label class="select select-inline" style="float:left; width:200px;">
																	접근 권한
																	<select id="bReplyPow" name="bReplyPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'REPLY' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ !roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										
										<tr>
											<th scope="row">댓글 권한</th>
											<td>
												<c:if test="${ !empty roleList }">
													<c:forEach var="code" items="${ roleList }">
														<c:set var="displayCmntPow" value="false" />
														<c:if test="${ code.cd eq 'A' }">
															<c:set var="displayCmntPow" value="true" />
														</c:if>
														
														<label class="" style="float:left; margin-right:20px;">
															<input type="radio" name="authorCm" value="${ code.cd }" <c:if test="${ item.authorCm eq code.cd }">checked</c:if> onclick="changeDisplay('cmntPow', ${ displayCmntPow });" />
															${ code.nm }
														</label>
													</c:forEach>
												</c:if>
		
												<table class="table table-striped table-bordered cmntPow" summary="" role="grid" aria-describedby="data-table_info">
													<colgroup>
														<col >
													</colgroup>
													<tbody>
														<tr>
															<td>
																<label class="select select-inline" style="float:left; width:200px;">
																	회원 그룹
																	<select id="aCmntPow" name="aCmntPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'CMNT' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
																<div class="input input-inline" style="float:left; padding:50px 5px 0 5px;">
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('aCmntPow', 'bCmntPow');"><i class="fa fa-chevron-right"></i></button><br/><br/>
																	<button type="button" class="btn btn-default" onclick="changeSelectedOp('bCmntPow', 'aCmntPow');"><i class="fa fa-chevron-left"></i></button>
																</div>
																<label class="select select-inline" style="float:left; width:200px;">
																	접근 권한
																	<select id="bCmntPow" name="bCmntPow" class="form-control custom-scroll" style="height:150px; overflow-y:scroll;" multiple>
																		<c:if test="${ !empty roleWwwList }">
																			<c:forEach var="role" items="${ roleWwwList }">
																				<c:set var="roleChk" value="true" />
																				
																				<c:if test="${ !empty bbsRoleList }">
																					<c:forEach var="acc" items="${ bbsRoleList }">
																						<c:if test="${ acc.roleTy eq 'CMNT' && acc.roleSn eq role.roleSn }">
																							<c:set var="roleChk" value="false" />
																						</c:if>
																					</c:forEach>
																				</c:if>
																				
																				<c:if test="${ !roleChk }">
																					<option value="${ role.roleSn }">${ role.roleNm }</option>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</select>
																</label>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"></label>
								<div class="col-md-6 col-sm-6">
									<button type="submit" class="btn btn-primary">저장</button>
									<button type="button" class="btn btn-default" onclick="goList();">목록</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>