Ext.form.Field.prototype.msgTarget = 'side';

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
    url: 'admin/jsonEntity.ac?metaId=2',
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
	        height:350,
	        width:1000,
	        title:'实体列表',
	        tbar : [{
	                    id : 'newWindow',
	                    text : '编辑',
	                    //iconCls: 'silk-add',
	                    //iconCls : 'edit',
	                    handler : function() {
	                       //showeditPanel(this);
	                    }
	         }, {
	                    id : 'refresh',
	                    text : '刷新',
	                    //iconCls: 'silk-add',
	                    //iconCls : 'edit',
	                    handler : function() {
	                       //store.load();//showeditPanel(this);
	                    }
	         },
	         {
	                id : 'delMetaData',
	                text : '删除',
	                //iconCls : 'edit',
	                handler : function() {
	                }
	         }]
	    });
        MyDynGrid.superclass.constructor.apply(this, arguments);
}
    
Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

grid = new MyDynGrid(config);

    
//d.renderTo("manage_entity_" + config.metaId);
};


