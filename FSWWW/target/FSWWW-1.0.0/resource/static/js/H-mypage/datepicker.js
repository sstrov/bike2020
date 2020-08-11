(function(){
  var layerPopup = document.getElementsByClassName('layer-popup')[0];
  var layerOpenLink = document.getElementsByClassName('layer-open');

  for(var i = 0; i < layerOpenLink.length; i++) {
    layerOpenLink[i].addEventListener('click',onLayerOpen);
  }
  
  layerPopup.addEventListener('click',onPopupClose);

  $('.date-input').datepicker({
    showOn: "both", 
    buttonImage: "../../static/image/common/datepicker.png", 
    buttonImageOnly: true,
    dateFormat: "yy.mm.dd"
  });

  function onPopupClose(e) {
    var close = this.getElementsByClassName('close-btn')[0];

    if(e.target == close) {
      this.classList.remove('on');
    }
  }
  function onLayerOpen() {
    layerPopup.classList.add('on');
  }
})();