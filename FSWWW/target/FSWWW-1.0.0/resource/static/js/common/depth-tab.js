
(function () {
  var tabContainer = document.getElementsByClassName('depth-tab-container');
  
  for(var l = 0; l < tabContainer.length; l++) {
    tabContainer[l].addEventListener('click',ontabClick_depth);
    tabContainer[l].getElementsByClassName('depth-tab-name')[0].click();
  }


function ontabClick_depth(e) {
  var tabNum = getElementIndex(e.target);
  var tabContentList = this.getElementsByClassName('depth-tab-content');
  var tabs = this.getElementsByClassName('depth-tab-name');
  
  for(var k = 0; k < tabs.length; k++) {
    if(e.target == tabs[k]) {
      tabMotion_depth(tabNum,tabContentList,tabs);
    }
  }
}

function tabMotion_depth(tabNum,tabContentList,tabs) {
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