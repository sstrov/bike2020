(function() {
  includeHTML(function() {
    var menuOpenBtn = document.getElementsByClassName('menu-open')[0];
    var menuCloseBtn = document.getElementsByClassName('header-close-btn')[0];
    var headerMenu = document.getElementsByClassName('pc-header')[0];
    var onMenu = headerMenu.getAttribute('data-on');
    var gnbItems = document.getElementsByClassName('gnb-item');


    menuOpenBtn.addEventListener('click',menuOpenHandler);
    menuCloseBtn.addEventListener('click',menuCloseHandler);
    window.addEventListener('load',menuOnRemove);
    window.addEventListener('resize',menuOnRemove);
    document.addEventListener('DOMConentLoaded',menuOnRemove);

    if(window.innerWidth <= 768) {
      for(var i = 0; i < gnbItems.length; i++) {
        gnbItems[i].addEventListener('click', gnbOnHandler);
      }
    }

    function menuOnRemove() {
      if(window.innerWidth > 768) {
        for(var l = 0; l < gnbItems.length; l++) {
          gnbItems[l].classList.remove('on');
        }
      }
    }

    if(onMenu >= 0) {
      // document.getElementsByClassName('gnb-item')[onMenu].classList.add('on');
    }

    function gnbOnHandler() {
      for(var j = 0; j < gnbItems.length; j++) {
        gnbItems[j].classList.remove('on');
        console.log(j);
      }
      this.classList.add('on');
    }
    
    function menuOpenHandler() {
      headerMenu.classList.add('active');
      for(var k = 0; k < gnbItems.length; k++) {
        gnbItems[k].classList.remove('on');
      }
      gnbItems[0].classList.add('on');
    }
    function menuCloseHandler() {
      headerMenu.classList.remove('active');
    }

  });

})();

/* 상단/서브메뉴 접근성 포커스 기능 추가 */
$(document).ready(function() {
	
	/* 메인메뉴 : 포커스가 들어갔을때 */
	$('.gnb-list li.gnb-item').focusin(function(){
		$(this).find(".depth-list").css({"display":"block"});
		//alert("포커스 들어옴");
	});
	$('.gnb-list li.gnb-item .depth-list li:last-child').focusout(function(){
		$(this).parent().parent().find(".depth-list").removeAttr("style");
		//alert("포커스 나감");
	});
	
	
	/* 서브메뉴 : 포커스가 들어갔을때 */
	$('.sub-menu .sub-ul .sub-item').focusin(function(){
		$(this).find(".sub-depth").css({"display":"inline-block","opacity":"1","visibility":"visible"});
		//alert("포커스 들어옴");
	});
	$('.sub-menu .sub-ul .sub-item .sub-depth li:last-child').focusout(function(){
		$(this).parent().parent().find(".sub-depth").removeAttr("style");
		//alert("포커스 나감");
	});
	
});
