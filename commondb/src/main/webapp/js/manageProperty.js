// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';

var in_propertyId;
var newMetaRefresh = function(){};

var metaPropertyStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: 'admin/listMetaProperty.ac',
    storeId: 'metaProperty_Store',
    // reader configs
    root: 'data',
    idProperty: 'propertyId',
    fields: [
                 {name: 'propertyId', mapping: 'propertyId'},
                 {name: 'propertyName'},
                 {name: 'propertyDesc'},
                 {name: 'propertyCode'},
                 {name: 'propertyLimit'},
                 {name: 'isEnabled'}

             ]
});

metaPropertyStore.load();

var metaPropertyColModel = new Ext.grid.ColumnModel([
    {id:'propertyName',header: "名称", width: 150, sortable: true, locked:false, dataIndex: 'propertyName'},
    {header: "描述", width: 250, sortable: true, dataIndex: 'propertyDesc'},
    {header: "代码", width: 100, sortable: true, dataIndex: 'propertyCode'},
    {header: "限制", width: 100, sortable: true, dataIndex: 'propertyLimit'}

    //{header: "是否可用", width: 80, sortable: true, dataIndex: 'isEnabled',renderer: function repVar(v) {
	//    if (v == 1) return "是"
	//    if (v == 0) return "否"
	//}}

]);

var metaPropertyGrid = new Ext.grid.GridPanel({
	id:'metaProperty_Grid',
    renderTo: "manage_property",
    ds: metaPropertyStore,
    cm:metaPropertyColModel,
    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),
    stripeRows: true,
    //autoExpandColumn: 'name',
    height:450,
    width:600,
    title:'数据类型列表',
    listeners: {
    },
    tbar : [{
                id : 'newMetaPropertyWindow',
                text : '新建',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   newMetaProperty(this);
                }
     },{
                id : 'editMetaPropertyWindow',
                text : '编辑',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   editMetaProperty(this);
                }
     }, {
                id : 'refreshMetaProperty',
                text : '刷新',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {
                   metaPropertyStore.load();
                }
     },
     {
			id : 'delMetaProperty',
			text : '删除',
			//iconCls : 'edit',
			handler : function() {
                var sm = metaPropertyGrid.getSelectionModel();
                var sel = sm.getSelected();
                if (sm.hasSelection()) {
                    Ext.Msg.show({
                        title : '删除数据类型',
                        buttons : Ext.MessageBox.YESNOCANCEL,
                        msg : '删除 ' + sel.data.propertyName + '?',
                        fn : function(btn) {
                            if (btn == 'yes') {
                                var conn = new Ext.data.Connection();
                                conn.request({
                                            url : 'admin/dropMetaProperty.ac',
                                            params : {
                                                propertyId : sel.id
                                            },
                                            success : function(resp, opt) {
                                            	Ext.Msg.alert('成功','删除成功');
                                                metaPropertyGrid.getStore().remove(sel);
                                            },
                                            failure : function(resp, opt) {
                                                Ext.Msg.alert('失败','删除失败');
                                            }
                                        });
                            }
                        }
                    });
                };
            }
     }/*,{
                id : 'manageDataWindow',
                text : '管理数据',
                //iconCls: 'silk-add',
                //iconCls : 'edit',
                handler : function() {

                		manageData(this);

                }
     }*/]
});
//-------------------------newDef-------------------------------
var  newMetaPropertyForm=  new Ext.form.FormPanel({
    id: 'newMetaProperty_form',
    url: 'admin/createMetaProperty.ac',
    renderTo: 'new_property_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [ {
                fieldLabel: '名称',
	            id:'propertyNameId',
			    name:'propertyName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {
                fieldLabel: '描述',
	            id:'propertyDescId',
			    name:'propertyDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    }, {
                fieldLabel: '代码',
	            id:'propertyCodeId',
			    name:'propertyCode',
			    allowBlank: true

    }, {
                fieldLabel: '限制',
	            id:'propertyLimitId',
			    name:'propertyLimit',
			    allowBlank: true

    }],
    buttons: [{
    	text: '保存',
        handler: saveMetaProperty,
        style: 'margin: 0 0 0 10px'
    	}]
});

function saveMetaProperty(){

    if (Ext.getCmp('newMetaProperty_form').form.isValid()){
        Ext.getCmp('newMetaProperty_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	metaPropertyStore.load();
            newMetaPropertyWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit'}
      });
   }
}

var newMetaPropertyWindow = new Ext.Window({
	id:'newMetaProperty_Window',
	applyTo: 'new_property_win',  //前端放置当前js文件的页面中的div名称
	title:'新建数据类型',
	width: 350,
	layout: 'fit',
	autoHeight: true,
	closable: true,
	closeAction: 'hide',//close
	resizable: false,
	items: newMetaPropertyForm ,//在window中加载编辑的form
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        newMetaPropertyForm.getForm().reset();
    }
});

function newMetaProperty( btn)
{
    newMetaPropertyForm.getForm().reset();
    newMetaPropertyWindow.show(btn);
}

//-------------------------editDef-------------------------------
var  editMetaPropertyForm=  new Ext.form.FormPanel({
    id: 'editMetaProperty_form',
    url: 'admin/updateMetaProperty.ac',
    renderTo: 'edit_property_form',
    //title:'用户',
    frame: true,
    labelAlign: 'left',
    bodyStyle:'padding:1px',
    width: 250,
    height:300,
    defaultType: 'textfield',
    labelWidth: 55,
    items: [{
                fieldLabel: '名称',
	            id:'propertyNameId2',
			    name:'propertyName',
			    allowBlank: false,
	            blankText: '名称不能为空'
    }, {
                fieldLabel: '描述',
	            id:'propertyDescId2',
			    name:'propertyDesc',
			    allowBlank: false,
	            blankText: '描述不能为空'
    }, {
                fieldLabel: '代码',
	            id:'propertyCodeId2',
			    name:'propertyCode',
			    allowBlank: true

    }, {
                fieldLabel: '限制',
	            id:'propertyLimitId2',
			    name:'propertyLimit',
			    allowBlank: true

    }],
    buttons: [{
    	text: '修改',
        handler: updateMetaProperty,
        style: 'margin: 0 0 0 10px'
    	}]
});

function updateMetaProperty(){

    if (Ext.getCmp('editMetaProperty_form').form.isValid()){
        Ext.getCmp('editMetaProperty_form').getForm().submit({
         success: function(f,a){
         	Ext.Msg.alert('成功', '保存成功');
         	metaPropertyStore.load();
            editMetaPropertyWindow.hide();
         },
         failure: function(f,a){
            Ext.Msg.alert('失败', '保存失败');
         },
         params : { action : 'submit',propertyId:in_propertyId}
      });
   }
}

var editMetaPropertyWindow = new Ext.Window({
	id:'editMetaProperty_Window',
	applyTo: 'edit_property_win',  //前端放置当前js文件的页面中的div名称
	title:'修改数据类型',
	width: 350,
	layout: 'fit',
	autoHeight: true,
	closable: true,
	closeAction: 'hide',//close
	resizable: false,
	items: editMetaPropertyForm ,//在window中加载编辑的form
	callback : null,
    params : null,
    plain:true,
    modal : true,
    //initHidden:true,
    reset : function() {
        editMetaPropertyForm.getForm().reset();
    }
});

function editMetaProperty( btn)
{
	var record = metaPropertyGrid.getSelectionModel().getSelected();

    if(!record){
        Ext.Msg.alert('信息','请选择要编辑的数据');
        return;
    }

    in_propertyId=record.id;

    editMetaPropertyForm.getForm().loadRecord(record);

    editMetaPropertyWindow.show(btn);
}
