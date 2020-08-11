(function () {
  var tabContainer = document.getElementsByClassName('tab-container');
  for (var l = 0; l < tabContainer.length; l++) {
    tabContainer[l].addEventListener('click', ontabClick);
    tabContainer[l].getElementsByClassName('tab-name')[0].click();
  }


  function ontabClick(e) {
    var elem = e.target;
    var tabNum = getElementIndex(elem);
    var tabContentList = this.getElementsByClassName('tab-content');
    var tabs = this.getElementsByClassName('tab-name');

    //클래스를 가지고있지 않으면 부모 엘리먼트로 갱신해라
    while (!elem.classList.contains('tab-name')) {
      elem = elem.parentNode;

      //nodeName은 html 태그 이름을 대문자 문자열로 가지고있음!
      if (elem.nodeName == 'BODY') {
        elem == null;
        break;
      }
    }
    tabNum = getElementIndex(elem);
    for (var k = 0; k < tabs.length; k++) {
      if (elem == tabs[k]) {
        tabMotion(tabNum, tabContentList, tabs);
      }
    }

  }

  function tabMotion(tabNum, tabContentList, tabs) {
    for (var i = 0; i < tabs.length; i++) {
      if (i == tabNum) {
        tabs[i].classList.add('on');
        $(tabs[i]).prop("title", "선택됨");
      } else {
        tabs[i].classList.remove('on');
        $(tabs[i]).removeAttr("title");
      }
    }

    for (var j = 0; j < tabContentList.length; j++) {
      if (j == tabNum) {
        tabContentList[j].classList.add('on');
      } else {
        tabContentList[j].classList.remove('on');
      }
    }
  }
})();

function getElementIndex(target) {
	if(target != undefined) {
		return [].indexOf.call(target.parentNode.children, target);
	} else {
		return null;
	}
}