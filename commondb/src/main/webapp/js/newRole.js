

 var store = new Ext.data.JsonStore({
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
         

    store.load();  
      
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
                store: ComMaskStore,
                typeAhead: false,
                mode: 'local',
                triggerAction: 'all',
                displayField:'maskName',
                valueField:'maskValue',
                //emptyText :'请选择权限',
                allowBlank:false,
                width:78
                    
 });
    



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
        ds: store,
        //renderTo: "newRole",
        cm: cm,
    	sm: sm,
    	stripeRows: true,
        //autoExpandColumn: 'entityName',
        height:400,
        width:750,
        //plugins: checkColumn,
        clicksToEdit: 1,
        title:'新建角色',
        tbar : [/*'角色名称:',
          {
　　　　　　　    id:'roleNameId',
			   name:'roleName',
　　　　　　　    xtype:'textfield',
　　　　　　　    width : 200,
　　　　　　　    readOnly:false,
			   emptyText :'请以"ROLE_"开头'
　　　            },'角色描述:'*/
			'角色名称:',
　　　            {
	　　　             id:'roleDescId',
			   name:'roleDesc',
　　　　　　　    xtype:'textfield',
　　　　　　　    width : 300,
　　　　　　　    readOnly:false
　　　            },{
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
                    url : 'security/createRole.ac',
                    params : {
                        metaIdArr : metaIdArr,
                        operArr: operArr,
                        roleDesc:Ext.getCmp('roleDescId').getValue()
                        //roleName:Ext.getCmp('roleNameId').getValue()
                    },
                    success : function(resp, opt) {
                        Ext.Msg.alert('成功','保存成功');
                    },
                    failure : function(resp, opt) {
                        Ext.Msg.alert('失败','保存失败');
                    }
       });
    }

    var gridForm = new Ext.FormPanel({
        id: 'role-form',
        url: 'security/createRole.ac',
        frame: true,
        labelAlign: 'left',
        bodyStyle:'padding:1px',
        width: 752,
        layout: 'fit',    // Specifies that the items will now be arranged in columns
        columnWidth: 1,
        items: roleGrid,
        renderTo: 'newRole'
    });

