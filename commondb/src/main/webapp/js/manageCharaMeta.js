//---------------------chara----------------------------------

// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';
var in_metaId;
var in_charaId;
var defStore;
var charaGroupGrid;

function loadDefStore(metaId){
	defStore.load({params: { metaId: metaId }}); 
	
	loadGroupStore();
}

function loadGroupStore(){

	groupStore.load({params: { charaIdArr: in_charaIdArr }});
	
}
function newCharaGroupGrid(metaId,response){

	in_metaId=metaId;
	defStore = new Ext.data.JsonStore({
	    // store configs
	    autoDestroy: true,
	    url: 'admin/listCharaDefByMeta.ac',
	    storeId: 'def_Store',
	    // reader configs
	    root: 'data',
	    idProperty: 'charaId',  
	    fields: [  
	                 {name: 'charaId', mapping: 'charaId'},  
	                 {name: 'charaName'},  
	                 {name: 'charaDesc'},
	                 {name: 'isshared'},
					 {name: 'user',convert: function(v, rec) {
					 											return v.userDesc;
					 }}
	             ]  
	});
	         
	defStore.load({params: { metaId: metaId }}); 
	
	
	defStore.on('load',function(s,records){
		var charaIdArr=[];
		var sCount=0
		defStore.each(function(r){
			charaIdArr[sCount]=r.get('charaId')
			sCount++;
		});
		in_charaIdArr=charaIdArr;
		newGroupStore(charaIdArr,metaId,response);
	});

}

var groupStore;
var groupSm;

function newGroupStore(charaIdArr,metaId,response){
	if(!charaGroupGrid || charaGroupGrid == null){
	groupStore = new Ext.data.GroupingStore({
	    autoDestroy: true,
	    url: 'admin/listCharaDataByCharaId.ac',
	   	//baseParams: {charaIdArr: charaIdArr},
	    storeId: 'group_Store',
		sortInfo:{field: 'dataId', direction: "ASC"},
	    groupField:'charaDef',
	    /*
		proxy: new Ext.data.HttpProxy({
			url: 'admin/listCharaDataByCharaId.ac'
	    }), 
	    */
		reader : new Ext.data.JsonReader({  
	        root: 'data',
	        idProperty: 'dataId',  
	        fields: [  
	                     {name: 'dataId', mapping: 'dataId'},  
	                     {name: 'dataName'},  
	                     {name: 'dataDesc'},
	                     {name: 'isshared'},
						 {name: 'charaId'},
	    				 {name: 'user',convert: function(v, rec) {
	    				 											return v.userDesc;
	    				 }},
	    				 {name: 'charaDef',convert: function(v, rec) {
	    				 											return v.charaName;
	    				 }}
	                 ] 
		})
	});
	groupStore.load({params: { charaIdArr: charaIdArr }});
	
	groupSm = new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	//var groupSm = new Ext.grid.RowSelectionModel({singleSelect:true});
	/*
	var groupColModel = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
	    groupSm,
	    {id:'dataName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'dataName'},
	    {header: "描述", width: 250, sortable: true, dataIndex: 'dataDesc'},
	    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
		    if (v == 1) return "是"
		    if (v == 0) return "否"
		}},
		{header: "用户", width: 100, sortable: true, dataIndex: 'user'},
		{header: "特征属性", width: 100, sortable: true, dataIndex: 'charaDef'}
	]);
	*/
	var groupColModel = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
	    groupSm,
	    {id:'dataName',header: "名称", width: 220, sortable: true, locked:false, dataIndex: 'dataName'}
		,{header: "特征属性", width: 180, sortable: true, dataIndex: 'charaDef'}
	]);
	charaGroupGrid = new Ext.grid.GridPanel({
	    //renderTo: "manage_def",
		id:'chara_GroupGrid',
	    ds: groupStore,
	    cm:groupColModel,
	    sm:groupSm,
	    //stripeRows: true,
	    //autoExpandColumn: 'name',
	    height:350,
	    width:500,
		frame:true,
		collapsible: true,
	    animCollapse: false,
		view: new Ext.grid.GroupingView({
	        groupTextTpl : '{text} ({[values.rs.length]} {[values.rs.length >= 1 ? "条" : "Item"]})',  //定义分组行模板  
	        groupByText : '使用当前字段进行分组',               //表头菜单提示信息  
	        showGroupsText : '特征属性',                       //表头菜单提示信息  
	        showGroupName : false,                             //显示分组字段名称  
	        startCollapsed : false,                            //展开分组  
	        hideGroupedColumn : true                          //隐藏分组列  
		}),
	    //title:'特征属性列表',
	    tbar : [{
	                id : 'refreshChara',
	                text : '刷新',
	                //iconCls: 'silk-add',
	                //iconCls : 'edit',
	                handler : function() {
	                   loadDefStore(in_metaId);
	                }
	     },{
	                id : 'manageChara',
	                text : '管理特征属性',
	                //iconCls: 'silk-add',
	                //iconCls : 'edit',
	                handler : function() {
	                   newCharaDefGrid(metaId);
	                }
	     }
	     ]
	});
	/*
	charaGroupGrid.on('activate',function(g){
			createForm(response);
	})
	*/
	createForm(response);
	}
}


var defGrid;
var manageDefWindow
function newCharaDefGrid(metaId){
	
	if(!defGrid || defGrid == null){	
	  
		var defColModel = new Ext.grid.ColumnModel([
		    {id:'charaName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'charaName'},
		    {header: "描述", width: 250, sortable: true, dataIndex: 'charaDesc'},
		    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
			    if (v == 1) return "是"
			    if (v == 0) return "否"
			}},
			{header: "用户", width: 100, sortable: true, dataIndex: 'user'}
		]);
		
		defGrid = new Ext.grid.GridPanel({
		    //renderTo: "manage_def",
		    ds: defStore,
		    cm:defColModel,
		    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
		    stripeRows: true,
		    //autoExpandColumn: 'name',
		    height:450,
		    width:600,
		    //title:'特征属性列表',
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
		                   loadDefStore(in_metaId);
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
		                                                loadDefStore(in_metaId);
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
		     },{
		                id : 'manageDataWindow',
		                text : '管理数据',
		                //iconCls: 'silk-add',
		                //iconCls : 'edit',
		                handler : function() {
		                	var record = defGrid.getSelectionModel().getSelected(); 
           
						    if(!record){  
						        Ext.Msg.alert('信息','请选择特征属性');  
						        return;  
						    }  
		                	newCharaDataGrid(record.id,record.get('charaName'));
		                	
		                }
		     }]
		});
	}
	if(!manageDefWindow || manageDefWindow == null){	
		manageDefWindow = new Ext.Window({ 
			id:'manageDef_Window',
			el: 'manage_def_win',  //前端放置当前js文件的页面中的div名称  
			title:'管理特征属性',  
			width: 600,
			layout: 'fit',
			autoHeight: true,  
			closable: true,  
			closeAction: 'hide',//close  
			resizable: false,   
			items: defGrid ,//在window中加载编辑的form  
			callback : null,
		    params : null,
		    plain:true,
		    modal : true,
		    //initHidden:true,
		    reset : function() {
		         defGrid.getStore().load();
		    }
		});
	}
	manageDefWindow.show();
}

var manageDataWindow;
var dataGrid;
var dataStore
function newCharaDataGrid(charaId,charaName){
	in_charaId=charaId;	
	if(!dataGrid || dataGrid == null){
			
		var dataColModel = new Ext.grid.ColumnModel([
		    {id:'dataName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'dataName'},
		    {header: "描述", width: 250, sortable: true, dataIndex: 'dataDesc'},
		    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
			    if (v == 1) return "是"
			    if (v == 0) return "否"
			}},
			{header: "用户", width: 100, sortable: true, dataIndex: 'user'}
		]);
		
		dataGrid = new Ext.grid.GridPanel({
		    //renderTo: "manage_data_form",
		    ds: groupStore,
		    cm:dataColModel,
		    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
		    stripeRows: true,
		    //autoExpandColumn: 'name',
		    height:350,
		    width:600,
		    //title:'数据列表',
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
		     }, /*{
		                id : 'refreshData',
		                text : '刷新',
		                //iconCls: 'silk-add',
		                //iconCls : 'edit',
		                handler : function() {
		                   var record = defGrid.getSelectionModel().getSelected();
		                   dataStore.filter( 'charaId', record.id, true, true );
						   dataStore.load();
		                }
		     },*/
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
		                                            	Ext.Msg.alert('成功','删除成功',function(){groupStore.filter( 'charaId', in_charaId, true, true );});
		                                                loadDefStore(in_metaId);
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
		
	}
	if(!manageDataWindow || manageDataWindow == null){
		manageDataWindow = new Ext.Window({ 
			id:'manageData_Window',
			el: 'manage_data_win',  //前端放置当前js文件的页面中的div名称  
			title:'特征属性数据',  
			width: 600,
			layout: 'fit',
			autoHeight: true,  
			closable: true,  
			closeAction: 'hide',//close  
			resizable: false,   
			items: dataGrid ,//在window中加载编辑的form  
			callback : null,
		    params : null,
		    plain:true,
		    modal : true,
		    //initHidden:true,
		    reset : function() {
		         dataGrid.getStore().load();
		    }
		    
		});	
		manageDataWindow.addListener('hide', function(win){
			groupStore.clearFilter();
		})
	}
	
	groupStore.filter( 'charaId', charaId, true, true );
    //groupStore.load();
    
	manageDataWindow.setTitle('管理'+charaName+'的数据');
    manageDataWindow.show();
}
function selChara(charaArr){
	
	var gSm=charaGroupGrid.getSelectionModel()
	gSm.clearSelections();
	if(charaArr!=null&&charaArr!=''){
		var dataIdSelArr=[];
		for(var k=0;k<charaArr.length;k++){
	    	dataIdSelArr[k]=charaArr[k].data_id;
	    	//alert(dataIdSelArr[k]);
	    }
	    
	    var recordArr = groupStore.queryBy(function(record,id){
	    	return dataIdSelArr.indexOf(record.get('dataId'))!=-1;  
		},this).getRange();
		
		gSm.selectRecords(recordArr,false);
	}
}
//新建def
var  newDefForm;
var newDefWindow;
function newDef(){
	
	if(!newDefForm || newDefForm == null){	
		newDefForm=  new Ext.form.FormPanel({
		    id: 'newDef_form',
		    url: 'admin/createCharaDefWithMeta.ac',
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
	}

	if(!newDefWindow || newDefWindow == null){	
		newDefWindow = new Ext.Window({  
			id:'newDef_Window',
			el: 'new_def_win',  //前端放置当前js文件的页面中的div名称  
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
	}
	newDefForm.getForm().reset();
    newDefWindow.show();
}

function saveDef(){
    	
    if (Ext.getCmp('newDef_form').form.isValid()){
        Ext.getCmp('newDef_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	loadDefStore(in_metaId);
            newDefWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',metaId:in_metaId}  
      });
   }
}

//更新def
var  editDefForm;
var editDefWindow;
function editDef(){
	
	if(!editDefForm || editDefForm == null){	
		editDefForm=  new Ext.form.FormPanel({
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
	}

	if(!editDefWindow || editDefWindow == null){	
		editDefWindow = new Ext.Window({  
			id:'editDef_Window',
			el: 'edit_def_win',  //前端放置当前js文件的页面中的div名称  
			title:'修改特征属性',  
			width:350,
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
	}
	
	var record = defGrid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_charaId=record.id;
    
    editDefForm.getForm().loadRecord(record);

    editDefWindow.show();
}

function updateDef(){
    	
    if (Ext.getCmp('editDef_form').form.isValid()){
        Ext.getCmp('editDef_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	loadDefStore(in_metaId);
            editDefWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId}  
      });
   }
}

//新建data
var  newDataForm;
var newDataWindow;
function newData(){
	
	if(!newDataForm || newDataForm == null){	
		newDataForm=  new Ext.form.FormPanel({
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
	}

	if(!newDataWindow || newDataWindow == null){	
		newDataWindow = new Ext.Window({  
			id:'newData_Window',
			el: 'new_data_win',  //前端放置当前js文件的页面中的div名称  
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
	}
	newDataForm.getForm().reset();
    newDataWindow.show();
}

function saveData(){
    
    if (Ext.getCmp('newData_form').form.isValid()){
        Ext.getCmp('newData_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功',function(){groupStore.filter( 'charaId', in_charaId, true, true );});
         	loadDefStore(in_metaId);
            newDataWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId}  
      });
   }
}
//更新data
var  editDataForm;
var editDataWindow;
function editData(){
	
	if(!editDataForm || editDataForm == null){
		editDataForm=  new Ext.form.FormPanel({
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
	}
	if(!editDataWindow || editDataWindow == null){	
		editDataWindow = new Ext.Window({ 
			id:'editData_Window',
			el: 'edit_data_win',  //前端放置当前js文件的页面中的div名称  
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
	}
	var record = dataGrid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_dataId=record.id;
    
    editDataForm.getForm().loadRecord(record);

    editDataWindow.show();
}

function updateData(){
    	
    if (Ext.getCmp('editData_form').form.isValid()){
        Ext.getCmp('editData_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功',function(){groupStore.filter( 'charaId', in_charaId, true, true );});
            loadDefStore(in_metaId);
            editDataWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',charaId:in_charaId,dataId:in_dataId}  
      });
   }
}

