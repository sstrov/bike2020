(function() {
  var imageTag = document.getElementsByClassName('image-api')[0];
  
  window.addEventListener('load',onImageChange);
  window.addEventListener('resize',onImageChange);

  function onImageChange() {
    if(window.innerWidth <= 486) {
      imageTag.setAttribute('src','../../static/image/E-openAPI/openapi_img_s.png');
    } else if (window.innerWidth <= 768) {
      imageTag.setAttribute('src','../../static/image/E-openAPI/openapi_img_t.png');
    } else {
      imageTag.setAttribute('src','../../static/image/E-openAPI/openapi_img_pc.png');
    }
  }
})();