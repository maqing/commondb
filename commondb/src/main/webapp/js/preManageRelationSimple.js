Ext.form.Field.prototype.msgTarget = 'side';

var in_metaId4;
var in_entityId;
var in_tMetaId;
var tEntityGrid;
var tEntityWindow;
var in_rArr;
var rEntityStore;
function rMeta(metaId,entityId){
	in_metaId4=metaId;
	in_entityId=entityId;
	listREntity();
}
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
	rEntityStore.load({params: { metaId: in_metaId4,entityId:in_entityId }});
	
}
var mStore;
var metaGrid;
var sm;

function createMetaGrid(){
	mStore = new Ext.data.JsonStore({
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
	
	sm = new Ext.grid.RowSelectionModel({singleSelect:true});
	
	var nm =new Ext.grid.RowNumberer();
	
	var colModel = new Ext.grid.ColumnModel([
		nm,
	    {id:'metaId',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'entityName'},
	    {header: "描述", width: 120, sortable: true, dataIndex: 'entityDesc'},
	    {header: "更新时间", width: 80, sortable: true,  dataIndex: 'lastUpdateTime'},
	    {header: "创建时间", width: 80, sortable: true,  dataIndex: 'createTime'}
	]);
	    
	metaGrid  =  new Ext.grid.GridPanel({
	    id:'metaGrid',
	    ds: mStore,
	    cm:colModel,
	    sm:sm,
	    stripeRows: true,
	    //autoExpandColumn: 'name',
	    height:350,
	    width:500,
	    //title:'关联实体', 
	    titleAlin:'left',
	    frame:true,
	    view: new Ext.grid.GridView(),
	    tbar : [{
		                id : 'rEntity_win',
		                text : '关联实体',
		                //iconCls: 'silk-add',
		                //iconCls : 'edit',
		                handler : selMeta
		}],
		listeners:{  
					//双击  
			       rowdblclick :selMeta
		}  
		/*
	    listeners:{  
					//双击  
			       rowdblclick :function(grid,row){  
						var ssel = sm.getSelected();
						if(ssel==null || ssel==''){  
						Ext.Msg.alert('信息','请选择');  
				        	return;  
						}  
						showGrid(ssel.get("metaId"));
			       }  
		}  
		*/
	});
}
function selMeta(){
	var ssel = sm.getSelected();
	if(ssel==null || ssel==''){  
	Ext.Msg.alert('信息','请选择');  
    	return;  
	}  
	showGrid(ssel.get("metaId"));
}

function showMeta(){

	var tMetaIdArr=[];
	for(var i=0;i<in_rArr.length;i++){
		if(tMetaIdArr.indexOf(in_rArr[i][0])==-1)
		tMetaIdArr.push(in_rArr[i][0]); 
    }
	    
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

}

//=============================================

var smdynT;
var tStore;

function showGrid(metaId){	
	
	in_tMetaId=metaId;
		
	var config = configMap['meta_'+in_tMetaId];
	//var config = configMap.meta_2;
	
	tStore = new Ext.data.JsonStore({
		id:'t_Store',
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
        var recordArr = s.queryBy(function(record,id){
            return entityIdSelArr.indexOf(record.get('id'))!=-1;  
        },this).getRange();
        
        if (smdynT){ 
            smdynT.selectRecords(recordArr,true);//有问题？？
        }
            
    });

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
	tEntityWindow.show(Ext.getBody());
	tStore.load();
	var entityIdSelArr=[];
	if(in_rArr!=null&&in_rArr!=''){
	    for(var k=0;k<in_rArr.length;k++){
	    	if(in_rArr[k][0]==in_tMetaId){
			   	entityIdSelArr.push(in_rArr[k][1]);
			   	//alert(entityIdSelArr[k]);
	    	}
		}
	}
};

var entityArr='';
function saveR(){
	var ssel = smdynT.getSelections();
	if(in_entityId!=null&&in_entityId!=''){
	    var tEntityIdArr=new Array();
	    for(var i=0;i<ssel.length;i++){
	    	tEntityIdArr[i]=ssel[i].get('id');
	    	//alert("tEntityIdArr:"+tEntityIdArr[i]+",tMetaId:"+in_tMetaId+",rMetaId:"+in_metaId4+",rEntityId:"+in_entityId);
	    }
	    
	    var conn = new Ext.data.Connection();
	    conn.request({
	                url : '../front/updateREntity.ac',
	                params : {
	                	rMetaId: in_metaId4,
	                	rEntityId: in_entityId,
	                    tMetaId: in_tMetaId,
	                    tEntityIdArr: tEntityIdArr
	                    
	                },
	                success : function(resp, opt) {
	                	Ext.Msg.alert('成功','保存成功');
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
		}else{
			
		    for(var i=0;i<ssel.length;i++){
		    	
		    	if(entityArr.length > 0){
		    		entityArr += '&';
                }
		    	entityArr += 'entityArr='+in_tMetaId+','+ssel[i].get('id');
		    	
		    }
		    //alert(entityArr);
		    tEntityWindow.close();
	        tStore.destroy();
	        //smdynT.clearSelections(true);
	        smdynT.destroy();
	        tEntityGrid.destroy();
            sm.clearSelections();
		}
}


