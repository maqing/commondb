var metaForm;

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
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    fieldLabel: '图片一名称',
                    name: 'picAttr',
                    allowBlank:false,
                    value: '头像'
                },{
                    fieldLabel: '图片二名称',
                    name: 'picAttr',
                    value: '生活照一'
                },{
                    fieldLabel: '图片三名称',
                    name: 'picAttr',
                    value: '生活照二'
                }]
            },{
                title:'描述属性',
                layout:'form',
                itemId:'descAttrs',
                autoHeight: true,
                //height:'auto',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    xtype: 'button',
                    text: '添加',
                    name: 'descAttr',
                    handler: function()
                    {
                        c = new Ext.form.TextField({
                                 fieldLabel: "属性名称",
                                 name:'descAttr',
                                 autoHeight: true
                            });
                            
                        var ii = this.ownerCt;//;
                        ii.add(c);
                        metaForm.doLayout();
                    }
                    
                },
                {
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
                }]
            },
            {
                title:'层级属性',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'hierarchyAttr'
                }]
            },
            {
                title:'特征属性',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',

                items: [{
                    fieldLabel: '属性名称',
                    name: 'characterAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'characterAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'characterAttr'
                },{
                    fieldLabel: '属性名称',
                    name: 'characterAttr'
                }]
            }]
        }],

        buttons: [{
            text: 'Save',
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
            text: 'Cancel'
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




