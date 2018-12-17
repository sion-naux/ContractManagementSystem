    function load(){
      return false;
    }
    
    // var btn = document.getElementById("test1");
    var shadow = document.getElementById("black-shadow");
    var dialog = document.getElementById("dialogBox");
    // btn.onclick = function(){
    //   ShowHide(true,shadow,dialog);
    //   return false;
    // }
    
    shadow.onclick = function(){
      ShowHide(false,shadow,dialog);
      return false;
    }
    function ShowHide(Boolean, item1, item2){
        for(var i=1,len=arguments.length;i<len;i++){
          if(Boolean){
            arguments[i].style.display="block";
          }
          else{
            arguments[i].style.display="none";
          }
        }
    }

