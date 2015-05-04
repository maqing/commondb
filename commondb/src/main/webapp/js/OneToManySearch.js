//---------------------chara----------------------------------

// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';
var in_metaId;
var defStoreS;
var in_charaIdArrS;


function searchCharaWindow(metaId){

	in_metaId=metaId;
	
	if(!defStoreS || defStoreS == null){
		defStoreS = new Ext.data.JsonStore({
		    // store configs
		    autoDestroy: true,
		    url: 'admin/listCharaDefByMeta.ac',
		    storeId: 'def_Store2',
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
		         
		defStoreS.load({params: { metaId: metaId }}); 
		
		
		defStoreS.on('load',function(s,records){
			var charaIdArr=[];
			var sCount=0
			defStoreS.each(function(r){
				charaIdArr[sCount]=r.get('charaId')
				sCount++;
			});
			in_charaIdArrS=charaIdArr;
			newGroup(charaIdArr,metaId);
		});
	}else{
		newGroup(in_charaIdArrS,metaId);
	}
}

var groupStoreS;
var charaGroupGridS;
var searchCharaWin;
var groupSmS;
function newGroup(charaIdArr,metaId){
	
	if(!charaGroupGridS || charaGroupGridS == null){
		groupStoreS = new Ext.data.GroupingStore({
		    autoDestroy: true,
		    url: 'admin/listCharaDataByCharaId.ac',
		    storeId: 'group_Store2',
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
		
		groupStoreS.load({params: { charaIdArr: charaIdArr }});
	
		groupSmS = new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		
		var groupColModel = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
		    groupSmS,
		    {id:'dataName',header: "名称", width: 250, sortable: true, locked:false, dataIndex: 'dataName'},
			{header: "特征属性", width: 200, sortable: true, dataIndex: 'charaDef'}
		]);
		charaGroupGridS = new Ext.grid.GridPanel({
		    //renderTo: "manage_def",
			id:'chara_GroupGrid2',
		    ds: groupStoreS,
		    cm:groupColModel,
		    sm:groupSmS,
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
		        hideGroupedColumn : false                          //隐藏分组列  
			})
		    
		});

	}
	if(!searchCharaWin || searchCharaWin == null){	
		searchCharaWin = new Ext.Window({ 
			id:'searchCharaWin_Window',
			renderTo: Ext.getBody(),  //前端放置当前js文件的页面中的div名称  
			title:'请选择特征属性',  
			width: 600,
			layout: 'fit',
			autoHeight: true,  
			closable: true,  
			closeAction: 'hide',//close  
			resizable: false,   
			items: charaGroupGridS ,//在window中加载编辑的form  
			callback : null,
		    params : null,
		    plain:true,
		    modal : true,
		    buttons: [{
				            text:'搜索',
				            handler:searchByChara
					  }]
		    
		});
	}
	
	searchCharaWin.show();
}

function searchByChara()
{
	
	var sel = groupSmS.getSelections();
	var charaArr=[];
	for(var i=0;i<sel.length;i++){
		var rowArr=[];
		rowArr[0]=sel[i].get('charaId');
		rowArr[1]=sel[i].get('dataId');
		charaArr[i]=rowArr;
		//alert(charaArr[i][0]+"和"+charaArr[i][1]);
	}
	
	//store2.proxy.conn.url='admin/jsonEntityByChara.ac?metaId='+in_metaId+'&charaArr='+charaArr;
	//store2.load();
	store2.proxy.conn.url='admin/jsonEntityByChara.ac';
	store2.load({params: { metaId: in_metaId,charaArr:charaArr }}); 
    searchCharaWin.hide();

}

