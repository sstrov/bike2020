(function() {
  $('.popupzone-slide').slick({
    dots: false,
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    autoplay: true,
    autoplaySpeed: 1000,
    appendArrows: $('.popupzone-btn-box'),
    prevArrow: '<button type="button" class="slick-prev"><img src="/stat2/resource/static/image/main/btn_left.png" alt="이전"></button>',
    nextArrow: '<button type="button" class="slick-next"><img src="/stat2/resource/static/image/main/btn_right.png" alt="다음"></button>',
  });

  $('.banner-slide').slick({
    dots: false,
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    autoplay: true,
    autoplaySpeed: 1000,
    appendArrows: $('.banner-btn-box'),
    prevArrow: '<button type="button" class="slick-prev"><img src="/stat2/resource/static/image/main/btn_left.png" alt="이전"></button>',
    nextArrow: '<button type="button" class="slick-next"><img src="/stat2/resource/static/image/main/btn_right.png" alt="다음"></button>',
    responsive: [
      {
        breakpoint: 1200,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },
      {
        breakpoint: 1000,
        settings: {
          slidesToShow: 4,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },
      {
        breakpoint: 430,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          infinite: true,
          dots: false
        }
      },

    ],
  });

  var popupzonePauseTrigger = false;
  var bannerPauseTrigger = false;
  var popupzonePauseBtn = document.querySelector('.popupzone-btn-box .slick-pause');  
  var bannerPauseBtn = document.querySelector('.banner-btn-box .slick-pause');  


  popupzonePauseBtn.addEventListener('click',function(){
    popupzonePauseHandler($('.popupzone-slide'));
  });

  bannerPauseBtn.addEventListener('click',function(){
    bannerPauseHandler($('.banner-slide'));
  });




  function popupzonePauseHandler(slide) {
    popupzonePauseTrigger ? popupzonePauseTrigger = false : popupzonePauseTrigger = true;
    if(popupzonePauseTrigger) {
      slide.slick('slickPause');
    } else {
      slide.slick('slickPlay');
    }
  }

  function bannerPauseHandler(slide) {
    bannerPauseTrigger ? bannerPauseTrigger = false : bannerPauseTrigger = true;
    if(bannerPauseTrigger) {
      slide.slick('slickPause');
    } else {
      slide.slick('slickPlay');
    }
  }

})();