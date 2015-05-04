var metaForm;
var test = new  Ext.FormPanel();
function tof(val) {
var t;
switch(val) {
    case null: t = "null"; break;
    case undefined: t = "undefined"; break;
    default:
        t = val.nodeName || Object.prototype.toString.call(val).match(/object\s(\w+)/)[1];
        break;
}
return  t.toLowerCase();
}

MetaForm = function() {
    MetaForm.superclass.constructor.call(this, {
        labelAlign: 'top',
        url:'/commondb/admin/createMeta.ac',
        title: '新建元数据',
        bodyStyle:'padding:5px',
        renderTo:'new_meta',
        width: 400,
        items: [{
            layout:'column',
            border:false,
            items:[{
                columnWidth:1,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '实体名称',
                    name: 'entityName',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: '实体描述',
                    name: 'entityDesc',
                    anchor:'95%'
                }]
            }]
        },{
            xtype:'tabpanel',
            itemId:'all_attrs',
            plain:true,
            activeTab: 0,
            autoHeight: true,
            defaults:{bodyStyle:'padding:10px'},
            items:[{
                title:'直观属性',
                layout:'column',
                itemId:'picAttrs',
                autoHeight: true,
                //height:'auto',
                defaults: {width: 230},
                items: [
                {
                columnWidth:0.5,
                itemId:'c_picAttrs',
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'picAttr',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'picAttr',
                    anchor:'95%'
                }]
            },{
                columnWidth:0.1,
                layout: 'form',
                border:false,
                items: [{
                    xtype: 'button',
                    text: '添加',
                    width:'0.1',
                    name: 'picAttr',
                    handler: function()
                    {
                        newPicField = new Ext.form.TextField({
                                xtype:'textfield',
                                fieldLabel: '属性名称',
                                name: 'picAttr',
                                anchor:'95%'
                            });
                            
                        var picAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        picAttrCt.add(newPicField);
                        
                        metaForm.doLayout();
                    }
                    
                }]
            }]
            },{
                title:'描述属性',
                layout:'column',
                itemId:'descAttrs',
                autoHeight: true,
                //height:'auto',
                defaults: {width: 230},
                

                items: [
                {
                columnWidth:0.5,
                itemId:'descAttrs',
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'descAttr',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'descAttr',
                    anchor:'95%'
                }]
            },
            {
                columnWidth:0.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'checkbox',
                    fieldLabel: '唯一属性',
                    name: 'unique',
                    anchor:'95%'
                }, {
                    xtype:'checkbox',
                    fieldLabel: '唯一属性',
                    name: 'unique',
                    anchor:'95%'
                }]
            },{
                columnWidth:0.2,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'checkbox',
                    fieldLabel: '自动提示',
                    checked:"true",
                    name: 'autoTip',
                    anchor:'95%'
                }, {
                    xtype:'checkbox',
                    fieldLabel: '自动提示',
                    checked:"true",
                    name: 'autoTip',
                    anchor:'95%'
                }]
            },{
                columnWidth:0.1,
                layout: 'form',
                border:false,
                items: [{
                    xtype: 'button',
                    text: '添加',
                    width:'0.1',
                    name: 'descAttr',
                    handler: function()
                    {
                        newDescField = new Ext.form.TextField({
			                    xtype:'textfield',
			                    fieldLabel: '属性名称',
			                    name: 'descAttr',
			                    anchor:'95%'
			                });
                            
                        var descAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        descAttrCt.add(newDescField);
                        
                        newUniqueCheckbox = new Ext.form.Checkbox({
		                    xtype:'checkbox',
		                    fieldLabel: '唯一属性',
		                    name: 'unique',
		                    anchor:'95%'
		                });
                            
                        var uniqueAttrCt = this.ownerCt.ownerCt.items.itemAt(1);
                        uniqueAttrCt.add(newUniqueCheckbox);
                        
                        autoTipCheckbox = new Ext.form.Checkbox({
                            xtype:'checkbox',
                            fieldLabel: '自动提示',
                            checked:"true",
                            name: 'autoTip',
                            anchor:'95%'
                        });
                            
                        var autoTipAttrCt = this.ownerCt.ownerCt.items.itemAt(2);
                        autoTipAttrCt.add(autoTipCheckbox ); 
                        
                        
                        
                        metaForm.doLayout();
                    }
                    
                }]
            }
                
                
                /*{
                    fieldLabel: '属性名称',
                    name: 'descAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'descAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'descAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'descAttr'
                }*/]
            },
            {
                
                title:'层级属性',
                layout:'column',
                itemId:'hierAttrs',
                autoHeight: true,
                //height:'auto',
                defaults: {width: 230},
                items: [
                {
                columnWidth:0.5,
                itemId:'hierarchyAttrs',
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr',
                    anchor:'95%'
                }]
            },{
                columnWidth:0.1,
                layout: 'form',
                border:false,
                items: [{
                    xtype: 'button',
                    text: '添加',
                    width:'0.1',
                    name: 'hierarchyAttr',
                    handler: function()
                    {
                        newHierField = new Ext.form.TextField({
                                xtype:'textfield',
                                fieldLabel: '属性名称',
                                name: 'hierarchyAttr',
                                anchor:'95%'
                            });
                            
                        var hierarchyAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        hierarchyAttrCt.add(newHierField);
                        
                        metaForm.doLayout();
                    }
                    
                }]
            }]
            },
            
            {
                title:'特征属性',
                layout:'column',
                itemId:'characterAttrs',
                autoHeight: true,
                //height:'auto',
                defaults: {width: 230},
                items: [
                {
                columnWidth:0.5,
                itemId:'c_characterAttrs',
                layout: 'form',
                border:false,
                items: [{
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'characterAttr',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: '属性名称',
                    name: 'characterAttr',
                    anchor:'95%'
                }]
            },{
                columnWidth:0.1,
                layout: 'form',
                border:false,
                items: [{
                    xtype: 'button',
                    text: '添加',
                    width:'0.1',
                    name: 'characterAttr',
                    handler: function()
                    {
                        newHierField = new Ext.form.TextField({
                                xtype:'textfield',
                                fieldLabel: '属性名称',
                                name: 'hierarchyAttr',
                                anchor:'95%'
                            });
                            
                        var hierarchyAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        hierarchyAttrCt.add(newHierField);
                        
                        metaForm.doLayout();
                    }
                    
                }]
            }]
            }]
        }],

        buttons: [{
            text: '保存',
            handler: function(){
            metaForm.getForm().submit({
	         success: function(f,a){
	            Ext.Msg.alert('Success', '保存成功');
	         },
	         failure: function(f,a){
	            Ext.Msg.alert('Warning', '保存失败');
	         }
	      });
   }
        },{
            text: '取消'
        }]
    });
    
    
};
/*
 MyForm = function() {
    MyForm.superclass.constructor.call(this, {
        labelWidth: 75, // label settings here cascade unless overridden
        url:'save-form.php',
        renderTo:'welcome2',
        frame:true,
        title: '新建MetaData ',
        bodyStyle:'padding:5px 5px 0',
        width: 350,

        items: [{
            xtype:'fieldset',
            collapsible: true,
            title: '基本信息',
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            collapsed: true,
            items :[{
                    fieldLabel: '实体名称',
                    name: 'entityName',
                    allowBlank:false
                }
            ]
        },{
            xtype:'fieldset',
            collapsible: true,
            title: '直观属性',
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            collapsed: true,
            items :[{
                    fieldLabel: '图片一名称',
                    name: 'photoName',
                    allowBlank:false
                },{
                    fieldLabel: '图片二名称',
                    name: 'photoName'
                },{
                    fieldLabel: '图片三名称',
                    name: 'photoName'
                }
            ]
        },{
            xtype:'fieldset',
            collapsible: true,
            title: '描述属性',
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            collapsed: true,
            items :[{
                    fieldLabel: '属性名称',
                    name: 'descName',
                    allowBlank:false
                },{
                    fieldLabel: '属性名称',
                    name: 'descName'
                },{
                    fieldLabel: '属性名称',
                    name: 'descName'
                }, {
                    fieldLabel: '属性名称',
                    name: 'descName'
                }
            ]
        },{
            xtype:'fieldset',
            title: '层级属性',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            items :[{
                    fieldLabel: '属性名称',
                    name: 'hierarchyName'
                },{
                    fieldLabel: '属性名称',
                    name: 'hierarchyName'
                },{
                    fieldLabel: '属性名称',
                    name: 'hierarchyName'
                }, {
                    fieldLabel: '属性名称',
                    name: 'hierarchyName'
                }
            ]
        },{
            xtype:'fieldset',
            title: '特征属性',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            items :[{
                    fieldLabel: '属性名称',
                    name: 'characterName'
                },{
                    fieldLabel: '属性名称',
                    name: 'characterName'
                },{
                    fieldLabel: '属性名称',
                    name: 'characterName'
                }, {
                    fieldLabel: '属性名称',
                    name: 'characterName'
                }
            ],
            buttons:[{text: 'add'}]
            
        }],

        buttons: [{
            text: 'Save'
        },{
            text: 'Cancel'
        }]
    });
};   

 */
Ext.extend(MetaForm, Ext.FormPanel, {});

metaForm = new MetaForm();




