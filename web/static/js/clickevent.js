    function load(){
      return false;
    }
    

    var shadow = document.getElementById("black-shadow");
    var dialog = document.getElementById("dialogBox");
    // btn.onclick = function(){
    //   ShowHide(true,shadow,dialog);
    //   return false;
    // }
    
    shadow.onclick = function(){
      ShowHide(false,shadow,dialog);
      return false;
    };
    function contribute(btn) {
        var tr = btn.parentElement.parentElement;
        var contract_num = tr.cells[0].innerHTML;
        console.log("你点的合同编号是："+contract_num);
        ShowHide(true,shadow,dialog);
        $("#contract_num_a").text(contract_num);

        return false;
    };
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

