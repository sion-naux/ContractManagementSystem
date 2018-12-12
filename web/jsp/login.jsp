<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>合同信息管理系统</title>
<meta name="keywords" content="合同信息管理系统">
<meta name="description" content="合同信息管理系统">
<meta name="viewport" content="width=device-width">
<link href="../static/css/base.css" rel="stylesheet" type="text/css">
<link href="../static/css/login.css" rel="stylesheet" type="text/css">
<link href="../static/css/login.1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../static/js/jQuery1.7.js"></script>
<script type="text/javascript" src="../static/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../static/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="../static/js/Validform_v5.3.2_min.js"></script>
</head>
<body>

	<div class="logo">合同信息管理系统</div>

<div id="tab">
	<ul class="tab_menu">
	  <li class="selected">登录</li>
	  <li>注册</li>
	</ul>
	<div class="tab_box"> 
	  <!-- 登录开始 -->
	  <div>
		<div class="stu_error_box"></div>
		<form action="" method="post" class="stu_login_error">
		  <div id="username">
			<label>帐&nbsp;&nbsp;&nbsp;号：</label>
			<input type="text" id="stu_username_hide" name="username" placeholder="输入帐号" nullmsg="帐号不能为空！" datatype="s6-18" errormsg="帐号范围在6~18个字符之间！" sucmsg="帐号验证通过！"/>
			<!--ajaxurl="demo/valid.jsp"--> 
		  </div>
		  <div id="password">
			<label>密&nbsp;&nbsp;&nbsp;码：</label>
			<input type="password" id="stu_password_hide" name="password" placeholder="输入密码" nullmsg="密码不能为空！" datatype="*6-16" errormsg="密码范围在6~16位之间！" sucmsg="密码验证通过！"/>
		  </div>

		  <div id="remember">
			<input type="checkbox" name="remember">
			<label>记住密码</label>
		  </div>
		  <div id="login">
			<button type="submit">登录</button>
		  </div>
		</form>
	  </div>
	 <!-- 学生登录结束-->
	   <!-- 注册开始-->
	  <div class="hide">
	  <div class="sec_error_box"></div>
		<form action="" method="post" class="sec_login_error">
		  <div id="username">
			<label>帐     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
			<input type="text" id="sec_username_hide" name="username" placeholder="输入帐号" nullmsg="帐号不能为空！" datatype="s6-18" errormsg="帐号范围在6~18个字符之间！" sucmsg="帐号验证通过！"/>
			<!--ajaxurl="demo/valid.jsp"--> 
		  </div>
		  <div id="password">
			<label>密     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
			<input type="password" id="sec_password_hide" name="password" placeholder="输入密码" nullmsg="密码不能为空！" datatype="*6-16" errormsg="密码范围在6~16位之间！" sucmsg="密码验证通过！"/>
			</div>
			<div id="password">
					<label>确认密码：</label>
					<input type="password" id="sec_password_hide" name="password" placeholder="确认密码" nullmsg="密码不能为空！" datatype="*6-16" errormsg="密码范围在6~16位之间！" sucmsg="密码验证通过！"/>
			</div>
		  <div id="login">
			<button type="submit">注册</button>
		  </div>
		</form>
	  </div>
	   <!-- 注册结束-->
	</div>
  </div>


<!-- <script>var basedir='public/ui/';</script>
<script src="public/ui/do.js"></script>
<script src="public/ui/config.js"></script>
<script>
Do.ready('form', function() {
	$("#form").Validform({
			ajaxPost:true,
			postonce:true,
			tiptype:function(msg,o,cssctl){
				if(!o.obj.is("form")){
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}else{
					var objtip=o.obj.find("#tip");
					cssctl(objtip,o.type);
					if(o.type==2){
						window.location.href='index.php?r=admin/index/index';
					}else{
						objtip.text(msg);
					}
				}
			}
	});
});

</script> -->

<script type="text/javascript">
	$(document).ready(function(){
		var $tab_li = $('#tab ul li');
		$tab_li.hover(function(){
			$(this).addClass('selected').siblings().removeClass('selected');
			var index = $tab_li.index(this);
			$('div.tab_box > div').eq(login).show().siblings().hide();
		});	
	});
	</script>



	<script type="text/javascript">
	$(function(){
	/*登录信息验证*/
	$("#stu_username_hide").focus(function(){
	 var username = $(this).val();
	 if(username=='输入帐号'){
	 $(this).val('');
	 }
	});
	$("#stu_username_hide").focusout(function(){
	 var username = $(this).val();
	 if(username==''){
	 $(this).val('输入帐号');
	 }
	});
	$("#stu_password_hide").focus(function(){
	 var username = $(this).val();
	 if(username=='输入密码'){
	 $(this).val('');
	 }
	});
	$("#stu_password_hide").focusout(function(){
	 var username = $(this).val();
	 if(username==''){
	 $(this).val('输入密码');
	 }
	});
	$(".stu_login_error").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$(".stu_error_box");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		ajaxPost:true
	});
	/*注册信息验证*/
	$("#sec_username_hide").focus(function(){
	 var username = $(this).val();
	 if(username=='输入帐号'){
	 $(this).val('');
	 }
	});
	$("#sec_username_hide").focusout(function(){
	 var username = $(this).val();
	 if(username==''){
	 $(this).val('输入帐号');
	 }
	});
	$("#sec_password_hide").focus(function(){
	 var username = $(this).val();
	 if(username=='输入密码'){
	 $(this).val('');
	 }
	});
	$("#sec_password_hide").focusout(function(){
	 var username = $(this).val();
	 if(username==''){
	 $(this).val('输入密码');
	 }
	});
	$(".sec_login_error").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$(".sec_error_box");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		ajaxPost:true
	});
	});
	</script>
	<script type="text/javascript">
	$(function(){
		$(".screenbg ul li").each(function(){
			$(this).css("opacity","0");
		});
		$(".screenbg ul li:first").css("opacity","1");
		var index = 0;
		var t;
		var li = $(".screenbg ul li");	
		var number = li.size();
		function change(index){
			li.css("visibility","visible");
			li.eq(login).siblings().animate({opacity:0},3000);
			li.eq(login).animate({opacity:1},3000);
		}
		function show(){
			login = login + 1;
			if(login<=number-1){
				change(login);
			}else{
				login = 0;
				change(login);
			}
		}
		t = setInterval(show,8000);
		//根据窗口宽度生成图片宽度
		var width = $(window).width();
		$(".screenbg ul img").css("width",width+"px");
	});
	</script>
</body>
</html>
