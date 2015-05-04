ConceptsPanel = function() {
    ConceptsPanel.superclass.constructor.call(this, {
        id: 'id-ConceptsPanel',
        region: 'west',
        title: 'Concepts',
        split: true,
        width: 250,
        minSize: 250,
        maxSize: 400,
        collapsible: true,
        margins: '0 0 0 5',
        rootVisible: false,
        lines: false,
        enableDrag: true,
        
        root: new Ext.tree.AsyncTreeNode({
            draggable:false,
            loader : new Ext.tree.TreeLoader({
                 dataUrl: hostaddress+'/xbrleditor/getConceptList.rap',
                 baseParams: {'taxonomyName':reqTaxonomyName}
            })
        }),

        collapseFirst: false
    });
    
    this.on('contextmenu', this.onContextMenu, this);
};


Ext.extend(ConceptsPanel, Ext.tree.TreePanel, { 

    onContextMenu : function(node, e) {
        node.select();
        var selectedNode = this.getSelectionModel().getSelectedNode();
        if(!this.menu) {
            this.menu = new Ext.menu.Menu({
                id: 'concepts-ctx',
                items: [{
                    id: 'addItem',
                    iconCls: 'add-item',
                    text: 'Add Item',
                    handler: this.addItem,
                    scope: this
                },
                {
                    id: 'deleteItem',
                    iconCls: 'delete-item',
                    text: 'Delete',
                    //handler: this.deleteItem(selectedNode.attributes.id),
                    scope: this 
                }],
                listeners: {
                    itemclick: function(item) {
                        switch (item.id) {
                            case 'deleteItem':
                                alert(selectedNode.attributes.id);
                                break;
                        }
                    }
                }
            });
        }       
        this.menu.showAt(e.getXY());
    },

    deleteItem: function(id) {
        alert(this.getSelectionModel().getSelectedNode().attributes.id);
    },

    addItem : function(attrs, inactive, preventAnim) {
        this.fireEvent('addItem');
    },

    afterRender : function() {
        this.addEvents({addItem:true});
    
        this.on('click', function(node, event){
            alert(node.attributes.id);
        });
    
        ConceptsPanel.superclass.afterRender.call(this);
        this.el.on('contextmenu', function(e) {
            e.preventDefault();
        });
    }
});

var TreeTest = function() {

	var root, tree;
	var menuC = new Ext.menu.Menu('mainContext');
	menuC.add(new Ext.menu.CheckItem({
						text : 'Aujourd\'hui'
					}), new Ext.menu.CheckItem({
						text : 'Toutes dates'
					}));

	return {
		init : function() {
			// yui-ext tree
			tree = new Ext.tree.TreePanel('nav', {
						animate : true,
						loader : new Ext.tree.TreeLoader({
									dataUrl : 'get-nodes.php'
								}),
						enableDD : true,
						containerScroll : true,
						dropConfig : {
							appendOnly : true
						}
					});

			// add a tree sorter in folder mode
			new Ext.tree.TreeSorter(tree, {
						folderSort : true
					});

			// set the root node
			root = new Ext.tree.AsyncTreeNode({
						text : 'PtahReg',
						draggable : false, // disable root node dragging
						id : 'PtahReg'
					});
			tree.setRootNode(root);

			// render the tree
			tree.render();

			tree.on('contextmenu', this.menuShow, this);

			root.expand(false, /*no anim*/false);

			// assignConmenu();
		},
\

		menuShow : function(node) {
			// alert ( "menuShow\n" + node.ui.getEl() );
			menuC.show(node.ui.getEl());
		}
	};
}();