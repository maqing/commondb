

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
    id: 'attrId',
    proxy: proxy,
    reader: reader,
    batch:false,
    writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
    autoSave: false,  // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    listeners: {
        write : function(store, action, result, res, rs) {
            //alert("");
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