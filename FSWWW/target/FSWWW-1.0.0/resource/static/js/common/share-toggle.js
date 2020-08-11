(function(){
  includeHTML(function(){
    var shareToggleBtn = document.getElementsByClassName('share-open')[0];
    
    if(shareToggleBtn != undefined) {
    	shareToggleBtn.addEventListener('click',snsToggleHandler);
    }

  });

  function snsToggleHandler() {
    var snsWrap = document.getElementsByClassName('sns-wrap')[0];
    snsWrap.classList.toggle('on');
  }
})();