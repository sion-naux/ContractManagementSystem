<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>合同管理系统</title>
  <!-- inject:css -->
  <link rel="stylesheet" href="../static/css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="images/favicon.png" />
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
                  <h2 class="card-title border-bottom-none">签订合同</h2>
                </div>
                <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                          <div class="card-body">
                            <h4 class="card-title">未签订合同</h4>
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
                                  <%--<td>Jacob</td>--%>
                                  <%--<td>123</td>--%>
                                  <%--<td>28.76%</td>--%>
                                  <%--<td><button class="btn btn-info" id="test1">查看</button></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                    <%--<td>Jacob</td>--%>
                                    <%--<td>123</td>--%>
                                    <%--<td>28.76%</td>--%>
                                    <%--<td><button class="btn btn-info">查看</button></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                  <%--<td>John</td>--%>
                                  <%--<td>Premier</td>--%>
                                  <%--<td>28.76%</td>--%>
                                  <%--<td><button class="btn btn-info">查看</button></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                  <%--<td>Peter</td>--%>
                                  <%--<td>After effects</td>--%>
                                  <%--<td>28.76%</td>--%>
                                    <%--<td><button class="btn btn-info">查看</button></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                  <%--<td>Dave</td>--%>
                                  <%--<td>53275535</td>--%>
                                  <%--<td>28.76%</td>--%>
                                  <%--<td><button class="btn btn-info">查看</button></td>--%>
                                <%--</tr>--%>
                                <%
                                  String countersign_contract_list = (String) request.getAttribute("get_contract_list");
                                  if (countersign_contract_list != null) {
                                    out.print(countersign_contract_list);
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
                                <li class="page-item"><a class="page-link" href="#"><i class="mdi mdi-chevron-left"></i></a></li>
                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item"><a class="page-link" href="#">4</a></li>
                                <li class="page-item"><a class="page-link" href="#"><i class="mdi mdi-chevron-right"></i></a></li>
                              </ul>
                            </nav>
                          </div>
                <div id="curved-line-chart" class="float-chart float-chart-mini"></div>
              </div>
            </div>
            <div class="black-shadow" id="black-shadow" tabindex=-1>
              </div>
              <div class="dialog" id="dialogBox">
                <div class="col-12 stretch-card">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="card-title">签订合同</h4>
                      <div>
                      <span class="card-description col-sm-3">
                        合同编号
                      </span>
                        <span class="card-description" id="box_cont_num">

                      </span>
                      </div>
                      <div>
                      <span class="card-description col-sm-3">
                        合同名称
                      </span>
                        <span class="card-description" id="box_cont_name">

                      </span>
                      </div>
                      <div class="col-sm-12">
                      <span class="card-description " >
                          客户姓名
                        </span>
                        <span class="card-description  col-sm-3" id="box_client_name">
                          你好
                        </span>
                      </div>
                      <form class="forms-sample">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label card-description">签订信息</label>
                          <div class="col-sm-9">
                              <textarea class="form-control" rows="5" id="conclude_message" placeholder="输入签订意见"></textarea>
                          </div>
                        </div>
                        <div style="text-align: center">
                        <button class="btn btn-success mr-2" onclick="submit_conclude()" >提交</button>
                        <button class="btn btn-light">取消</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
          </div>


       
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="container-fluid clearfix">
            <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © 2018 Bootstrapdash
              All rights reserved. </span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="mdi mdi-heart text-danger"></i></span>
          </div>
        </footer>
        <!-- partial -->
      </div>
      <!-- row-offcanvas ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <script src="../static/js/jQuery1.7.js"></script>
  <script src="../static/js/clickevent.js"></script>
  <!-- End custom js for this page-->
</body>

</html>
