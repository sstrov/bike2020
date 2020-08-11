/**
 * jQuery 날짜 형식 플러그인 옵션
 *   - 한글화, 년/월 선택
 *   - 선택년 현재년 -100년까지
 *   - ToDay/닫기 버튼 활성화
 */
var option = {
	showMonthAfterYear : true,
	dateFormat         : 'yy-mm-dd',
	monthNamesShort    : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	dayNamesMin        : ['일','월','화','수','목','금','토'],
	changeMonth        : true,
	changeYear         : true,
	gotoCurrent        : false,
	yearRange          : '-70:+5',
	closeText          : 'Close',
	prevText           : '<i class="fa fa-chevron-left"></i>',
	nextText           : '<i class="fa fa-chevron-right"></i>',
	showButtonPanel    : true,
	onSelect:function(){
		$(this).prev('input[type="hidden"]').val(this.value.replace(/-/g,""));
	}
};

var summNoteOp = {
	height: 500,
	toolbar: [
		// [groupName, [list of button]]
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['view', ['fullscreen', 'codeview', 'undo', 'redo']]
	]/*,
	callbacks: {
		onBlurCodeview: function(contents, $editable) {
			$(this).html(contents);
		}
	}*/
};

/**
 * 배열의 모든 정보 조회
 */
var print_r = function(tar){ 
    var str = ''; 
    for (var p in tar) { 
        var tmp = tar[p]; 
        if (tmp != null && tmp.toString != null && tmp.toString() != ''){ 
            if (str != '') str += ", "; 
            str += p.toString() + " = " + tmp.toString(); 
        } 
    } 
    return str; 
};

/**
 * 문자열의 특수문자로 치환
 * @param {Object} str
 * @param {Object} s1
 * @param {Object} s2
 */
function replaceAll(str, s1, s2) {
	return str.split(s1).join(s2);
}

/**
 * 날짜 형식 변수 숫자만 추출
 */
function getDateReplaceText(str) {
	str = replaceAll(str, "-", "");
	str = replaceAll(str, ":", "");
	str = replaceAll(str, " ", "");
	return str;
}

/**
 * 금액 세자리 콤마넣기
 */
function formatCurrency(x)
{
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/**
 * 입력값에서 콤마를 없앤다.
 */
function removeComma(str) {
	return str.replace(/,/gi,"");
}

/**
 * INPUT 유효성 검사
 */
function isEmpty(input) {
	var checkType = false;
	var result = false;

	if(input.length) {
		if(input.length > 0) {
			if(input[0].type == "checkbox" || input[0].type == "radio") {
				checkType = true;
			}
		}
	}

	if(checkType) {
		for(var i=0; i<input.length; i++) {
			if(!input[i].checked) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
	} else {
		if (input.value == null || input.value.replace(/ /gi,"") == "") {
			result = true;
		}
	}

	return result;
}

// 아이디 정규식 유효성 검사
function idCheck(v) {
	var regularID = /^[a-zA-Z]+[_a-zA-Z0-9]{1,20}$/;
	return regularID.test(v);
}

// 사이트 아이디 정규식 유효성 검사
function siteIdCheck(v) {
	var regularID = /^[a-z]+[_a-z0-9]{1,20}$/;
	return regularID.test(v);
}

// 도메인 정규식 유효성 검사
function regUrlType(v) {
	var regularID = /^(((http(s?))\:\/\/)?)([0-9a-zA-Z\-]+\.)+[0-9a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?/;
	return regularID.test(v);
}

// 이메일 정규식 유효성 검사
function emailCheck(v) {
	var regularID =/[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	return regularID.test(v);
}

/**
 * 전체 선택 체크 박스 선택시 리스트 체크박스 전체 채크
 */
function toggleCheck(obj) {
	
	if($('.chkKey') == undefined) {

	} else {
		$('.chkKey').each(function(idx, item) {
			$(this).prop('checked', obj.checked);
		});
	}
}

/** 
 * 특정 엘리먼트 ID의 셀렉트 박스에 정보 추가
 * objNm : 엘리먼트 ID
 * key : 옵션의 Key 값
 * name : 옵션의 Value 값
 * selected : 동일값 체크 true/false
 */
function addSelect(objNm, cd, cdNm, selected) {
	var addOpt = document.createElement('option');
	
	addOpt.value    = cd;
	addOpt.selected = selected;
	addOpt.appendChild(document.createTextNode(cdNm));
	$(objNm).append(addOpt);
}

/**
 * 특정 엘리먼트 ID의 셀렉트 박스를 초기화
 */
function delSelect(objNm) {
	$(objNm).find("option").each(function(){
		$(this).remove();
	});
}

/**
 * 선택된 셀렉트 정보를 한칸 위로 이동
 */
function selectUp(objNm) {
	var form = document.getElementById(objNm);
	var selectIdx = form.selectedIndex;
	if(selectIdx < 0) {
		alert('필드 항목을 선택해 주세요.');
		return false;
	}
	
	if(selectIdx) {
		var temp = form.options[selectIdx].text;
		form.options[selectIdx].text = form.options[selectIdx - 1].text;
		form.options[selectIdx - 1].text = temp;
		
		var tempIdxVal = form.options[selectIdx].value;
		form.options[selectIdx].value = form.options[selectIdx - 1].value;
		form.options[selectIdx - 1].value = tempIdxVal;
		form.selectedIndex = selectIdx - 1;
	}
}

/**
 * 선택된 셀렉트 정보를 맨 위로 이동
 */
function selectUps(objNm) {
	var form = document.getElementById(objNm);
	var selectIdx = form.selectedIndex;
	if(selectIdx < 0) {
		alert('필드 항목을 선택해 주세요.');
		return false;
	}
	
	if(selectIdx) {
		var temp = form.options[selectIdx].text;
		var tempIdxVal = form.options[selectIdx].value;
		for(var i=selectIdx-1; i>=0; i--) {
			form.options[i + 1].text = form.options[i].text;
			form.options[i + 1].value = form.options[i].value;
		}
		form.options[0].text  = temp;
		form.options[0].value = tempIdxVal;
		form.selectedIndex = 0;
	}
}

/**
 * 선택된 셀렉트 정보를 한칸 아래로 이동
 */
function selectDn(objNm) {
	var form = document.getElementById(objNm);
	var selectIdx = form.selectedIndex;
	if(selectIdx < 0) {
		alert('필드 항목을 선택해 주세요.');
		return false;
	}
	
	if(selectIdx + 1 < form.length) {
		var temp = form.options[selectIdx + 1].text;
		form.options[selectIdx + 1].text = form.options[selectIdx].text;
		form.options[selectIdx].text = temp;
		
		var tempIdxVal = form.options[selectIdx + 1].value;
		form.options[selectIdx + 1].value = form.options[selectIdx].value;
		form.options[selectIdx].value = tempIdxVal;
		form.selectedIndex = selectIdx + 1;
	}
}

/**
 * 선택된 셀렉트 정보를 맨 아래로 이동
 */
function selectDns(objNm) {
	var form         = document.getElementById(objNm);
	var selectIdx    = form.selectedIndex;
	var selectLength = form.length - 1;
	if(selectIdx < 0) {
		alert('필드 항목을 선택해 주세요.');
		return false;
	}
	
	if(selectIdx < selectLength) {
		var temp = form.options[selectIdx].text;
		var tempIdxVal = form.options[selectIdx].value;
		for(var i=selectIdx + 1; i<=selectLength; i++) {
			form.options[i - 1].text = form.options[i].text;
			form.options[i - 1].value = form.options[i].value;
		}
		form.options[selectLength].text = temp;
		form.options[selectLength].value = tempIdxVal;
		form.selectedIndex = selectLength;
	}
}

/**
 * timymce 웹 에디터 오픈
 * @param id
 */
function setTmEdirot(id) {
	
	tinymce.init({
		selector          : "#" + id,
		theme             : "modern",
		forced_root_block : false,
		force_br_newlines : true,
		force_p_newlines  : false,
		relative_urls     : false,
		plugins: [
			"advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
			"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
			"save table contextmenu directionality emoticons template paste textcolor importcss"
		],
		content_css: "css/development.css",
		add_unload_trigger: false,
		toolbar: "insertfile undo redo | styleselect | fontselect | fontsizeselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons table",
		image_advtab: true,
		style_formats: [
			{title: 'Bold text', format: 'h1'},
			{title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
			{title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
			{title: 'Example 1', inline: 'span', classes: 'example1'},
			{title: 'Example 2', inline: 'span', classes: 'example2'},
			{title: 'Table styles'},
			{title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
		],
		template_replace_values : {
			username : "Jack Black"
		},
		template_preview_replace_values : {
			username : "Preview user name"
		},
		templates: [
			{title: 'Some title 1', description: 'Some desc 1', content: '<strong class="red">My content: {$username}</strong>'},
			{title: 'Some title 2', description: 'Some desc 2', url: 'development.html'}
		],
		// 허용 Tag 추가
		valid_elements: "*[*]",
		valid_children : "+body[style]",
		cleanup : false,
		inline_style : true,
		spellchecker_callback: function(method, words, callback) {
			if (method == "spellcheck") {
				var suggestions = {};

				for (var i = 0; i < words.length; i++) {
					suggestions[words[i]] = ["First", "second"];
				}

				callback(suggestions);
			}
		}
	});
}

function unhtmlspecialchars(str)
{
	str = replaceAll(str, '&amp;', '&');
	str = replaceAll(str, '&#039;', '\'');
	str = replaceAll(str, '&quot;', '\"');
	str = replaceAll(str, '&lt;', '<');
	str = replaceAll(str, '&gt;', '>');
	str = replaceAll(str, '&middot;', 'ㆍ');

	return str;
}

/**
 * 이미지 선택 반영
 */
function setContent_img_tinymce(path, fullPath, obj) {
	if($("#" + path).prop('id') == undefined) {
		alert('반영할 내용 창이 없습니다.');
		return false;
	}
	var fileName = $(obj).find('input:text[name="fileName"]').val();
	var cnt = '<img src="' + fullPath + '" alt="' + fileName + '" /><br/><br/>';
	tinymce.get(path).execCommand('mceInsertContent', false, cnt);
}

/**
 * timymce 웹 에디터 내용 삽입
 */
function setTmContent(id, content) {
	tinymce.get(id).execCommand('mceInsertContent', false, content);
}

function setTmContent_all(id, content) {
	tinymce.get(id).execCommand('mceSetContent', false, content);
}

/**
 * 이미지 선택 반영 - summernote
 */
function setContent_img_summ(path, fullPath, obj) {
	if($("#" + path).prop('id') == undefined) {
		alert('반영할 내용 창이 없습니다.');
		return false;
	}
	var fileName = $(obj).find('input:text[name="fileName"]').val();
	var cnt = '<img src="' + fullPath + '" alt="' + fileName + '" />';
	
	$("#" + path).summernote("pasteHTML", cnt);
}

/**
 * smart 웹 에디터 오픈 
 * @param id
 */
function setSeEditor(id, oEditors) {
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: id,
		sSkinURI: "/resource/common/editor/SE2.9.0/SmartEditor2Skin_ko_KR.html",
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bSkipXssFilter : true,  // client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
			//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function(){
				//alert("완료!");
			}
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	});
}

/**
 * smart 웹 에디터 내용 삽입
 */
function setSeContent(id, content, oEditors) {
	oEditors.getById[id].exec("SET_CONTENTS", [content]);
}

/**
 * smart 웹 에디터 내용 추가
 */
function setSeContentAppend(id, fullPath, oEditors, obj) {
	if($("#" + id).prop('id') == undefined) {
		alert('반영할 내용 창이 없습니다.');
		return false;
	}

	var fileName = $(obj).find('.fileName').val();
	var cnt = '<img src="' + fullPath + '" alt="' + fileName + '" /><br/><br/>';
	oEditors.getById[id].exec("PASTE_HTML", [cnt]);
}

/**
 * 멀티파일 업로드 - 이미지 관리
 */
function setPlupload_imgManagement(id, u, size) {
	 $("#" + id).pluploadQueue({
        // General settings
        runtimes : 'html5,flash,silverlight,html4',
        url : u,
        rename : false,
        dragdrop: true,
        filters : {
            max_file_size : size + 'mb',
            // Specify what files to browse for
            mime_types: [
                {title : "Image files", extensions : "jpg,gif,png,bmp,jpeg"}
            ]
        },
 
        // Flash settings
        flash_swf_url : '/resource/mng/plugins/plupload-2.3.6/js/Moxie.swf',
     
        // Silverlight settings
        silverlight_xap_url : '/resource/mng/plugins/plupload-2.3.6/js/Moxie.xap'
    });
}

/**
 * 멀티파일 업로드 - 문서파일
 */
function setPlupload_doc(id, u, size) {
	$("#" + id).pluploadQueue({
        // General settings
        runtimes : 'html5,flash,silverlight,html4',
        url : u,
        rename : false,
        dragdrop: true,
        filters : {
        	max_file_size : size + 'mb',
        	// Specify what files to browse for
        	mime_types: [
	        	{title : "Doc files", extensions : "txt,xls,xlsx,doc,docx,ppt,pptx,pps,ppsx,hwp,pdf"},
	            {title : "Image files", extensions : "jpg,gif,png,bmp,jpeg"},
	            {title : "Zip files", extensions : "zip,alz,egg,rar,7z,iso"},
	            {title : "video files", extensions : "avi,mkv,flv,wmv"},
	            {title : "music files", extensions : "mp3,mp4,wma,wav"}
	        ]
        },
 
        // Flash settings
        flash_swf_url : '/resource/mng/plugins/plupload-2.3.6/js/Moxie.swf',
     
        // Silverlight settings
        silverlight_xap_url : '/resource/mng/plugins/plupload-2.3.6/js/Moxie.xap'
    });
}

/**
 * 파일 체크
 */
function chkFile(obj, type, f, flag, size) {
	if($(obj).val() == "") {
		return false;
	}
	
	var ext = obj.value.split(".").pop().toLowerCase();
	var imgArray = '*';

	if(type == 'img') {
		imgArray = Array("jpg", "jpeg", "gif", "png");
	} else if(type == 'ico') {
		imgArray = Array("ico");
	} else if(type != 'file') {
		imgArray = Array(type);
	}
	
	// 엑셀 파일이 아닐시 리턴
	if(imgArray != "*" && !array_search(ext, imgArray)) {
		alert(imgArray.toString() + " 파일만 업로드 가능 합니다.");
		$(obj).val("");
		return false;
	}
	
	if(size == '' || size == null) {
		size = 10;
	}
	
	if(this.chkFileSize(obj, f, size) && flag) {
		__setFileData(f);
	}
}

/**
 * 파일 용량 체크
 */
function chkFileSize(obj, f, size) {
	var maxSize  = size * 1048576;
	var fileSize = 0;
	
	// 브라우저 확인
	var browser = navigator.appName;
	
	// 익스플로러 체크
	if(browser == "Microsoft Internet Explorer") {
		var oas = new ActiveXObject("Scripting.FileSystemObject");
		fileSize = oas.getFile(obj.value).size;
	} else {
		fileSize = obj.files[0].size;
	}
	
	if(fileSize > maxSize) {
		alert('첨부된 파일이 업로드 가능 용량을 초과 하였습니다. (제한 : ' + size + ' MB)');
		f.reset();
		return false;
	}
	
	return true;
}

/**
 * 배열내의 정보 조회
 */
function array_search(needle, haystack, strict) {
    var key = '';
    for (key in haystack) {
        if((strict && haystack[key] === needle) || (!strict && haystack[key] == needle)) {
            return key;
        }
    }
    return false;
}

/**
 * 각종 화면 활성/비활성 설정
 */
function changeDisplay(cls, bln) {
	$("." + cls).hide();

	if(bln)
		$("." + cls).show();
}

/**
 * 셀렉트 박스 정보 이동
 */
function changeSelectedOp(afterId, beforeId) {
	$("#" + afterId + " option:selected").remove().appendTo("#" + beforeId);
}

/**
 * 설정된 길이보다 문자가 길시 자름
 */
function calculateValueDot(str, len) {
	var tcount = 0;
	var tmpStr = new String(str);
	var tmp = "";
	var onechar;
	for(var k=0; k<tmpStr.length; k++) {
		onechar = tmpStr.charAt(k);
		tcount += (escape(onechar).length > 4)? 2 : 1;
		if(len >= tcount)
			tmp += onechar;
	}

	return tmp + ((tmpStr.length > len)? "..." : "");
}

/**
 * 문자열 길이 추출
 * Description : 등록된 길이가 설정된 길이보다 클시 해당 길이 만큼 자른다
 */
function getStrLength(obj, id, len) {
	var strLength = calculateBytes($(obj).val());
	if(strLength > len) {
		alert("최대 " + len + "bytes(한글 " + (len/2) + "자, 영문/숫자 " + len + "자)까지 입력 가능 합니다.");
		$(obj).val(calculateValue($(obj).val(), len));
		strLength = calculateBytes($(obj).val());
	}

	if(id != null && id.length > 0)
		$("#" + id).html("(<strong>" + strLength + "</strong>/" + len + ")");
}

/**
 * 설정된 길이보다 문자가 길시 자름
 */
function calculateValue(str, len) {
	var tcount = 0;
	var tmpStr = new String(str);
	var tmp = "";
	var onechar;
	for(var k=0; k<tmpStr.length; k++) {
		onechar = tmpStr.charAt(k);
		tcount += (escape(onechar).length > 4)? 2 : 1;
		if(len >= tcount)
			tmp += onechar;
	}

	return tmp;
}

/**
 * 문자의 바이트수 리턴
 */
function calculateBytes(str) {
	var tcount = 0;
	var tmpStr = new String(str);
	var onechar;
	for(var k=0; k<tmpStr.length; k++) {
		onechar = tmpStr.charAt(k);
		tcount += (escape(onechar).length > 4)? 2 : 1;
	}

	return tcount;
}

/**
 * 쿠키 정보 저장
 */
function SetCookie(name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (2 < argc) ? argv[2] : new Date(getDatePlus(365));	// 365일 동안 쿠키 저장
	var path = (3 < argc) ? argv[3] : null;
	var domain = (4 < argc) ? argv[4] : null;
	var secure = (5 < argc) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) +
		((expires == null) ? "" :
		("; expires=" + expires.toUTCString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "");
}

function getCookie(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
};

$(document).ready(function() {
	if($('.datepicker').prop('class') != undefined) {
		$('.datepicker').datepicker({
			format: 'yyyy-mm-dd',
			language: 'kr',
			todayBtn: true,
			todayHighlight: true
		});
	}
	
	if($('.datepicker_year').prop('class') != undefined) {
		$('.datepicker_year').datepicker({
			minViewMode: 'years',
			format: 'yyyy',
			language: 'kr',
			todayBtn: true,
			todayHighlight: true
		});
	}
});

/**
 * 모바일 체크
 */
function isMobile() {
	var filter = "win16|win32|win64|mac|macintel";
	
	if(navigator.platform) {
		if(filter.indexOf(navigator.platform.toLowerCase()) < 0) {
			return true;
		} else {
			//pc
			return false;
		}
	} else {
		return true;
	}
}

// 문자열 정보 숨기기
function setStringSec(s) {
	var result = "";
	result = s.split("");
	
	if(s.length == 1) {
		return s;
	} else if(s.length == 2) {
		return result[0] + "*";
	} else {
		for(var i = 2; i < result.length - 1; i++){
			result[i] = "*";
		}
		return result.join("");
	}
}