 var msg = function(title, msg){
        Ext.Msg.show({
            title: title,
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: Ext.Msg.INFO,
            buttons: Ext.Msg.OK
        });
    };
    
   
    
    
    
    
Ext.ns('Example');
Example.children = [
{id:1, text:'中国', children:[
{id:2, text:'北京', children:[{id:19, text:'海淀', children:[{id:20, text:'上地', leaf:true, checked:true}]}]}
,{id:3, text:'上海', leaf:true}
,{id:4, text:'重庆', leaf:true}
,{id:5, text:'湖南', children:[{id:6, text:'长沙', leaf:true,checked:false}]}
,{id:8, text:'湖北', children:[{id:9, text:'武汉', leaf:true,checked:false},{id:10, text:'荆州', leaf:true,checked:false}]}
]}
];

 var chooser;
 var g_attrId;
    function insertImage(data, attrId){
       
        Ext.getDom('image' + g_attrId).src = data.url;
        
       
    };

    
    
var MyDynForm = function(config) 
    {
        

    //Reusable config options here
    Ext.apply(this, {
        fileUpload: true,
        autoScroll:true,
        frame: true,
        bodyStyle: 'padding: 10px 10px 0 10px;',
        defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
        },

        id: 'new_entity_form_' + config.metaId,
        labelWidth: 75, // label settings here cascade unless overridden
        url:'/commondb/admin/createEntity.ac?metaId=' + config.metaId,
        renderTo: 'new_entity_' + config.metaId,
        frame:true,
        title: '新建实体-' + config.title,
        
        width: 550,
          items: [ {
            xtype:'fieldset',
            collapsible: true,
            title: '直观属性',
            autoHeight:true,
            defaults: {width: 250},
            //collapsed: true,
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.picArray.length ; j++) {
                items.push({
                    xtype: 'fileuploadfield',
		            emptyText: '请选择图片',
		            buttonText: '',
                    anchor:'95%',
		            buttonCfg: {
		                iconCls: 'upload-icon'
		            },
	                fieldLabel: config.picArray[j].attrName,
	                    name: 'p_' + config.picArray[j].attrId    
	            });
                
                items.push({
		            layout:'column',
		            items:[{
                         columnWidth:.35,
                            xtype:'label',
                            text: '从服务器：'
                        
                    },
                     {
		              columnWidth:.5,
		             xtype:'box'
		             ,anchor:''
		             ,isFormField:true
		             ,fieldLabel:'Image'
		             ,autoEl:{
		             tag:'div', 
                     children:[{
                     id:'image' + config.picArray[j].attrId,
		             tag:'img'
		             ,qtip:'You can also have a tooltip on the image'
		             ,src: '/commondb/images/default.gif'
		             },{
		             tag:'div'
		             ,style:'margin:0 0 4px 0'
		             ,html:''
		             }]
		             }
		             },{
		                 columnWidth:.15,
	                    xtype:'button',
                        attrId:config.picArray[j].attrId,
                        handler:function ()
					    {
					        
					        if(!chooser){
					            chooser = new ImageChooser({
					                url:'/commondb/admin/listPics.ac',
					                width:515,
					                height:350,
					                attrId:this.attrId
					            });
					        }
                            g_attrId = this.attrId;
                            
					        chooser.attrId = this.attrId;
                            
					        chooser.show(this.getEl(), insertImage);
					    },
                        style:'padding:4px 4px 4px 4px',
	                    anchor:'95%'
		                
                    }]
                });
            }
            return items;
            }).createDelegate(this)()
        },{
            xtype:'fieldset',
            title: '描述属性',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'combo',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.descArray.length ; j++) {
	            var descAttrStore = new Ext.data.JsonStore({
				    // store configs
                    autoLoad:true,
				    autoDestroy: true,
				    url: 'admin/listDescAttrData.ac',
                    baseParams:{metaId : config.metaId, columnName:'d_' + config.descArray[j].attrId},
				    // reader configs
				    root: 'data',
				    fields: [  
				                 {name: 'd_' + config.descArray[j].attrId, type:'string'}         
				             ]  
			    });
                descAttrStore.load();
                //descAttrStore.load({params : });
                
                items.push({
                    pageSize:15,
			        displayField:'d_' + config.descArray[j].attrId,
                    minchars:0,
			        triggerAction: 'all',
                    enableKeyEvents :true,
			        emptyText:'',
			        selectOnFocus:true,
                    mode:'local',
	                store: descAttrStore,
	              
	                fieldLabel: config.descArray[j].attrName,
	                    name: 'd_' + config.descArray[j].attrId    
	                });
            }
            return items;
            }).createDelegate(this)()
        
        },
        {
            xtype:'fieldset',
            title: '层级属性',
            collapsible: true,
            //autoHeight:true,
            defaults: {width: 360},
            defaultType: 'treepanel',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.hierArray.length ; j++) {
            	
                items.push({
                    isFormField:true
                    ,xtype:'treepanel'
        				,height:120
        				,autoScroll:true
        				,border:true
        				,bodyStyle:'background-color:white;border:1px solid #B5B8C8'
        				,rootVisible:true
        				//,anchor:'-24 -60'
                    ,root:{
		                nodeType:'async'
		                ,text:'root'
		                ,id:'root'
		                ,expanded:true
		                ,uiProvider:false
		                ,children:config.hierArray[j].treeValue.children//Example.children
	                },
	                fieldLabel: config.hierArray[j].attrName,
                    name: 'h_' + config.hierArray[j].attrId    
                });
                
            }
            return items;
        }).createDelegate(this)()
        },
        {
            xtype:'fieldset',
            title: '特征属性',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'combo',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.charaArray.length ; j++) {
                var charaAttrStore = new Ext.data.JsonStore({
                    // store configs
                    autoLoad:true,
                    autoDestroy: true,
                    url: 'admin/listDescAttrData.ac',
                    baseParams:{metaId : config.metaId, columnName:'c_' + config.charaArray[j].attrId},
                    // reader configs
                    root: 'data',
                    fields: [  
                                 {name: 'c_' + config.charaArray[j].attrId, type:'string'}         
                            ]  
                });
                charaAttrStore.load();
                //descAttrStore.load({params : });
                
                items.push({
                    pageSize:15,
                    displayField:'c_' + config.descArray[j].attrId,
                    minchars:0,
                    triggerAction: 'all',
                    enableKeyEvents :true,
                    emptyText:'',
                    selectOnFocus:true,
                    mode:'local',
                    store: charaAttrStore,
                  
                    fieldLabel: config.charaArray[j].attrName,
                        name: 'c_' + config.charaArray[j].attrId    
                    });
            }
            return items;
            }).createDelegate(this)()
        }]

        });
    this.metaId = config.metaId;
    MyDynForm.superclass.constructor.apply(this, arguments);
};
// MyPanel Extends Ext.Panel
Ext.extend(MyDynForm, Ext.FormPanel, {

    myNewFunction: function() {
        //alert(this.metaId);
    },

    onRender: function() {
        MyDynForm.superclass.onRender.apply(this, arguments);
        this.myNewFunction();
    }
});




