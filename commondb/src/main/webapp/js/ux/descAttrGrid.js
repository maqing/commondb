var dproxy = new Ext.data.HttpProxy({
    api: {
        read : 'admin/listDescAttrs.ac',
        create : 'admin/createDescAttr.ac',
        update: 'admin/updateDescAttr.ac',
        destroy: 'admin/delDescAttr.ac'
    }
});

// Typical JsonReader.  Notice additional meta-data params for defining the core attributes of your json-response
var dreader = new Ext.data.JsonReader({
    //totalProperty: 'total',
    successProperty: 'success',
    idProperty: 'attrId',
    root: 'data'
}, [
       {name: 'attrId', mapping: 'attrId'},
       {name: 'metaId', mapping: 'metaId'},
       {name: 'attrName'},
       {name: 'propertyId'}
]);

// The new DataWriter component.
var dwriter = new Ext.data.JsonWriter({
    encode: true,
    writeAllFields: true
});

// Typical Store collecting the Proxy, Reader and Writer together.
var dstore = new Ext.data.Store({
    id: 'user',
    batch:false,
    proxy: dproxy,
    reader: dreader,
    writer: dwriter,  // <-- plug a DataWriter into the store just as you would a Reader
    autoSave:false,//autoSave: true,  // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    listeners: {
        write : function(store, action, result, res, rs) {
            //

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