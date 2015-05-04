//---------------------chara----------------------------------

// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';
var in_metaId;
var in_entityId;
var charaGroupGrid;
function rEntityChara(entityId){
	in_entityId=entityId;

	groupStore.load({params: { metaId: in_metaId,entityId:in_entityId }});

}

var groupStore;

function newCharaGroupGrid(metaId,response){
	in_metaId=metaId;

	if(!charaGroupGrid || charaGroupGrid == null){
	/*	
	groupStore = new Ext.data.GroupingStore({
	    autoDestroy: true,
	    url: 'admin/listCharaDataByEntity.ac',
		storeId: 'group_Store',
		sortInfo:{field: 'dataId', direction: "ASC"},
	    groupField:'charaDef',
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
	
	var groupColModel = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
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
	groupStore = new Ext.data.GroupingStore({
	    autoDestroy: true,
	    url: 'admin/listCharaDataByEntity.ac',
		storeId: 'group_Store',
		sortInfo:{field: 'dataId', direction: "ASC"},
	    groupField:'charaDef',
		reader : new Ext.data.JsonReader({  
	        root: 'data',
	        idProperty: 'dataId',  
	        fields: [  
	                     {name: 'dataId', mapping: 'dataId'},  
	                     {name: 'dataName'},  
	                     {name: 'charaDef',convert: function(v, rec) {
	    				 											return v.charaName;
	    				 }}
	                 ] 
		})
	});	
	var groupColModel = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
	    {id:'dataName',header: "名称", width: 250, sortable: true, locked:false, dataIndex: 'dataName'}
		,{header: "特征属性", width: 200, sortable: true, dataIndex: 'charaDef'}
	]);
	
	charaGroupGrid = new Ext.grid.GridPanel({
	    //renderTo: "manage_def",
		id:'chara_GroupGrid',
	    ds: groupStore,
	    cm:groupColModel,
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
	        hideGroupedColumn : true                        //隐藏分组列  
		}),
	    //title:'特征属性列表',
	    tbar : [{
	                id : 'refreshChara',
	                text : '刷新特征属性',
	                //iconCls: 'silk-add',
	                //iconCls : 'edit',
	                handler : function() {
	                   groupStore.load({params: { metaId: in_metaId,entityId:in_entityId }});
	                }
	     }]
	});
	createForm(response);
	}
}
        