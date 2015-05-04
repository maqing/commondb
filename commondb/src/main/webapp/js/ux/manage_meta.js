/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */


var picAttrStore;
var descAttrStore;



var metaForm;
metaForm = null;

var win;
win=null;
function repVar(v) {
    if (v == 1) return "时间"
    else if (v == 2) return "文本"
    else if (v == 3) return "备注"
    else if (v == 4) return "数字"
    else if (v == 5) return "货币"
    else if (v == 6) return "是否"
    else if (v == 7) return "超链接"
}
var metaPropertyStore = new Ext.data.JsonStore({
    // store configs
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
//======================charaDefGrid
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
    id:"charaDef_Grid_edit",
    //renderTo: "manage_charaDef_edit",
    ds: charaDefStore,
    cm:charaDefColModel,
    sm:charaDefSm,
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:200,
    width:650,
    title:'特征属性'
    /*
    tbar : [{
            id : 'delDef',
            text : '保存关联',
            //iconCls : 'edit',
            handler : updateChara
    }]
    */
});

function selChara(charaArr){
    charaDefSm.clearSelections();
    if(charaArr!=null&&charaArr!=''){
        var charaIdSelArr=[];
        for(var k=0;k<charaArr.length;k++){
            charaIdSelArr[k]=charaArr[k].charaId;
        }

        var recordArr = charaDefStore.queryBy(function(record,id){
            return charaIdSelArr.indexOf(record.get('charaId'))!=-1;
        },this).getRange();

        charaDefSm.selectRecords(recordArr,false);

    }
}

function updateChara(metaId){
    //alert(metaForm.getMetaId());
    var sm = charaDefGrid.getSelectionModel();
    var sel = sm.getSelections();
    var charaIdArr=[];
    for(var i=0;i<sel.length;i++){
        charaIdArr[i]=sel[i].get('charaId');
    }
    var conn = new Ext.data.Connection();
    conn.request({
                url : 'admin/updateRMetaChara.ac',
                params : {
                    metaId:metaId,
                    charaIdArr:charaIdArr
                },
                success : function(resp, opt) {
                    Ext.Msg.alert('成功','保存成功');
                },
                failure : function(resp, opt) {
                    Ext.Msg.alert('失败','保存失败');
                }
   });
}

var clearMetaInfo = function ()
{

    store.load();
    //charaDefStore.load();
    /*
    pstore.load({params : {metaId : 0}});
    cstore.load({params : {metaId : 0}});
    dstore.load({params : {metaId : 0}});
    hstore.load({params : {metaId : 0}});
    */
}
function showeditPanel( btn)
{       //直接取得选中的行对应的record

        var record = g_grid.getSelectionModel().getSelected();



        if(!record){
            //Ext.Msg.alert('信息','请选择要编辑的数据');
	        if(!metaForm)
	        {

	            metaForm =  new EditMetaForm(null);
	        }
        }
        else
        {
            if(!metaForm)
            {
                metaForm =  new EditMetaForm(null);
            }
            else
            {
                metaForm.loadRecord(record);
                metaForm.setTitle("编辑：" + record.data.entityName);
            }

        }

        //--定义编辑窗体
        /*
        if(!win)
        {

            win = xjjlEditWindow = new Ext.Window({
                el:'edit_win',
                modal:true,
                //layout:'fit',
                width:420,
                autoHeight:true,
                closable: true,
                closeAction:'hide',
                plain: true,
                autoDestroy: true,
                resizable: false,
                items: metaForm //在window中加载编辑的form
            });
        }
          */

        //win.show(null);//显示编辑窗口


       //[注意]先xjjlEditWindow.show(); 再 xjjlEditForm.getForm().loadRecord(currrecordRecord); 就可以解决之前的页面加载完成以后,第一次点击[编辑]按钮时无法加载数据到form的问题了。
        //xjjlEditForm.getForm().loadRecord(record); //关键是这里用当前选中的grid中的record填充form
}

//===============================================================================
//var App = new Ext.App({});


var ptextField =  new Ext.form.TextField();
var dtextField =  new Ext.form.TextField();
var ctextField =  new Ext.form.TextField();
var htextField =  new Ext.form.TextField();




pGrid = Ext.extend(Ext.grid.EditorGridPanel, {
        autoDestroy: true,
    iconCls: 'silk-grid',
    frame: true,
    title: '直观属性',
    height: 173,
    width: 280,
    //style: 'margin-top: 1px',

    initComponent : function() {

        // typical viewConfig
        this.viewConfig = {
            forceFit: true
        };

        // relay the Store's CRUD events into this grid so these events can be conveniently listened-to in our application-code.
        this.relayEvents(this.store, ['destroy', 'save', 'update']);

        // build toolbars and buttons.
        this.tbar = this.buildTopToolbar();
        //this.bbar = this.buildBottomToolbar();
        //this.buttons = this.buildUI();

        // super
        pGrid.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar : function() {
        return [{
            text: '增加',
            iconCls: 'silk-add',
            handler: this.onAdd,
            scope: this
        }, '-', {
            text: '删除',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'];
    },



    /**
     * buildUI
     */
    buildUI : function() {
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onSave,
            scope: this
        }];
    },

    /**
     * onSave
     */
    onSave : function(btn, ev) {
        this.store.save();
    },

    /**
     * onAdd
     */
    onAdd : function(btn, ev) {

        var u = new this.store.recordType({
            attrId : null,
            metaId: metaForm.metaId,
            attrName : '属性名'
        });
        this.stopEditing();
        this.store.insert(0, u);
        this.startEditing(0, 2);
    },

    /**
     * onDelete
     */
    onDelete : function(btn, ev) {
        var index = this.getSelectionModel().getSelectedCell();
        if (!index) {
            return false;
        }

        var rec = this.store.getAt(index[0]);
        this.store.remove(rec);
    }
});
pGrid2 = Ext.extend(Ext.grid.EditorGridPanel, {
    autoDestroy: true,
    iconCls: 'silk-grid',
    frame: true,
    title: '描述属性',
    height: 173,
    width: 280,
     //style: 'margin-top: 1px',

    initComponent : function() {

        // typical viewConfig
        this.viewConfig = {
            forceFit: true
        };

        // relay the Store's CRUD events into this grid so these events can be conveniently listened-to in our application-code.
        this.relayEvents(this.store, ['destroy', 'save', 'update']);

        // build toolbars and buttons.
        this.tbar = this.buildTopToolbar();
        //this.bbar = this.buildBottomToolbar();
        //this.buttons = this.buildUI();

        // super
        pGrid2.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar : function() {
        return [{
            text: '增加',
            iconCls: 'silk-add',
            handler: this.onAdd,
            scope: this
        }, '-', {
            text: '删除',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'];
    },



    /**
     * buildUI
     */
    buildUI : function() {
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onSave,
            scope: this
        }];
    },

    /**
     * onSave
     */
    onSave : function(btn, ev) {
        this.store.save();
    },

    /**
     * onAdd
     */
    onAdd : function(btn, ev) {

        var u = new this.store.recordType({
            attrId : null,
            metaId: metaForm.metaId,
            attrName : '属性名',
            propertyId : '属性类型'
        });
        this.stopEditing();
        this.store.insert(0, u);
        this.startEditing(0, 2);
    },

    /**
     * onDelete
     */
    onDelete : function(btn, ev) {
        var index = this.getSelectionModel().getSelectedCell();
        if (!index) {
            return false;
        }

        var rec = this.store.getAt(index[0]);
        this.store.remove(rec);
    }
});
 var picAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: ptextField}
];

 var hierAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: htextField}
];

 var descAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: dtextField},
        {header: "属性类型", width: 50, sortable: true, dataIndex: 'propertyId',renderer: repVar,
        editor: new Ext.form.ComboBox({
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
						})}
];

 var charaAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: ctextField}
];

//================================================================================

    //构建Store
    picAttrStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listPicAttrs.ac',
    storeId: 'picAttrStore',
    // reader configs
    root: 'data',
    idProperty: 'attrId',
    fields: [
                 {name: 'attrId', mapping: 'attrId'},
                 {name: 'attrName'}
             ]
    });






    //构建Store
    descAttrStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listDescAttrs.ac',
    storeId: 'descAttrStore',
    // reader configs
    root: 'data',
    idProperty: 'attrId',
    fields: [
                 {name: 'attrId', mapping: 'attrId'},
                 {name: 'attrName'},
                 {name: 'propertyId'}
             ]
    });

    var descAttrCM = new Ext.grid.ColumnModel([
        {id:'attrId',header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "属性名称", width: 100, sortable: true, dataIndex: 'attrName'},
        {header: "属性类型", width: 50, sortable: true, dataIndex: 'propertyId',renderer: repVar}
    ]);

    var hierAttrStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listHierAttrs.ac',
    storeId: 'descAttrStore',
    // reader configs
    root: 'data',
    idProperty: 'attrId',
    fields: [
                 {name: 'attrId', mapping: 'attrId'},
                 {name: 'attrName'}
             ]
    });



    var hierAttrCM = new Ext.grid.ColumnModel([
        {id:'attrId',header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName'}
    ]);


    var charaAttrStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listCharaAttrs.ac',
    storeId: 'descAttrStore',
    // reader configs
    root: 'data',
    idProperty: 'attrId',
    fields: [
                 {name: 'attrId', mapping: 'attrId'},
                 {name: 'attrName'}
             ]
    });



    var charaAttrCM = new Ext.grid.ColumnModel([
        {id:'attrId',header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName'}
    ]);




EditMetaForm = function(in_metaId) {

    if(in_metaId)
    {
        this.setMetaId(in_metaId);
    }

    EditMetaForm.superclass.constructor.call(this, {
        labelAlign: 'top',
        url:'admin/updateMeta.ac',
        title: '编辑元数据',
        bodyStyle:'padding:5px',
        renderTo:'edit_meta',
        width: 410,
        height: 390,
        metaId:in_metaId,
        items: [{
            layout:'column',
            border:false,
            items:[{
                columnWidth:1,
                layout: 'form',
                border:false,
                items: [{
                    xtype:'hidden',
                    name: 'metaId'
                },{
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
            //autoHeight: true,
            //defaults:{bodyStyle:'padding:10px'},
            items:[
            charaDefGrid,
            new pGrid({
		        //renderTo: 'user-grid',
                title:'直观属性',
                metaId: in_metaId,
		        store: pstore,
                //cm: picAttrCM,
		        columns : picAttrColumns
            })
            ,new pGrid2({
                //renderTo: 'user-grid',
                metaId: in_metaId,
                title:'描述属性',
                store: dstore,
                //cm: picAttrCM,
                columns : descAttrColumns
            }),

            new pGrid({
                //renderTo: 'user-grid',
                metaId: in_metaId,
                store: hstore,
                title:'层级属性',
                //cm: picAttrCM,
                columns : hierAttrColumns
            })
            ]
        }],


        buttons: [{
            text: '保存',
            handler: function(){
            if(!metaForm.getForm().getValues()['metaId'])
            {
                Ext.Msg.alert('失败', '未指定元数据');
                return;
            }
            pstore.save();
            dstore.save();
            cstore.save();
            hstore.save();
            updateChara(metaForm.getMetaId());
            metaForm.getForm().submit({
                waitMsg:'提交中...',
                waitTitle:'请等待',
             success: function(f,a){
                Ext.Msg.alert('成功', '保存成功');
                metaForm.getForm().updateRecord(metaForm.record);
                store.load();

             },
             failure: function(f,a){
                Ext.Msg.alert('失败', '保存失败');

             }
          });
   }
        },{
            text: '取消',
            handler: function(){

            }
        }]
    });
};



Ext.extend(EditMetaForm, Ext.FormPanel, {
     record : null,

	 loadRecord : function(rec) {
	        this.record = rec;

	        this.getForm().loadRecord(rec);
            this.setMetaId(rec.id);

	    },

     setMetaId: function(in_metaId)
     {
        this.metaId = in_metaId;
    pstore.load({params : {metaId : in_metaId}});
    cstore.load({params : {metaId : in_metaId}});
    dstore.load({params : {metaId : in_metaId}});
    hstore.load({params : {metaId : in_metaId}});

     },
     getMetaId: function()
     {
        //this.metaId = in_metaId;
        return this.metaId;
     }
});


    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';


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
                 {name: 'lastUpdateTime'},
                 {name: 'characterAttrDefList'}
             ]
});


     //载入
     store.load();


    // the DefaultColumnModel expects this blob to define columns. It can be extended to provide
    // custom or reusable ColumnModels
     var colModel = new Ext.grid.ColumnModel([
        {id:'entityName',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'entityName'},
        {header: "描述", width: 55, sortable: true, dataIndex: 'entityDesc'},
        {header: "更新时间", width: 80, sortable: true,  dataIndex: 'lastUpdateTime'},
        {header: "创建时间", width: 80, sortable: true,  dataIndex: 'createTime'}
    ]);

   if(true) //Ext.getCmp('manage_meta_grid')||true
   {
        g_grid = new Ext.grid.GridPanel({
        id:'manage_meta_grid',
        autoDestroy: true,
        renderTo: "manageMeta",
        ds: store,
        cm:colModel,
        stripeRows: true,
        //autoExpandColumn: 'name',
        height:350,
        width:400,
        title:'元数据定义列表',
        listeners: {
            rowclick: function(g, index, ev) {
                var rec = g_grid.store.getAt(index);
                metaForm.loadRecord(rec);
                metaForm.setTitle("编辑:" + rec.data.entityName)
                var charaArr=rec.data.characterAttrDefList;
                selChara(charaArr);
            },
            destroy : function() {
                metaForm.getForm().reset();
                metaForm.setTitle("编辑元数据");
            }
        },
        tbar : [{
                    id : 'newWindow',
                    text : '编辑',
                    //iconCls: 'silk-add',
                    //iconCls : 'edit',
                    handler : function() {
                       showeditPanel(this);
                    }
         }, {
                    id : 'refresh',
                    text : '刷新',
                    //iconCls: 'silk-add',
                    //iconCls : 'edit',
                    handler : function() {
                       store.load();//showeditPanel(this);
                    }
         },
         {
                id : 'delMetaData',
                text : '删除',
                //iconCls : 'edit',
                handler : function() {
                    var sm = g_grid.getSelectionModel();
                    var sel = sm.getSelected();
                    if (sm.hasSelection()) {
                        Ext.Msg.show({
                            title : '删除元数据',
                            buttons : Ext.MessageBox.YESNOCANCEL,
                            msg : '删除 ' + sel.data.entityName + '?',
                            fn : function(btn) {
                                if (btn == 'yes') {
                                    var conn = new Ext.data.Connection();
                                    conn.request({
                                                url : 'admin/delMeta.ac',
                                                params : {
                                                    metaId : sel.id
                                                },
                                                success : function(resp, opt) {

                                                    g_grid.getStore().remove(sel);
                                                    metaForm.getForm().reset();

                                                    metaForm.setTitle("编辑元数据");
                                                    pstore.load({params : {metaId : 0}});
												    cstore.load({params : {metaId : 0}});
												    dstore.load({params : {metaId : 0}});
												    hstore.load({params : {metaId : 0}});
												},
                                                failure : function(resp, opt) {
                                                    Ext.Msg
                                                            .alert('错误',
                                                                    '删除失败');
                                                }
                                            });
                                }
                            }
                        });
                    };
                }
         }]
    });

    g_grid.on('destroy',function(){
                                    //model.destroy();

                                });

   }

   showeditPanel();





