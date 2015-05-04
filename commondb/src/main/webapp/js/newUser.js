    var store = new Ext.data.JsonStore({
	    // store configs
	    autoDestroy: true,
	    url: 'security/findRole.ac',
	    storeId: 'myStore',
	    // reader configs
	    root: 'data',
	    idProperty: 'roleId',  
	    fields: [  
	                 {name: 'roleId', mapping: 'roleId'},  
	                 {name: 'roleName'},  
	                 {name: 'roleDesc'}
	             ]  
	});
         

    store.load();  
  

    
    var sm = new Ext.grid.CheckboxSelectionModel({singleSelect :false});
    
    var userGrid = new Ext.grid.GridPanel({
        //renderTo: "newUser",
        ds: store,
        columns:[
	        new Ext.grid.RowNumberer(),
	        sm,
	        /*
	        {id:'roleName',header: "角色名称", width: 100, sortable: true, locked:false, dataIndex: 'roleName'},
	        {header: "描述", width: 250, sortable: true, dataIndex: 'roleDesc'}
	        */
	        {id:'roleDesc',header: "角色名称", width: 200, sortable: true, locked:false, dataIndex: 'roleDesc'}
	   	],
    	sm: sm,
        stripeRows: true,
        //autoExpandColumn: 'name',
        height:350,
        width:600,
        title:'角色列表'
    });
    function save(){
    	var sel = sm.getSelections();
        if(sel.length==0){  
	        Ext.Msg.alert('错误','请选择角色');  
	        return;  
    	}
    	if(Ext.getCmp('pwdId').getValue()!=Ext.getCmp('pwdId2').getValue()){  
	        Ext.Msg.alert('错误','两次输入的密码不一致');  
	        return;  
    	} 
        var roleIdArr=new Array();
        
        for(var i=0;i<sel.length;i++){
        	roleIdArr[i]=sel[i].get('roleId');
        }
        if (gridForm.form.isValid()){
	        gridForm.getForm().submit({
	         success: function(f,a){
	            Ext.Msg.alert('成功', '保存成功');
	         },
	         failure: function(f,a){
	            Ext.Msg.alert('失败', '保存失败');
	         },
	         params : { action : 'submit',roleIdArr:roleIdArr }  
	      });
       }
    }

    var gridForm = new Ext.FormPanel({
        id: 'user-form',
        url: 'security/createUser.ac',
        renderTo: 'newUser',
        title:'新建用户',
        frame: true,
        labelAlign: 'left',
        bodyStyle:'padding:1px',
        width: 752,
        layout: 'column',
        items:[{
	        columnWidth: 0.6,
	        layout: 'fit',
	        items: userGrid
        	},{
        	columnWidth: 0.4,
        	xtype: 'fieldset',
            labelWidth: 60,
            //title:'用户信息',
			defaultType: 'textfield',
            autoHeight: true,
            bodyStyle: Ext.isIE ? 'padding:0 0 5px 15px;' : 'padding:10px 15px;',
            border: false,
            style: {
                "margin-left": "10px", // when you add custom margin in IE 6...
                "margin-right": Ext.isIE6 ? (Ext.isStrict ? "-10px" : "-13px") : "0"  // you have to adjust for it somewhere else
            },
            items: [{
            	fieldLabel: '用户名',
                id:'userNameId',
			    name:'userName',
                //vtype:'alpha',
			    allowBlank: false,
                blankText: '用户名不能为空'
            },{
            	fieldLabel: '密码',
                id:'pwdId',
			    name:'pwd',
			    inputType: 'password',
			    allowBlank: false,
                blankText: '密码不能为空'
            },{
            	fieldLabel: '确认密码',
                id:'pwdId2',
			    name:'pwd2',
			    inputType: 'password',
			    allowBlank: false,
                blankText: '密码不能为空'
            },{
            	fieldLabel: '描述',
                id:'userDescId',
			    name:'userDesc'
            },{
    			xtype: 'radio',
    			//id:'disabledId',
                name: 'disabled',
                inputValue: 'false',
                boxLabel: '激活',
                checked:true
            },{
				xtype: 'radio',
				//id:'disabledId',
				name: 'disabled',
				inputValue: 'true',
				boxLabel: '停用'
            }]
        }],
        buttons: [{
            text: '保存',
            handler: save,
            style: 'margin: 0 0 0 10px'
        	},{
            text: '清空',
            handler: function(){ 
                gridForm.form.reset();
            }
        }]
	});