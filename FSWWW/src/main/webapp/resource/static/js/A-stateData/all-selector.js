(function(){
  var allInput = document.querySelector('.all input');
  var allLevel1 = document.querySelector('.all-level-1 input');
  var allLevel2 = document.querySelector('.all-level-2 input');
  var point1 = document.querySelector('.all-point-1 input');
  var point2 = document.querySelector('.all-point-2 input');
  
  allInput.addEventListener('input', function(){
    var inputList = document.querySelectorAll('.category-list input');
    allInputClick(this,inputList);
  });

  allLevel1.addEventListener('input', function(){
    var inputList = document.querySelectorAll('.level-1-list input');
    allInputClick(this,inputList);
  });
  allLevel2.addEventListener('input', function(){
    var inputList = document.querySelectorAll('.level-2-list input');
    allInputClick(this,inputList);
  });
  point1.addEventListener('input', function(){
    var inputList = document.querySelectorAll('.point-1-list input');
    allInputClick(this,inputList);
  });

  point2.addEventListener('input', function(){
    var inputList = document.querySelectorAll('.point-2-list input');
    allInputClick(this,inputList);
  });

  function allInputClick(self,List) {
    if(self.checked) {
      for(var i = 0; i < List.length; i++) {
        List[i].checked = true;
      }
    } else {
        for(var j = 0; j < List.length; j++) {
          List[j].checked = false;
        }

    }
  }
})();