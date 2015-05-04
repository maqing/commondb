Ext.form.Field.prototype.msgTarget = 'side';
var detailWindow; 

function showeditPanel( grid,metaId)  
{       //直接取得选中的行对应的record  
        var record = grid.getSelectionModel().getSelected();   
        if(!record){  
            Ext.Msg.alert('信息','请选择要编辑的数据');  
            return;  
        }  
          
        //--定义编辑窗体  
        //entityForm.show();
        if(!detailWindow)  
        {  

            win = detailWindow = new Ext.Window({  
                //el: 'bd',  //前端放置当前js文件的页面中的div名称  
                //renderTo:'viewport',
                title:'查看记录',  
                width: 600,  
                height: 700,
                autoScroll:true,
                //autoheight: true,  
                //layout:'fit',
                closable: true,  
                closeAction: 'hide',  
                resizable: false, 
                y:0,
                items: entityForm //在window中加载编辑的form  
            });  
        }
        
        //选中关联的特征属性
        rEntityChara(record.data.id);
        //选中关联的元数据
        rMeta(metaId,record.data.id);
        
        detailWindow.show();//显示编辑窗口 
        
        //entityForm.show();
        entityForm.getForm().loadRecord(record);
        //entityForm.myInitFields(record);
        
       //读取层级属性数据
        var mk = new Ext.LoadMask('manage_entity_' + metaId, {
            msg: '正在加载数据，请稍候！',
            removeMask: true //完成后移除
        });
        mk.show(); //显示
        //var hieraData = rHieraData(metaId,record.data.id);
        new Ext.data.Connection().request({
            url: '../admin/jsonHierarchyByEntity.ac?metaId=' + metaId,
            params: { entityId: record.data.id},
            success: function(response, options){
                var res = Ext.util.JSON.decode(response.responseText); 
                var hieraData = res.data;
                entityForm.myInitFields(record,hieraData);
                mk.hide();  
            },
            failure: function() { mk.hide(); }
        });
        
}
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
var store2
function createGrid(config)
{
    store2 = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/jsonEntity.ac?metaId=' + config.metaId,
    //url: '../admin/jsonEntity.ac',
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'id',  
    fields: config.fields  
});
	//store2.load({params: { metaId: config.metaId }}); 
    store2.load();
    var grid;

	var MyDynGrid = function(config)
	{
	    Ext.apply(this, {
	        renderTo: 'manage_entity_' + config.metaId,
	        ds: store2,
            cm: new MyDynColMode(config),
	        plugins: config.myFilter,
            stripeRows: true,
	        autoExpandColumn: 'id',
	        height:500,
	        width:940,
	        title:'实体列表:'+config.entityName,
	        tbar : [{
                        id : 'refresh',
                        text : '刷新',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           //store2.proxy.conn.url='admin/jsonEntity.ac';
                           //store2.load({params: { metaId: in_metaId}});
                        	store2.proxy.conn.url='admin/jsonEntity.ac?metaId=' + config.metaId;
                        	store2.load();
                        }
             		},{
                        id : 'newWindow',
                        text : '查看',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           showeditPanel(grid,config.metaId);
                        }
             		},{
                        id : 'searchCharaWindow',
                        text : '特征属性搜索',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           searchCharaWindow(config.metaId,store2);
                        }
             		},{
                        id : 'searchHierWindow',
                        text : '层级属性搜索',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                        	
                           searchHierWindow(config.metaId,store2);
                        }
             		}],
             listeners:{  
					//双击  
			       rowdblclick :function(grid,row){  
			           showeditPanel(grid,config.metaId);
			       }  
			 }  
	    });
        MyDynGrid.superclass.constructor.apply(this, arguments);
}
    
Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

grid = new MyDynGrid(config);

    
//d.renderTo("manage_entity_" + config.metaId);
};


