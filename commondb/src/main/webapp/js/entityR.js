

var in_roleid;

function showRelPanel( btn)  
{       
    var record = grid.getSelectionModel().getSelected(); 
           
    if(!record){  
        Ext.Msg.alert('信息','请选择要编辑的数据');  
        return;  
    }  
    
    in_roleid=record.id;
    
    //editRoleForm.getForm().loadRecord(record);
    Ext.getCmp('roleNameId2').setValue(record.get('roleName'));
    Ext.getCmp('roleDescId2').setValue(record.get('roleDesc'));
    var metaIdSelArr=new Array();
    var metaOperSelArr=new Array();
    var metaArr=record.get('metaList');
    for(var k=0;k<metaArr.length;k++){
    	metaIdSelArr[k]=metaArr[k].metaId;
    	if(metaArr[k].roleOper==1){
    		metaOperSelArr[k]=true;
    	}else{metaOperSelArr[k]=false;}
    }
    
    var recordArr = metaStore.queryBy(function(r,id){  
	    return metaIdSelArr.indexOf(r.get('metaId'))!=-1;  
	},this).getRange();
	
	sm.selectRecords(recordArr,false);
	
    for(var n=0;n<recordArr.length;n++){
    	alert("需要"+metaOperSelArr[n]+"原来"+recordArr[n].data.permission);
    	recordArr[n].data.permission=metaOperSelArr[n];
    }
    
    EditWindow.show(btn);
          
}
var EditWindow = new Ext.Window({  
	applyTo: 'edit_win',  //前端放置当前js文件的页面中的div名称  
	title:'修改',  
	//width: 755,
	layout: 'fit',
	autoHeight: true,  
	closable: true,  
	closeAction: 'hide',//close  
	resizable: false,   
	items: editRoleForm ,//在window中加载编辑的form  
	callback : null,
    params : null,
    plain:true,
    modal : true
});

var MyDynStore = function(config) 
{
    Ext.apply(this, {
    // store configs
    autoDestroy: true,
    url: 'admin/jsonEntity.ac?metaId=' + config.metaId,
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'id',  
    fields: config.fields  
    });
    MyDynStore.superclass.constructor.apply(this, arguments);
 }

Ext.extend(MyDynStore, Ext.data.JsonStore,{});

var MyDynColMode = function(config)
{
	Ext.apply(this, {
	    columns:config.columns
	});
    MyDynColMode.superclass.constructor.apply(this, arguments);
}

Ext.extend(MyDynColMode, Ext.grid.ColumnModel, {});

function createGrid(config)
{
    var store2 = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/jsonEntity.ac?metaId=' + config.metaId,
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'id',  
    fields: config.fields  
});
    store2.load();
    var grid;

	var MyDynGrid = function(config)
	{
	    Ext.apply(this, {
	        renderTo: 'manage_entity_' + config.metaId,
	        ds: store2,
	        cm: new MyDynColMode(config),
	        stripeRows: true,
	        autoExpandColumn: 'id',
	        height:500,
	        width:940,
	        title:'实体列表',
	        tbar : [
	         {
	                id : 'relData',
	                text : '保存关联',
	                //iconCls : 'edit',
	                handler : function() {
                        showRelPanel(grid);
	                }
	                
	         }]
	    });
        MyDynGrid.superclass.constructor.apply(this, arguments);
}
    
Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

grid = new MyDynGrid(config);

    
//d.renderTo("manage_entity_" + config.metaId);
};

function save(){
	
    var sel = sm.getSelections();
    var metaIdArr=new Array();
    var operArr=new Array();
    for(var i=0;i<sel.length;i++){
    	metaIdArr[i]=sel[i].get('metaId');
    	//alert(sel[i].get('permission'));
     	if(sel[i].get('permission')==true){
    		operArr[i]=1;
    	}else{
    		operArr[i]=0;
    	}
    }
    
    var conn = new Ext.data.Connection();
    conn.request({
                url : 'admin/saveEntityR.ac',
                params : {
                	roleId:in_roleid,
                    metaIdArr : metaIdArr,
                    operArr: operArr,
                    roleDesc:Ext.getCmp('roleDescId2').getValue(),
                    roleName:Ext.getCmp('roleNameId2').getValue()
                },
                success : function(resp, opt) {
                	roleStore.load();
                    Ext.Msg.alert('成功','保存成功');
                    EditWindow.hide();
                },
                failure : function(resp, opt) {
                    Ext.Msg.alert('错误','保存失败');
                }
   });
}   
