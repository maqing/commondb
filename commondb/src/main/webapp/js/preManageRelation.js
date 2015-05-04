Ext.form.Field.prototype.msgTarget = 'side';

var in_metaId;
var in_entityId;
var in_tMetaId;
var in_rArr;
//var smdyn = new Ext.grid.CheckboxSelectionModel();
//var rnm =new Ext.grid.RowNumberer();
//var smdyn ;
//var rnm ;
var rEntityGrid;
//var metaGrid;
//var metaWindow;
var tEntityGrid;
var tEntityWindow;

//=================================================
var rStore;
var smdynR;

function createGrid(config){
	
	rStore = new Ext.data.JsonStore({
	    // store configs
	    autoDestroy: true,
	    url: 'jsonEntity.ac?metaId=' + config.metaId,
	    storeId: 'myStore',
	    // reader configs
	    root: 'data',
	    idProperty: 'id',  
	    fields: config.fields  
	});
	
	rStore.load();


	var rnmR =new Ext.grid.RowNumberer();
	smdynR = new Ext.grid.CheckboxSelectionModel({singleSelect :true});
	var headArr=new Array(rnmR,smdynR);
	//config.columns.push(rnmR);
	//config.columns.push(smdynR);
	var colArr=headArr.concat(config.columns);
	var colModelR = new Ext.grid.ColumnModel(colArr);
            
    var MyDynGrid = function(config)
    {
    	Ext.apply(this,{
        	id:"rEntity_" + config.metaId,
            renderTo: 'manage_entity_' + config.metaId,
        	ds: rStore,
            //cm: new MyDynColMode(config),
            //cm:deviceKpiInfo_cm,
            cm:colModelR,
        	//cm:config.columns,
            sm:smdynR,
            stripeRows: true,
            autoExpandColumn: 'id',
            height:350,
            width:930,
            title:config.mName,
            tbar : [{
                        id : 'newWindow',
                        text : '管理关联关系',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           var ssel = smdynR.getSelected();
                           if(ssel==null || ssel==''){  
						        Ext.Msg.alert('信息','请选择');  
						        return;  
						   }  
						   in_metaId=config.metaId;
                           in_entityId=ssel.get('id');
                           //rArr=ssel.get('rList');
                           //showMeta();
                           listREntity();
                        }
            		}]
        });
        MyDynGrid.superclass.constructor.apply(this, arguments);
	}
    
	Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

	rEntityGrid = new MyDynGrid(config);

};

//=============================================

var mStore = new Ext.data.JsonStore({
	// store configs
	autoDestroy: true,
	url: 'admin/listDb.ac',
	storeId: 'myStore',
	// reader configs
	root: 'data',
	idProperty: 'metaId',  
	fields: [  
	             {name: 'metaId', mapping: 'metaId'},  
	             {name: 'entityName'},  
	             {name: 'entityDesc'},   
	             {name: 'createTime'},  
	             {name: 'lastUpdateTime'}             
	         ]  
});
 
mStore.load();

var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});

var nm =new Ext.grid.RowNumberer();

var colModel = new Ext.grid.ColumnModel([
	nm,
    sm,
    {id:'metaId',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'entityName'},
    {header: "描述", width: 55, sortable: true, dataIndex: 'entityDesc'},
    {header: "更新时间", width: 80, sortable: true,  dataIndex: 'lastUpdateTime'},
    {header: "创建时间", width: 80, sortable: true,  dataIndex: 'createTime'}
]);
    
var metaGrid  =  new Ext.grid.GridPanel({
    id:'metaGrid',
    ds: mStore,
    cm:colModel,
    sm:sm,
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:350,
    width:440
    //title:'元数据列表'
});

var metaWindow = new Ext.Window({
	id:'meta_Window',
    disableMaskedElements:true,
    el: 'meta_win',  //前端放置当前js文件的页面中的div名称  
    title:'请选择元数据(高亮为已存在关联的元数据)',  
    width: 450,
    //height:100,
    layout: 'fit',
    autoHeight: true, 
    //autoWidth: true,
    closable: false,  
    closeAction: 'hide',  
    resizable: false,   
    items: metaGrid, //在window中加载编辑的form
    buttons: [{
        text:'下一步',
        handler: function(){
	       		var ssel = sm.getSelected();
				if(ssel==null || ssel==''){  
				    Ext.Msg.alert('信息','请选择');  
				        return;  
				}  
			    metaWindow.hide();
			   	in_tMetaId=ssel.get("metaId");
			    showGrid(ssel.get("metaId"));
        }
    },{
        text: '关闭',
        handler: function(){
        		metaWindow.hide();
        		sm.clearSelections();
        }
    }]

}); 
var rEntityStore;
function listREntity(){
	if(!rEntityStore || rEntityStore == null){
		rEntityStore = new Ext.data.JsonStore({
		    autoDestroy: true,
		    url: '../front/listREntity.ac',
			storeId: 'myData_Store',
			root: 'data',
		    idProperty: 'dataId',
			fields: [  
	                     {name: 'dataId', mapping: 'dataId'},  
	                     {name: 'meta2Id'},  
	                     {name: 'entity2Id'}
	                 ] 
	
		});
		rEntityStore.on('load',function(s,records){
			in_rArr=[];
			var myCount=0;
			rEntityStore.each(function(r){
				var rowArr=[];
				rowArr[0]=r.data.meta2Id;
				rowArr[1]=r.data.entity2Id;
				in_rArr[myCount]=rowArr;
				//alert(in_rArr[myCount][0]+"和"+in_rArr[myCount][1]);
				myCount++;
			});
			showMeta();
		});
	}
	rEntityStore.load({params: { metaId: in_metaId,entityId:in_entityId }});
	
}
function showMeta(){
		
	metaWindow.show(Ext.getBody());
	
	var tMetaIdArr=[];
	for(var i=0;i<in_rArr.length;i++){
		if(tMetaIdArr.indexOf(in_rArr[i][0])==-1)
		tMetaIdArr.push(in_rArr[i][0]); 
    }
	    
	metaWindow.on('activate',function(m){
	    var girdcount=0;
	    mStore.each(function(r){
	    	//alert(r.get('metaId')+"ok");
	        if(tMetaIdArr.indexOf(r.get('metaId'))!=-1){ 
	        	metaGrid.getView().getRow(girdcount).style.backgroundColor='#FFFF00';
	        	//alert(r.get('metaId')+"ok"+girdcount);
	        }else{
	        	//alert(r.get('metaId')+"no"+girdcount);
	        	metaGrid.getView().getRow(girdcount).style.backgroundColor='#FFFFFF';
	        	
	        }
	        girdcount=girdcount+1; 
	    });
	});
}

//=============================================
//smdyn.singleSelect =false;
var smdynT;
var tStore;
function showGrid(metaId){	
	
	in_tMetaId=metaId;
		
	var config = configMap['meta_'+in_tMetaId];
	//var config = configMap.meta_2;
	
	tStore = new Ext.data.JsonStore({
		id:'ssss',
		// store configs
		autoDestroy: true,
		url: 'jsonEntity.ac?metaId=' + config.metaId,
		storeId: 'myStore1',
		// reader configs
		root: 'data',
		idProperty: 'id',  
		fields: config.fields  
	});
	
	config.store= tStore;
	
	/*
	var MyDynGrid = function(mconfig)
	{
        Ext.apply(this, {
            id:"tEntity_" + mconfig.metaId,
		    ds: mconfig.store,
		    //cm: mconfig.columns,
		    sm:smdynT,
		    stripeRows: true,
		    autoExpandColumn: 'id',
            height:350,
            width:440,
            title:mconfig.mName
            
        });
        
        MyDynGrid.superclass.constructor.apply(this, arguments);
	};
	
	Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});
	
	tEntityGrid = new MyDynGrid(config);
	*/
	var rnmT =new Ext.grid.RowNumberer();
	smdynT = new Ext.grid.CheckboxSelectionModel({singleSelect :false});
	var headArr=new Array(rnmT,smdynT);
	var colArr=headArr.concat(config.columns);
	var colModelT = new Ext.grid.ColumnModel(colArr);

	
	tEntityGrid  =  new Ext.grid.GridPanel({
	    //id:"tEntity_" + config.metaId,
		id:'tEntity_grid',
	    ds: config.store,
	    cm: colModelT,
	    sm:smdynT,
	    stripeRows: true,
	    autoExpandColumn: 'id',
        height:350,
        width:440
        //title:config.mName
	});
    
        tStore.on('load',function(s,records){
    //tEntityWindow.on('activate',function(s){
    //tEntityGrid.on('afterrender',function(s){
        var recordArr = s.queryBy(function(record,id){
            return entityIdSelArr.indexOf(record.get('id'))!=-1;  
        },this).getRange();
        //var sm = tEntityGrid.getSelectionModel(); 
        if (smdynT){ 
            //smdynT.grid=tEntityGrid;
            smdynT.selectRecords(recordArr,true);//有问题？？
        }
            
    });
/*		
    tEntityGrid.on('afterrender',function(){
        alert('gg');
        var s = tEntityGrid.getStore();
        var sm = tEntityGrid.getSelectionModel(); 
        var recordArr = s.queryBy(function(record,id){
                return entityIdSelArr.indexOf(record.get('id'))!=-1;  
            }).getRange();
            
            if (sm){
                alert('gg');
                sm.selectRecords(recordArr,true);//有问题？？
                alert('gg2');
            }
    });
 */   
	//if(!tEntityWindow){ 
		
            	tEntityWindow = new Ext.Window({  
            			id:'tEntity_Window',
				        disableMaskedElements:true,
				        renderTo: Ext.getBody(),//'entity_win',  //前端放置当前js文件的页面中的div名称  
				        title:'请选择需要关联的记录——'+config.mName,  
				        width: 450, 
				        layout: 'fit',
				        autoHeight: true,  
				        closable: false,  
				        closeAction: 'close',  
				        resizable: false,   
				        items: tEntityGrid, //在window中加载编辑的form
				        tbar : [{
	                        id : 'refresh',
	                        text : '刷新',
	                        //iconCls: 'silk-add',
	                        //iconCls : 'edit',
	                        handler : function() {
				        		tStore.proxy.conn.url='jsonEntity.ac?metaId=' + config.metaId;
				        		tStore.load();
	                        }
	             		},{
	                        id : 'searchCharaWindow',
	                        text : '特征属性搜索',
	                        //iconCls: 'silk-add',
	                        //iconCls : 'edit',
	                        handler : function() {
	                           	searchCharaWindow(metaId,tStore);
	                        }
	             		},{
	                        id : 'searchHierWindow',
	                        text : '层级属性搜索',
	                        //iconCls: 'silk-add',
	                        //iconCls : 'edit',
	                        handler : function() {
	                        	searchHierWindow(metaId,tStore);
	                        }
	             		}],
				        buttons: [{
				            text: '上一步',
				            handler: function(){
				            	tEntityWindow.close();
				            	tStore.destroy();
				            	//smdynT.clearSelections(true);
				            	smdynT.destroy();
				            	tEntityGrid.destroy();
				            	showMeta(this);
				            }
				            },{
				            text:'提交',
				            handler:saveR
					       },{
				            text: '关闭',
				            handler: function(){
				            	tEntityWindow.close();
				            	tStore.destroy();
				            	//smdynT.clearSelections(true);
				            	smdynT.destroy();
				            	tEntityGrid.destroy();
				            	sm.clearSelections();
				            }
				        }]
					
					});
            
	//}
    
	tEntityWindow.items.clear();
	tEntityWindow.items.add(tEntityGrid);
	tEntityWindow.show(Ext.getBody());
	tStore.load();
	var entityIdSelArr=[];
    for(var k=0;k<in_rArr.length;k++){
    	if(in_rArr[k][0]==in_tMetaId){
		   	entityIdSelArr.push(in_rArr[k][1]);
		   	//alert(entityIdSelArr[k]);
    	}
	}
    

   
    

	/*
	tStore.on('load',function(m){
	    var girdcount=0;
	    tStore.each(function(r){
	    	
	        if(entityIdSelArr.indexOf(r.get('id'))!=-1){ 
	        	
	        	tEntityGrid.getView().getRow(girdcount).style.backgroundColor='#FFFF00';
	        	
	        }else{
	        	
	        	tEntityGrid.getView().getRow(girdcount).style.backgroundColor='#FFFFFF';
	        	
	        }
	        girdcount=girdcount+1; 
	    })
	});
	*/
};


function saveR(){
		var ssel = smdynT.getSelections();
		/*
		if(ssel.length==0){  
	        Ext.Msg.alert('信息','请选择');  
	        return;  
		}  
		*/
	    var tEntityIdArr=new Array();
	    for(var i=0;i<ssel.length;i++){
	    	tEntityIdArr[i]=ssel[i].get('id');
	    	//alert("tEntityIdArr:"+tEntityIdArr[i]+",tMetaId:"+in_tMetaId+",rMetaId:"+in_metaId+",rEntityId:"+in_entityId);
	    }
	    
	    var conn = new Ext.data.Connection();
	    conn.request({
	                url : '../front/updateREntity.ac',
	                params : {
	                	rMetaId: in_metaId,
	                	rEntityId: in_entityId,
	                    tMetaId: in_tMetaId,
	                    tEntityIdArr: tEntityIdArr
	                    
	                },
	                success : function(resp, opt) {
	                	//Ext.Msg.alert('成功','保存成功');
	                	tEntityWindow.close();
				        tStore.destroy();
				        //smdynT.clearSelections(true);
				        smdynT.destroy();
				        tEntityGrid.destroy();
	                    sm.clearSelections();
	                    rEntityStore.reload();
	                },
	                failure : function(resp, opt) {
	                    Ext.Msg.alert('失败','保存失败');
			        }
			});
		    
}


