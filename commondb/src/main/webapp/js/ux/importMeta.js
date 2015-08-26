var metaForm;
var importMetaRefresh = function(){};


MetaForm = function() {
    MetaForm.superclass.constructor.call(this, {
    	id:'MetaFormid',
        labelAlign: 'top',
        url:'admin/importMeta.ac',
        title: '导入元数据',
        bodyStyle:'padding:5px',
        renderTo:'import_meta',
        width: 400,
        layout:'form',
        fileUpload:true,
        items: [{
            xtype:'textfield',
            fieldLabel: '文件名', 
            id:'uploadFile', 
            name: 'uploadMeta', 
            inputType: 'file'//文件类型 
        }],

        buttons: [{
            text: '上传',
            handler: function(){
            metaForm.getForm().submit({
             method : 'post', 
             waitMsg : '操作处理中，请稍等...',  
             waitTitle:'提示',  
	         success: function(f,a){
	            Ext.Msg.alert('成功', '上传成功');
                metaForm.getForm().reset();
	         },
	         failure: function(f,a){

	            Ext.Msg.alert('警告', '上传失败 : ' + a.result.errormsg);
	         }
	      });
   }
        },{
            text: '取消'
        }]
    });


};

Ext.extend(MetaForm, Ext.FormPanel, {});

metaForm = new MetaForm();






