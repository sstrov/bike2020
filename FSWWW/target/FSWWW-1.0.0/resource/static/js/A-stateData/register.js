(function(){
  var sheetRadio = document.getElementsByClassName('sheet');
  var uploadRadio = document.getElementsByClassName('upload');
  var registerContainer = document.getElementsByClassName('register');
  var registerOpenBtn = document.getElementsByClassName('register-open');

  
  window.addEventListener('load',function(){
    for(var i = 0; i < sheetRadio.length; i++) {
      sheetRadio[i].addEventListener('input',uploadDisabled);
    } 
    for(var j = 0; j < uploadRadio.length; j++) {
      uploadRadio[j].addEventListener('input',uploadAbled);
    }
    
    registerContainer[0].addEventListener('click',registerClosed);
    registerOpenBtn[0].addEventListener('click',registerOpen);
  });

  function registerOpen() {
    registerContainer[0].classList.add('on');
  }
  function registerClosed(e) {
    var closedBtn = document.getElementsByClassName('closed');
    for(var k = 0; k < closedBtn.length; k++) {
      if(e.target == closedBtn[k]) {
        this.classList.remove('on');
      }
    }
  }
  
  function uploadDisabled() {
    var fileInput = document.getElementById('find-file');
    if(this.checked) {
      fileInput.setAttribute('disabled',true);
    }
  }
  function uploadAbled() {
    var fileInput = document.getElementById('find-file');
    if(this.checked) {
      fileInput.removeAttribute('disabled');
    }
  }

  
})();