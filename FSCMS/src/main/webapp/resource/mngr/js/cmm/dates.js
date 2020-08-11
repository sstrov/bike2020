/**
 * agoDay 일자를 추가한 날짜를 가져온다
 */
function getDatePlus(agoDay) {
	var Now = new Date();

	Now.setTime( Now.getTime() + agoDay*24*60*60*1000);

	var NowDay = Now.getDate();
	if(agoDay != 0) {
		Now.setDate(NowDay + 1);
		NowDay = Now.getDate();
	}
	var NowMonth = Now.getMonth()+1;// 1월이 0부터 시작함.
	var NowYear = Now.getFullYear();

	if (NowYear < 2000) NowYear += 1900; //for Netscape
	if (NowMonth < 10 )NowMonth ="0"+NowMonth;
	if (NowDay < 10 )NowDay ="0"+NowDay;

	return NowYear+"-"+NowMonth+"-"+NowDay;
}

/**
 * agoDay 이전 날짜를 가져온다
 */
function getDateMPlus(agoDay){
	var Now = new Date();

	Now.setMonth(Now.getMonth() + Number(agoDay));
	Now.setDate(Now.getDate() - 1);

	var NowDay = Now.getDate();
	if(agoDay != 0) {
		//NowDay += 1;
	}
	var NowMonth = Now.getMonth() + 1;// 1월이 0부터 시작함.
	var NowYear = Now.getFullYear();

	if (NowYear < 2000) NowYear += 1900; //for Netscape
	if (NowMonth < 10 )NowMonth ="0"+NowMonth;
	if (NowDay < 10 )NowDay ="0"+NowDay;

	return NowYear+"-"+NowMonth+"-"+NowDay;
}

/**
 * agoDay 이전 날짜를 가져온다
 */
function getDateBefore(agoDay){
	var Now = new Date();

	Now.setTime( Now.getTime() - agoDay*24*60*60*1000);

	var NowDay = Now.getDate();
	if(agoDay != 0) {
		Now.setDate(NowDay + 1);
		NowDay = Now.getDate();
	}
	var NowMonth = Now.getMonth()+1;// 1월이 0부터 시작함.
	var NowYear = Now.getFullYear();

	if (NowYear < 2000) NowYear += 1900; //for Netscape
	if (NowMonth < 10 )NowMonth ="0"+NowMonth;
	if (NowDay < 10 )NowDay ="0"+NowDay;

	return NowYear+"-"+NowMonth+"-"+NowDay;
}

/**
 * agoDay 이전 날짜를 가져온다
 */
function getDateMBefore(agoDay){
	var Now = new Date();

	Now.setMonth(Now.getMonth() - agoDay);

	var NowDay = Now.getDate();
	if(agoDay != 0) {
		Now.setDate(NowDay + 1);
		NowDay = Now.getDate();
	}
	var NowMonth = Now.getMonth()+1;// 1월이 0부터 시작함.
	var NowYear = Now.getFullYear();

	if (NowYear < 2000) NowYear += 1900; //for Netscape
	if (NowMonth < 10 )NowMonth ="0"+NowMonth;
	if (NowDay < 10 )NowDay ="0"+NowDay;

	return NowYear+"-"+NowMonth+"-"+NowDay;
}

/**
 * agoDay 이전 날짜를 가져온다
 */
function getDateYBefore(agoDay){
	var Now = new Date();

	Now.setYear(Now.getFullYear() - agoDay);

	var NowDay = Now.getDate();
	if(agoDay != 0) {
		Now.setDate(NowDay + 1);
		NowDay = Now.getDate();
	}
	var NowMonth = Now.getMonth()+1;// 1월이 0부터 시작함.
	var NowYear = Now.getFullYear();

	if (NowYear < 2000) NowYear += 1900; //for Netscape
	if (NowMonth < 10 )NowMonth ="0"+NowMonth;
	if (NowDay < 10 )NowDay ="0"+NowDay;

	return NowYear+"-"+NowMonth+"-"+NowDay;
}

/**
 * 일수를 계산하여 적용
 */
function periodSel(s, e, n) {
	$("#"+s).val(getDateBefore(n));	// 몇일전.
	$("#"+e).val(getDateBefore(0));	// 오늘
}

/**
 * 선택된 개월 만큼 제거
 */
function periodMSel(s, e, n) {
	$("#"+s).val(getDateMBefore(n));	// 몇일전.
	$("#"+e).val(getDateMBefore(0));	// 오늘
}

function periodMSelAdd(s, e, n) {
	if(n == "") {
		$("#"+s).val("");
		$("#"+e).val("");
	} else {
		$("#"+s).val(getDateMBefore(0));	// 오늘.
		$("#"+e).val(getDateMPlus(n));		// 몇일 후
	}
}

function periodMSelAddBun(s, e, n) {
	if(n == "") {
		$("#"+s).val("");
		$("#"+e).val("");
	} else {
		var Now = new Date();
		
		if(n == "1") {
			$("#"+s).val(Now.getFullYear() + "-01-01");
			$("#"+e).val(Now.getFullYear() + "-03-31");
		} else if(n == "2") {
			$("#"+s).val(Now.getFullYear() + "-04-01");
			$("#"+e).val(Now.getFullYear() + "-06-30");
		} else if(n == "3") {
			$("#"+s).val(Now.getFullYear() + "-07-01");
			$("#"+e).val(Now.getFullYear() + "-09-30");
		} else {
			$("#"+s).val(Now.getFullYear() + "-10-01");
			$("#"+e).val(Now.getFullYear() + "-12-31");
		}
	}
}

/**
 * 선택된 년 만큼 제거
 */
function periodYSel(s, e, n) {
	$("#"+s).val(getDateYBefore(n));	// 몇일전.
	$("#"+e).val(getDateYBefore(0));	// 오늘
}

/**
 * 일수를 계산하여 적용
 */
function periodSel_del(s, e) {
	$("#"+s).val('');	// 몇일전.
	$("#"+e).val('');	// 오늘
}