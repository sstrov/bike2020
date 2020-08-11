(function(){
  var graphItem = document.getElementsByClassName('graph-item');
  
  
  for(var i = 0; i < graphItem.length; i++) {
    graphItem[i].addEventListener('click',onCommentOpen);
  }
  
  
  
  function onCommentOpen(e) {
    var commentOpenBtn = this.getElementsByClassName('comment-open')[0];
    var commentPopup = this.getElementsByClassName('comment-popup')[0];
    if(e.target == commentOpenBtn) {
      commentPopup.classList.add('on');
    }
    var closeBtn = this.getElementsByClassName('close-btn')[0];
    if(e.target == closeBtn) {
      commentPopup.classList.remove('on');
    }
  }
})();