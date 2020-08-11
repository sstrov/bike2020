(function(){
  var tableTag = document.getElementsByClassName('table-responsive')[0];

  window.addEventListener('load',tablePcToggle);
  window.addEventListener('resize',tablePcToggle);

  function tablePcToggle() {
    if(window.innerWidth <= 768) {
      tableTag.classList.remove('table-pc');
    } else {
      tableTag.classList.add('table-pc');
    }
  }

})();