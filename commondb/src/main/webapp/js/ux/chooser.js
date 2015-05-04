/*!
 * Ext JS Library 3.0+
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
var ImageChooser = function(config){
	this.config = config;
    this.attrId = {};
}

ImageChooser.prototype = {
    // cache data by image name for easy lookup
    lookup : {},
    
	show : function(el, callback){
		if(!this.win){
			this.initTemplates();

			this.store = new Ext.data.JsonStore({
			    url: this.config.url,
			    root: 'data',
			    fields: [
			        'picName', 'previewUrl','picUrl',
			        {name:'picSize', type: 'float'},
			        {name:'createTime', type:'date', dateFormat:'timestamp'}
			    ],
			    listeners: {
			    	'load': {fn:function(){ this.view.select(0); }, scope:this, single:true}
			    }
			});
			this.store.load();

			var formatSize = function(data){
		        if(data.picSize < 1024) {
		            return data.picSize + " bytes";
		        } else {
		            return (Math.round(((data.picSize*10) / 1024))/10) + " KB";
		        }
		    };

			var formatData = function(data){
		    	data.shortName = data.picName.ellipse(15);
		    	data.sizeString = formatSize(data);
		    	data.dateString = new Date(data.createTime).format("m/d/Y g:i a");
		    	this.lookup[data.picName] = data;
		    	return data;
		    };

		    this.view = new Ext.DataView({
				tpl: this.thumbTemplate,
				singleSelect: true,
				overClass:'x-view-over',
				itemSelector: 'div.thumb-wrap',
				emptyText : '<div style="padding:10px;">无符合条件的文件</div>',
				store: this.store,
				listeners: {
					'selectionchange': {fn:this.showDetails, scope:this, buffer:100},
					'dblclick'       : {fn:this.doCallback, scope:this},
					'loadexception'  : {fn:this.onLoadException, scope:this},
					'beforeselect'   : {fn:function(view){
				        return view.store.getRange().length > 0;
				    }}
				},
				prepareData: formatData.createDelegate(this)
			});

			var cfg = {
		    	title: '选择图片',
		    	id: 'img-chooser-dlg',
		    	layout: 'border',
				minWidth: 500,
				minHeight: 300,
				modal: true,
				closeAction: 'hide',
				border: false,
				items:[{
					id: 'img-chooser-view',
					region: 'center',
					autoScroll: true,
					items: this.view
                    /*tbar:[{
                    	text: '过滤:'
                    },{
                    	xtype: 'textfield',
                    	id: 'filter',
                    	selectOnFocus: true,
                    	width: 100,
                    	listeners: {
                    		'render': {fn:function(){
						    	Ext.getCmp('filter').getEl().on('keyup', function(){
						    		this.filter();
						    	}, this, {buffer:500});
                    		}, scope:this}
                    	}
                    }, ' ', '-', {
                    	text: '排序:'
                    }, {
                    	id: 'sortSelect',
                    	xtype: 'combo',
				        typeAhead: true,
				        triggerAction: 'all',
				        width: 100,
				        editable: false,
				        mode: 'local',
				        displayField: 'desc',
				        valueField: 'name',
				        lazyInit: false,
				        value: 'picName',
				        store: new Ext.data.ArrayStore({
					        fields: ['name', 'desc'],
					        data : [['picName', '文件名'],['picSize', '文件大小'],['createTime', '创建时间']]
					    }),
					    listeners: {
							'select': {fn:this.sortImages, scope:this}
					    }
				    }]*/
				},{
					id: 'img-detail-panel',
					region: 'east',
					split: true,
					width: 150,
					minWidth: 150,
					maxWidth: 250
				}],
				buttons: [{
					id: 'ok-btn',
					text: '确定',
					handler: this.doCallback,
					scope: this
				},{
					text: '取消',
					handler: function(){ this.win.hide(); },
					scope: this
				}],
				keys: {
					key: 27, // Esc key
					handler: function(){ this.win.hide(); },
					scope: this
				}
			};
			Ext.apply(cfg, this.config);
		    this.win = new Ext.Window(cfg);
		}

		this.reset();
	    this.win.show(el);
		this.callback = callback;
		this.animateTarget = el;
	},

	initTemplates : function(){
		this.thumbTemplate = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="thumb-wrap" id="{picName}">',
				'<div class="thumb"><img src="../{previewUrl}" title="{picName}"></div>',
				'<span>{shortName}</span></div>',
			'</tpl>'
		);
		this.thumbTemplate.compile();

		this.detailsTemplate = new Ext.XTemplate(
			'<div class="details">',
				'<tpl for=".">',
					'<img src="../{previewUrl}"><div class="details-info">',
					'<b>文件名:</b>',
					'<span>{picName}</span>',
					//'<b>文件大小:</b>',
					//'<span>{sizeString}</span>',
					//'<b>创建时间:</b>',
					//'<span>{dateString}</span></div>',
				'</tpl>',
			'</div>'
		);
		this.detailsTemplate.compile();
	},

	showDetails : function(){
	    var selNode = this.view.getSelectedNodes();
	    var detailEl = Ext.getCmp('img-detail-panel').body;
		if(selNode && selNode.length > 0){
			selNode = selNode[0];
			Ext.getCmp('ok-btn').enable();
		    var data = this.lookup[selNode.id];
            detailEl.hide();
            this.detailsTemplate.overwrite(detailEl, data);
            detailEl.slideIn('l', {stopFx:true,duration:.2});
		}else{
		    Ext.getCmp('ok-btn').disable();
		    detailEl.update('');
		}
	},

	filter : function(){
		var filter = Ext.getCmp('filter');
		this.view.store.filter('name', filter.getValue());
		this.view.select(0);
	},

	sortImages : function(){
		var v = Ext.getCmp('sortSelect').getValue();
    	this.view.store.sort(v, v == 'name' ? 'asc' : 'desc');
    	this.view.select(0);
    },

	reset : function(){
		if(this.win.rendered){
			//Ext.getCmp('filter').reset();
			this.view.getEl().dom.scrollTop = 0;
		}
	    this.view.store.clearFilter();
		this.view.select(0);
	},

	doCallback : function(){
        var selNode = this.view.getSelectedNodes()[0];
		var callback = this.callback;
		var lookup = this.lookup;
		this.win.hide(this.animateTarget, function(){
            if(selNode && callback){
				var data = lookup[selNode.id];
                
				callback(data,this.attrId);
			}
		});
    },

	onLoadException : function(v,o){
	    this.view.getEl().update('<div style="padding:10px;">载入文件错误.</div>');
	}
};

String.prototype.ellipse = function(maxLength){
    if(this.length > maxLength){
        return this.substr(0, maxLength-3) + '...';
    }
    return this;
};
