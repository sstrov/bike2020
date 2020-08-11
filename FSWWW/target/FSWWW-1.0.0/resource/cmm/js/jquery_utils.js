function lodingFixedOn(msg, w, h) {
	var l = (window.innerWidth - w) / 2;
	var t = (window.innerHeight - h) /2;

	var str = '<div class="loding_line" style="position:fixed; z-index:10000; left:' + l + 'px; top:' + t + 'px; width:' + w + 'px; height:' + h + 'px; background-color:#fff;">' +
			'<table style="width:100%; height:' + h + 'px; border:0;">' +
				'<tr>' +
					'<td style="font-size:9pt; text-align:center; vertical-align:middle; border:2px solid silver;">' +
						'<img src="/stat2/resource/cmm/img/ajax-loader.gif" alt="로딩 이미지" /><br/>' + msg +
					'</td>' +
				'</tr>' +
			'</table>' +
		'</div>' +
		'<div class="ui-widget-overlay ui-front loding_line" style="position:fixed; top:0; left:0; width:100%; height:100%; z-index:9999; background:repeat-x scroll 50% 50% #AAA; opacity:0.3;"></div>';

	$(document.body).css("overflow-y", "hidden");
	$(document.body).append(str);
}

/**
 * 로딩 이미지 제거
 * @param target
 */
function lodingOff(target) {
	$(document.body).css("overflow-y", "auto");
	$(target).find(".loding_line").remove();
}

/**
 * 내부 로딩
 */
function lodingOnTarget(target, msg, h) {
	var str = "\n<div class=\"loding_line\" style=\"height:" + h + "; line-height:" + h + "; text-align:center;\">" +
	"\n\t<table width=\"100%\" height=\"" + h + "\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"font-size:13px; text-align:center; vertical-align:middle;\">" + 
	"\n\t<img src=\"/stat2/resource/cmm/img/ajax-loader.gif\" alt=\"로딩 이미지\" /><br/>" + msg +
	"\n\t</td></tr></table>" +
	"\n</div>";
	
	$(target).html(str);
}

function lodingOnTargetNonLoad(target, msg, h) {
	var str = "\n<div class=\"loding_line\" style=\"height:" + h + "; line-height:" + h + "; text-align:center;\">" +
	"\n\t<table width=\"100%\" height=\"" + h + "\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td style=\"font-size:13px; text-align:center; vertical-align:middle;\">" + 
	"\n\t" + msg +
	"\n\t</td></tr></table>" +
	"\n</div>";
	
	$(target).html(str);
}

/**
 * 내부 알림
 */
function alertOnTarget(target, msg, h) {
	var str = "\n<div class=\"loding_line\" style=\"height:" + h + "px; line-height:" + h + "; text-align:center; border:0;\">" +
	"\n\t<table width=\"100%\" height=\"" + h + "\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:0;\"><tbody style=\"border:0;\"><tr><td style=\"border:0; font-size:13px; text-align:center; vertical-align:middle;\">" + 
	"\n\t" + msg +
	"\n\t</td></tr></tbody></table>" +
	"\n</div>";
	
	$(target).html(str);
}