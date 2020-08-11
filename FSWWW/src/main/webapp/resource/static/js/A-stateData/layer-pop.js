(function(){
  var layerList = document.getElementsByClassName('layer-pop');
  var layerOpen01 = document.getElementsByClassName('layer-btn-01')[0];
  var layerOpen02 = document.getElementsByClassName('layer-btn-02')[0];
  var layerOpen03 = document.getElementsByClassName('layer-btn-03')[0];
  

  if(layerOpen01 != undefined) {
	  layerOpen01.addEventListener('click',function(){
		$(".layer-pop").removeClass("on");
	    document.getElementsByClassName('layer-pop-01')[0].classList.add('on');
	  });
  }
  
  if(layerOpen02 != undefined) {
	  layerOpen02.addEventListener('click',function(){
		$(".layer-pop").removeClass("on");
	    document.getElementsByClassName('layer-pop-02')[0].classList.add('on');
	  });
  }
  
  if(layerOpen03 != undefined) {
	  layerOpen03.addEventListener('click',function(){
		$(".layer-pop").removeClass("on");
	    document.getElementsByClassName('layer-pop-03')[0].classList.add('on');
	  });
  }

  if(layerList != undefined) {
	  for(var i = 0; i < layerList.length; i++) {
		  if(layerList[i] != undefined) {
			  layerList[i].addEventListener('click',layerClose);
		  }
	  }
  }

  function layerClose(e) {
    var closeBtn = this.getElementsByClassName('close')[0];
    if(e.target == closeBtn) {
      this.classList.remove('on');
    }
  }
})();