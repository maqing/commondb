// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';

var in_charaId;
var in_dataId;
var newMetaRefresh = function(){};

var defStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listCharaDef.ac',
    storeId: 'def_Store',
    // reader configs
    root: 'data',
    idProperty: 'charaId',  
    fields: [  
                 {name: 'charaId', mapping: 'charaId'},  
                 {name: 'charaName'},  
                 {name: 'charaDesc'},
                 {name: 'ischeckmultiple'},
                 {name: 'isshared'},
				 {name: 'user',convert: function(v, rec) {
				 											return v.userDesc;
				 }}
             ]  
});
         
defStore.load();  
  
var defColModel = new Ext.grid.ColumnModel([
    {id:'charaName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'charaName'},
    {header: "描述", width: 250, sortable: true, dataIndex: 'charaDesc'},
    {header: "是否多选", width: 80, sortable: true, dataIndex: 'ischeckmultiple',renderer: function repVar(v) {
	    if (v == 1) return "是"
	    if (v == 0) return "否"
	}},
    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
	    if (v == 1) return "是"
	    if (v == 0) return "否"
	}},
	{header: "用户", width: 100, sortable: true, dataIndex: 'user'}
]);

var defGrid = new Ext.grid.GridPanel({
	id:'def_Grid',
    renderTo: "manage_def",
    ds: defStore,
    cm:defColModel,
    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:200,
    width:600,
    title:'特征属性列表',
    listeners: {
            rowclick: function(g, index, ev) {
                manageData() ;
            },
            destroy : function() {

            }
    },
    tbar : [{
                id : 'newDefWindow',
                text : '新建',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   newDef(this);
                }
     },{
                id : 'editDefWindow',
                text : '编辑',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   editDef(this);
                }
     }, {
                id : 'refreshDef',
                text : '刷新',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   defStore.load();
                }
     },
     {
			id : 'delDef',
			text : '删除',
			//iconCls : 'edit',
			handler : function() {
                var sm = defGrid.getSelectionModel();
                var sel = sm.getSelected();
                if (sm.hasSelection()) {
                    Ext.Msg.show({
                        title : '删除特征属性',
                        buttons : Ext.MessageBox.YESNOCANCEL,
                        msg : '删除 ' + sel.data.charaName + '?',
                        fn : function(btn) {
                            if (btn == 'yes') {
                                var conn = new Ext.data.Connection();
                                conn.request({
                                            url : 'admin/dropCharaDef.ac',
                                            params : {
                                                charaId : sel.id
                                            },
                                            success : function(resp, opt) {
                                            	Ext.Msg.alert('成功','删除成功');
                                                defGrid.getStore().remove(sel);
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
     }/*,{
                id : 'manageDataWindow',
                text : '管理数据',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                	
                		manageData(this);
                	
                }
     }*/]
});
//-------------------------newDef-------------------------------
var  newDefForm=  new Ext.form.FormPanel({
    id: 'newDef_form',
    url: 'admin/createCharaDef.ac',
    renderTo: 'new_def_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '名称',
	            id:'charaNameId',
			    name:'charaName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {                    
                fieldLabel: '描述',
	            id:'charaDescId',
			    name:'charaDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'ischeckmultiple',
                inputValue: '1',
                boxLabel: '多选',
                checked:true
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'ischeckmultiple',
				inputValue: '0',
				boxLabel: '单选'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'isshared',
                inputValue: '1',
                boxLabel: '共享',
                checked:true
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'isshared',
				inputValue: '0',
				boxLabel: '不共享'
    }],
    buttons: [{
    	text: '保存',
        handler: saveDef,
        style: 'margin: 0 0 0 10px'
    	}]
});

function saveDef(){
    	
    if (Ext.getCmp('newDef_form').form.isValid()){
        Ext.getCmp('newDef_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	defStore.load();
            newDefWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit'}  
      });
   }
}

var newDefWindow = new Ext.Window({  
	id:'newDef_Window',
	applyTo: 'new_def_win',  //前端放置当前js文件的页面中的div名称  
	title:'新建特征属性',  
	width: 350,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: newDefForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        newDefForm.getForm().reset();
    }
});

function newDef( btn)  
{       
    newDefForm.getForm().reset();
    newDefWindow.show(btn);
}

//-------------------------editDef-------------------------------
var  editDefForm=  new Ext.form.FormPanel({
    id: 'editDef_form',
    url: 'admin/updateCharaDef.ac',
    renderTo: 'edit_def_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '名称',
	            id:'charaNameId2',
			    name:'charaName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {                    
                fieldLabel: '描述',
	            id:'charaDescId2',
			    name:'charaDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'ischeckmultiple',
                inputValue: '1',
                boxLabel: '多选',
                checked:true
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'ischeckmultiple',
				inputValue: '0',
				boxLabel: '单选'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'isshared',
                inputValue: '1',
                boxLabel: '共享'
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'isshared',
				inputValue: '0',
				boxLabel: '不共享'
    }],
    buttons: [{
    	text: '修改',
        handler: updateDef,
        style: 'margin: 0 0 0 10px'
    	}]
});

function updateDef(){
    	
    if (Ext.getCmp('editDef_form').form.isValid()){
        Ext.getCmp('editDef_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	defStore.load();
            editDefWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId}  
      });
   }
}

var editDefWindow = new Ext.Window({ 
	id:'editDef_Window',
	applyTo: 'edit_def_win',  //前端放置当前js文件的页面中的div名称  
	title:'修改特征属性',  
	width: 350,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: editDefForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        editDefForm.getForm().reset();
    }
});

function editDef( btn)  
{   
	var record = defGrid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_charaId=record.id;
    
    editDefForm.getForm().loadRecord(record);

    editDefWindow.show(btn);
}
//---------------------------------DataManage-------
var dataStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listCharaData.ac',
    storeId: 'data_Store',
    // reader configs
    root: 'data',
    idProperty: 'dataId',  
    fields: [  
                 {name: 'dataId', mapping: 'dataId'},  
                 {name: 'dataName'},  
                 {name: 'dataDesc'},
                 {name: 'isshared'},
				 {name: 'user',convert: function(v, rec) {
				 											return v.userDesc;
				 }}
             ]  
});
         
//dataStore.load();  
  
var dataColModel = new Ext.grid.ColumnModel([
    {id:'dataName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'dataName'},
    {header: "描述", width: 250, sortable: true, dataIndex: 'dataDesc'},
    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
	    if (v == 1) return "是"
	    if (v == 0) return "否"
	}},
	{header: "用户", width: 100, sortable: true, dataIndex: 'user'}
]);

var dataGrid = new Ext.grid.GridPanel({
    renderTo: "manage_data_form",
    ds: dataStore,
    cm:dataColModel,
    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:250,
    width:600,
    title:'特征属性数据',
    tbar : [{
                id : 'newDataWindow',
                text : '新建',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   newData(this);
                }
     },{
                id : 'editDataWindow',
                text : '编辑',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   editData(this);
                }
     }, {
                id : 'refreshData',
                text : '刷新',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   dataStore.proxy.conn.url='admin/listCharaData.ac?charaId='+in_charaId;
                   dataStore.load();
                }
     },
     {
			id : 'delData',
			text : '删除',
			//iconCls : 'edit',
			handler : function() {
                var sm = dataGrid.getSelectionModel();
                var sel = sm.getSelected();
                if (sm.hasSelection()) {
                    Ext.Msg.show({
                        title : '删除特征属性',
                        buttons : Ext.MessageBox.YESNOCANCEL,
                        msg : '删除 ' + sel.data.dataName + '?',
                        fn : function(btn) {
                            if (btn == 'yes') {
                                var conn = new Ext.data.Connection();
                                conn.request({
                                            url : 'admin/dropCharaData.ac',
                                            params : {
                                                dataId : sel.id
                                            },
                                            success : function(resp, opt) {
                                            	Ext.Msg.alert('成功','删除成功');
                                                dataGrid.getStore().remove(sel);
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
     }]
});
/*
var manageDataWindow = new Ext.Window({  
	id:'manageData_Window',
	applyTo: 'manage_data_win',  //前端放置当前js文件的页面中的div名称  
	title:'特征属性数据',  
	width: 605,
	layout: 'fit',
	autoHeight: true,  
	closable: false,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: dataGrid ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : false,
    //initHidden:true,
    reset : function() {
         dataGrid.getStore().load();
    }
});

*/
function manageData()  
{   
	var record = defGrid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择特征属性');  
        return;  
    }  
    
    in_charaId=record.id;
    
    dataStore.proxy.conn.url='admin/listCharaData.ac?charaId='+in_charaId;
    dataStore.load();
    
	//manageDataWindow.setTitle(record.get('charaName')+'的数据');
	dataGrid.setTitle(record.get('charaName')+'的数据');
    //manageDataWindow.show(btn);
}
//-------------------------newData-------------------------------
var  newDataForm=  new Ext.form.FormPanel({
    id: 'newData_form',
    url: 'admin/createCharaData.ac',
    renderTo: 'new_data_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '名称',
	            id:'dataNameId',
			    name:'dataName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {                    
                fieldLabel: '描述',
	            id:'dataDescId',
			    name:'dataDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'isshared',
                inputValue: '1',
                boxLabel: '共享',
                checked:true
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'isshared',
				inputValue: '0',
				boxLabel: '不共享'
    }],
    buttons: [{
    	text: '保存',
        handler: saveData,
        style: 'margin: 0 0 0 10px'
    	}]
});

function saveData(){
    	
    if (Ext.getCmp('newData_form').form.isValid()){
        Ext.getCmp('newData_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	dataStore.proxy.conn.url='admin/listCharaData.ac?charaId='+in_charaId;
         	dataStore.load();
            newDataWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId}  
      });
   }
}

var newDataWindow = new Ext.Window({
	id:'newData_Window',
	applyTo: 'new_data_win',  //前端放置当前js文件的页面中的div名称  
	title:'新建特征属性数据',  
	width: 350,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: newDataForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        newDataForm.getForm().reset();
    }
});

function newData( btn)  
{       
    newDataForm.getForm().reset();
    newDataWindow.show(btn);
}
//-------------------------editData-------------------------------
var  editDataForm=  new Ext.form.FormPanel({
    id: 'editData_form',
    url: 'admin/updateCharaData.ac',
    renderTo: 'edit_data_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {                    
                fieldLabel: '名称',
	            id:'dataNameId2',
			    name:'dataName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {                    
                fieldLabel: '描述',
	            id:'dataDescId2',
			    name:'dataDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'isshared',
                inputValue: '1',
                boxLabel: '共享'
    },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'isshared',
				inputValue: '0',
				boxLabel: '不共享'
    }],
    buttons: [{
    	text: '修改',
        handler: updateData,
        style: 'margin: 0 0 0 10px'
    	}]
});

function updateData(){
    	
    if (Ext.getCmp('editData_form').form.isValid()){
        Ext.getCmp('editData_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	dataStore.proxy.conn.url='admin/listCharaData.ac?charaId='+in_charaId;
         	dataStore.load();
            editDataWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId,dataId:in_dataId}  
      });
   }
}

var editDataWindow = new Ext.Window({  
	id:'editData_Window',
	applyTo: 'edit_data_win',  //前端放置当前js文件的页面中的div名称  
	title:'新建特征属性数据',  
	width: 350,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: editDataForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        editDataForm.getForm().reset();
    }
});

function editData( btn)  
{   
	var record = dataGrid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_dataId=record.id;
    
    editDataForm.getForm().loadRecord(record);

    editDataWindow.show(btn);
}