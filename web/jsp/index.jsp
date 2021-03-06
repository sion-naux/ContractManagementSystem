<%@ page import="java.util.List" %>
<%@ page import="entity.Number" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>合同管理系统</title>
    <!-- inject:css -->
    <link rel="stylesheet" type="text/css" href="../static/css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="../static/images/favicon.png"/>
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
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/overapproval\">\n" +
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
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/procedure\">\n" +
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
                                    " <a class=\"nav-link\" href=\"" + request.getContextPath() + "/logManage\">\n" +
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
                        <div class="card performance-cards">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col d-flex flex-row justify-content-center align-items-center">
                                        <div class="wrapper icon-circle bg-success">
                                            <i class="icon-rocket icons"></i>
                                        </div>

                                        <%
                                            Number number = (Number) request.getAttribute("number");
                                        %>
                                        <div class="wrapper text-wrapper">
                                            <p class="text-dark">
                                                <%
                                                    if(number != null){
                                                        out.print(number.getDingGao());
                                                    }
                                                %></p>
                                            <p>待定稿合同</p>
                                        </div>
                                    </div>
                                    <div class="col d-flex flex-row justify-content-center align-items-center">
                                        <div class="wrapper icon-circle bg-danger">
                                            <i class="icon-briefcase icons"></i>
                                        </div>
                                        <div class="wrapper text-wrapper">
                                            <p class="text-dark"><%
                                                if(number != null){
                                                    out.print(number.getHuiQian());
                                                }
                                            %></p>
                                            <p>待会签合同</p>
                                        </div>
                                    </div>
                                    <div class="col d-flex flex-row justify-content-center align-items-center">
                                        <div class="wrapper icon-circle bg-warning">
                                            <i class="icon-umbrella icons"></i>
                                        </div>
                                        <div class="wrapper text-wrapper">
                                            <p class="text-dark"><%
                                                if(number != null){
                                                    out.print(number.getShenPi());
                                                }
                                            %></p>
                                            <p>待审批合同</p>
                                        </div>
                                    </div>
                                    <div class="col d-flex flex-row justify-content-center align-items-center">
                                        <div class="wrapper icon-circle bg-primary">
                                            <i class="icon-check icons"></i>
                                        </div>
                                        <div class="wrapper text-wrapper">
                                            <p class="text-dark"><%
                                                if(number != null){
                                                    out.print(number.getQianDing());
                                                }
                                            %></p>
                                            <p>待签订合同</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ROW ENDS -->
                <div class="row">
                    <div class="col-md-6 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title mb-0">Statistics</h4>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-inline-block pt-3">
                                        <div class="d-flex">
                                            <h2 class="mb-0">$10,200</h2>
                                            <div class="d-flex align-items-center ml-2">
                                                <i class="mdi mdi-clock text-muted"></i>
                                                <small class=" ml-1 mb-0">Updated: 9:10am</small>
                                            </div>
                                        </div>
                                        <small class="text-gray">Raised from 89 orders.</small>
                                    </div>
                                    <div class="d-inline-block">
                                        <div class="bg-success px-4 py-2 rounded">
                                            <i class="mdi mdi-buffer text-white icon-lg"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title mb-0">Daily Order</h4>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-inline-block pt-3">
                                        <div class="d-flex">
                                            <h2 class="mb-0">$2256</h2>
                                            <div class="d-flex align-items-center ml-2">
                                                <i class="mdi mdi-clock text-muted"></i>
                                                <small class=" ml-1 mb-0">Updated: 05:42pm</small>
                                            </div>
                                        </div>
                                        <small class="text-gray">hey, let’s have lunch together</small>
                                    </div>
                                    <div class="d-inline-block">
                                        <div class="bg-warning px-4 py-2 rounded">
                                            <i class="mdi mdi-wallet text-white icon-lg"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row section social-section">
                    <div class="col-lg-4 grid-margin stretch-card">
                        <div class="social-card w-100 bg-facebook">
                            <div class="social-icon">
                                <i class="icon-social-facebook icons"></i>
                            </div>
                            <div class="content">
                                <p>+ 1500</p>
                                <p>Facebook Likes</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 grid-margin stretch-card">
                        <div class="social-card w-100 bg-twitter">
                            <div class="social-icon">
                                <i class="icon-social-twitter icons"></i>
                            </div>
                            <div class="content">
                                <p>+574</p>
                                <p>Twitter Followers</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 grid-margin stretch-card">
                        <div class="social-card w-100 bg-dribbble">
                            <div class="social-icon">
                                <i class="icon-social-dribbble icons"></i>
                            </div>
                            <div class="content">
                                <p>+450</p>
                                <p>Dribble Shots</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ROW ENDS -->
                <div class="row">
                    <div class="col-lg-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">Manage Users</h2>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start Date</th>
                                            <th>Assign</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Airi Satou</td>
                                            <td>Accountant</td>
                                            <td>Tokyo</td>
                                            <td>55</td>
                                            <td>2009/10/09</td>
                                            <td>$162,700</td>
                                        </tr>
                                        <tr>
                                            <td>Angelica Ramos</td>
                                            <td>Chief Executive Officer (CEO)</td>
                                            <td>London</td>
                                            <td>30</td>
                                            <td>2009/10/09</td>
                                            <td>$190,500</td>
                                        </tr>
                                        <tr>
                                            <td>Ashton Cox</td>
                                            <td>Regional Director</td>
                                            <td>San Francisco</td>
                                            <td>36</td>
                                            <td>2004/12/02</td>
                                            <td>$62,700</td>
                                        </tr>
                                        <tr>
                                            <td>Angelica Ramos</td>
                                            <td>Chief Executive Officer (CEO)</td>
                                            <td>London</td>
                                            <td>30</td>
                                            <td>2011/07/25</td>
                                            <td>$190,500</td>
                                        </tr>
                                        <tr>
                                            <td>Ashton Cox</td>
                                            <td>Regional Director</td>
                                            <td>San Francisco</td>
                                            <td>32</td>
                                            <td>2004/12/02</td>
                                            <td>$62,700</td>
                                        </tr>
                                        <tr>
                                            <td>Angelica Ramos</td>
                                            <td>Chief Executive Officer (CEO)</td>
                                            <td>London</td>
                                            <td>31</td>
                                            <td>2011/07/25</td>
                                            <td>$190,500</td>
                                        </tr>
                                        <tr>
                                            <td>Ashton Cox</td>
                                            <td>Regional Director</td>
                                            <td>Tokyo</td>
                                            <td>39</td>
                                            <td>2004/12/02</td>
                                            <td>$62,700</td>
                                        </tr>
                                        </tbody>
                                    </table>
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
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ROW ENDS -->
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

</body>

</html>
