
Ext.form.Field.prototype.msgTarget = 'side';

var roleRefresh = function()
{
    roleStore.load();
    metaStore.load();
}

var metaStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/getAllMetaForAdmin.ac',
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'metaId',  
    fields: [  
                 {name: 'metaId', mapping: 'metaId'},  
                 {name: 'entityName'},  
                 {name: 'entityDesc'},   
                 {name: 'createTime'},  
                 {name: 'lastUpdateTime'},
                 {name: 'permission',defaultValue:'0'}
             ]  
});

metaStore.load();  
var editRoleWindow = new Ext.Window({
    id:'Edit_Role_Window1',
    applyTo: 'edit_role_win',  //前端放置当前js文件的页面中的div名称  
    //el: 'edit_win', 
    title:'修改',  
    width: 752,
    layout: 'fit',
    autoHeight: true,  
    closable: true,  
    closeAction: 'hide',//close  
    resizable: false,   
    items: editRoleForm ,//在window中加载编辑的form  
    callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        editRoleForm.getForm().reset();
    },
    listeners:{
        
    }
});

var in_roleid;

function showRoleEditPanel( btn)  
{       
    var record = grid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_roleid=record.id;
    
    //editRoleForm.getForm().loadRecord(record);

    //Ext.getCmp('roleNameId2').setValue(record.get('roleName'));
    
	
	//roleGrid.on('render',function(m){
	
	
	/*	
    for(var n=0;n<recordArr.length;n++){
    	//alert("需要"+metaOperSelArr[n]+"原来"+recordArr[n].data.permission);
    	//recordArr[n].data.permission=metaOperSelArr[n];
    	//alert(recordArr[n].data.permission.maskValue);
    	//maskCombox.setValue(recordArr[n].data.permission.maskValue);
		//maskCombox.el.dom.value=recordArr[n].data.permission.maskName;
    }
    */
    

    editRoleWindow.show(btn);
    
    
    var metaIdSelArr=[];
    var metaOperSelArr=[];
    var metaArr=record.get('metaList');
    
    //alert('metaArr' + metaArr.length)
    for(var k=0;k<metaArr.length;k++){
        metaIdSelArr[k]=metaArr[k].metaId;
        metaOperSelArr[k]=metaArr[k].roleOper;
        
    }
    
    var recordArr = metaStore.queryBy(function(r,id){  
        return metaIdSelArr.indexOf(r.get('metaId'))!=-1;  
    },this).getRange();
    
    sm.selectRecords(recordArr,false);
    Ext.getCmp('roleDescId2').setValue(record.get('roleDesc'));
    
    var girdcount=0;
        metaStore.each(function(r){
           r.set('permission', 0);
           r.commit();
        });
        
        metaStore.each(function(r){
            for(var j=0;j<metaIdSelArr.length;j++){
                if(metaIdSelArr[j]==r.get('metaId')){                   
                    r.set('permission', metaOperSelArr[j]);
                    r.commit();
                    
                }
            } 
            girdcount=girdcount+1; 
        });
        
        
          
}



     


  
var sm = new Ext.grid.CheckboxSelectionModel({singleSelect :false});

/*
var ComMaskData = [
    ['0','无权限'],['1','读取'],['8','删除'],['16','管理']
  ];
*/
var ComMaskData = [
    ['0','无权限'],['1','管理']
  ];

var ComMaskStore = new Ext.data.Store({
	reader: new Ext.data.ArrayReader({}, [
        {name: 'maskValue'},
        {name: 'maskName'}
      ])
});

ComMaskStore.loadData(ComMaskData);

var maskCombox=new Ext.form.ComboBox({
                //fieldLabel: '掩码',
                id:'ComMask',
                //readOnly:true,
                xtype: 'combo',
                store: ComMaskStore,
                typeAhead: true,
                mode: 'local',
                triggerAction: 'all',
                displayField:'maskName',
                valueField:'maskValue',
                //emptyText :'请选择权限',
                //value:1,
                //lazyRender: true,
                allowBlank:false,
                width:78
})

var cm = new Ext.grid.ColumnModel([
        new Ext.grid.RowNumberer(),
        sm,
        {id:'entityName',header: "名称", width: 100, sortable: true, locked:false, dataIndex: 'entityName'},
        {header: "描述", width: 200, sortable: true, dataIndex: 'entityDesc'},
        {header: "更新时间", width: 150, sortable: true,  dataIndex: 'lastUpdateTime'},
        {header: "创建时间", width: 150, sortable: true,  dataIndex: 'createTime'},
        {header: "权限", width: 80, sortable: true,  dataIndex: 'permission',editor: maskCombox,renderer: function(value, cellmeta, record) {
	          //通过匹配value取得ds索引
	          var index = ComMaskStore.find(Ext.getCmp('ComMask').valueField,value); 
	          //通过索引取得记录ds中的记录集
	          var record = ComMaskStore.getAt(index); 
	          //返回记录集中的value字段的值
	          return record.data.maskName;
	     }}
]);

var roleGrid = new Ext.grid.EditorGridPanel({
	id:'roleGrid',
    ds: metaStore,
    //renderTo: "newRole",
    cm: cm,
	sm: sm,
	stripeRows: true,
    //autoExpandColumn: 'entityName',
    height:400,
    width:750,
    //plugins: checkColumn,
    clicksToEdit: 1,
    //title:'角色',
    tbar : [/*'角色名称:',
          {
　　　　　　　    id:'roleNameId2',
		   name:'roleName',
　　　　　　　    xtype:'textfield',
　　　　　　　    width : 200,
　　　　　　　    readOnly:false
　　　            },'角色描述:',*/
    	'角色名称:',
　　　            {
	　　　             id:'roleDescId2',
		   name:'roleDesc',
　　　　　　　    xtype:'textfield',
　　　　　　　    width : 300,
　　　　　　　    readOnly:false
　　　            },{
            xtype:'button',
            text: '保存',
        tooltip:'请选择列表中的元数据',
        //iconCls:'edit',
        handler : save
     }]
});

function save(){
	
    var sel = sm.getSelections();
    var metaIdArr=new Array();
    var operArr=new Array();
    for(var i=0;i<sel.length;i++){
    	metaIdArr[i]=sel[i].get('metaId');
    	operArr[i]=sel[i].get('permission');
    }
    
    var conn = new Ext.data.Connection();
    conn.request({
                url : 'security/updateRole.ac',
                params : {
                	roleId:in_roleid,
                    metaIdArr : metaIdArr,
                    operArr: operArr,
                    roleDesc:Ext.getCmp('roleDescId2').getValue()
                    //roleName:Ext.getCmp('roleNameId2').getValue()
                },
                success : function(resp, opt) {
                	Ext.Msg.alert('成功','保存成功');
                    roleStore.load();
                    editRoleWindow.hide();
                },
                failure : function(resp, opt) {
                    Ext.Msg.alert('失败','保存失败');
                }
   });
}   

var editRoleForm=  new Ext.form.FormPanel({
        id: 'role-form',
        url: 'security/updateRole.ac',
        frame: true,
        labelAlign: 'left',
        bodyStyle:'padding:1px',
        width: 752,
        layout: 'fit',    // Specifies that the items will now be arranged in columns
        columnWidth: 1,
        items: roleGrid,
        renderTo: 'edit_role'//Ext.getBody()//
});


   
     //构建Store
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
	                 {name: 'roleDesc'},
	                 {name: 'metaList'}    
	             ]  
	});
         

     //载入  
     roleStore.load();  
  

    // the DefaultColumnModel expects this blob to define columns. It can be extended to provide
    // custom or reusable ColumnModels
     var colModel = new Ext.grid.ColumnModel([
     /*
        {id:'roleName',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'roleName'},
        {header: "描述", width: 250, sortable: true, dataIndex: 'roleDesc'}
        */
      {id:'roleDesc',header: "名称", width: 300, sortable: true, locked:false, dataIndex: 'roleDesc'}
    ]);
    
    var grid = new Ext.grid.GridPanel({
        renderTo: "manageRole",
        ds: roleStore,
        cm:colModel,
        sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
        stripeRows: true,
        //autoExpandColumn: 'name',
        height:350,
        width:600,
        title:'角色列表',
        tbar : [{
                    id : 'newWindow',
                    text : '编辑',
                    //iconCls: 'silk-add',
                    //iconCls : 'edit',
                    handler : function() {
                        
                       showRoleEditPanel(this);
                    }
         }, {
                    id : 'refresh',
                    text : '刷新',
                    //iconCls: 'silk-add',
                    //iconCls : 'edit',
                    handler : function() {
                       roleStore.load();
                    }
         },
         {
				id : 'delRoleData',
				text : '删除',
				//iconCls : 'edit',
				handler : function() {
                    var sm = grid.getSelectionModel();
                    var sel = sm.getSelected();
                    if (sm.hasSelection()) {
                        Ext.Msg.show({
                            title : '删除角色',
                            buttons : Ext.MessageBox.YESNOCANCEL,
                            msg : '确定要删除?',//'删除 ' + sel.data.roleName + '?'
                            fn : function(btn) {
                                if (btn == 'yes') {
                                    var conn = new Ext.data.Connection();
                                    conn.request({
                                                url : 'security/delRole.ac',
                                                params : {
                                                    roleId : sel.id
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
         }]
    });


    
