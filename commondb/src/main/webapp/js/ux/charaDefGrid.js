var defProxy = new Ext.data.HttpProxy({
    api: {
        read : 'admin/listCharaDef.ac',
        create : 'admin/createCharaDef.ac',
        update: 'admin/updateCharaDef.ac',
        destroy: 'admin/dropCharaDef.ac'
    }
});

// Typical JsonReader.  Notice additional meta-data params for defining the core attributes of your json-response
var defReader = new Ext.data.JsonReader({
    //totalProperty: 'total',
    successProperty: 'success',
    idProperty: 'charaId',
    root: 'data'
}, [
       {name: 'charaId', mapping: 'charaId'},  
       //{name: 'metaId', mapping: 'metaId'},  
       {name: 'charaName'},
       {name: 'charaDesc'},
       {name: 'isshared'}  
]);

// The new DataWriter component.
var defWriter = new Ext.data.JsonWriter({
    encode: true,
    writeAllFields: true
});

// Typical Store collecting the Proxy, Reader and Writer together.
var defStore = new Ext.data.Store({
    batch:false,
    id: 'defStore',
    proxy: defProxy,
    reader: defReader,
    writer: defWriter,  // <-- plug a DataWriter into the store just as you would a Reader
    autoSave: false,  // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
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