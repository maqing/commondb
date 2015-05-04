//---------------------chara----------------------------------

// turn on validation errors beside the field globally
Ext.form.Field.prototype.msgTarget = 'side';
var in_metaId;
var in_entityId;
var charaGroupGrid;
function rEntityChara(entityId){
	in_entityId=entityId;
	groupStore.load({params: { metaId: in_metaId,entityId:in_entityId }});
}

var groupStore;

function newCharaGroupGrid(metaId,response){
	in_metaId=metaId;

	if(!groupStore || groupStore == null){
	
	groupStore = new Ext.data.JsonStore({
	    autoDestroy: true,
	    url: 'admin/listCharaDataByEntity.ac',
		storeId: 'group_Store',
	    root: 'data',
        idProperty: 'dataId',  
        fields: [  
                     {name: 'dataId', mapping: 'dataId'},  
                     {name: 'dataName'},  
                     {name: 'charaDef',convert: function(v, rec) {
    				 											return v.charaName;
    				 }}
                 ] 

	});	
	
	     
	groupStore.on('load',function(s,records){
			var charaDefArr=[];
			charaGroupGrid='';
			/*
		 	s.each(function(r){
		 		
		        if(charaDefArr.indexOf(r.data.charaDef)==-1){ 
		        	charaDefArr.push(r.data.charaDef);
		        	if(charaGroupGrid.length > 0){
		        		charaGroupGrid += ';<br>';
                    }
		        	charaGroupGrid+='<b>'+r.data.charaDef+'</b>:'+r.data.dataName;
		        }else{
		        	charaGroupGrid+=','+r.data.dataName;
		        	
		        }

		    });
		 	document.getElementById("myChara").innerHtml = charaGroupGrid; 
		 	*/
			s.each(function(r){
		 		
		        if(charaDefArr.indexOf(r.data.charaDef)==-1){ 
		        	charaDefArr.push(r.data.charaDef);
		        	if(charaGroupGrid.length > 0){
		        		charaGroupGrid += '\r\n';
                    }
		        	charaGroupGrid+=r.data.charaDef+'：'+r.data.dataName;
		        }else{
		        	charaGroupGrid+='，'+r.data.dataName;
		        	
		        }

		    });
			Ext.getCmp('charaTextArea').setValue(charaGroupGrid);
	});
	createForm(response);
	}
}
        