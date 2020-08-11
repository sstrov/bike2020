(function(){
  var requsetContainer = document.getElementsByClassName('request-wrap');
  var requsetOpenBtn = document.getElementsByClassName('key-request-btn');

  setTimeout(function(){
    for(var j = 0; j < requsetContainer.length; j++) {
      requsetContainer[j].addEventListener('click',requestClose);
    }
    requsetOpenBtn[0].addEventListener('click',requestOpen);
  },500);

  function requestOpen() {
    requsetContainer[0].classList.add('on');
  }


  function requestClose(e) {
    var closeBtn = this.getElementsByClassName('close-btn');

    for(var i = 0; i < closeBtn.length; i++) {
      if(e.target == closeBtn[i]) {
        this.classList.remove('on');
      }
    }
  }
})();