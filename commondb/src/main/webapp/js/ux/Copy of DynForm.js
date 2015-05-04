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

Example.children = [
{id:1, text:'Americas', children:[
{id:2, text:'USA', leaf:true}
,{id:3, text:'Canada', leaf:true}
,{id:4, text:'Mexico', leaf:true}
,{id:5, text:'Colombia', leaf:true}
]}
,{id:6, text:'Europe', children:[
{id:7, text:'United Kingdom', leaf:true}
,{id:8, text:'France', leaf:true}
,{id:9, text:'Germany', leaf:true}
,{id:10, text:'Slovakia', leaf:true}
]}
,{id:11, text:'Asia', children:[
{id:12, text:'China', leaf:true}
,{id:13, text:'Japan', leaf:true}
,{id:14, text:'India', leaf:true}
,{id:15, text:'South Korea', leaf:true}
]}
,{id:16, text:'Africa', children:[
{id:17, text:'Egypt', leaf:true}
,{id:18, text:'South Africa', leaf:true}
,{id:19, text:'Ghana', leaf:true}
,{id:20, text:'Agleria', leaf:true}
]}
,{id:21, text:'Australia',children:[
{id:22, text:'Australia', leaf:true}
,{id:23, text:'New Zealand', leaf:true}
,{id:24, text:'Guinea', leaf:true}
,{id:25, text:'Indonesia', leaf:true}
]}
];

var MyDynForm = function(config) 
    {
    //Reusable config options here
    Ext.apply(this, {
        fileUpload: true,
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
          items: [{
            xtype:'fieldset',
            collapsible: true,
            title: '直观属性',
            autoHeight:true,
            defaults: {width: 210},
            //collapsed: true,
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.picArray.length ; j++) {
                items.push({
                    xtype: 'fileuploadfield',
		            emptyText: '请选择图片',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'upload-icon'
		            },
	                fieldLabel: config.picArray[j].attrName,
	                    name: 'p_' + config.picArray[j].attrId    
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
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.hierArray.length ; j++) {
                items.push({
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




