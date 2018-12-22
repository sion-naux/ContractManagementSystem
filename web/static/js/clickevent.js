//授权的checkbox只能点一个
$(document).ready(function(){
    $('#checkbox_group_role').find('input[type=checkbox]').bind('click', function(){
        $('#checkbox_group_role').find('input[type=checkbox]').not(this).attr("checked", false);
    });
});


function load() {
    return false;
}


var shadow = document.getElementById("black-shadow");
var dialog = document.getElementById("dialogBox");
var dialog2 = document.getElementById("dialogBox2");
// btn.onclick = function(){
//   ShowHide(true,shadow,dialog);
//   return false;
// }

shadow.onclick = function () {
    ShowHide(false, shadow, dialog);
    ShowHide(false, shadow, dialog2);
    return false;
};

//分配合同按钮
function contribute(btn) {
    var tr = btn.parentElement.parentElement;
    var contract_num = tr.cells[0].innerHTML;
    console.log("你点的合同编号是：" + contract_num);
    ShowHide(true, shadow, dialog);

    $("#contract_num_a").val(contract_num);

    return false;
};

//授权按钮
function auth(btn) {
    var tr = btn.parentElement.parentElement;
    var username = tr.cells[0].innerHTML;
    console.log("你点的用户名是：" + username);
    ShowHide(true, shadow, dialog2);
    $("#username_input").val(username);
    return false;

}

//添加角色按钮
function add_role(btn) {
    ShowHide(true, shadow, dialog);

    return false;

}

//显示弹框
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

//分配合同提交响应
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

//提交授权
function submit_auth(){

    var s = document.getElementsByName("checkbox_role");
    var role_list_Array = "";

    for( var i = 0; i < s.length; i++ )
    {
        if ( s[i].checked ){
            role_list_Array= s[i].value;
            break;
        }
    }
    $("#role_list").val(role_list_Array);


    document.getElementById("authForm").submit()
}

//提交添加角色
function submit_add_role(){

    var s = document.getElementsByName("checkbox_addrole");
    var addrole_list_Array = new Array();

    for( var i = 0; i < s.length; i++ )
    {
        if ( s[i].checked ){
            addrole_list_Array.push(s[i].value);

        }
    }
    $("#addrole_list").val(addrole_list_Array);


    document.getElementById("addrole_form").submit()
}
function approval(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
}

function search_info(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_content").innerHTML = tr.cells[4].innerHTML;
    ShowHide(true, shadow, dialog);
}

function cancel(btn){
    ShowHide(false, shadow, dialog);
}

function over_approval(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_content").innerHTML = tr.cells[4].innerHTML;
    document.getElementById("box_approval_content").innerHTML = tr.cells[5].innerHTML;
    ShowHide(true, shadow, dialog);
}

function over_countersign(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
    var cont_num = $("#box_cont_num").html();
    alert(cont_num);
    $.ajax({
        url : "http://localhost:8080/get_countersign_content?cont_num=" + cont_num,
        type : "GET",
        // dataType : 'text',
        success (data){
            var item = JSON.parse(data);

            document.getElementById("sign_message").innerHTML = item.msg;

        },
        error (data){
            alert("操作失败");
        }

    });
}

function countersign(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
}

function conclude(btn) {
    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
}

function over_conclude(btn) {

    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
    var cont_num = $("#box_cont_num").html();
    $.ajax({
        url : "http://localhost:8080/get_conclude_content?cont_num=" + cont_num,
        type : "GET",
        // dataType : 'text',
        success (data){
            var item = JSON.parse(data);
            document.getElementById("conclude_message").innerHTML = item.msg;
        },
        error (data){
            alert("操作失败");
        }

    });
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
    alert(data);
    $.ajax({
        url : "http://localhost:8080/countersign",
        type : "POST",
        data :  data,
        // dataType : 'text',
        success (data){
            alert("chenggong");
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

function submit_conclude(){
    // $(document).ready(function () {
    //     $("#sign_submit").click(function (e) {
    //         e.preventDefault();
    var obj = $(this);
    // var cont_num = document.getElementById("box_cont_num").innerText;
    // var cont_name = document.getElementById("box_cont_name").innerText;
    // var sign_msg = document.getElementById("sign_message").innerText;
    var cont_num = $("#box_cont_num").html();
    var cont_name = $("#box_cont_name").text();
    var sign_msg = $("#conclude_message").val();
    var data = cont_num + "&" + cont_name + "&" + sign_msg;
    alert(data);
    $.ajax({
        url : "http://localhost:8080/conclude",
        type : "POST",
        data :  data,
        // dataType : 'text',
        success (data){
            alert("chenggong");
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



function submit_approval1(){
    var obj = $(this);
    var cont_num = $("#box_cont_num").html();
    var cont_name = $("#box_cont_name").text();
    var sign_msg = $("#approval_message").val();
    var data = cont_num + "&" + cont_name + "&" + sign_msg + "&" +"y";
    alert(data);
    $.ajax({
        url : "http://localhost:8081/approval",
        type : "POST",
        data :  data,
        // dataType : 'text',
        success (data){
            alert("chenggong");
            ShowHide(false,shadow,dialog);
            var row = document.getElementById(cont_num);
            row.remove();
        },
        error (data){
            alert(data.msg);
        }
    });
}

function submit_approval2(){
    var obj = $(this);
    var cont_num = $("#box_cont_num").html();
    var cont_name = $("#box_cont_name").text();
    var sign_msg = $("#approval_message").val();
    var data = cont_num + "&" + cont_name + "&" + sign_msg + "&" +"n";
    alert(data);
    $.ajax({
        url : "http://localhost:8080/approval",
        type : "POST",
        data :  data,
        // dataType : 'text',
        success (data){
            ShowHide(false,shadow,dialog);
            alert("chenggong");
            var row = document.getElementById(cont_num);
            row.remove();
        },
        error (data){
            alert(data.msg);
        }
    });
}


function submit_search(){
    var obj = $(this);
    var search_message = $("#search_message").val();
    var data = search_message;
    $.ajax({
        url : "http://localhost:8080/query_special?search_message=" + search_message,
        type : "GET",
        // data :  data,
        // dataType : 'text',
        success (data){
            var items = JSON.parse(data);
            var str;
            $("#search-content").html("");
            $.each(items, function(index, item){
                 str = "<tr>" +
                    "<td>" + item.name + "</td>" +
                    "<td>" + item.beginTime + "</td>" +
                    "<td>" + item.userName + "</td>" +
                    "<td>" + item.type + "</td>" +
                     "<td>" + item.content + "</td>" +
                    "<td><button class=\"btn btn-info\" onclick=\"search_info(this)\">查看</button></td>" +
                "</tr>";
                $("#search-content").append(str);
            })
            $('table tr').find('td:eq(4)').hide();

        },
        error (data){
            var data = JSON.parse(data);
            alert(data.data);
        }
    });
}

function final_cont(btn) {

    var tr = btn.parentElement.parentElement;
    document.getElementById("box_cont_num").innerHTML = tr.cells[0].innerHTML;
    document.getElementById("box_cont_name").innerHTML = tr.cells[1].innerHTML;
    ShowHide(true, shadow, dialog);
    var cont_num = $("#box_cont_num").html();
    $.ajax({
        url : "http://localhost:8080/get_final_content?cont_num=" + cont_num,
        type : "GET",
        // dataType : 'text',
        success (data){
            var item = JSON.parse(data);
            document.getElementById("box_client_name").innerHTML = item.client_name;
            document.getElementById("box_begin_time").innerHTML = item.begin_time;
            document.getElementById("box_end_time").innerHTML = item.end_time;
            document.getElementById("contract_content").innerHTML = item.contract_content;
        },
        error (data){
            alert("操作失败");
        }

    });
}

function submit_final(btn) {

    var cont_num = $("#box_cont_num").html();
    $.ajax({
        url : "http://localhost:8080/get_final_content",
        type : "POST",
        data : cont_num,
        // dataType : 'text',
        success (data){

        },
        error (data){
            alert("操作失败");
        }
    });
}

function procedure_search(){
    var obj = $(this);
    var search_message = $("#search_message").val();
    alert(search_message)
    $.ajax({
        url : "http://localhost:8081/choice?search_message=" + search_message,
        type : "GET",
        // data :  data,
        // dataType : 'text',
        success (data){

        },
        error (data){
            var data = JSON.parse(data);
            alert(data.data);
        }
    });
}