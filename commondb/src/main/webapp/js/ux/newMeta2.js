var metaForm;
var test = new  Ext.FormPanel();
var newMetaRefresh = function(){};
var descFiledCount=0;

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
//======================metaProperty
var metaPropertyStore;
getMetaPropertyStore();
function getMetaPropertyStore(){
metaPropertyStore = new Ext.data.JsonStore({
    // store configs
	id:'metaPropertyStoreid',
	name:'metaPropertyStore',
    autoDestroy: true,
    url: 'admin/listMetaProperty.ac',
    storeId: 'metaProperty_Store',
    // reader configs
    root: 'data',
    idProperty: 'propertyId',
    fields: [
                 {name: 'propertyId', type:'int'},
                 {name: 'propertyName'}
             ]
});
metaPropertyStore.load();
}

var charaDefStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listCharaDef.ac',
    storeId: 'charaDef_Store',
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

charaDefStore.load();

var charaDefSm = new Ext.grid.CheckboxSelectionModel({singleSelect :false});

var charaDefColModel = new Ext.grid.ColumnModel([
    new Ext.grid.RowNumberer(),
    charaDefSm,
    {id:'charaName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'charaName'},
    {header: "描述", width: 250, sortable: true, dataIndex: 'charaDesc'},
    {header: "是否共享", width: 80, sortable: true, dataIndex: 'isshared',renderer: function repVar(v) {
        if (v == 1) return "是"
        if (v == 0) return "否"
    }},
    {header: "用户", width: 100, sortable: true, dataIndex: 'user'}
]);

var charaDefGrid = new Ext.grid.GridPanel({
    id:"charaDef_Grid",
    //renderTo: "manage_charaDef",
    ds: charaDefStore,
    cm:charaDefColModel,
    sm:charaDefSm,
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:200,
    width:650,
    title:'特征属性'

});

MetaForm = function() {
    MetaForm.superclass.constructor.call(this, {
    	id:'MetaFormid',
        labelAlign: 'top',
        url:'admin/createMeta.ac',
        title: '新建元数据',
        bodyStyle:'padding:5px',
        renderTo:'new_meta',
        width: 400,
        layout:'form',
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
            height:230,
            autoScroll:true,
            //autoHeight: true,
            defaults:{bodyStyle:'padding:10px'},
            region: 'center',
            //deferredRender :true,
			//layoutOnTabChange :true ,
            items:[{
                title:'直观属性',
                layout:'column',
                itemId:'picAttrs',
                autoScroll:true,
                //autoHeight: true,
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
                //autoHeight: true,
                autoScroll:true,
                //height:'auto',
                defaults: {width: 230},


                items: [
                {
                columnWidth:0.4,
                itemId:'descAttrs',
                layout: 'form',
                border:false,
                items: [{
		                columnWidth:0.4,
		                layout: 'form',
		                border:false,
		                items: [{xtype:'textfield',
		                    fieldLabel: '属性名称',
		                    id: 'descAttrid0',
		                    name: 'descAttr',
		                    anchor:'95%'
		                }]
		             }]
	            },{
                columnWidth:0.4,
                layout: 'form',
                border:false,
                items: [new Ext.form.ComboBox({
                			id:'metaPropertyid0',
                			name:'metaProperty',
						    store: metaPropertyStore,
						    displayField:'propertyName',
						    valueField : 'propertyId',
						    hiddenName:'metaProperty',
						    typeAhead: true,
						    triggerAction: 'all',
						    emptyText:'请选择属性类型...',
						    selectOnFocus:true,
						    allowBlank : false,
						    anchor:'95%'
						})]
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
                    	descFiledCount++;
                        newDescField = new Ext.form.TextField({
			                    xtype:'textfield',
			                    fieldLabel: '属性名称',
			                    id: 'descAttrid'+descFiledCount,
			                    name: 'descAttr',
			                    anchor:'95%'
			                });
						var metaPropertyCombo = new Ext.form.ComboBox({
							id:'metaPropertyid'+descFiledCount,
							name:'metaProperty',
						    store: metaPropertyStore,
						    displayField:'propertyName',
						    valueField : 'propertyId',
						    hiddenName:'metaProperty',
						    typeAhead: true,
						    triggerAction: 'all',
						    emptyText:'请选择属性类型...',
						    selectOnFocus:true,
						    allowBlank : false,
						    anchor:'95%'
						});
                        var descAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        descAttrCt.add(newDescField);
                        var descAttrCt2 = this.ownerCt.ownerCt.items.itemAt(1);
						descAttrCt2.add(metaPropertyCombo);
                        metaForm.doLayout();
                    }

                }]},{
                columnWidth:0.1,
                layout: 'form',
                border:false,
                items: [{
                    xtype: 'button',
                    text: '删除',
                    width:'0.1',
                    name: 'descAttrdel',
                    handler: function()
                    {
                    	//var items = this.ownerCt.ownerCt.items.itemAt(0).items;
						//var items2 = this.ownerCt.ownerCt.items.itemAt(1).items;
						//items.removeAt(items.length-1);
                    	//items2.removeAt(items.length-1);
                    	//this.ownerCt.ownerCt.doLayout();
                    	//this.ownerCt.ownerCt.render();

						Ext.getCmp('MetaFormid').remove(Ext.getCmp('descAttrid'+descFiledCount), true);
						Ext.getCmp('MetaFormid').remove(Ext.getCmp('metaPropertyid'+descFiledCount), true);

						getMetaPropertyStore();

						for(var i=0;i<descFiledCount;i++){
						 Ext.getCmp('metaPropertyid'+i).store= metaPropertyStore;
					     //Ext.getCmp('metaPropertyid'+i).store.load();
						}

						Ext.getCmp('MetaFormid').doLayout();

						descFiledCount--;

                    }

                }]
            }
            ]
            },
            {

                title:'层级属性',
                layout:'column',
                itemId:'hierAttrs',
                //autoHeight: true,
                //height:'430',
                autoScroll:true,
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
            charaDefGrid

            /*{
                //height:300,
                //width: 230,


                title:'特征属性',
                layout:'column',
                itemId:'characterAttrs',
                autoHeight: true,

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
                                name: 'characterAttr',
                                anchor:'95%'
                            });

                        var hierarchyAttrCt = this.ownerCt.ownerCt.items.itemAt(0);
                        hierarchyAttrCt.add(newHierField);

                        metaForm.doLayout();
                    }

                }]
            }]
            }*/]
        }],

        buttons: [{
            text: '保存',
            handler: function(){
        	var sm = charaDefGrid.getSelectionModel();
            var sel = sm.getSelections();
            var charaIdArr=[];
            for(var i=0;i<sel.length;i++){
		    	charaIdArr[i]=sel[i].get('charaId');
		    }
            metaForm.getForm().submit({
	         success: function(f,a){
	            Ext.Msg.alert('成功', '保存成功');
                metaForm.getForm().reset();
	         },
	         failure: function(f,a){

	            Ext.Msg.alert('警告', '保存失败 : ' + a.result.errormsg);
	         },
	         params : { action : 'submit',characterAttr:charaIdArr }
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

//======================charaDefGrid





