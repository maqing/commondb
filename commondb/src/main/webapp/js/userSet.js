Ext.form.Field.prototype.msgTarget = 'side';

function showXPanel( btn)  
{       
    XWindow.show(btn);
    
          
}
var XWindow = new Ext.Window({  
	applyTo: 'x_win',  //前端放置当前js文件的页面中的div名称  
	title:'用户信息设置',  
	//width: 755,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: [cpForm, modifyForm],//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true
    
});

function showCpPanel( btn)  
{       
    CpWindow.show(btn);
    
          
}
var CpWindow = new Ext.Window({  
	applyTo: 'cp_win',  //前端放置当前js文件的页面中的div名称  
	title:'重置密码',  
	//width: 755,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: cpForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        cpForm.getForm().reset();
    }
});
var  cpForm=  new Ext.form.FormPanel({
    id: 'cp-form',
    url: 'front/changePassword.ac',
    renderTo: 'cp_div',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '旧密码',
	            id:'pwdId1',
			    name:'curPwd',
			    inputType: 'password',
			    allowBlank: false,
	            blankText: '密码不能为空'
    }, {                    
                fieldLabel: '新密码',
	            id:'pwdId2',
			    name:'pwd',
			    inputType: 'password',
			    allowBlank: false,
	            blankText: '密码不能为空'
    },{
            	fieldLabel: '确认新密码',
                id:'pwdId3',
			    name:'pwd3',
			    inputType: 'password',
			    allowBlank: false,
                blankText: '密码不能为空'
    }],
    buttons: [{
        text: '保存',
        handler: saveChange,
        style: 'margin: 0 0 0 10px'
    	}/*,{
        text: '关闭',
        handler: function(){ 
        	EditWindow.hide();
        }
    }*/]
});

function saveChange(){
    if(Ext.getCmp('pwdId2').getValue()!=Ext.getCmp('pwdId3').getValue()){  
	        Ext.Msg.alert('错误','两次输入的密码不一致');  
	        return;  
    } 	
    if (Ext.getCmp('cp-form').form.isValid()){
        Ext.getCmp('cp-form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
            CpWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit'}  
      });
   }
}
////////////////////////////////////////////////////////////////////////////
function showModifyPanel( btn)  
{       
   
    //Ext.getCmp('usernameid').setValue(${user.userName});
    //Ext.getCmp('userdescid').setValue(${user.userDesc});
    
    ModifyWindow.show(btn);
          
}
var ModifyWindow = new Ext.Window({  
	applyTo: 'modify_win',  //前端放置当前js文件的页面中的div名称  
	title:'重置密码',  
	//width: 755,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: modifyForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        modifyForm.getForm().reset();
    }
});
var  modifyForm=  new Ext.form.FormPanel({
    id: 'modify-form',
    url: 'front/modifyUserInfo.ac',
    renderTo: 'modify_div',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:200,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '用户名',
	            id:'usernameid',
			    name:'userName',
			    allowBlank: false,
	            blankText: '用户名不能为空'
    }, {                    
                fieldLabel: '描述',
	            id:'userdescid',
			    name:'userDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    }],
    buttons: [{
        text: '保存',
        handler: saveModify,
        style: 'margin: 0 0 0 10px'
    	}/*,{
        text: '关闭',
        handler: function(){ 
        	EditWindow.hide();
        }
    }*/]
});

function saveModify(){
    	
    if (Ext.getCmp('modify-form').form.isValid()){
        Ext.getCmp('modify-form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
            ModifyWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit'}  
      });
   }
}
