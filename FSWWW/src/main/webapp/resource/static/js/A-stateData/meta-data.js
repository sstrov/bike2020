(function(){
  var metaToggleBtn = document.getElementsByClassName('meta-data-btn')[0];

  if(metaToggleBtn != undefined) {
	  metaToggleBtn.addEventListener('click',metaDataToggle);
  }

  function metaDataToggle() {
    var metaData = document.getElementsByClassName('meta-data')[0];
    if(metaData != undefined) {
    	metaData.classList.toggle('on');
    }
  }
})();