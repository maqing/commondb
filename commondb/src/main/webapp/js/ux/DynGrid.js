Ext.form.Field.prototype.msgTarget = 'side';
var editWindow; 
var store2;
function showeditPanel( grid,metaId)  
{       //直接取得选中的行对应的record  
        var record = grid.getSelectionModel().getSelected();   
        if(!record){  
            Ext.Msg.alert('信息','请选择要编辑的数据');  
            return;  
        }  
                
        //--定义编辑窗体  
        //entityForm.show();
        if(!editWindow)  
        {  

            win = editWindow = new Ext.Window({  
                //el: 'bd',  //前端放置当前js文件的页面中的div名称  
                //renderTo:'viewport',
                title:'编辑记录',  
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
        
        editWindow.show();
        entityForm.getForm().loadRecord(record);
        
        
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






function createGrid(cf)
{
    var proxy = new Ext.data.HttpProxy({
	    api: {
	        read : 'admin/jsonEntity.ac?metaId=' + cf.metaId ,
	        destroy: 'admin/delEntity.ac?metaId='+ cf.metaId 
	    }
    });
    
    var reader = new Ext.data.JsonReader({
    idProperty: 'id',
    root: 'data'
	}, cf.fields);

// The new DataWriter component.
var writer = new Ext.data.JsonWriter({
    encode: true,
    writeAllFields: false
});

store2 = new Ext.data.Store({
    id: 'userdd',
    proxy: proxy,
    reader: reader,
    writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
    autoSave: true
});


/*
    store2 = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/jsonEntity.ac?metaId=' + cf.metaId,
    storeId: 'myStore',
    // reader configs
    root: 'data',
    idProperty: 'id',  
    fields: cf.fields  
});
*/
    store2.load();
    var grid;

	var MyDynGrid = function(cf)
    {
        Ext.apply(this, {
            renderTo: 'manage_entity_' + cf.metaId,
            ds: store2,
            cm: new MyDynColMode(cf),
            stripeRows: true,
            autoExpandColumn: 'id',
            height:500,
            width:940,
            title:'实体列表:' + cf.entityName,
            tbar : [{
                        id : 'newWindow',
                        text : '编辑',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           showeditPanel(grid,cf.metaId);
                        }
             }, {
                        id : 'refresh',
                        text : '刷新',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           store2.reload();
                        }
             },
             {
                    id : 'delData',
                    text : '删除',
                    //iconCls : 'edit',
                    handler : function() {
                        
                        if( confirm("真的要删除该条数据?") )
                        {
                            var rec = grid.getSelectionModel().getSelected();   
                            //var index = grid.getSelectionModel().getSelectedCell();
                            if (!rec) {
                                Ext.Msg.alert('信息', '请选择数据!');
                                return false;
                                
                            }
                            //var rec = grid.store.getAt(index[0]);
                            
                            
                            new Ext.data.Connection().request({
							    url: 'admin/delEntity.ac?metaId='+ cf.metaId + '&entityId=' + rec['id'],
							    //params: {id: selectedPersons\[0\].get("id")},
							    failure: function(){Ext.Msg.alert('失败', '删除失败!');},
							    success: function(){grid.store.remove(rec); Ext.Msg.alert('成功', '删除成功!');}
							});
                            

                        }
                    }
             }],
             listeners:{  
					//双击  
			       rowdblclick :function(grid,row){  
			           showeditPanel(grid,cf.metaId);
			       }  
			 }  
        });
        
        //this.relayEvents(this.store, ['destroy']);


        MyDynGrid.superclass.constructor.apply(this, arguments);
};
    
Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

grid = new MyDynGrid(cf);

    
//d.renderTo("manage_entity_" + config.metaId);
};


