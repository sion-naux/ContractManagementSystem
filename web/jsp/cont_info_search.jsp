<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>

<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>合同管理系统</title>
  <!-- inject:css -->
  <link rel="stylesheet" href="../static/css/style.css">
  <link rel="stylesheet" href="../static/css/font-awesome.min.css">

  <!-- endinject -->
  <link rel="shortcut icon" href="../static/images/favicon.png" />
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper">
        <a class="navbar-brand brand-logo" href="index.jsp"><img src="../static/images/logo.svg" alt="logo"></a>
        <a class="navbar-brand brand-logo-mini" href="index.jsp"><img src="../static/images/logo_mini.svg" alt="logo"></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center">
        <p class="page-name d-none d-lg-block">早上好！</p>
        <ul class="navbar-nav ml-lg-auto">
          <li class="nav-item">
            <form class="mt-2 mt-md-0 d-none d-lg-block search-input">
              <div class="input-group">
                <span class="input-group-addon d-flex align-items-center"><i class="icon-magnifier icons"></i></span>
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
        <!-- partial:partials/_sidebar.html -->
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
          <ul class="nav">
            <li class="nav-item nav-category">
              <span class="nav-link">合同起草</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/draft.jsp">
                <span class="menu-title">起草合同</span>
                <i class="icon-speedometer menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/cont_final.jsp">
                <span class="menu-title">待定稿合同</span>
                <i class="icon-wrench menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/over_cont_final.jsp">
                <span class="menu-title">已定稿合同</span>
                <i class="icon-wrench menu-icon"></i>
              </a>
            </li>
            <li class="nav-item nav-category">
              <span class="nav-link">合同会签</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/countersign.jsp">
                <span class="menu-title">待会签合同</span>
                <i class="icon-speedometer menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"href="<%=request.getContextPath()%>/jsp/over_countersign.jsp">
                <span class="menu-title">已会签合同</span>
                <i class="icon-grid menu-icon"></i>
              </a>
            </li>
            <!-- <li class="nav-item">
              <a class="nav-link" href="pages/icons/font-awesome.html">
                <span class="menu-title">Icons</span>
                <i class="icon-globe menu-icon"></i>
              </a>
            </li> -->
            <li class="nav-item nav-category">
              <span class="nav-link">合同审批</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/approval">
                <span class="menu-title">待审批合同</span>
                <i class="icon-flag menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/over_approval.jsp">
                <span class="menu-title">已审批合同</span>
                <i class="icon-flag menu-icon"></i>
              </a>
            </li>
            <li class="nav-item nav-category">
              <span class="nav-link">合同签订</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/conclude.jsp">
                <span class="menu-title">待签订合同</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/over_conclude.jsp">
                <span class="menu-title">已签订合同</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
            <li class="nav-item nav-category">
              <span class="nav-link">合同查询</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/cont_info_search.jsp">
                <span class="menu-title">合同信息查询</span>
                <i class="icon-bubbles menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/cont_proc_search.jsp">
                <span class="menu-title">合同流程查询</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
            <li class="nav-item nav-category">
              <span class="nav-link">系统管理</span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/contribute">
                <span class="menu-title">分配合同</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/authManage">
                <span class="menu-title">权限管理</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/jsp/auth_manage.jsp">
                <span class="menu-title">日志管理</span>
                <i class="icon-pie-chart menu-icon"></i>
              </a>
            </li>
          </ul>
        </nav>
        <!-- partial -->
        <div class="content-wrapper">
          <div class="row">
            <div class="col-lg-12 grid-margin">
              <div class="card overflow-hidden dashboard-curved-chart">
                <div class="card-body mx-3">
                  <h2 class="card-title border-bottom-none">合同信息查询</h2>
                </div>
                <div class="search d1">
                  <form class="search">
                  <input type="text" placeholder="输入关键字">
                  <button type="submit"></button>
                  </form>
                </div>

                <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                          <div class="card-body">
                            <h4 class="card-title">匹配合同</h4>
                            <table class="table table-hover">
                              <thead>
                                <tr>
                                  <th>合同名称</th>
                                  <th>起草日期</th>
                                  <th>起草人</th>
                                  <th>状态</th>
                                  <th>操作</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <td>Jacob</td>
                                  <td>123</td>
                                  <td>28.76%</td>
                                  <td><label class="badge badge-danger">Pending</label></td>
                                  <td><button class="btn btn-info" id="test1">查看</button></td>
                                </tr>
                                <tr>
                                    <td>Jacob</td>
                                    <td>123</td>
                                    <td>28.76%</td>
                                    <td><label class="badge badge-warning">In progress</label></td>
                                    <td><button class="btn btn-info">查看</button></td>
                                </tr>
                                <tr>
                                  <td>John</td>
                                  <td>Premier</td>
                                  <td>28.76%</td>
                                  <td><label class="badge badge-info">Fixed</label></td>
                                  <td><button class="btn btn-info" >查看</button></td>
                                </tr>
                                <tr>
                                  <td>Peter</td>
                                  <td>After effects</td>
                                  <td>28.76%</td>
                                  <td><label class="badge badge-success">Completed</label></td>
                                  <td><button class="btn btn-info">查看</button></td>
                                </tr>
                                <tr>
                                  <td>Dave</td>
                                  <td>53275535</td>
                                  <td>28.76%</td>
                                  <td><label class="badge badge-secondary">Completed</label></td>
                                  <td><button class="btn btn-info">查看</button></td>
                                </tr>
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
                      <span class="card-description col-sm-3">
                        合同名称
                      </span>
                      <span class="card-description">
                        合同名称
                      </span></br>
                      <div class="col-sm-12">
                      <span class="card-description " >
                          客户姓名
                        </span>
                        <span class="card-description  col-sm-3" >
                          你好
                        </span>
                      </div>
                      <form class="forms-sample">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label card-description">签订信息</label>
                          <div class="col-sm-9">
                              <textarea class="form-control" rows="5" placeholder="输入签订意见"></textarea>
                          </div>
                        </div>
                        <div style="text-align: center">
                        <button type="submit" class="btn btn-success mr-2" >提交</button>
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


  <script src="../static/js/clickevent.js"></script>
  <!-- End custom js for this page-->
</body>

</html>
