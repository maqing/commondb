<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 
Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
	<link rel="stylesheet" type="text/css" href="lib/extjs3/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="css/ext-patch.css" />
	<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>

<div  id='LoginFormDiv'></div>

<script src="lib/extjs3/adapter/ext/ext-base.js"></script>
<script src="lib/extjs3/ext-all.js"></script>
<script type="text/javascript" src="lib/extjs3/ext-lang-zh_CN.js"></script>
<script type="text/javascript" language="javascript" >
var loginForm;
var formPanel_LoginFormID = 'LoginForm';
var textfield_UserNameID = 'j_username';
var textfield_PasswordID = 'j_password';
   
    
Ext.onReady(function(){
    
        loginForm = new Ext.FormPanel({
            id: formPanel_LoginFormID,
            renderTo: 'LoginFormDiv',
            url:'${pageContext.request.contextPath}/j_spring_security_check',
//            title: "通用数据库平台登录",
            title: "后处理设备管理平台登录",
            frame: true,
            border: false,
            //bodyStyle:'padding:5px',
            width:350,
            height:160,
			buttonAlign:'center',
			//labelAlign :'right',
            bodyStyle: 'padding:10px 10px 10px 30px',                
            defaults: {width:180, xtype:"textfield"},
            labelWidth: 55,
            onSubmit: Ext.emptyFn,
	        submit: function() {
	            this.getEl().dom.action ='${pageContext.request.contextPath}/j_spring_security_check';// 提交的url
				this.getEl().dom.method = 'post';
				this.getEl().dom.submit();
			},
            items: [{
                id: 'textfield_UserNameID',
                name:'j_username',
                fieldLabel: '用户名',                    
                allowBlank: false, //禁止为空
                blankText: '用户名不能为空'
            }, {                    
                id: 'textfield_PasswordID',
                name:'j_password',
                fieldLabel: '密&nbsp;&nbsp;&nbsp;码',
                inputType: 'password',
                allowBlank: true //可以为空
            },{
            	//id: 'remember_me',
                xtype: 'checkbox',
                name: '_spring_security_remember_me',
                boxLabel:  '两周内自动登录'
            }],
            keys: {
                key: 13,
                fn: submit_login
            },
            buttons: [{
                text: '登录',
                handler: submit_login,
                style: 'margin: 0 0 0 10px'
            }, {
                text: '重置',
                handler: function(){ 
                    loginForm.form.reset();//重置表单
                }
            }]
        });
        
        if(getQueryStringRegExp('error')=='true') Ext.Msg.alert('失败','用户名或密码错误！');
        
});//Ext.onReady
    
    
function submit_login(){
    loginForm.form.submit();
}

function getQueryStringRegExp(name){

       var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");

       if (reg.test(location.href))

       return unescape(RegExp.$2.replace(/\+/g, " "));

       return "";

}

    
</script>
</body>
</html>