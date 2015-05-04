
Ext.form.Field.prototype.msgTarget = 'side';
var configMap = {};
var in_metaId;
var in_entityId;
var in_tMetaId;
Ext.override(Ext.Window,{
    disabledMaskedControls : [],
    beforeShow : function(){
        
        delete this.el.lastXY;
        delete this.el.lastLT;
        if(this.x === undefined || this.y === undefined){
            var xy = this.el.getAlignToXY(this.container, 'c-c');
            var pos = this.el.translatePoints(xy[0], xy[1]);
            this.x = this.x === undefined? pos.left : this.x;
            this.y = this.y === undefined? pos.top : this.y;
        }
        this.el.setLeftTop(this.x, this.y);
 
        if(this.expandOnShow){
            this.expand(false);
        }
 
        if(this.modal){
            Ext.getBody().addClass("x-body-masked");
            this.mask.setSize(Ext.lib.Dom.getViewWidth(true), Ext.lib.Dom.getViewHeight(true));
            this.mask.show();
            if (this.disableMaskedElements === true) {
                var db = Ext.fly(document.body);
                var els = db.select("input, select, a, button");
                els.each(function(el){
                    if (!el.dom.disabled && el.isVisible() && el.findParent('div[id='+this.getId()+']') === null) {
                        el.dom.disabled = true;
                        this.disabledMaskedControls.push(el.dom);
                    }
                }, this);
            }
        }
 
 
    },
    afterHide : function(){
        Ext.each(this.disabledMaskedControls, function(el) {
            el.disabled = false;
        });
        this.disabledMaskedControls = [];
 
        this.proxy.hide();
        if(this.monitorResize || this.modal || this.constrain || this.constrainHeader){
            Ext.EventManager.removeResizeListener(this.onWindowResize, this);
        }
        if(this.modal){
            this.mask.hide();
            Ext.getBody().removeClass("x-body-masked");
        }
        if(this.keyMap){
            this.keyMap.disable();
        }
        this.fireEvent("hide", this);
    }
 
});
//=============================================
//构建Store
var store = new Ext.data.JsonStore({
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
         

//载入  
store.load();  
  

// the DefaultColumnModel expects this blob to define columns. It can be extended to provide
// custom or reusable ColumnModels
var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
var colModel = new Ext.grid.ColumnModel([
	new Ext.grid.RowNumberer(),
    sm,
    {id:'metaId',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'entityName'},
    {header: "描述", width: 55, sortable: true, dataIndex: 'entityDesc'},
    {header: "更新时间", width: 80, sortable: true,  dataIndex: 'lastUpdateTime'},
    {header: "创建时间", width: 80, sortable: true,  dataIndex: 'createTime'}
]);
    
var metaGrid;
var metaWindow;
var showSeletMetaPanel = function (btn)
{

    if(!metaWindow)  
        {  
            if(!metaGrid)
            {
                  

            metaGrid  =  new Ext.grid.GridPanel({
            
                id:'metaGrid',
                ds: store,
                cm:colModel,
                sm:sm,
                stripeRows: true,
                //autoExpandColumn: 'name',
                height:350,
                width:440
                //title:'元数据列表'
            });
            }
           
            win = metaWindow = new Ext.Window({  
                disableMaskedElements:true,
                el: 'meta_select',  //前端放置当前js文件的页面中的div名称  
                title:'请选择元数据',  
                //width: 450,  
                layout: 'fit',
                autoHeight: true,  
                closable: true,  
                closeAction: 'hide',  
                resizable: false,   
                items: metaGrid, //在window中加载编辑的form
                buttons: [{
                    text:'下一步',
                    handler: function(){
                   
						var sm = metaGrid.getSelectionModel();
						
						var m = sm.getSelections();
					    var metaIdArr = [];
					    
					    for(var i = 0, len = m.length; i < len; i++){               
					           metaIdArr.push(m[i].get("metaId"));   
	                           
					    }   
					    win.hide();
	                       
	                    createGrid2(metaIdArr);
                        
                    }
                },{
                    text: '关闭',
                    handler: function(){
                    win.hide();
                    }
                }]

            });           
              
        }
       
		metaWindow.show(Ext.getBody());
}


//=============================================

var MyDynStore = function(config) 
{

    Ext.apply(this, {
	    // store configs
	    autoDestroy: true,
	    url: 'jsonEntity.ac?metaId=' + config.metaId,
	    storeId: 'myStore',
	    // reader configs
	    root: 'data',
	    idProperty: 'id',  
	    fields: config.fields  
    });
    MyDynStore.superclass.constructor.apply(this, arguments);
 }

Ext.extend(MyDynStore, Ext.data.JsonStore,{});


var smdyn = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
var smdyn2 = new Ext.grid.CheckboxSelectionModel({singleSelect:false});

var rnm =new Ext.grid.RowNumberer();
/*
var MyDynColMode = function(config)
{
   
    var col = [rnm,smdyn];
    var d = col.concat(config.columns);
    config.columns = d;
    Ext.apply(this, {
    	//columns:col.concat(config.columns)
    	//columns:Ext.util.JSON.decode("["+"new Ext.grid.RowNumberer()"+","+"new Ext.grid.CheckboxSelectionModel()"+","+config.columns+"]")
        
    });
    MyDynColMode.superclass.constructor.apply(this, arguments);
    //alert(col[0]);
}

Ext.extend(MyDynColMode, Ext.grid.ColumnModel, {});
*/

function createGrid(config)
{
     
   var store2 = new Ext.data.JsonStore({
	    // store configs
	    autoDestroy: true,
	    url: 'jsonEntity.ac?metaId=' + config.metaId,
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
            //cm: new MyDynColMode(config),
            //cm:deviceKpiInfo_cm,
            //cm:new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),smdyn,config.columns]),
            sm:smdyn,
            stripeRows: true,
            autoExpandColumn: 'id',
            height:350,
            width:930,
            title:config.mName,
            tbar : [{
                        id : 'newWindow',
                        text : '创建关联关系',
                        //iconCls: 'silk-add',
                        //iconCls : 'edit',
                        handler : function() {
                           showSeletMetaPanel(this);
                           var ssel = smdyn.getSelected();
						   in_metaId=config.metaId;
                           in_entityId=ssel.get('id');
                        }
             }]
        });
        MyDynGrid.superclass.constructor.apply(this, arguments);
}
    
Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

grid = new MyDynGrid(config);


//d.renderTo("manage_entity_" + config.metaId);
};

var entityListWin;
var panel;

function createGrid2(metaIdArr)
{
	 in_tMetaId=metaIdArr[0];
     var MyDynGrid = function(config)
        {
            Ext.apply(this, {
                id:"e_sel_" + config.metaId,
                ds: config.store,
                //cm: 
                sm:smdyn2,
                stripeRows: true,
                autoExpandColumn: 'id',
                height:350,
                width:400,
                title:config.mName
                
            });
            
            MyDynGrid.superclass.constructor.apply(this, arguments);
       };
       Ext.extend(MyDynGrid, Ext.grid.GridPanel, {});

		if(!panel || panel == null)
		{       
		       panel = new Ext.Panel({
		                id:'main-panel',
		                //renderTo:'entity_list',
		                baseCls:'x-plain',
		                //renderTo: Ext.getBody(),
		                layout:'table',
		                layoutConfig: {columns:2},
		                // applied to child components
		                defaults: {frame:true, width:400, height: 400},
		                items:[],
		                itemId:[]
		            });
		}
		
		for(var j = 0 ; j < panel.itemId.length ; j++)
		{
		    panel.remove(panel.getComponent('e_sel_' + panel.itemId[j]));
		}
		
		panel.doLayout();
		//panel.reload();

    for(var i = 0 ; i < metaIdArr.length ; i ++)
    {
        var exist = false;
        for(var j = 0 ; j < panel.itemId.length ; j++)
        {
            if((panel.itemId[j] + "") == ("" + metaIdArr[i]))
            {
                //exist = true;
            }
        }
        if(!exist)
        {
	        var config = configMap['meta_' + metaIdArr[i]];
	        
	        
	        //config.columns = mycolumns;
	               
	        var store2 = new Ext.data.JsonStore({
		        // store configs
		        autoDestroy: true,
		        url: 'jsonEntity.ac?metaId=' + config.metaId,
		        storeId: 'myStore',
		        // reader configs
		        root: 'data',
		        idProperty: 'id',  
		        fields: config.fields  
	        });
	        store2.load();
	        config.store= store2;
	        var grid;
	        grid = new MyDynGrid(config);
	        //grid.getColumnModel().columns = grid.getColumnModel().columns.concat(confg.columns);
	        panel.items.add(grid);
	        panel.itemId.push(metaIdArr[i]);
    
        }
                
       
    }
    
	if(!entityListWin)
	{
	    entityListWin = new Ext.Window({  
	                disableMaskedElements:true,
	                el: 'entity_list',  //前端放置当前js文件的页面中的div名称  
	                title:'请选择需要关联的记录',  
	                //width: 820, 
	                layout: 'fit',
	                autoHeight: true,  
	                closable: true,  
	                closeAction: 'hide',  
	                resizable: false,   
	                items: panel, //在window中加载编辑的form
	                buttons: [{
	                    text:'提交',
	                    handler:saveR
				       },{
	                    text: '关闭',
	                    handler: function(){
	                        entityListWin.hide();
	                    }
	                }]
	
	            });     
	}
	entityListWin.items.clear();
	entityListWin.items.add(panel);
	entityListWin.show();
	//d.renderTo("manage_entity_" + config.metaId);
};

function saveR(){
		var sel = smdyn2.getSelections();
		if(sel.length==0){  
	        Ext.Msg.alert('信息','请选择');  
	        return;  
    	}  
	    var tEntityIdArr=new Array();
	    for(var i=0;i<sel.length;i++){
	    	tEntityIdArr[i]=sel[i].get('id');
	    	//alert("tEntityIdArr:"+tEntityIdArr[i]+",tMetaId:"+in_tMetaId+",rMetaId:"+in_metaId+",rEntityId:"+in_entityId);
	    }
	    
	    var conn = new Ext.data.Connection();
	    conn.request({
	                url : '../front/saveREntity.ac',
	                params : {
	                	rMetaId: in_metaId,
	                	rEntityId: in_entityId,
	                    tMetaId: in_tMetaId,
	                    tEntityIdArr: tEntityIdArr
	                    
	                },
	                success : function(resp, opt) {
	                	Ext.Msg.alert('成功','保存成功');
	                    entityListWin.hide();
	                },
	                failure : function(resp, opt) {
	                    Ext.Msg.alert('失败','保存失败');
		            }
			});
	                	
	                	
	       entityListWin.hide();
                    
}
