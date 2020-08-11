(function () {
  includeHTML(function () {

    $('.popup-slide').slick({
      dots: true,
      infinite: true,
      slidesToShow: 2,
      slidesToScroll: 2,
      autoplay: true,
      autoplaySpeed: 3000,
      responsive: [{
          breakpoint: 640,
          settings: {
            dots: true,
            slidesToShow: 1,
            slidesToScroll: 1,
            infinite: true,
          }
        },

      ]
    });

    
    $('.popup-slide').on('init', function (slick) {
      popupCloseBoxSetting();
    });
    
    $('.popup-slide').on('reInit', function (slick) {
      popupCloseBoxSetting();
    });

    var pauseTrigger = false;
    var popupContainer = document.getElementsByClassName('popup-container')[0];
    var popupOpen = document.getElementsByClassName('popup-toggle-btn')[0];
    var popupBox = document.getElementsByClassName('pop-box')[0];
    var popupSlide = document.getElementsByClassName('popup-slide')[0];
    var dotList = document.getElementsByClassName('slick-dots')[0];
    var popupPause = document.createElement('li');
    var popupCloseBox = document.createElement('li');
    var dotRight;

    popupPause.addEventListener('click', pauseHandler);
    
    if(dotList != undefined) {
    	dotList.addEventListener('click', popupCloseHandler);
    }
    if(popupOpen != undefined) {
    	popupOpen.addEventListener('click', popupOpenHandler);
    }

    window.addEventListener('load', function () {
      dotRight = popupBox.getBoundingClientRect().right - popupSlide.getBoundingClientRect().right;
    });
    window.addEventListener('resisze', function () {
      dotRight = popupBox.getBoundingClientRect().right - popupSlide.getBoundingClientRect().right;
    });
    document.addEventListener('DOMConetneLoaded', function () {
      dotRight = popupBox.getBoundingClientRect().right - popupSlide.getBoundingClientRect().right;
    });

    window.addEventListener('load', popupControler);
    window.addEventListener('resize', popupControler);
    document.addEventListener('DOMContentLoaded', popupControler);
    window.addEventListener('load', dotListPosition);
    window.addEventListener('resize', dotListPosition);
    document.addEventListener('DOMContentLoaded', dotListPosition);
    window.addEventListener('load', openBtnRewording);
    window.addEventListener('resize', openBtnRewording);
    document.addEventListener('DOMContentLoaded', openBtnRewording);
    window.addEventListener('load', popupCloseBoxSetting);
    document.addEventListener('DOMContentLoaded', popupCloseBoxSetting);
  
    popupCloseBoxSetting();

    function popupCloseBoxSetting() {
      popupPause.classList.add('popup-pause-li');
      popupPause.innerHTML = '<a href="#this" class="popup-pause-btn">정지</a>';
      popupCloseBox.innerHTML = '' +
        '<div class="close-btn-container">' +
        '<label for="popupClose">' +
        '<input type="checkbox" id="popupClose" title="오늘 하루 열지 않기">' +
        '오늘 하루 열지 않기' +
        '</label>' +
        '<a class="popup-close">' +
        '<img src="/stat2/resource/static/image/common/close.png" alt="팝업닫기버튼">' +
        '닫기' +
        '</a>' +
        '</div>';
        window.resizeBy(1,0);
        window.resizeBy(-1,0);
      }
      window.resizeBy(1,0);
      window.resizeBy(-1,0);


    function openBtnRewording() {
      if (window.innerWidth <= 480) {
        popupOpen.innerHTML = '팝업 열기';
      } else {
        popupOpen.innerHTML = '<span>POPUP</span>' +
          '<img src="/stat2/resource/static/image/common/popup-open.png" alt="팝업닫기 버튼 아이콘"></img>';
      }
    }

    function popupOpenHandler() {
      popupContainer.classList.add('active');
      headerSetTop();
    }

    function popupCloseHandler(e) {
      var popupClose = document.getElementsByClassName('popup-close')[0];
      if (e.target == popupClose || e.target == popupClose.getElementsByTagName('img')[0]) {
        popupContainer.classList.remove('active');
      }
      headerSetTop();
    }

    function pauseHandler() {
      pauseTrigger ? pauseTrigger = false : pauseTrigger = true;
      if (pauseTrigger) {
        $('.popup-slide').slick('slickPause');
        this.classList.add('pause');
      } else {
        $('.popup-slide').slick('slickPlay');
        this.classList.remove('pause');
      }
    }

    function dotListPosition() {
      dotRight = popupBox.getBoundingClientRect().right - popupSlide.getBoundingClientRect().right;
      dotList.style.right = -(dotRight - 14) + 'px';
      dotList.addEventListener('click', popupCloseHandler);
    }

    function popupControler() {
      dotList = document.getElementsByClassName('slick-dots')[0];
      dotList.appendChild(popupPause);
      dotList.appendChild(popupCloseBox);
    }



    var pcHeader = document.getElementsByClassName('pc-header')[0];

    function headerSetTop() {
      if (window.innerWidth <= 768) {

        if (!popupContainer.classList.contains('active')) {
          pcHeader.style.top =  '125px';
        } else {
          pcHeader.style.top = 0;
        }
      } else {
        pcHeader.style.top = '125px';
      }
    }
  });
})();