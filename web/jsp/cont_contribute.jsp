<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>合同管理系统</title>
    <!-- inject:css -->
    <link rel="stylesheet" href="../static/css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="../static/images/favicon.png"/>
    <script src="../static/js/jquery-2.1.1.min.js"></script>
</head>
<body>
<div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper">
            <a class="navbar-brand brand-logo" href="<%=request.getContextPath()%>/index"><img src="../static/images/logo.svg" alt="logo"></a>
            <a class="navbar-brand brand-logo-mini" href="<%=request.getContextPath()%>/index"><img src="../static/images/logo_mini.svg"
                                                                                                    alt="logo"></a>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-center">
            <p class="page-name d-none d-lg-block">早上好！</p>
            <ul class="navbar-nav ml-lg-auto">
                <li class="nav-item">
                    <form class="mt-2 mt-md-0 d-none d-lg-block search-input">
                        <div class="input-group">
                            <span class="input-group-addon d-flex align-items-center"><i
                                    class="icon-magnifier icons"></i></span>
                            <input type="text" class="form-control" placeholder="Search...">
                        </div>
                    </form>
                </li>
                <li class="nav-item dropdown mail-dropdown">
                    <a class="nav-link count-indicator" id="MailDropdown" href="#" data-toggle="dropdown">
                        <i class="icon-envelope-letter icons"></i>
                        <span class="count bg-danger"></span>
                    </a>
                </li>
                <li class="nav-item dropdown notification-dropdown">
                    <a class="nav-link count-indicator" id="notificationDropdown" href="#" data-toggle="dropdown">
                        <i class="icon-speech icons"></i>
                        <span class="count"></span>
                    </a>
                </li>
                <li class="nav-item lang-dropdown d-none d-sm-block">
                    <a class="nav-link" href="#">
                        <p class="mb-0">English <i class="flag-icon flag-icon-gb"></i></p>
                    </a>
                </li>
                <li class="nav-item d-none d-sm-block profile-img">
                    <a class="nav-link profile-image" href="#">
                        <img src="../static/images/faces/face4.jpg" alt="profile-img">
                        <span class="online-status online bg-success"></span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>    <!-- partial -->
    <!-- partial -->
    <div class="container-fluid page-body-wrapper" style="margin-top: -9px;">
        <div class="row row-offcanvas row-offcanvas-right">
            <%
                List<String> l = (List<String>) request.getAttribute("right_list");

            %>
            <!-- partial:partials/_sidebar.html -->
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
                <ul class="nav">
                    <%
                        if (l.contains("起草合同")) {
                            out.print("<li class=\"nav-item nav-category\"><span class=\"nav-link\">合同起草</span></li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/draft\">\n" +
                                    "                            <span class=\"menu-title\">起草合同</span>\n" +
                                    "                            <i class=\"icon-speedometer menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>");
                        }

                    %>
                    <%
                        if (l.contains("定稿合同")) {
                            out.print("<li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/cont_final\">\n" +
                                    "                            <span class=\"menu-title\">待定稿合同</span>\n" +
                                    "                            <i class=\"icon-wrench menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>" + "<li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/over_cont_final\">\n" +
                                    "                            <span class=\"menu-title\">已定稿合同</span>\n" +
                                    "                            <i class=\"icon-wrench menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>");
                        }
                    %>
                    <%
                        if (l.contains("会签合同")) {
                            out.print("<li class=\"nav-item nav-category\">\n" +
                                    "                        <span class=\"nav-link\">合同会签</span>\n" +
                                    "                    </li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/countersign\">\n" +
                                    " <span class=\"menu-title\">待会签合同</span>\n" +
                                    " <i class=\"icon-speedometer menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>\n" +
                                    "<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/over_countersign\">\n" +
                                    " <span class=\"menu-title\">已会签合同</span>\n" +
                                    " <i class=\"icon-grid menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    " </li>");
                        }
                    %>
                    <%
                        if (l.contains("审批合同")) {
                            out.print("<li class=\"nav-item nav-category\">\n" +
                                    "                        <span class=\"nav-link\">合同审批</span>\n" +
                                    "                    </li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/approval\">\n" +
                                    " <span class=\"menu-title\">待审批合同</span>\n" +
                                    " <i class=\"icon-flag menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>\n" +
                                    "<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/jsp/over_approval.jsp\">\n" +
                                    " <span class=\"menu-title\">已审批合同</span>\n" +
                                    " <i class=\"icon-flag menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    " </li> ");
                        }
                    %>
                    <%
                        if (l.contains("签订合同")) {
                            out.print("<li class=\"nav-item nav-category\">\n" +
                                    "                        <span class=\"nav-link\">合同签订</span>\n" +
                                    "                    </li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() + "/conclude\">\n" +
                                    " <span class=\"menu-title\">待签订合同</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>\n" +
                                    "<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/over_conclude\">\n" +
                                    " <span class=\"menu-title\">已签订合同</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    "</li>");
                        }
                    %>
                    <%
                        if (l.contains("查询合同")) {
                            out.print("<li class=\"nav-item nav-category\">\n" +
                                    "                        <span class=\"nav-link\">合同查询</span>\n" +
                                    "                    </li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() +
                                    "/search\">\n" +
                                    " <span class=\"menu-title\">合同信息查询</span>\n" +
                                    " <i class=\"icon-bubbles menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>"
                            );
                        }
                    %>
                    <%
                        if (l.contains("流程查询")) {
                            out.print("<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/jsp/cont_proc_search.jsp\">\n" +
                                    " <span class=\"menu-title\">合同流程查询</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    "</li>");
                        }
                    %>
                    <%
                        if (l.contains("分配会签")) {
                            out.print("<li class=\"nav-item nav-category\">\n" +
                                    "                        <span class=\"nav-link\">系统管理</span>\n" +
                                    "                    </li>\n" +
                                    "                    <li class=\"nav-item\">\n" +
                                    "                        <a class=\"nav-link\" href=\"" + request.getContextPath() +
                                    "/contribute\">\n" +
                                    " <span class=\"menu-title\">分配合同</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    "                        </a>\n" +
                                    "                    </li>\n" +
                                    "<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/authManage\">\n" +
                                    " <span class=\"menu-title\">权限管理</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    "</li>\n" +
                                    "<li class=\"nav-item\">\n" +
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/jsp/auth_manage.jsp\">\n" +
                                    " <span class=\"menu-title\">日志管理</span>\n" +
                                    " <i class=\"icon-pie-chart menu-icon\"></i>\n" +
                                    " </a>\n" +
                                    "</li>");
                        }

                    %>


                </ul>
            </nav>
            <!-- partial -->
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 grid-margin">
                        <div class="card overflow-hidden dashboard-curved-chart">
                            <div class="card-body mx-3">
                                <h2 class="card-title border-bottom-none">分配合同</h2>
                            </div>
                            <div class="col-lg-12 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">待分配合同</h4>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th>合同编号</th>
                                                <th>合同名称</th>
                                                <th>起草日期</th>
                                                <th>起草人</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%--<tr>--%>
                                            <%--<td>这是合同名称</td>--%>
                                            <%--<td>这是起草日期</td>--%>
                                            <%--<td>这是起草人</td>--%>
                                            <%--<td>--%>
                                            <%--<button class="btn btn-info" id="test1">分配</button>--%>
                                            <%--</td>--%>
                                            <%--</tr>--%>
                                            <%
                                                String contribute_contract_list = (String) request.getAttribute("contribute_contract_list");
                                                if (contribute_contract_list != null) {
                                                    out.print(contribute_contract_list);
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex align-items-center justify-content-between flex-wrap">
                                <p class="mb-0">Showing 1 to 10 of 57 entries</p>
                                <nav>
                                    <ul class="pagination rounded-separated pagination-info mt-3">
                                        <li class="page-item"><a class="page-link" href="#"><i
                                                class="mdi mdi-chevron-left"></i></a></li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                                        <li class="page-item"><a class="page-link" href="#"><i
                                                class="mdi mdi-chevron-right"></i></a></li>
                                    </ul>
                                </nav>
                            </div>
                            <div id="curved-line-chart" class="float-chart float-chart-mini"></div>
                        </div>
                    </div>
                </div>
                <div class="black-shadow" id="black-shadow" tabindex=-1>
                </div>
                <div class="dialog" id="dialogBox">
                    <!-- <div class="col-12 stretch-card">
                      <div class="card">
                        <div class="card-body">
                          <h4 class="card-title">会签合同</h4>
                          <span class="card-description col-sm-3">
                            合同名称
                          </span>
                          <span class="card-description">
                            合同名称
                          </span>
                          <form class="forms-sample">
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label card-description">会签意见</label>
                              <div class="col-sm-9">
                                  <textarea class="form-control" rows="5" placeholder="输入会签意见"></textarea>
                              </div>
                            </div>
                            <div style="text-align: center">
                            <button type="submit" class="btn btn-success mr-2" >提交</button>
                            <button class="btn btn-light">取消</button>
                            </div>
                          </form>
                        </div>
                      </div>
                    </div> -->
                    <%--<div class="container-selector">--%>
                    <%--<form>--%>
                    <%--<div>--%>
                    <%--<p>分配会签</p>--%>
                    <%--</div>--%>
                    <%--<ul class="data-list" id="lList" style="overflow-y: scroll;height: 250px;">--%>
                    <%--<%--%>
                    <%--String contribute_person_list = (String) request.getAttribute("contribute_person_list");--%>
                    <%--if (contribute_person_list != null) {--%>
                    <%--out.print(contribute_person_list);--%>
                    <%--}--%>
                    <%--%>--%>
                    <%--</ul>--%>
                    <%--<div class="button-box">--%>
                    <%--<button type="button" name="button" id="add">添 加</button>--%>
                    <%--<button type="button" name="button" id="remove">删 除</button>--%>
                    <%--</div>--%>
                    <%--<ul class="data-list" id="rList" style="height: 250px">--%>

                    <%--</ul>--%>
                    <%--<div style="text-align: center">--%>
                    <%--<button class="btn btn-success mr-2">提交</button>--%>
                    <%--<button class="btn btn-light">取消</button>--%>
                    <%--</div>--%>
                    <%--</form>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <div class="container-selector">
                        <form id="myForm" action="<%=request.getContextPath()%>/contribute" method="post">
                            <input type="hidden" name="countersign_list" value="" id="countersign_list_Array">
                            <input type="hidden" name="approval_list" value="" id="approval_list_Array">
                            <input type="hidden" name="sign_list" value="" id="sign_list_Array">
                            <input type="hidden" name="contract_num_a" value="" id="contract_num_a">

                                <%--<a id="contract_num_a"></a>--%>
                            <div>
                                <p>分配会签人</p>
                            </div>
                            <ul class="data-list" id="lList1"  style="overflow-y: scroll;height: 250px;">
                                <%
                                    String contribute_person_list = (String) request.getAttribute("contribute_person_list");
                                    if (contribute_person_list != null) {
                                        out.print(contribute_person_list);
                                    }
                                %>
                            </ul>
                            <div class="button-box">
                                <button type="button" name="button" id="add1">添 加</button>
                                <button type="button" name="button" id="remove1">删 除</button>
                            </div>
                            <ul class="data-list" id="rList1"  style="height: 250px">

                            </ul>
                            <div>
                                <p>分配审批人</p>
                            </div>
                            <ul class="data-list" id="lList2" style="overflow-y: scroll;height: 250px;">
                                <%
                                    if (contribute_person_list != null) {
                                        out.print(contribute_person_list);
                                    }
                                %>
                            </ul>
                            <div class="button-box">
                                <button type="button" name="button" id="add2">添 加</button>
                                <button type="button" name="button" id="remove2">删 除</button>
                            </div>
                            <ul class="data-list" id="rList2"  style="height: 250px">

                            </ul>
                            <div>
                                <p>分配签订人</p>
                            </div>
                            <ul class="data-list" id="lList3" style="overflow-y: scroll;height: 250px;">
                                <%
                                    if (contribute_person_list != null) {
                                        out.print(contribute_person_list);
                                    }
                                %>
                            </ul>
                            <div class="button-box">
                                <button type="button" name="button" id="add3">添 加</button>
                                <button type="button" name="button" id="remove3">删 除</button>
                            </div>
                            <ul class="data-list" id="rList3"  style="height: 250px">

                            </ul>


                            </ul>
                            <div style="text-align: center">
                                <button type="button" class="btn btn-success mr-2" onclick="submit_contribute();">提交</button>

                                <button type="button" class="btn btn-light" onclick="ShowHide(false,shadow,dialog);return false;">取消</button>
                            </div>
                        </form>

                    </div>

                </div>

            </div>
            <!-- content-wrapper ends -->
            <!-- partial:partials/_footer.html -->
            <footer class="footer">
                <div class="container-fluid clearfix">
            <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © 2018 Bootstrapdash
              All rights reserved. </span>
                    <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i
                            class="mdi mdi-heart text-danger"></i></span>
                </div>
            </footer>
            <!-- partial -->
        </div>
        <!-- row-offcanvas ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->

<!-- inject:js -->
<script src="../static/js/clickevent.js"></script>
<script src="../static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        var lList1 = $("#lList1");
        var llList1 = document.getElementById("lList1");
        var rList1 = $("#rList1");
        var items11 = $("#lList1 li");
        var items12 = $("#RList1 li");

        var lList2 = $("#lList2");
        var llList2 = document.getElementById("lList2");
        var rList2 = $("#rList2");
        var items21 = $("#lList2 li");
        var items22 = $("#RList2 li");


        var lList3 = $("#lList3");
        var llList3 = document.getElementById("lList3");
        var rList3 = $("#rList3");
        var items31 = $("#lList3 li");
        var items32 = $("#RList3 li");

        for (var i = 0; i < items11.length; i++) {
            items11[i].onclick = itemsclick;
            items11[i].ondblclick = itemsdblclick1;
        }
        for (var i = 0; i < items12.length; i++) {
            items12[i].onclick = itemsclick;
            items12[i].ondblclick = itemsdblclick1;
        }
        for (var i = 0; i < items21.length; i++) {
            items21[i].onclick = itemsclick;
            items21[i].ondblclick = itemsdblclick2;
        }
        for (var i = 0; i < items22.length; i++) {
            items22[i].onclick = itemsclick;
            items22[i].ondblclick = itemsdblclick2;
        }
        for (var i = 0; i < items31.length; i++) {
            items31[i].onclick = itemsclick;
            items31[i].ondblclick = itemsdblclick3;
        }
        for (var i = 0; i < items32.length; i++) {
            items32[i].onclick = itemsclick;
            items32[i].ondblclick = itemsdblclick3;
        }

        function itemsdblclick1() {
            if (this.parentNode === llList1) {
                rList1.append(this);
            } else {
                lList1.append(this);
            }
        }


        function itemsdblclick2() {
            if (this.parentNode === llList2) {
                rList2.append(this);
            } else {
                lList2.append(this);
            }
        }


        function itemsdblclick3() {
            if (this.parentNode === llList3) {
                rList3.append(this);
            } else {
                lList3.append(this);
            }
        }

        function itemsclick() {
            var classname = this.className;
            if (classname === "selected") {
                this.className = "";
            } else {
                this.className = "selected";
            }
        }

        function itemsMove1() {
            var items1 = $("#lList1 li.selected");
            var items2 = $("#RList1 li.selected");
            for (var i = 0; i < items1.length; i++) {
                if (this.id === "add1") {
                    rList1.append(items1[i]);
                    items1[i].className = "";
                } else {
                    lList1.append(items1[i]);
                    items1[i].className = "";
                }
            }
            for (var i = 0; i < items2.length; i++) {
                if (this.id === "add1") {
                    rList1.append(items2[i]);
                    items2[i].className = "";
                } else {
                    lList1.append(items2[i]);
                    items2[i].className = "";
                }
            }
        }

        function itemsMove2() {
            var items1 = $("#lList2 li.selected");
            var items2 = $("#RList2 li.selected");
            for (var i = 0; i < items1.length; i++) {
                if (this.id === "add2") {
                    rList2.append(items1[i]);
                    items1[i].className = "";
                } else {
                    lList2.append(items1[i]);
                    items1[i].className = "";
                }
            }
            for (var i = 0; i < items2.length; i++) {
                if (this.id === "add2") {
                    rList2.append(items2[i]);
                    items2[i].className = "";
                } else {
                    lList2.append(items2[i]);
                    items2[i].className = "";
                }
            }
        }

        function itemsMove3() {
            var items1 = $("#lList3 li.selected");
            var items2 = $("#RList3 li.selected");

            for (var i = 0; i < items1.length; i++) {
                if (this.id === "add3") {
                    rList3.append(items1[i]);
                    items1[i].className = "";
                } else {
                    lList3.append(items1[i]);
                    items1[i].className = "";
                }
            }
            for (var i = 0; i < items2.length; i++) {
                if (this.id === "add3") {
                    rList3.append(items2[i]);
                    items2[i].className = "";
                } else {
                    lList3.append(items2[i]);
                    items2[i].className = "";
                }
            }
        }

        $("#add1").on("click", itemsMove1);
        $("#remove1").on("click", itemsMove1);
        $("#add2").on("click", itemsMove2);
        $("#remove2").on("click", itemsMove2);
        $("#add3").on("click", itemsMove3);
        $("#remove3").on("click", itemsMove3);
    });

</script>
<!-- End custom js for this page-->
</body>

</html>
