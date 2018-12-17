function load() {
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

function countersign(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
}

function over_countersign(btn) {

    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
    var cont_num = $("#box_cont_num").html();
    $.ajax({
        url : "http://localhost:8080/get_countersign_content?cont_num=" + cont_num,
        type : "GET",
        // dataType : 'text',
        success (data){
            var item = JSON.parse(data)
            document.getElementById("sign_message").innerHTML = item.msg;

        },
        error (data){
            alert("操作失败");
        }

    });


}


function ShowHide(Boolean, item1, item2) {
    for (var i = 1, len = arguments.length; i < len; i++) {
        if (Boolean) {
            arguments[i].style.display = "block";
        }
        else {
            arguments[i].style.display = "none";
        }
    }
}

function submit_contribute() {
    var countersign_list = document.getElementById("rList1");
    var countersign_list_items = countersign_list.childNodes;
    var countersign_list_items_len = countersign_list_items.length;
    for (var i = 1; i < countersign_list_items_len; i++) {
        console.log("countersign_list:" + "[" + i + "]" + countersign_list_items[i].innerHTML);
    }
    var countersign_list_Array = new Array();
    for (var i = 1; i < countersign_list_items_len; i++) {
        countersign_list_Array.push(countersign_list_items[i].innerHTML);
    }

    var approval_list = document.getElementById("rList2");
    var approval_list_items = approval_list.childNodes;
    var approval_list_items_len = approval_list_items.length;
    for (var i = 1; i < approval_list_items_len; i++) {
        console.log("approval_list:" + "[" + i + "]" + approval_list_items[i].innerHTML);
    }
    var approval_list_Array = new Array();
    for (var i = 1; i < approval_list_items_len; i++) {
        approval_list_Array.push(approval_list_items[i].innerHTML);
    }

    var sign_list = document.getElementById("rList3");
    var sign_list_items = sign_list.childNodes;
    var sign_list_items_len = sign_list_items.length;
    for (var i = 1; i < sign_list_items_len; i++) {
        console.log("sign_list:" + "[" + i + "]" + sign_list_items[i].innerHTML);
    }
    var sign_list_Array = new Array();
    for (var i = 1; i < sign_list_items_len; i++) {
        sign_list_Array.push(sign_list_items[i].innerHTML);
    }
    $("#countersign_list_Array").val(countersign_list_Array);
    $("#approval_list_Array").val(approval_list_Array);
    $("#sign_list_Array").val(sign_list_Array);
    document.getElementById("myForm").submit()
}

function submit_sign(){
    // $(document).ready(function () {
    //     $("#sign_submit").click(function (e) {
    //         e.preventDefault();
            var obj = $(this);
            // var cont_num = document.getElementById("box_cont_num").innerText;
            // var cont_name = document.getElementById("box_cont_name").innerText;
            // var sign_msg = document.getElementById("sign_message").innerText;
            var cont_num = $("#box_cont_num").html();
            var cont_name = $("#box_cont_name").text();
            var sign_msg = $("#sign_message").val();
            var data = cont_num + "&" + cont_name + "&" + sign_msg;
            $.ajax({
                url : "http://localhost:8080/countersign",
                type : "POST",
                data :  data,
                // dataType : 'text',
                success (data){
                    // alert("chenggong");
                    ShowHide(false,shadow,dialog);
                    var row = document.getElementById(cont_num);
                    row.remove();


                },
                error (data){
                    alert(data.msg);
                }

            });

    //     })
    // })

}
