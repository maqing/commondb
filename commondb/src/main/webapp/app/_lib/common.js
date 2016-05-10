		String.prototype.trim= function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}

		String.prototype.trimrn= function(){
		    return this.replace(/\t\r\n/gi, "");
		}
		$(document).ready(function() {
		$("a").each(function(index)
			{
				var href=$(this)[0].href;

				if(href.lastIndexOf('%') < 0)
				{
					$(this)[0].href=encodeURI($(this)[0].href.trim());
				}

		    }
		    );
		}
		);



		function remind(metaId, entityId)
		{


		    window.open("/app/tool/preRemind.ac?metaId=" + metaId+"&entityId=" + entityId,'remind',
		    'toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=700,height=500');
		}


		function selectOperationRec(defaultEntity, displayDiv)
		{
		    url = "/app/tool/selectEntity.ac?restrict=1&entityName=" + defaultEntity + "&displayDiv=" + displayDiv + "&disType=1" ;
		    url = encodeURI(url);
		    window.open(url,'选择','toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=810,height=600');
		}


		function delOperationRec(id)
		{
			jQuery.get('/app/tool/delOperationRec.ac',{operationRecId:id},function(data){jQuery("#oper_" + id).remove();});
		}

		function addOperationRec(metaId, entityId, label, operationType, displayDiv, prompt, isParent)
		{
			//alert('begin add');
			jQuery.get('/app/tool/addOperationRec.ac',{metaId:metaId,entityId:entityId,label:label,operationType:operationType},
				function(jsondata){
					//alert(jsondata.flag);
					if (jsondata.flag == 'ERROR') {
						if (prompt.toUpperCase() == 'TRUE')
							alert(jsondata.info);
					}
					else {
						setOperatorBox(metaId, entityId, label, jsondata.tip, jsondata.id, displayDiv, isParent);
						}
					});
			//alert('end add');
		}

		function addOperationRecFile(metaId, entityId, label, filePath, operationType, displayDiv, prompt, isParent)
		{
			//alert('begin add');
			jQuery.get('/app/tool/addOperationRec.ac',{metaId:metaId,entityId:entityId,label:label,operationType:operationType},
				function(jsondata){
					//alert(jsondata.flag);
					if (jsondata.flag == 'ERROR') {
						if (prompt.toUpperCase() == 'TRUE')
							alert(jsondata.info);
					}
					else {
						setOperatorBoxFile(metaId, entityId, label, filePath, jsondata.tip, jsondata.id, displayDiv, isParent);
						}
					});
			//alert('end add');
		}


		function setOperatorBox(metaId, entityID, entityLable, title, operationRecId, displayDiv, isParent)
		{
			var checkedStr = "";
			checkedStr = " <span id='oper_" + operationRecId +"' > "
				+ "<a target='_blank_view_operationRec' href='/app/tool/viewEntity.ac?metaId="
				+ metaId + "&entityId=" + entityID
				+ "' title='" + title + "'>" + entityLable + "</a> "
				+ " <a href='#' onclick='delOperationRec(&quot;" + operationRecId
				+ "&quot;)' ><img src='/app/img/del-icon.png' /></a> <br/></span> ";
			//alert(isParent);
			if (isParent.toUpperCase() == 'TRUE') {
				//alert('set parent');
				window.opener.$("#" + displayDiv)[0].innerHTML += checkedStr;
				}
			else
				$("#" + displayDiv)[0].innerHTML += checkedStr;

		}

		function setOperatorBoxFile(metaId, entityID, entityLable, filePath, title, operationRecId, displayDiv, isParent)
		{
			var checkedStr = "";
			checkedStr = " <span id='oper_" + operationRecId +"' > "
				+ "<a target='_blank_view_operationRec' href='"
				+ filePath
				+ "' title='" + title + "'>" + entityLable + "</a> "
				+ " <a href='#' onclick='delOperationRec(&quot;" + operationRecId
				+ "&quot;)' ><img src='/app/img/del-icon.png' /></a> <br/></span> ";
			//alert(isParent);
			if (isParent.toUpperCase() == 'TRUE') {
				//alert('set parent');
				window.opener.$("#" + displayDiv)[0].innerHTML += checkedStr;
				}
			else
				$("#" + displayDiv)[0].innerHTML += checkedStr;

		}

		function selectHier(attrId, displayDiv, inputFieldId)
		{
		    var initSelect = $("#" + inputFieldId).attr("value");

		    window.open("/app/tool/selectHier.ac?attrId=" + attrId +"&initSelect=" + initSelect+"&displayDiv=" + displayDiv + "&inputFieldId=" + inputFieldId,'选择',
		    'toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=800,height=500');
		}

		function exportOperBox(metaId, entityIdArrId)
		{
		    url = "/app/tool/selectEntity.ac?entityName=" + defaultEntity + "&displayDiv=" + displayDiv + "&inputFieldId=" + inputFieldId;
		    url = encodeURI(url);
		    window.open(url,'选择','toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=650,height=600');
		}
		function selectEntity(defaultEntity, displayDiv, inputFieldId)
		{
		    url = "/app/tool/selectEntity.ac?entityName=" + defaultEntity + "&displayDiv=" + displayDiv + "&inputFieldId=" + inputFieldId;
		    url = encodeURI(url);
		    window.open(url,'选择','toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=810,height=600');
		}

		function operBox(defaultEntity, displayDiv, inputFieldId)
		{
		    url = "/app/tool/selectEntity.ac?restrict=1&entityName=" + defaultEntity + "&displayDiv=" + displayDiv + "&inputFieldId=" + inputFieldId;
		    url = encodeURI(url);
		    window.open(url,'选择','toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=810,height=600');
		}

		function operBoxQueryParam(defaultEntity, displayDiv, inputFieldId,queryParam)
		{
		    url = "/app/tool/selectEntity.ac?restrict=1&entityName=" + defaultEntity + "&displayDiv="
		    	+ displayDiv + "&inputFieldId=" + inputFieldId
		    	+ queryParam;
		    url = encodeURI(url);
		    window.open(url,'选择','toolbar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=810,height=600');
		}

		function removeEntity(metaId, entityId, displayDiv, inputFieldId)
		{
		    var entity = $("#" + inputFieldId).attr("value");
		    var entityArr = entity.split(",");
		    var newEntityStr = "";

		    for( i = 0 ; i < entityArr.length ; i ++)
		    {
		        //alert(metaId + "_" + entityId + "_");
		        //alert(entityArr[i].search(metaId + "_" + entityId + "_"));

		        if(entityArr[i].search(metaId + "_" + entityId + "_") < 0)
		        {
		            newEntityStr += "," + entityArr[i];
		        }
		    }
		    newEntityStr = newEntityStr.substring(1);
		    $("#link_"+metaId+"_" + entityId).remove();
		    $("#del_icon_"+metaId+"_" + entityId).remove();
		    var entity = $("#" + inputFieldId)[0].value=newEntityStr;

		}
		function refreshCField(fieldId)
		{
		    var valueStr = "";
		    $("input[name=checkbox_"+fieldId+"]:checked").each(function(i,n){
		        valueStr += "," + n.value;
		    });
		    valueStr = valueStr.substring(1);
		    $("#" + fieldId)[0].value=valueStr;
		}



		function refreshMoney(fieldId)
		{

			$('#' + fieldId)[0].value= $('#display_' + fieldId)[0].value + " " + $('#unit_' + fieldId)[0].value;
		}

		function initAutoComplete(fieldId,json,url)
		{
		    $("#" + fieldId).autocomplete(json);

		    $("#" + fieldId).result(function(event, data, formatted) {
		        //$("#info_" + fieldId).html("<a href='"+ url +"' >新建</a>");
		    });

		    $("#" + fieldId).blur(function(){
		       $("#" + fieldId).search();
		    });
		}

		function autoCmpltFieldLocalCache(fieldId, entityName, descFieldName, newEntityUrl)
		{
		    url = "/app/tool/autoTip.ac?entityName=" + entityName + "&descFieldName="+descFieldName;
		    url = encodeURI(url);

		    $.getJSON(url , {}, function(json) {
		           initAutoComplete(fieldId,json,newEntityUrl);
		    });
		}

		function autoCmpltField(fieldId, entityName, descFieldName, newEntityUrl)
		{
		    $("#" + fieldId).autocomplete("/app/tool/autoTip.ac?entityName=" + entityName + "&descFieldName="+descFieldName, {
			 selectFirst: false,
		     multiple: true,
		     width:150,
		     max:50,
		     max:50,
		     multipleSeparator: '',
		     dataType: 'json',
		     parse: function(data) {
		       var rows = [];
		       for(var i=0; i<data.length; i++){
		        rows[rows.length] = {
		          data:data[i],
		          value:data[i],
		          result:data[i]
		          };
		        }
		        return rows;
		       },
		     formatItem: function(row, i, n) {
		        return row;
		    }
		  });

		    $("#" + fieldId).result(function(event, data, formatted) {
		        if (data)
		            alert(data);
		        else
		            alert('gg');
		    });
		}

		function delRec(delMetaId, delEntityId)
		{
			if (confirm("你确信要删除该记录？"))
			{
				//jQuery.get('/admin/delEntity.ac',{metaId:${meta.getId()},entityId:'$row.get(2).getValue()'},
				//			function(data){jQuery('#trentity_$row.get(2).getValue()').remove();})
				jQuery.get('/admin/delEntity.ac',{metaId:delMetaId,entityId:delEntityId},function(data){jQuery("#trentity_"+delEntityId).remove();});
			}
		}

		function delMasterAndDetailRec(delMetaId, delEntityId)
		{
			if (confirm("你确信要删除该记录？"))
			{
				removeEntity(delMetaId, delEntityId, '关联属性_display','relation');
				//jQuery.get('/admin/delEntity.ac',{metaId:${meta.getId()},entityId:'$row.get(2).getValue()'},
				//			function(data){jQuery('#trentity_$row.get(2).getValue()').remove();})
				jQuery.get('/admin/delEntity.ac',{metaId:delMetaId,entityId:delEntityId},function(data){jQuery("#trentity_"+delEntityId).remove();});
			}
		}


		function initAllSelect()
		{
					$("#entityName").bind("change",reSelectEntity);

					$('#checkAll').toggle(function(){
			            $("input[name*=checkentity]").each(function(){
			                $(this).attr('checked',true);
			            });
			            $(this).html('取消');
			        },function(){
			            $("input[name*=checkentity]").each(function(){
			                $(this).attr('checked',false);
			            });
			            $(this).html('全选');
			        });

		}

		function reSelectEntity()
		{
			$("#searchForm")[0].submit();
		}

		function mergeEntity(actionAddress)
		{

			var mergeId = "";
			var rec = 0;
			$("input[name*=checkentity]:checked").each(function(){
				id = $(this).attr('id').replace("checkentity_","");
				if (rec==0)
	        		mergeId +=  id;
	        	else
	        		mergeId += "," + id;
	        	rec = rec +1;
	        });


			//alert(mergeId);
			//$("#${mergeEntityIds}")[0].value = mergeId;
			//$("#mergeEntityIds").value = mergeId;
			document.searchForm.mergeEntityIds.value = mergeId;
			if (rec==0)
			{
				alert("请至少选择一条记录");
			}
			else
			{
				//window.location.href="/app/customer/preMergeEntity.ac"
				document.searchForm.action = actionAddress;
				//document.searchForm.method = "post";
				document.searchForm.submit();
			}
		}

		function uploadMultiFile(mySessionid, fileDiretory)
		{
			$('#fileUpload').uploadify({
	    		'uploader'  : '../_lib/uploadify/uploadify.swf',
	    		//'script'    : '/app/tool/uploadFile.ac',
	    		'script'    : '/app/tool/uploadFile.ac;jsessionid=' + mySessionid,
	    		'method'	: 'post',
				'scriptData' : {'fileDiretory':fileDiretory},
				//'cancelImg' : '../_lib/uploadify/cancel.png',
				'cancelImg' : '/app/img/del-icon.png',
	    		'multi'     : true,
	    		'auto'      : false,
	    		'removeCompleted' : false,
	    		'queueID'   : 'fileQueue',
				'fileDataName'  : 'fileUpload',
	    		//'buttonText': 'browse',
	 			'buttonImg'      : '/app/img/add-icon.png',
	 			'height' : '16',
	 			'width' : '16',
	 			'wmode'          : 'transparent',
	     		'sizeLimit': 10*1024*1024,
	    		//'fileExt':'*.jpg;*.png;*.jpeg;*.gif;*.bmp', //这里控制浏览时只能选择的文件格式
	  			//'fileDesc':'请选择jpg,png,jpeg,gif,bmp图片', //这里显示格式的提示
				onSelect: function(event, queueID, fileObj){
					if(fileObj.size>10*1024*1024)//这里判断上传文件的大小以b为单位
					{
						alert("文件大小不可超过10MB!\n请处理好后再上传!");
						return false; //这里返回false就不会在上传队列中添加
					}
						else
					{
						return true;
					}
				},
				onComplete: function (event, queueID, fileObj, response, data) {
	            	//var jsonObject=eval('('+response+')');
	            	//var jsonObject=jQuery.parseJSON(response)
	            	//var fileName=jsonObject.fileSaveName;//得到回调的路径。
	            	var fileName = eval('('+response+')');

	            	var attachfileName = "*" + fileName;
	            	if ($('#attachNameList')[0].value.trim() == "")
	            	{
	            		attachfileName = attachfileName.substring(1);
	            	}
	            	$('#attachNameList')[0].value += attachfileName;
					//$('<li></li>').appendTo('.files').text(fileObj.name + ' change to:' + fileName);
				},
				onError: function(event, queueID, fileObj, errorObj) {
				    alert("文件:" + fileObj.name + "上传失败" + errorObj.type + ' Error: ' + errorObj.info);
				},
	       		onCancel: function(event, queueID, fileObj){
	       			//alert("取消了" + fileObj.name);
	       		},
				onAllComplete : function(event,data) {
					if (data.errors > 0) {
				    	alert("文件上传失败，请检查后再提交。");
	          		} else {
	          			$('#newEntity').submit();
	          		}
	        	}
	       	});
		}

		function submitUploadMultiFile() {
			var num = $('#fileUpload').uploadifySettings('queueSize');
			if (num == 0) {
				//没有选择文件,直接提交
				//alert($('#attachNameList').attr("value"));
          		$('#newEntity').submit();
			}
			else{
				//先上传文件，由uploadify自己提交
				$('#fileUpload').uploadifySettings('scriptData', { 'fileDiretory': calcFileDirectory() });

				$('#fileUpload').uploadifyUpload();
			}
		}


		function removeAttachment(attachmentId, attachmentPath, inputFieldId)
		{
		    var attachment = $("#" + inputFieldId).attr("value");
		    var attachmentArr = attachment.split("*");
		    var newAttachmentStr = "";

		    for( i = 0 ; i < attachmentArr.length ; i ++)
		    {

		        if(attachmentArr[i].search(attachmentPath) < 0)
		        {
		            newAttachmentStr += "*" + attachmentArr[i];
		        }
		    }
		    newAttachmentStr = newAttachmentStr.substring(1);
		    $("#" + attachmentId).remove();
		    $("#del_icon_" + attachmentId).remove();
		    var attachment = $("#" + inputFieldId)[0].value=newAttachmentStr;

		}

	    function calcFileDirectory()
	    {
	    	var fileDirectory = "";

	    	if (document.getElementById("c_4") == null) return "/";

	    	var fileTypeDir = "";
		    var fileType = $("#c_4").attr("value");
		    var fileTypeArr = fileType.split(",");
		    for( i = 0 ; i < fileTypeArr.length ; i ++)
		    {
		    	if (fileTypeArr[i].length>0) {
	            	fileTypeDir = $("#checkbox_c_4_" + fileTypeArr[i])[0].nextSibling.nodeValue + "/";
	            }
		    }

	    	var bussinessTypeDir = "";
		    var bussinessType = $("#c_8").attr("value");
		    var bussinessTypeArr = bussinessType.split(",");
		    for( i = 0 ; i < bussinessTypeArr.length ; i ++)
		    {
		    	if (bussinessTypeArr[i].length>0) {
	            	bussinessTypeDir = $("#checkbox_c_8_" + bussinessTypeArr[i])[0].nextSibling.nodeValue + "/";
	            }
		    }

	    	var companyTypeDir = "";
		    var companyType = $("#c_9").attr("value");
		    var companyTypeArr = companyType.split(",");
		    for( i = 0 ; i < companyTypeArr.length ; i ++)
		    {
		    	if (companyTypeArr[i].length>0) {
	            	companyTypeDir = $("#checkbox_c_9_" + companyTypeArr[i])[0].nextSibling.nodeValue + "/";
	            }
		    }

		    fileDirectory = "/" + fileTypeDir + bussinessTypeDir + companyTypeDir;

		    //alert(fileDirectory);
		    return fileDirectory;
	    }

	    //打开新窗口，类似link
	    function openNewWindowToBlank(actionAddress)
	    {
	    	var selfForm = document.forms[0];
	    	var oldFormTarget = selfForm.target;
	    	var oldFormAction = selfForm.action;

	    	selfForm.target="_blank";
        	selfForm.action=actionAddress;

        	selfForm.submit();
        	selfForm.target=oldFormTarget;
        	selfForm.action=oldFormAction;
	    }

		function updateRelationListForm(relationForm, relationDivId, relationUrl) {
			$.ajax({
				url:relationUrl,
				type:'post',
				dataType:'html',
				data:$("#" + relationForm).serialize(),
				error: function(){alert('error');},
				success:function(data){
					$("#"+relationDivId).html(data);
				}
			});
			
		}

		function addByTransDate(dateParameter, num) {
		    var translateDate = "", dateString = "", monthString = "", dayString = "";
		    translateDate = dateParameter.replace("-", "/").replace("-", "/");
		    var newDate = new Date(translateDate);
		    newDate = newDate.valueOf();
		    newDate = newDate + num * 24 * 60 * 60 * 1000;
		    newDate = new Date(newDate);
		    //如果月份长度少于2，则前加 0 补位  
		    if ((newDate.getMonth() + 1).toString().length == 1) {
		monthString = 0 + "" + (newDate.getMonth() + 1).toString();
		    } else {
		monthString = (newDate.getMonth() + 1).toString();
		    }
		    //如果天数长度少于2，则前加 0 补位  
		    if (newDate.getDate().toString().length == 1) {
		dayString = 0 + "" + newDate.getDate().toString();
		    } else {
		dayString = newDate.getDate().toString();
		    }
		    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString + " 00:00:00";
		    return dateString;
		}
		 
		function reduceByTransDate(dateParameter, num) {
		    var translateDate = "", dateString = "", monthString = "", dayString = "";
		    translateDate = dateParameter.replace("-", "/").replace("-", "/");
		    var newDate = new Date(translateDate);
		    newDate = newDate.valueOf();
		    newDate = newDate - num * 24 * 60 * 60 * 1000;
		    newDate = new Date(newDate);
		    //如果月份长度少于2，则前加 0 补位  
		    if ((newDate.getMonth() + 1).toString().length == 1) {
		monthString = 0 + "" + (newDate.getMonth() + 1).toString();
		    } else {
		monthString = (newDate.getMonth() + 1).toString();
		    }
		    //如果天数长度少于2，则前加 0 补位  
		    if (newDate.getDate().toString().length == 1) {
		dayString = 0 + "" + newDate.getDate().toString();
		    } else {
		dayString = newDate.getDate().toString();
		    }
		    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString + " 00:00:00";
		    return dateString;
		}
		 
		//得到日期  主方法
		function showTime(pdVal) {
		    var trans_day = "";
		    var cur_date = new Date();
		    var cur_year = new Date().getFullYear();
		    var cur_month = cur_date.getMonth() + 1;
		    var real_date = cur_date.getDate();
		    cur_month = cur_month > 9 ? cur_month : ("0" + cur_month);
		    real_date = real_date > 9 ? real_date : ("0" + real_date);
		    eT = cur_year + "-" + cur_month + "-" + real_date;
		    if (pdVal == 1) {
		trans_day = addByTransDate(eT, 1);
		    }
		    else if (pdVal == -1) {
		trans_day = reduceByTransDate(eT, 1);
		    }
		    else {
		trans_day = eT  + " 00:00:00";
		    }
		   //处理
		    return trans_day;
		}
