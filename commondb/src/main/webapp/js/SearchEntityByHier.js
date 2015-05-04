//---------------------chara----------------------------------

// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';
var in_metaId3;
var searchHierWin;
var config;


function searchHierWindow(in_metaId3,dynStore)
{
    new Ext.data.Connection().request({
    url: 'admin/fetchMetaAttrs.ac',
    params: { metaId: in_metaId3 },
    //failure: requestFailed,
    success: function(response, options){
        initConfig(in_metaId3,response,dynStore);
        
    	}
    });
}
function initConfig(metaId,response,dynStore)
{   

    var res = Ext.util.JSON.decode(response.responseText); 
    
     
    if(res.success)
    {

       config = { title:res.data.entityName,
            picArray:res.data.picAttr, 
            hierArray:res.data.hierAttr, 
            charaArray:res.data.charaAttr,
            descArray:res.data.descAttr};
      searchHierWindow2step(metaId,dynStore);
    }

   //loadmask.hide();
};

function searchHierWindow2step(metaId,dynStore){
	in_metaId3=metaId;

	if(!searchHierWin || searchHierWin == null){	
		searchHierWin = new Ext.Window({ 
			id:'searchHierWin_Window',
			renderTo: Ext.getBody(),  //前端放置当前js文件的页面中的div名称  
			title:'请选择层级属性',  
			width: 600,
			layout: 'fit',
			autoHeight: true,  
			closable: true,  
			closeAction: 'hide',//close  
			resizable: false,   
			items :(
		            function() {
		                var items = [];
		                for(var j = 0; j < config.hierArray.length ; j++) {
		                	 nav = new ConceptsPanel({
		                        id:'my_treepanel_' + config.hierArray[j].attrId+'s',
		                        isFormField:true
		                        ,xtype:'treepanel'
		                        ,autoHeight :true
		                        ,autoScroll:true
		                        ,border:true
		                        ,bodyStyle:'background-color:white;border:1px solid #B5B8C8'
		                        ,rootVisible:true
		                        //,anchor:'-24 -60'
		                        ,root:config.hierArray[j].treeValue /*{
		                            nodeType:'async'
		                            ,text:'root'
		                            ,id:'root'
		                            ,expanded:true
		                            ,uiProvider:false
		                            ,children:config.hierArray[j].treeValue//.children//Example.children
		                        }*/,
		                        fieldLabel: config.hierArray[j].attrName,
		                        attrId : config.hierArray[j].attrId
		                        
		                       
		                    });
		    	
		                    
		                    items.push(nav);
		                    
		                    items.push({
		                        xtype: 'hidden',

		                        anchor:'95%',

		                        
		                        name: 'h_' + config.hierArray[j].attrId,    
		                        id: 'h_' + config.hierArray[j].attrId    
		                    });
		                    
		                    
		                }
		                return items;
		            }).createDelegate(this)(),
			callback : null,
		    params : null,
		    plain:true,
		    modal : true,
		    buttons: [{
				            text:'搜索',
				            //handler:searchByHier
				            handler:function (){
				            	
				            	var myHier=config.hierArray;
				                var hierarchyArr = '';
				            	for(var j = 0; j < myHier.length ; j++) {
				            		selNodes1 = Ext.getCmp('my_treepanel_' + myHier[j].attrId+'s').getChecked();
				            		Ext.each(selNodes1, function(node){
				                        if(hierarchyArr.length > 0){
				                            hierarchyArr += '&';
				                        }
				                        hierarchyArr += 'hierarchyArr='+myHier[j].attrId+','+node.id;
				               		 });
				            	}
				            	//alert(hierarchyArr);
				            	
				            	//store2.proxy.conn.url='admin/jsonEntityByChara.ac?metaId='+in_metaId3+'&charaArr='+charaArr;
				            	//store2.load();
				            	dynStore.proxy.conn.url='admin/jsonEntityByHierarchy.ac?metaId='+in_metaId3+'&'+hierarchyArr;
				            	//store2.load({params: { metaId: in_metaId3,hierarchyArr:hierarchyArr }}); 
				            	dynStore.load();
				            	searchHierWin.hide();

				            }
					  }]
		    
		});
	}
	for(var j = 0; j < this.config.hierArray.length ; j++) {
          var treepanel = Ext.getCmp('my_treepanel_' + this.config.hierArray[j].attrId+'s');
          if(searchHierWin){
        	  treepanel.getRootNode().expand();
          }

    }
	searchHierWin.show();
}

function searchByHier(dynStore)
{
	
	var myHier=config.hierArray;
    var hierarchyArr = '';
	for(var j = 0; j < myHier.length ; j++) {
		selNodes1 = Ext.getCmp('my_treepanel_' + myHier[j].attrId+'s').getChecked();
		Ext.each(selNodes1, function(node){
            if(hierarchyArr.length > 0){
                hierarchyArr += '&';
            }
            hierarchyArr += 'hierarchyArr='+myHier[j].attrId+','+node.id;
   		 });
	}
	//alert(hierarchyArr);
	
	//store2.proxy.conn.url='admin/jsonEntityByChara.ac?metaId='+in_metaId3+'&charaArr='+charaArr;
	//store2.load();
	dynStore.proxy.conn.url='admin/jsonEntityByHierarchy.ac?metaId='+in_metaId3+'&'+hierarchyArr;
	//store2.load({params: { metaId: in_metaId3,hierarchyArr:hierarchyArr }}); 
	dynStore.load();
	searchHierWin.hide();

}



//loadmask.show();


