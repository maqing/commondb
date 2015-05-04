 var moneyTypeData = [['1', '人民币'], ['2', '美元'], ['3', '欧元'], ['4', '日元'], ['5', '其他']];
 var moneyTypeDS = new Ext.data.Store({
    proxy: new Ext.data.MemoryProxy(moneyTypeData),
    reader: new Ext.data.ArrayReader({}, [
    {
        name: 'id'
    }, {
        name: 'name'
    }])
});
moneyTypeDS.load();
var msg = function(title, msg){
        Ext.Msg.show({
            title: title,
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: Ext.Msg.INFO,
            buttons: Ext.Msg.OK
        });
};

Ext.ns('Example');


ConceptsPanel = function() {
    ConceptsPanel.superclass.constructor.call(this, {addNodeWin:null});
    ConceptsPanel.superclass.constructor.apply(this, arguments);
    this.on('contextmenu', this.onContextMenu, this);

    this.on('checkchange', this.onCheckchange, this);

};

var win;

Ext.extend(ConceptsPanel, Ext.tree.TreePanel, {

    onCheckchange: function(node, checked){
        if(checked){
        	/*
            this.suspendEvents( false ) ;

            var previousCheckeds = this.getChecked();
            for( var i = 0 ; i < previousCheckeds.length ; i ++ )
            {
                if(previousCheckeds[i] != node) previousCheckeds[i].getUI().toggleCheck(false);;
            }

            Ext.getCmp('h_' + this.attrId).setValue(node.id);
            this.resumeEvents(  ) ;
            */
        }else
        {
            Ext.getCmp('h_' + this.attrId).setValue('');
        }


    },

    onContextMenu : function(node, e) {
        node.select();
        var selectedNode = this.getSelectionModel().getSelectedNode();
        if(!this.menu) {
            this.menu = new Ext.menu.Menu({
                id: 'concepts-ctx',
                items: [{
                    id: 'addItem',
                    iconCls: 'add-item',
                    text: '增加子节点',
                    handler: this.addItem,
                    scope: this
                },
                {
                    id: 'deleteItem',
                    iconCls: 'delete-item',
                    text: '删除',
                    handler: this.deleteItem,
                    scope: this
                },
                {
                    id: 'editItem',
                    iconCls: 'edit-item',
                    text: '编辑节点',
                    handler: this.editItem,
                    scope: this
                }],
                listeners: {
                    /*
                    itemclick: function(item) {
                        switch (item.id) {
                            case 'deleteItem':
                                alert('只有管理员才可以删除');//selectedNode.attributes.id);
                                break;
                        }
                    }*/
                }
            });
        }
        this.menu.showAt(e.getXY());
    },

    deleteItem: function(attrs, inactive, preventAnim) {
        if(confirm('该节点以及和其子孙都会被删除，你确定么?'))
        {
            var nodeId = this.getSelectionModel().getSelectedNode().attributes.id;
            var selectNode = this.getSelectionModel().getSelectedNode();

            new Ext.data.Connection().request({
			    url: '../admin/delNode.ac?nodeId='  + nodeId,
			    //params: {id: selectedPersons\[0\].get("id")},
			    failure: function(fp, o)
                    {
                                Ext.Msg.alert('失败', '删除失败');
                    },
			    success: function(fp, o)
                    {
                        //o.result
                        selectNode.remove();
                        Ext.Msg.alert('成功', '删除成功');
                     }
			});
        }

        //this.getSelectionModel().getSelectedNode().attributes.id
    },

    editItem : function(attrs, inactive, preventAnim) {
       var nodeId = this.getSelectionModel().getSelectedNode().attributes.id;
       var selectNode = this.getSelectionModel().getSelectedNode();



       if(!this.editNodeWin)
        {
            this.editNodeForm = f = new Ext.FormPanel({
                 selectNode:selectNode,
                 labelWidth: 75, // label settings here cascade unless overridden
                    url:'../admin/editNode.ac?nodeId=' + nodeId,
                    frame:true,
                    title: '编辑子节点',
                    bodyStyle:'padding:5px 5px 0',
                    width: 350,
                    defaults: {width: 230},
                    defaultType: 'textfield',

                    items: [{
                            fieldLabel: '名字',
                            name: 'nodeName',
                            allowBlank:false,
                            value:selectNode.text,
                            selected:true
                        }],
                   buttons: [{
                        text: '保存',
                        handler: function(){

                            f.getForm().submit(
                                {
                                clientValidation: true,
                                 waitMsg: '提交中...',
                                 waitTitle: '请等待',
                                url: '../admin/editNode.ac',
                                params: {
                                nodeId: f.nodeId
                                },
                               success: function(fp, o){
                                //o.result
                                f.selectNode.setText(o.result.data.content);

                                Ext.Msg.alert('成功', '编辑成功');
                                },
                                    failure: function(form, action) {
                                        switch (action.failureType) {
                                            case Ext.form.Action.CLIENT_INVALID:
                                                Ext.Msg.alert('失败', '值非法，请确认输入无误');
                                                break;
                                            case Ext.form.Action.CONNECT_FAILURE:
                                                Ext.Msg.alert('失败', '通信失败');
                                                break;
                                            case Ext.form.Action.SERVER_INVALID:
                                               Ext.Msg.alert('失败', action.result.msg);
                                       }
                                    }
                                }
                            );
                            win.hide();
                         }
                    },{
                        text: '取消',
                         handler: function(){
                        win.hide();
                         }
                    }]


                });

            this.editNodeWin =win= new Ext.Window({

                layout:'fit',
                width:500,
                height:300,
                closeAction:'hide',
                plain: true,

                items: this.editNodeForm


            });

        }

        this.editNodeForm.nodeId = nodeId;
        this.editNodeForm.selectNode = selectNode;

        this.editNodeWin.show(Ext.getBody());

    },

    addItem : function(attrs, inactive, preventAnim) {
       var nodeId = this.getSelectionModel().getSelectedNode().attributes.id;
       var selectNode = this.getSelectionModel().getSelectedNode();



       if(!this.addNodeWin)
        {
            this.addNodeForm = f = new Ext.FormPanel({
                 selectNode:selectNode,
                 labelWidth: 75, // label settings here cascade unless overridden
                    url:'../admin/saveNode.ac?nodeId=' + nodeId,
                    frame:true,
                    title: '增加子节点',
                    bodyStyle:'padding:5px 5px 0',
                    width: 350,
                    defaults: {width: 230},
                    defaultType: 'textfield',

                    items: [{
                            fieldLabel: '名字',
                            name: 'nodeName',
                            allowBlank:false
                        }],
                   buttons: [{
                        text: '保存',
                        handler: function(){

                            f.getForm().submit(
	                            {
	                            clientValidation: true,
	                             waitMsg: '提交中...',
                                 waitTitle: '请等待',
	                            url: '../admin/saveNode.ac',
                                params: {
                                nodeId: f.nodeId
                                },
	                           success: function(fp, o){
                                //o.result
                                f.selectNode.expand();
                                f.selectNode.appendChild(new Ext.tree.TreeNode({
                                    leaf:false,
                                    id:o.result.data.valueId,
				                    text:o.result.data.content,
                                    checked: false
				                }));
	                            Ext.Msg.alert('成功', '创建成功');
		                        },
		                            failure: function(form, action) {
		                                switch (action.failureType) {
		                                    case Ext.form.Action.CLIENT_INVALID:
		                                        Ext.Msg.alert('失败', '值非法，请确认输入无误');
		                                        break;
		                                    case Ext.form.Action.CONNECT_FAILURE:
		                                        Ext.Msg.alert('失败', '通信失败');
		                                        break;
		                                    case Ext.form.Action.SERVER_INVALID:
		                                       Ext.Msg.alert('失败', action.result.msg);
		                               }
		                            }
		                        }
                            );
                            win.hide();
                         }
                    },{
                        text: '取消',
                         handler: function(){
                        win.hide();
                         }
                    }]


                });

            this.addNodeWin =win= new Ext.Window({

                layout:'fit',
                width:500,
                height:300,
                closeAction:'hide',
                plain: true,

                items: this.addNodeForm


            });

        }

        this.addNodeForm.nodeId = nodeId;
        this.addNodeForm.selectNode = selectNode;

        this.addNodeWin.show(Ext.getBody());

    },

    afterRender : function() {
        this.addEvents({addItem:true});

        this.on('click', function(node, event){

        });

        ConceptsPanel.superclass.afterRender.call(this);
        this.el.on('contextmenu', function(e) {
            e.preventDefault();
        });
    }
});


 var chooser;
 var g_attrId;
    function insertImage(data, attrId){

        Ext.getDom('image' + g_attrId).src = '../' + data.previewUrl;
        Ext.getCmp('p_server' + g_attrId).setValue(data.picUrl);


    };



var MyDynForm = function(config)
    {


    //Reusable config options here
    Ext.apply(this, {
        config: config,
        fileUpload: true,
        autoScroll:false,
        autoHeight:true,
        frame: true,
        bodyStyle: 'padding: 10px 10px 0 10px;',
        defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
        },

        id: 'new_entity_form_' + config.metaId,
        labelWidth: 75, // label settings here cascade unless overridden
        url:'../admin/createEntity.ac?metaId=' + config.metaId,
        //renderTo: 'new_entity_' + config.metaId,
        frame:true,
        title: '新建实体-' + config.title,

        width: 550,
          items: [ {
            xtype:'fieldset',
            collapsible: true,
            title: '直观属性',
            autoHeight:true,
            defaults: {width: 250},
            //collapsed: true,
            items :(
            function() {
            var items = [];
             items.push({
                    xtype: 'hidden',

                    anchor:'95%',
                    name:'id',
                    id:'id'
            });

            for(var j = 0; j < config.picArray.length ; j++) {
                var fs = new Ext.ux.form.FileUploadField({
                    xtype: 'fileuploadfield',
                    emptyText: '请选择图片',
                    buttonText: '',
                    anchor:'95%',
                    buttonCfg: {
                        iconCls: 'upload-icon'
                    },
                    fieldLabel: config.picArray[j].attrName,
                    name:'myFile',
                    attrId:config.picArray[j].attrId
                    //name: 'p_' + config.picArray[j].attrId
                });

                fs.on('fileselected', function ff(){Ext.getCmp('p_' + this.attrId).setValue(this.value);});

                items.push(fs);
                items.push({
                    xtype: 'hidden',

                    anchor:'95%',

                    fieldLabel: config.picArray[j].attrName,
                    name:'p_' + config.picArray[j].attrId,
                    id:'p_' + config.picArray[j].attrId
                });

                items.push({
                    xtype: 'hidden',

                    anchor:'95%',

                    fieldLabel: config.picArray[j].attrName,
                    name:'p_server' + config.picArray[j].attrId,
                    id:'p_server' + config.picArray[j].attrId
                });

                items.push({
		            layout:'column',
		            items:[{
                         columnWidth:.35,
                            xtype:'label',
                            text: '从服务器：'

                    },

                     {
		              columnWidth:.5,
		             xtype:'box'
		             ,anchor:''
		             ,isFormField:true
		             ,fieldLabel:'Image'

		             ,autoEl:{
		             tag:'div',
                     children:[{
                     id:'image' + config.picArray[j].attrId,
		             tag:'img'
		             ,qtip:'从服务器选择图片'
		             ,src: '../images/default.gif'
		             },{
		             tag:'div'
		             ,style:'margin:0 0 4px 0'
		             ,html:''
		             }]
		             }
		             },{
		                 columnWidth:.15,
	                    xtype:'button',
                        attrId:config.picArray[j].attrId,
                        handler:function ()
					    {

					        if(!chooser){
					            chooser = new ImageChooser({
					                url:'../admin/listPics.ac',
					                width:515,
					                height:350,
					                attrId:this.attrId
					            });
					        }
                            g_attrId = this.attrId;

					        chooser.attrId = this.attrId;

					        chooser.show(this.getEl(), insertImage);
					    },
                        style:'padding:4px 4px 4px 4px',
	                    anchor:'95%'

                    }]
                });
            }
            return items;
            }).createDelegate(this)()
        },{
            xtype:'fieldset',
            title: '描述属性',
            collapsible: true,
            autoHeight:true,
            //defaultType: 'combo',
            defaults: {width: 210},
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.descArray.length ; j++) {
            	//alert(config.descArray[j].propertyId+'和'+j);
	            var descAttrStore = new Ext.data.JsonStore({
				    // store configs
                    autoLoad:true,
				    autoDestroy: true,
				    url: 'admin/listDescAttrData.ac',
                    baseParams:{metaId : config.metaId, columnName:'d_' + config.descArray[j].attrId},
				    // reader configs
				    root: 'data',
				    fields: [
				                 {name: 'd_' + config.descArray[j].attrId, type:'string'}
				             ]
			    });
                descAttrStore.load();
                //descAttrStore.load({params : });
                /*
                items.push({
                    pageSize:15,
			        displayField:'d_' + config.descArray[j].attrId,
                    minchars:0,
			        triggerAction: 'all',
                    enableKeyEvents :true,
			        emptyText:'',
			        selectOnFocus:true,
                    mode:'local',
	                store: descAttrStore,

	                fieldLabel: config.descArray[j].attrName,
	                name: 'd_' + config.descArray[j].attrId
	                });
	            */
				if(config.descArray[j].propertyId==1)
				{
                	items.push(
                    	new Ext.form.DateField({
                        fieldLabel: config.descArray[j].attrName,
                        name: 'd_' + config.descArray[j].attrId,
                        altFormats: 'Y-m-d H:i:s',
					    format:'Y-m-d H:i:s',
					    emptyText:'请选择时间...',
                        allowBlank:false
                    	})
                    )
				}else if(config.descArray[j].propertyId==2)
				{
                	items.push(
                    	new Ext.form.TextField({
                        fieldLabel: config.descArray[j].attrName,
                        name: 'd_' + config.descArray[j].attrId,
                        emptyText:'文本...',
                        allowBlank:false
                    	})
                    )
				}else if(config.descArray[j].propertyId==3)
				{
					items.push(
                    	new Ext.form.TextArea({
                        fieldLabel: config.descArray[j].attrName,
                        name: 'd_' + config.descArray[j].attrId,
                        emptyText:'备注...',
                        allowBlank:false
                    	})
                    )
				}else if(config.descArray[j].propertyId==4)
				{
					items.push(
                    	new Ext.form.TextField({
                        fieldLabel: config.descArray[j].attrName,
                        name: 'd_' + config.descArray[j].attrId,
                        emptyText:'数字...',
                        allowBlank:false
                    	})
                    )
				}else if(config.descArray[j].propertyId==5)
				{
					items.push([
                    	new Ext.form.TextField({
	                        fieldLabel: config.descArray[j].attrName,
	                        name: 'd_' + config.descArray[j].attrId,
	                        emptyText:'货币...',
	                        allowBlank:false

                    	}),
                    	new Ext.form.ComboBox({
							name:'moneyType',
						    store: moneyTypeDS,
						    displayField:'name',
						    valueField : 'id',
						    hiddenName:'moneyType',
						    typeAhead: true,
						    triggerAction: 'all',
						    emptyText:'请选择货币类型...',
						    selectOnFocus:true,
						    allowBlank : false
						})
                    ])
				}else if(config.descArray[j].propertyId==6)
				{
					items.push(
                    	new Ext.form.RadioGroup({
	                        fieldLabel: config.descArray[j].attrName,
	                        //name: 'd_' + config.descArray[j].attrId,
	                        items: [
				                {boxLabel: '是', name: 'd_' + config.descArray[j].attrId, checked: true},
				                {boxLabel: '否', name: 'd_' + config.descArray[j].attrId}

				            ]

                    	})
                    )
				}else if(config.descArray[j].propertyId==7)
				{
					items.push(
                    	new Ext.form.TextField({
                        fieldLabel: config.descArray[j].attrName,
                        name: 'd_' + config.descArray[j].attrId,
                        allowBlank:false,
                        value :'http://'
                    	})
                    )
				}
            }
            return items;
            }).createDelegate(this)()

        },
        {
            xtype:'fieldset',
            title: '层级属性',
            collapsible: true,
            //autoHeight:true,
            defaults: {width: 360},
            defaultType: 'treepanel',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.hierArray.length ; j++) {
            	 nav = new ConceptsPanel({
                    id:'my_treepanel_' + config.hierArray[j].attrId,
                    isFormField:true
                    ,xtype:'treepanel'
                    ,height:120
                    ,autoScroll:true
                    ,border:true
                    ,bodyStyle:'background-color:white;border:1px solid #B5B8C8'
                    ,rootVisible:true
                    //,anchor:'-24 -60'
                    ,root:config.hierArray[j].treeValue /*{
                        nodeType:'async'
                        ,text:'root'
                        ,id:'root'
                        ,expanded:true
                        ,uiProvider:false
                        ,children:config.hierArray[j].treeValue//.children//Example.children
                    }*/,
                    fieldLabel: config.hierArray[j].attrName,
                    attrId : config.hierArray[j].attrId

                   /* listeners: {
			            'checkchange': function(node, checked){
			                if(checked){

                                Ext.getCmp('h_' + this.attrId).setValue(node.id);

			                }else{
			                    //node.getUI().removeClass('complete');
			                }
			            }
			        } */

                    //
                });


                items.push(nav);

                items.push({
                    xtype: 'hidden',

                    anchor:'95%',


                    name: 'h_' + config.hierArray[j].attrId,
                    id: 'h_' + config.hierArray[j].attrId
                });


            }
            return items;
        }).createDelegate(this)()
        },
        /*{
            xtype:'fieldset',
            title: '特征属性',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'combo',
            items :(
            function() {
            var items = [];
            for(var j = 0; j < config.charaArray.length ; j++) {
                var charaAttrStore = new Ext.data.JsonStore({
                    // store configs
                    autoLoad:true,
                    autoDestroy: true,
                    url: 'admin/listDescAttrData.ac',
                    baseParams:{metaId : config.metaId, columnName:'c_' + config.charaArray[j].attrId},
                    // reader configs
                    root: 'data',
                    fields: [
                                 {name: 'c_' + config.charaArray[j].attrId, type:'string'}
                            ]
                });
                charaAttrStore.load();
                //descAttrStore.load({params : });

                items.push({
                    pageSize:15,
                    displayField:'c_' + config.descArray[j].attrId,
                    minchars:0,
                    triggerAction: 'all',
                    enableKeyEvents :true,
                    emptyText:'',
                    selectOnFocus:true,
                    mode:'local',
                    store: charaAttrStore,

                    fieldLabel: config.charaArray[j].attrName,
                        name: 'c_' + config.charaArray[j].attrId
                    });
            }
            return items;
            }).createDelegate(this)()
        },*/{
	        layout: 'fit',
	        items: charaGroupGrid
       },{
	        layout: 'fit',
	        items: metaGrid
       }]

        });
    this.metaId = config.metaId;
    MyDynForm.superclass.constructor.apply(this, arguments);
};
// MyPanel Extends Ext.Panel
Ext.extend(MyDynForm, Ext.FormPanel, {
    myInitFields: function(record,hieraData)
    {
        for(var j = 0; j < this.config.picArray.length ; j++) {
            Ext.getCmp('p_server' + this.config.picArray[j].attrId).setValue(record.data['p_' + this.config.picArray[j].attrId]);
            Ext.getCmp('p_' + this.config.picArray[j].attrId).setValue("");
            //Ext.getDom('image' + this.config.picArray[j].attrId).src=record['p_' + this.config.picArray[j].attrId];
            if(record.data['p_' + this.config.picArray[j].attrId])
            {
                //alert(record.data['p_' + this.config.picArray[j].attrId]);
                Ext.getDom('image' + this.config.picArray[j].attrId).src = '../' + record.data['p_' + this.config.picArray[j].attrId] + '_p.jpg';
            }
            else
            {
                   Ext.getDom('image' + this.config.picArray[j].attrId).src = '../images/default.gif';
            }
        }

        //初始化tree层级属性
        for(var j = 0; j < this.config.hierArray.length ; j++) {
             var treepanel = Ext.getCmp('my_treepanel_' + this.config.hierArray[j].attrId);
             treepanel.getRootNode().expand(true);
            //清空所有节点
            checkedNodeArr = treepanel.getChecked();
            if(checkedNodeArr )
            {
                for(var k = 0 ; k < checkedNodeArr.length ; k ++)
                {
                    checkedNodeArr[k].getUI().toggleCheck(false);
                }
            }

            for(var m = 0 ; m < hieraData.length ; m ++)
            {
                if(hieraData[m].attr_id=this.config.hierArray[j].attrId)
                {
                    var node = treepanel.getNodeById(hieraData[m].value_id);

	                if(node)
	                {
	                    node.getUI().toggleCheck(true);
	                    node.expand();
	                }
                }
            }


        }

    },

    myResetForm: function()
    {
        for(var j = 0; j < this.config.picArray.length ; j++) {
            Ext.getCmp('p_server' + this.config.picArray[j].attrId).setValue("");
            Ext.getCmp('p_' + this.config.picArray[j].attrId).setValue("");
            Ext.getDom('image' + this.config.picArray[j].attrId).src = '../images/default.gif';
        }

        for(var j = 0; j < this.config.hierArray.length ; j++) {
            var treepanel = Ext.getCmp('my_treepanel_' + this.config.hierArray[j].attrId);

	        var nodes=treepanel .getChecked();
	        if(nodes && nodes.length){
	             for(var i=0;i<nodes.length;i++){
	              //设置UI状态为未选中状态
	              nodes[i].getUI().toggleCheck(false);
	              //设置节点属性为未选中状态
	              nodes[i].attributes.checked=false;
	             }
	        }
        }



    },
    myNewFunction: function() {
                for(var j = 0; j < this.config.hierArray.length ; j++) {
             var treepanel = Ext.getCmp('my_treepanel_' + this.config.hierArray[j].attrId);
             treepanel.getRootNode().expand();

        }
    },

    afterRender: function() {
        MyDynForm.superclass.afterRender.apply(this, arguments);

    }
});




