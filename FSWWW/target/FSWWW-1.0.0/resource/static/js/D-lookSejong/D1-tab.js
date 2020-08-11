(function(){
  var tabTitle = document.querySelector('#tab_moType1 .title');
  var tabMoType1 = document.getElementById('tab_moType1');

  tabTitle.addEventListener('click',onTabToggle);
  window.addEventListener('resize', onTabTypeToggle);
  window.addEventListener('load', onTabTypeToggle);

  function onTabTypeToggle() {
    if(window.innerWidth < 768) {
      tabMoType1.className = 'mobile';
    } else {
      tabMoType1.className = 'pc';
    }
  }
  function onTabToggle() {
    $('.tab-ul li').slideToggle();
  }
})();