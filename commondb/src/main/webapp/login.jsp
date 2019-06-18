<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
      <title>login</title>
      <link rel="stylesheet" href="http://csdnimg.cn/public/common/libs/bootstrap/css/bootstrap.css">
      <link rel="stylesheet" href="css/font-awesome.min.css">
      <link rel="stylesheet" href="css/system.css">
      <style>
          body{background: url("images/login_bg.jpg") no-repeat center; width: 100%;height:100%; min-height: 640px; background-size:cover;overflow: hidden;}
      </style>
     <script type="text/javascript" src="lib/jquery/jquery-1.11.1.min.js"></script>
     <script type="text/javascript">
     	$(function(){
     		$(".register").click(function(){
     			$("#theForm").submit();
     		});
     		
     	})
     </script>
  </head>
  <body>
  <div class="wrap">
      <div class="wrap_t">数据采集平台登录</div>
      <form name="theForm" id="theForm" 
       action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
	      <div class="wrap_c">
	          <div class="wrap_close"><i class="fa fa-close"></i> </div>
	          <ul class="wrap_list clearfix">
	              <li>
	                  <span>用户名</span><input type="text" name="j_username" id="textfield_UserNameID" placeholder="请输入用户名">
	              </li>
	              <li>
	                  <span>密码</span><input type="password" name="j_password" id="textfield_PasswordID" placeholder="请输入密码">
	              </li>
	          </ul>
	          <div class="check">
	              <!--<input type="checkbox"/>-->
	                <!--  <i class="fa fa-square-o"></i>-->
	              <i class="fa fa-check-square"></i>
	              <!--注：这里把复选框的两种状态都写在这，空心方框的是未选中的状态，实心带对勾的选中的状态-->
	              <span>两周内自动登录</span>
	
	          </div>
	          <div class="login">
	              <span class="register">登录</span>
	              <span class="reset">重置</span>
	          </div>
	      </div>
      </form>
  </div>
  
  </body>
</html>