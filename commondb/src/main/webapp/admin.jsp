<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0
Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

  <title>Common DB</title>
  		<link rel="stylesheet" type="text/css"
			href="lib/extjs3/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="shared/extjs/css/ext-patch.css" />
		<!-- <link rel="stylesheet" type="text/css" href="css/ext-all.css">  -->



    <link rel="stylesheet" type="text/css" href="resources/docs.css"></link>
	<link rel="stylesheet" type="text/css" href="resources/style.css"></link>
	<link rel="stylesheet" type="text/css" href="css/fileuploadfield.css"/>

	<style type="text/css">
	</style>

</head>
<body scroll="no" id="docs">
  <div id="loading-mask" style=""></div>
  <div id="loading">
    <div class="loading-indicator"><img src="resources/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>Loading...</div>
  </div>
    <!-- include everything after the loading indicator -->

	<script src="lib/extjs3/adapter/ext/ext-base.js"></script>
	<script src="lib/extjs3/ext-all-debug.js"></script>
	<script type="text/javascript" src="lib/extjs3/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="resources/TabCloseMenu.js"></script>
    <script type="text/javascript" src="js/ux/FileUploadField.js"></script>
    <script type="text/javascript" src="js/ux/CheckColumn.js"></script>

    <script type="text/javascript" src="admin/listMeta.ac"></script>
    <script type="text/javascript" src="js/admin_main.js"></script>
<script type="text/javascript" src="js/ux/picAttrGrid.js"></script>

	<script type="text/javascript" src="js/ux/descAttrGrid.js"></script>
	<script type="text/javascript" src="js/ux/hierAttrGrid.js"></script>
	<script type="text/javascript" src="js/ux/charaAttrGrid.js"></script>
    <script type="text/javascript" src="js/applySortOverload.js"></script>
	<script type="text/javascript">




            </script>


<div id="north" >
<div id="header"><h1>
<b><i>Common DB</i> 通用数据库平台</b>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<security:authentication property="principal.userDesc"/>您好！您的权限为<security:authentication property="principal.authoritiesDescString"/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="j_spring_security_logout">注销</a>
<!-- &nbsp;&nbsp;&nbsp;&nbsp;<a href="front/findMetaPerm.ac" target="about_blank">前台 </a> -->
&nbsp;&nbsp;&nbsp;&nbsp;<a href="app/sys/home.ac" target="about_blank">前台 </a>


</h1></div>
</div>

<div id="south">
    <div class="power" id="power" >

	</div>
    <div class="bq" id="banquan" >


    </div>
</div>
 <script type="text/javascript">




</script>

  </body>
</html>