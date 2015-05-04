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
            
            
            metaForm.loadRecord(record);
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
        else
        {
            metaForm.loadRecord(record);
        }
        xjjlEditWindow.show(btn);//显示编辑窗口  
          
       //[注意]先xjjlEditWindow.show(); 再 xjjlEditForm.getForm().loadRecord(currrecordRecord); 就可以解决之前的页面加载完成以后,第一次点击[编辑]按钮时无法加载数据到form的问题了。  
        //xjjlEditForm.getForm().loadRecord(record); //关键是这里用当前选中的grid中的record填充form  
}

//===============================================================================
//var App = new Ext.App({});


var textField =  new Ext.form.TextField();



pGrid = Ext.extend(Ext.grid.EditorGridPanel, {

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

 var picAttrColumns =  [
        {header: "ID", width: 20, sortable: true, locked:false, dataIndex: 'attrId', editor: textField},
        {header: "metaId", width: 20, sortable: true, locked:false, hidden:true, dataIndex: 'metaId', editor: textField},
        {header: "属性名称", width: 150, sortable: true, dataIndex: 'attrName', editor: textField}
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


    this.setMetaId(in_metaId);
    
    EditMetaForm.superclass.constructor.call(this, {
        labelAlign: 'top',
        url:'admin/updateMeta.ac',
        baseParams : {metaId:in_metaId},
        title: '编辑元数据',
        bodyStyle:'padding:5px',
        renderTo:'new_entity_8',
        width: 710,
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
            ,new pGrid({
                //renderTo: 'user-grid',
                metaId: in_metaId,
                title:'描述属性',
                store: dstore,
                //cm: picAttrCM,
                columns : picAttrColumns,
                listeners: {
                    rowclick: function(g, index, ev) {
                        var rec = g.store.getAt(index);
                        
                    },
                    destroy : function() {
                        
                    }
                }
            }),
            new pGrid({
                //renderTo: 'user-grid',
                metaId: in_metaId,
                store: cstore,
                title:'特征属性',
                //cm: picAttrCM,
                columns : picAttrColumns,
                listeners: {
                    rowclick: function(g, index, ev) {
                        var rec = g.store.getAt(index);
                        
                    },
                    destroy : function() {
                        
                    }
                }
            }),
            new pGrid({
                //renderTo: 'user-grid',
                metaId: in_metaId,
                store: hstore,
                title:'层级属性',
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
            ]
        }],


        buttons: [{
            text: 'Save',
            handler: function(){            
            metaForm.getForm().submit({
             success: function(f,a){
                Ext.Msg.alert('成功', '保存成功');
                metaForm.getForm().updateRecord(metaForm.record);
                win.hide();
             },
             failure: function(f,a){
                Ext.Msg.alert('警告', '保存失败');
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



Ext.extend(EditMetaForm, Ext.FormPanel, {
     record : null,

	 loadRecord : function(rec) {
	        this.record = rec;
            
	        this.getForm().loadRecord(rec);
            this.setMetaId(rec.id);
	        
	    },
        
     setMetaId: function(in_metaId)
     {
    pstore.load({params : {metaId : in_metaId}});
    cstore.load({params : {metaId : in_metaId}});
    dstore.load({params : {metaId : in_metaId}});
    hstore.load({params : {metaId : in_metaId}});        
     
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
        {header: "更新时间", width: 80, sortable: true,  dataIndex: 'lastUpdateTime'},
        {header: "创建时间", width: 80, sortable: true,  dataIndex: 'createTime'}
    ]);
    
    var grid = new Ext.grid.GridPanel({
        renderTo: "new_entity_list",
        ds: store,
        cm:colModel,
        stripeRows: true,
        //autoExpandColumn: 'name',
        height:350,
        width:600,
        title:'实体列表',
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
                    var sm = grid.getSelectionModel();
                    var sel = sm.getSelected();
                    if (sm.hasSelection()) {
                        Ext.Msg.show({
                            title : '删除元数据',
                            buttons : Ext.MessageBox.YESNOCANCEL,
                            msg : '删除 ' + sel.data.title + '?',
                            fn : function(btn) {
                                if (btn == 'yes') {
                                    var conn = new Ext.data.Connection();
                                    conn.request({
                                                url : 'admin/delMeta.ac',
                                                params : {
                                                    metaId : sel.id
                                                },
                                                success : function(resp, opt) {
                                                    grid.getStore().remove(sel);
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


    
