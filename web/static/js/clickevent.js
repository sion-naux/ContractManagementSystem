function load() {
    return false;
}


var shadow = document.getElementById("black-shadow");
var dialog = document.getElementById("dialogBox");
// btn.onclick = function(){
//   ShowHide(true,shadow,dialog);
//   return false;
// }

shadow.onclick = function () {
    ShowHide(false, shadow, dialog);
    return false;
};

function contribute(btn) {
    var tr = btn.parentElement.parentElement;
    var contract_num = tr.cells[0].innerHTML;
    console.log("你点的合同编号是：" + contract_num);
    ShowHide(true, shadow, dialog);

    $("#contract_num_a").val(contract_num);

    return false;
};

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

