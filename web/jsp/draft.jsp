<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>合同管理系统</title>
  <!-- plugins:css -->
  <!-- inject:css -->
  <link rel="stylesheet" href="../static/css/style.css">

  <link href="../static/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="../static/css/font-awesome.min.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css" /> -->
<!--时间css end-->
<link href="../static/css/base.css" rel="stylesheet">
<script type="text/javascript" src="../static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
 <!--时间js start-->
<script src="../static/js/moment-with-locales.js"></script>
<script src="../static/js/bootstrap-datetimepicker.js"></script>
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
            <div class="dropdown-menu navbar-dropdown mail-notification dropdownAnimation" aria-labelledby="MailDropdown">
              <a class="dropdown-item" href="#">
                <div class="sender-img">
                  <img src="../static/images/faces/face6.jpg" alt="">
                  <span class="badge badge-success">&nbsp;</span>
                </div>
                <div class="sender">
                  <p class="Sende-name">John Doe</p>
                  <p class="Sender-message">Hey, We have a meeting planned at the end of the day.</p>
                </div>
              </a>
              <a class="dropdown-item" href="#">
                <div class="sender-img">
                  <img src="../static/images/faces/face2.jpg" alt="">
                  <span class="badge badge-success">&nbsp;</span>
                </div>
                <div class="sender">
                  <p class="Sende-name">Leanne Jones</p>
                  <p class="Sender-message">Can we schedule a call this afternoon?</p>
                </div>
              </a>
              <a class="dropdown-item" href="#">
                <div class="sender-img">
                  <img src="../static/images/faces/face3.jpg" alt="">
                  <span class="badge badge-primary">&nbsp;</span>
                </div>
                <div class="sender">
                  <p class="Sende-name">Stella</p>
                  <p class="Sender-message">Great presentation the other day. Keep up the good work!</p>
                </div>
              </a>
              <a class="dropdown-item" href="#">
                <div class="sender-img">
                  <img src="../static/images/faces/face4.jpg" alt="">
                  <span class="badge badge-warning">&nbsp;</span>
                </div>
                <div class="sender">
                  <p class="Sende-name">James Brown</p>
                  <p class="Sender-message">Need the updates of the project at the end of the week.</p>
                </div>
              </a>
              <a href="#" class="dropdown-item view-all">View all</a>
            </div>
          </li>
          <li class="nav-item dropdown notification-dropdown">
            <a class="nav-link count-indicator" id="notificationDropdown" href="#" data-toggle="dropdown">
              <i class="icon-speech icons"></i>
              <span class="count"></span>
            </a>
            <div class="dropdown-menu navbar-dropdown preview-list notification-drop-down dropdownAnimation" aria-labelledby="notificationDropdown">
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon">
                    <i class="icon-info mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject font-weight-medium">Application Error</p>
                  <p class="font-weight-light small-text">
                    Just now
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon">
                    <i class="icon-speech mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject">Settings</p>
                  <p class="font-weight-light small-text">
                    Private message
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon">
                    <i class="icon-envelope mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject">New user registration</p>
                  <p class="font-weight-light small-text">
                    2 days ago
                  </p>
                </div>
              </a>
            </div>
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
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center ml-auto" type="button" data-toggle="offcanvas">
          <span class="icon-menu icons"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
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
              <a class="nav-link" href="<%=request.getContextPath()%>/countersign">
                <span class="menu-title">待会签合同</span>
                <i class="icon-speedometer menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"href="<%=request.getContextPath()%>/over_countersign">
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
                  <h2 class="card-title border-bottom-none">起草合同</h2>
                  <!-- <div class="align-items-center mb-5 justify-content-between d-lg-flex d-xl-flex d-md-block d-block">
                    <div id="chartLegend"></div>
                    <div class="nav-wrapper d-inline-block mt-4 mt-lg-0">
                      <ul class="nav nav-pills">
                        <li class="nav-item">
                          <a class="nav-link" href="#">Week</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="#">Month</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="#">Year</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="#">Today</a>
                        </li>
                      </ul>
                    </div>
                  </div> -->
                </div>
                <div class="col-md-9 grid-margin stretch-card">
                  <div class="card">
                    <div class="card-body">
                      <!-- <h4 class="card-title">Basic form</h4> -->
                      <p class="card-description">
                        填写信息
                      </p>
                      <form class="forms-sample" action="<%=request.getContextPath()%>/draft" method="post">
                        <div class="form-group">
                          <input type="text" class="form-control" name="contract_name" placeholder="合同名称">
                        </div>
                        <div class="form-group">
                          <input type="text" class="form-control" name="contract_customer" placeholder="客户">
                        </div>
                        <div class="form-group">
                            <div class='input-group date' >
                                <input type='text' class="form-control" name="contract_begin_time" id='datetimepicker3' placeholder="开始时间"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                                </span>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker3').datetimepicker({
                                  
                                  locale: 'zh-cn',
                                  viewMode: 'days',
                                  format: 'YYYY/MM/DD'
                                });
                            });
                        </script>
                        <div class="form-group">
                            <div class='input-group date' >
                                <input type='text' class="form-control" name="contract_end_time" id='datetimepicker4' placeholder="结束时间"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                                </span>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker4').datetimepicker({
                                  
                                  locale: 'zh-cn',
                                  viewMode: 'days',
                                  format: 'YYYY/MM/DD'
                                });
                            });
                        </script>
                        <div class="form-group" style="margin-top: 20px">
                          <input type="file" name="img[]" class="file-upload-default">
                          <div class="input-group col-xs-12">
                            <input type="text" class="form-control file-upload-info" name="option_name" disabled placeholder="上传附件">
                            <span class="input-group-append">
                              <input class="dropfy" type="file">
                            </span>
                          </div>
                        </div>
                        <div class="form-group">
                          <textarea class="form-control" rows="5" name="contract_content" placeholder="合同内容"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success mr-2" id="draft" >确认</button>
                        <button class="btn btn-light">取消</button>
                      </form>
                    </div>
                  </div>
                </div>
                <div id="curved-line-chart" class="float-chart float-chart-mini"></div>
                
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

  

</body>

</html>
