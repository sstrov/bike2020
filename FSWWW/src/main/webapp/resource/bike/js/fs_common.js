$(document).ready(function() {

	//건너뛰기 포커스이동
	$(".skip_nav:eq(0)").click(function(){
		$("#fs_container_wrap").attr("tabindex", -1).focus();
	});
	$(".skip_nav:eq(1)").click(function(){
		$("#fs_top_menu").attr("tabindex", -1).focus();
	});
	$(".skip_nav:eq(2)").click(function(){
		$("#fs_footer").attr("tabindex", -1).focus();
	});

	var esing = "easeInOutExpo";
	var time = 400;

	//메뉴 %자동조절
	//var snb_menu_Width = 100/$('ul.lnb > li').length;
	//$('ul.lnb > li').css('width',snb_menu_Width+'%'); //메뉴 개수에 따라 %자동조절

	//전체메뉴(접근성 기능 포함)
	/*
	function chk(){
		if(cc == 1){
			$(".fs_top_menu ul.lnb > li > ul").stop(true,false).show(0,'easeInOutExpo');
			$(".menu_text").stop(true,false).show();
		}else{
			$(".fs_top_menu ul.lnb > li > ul").stop(true,false).hide(0,'easeInOutExpo');
			$(".menu_text").stop(true,false).hide();
		}
	}
	//마우스가 들어갔을때
	$('.fs_top_menu > ul.lnb > li').mouseover(function(){
		setTimeout(chk);
		cc = 1;
		$(this).addClass('on');
		$(this).parent().parent().addClass('on');
	});
	//마우스가 나갔을때
	$('.fs_top_menu').mouseout(function(){
		setTimeout(chk);
		cc = 0;
		$('.fs_top_menu ul.lnb li').removeClass('on');
		$(".fs_top_menu").removeClass('on');
	});
	//포커스가 들어갔을때
	$('.fs_top_menu ul.lnb li a').focus(function(){
		setTimeout(chk);
		cc = 1;
		$(this).parent().parent().addClass('on');
	});
	//포커스가 나갔을때
	$('.fs_top_menu ul.lnb li a').blur(function(){
		setTimeout(chk);
		cc = 0;
		$('.fs_top_menu ul.lnb li').removeClass('on');
	});
	*/

	//개별메뉴(접근성 기능 포함) : 수정
	$(".fs_top_menu .lnb li").mouseenter(function(){
		$(this).find(".lnb_bg").stop(true,false).show();
		$(this).addClass("on");
	});
	$(".fs_top_menu .lnb li").mouseleave(function(){
		$(this).find(".lnb_bg").stop(true,false).hide();
		$(this).removeClass("on");
	});
	$(".fs_top_menu .lnb li").focusin(function(){
		$(this).find(".lnb_bg").stop(true,false).show();
		$(this).addClass("on");
	});
	$(".fs_top_menu .lnb li").focusout(function(){
		$(this).find(".lnb_bg").stop(true,false).hide();
		$(this).removeClass("on");
	});
	/*
	$(".fs_top_menu .lnb li .lnb_bg .lnb_layer01 li").mouseenter(function(){
		$(this).find(".lnb_layer02").stop(true,false).show();
		$(this).addClass("on");
	});
	$(".fs_top_menu .lnb li .lnb_bg .lnb_layer01 li").mouseleave(function(){
		$(this).find(".lnb_layer02").stop(true,false).hide();
		$(this).removeClass("on");
	});
	$(".fs_top_menu .lnb li .lnb_bg .lnb_layer01 li").focusin(function(){
		$(this).find(".lnb_layer02").stop(true,false).show();
		$(this).addClass("on");
	});
	$(".fs_top_menu .lnb li .lnb_bg .lnb_layer01 li").focusout(function(){
		$(this).find(".lnb_layer02").stop(true,false).hide();
		$(this).removeClass("on");
	});
	*/
	$("#fs_header").mouseenter(function(){
		$("#fs_header").addClass("onbg");
	});
	$("#fs_header").mouseleave(function(){
		$("#fs_header").removeClass("onbg");
	});


	//사이트맵
	$(".mainsitemap_box_warp").hide();
	$(".mainsitemap").html($(".fs_top_menu").html());
	$(".sitemap_box .sitemap").click(function(){
		$(".mainsitemap_box_warp").fadeIn(time,esing);
		$("body").css("overflow","hidden");
	});
	$(".mainsitemap_close").click(function(){
		$(".mainsitemap_box_warp").fadeOut(time,esing);
		$("body").css("overflow","visible");
	});


	//스크롤시 메뉴 상단고정
	$(window).scroll(function(){
		if($(window).scrollTop() > 95){
			$("#fs_header,.fs_sub_visual").addClass("on");
		}else{
			$("#fs_header,.fs_sub_visual").removeClass("on");
		}
	});

	//태블릿,모바일 메뉴열기
	$(".mbtn_box .menu_open").click(function(){
		$("body").toggleClass("overflow_y");
		$(this).toggleClass("on");

		$(".fs_mtop_menu").stop(true,false).slideToggle(0);
		$(".fs_mtop_menu").html($(".fs_top_menu").html());

		menu_mo();

		$("html, body").animate({scrollTop:0},{duration:0,easing:"easeInOutExpo"});

		$(window).resize(function (){
			// width값을 가져오기
			var width_size = window.outerWidth;

			// 800 이하인지 if문으로 확인
			if (width_size <= 1024) {
				menu_mo();

				$("html, body").animate({scrollTop:0},{duration:0,easing:"easeInOutExpo"});

			}else{
				$(".fs_mtop_menu").hide();
				$("body").removeClass("overflow_y");
				$(".menu_open").removeClass("on");
			}
		})
		$(window).load(function (){
			// width값을 가져오기
			var width_size = window.outerWidth;

			// 800 이하인지 if문으로 확인
			if (width_size <= 1024) {
				menu_mo();
				
				$("html, body").animate({scrollTop:0},{duration:0,easing:"easeInOutExpo"});
			}else{
				$(".fs_mtop_menu").hide();
				$("body").removeClass("overflow_y");
				$(".menu_open").removeClass("on");
			}
		})
		return false;
	});

	//애니메이션 효과(참고:https://github.com/daneden/animate.css)
	//메인
	$('.main_visual_text .sta').addClass('animated fadeInDown');
	$('.main_visual_text .stb').addClass('animated fadeInUp');
	$('.main_visual_text .stc').addClass('animated fadeInUp');
	//$('.fs_main_visual .mvis_controll_box').addClass('animated fadeInUp');
	//서브
	$('.snb_vis_box .sta').addClass('animated fadeInDown');
	$('.snb_vis_box .stb').addClass('animated fadeInRight');
	$('.snb_vis_box .stc').addClass('animated fadeInUp');

	//레프트메뉴
	menu_left();

	//sns
	$(".sns_box .sns_open_btn").on("click", function(){
		$(this).toggleClass("on");
		$(".sns_open").toggleClass("on");
	});

	//맨위로
	$(".btn_top").hide();
	$(window).scroll(function(){
		if($(window).scrollTop() > 200){
			$(".btn_top").show();
		}else{
			$(".btn_top").hide();
		}
	});

	//맨위로가기
	$(".btn_top").click(function(){
		$("html, body").animate({scrollTop:0},{duration:800,easing:"easeInOutExpo"});
		return false;
	});


	/*** 컨텐츠 ***/
	//탭메뉴 %자동조절
	var tap_menu_Width = 100/$('ul.tap_btn > li').length;
	$('ul.tap_btn > li').css('width',tap_menu_Width+'%'); //메뉴 개수에 따라 %자동조절

	//이중 탭
	$('.tap_box > .tab_content').children().css('display', 'none'); 
	
	$('.tap_box > .tab_content div:first-child').css('display', 'block'); 
	//$('.tap_box > .tap_btn li:first-child').addClass('on'); 
	$('.tap_box > .tap_btn_st2 li:first-child').addClass('on'); 
	
	$('.tap_box').delegate('.tap_btn > li', 'click', function() { 
		var index = $(this).parent().children().index(this); 
		$(this).siblings().removeClass(); 
		$(this).addClass('on'); 
		$(this).parent().next('.tab_content').children().hide().eq(index).show(); 
	});

	$('.tap_box').delegate('.tap_btn_st2 > li', 'click', function() { 
		var index = $(this).parent().children().index(this); 
		$(this).siblings().removeClass(); 
		$(this).addClass('on'); 
		$(this).parent().next('.tab_content').children().hide().eq(index).show(); 
	});

	
	$(".tap_box .mob_tap > a").click(function(){
		$(".tap_btn2").toggleClass("on");
		$(this).toggleClass("on");
	});
});



//sns url복사
function copy_to_clipboard() {
	if (navigator.userAgent.match(/ipad|ipod|iphone/i)) {
		var textArea = document.getElementById("copytextarea");

		var range = document.createRange();
		range.selectNodeContents(textArea);
		var selection = window.getSelection();
		selection.removeAllRanges();
		selection.addRange(range);
		textArea.setSelectionRange(0, 999999);
		document.execCommand('copy');
		alert('복사 되었습니다.');
		//$("div.sns_open").hide();
	}else{
		try {
			//$(".url_text").select();
			document.execCommand('copy');
			alert('복사 되었습니다.');
		} catch(err) {
			alert('이 브라우저는 지원하지 않습니다.');
		} finally{
			//$("div.sns_open").hide();
		}
	}
}

//모바일 메뉴
function menu_mo(){

	$(".fs_mtop_menu a.deplink").click(function(e) { e.preventDefault(); });//링크 안걸리게
	$(".fs_mtop_menu .lnb > li").find(".lnb_bg").hide(0);
	$(".fs_mtop_menu .lnb > li").find(".lnb_layer01").hide(0);
	$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li .lnb_layer02").hide(0);
	$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > .lnb_layer02 > li > .lnb_layer03").hide(0);
	$(".fs_mtop_menu .lnb > li:eq(0)").find(".lnb_bg").show(0);
	$(".fs_mtop_menu .lnb > li:eq(0)").find(".lnb_layer01").show(0);
	$(".fs_mtop_menu .lnb > li:eq(0) > .lnb_bg > .lnb_layer01 > li:eq(0)").find(".lnb_layer02").show(0);
	$(".fs_mtop_menu .lnb > li:eq(0) > .lnb_bg > .lnb_layer01 > li > .lnb_layer02 > li:eq(0)").find(".lnb_layer03").show(0);
	$(".fs_mtop_menu .lnb > li:eq(0)").addClass("on");

	$(".fs_mtop_menu .lnb > li > a").click(function(){
		$(".fs_mtop_menu .lnb > li > .lnb_bg").hide(0);
		$(".fs_mtop_menu .lnb > li").find(".lnb_layer01").hide(0);
		$(".fs_mtop_menu .lnb > li").removeClass("on");

		if(!$(this).next().is(":visible")) {
			$(this).next().show(0);
			$(this).next().find(".lnb_layer01").show(0);
			$(this).parent().addClass("on");
			$(this).addClass("deplink");
		}

	});
	
	$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > a").click(function(){
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li .lnb_layer02").slideUp();
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li").removeClass("on");
		
		if(!$(this).next().is(":visible")) {
			$(this).next().slideDown();
			$(this).parent().addClass("on");
		}
		
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li").each(function() {
			if($(this).find('> ul > li').prop('tagName') == undefined) {
				$(this).find('ul.lnb_layer02').hide();
			}
		});

	});
	
	$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > .lnb_layer02 > li > a").click(function(){
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > .lnb_layer02 > li > .lnb_layer03").slideUp();
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > .lnb_layer02 > li").removeClass("on");
		
		if(!$(this).next().is(":visible")) {
			$(this).next().slideDown();
			$(this).parent().addClass("on");
		}
		
		$(".fs_mtop_menu .lnb > li > .lnb_bg > .lnb_layer01 > li > lnb_layer02 > li").each(function() {
			if($(this).find('> ul > li').prop('tagName') == undefined) {
				$(this).find('ul.lnb_layer03').hide();
			}
		});

	});

	/*
	$(".fs_mtop_menu").scroll(function(){
		if($(".fs_mtop_menu").scrollTop() < 30){
			$(".mbtn_box .menu_open").css("margin-top","0");
			$(".mbtn_box .login_open").css("margin","-31px 15px 0 0");
		}else{
			$(".mbtn_box .menu_open").css("margin-top","-94px");
			$(".mbtn_box .login_open").css("margin","17px 15px 0 0");
		}
	});
	*/

	$(".mg4").click(function(){
		$(".mglobal_box .global_btn .lang_cont").stop(true,false).slideToggle();
	});

	$(".mg6").click(function(){
		$(".mglobal_box .global_btn .lang_cont2").stop(true,false).slideToggle();
	});


	return false;
};


//레프트 메뉴
function menu_left(){

	$(".snb_menu .dep2").hide();
	$(".snb_menu .dep3").hide();
	$(".snb_menu a.deplink").click(function(e) { e.preventDefault(); });//링크 안걸리게
	$(".snb_menu .dep1 > li.on").find(".dep2").show();
	$(".snb_menu .dep1 > li > .dep2 > li.on").find(".dep3").show();

	$(".snb_menu .dep1 > li > a").click(function(){
		$(".snb_menu .dep1 > li .dep2").slideUp();
		$(".snb_menu .dep1 > li").removeClass("on");

		if(!$(this).next().is(":visible")) {
			$(this).next().slideDown();
			$(this).parent().addClass("on");
			$(this).addClass("deplink");
		}
	});
	
	$(".snb_menu .dep1 > li > .dep2 > li > a").click(function(){
		$(".snb_menu .dep1 > li > .dep2 > li .dep3").slideUp();
		$(".snb_menu .dep1 > li > .dep2 > li").removeClass("on");
		
		if(!$(this).next().is(":visible")) {
			$(this).next().slideDown();
			$(this).parent().addClass("on");
		}

		$(".snb_menu .dep1 > li > .dep2 > li").each(function() {
			if($(this).find('> ul > li').prop('tagName') == undefined) {
				$(this).find('ul.dep3').hide();
			}
		});
	});

	return false;

};

//컨텐츠탭
function cont_tap(param,h,marginb){
	var param = $(param);
	var h = h;
	var marginb = marginb;

	param.find(h + '> a').parent().parent().find('div').hide();
	param.find(h + '> a:eq(0)').parent().parent().find('div').show();
	param.find(h + '> a:eq(0)').parent().addClass("on");

	param.find(h + '> a').bind('click',function (){

		var height = $(this).parent().parent().find('> div').height();

		param.find(h + '> a').parent().removeClass("on");
		param.find(h + '> a').parent().parent().find('div:eq(0)').hide();
		$(".notice_box2 > ul").css({"height":height,"margin-bottom":marginb});
		$(this).parent().parent().find('div').show();
		$(this).parent().addClass("on");

		return false;
	});

};

//탭메뉴
function cont_tap2(param,cont){
	var param = $(param);
	var cont = cont;

	$(cont).hide();
	$(cont).eq(0).show();

	param.find("li").click(function () {
		param.find("li").removeClass("on");
		$(this).addClass("on");
		$(cont).hide();
		var activeTab = $(this).find("> a").attr("class");
		$("#" + activeTab).fadeIn();

		$("form").each(function() {
			this.reset();
		});

	});

};
/*
//메인탭
function cont_tap(param,h){
	var param = $(param);
	var h = h;

	param.find(h + '> a').parent().parent().find('> div').hide();
	param.find(h + '> a:eq(0)').parent().parent().find('> div').show();
	param.find(h + '> a:eq(0)').parent().parent().addClass("on");

	param.find(h + '> a').bind('click',function (){
		param.find(h + '> a').parent().parent().removeClass("on");

		param.find(h + '> a').parent().parent().find('> div:eq(0)').hide();
		$(this).parent().parent().find('> div').show();
		$(this).parent().parent().addClass("on");
		return false;
	});
};
*/