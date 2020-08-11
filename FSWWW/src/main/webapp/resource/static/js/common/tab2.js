
(function () {
  var tabContainer = document.getElementsByClassName('tab2-container');

  for(var l = 0; l < tabContainer.length; l++) {
	  if(tabContainer[l] != undefined) {
	    tabContainer[l].addEventListener('click',ontabClick);
	    if(tabContainer[l].getElementsByClassName('tab2-name')[0] != undefined) {
	    	tabContainer[l].getElementsByClassName('tab2-name')[0].click();
	    }
	  }
  }


function ontabClick(e) {
  var tabNum = getElementIndex(e.target);
  var tabContentList = this.getElementsByClassName('tab2-content');
  var tabs = this.getElementsByClassName('tab2-name');
  
  for(var k = 0; k < tabs.length; k++) {
    if(e.target == tabs[k]) {
      tabMotion(tabNum,tabContentList,tabs);
    }
  }
}

function tabMotion(tabNum,tabContentList,tabs) {
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
	if(target.parentNode != undefined) {
		return  [].indexOf.call(target.parentNode.children, target);
	} else {
		return null;
	}
}