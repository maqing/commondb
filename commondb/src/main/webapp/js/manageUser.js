// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';


var in_userid;
var in_username;

var userRefresh = function()
{
    roleStore.load();
    userStore.load();
}

function showeditPanel( btn)  
{       
    var record = grid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_userid=record.id;
    
    editUserForm.getForm().loadRecord(record);
    
    var roleIdSelArr=new Array();
    var roleArr=record.get('roleList');
    for(var k=0;k<roleArr.length;k++){
    	roleIdSelArr[k]=roleArr[k].roleId;
    }
    
    var recordArr = roleStore.queryBy(function(record,id){
    	return roleIdSelArr.indexOf(record.get('roleId'))!=-1;  
	},this).getRange();
	
	sm.selectRecords(recordArr,false);
	
    EditWindow.show(btn);
          
}

var EditWindow = new Ext.Window({
	id:'Edit_Window2',
	applyTo: 'edit_win2',  //前端放置当前js文件的页面中的div名称  
	//el:'edit_win2',
	title:'修改',  
	width: 752,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: editUserForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        editUserForm.getForm().reset();
    }
});

function showresetPanel( btn)  
{       
    var record = grid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    in_username=record.get('userName');
    in_userid=record.id;
    ResetWindow.setTitle('为'+in_username+'重置密码');
    ResetWindow.show(btn);
          
}
var ResetWindow = new Ext.Window({  
	id:'Reset_Window',
	applyTo: 'reset_win',  //前端放置当前js文件的页面中的div名称  
	//el: 'reset_win',
	title:'重置密码',  
	width: 250,
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
    url: 'security/resetPassword.ac',
    renderTo: 'reset_password',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    buttonAlign: 'center',
    bodyStyle:'padding:1px',
    width: 250,
    height:150,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '新密码',
	            id:'pwdId3',
			    name:'pwd',
			    inputType: 'password',
			    allowBlank: false,
	            blankText: '密码不能为空'
    },{
            	fieldLabel: '确认密码',
                id:'pwdId4',
			    name:'pwd4',
			    inputType: 'password',
			    allowBlank: false,
                blankText: '密码不能为空'
            }],
    buttons: [{
        text: '保存',
        handler: saveReset,
        style: 'margin: 0 0 0 10px'
    	}/*,{
        text: '关闭',
        handler: function(){ 
        	EditWindow.hide();
        }
    }*/]
});

function saveReset(){
    if(Ext.getCmp('pwdId3').getValue()!=Ext.getCmp('pwdId4').getValue()){  
	        Ext.Msg.alert('错误','两次输入的密码不一致');  
	        return;  
    }	
    if (Ext.getCmp('cp-form').form.isValid()){
        Ext.getCmp('cp-form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
            ResetWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',userId:in_userid }  
      });
   }
}



var roleStore = new Ext.data.JsonStore({
	// store configs
	autoDestroy: true,
	url: 'security/findRole.ac',
	storeId: 'myStore',
	// reader configs
	root: 'data',
	idProperty: 'roleId',  
	fields: [  
	             {name: 'roleId', mapping: 'roleId'},  
	             {name: 'roleName'},  
	             {name: 'roleDesc'}
	             
	         ]  
});
         

roleStore.load();  

var sm = new Ext.grid.CheckboxSelectionModel({singleSelect :false});

var userGrid = new Ext.grid.GridPanel({
    //renderTo: "newUser",
	id:'userGrid',
    ds: roleStore,
    columns:[
        new Ext.grid.RowNumberer(),
        sm,
        /*
        {id:'roleName',header: "角色名称", width: 100, sortable: true, locked:false, dataIndex: 'roleName'},
        {header: "描述", width: 250, sortable: true, dataIndex: 'roleDesc'}
        */
        {id:'roleDesc',header: "角色名称", width: 200, sortable: true, locked:false, dataIndex: 'roleDesc'}
   	],
	sm: sm,
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:350,
    width:600,
    title:'角色列表'
    
});
        
function save(){
    	
    var sel = sm.getSelections();
    if(sel.length==0){  
	        Ext.Msg.alert('信息','请选择角色');  
	        return;  
    }  
    var roleIdArr=new Array();
    
    for(var i=0;i<sel.length;i++){
    	roleIdArr[i]=sel[i].get('roleId');
    }
    
    if (Ext.getCmp('user-form').form.isValid()){
        Ext.getCmp('user-form').getForm().submit({
         success: function(f,a){
         	userStore.load();
            Ext.Msg.alert('成功', '保存成功');
            EditWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',roleIdArr:roleIdArr,userId:in_userid }  
      });
   }
}

var  editUserForm=  new Ext.form.FormPanel({
    id: 'user-form',
    url: 'security/updateUser.ac',
    renderTo: 'edit_user',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 752,
    layout: 'column',
    items:[{
        columnWidth: 0.6,
        layout: 'fit',
        items: userGrid
    	},{
    	columnWidth: 0.4,
    	xtype: 'fieldset',
        labelWidth: 50,
        //title:'用户信息',
		defaultType: 'textfield',
        autoHeight: true,
        bodyStyle: Ext.isIE ? 'padding:0 0 5px 15px;' : 'padding:10px 15px;',
        border: false,
        style: {
            "margin-left": "10px", // when you add custom margin in IE 6...
            "margin-right": Ext.isIE6 ? (Ext.isStrict ? "-10px" : "-13px") : "0"  // you have to adjust for it somewhere else
        },
        items: [{
        	fieldLabel: '用户名',
            id:'userNameId2',
		    name:'userName',
            //vtype:'alpha',
		    allowBlank: false,
            blankText: '用户名不能为空'
        }/*,{
        	fieldLabel: '密码',
            id:'pwdId2',
		    name:'pwd',
		    inputType: 'password',
		    allowBlank: false,
            blankText: '密码不能为空'
        }*/,{
        	fieldLabel: '描述',
            id:'userDescId2',
		    name:'userDesc'
        },{
			xtype: 'radio',
			//id:'disabledId',
            name: 'disabled',
            inputValue: 'false',
            boxLabel: '激活'
        },{
			xtype: 'radio',
			//id:'disabledId',
			name: 'disabled',
			inputValue: 'true',
			boxLabel: '停用'
        }]
    }],
    buttons: [{
        text: '保存',
        handler: save,
        style: 'margin: 0 0 0 10px'
    	}/*,{
        text: '关闭',
        handler: function(){ 
        	EditWindow.hide();
        }
    }*/]
});


    

var userStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'security/findUser.ac',
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'userId',  
    fields: [  
                 {name: 'userId', mapping: 'userId'},  
                 {name: 'userName'},  
                 {name: 'userDesc'},
                 {name: 'disabled',convert: function(v, rec) {
											                   if (v==true) return 'true';
											                   if (v==false) return 'false';
											                 }
            	},
				{name: 'roleList'}
             ]  
});
         
userStore.load();  
  
function repVar(v) {
    if (v == 'true') return "停用"
    if (v == 'false') return "激活"
}

var colModel = new Ext.grid.ColumnModel([
    {id:'userName',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'userName'},
    {header: "描述", width: 250, sortable: true, dataIndex: 'userDesc'},
    {header: "状态", width: 80, sortable: true, dataIndex: 'disabled',renderer: repVar}
]);

var grid = new Ext.grid.GridPanel({
    renderTo: "manageUser",
    ds: userStore,
    cm:colModel,
    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:350,
    width:600,
    title:'用户列表',
    tbar : [{
                id : 'newWindow',
                text : '编辑',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   showeditPanel(this);
                }
     }, {
                id : 'refresh',
                text : '刷新',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   userStore.load();//showeditPanel(this);
                }
     },
     {
			id : 'delUserData',
			text : '删除',
			//iconCls : 'edit',
			handler : function() {
                var sm = grid.getSelectionModel();
                var sel = sm.getSelected();
                if (sm.hasSelection()) {
                    Ext.Msg.show({
                        title : '删除用户',
                        buttons : Ext.MessageBox.YESNOCANCEL,
                        msg : '删除 ' + sel.data.userName + '?',
                        fn : function(btn) {
                            if (btn == 'yes') {
                                var conn = new Ext.data.Connection();
                                conn.request({
                                            url : 'security/delUser.ac',
                                            params : {
                                                userId : sel.id
                                            },
                                            success : function(resp, opt) {
                                            	Ext.Msg.alert('成功','删除成功');
                                                grid.getStore().remove(sel);
                                            },
                                            failure : function(resp, opt) {
                                                Ext.Msg.alert('失败','删除失败');
                                            }
                                        });
                            }
                        }
                    });
                };
            }
     },
     {
			id : 'stopUserData',
			text : '修改状态 ',
			//iconCls : 'edit',
			handler : function() {
                var sm = grid.getSelectionModel();
                var sel = sm.getSelected();
                if (sm.hasSelection()) {
                    Ext.Msg.show({
                        title : '修改状态',
                        buttons : Ext.MessageBox.YESNOCANCEL,
                        msg : '修改 ' + sel.data.userName + '的状态?',
                        fn : function(btn) {
                            if (btn == 'yes') {
                                var conn = new Ext.data.Connection();
                                conn.request({
                                            url : 'security/stopUser.ac',
                                            params : {
                                                userId : sel.id
                                            },
                                            success : function(resp, opt) {
                                            	Ext.Msg.alert('成功','修改状态成功');
                                                userStore.load();
                                            },
                                            failure : function(resp, opt) {
                                                Ext.Msg.alert('失败','修改状态失败');
                                            }
                                        });
                            }
                        }
                    });
                };
            }
     },{
                id : 'newreset',
                text : '重置密码',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   showresetPanel(this);
                }
     }]
});


    
