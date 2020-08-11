(function () {
  // 모달 닫기
  var modalList = document.getElementsByClassName('modal');

  // [...modalList].forEach(element => element.addEventListener('click', onModalClose));

  for(var k = 0; k < modalList.length; k++) {
    modalList[k].addEventListener('click', onModalClose)
  }
  function onModalClose(e) {
    var closeBtn = this.getElementsByClassName('close');

    for (var i = 0; i < closeBtn.length; i++) {
      if (e.target == closeBtn[i]) {
        this.classList.remove('on');
      }
    }
  }

  // 모달 열기
  /*var openBtnList = document.getElementsByClassName('modal-open');
  // [...openBtnList].forEach(element => element.addEventListener('click', onModalOpen));
  
  for(var l = 0; l < openBtnList.length; l++) {
    openBtnList[l].addEventListener('click', onModalOpen);
  }*/
  /*function onModalOpen() {
	  alert("dd");
    var modalList = document.getElementsByClassName('modal');
    var modalValue = this.getAttribute('data-modal');

    for (var j = 0; j < modalList.length; j++) {
      if (modalValue == modalList[j].getAttribute('data-modal')) {
        modalList[j].classList.add('on');
      }
    }
  }*/

})();