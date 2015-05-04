/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */

var metaForm;
var xjjlEditWindow;
var win;
var button;
var picAttrStore;
var descAttrStore;

function showeditPanel( btn)  
{       //直接取得选中的行对应的record  
        var record = grid.getSelectionModel().getSelected();   
        if(!record){  
            Ext.Msg.alert('信息','请选择要编辑的数据');  
            return;  
        }  
          
        //--定义编辑窗体  
        if(!xjjlEditWindow)  
        {  
            metaForm =  new EditMetaForm(record.id);
            
            
            metaForm.getForm().loadRecord(record);
            win = xjjlEditWindow = new Ext.Window({  
                el: 'edit_win',  //前端放置当前js文件的页面中的div名称  
                title:'编辑记录',  
                width: 410,  
                autoHeight: true,  
                closable: false,  
                closeAction: 'hide',  
                resizable: false,   
                items: metaForm //在window中加载编辑的form  
            });           
              
        }  
        xjjlEditWindow.show(btn);//显示编辑窗口  
          
       //[注意]先xjjlEditWindow.show(); 再 xjjlEditForm.getForm().loadRecord(currrecordRecord); 就可以解决之前的页面加载完成以后,第一次点击[编辑]按钮时无法加载数据到form的问题了。  
        //xjjlEditForm.getForm().loadRecord(record); //关键是这里用当前选中的grid中的record填充form  
}

//===============================================================================
//var App = new Ext.App({});

var proxy = new Ext.data.HttpProxy({
    api: {
        read : 'admin/listPicAttrs.ac',
        create : 'admin/createPicAttr.ac',
        update: 'admin/updatePicAttr.ac',
        destroy: 'admin/delPicAttr.ac'
    }
});

// Typical JsonReader.  Notice additional meta-data params for defining the core attributes of your json-response
var reader = new Ext.data.JsonReader({
    //totalProperty: 'total',
    successProperty: 'success',
    idProperty: 'attrId',
    root: 'data'
}, [
       {name: 'attrId', mapping: 'attrId'},  
       {name: 'metaId', mapping: 'metaId'},  
       {name: 'attrName'}  
]);

// The new DataWriter component.
var writer = new Ext.data.JsonWriter({
    encode: true,
    writeAllFields: true
});

// Typical Store collecting the Proxy, Reader and Writer together.
var pstore = new Ext.data.Store({
    id: 'user',
    proxy: proxy,
    reader: reader,
    writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
    autoSave: true,  // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    listeners: {
        write : function(store, action, result, res, rs) {
            //App.setAlert(res.success, res.message); // <-- show user-feedback for all write actions
        },
        exception : function(proxy, type, action, options, res, arg) {
            if (type === 'remote') {
                Ext.Msg.show({
                    title: 'REMOTE EXCEPTION',
                    msg: res.message,
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});


// A new generic text field
var textField =  new Ext.form.TextField();

// Let's pretend we rendered our grid-columns with meta-data from our ORM framework.


// load the store immeditately


pGrid = Ext.extend(Ext.grid.EditorGridPanel, {
    //renderTo: 'user-grid',
    iconCls: 'silk-grid',
    frame: true,
    title: '直观属性',
    height: 200,
    width: 280,
    metaId: 0,
    style: 'margin-top: 10px',

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
        this.buttons = this.buildUI();

        // super
        pGrid.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar : function() {
        return [{
            text: 'Add',
            iconCls: 'silk-add',
            handler: this.onAdd,
            scope: this
        }, '-', {
            text: 'Delete',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'];
    },

    /**
     * buildBottomToolbar
     */
    buildBottomToolbar : function() {
        return ['<b>@cfg:</b>', '-', {
            text: 'autoSave',
            enableToggle: true,
            pressed: true,
            tooltip: 'When enabled, Store will execute Ajax requests as soon as a Record becomes dirty.',
            toggleHandler: function(btn, pressed) {
                this.store.autoSave = pressed;
            },
            scope: this
        }, '-', {
            text: 'batch',
            enableToggle: true,
            pressed: true,
            tooltip: 'When enabled, Store will batch all records for each type of CRUD verb into a single Ajax request.',
            toggleHandler: function(btn, pressed) {
                this.store.batch = pressed;
            },
            scope: this
        }, '-', {
            text: 'writeAllFields',
            enableToggle: true,
            tooltip: 'When enabled, Writer will write *all* fields to the server -- not just those that changed.',
            toggleHandler: function(btn, pressed) {
                store.writer.writeAllFields = pressed;
            },
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
            attrId : 0,
            metaId: this.metaId,
            attrName : '属性名'
        });
        this.stopEditing();
        this.store.insert(0, u);
        this.startEditing(0, 1);
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
    
    var picAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId', editor: textField},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId', editor: textField},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: textField}
];


    
    
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
                 {name: 'attrName'}         
             ]  
    });
         
    
    
    var descAttrCM = new Ext.grid.ColumnModel([
        {id:'attrId',header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId'},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName'}
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
    pstore.load({params : {metaId : in_metaId}});
    proxy.on('beforewrite', function(p, params) {
        
       
     });
     
    picAttrStore.load({params : {metaId : in_metaId}});
    descAttrStore.load({params : {metaId : in_metaId}});
    hierAttrStore.load({params : {metaId : in_metaId}});
    charaAttrStore.load({params : {metaId : in_metaId}});
    
    EditMetaForm.superclass.constructor.call(this, {
        labelAlign: 'top',
        //url:'/commondb/admin/createMeta.ac',
        title: '编辑元数据',
        bodyStyle:'padding:5px',
        renderTo:'edit_meta',
        width: 410,
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
            items:[
            new pGrid({
		        //renderTo: 'user-grid',
                metaId: in_metaId,
		        store: pstore,
                //cm: picAttrCM,
		        columns : picAttrColumns,
		        listeners: {
		            rowclick: function(g, index, ev) {
		                var rec = g.store.getAt(index);
		                
		            },
		            destroy : function() {
		                
		            }
		        }
            })
            ,{
               xtype: 'grid',
                ds: descAttrStore,
                autoHeight:true,
                cm: descAttrCM,
                sm: new Ext.grid.RowSelectionModel({
                    singleSelect: true,
                    listeners: {
                        rowselect: function(sm, row, rec) {
                            alert('select me');
                        }
                    }
                }),


                title:'描述属性',
                border: true,
                listeners: {
                    render: function(g) {
                        g.getSelectionModel().selectRow(0);
                    },
                    delay: 10 // Allow rows to be rendered.
                }
            },
            {
               xtype: 'grid',
                ds: hierAttrStore,
                autoHeight:true,
                cm: hierAttrCM,
                sm: new Ext.grid.RowSelectionModel({
                    singleSelect: true,
                    listeners: {
                        rowselect: function(sm, row, rec) {
                            
                        }
                    }
                }),


                title:'层级属性',
                border: true,
                listeners: {
                    render: function(g) {
                        g.getSelectionModel().selectRow(0);
                    },
                    delay: 10 // Allow rows to be rendered.
                }
            },
            {
	            layout:'column',
                title : "特征属性",
                height : 200,
	            border:false,
	            items:[{
	                columnWidth:0.5,
	                layout: 'fit',
                    bodyStyle:'padding:5px',
	                border:false,
	                items: [{
		               xtype: 'grid',
		                ds: charaAttrStore,
                        defaults:{bodyStyle:'padding:10px'},
                        height : 185,
		                //autoHeight:true,
		                cm: charaAttrCM,
		                sm: new Ext.grid.RowSelectionModel({
		                    singleSelect: true,
		                    listeners: {
		                        rowselect: function(sm, row, rec) {
		                           
		                        }
		                    }
		                }),
		
		
		                title:'特征属性',
		                border: true,
		                listeners: {
		                    render: function(g) {
		                        g.getSelectionModel().selectRow(0);
		                    },
		                    delay: 10 // Allow rows to be rendered.
		                }
		            }]
	            },
                {
                    columnWidth:0.5,
                    layout: 'fit',
                    bodyStyle:'padding:5px',
                    border:false,
                    items: [{
                       xtype: 'grid',
                        ds: picAttrStore,
                        defaults:{bodyStyle:'padding:10px'},
                        height : 185,
                        //autoHeight:true,
                        cm: picAttrCM,
                        sm: new Ext.grid.RowSelectionModel({
                            singleSelect: true,
                            listeners: {
                                rowselect: function(sm, row, rec) {
                                   
                                }
                            }
                        }),
        
        
                        title:'属性可选值',
                        border: true,
                        listeners: {
                            render: function(g) {
                                g.getSelectionModel().selectRow(0);
                            },
                            delay: 10 // Allow rows to be rendered.
                        }
                    }]
                }]
            }
            ]
        }],

        buttons: [{
            text: 'Save',
            handler: function(){
            metaForm.getForm().submit({
             success: function(f,a){
                Ext.Msg.alert('Success', '保存成功');
                win.hide();
             },
             failure: function(f,a){
                Ext.Msg.alert('Warning', '保存失败');
                win.hide();
             }
          });
   }
        },{
            text: 'Cancel',
            handler: function(){
                win.hide();
            }
        }]
    });
};



Ext.extend(EditMetaForm, Ext.FormPanel, {});


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
                 {name: 'lastUpdateTime'}             
             ]  
});
         

     //载入  
     store.load();  
  

    // the DefaultColumnModel expects this blob to define columns. It can be extended to provide
    // custom or reusable ColumnModels
     var colModel = new Ext.grid.ColumnModel([
        {id:'entityName',header: "名称", width: 160, sortable: true, locked:false, dataIndex: 'entityName'},
        {header: "描述", width: 55, sortable: true, dataIndex: 'entityDesc'},
        {header: "更新时间", width: 80, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastUpdateTime'},
        {header: "创建时间", width: 80, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'createTime'}
    ]);
    
    var grid = new Ext.grid.GridPanel({
        renderTo: "manageMeta",
        ds: store,
        cm:colModel,
        stripeRows: true,
        //autoExpandColumn: 'name',
        height:350,
        width:600,
        title:'元数据定义列表',
        tbar : [{
                    id : 'newWindow',
                    text : '编辑',
                    iconCls: 'silk-add',
                    //iconCls : 'edit',
                    handler : function() {
                       showeditPanel(this);
                    }
         },{
                    id : 'delWindow',
                    text : '删除',
                    //iconCls : 'edit',
                    handler : function() {
                       showeditPanel(this);
                    }
         }]
    });


    
